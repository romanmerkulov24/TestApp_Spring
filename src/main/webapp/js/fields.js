var selectedField;

$(".updateBtn").click(function (e) {
    e.preventDefault();
    var id = $(this).val();
    $.ajax({
        url: window.contextPath + "/fields/" + id,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        complete: function (data) {
            var field = data.responseJSON;
            selectedField = field;
            $("#newLabel").val(field.label);
            if (field.active) {
                $("#active").prop("checked", true);
            }
            if (field.required) {
                $("#required").prop("checked", true);
            }
        }
    });
});

$("#saveBtn").click(function (e) {
    e.preventDefault();
    selectedField.label = $("#newLabel").val();
    selectedField.active = $("#active").prop("checked");
    selectedField.required = $("#required").prop("checked");
    $.ajax({
        url: window.contextPath + "/fields/" + selectedField.id,
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: JSON.stringify(selectedField),
        success: window.location.replace(window.contextPath + "/fields")
    });
});