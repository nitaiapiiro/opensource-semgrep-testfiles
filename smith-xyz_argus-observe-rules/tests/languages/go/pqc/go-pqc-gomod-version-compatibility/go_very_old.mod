module example-very-old

// SHOULD BE FLAGGED: Very old Go version
go 1.19

require (
    github.com/old/dependency v0.5.0
)
