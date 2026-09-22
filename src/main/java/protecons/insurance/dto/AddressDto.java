package protecons.insurance.dto;

public class AddressDto {
    private String street;
    private String city;
    private String state;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    private String zipCode;
}
