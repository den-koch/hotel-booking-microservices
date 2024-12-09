package io.github.denkoch.servicesservice.repository;

import io.github.denkoch.servicesservice.model.Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends MongoRepository<Service, String> {
}
