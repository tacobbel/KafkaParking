package sk.upjs.kafka.part1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.upjs.kafka.part1.entity.Employee;


@Repository
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        boolean existsByCardId(String cardId);
    }

