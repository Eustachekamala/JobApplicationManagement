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
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;

    //Relationship with Applicant
    @OneToOne
    @JoinColumn(name = "applicantId", nullable = false)
    @JsonIgnore
    private Applicant applicant;
}
