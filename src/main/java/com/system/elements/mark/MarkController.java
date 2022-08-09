package com.system.elements.mark;

import com.system.elements.device.DeviceRepository;
import com.system.elements.rental.Rental;
import com.system.elements.rental.RentalRepository;
import com.system.elements.user.User;
import com.system.elements.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class MarkController {

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private RentalRepository repoRental;

    @Autowired
    private DeviceRepository repoDevice;

    @Autowired
    private MarkRepository repoMark;

    @PostMapping("/addMark")
    private String addMarkProcess (Model model, Mark mark, @RequestParam("ids") String ids) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repoUser.findUserByEmail(email);

        int userId = Integer.parseInt(ids.split(":")[0]);
        int deviceId = Integer.parseInt(ids.split(":")[1]);
        int markId = Integer.parseInt(ids.split(":")[2]);

        if (markId != 0) {
            Mark oldMark = repoMark.findMarkById(markId);
            oldMark.setDevice(repoDevice.findDeviceById(deviceId));
            oldMark.setUser(repoUser.findUserById(userId));
            oldMark.setValue(mark.getValue());
            oldMark.setDescription(mark.getDescription());

            try {
                repoMark.save(oldMark);
            } catch (Exception e) {

            }

        } else {
            mark.setUser(repoUser.findUserById(userId));
            mark.setDevice(repoDevice.findDeviceById(deviceId));

            try {
                repoMark.save(mark);
            } catch (Exception e) {

            }
        }



        Set<Rental> rentals = repoRental.findAllUserRentals(user);

        model.addAttribute("rentals", rentals);

        if (user.getEmployee() == null) {
            model.addAttribute("role", "Klient");
        } else {
            model.addAttribute("role", "Pracownik");
        }

        return "rentalList";
    }



}
