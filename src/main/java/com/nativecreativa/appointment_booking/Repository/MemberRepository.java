package com.nativecreativa.appointment_booking.Repository;

import com.nativecreativa.appointment_booking.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    public Member findByUsername(String username);
}
