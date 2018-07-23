package ua.nure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.entity.Field;
import ua.nure.entity.Option;
import ua.nure.repository.FieldRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FieldService {

    @Autowired
    private FieldRepo fieldRepo;

    @Transactional
    public void save(Field field) {
        Set<Option> options = Arrays.stream(field.getOptionsString().split(System.lineSeparator()))
                .map(String::trim)
                .map(Option::new)
                .peek(option -> option.setField(field))
                .collect(Collectors.toSet());
        field.setOptions(options);
        fieldRepo.save(field);
    }

    public List<Field> getActiveFields() {
        return fieldRepo.findAllByActive(Boolean.TRUE);
    }

    public List<Field> getAll() {
        return fieldRepo.findAll();
    }

    public void delete(Integer id) {
        fieldRepo.deleteById(id);
    }

    public Optional<Field> findById(Integer id) {
        return fieldRepo.findById(id);
    }

    @Transactional
    public void update(Field field) {
        Field oldField = fieldRepo.getOne(field.getId());
        oldField.setLabel(field.getLabel());
        oldField.setActive(field.isActive());
        oldField.setRequired(field.isRequired());
        fieldRepo.save(oldField);
    }
}
