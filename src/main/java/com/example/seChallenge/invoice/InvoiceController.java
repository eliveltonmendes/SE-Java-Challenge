package com.example.seChallenge.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.example.seChallenge.domain.Invoice;
import com.example.seChallenge.invoice.dto.OrderResponseDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@ControllerAdvice
@RestController
@RequestMapping(value = { "/invoice" })
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping()
    public OrderResponseDTO[] calculateFoodPayment(@RequestBody Invoice order) throws Exception {
        return invoiceService.calculateFoodPayment(order);
    }

    @GetMapping()
    public List<Invoice> getAllInvoices() {
        return this.invoiceService.getAllInvoices();
    }
}
