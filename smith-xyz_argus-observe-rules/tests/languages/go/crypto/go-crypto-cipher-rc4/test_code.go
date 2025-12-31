package gocryptocipherrc4

import (
	"crypto/cipher"
	"crypto/rc4"
	"io"
)

func rc4Basic() {
	key := []byte("key")

	cipher1, _ := rc4.NewCipher(key)

	cipher2 := rc4.NewCipher(key)

	stream := rc4.NewCipher(key)

	_, _, _ = cipher1, cipher2, stream
}

func rc4XORKeyStream() {
	key := []byte("key")
	cipher, _ := rc4.NewCipher(key)

	dst := make([]byte, 16)
	src := make([]byte, 16)
	cipher.XORKeyStream(dst, src)

	_ = dst
}

func rc4XORKeyStreamPattern() {
	key := []byte("key")
	cipher, _ := rc4.NewCipher(key)
	dst := make([]byte, 16)
	src := make([]byte, 16)
	cipher.XORKeyStream(dst, src)

	_ = dst
}

func rc4StreamReader() {
	key := []byte("key")
	cipher, _ := rc4.NewCipher(key)
	reader := io.Reader(nil)

	streamReader := cipher.StreamReader{S: cipher, R: reader}

	_ = streamReader
}

func rc4StreamWriter() {
	key := []byte("key")
	cipher, _ := rc4.NewCipher(key)
	writer := io.Writer(nil)

	streamWriter := cipher.StreamWriter{S: cipher, W: writer}

	_ = streamWriter
}

func rc4StreamReaderPattern() {
	key := []byte("key")
	cipher, _ := rc4.NewCipher(key)
	input := io.Reader(nil)

	reader := cipher.StreamReader{S: cipher, R: input}

	_ = reader
}

func rc4StreamWriterPattern() {
	key := []byte("key")
	cipher, _ := rc4.NewCipher(key)
	output := io.Writer(nil)

	writer := cipher.StreamWriter{S: cipher, W: output}

	_ = writer
}

func rc4InterfaceUsage() {
	key := []byte("key")

	var stream cipher.Stream = rc4.NewCipher(key)

	stream2 := cipher.Stream(rc4.NewCipher(key))

	_, _ = stream, stream2
}

func rc4FunctionParameter() {
	key := []byte("key")

	useStream(rc4.NewCipher(key))
}

func useStream(s cipher.Stream) {
	_ = s
}

func rc4IOCopyPattern() {
	key := []byte("key")
	cipher, _ := rc4.NewCipher(key)
	reader := io.Reader(nil)
	writer := io.Writer(nil)

	io.Copy(writer, &cipher.StreamReader{S: cipher, R: reader})
}

func rc4LoopPattern() {
	key := []byte("key")
	cipher, _ := rc4.NewCipher(key)
	data := make([]byte, 16)
	dst := make([]byte, 16)
	src := make([]byte, 16)

	for i := 0; i < len(data); i++ {
		cipher.XORKeyStream(dst, src)
	}

	_ = data
}

func rc4ErrorCheckPattern() {
	key := []byte("key")
	cipher, err := rc4.NewCipher(key)
	if err == nil {
		dst := make([]byte, 16)
		src := make([]byte, 16)
		cipher.XORKeyStream(dst, src)
	}
}
