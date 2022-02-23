package com.example.demo.services;

import com.example.demo.orm.Worker;
import com.example.demo.repositories.WorkerRepository;
import com.example.demo.specification.SpecificationWorker;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class DynamicWorkersReportService {

    private final WorkerRepository workerRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public DynamicWorkersReportService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public void initial(Scanner scanner) {
        System.out.println("Insert a name:");
        String name = scanner.next();

        if(name.equalsIgnoreCase("NULL")) {
            name = null;
        }

        System.out.println("Insert a cpf:");
        String cpf = scanner.next();

        if(name.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        System.out.println("Insert a salary:");
        Double salary = scanner.nextDouble();

        if(salary == 0) {
            salary = null;
        }

        System.out.println("Insert a hiring date:");
        String date = scanner.next();
        LocalDate hiringDate;

        if(date.equalsIgnoreCase("NULL")) {
            hiringDate = null;
        } else {
            hiringDate = LocalDate.parse(date, formatter);
        }

        List<Worker> workers = workerRepository.findAll(Specification
                .where(SpecificationWorker.name(name))
                .or(SpecificationWorker.cpf(cpf))
                .or(SpecificationWorker.salary(salary))
                .or(SpecificationWorker.hiringDate(hiringDate)));

        workers.forEach(System.out::println);
    }
}
