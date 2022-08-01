package com.system.elements.rental;

import com.system.elements.device.Device;
import com.system.elements.user.User;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, name = "id")
    private int id;

    @Column(length = 11, name = "amount")
    private int amount;

    @Column(nullable = false, name = "rentalDate")
    private Date rentalDate;

    @Column(nullable = false, length = 7, name = "rentalTime")
    private Time rentalTime;

    @Column(nullable = true, name = "returnDate")
    private Date returnDate;

    @Column(nullable = true, length = 7, name = "returnTime")
    private Time returnTime;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "deviceId")
    private Device device;

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Time getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(Time rentalTime) {
        this.rentalTime = rentalTime;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Time getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Time returnTime) {
        this.returnTime = returnTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
