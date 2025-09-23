package org.eustache.management_systeme.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    private String position;

    //Relationship with Applicant
    @ManyToOne
    @JoinColumn(name = "applicantId", nullable = false)
    @JsonIgnore
    private Applicant applicant;
}
