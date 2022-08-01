package com.system.elements.store;

import com.system.elements.device.Device;
import com.system.elements.employee.Employee;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, name = "id")
    private int id;

    @Column(nullable = false, unique = true, length = 255, name = "name")
    private String name;

    @Column(nullable = false, length = 255, name = "address")
    private String address;

    @Column(nullable = false, length = 255, name = "city")
    private String city;

    @OneToMany(mappedBy = "store", targetEntity = Employee.class, cascade = CascadeType.ALL)
    private Set<Employee> employee;

    @OneToMany(mappedBy = "store", targetEntity = Device.class, cascade = CascadeType.ALL)
    private Set<Device> device;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Employee> getEmployee() {
        return employee;
    }

    public Set<Device> getDevice() {
        return device;
    }
}
