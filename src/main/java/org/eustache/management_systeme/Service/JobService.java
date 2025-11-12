package org.eustache.management_systeme.Service;

import jakarta.transaction.Transactional;

import org.eustache.management_systeme.DTOs.Requests.JobRequestDTO;
import org.eustache.management_systeme.DTOs.Responses.JobResponseDTO;
import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Entity.Job;
import org.eustache.management_systeme.Mappers.JobMapper;
import org.eustache.management_systeme.Repository.ApplicantRepository;
import org.eustache.management_systeme.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private JobMapper jobMapper;

    public ResponseEntity<JobResponseDTO> createJob(JobRequestDTO dto){
        Job job = jobMapper.toDto(dto);
        Job savedJob = jobRepository.save(job);
        return new ResponseEntity<>(jobMapper.toEntity(savedJob),HttpStatus.CREATED);
    }

    public Applicant addJobToApplicant(Integer applicantId, Integer jobId) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found with id: " + applicantId));
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));

        applicant.getJobs().add(job);
        return applicantRepository.save(applicant);
    }

    @Cacheable(value = "job", key = "#id")
    public Job getJobById(Integer id){
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

    public Job getAllJobs(){
        return jobRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("No jobs found"));
    }

    public Job getJobByTitle(String title){
        return jobRepository.findJobByTitle(title)
                .orElseThrow(() -> new RuntimeException("Job not found with title: " + title));
    }

    @CachePut(value = "job", key = "#id")
    public Job updateJob(Integer id, String title){
        Job updatedJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
        updatedJob.setTitle(title);
        return jobRepository.save(updatedJob);
    }

    @Transactional
    public String deleteJob(Integer id){
        Optional<Job> job = jobRepository.findById(id);
        if(job.isPresent()){
            jobRepository.deleteById(id);
            return "Job deleted successfully with id: " + id;
        } else {
            throw new RuntimeException("Job not found with id: " + id);
        }
    }
}
