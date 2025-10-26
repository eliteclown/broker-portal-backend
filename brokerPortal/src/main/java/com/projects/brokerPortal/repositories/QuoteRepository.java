package com.projects.brokerPortal.repositories;

import com.projects.brokerPortal.entities.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity,Long> {
}
