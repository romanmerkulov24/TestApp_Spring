package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.nure.entity.Customer;
import ua.nure.entity.Field;
import ua.nure.enums.FieldType;
import ua.nure.security.CustomerDetailsIml;
import ua.nure.service.CustomerService;
import ua.nure.service.FieldService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/fields")
public class FieldsController {

    @Autowired
    private FieldService fieldService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String getFields(Model model, HttpSession session) {
        model.addAttribute("types", FieldType.values());
        model.addAttribute("field", new Field());
        model.addAttribute("fieldList", fieldService.getAll());

        CustomerDetailsIml customerDetails = (CustomerDetailsIml) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Customer customer = customerService.findOneByEmail(customerDetails.getUsername());
        session.setAttribute("user", customer);
        return "fields";
    }

    @PostMapping("/save")
    public String addField(@ModelAttribute Field field) {
        //TODO: validation!!!

        fieldService.save(field);
        return "redirect:/fields/";
    }

    @PostMapping("/delete")
    public String deleteField(@RequestParam("fieldId") Integer fieldId) {
        fieldService.delete(fieldId);
        return "redirect:/fields/";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Field getField(@PathVariable String id) {
        return fieldService.findById(Integer.valueOf(id))
                .orElseThrow(() -> new NoSuchElementException("Field doesn't exist"));
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateField(@PathVariable String id, @RequestBody Field field) {
        fieldService.findById(Integer.valueOf(id))
                .orElseThrow(() -> new NoSuchElementException("Field doesn't exist"));
        fieldService.update(field);
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

}
