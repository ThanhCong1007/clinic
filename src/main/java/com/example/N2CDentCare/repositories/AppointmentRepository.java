package com.example.N2CDentCare.repositories;

import com.example.N2CDentCare.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByAppointmentDate(LocalDate date);
    List<Appointment> findByPhoneNumber(String phoneNumber);
    List<Appointment> findByStatus(String status);

}