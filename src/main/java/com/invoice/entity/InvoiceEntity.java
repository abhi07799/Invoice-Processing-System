package com.invoice.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class InvoiceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "invoice_name", nullable = false)
    private String invoiceName;
    
    @Column(name = "invoice_amount", nullable = false)
    private int invoiceAmount;
    
    @Column(name = "invoice_createddate", nullable = false)
    private Date createdDate;
    
    @Column(name = "invoice_duedate", nullable = false)
    private Date dueDate;
    
    @Column(name = "invoice_paiddate")
    private Date paidDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_status")
    private InvoiceStatus status;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private ClientEntity client;
    
    // Constructors, getters, setters
}

