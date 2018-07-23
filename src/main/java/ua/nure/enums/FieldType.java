package ua.nure.enums;

public enum FieldType {

    SINGLE_LINE("Single line text", "text"),
    MULTI_LINE("Multiline text","textarea"),
    RADIO("Radio button","radio"),
    CHECKBOX("Checkbox", "checkbox"),
    COMBOBOX("Combobox", "select"),
    DATE("Date", "date");

    private String fullName;
    private String htmlName;

    FieldType(String fullName, String htmlName) {
        this.fullName = fullName;
        this.htmlName = htmlName;
    }

    public String getHtmlName() {
        return htmlName;
    }

    public void setHtmlName(String htmlName) {
        this.htmlName = htmlName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
