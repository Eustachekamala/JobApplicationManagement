package org.eustache.management_systeme.Repository;

import org.eustache.management_systeme.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    Optional<Job> findJobByTitle(String title);
}
