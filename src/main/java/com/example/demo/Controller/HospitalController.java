package com.example.demo.Controller;

import com.example.demo.DTO.HospitalRequest;
import com.example.demo.DTO.HospitalResponse;
import com.example.demo.Service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class HospitalController {
    private final HospitalService hospitalService;
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    public ResponseEntity<HospitalResponse>createPatient(@Valid @RequestBody HospitalRequest hospitalRequest){
        HospitalResponse hospitalResponse = hospitalService.createPatient(hospitalRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hospitalResponse);
    }
    @GetMapping
    public ResponseEntity<List<HospitalResponse>> getAllPatients(){

        return  ResponseEntity
                .ok(hospitalService.getAllPatients()
                );
    }
    @GetMapping("/identification")
    public ResponseEntity<HospitalResponse> getPatientByIdentification(@Valid @PathVariable("identification") String identification){

        return ResponseEntity.ok(
                hospitalService.findByIdentification(identification)
        );
    }
    @PutMapping("/identification")
    public ResponseEntity<HospitalResponse> updatePatientByIdentification(
            @PathVariable String identification,
            @Valid @RequestBody HospitalRequest hospitalRequest){
        return ResponseEntity.ok(
                hospitalService.updatePatient(identification, hospitalRequest));
    }
    @DeleteMapping("/identification")
    public ResponseEntity<Void> deletePatientByIdentification(
            @PathVariable String identification
    ){
        hospitalService.deletePatient(identification);
        return ResponseEntity.noContent().build();
    }
}
