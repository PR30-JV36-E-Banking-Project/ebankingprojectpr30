/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function goBack() {
    window.history.back();
}

function showDiv(divId, element)
{
    document.getElementById(divId).style.display = element.value == 0 ? 'none':'block';
}