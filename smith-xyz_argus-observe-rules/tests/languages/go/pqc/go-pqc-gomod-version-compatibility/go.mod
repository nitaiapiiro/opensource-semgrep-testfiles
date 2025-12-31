module example-legacy

// SHOULD BE FLAGGED: Go version prior to 1.24
go 1.23.2

require (
    github.com/example/dependency v1.0.0
)
