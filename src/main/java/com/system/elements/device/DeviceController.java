package com.system.elements.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class DeviceController {

    @Autowired
    private DeviceRepository repoDevice;

    @RequestMapping("/addDevice")
    private String viewAddDevicePage (Model model) {
        model.addAttribute("device", new Device());
        return "addDevice";
    }

    @PostMapping("/addDevice")
    private String processAddDevice(Device device) {
        try {
            repoDevice.save(device);
        } catch (Exception e) {

        }
        return "deviceList";
    }

    @RequestMapping("/manageDevice")
    private String viewManageDevicePage (Model model) {
        model.addAttribute("device", new Device());
        return "manageDevice";
    }

    @PostMapping("/manageDevice")
    private String processManageDevice (Device device) {
        try {
            repoDevice.save(device);
        } catch (Exception e) {

        }
        return "deviceList";
    }

    @RequestMapping("/deviceList")
    private String viewDeviceListPage (Model model) {
        Set<Device> devices = repoDevice.findAllDevicesNameASC();
        model.addAttribute("devices", devices);
        return "deviceList";
    }

    @PostMapping("/deviceSearch")
    private String processSearchDevice (@RequestParam("keyWord") String keyWord, Model model) {
        Set<Device> devices = repoDevice.findAllDevicesByKeyWord(keyWord);
        model.addAttribute("devices", devices);
        return "deviceList";
    }
}
