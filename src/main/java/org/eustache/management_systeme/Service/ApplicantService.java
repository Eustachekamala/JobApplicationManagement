package org.eustache.management_systeme.Service;

import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Entity.Application;
import org.eustache.management_systeme.Entity.Resume;
import org.eustache.management_systeme.Repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {
    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public Applicant createApplicant(Applicant applicant) {
        Resume resume = applicant.getResume();
        List<Application> applications = applicant.getApplications();

        // Set the applicant for the resume
        if (resume != null) {
            resume.setApplicant(applicant);
            applicant.setResume(resume);
        }

        // Set the applicant for each application
        if (applications != null) {
            for (Application application : applications) {
                application.setApplicant(applicant);
            }
            applicant.setApplications(applications);
        }
        return applicantRepository.save(applicant);
    }

    public Applicant getApplicantById(int id) {
        return applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
    }

    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    public Applicant updateApplicant(int id, Applicant updatedApplicant) {
        Applicant existingApplicant = getApplicantById(id);
        existingApplicant.setFirstname(updatedApplicant.getFirstname());
        existingApplicant.setLastname(updatedApplicant.getLastname());
        existingApplicant.setEmail(updatedApplicant.getEmail());
        existingApplicant.setPhone(updatedApplicant.getPhone());
        existingApplicant.setStatus(updatedApplicant.getStatus());
        existingApplicant.setResume(updatedApplicant.getResume());
        return applicantRepository.save(existingApplicant);
    }

    public String deleteApplicant(int id) {
        applicantRepository.deleteById(id);
        return "Applicant with id " + id + " has been deleted.";
    }
}
