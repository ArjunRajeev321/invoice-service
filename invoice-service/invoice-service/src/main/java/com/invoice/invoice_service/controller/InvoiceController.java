package com.invoice.invoice_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/entry")
public class InvoiceController {


    @GetMapping
    public String error() {
        return "Error handling";
    }
}
