var winWidth=1000,
    winHeight=900,
    wmlSubWindow,
    wmlFlagSubWindow = false;

function openAddress(paramName) {
    var url = '../address/adr010.do?adr010webmail=1&adr010webmailAddress=' + paramName;
    return openEditorSubWindow(url);
}

function openSyain(paramName) {
    var url = '../user/usr040.do?usr040webmail=1&usr040webmailAddress=' + paramName;
    return openEditorSubWindow(url);
}

function openSyainPlus(paramName) {
    var url = '../user/usr040.do?usr040webmail=1&usr040webmailAddress=' + paramName;
    url += '&usr040webmailType=1';
    return openEditorSubWindow(url);
}

function openDestlistSubWindow(destlistId, paramName) {
    var url = '../webmail/wml290.do?wmlEditDestList=' + destlistId + ' &wml290webmailAddress=' + paramName;
    return openEditorSubWindow(url);
}

function openEditorSubWindow(url) {
    if ($(window).height() - 20 < 900) {
        winHeight = $(window).height() - 20;
        if (winHeight < 100) {
            winHeight = 100;
        }
    }

    var winx = getWmlCenterX(winWidth);
    var winy = getWmlCenterY(winHeight);
    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ", toolbar=no ,scrollbars=yes, resizable=yes, left=" + winx + ", top=" + winy;

    if (!wmlFlagSubWindow || (wmlFlagSubWindow && wmlSubWindow.closed)) {
        wmlSubWindow = window.open(url, 'thissite', newWinOpt);
        smlFlagSubWindow = true;
    } else {
        wmlSubWindow.location.href=url;
        wmlSubWindow.focus();
        return;
    }

    return false;
}

function wmlEditorWindowClose(){
    if(wmlSubWindow != null){
        wmlSubWindow.close();
    }
}

function wmlAfterNewWinOpenUser(win){
    win.moveTo(0,0);
    wmlSubWindow.focus();
    return;
}

function getWmlCenterX(winWidth) {
  var x = (screen.width - winWidth) / 2;
  return x;
}

function getWmlCenterY(winHeight) {
  var y = (screen.height - winHeight) / 2;
  return y;
}

function openEntryAddress(mailNum) {
    var url = '../address/adr020.do?adr020webmail=1&adr020webmailId=' + mailNum;
    openEntrySubWindow(url);
}

function openEntrySmail() {
    var url = '../smail/sml010.do?CMD=calledWebmail&sml020webmail=1&sml020webmailId=' + getParamValue('wml010shareMailNum');
    YAHOO.example.container.dialog8.cancel();
    openEntrySubWindow(url);
}

function openEntryCircular() {
    var url = '../circular/cir040.do?CMD=calledWebmail&cir040webmailId=' + getParamValue('wml010shareMailNum');
    YAHOO.example.container.dialog8.cancel();
    openEntrySubWindow(url);
}

function openEntryFilekanri() {
    var url = '../file/fil080.do?CMD=calledWebmail&fil080webmailId=' + getParamValue('wml010shareMailNum');
    YAHOO.example.container.dialog8.cancel();
    openEntrySubWindow(url);
}

function openEntrySubWindow(url) {
    window.document.getElementById("wml010EntryMailArea").src = url + '&reload=' + (+new Date());
    window.document.getElementById("wml010EntryMailArea").src
      = window.document.getElementById("wml010EntryMailArea").src;

    var dialog9Height = YAHOO.util.Dom.getViewportHeight() - 80;
    if (document.body.clientHeight <= 0) {
        dialog9Height = 80;
    }

    document.getElementById('wml010EntryMailArea').style.height = dialog9Height + 'px';

    var dialog9Width = 1000;
    if (YAHOO.util.Dom.getViewportWidth() * 0.9 > 950) {
        dialog9Width = YAHOO.util.Dom.getViewportWidth() * 0.9;
    }

    document.getElementById('dialog9').style.width = dialog9Width;
    YAHOO.example.container.dialog9.show();

}

function webmailEntrySubWindowClose() {
    YAHOO.example.container.dialog9.cancel();
}