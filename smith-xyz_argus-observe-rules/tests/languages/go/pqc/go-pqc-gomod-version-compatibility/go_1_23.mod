module example-go123

// SHOULD BE FLAGGED: Go 1.23 lacks ML-KEM (needs 1.24+)
go 1.23.0

require (
    github.com/example/dependency v1.5.0
)
