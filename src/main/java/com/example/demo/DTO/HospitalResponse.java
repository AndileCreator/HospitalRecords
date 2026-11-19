package com.example.demo.DTO;

import com.example.demo.Enums.PaymentStatus;
import com.example.demo.Enums.Sex;
import com.example.demo.Enums.Status;

public record HospitalResponse(
        Long id,
        String firstName,
        String lastName,
        Sex sex,
        PaymentStatus paymentStatus,
        Status status,
        String identification
) {
}
