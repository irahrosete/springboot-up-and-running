package com.example.springbootupandrunning.repository;

import com.example.springbootupandrunning.domain.Aircraft;
import org.springframework.data.repository.CrudRepository;

public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
}
