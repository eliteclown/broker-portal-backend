package com.projects.brokerPortal.repositories;

import com.projects.brokerPortal.entities.BrokerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerRepository extends JpaRepository<BrokerEntity,Long> {
}
