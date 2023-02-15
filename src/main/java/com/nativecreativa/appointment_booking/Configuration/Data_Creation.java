package com.nativecreativa.appointment_booking.Configuration;

import com.nativecreativa.appointment_booking.Model.FitnessCenter;
import com.nativecreativa.appointment_booking.Model.Role;
import com.nativecreativa.appointment_booking.Service.FitnessCenter_Service;
import com.nativecreativa.appointment_booking.Service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Data_Creation {
    private final FitnessCenter_Service fitnessCenterService;
    private final MemberService memberService;


    public Data_Creation(FitnessCenter_Service fitnessCenterService, MemberService memberService) {
        this.fitnessCenterService = fitnessCenterService;
        this.memberService = memberService;

    }
    @PostConstruct
    public void DataCreation(){
        this.memberService.register("Nikolina","Petrovikj","niki","niki", "ROLE_ADMIN");

        this.fitnessCenterService.addNew(new FitnessCenter("Magnus","Centar za borecki sportovi","Skopje","Misko Mihajlovski","075222358"));
        this.fitnessCenterService.addNew(new FitnessCenter("Arena","Fitness center","Tetovo","Nikolina Petrovikj","072241889"));
        this.fitnessCenterService.addNew(new FitnessCenter("Adora","Kickbox","Skopje","Nikolina Petrovikj","072241889"));
        this.fitnessCenterService.addNew(new FitnessCenter("Atleta","Atleta Aerodrom","Skopje","Ruger Boskovic","075222358"));
    }

}
