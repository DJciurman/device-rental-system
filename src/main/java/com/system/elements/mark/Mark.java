package com.system.elements.mark;

import com.system.elements.device.Device;

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

    @ManyToOne
    @JoinColumn(nullable = false, name = "deviceId")
    private Device device;

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
