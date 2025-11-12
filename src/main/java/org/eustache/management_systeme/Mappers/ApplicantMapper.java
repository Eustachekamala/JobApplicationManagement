package org.eustache.management_systeme.Mappers;

import org.eustache.management_systeme.DTOs.Requests.ApplicantRequestDTO;
import org.eustache.management_systeme.DTOs.Requests.ResumeRequestDTO;
import org.eustache.management_systeme.DTOs.Responses.ApplicantResponseDTO;
import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Entity.Resume;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper {

    // Map DTO → Entity
    public Applicant toEntity(ApplicantRequestDTO applicantRequestDTO) {
        if (applicantRequestDTO == null) return null;

        Applicant applicant = new Applicant();
        applicant.setFirstname(applicantRequestDTO.firstname());
        applicant.setLastname(applicantRequestDTO.lastname());
        applicant.setEmail(applicantRequestDTO.email());
        applicant.setPhone(applicantRequestDTO.phone());
        applicant.setStatus(Applicant.Status.CREATED);

        ResumeRequestDTO resumeDTO = applicantRequestDTO.content();
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

        Resume resume = applicant.getResume();
        ResumeRequestDTO resumeDTO = (resume != null)
                ? new ResumeRequestDTO(resume.getContent())
                : null;

        return new ApplicantResponseDTO(
                applicant.getId(),
                applicant.getFirstname(),
                applicant.getLastname(),
                applicant.getEmail(),
                applicant.getPhone(),
                applicant.getStatus().name(),
                resumeDTO
        );
    }
}
