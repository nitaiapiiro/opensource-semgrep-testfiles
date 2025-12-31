import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.*
import kotlin.io.path.Path
import kotlin.io.path.writeText
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PathTraversalMethodSinksTestCases {

    // File Methods
    @PostMapping("/file/operations")
    fun testFileOperations(
        @RequestParam("filename") filename: String,
        @RequestParam("newName") newName: String,
        @RequestParam("content") content: String
    ): String {
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val file = File(filename)
        
        // VULNERABLE: File.createNewFile()
        val created = file.createNewFile()
        
        // VULNERABLE: File.delete()
        val deleted = file.delete()
        
        // VULNERABLE: File.renameTo(File dest)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val renamed = file.renameTo(File(newName))
        
        // VULNERABLE: File.listFiles()
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val files = File(filename).listFiles()

        // ok: kotlin_pathtraversal_rule-FilePathTraversal
        val files = File("/tmp/safe.txt")
        
        return "Operations performed"
    }
    
    // FileInputStream/FileOutputStream
    @GetMapping("/streams/file")
    fun testFileStreams(@RequestParam("file") filename: String): ByteArray {
        // VULNERABLE: FileInputStream(String name)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val fis1 = FileInputStream(filename)
        
        // VULNERABLE: FileOutputStream(String name)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val fos1 = FileOutputStream(filename)
        
        // VULNERABLE: FileOutputStream(File file, boolean append)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val fos4 = FileOutputStream(File(filename), false)
        
        return fis1.readBytes()
    }
    
    // FileReader/FileWriter
    @PostMapping("/streams/reader-writer")
    fun testFileReaderWriter(
        @RequestParam("inputFile") inputFile: String,
        @RequestParam("outputFile") outputFile: String,
        @RequestParam("content") content: String
    ): String {
        // VULNERABLE: FileReader(String fileName)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val reader1 = FileReader(inputFile)
        
        // VULNERABLE: FileReader(File file)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val reader2 = FileReader(File(inputFile))
        
        // VULNERABLE: FileWriter(String fileName)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val writer1 = FileWriter(outputFile)
        
        // VULNERABLE: FileWriter(String fileName, boolean append)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val writer2 = FileWriter(outputFile, true)
        
        // VULNERABLE: FileWriter(File file)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val writer3 = FileWriter(File(outputFile))
        
        // VULNERABLE: FileWriter(File file, boolean append)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val writer4 = FileWriter(File(outputFile), false)
        
        writer1.write(content)
        writer1.close()
        
        return "Read/Write completed"
    }
    
    // RandomAccessFile
    @GetMapping("/streams/random-access")
    fun testRandomAccessFile(
        @RequestParam("file") filename: String,
        @RequestParam("mode") mode: String
    ): String {
        // VULNERABLE: RandomAccessFile(String name, String mode)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val raf1 = RandomAccessFile(filename, mode)
        
        // VULNERABLE: RandomAccessFile(File file, String mode)
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val raf2 = RandomAccessFile(File(filename), mode)
        
        val content = raf1.readLine()
        raf1.close()
        raf2.close()
        
        return content ?: "Empty file"
    }

    @GetMapping("/write")
    fun writeFile(@RequestParam filename: String): String {
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        val path = Path(filename)
        path.writeText("Hello, World!")
        path.outputStream().use { it.write("Binary Data".toByteArray()) }
        return "File written."
    }
}

@RestController
@RequestMapping("/hello")
internal class HelloWorldController {
    private val logger = LoggerFactory.getLogger(HelloWorldController::class.java)

    @GetMapping("/message", produces = ["application/json"])
    fun greeting(
        @RequestParam(value = "name", required = true) name: String,
        @RequestParam(value = "msg", defaultValue = "Hello Spring") msg: String
    ): HelloMessage {
        // Customer Example
        // ruleid: kotlin_pathtraversal_rule-FilePathTraversal
        File(name).writeText(msg)
        logger.info("Greeting request received with name: $name, message: $msg")
        return HelloMessage(name, msg)
    }
}
