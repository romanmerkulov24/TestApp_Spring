package ua.nure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nure.entity.Field;

public class ResponseDto {

    private Field initialField;

    private String fieldValue;

    public Field getInitialField() {
        return initialField;
    }

    public void setInitialField(Field initialField) {
        this.initialField = initialField;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @JsonProperty("label")
    public String getLabel() {
        return initialField.getLabel();
    }
}
