package com.example.demo.orm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "work_units")
public class WorkUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String address;

    @ManyToMany(mappedBy = "workUnits", fetch = FetchType.EAGER)
    private List<Worker> workers;

    public WorkUnit() {}

    public WorkUnit(String description, String address) {
        this.setDescription(description);
        this.setAddress(address);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        return "Work Unit [id= " + id + "\t||\tdescription= " + description + "\t||\taddress= " + address + "]";
    }


}
