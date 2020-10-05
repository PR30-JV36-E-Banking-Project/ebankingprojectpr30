$(document).ready(function () {

    $("#login-Form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {
//    var ={} is object and var=[] is array
//    var search = {};
//    search["user-name"] = $("#user-name").val();
//    search["password"] = $("#password").val();
//    search["captcha"] = $("#captcha").val();
//    //search["email"] = $("#email").val();
//
//    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/login",
        data: $("#login-Form").serialize(),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<span>"
                + JSON.stringify(data.msg) + "</span>";
            $('#error').html(json);

        },
        error: function (e) {

            var json = "<pre>"
                + e.responseText + "</pre>";
            $('#error').html(json);

        }
    });

}