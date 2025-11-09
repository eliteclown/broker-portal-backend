package com.projects.brokerPortal.repositories;

import com.projects.brokerPortal.entities.BrokerEntity;
import com.projects.brokerPortal.entities.QuoteListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteListRepository extends JpaRepository<QuoteListEntity,Long> {
    List<QuoteListEntity> findAllByBroker(BrokerEntity brokerEntity);
}
