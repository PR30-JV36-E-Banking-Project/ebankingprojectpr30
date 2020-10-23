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

$(document).ready(function () {
    showDiv();
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
        hidden_accNumber_
    } else {
        $("#hidden_div_1").show();
        $("#hidden_div_0").hide();
        $("#hidden_accNumber_1").show();
        $("#hidden_accNumber_0").hide();
    }
    ;
}

