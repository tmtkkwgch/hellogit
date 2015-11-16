var portalSubWindow;
var portalPreview;

function changeOpenKbn(itemId, value){

    var parts = document.getElementById('portlet_area' + itemId);

    if (value == 1) {
        parts.className = 'tl4 portlet_area2_closed';
    } else {
        parts.className = 'tl4 portlet_area2';
    }

    var url = '../portal/ptl040.do';
    param= '?CMD=' + 'changeDsp';
    param= param + '&' + 'ptlPortalSid=' + document.forms[0].ptlPortalSid.value;
    param= param + '&' + 'ptl040PortalItemId=' + itemId;
    param= param + '&' + 'ptl040view=' + value;
    url = url + param;
    $.get(url);
}

function delPortlet(itemId, pltName){
    if (confirm(pltName + 'を削除します。\r\nよろしいですか?')) {
       var id = document.getElementById(itemId);
       var ptlParent = id.parentNode;

       var url = '../portal/ptl040.do';
       param= '?CMD=' + 'deletePosition';
       param= param + '&' + 'ptlPortalSid=' + document.forms[0].ptlPortalSid.value;
       param= param + '&' + 'ptl040PortalItemId=' + itemId;
       url = url + param;
       $.get(url);

       ptlParent.removeChild(id);
    }

    return false;
}

function openMainInfoWindow(sid) {

    var winWidth=800;
    var winHeight=600;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ", toolbar=no ,scrollbars=yes, resizable=yes, left=" + winx + ", top=" + winy;
    var url = '../main/man310.do?imssid=' + sid;

    if (!flagSubWindow || (flagSubWindow && subWindow.closed)) {
        subWindow = window.open(url, 'thissite', newWinOpt);
        flagSubWindow = true;
    } else {
        subWindow.location.href=url;
        subWindow.focus();
        return;
    }
}

var subWindow;
var flagSubWindow = false;

$(function() {
    $( ".portal_area" ).sortable({ revert: true });
    $( ".portal_area" ).sortable({ opacity: 0.6 });
    $( ".portal_area" ).sortable({ tolerance: 'pointer' });
    $( ".portal_area" ).sortable({
        connectWith: ".portal_area"
    });
    $( ".portlet" ).addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
                   .find( ".portlet-header" )
                   .addClass( "ui-widget-header ui-corner-all" )
                   .prepend( "<span class='ui-icon ui-icon-minusthick'></span>")
                   .end()
                   .find( ".portlet-content" );
    $( ".portlet-header .ui-icon" ).click(function() {
          $( this ).toggleClass( "ui-icon-minusthick" ).toggleClass( "ui-icon-plusthick" );
          $( this ).parents( ".portlet:first" ).find( ".portlet-content" ).toggle();
    });
    $( ".portal_area" ).disableSelection();
    $( ".portal_area" ).sortable({
         stop: function(event, ui) {saveScreenPosition();}
    });

});

function saveScreenPosition() {
    paramString = '';

    paramString += '&ptlPortalSid=' + document.forms[0].ptlPortalSid.value;

    positionParam = String($('#mainScreenListTop').sortable('toArray'));
    if (positionParam != null && positionParam != '') {
        idList = positionParam.split(",");
        for (count = 0; count < idList.length; count++) {
            paramString += '&ptl040PortalItemHead=' + idList[count];
        }
    }

    positionParam = String($('#mainScreenListBottom').sortable('toArray'));
    if (positionParam != null && positionParam != '') {
        idList = positionParam.split(",");
        for (count = 0; count < idList.length; count++) {
            paramString += '&ptl040PortalItemBottom=' + idList[count];
        }
    }

    positionParam = String($('#mainScreenListLeft').sortable('toArray'));
    if (positionParam != null && positionParam != '') {
        idList = positionParam.split(",");
        for (count = 0; count < idList.length; count++) {
            paramString += '&ptl040PortalItemLeft=' + idList[count];
        }
    }

    positionParam = String($('#mainScreenListCenter').sortable('toArray'));
    if (positionParam != null && positionParam != '') {
        idList = positionParam.split(",");
        for (count = 0; count < idList.length; count++) {
            paramString += '&ptl040PortalItemCenter=' + idList[count];
        }
    }

    positionParam = String($('#mainScreenListRight').sortable('toArray'));
    if (positionParam != null && positionParam != '') {
        idList = positionParam.split(",");
        for (count = 0; count < idList.length; count++) {
            paramString += '&ptl040PortalItemRight=' + idList[count];
        }
    }

    jQuery.ajax("../portal/ptl040.do?CMD=setPosition" + paramString);

    return false;
}


function initChgArea() {

    var topView = document.forms[0].ptl040areaTop.value;
    var bottomView = document.forms[0].ptl040areaBottom.value;
    var leftView = document.forms[0].ptl040areaLeft.value;
    var centerView = document.forms[0].ptl040areaCenter.value;
    var rightView = document.forms[0].ptl040areaRight.value;

    areaShowOrHide('Top', topView);
    areaShowOrHide('Bottom', bottomView);
    areaShowOrHide('Left', leftView);
    areaShowOrHide('Center', centerView);
    areaShowOrHide('Right', rightView);

    spaceShowOrHide();
}

function chgArea(checkId, areaType, view) {

    areaShowOrHide(areaType, view);
    setAreaWidth();
    spaceShowOrHide();
}

function areaShowOrHide(areaType, view) {

    if (view == 0) {
        $('#mainScreenList' + areaType).show();
    } else {
        $('#mainScreenList' + areaType).hide();
    }

    setAreaWidth();
    spaceShowOrHide();
}

function setAreaWidth() {

    var leftView = document.forms[0].ptl040areaLeft.value;
    var centerView = document.forms[0].ptl040areaCenter.value;
    var rightView = document.forms[0].ptl040areaRight.value;

    var leftFlg = (leftView == 0);
    var centerFlg = (centerView == 0);
    var rightFlg = (rightView == 0);


    var leftArea = document.getElementById('mainScreenListLeft');
    var centerArea = document.getElementById('mainScreenListCenter');
    var rightArea = document.getElementById('mainScreenListRight');

    if (leftFlg) {
        if (centerFlg && rightFlg) {
           leftArea.width = '33%';
           centerArea.width = '33%';
           rightArea.width = '33%';
        } else {

           if (centerFlg && !rightFlg) {
               leftArea.width = '33%';
               centerArea.width = '67%';
           } else if (!centerFlg && rightFlg) {
               leftArea.width = '50%';
               rightArea.width = '50%';
           } else {
               leftArea.width = '100%';

           }
        }

     } else {
         if (centerFlg && rightFlg) {
             centerArea.width = '67%';
             rightArea.width = '33%';
         } else if (centerFlg && !rightFlg) {
             centerArea.width = '100%';
         } else if (!centerFlg && rightFlg) {
             rightArea.width = '100%';
         }
     }
}

function spaceShowOrHide() {
    var leftView = document.forms[0].ptl040areaLeft.value;
    var centerView = document.forms[0].ptl040areaCenter.value;
    var rightView = document.forms[0].ptl040areaRight.value;

    var leftSpaceDspFlg = false;
    if (leftView == 0) {
        if (centerView == 0 || rightView == 0) {
            leftSpaceDspFlg = true;
        }
    }

    var rightSpaceDspFlg = false;
    if (rightView == 0) {
        if (centerView == 0 || leftView == 0) {
            rightSpaceDspFlg = true;
        }
    }

    if (leftSpaceDspFlg == true) {
        $('#left_space').show();
    } else {
        $('#left_space').hide();
    }

    if (rightSpaceDspFlg == true) {
        $('#right_space').show();
    } else {
        $('#right_space').hide();
    }
}

function openPortalPopup(num){
    var winWidth=600;
    var winHeight=400;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);
    var ptlSid = document.forms[0].ptlPortalSid.value;

    var opt = 'width=' + winWidth + ', height=' + winHeight + ', resizable=yes , toolbar=yes ,' +
    'resizable=yes , left=' + winx + ', top=' + winy + ',scrollbars=yes';

    var popupHtml = '../portal/ptl080.do?ptlPortalSid=' + ptlSid;
    if (num && num == 1) {
        popupHtml = '../portal/ptl081.do?ptlPortalSid=' + ptlSid;
    }
    portalSubWindow = window.open(popupHtml, 'thissite', opt);

    return false;
}

function portalPopupClose() {
    if(portalSubWindow != null){
        portalSubWindow.close();
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

function openPortalPreview(sid) {
    var winLocation=0;
    var winStatus=0;
    var winToolbar=0;
    var winScrollbars=1;

    var opt = 'location=' + winLocation + ', status=' + winStatus + ', toolbar=' + winToolbar + ', scrollbars=' + winScrollbars;
    var popHtml = '../portal/ptl070.do?ptlPortalSid=' + sid;

    portalPreview = window.open(popHtml, 'portalPreview', opt);
}

function closePortalPreview() {
    if(portalPreview != null){
        portalPreview.close();
    }
}
