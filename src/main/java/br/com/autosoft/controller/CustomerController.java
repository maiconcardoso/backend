package br.com.autosoft.controller;

import java.util.List;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.autosoft.dtos.CustomerDTO;
import br.com.autosoft.entities.Customer;
import br.com.autosoft.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll() {
        List<CustomerDTO> registeredCustomer = service.read();
        return ResponseEntity.status(HttpStatus.OK).body(registeredCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
        CustomerDTO customerFound = service.readById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerFound);
    }

    @GetMapping("/find")
    public ResponseEntity<List<CustomerDTO>> findByNameCustomer(@RequestParam String name) {
        List<CustomerDTO> customerNameFound = service.readByNameCustomer(name);
        if(customerNameFound.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customerNameFound);
        return ResponseEntity.status(HttpStatus.OK).body(customerNameFound); 
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customerToBeSaved) {
        Customer customerSave = service.save(customerToBeSaved);
        return ResponseEntity.status(HttpStatus.OK).body(customerSave);
    }
}
