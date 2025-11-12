package org.eustache.management_systeme.Mappers;

import java.util.List;

import org.eustache.management_systeme.DTOs.Requests.ApplicantRequestDTO;
import org.eustache.management_systeme.DTOs.Requests.ResumeRequestDTO;
import org.eustache.management_systeme.DTOs.Responses.ApplicantResponseDTO;
import org.eustache.management_systeme.DTOs.Responses.ApplicationResponseDTO;
import org.eustache.management_systeme.DTOs.Responses.JobResponseDTO;
import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Entity.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private JobMapper jobMapper;

    // Map DTO → Entity
    public Applicant toEntity(ApplicantRequestDTO applicantRequestDTO) {
        if (applicantRequestDTO == null) return null;

        Applicant applicant = new Applicant();
        applicant.setFirstname(applicantRequestDTO.firstname());
        applicant.setLastname(applicantRequestDTO.lastname());
        applicant.setEmail(applicantRequestDTO.email());
        applicant.setPhone(applicantRequestDTO.phone());
        applicant.setStatus(Applicant.Status.CREATED);

        ResumeRequestDTO resumeDTO = applicantRequestDTO.resume();
        if (resumeDTO != null) {
            Resume resume = new Resume();
            resume.setContent(resumeDTO.content());
            resume.setApplicant(applicant);
            applicant.setResume(resume);
        }

        return applicant;
    }

    // Map Entity → Response DTO
    public ApplicantResponseDTO toResponseDTO(Applicant applicant) {
        if (applicant == null) return null;

        // Map Resume
        Resume resume = applicant.getResume();
        ResumeRequestDTO resumeDTO = (resume != null)
                ? new ResumeRequestDTO(resume.getContent())
                : null;

        // Map Applications
        List<ApplicationResponseDTO> applications = null;
        if (applicant.getApplications() != null && !applicant.getApplications().isEmpty()) {
            applications = applicant.getApplications().stream()
                    .map(applicationMapper::toResponse)
                    .toList();
        }

        // Map Jobs
        List<JobResponseDTO> jobs = null;
        if (applicant.getJobs() != null && !applicant.getJobs().isEmpty()) {
            jobs = applicant.getJobs().stream()
                    .map(jobMapper::toResponseDTO)
                    .toList();
        }

        // Build and return DTO
        return new ApplicantResponseDTO(
                applicant.getId(),
                applicant.getFirstname(),
                applicant.getLastname(),
                applicant.getEmail(),
                applicant.getPhone(),
                applicant.getStatus().name(),
                resumeDTO,
                applications,
                jobs
        );
    }

}
