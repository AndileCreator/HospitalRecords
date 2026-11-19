package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record HospitalRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name must not exceed 100 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name must not exceed 100 characters")
        String lastName,

        @NotBlank(message = "Identification is Required")
        @Size(min = 13, max = 13, message = "Identification can not exceed 13 characters")
        String identification,

        @NotBlank(message = "Patient's sex is required")
        @Size(max = 6, min = 4, message = "Sex must be 4 to 6 characters")
        String sex,

        @NotBlank(message = "Patient status is required")
        @Size(max = 9, message = "Characters must not exceed 9 characters")
        String status,

        @NotBlank(message = "Total cost is required")
        BigDecimal price,

        @NotBlank(message = "Payment status is required")
        String paymentStatus
) {
}
