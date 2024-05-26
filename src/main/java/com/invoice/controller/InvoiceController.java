package com.invoice.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.entity.InvoiceEntity;
import com.invoice.service.InvoiceService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    
    @Operation(summary = "Tesing The Controller",
    	       description = "Jai Bajrang Bali")
    @GetMapping("/test")
    public String test()
    {
    	return "Jai Bajrang Bali";
    }

    @GetMapping("/allInvoices")
    public List<InvoiceEntity> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<InvoiceEntity> getInvoiceById(@PathVariable Long id) {
        Optional<InvoiceEntity> invoice = invoiceService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addInvoice")
    public ResponseEntity<InvoiceEntity> createInvoice(@RequestBody InvoiceEntity invoice) {
    	InvoiceEntity createdInvoice = invoiceService.createInvoice(invoice);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @PutMapping("/updateInvoice/{id}")
    public ResponseEntity<InvoiceEntity> updateInvoice(@PathVariable Long id, @RequestBody InvoiceEntity updatedInvoice) {
    	InvoiceEntity invoice = invoiceService.updateInvoice(id, updatedInvoice);
        return invoice != null ? ResponseEntity.ok(invoice) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
    

    @GetMapping("/export")
    public ResponseEntity<String> exportInvoicesToCsv() throws IOException {
      List<String[]> csvData = invoiceService.getInvoicesForCsv();
      
      StringBuilder csvString = new StringBuilder();
      for (String[] row : csvData) {
        csvString.append(String.join(",", row) + "\n");
      }
      
      String filename = "invoices_" + LocalDate.now() + ".csv";
      
      return ResponseEntity.ok()
          .header("Content-Type", "text/csv")
          .header("Content-Disposition", "attachment; filename=" + filename)
          .body(csvString.toString());
    }
}

