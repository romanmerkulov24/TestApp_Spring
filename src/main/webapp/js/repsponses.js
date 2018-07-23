var ws;

function connect() {
    //connect to stomp where stomp endpoint is exposed
    var socket = new SockJS("/response");
    ws = Stomp.over(socket);

    ws.connect({}, function (frame) {

        ws.subscribe("/response/load", function (message) {
            showGreeting(message.body);
        });
    });
}

function showGreeting(message) {
    var newRow = "<tr>";
    for (var i = 0; i < window.labels.length; i++) {
        newRow += "<td>" + getColumnValue(JSON.parse(message), window.labels[i]) + "</td>";
    }
    newRow += "</tr>";
    $("#responseTable").append(newRow);
}

function getColumnValue(values, label) {
    var value = "N/A";
    for (var i = 0; i < values.length; i++) {
        if (values[i].label === label) {
            value = values[i].fieldValue;
        }
    }
    return value;
}