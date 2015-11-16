var inboxDatatableId = 'standard';
var searchDatatableId = 'searchResultList';
var mailOpenData;
var searchMailOpenData;

function initEditor(datatable, type, initAddress) {
    if (type == 0) {
        initEditorForRecordData(type, initAddress, null);
    } else {
        initEditorForRecordData(type, initAddress, getSelectRecordData(datatable));
    }
}

function initEditorForRecordData(type, initAddress, selectData) {
    if(YAHOO.example.app.editor) {
        if (type == 1 || type == 2) {
            document.getElementById('msg_dialog10').innerHTML = msglist.reply;
        } else if (type == 3) {
            document.getElementById('msg_dialog10').innerHTML = msglist.trasfer;
        } else {
            document.getElementById('msg_dialog10').innerHTML = msglist.newMail;
        }
        YAHOO.example.container.dialog10.show();
        return;
    }

    var param = 'CMD=';
    if (type == 0) {
        setParamValue('CMD', 'createSendMail');
        //メッセージ番号を初期化
        setParamValue('wml010sendMessageNum', '');
    } else {
        if (selectData == null || selectData.length != 1) {
            alert(msglist.mailSelect);
            closeLoadingDialog();
        }

        if (type == 1) {
            setParamValue('CMD', 'replyMail');
        } else if (type == 2) {
            setParamValue('CMD', 'replyMailAll');
        } else if (type == 3) {
            setParamValue('CMD', 'forwardMail');
        } else if (type == 4) {
            setParamValue('CMD', 'editMail');
        }

        //選択されているメールのメッセージ番号
        setParamValue('wml010sendMessageNum', selectData[0].XID);
    }

    setParamValue('wml010sendMailType', type);
    var formObject = document.getElementById('webmailForm');
    YAHOO.util.Connect.setForm(formObject);

    var request = YAHOO.util.Connect.asyncRequest('POST', '../webmail/wml010.do', {
        success: function(o) {

            var mailData = o.responseText;
//setDebugMsg("\r\n送信メール初期データ 取得成功\r\n");
//addDebugMsg(mailData);
            if (mailData != null) {
                var data = JSON.parse(mailData);
                var toAddress = data.to;

                if (initAddress) {
                    if (toAddress != null && toAddress.length > 0) {
                        toAddress = toAddress + "," + initAddress;
                    } else {
                        toAddress = initAddress;
                    }
                }

                YAHOO.example.EditorData = {
                    sendFormat : data.sendFormat,
                    sendFormatChange : data.sendFormatChange,
                    sendAccount : data.sendAccount,
                    sendSign : data.sendSign,
                    mailTemplate : data.mailTemplate,
                    to : toAddress,
                    cc : data.cc,
                    bcc : data.bcc,
                    subject : decodeURIComponent(data.subject),
                    content : decodeURIComponent(data.content),
                    fileList : data.fileList,
                    timeSent : data.timeSent,
                    timeSentYear : data.timeSentYear,
                    timeSentMonth : data.timeSentMonth,
                    timeSentDay : data.timeSentDay,
                    timeSentHour : data.timeSentHour,
                    timeSentMinute : data.timeSentMinute,
                    timeSentType : data.timeSentType,
                    timeSentDef : data.timeSentDef,
                    compressFileType : data.compressFileType,
                    compressFileDef : data.compressFileDef,
                    compressFilePlan : data.compressFilePlan
                };
            }

            viewSendMailEditor();

//            closeLoadingDialog();

        },
        //取得失敗時の処理
        failure: function(o) {
//            closeLoadingDialog();
        }
    });
}

function viewSendMailEditor() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;

    if(!YAHOO.example.app.editor) {
        var cTab = new YAHOO.widget.Tab({
            label: '<span class="close"></span><span class="icon"></span>' + msglist.createMail,
            id: 'composeView',
            active: true,
            content: '<div id="newMail" style="background-color: #eeeeee;"></div>'
        });

        //Add the close button to the tab
        Event.on(cTab.get('labelEl').getElementsByTagName('span')[0], 'click', function(ev) {
            Event.stopEvent(ev);
            YAHOO.example.app.tabView.set('activeTab', YAHOO.example.app.tabView.get('tabs')[0]);
            YAHOO.example.app.destroyEditor();
            YAHOO.example.app.tabView.removeTab(cTab);
        });

        YAHOO.example.app.tabView.addTab(cTab);
        window.setTimeout(function() {
            var transactionObj = YAHOO.util.Get.script('../webmail/js/assets/editor.js?reload=' + (+new Date()), { autopurge: false });
        }, 0);
    }

    var t = YAHOO.example.app.tabView.get('tabs');
    for (var i = 0; i < t.length; i++) {
        if (t[i].get('id') == 'composeView') {
            YAHOO.example.app.tabView.set('activeTab', t[i]);
        }
    }
}

function getParamValue(name) {
    return document.getElementsByName(name)[0].value;
}

function setParamValue(name, value) {
    document.getElementsByName(name)[0].value = value;
}

function getRadioValue(name) {
    var value = 0;
    var paramArray = document.getElementsByName(name);
    if (paramArray != null) {
        for (index = 0; index < paramArray.length; index++) {
            if (paramArray[index].checked) {
                value = paramArray[index].value;
            }
        }
    }

    return value;
}

function getSelectBoxValue(name) {
    var value = 0;
    var paramList = document.getElementsByName(name)[0];
    if (paramList != null) {
        for (index = 0; index < paramList.length; index++) {
            if (paramList[index].selected) {
                value = paramList[index].value;
                break;
            }
        }
    }

    return value;
}

function getMailListType() {
    return (getSelectTabId() == 'searchView') ? 1 : 0;
}

function getMailListViewMode() {
    if (getMailListType() == 1) {

        if (document.all[searchDatatableId].style.display == 'none') {
            return 1;
        } else {
               return 0
        }
    }
    if (document.all[inboxDatatableId].style.display == 'none') {
        return 1;
    } else {
        return 0
    }
}

function getAccount() {
    return getSelectBoxValue('wmlViewAccount');
}

function getDirectory() {
    return getParamValue('wml010viewDirectory');
}

function setDirectory(dirId) {
    document.getElementsByName('wml010viewDirectory')[0].value = dirId;
}

function setDirectoryType(dirType) {
    document.getElementsByName('wml010viewDirectoryType')[0].value = dirType;
}

function getLabel() {
    return getParamValue('wml010viewLabel');
}

function setLabel(labelId) {
    document.getElementsByName('wml010viewLabel')[0].value = labelId;
}

function setViewDelMail(delMailFlg) {
    document.getElementsByName('wml010viewDelMail')[0].value = delMailFlg;
}

function getInboxPage() {
    var page = document.getElementsByName('wml010selectPage')[0].value;
    var pageList = document.getElementsByName('wml010pageTop')[0];
    if (pageList != null) {
        for (page = 1; page <= pageList.length; page++) {
            if (pageList[page - 1].selected) {
                break;
            }
        }
    }

    return page;
}

function setInboxSort(sortKey, order) {
    setParamValue('wml010sortKey', sortKey);
    setParamValue('wml010order', order);
}

function setInboxSelectPage(page) {
    setParamValue('wml010selectPage', page);
}

function setErrorMessge(message) {
    if (message == null) {
        document.getElementById('webmailErrMsg').innerHTML = '';
    } else {
        var errMessage = '<div style="width:100%; padding-left: 200px!important;">'
          + '<div style="text-align:left!important">'
          + '<div class="text_error"><b>';

        for (i = 0; i < message.length; i++) {
            errMessage += '<br>' + message[i] ;
        }

        errMessage += '</b></div>'
                   + '</div></div>';
        document.getElementById('webmailErrMsg').innerHTML = errMessage;
        errMessage = null;
   }
}

function dustMail(datatable) {
    showLoadingDialog();
    requestMailList(datatable, 'dustMail', msglist.failedDltmail, true);
}

function dustMailForRecord(recordData) {
    showLoadingDialog();
    requestMailListForRecord('dustMail', msglist.failedDltmail, true, recordData);
}

function emptyTrash(datatable) {
    showLoadingDialog();
    requestMailList(datatable, 'emptyTrash', msglist.failedTrash, false);
}

function keepMail(datatable) {
    requestMailList(datatable, 'keepMail', msglist.faildedSaveMail, true);
}

function emlOutput(datatable) {
    if (getSelectRecordData(datatable) == null || getSelectRecordData(datatable).length == 0) {
        alert(msglist.mailSelect);
    } else {
        location.href = getRequestMailListURL('emlOutput', getSelectRecordData(datatable));
    }
}

function changeReadState(datatable, type) {
    if (type == 1) {
        requestMailList(datatable, 'unreadSelectMail', msglist.faildedChangeMail, true);
    } else {
        requestMailList(datatable, 'readSelectMail', msglist.faildedChangeMail, true);
    }
}

function requestMailList(datatable, cmd, errMessage, checkRecord) {
    requestMailListForRecord(cmd, errMessage, checkRecord, getSelectRecordData(datatable));
}

function requestMailListForRecord(cmd, errMessage, checkRecord, recordData) {
    if (checkRecord && (recordData == null || recordData.length == 0)) {
        closeLoadingDialog();
        alert(msglist.mailSelect);
    } else {
        var url = getRequestMailListURL(cmd, recordData);
        recordData = null;
        YAHOO.util.Connect.asyncRequest('GET', url, {

            success: function(o) {
                var message = o.responseText;
                if (message != null) {
                    var data = JSON.parse(message);

                    if (data.message != null) {
                        if (data.message == 'success') {
                            reloadMailList();
                        } else {
                            alert(data.message);
                        }
                    }
                } else {
                    alert(errMessage);
                }
            },
            failure: function(o) {
                alert(errMessage);
            }
      });
    }
}

function getRequestMailListURL(cmd, recordData) {
    var url = '../webmail/wml010.do';
    url += '?CMD=' + cmd;
    url += '&wmlViewAccount=' + getAccount();
    if (recordData) {
        for (i = 0; i < recordData.length; i++) {
            url += '&wml010selectMessageNum=' + recordData[i].XID;
            url += '&wml010selectMessageDirId=' + recordData[i].dirId;
        }
    }
    return url;
}

//メール一覧(検索結果を含む)の再表示を行う
function reloadMailList() {
    reloadMailListWithTree(0);
}

function reloadMailListWithTree(reloadTreeType) {

    readMailList(1);

    //ヘッダーのチェックボックスをoffにする
    if (YAHOO.example.app.search != null) {
        searchResultLoad(getParamValue('wml010searchType'));
        YAHOO.example.app.search.dt.getColumnSet().flat[0].getThEl().children[0].children[0].children[0].checked = false;
    } else {
        YAHOO.example.app.dt.getColumnSet().flat[0].getThEl().children[0].children[0].children[0].checked = false;
    }

    //ディレクトリ、ラベルツリーを再表示する
    redrawDirectoryTree(reloadTreeType);
}

//エラーメッセージをクリアする
function clearErrorMessge(message) {
    setErrorMessge(null);
}

//「読み込み中」ダイアログの初期設定
function initLoadingDialog() {
  YAHOO.example.container.dialog3 = new YAHOO.widget.Dialog("dialog3",
              { width : "30em",
                fixedcenter : true,
                visible : false,
                constraintoviewport : false,
                modal: true,
                close: false
              });
  YAHOO.example.container.dialog3.render();
}

//「読み込み中」ダイアログを表示する
function showLoadingDialog() {
    if (YAHOO.example.container == null) {
        YAHOO.namespace("example.container");
    }

    if (YAHOO.example.container.dialog3 == null) {
        initLoadingDialog();
    }
    YAHOO.example.container.dialog3.show();
}

//「読み込み中」ダイアログを閉じる
function closeLoadingDialog() {
  try {
    YAHOO.example.container.dialog3.cancel();
  } catch (e) {
    setInterval('closeLoadingDialogRetry()', 1000);
  }
}

//「読み込み中」ダイアログを閉じる(リトライ)
function closeLoadingDialogRetry() {
  if (YAHOO.example.container && YAHOO.example.container.dialog3) {
    YAHOO.example.container.dialog3.cancel();
  }
}

//「ラベルを追加」ダイアログを表示する
function showAddLabelDialog() {
    setLabelDialogWidth(YAHOO.example.container.dialog1, 'dialog_sel');
    YAHOO.example.container.dialog1.show();
}

//「ラベルを追加」ダイアログを閉じる
function closeAddLabelDialog() {
    YAHOO.example.container.dialog1.cancel();
}

//「ラベルを削除」ダイアログを表示する
function showDelLabelDialog() {
    setLabelDialogWidth(YAHOO.example.container.dialog2, 'dialog_del');
    YAHOO.example.container.dialog2.show();
}

//「ラベルを追加」ダイアログを閉じる
function closeDelLabelDialog() {
    YAHOO.example.container.dialog2.cancel();
}

//ラベルコンボに応じてラベルダイアログの横幅を調整する
function setLabelDialogWidth(dialog, comboId) {
    var dialogWidth = 0;

    var options = document.getElementById(comboId).options;
    for (optionLen = 0; optionLen < options.length; optionLen++) {
        if (dialogWidth < options[optionLen].text.length) {
            dialogWidth = options[optionLen].text.length;
        }
    }
    if (dialogWidth < 30) {
        dialogWidth = 30;
    }

    var childNode = dialog.element.childNodes;
    for (i = 0; i < childNode.length; i++) {
        YAHOO.util.Dom.setStyle(childNode[i], "width", dialogWidth + "em");
    }
    YAHOO.util.Dom.setStyle(dialog.element, "width", dialogWidth + "em");

}

//「メールの移動」ダイアログを表示する
function showMoveMailDialog() {
    setLabelDialogWidth(YAHOO.example.container.dialog5, 'dialog_del');
    //「受信」フォルダをデフォルトで選択
    try {
        var paramArray = document.getElementsByName('wml010moveFolder');
        paramArray[0].checked = true;
        for (index = 1; index < paramArray.length; index++) {
            paramArray[index].checked = false;
        }
    } catch (e) {
    }
    YAHOO.example.container.dialog5.show();
}

//「メールの移動」ダイアログを閉じる
function closeMoveMailDialog() {
    YAHOO.example.container.dialog5.cancel();
}

//「宛先の確認」ダイアログを表示する
function showCheckAddressDialog() {
    YAHOO.example.container.dialog6.show();
}
//「宛先の確認」ダイアログを閉じる
function closeCheckAddressDialog() {
  YAHOO.example.container.dialog6.cancel();
  document.getElementById("wml010SendConfirmArea").src = '';
}

//指定したDatatableの選択されたレコードの行データを取得する
function getSelectRecordData(datatable) {
    if (datatable == null || datatable.getRecordSet() == null) {
        return null;
    }

    var selectData = new Array();

    if (getMailListViewMode() == 1) {
//        var selectRecord = datatable.getRecordSet().getRecord(datatable.getSelectedRows()[0]);
//        if (selectRecord != null) {
//            selectData.push(selectRecord._oData);
//        }
        if (getMailListType() == 1) {
            selectData.push(searchMailOpenData);
        } else {
            selectData.push(mailOpenData);
        }

    } else {
        var dtLength = datatable.getRecordSet().getLength();
        var index;
        for (index = 0; index < dtLength; index++) {
            if (datatable.getRecordSet().getRecords(index, 1)[0].getData('selectedMail')) {
                selectData.push(datatable.getRecordSet().getRecords(index, 1)[0]._oData);
            }
        }
    }

    return selectData;
}

function getSelectedMailNum(datatable) {
    return getSelectRecordData(datatable)[0].XID;
}

function getSelectedMailAttach(datatable) {
    return getSelectRecordData(datatable)[0].Attach;
}

//指定したDatatableの選択されたレコードの行データを取得する
//function getSelectRecordData(datatable) {
//    if (datatable == null || datatable.getSelectedRows() == null || datatable.getSelectedRows().length == 0) {
//        return null;
//    }
//    var selectData = new Array();
//    for (i = 0; i < datatable.getSelectedRows().length; i++) {
//        var selectRecord = datatable.getRecordSet().getRecord(datatable.getSelectedRows()[i]);
//        if (selectRecord != null) {
//            selectData.push(selectRecord._oData);
//        }
//    }
//    return selectData;
//}


//指定したタブを取得する
function getTab(id) {
    var t = YAHOO.example.app.tabView.get('tabs');
    for (var i = 0; i < t.length; i++) {
        if (t[i].get('id') == id) {
            return t[i];
        }
    }

    return null;
}

//選択されているタブのidを取得する {
function getSelectTabId() {
    return YAHOO.example.app.tabView.get('activeTab').get('id');
}

//メールを既読に変更する
function readedMail(type) {
    var datatable;
    if (type == 1) {
        datatable = YAHOO.example.app.search.dt;
    } else {
        datatable = YAHOO.example.app.dt;
    }

    changeMailReaded(datatable.getRecordSet().getRecord(datatable.getSelectedRows()[0]),
                    datatable.getSelectedTrEls()[0],
                    getSelectRecordData(datatable), 0);
    datatable = null;
}

//メールの未読/既読を変更する
function changeMailReaded(record, targetRow, selectData, readType) {
    if (selectData.length == 1
    && ((readType == 0 && selectData[0].Readed == false)
        || (readType == 1 && selectData[0].Readed == true))) {

        var url = '../webmail/wml010.do';
        if (readType == 1) {
            url += '?CMD=noReadMail';
        } else {
            url += '?CMD=readedMail';
        }
        url += '&wmlViewAccount=' + getAccount();
        url += '&wml010selectMessageNum=' + selectData[0].XID;
        selectData = null;

        YAHOO.util.Connect.asyncRequest('GET', url, {
            failure: function(o) {
                alert(msglist.mailError);
            }
        });

        if (readType == 1) {
            record._oData.Readed = false;
        } else {
            record._oData.Readed = true;
        }

        var selectRowTd = targetRow.childNodes;
        for (i = 0; i < selectRowTd.length; i++) {
            if (readType == 1) {
                YAHOO.util.Dom.addClass(selectRowTd[i], "notRead");
                if (i == 2) {
                    selectRowTd[i].childNodes[0].innerHTML = "<img src='../webmail/images/assets/readed.gif'>";
                }
            } else {
                YAHOO.util.Dom.removeClass(selectRowTd[i], "notRead");
                if (i == 2) {
                    selectRowTd[i].childNodes[0].innerHTML = "";
                }
            }
        }

        //未読件数を設定する
        var dirId = record._oData.dirId;
        changeDirNoRead(dirId, readType);
        changeLabelNoRead(record._oData.LabelId, readType);
    }
    selectData = null;
}

//Create the Column Definitions
function getColumnDefs(w, magicNum) {
  var titleWidth = w - magicNum;

  return [
//    {key:"XID", label:"　", width: 15, sortable:false, formatter: formatMailCheckBox },
    {key:"XID", label:"<input type=\"checkbox\" id=\"wml010AllSelect\" name=\"wml010AllSelect\" onClick=\"mailListAllSelect(0)\" />", width: '15px', sortable:false, formatter: formatMailCheckBox },
//    {key:"Attach", label:"&nbsp;", sortable:true, formatter: formatMailListAttach },
//    {key:"Readed", label:"&nbsp;", sortable:true, formatter: formatMailListReaded },
    {key:"Attach", label:"<img src=\"../webmail/images/assets/attach2.gif\">", width: "20px", sortable:true, formatter: formatMailListAttach },
    {key:"Readed", label:"<img src=\"../webmail/images/assets/readed.gif\">", width: "20px", sortable:true, formatter: formatMailListReaded },
    {key:"From", label:msglist.sender, width:146, sortable:true, resizeable:false, formatter: formatMailListText},
    {key:"Subject", label:msglist.subject, width: titleWidth, sortable:true, resizeable:false, formatter: formatMailListSubject},
    {key:"Date", label:msglist.dateTime, width:130, sortable:true, resizeable:false, formatter: formatMailListText},
    {key:"MailSize", label:msglist.txtSize, width:60, sortable:true, resizeable:false, formatter: formatMailListMailSize}
  ];
}

function getReceiveMailFields() {
  return ["selectedMail", "XID", "dirId", "Date","To","Cc","Bcc","From","ListFrom","Subject","Attach","Readed","Reply","Forward","EditMail","Label", "Body", "MailSize", "viewMailSize", "LabelId", "LabelName", "fileId", "fileName", "fileSize"];
}

//メール一覧(検索結果含む)のformatter
var formatMailCheckBox = function(el, oRecord, oColumn, sData) {
    el.innerHTML = '<input type="checkbox" name="wml010selectMailListData" value="'
                + oRecord.getData("XID") + '">';
    el.parentNode.parentNode.align = 'center';
};

var formatMailListAttach = function(el, oRecord, oColumn, sData) {
    if (oRecord.getData("Attach")) {
        el.innerHTML = "<img src='../webmail/images/assets/attach2.gif'>";
        el.parentNode.parentNode.align = 'center';
    } else {
        el.innerHTML = "";
    }

};

var formatMailListReaded = function(el, oRecord, oColumn, sData) {
    if (oRecord.getData("Readed") == false) {
        el.innerHTML = "<img src='../webmail/images/assets/readed.gif'>";
        el.parentNode.parentNode.align = 'center';
    } else {
        el.innerHTML = "";
    }

};

var formatMailListSubject = function(el, oRecord, oColumn, oData) {
    el.innerHTML = oRecord.getData("Subject");

    if (oRecord.getData("Label")) {
        el.innerHTML = "<span class='mailLabel'>" + oRecord.getData("Label")
                        + "</span>&nbsp;" + el.innerHTML;
    }

    if (oRecord.getData("Reply")) {
        el.innerHTML = "<span class='reply'> </span>" + el.innerHTML;
    }
    if (oRecord.getData("Forward")) {
        el.innerHTML = "<span class='forward'> </span>" + el.innerHTML;
    }

    changeFormatMailList(el, oRecord);
};

var formatMailListText = function(el, oRecord, oColumn, oData) {
    if (oColumn.key == 'From') {
        if (isSendDir()) {
            el.innerHTML = oRecord.getData("ListTo");
        } else {
            el.innerHTML = oRecord.getData("ListFrom");
        }
    } else if (oColumn.key == 'Date') {
        el.innerHTML = oRecord.getData("Date");
    }
    changeFormatMailList(el, oRecord);
}

var formatMailListMailSize = function(el, oRecord, oColumn, oData) {
    el.innerHTML = "<div style='text-align: right;'>" + oRecord.getData("viewMailSize") + "</div>";
    changeFormatMailList(el, oRecord);
}

function changeFormatMailList(el, oRecord) {
    if (oRecord.getData("Readed") == false) {
        YAHOO.util.Dom.addClass(el.parentNode, "notRead");

    } else if (YAHOO.util.Dom.hasClass(el.parentNode, "notRead")) {
        YAHOO.util.Dom.removeClass(el.parentNode, "notRead");
    }
}

function mailDetailShow(dataTableId, mailDetailId, toolbar, toolbarBottom, data) {
    document.getElementById(dataTableId).style.display='none';
    document.getElementById(mailDetailId).style.display='block';

    mailDetailShowToolbar(toolbar, data);
    mailDetailShowToolbar(toolbarBottom, data);
}

function mailDetailClose(dataTableId, mailDetailId, toolbar, toolbarBottom) {
    document.getElementById(dataTableId).style.display='block';
    document.getElementById(mailDetailId).style.display='none';

    mailDetailCloseToolbar(toolbar);
    mailDetailCloseToolbar(toolbarBottom);
}

function mailDetailShowToolbar(toolbar, data) {
    var toolbarButtonArray = toolbar.getButtons();
    var toolbarButtonValue;
    for (idx = 0; idx < toolbarButtonArray.length; idx++) {
        toolbarButtonValue = toolbarButtonArray[idx].get('value');

        if (toolbarButtonValue == 'new'
        || toolbarButtonValue == 'read'
        || toolbarButtonValue == 'unread'
        || toolbarButtonValue == 'emlOutput') {
            document.getElementById(toolbarButtonArray[idx].get('id')).style.display='none';
        }

        if (toolbarButtonValue == 'mailClose'
        || toolbarButtonValue == 'reply'
        || toolbarButtonValue == 'allReply'
        || toolbarButtonValue == 'forward'
        || toolbarButtonValue == 'export'
        || toolbarButtonValue == 'share'
        || toolbarButtonValue == 'movePrevMail'
        || toolbarButtonValue == 'moveNextMail'
        || (toolbarButtonValue == 'mailEdit' && data.EditMail)) {
            document.getElementById(toolbarButtonArray[idx].get('id')).style.display='block';
        }
    }

    toolvarButtonValue = null;
    toolvarButtonArray = null;
}

function mailDetailCloseToolbar(toolbar) {
    var toolbarButtonArray = toolbar.getButtons();
    var toolbarButtonValue;
    for (idx = 0; idx < toolbarButtonArray.length; idx++) {
        toolbarButtonValue = toolbarButtonArray[idx].get('value');

        if (toolbarButtonValue == 'new'
        || toolbarButtonValue == 'read'
        || toolbarButtonValue == 'unread'
        || toolbarButtonValue == 'emlOutput') {
            document.getElementById(toolbarButtonArray[idx].get('id')).style.display='block';
        }

        if (toolbarButtonValue == 'mailClose'
        || toolbarButtonValue == 'mailEdit'
        || toolbarButtonValue == 'reply'
        || toolbarButtonValue == 'allReply'
        || toolbarButtonValue == 'export'
        || toolbarButtonValue == 'forward'
        || toolbarButtonValue == 'share'
        || toolbarButtonValue == 'movePrevMail'
        || toolbarButtonValue == 'moveNextMail') {
            document.getElementById(toolbarButtonArray[idx].get('id')).style.display='none';
        }
    }

    toolvarButtonValue = null;
    toolvarButtonArray = null;
}

function isOpenEditor() {
    return YAHOO.example.app.editor != null;
}

function trim(str) {
   return str.replace(/^[ ]+|[ ]+$/g, '');
}

function isNullZeroString(str) {
   if (str == null) {
       return true;
   }

   return trim(str).length == 0;
}

function isSpaceStart(str) {
    var target = str.substring(0, 1);
    if ('　' == target || ' ' == target) {
        return true;
    }
   return false;
}

function isSpaceOnly(str) {
   if (str == ' ' || str == '　') {
       return true;
   }
   return false;
}

function openDeleteLabel() {

    var selectData;

    if(! Array.indexOf) {
    Array.prototype.indexOf = function(o)
      {
        for(var i in this) {
          if(this[i] == o) {
          return i;
        }
      }
      return -1;
      }
    }

    if (getMailListType() == 1) {
       selectData = getSelectRecordData(YAHOO.example.app.search.dt);
    } else {
       selectData = getSelectRecordData(YAHOO.example.app.dt);
    }

    if (selectData) {
        var labelId = new Array();
        var labelName = new Array();

        for (i = 0; i < selectData.length; i++) {
            if (selectData[i].LabelId) {
                for (lbIdx = 0; lbIdx < selectData[i].LabelId.length; lbIdx++) {
                    if (labelId.indexOf(selectData[i].LabelId[lbIdx]) < 0) {
                        labelId.push(selectData[i].LabelId[lbIdx]);
                        labelName.push(selectData[i].LabelName[lbIdx]);
                    }
                }
            }
        }

        if (labelId.length > 0) {
            document.getElementById('dialog_del').innerHTML = '';
            for (i = 0; i < labelId.length; i++) {
                document.getElementById('dialog_del').options[i] = new Option(labelName[i], labelId[i]);
            }

            showDelLabelDialog();
        } else {
            alert(msglist.labelSelectMail);
        }

        labelId = null;
        labelName = null;
    } else {
        alert(msglist.mailSelect);
    }
}

function mailListAllSelect(type) {
    if (type == 1) {
       setMailListSelect(YAHOO.example.app.search.dt,
                         YAHOO.example.app.search.dt.getColumnSet().flat[0].getThEl().children[0].children[0].children[0].checked);
    } else {
       setMailListSelect(YAHOO.example.app.dt,
                         YAHOO.example.app.dt.getColumnSet().flat[0].getThEl().children[0].children[0].children[0].checked);
    }
}

function setMailListSelect(datatable, select) {
    var trEl = datatable.getFirstTrEl();
    var dtLength = datatable.getRecordSet().getLength();
    var index;

    for (index = 0; index < dtLength; index++) {
        datatable.getRecordSet().getRecords(index, 1)[0].setData('selectedMail', select);
        trEl.children[0].children[0].children[0].checked = select;
        trEl = datatable.getNextTrEl(trEl);
    }
}

function setOpenMailData(type, data) {
    if (type == 1) {
        searchMailOpenData = data;
    } else {
        mailOpenData = data;
    }
}

function writeAccountDiskData() {
    document.getElementById('limitDiskArea').style.display='none';
    document.getElementById('useDiskSize').innerHTML = YAHOO.example.accountDisk.useDiskSize;

    if (YAHOO.example.accountDisk.limitDiskSize > 0) {
        document.getElementById('limitDiskArea').style.display='block';
        document.getElementById('limitDiskSize').innerHTML = YAHOO.example.accountDisk.limitDiskSize;
        document.getElementById('useDiskRatio').innerHTML = YAHOO.example.accountDisk.useDiskRatio;
    }

    document.getElementById('warnDiskArea').style.display='none';
    if (YAHOO.example.accountDisk.warnDiskRatio > 0) {
        document.getElementById('warnDiskArea').style.display='block';
        document.getElementById('warnDiskRatio').innerHTML = YAHOO.example.accountDisk.warnDiskRatio;
    }
}

function setDirectoryTreeData(treeResponseText) {
    if (treeResponseText != null && treeResponseText.length > 0) {
        var treeData = JSON.parse(treeResponseText);
        var treeMap = new Object();
        var dirId = null;
        for (i = 0; i < treeData.directory.length; i++) {
            dirId = treeData.directory[i].ID;
            treeMap['dir' + dirId] = treeData.directory[i];

            if (treeData.directory[i].NRCNT == 0) {
                document.getElementById('dirNrCnt' + dirId).innerHTML = '';
            } else {
                document.getElementById('dirNrCnt' + dirId).innerHTML = '&nbsp;('
                       + treeData.directory[i].NRCNT + ')';
            }
        }
        dirId = null;

        if (treeData.label) {
            var labelId;

            for (i = 0; i < treeData.label.length; i++) {
                labelId = treeData.label[i].ID;
                treeMap['label' + labelId] = treeData.label[i];
                if (treeData.label[i].NRCNT == 0) {
                    document.getElementById('labelNrCnt' + labelId).innerHTML = '';
                } else {
                    document.getElementById('labelNrCnt' + labelId).innerHTML = '&nbsp;('
                     + treeData.label[i].NRCNT + ')';
                }
            }
            labelId = null;
        }

        YAHOO.example.app.treeMap = treeMap;
    }
}

function getSortColumnName(sortKey) {
    if (sortKey == 1) {
        return "Attach";
    } else if (sortKey == 2) {
        return "Subject";
    } else if (sortKey == 3) {
        return "From";
    } else if (sortKey == 4) {
        return "Date";
    } else if (sortKey == 5) {
        return "Readed";
    } else if (sortKey == 6) {
        return "MailSize";
    }

    return "Date";
}

function exportPdf(datatable) {
    exportWebmail(datatable, "exportPdf");
}

function exportMail(datatable) {
    exportWebmail(datatable, "exportMail");
}

function exportWebmail(datatable, cmd) {
    var selectData = getSelectRecordData(datatable);
    if (selectData == null || selectData.length == 0) {
        alert(msglist.mailSelect);
    }

    var url = "../webmail/wml010.do";
    var exportParam = "&wmlViewAccount=" + getAccount()
        + "&wml010selectMessageNum=" + selectData[0].XID;
    selectData = null;

    clearErrorMessge();
    var request = YAHOO.util.Connect.asyncRequest('GET', url + "?CMD=exportMailCheck" + exportParam, {
        success: function(o) {
            try {
              var mailData = o.responseText;
              if (mailData != null) {
                  var data = JSON.parse(mailData);
                  if (data.error == 1) {
                      setErrorMessge([data.errorMessage]);
                      closeLoadingDialog();
                      return;
                  }

                  window.location.href = url + "?CMD=" + cmd + exportParam;
              }
            } catch (e) {
                clearErrorMessge();
                closeLoadingDialog();
                setErrorMessge([msglist.failedMailExport]);
            }

            closeLoadingDialog();
        },

        //取得失敗時の処理
        failure: function(o) {
            closeLoadingDialog();
        }
    });
}

function isSendDir(dirType) {
    var dirType = getParamValue('wml010viewDirectoryType');
    return (dirType == DIRTYPE_SENT || dirType == DIRTYPE_UNSENT || dirType == DIRTYPE_DRAFT);
}

var mvRecordInterval;
var mvRecordIntervalTopId;
function moveSelectRecord(tableType, moveCnt) {
    var datatable = getMailDataTable(tableType);

    if (datatable == null || datatable.getRecordSet() == null) {
        return null;
    }

    var selectRowId = datatable.getSelectedRows()[0];
    var selectRecord = datatable.getRecordSet().getRecord(selectRowId);
    var selectIdx = datatable.getRecordSet().getRecordIndex(selectRecord);
    var nextIdx = selectIdx + moveCnt;
    datatable.unselectRow(selectRecord);

    var recordCnt = datatable.getRecordSet().getLength();
    var nextPage = 0;
    if (nextIdx < 0) {
      nextIdx = recordCnt - 1;
      nextPage = -1;
    } else if (nextIdx >= recordCnt) {
      nextIdx = 0;
      nextPage = 1;
    }

    if (nextPage != 0) {
      var mvTopId = datatable.getRecordSet().getRecords(0, 1)[0];
      if (tableType == 1) {
        searchMailClose();
        var selectPage = getSearchResultSelectPage(0);
        selectPage += nextPage;
        if (selectPage > 0 && selectPage <= YAHOO.example.SearchData.emails.maxPage) {
          changeSearchResultPage(nextPage, 0);
        } else {
          readNextRecord(tableType, selectIdx);
          return;
        }
      } else {
        inboxMailClose();
        var selectPage = getInboxSelectPage(0);
        selectPage += nextPage;
        if (selectPage > 0 && selectPage <= YAHOO.example.Data.emails.maxPage) {
          changePage(nextPage, 0)
        } else {
          readNextRecord(tableType, selectIdx);
          return;
        }
      }
      mvRecordIntervalTopId = mvTopId;
      mvRecordInterval = setInterval('checkDataTablePage(' + tableType + ',' + moveCnt + ')', 200);
    } else {
      readNextRecord(tableType, nextIdx);
    }
}
function getMailDataTable(tableType) {
    if (tableType == 1) {
        return YAHOO.example.app.search.dt;
    }
    return YAHOO.example.app.dt;
}
function readNextRecord(tableType, nextIdx) {
    var nextRecord = getMailDataTable(tableType).getRecordSet().getRecords(nextIdx, 1)[0];
    setTimeout(function(){ getMailDataTable(tableType).selectRow(nextRecord) }, 100);

}

function checkDataTablePage(tableType, moveCnt) {
  try {
    var nextTopId = getMailDataTable(tableType).getRecordSet().getRecords(0, 1)[0];
    if (mvRecordIntervalTopId != nextTopId) {
      clearInterval(mvRecordInterval);
      mvRecordInterval = null;

      var nextPageIdx = 0;
      if (moveCnt < 0) {
        nextPageIdx = getMailDataTable(tableType).getRecordSet().getLength() - 1;
      }
      readNextRecord(tableType, nextPageIdx);
    }
  } catch (e) {
      clearInterval(mvRecordInterval);
      mvRecordInterval = null;
      throw e;
  }
}

function openShareDialog(datatable) {
    setParamValue('wml010shareMailNum', getSelectedMailNum(datatable));

    if (getParamValue('wml010pluginCircularUse') == 0) {
        $('#entryCircularArea').show();
    } else {
        $('#entryCircularArea').hide();
    }

    if (getParamValue('wml010pluginSmailUse') == 0) {
        $('#entrySmailArea').show();
    } else {
        $('#entrySmailArea').hide();
    }

    if (getParamValue('wml010pluginFilekanriUse') && getSelectedMailAttach(datatable)) {
        $('#entryFilekanriArea').show();
    } else {
        $('#entryFilekanriArea').hide();
    }
    YAHOO.example.container.dialog8.show();
}

function openDestlistDialog(txtName) {
    setEditorFocus(txtName);
    openDestlist(0);
}

function createTooltipAddress(data) {
    var addressData = '';
    if (data.From) {
        addressData += '<tr><td>' + msglist.mailSender2 + ": </td><td>" + data.From + '</td></tr>';
    }
    if (data.To) {
        addressData += createTooltipAddressList(data.To, msglist.address);
    }
    if (data.Cc) {
        addressData +=  createTooltipAddressList(data.Cc, 'CC');
    }
    if (data.Bcc) {
        addressData += createTooltipAddressList(data.Bcc, 'BCC');
    }

    if (addressData) {
        addressData = '<table border=\"0\">' + addressData + '</table>';
    }
    return addressData;
}

function createTooltipAddressList(address, title) {
    if (!address) {
        return '';
    }
    var addressArray = address.split(',');
    address = checkMineAddress(addressArray[0], title + ": ");
    for (var idx = 1; idx < addressArray.length && idx < 5; idx++) {
        address += checkMineAddress(addressArray[idx], '');
    }

    return address;
}

function checkMineAddress(address, title) {
    if (address.indexOf(getParamValue('wml010viewAccountAddress')) >= 0) {
        address = '<tr><td>' + title + '</td><td style=\"font-weight: bold!important;\">' + address + '</td></tr>';
    } else {
        address = '<tr><td>' + title + '</td><td>' + address + '</td></tr>';
    }
    return address;
}