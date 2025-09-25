package org.eustache.management_systeme.Repository;

import org.eustache.management_systeme.Entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    Optional<Applicant> findByFirstname(String firstName);
}
