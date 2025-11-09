package com.projects.brokerPortal.repositories;

import com.projects.brokerPortal.entities.BrokerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrokerRepository extends JpaRepository<BrokerEntity,Long> {
    Optional<BrokerEntity> findByEmail(String email);
}
