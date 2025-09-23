package org.eustache.management_systeme.Repository;

import org.eustache.management_systeme.Entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {
}
