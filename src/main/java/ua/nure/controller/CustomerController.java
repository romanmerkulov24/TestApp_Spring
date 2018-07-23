package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.nure.entity.Customer;
import ua.nure.service.CustomerService;
import ua.nure.service.MailSenderService;

import javax.servlet.http.HttpSession;

import static ua.nure.service.MailSenderService.MailTemplate.CHAHGE_PASSWORD_MSG;
import static ua.nure.service.MailSenderService.MailTemplate.SUCCESSFUL_CHANGE_PASSWORD;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    private MailSenderService mailSenderService;

    @GetMapping
    public String getPageEditProfile() {
        return "editProfile";
    }

    @PostMapping("/edit")
    public String editProfile(@ModelAttribute("customer") Customer customer) {
        customerService.editCustomer(customer);
        return "redirect:/fields";
    }

    @GetMapping("/changePass")
    public String getPageChangePass() {
        return "changePass";
    }

    @PostMapping("/change")
    public String changePass(@RequestParam("currentPass") String currentPass,
                             @RequestParam("newPass") String newPass,
                             @RequestParam("confirmPass") String confirmPass,
                             HttpSession session, Model model){

        Customer customer = (Customer) session.getAttribute("user");

        if(!customerService.checkPass(currentPass,customer.getPassword())){
            model.addAttribute("errorCheckPass", "You have entered the wrong password!");
            return "changePass";
        }

        if(!newPass.equals(confirmPass)){
            model.addAttribute("errorConfirm", "Passwords do not match !");
            return "changePass";
        }

        customerService.updateForPassword(customer,newPass);

        mailSenderService.send(customer.getEmail(),SUCCESSFUL_CHANGE_PASSWORD,CHAHGE_PASSWORD_MSG);

        return "fields";
    }


}
