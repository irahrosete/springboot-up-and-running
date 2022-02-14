package com.example.springbootupandrunning.repository;

import com.example.springbootupandrunning.domain.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, String> {
}
