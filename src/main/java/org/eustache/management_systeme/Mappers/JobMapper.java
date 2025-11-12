package org.eustache.management_systeme.Mappers;

import org.eustache.management_systeme.DTOs.Requests.JobRequestDTO;
import org.eustache.management_systeme.DTOs.Responses.JobResponseDTO;
import org.eustache.management_systeme.Entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    // This method maps JobRequestDTO to Job entity
  public Job toDto(JobRequestDTO jobRequestDTO) {
    if (jobRequestDTO == null) return null;

    Job job = new Job();
    job.setTitle(jobRequestDTO.title());
    job.setDescription(jobRequestDTO.description());
    return job;
  }

  // This method maps Job entity to JobResponseDTO
  public JobResponseDTO toEntity(Job job) {
    if (job == null) return null;

    return new JobResponseDTO(
        job.getId(),
        job.getTitle(),
        job.getDescription()
    );
  }
}
