package com.example.demo.Service;

import com.example.demo.DTO.HospitalRequest;
import com.example.demo.DTO.HospitalResponse;
import com.example.demo.Entity.HospitalRecords;
import com.example.demo.Enums.PaymentStatus;
import com.example.demo.Enums.Sex;
import com.example.demo.Enums.Status;
import com.example.demo.Exceptions.DuplicatedPatientException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repository.HospitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public HospitalResponse createPatient(HospitalRequest hospitalRequest) {
        if (hospitalRepository.existsByIdentification(hospitalRequest.identification())){
            throw new DuplicatedPatientException(
                    "A patient with this identification already exists."
            );
        }
        HospitalRecords patient = new HospitalRecords();
        patient.setFirstName(hospitalRequest.firstName());
        patient.setLastName(hospitalRequest.lastName());
        patient.setSex(Sex.valueOf(hospitalRequest.sex()));
        patient.setPaymentStatus(PaymentStatus.valueOf(hospitalRequest.paymentStatus()));
        patient.setStatus(Status.valueOf(hospitalRequest.status()));
        patient.setIdentification(hospitalRequest.identification());
        patient.setPrice(hospitalRequest.price());

        HospitalRecords savedPatient = hospitalRepository.save(patient);
        return mapToResponse(savedPatient);
    }
    @Transactional(readOnly = true)
    public List<HospitalResponse> getAllPatients(){
        return hospitalRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public HospitalResponse findByIdentification(String identification){
        HospitalRecords patient = findPatientOrThrow(identification);
        return mapToResponse(patient);
    }

    @Transactional
    public HospitalResponse updatePatient(String identification,HospitalRequest hospitalRequest) {
        HospitalRecords patient = findPatientOrThrow(identification);

        boolean exists = hospitalRepository
                .existsByIdentificationAndIdNot(
                        hospitalRequest.identification(),
                        Long.valueOf(identification)
                );
        if (exists){
            throw new DuplicatedPatientException(
                    "A patient with this identification already exists.");
        }
        patient.setFirstName(hospitalRequest.firstName());
        patient.setLastName(hospitalRequest.lastName());
        patient.setStatus(Status.valueOf(hospitalRequest.status()));
        patient.setPaymentStatus(PaymentStatus.valueOf(hospitalRequest.paymentStatus()));
        patient.setIdentification(hospitalRequest.identification());
        patient.setPrice(hospitalRequest.price());

        HospitalRecords updatedPatient = hospitalRepository.save(patient);
        return mapToResponse(updatedPatient);
    }

    @Transactional
    public void deletePatient(String identification){
        HospitalRecords patient = findPatientOrThrow(identification);
        hospitalRepository.delete(patient);
    }
    private HospitalRecords findPatientOrThrow(String identification){
        return hospitalRepository.findByIdentification(identification)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found by IDENTIFICATION: " + identification
        ));
    }
    private HospitalResponse mapToResponse(HospitalRecords patient){
        return new HospitalResponse(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getSex(),
                patient.getPaymentStatus(),
                patient.getStatus(),
                patient.getIdentification()
        );
    }
}
