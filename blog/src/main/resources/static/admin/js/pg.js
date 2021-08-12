function showSuccessMsg(options) {
    document.getElementById("test").innerHTML=options
    $('.popup_con').fadeIn('fast', function () {
        $('.popup_con').fadeOut('5000');
    });
}

function showFailMsg(options,id) {
    document.getElementById(id).innerHTML=options
    $('.fail-con').fadeIn('fast', function () {
        $('.fail-con').fadeOut('5000');
    });
}

