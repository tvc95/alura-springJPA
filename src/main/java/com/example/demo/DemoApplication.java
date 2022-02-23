package com.example.demo;

import com.example.demo.services.CrudPositionService;
import com.example.demo.services.CrudWorkUnitService;
import com.example.demo.services.CrudWorkerService;
import com.example.demo.services.ReportsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final CrudPositionService cps;
	private final CrudWorkerService cws;
	private final CrudWorkUnitService cwus;
	private final ReportsService rs;
	private Boolean system = true;

	public DemoApplication(CrudPositionService cps, CrudWorkerService cws, CrudWorkUnitService cwus, ReportsService rs) {
		this.cps = cps;
		this.cws = cws;
		this.cwus = cwus;
		this.rs = rs;
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
			System.out.println("4 - Reports");

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
				case 4:
					rs.initial(s);
					break;
				default:
					system = false;
					break;
			}
		}
	}

}
