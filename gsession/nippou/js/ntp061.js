function buttonCopy(cmd, mod){
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp060ProcMode.value=mod;
    document.forms[0].submit();
    return false;
}

$(function() {

    /* アドレス帳ボタンクリック */
    $("#adrBtn").live("click", function(){
        var ScrTop = $(document).scrollTop();
        $('#adrArea').css("top",ScrTop + "px");
        if($('#adrArea')[0].style.display == 'none'){
            $('#adrArea')[0].style.display="block";
            Glayer.show();
            $(".adrselectbox").css('visibility','visible');
          } else {
            $('#adrArea')[0].style.display="none";
            Glayer.hide();
          }
    });

    /* アドレス帳閉じるボタンクリック */
    $("#adrClose").live("click", function(){
        $('#adrArea')[0].style.display="none";
        Glayer.hide();
    });

    /* 商品追加ボタンクリック */
    $("#itmAddBtn").live("click", function(){
        var ScrTop = $(document).scrollTop();
        $('#itmArea').css("top",ScrTop + "px");
        checkBoxCh();
        if($('#itmArea')[0].style.display == 'none'){
            $('#itmArea')[0].style.display="block";
            Glayer.show();
            $(".itmselectbox").css('visibility','visible');
        } else {
            $('#itmArea')[0].style.display="none";
            Glayer.hide();
        }
    });

    /* 商品追加閉じるボタンクリック */
    $("#itmClose").live("click", function(){
        $('#itmArea')[0].style.display="none";
        Glayer.hide();
    });

    /* 商品ポップアップ*/
    checkBoxCh();


    /* アドレス帳hover*/
    $('.comp_select_area').hover(

        function () {
            $(this).addClass("comp_select_area_hover");
          },
          function () {
              $(this).removeClass("comp_select_area_hover");
          }
    );

    //見込み度基準ボタン
    $(".mikomido_btn").live("click", function(){
        $('#mikomidoPop').dialog({
            autoOpen: true,
            bgiframe: true,
            resizable: false,
            width:400,
            modal: true,
            closeOnEscape: false,
            overlay: {
                backgroundColor: '#000000',
                opacity: 0.5
            },
            buttons: {
                OK: function() {
                    $(this).dialog('close');
                }
            }
        });
    });

});

/* 登録確認ダイアログ */
function addOkOpen() {
    $('#dialogAddOk').dialog({
        autoOpen: false,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: 200,
        modal: true,
        overlay: {
        backgroundColor: '#000000',
        opacity: 0.5
        },
        buttons: {
          登録して日報に反映: function() {
            buttonPush('addOkPopNtp');
          },
          登録: function() {
              buttonPush('addOkPop');
          },
          ｷｬﾝｾﾙ: function() {
            $(this).dialog('close');
          }
        }
    });
    $('#dialogAddOk').dialog('open');
}

/* 登録完了ダイアログ */
function addCompOpen() {
    $('#dialogAddComp').dialog({
        autoOpen: false,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: 160,
        modal: true,
        overlay: {
        backgroundColor: '#000000',
        opacity: 0.5
        },
        buttons: {
          OK: function() {
            if ($("input:hidden[name=ntp061AddCompFlg]").val() == 1) {
                //案件選択画面へ
                buttonPush('backNtp200');
            } else {
                //日報登録画面へ
                setParentNtp();
            }
          }
        }
    });
    $('#dialogAddComp').dialog('open');
}

/* 選択されている値をチェック */
function checkBoxCh() {

    var selShohin = $('select[name=ntp061SelectShohin]')[0];
    var selList   = $("input:checkbox[@name^='ntp061ItmShohinList']");
    var rowState  = 1;

    //チェックをすべてはずす
    selList.attr('checked', false);
    for (var j = 0; j < selList.length; j++) {
        if (j % 2 == 0) {
            rowState = 1;
        } else {
            rowState = 2;
        }
        resetShohinName(rowState, selList[j].value);
    }


    for (var i = 0; i < selShohin.length; i++) {
        for (var n = 0; n < selList.length; n++) {
            if (selShohin.options[i].value == selList[n].value) {
                //一致する値にチェック,色変更する
                clickShohinName(1, selList[n].value);
            }
        }
    }

    return false;
}

function adrWinClose() {
    //アドレス帳閉じる
    $('#adrArea')[0].style.display="none";
    Glayer.hide();
}

function selectLine(idx) {
    document.forms[0].CMD.value='init';
    document.forms[0].ntp061AdrIndex.value=idx;
    document.forms[0].ntp061AdrStr.value='-1';
    document.forms[0].submit();
    return false;
}

function selectStr(str) {
    document.forms[0].CMD.value='init';
    document.forms[0].ntp061AdrStr.value=str;
    document.forms[0].submit();
    return false;
}

function clickCompanyName(coSid, coBaseSid) {

    var companyId = coSid + ":" + coBaseSid;
    var coRadio = document.getElementsByName('ntp061AdrselectCompany');

    if (coRadio != null) {
        for (index = 0; index < coRadio.length; index++) {
            coRadio[index].checked = (coRadio[index].value == companyId);
        }
    }

    return selectCompany();
}

function selectCompany() {
    var coRadio = document.getElementsByName('ntp061AdrselectCompany');
    if (coRadio != null) {
        for (index = 0; index < coRadio.length; index++) {
            if (coRadio[index].checked == true) {
               var paramName = 'ntp061AdrselectCompanyName' + coRadio[index].value;
               var coParam = coRadio[index].value.split(':');
               viewTanto(coParam[0], coParam[1],
                         document.getElementsByName(paramName)[0].value);

               break;
            }
        }
    }
}

function viewTanto(adrSid, adrBaseSid, adrName) {

    document.forms['ntp061Form'].ntp061CompanySid.value = adrSid;
    document.forms['ntp061Form'].ntp061CompanyBaseSid.value = adrBaseSid;
    document.forms['ntp061Form'].ntp061CompanyName.value = adrName;
    var comCode = $('input[name=ntp061selectCompanyCode' + adrSid + '_' + adrBaseSid + ']').val();
    document.forms['ntp061Form'].ntp061CompanyCode.value = comCode;

//    var url = "../address/adr241.do";
//    url += "?adr240CompanySid=" + adrSid;
//    url += "&adr240CompanyBaseSid=" + adrBaseSid;
//
//    $.ajaxSetup({async:false});
//    $.post(url, function(data){
//        if ($('#tantoArea')[0] != null) {
//            $('#tantoArea')[0].innerHTML = data;
//        }
//    });
}

function setParent(coParamId) {

    var coParam = coParamId.split(':');

    document.forms['ntp061Form'].ntp061CompanySid.value = coParam[0];
    document.forms['ntp061Form'].ntp061CompanyBaseSid.value = coParam[1];
    document.forms['ntp061Form'].ntp061AdrCompanyName.value = document.getElementsByName('ntp061selectCompanyName' + coParamId)[0].value;
    var comCode = $('input[name=ntp061selectCompanyCode' + coParam[0] + '_' + coParam[1] + ']').val();
    document.forms['ntp061Form'].ntp061AdrCompanyCode.value = comCode;

    document.forms['ntp061Form'].CMD.value = 'selectedCompany';
    var parentId = 'ntp061Adr';

    var companySid = encodeURIComponent(document.forms['ntp061Form'].ntp061CompanySid.value);
    var companyBaseSid = encodeURIComponent(document.forms['ntp061Form'].ntp061CompanyBaseSid.value);
    var proAddFlg = encodeURIComponent(document.forms['ntp061Form'].ntp061AdrProAddFlg.value);
    var companyId = companySid + ":" + companyBaseSid;

    if (companyId.length <= 1) {
        adrWinClose();
        return false;
    }

//    if (document.forms['ntp061Form'].ntp061Adrmode.value == 0) {
//
//        addParentParam(parentId + 'CompanyIdArea', parentId + 'CompanySid', companySid);
//        addParentParam(parentId + 'CompanyBaseIdArea', parentId + 'CompanyBaseSid', companyBaseSid);
//        if (proAddFlg != 1) {
//            var parentTitle = getParentParam(parentId + 'Title');
//            if (parentTitle == null || parentTitle.length == 0) {
//                setParentParam(parentId + 'Title', document.forms['ntp061Form'].ntp061AdrCompanyName.value);
//            }
//        }
//
//        var addressId = document.getElementsByName('ntp061AdrAddress');
//        if (addressId != null) {
//            for (index = 0; index < addressId.length; index++) {
//                if (addressId[index].checked == true) {
//                    addParentParam(parentId + 'AddressIdArea',
//                                   parentId + 'AddressId',
//                                   encodeURIComponent(addressId[index].value));
//                }
//            }
//        }
//
//    } else {


        addParamNew('ntp061CompanyIdArea', 'ntp061CompanySid', companySid);
        addParamNew('ntp061CompanyBaseIdArea', 'ntp061CompanyBaseSid', companyBaseSid);
//    }

    var adrCheck = document.getElementsByName('ntp061AdrAddress');
    var nocheckFlg = 0;

    document.forms['ntp061Form'].submit();

    return false;
}

function addParamNew(parentAreaId, paramName, value) {
    var parentArea = document.getElementById(parentAreaId);

    paramHtml = '<input type="hidden" name="' + paramName + '" value="' + value + '">';
    parentArea.innerHTML = paramHtml;
}

function itmChangePage(pageCombo) {
    if (pageCombo == 0) {
        document.forms[0].ntp061ItmPageBottom.value = document.forms[0].ntp061ItmPageTop.value;
    } else {
        document.forms[0].ntp061ItmPageTop.value = document.forms[0].ntp061ItmPageBottom.value;
    }
    buttonPush('itmchangePage');
}


var checkBoxClickFlg = 0;

function clickMulti() {
    checkBoxClickFlg = 1;
    return false;
}

function clickShohinName(typeNo, itmSid) {

    if (checkBoxClickFlg == 0) {
        //tr押下時
        if (!$('#tr_' + itmSid).children().children().attr('checked')) {
            $('#tr_' + itmSid).children().children().attr('checked','checked');
            if (typeNo == 1) {
                $('#tr_' + itmSid)[0].className = 'td_type_selected';
            } else {
                $('#tr_' + itmSid)[0].className = 'td_type_selected2';
            }
        } else {
            $('#tr_' + itmSid).children().children().attr('checked','');
            var cssName = 'td_line_color' + typeNo;
            $('#tr_' + itmSid)[0].className = cssName;
        }
    } else {
        //checkBox押下時
        if ($('#tr_' + itmSid).children().children().attr('checked')) {
            $('#tr_' + itmSid).children().children().attr('checked','checked');
            if (typeNo == 1) {
                $('#tr_' + itmSid)[0].className = 'td_type_selected';
            } else {
                $('#tr_' + itmSid)[0].className = 'td_type_selected2';
            }
        } else {
            $('#tr_' + itmSid).children().children().attr('checked','');
            var cssName = 'td_line_color' + typeNo;
            $('#tr_' + itmSid)[0].className = cssName;
        }
        checkBoxClickFlg = 0;
    }

    return false;
}

function resetShohinName(typeNo, itmSid) {

    $('#tr_' + itmSid).children().children().attr('checked','');
    var cssName = 'td_line_color' + typeNo;
    $('#tr_' + itmSid)[0].className = cssName;


    return false;
}

function setParentNtp() {

    var parentDocument          = window.opener.document;
    var parentId                = document.forms['ntp061Form'].ntp200parentPageId.value;
    var rowNumber               = document.forms['ntp061Form'].ntp200RowNumber.value;
    var ankenSid                = $('input:hidden[name=ntp061AnkenSid]').val();
    var ankenCode               = replaceHtmlTag($('input:text[name=ntp061NanCode]').val());
    var ankenName               = replaceHtmlTag($('input:text[name=ntp061NanName]').val());
    var ankenNameTitle          = $('input:text[name=ntp061NanName]').val();
    var ankenCompanySid         = $('input:hidden[name=ntp061SvCompanySid]').val();
    var ankenCompanyCode        = replaceHtmlTag($('input:hidden[name=ntp061SvCompanyCode]').val());
    var ankenCompanyName        = replaceHtmlTag($('input:hidden[name=ntp061SvCompanyName]').val());
    var ankenCompanyBaseSid     = $('input:hidden[name=ntp061SvCompanyBaseSid]').val();
    var ankenCompanyBaseName    = $('input:hidden[name=ntp061SvCompanyBaseName]').val();

    if (rowNumber != "") {
        rowNumber = "_" + rowNumber;
    }

    window.opener.$('#' + parentId + 'AnkenIdArea'       + rowNumber).html("");
    window.opener.$('#' + parentId + 'AnkenCodeArea'     + rowNumber).html("");
    window.opener.$('#' + parentId + 'AnkenNameArea'     + rowNumber).html("");



    addParentParam(parentId + 'AnkenIdArea' + rowNumber, parentId + 'AnkenSid' + rowNumber, ankenSid);

    window.opener.$('#' + parentId + 'AnkenCodeArea' + rowNumber).html("<span class=\"text_anken_code\">案件コード："
            + "<span class=\"anken_code_name" + rowNumber + "\">"
            + ankenCode
            + "</span>"
            + "</span>");

    window.opener.$('#' + parentId + 'AnkenNameArea' + rowNumber).html("<span class=\"text_anken\">"
            + "<a id=\"" + ankenSid + "\" class=\"sc_link anken_click\">"
            + "<span class=\"anken_name" + rowNumber + "\">"
            + ankenName
            + "</span>"
            + "</a></span>"
            + "<a href=\"javascript:void(0);\" onclick=\"delAnken('" + parentId + "','" + rowNumber + "');\">"
            + "<img src=\"../common/images/delete.gif\" class=\"img_bottom\" alt=\"\" width=\"15\"></a>");

    if (ankenCompanySid != null && ankenCompanySid != "" && ankenCompanySid != 0 && ankenCompanySid != -1
            && window.opener.$('#' + parentId + 'CompanyIdArea'     + rowNumber).html() != null
            && window.opener.$('#' + parentId + 'CompNameArea'      + rowNumber).html() != null
            && window.opener.$('#' + parentId + 'CompanyBaseIdArea' + rowNumber).html() != null
            && window.opener.$('#' + parentId + 'AddressIdArea'     + rowNumber).html() != null
            && window.opener.$('#' + parentId + 'AddressNameArea'   + rowNumber).html() != null) {

        window.opener.$('#' + parentId + 'CompanyIdArea'     + rowNumber).html("");
        window.opener.$('#' + parentId + 'CompNameArea'      + rowNumber).html("");
        window.opener.$('#' + parentId + 'CompanyBaseIdArea' + rowNumber).html("");
        window.opener.$('#' + parentId + 'AddressIdArea'     + rowNumber).html("");
        window.opener.$('#' + parentId + 'AddressNameArea'   + rowNumber).html("");

        addParentParam(parentId + 'CompanyIdArea' + rowNumber, parentId + 'CompanySid' + rowNumber, ankenCompanySid);
        if(ankenCompanyBaseSid != null && ankenCompanyBaseSid != "" && ankenCompanyBaseSid != 0 && ankenCompanyBaseSid != -1) {
            addParentParam(parentId + 'CompanyBaseIdArea' + rowNumber, parentId + 'CompanyBaseSid' + rowNumber, ankenCompanyBaseSid);
        }

        window.opener.$('#' + parentId + 'CompanyCodeArea' + rowNumber).html("<span class=\"text_anken_code\">企業コード："
                + "<span class=\"comp_code_name" + rowNumber + "\">"
                + ankenCompanyCode
                + "</span>"
                + "</span>");

        window.opener.$('#' + parentId + 'CompNameArea' + rowNumber).html("<span class=\"text_company\">"
                     + "<a id=\"" + ankenCompanySid + "\" class=\"sc_link comp_click\">"
                     + "<span class=\"comp_name" + rowNumber + "\">"
                     + ankenCompanyName + " " + ankenCompanyBaseName
                     + "</span>"
                     + "</a></span>"
                     + "<a href=\"javascript:void(0);\" onclick=\"delCompany('" + parentId + "','" + rowNumber + "');\">"
                     + "<img src=\"../common/images/delete.gif\" class=\"img_bottom\" alt=\"\" width=\"15\"></a>");
    }

    //タイトル設定
    if (window.opener.$("input[name=" + parentId + "Title" + rowNumber + "]").val() != null) {
        var titlestr = window.opener.$("input[name=" + parentId + "Title" + rowNumber + "]").val();
        if (titlestr == '') {
            window.opener.$("input[name=" + parentId + "Title" + rowNumber + "]").val(ankenNameTitle);
        }
    }

    window.close();

    return false;
}

function addParentParam(parentAreaId, paramName, value) {
    var parentArea = window.opener.document.getElementById(parentAreaId);

    var paramHtml = parentArea.innerHTML;
    paramHtml += '<input type="hidden" name="' + paramName + '" value="' + value + '">';
    parentArea.innerHTML = paramHtml;
}

function replaceHtmlTag(s) {
    return s.replace(/&/g,"&amp;").replace(/"/g,"&quot;").replace(/'/g,"&#039;").replace(/</g,"&lt;").replace(/>/g,"&gt;") ;
}

function change061Tab(tab) {
    document.forms[0].ntp061SearchMode.value = tab;

    document.forms[0].CMD.value='changeTab';
    document.forms[0].submit();
    return false;
}

String.prototype.replaceAll = function (org, dest){
    return this.split(org).join(dest);
}

function itemSearchPush(cmd){

    $('.shohinErrorStr').children().remove();

    var priceSel  = $('input:text[name=ntp061ItmNhnPriceSale]').val().replaceAll(",","");
    var priceCost = $('input:text[name=ntp061ItmNhnPriceCost]').val().replaceAll(",","");

    if (isNaN(priceSel) && priceSel != "") {
        $('.shohinErrorStr').append('<span style=\"color:#ff0000;padding-top:220px;height:100%;width:100%;font-size:16px;font-weight:bold;text-align:center;\">販売金額は数値で入力して下さい。</span>')
        /* 販売金額入力エラー */
        $('#dialog_error').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 160,
            width: 350,
            modal: true,
            overlay: {
              backgroundColor: '#000000',
              opacity: 0.5
            },
            buttons: {
              はい: function() {
                  $(this).dialog('close');
              }
            }
        });


    } else if (priceSel.length > 9) {
            $('.shohinErrorStr').append('<span style=\"color:#ff0000;padding-top:220px;height:100%;width:100%;font-size:16px;font-weight:bold;text-align:center;\">販売金額は9桁以内の半角数字で入力して下さい。</span>')
            /* 販売金額入力エラー */
            $('#dialog_error').dialog({
                autoOpen: true,  // hide dialog
                bgiframe: true,   // for IE6
                resizable: false,
                height: 160,
                width: 350,
                modal: true,
                overlay: {
                  backgroundColor: '#000000',
                  opacity: 0.5
                },
                buttons: {
                  はい: function() {
                      $(this).dialog('close');
                  }
                }
            });


    } else if (isNaN(priceCost) && priceCost != "") {
        $('.shohinErrorStr').append('<span style=\"color:#ff0000;padding-top:220px;height:100%;width:100%;font-size:16px;font-weight:bold;text-align:center;\">原価金額は数値で入力して下さい。</span>')
        /* 原価金額入力エラー */
        $('#dialog_error').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 160,
            width: 350,
            modal: true,
            overlay: {
              backgroundColor: '#000000',
              opacity: 0.5
            },
            buttons: {
              はい: function() {
                  $(this).dialog('close');
              }
            }
        });
    } else if (priceCost.length > 9) {
        $('.shohinErrorStr').append('<span style=\"color:#ff0000;padding-top:220px;height:100%;width:100%;font-size:16px;font-weight:bold;text-align:center;\">原価金額は9桁以内の半角数字で入力して下さい。</span>')
        /* 原価金額入力エラー */
        $('#dialog_error').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 160,
            width: 350,
            modal: true,
            overlay: {
              backgroundColor: '#000000',
              opacity: 0.5
            },
            buttons: {
              はい: function() {
                  $(this).dialog('close');
              }
            }
        });
    } else {
        document.forms[0].CMD.value=cmd;
        document.forms[0].submit();
    }
    return false;
}

function itemSelectPush(cmd){

    var priceSel  = $('input:text[name=ntp061ItmNhnPriceSale]').val();
    var priceCost = $('input:text[name=ntp061ItmNhnPriceCost]').val();

    if (isNaN(priceSel) && priceSel != "") {
      $('input:text[name=ntp061ItmNhnPriceSale]').val('');
    }

    if (isNaN(priceCost) && priceCost != "") {
      $('input:text[name=ntp061ItmNhnPriceCost]').val('');
    }

    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();

    return false;
}

function changeGroupCombo(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function moveUser(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function deleteDspCompany(){
    $('input:hidden[name=ntp061CompanySid]').val('');
    $('input:hidden[name=ntp061CompanyCode]').val('');
    $('input:hidden[name=ntp061CompanyName]').val('');
    $('input:hidden[name=ntp061CompanyBaseSid]').val('');
    $('input:hidden[name=ntp061CompanyBaseName]').val('');
    document.forms[0].submit();
    return false;
}

function moveDay(elmYear, elmMonth, elmDay, kbn) {

    systemDate = new Date();

    if (kbn == 2) {
        $(elmYear).val(convYear(systemDate.getYear()));
        $(elmMonth).val(systemDate.getMonth() + 1);
        $(elmDay).val(systemDate.getDate());
        return;
    }

    if (kbn == 1 || kbn == 3) {

      var ymdf = escape(elmYear.value + '/' + elmMonth.value + "/" + elmDay.value);
    re = new RegExp(/(\d{4})\/(\d{1,2})\/(\d{1,2})/);
        if (ymdf.match(re)) {

        newDate = new Date(elmYear.value, elmMonth.value - 1, elmDay.value)

            if (kbn == 1) {
                newDate.setDate(newDate.getDate() - 1);
            } else if (kbn == 3) {
                newDate.setDate(newDate.getDate() + 1);
            }

            var newYear = convYear(newDate.getYear());
            var systemYear = convYear(systemDate.getYear());

            if (newYear < systemYear - 5 || newYear > systemYear + 5) {
                $(elmYear).val('');
            } else {
                $(elmYear).val(newYear);
            }
            $(elmMonth).val(newDate.getMonth() + 1);
            $(elmDay).val(newDate.getDate());

        } else {

            if (elmYear.value == '' && elmMonth.value == '' && elmDay.value == '') {
                $(elmYear).val(convYear(systemDate.getYear()));
                $(elmMonth).val(systemDate.getMonth() + 1);
                $(elmDay).val(systemDate.getDate());
            }
        }
    }
}