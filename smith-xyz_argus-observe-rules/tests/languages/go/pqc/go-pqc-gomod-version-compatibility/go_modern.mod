module example-modern

// SHOULD NOT BE FLAGGED: Go version 1.24+ supports PQC
go 1.24.0

require (
    github.com/example/dependency v2.0.0
)
