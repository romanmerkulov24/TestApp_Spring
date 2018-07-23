var ws;

function connect() {
    //connect to stomp where stomp endpoint is exposed
    var socket = new SockJS("/response");
    ws = Stomp.over(socket);
}

function sendResponse() {
    var responses = [];
    for (var i = 0; i < fields.length; i++) {
        var docFields = document.getElementsByName(fields[i].id);

        //for checkboxes
        if (docFields[0].type === 'checkbox') {
            docFields.forEach(function (element) {
                if (element.checked) {
                    responses.push({initialField: fields[i], fieldValue: element.value});
                }
            });

        //for select inputs
        } else if (docFields[0].type === 'select-one') {
            var select = document.getElementsByName('14')[0];
            responses.push({initialField: fields[i], fieldValue: select.options[select.selectedIndex].text});
        }

        //for other inputs
        else {
            responses.push({initialField: fields[i], fieldValue: docFields[0].value});
        }
    }

    ws.send("/app/save", {}, JSON.stringify(responses));
    $('#userMsg').show();
    $('#questionnaire').hide();
}

function showGreeting(message) {
    $("#responseTable").append("<tr><td> " + message + "</td></tr>");
}
