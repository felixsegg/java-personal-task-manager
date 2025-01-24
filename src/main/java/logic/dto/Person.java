package logic.dto;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Person {
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> systemRegistryTime = new SimpleObjectProperty<>();
    private final ObjectProperty<Address> address = new SimpleObjectProperty<>();
    private final ReadOnlySetWrapper<Task> ownedTasks = new ReadOnlySetWrapper<>(FXCollections.observableSet());
    private final ReadOnlySetWrapper<Task> associatedTasks = new ReadOnlySetWrapper<>(FXCollections.observableSet());
    
    
    public Person(String firstName, String lastName, LocalDate birthday, LocalTime systemRegistryTime, Address address) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setSystemRegistryTime(systemRegistryTime);
        setAddress(address);
    }
    
    public String getFirstName() {
        return firstName.get();
    }
    
    public StringProperty firstNameProperty() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    public String getLastName() {
        return lastName.get();
    }
    
    public StringProperty lastNameProperty() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    public LocalDate getBirthday() {
        return birthday.get();
    }
    
    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
    
    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }
    
    public LocalTime getSystemRegistryTime() {
        return systemRegistryTime.get();
    }
    
    public ObjectProperty<LocalTime> systemRegistryTimeProperty() {
        return systemRegistryTime;
    }
    
    public void setSystemRegistryTime(LocalTime systemRegistryTime) {
        this.systemRegistryTime.set(systemRegistryTime);
    }
    
    public Address getAddress() {
        return address.get();
    }
    
    public ObjectProperty<Address> addressProperty() {
        return address;
    }
    
    protected SetProperty<Task> getOwnedTasks() {
        return ownedTasks;
    }
    
    public ReadOnlySetProperty<Task> getOwnedTasksReadOnly() {
        return ownedTasks.getReadOnlyProperty();
    }
    
    protected SetProperty<Task> getAssociatedTasks() {
        return associatedTasks;
    }
    
    public ReadOnlySetProperty<Task> getAssociatedTasksReadOnly() {
        return associatedTasks.getReadOnlyProperty();
    }
    
    public void setAddress(Address address) {
        if (address == null) {
            this.address.set(null);
            return;
        } else if (address.getPerson() != null)
            throw new IllegalArgumentException("Address already has a person set!");
        
        if (getAddress() != null)
            getAddress().setPerson(null);
        
        this.address.set(address);
        address.setPerson(this);
    }
    
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ", born " + getBirthday()
                + ". System registry time: " + getSystemRegistryTime()
                + ". Address: " + getAddress();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getFirstName(), person.getFirstName())
                && Objects.equals(getLastName(), person.getLastName())
                && Objects.equals(getBirthday(), person.getBirthday())
                && Objects.equals(getSystemRegistryTime(), person.getSystemRegistryTime())
                && Objects.equals(getAddress(), person.getAddress());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getBirthday(), getSystemRegistryTime(), getAddress());
    }
}
