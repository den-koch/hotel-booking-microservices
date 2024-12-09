package io.github.denkoch.servicesservice.repository;

import io.github.denkoch.servicesservice.model.ServiceHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceHistoryRepository extends MongoRepository<ServiceHistory, String> {
}
