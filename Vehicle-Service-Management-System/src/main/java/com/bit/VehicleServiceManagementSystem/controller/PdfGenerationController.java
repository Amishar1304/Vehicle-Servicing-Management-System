package com.bit.VehicleServiceManagementSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.VehicleServiceManagementSystem.service.PdfGeneratorService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PdfGenerationController {
    private final PdfGeneratorService pdfGeneratorService;

    public PdfGenerationController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdfGenerate/{customerId}")
    public ResponseEntity<String> generatePDF(@PathVariable Long customerId, HttpServletResponse response) {
        try {
            // Set content type to application/pdf
            response.setContentType("application/pdf");
            // Set the file name dynamically based on the customer ID
            response.setHeader("Content-Disposition", "attachment; filename=customer_" + customerId + ".pdf");
            // Generate and export the PDF
            pdfGeneratorService.export(response, customerId);
            return ResponseEntity.ok("PDF generated successfully for customer with ID: " + customerId);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body("Error generating PDF for customer with ID: " + customerId);
        }
    }
}
