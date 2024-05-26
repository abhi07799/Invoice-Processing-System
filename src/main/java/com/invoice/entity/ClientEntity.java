package com.invoice.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class ClientEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "client_name", nullable = false)
	private String clientName;

	@Column(name = "client_mail", nullable = false)
	private String clientEmail;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER) // Optional: Eager fetching
	 private List<InvoiceEntity> clientInvoices;

	// Constructors, getters, setters
}
