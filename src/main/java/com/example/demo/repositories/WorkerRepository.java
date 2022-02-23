package com.example.demo.repositories;

import com.example.demo.orm.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Integer> {
}
