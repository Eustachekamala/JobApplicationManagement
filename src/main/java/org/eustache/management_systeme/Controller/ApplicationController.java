package org.eustache.management_systeme.Controller;

import org.eustache.management_systeme.Entity.Application;
import org.eustache.management_systeme.Service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/create/{applicantId}")
    public ResponseEntity<Application> createApplication(
            @PathVariable Integer applicantId,
            @RequestBody Application application) {
        return ResponseEntity.ok(applicationService.createApplication(applicantId, application));
    }
}
