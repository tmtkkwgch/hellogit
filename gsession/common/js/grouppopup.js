/********************************************************************
 * グループツリーポップアップ
 */

/**
 * formOj フォームオブジェクト
 * selBoxName 親画面セレクトボックス名
 * myGpFlg マイグループフラグ 0：表示しない 1:表示する
 */
function openGroupWindow(formOj, selBoxName, myGpFlg) {
    openGroup(formOj, selBoxName, myGpFlg, 0, "");
    return;
}

/**
 * 親画面に戻る際にアクションにコマンドを渡す場合
 * cmd コマンド
 */
function openGroupWindow(formOj, selBoxName, myGpFlg, cmd) {
    if (cmd != "") {
        document.forms[0].CMD.value=cmd;
    }
    openGroup(formOj, selBoxName, myGpFlg, 0, "");
    return;
}

/**
 * 親画面へ値設定後、親画面の再読み込みを設定
 * submitFlg  0：再読み込みする 1:しない
 */
function openGroupWindow(formOj, selBoxName, myGpFlg, cmd, submitFlg) {
    if (cmd != "") {
        document.forms[0].CMD.value=cmd;
    }
    openGroup(formOj, selBoxName, myGpFlg, submitFlg, "");
    return;
}

/**
 * 部分更新画面の場合に更新を実行するボタンの名前を渡す
 * prtPrm  更新を実行するボタンの名前
 */
function openGroupWindow(formOj, selBoxName, myGpFlg, cmd, submitFlg, prtPrm) {
    if (cmd != "") {
        document.forms[0].CMD.value=cmd;
    }
    openGroup(formOj, selBoxName, myGpFlg, submitFlg, prtPrm);
    return;
}
/**
 * 選択不可のグループがある場合にSIDを格納しているhiddenパラメータ名を渡す
 * disableGpInp  選択不可のグループSIDを格納しているhiddenパラメータ名
 */
function openGroupWindow(formOj, selBoxName, myGpFlg, cmd, submitFlg, prtPrm, disableGpInp) {
    openGroupWindow_Disabled(formOj, selBoxName, myGpFlg, cmd, submitFlg, prtPrm, disableGpInp, 0);
    return;
}

/**
 * 選択不可のグループがある場合にSIDを格納しているhiddenパラメータ名を渡す
 * disableGpInp 選択不可のグループSIDを格納しているhiddenパラメータ名
 * disableGpFlg 選択不可のグループ設定の有効/無効 0:無効 1:有効
 */
function openGroupWindow_Disabled(formOj, selBoxName, myGpFlg, cmd, submitFlg, prtPrm, disableGpInp, disableGpFlg) {
    if (cmd != "") {
        document.forms[0].CMD.value=cmd;
    }
    openGroup(formOj, selBoxName, myGpFlg, submitFlg, prtPrm, disableGpInp, disableGpFlg);
    return;
}

function openGroup(formOj, selBoxName, myGpFlg, submitFlg, prtPrm) {
    openGroup(formOj, selBoxName, myGpFlg, submitFlg, prtPrm, null, 0);
    return;
}
function openGroup(formOj, selBoxName, myGpFlg, submitFlg, prtPrm, disableGpInp, disableGpFlg) {
    var winWidth=590;
    var winHeight=480;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    var smtFlg = "";

    if (submitFlg != "") {
        smtFlg = submitFlg;
    }

    if (prtPrm == undefined) {
        prtPrm = "";
    }


    formOj.blur()
    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ", toolbar=no ,scrollbars=yes, resizable=yes, left=" + winx + ", top=" + winy;
    var url = '';

    if (!flagSubWindow || !subWindow || (flagSubWindow && (subWindow.closed || subWindow.top != subWindow.self))) {

        subWindow = window.open(url, 'thissite', newWinOpt);
        flagSubWindow = true;

        //form作成
        var form = document.createElement("form");
        form.target = "thissite";
        form.method = 'post';
        form.action = '../common/cmn210.do';

        //hiddenの設定
        var qs = [
                  {type:'hidden', name:'parentDspID', value:formOj.form.name}
                  ,{type:'hidden', name:'selBoxName', value:selBoxName}
                  ,{type:'hidden', name:'myGroupFlg', value:myGpFlg}
                  ,{type:'hidden', name:'submitFlg', value:smtFlg}
                  ,{type:'hidden', name:'prtPrm', value:prtPrm}
                  ,{type:'hidden', name:'cmn210disableGroupFlg', value:disableGpFlg}
                  ];
        if (disableGpInp) {
            $("[name='" + disableGpInp + "']").each(function(){
                qs.push({type:'hidden', name:'cmn210disableGroupSid', value:this.value});
            });
        }

        for(var i = 0; i < qs.length; i++) {
            var ol = qs[i];
            var input = document.createElement("input");
            for(var p in ol) {
                input.setAttribute(p, ol[p]);
            }
            form.appendChild(input);
        }

        var body = document.getElementsByTagName("body")[0];
        // 作成したformをbodyに追加
        body.appendChild(form);
        //サブミット
        form.submit();
        //追加したformを削除
        body.removeChild(form);

    } else {
        subWindow.focus();
    }

}

/********************************************************************
 * 全グループから選択ポップアップ
 *
 * formOj     フォームオブジェクト
 * selBoxName 親画面セレクトボックス名
 * selGpSid   親画面選択グループSID
 * myGpFlg    マイグループフラグ 0：表示しない 1:表示する
 * submitCmd  親画面遷移コマンド
 * svName     選択ユーザ保持パラメータ名
 * selUsrSid  親画面ユーザSid
 * selGrpFlg  グループ一覧表示フラグ 0:表示しない 1:表示する
 * admGpFlg   管理者グループ表示フラグ 0:表示する 1:表示しない
 * splitFlg   文字列置換えフラグ 0:しない 1:する
 * cmdKbn     親画面に渡すコマンドの名前 0:CMD 1:cmd
 */

function openAllGroup(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg) {
    openAllGroup(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, 0, 0, 0);
    return;
}

function openAllGroup(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg, admGpFlg) {
    openAllGroup(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, admGpFlg, 0, 0);
    return;
}

function openAllGroup(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg, admGpFlg, splitFlg) {
    openAllGroup(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, admGpFlg, splitFlg, 0);
    return;
}

function openAllGroup(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg, admGpFlg, splitFlg, cmdKbn) {

    var winWidth=1024;
    var winHeight=700;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    formOj.blur()
    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ", toolbar=no ,scrollbars=yes, resizable=yes, left=" + winx + ", top=" + winy;
    var url = '';

    if (!flagSubWindow || !subWindow || (flagSubWindow && (subWindow.closed || subWindow.top != subWindow.self))) {
        subWindow = window.open(url, 'thissite', newWinOpt);
        flagSubWindow = true;

        //form作成
        var form = document.createElement("form");
        form.target = "thissite";
        form.method = 'post';
        form.action = '../common/cmn220.do';

        var svUsers=[];
        $("[name='" + svName + "']").each(function(){
            if (splitFlg == 1) {
                this.value = this.value.split("d41d8cd98f00b204e9800998ecf8427e").join("");
            }
            svUsers.push(this.value);
        });

        //hiddenの設定
        var qs = [
                  {type:'hidden', name:'parentDspID', value:formOj.form.name}
                  ,{type:'hidden', name:'selBoxName', value:selBoxName}
                  ,{type:'hidden', name:'myGroupFlg', value:myGpFlg}
                  ,{type:'hidden', name:'submitCmd', value:submitCmd}
                  ,{type:'hidden', name:'sv_users', value:svUsers}
                  ,{type:'hidden', name:'selUserSid', value:selUsrSid}
                  ,{type:'hidden', name:'svDomName', value:svName}
                  ,{type:'hidden', name:'selGroup', value:selGpSid}
                  ,{type:'hidden', name:'admGpFlg', value:admGpFlg}
                  ,{type:'hidden', name:'splitFlg', value:splitFlg}
                  ,{type:'hidden', name:'cmdKbn', value:cmdKbn}
                  ,{type:'hidden', name:'selGrpFlg', value:selGrpFlg}
                  ];

        for(var i = 0; i < qs.length; i++) {
            var ol = qs[i];
            var input = document.createElement("input");
            for(var p in ol) {
                input.setAttribute(p, ol[p]);
            }
            form.appendChild(input);
        }

        var body = document.getElementsByTagName("body")[0];
        // 作成したformをbodyに追加
        body.appendChild(form);
        //サブミット
        form.submit();
        //追加したformを削除
        body.removeChild(form);

    } else {
        subWindow.focus();
    }
}
/**
 * 選択禁止対応
 * banUsrSid 選択禁止ユーザSID保持パラメータ名
 * banGpSid 選択禁止グループSID保持パラメータ名
 * disableGpSid ユーザ絞り込みグループ選択禁止保持パラメータ名
 **/
function openAllGroup_setDisable(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg, admGpFlg, splitFlg, cmdKbn, banUserInp, banGpInp, disableGpInp) {

    var winWidth=1024;
    var winHeight=700;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    formOj.blur()
    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ", toolbar=no ,scrollbars=yes, resizable=yes, left=" + winx + ", top=" + winy;
    var url = '';

    if (!flagSubWindow || !subWindow || (flagSubWindow && (subWindow.closed || subWindow.top != subWindow.self))) {
        subWindow = window.open(url, 'thissite', newWinOpt);
        flagSubWindow = true;

        //form作成
        var form = document.createElement("form");
        form.target = "thissite";
        form.method = 'post';
        form.action = '../common/cmn220.do';

        var svUsers=[];
        $("[name='" + svName + "']").each(function(){
            if (splitFlg == 1) {
                this.value = this.value.split("d41d8cd98f00b204e9800998ecf8427e").join("");
            }
            svUsers.push(this.value);
        });

        //hiddenの設定
        var qs = [
                  {type:'hidden', name:'parentDspID', value:formOj.form.name}
                  ,{type:'hidden', name:'selBoxName', value:selBoxName}
                  ,{type:'hidden', name:'myGroupFlg', value:myGpFlg}
                  ,{type:'hidden', name:'submitCmd', value:submitCmd}
                  ,{type:'hidden', name:'sv_users', value:svUsers}
                  ,{type:'hidden', name:'selUserSid', value:selUsrSid}
                  ,{type:'hidden', name:'svDomName', value:svName}
                  ,{type:'hidden', name:'selGroup', value:selGpSid}
                  ,{type:'hidden', name:'admGpFlg', value:admGpFlg}
                  ,{type:'hidden', name:'splitFlg', value:splitFlg}
                  ,{type:'hidden', name:'cmdKbn', value:cmdKbn}
                  ,{type:'hidden', name:'selGrpFlg', value:selGrpFlg}
                  ];
        if (banUserInp) {
            $("[name='" + banUserInp + "']").each(function(){
                qs.push({type:'hidden', name:'cmn220banUserSid', value:this.value});
            });
        }
        if (banGpInp) {
            $("[name='" + banGpInp + "']").each(function(){
                qs.push({type:'hidden', name:'cmn220banGroupSid', value:this.value});
            });
        }
        if (disableGpInp) {
            $("[name='" + disableGpInp + "']").each(function(){
                qs.push({type:'hidden', name:'cmn220disableGroupSid', value:this.value});
            });
        }

        for(var i = 0; i < qs.length; i++) {
            var ol = qs[i];
            var input = document.createElement("input");
            for(var p in ol) {
                input.setAttribute(p, ol[p]);
            }
            form.appendChild(input);
        }

        var body = document.getElementsByTagName("body")[0];
        // 作成したformをbodyに追加
        body.appendChild(form);
        //サブミット
        form.submit();
        //追加したformを削除
        body.removeChild(form);

    } else {
        subWindow.focus();
    }
}

/* 呼び出し元再読み込みなし */
function openAllGroup_no_submit(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg, admGpFlg, splitFlg, cmdKbn, parentFormName, paramBtn) {
    return openAllGroup_no_submit(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg, admGpFlg, splitFlg, cmdKbn, parentFormName, paramBtn, null, null, null);
}
/**
 * 呼び出し元再読み込みなし
 * banUsrSid 選択禁止ユーザSID保持パラメータ名
 * banGpSid 選択禁止グループSID保持パラメータ名
 * disableGpSid disableGpSid ユーザ絞り込みグループ選択禁止保持パラメータ名
 **/
function openAllGroup_no_submit(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg, admGpFlg, splitFlg, cmdKbn, parentFormName, paramBtn, banUserInp, banGpInp, disableGpInp) {

    var winWidth=1024;
    var winHeight=700;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    formOj.blur()
    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ", toolbar=no ,scrollbars=yes, resizable=yes, left=" + winx + ", top=" + winy;
    var url = '';

    if (!flagSubWindow || !subWindow || (flagSubWindow && (subWindow.closed || subWindow.top != subWindow.self))) {
        subWindow = window.open(url, 'thissite', newWinOpt);
        flagSubWindow = true;

        //form作成
        var form = document.createElement("form");
        form.target = "thissite";
        form.method = 'post';
        form.action = '../common/cmn220.do';

        var svUsers=[];
        $("[name='" + svName + "']").each(function(){
            if (splitFlg == 1) {
                this.value = this.value.split("d41d8cd98f00b204e9800998ecf8427e").join("");
            }
            svUsers.push(this.value);
        });

        var prm = '?parentDspID=' + formOj.form.name + '&selBoxName=' + selBoxName + '&myGroupFlg=' + myGpFlg
        + '&submitCmd=' + submitCmd + '&sv_users=' + svUsers + '&selUserSid=' + selUsrSid
        + '&svDomName=' + svName + '&selGroup=' + selGpSid + '&admGpFlg=' + admGpFlg
        + '&splitFlg=' + splitFlg + '&cmdKbn=' + cmdKbn + '&selGrpFlg=' + selGrpFlg + '&parentFormName=' + parentFormName + '&paramBtn=' + paramBtn;

        //hiddenの設定
        var qs = [
                  {type:'hidden', name:'parentDspID', value:formOj.form.name}
                  ,{type:'hidden', name:'selBoxName', value:selBoxName}
                  ,{type:'hidden', name:'myGroupFlg', value:myGpFlg}
                  ,{type:'hidden', name:'submitCmd', value:submitCmd}
                  ,{type:'hidden', name:'sv_users', value:svUsers}
                  ,{type:'hidden', name:'selUserSid', value:selUsrSid}
                  ,{type:'hidden', name:'svDomName', value:svName}
                  ,{type:'hidden', name:'selGroup', value:selGpSid}
                  ,{type:'hidden', name:'admGpFlg', value:admGpFlg}
                  ,{type:'hidden', name:'splitFlg', value:splitFlg}
                  ,{type:'hidden', name:'cmdKbn', value:cmdKbn}
                  ,{type:'hidden', name:'selGrpFlg', value:selGrpFlg}
                  ,{type:'hidden', name:'parentFormName', value:parentFormName}
                  ,{type:'hidden', name:'paramBtn', value:paramBtn}
                  ];
        if (banUserInp) {
            $("[name='" + banUserInp + "']").each(function(){
                qs.push({type:'hidden', name:'cmn220banUserSid', value:this.value});
            });
        }
        if (banGpInp) {
            $("[name='" + banGpInp + "']").each(function(){
                qs.push({type:'hidden', name:'cmn220banGroupSid', value:this.value});
            });
        }
        if (disableGpInp) {
            $("[name='" + disableGpInp + "']").each(function(){
                qs.push({type:'hidden', name:'cmn220disableGroupSid', value:this.value});
            });
        }
        for(var i = 0; i < qs.length; i++) {
            var ol = qs[i];
            var input = document.createElement("input");
            for(var p in ol) {
                input.setAttribute(p, ol[p]);
            }
            form.appendChild(input);
        }

        var body = document.getElementsByTagName("body")[0];
        // 作成したformをbodyに追加
        body.appendChild(form);
        //サブミット
        form.submit();
        //追加したformを削除
        body.removeChild(form);

    } else {
        subWindow.focus();
    }
}


/********************************************************************
 * 全グループから選択ポップアップ(移動先２つ)
 *
 * formOj     フォームオブジェクト
 * selBoxName 親画面セレクトボックス名
 * selGpSid   親画面選択グループSID
 * myGpFlg    マイグループフラグ 0：表示しない 1:表示する
 * submitCmd  親画面遷移コマンド
 * svName     選択ユーザ保持パラメータ名(上)
 * svName2    選択ユーザ保持パラメータ名(下)
 * plginId    プラグインid
 * selUsrSid  親画面ユーザSid
 * selGrpFlg  グループ一覧表示フラグ 0:表示しない 1:表示する
 * admGpFlg   管理者グループ表示フラグ 0:表示する 1:表示しない
 * splitFlg   文字列置換えフラグ 0:しない 1:する
 * cmdKbn     親画面に渡すコマンドの名前 0:CMD 1:cmd
 */

function openAllGroup2(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, svName2, plginId, selUsrSid, selGrpFlg) {
    openAllGroup(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg, 0, 0);
    return;
}

function openAllGroup2(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, svName2, plginId, selUsrSid, selGrpFlg, admGpFlg) {
    openAllGroup(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg, admGpFlg, 0, 0);
    return;
}

function openAllGroup2(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, svName2, plginId, selUsrSid, selGrpFlg, admGpFlg, splitFlg) {
    openAllGroup(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, selUsrSid, selGrpFlg, admGpFlg, splitFlg, 0);
    return;
}

function openAllGroup2(formOj, selBoxName, selGpSid, myGpFlg, submitCmd, svName, svName2, plginId, selUsrSid, selGrpFlg, admGpFlg, splitFlg, cmdKbn) {

    var winWidth=1024;
    var winHeight=700;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    formOj.blur()
    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ", toolbar=no ,scrollbars=yes, resizable=yes, left=" + winx + ", top=" + winy;
    var url = '';

    if (!flagSubWindow || !subWindow || (flagSubWindow && (subWindow.closed || subWindow.top != subWindow.self))) {
        //サブウィンドウ表示
        subWindow = window.open(url, 'thissite', newWinOpt);
        flagSubWindow = true;

        //form作成
        var form = document.createElement("form");
        form.target = "thissite";
        form.method = 'post';
        form.action = '../common/cmn230.do';

        var svUsers=[];
        $("[name='" + svName + "']").each(function(){
            if (splitFlg == 1) {
                this.value = this.value.split("d41d8cd98f00b204e9800998ecf8427e").join("");
            }
            svUsers.push(this.value);
        });

        var svUsers2=[];
        $("[name='" + svName2 + "']").each(function(){
            if (splitFlg == 1) {
                this.value = this.value.split("d41d8cd98f00b204e9800998ecf8427e").join("");
            }
            svUsers2.push(this.value);
        });

        //hiddenの設定
        var qs = [
                  {type:'hidden', name:'parentDspID', value:formOj.form.name}
                  ,{type:'hidden', name:'selBoxName', value:selBoxName}
                  ,{type:'hidden', name:'myGroupFlg', value:myGpFlg}
                  ,{type:'hidden', name:'submitCmd', value:submitCmd}
                  ,{type:'hidden', name:'sv_users', value:svUsers}
                  ,{type:'hidden', name:'sv_users2', value:svUsers2}
                  ,{type:'hidden', name:'selUserSid', value:selUsrSid}
                  ,{type:'hidden', name:'svDomName', value:svName}
                  ,{type:'hidden', name:'svDomName2', value:svName2}
                  ,{type:'hidden', name:'plginId', value:plginId}
                  ,{type:'hidden', name:'selGroup', value:selGpSid}
                  ,{type:'hidden', name:'admGpFlg', value:admGpFlg}
                  ,{type:'hidden', name:'splitFlg', value:splitFlg}
                  ,{type:'hidden', name:'cmdKbn', value:cmdKbn}
                  ,{type:'hidden', name:'selGrpFlg', value:selGrpFlg}
                  ];

        for(var i = 0; i < qs.length; i++) {
            var ol = qs[i];
            var input = document.createElement("input");
            for(var p in ol) {
                input.setAttribute(p, ol[p]);
            }
            form.appendChild(input);
        }

        var body = document.getElementsByTagName("body")[0];
        // 作成したformをbodyに追加
        body.appendChild(form);
        //サブミット
        form.submit();
        //追加したformを削除
        body.removeChild(form);

    } else {
        subWindow.focus();
    }
}


var subWindow;
var flagSubWindow = false;

function windowClose(){

    if (document.forms[0].CMD.value != 'subwindow_close_ok') {
      if(subWindow) {
        try {
            subWindow.close();
        } catch (e) {}
      }
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