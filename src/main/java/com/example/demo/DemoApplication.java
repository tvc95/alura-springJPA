package com.example.demo;

import com.example.demo.orm.Position;
import com.example.demo.repositories.PositionRepository;
import com.example.demo.services.CrudPositionService;
import com.example.demo.services.CrudWorkUnitService;
import com.example.demo.services.CrudWorkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final CrudPositionService cps;
	private final CrudWorkerService cws;
	private final CrudWorkUnitService cwus;
	private Boolean system = true;

	public DemoApplication(CrudPositionService cps, CrudWorkerService cws, CrudWorkUnitService cwus) {
		this.cps = cps;
		this.cws = cws;
		this.cwus = cwus;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner s = new Scanner(System.in);

		while(system) {
			System.out.println("Select action below:");
			System.out.println("0 - Exit");
			System.out.println("1 - Position");
			System.out.println("2 - Worker");
			System.out.println("3 - Work Units");

			int action = s.nextInt();

			switch (action) {
				case 1:
					cps.initial(s);
					break;
				case 2:
					cws.initial(s);
					break;
				case 3:
					cwus.initial(s);
					break;
				default:
					system = false;
					break;
			}
		}
	}

}
