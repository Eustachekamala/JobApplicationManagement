package org.eustache.management_systeme.Service;

import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Entity.Job;
import org.eustache.management_systeme.Repository.ApplicantRepository;
import org.eustache.management_systeme.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Job getJobById(Integer id){
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

    public Job getAllJobs(){
        return jobRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("No jobs found"));
    }
}
