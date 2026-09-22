package protecons.insurance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class CustomerRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private AddressDto address;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public AddressDto getAddress() {
        return address;
    }
}
