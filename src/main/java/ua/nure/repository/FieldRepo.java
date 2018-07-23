package ua.nure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.entity.Field;

import java.util.List;

public interface FieldRepo extends JpaRepository<Field, Integer> {

    List<Field> findAllByActive(Boolean active);

}
