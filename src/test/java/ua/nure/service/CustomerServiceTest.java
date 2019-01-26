package ua.nure.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ua.nure.config.TestConfig;
import ua.nure.dto.CustomerDto;
import ua.nure.entity.Customer;
import ua.nure.repository.CustomerRepo;

import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder encoder;

    private static final Customer CUSTOMER = mock(Customer.class);

    @Test
    public void editCustomerTest() {
        customerService.editCustomer(CUSTOMER);
        verify(customerRepo, times(1)).saveAndFlush(CUSTOMER);
    }

    @Test
    public void saveTest(){
        CustomerDto customerDto = mock(CustomerDto.class);
        customerService.save(customerDto);
        verify(customerRepo, times(1))
                .save(CustomerDto.convertToCustomer(customerDto));
    }

    @Test
    public void findOneByEmailTestWhenReturnCustomer(){
        when(customerRepo.findOneByEmail(anyString())).thenReturn(Optional.ofNullable(CUSTOMER));
        customerService.findOneByEmail(anyString());
        verify(mock(RuntimeException.class), never()).fillInStackTrace();
    }

    @Test(expected = RuntimeException.class)
    public void findOneByEmailTestWhenReturnNull(){
        when(customerRepo.findOneByEmail(anyString())).thenReturn(null);
        RuntimeException exception = new RuntimeException();
        doThrow(exception).when(customerRepo.findOneByEmail(anyString()));
        customerService.findOneByEmail(anyString());
    }

    @Test
    public void findOneByIdTest(){
        customerService.findOneById(anyInt());
        verify(customerRepo,times(1)).findById(anyInt());
    }

    @Test
    public void updateForPasswordTest(){
        customerService.updateForPassword(CUSTOMER,anyString());
        verify(customerRepo,times(1))
                .updateForPassword(encoder.encode(anyString()),CUSTOMER.getId());
    }

}