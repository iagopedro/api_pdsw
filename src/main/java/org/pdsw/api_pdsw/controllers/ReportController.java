package org.pdsw.api_pdsw.controllers;

import java.util.List;
import java.util.Optional;

import org.pdsw.api_pdsw.entities.Report;
import org.pdsw.api_pdsw.services.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

  private ReportService reportService;

  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @GetMapping
  public ResponseEntity<List<Report>> getAllReports() {
      List<Report> reports = reportService.getAllReports();
      return ResponseEntity.ok(reports);
  }

  @PostMapping
  public ResponseEntity<Report> createReport(@RequestBody Report report, @RequestParam Long userId, @RequestParam(required = false) Optional<Long> cooperativeId) { 
      Report createdReport = reportService.createReport(report, userId, cooperativeId);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Report>> getReportsByUserId(@PathVariable Long userId) {
      List<Report> reports = reportService.getReportsByUserId(userId);
      return ResponseEntity.ok(reports);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Report> getReportById(@PathVariable Long id) {
      Report report = reportService.getReportById(id);
      return ResponseEntity.ok(report);
  }

  @PutMapping("/schedule")
  public ResponseEntity<Report> scheduleReport(@RequestParam Long reportId, @RequestParam Long cooperativeId) {
      Report updatedReport = reportService.scheduleReport(reportId, cooperativeId);
      return ResponseEntity.ok(updatedReport);
  }

  @PutMapping("/collect")
  public ResponseEntity<Report> collectReport(@RequestParam Long reportId, @RequestParam Long cooperativeId) {
      Report updatedReport = reportService.collectReport(reportId);
      return ResponseEntity.ok(updatedReport);
  }
  
}
