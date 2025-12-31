package main

import (
	"encoding/json"
	"flag"
	"fmt"
	"log"
	"math"
	"os"
	"time"
)

// ProjectSummary contains individual project results
type ProjectSummary struct {
	SourceTotal         int
	SourceScanTimeInSec int
	TargetTotal         int
	TargetScanTimeInSec int
	Lang                string
	Name                string
	URL                 string
}

// SastReportSummary summarizes details of the BAP analysis
type SastReportSummary struct {
	RepoCount         int
	TargetTimeInSec   int
	SourceTimeInSec   int
	Total             int
	SourceTotal       int
	TargetTotal       int
	SourceFailedCount int
	TargetFailedCount int
	ReportDate        string
	SourceVersion     string
	TargetVersion     string
	SourceName        string
	TargetName        string
	ProjectSummary    map[string]*ProjectSummary
}

var (
	reportPath         string
	acceptableTimeDiff time.Duration
	acceptableFailures int
	acceptableVulnDiff int
)

func init() {
	flag.StringVar(&reportPath, "report", "./scan-reports/gl-bap-analysis-report.json", "path to gl-bap-analysis-report")
	flag.DurationVar(&acceptableTimeDiff, "timediff", time.Duration(time.Minute*5), "acceptable amount of time difference allowed between source/target in time.Duration")
	flag.IntVar(&acceptableVulnDiff, "vulndiff", 50, "acceptable amount of vulnerability count differences between source/target")
	flag.IntVar(&acceptableFailures, "fail", 0, "acceptable amount of projects that failed for either source/target")
}

func main() {
	flag.Parse()

	dat, err := os.ReadFile(reportPath)
	if err != nil {
		log.Fatalf("failed to read report: %s\n", err)
	}

	results := &SastReportSummary{}
	if err := json.Unmarshal(dat, &results); err != nil {
		log.Fatalf("failed to parse report: %s\n", err)
	}

	if err := verify(results); err != nil {
		log.Fatalf("Verification failed, exiting with error: %s\n", err)
	}

	log.Print("Verification successful, no major changes identified")
}

func verify(results *SastReportSummary) error {
	for projectName, summary := range results.ProjectSummary {
		timeDiff := float64(summary.TargetScanTimeInSec - summary.SourceScanTimeInSec)
		accept := acceptableTimeDiff.Seconds()
		if math.Abs(timeDiff) > accept {
			return fmt.Errorf("scan time difference for %s of %f exceeded allowed amount %d", projectName, timeDiff, acceptableTimeDiff)
		}
	}

	vulnDiff := float64(results.TargetTotal - results.SourceTotal)
	if math.Abs(vulnDiff) > float64(acceptableVulnDiff) {
		return fmt.Errorf("vulnerability count difference of %f exceeded allowed amount %d", vulnDiff, acceptableVulnDiff)
	}

	// when introducing test projects only consisting of a newly supported language,
	// baseline will fail on it until the next Semgrep version officially supporting it
	// is released. `target` will build Semgrep from sources and should succeed.
	failures := results.TargetFailedCount
	if failures > acceptableFailures {
		return fmt.Errorf("failed project count of %d exceeded allowed amount %d", failures, acceptableFailures)
	}

	return nil
}
