package org.eustache.management_systeme.DTOs.Responses;

import org.eustache.management_systeme.DTOs.Requests.ResumeRequestDTO;

public record ApplicantResponseDTO(
    Integer id,
    String firstname,
    String lastname,
    String email,
    String phone,
    String status,
    ResumeRequestDTO content
) {

}
