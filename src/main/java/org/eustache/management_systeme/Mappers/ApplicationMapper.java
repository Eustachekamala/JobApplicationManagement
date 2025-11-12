package org.eustache.management_systeme.Mappers;

import org.eustache.management_systeme.DTOs.Requests.ApplicationRequestDTO;
import org.eustache.management_systeme.DTOs.Responses.ApplicationResponseDTO;
import org.eustache.management_systeme.Entity.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    // Maps DTO -> Entity
    public Application toEntity(ApplicationRequestDTO applicationRequestDTO){
        if(applicationRequestDTO == null) return null;
        Application application = new Application();
        application.setStatus(Application.Status.PENDING);
        application.setPosition(applicationRequestDTO.position());
        application.setAppliedAt(applicationRequestDTO.appliedAt());
        return application;
    }

    // Maps Entity -> DTO
    public ApplicationResponseDTO toResponse(Application application){
        if(application == null) return null;
        return new ApplicationResponseDTO(
            application.getId(),
            application.getStatus().name(),
            application.getPosition(),
            application.getAppliedAt()
        );
    }
}
