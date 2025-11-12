package org.eustache.management_systeme.DTOs.Requests;

public record ApplicantRequestDTO(
    String firstname,
    String lastname,
    String email,
    String phone, 
    ResumeRequestDTO content
) {

}
