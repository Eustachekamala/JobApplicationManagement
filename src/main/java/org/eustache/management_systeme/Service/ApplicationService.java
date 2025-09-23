package org.eustache.management_systeme.Service;

import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Entity.Application;
import org.eustache.management_systeme.Repository.ApplicantRepository;
import org.eustache.management_systeme.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, ApplicantRepository applicantRepository) {
        this.applicationRepository = applicationRepository;
        this.applicantRepository = applicantRepository;
    }

    public Application createApplication(Integer applicantId, Application application) {
        Optional<Applicant> applicantOpt = applicantRepository.findById(applicantId);
        if (applicantOpt.isPresent()) {
            application.setApplicant(applicantOpt.get());
            return applicationRepository.save(application);
        } else {
            throw new RuntimeException("Applicant not found with id: " + applicantId);
        }
    }
}
