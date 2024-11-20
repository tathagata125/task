package com.Tathagata.yummyrest.service;

import com.Tathagata.yummyrest.dto.CustomerRequest;
import com.Tathagata.yummyrest.entity.Customer;
import com.Tathagata.yummyrest.mapper.CustomerMapper;
import com.Tathagata.yummyrest.repo.CustomerRepo;
import com.Tathagata.yummyrest.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    public String createCustomer(CustomerRequest request) {
        String email = request.email();
        Customer customer = repo.findByEmail(email);
        CharSequence rawPassword = null;
        if (customer != null && passwordEncoder.matches(rawPassword, customer.getPassword())) {
            return jwtUtil.generateToken(email);
        }
        return null;
    }

    public Customer getCustomerInfo(String email) {
        return repo.findByEmail(email);
    }

    public String updateCustomer(CustomerRequest request) {
        Customer customer = repo.findByEmail(request.email());
        if (customer != null) {
            customer.setFirstName(request.firstName());
            customer.setLastName(request.lastName());
            customer.setPassword(passwordEncoder.encode(request.password()));
            repo.save(customer);
            return "Updated";
        }
        return "Customer not found";
    }

    public void deleteCustomer(String email) {
        Customer customer = repo.findByEmail(email);
        if (customer != null) {
            repo.delete(customer);
        }
    }

    public boolean validateCustomerLogin(String email, String password) {
        Customer customer = repo.findByEmail(email);
        return customer != null && passwordEncoder.matches(password, customer.getPassword());
    }
    public void updateCustomerDetails(String email, CustomerRequest request) {
        Customer customer = repo.findByEmail(email);
        if (customer != null) {
            customer.setFirstName(request.firstName());
            customer.setLastName(request.lastName());
            // Update other details except email and password
            repo.save(customer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }
}
