package com.nativecreativa.appointment_booking.Service;

import com.nativecreativa.appointment_booking.Model.Member;
import com.nativecreativa.appointment_booking.Model.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    Member register(String name, String surname, String username, String password, Role role);
    public Member update(String name, String surname, String username, String password, Role role);


}
