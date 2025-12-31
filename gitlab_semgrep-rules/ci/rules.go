package main

import (
	"fmt"
	"io/fs"
	"os"
	"path/filepath"
	"strings"
)

// Single Source of Truth to list all rules including tests.
// Do not reimplement similar logic elsewhere.
func ListRuleFilesWithTests() []string {
	var result []string

	root := ".."

	_, err := os.Stat(filepath.Join(root, ".gitlab-ci.yml"))
	if err != nil {
		panic(fmt.Errorf("expected .gitlab-ci.yml in parent dir: %w", err))
	}

	err = filepath.WalkDir(root, func(path string, d fs.DirEntry, err error) error {
		if err != nil {
			return err
		}

		if path != root && d.IsDir() && strings.HasPrefix(d.Name(), ".") {
			// skip dot subfolders
			return fs.SkipDir
		}

		if !strings.HasPrefix(d.Name(), "rule-") {
			return nil
		}

		if !d.Type().IsRegular() {
			panic(fmt.Errorf("rule is not a regular file: %s", path))
		}

		if strings.HasPrefix(path, "../qa/") {
			// TODO Remove once Ruby is gone
			return nil
		}

		result = append(result, path)

		return nil
	})
	if err != nil {
		panic(err)
	}

	return result
}

func ListRuleFiles() []string {
	var result []string

	for _, file := range ListRuleFilesWithTests() {
		if strings.HasSuffix(file, ".yml") {
			result = append(result, file)
		}
	}

	return result
}

func ListTestFiles() []string {
	var result []string

	for _, file := range ListRuleFilesWithTests() {
		if !strings.HasSuffix(file, ".yml") {
			result = append(result, file)
		}
	}

	return result
}
