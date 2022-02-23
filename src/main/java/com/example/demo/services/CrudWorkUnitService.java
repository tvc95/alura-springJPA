package com.example.demo.services;

import com.example.demo.orm.WorkUnit;
import com.example.demo.repositories.WorkUnitRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudWorkUnitService {
     private Boolean system = true;
     private final WorkUnitRepository workUnitRepository;

     public CrudWorkUnitService(WorkUnitRepository workUnitRepository) {
         this.workUnitRepository = workUnitRepository;
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
        System.out.println("Work Unit name: ");
        String name = s.next();

        System.out.println("Work Unit address: ");
        String address = s.next();

        WorkUnit work = new WorkUnit();
        work.setDescription(name);
        work.setAddress(address);

        workUnitRepository.save(work);
        System.out.println("Saved");
    }

    public void update(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();

        System.out.println("Work Unit name: ");
        String name = scanner.next();

        System.out.println("Work Unit address: ");
        String address = scanner.next();

        WorkUnit work = new WorkUnit();
        work.setId(id);
        work.setDescription(name);
        work.setAddress(address);

        workUnitRepository.save(work);
        System.out.println("Successfully updated!");
    }

    private void list() {
        Iterable<WorkUnit> workUnitsIterable = workUnitRepository.findAll();
        workUnitsIterable.forEach(workUnit -> System.out.println(workUnit.toString()));
    }

    private void delete(Scanner scanner) {
        System.out.println("Id:");
        int id = scanner.nextInt();
        workUnitRepository.deleteById(id);
        System.out.println("Operation successful!");
    }
}
