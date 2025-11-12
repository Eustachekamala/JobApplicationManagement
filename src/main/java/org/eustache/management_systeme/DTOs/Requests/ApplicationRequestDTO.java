package org.eustache.management_systeme.DTOs.Requests;

import java.time.LocalDate;

public record ApplicationRequestDTO(
    String status,
    String position,
    LocalDate appliedAt
) {

}
