package com.invoice.dto;

import java.util.Date;

import com.invoice.entity.ClientEntity;
import com.invoice.entity.InvoiceStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class InvoiceDTO
{
	private Long id;
    private int amount;
    private Date dueDate;
    private Date paidDate;
    private InvoiceStatus status;
    private ClientEntity client;
}
