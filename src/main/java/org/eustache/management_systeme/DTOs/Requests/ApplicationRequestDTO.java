package org.eustache.management_systeme.DTOs.Requests;

import java.time.LocalDate;

public record ApplicationRequestDTO(
    String position,
    LocalDate appliedAt
) {

}
