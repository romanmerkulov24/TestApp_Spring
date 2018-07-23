package ua.nure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nure.repository.CustomerRepo;
import ua.nure.security.CustomerDetailsIml;

@Service
public class CustomerDetailService implements UserDetailsService{

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepo.findOneByEmail(email)
                .map(CustomerDetailsIml::new)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
