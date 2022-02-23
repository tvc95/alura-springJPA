package com.example.demo.specification;

import com.example.demo.orm.Worker;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class SpecificationWorker {
    public static Specification<Worker> name(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }

    public static Specification<Worker> cpf(String cpf) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpf"), cpf));
    }

    public static Specification<Worker> salary(Double salary) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("salary"), salary));
    }

    public static Specification<Worker> hiringDate(LocalDate hiringDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("hiringDate"), hiringDate));
    }
}
