function radChangeDelKbn(num) {
    if ($('#radioDel' + num).attr('checked')) {
        $('#delYear' + num).attr("disabled", "");
        $('#delMonth' + num).attr("disabled", "");
        $('#delDay' + num).attr("disabled", "");
    } else {
        //設定しない
        $('#delYear' + num).attr("disabled", "disabled");
        $('#delMonth' + num).attr("disabled", "disabled");
        $('#delDay' + num).attr("disabled", "disabled");

        $('#delYear' + num).val('0');
        $('#delMonth' + num).val('0');
        $('#delDay' + num).val('0');
    }
}

$(function(){
    var num;
    for(num = 1; num <= 3; num++) {
        radChangeDelKbn(num);
    }
})