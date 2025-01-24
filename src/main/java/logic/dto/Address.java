package logic.dto;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Locale;
import java.util.Objects;

public class Address {
    private final StringProperty street = new SimpleStringProperty();
    private final ObjectProperty<Short> houseNumber = new SimpleObjectProperty<>();
    private final StringProperty zipCode = new SimpleStringProperty();
    private final StringProperty city = new SimpleStringProperty();
    private final ObjectProperty<Locale.IsoCountryCode> country = new SimpleObjectProperty<>();
    private final StringProperty additionalInfo = new SimpleStringProperty();
    private final ObjectProperty<Person> person = new SimpleObjectProperty<>();
    
    public Address(String street, Short houseNumber, String zipCode, String city, Locale.IsoCountryCode country, String additionalInfo) {
        this.street.set(street);
        this.houseNumber.set(houseNumber);
        this.zipCode.set(zipCode);
        this.city.set(city);
        this.country.set(country);
        this.additionalInfo.set(additionalInfo);
    }
    
    public String getStreet() {
        return street.get();
    }
    
    public StringProperty streetProperty() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street.set(street);
    }
    
    public Short getHouseNumber() {
        return houseNumber.get();
    }
    
    public ObjectProperty<Short> houseNumberProperty() {
        return houseNumber;
    }
    
    public void setHouseNumber(Short houseNumber) {
        this.houseNumber.set(houseNumber);
    }
    
    public String getZipCode() {
        return zipCode.get();
    }
    
    public StringProperty zipCodeProperty() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }
    
    public String getCity() {
        return city.get();
    }
    
    public StringProperty cityProperty() {
        return city;
    }
    
    public void setCity(String city) {
        this.city.set(city);
    }
    
    public Locale.IsoCountryCode getCountry() {
        return country.get();
    }
    
    public ObjectProperty<Locale.IsoCountryCode> countryProperty() {
        return country;
    }
    
    public void setCountry(Locale.IsoCountryCode country) {
        this.country.set(country);
    }
    
    public String getAdditionalInfo() {
        return additionalInfo.get();
    }
    
    public StringProperty additionalInfoProperty() {
        return additionalInfo;
    }
    
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo.set(additionalInfo);
    }
    
    public Person getPerson() {
        return person.get();
    }
    
    protected void setPerson(Person person) {
        this.person.set(person);
    }
    
    @Override
    public String toString() {
        return getStreet() + " "
                + getHouseNumber() + ",  "
                + getZipCode() + " "
                + getCity() + ", "
                + getCountry() + ", "
                + getAdditionalInfo();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getStreet(), address.getStreet())
                && Objects.equals(getHouseNumber(), address.getHouseNumber())
                && Objects.equals(getZipCode(), address.getZipCode())
                && Objects.equals(getCity(), address.getCity())
                && Objects.equals(getCountry(), address.getCountry())
                && Objects.equals(getAdditionalInfo(), address.getAdditionalInfo())
                && Objects.equals(getPerson(), address.getPerson());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getHouseNumber(), getZipCode(), getCity(), getCountry(), getAdditionalInfo(), getPerson());
    }
}
