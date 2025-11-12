package org.eustache.management_systeme.Controller;

import org.eustache.management_systeme.DTOs.Requests.ApplicationRequestDTO;
import org.eustache.management_systeme.DTOs.Responses.ApplicationResponseDTO;
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
    public ResponseEntity<ApplicationResponseDTO> createApplication(
            @PathVariable Integer applicantId,
            @RequestBody ApplicationRequestDTO application) {
        return ResponseEntity.ok(applicationService.createApplication(applicantId, application));
    }
}
