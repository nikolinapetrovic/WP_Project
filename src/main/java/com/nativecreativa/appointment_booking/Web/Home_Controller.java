package com.nativecreativa.appointment_booking.Web;
import com.nativecreativa.appointment_booking.Model.Appointment;
import com.nativecreativa.appointment_booking.Model.FitnessCenter;
import com.nativecreativa.appointment_booking.Model.Role;
import com.nativecreativa.appointment_booking.Service.Appointment_Service;
import com.nativecreativa.appointment_booking.Service.FitnessCenter_Service;
import com.nativecreativa.appointment_booking.Service.MemberService;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping({"/","/home"})
public class Home_Controller {
    private final FitnessCenter_Service fitnessCenterService;
    private final Appointment_Service appointmentService;
    private final MemberService memberService;

    public Home_Controller(FitnessCenter_Service fitnessCenterService, Appointment_Service appointmentService, MemberService memberService) {
        this.fitnessCenterService = fitnessCenterService;
        this.appointmentService = appointmentService;
        this.memberService = memberService;
    }


    @GetMapping("/register")
    public String getRegisterPage(@NotNull Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name,@RequestParam String surname,@RequestParam String username, @RequestParam String password,Model model) {
        try {
            memberService.register(name, surname, username, password, "ROLE_USER");
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping
    public String getHomepage(){return "home";}
    @GetMapping("/contact-us")
    public String getContact(){return "contact";}
    @GetMapping("/about")
    public String getAbout(){return "about";}
    @GetMapping("/book")
    public String getBooking(Model model){
        List<FitnessCenter> fitnessCenterList = fitnessCenterService.listAll();
        model.addAttribute("fitnesscenters",fitnessCenterList);
        return "book_appointment";
    }
    @PostMapping( "/book")
    public String booking(@RequestParam String name_lastname,
                          @RequestParam String email,
                          @RequestParam String number,
                          @RequestParam Long fitnessCenter_id,
                          @RequestParam @DateTimeFormat String localDateTime){
        FitnessCenter fitnessCenter = fitnessCenterService.findById(fitnessCenter_id);
        Appointment appointment = new Appointment(name_lastname,email,number,LocalDateTime.parse(localDateTime),fitnessCenter);
        appointmentService.addNew(appointment);
        fitnessCenter.getAppointmentList().add(appointment);
        return "redirect:/booking-success";
    }
    @GetMapping("/booking-success")
    public String getBooking_Success(Model model){
        model.addAttribute("message","Appointment Successfully booked");
        return "book_appointment_success";
    }




}
