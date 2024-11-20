package com.Tathagata.yummyrest.controller;

import com.Tathagata.yummyrest.dto.CustomerRequest;
import com.Tathagata.yummyrest.entity.Customer;
import com.Tathagata.yummyrest.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestParam String email, @RequestParam String password) {
        boolean isValid = customerService.validateCustomerLogin(email, password);
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
    @GetMapping("/info")
    public ResponseEntity<Customer> getCustomerInfo(@RequestParam String email) {
        return ResponseEntity.ok(customerService.getCustomerInfo(email));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestParam String email) {
        customerService.deleteCustomer(email);
        return ResponseEntity.ok("Customer deleted");
    }
}
