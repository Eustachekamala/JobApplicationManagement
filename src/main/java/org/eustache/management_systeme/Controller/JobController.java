package org.eustache.management_systeme.Controller;

import org.eustache.management_systeme.Entity.Applicant;
import org.eustache.management_systeme.Entity.Job;
import org.eustache.management_systeme.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    //Get all jobs
    @GetMapping("/all")
    public ResponseEntity<Job> getAllJobs() {
        return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    }

    //Get a job by id
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(
            @PathVariable  Integer id
    ) {
        return new ResponseEntity<>(jobService.getJobById(id), HttpStatus.OK);
    }

    //Add a job to an applicant
    @PostMapping("/add-job-to-applicant")
    public ResponseEntity<Applicant> addJobToApplicant(
           @RequestParam Integer applicantId,
            @RequestParam  Integer jobId
    ) {
       return new ResponseEntity<>(jobService.addJobToApplicant(applicantId, jobId), HttpStatus.CREATED);
    }

    //Create a job
    @PostMapping("/create")
    public ResponseEntity<Job> createJob(
            @RequestBody Job job
    ) {
        return new ResponseEntity<>(jobService.createJob(job), HttpStatus.CREATED);
    }

    //Update a job
    @PatchMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(
            @PathVariable Integer id,
            @RequestBody String title
    ) {
        return new ResponseEntity<>(jobService.updateJob(id, title), HttpStatus.OK);
    }

    //Get a job by title
    @GetMapping("/title/{title}")
    public ResponseEntity<Job> getJobByTitle(
            @PathVariable String title
    ) {
        return new ResponseEntity<>(jobService.getJobByTitle(title), HttpStatus.OK);
    }
}
