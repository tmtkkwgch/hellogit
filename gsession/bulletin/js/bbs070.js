function buttonPush(cmd){

    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

//function bbs070DateKbn() {
//    if (document.getElementById('limit0').checked == true) {
//        document.forms[0].bbs070limitYear.disabled=true;
//        document.forms[0].bbs070limitMonth.disabled=true;
//        document.forms[0].bbs070limitDay.disabled=true;
//        document.forms[0].limitCalendarBtn.disabled=true;
//    } else {
//        document.forms[0].bbs070limitYear.disabled=false;
//        document.forms[0].bbs070limitMonth.disabled=false;
//        document.forms[0].bbs070limitDay.disabled=false;
//        document.forms[0].limitCalendarBtn.disabled=false;
//    }
//}

$(function() {

    //掲示期間設定区分 変更時
    $("input:radio[name=bbs070limit]").live('change', function(){
        if ($(this).val() == 1) {
            $('#limit_date_area').show();
        } else {
            $('#limit_date_area').hide();
        }
    });

    var kbn = Number($("input:radio[name=bbs070limit]:checked").val());
    if (kbn == 1) {
        $('#limit_date_area').show();
    } else {
        $('#limit_date_area').hide();
    }
})