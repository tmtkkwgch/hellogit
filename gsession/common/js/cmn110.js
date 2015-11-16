function confirmButtonPush(){
    document.forms[0].CMD.value = 'fileUpload';
    document.forms['cmn110Form'].cmn110uploadType.value=0;
    document.forms[0].submit();
    return false;
}

function checkStatus(cmn110CloseFlg) {
    var decision = document.forms[0].cmn110Decision.value;
    if (decision == 1) {
        var tempCloseFlg = false;
        if (document.forms[0].cmn110tempName) {
          var tempNameList = $("*[name=cmn110tempName]");
          var tempSaveNameList = $("*[name=cmn110tempSaveName]");
          for (tempIdx = 0; tempIdx < tempNameList.length; tempIdx++) {
            var tempName = tempNameList[tempIdx].value;
            var tempSaveName = tempSaveNameList[tempIdx].value;
            if (document.forms[0].cmn110Mode.value == 1) {
                var imageName = tempName;
                var imageSaveName = tempSaveName;
                tempCloseFlg = true;
                if (document.forms[0].cmn110TempDirPlus.value == 'bbs') {
                    window.opener.document.bbs030Form.bbs030ImageName.value=imageName;
                    window.opener.document.bbs030Form.bbs030ImageSaveName.value=imageSaveName;
                    window.opener.document.bbs030Form.submit();

                } else if (document.forms[0].cmn110TempDirPlus.value == 'projectIco') {
                    var prjCmdMode = document.forms[0].cmn110PrjCmdMode.value;
                    var prjSid = document.forms[0].cmn110PrjSid.value;
                    var icoSetFlg = document.forms[0].cmn110PrjUseFlg.value;

                    window.opener.document.prj020Form.prj020IcoName.value=imageName;
                    window.opener.document.prj020Form.prj020IcoSaveName.value=imageSaveName;
                    window.opener.document.prj020Form.prj020cmdMode.value=prjCmdMode;
                    window.opener.document.prj020Form.prj020prjSid.value=prjSid;
                    window.opener.document.prj020Form.prj020IcoSetFlg.value=icoSetFlg;

                    window.opener.document.prj020Form.submit();

                } else if (document.forms[0].cmn110TempDirPlus.value == 'cmn160Logo') {
                    window.opener.document.cmn160Form.cmn160LogoName.value=imageName;
                    window.opener.document.cmn160Form.cmn160LogoSaveName.value=imageSaveName;
                    window.opener.document.cmn160Form.cmn160TempSetFlg.value='1';

                    window.opener.document.cmn160Form.submit();

                } else if (document.forms[0].cmn110TempDirPlus.value == 'cmn160MenuLogo') {
                    window.opener.document.cmn160Form.cmn160MenuLogoName.value=imageName;
                    window.opener.document.cmn160Form.cmn160MenuLogoSaveName.value=imageSaveName;
                    window.opener.document.cmn160Form.cmn160MenuTempSetFlg.value='1';

                    window.opener.document.cmn160Form.submit();

                } else if (document.forms[0].cmn110TempDirPlus.value == 'main') {
                    window.opener.document.man340Form.CMD.value='getImg';
                    window.opener.document.man340Form.man340file.value=imageName;
                    window.opener.document.man340Form.man340SaveFile.value=imageSaveName;
                    window.opener.document.man340Form.submit();

                }
            } else if (document.forms[0].cmn110Mode.value == 2) {

                setParentParamFileMode();
                if (document.forms[0].cmn110fileLimit.value == 1) {
                    tempCloseFlg = true;
                } else {
                    document.forms[0].cmn110Decision.value = 0;
                }

            } else if (document.forms[0].cmn110pluginId.value.indexOf('enquete') >= 0) {
                tempCloseFlg = true;
                window.opener.document.forms[0].submit();

            } else if (document.forms[0].cmn110fileLimit.value == 1) {
                setParentParam(tempIdx);
                tempCloseFlg = true;
            } else if (document.forms[0].cmn110Mode.value == 4) {
                tempCloseFlg = true;
                window.opener.document.usr031Form.usr031ImageName.value=tempName;
                window.opener.document.usr031Form.usr031ImageSaveName.value=tempSaveName;
                window.opener.document.usr031Form.submit();
            } else if (document.forms[0].cmn110Mode.value == 5) {
                tempCloseFlg = true;
                window.opener.document.fil030Form.fil030ImageName.value=tempName;
                window.opener.document.fil030Form.fil030ImageSaveName.value=tempSaveName;
                window.opener.document.fil030Form.fil030InitFlg.value='1';
                window.opener.document.fil030Form.submit();
            } else {
                setParentParam(tempIdx);
                document.forms[0].cmn110Decision.value = 0;
            }
          }

        }

        if (cmn110CloseFlg) {
            tempCloseFlg = true;
        }

        if (tempCloseFlg) {
            tempClose();
        }
    }
}

function setParentParamFileMode() {

    if (!window.opener.document.createElement) return;

    var fileArray;
    var keyStr;

    fileArray = document.getElementsByName("fileList");
    keyStr = document.forms[0].splitStr.value;

    var parentListName = document.forms[0].cmn110parentListName.value;
    var parentFileList = window.opener.document.getElementsByName(parentListName);

    if (parentFileList != null && parentFileList.length > 0) {
        while (parentFileList[0].options.length > 0){
            parentFileList[0].removeChild(parentFileList[0].options[0]);
        }
    }

    if (fileArray != null && fileArray.length > 0) {

        for (i = 0; i < fileArray.length; i++) {
            var sp = fileArray[i].value.split(keyStr);

            if (sp != null && sp.length == 3) {

                var tempName = sp[0];
                var updateKbn = sp[1];
                var tempSaveName = sp[2];

                var option = window.opener.document.createElement("option");
                option.value = tempSaveName;

                if (updateKbn == 3) {
                    option.style.backgroundColor = '#FFEEBB';
                }

                var optionStr = window.opener.document.createTextNode(tempName);
                option.appendChild(optionStr);

                parentFileList[0].appendChild(option);
            }
        }
    }
}

function tempClose() {
    window.close();
}

function setParentParam(tempIdx){

    if (!window.opener.document.createElement) return;

    var tempName = $("*[name=cmn110tempName]")[tempIdx].value;
    var tempSaveName = $("*[name=cmn110tempSaveName]")[tempIdx].value;

    var option = window.opener.document.createElement("option");
    option.value = tempSaveName;
    var optionStr = window.opener.document.createTextNode(tempName);
    option.appendChild(optionStr);

    var parentListName = document.forms[0].cmn110parentListName.value;
    var parentFileList = window.opener.document.getElementsByName(parentListName);

    var fileLimit = document.forms[0].cmn110fileLimit.value;

    if (fileLimit == 1) {
        parentFileList[0].options[0] = null;
    }

    parentFileList[0].appendChild(option);
}

var subWindow;
var flagSubWindow = false;

function opnTemp(listName, pluginId, fileLimit, mode) {
    var winWidth=558;
    var winHeight=290;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ",left=" + winx + ",top=" + winy + ",toolbar=no,scrollbars=yes";

    var url = '../common/cmn110.do';
    url = url + '?cmn110parentListName=' + listName;
    url = url + '&cmn110pluginId=' + pluginId;
    url = url + '&cmn110fileLimit=' + fileLimit;
    url = url + '&cmn110Mode=' + mode;

    if (!flagSubWindow || subWindow == null || (flagSubWindow && subWindow.closed)) {
        subWindow = window.open(url, 'thissite', newWinOpt);
        flagSubWindow = true;
        afterNewTempFileWinOpen(subWindow);
    } else {
        subWindow.location.href=url;
        subWindow.focus();
        return;
    }
}

function opnTempPlus(listName, pluginId, fileLimit, mode, plusDir) {
    var winWidth=558;
    var winHeight=290;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ",left=" + winx + ",top=" + winy + ",toolbar=no,scrollbars=yes";

    var url = '../common/cmn110.do';
    url = url + '?cmn110parentListName=' + listName;
    url = url + '&cmn110pluginId=' + pluginId;
    url = url + '&cmn110fileLimit=' + fileLimit;
    url = url + '&cmn110Mode=' + mode;
    url = url + '&cmn110TempDirPlus=' + plusDir;

    if (!flagSubWindow || subWindow == null || (flagSubWindow && subWindow.closed)) {
        subWindow = window.open(url, 'thissite', newWinOpt);
        flagSubWindow = true;
        afterNewTempFileWinOpen(subWindow);
    } else {
        subWindow.location.href=url;
        subWindow.focus();
        return;
    }
}

//プロジェクトプラグインで使用
function opnTempPlusPrj(listName, pluginId, fileLimit, mode, plusDir, cmdMode, prjSid) {
    var winWidth=558;
    var winHeight=290;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ",left=" + winx + ",top=" + winy + ",toolbar=no,scrollbars=yes";

    var url = '../common/cmn110.do';
    url = url + '?cmn110parentListName=' + listName;
    url = url + '&cmn110pluginId=' + pluginId;
    url = url + '&cmn110fileLimit=' + fileLimit;
    url = url + '&cmn110Mode=' + mode;
    url = url + '&cmn110TempDirPlus=' + plusDir;

    //プロジェクト使用フラグ
    url = url + '&cmn110PrjUseFlg=' + 1;

    if (cmdMode == 1) {
        //プロジェクト編集時
        url = url + '&cmn110PrjCmdMode=' + 1;
        url = url + '&cmn110PrjSid=' + prjSid;
    }

    if (!flagSubWindow || subWindow == null || (flagSubWindow && subWindow.closed)) {
        subWindow = window.open(url, 'thissite', newWinOpt);
        flagSubWindow = true;
        afterNewTempFileWinOpen(subWindow);
    } else {
        subWindow.location.href=url;
        subWindow.focus();
        return;
    }
}

function windowClose(){
    if(subWindow != null){
        subWindow.close();
    }
}

function afterNewTempFileWinOpen(win){
    subWindow.focus();
    return;
}

function getCenterX(winWidth) {
  var x = (screen.width - winWidth) / 2;
  return x;
}

function getCenterY(winHeight) {
  var y = (screen.height - winHeight) / 2;
  return y;
}