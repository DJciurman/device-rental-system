package com.system.elements.rental;

import com.system.elements.device.Device;
import com.system.elements.device.DeviceRepository;
import com.system.elements.mark.Mark;
import com.system.elements.mark.MarkRepository;
import com.system.elements.user.User;
import com.system.elements.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Controller
public class RentalController {

    @Autowired
    private RentalRepository repoRental;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private DeviceRepository repoDevice;

    @Autowired
    private MarkRepository repoMark;

    @RequestMapping(value = {"/rentalList", "/rentalAction"})
    private String viewRentalListPage(Model model) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repoUser.findUserByEmail(email);

        if (user.getEmployee() == null) {
            model.addAttribute("role", "Klient");
        } else {
            model.addAttribute("role", "Pracownik");
        }

        Set<Rental> rentals = repoRental.findAllUserRentals(user);

        model.addAttribute("rentals", rentals);

        return "rentalList";
    }

    @PostMapping("/rentalAction")
    private String processReturnDevice(@RequestParam("rentalId") String optionId, Model model) {

        String option = optionId.split(":")[0];
        int id = Integer.parseInt(optionId.split(":")[1]);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repoUser.findUserByEmail(email);

        if (option.equals("return")) {

            Rental rental = repoRental.findRentalById(id);

            long currentMilisTime = System.currentTimeMillis();
            Date returnDate = new Date(currentMilisTime);

            rental.setReturnDate(returnDate);

            Time returnTime = new Time(currentMilisTime);

            rental.setReturnTime(returnTime);

            repoRental.save(rental);

            Device device = rental.getDevice();

            device.setAmount(device.getAmount() + rental.getAmount());

            repoDevice.save(device);
        } else if (option.equals("rate")) {
            Rental rental = repoRental.findRentalById(id);

            User rentalUser = rental.getUser();
            Device rentalDevice = rental.getDevice();

            Mark mark = repoMark.findMarkByUserAndDevice(rentalUser, rentalDevice);
            if (mark != null) {
                model.addAttribute("mark", mark);
                return "addMark";
            } else {
                mark = new Mark();

                mark.setDevice(rentalDevice);
                mark.setUser(user);
            }
            model.addAttribute("mark", mark);
            return "addMark";
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
