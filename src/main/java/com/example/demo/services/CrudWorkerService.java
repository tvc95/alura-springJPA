package com.example.demo.services;

import com.example.demo.orm.Position;
import com.example.demo.orm.WorkUnit;
import com.example.demo.orm.Worker;
import com.example.demo.repositories.PositionRepository;
import com.example.demo.repositories.WorkUnitRepository;
import com.example.demo.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudWorkerService {
    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final PositionRepository positionRepository;
    private final WorkerRepository workerRepository;
    private final WorkUnitRepository workUnitRepository;

    public CrudWorkerService(PositionRepository pRepo, WorkerRepository wRepo, WorkUnitRepository wUnitRepo) {
        this.positionRepository = pRepo;
        this.workerRepository = wRepo;
        this.workUnitRepository = wUnitRepo;
    }

    public void initial(Scanner scanner) {
        while (system) {
            System.out.println("Select an action");
            System.out.println("0 - Exit");
            System.out.println("1 - Save");
            System.out.println("2 - Update");
            System.out.println("3 - List");
            System.out.println("4 - Delete");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    save(scanner);
                    break;
                case 2:
                    update(scanner);
                    break;
                case 3:
                    list();
                    break;
                case 4:
                    delete(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    public void save(Scanner s) {
        System.out.println("Name: ");
        String name = s.next();

        System.out.println("CPF: ");
        String cpf = s.next();

        System.out.println("Salary:");
        Double salary = s.nextDouble();

        System.out.println("Begin date: ");
        String begin = s.next();

        System.out.println("positionId: ");
        Integer posId = s.nextInt();

        List<WorkUnit> units = unit(s);

        Worker worker = new Worker();
        worker.setName(name);
        worker.setCpf(cpf);
        worker.setSalary(salary);
        worker.setHiringDate(LocalDate.parse(begin, formatter));

        Optional<Position> position = positionRepository.findById(posId);
        worker.setPosition(position.get());
        worker.setWorkUnits(units);

        workerRepository.save(worker);
        System.out.println("Saved");
    }

    private List<WorkUnit> unit(Scanner scanner) {
        Boolean isTrue = true;
        List<WorkUnit> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Type unitId (Type 0 to exit)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<WorkUnit> unidade = workUnitRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }

    public void update(Scanner scanner) {
        System.out.println("Id:");
        Integer id = scanner.nextInt();

        System.out.println("name:");
        String name = scanner.next();

        System.out.println("CPF: ");
        String cpf = scanner.next();

        System.out.println("Salary:");
        Double salary = scanner.nextDouble();

        System.out.println("Begin date: ");
        String begin = scanner.next();

        System.out.println("positionId: ");
        Integer posId = scanner.nextInt();

        Worker worker = new Worker();
        worker.setName(name);
        worker.setCpf(cpf);
        worker.setSalary(salary);
        worker.setHiringDate(LocalDate.parse(begin, formatter));

        Optional<Position> position = positionRepository.findById(posId);
        worker.setPosition(position.get());

        workerRepository.save(worker);
        System.out.println("Updated successfully!");

    }

    private void list() {
        Iterable<Worker> workersIterable = workerRepository.findAll();
        workersIterable.forEach(w -> System.out.println(w.toString()));
    }

    private void delete(Scanner scanner) {
        System.out.println("Id:");
        int id = scanner.nextInt();
        workerRepository.deleteById(id);
        System.out.println("Operation successful!");
    }
}
