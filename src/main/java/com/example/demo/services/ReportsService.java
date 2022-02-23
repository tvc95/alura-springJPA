package com.example.demo.services;

import com.example.demo.orm.Worker;
import com.example.demo.orm.WorkerProjection;
import com.example.demo.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class ReportsService {
    private Boolean system = true;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final WorkerRepository workerRepository;

    public ReportsService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public void initial(Scanner scanner) {
        while (system) {
            System.out.println("Select an action:");
            System.out.println("0 - Exit");
            System.out.println("1 - Search worker by name");
            System.out.println("2 - Search worker by name, hiring date and greater salary");
            System.out.println("3 - Search worker by hiring date");
            System.out.println("4 - List workers by salary");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    searchWorkerName(scanner);
                    break;
                case 2:
                    searchWorkerNameSalaryGreaterDate(scanner);
                    break;
                case 3:
                    searchWorkerHiringDate(scanner);
                    break;
                case 4:
                    searchWorkerSalary();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void searchWorkerName(Scanner scanner) {
        System.out.println("Insert a name to be searched:");
        String name = scanner.next();

        List<Worker> list = workerRepository.findByName(name);
        list.forEach(System.out::println);
    }

    private void searchWorkerNameSalaryGreaterDate(Scanner scanner) {
        System.out.println("Insert a name to be searched:");
        String name = scanner.next();

        System.out.println("Insert a hiring date to be searched:");
        String date = scanner.next();
        LocalDate localDate = LocalDate.parse(date, formatter);

        System.out.println("Insert a salary to be searched:");
        Double salary = scanner.nextDouble();

        List<Worker> list = workerRepository.findNameGreaterSalaryHiringDate(name, salary, localDate);
        list.forEach(System.out::println);
    }

    private void searchWorkerHiringDate(Scanner scanner) {
        System.out.println("Insert a hiring date to be searched:");
        String date = scanner.next();
        LocalDate localDate = LocalDate.parse(date, formatter);

        List<Worker> list = workerRepository.findHiringDateGreater(localDate);
        list.forEach(System.out::println);
    }

    private void searchWorkerSalary() {
        List<WorkerProjection> list = workerRepository.findWorkersSalaries();
        list.forEach(w -> System.out.println("Worker: id= " + w.getId() + "\t|\tname= " + w.getName() + "\t|\tsalary= " + w.getSalary()));
    }
}
