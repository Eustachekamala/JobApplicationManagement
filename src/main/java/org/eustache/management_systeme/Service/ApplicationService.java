package org.eustache.management_systeme.Service;

import org.eustache.management_systeme.DTOs.Requests.ApplicationRequestDTO;
import org.eustache.management_systeme.DTOs.Responses.ApplicationResponseDTO;
import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Entity.Application;
import org.eustache.management_systeme.Mappers.ApplicationMapper;
import org.eustache.management_systeme.Repository.ApplicantRepository;
import org.eustache.management_systeme.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private ApplicationMapper applicationMapper;


    public ApplicationResponseDTO createApplication(Integer applicantId, ApplicationRequestDTO applicationRequestDTO) {
        // Find applicant by ID
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found with id: " + applicantId));

        // Map DTO → Entity
        Application application = applicationMapper.toEntity(applicationRequestDTO);

        // Link applicant to the application
        application.setApplicant(applicant);

        // Save the application
        Application savedApplication = applicationRepository.save(application);

        // Return mapped response
        return applicationMapper.toResponse(savedApplication);
    }

}
