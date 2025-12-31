// License: LGPL-3.0 License (c) find-sec-bugs
// source (original): https://github.com/semgrep/semgrep-rules/blob/release/java/spring/security/injection/tainted-file-path.java
package traversal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import java.nio.file.*;
import javax.xml.transform.*;

@RestController
public class PreflightController {
    static final String CONTENT_DISPOSITION_STATIC_FILE_LOCATION = "contentDispositionUpload";
    private UnrestrictedFileUpload unrestrictedFileUpload;

    public PreflightController(UnrestrictedFileUpload unrestrictedFileUpload) {
        this.unrestrictedFileUpload = unrestrictedFileUpload;
    }

    @RequestMapping(
            CONTENT_DISPOSITION_STATIC_FILE_LOCATION + "/" + "{fileName}")
    public ResponseEntity<byte[]> fetchFile(@PathVariable("fileName") String fileName)
            throws IOException {
        InputStream inputStream =
                // ruleid: java_traversal_rule-RelativePathTraversal
                new FileInputStream(
                        unrestrictedFileUpload.getContentDispositionRoot().toFile()
                                + "/"
                                + fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment");
        return new ResponseEntity<byte[]>(
                IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.OK);
    }

    public static void bad(@RequestParam String user)
    {
        Socket sock;
        BufferedReader filenameReader = new BufferedReader(
                new InputStreamReader(sock.getInputStream(), "UTF-8"));
        String filename = filenameReader.readLine();
        // ruleid: java_traversal_rule-RelativePathTraversal
        BufferedReader fileReader = new BufferedReader(new FileReader("/home/" + user + "/" + filename));
        String fileLine = fileReader.readLine();
        while(fileLine != null) {
            sock.getOutputStream().write(fileLine.getBytes());
            fileLine = fileReader.readLine();
        }
    }

    public static void bad2(@RequestParam String filename)
    {
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext(new String[] {"If-you-have-any.xml"});

        // ruleid: java_traversal_rule-RelativePathTraversal
        Resource resource = appContext.getResource("classpath:com/" + filename);

        try {
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void ok(@RequestParam String filename)
    {
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext(new String[] {"If-you-have-any.xml"});

        // ok: java_traversal_rule-RelativePathTraversal
        Resource resource =
                appContext.getResource("classpath:com/" + org.apache.commons.io.FilenameUtils.getName(filename));

        try {
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenResourceAsFile_thenReadSuccessful(@RequestParam String filename) throws IOException {
        // ruleid: java_traversal_rule-RelativePathTraversal
        File resource = new ClassPathResource("data/employees.dat" + filename).getFile();
        String employees = new String(Files.readAllBytes(resource.toPath()));
        assertEquals("Joe Employee,Jan Employee,James T. Employee", employees);
    }

    public String bad3(@RequestParam String filename) throws IOException {
        // ruleid: java_traversal_rule-RelativePathTraversal
        File resource = new File("data/employees.dat" + filename);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            reader.close();
            return "First line of the file: " + line;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the file";
        }
    }

    public String bad4(@RequestParam String fileName) {
        try {
            // ruleid: java_traversal_rule-RelativePathTraversal
            FileReader fileReader = new FileReader("/path/to/directory/" + fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            reader.close();
            return "First line of the file: " + line;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the file";
        }
    }

    public String bad5(@RequestParam String fileName) {
        try {
            // ruleid: java_traversal_rule-RelativePathTraversal
            Path path = Paths.get("/path/to/directory/" + fileName);
            BufferedReader reader = Files.newBufferedReader(path);
            String line = reader.readLine();
            reader.close();
            return "First line of the file: " + line;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the file";
        }
    }

    public String bad6(@RequestParam String fileName) {
        try {
            // ruleid: java_traversal_rule-RelativePathTraversal
            File file = ResourceUtils.getFile("classpath:" + fileName);
            // ruleid: java_traversal_rule-RelativePathTraversal
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            reader.close();
            return "First line of the file: " + line;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the file";
        }
    }

    public String bad7(@RequestParam String fileName, @RequestParam String content) {
        try {
            // ruleid: java_traversal_rule-RelativePathTraversal
            FileOutputStream fos = new FileOutputStream("/path/to/directory/" + fileName);
            fos.write(content.getBytes());
            fos.close();
            return "File written successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error writing to the file";
        }
    }

    public String bad8(@RequestParam String fileName) {
        try {
            // ruleid: java_traversal_rule-RelativePathTraversal
            StreamSource source = new StreamSource(new File("/path/to/directory/" + fileName));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(source, new StreamResult(writer));
            String result = writer.toString();
            return "File content: " + result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error reading the file";
        }
    }

    public String bad9(@RequestParam String fileName, @RequestParam String content) {
        try {
            // ruleid: java_traversal_rule-RelativePathTraversal
            File file = new File("/path/to/directory/" + fileName);
            // ruleid: java_traversal_rule-RelativePathTraversal
            OutputStream os = FileUtils.openOutputStream(file);
            os.write(content.getBytes());
            os.close();
            return "File written successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error writing to the file";
        }
    }
}
