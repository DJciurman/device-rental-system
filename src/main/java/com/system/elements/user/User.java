package com.system.elements.user;

import com.system.elements.employee.Employee;
import com.system.elements.mark.Mark;
import com.system.elements.message.Message;
import com.system.elements.rental.Rental;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, name = "id")
    private int id;

    @Column(nullable = false, length = 255, name = "firstName")
    private String firstName;

    @Column(nullable = false, length = 255, name = "lastName")
    private String lastName;

    @Column(nullable = false, unique = true, length = 255, name = "email")
    private String email;

    @Column(nullable = false, length = 255, name = "accountEmail")
    private String accountEmail;

    @Column(nullable = false, length = 255, name = "password")
    private String password;

    @Column(nullable = false, length = 11, name = "phoneNumber")
    private int phoneNumber;

    @Column(nullable = false, length = 255, name = "address")
    private String address;

    @Column(nullable = false, length = 255, name = "city")
    private String city;

    @OneToOne(mappedBy = "user", targetEntity = Employee.class, cascade = CascadeType.ALL)
    private Employee employee;

    @OneToMany(mappedBy = "user", targetEntity = Rental.class, cascade = CascadeType.ALL)
    private Set<Rental> rental;

    @OneToMany(mappedBy = "sender", targetEntity = Message.class, cascade = CascadeType.ALL)
    private Set<Message> sender;

    @OneToMany(mappedBy = "receiver", targetEntity = Message.class, cascade = CascadeType.ALL)
    private Set<Message> receiver;

    @OneToMany(mappedBy = "user", targetEntity = Mark.class, cascade = CascadeType.ALL)
    private Set<Mark> mark;

    public int getId() {
        return id;
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
        this.accountEmail = email;
    }

    public void setEmailOnDelete(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Rental> getRental() {
        return rental;
    }

    public Set<Message> getSender() {
        return sender;
    }

    public Set<Message> getReceiver() {
        return receiver;
    }

    public Set<Mark> getMark() {
        return mark;
    }
}
