package com.nativecreativa.appointment_booking.Service.Implementations;

import com.nativecreativa.appointment_booking.Model.Member;
import com.nativecreativa.appointment_booking.Model.Role;
import com.nativecreativa.appointment_booking.Repository.MemberRepository;
import com.nativecreativa.appointment_booking.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return memberRepository.findByUsername(username);
    }


    @Override
    public Member register(String name, String surname, String username, String password, Role role) {
        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password must not be empty");
        }

        if (memberRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        Member member =new Member(name,surname,username, passwordEncoder.encode(password),role);

        return memberRepository.save(member);
    }

    @Override
    public Member update(String name, String surname, String username, String password, Role role) {
        Member member =new Member(name,surname,username, passwordEncoder.encode(password),role);
        return memberRepository.save(member);
    }

}
