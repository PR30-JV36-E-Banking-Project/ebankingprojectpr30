$.ajax({
    type: 'get',
    url: '/ajax',
    dataType: 'json',
    data: {
        txt: txtbox,
        hidden: hiddenTxt
    },
    cache: false,
    success: function (returndata) {

            $('#notify').html(returndata);
    },
    error: function () {
        console.error('Failed to process ajax !');
    }
}).done(function () {
    alert('Done!');
}).fail(function () {
    alert('Fail!');
});