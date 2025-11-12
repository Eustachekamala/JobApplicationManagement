package org.eustache.management_systeme.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applicants")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Status status;

    //Relationship with Resume
    @OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL)
    private Resume resume;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();

    //Relationship with Jobs
    @ManyToMany
    @JoinTable(
            name = "applicant_jobs",
            joinColumns = @JoinColumn(name = "applicant_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<Job> jobs = new ArrayList<>();

    public enum Status {
        PENDING, ACCEPTED
    }
}
