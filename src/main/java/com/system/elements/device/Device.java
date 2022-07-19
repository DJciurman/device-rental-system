package com.system.elements.device;

import com.system.elements.mark.Mark;
import com.system.elements.rental.Rental;
import com.system.elements.store.Store;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, name = "id")
    private int id;

    @Column(nullable = false, length = 255, name = "name")
    private String name;

    @Column(nullable = false, length = 255, name = "description")
    private String description;

    @Column(nullable = false, length = 255, name = "status")
    private String status;

    @Column(nullable = false, length = 11, name = "amount")
    private int amount;

    @Column(nullable = false, length = 11, name = "rate")
    private int rate = 0;

    @ManyToOne
    @JoinColumn(name = "storeId", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "device", targetEntity = Rental.class, cascade = CascadeType.ALL)
    private Set<Rental> rental;

    @OneToMany(mappedBy = "device", targetEntity = Mark.class, cascade = CascadeType.ALL)
    private Set<Mark> mark;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Set<Rental> getRental() {
        return rental;
    }

    public Set<Mark> getMark() {
        return mark;
    }
}
