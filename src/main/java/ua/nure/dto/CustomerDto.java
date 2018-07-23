package ua.nure.dto;

import org.hibernate.validator.constraints.Length;
import ua.nure.entity.Customer;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CustomerDto {

    @NotBlank(message = "First name is blank")
    @Pattern(regexp = "\\p{L}+", message = "Invalid first name format")
    private String firstName;

    @NotBlank(message = "First name is blank")
    @Pattern(regexp = "\\p{L}+", message = "Invalid last name format")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Length(min = 6, max = 13, message = "Invalid format")
    private String phone;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Password confirmation is required")
    private String passwordConfirmation;

    //TODO: fix it
    @SuppressWarnings("unused")
    @AssertTrue(message="passwords do not match")
    private boolean isValid() {
        return password.equals(passwordConfirmation);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public static Customer convertToCustomer(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.firstName);
        customer.setLastName(dto.lastName);
        customer.setEmail(dto.email);
        customer.setPhone(dto.phone);
        customer.setPassword(dto.password);
        return customer;
    }
}
