function movePortal(ptlSid) {
    document.forms[0].ptlMainSid.value=ptlSid;
    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}

function movePortalSetting() {
    document.forms[0].ptlBackPage.value=1;
    document.forms[0].CMD.value='portalSetting';
    document.forms[0].submit();
    return false;
}

function initArea() {
    hideArea('Top', document.forms[0].ptl010areaTop.value);
    hideArea('Left', document.forms[0].ptl010areaLeft.value);
    hideArea('Center', document.forms[0].ptl010areaCenter.value);
    hideArea('Right', document.forms[0].ptl010areaRight.value);
    hideArea('Bottom', document.forms[0].ptl010areaBottom.value);
    setAreaWidth();
}

function hideArea(areaType, value) {
    if (value == "0") {
        $('#mainScreenList' + areaType).show();
    } else {
        $('#mainScreenList' + areaType).hide();
    }
}

function setAreaWidth() {

    var leftFlg = (document.forms[0].ptl010areaLeft.value == "0");
    var centerFlg = (document.forms[0].ptl010areaCenter.value == "0");
    var rightFlg = (document.forms[0].ptl010areaRight.value == "0");

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
               rightArea.width = '1';
           } else if (!centerFlg && rightFlg) {
               leftArea.width = '50%';
               centerArea.width = '1';
               rightArea.width = '50%';
           } else {
               leftArea.width = '100%';
               centerArea.width = '1';
               rightArea.width = '1';
           }
        }

     } else {
         leftArea.width = '1';
         if (centerFlg && rightFlg) {
             centerArea.width = '67%';
             rightArea.width = '33%';
         } else if (centerFlg && !rightFlg) {
             centerArea.width = '100%';
             rightArea.width = '1';
         } else if (!centerFlg && rightFlg) {
             centerArea.width = '1';
             rightArea.width = '100%';
         }
     }
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

$(function() {

    //aタグhref属性に ${TIME} or ${HASH_UID_TM_KW} が含まれている
    $("a[href *= '${TIME}'],a[href *= '${HASH_UID_TM_KW}']").live("click", function() {

        var url = $(this).attr("href");
        var target = $(this).attr("target");

        clickUrl(url, target);
        return false;
    });
});

function clickUrl(url, target) {

    //URL、パラメータ情報を取得する
    $.ajax({
        async: true,
        url:"../portal/ptl010.do",
        type: "post",
        data: {
            CMD: "getClickUrl",
            url: url}
    }).done(function( data ) {
        if (data != null || data != "") {

            var clickUrl = data.url;
            if( typeof target == 'undefined') {
                window.open(clickUrl, "body");
            } else {
                window.open(clickUrl, target);
            }
        }
    }).fail(function(data){
        //JSONデータ失敗時の処理
    });
}