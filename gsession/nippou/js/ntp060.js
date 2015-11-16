$(function() {
    $(".anken_link_tag").live("click", function(){
        buttonSubmit('edit',$(this).attr('id'));
    });

    $(".anken_link_area0").live({
         mouseenter:function(){
            $(this).addClass('anken_link_hover1');
            $('#tr_' + $(this).attr('id')).addClass('anken_link_hover1');
            $('.td_' + $(this).attr('id')).addClass('anken_link_hover1');
         },
         mouseleave:function(){
            $(this).removeClass('anken_link_hover1');
            $('#tr_' + $(this).attr('id')).removeClass('anken_link_hover1');
            $('.td_' + $(this).attr('id')).removeClass('anken_link_hover1');
         }
     });

    $(".anken_link_area1").live({
        mouseenter:function(){
            $(this).addClass('anken_link_hover2');
            $('#tr_' + $(this).attr('id')).addClass('anken_link_hover2');
            $('.td_' + $(this).attr('id')).addClass('anken_link_hover2');
        },
     mouseleave:function(){
            $(this).removeClass('anken_link_hover2');
            $('#tr_' + $(this).attr('id')).removeClass('anken_link_hover2');
            $('.td_' + $(this).attr('id')).removeClass('anken_link_hover2');
        }
     });


    //見積もり金額ツールチップ
    $(".mitumori_kin_str_area").live("mouseenter",function(e){
        $("#tooltip_area").append("<div id=\"ttp\">"+ ($(this).next().html()) +"</div>");
        $("#ttp")
         .css("position","absolute")
         .css("top",(e.pageY) - 35 + "px")
         .css("left",(e.pageX) - 70 + "px")
         .removeClass('display_none')
         .css("filter","alpha(opacity=85)")
         .fadeIn("fast");
    }).live("mousemove",function(e){
        $("#ttp")
         .css("top",(e.pageY) - 35 + "px")
         .css("left",(e.pageX) - 70 + "px")
    }).live("mouseleave",function(e){
        $("#ttp").remove();
    });

    //受注金額ツールチップ
    $(".jutyu_kin_str_area").live("mouseenter",function(e){
        $("#tooltip_area").append("<div id=\"ttp\">"+ ($(this).next().html()) +"</div>");
        $("#ttp")
         .css("position","absolute")
         .css("top",(e.pageY) - 35 + "px")
         .css("left",(e.pageX) - 70 + "px")
         .removeClass('display_none')
         .css("filter","alpha(opacity=85)")
         .fadeIn("fast");
    }).live("mousemove",function(e){
        $("#ttp")
         .css("top",(e.pageY) - 35 + "px")
         .css("left",(e.pageX) - 70 + "px")
    }).live("mouseleave",function(e){
        $("#ttp").remove();
    });

});

function buttonSubmit(mode, sid) {
    document.forms[0].ntp060NanSid.value=sid;
    document.forms[0].ntp060ProcMode.value=mode;
    buttonPush(mode);
}
function changePage(pageCombo) {
    if (pageCombo == 0) {
        document.forms[0].ntp060PageBottom.value = document.forms[0].ntp060PageTop.value;
    } else {
        document.forms[0].ntp060PageTop.value = document.forms[0].ntp060PageBottom.value;
    }
    buttonPush('changePage');
}

function ntpSearch(sid) {
    document.forms[0].paramAnkenSid.value = sid;
    document.forms[0].CMD.value='ntpSearch';
    document.forms[0].submit();
    return false;
}