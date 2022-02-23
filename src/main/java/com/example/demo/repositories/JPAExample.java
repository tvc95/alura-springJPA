package com.example.demo.repositories;

import com.example.demo.orm.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAExample {
    private EntityManagerFactory emf;
    private EntityManager em;

    public JPAExample() {
        this.emf = Persistence.createEntityManagerFactory("jpa");
        this.em = emf.createEntityManager();
    }

    public void save(Position p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    public Position findById(Integer id) {
        em.getTransaction().begin();
        Position position = em.find(Position.class, id);
        em.getTransaction().commit();
        em.close();
        return position;
    }

    public void deleteById(Integer id) {
        Position pos = em.find(Position.class, id);

        em.getTransaction().begin();
        em.remove(pos);
        em.getTransaction().commit();
    }
}
