package com.system.elements.mark;

import com.system.elements.device.Device;
import com.system.elements.user.User;

import javax.persistence.*;

@Entity
@Table(name = "mark")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, name = "id")
    private int id;

    @Column(nullable = false, length = 11, name = "value")
    private int value;

    @Column(nullable = true, length = 255, name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false, name = "deviceId")
    private Device device;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
