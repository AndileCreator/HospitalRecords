package com.example.demo.Entity;

import com.example.demo.Enums.PaymentStatus;
import com.example.demo.Enums.Sex;
import com.example.demo.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Patient Records")
public class HospitalRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "identification", nullable = false)
    private String identification;

    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

}
