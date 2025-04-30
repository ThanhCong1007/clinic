package com.example.N2CDentCare.Service;

import com.example.N2CDentCare.model.Appointment;
import com.example.N2CDentCare.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAppointmentsByPhoneNumber(String phoneNumber) {
        return appointmentRepository.findByPhoneNumber(phoneNumber);
    }
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    public List<Appointment> getAppointmentsByPhone(String phoneNumber) {
        return appointmentRepository.findByPhoneNumber(phoneNumber);
    }

    public boolean isTimeSlotAvailable(LocalDate date, LocalTime time) {
        List<Appointment> appointmentsOnDate = appointmentRepository.findByAppointmentDate(date);

        // Kiểm tra xem thời gian có trùng với các lịch hẹn hiện có không
        for (Appointment app : appointmentsOnDate) {
            // Giả sử mỗi buổi hẹn kéo dài 1 giờ
            LocalTime endTime = app.getAppointmentTime().plusHours(1);

            if ((time.equals(app.getAppointmentTime()) || time.isAfter(app.getAppointmentTime()))
                    && time.isBefore(endTime)) {
                return false;
            }
        }

        return true;
    }

    public void updateAppointmentStatus(Long id, String status) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setStatus(status);
            appointmentRepository.save(appointment);
        }
    }
}