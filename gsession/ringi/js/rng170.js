function wmlInitDelKbn() {
    var num;
    for (num = 1; num <= 5; num++) {
        wmlChangeDelKbn(num);
    }
}

function wmlChangeDelKbn(num) {
    if ($('#manuDelOk' + num)[0].checked) {
        $('#delYear' + num)[0].disabled = false;
        $('#delMonth' + num)[0].disabled = false;
        $('#delDay' + num)[0].disabled = false;
    } else {
        $('#delYear' + num)[0].disabled = true;
        $('#delMonth' + num)[0].disabled = true;
        $('#delDay' + num)[0].disabled = true;
    }
}