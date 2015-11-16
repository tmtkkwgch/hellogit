var ankenSubWindow;

function openAnkenWindow(parentPageId, rowNumber) {
    var winWidth=900;
    var winHeight=800;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    var url = '../nippou/ntp200.do';
    url += '?ntp200parentPageId=' + parentPageId + "&ntp200RowNumber=" + rowNumber;
    var opt = 'width=' + winWidth + ', height=' + winHeight + ', resizable=yes , toolbar=no ,' +
    'resizable=no , left=' + winx + ', top=' + winy + ',scrollbars=yes';
    ankenSubWindow = window.open(url, 'thissite', opt);
    return false;
}

function companyWindowClose() {
    if(ankenSubWindow != null){
        ankenSubWindow.close();
    }
}

function getCenterX(winWidth) {
  var x = (screen.width - winWidth) / 2;
  return x;
}

function getCenterY(winHeight) {
  var y = (screen.height - winHeight) / 2;
  return y;
}

function selectLine(idx) {
    document.forms[0].CMD.value='init';
    document.forms[0].ntp200Index.value=idx;
    document.forms[0].ntp200Str.value='-1';
    document.forms[0].submit();
    return false;
}

function selectStr(str) {
    document.forms[0].CMD.value='init';
    document.forms[0].ntp200Str.value=str;
    document.forms[0].submit();
    return false;
}

function parentReload() {

    var parentDocument = window.opener.document;
    parentDocument.forms[0].CMD.value = 'selectedCompany';

    var parentId = document.forms['ntp200Form'].ntp200parentPageId.value;

    var companySid = encodeURIComponent(document.forms['ntp200Form'].ntp200CompanySid.value);
    var companyBaseSid = encodeURIComponent(document.forms['ntp200Form'].ntp200CompanyBaseSid.value);
    var proAddFlg = encodeURIComponent(document.forms['ntp200Form'].ntp200ProAddFlg.value);
    var companyId = companySid + ":" + companyBaseSid;
    if (companyId.length <= 1) {
        window.close();
        return false;
    }

    if (document.forms['ntp200Form'].ntp200mode.value == 0) {

        addParentParam(parentId + 'CompanyIdArea', parentId + 'CompanySid', companySid);
        addParentParam(parentId + 'CompanyBaseIdArea', parentId + 'CompanyBaseSid', companyBaseSid);
        if (proAddFlg != 1) {
            var parentTitle = getParentParam(parentId + 'Title');
            if (parentTitle == null || parentTitle.length == 0) {
                setParentParam(parentId + 'Title', document.forms['ntp200Form'].ntp200CompanyName.value);
            }
        }

        var addressId = document.getElementsByName('ntp200Address');
        if (addressId != null) {
            for (index = 0; index < addressId.length; index++) {
                if (addressId[index].checked == true) {
                    addParentParam(parentId + 'AddressIdArea',
                                   parentId + 'AddressId',
                                   encodeURIComponent(addressId[index].value));
                }
            }
        }

    } else {
        addParentParamNew(parentId + 'CompanyIdArea', parentId + 'CompanySid', companySid);
        addParentParamNew(parentId + 'CompanyBaseIdArea', parentId + 'CompanyBaseSid', companyBaseSid);
    }

    var adrCheck = document.getElementsByName('ntp200Address');
    var nocheckFlg = 0;

    for (index = 0; index < adrCheck.length; index++) {
        if (adrCheck[index].checked) {
            nocheckFlg = 1;
        }
    }

    if (document.forms['ntp200Form'].ntp200ProAddFlg.value == 1 && nocheckFlg == 0) {
        document.forms[0].CMD.value = 'proNoSelTanto';
        document.forms[0].submit();
        return false;
    }

    parentDocument.forms[0].submit();
    if (proAddFlg == 1) {
      window.close();
    }
    return false;
}

function getParentParam(paramName) {
    return window.opener.document.getElementsByName(paramName)[0].value;
}

function setParentParam(paramName, value) {
    window.opener.document.getElementsByName(paramName)[0].value = value;
}

function addParentParam(parentAreaId, paramName, value) {
    var parentArea = window.opener.document.getElementById(parentAreaId);

    var paramHtml = parentArea.innerHTML;
    paramHtml += '<input type="hidden" name="' + paramName + '" value="' + value + '">';
    parentArea.innerHTML = paramHtml;
}

function addParentParamNew(parentAreaId, paramName, value) {
    var parentArea = window.opener.document.getElementById(parentAreaId);

    paramHtml = '<input type="hidden" name="' + paramName + '" value="' + value + '">';
    parentArea.innerHTML = paramHtml;
}

function clickCompanyName(coSid, coBaseSid) {

    var companyId = coSid + ":" + coBaseSid;
    var coRadio = document.getElementsByName('ntp200selectCompany');

    if (coRadio != null) {
        for (index = 0; index < coRadio.length; index++) {
            coRadio[index].checked = (coRadio[index].value == companyId);
        }
    }

    return selectCompany();
}

function selectCompany() {
    var coRadio = document.getElementsByName('ntp200selectCompany');
    if (coRadio != null) {
        for (index = 0; index < coRadio.length; index++) {
            if (coRadio[index].checked == true) {
               var paramName = 'ntp200selectCompanyName' + coRadio[index].value;
               var coParam = coRadio[index].value.split(':');
               viewTanto(coParam[0], coParam[1],
                         document.getElementsByName(paramName)[0].value);
               break;
            }
        }
    }
}

function viewTanto(adrSid, adrBaseSid, adrName) {

    document.forms['ntp200Form'].ntp200CompanySid.value = adrSid;
    document.forms['ntp200Form'].ntp200CompanyBaseSid.value = adrBaseSid;
    document.forms['ntp200Form'].ntp200CompanyName.value = adrName;

    var url = "../address/adr241.do";
    url += "?ntp200CompanySid=" + adrSid;
    url += "&ntp200CompanyBaseSid=" + adrBaseSid;

    $.ajaxSetup({async:false});
    $.post(url, function(data){
        if ($('#tantoArea')[0] != null) {
            $('#tantoArea')[0].innerHTML = data;
        }
    });
}


function clickAddressName(adrSid) {

    var adrCheck = document.getElementsByName('ntp200Address');
    if (adrCheck != null) {
        for (index = 0; index < adrCheck.length; index++) {
            if (adrCheck[index].value == adrSid) {
                if (adrCheck[index].checked == false) {
                    adrCheck[index].checked = true;
                } else {
                    adrCheck[index].checked = false;
                }
                break;
            }
        }
    }
}


function setParent(selectSid) {

    var parentDocument          = window.opener.document;
    var parentId                = document.forms['ntp200Form'].ntp200parentPageId.value;
    var rowNumber               = document.forms['ntp200Form'].ntp200RowNumber.value;
    var ankenCode               = replaceHtmlTag($('input#ankenCode_'        + selectSid).val());
    var ankenName               = replaceHtmlTag($('input#ankenName_'        + selectSid).val());
    var ankenMikomido           = $('input#ankenMikomido_'    + selectSid).val();
    var ankenNameTitle          = $('input#ankenName_'        + selectSid).val();
    var ankenCompanySid         = $('input#ankenCompanySid_'  + selectSid).val();
    var ankenCompanyCode        = replaceHtmlTag($('input#ankenCompanyCode_'  + selectSid).val());
    var ankenCompanyName        = replaceHtmlTag($('input#ankenCompanyName_' + selectSid).val());
    var ankenCompanyBaseSid     = $('input#ankenBaseSid_'     + selectSid).val();
    var ankenCompanyBaseName    = replaceHtmlTag($('input#ankenBaseName_'    + selectSid).val());

    if (rowNumber != "") {
        rowNumber = "_" + rowNumber;
    }

    window.opener.$('#' + parentId + 'AnkenIdArea'       + rowNumber).html("");
    window.opener.$('#' + parentId + 'AnkenCodeArea'     + rowNumber).html("");
    window.opener.$('#' + parentId + 'AnkenNameArea'     + rowNumber).html("");


    addParentParam(parentId + 'AnkenIdArea' + rowNumber, parentId + 'AnkenSid' + rowNumber, selectSid);

    window.opener.$('#' + parentId + 'AnkenCodeArea' + rowNumber).append("<span class=\"text_anken_code\">案件コード："
            + "<span class=\"anken_code_name" + rowNumber + "\">"
            + ankenCode
            + "</span>"
            + "</span>");

    window.opener.$('#' + parentId + 'AnkenNameArea' + rowNumber).html("<span class=\"text_anken\">"
            + "<a id=\"" + selectSid + "\" class=\"sc_link anken_click\">"
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
        addParentParam(parentId + 'CompanyBaseIdArea' + rowNumber, parentId + 'CompanyBaseSid' + rowNumber, ankenCompanyBaseSid);

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

    //見込み度設定
    if (window.opener.$("input[name=" + parentId + "Mikomido" + rowNumber + "]").val() != null) {
        window.opener.$("input[name=" + parentId + "Mikomido" + rowNumber + "][value=" + ankenMikomido + "]").attr("checked", true);
    }


    window.close();

    return false;
}

function delAnken(parentId, rowNumber) {

    $('#' + parentId + 'AnkenIdArea' + rowNumber).html("");
    $('#' + parentId + 'AnkenCodeArea' + rowNumber).html("");
    $('#' + parentId + 'AnkenNameArea' + rowNumber).html("");

    return false;
}

function changePage(pageCombo) {
    if (pageCombo == 0) {
        document.forms[0].ntp200PageBottom.value = document.forms[0].ntp200PageTop.value;
    } else {
        document.forms[0].ntp200PageTop.value = document.forms[0].ntp200PageBottom.value;
    }
    buttonPush('changePage');
}

function replaceHtmlTag(s) {
    return s.replace(/&/g,"&amp;").replace(/"/g,"&quot;").replace(/'/g,"&#039;").replace(/</g,"&lt;").replace(/>/g,"&gt;") ;
}

$(function() {

    $(".anken_link_area0").live({
         mouseenter:function(){

            $('.td_anken_' + $(this).attr('id')).addClass('anken_link_hover1');
         },
         mouseleave:function(){

            $('.td_anken_' + $(this).attr('id')).removeClass('anken_link_hover1');
         }
     });

    $(".anken_link_area1").live({
        mouseenter:function(){

            $('.td_anken_' + $(this).attr('id')).addClass('anken_link_hover2');
        },
     mouseleave:function(){

            $('.td_anken_' + $(this).attr('id')).removeClass('anken_link_hover2');
        }
     });

    $(".anken_sort_link").live({
        mouseenter:function(){
            $(this).addClass('anken_sort_link_hover');
        },
     mouseleave:function(){
            $(this).removeClass('anken_sort_link_hover');
        }
     });


    $(".anken_sort_link").live({
        click:function(){

          if($(this).attr('id') == $("input[name=ntp200SortKey1]").val()){

            if($("input[name=ntp200OrderKey1]").val() == 0) {
                $(this).removeClass("anken_sort_sel_asc");
                $(this).addClass("anken_sort_sel_desc");
                $("input[name=ntp200OrderKey1]").val(1);
            } else {
                $(this).removeClass("anken_sort_sel_desc");
                $(this).addClass("anken_sort_sel_asc");
                $("input[name=ntp200OrderKey1]").val(0);
            }

          } else {

              $(".anken_sort_link").removeClass("anken_sort_sel_asc");
              $(".anken_sort_link").removeClass("anken_sort_sel_desc");
              $(this).addClass("anken_sort_sel_desc");
              $("input[name=ntp200SortKey1]").val($(this).attr('id'));
              $("input[name=ntp200OrderKey1]").val(1);

          }

          document.forms[0].submit();

        }
     });

});
