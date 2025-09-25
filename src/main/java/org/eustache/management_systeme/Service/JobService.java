package org.eustache.management_systeme.Service;

import jakarta.transaction.Transactional;
import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Entity.Job;
import org.eustache.management_systeme.Repository.ApplicantRepository;
import org.eustache.management_systeme.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final ApplicantRepository applicantRepository;

    @Autowired
    public JobService(JobRepository jobRepository, ApplicantRepository applicantRepository) {
        this.jobRepository = jobRepository;
        this.applicantRepository = applicantRepository;
    }

    public Job createJob(Job job){
        return jobRepository.save(job);
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
