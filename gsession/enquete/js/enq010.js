function enq010searchDetail() {
    var dspVal = document.forms[0].enq010searchDetailFlg.value;

    if (dspVal == 1) {
        $('#enq010searchDetailArea').show();
        $('#simpleSearch').hide();
        changeHelpPrm(dspVal);
    } else {
        $('#enq010searchDetailArea').hide();
        $('#simpleSearch').show();
        changeHelpPrm(dspVal);
    }
}

function enq010changeSearch() {
    var dspVal = document.forms[0].enq010searchDetailFlg.value;

    if (dspVal == 0) {
        document.forms[0].enq010searchDetailFlg.value='1';
    } else {
        document.forms[0].enq010searchDetailFlg.value='0';
    }
    enq010searchDetail();
}

function changeHelpPrm(dspVal) {
    var wk = document.forms[0].helpPrm.value;
    var helpPrm = wk.substr(0, wk.length - 1);
    helpPrm += dspVal;
    document.forms[0].helpPrm.value=helpPrm;
}

function enq010chkSrhDate(dateType) {
    if (dateType == 1) {
        enq010ChangeDateArea('enq010makeDateKbn', 'enq010makeDateArea');
    } else if (dateType == 2) {
        enq010ChangeDateArea('enq010pubDateKbn', 'enq010pubDateArea');
    } else if (dateType == 3) {
        enq010ChangeDateArea('enq010ansDateKbn', 'enq010ansDateArea');
    } else if (dateType == 4) {
        enq010ChangeDateArea('enq010resPubDateKbn', 'enq010resPubDateArea');
    }
}

function enq010ChangeDateArea(paramName, areaId) {
    if ($('input[name="' + paramName + '"]:checked').val() == 1) {
        $('#' + areaId).show();
    } else {
        $('#' + areaId).hide();
    }
}

function enq010changePage(id){
    if (id == 0) {
        document.forms[0].enq010pageBottom.value=document.forms[0].enq010pageTop.value;
    } else {
        document.forms[0].enq010pageTop.value=document.forms[0].enq010pageBottom.value;
    }

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}

function addEnquete() {
    document.forms[0].CMD.value='enq010Add';
    document.forms[0].enqEditMode.value='0';
    document.forms[0].submit();
}

function editEnquete(enqSid) {
    document.forms[0].CMD.value='enq010Edit';
    document.forms[0].enqEditMode.value='1';
    document.forms[0].editEnqSid.value=enqSid;
    document.forms[0].submit();
}

function changeFolder(folder, subFolder) {
    document.forms[0].CMD.value='enq010ChangeFolder';
    document.forms[0].enq010folder.value=folder;
    document.forms[0].enq010subFolder.value=subFolder;
    document.forms[0].enq010initFlg.value='0';
    document.forms[0].enq010searchDetailFlg.value='0';
    document.forms[0].submit();
}

function ansEnquete(enqSid) {
    document.forms[0].CMD.value='enq010Answer';
    document.forms[0].ansEnqSid.value=enqSid;
    document.forms[0].submit();
}

function ansdEnquete(enqSid) {
    document.forms[0].CMD.value='enq010Answered';
    document.forms[0].ansEnqSid.value=enqSid;
    document.forms[0].submit();
}


function enqueteResult(enqSid) {
    document.forms[0].CMD.value='enq010Result';
    document.forms[0].ansEnqSid.value=enqSid;
    document.forms[0].submit();
}

function editTemplate(enqSid) {
    document.forms[0].CMD.value='selectTemplate';
    document.forms[0].enq210templateId.value=enqSid;
    document.forms[0].submit();
}

$(function() {
//    $('#enq010searchDetailArea').hide();
    enq010chkSrhDate(1);
    enq010chkSrhDate(2);
    enq010chkSrhDate(3);
    enq010chkSrhDate(4);
});

function enq010Smail(enqId, sendKbn) {
    document.forms['enq010Form'].CMD.value='enq010SendSmail';
    document.forms['enq010Form'].enq010smailEnquate.value=enqId;
    document.forms['enq010Form'].enq010ansedSendKbn.value=sendKbn;
    document.forms['enq010Form'].submit();
    return false;
}

function callSmailWindowClose() {
    $('#smlPop').dialog('close');
}

function enq010ClickTitle(sortKey, order) {
    document.forms[0].CMD.value='init';
    document.forms[0].enq010sortKey.value=sortKey;
    document.forms[0].enq010order.value=order;
    document.forms[0].submit();
    return false;
}

function changeSelImg(id) {
    if (id.hasClass("menu_head_not_sel")) {
        id.removeClass("menu_head_not_sel");
        id.addClass("menu_head_sel");
    } else {
        id.removeClass("menu_head_sel");
        id.addClass("menu_head_not_sel");
    }
}

function changeLeftMenu(mode) {

    if (mode == 0) {
        if ($("#enq_folder_area").children().hasClass("menu_head_not_sel")) {
            $("#enq_folder_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($('#enq_folder_area').children());
        }

        if ($("#enq_template_area").children().hasClass("menu_head_sel")) {
            $("#enq_template_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($('#enq_template_area').children());
        }
    }
}
function chkAnsOverSimple() {
    document.forms[0].CMD.value='enq010chkAnsOverSimple';
    document.forms[0].submit();
    return false;
}
$(function(){
//    if (document.forms[0].enq010searchDetailFlg.value == '1') {
//        enq010searchDetail();
//    }

    /* アンケートフォルダ hover */
    $(".menu_head_area").live({
        mouseenter:function (e) {
          $(this).addClass("menu_head_area_on");
        },
        mouseleave:function (e) {
          $(this).removeClass("menu_head_area_on");
        }
    });

    /* テンプレート 追加 hover */
    $("#template_add").live({
        mouseenter:function (e) {
          $(this).addClass("template_add");
        },
        mouseleave:function (e) {
          $(this).removeClass("template_add");
        }
    });


    /* アンケートフォルダエリア クリック */
    $("#enq_folder_area").live("click", function(){
        $("#enq_folder_child_area").animate( { height: 'toggle'}, 'middle' );
        changeSelImg($(this).children());
    });

    /* テンプレートエリア クリック */
    $("#enq_template_area").live("click", function(){
        $("#enq_template_child_area").animate( { height: 'toggle'}, 'middle' );
        changeSelImg($(this).children());
    });

    //ひな形ツールチップ
    $(".template_sel_txt").mouseover(function(e){

        var txtVal = $(this).children("span.tooltips").html();
        txtVal = txtVal.replace(/\r\n/g, "<br />");
        txtVal = txtVal.replace(/(\n|\r)/g, "<br />");

         $("#tooltip_area").append("<div id=\"ttp\">"+ (txtVal) +"</div>");
         $("#ttp")
          .css("position","absolute")
          .css("top",(e.pageY) + -10 + "px")
          .css("left",(e.pageX) + 20 + "px")
          .removeClass('display_none')
          .css("filter","alpha(opacity=85)")
          .fadeIn("fast");
     }).mousemove(function(e){
         $("#ttp")
          .css("top",(e.pageY) + -10 + "px")
          .css("left",(e.pageX) + 20 + "px");
     }).mouseout(function(){
         $("#ttp").remove();
     });

    $("label").inFieldLabels();

    // ショートメール通知ボタン
    $("[name='smlNotice']").click(function(){

        var thisObj = $(this);
        var enqId = $(this).attr("id");
        var dialogName = "dialogSmlNotice";
        var _buttons = {};

        // ボタンイベント
        _buttons["OK"] = function() {
            var sendKbn = $("input:radio[name='enq010ansedSendKbn']:checked").val();
            $(this).dialog('close');
            enq010Smail(enqId, sendKbn);
        };
        _buttons[msglist["cancel"]] = function() {
            $(this).dialog('close');
        };

        $('#enq010ansedSendKbn').attr("checked", false);
        $('#' + dialogName).dialog({
            autoOpen: true,   // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 200,
            width: 360,
            modal: true,
            closeOnEscape: false,
            overlay: {
                backgroundColor: '#000000',
                opacity: 0.5
            },
            buttons: _buttons
        });
    });

});