package org.eustache.management_systeme.Service;

import org.eustache.management_systeme.DTOs.Requests.ApplicantRequestDTO;
import org.eustache.management_systeme.DTOs.Responses.ApplicantResponseDTO;
import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Entity.Application;
import org.eustache.management_systeme.Entity.Resume;
import org.eustache.management_systeme.Mappers.ApplicantMapper;
import org.eustache.management_systeme.Repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

     @Autowired
    private ApplicantRepository applicantRepository;
     @Autowired
    private ApplicantMapper applicantMapper;

    /**
     * Create a new applicant and handle relationships.
     */
    public ApplicantResponseDTO createApplicant(ApplicantRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ApplicantRequestDTO cannot be null");
        }

        // Map DTO → Entity
        Applicant applicant = applicantMapper.toEntity(dto);

        // Ensure resume relationship is set
        Resume resume = applicant.getResume();
        if (resume != null) {
            resume.setApplicant(applicant);
        }

        // Ensure all applications are linked to this applicant
        List<Application> applications = applicant.getApplications();
        if (applications != null && !applications.isEmpty()) {
            for (Application application : applications) {
                application.setApplicant(applicant);
            }
        }

        // Default status
        if (applicant.getStatus() == null) {
            applicant.setStatus(Applicant.Status.CREATED);
        }

        // Save and return mapped response
        Applicant savedApplicant = applicantRepository.save(applicant);
        return applicantMapper.toResponseDTO(savedApplicant);
    }

    /**
     * Find applicant by ID.
     */
    public ApplicantResponseDTO getApplicantById(Integer id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found with ID: " + id));
        return applicantMapper.toResponseDTO(applicant);
    }

    /**
     * Retrieve all applicants.
     */
    public List<ApplicantResponseDTO> getAllApplicants() {
        return applicantRepository.findAll()
                .stream()
                .map(applicantMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find applicant by first name.
     */
    public ApplicantResponseDTO getApplicantByFirstname(String firstName) {
        Applicant applicant = applicantRepository.findByFirstname(firstName)
                .orElseThrow(() -> new RuntimeException("Applicant not found with firstname: " + firstName));
        return applicantMapper.toResponseDTO(applicant);
    }

    /**
     * Update applicant partially.
     */
    public ApplicantResponseDTO updateApplicant(int id, ApplicantRequestDTO updatedApplicantDTO) {
        Applicant existingApplicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found with ID: " + id));

        // Map only fields that are not null
        Optional.ofNullable(updatedApplicantDTO.firstname()).ifPresent(existingApplicant::setFirstname);
        Optional.ofNullable(updatedApplicantDTO.lastname()).ifPresent(existingApplicant::setLastname);
        Optional.ofNullable(updatedApplicantDTO.email()).ifPresent(existingApplicant::setEmail);
        Optional.ofNullable(updatedApplicantDTO.phone()).ifPresent(existingApplicant::setPhone);

        // Handle resume update if provided
        if (updatedApplicantDTO.content() != null) {
            Resume updatedResume = new Resume();
            updatedResume.setContent(updatedApplicantDTO.content().content());
            updatedResume.setApplicant(existingApplicant);
            existingApplicant.setResume(updatedResume);
        }

        Applicant updated = applicantRepository.save(existingApplicant);
        return applicantMapper.toResponseDTO(updated);
    }

    /**
     * Delete an applicant by ID.
     */
    public String deleteApplicant(int id) {
        if (!applicantRepository.existsById(id)) {
            throw new RuntimeException("Applicant not found with ID: " + id);
        }
        applicantRepository.deleteById(id);
        return "Applicant with ID " + id + " has been deleted successfully.";
    }
}
