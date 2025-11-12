package org.eustache.management_systeme.DTOs.Responses;

import java.time.LocalDate;

public record ApplicationResponseDTO(
    Integer id,
    String status,
    String position,
    LocalDate appliedAt
) {

}
