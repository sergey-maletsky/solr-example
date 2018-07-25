$(function () {
    $('.date').datepicker({
        'format': 'dd.mm.yyyy',
        'autoclose': true
    });
    $('.time').timepicker({
        'timeFormat': 'H:i',
        'minTime': '07:00',
        'maxTime': '20:00',
        'step': 15
    });

    $('#study_time').datepair();

    $("#study-submit").on("click", function (event) {
        event.preventDefault();

        var formData = $("#study").serializeArray();
        var json = convertFormToJSON(formData);
        post('/studies', json);
    });

    $("#study-edited-submit").on("click", function (event) {
        event.preventDefault();

        var formData = $("#study-edit").serializeArray();
        var json = convertFormToJSON(formData);
        var studyId = $("#study_id").text();
        put('/studies/status/' + studyId, json);
    });

    $("#patient-submit").on("click", function (event) {
        event.preventDefault();

        var formData = $("#patient").serializeArray();
        var json = convertFormToJSON(formData);
        post('/patients', json);
    });

    function convertFormToJSON(formData) {

        var json = {};
        $.each(formData, function() {
            var value = this.value;
            if ((this.name === "startTime"
                || this.name === "endTime") && this.value) {
                value = convertTime(this.value);
                json[this.name] = value;
            } else if (this.name === "dateOfBirth" && this.value) {
                value = convertDate(this.value);
                json[this.name] = value;
            } else if (this.name === "dateOfBirth"
                || this.name === "startTime"
                || this.name === "endTime") {
            } else {
                json[this.name] = value || "";
            }
        });

        return json;
    }

    function convertTime(time) {

        var currentDate = new Date();
        if (time) {
            var splitedTime = time.split(":");
            currentDate.setHours(splitedTime[0]);
            currentDate.setMinutes(splitedTime[1]);
        }
        return currentDate;
    }

    function convertDate(date) {

        var convertedDate;
        if (date) {
            var splitedDate = date.split(".");
            convertedDate = new Date(splitedDate[2], splitedDate[1] - 1, splitedDate[0]);
        }
        return convertedDate;
    }

    function post(url, json) {

        options = {
            method: 'POST',
            url: url,
            data: json,
            dataType: "json"
        }
        request(options);
    }

    function put(url, json) {

        options = {
            method: 'PUT',
            url: url,
            data: json,
            dataType: "json"
        }
        request(options);
    }

    function get(url) {

        options = {
            method: 'GET',
            accept: 'application/json',
            url: url
        }
        request(options);
    }

    function request(options, timeToWaitIfTimeout) {

        $('#error-field').text("");
        timeToWaitIfTimeout = (typeof timeToWaitIfTimeout === 'undefined') ? 1 : timeToWaitIfTimeout;

        return $.ajax(options)
            .done(function (data) {
                console.log(data);
            })
            .fail(function (jqXHR, textStatus) {
                processError(jqXHR, options, textStatus, timeToWaitIfTimeout);
            });
    }

    function processError(jqXHR, options, textStatus, timeToWaitIfTimeout) {

        if ((textStatus == 'timeout' || jqXHR.status >= 500) && timeToWaitIfTimeout < 16) {
            console.warn("Connection timeout or http status 5xx. \nURL:%s \nWaiting for %d seconds and requesting again.",
                options.url, timeToWaitIfTimeout);
            setTimeout(request(options, timeToWaitIfTimeout * 2), 1000 * timeToWaitIfTimeout);
        } else {
            console.error("Error while trying to make " + options.method + " request to %s \nCode: %d, %s",
                options.url, jqXHR.status, jqXHR.statusText); //validatorErrors
            var errors = $.parseJSON(jqXHR.responseText);

            $('#error-field').text(errors.errorMessage ? errors.errorMessage : getValidatorErrors(errors.validatorErrors));
        }
    }
    
    function getValidatorErrors(errors) {

        var result = "";
        $.each( errors, function (key, val) {
            result += key + " : " + val + "; ";
        });
        return result;
    }
});