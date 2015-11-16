function onChildAllChecked(gsid, dirlevel){
    var start = 0;
    var num1 = parseInt(dirlevel);
    var num2 = 0;
    var sizeAry = grpFrame.document.getElementsByName("c1");
    for(i=0; i<sizeAry.length; i++) {
        num2 = parseInt(sizeAry[i].className);
        if (num1 >= num2) {
            start = 0;
        }
        if (sizeAry[i].value == gsid && sizeAry[i].checked == true) {
            start = 1;
        }
        if (start == 1 && sizeAry[i].disabled == false) {
          sizeAry[i].checked = true;
        }
    }
}

function onChildChecked(){
    var end = 0;
    var gsid = 0;
    var dirlevel = 0;
    var level = 0;
    var svLevel = 0;

    var sizeAry = grpFrame.document.getElementsByName("c1");

    for(i=0; i<sizeAry.length; i++) {

        gsid = sizeAry[i].value;
        dirlevel = parseInt(sizeAry[i].className);

        if (sizeAry[i].checked == true) {
            svLevel = dirlevel;
            end = 0;
            for(j=i+1; j < sizeAry.length && end==0; j++) {
                level = parseInt(sizeAry[j].className);

                if (svLevel >= level) {
                    end = 1;
                }
                if (svLevel < level && end==0) {
                    sizeAry[j].checked = true;
                }
            }
        }
    }
    defaultGroup();
}

function onParentChecked(){

    var gsid = 0;
    var dirlevel = 0;
    var level = 0;
    var svLevel = 0;

    sizeAry = grpFrame.document.getElementsByName("c1");
    for(i=0; i<sizeAry.length; i++) {

        gsid = sizeAry[i].value;
        dirlevel = parseInt(sizeAry[i].className);
        svLevel = 0;
        if (sizeAry[i].checked == true) {
        svLevel = dirlevel;
            for(j=i-1; 0<=j; j--) {
                level = parseInt(sizeAry[j].className);
                if (svLevel > level) {
                    sizeAry[j].checked = true;
                    svLevel = level;
                }
            }
        }
    }
    defaultGroup();
}






function defaultGroup() {

    var groupAry = grpFrame.document.getElementsByName('c1');

    //予約済みユーザの場合はグループをdisableにする
    if (document.forms[0].usr030selectuser.value <= 100
    && document.forms[0].processMode.value != 'add') {
        //所属グループ
        for (var i = 0; i < groupAry.length; i++) {
            groupAry[i].disabled = true;
        }
        //デフォルトグループ
        document.forms[0].usr031defgroup.disabled = true;
        return;
    }


    //チェックされたグループを取得する
    var checkGroup = new Object;
    var checkGroupIndex = 0;

    for(i = 0; i < groupAry.length; i++) {
        if (groupAry[i].checked == true) {
            checkGroup[checkGroupIndex] = groupAry[i].value;
            checkGroupIndex++;
        }
    }

    //チェックされたグループ以外のデフォルトグループを非表示にする
    var defValue = document.forms[0].usr031defgroup.value;

    var defGroupAry = document.forms[0].usr031defgroup.options;
    var defLength = defGroupAry.length;
    for (i = defLength - 1; i >= 0; i--) {
        if (defGroupAry[i].value != -1) {
            defGroupAry[i] = null;
        }
    }

    var defId = document.getElementsByName('defGrpId');
    var defName = document.getElementsByName('defGrpNm');
    for (i = 0; i < defId.length; i++) {
        defGroupAry[defGroupAry.length] = new Option(defName[i].value, defId[i].value);
    }

    var defLength = defGroupAry.length;
    for (defIdx = defLength - 1; defIdx >= 0; defIdx--) {
        if (defGroupAry[defIdx].value == -1) {
            continue;
        }

        var remove = true;
        for (chkIdx = 0; chkIdx < checkGroupIndex; chkIdx++) {
            if (checkGroup[chkIdx] == defGroupAry[defIdx].value) {
                remove = false;
                break;
            }
        }

        if (remove == true) {
            if (defValue == defGroupAry[defIdx].value) {
                defValue = -1;
            }
            defGroupAry[defIdx] = null;
        } else {
            if (defValue == defGroupAry[defIdx].value) {
                document.forms[0].usr031defgroup.value = defValue;
                defGroupAry[defIdx].selected = true;
            }
        }
    }
}

function setShowGroup() {

    var groupAry = grpFrame.document.getElementsByName('c1');

    //予約済みユーザの場合はグループのdisableを解除する
    if (document.forms[0].usr030selectuser.value <= 100
    && document.forms[0].processMode.value != 'add') {
        //所属グループ
        for (var i = 0; i < groupAry.length; i++) {
            groupAry[i].disabled = false;
        }
        //デフォルトグループ
        document.forms[0].usr031defgroup.disabled = false;
    }
}

function buttonPushUsr(cmd){
    document.forms[0].CMD.value=cmd;

    if (cmd == 'pictDelete') {
        document.forms[0].usr031ImageName.value='';
    }

    document.forms[0].submit();
    return false;
}


YAHOO.namespace("subbox");
YAHOO.namespace("labelbox");

function init() {

  YAHOO.subbox.subPanel = new YAHOO.widget.Panel("subPanel", { width:"410px", fixedcenter:true, visible:false, constraintoviewport:true, close:false } );
  YAHOO.subbox.subPanel.render();

  YAHOO.labelbox.labelPanel = new YAHOO.widget.Panel("labelPanel", { width:"400px", fixedcenter:true, visible:false, constraintoviewport:true, close:false } );
  YAHOO.labelbox.labelPanel.render();

}

YAHOO.util.Event.addListener(window, "load", init);


function openpos(){
  pos.location='../main/man111.do';
  YAHOO.subbox.subPanel.show();
  return false;
}

function submitStyleChange() {

    //ログイン制御
    document.forms[0].usr031NumCont.disabled=false;

    //自動登録
    document.forms[0].usr031NumAutAdd.disabled=false;
}

function changeUidElementStatus() {

    var mblUseKbn = 0;
    for (i = 0; i < document.forms[0].usr031UsiMblUseKbn.length; i++) {
        if (document.forms[0].usr031UsiMblUseKbn[i].checked == true) {
            mblUseKbn = i;
        }
    }
    var mblUseKbnVal = document.forms[0].usr031UsiMblUseKbn[mblUseKbn].value;

    //モバイル使用 = 許可する
    if (mblUseKbnVal == 0) {

        //ログイン制御
        document.forms[0].usr031NumCont.disabled=false;

        //ログイン制御 = ON
        if (document.forms[0].usr031NumCont.checked) {

            //自動登録
            document.forms[0].usr031NumAutAdd.disabled=false;

            //固体識別番号1
            document.forms[0].usr031CmuUid1.readOnly=false;
            document.forms[0].usr031CmuUid1.style.backgroundColor = '#ffffff';

            //固体識別番号2
            document.forms[0].usr031CmuUid2.readOnly=false;
            document.forms[0].usr031CmuUid2.style.backgroundColor = '#ffffff';

            //固体識別番号3
            document.forms[0].usr031CmuUid3.readOnly=false;
            document.forms[0].usr031CmuUid3.style.backgroundColor = '#ffffff';

            //履歴ボタン1
            document.forms[0].hisBtn1.disabled=false;
            document.forms[0].hisBtn1.style.color = '#000066';

            //履歴ボタン2
            document.forms[0].hisBtn2.disabled=false;
            document.forms[0].hisBtn2.style.color = '#000066';

            //履歴ボタン3
            document.forms[0].hisBtn3.disabled=false;
            document.forms[0].hisBtn3.style.color = '#000066';

       } else {

            windowClose();

            //自動登録
            document.forms[0].usr031NumAutAdd.disabled=true;

            //固体識別番号1
            document.forms[0].usr031CmuUid1.readOnly=true;
            document.forms[0].usr031CmuUid1.style.backgroundColor = '#e0e0e0';

            //固体識別番号2
            document.forms[0].usr031CmuUid2.readOnly=true;
            document.forms[0].usr031CmuUid2.style.backgroundColor = '#e0e0e0';

            //固体識別番号3
            document.forms[0].usr031CmuUid3.readOnly=true;
            document.forms[0].usr031CmuUid3.style.backgroundColor = '#e0e0e0';

            //履歴ボタン1
            document.forms[0].hisBtn1.disabled=true;
            document.forms[0].hisBtn1.style.color = '#e0e0e0';

            //履歴ボタン2
            document.forms[0].hisBtn2.disabled=true;
            document.forms[0].hisBtn2.style.color = '#e0e0e0';

            //履歴ボタン3
            document.forms[0].hisBtn3.disabled=true;
            document.forms[0].hisBtn3.style.color = '#e0e0e0';
       }

    //モバイル使用 = 許可しない
    } else if (mblUseKbnVal == 1) {

        windowClose();

        //ログイン制御
        document.forms[0].usr031NumCont.disabled=true;

        //自動登録
        document.forms[0].usr031NumAutAdd.disabled=true;

        //固体識別番号1
        document.forms[0].usr031CmuUid1.readOnly=true;
        document.forms[0].usr031CmuUid1.style.backgroundColor = '#e0e0e0';

        //固体識別番号2
        document.forms[0].usr031CmuUid2.readOnly=true;
        document.forms[0].usr031CmuUid2.style.backgroundColor = '#e0e0e0';

        //固体識別番号3
        document.forms[0].usr031CmuUid3.readOnly=true;
        document.forms[0].usr031CmuUid3.style.backgroundColor = '#e0e0e0';

        //履歴ボタン1
        document.forms[0].hisBtn1.disabled=true;
        document.forms[0].hisBtn1.style.color = '#e0e0e0';

        //履歴ボタン2
        document.forms[0].hisBtn2.disabled=true;
        document.forms[0].hisBtn2.style.color = '#e0e0e0';

        //履歴ボタン3
        document.forms[0].hisBtn3.disabled=true;
        document.forms[0].hisBtn3.style.color = '#e0e0e0';
    }
}

function buttonPush(cmd) {
    document.forms[0].CMD.value = cmd;
    document.forms[0].submit();
}

function allPublish() {
	document.forms[0].usr031UsiBdateKf[0].checked = true;
	document.forms[0].usr031UsiMail1Kf[0].checked = true;
	document.forms[0].usr031UsiMail2Kf[0].checked = true;
	document.forms[0].usr031UsiMail3Kf[0].checked = true;
	document.forms[0].usr031UsiZipKf[0].checked = true;
	document.forms[0].usr031UsiTdfKf[0].checked = true;
	document.forms[0].usr031UsiAddr1Kf[0].checked = true;
	document.forms[0].usr031UsiAddr2Kf[0].checked = true;
	document.forms[0].usr031UsiTel1Kf[0].checked = true;
	document.forms[0].usr031UsiTel2Kf[0].checked = true;
	document.forms[0].usr031UsiTel3Kf[0].checked = true;
	document.forms[0].usr031UsiFax1Kf[0].checked = true;
	document.forms[0].usr031UsiFax2Kf[0].checked = true;
	document.forms[0].usr031UsiFax3Kf[0].checked = true;
}
function allDisPublish() {

	document.forms[0].usr031UsiBdateKf[1].checked = true;
	document.forms[0].usr031UsiMail1Kf[1].checked = true;
	document.forms[0].usr031UsiMail2Kf[1].checked = true;
	document.forms[0].usr031UsiMail3Kf[1].checked = true;
	document.forms[0].usr031UsiZipKf[1].checked = true;
	document.forms[0].usr031UsiTdfKf[1].checked = true;
	document.forms[0].usr031UsiAddr1Kf[1].checked = true;
	document.forms[0].usr031UsiAddr2Kf[1].checked = true;
	document.forms[0].usr031UsiTel1Kf[1].checked = true;
	document.forms[0].usr031UsiTel2Kf[1].checked = true;
	document.forms[0].usr031UsiTel3Kf[1].checked = true;
	document.forms[0].usr031UsiFax1Kf[1].checked = true;
	document.forms[0].usr031UsiFax2Kf[1].checked = true;
	document.forms[0].usr031UsiFax3Kf[1].checked = true;
}
