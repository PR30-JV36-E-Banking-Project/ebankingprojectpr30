/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function goBack() {

    window.history.back();
}

function myFunction(value) {
    fire_ajax_submit(value);
}

function myFunction1(value) {
    fire_ajax_submit1(value);
}

function fire_ajax_submit(accountID) {
    var tittle = document.getElementById("tittle").innerHTML;
    var search = {
        "accountID": accountID,
        "tittle": tittle
    };
    $.ajax({
        type: "POST",
        url: "/getUsername",
        data: search,
        success: function (data) {
            $('#receiverU').html(data);
        }
    });
}

function fire_ajax_submit1(accountID) {
    var search = {
        "accountID": accountID
    };
    $.ajax({
        type: "POST",
        url: "/getUsernameForAdmin",
        data: search,
        success: function (data) {
            $('#customerName').html(data);
        }
    });
}

$(document).ready(function () {
    showDiv();
    var RCID = document.getElementById("AccountReceiver").value;
    myFunction(RCID);
});

function showDiv(divId, element)
{
    var el = document.getElementById('accountType');
    var text = el.options[el.selectedIndex].innerHTML;
    if ($("#accountType")[0].selectedIndex === 0) {
        $("#hidden_div_0").show();
        $("#hidden_div_1").hide();
        $("#hidden_accNumber_0").show();
        $("#hidden_accNumber_1").hide();
        $("#hidden_button_0").show();
        $("#hidden_button_1").hide();
    } else {
        $("#hidden_div_1").show();
        $("#hidden_div_0").hide();
        $("#hidden_accNumber_1").show();
        $("#hidden_accNumber_0").hide();
        $("#hidden_button_1").show();
        $("#hidden_button_0").hide();
    }
    ;
}



