package com.example.demo.orm;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "workers")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String cpf;
    private Double salary;
    private LocalDate hiringDate;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "workers_work_units",
            joinColumns = { @JoinColumn(name = "fk_worker") },
    inverseJoinColumns = { @JoinColumn(name = "fk_unit")})
    private List<WorkUnit> workUnits;

    public Worker() {}

    public Worker(String name) {
        this.setName(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Worker [id= " + id + "\t||\tname= " + name + "\t||\tcpf= " + getCpf()
                + "\t||\thiringDate= " + hiringDate + "\t||\tsalary= " + salary
                + "\t||\tposition= " + getPosition().getDescription();
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<WorkUnit> getWorkUnits() {
        return workUnits;
    }

    public void setWorkUnits(List<WorkUnit> workUnits) {
        this.workUnits = workUnits;
    }
}
