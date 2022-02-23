package com.example.demo.repositories;

import com.example.demo.orm.WorkUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkUnitRepository extends CrudRepository<WorkUnit, Integer> {
}
