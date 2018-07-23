package ua.nure.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.dto.CustomerDto;
import ua.nure.entity.Customer;
import ua.nure.repository.CustomerRepo;

import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public void save(CustomerDto customerDto) {
        Customer customer = CustomerDto.convertToCustomer(customerDto);
        customer.setPassword(encoder.encode(customerDto.getPassword()));
        customerRepo.save(customer);
        LOGGER.info("New customer [{}] was saved", customer.getEmail());
    }

    public void editCustomer(Customer customer) {
        customerRepo.saveAndFlush(customer);
    }

    public Customer findOneByEmail(String email) {
        LOGGER.info("CustomerService#findOneByEmail");
        return customerRepo.findOneByEmail(email).orElseThrow(()->new RuntimeException());
    }

    public Optional<Customer> findOneById(Integer id) {
        return customerRepo.findById(id);
    }

    @Transactional
    public void updateForPassword(Customer customer, String password) {
        customerRepo.updateForPassword(encoder.encode(password), customer.getId());
    }

    public boolean checkPass(String pass, String encodePass){
        return encoder.matches(pass,encodePass);
    }
}
