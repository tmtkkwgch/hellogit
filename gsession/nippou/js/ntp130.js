function buttonSubmit(mode, sid) {
    document.forms[0].ntp130NhnSid.value=sid;
    document.forms[0].ntp130ProcMode.value=mode;
    buttonPush(mode);
}
function changePage(pageCombo) {
    if (pageCombo == 0) {
        document.forms[0].ntp130PageBottom.value = document.forms[0].ntp130PageTop.value;
    } else {
        document.forms[0].ntp130PageTop.value = document.forms[0].ntp130PageBottom.value;
    }
    buttonPush('changePage');
}


function changeMode(cmd){
    document.forms[0].CMD.value = "";
    $("input[name=ntp130DspKbn]").val(cmd);
    document.forms[0].submit();
    return false;
}

function buttonSubmitCatagory(cmd, mode, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp130ProcMode.value=mode;
    document.forms[0].ntp130EditSid.value=sid;
    document.forms[0].submit();
    return false;
}

function buttonCatagoryAdd(cmd, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp130ProcMode.value=cmd;
    document.forms[0].ntp130SelCategorySid.value=sid;
    document.forms[0].ntp130EditSid.value=0;
    document.forms[0].submit();
    return false;
}

//function buttonDspShohin(cmd, sid) {
//    document.forms[0].CMD.value=cmd;
//    document.forms[0].ntp130EditSid.value=sid;
//    document.forms[0].submit();
//    return false;
//}

$(function() {

    $(".shohin_list_btn").live("click", function(){

        $('#tmpShohinArea').children().remove();
        $('#tmpShohinArea').append("<div style=\"padding-top:220px;height:100%;width:100%;text-align:center;\"><img src=\"../nippou/images/ajax-loader.gif\" /></div>");

        //商品一覧取得
        var categorySid = $(this).attr('id');
        var pageNum   = $("input:hidden[name='ntp130ShohinPageNum']").val();

        /* 商品一覧ポップアップ */
        $('#shohinPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 550,
            width: 400,
            modal: true,
            overlay: {
              backgroundColor: '#000000',
              opacity: 0.5
            },
            buttons: {
              閉じる: function() {
                  $(this).dialog('close');
              }
            }
        });

        //商品一覧成形
        getShohinList(categorySid, pageNum);
    });

    //商品一覧 次ページクリック
    $(".nextPageBtn").live("click", function(){
        var paramStr = $(this).attr('id').split(":");
        getShohinList(paramStr[0], parseInt(paramStr[1]) + 1);
    });

    //商品一覧 前ページクリック
    $(".prevPageBtn").live("click", function(){
        var paramStr = $(this).attr('id').split(":");
        getShohinList(paramStr[0], parseInt(paramStr[1]) - 1);
    });

    //商品一覧 コンボ変更
    $(".selchange").live("change", function(){
        getShohinList($(this).attr('id'), $(this).val());
    });

});

//商品一覧成形
function getShohinList(categorySid, pageNum) {

    //商品一覧取得
    $.ajaxSetup({async:false});
    $.post('../nippou/ntp130.do', {"cmd":"getShohinList",
                                   "CMD":"getShohinList",
                                   "categorySid":categorySid,
                                   "pageNum":pageNum},
      function(data) {
        if (data != null || data != "") {

            $('#tmpShohinArea').children().remove();

            if (data.shohinDataList != null && data.shohinDataList.length > 0) {

                var shohinInfstr = "";
                var maxpagesize = data.maxPageSize;
                pageNum = data.pageNum;

                //ページング
                if (parseInt(maxpagesize) > 1) {
                    shohinInfstr += "<div style=\"width:100%;text-align:right;\">"
                              +  "<img alt=\"前頁\" src=\"../common/images/arrow2_l.gif\" id=\"" + categorySid + ":" + pageNum + "\" class=\"prevPageBtn cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "<select name=\"shohinChange\" id=\"" + categorySid + "\" class=\"selchange text_i cursor_pointer\">";

                    for (p=1; p <= parseInt(maxpagesize); p++) {
                        if (pageNum == p) {
                            shohinInfstr +=  "<option value=\"" + p + "\" selected=\"selected\">" + p + " / " + maxpagesize + "</option>";
                        } else {
                            shohinInfstr +=  "<option value=\"" + p + "\">" + p + " / " + maxpagesize + "</option>";
                        }
                    }

                    shohinInfstr +=  "</select>"
                              +   "<img src=\"../common/images/arrow2_r.gif\" name=\"btn_next\" alt=\"次頁\" id=\"" + categorySid + ":" + pageNum + "\" class=\"nextPageBtn cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +   "</div>";
                }


                //商品一覧
                shohinInfstr += "<table class=\"tl0\" style=\"border-collapse:collapse;\" width=\"100%\">";

                for (i=0; i<data.shohinDataList.length; i++) {

                    shohinInfstr += "<tr height=\"40px\" class=\"cursor_pointer\" onclick=\"return buttonSubmit('edit',"
                              +  data.shohinDataList[i].nhnSid + ");\">"
                              +  "<td class=\"usr_inf_area_left user_select_area\" height=\"40px\" width=\"55px\"><a href=\"javascript:void(0);\" onclick=\"return buttonSubmit('edit',"
                              +  data.shohinDataList[i].nhnSid + ");\">";


                    shohinInfstr += "</a>"
                              +  "</td>"
                              +  "<td class=\"left_space usr_inf_area_top_bottom2 user_select_area\" align=\"left\" nowrap=\"nowrap\" width=\"100%\">"
                              +  "<span class=\"text_link2\"><a href=\"javascript:void(0);\" onclick=\"return buttonSubmit('edit',"
                              +  data.shohinDataList[i].nhnSid + ");\">"
                              +  data.shohinDataList[i].nhnName + "\</a></span>"
                              +  "</td>"
                              +  "</tr>";
                }

                shohinInfstr += "</table>";


                //ページング
                if (parseInt(maxpagesize) > 1) {
                    shohinInfstr += "<div style=\"width:100%;text-align:right;\">"
                              +  "<img alt=\"前頁\" src=\"../common/images/arrow2_l.gif\" id=\"" + categorySid + ":" + pageNum + "\" class=\"prevPageBtn img_bottom cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "<select name=\"shohinChange\" id=\"" + categorySid + "\" class=\"selchange text_i cursor_pointer\">";

                    for (p=1; p <= parseInt(maxpagesize); p++) {
                        if (pageNum == p) {
                            shohinInfstr +=  "<option value=\"" + p + "\" selected=\"selected\">" + p + " / " + maxpagesize + "</option>";
                        } else {
                            shohinInfstr +=  "<option value=\"" + p + "\">" + p + " / " + maxpagesize + "</option>";
                        }
                    }

                    shohinInfstr +=  "</select>"
                              +  "<img src=\"../common/images/arrow2_r.gif\" name=\"btn_next\" alt=\"次頁\" id=\"" + categorySid + ":" + pageNum + "\" class=\"nextPageBtn img_bottom cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "</div>";
                }

                $('#tmpShohinArea').append(shohinInfstr);

                /* 商品リンク hover */
                $('.user_select_area').hover(
                    function () {
                        $(this).removeClass("user_select_area").addClass("user_select_area_hover");
                      },
                      function () {
                          $(this).removeClass("user_select_area_hover").addClass("user_select_area");
                      }
                );
            } else {
                $('#tmpShohinArea').append("<span style=\"padding-top:220px;height:100%;width:100%;font-weight:bold;text-align:center;\">該当する商品はありません。</span>");
            }
        }
    });
}