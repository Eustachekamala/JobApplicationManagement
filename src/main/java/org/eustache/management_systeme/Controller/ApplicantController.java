package org.eustache.management_systeme.Controller;

import java.util.List;

import org.eustache.management_systeme.DTOs.Requests.ApplicantRequestDTO;
import org.eustache.management_systeme.DTOs.Responses.ApplicantResponseDTO;
import org.eustache.management_systeme.Service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/applicants")
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

    @GetMapping("id/{id}")
    public ResponseEntity<ApplicantResponseDTO> getApplicantById(
            @PathVariable Integer id
    ) {
        return new ResponseEntity<>(applicantService.getApplicantById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApplicantResponseDTO> createApplicant(
            @RequestBody ApplicantRequestDTO applicant
    ) {
        return new ResponseEntity<>(applicantService.createApplicant(applicant), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApplicantResponseDTO> updateApplicant(
            @PathVariable Integer id,
            @RequestBody ApplicantRequestDTO applicant
    ) {
        return new ResponseEntity<>(applicantService.updateApplicant(id, applicant), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApplicantResponseDTO>> getAllApplicants() {
        return new ResponseEntity<>(applicantService.getAllApplicants(), HttpStatus.OK);
    }

    @GetMapping("firstname/{firstname}")
    public ResponseEntity<ApplicantResponseDTO> getApplicantByFirstname(
            @PathVariable String firstname
    ) {
        return new ResponseEntity<>(applicantService.getApplicantByFirstname(firstname), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteApplicant(
            @PathVariable Integer id
    ) {
        return applicantService.deleteApplicant(id);
    }
}
