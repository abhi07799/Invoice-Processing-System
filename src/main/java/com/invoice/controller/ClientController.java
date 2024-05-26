package com.invoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.entity.ClientEntity;
import com.invoice.service.ClientService;

@RestController
@RequestMapping(path = "api/v1/client/")
public class ClientController
{
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/addClient")
	public ResponseEntity<?> createClient(@RequestBody ClientEntity dto)
	{		
		return new ResponseEntity<>(clientService.addClient(dto),HttpStatus.CREATED);
	}
	
	@GetMapping("getClientById/{id}")
	public ResponseEntity<?> getClientById(@PathVariable Long id)
	{
		return new ResponseEntity<>(clientService.getClientById(id),HttpStatus.OK);
	}
	
	@GetMapping("/allClients")
	public ResponseEntity<?> getAllClients()
	{
		return new ResponseEntity<>(clientService.getAllClients(),HttpStatus.OK);
	}
	
	@GetMapping("/sendMail/{id}")
    public ResponseEntity<?> sendInvoiceOnMail(@PathVariable Long id)
    {
    	clientService.sendSimpleEmail(id);
        return ResponseEntity.ok("Email sent successfully!");
    }
}
