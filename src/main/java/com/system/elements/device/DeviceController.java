package com.system.elements.device;

import com.system.elements.rental.Rental;
import com.system.elements.rental.RentalRepository;
import com.system.elements.store.Store;
import com.system.elements.store.StoreRepository;
import com.system.elements.user.User;
import com.system.elements.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.sql.Date;
import java.sql.Time;

@Controller
public class DeviceController {

    @Autowired
    private DeviceRepository repoDevice;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private RentalRepository repoRental;

    @Autowired
    private StoreRepository repoStore;

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

    @PostMapping("/manageDevice")
    private String processManageDevice (Device device) {
        try {
            repoDevice.save(device);
        } catch (Exception e) {

        }
        return "deviceList";
    }

    @RequestMapping(value = {"/deviceList","/deviceAction"})
    private String viewDeviceListPage (Model model) {
        String role = "Gosc";

        Set<Store> stores = repoStore.findAllStores();
        model.addAttribute("stores", stores);

        Store store = repoStore.findStoreById(1);

        Set<Device> devices = repoDevice.findAllDevicesByStoreNameASC(store);
        model.addAttribute("devices", devices);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = repoUser.findUserByEmail(email);

            if (user.getEmployee() != null) {
                role = "Pracownik";
            }
            else {
                role = "Klient";
            }
        }
        model.addAttribute("role", role);
        return "deviceList";
    }

    @PostMapping("/deviceSearch")
    private String processSearchDevice (@RequestParam("keyWord") String keyWord, @RequestParam("store") int id, Model model) {
        String role = "Gosc";

        Set<Store> stores = repoStore.findAllStores();
        model.addAttribute("stores", stores);

        Store store = repoStore.findStoreById(id);
        Set<Device> devices = repoDevice.findAllDevicesByKeyWordInStore(keyWord, store);
        model.addAttribute("devices", devices);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = repoUser.findUserByEmail(email);

            if (user.getEmployee() != null) {
                role = "Pracownik";
            }
            else {
                role = "Klient";
            }
        }
        model.addAttribute("role", role);
        return "deviceList";
    }

    @PostMapping("/deviceAction")
    private String processActionOnDevice (@RequestParam("amount") int amount, @RequestParam("deviceId") String optionId, Model model) {
        String role = "Gosc";

        String option = optionId.split(":")[0];
        int id = Integer.parseInt(optionId.split(":")[1]);
        if (option.equals("edit")) {
            Device device = repoDevice.findDeviceById(id);
            model.addAttribute("device", device);
            return "manageDevice";
        }
        else if (option.equals("rent") && amount != 0) {
            Device device = repoDevice.findDeviceById(id);
            Rental rental = new Rental();
            rental.setDevice(device);
            rental.setAmount(amount);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = repoUser.findUserByEmail(email);

            rental.setUser(user);

            long currentMilisTime = System.currentTimeMillis();
            Date rentalDate = new Date(currentMilisTime);

            rental.setRentalDate(rentalDate);

            Time rentalTime = new Time(currentMilisTime);

            rental.setRentalTime(rentalTime);

            repoRental.save(rental);

            device.setAmount(device.getAmount() - amount);
            repoDevice.save(device);
        }
        Set<Store> stores = repoStore.findAllStores();
        model.addAttribute("stores", stores);

        Store store = repoStore.findStoreById(1);

        Set<Device> devices = repoDevice.findAllDevicesByStoreNameASC(store);
        model.addAttribute("devices", devices);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = repoUser.findUserByEmail(email);

            if (user.getEmployee() != null) {
                role = "Pracownik";
            }
            else {
                role = "Klient";
            }
        }
        model.addAttribute("role", role);
        return "deviceList";
    }
}
