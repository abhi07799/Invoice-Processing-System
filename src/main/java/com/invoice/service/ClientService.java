package com.invoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.invoice.entity.ClientEntity;
import com.invoice.entity.InvoiceEntity;
import com.invoice.exception.CustomException;
import com.invoice.repository.ClientRepository;
import com.invoice.repository.InvoiceRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ClientService
{
	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private InvoiceRepository invoiceRepository;
	
//	@Autowired
//	private JavaMailSender emailService;

	public ClientEntity addClient(ClientEntity user)
	{
		log.info("Creating Client...");
		try
		{
			ClientEntity ent = clientRepo.save(user);
			log.info("Client Created Successfully");
			return ent;
		} catch (Exception ex)
		{
			log.error("Error in Client Creation");
			if (ex instanceof DataIntegrityViolationException)
			{
				throw new CustomException("Something Wrong With Data", 400);
			} else
			{
				throw new CustomException(ex.getLocalizedMessage(), 500);
			}

		}
	}

	public ClientEntity getClientById(Long id)
	{
		log.info("Getting Client By Id...");
		try
		{
			Optional<ClientEntity> clientOptional = clientRepo.findById(id);
			if (clientOptional.isPresent())
			{
				ClientEntity client = clientOptional.get();
				// Fetch invoices for this client (replace with your logic)
				List<InvoiceEntity> invoices = invoiceRepository.findByClient(client);
				client.setClientInvoices(invoices); // Assuming a setter for invoices list
				return client;
			}
			else
			{
				return null;
			}
		} catch (Exception ex)
		{
			log.error("Error in Get Client By Id");
			throw new CustomException(ex.getLocalizedMessage(), 404);
		}
	}

	public List<ClientEntity> getAllClients()
	{
		log.info("Getting All Clients...");
		try
		{
			log.info("All Clients Retrieved Successfully");
			return clientRepo.findAll();
		} catch (Exception ex)
		{
			log.error("Error in Get All Clients");
			throw new CustomException(ex.getLocalizedMessage(), 404);
		}
	}
	
	//for sending an email
		 public void sendSimpleEmail(Long id) {
//			    SimpleMailMessage message = new SimpleMailMessage();
//			    message.setFrom("ghostofuchiha7799@gmail.com");
//			    ClientEntity client= getClientById(id);
//			    message.setTo(client.getClientEmail());
//			    message.setSubject("Your Invoices");
//			    message.setText("invoice");
//			    emailService.send(message);
			  }

	
}
