package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.dto.CustomerDto;
import ua.nure.repository.CustomerRepo;
import ua.nure.service.CustomerService;
import ua.nure.service.MailSenderService;

import javax.validation.Valid;

import static ua.nure.service.MailSenderService.MailTemplate.SIGNUP_MSG;
import static ua.nure.service.MailSenderService.MailTemplate.SUCCESSFUL_SIGHUP;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final String LOGIN_VIEW = "login";
    private static final String REGISTRATION_VIEW = "registration";

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private MailSenderService mailSenderService;

    @GetMapping
    public String redirectToRegisterView(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return REGISTRATION_VIEW;
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") @Valid CustomerDto customer,
                               BindingResult result,
                               ModelMap model) {
        if (customerRepo.existsByEmail(customer.getEmail())) {
            result.rejectValue("email", "error.customer", "Email is not unique");
        }
        if (result.hasFieldErrors()) {
            model.addAttribute("customer", customer);
            return REGISTRATION_VIEW;
        }

        customerService.save(customer);
        mailSenderService.send(customer.getEmail(), SUCCESSFUL_SIGHUP, SIGNUP_MSG);
        model.remove("customer");
        return LOGIN_VIEW;
    }
}
