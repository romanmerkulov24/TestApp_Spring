package ua.nure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.entity.Response;

import java.util.List;

public interface ResponseRepo extends JpaRepository<Response,Integer> {

    List<Response> findAllByFieldActive(Boolean active);

}
