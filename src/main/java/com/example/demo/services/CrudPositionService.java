package com.example.demo.services;

import com.example.demo.orm.Position;
import com.example.demo.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudPositionService {

    private Boolean system = true;
    private final PositionRepository positionRepository;

    public CrudPositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
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
        System.out.println("Position description: ");
        String description = s.next();

        Position pos = new Position();
        pos.setDescription(description);

        positionRepository.save(pos);
        System.out.println("Saved");
    }

    public void update(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();

        System.out.println("Position description");
        String description = scanner.next();

        Position position = new Position();
        position.setId(id);
        position.setDescription(description);

        positionRepository.save(position);
        System.out.println("Successfully updated!");
    }

    private void list() {
        Iterable<Position> positionsIterable = positionRepository.findAll();
        positionsIterable.forEach(position -> System.out.println(position.toString()));
    }

    private void delete(Scanner scanner) {
        System.out.println("Id:");
        int id = scanner.nextInt();
        positionRepository.deleteById(id);
        System.out.println("Operation successful!");
    }
}
