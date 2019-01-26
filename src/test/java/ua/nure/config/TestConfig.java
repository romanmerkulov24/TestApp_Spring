package ua.nure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.nure.repository.CustomerRepo;
import ua.nure.service.CustomerService;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public CustomerService getCustomerService(){
        return new CustomerService();
    }

    @Bean
    public CustomerRepo getCustomerRepo(){
        return mock(CustomerRepo.class);
    }

    @Bean
    public PasswordEncoder getEngoder(){
        return mock(PasswordEncoder.class);
    }

}
