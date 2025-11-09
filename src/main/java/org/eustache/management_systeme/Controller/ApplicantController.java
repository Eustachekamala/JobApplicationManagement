package org.eustache.management_systeme.Controller;

import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/applicants")
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

    @GetMapping("id/{id}")
    public Applicant getApplicantById(
            @PathVariable Integer id
    ) {
        return applicantService.getApplicantById(id);
    }

    @PostMapping("/create")
    public Applicant createApplicant(
            @RequestBody Applicant applicant
    ) {
        return applicantService.createApplicant(applicant);
    }

    @PostMapping("/update/{id}")
    public Applicant updateApplicant(
            @PathVariable Integer id,
            @RequestBody Applicant applicant
    ) {
        return applicantService.updateApplicant(id, applicant);
    }

    @GetMapping("/all")
    public Iterable<Applicant> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    @GetMapping("firstname/{firstname}")
    public Applicant getApplicantByFirstname(
            @PathVariable String firstname
    ) {
        return applicantService.getApplicantByFirstname(firstname);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteApplicant(
            @PathVariable Integer id
    ) {
        return applicantService.deleteApplicant(id);
    }
}
