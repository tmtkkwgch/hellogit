function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function buttonPush2(cmd) {
    var frm = document.forms['man001Form'];
    frm.CMD.value=cmd;
    frm.submit();
    return false;
}

function movePortalSetting() {
    document.man001Form.ptlBackPage.value=1;
    document.man001Form.CMD.value='portalSetting';
    document.man001Form.submit();
    return false;
}

function loadSortable() {
    var lockFlg = document.forms['lockForm'].lockFlg.value;
    if (lockFlg == 1) {
        return false;
    }
    createSortable();
}

$(function() {
    $( ".column" ).sortable({ revert: true });
    $( ".column" ).sortable({ opacity: 0.6 });
    $( ".column" ).sortable({ tolerance: 'pointer' });
    $( ".column" ).sortable({
        connectWith: ".column2"
    });

    $( ".column" ).sortable({
         stop: function(event, ui) {

            var paramString = '';

            positionParam = String($('#mainScreenListLeft').sortable('toArray'));
            if (positionParam != null && positionParam != '') {
                idList = positionParam.split(",");
                for (count = 0; count < idList.length; count++) {
                    paramString += '&man001PositionLeft=' + idList[count];
                }
            }

            positionParam = String($('#mainScreenListRight').sortable('toArray'));
            if (positionParam != null && positionParam != '') {
                idList = positionParam.split(",");
                for (count = 0; count < idList.length; count++) {
                    paramString += '&man001PositionRight=' + idList[count];
                }
            }

            jQuery.ajax("../main/man001.do?CMD=setPosition" + paramString);

         }
    });

    $( ".column" ).sortable({
        over: function(event, ui) {
            $(ui.item).css("width", "75%");
        }
    });

    $( ".column2" ).sortable({ revert: true });
    $( ".column2" ).sortable({ opacity: 0.6 });
    $( ".column2" ).sortable({ tolerance: 'pointer' });

    $( ".column2" ).sortable({
        connectWith: ".column"
    });

    $( ".column2" ).sortable({
        stop: function(event, ui) {

           var paramString = '';

           positionParam = String($('#mainScreenListLeft').sortable('toArray'));
           if (positionParam != null && positionParam != '') {
               idList = positionParam.split(",");
               for (count = 0; count < idList.length; count++) {
                   paramString += '&man001PositionLeft=' + idList[count];
               }
           }

           positionParam = String($('#mainScreenListRight').sortable('toArray'));
           if (positionParam != null && positionParam != '') {
               idList = positionParam.split(",");
               for (count = 0; count < idList.length; count++) {
                   paramString += '&man001PositionRight=' + idList[count];
               }
           }

           jQuery.ajax("../main/man001.do?CMD=setPosition" + paramString);

        }
   });

    $( ".column2" ).sortable({
        over: function(event, ui) {
            $(ui.item).css("width", "25%");
        }
    });

    $( ".column3" ).sortable({ revert: true });
    $( ".column3" ).sortable({ opacity: 0.6 });
    $( ".column3" ).sortable({ tolerance: 'pointer' });
    $( ".column3" ).sortable({
        connectWith: ".column3"
    });
    $( ".column3" ).sortable({
         stop: function(event, ui) {saveScreenPosition();}
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

   $('#mainScreenListLeft').sortable( "disable" )
   $('#mainScreenListRight').sortable( "disable" )
   $('#mainScreenListTop').sortable( "disable" )
   $('#mainScreenListBottom').sortable( "disable" )
   $('#mainScreenListCenter').sortable( "disable" )

});

function saveScreenPosition() {
    var paramString = '';

    positionParam = String($('#mainScreenListLeft').sortable('toArray'));
    if (positionParam != null && positionParam != '') {
        idList = positionParam.split(",");
        for (count = 0; count < idList.length; count++) {
            paramString += '&man001PositionLeft=' + idList[count];
        }
    }

    positionParam = String($('#mainScreenListRight').sortable('toArray'));
    if (positionParam != null && positionParam != '') {
        idList = positionParam.split(",");
        for (count = 0; count < idList.length; count++) {
            paramString += '&man001PositionRight=' + idList[count];
        }
    }

    positionParam = String($('#mainScreenListTop').sortable('toArray'));
    if (positionParam != null && positionParam != '') {
        idList = positionParam.split(",");
        for (count = 0; count < idList.length; count++) {
            paramString += '&man001PositionTop=' + idList[count];
        }
    }

    positionParam = String($('#mainScreenListBottom').sortable('toArray'));
    if (positionParam != null && positionParam != '') {
        idList = positionParam.split(",");
        for (count = 0; count < idList.length; count++) {
            paramString += '&man001PositionBottom=' + idList[count];
        }
    }

    positionParam = String($('#mainScreenListCenter').sortable('toArray'));
    if (positionParam != null && positionParam != '') {
        idList = positionParam.split(",");
        for (count = 0; count < idList.length; count++) {
            paramString += '&man001PositionCenter=' + idList[count];
        }
    }

    jQuery.ajax("../main/man001.do?CMD=setPosition" + paramString);

    return false;
}
function destroySortable() {
    $('#mainScreenListLeft').sortable( "disable" )
    $('#mainScreenListRight').sortable( "disable" )
    $('#mainScreenListTop').sortable( "disable" )
    $('#mainScreenListBottom').sortable( "disable" )
    $('#mainScreenListCenter').sortable( "disable" )
}

function createSortable() {
    $('#mainScreenListLeft').sortable( "enable" )
    $('#mainScreenListRight').sortable( "enable" )
    $('#mainScreenListTop').sortable( "enable" )
    $('#mainScreenListBottom').sortable( "enable" )
    $('#mainScreenListCenter').sortable( "enable" )
}

function man001ChangeSearchMode(mode) {
    var webObj = document.getElementById('man001searchWeb');
    var imgObj = document.getElementById('man001searchImage');

    if (mode == 1) {
        changeStyle(webObj, 'web mode_font_not_select');
        changeStyle(imgObj, 'image mode_font_select');
    } else {
        changeStyle(webObj, 'web mode_font_select');
        changeStyle(imgObj, 'image mode_font_not_select');
    }

    document.getElementsByName('man001searchMode')[0].value = mode;
}

function infoConf(cmd){
    document.forms['man001Form'].CMD.value=cmd;
    document.forms['man001Form'].submit();
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


function initArea() {
    if (document.forms['man001Form'].man001layoutDefFlg.value == "false") {

        hideArea('Top', document.forms['man001Form'].man001areaTop.value);
        hideArea('Left', document.forms['man001Form'].man001areaLeft.value);
        hideArea('Center', document.forms['man001Form'].man001areaCenter.value);
        hideArea('Right', document.forms['man001Form'].man001areaRight.value);
        hideArea('Bottom', document.forms['man001Form'].man001areaBottom.value);
        setAreaWidth();
    }

}

function hideArea(areaType, value) {
    if (value == "0") {
        $('#mainScreenList' + areaType).show();
    } else {
        $('#mainScreenList' + areaType).hide();
    }
}

function setAreaWidth() {

    var leftFlg = (document.forms['man001Form'].man001areaLeft.value == "0");
    var centerFlg = (document.forms['man001Form'].man001areaCenter.value == "0");
    var rightFlg = (document.forms['man001Form'].man001areaRight.value == "0");

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
