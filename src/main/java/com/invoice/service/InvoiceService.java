package com.invoice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.invoice.entity.ClientEntity;
import com.invoice.entity.InvoiceEntity;
import com.invoice.entity.InvoiceStatus;
import com.invoice.exception.CustomException;
import com.invoice.repository.InvoiceRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class InvoiceService
{

	@Autowired
	private InvoiceRepository invoiceRepository;

	public List<InvoiceEntity> getAllInvoices()
	{
		try
		{
			log.info("Getting All Invoices...");
			List<InvoiceEntity> list = invoiceRepository.findAll();
			log.info("All Invoices Sucessfully Fetched");
			return list;
		} catch (Exception ex)
		{
			log.error("Error in Getting All Invoices");
			throw new CustomException(ex.getLocalizedMessage(), 404);
		}

	}

	public Optional<InvoiceEntity> getInvoiceById(Long id)
	{
		try
		{
			log.info("Getting Invoice By Id...");
			Optional<InvoiceEntity> invoice = invoiceRepository.findById(id);
			log.info("Successfully Received Invoice By Id");
			return invoice;
		} catch (Exception ex)
		{
			log.error("Error in Get Invoice By Id");
			throw new CustomException(ex.getLocalizedMessage(), 404);
		}

	}

	public InvoiceEntity createInvoice(InvoiceEntity invoice)
	{
		try
		{
			log.info("Adding New Invoice...");
			invoice.setCreatedDate(new Date());
			Date dueDate = new Date();
			dueDate.setDate(new Date().getDate() + 7);
			invoice.setDueDate(dueDate);
			InvoiceEntity saved = invoiceRepository.save(invoice);
			log.info("New Invoice Added Successfully");
			return saved;
		} catch (Exception ex)
		{
			log.error("Error in Add New Invoice");
			throw new CustomException(ex.getLocalizedMessage(), 500);
		}

	}

	public InvoiceEntity updateInvoice(Long id, InvoiceEntity updatedInvoice)
	{
		try
		{
			log.info("Updating Invoice Paid Date...");
			Optional<InvoiceEntity> invoiceOptional = getInvoiceById(id);
			if (invoiceOptional.isPresent())
			{
				InvoiceEntity invoice = invoiceOptional.get();
				if (updatedInvoice.getStatus() == InvoiceStatus.PAID)
				{
					log.info("Updated Invoice Successfully");
					invoice.setPaidDate(new Date());
					invoice.setStatus(InvoiceStatus.PAID);
					return invoiceRepository.save(invoice);
				}
			}

		} catch (Exception ex)
		{
			log.error("Error in Get User By Id");
			throw new CustomException(ex.getLocalizedMessage(), 404);
		}
		return null;
	}

	public void deleteInvoice(Long id)
	{
		try
		{
			log.info("Deleting Invoice...");
			invoiceRepository.deleteById(id);
			log.info("Deleted Invoice Successfully");
		} catch (Exception ex)
		{
			log.error("Error in Deleting Invoice");
			throw new CustomException(ex.getLocalizedMessage(), 404);
		}

	}

	public List<String[]> getInvoicesForCsv()
	{
		log.info("Exporting Invoices To CSV File...");
		List<InvoiceEntity> invoices = invoiceRepository.findAll();
		List<String[]> csvData = new ArrayList<>();

		// Header row
		csvData.add(new String[]
		{ "Invoice ID", "Invoice Name", "Invoice Amount", "Created Date", "Due Date", "Paid Date", "Status",
				"Client Name", "Client Email" });

		for (InvoiceEntity invoice : invoices)
		{
			// Format invoice data for CSV row
			String[] rowData = new String[]
			{ invoice.getId().toString(), invoice.getInvoiceName(), String.valueOf(invoice.getInvoiceAmount()),
					formatDate(invoice.getCreatedDate()), formatDate(invoice.getDueDate()),
					invoice.getPaidDate() != null ? formatDate(invoice.getPaidDate()) : "NOT PAID YET",
					invoice.getStatus().toString(), invoice.getClient().getClientName(),
					invoice.getClient().getClientEmail() };
			csvData.add(rowData);
		}
		log.info("Exported Invoices Succesfully To CSV File...");
		return csvData;
	}

	// Utility method to format Date objects for CSV (optional)
	private String formatDate(Date date)
	{
		if (date == null)
		{
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

}
