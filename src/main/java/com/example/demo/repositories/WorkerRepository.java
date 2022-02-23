package com.example.demo.repositories;

import com.example.demo.orm.Worker;
import com.example.demo.orm.WorkerProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkerRepository extends PagingAndSortingRepository<Worker, Integer> {
    // Derived query method
    List<Worker> findByName(String name);

    // @Query annotated method
    @Query("SELECT w FROM Worker w WHERE w.name = :name AND w.salary >= :salary AND w.hiringDate = :date")
    List<Worker> findNameGreaterSalaryHiringDate(String name, Double salary, LocalDate date);

    @Query(value = "SELECT * FROM workers w WHERE w.hiringDate >= :date", nativeQuery = true)
    List<Worker> findHiringDateGreater(LocalDate date);

    @Query(value = "SELECT w.id, w.name, w.salary FROM workers w", nativeQuery = true)
    List<WorkerProjection> findWorkersSalaries();
}
