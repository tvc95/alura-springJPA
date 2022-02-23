package com.example.demo.orm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    @OneToMany(mappedBy = "position")
    private List<Worker> workers;

    public Position() {}

    public Position(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Position [id= " + id + "\t||\tdescription= " + description + "]";
    }
}
