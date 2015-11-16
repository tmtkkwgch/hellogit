function setLabelModal(p_sType, p_aArgs, p_sMode) {
    if(p_sMode == "add"){
        window.setTimeout(function() {
            showAddLabelDialog();
        }, 0);
    } else {
        window.setTimeout(function() {
            showDelLabelDialog();
        }, 0);
    }

}

//ディレクトリ一覧で右クリック時のコンテキストメニューを設定
function setDirContextMenu(dirAreaId) {

    var onContextMenuClick = function(p_sType, p_aArgs, p_dirElement) {
        var task = p_aArgs[1];
        if(task) {
            if(p_dirElement) {
                var dirSid = p_dirElement.id.substring(dirAreaIdStr.length);
                switch(task.index) {
                    case 0:     //全て未読にする
                        changeMailReadedAll(dirSid, 1);
                        break;
                    case 1:     //全て既読にする
                        changeMailReadedAll(dirSid, 0);
                        break;
                }
            }
        }
    };

    var myContextMenu
        = new YAHOO.widget.ContextMenu(dirAreaId + "ContextMenu",
                                         {trigger:document.getElementById(dirAreaId)});
    myContextMenu.addItem(msglist.allUnread);
    myContextMenu.addItem(msglist.allRead);

    myContextMenu.render('treewrapper');
    myContextMenu.clickEvent.subscribe(onContextMenuClick, document.getElementById(dirAreaId));

}

function changeMailReadedAll(dirSid, type) {
    showLoadingDialog();

    var url = '../webmail/wml010.do';
    if (type == 1) {
        url += '?CMD=noReadMailAll';
    } else {
        url += '?CMD=readedMailAll';
    }
    url += '&wmlViewAccount=' + getAccount();
    url += '&wml010viewDirectory=' + dirSid;
    url += '&wml010shainGroup=' + getSelectBoxValue('wml010shainGroup');
    // XMLデータ取得処理
    var request = YAHOO.util.Connect.asyncRequest('GET', url, {
        success: function(o) {
            setDirectoryTreeData(o.responseText);

            var rowList = YAHOO.example.app.dt.getTbodyEl().childNodes;
            if (rowList) {
                for (var rowIdx = 0; rowIdx < rowList.length; rowIdx++) {
                    if (type == 1) {
                        YAHOO.example.app.dt.getRecord(rowList[rowIdx])._oData.Readed = false;
                    } else {
                        YAHOO.example.app.dt.getRecord(rowList[rowIdx])._oData.Readed = true;
                    }

                    var selectRowTd = rowList[rowIdx].childNodes;
                    for (i = 0; i < selectRowTd.length; i++) {
                        if (type == 1) {
                            YAHOO.util.Dom.addClass(selectRowTd[i], "notRead");
                        } else {
                            YAHOO.util.Dom.removeClass(selectRowTd[i], "notRead");
                        }
                    }
                }
            }

            closeLoadingDialog();
        },

        //取得失敗時の処理
        failure: function(o) {
            alert(msglist.failedTree);
            closeLoadingDialog();
        }
    });
}

//メール一覧で右クリック時のコンテキストメニューを設定
function setMailListContextMenu(tableId, datatable) {

    var onContextMenuClick = function(p_sType, p_aArgs, p_dataTable) {
        var task = p_aArgs[1];
        if(task) {
            var elRow = this.contextEventTarget;
            elRow = p_dataTable.getTrEl(elRow);
            if(elRow) {
                var selectData = new Array();
                selectData.push(p_dataTable.getRecord(elRow)._oData);

                switch(task.index) {
                    case 0:     //返信
                        initEditorForRecordData(1, null, selectData);
                        break;
                    case 1:     //全員に返信
                        initEditorForRecordData(2, null, selectData);
                        break;
                    case 2:     //転送
                        initEditorForRecordData(3, null, selectData);
                        break;
                    case 3:     //削除
                        dustMailForRecord(selectData);
                        break;
                    case 4:     //既読
                        changeMailReaded(p_dataTable.getRecord(elRow), elRow, selectData, 0);
                        break;
                    case 5:     //未読
                        changeMailReaded(p_dataTable.getRecord(elRow), elRow, selectData, 1);
                }
                selectData = null;
            }
        }
    };

    var myContextMenu
            = new YAHOO.widget.ContextMenu(tableId + "ContextMenu",
                                        {trigger:datatable.getTbodyEl()}
            );

    myContextMenu.addItem(msglist.reply);
    myContextMenu.addItem(msglist.replyAll);
    myContextMenu.addItem(msglist.trasfer);
    myContextMenu.addItem(msglist.delet);
    myContextMenu.addItem(msglist.asRead);
    myContextMenu.addItem(msglist.asUnRead);

    myContextMenu.render(tableId);
    myContextMenu.clickEvent.subscribe(onContextMenuClick, datatable);
}
