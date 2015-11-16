//var magicNum = 423,
var magicNum = 510,
    mailListHeight = 600;

function getBasicWidth() {
//    return screen.width;
   return document.body.clientWidth;
}

var inboxMailShow = function(data) {
    mailDetailShow('standard', 'center2', YAHOO.example.app.inboxToolbar, YAHOO.example.app.inboxToolbarBottom, data);
    document.getElementById('mailControl').innerHTML = '';
    document.getElementById('mailControlBottom').innerHTML = '';
    setOpenMailData(0, data);
}

var inboxMailClose = function() {
    mailDetailClose('standard', 'center2', YAHOO.example.app.inboxToolbar, YAHOO.example.app.inboxToolbarBottom);
    setOpenMailData(0, null);
    setInboxPageCombo();
}

function tableWidth() {
    var width = getBasicWidth() - 240;
    if (width < 780) {
        width = 780;
    }

    return width;
}

function getInboxSortKey() {
    return getSortColumnName(getParamValue('wml010sortKey'));
}

function getInboxOrder() {
    if (getParamValue('wml010order') == 1) {
        return YAHOO.widget.DataTable.CLASS_DESC;
    }
    return YAHOO.widget.DataTable.CLASS_ASC;
}

function doInboxSort(sortKey, order) {
    if (sortKey == 'Attach') {
        setParamValue('wml010sortKey', 1);
    } else if (sortKey == 'Subject') {
        setParamValue('wml010sortKey', 2);
    } else if (sortKey == 'From') {
        setParamValue('wml010sortKey', 3);
    } else if (sortKey == 'Date') {
        setParamValue('wml010sortKey', 4);
    } else if (sortKey == 'Readed') {
        setParamValue('wml010sortKey', 5);
    } else if (sortKey == 'MailSize') {
        setParamValue('wml010sortKey', 6);
    }
    setParamValue('wml010order', order);

    reviewInboxTable();
}

(function() {
    document.getElementById('mailBox').style.width = tableWidth() + "px";

    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event,
        dataTable = null,
        emails = {
            currStorage: 20,
            maxStorage: 200,
            messages: []
        };

    var prettySize = function(size) {
        var gb = 1024 * 1024 * 1024, mb = 1024 * 1024, mysize;
        if (size > gb) {
            mysize = Math.round(size / gb) + " GB";
        } else if (size > mb) {
            mysize = Math.round(size / mb) + " MB";
        } else if ( size >= 1024 ) {
            mysize = Math.round(size / 1024) + " Kb";
        } else {
            mysize = size + " b";
        }
        return mysize;
    };

    function initDataTable(h, w, emailData) {

        //Create the datasource
        var myDataSource = new YAHOO.util.DataSource(emailData);
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
        myDataSource.responseSchema = {
            resultsList: 'messages',
            fields: getReceiveMailFields()
        };
        //Give the DT a custom Height
        var dtH = (h - 27 - YAHOO.example.app.inboxToolbarHeight);

        //Create the DT, setting scrollable to true and setting the height
        YAHOO.widget.DataTable.MSG_EMPTY = msglist.thereMsg;

        dataTable = new YAHOO.widget.DataTable("standard",
                getColumnDefs(w, magicNum), myDataSource,
//                { scrollable: true, height: dtH + 'px', width: w + 'px',
                { scrollable: false, height: '100%', width: w + 'px',
                   selectionMode:'single',
                   sortedBy:{key:getInboxSortKey(), dir:getInboxOrder()}
                });

        //ソート
        dataTable.sortColumn = function(oColumn) {
            var sDir = 0
            var sortDir = YAHOO.widget.DataTable.CLASS_ASC;

            if(oColumn.key == this.get("sortedBy").key) {
                if (this.get("sortedBy").dir === YAHOO.widget.DataTable.CLASS_ASC) {
                    sDir = 1;
                    sortDir = YAHOO.widget.DataTable.CLASS_DESC;
                }
            }

            this.set("sortedBy", {key:oColumn.key, dir:sortDir, column:oColumn});
            doInboxSort(oColumn.key, sDir);
        };

        // Subscribe to events for row selection
        dataTable.subscribe("rowMouseoverEvent", dataTable.onEventHighlightRow);
        dataTable.subscribe("rowMouseoutEvent", dataTable.onEventUnhighlightRow);
//        dataTable.subscribe("rowClickEvent", dataTable.onEventSelectRow);

//TODO
        var inboxTooltip = new YAHOO.widget.Tooltip("inboxMailTooltip", {
            styles: {
//                backgroundColor: "#333",
//                color: "#eee",
                borderColor: "#fff",
                textAlign: "left!important"
            },

            context:"myContextEl_inboxTooltip",
            text:"",
            showDelay: 500
        });

        dataTable.subscribe("cellMouseoverEvent", function(oArgs) {
          try {
            var column = this.getColumn(oArgs.target);
            var columnKey = column.key;
            if (columnKey == 'From') {
              var cellRecord = this.getRecordSet().getRecord(oArgs);
              var data = this.getRecord(oArgs.target)._oData;
              var viewAddress = createTooltipAddress(data);
              if (viewAddress) {

                  inboxTooltip.setBody(viewAddress);
                  var xy = [parseInt(oArgs.event.clientX,10) + 10 + document.body.scrollLeft ,parseInt(oArgs.event.clientY,10) + 10 + document.body.scrollTop];
                  inboxTooltip.cfg.setProperty('xy',xy);
                  inboxTooltip.show();
              } else {
                  inboxTooltip.hide();
              }
            } else {
                inboxTooltip.hide();
            }
          } catch (e) {}
        }, dataTable, true);

        dataTable.subscribe("cellMouseoutEvent", function(oArgs) {
            inboxTooltip.hide();
        }, dataTable, true);

        dataTable.subscribe("cellClickEvent", function(oArgs) {
            var target = oArgs.target;
            var columnKey = dataTable.getColumn(target).key;
            if (columnKey == 'XID') {
                document.getElementById(oArgs.target.id).children[0].children[0].checked =
                    (document.getElementById(oArgs.target.id).children[0].children[0].checked == false);
                this.getRecord(oArgs.target).setData('selectedMail',
                                                     document.getElementById(oArgs.target.id).children[0].children[0].checked);
            } else {
                dataTable.onEventSelectRow(oArgs);
            }
        }, dataTable, true);

        //checkboxクリック時のイベントハンドラ
        dataTable.subscribe("checkboxClickEvent", function(oArgs) {
//            this.getRecord(oArgs.target).setData('selectedMail', oArgs.target.checked);
            oArgs.target.checked = (oArgs.target.checked == false);
        }, dataTable, true);

        dataTable.subscribe("rowSelectEvent", function(oArgs) {
            var data = this.getRecordSet().getRecord(this.getSelectedRows()[0])._oData;
            var preview = Dom.get("center2");

            var array = data.Subject.split(" </span>");
            var subject;
            if (array.length > 1) {
                subject = array[1]
            } else {
                subject = array[0]
            }

            if (data.Reply) {
                subject = "<span class='reply'> </span>" + subject;
            }
            if (data.Forward) {
                subject = "<span class='forward'> </span>" + subject;
            }

            var headerHtml = '<table width=\"100%\">'
                headerHtml+= '<tr>'
                          + '<td width=\"80%\" align=\"left\">'
                          + '<span style=\"font-weight: bold\">' + msglist.mailSubject + ' </span>' + '<span style=\"font-weight: bold\">' + subject + '</span>'
                          + '<br><span style=\"font-weight: bold\">' + msglist.mailSender2 + ': </span>' + '<span style=\"font-weight: bold\">' + data.From;
            if (data.From.length > 0 && getParamValue('wml010pluginAddressUse') == 0) {
                headerHtml += '&nbsp;&nbsp;<a href=\"#\" onClick=\"openEntryAddress(' + data.XID + ');\">'
                  + '<img src=\"../webmail/images/add_address_icon.gif\" valign=\"bottom\" alt="\"registerAddress\""></a>';
            }
            headerHtml +=  '</span>';
            headerHtml+= '<br><span style=\"font-weight: bold\">' + msglist.mailSendDate + ': </span>' + '<span style=\"font-weight: bold\">' + data.Date + '</span>'
                      + '<br><span style=\"font-weight: bold\">' + msglist.address + ': </span>' + '<span style=\"font-weight: bold\">' + data.To + '</span>';
            if (data.Cc.length > 0) {
                headerHtml += '<br><span style=\"font-weight: bold\">CC: </span>' + '<span style=\"font-weight: bold\">' + data.Cc + '</span>';
            }
            if (data.Bcc.length > 0) {
                headerHtml += '<br><span style=\"font-weight: bold\">BCC: </span>' + '<span style=\"font-weight: bold\">' + data.Bcc + '</span>';
            }
            if (data.SendPlanDate && data.SendPlanDate.length > 0) {
                headerHtml += '<br><span style=\"font-weight: bold\">' + msglist.wmlSendPlan + ': </span>' + '<span style=\"font-weight: bold; color:#ff0000;\">' + data.SendPlanDate + '</span>';
            }
            headerHtml += '<br><span style=\"font-weight: bold\">' + msglist.label + ': </span>' + '<span style=\"font-weight: bold\">' + data.Label + '</span>';
                        + '</td>';

            //ヘッダ情報取得
            headerHtml += '<td width=\"20%\" align=\"right\" valign=\"bottom\">'
                           + '<a href=\"#\" onClick=\"openMailHeader('
                           + data.XID + ');\">'
                           + '<span style=\"font-size: 11px!important;\">' + msglist.headerInfo + '</span>'
                           + '</a></td></tr>';
            headerHtml += '</table>';

            if (data.fileId.length > 0) {
                headerHtml += '<table width="100%" border=\"1\" padding=\"3\" margin=\"3\" style=\"background-color:#ffffff!important\">';
                var fileIndex;
                for (fileIndex = 0; fileIndex < data.fileId.length; fileIndex++) {
                    headerHtml += '<tr><td>';
                    headerHtml += '<span class=\"tempfile\">'
                                   + '<a ';
                    if (data.fileName[fileIndex] == "attach.html") {
                        headerHtml += 'target=\"_blank\"';
                    }
                    headerHtml += 'href=\"../webmail/wml010.do?CMD=downloadFile&wml010downloadMessageNum='
                                   + data.XID
                                   + '&wmlViewAccount=' + getAccount()
                                   + '&wml010downloadFileId='
                                   + data.fileId[fileIndex]
                                   + '\">'
                                   + data.fileName[fileIndex]
                                   + '&nbsp;(' + data.fileSize[fileIndex] + ')'
                                   + '</a></span>';
                    headerHtml += '</td></tr>';
                }
                headerHtml += '</table>';
            }

            Dom.get('previewHeader').innerHTML = headerHtml;

//            Dom.get('previewBody').innerHTML = '<p style="padding: 3px;">' + data.Body + '</p>';
            Dom.get('previewBody').innerHTML = decodeURIComponent(data.Body);
            inboxMailShow(data);
            readedMail(0);

        }, dataTable, true);

        YAHOO.example.app.dt = dataTable;
        YAHOO.example.app.inboxLoaded = true;

        //コンテキストメニューの設定
        setMailListContextMenu("standard", dataTable);

        resize();
    };

    //Pipes callback
    YAHOO.example.app.inboxDataReady = function(d) {
        emails.messages = [];
        setTimeout(function() {
            //init the datatable
            if (!YAHOO.example.app.inboxLoaded) {
                var inboxMaxPage = YAHOO.example.Data.emails.maxPage;
                initDataTable(mailListHeight, tableWidth(), YAHOO.example.Data.emails);
                if (inboxMaxPage <= 0) {
                    readInboxMailList();
                }
                inboxMaxPage = null;
            } else {
//                dataTable.getRecordSet().replaceRecords(emails.messages);
                dataTable.render();
            }
            //Setup some sizes
            YAHOO.util.Dom.removeClass(YAHOO.example.app.tabView._tabParent, 'loading');
            YAHOO.example.app.inboxLoading = false;
        }, 1000);
    };

    var loader = new YAHOO.util.YUILoader({
        base: '../common/js/yui/',
        require: ['datatable', 'editor'],
        ignore: ['containercore'],
        onSuccess: function() {

            var d = document.createElement('div');
            var mailListHeaderHtml = '<div id="top2" style="width: 100%">'
                                   + '  <div id="inboxToolbar">'
                                   + '  <span id="mailControl">';

            mailListHeaderHtml += createPageComboHtml(0);

            mailListHeaderHtml += '  </span>'
                               + '  </div>'
                               + '  <div id="standard" style="z-index: 1;"></div>'
                               + '</div>'
                               + '<div id="center2" class="mailContentArea">'
                               + '  <div id="previewHeader" class="mailHeader"><br><br><br><br></div>'
                               + '  <div id="previewBody" class="mailBody">'
                               + '    <p><b>' + msglist.clickMail + '</b></p><p>' + msglist.selectDisp + '</p>'
                               + '  </div>'
                               + '  <div id="tailHeader" class="mailHeader" style="text-align: right!important; padding-right: 5px;">'
                               + '    <a href="#" onClick="exportPdf(YAHOO.example.app.dt);"><img src="./images/assets/pdf_export.gif">&nbsp;'+ msglist.pdfExport + '</a>'
                               + '    <a href="#" onClick="exportMail(YAHOO.example.app.dt);"><img src="./images/assets/mail_export.gif">&nbsp;'+ msglist.emlExport + '</a>'
                               + '  </div>'
                               + '</div>';

            mailListHeaderHtml += '<div id="bottom2" style="width: 100%">'
                               + '  <div id="inboxToolbarBottom">'
                               + '  <span id="mailControlBottom">'
                               + createPageComboHtml(1)
                               + '  </span>'
                               + '  </div>'
                               + '</div>';

            var d = document.createElement('div');
            d.innerHTML = mailListHeaderHtml;
            document.getElementById('mailBox1').appendChild(d);

//            var resizeTop = new YAHOO.util.Resize('top2', {
//                                                  minWidth: tableWidth(),
//                                                  maxWidth: tableWidth(),
//                                                  minHeight: mailListHeight
//                                                  });

            YAHOO.example.app.inboxToolbar = new YAHOO.widget.Toolbar('inboxToolbar', {
                buttons: [
                    { id: 'newButton', type: 'push', label: msglist.createMail, value: 'new'},
                    { id: 'closeButton', type: 'push', label: msglist.goBack, value: 'mailClose'},
                    { id: 'moveBoxButton', type: 'push', label: msglist.mailSave, value: 'mailMove'},
                    { id: 'tb_changeMailDir', type: 'push', label: msglist.mailMove, value: 'changeMailDir' },
                    { id: 'editBoxButton', type: 'push', label: msglist.mailEdit, value: 'mailEdit'},
                    { type: 'separator' },
                    { id: 'tb_delete', type: 'push', label: msglist.delet, value: 'delete' },
                    { id: 'tb_reply', type: 'push', label: msglist.reply, value: 'reply' },
                    { id: 'tb_allReply', type: 'push', label: msglist.replyAll, value: 'allReply' },
                    { id: 'tb_forward', type: 'push', label: msglist.trasfer, value: 'forward' },
                    { type: 'separator' },
                    { id: 'tb_addLabel', type: 'push', label: msglist.addLabel, value: 'label' },
                    { id: 'tb_delLabel', type: 'push', label: msglist.deleteLabel, value: 'label' },
                    { type: 'separator' },
                    { id: 'tb_kidoku', type: 'push', label: msglist.asRead2, value: 'read' },
                    { id: 'tb_midoku', type: 'push', label: msglist.asUnRead2, value: 'unread' },
                    { type: 'separator' },
                    { id: 'tb_emlOutput', type: 'push', label: msglist.emlOutput, value: 'emlOutput' },
                    { type: 'separator' },
                    { id: 'tb_share', type: 'push', label: msglist.share, value: 'share' },
                    { type: 'separator' },
                    { id: 'movePrevMailBtn', type: 'push', label: msglist.movePrevious, value: 'movePrevMail' },
                    { id: 'moveNextMailBtn', type: 'push', label: msglist.moveNext, value: 'moveNextMail' }
                ]
            });
            YAHOO.example.app.inboxToolbar.on('buttonClick', function(ev) {
                if (ev.button.id=='tb_delete') {
                    window.setTimeout(function() {
                        dustMail(YAHOO.example.app.dt);
                        if (getMailListViewMode() == 1) {
                            inboxMailClose();
                        }
                    }, 0);
                }
                if (ev.button.id=='tb_addLabel') {
                    window.setTimeout(function() {
                        showAddLabelDialog();
                    }, 0);
                }
                if (ev.button.id=='tb_delLabel') {
                    window.setTimeout(function() {
                        openDeleteLabel();
                    }, 0);
                }

                if (ev.button.id=='tb_kidoku') {
                    changeReadState(YAHOO.example.app.dt, 0);
                }
                if (ev.button.id=='tb_midoku') {
                    changeReadState(YAHOO.example.app.dt, 1);
                }

                if (ev.button.id=='tb_emlOutput') {
                    emlOutput(YAHOO.example.app.dt);
                }

                if (ev.button.id=='tb_share') {
                    openShareDialog(YAHOO.example.app.dt);
                }

                if (ev.button.id=='tb_smail') {
                    if (getParamValue('wml010pluginSmailUse') == 0) {
                        openEntrySmail(getSelectedMailNum(YAHOO.example.app.dt));
                    }
                }

                if (ev.button.id=='closeButton') {
                    inboxMailClose();
                }

                if (ev.button.id=='moveBoxButton') {
                    keepMail(YAHOO.example.app.dt);
                    inboxMailClose();
                }

                if (ev.button.id=='editBoxButton') {
                    initEditor(YAHOO.example.app.dt, 4);
                }

                if (ev.button.id=='tb_changeMailDir') {
                    window.setTimeout(function() {
                        showMoveMailDialog();
                    }, 0);
                }

                if (ev.button.id=='movePrevMailBtn') {
                    moveSelectRecord(0, -1);
                }

                if (ev.button.id=='moveNextMailBtn') {
                    moveSelectRecord(0, 1);
                }

            });
            YAHOO.example.app.inboxToolbar.on('buttonClick', function(ev) {
                if (ev.button.id == 'newButton') {
                    initEditor(YAHOO.example.app.dt, 0);
                } else if (ev.button.id == 'tb_reply') {
                    initEditor(YAHOO.example.app.dt, 1);
                } else if (ev.button.id == 'tb_allReply') {
                    initEditor(YAHOO.example.app.dt, 2);
                } else if (ev.button.id == 'tb_forward') {
                    initEditor(YAHOO.example.app.dt, 3);
                }

                YAHOO.util.Event.onContentReady('composeTo', function () {
                    if (YAHOO.example.app.tabView.get('activeTab').get('id') == 'composeView') {
                        document.getElementById('composeTo').focus();
                    }
                });
            });
            //Show an alert message with the button they clicked
            YAHOO.example.app.inboxToolbar.on('buttonClick', function(ev) {
                if (dataTable.getRecordSet().getRecord(dataTable.getSelectedRows()[0]) != null) {
                    var data = dataTable.getRecordSet().getRecord(dataTable.getSelectedRows()[0])._oData;
                }
            });
            //Grab it's height for later use
            var toolbarHeight = Dom.get('inboxToolbar').clientHeight + 3;
            if (toolbarHeight > 38) { toolbarHeight = 38; }
            YAHOO.example.app.inboxToolbarHeight = toolbarHeight;

            //Toolbar(下)
            YAHOO.example.app.inboxToolbarBottom = new YAHOO.widget.Toolbar('inboxToolbarBottom', {
                buttons: [
                    { id: 'newButton', type: 'push', label: msglist.createMail, value: 'new'},
                    { id: 'closeButton', type: 'push', label: msglist.goBack, value: 'mailClose'},
                    { id: 'moveBoxButton', type: 'push', label: msglist.mailSave, value: 'mailMove'},
                    { id: 'tb_changeMailDir', type: 'push', label: msglist.mailMove, value: 'changeMailDir' },
                    { id: 'editBoxButton', type: 'push', label: msglist.mailEdit, value: 'mailEdit'},
                    { type: 'separator' },
                    { id: 'tb_delete', type: 'push', label: msglist.delet, value: 'delete' },
                    { id: 'tb_reply', type: 'push', label: msglist.reply, value: 'reply' },
                    { id: 'tb_allReply', type: 'push', label: msglist.replyAll, value: 'allReply' },
                    { id: 'tb_forward', type: 'push', label: msglist.trasfer, value: 'forward' },
                    { type: 'separator' },
                    { id: 'tb_addLabel', type: 'push', label: msglist.addLabel, value: 'label' },
                    { id: 'tb_delLabel', type: 'push', label: msglist.deleteLabel, value: 'label' },
                    { type: 'separator' },
                    { id: 'tb_kidoku', type: 'push', label: msglist.asRead2, value: 'read' },
                    { id: 'tb_midoku', type: 'push', label: msglist.asUnRead2, value: 'unread' },
                    { type: 'separator' },
                    { id: 'tb_emlOutput', type: 'push', label: msglist.emlOutput, value: 'emlOutput' },
                    { type: 'separator' },
                    { id: 'tb_share', type: 'push', label: msglist.share, value: 'share' },
                    { type: 'separator' },
                    { id: 'movePrevMailBtn', type: 'push', label: msglist.movePrevious, value: 'movePrevMail' },
                    { id: 'moveNextMailBtn', type: 'push', label: msglist.moveNext, value: 'moveNextMail' }
                ]
            });
            YAHOO.example.app.inboxToolbarBottom.on('buttonClick', function(ev) {

                if (ev.button.id=='tb_delete') {
                    window.setTimeout(function() {
                        dustMail(YAHOO.example.app.dt);
                        if (getMailListViewMode() == 1) {
                            inboxMailClose();
                            document.body.scrollTop = '100';
                        }
                    }, 0);
                }
                if (ev.button.id=='tb_addLabel') {
                    window.setTimeout(function() {
                        showAddLabelDialog();
                    }, 0);
                }
                if (ev.button.id=='tb_delLabel') {
                    window.setTimeout(function() {
                        openDeleteLabel();
                    }, 0);
                }

                if (ev.button.id=='tb_kidoku') {
                    changeReadState(YAHOO.example.app.dt, 0);
                }
                if (ev.button.id=='tb_midoku') {
                    changeReadState(YAHOO.example.app.dt, 1);
                }

                if (ev.button.id=='tb_emlOutput') {
                    emlOutput(YAHOO.example.app.dt);
                }

                if (ev.button.id=='tb_share') {
                    openShareDialog(YAHOO.example.app.dt);
                }

                if (ev.button.id=='closeButton') {
                    inboxMailClose();
                    document.body.scrollTop = '100';
                }

                if (ev.button.id=='moveBoxButton') {
                    keepMail(YAHOO.example.app.dt);
                    if (getMailListViewMode() == 1) {
                        inboxMailClose();
                        document.body.scrollTop = '100';
                    }
                }

                if (ev.button.id=='editBoxButton') {
                    initEditor(YAHOO.example.app.dt, 4);
                }

                if (ev.button.id=='tb_changeMailDir') {
                    window.setTimeout(function() {
                        showMoveMailDialog();
                    }, 0);
                }

                if (ev.button.id=='tb_export') {
                    exportMail(YAHOO.example.app.dt);
                }

                if (ev.button.id=='movePrevMailBtn') {
                    moveSelectRecord(0, -1);
                }

                if (ev.button.id=='moveNextMailBtn') {
                    moveSelectRecord(0, 1);
                }

            });
            YAHOO.example.app.inboxToolbarBottom.on('buttonClick', function(ev) {
                if (ev.button.id == 'newButton') {
                    initEditor(YAHOO.example.app.dt, 0);
                } else if (ev.button.id == 'tb_reply') {
                    initEditor(YAHOO.example.app.dt, 1);
                } else if (ev.button.id == 'tb_allReply') {
                    initEditor(YAHOO.example.app.dt, 2);
                } else if (ev.button.id == 'tb_forward') {
                    initEditor(YAHOO.example.app.dt, 3);
                }

                YAHOO.util.Event.onContentReady('composeTo', function () {
                    document.getElementById('composeTo').focus();
                });
            });

            inboxMailClose();
            window.setTimeout(function() {
                YAHOO.example.app.inboxDataReady();

                if (YAHOO.example.app.init) {
                    YAHOO.util.Dom.setStyle(document.body, 'visibility', 'visible');
                    YAHOO.util.Event.onContentReady('center2', function () {
                        document.getElementById('id_wml010Body').style.display = '';
                        closeLoadingDialog();
                    });
                    YAHOO.example.app.init = false;
                }

            }, 0);
        }
    });
    loader.insert({}, 'js');

})();

function changePage(value, comboType) {
    var pageList = document.getElementsByName('wml010pageTop')[0];
    var pageListBottom = document.getElementsByName('wml010pageBottom')[0];

    var selectPage = getInboxSelectPage(comboType);
    selectPage += value;

    if (selectPage > 0 && selectPage <= YAHOO.example.Data.emails.maxPage) {
        for (page = 1; page <= pageList.length; page++) {
            if (page == selectPage) {
                pageList[page - 1].selected = true;
                pageListBottom[page - 1].selected = true;
            } else {
                pageList[page - 1].selected = false;
                pageListBottom[page - 1].selected = false;
            }
        }

        setParamValue('wml010selectPage', selectPage);
        reviewInboxTable();
    }
    setParamValue('wml010selectPage', selectPage);
}

function getInboxSelectPage(comboType) {
    var selectPage = 0;

    var pageList = document.getElementsByName('wml010pageTop')[0];
    var pageListBottom = document.getElementsByName('wml010pageBottom')[0];

    for (page = 1; page <= pageList.length; page++) {
        if ((comboType == 0 && pageList[page - 1].selected == true)
            || (comboType == 1 && pageListBottom[page - 1].selected == true)) {
            selectPage = page;
            break;
        }
    }

    return selectPage;
}

function reviewInboxTable() {
    readMailList(1);
    YAHOO.example.app.dt.getColumnSet().flat[0].getThEl().children[0].children[0].children[0].checked = false;
}

function inboxResize() {
    var dataTable = YAHOO.example.app.dt;
    dataTable.set('width', tableWidth() + 'px');
    dataTable.setColumnWidth(dataTable.getColumn('Subject'), (tableWidth() - magicNum));
    dataTable._syncColWidths();
    dataTable._syncScrollPadding();

    document.getElementById('previewBody').style.width = tableWidth();
    document.getElementById('mailBox').style.width = (tableWidth() + 6) + "px";
}

function setInboxPageCombo() {
    document.getElementById('mailControl').innerHTML = createPageComboHtml(0);
    document.getElementById('mailControlBottom').innerHTML = createPageComboHtml(1);
}

function createPageComboHtml(comboType) {

    var maxPage = YAHOO.example.Data.emails.maxPage;
    var mailListHeaderHtml = '';
    if (maxPage > 1) {
        mailListHeaderHtml += '<img alt="' + msglist.prevPage + '" src="../common/images/arrow2_l.gif" width="20" height="20" onClick="changePage(-1, ' + comboType + ');" style="vertical-align:middle;">'
        if (comboType == 1) {
            mailListHeaderHtml += '<select name="wml010pageBottom" onchange="changePage(0, ' + comboType + ');" style="vertical-align:middle;">';
        } else {
            mailListHeaderHtml += '<select name="wml010pageTop" onchange="changePage(0, ' + comboType + ');" style="vertical-align:middle;">';
        }

        for (page = 1; page <= maxPage; page++) {
            var pageSelected = '';
            if (YAHOO.example.Data.emails.page == page) {
                pageSelected = ' selected';
            }
            mailListHeaderHtml += '<option value="' + page + '"' + pageSelected + '>' + page + ' / ' + maxPage + '</option>'
        }
        mailListHeaderHtml += '</select>'
                           + '<img alt="' + msglist.nextPage + '" src="../common/images/arrow2_r.gif" width="20" height="20" onClick="changePage(1, ' + comboType + ');" style="vertical-align:middle;">'
    }

    return mailListHeaderHtml;
}

function openMailHeader(mailNum) {
    var detailWidth = 700;
    var detailHeight = 500;

    window.open('../webmail/wml011.do?wml011mailNum=' + mailNum, '_blank', 'width=' + detailWidth
              + ',height=' + detailHeight + ',titlebar=no,toolbar=no,scrollbars=yes'
              + ', left=' + getWml010CenterX(detailWidth) + ', top=' + getWml010CenterY(detailHeight));
    return false;
}

function getWml010CenterX(winWidth) {
  var x = (screen.width - winWidth) / 2;
  return x;
}

function getWml010CenterY(winHeight) {
  var y = (screen.height - winHeight) / 2;
  return y;
}

var inboxTryCount = 1;
var inboxMaxTryCount = 2;
var inboxReqFinish = true;
var inboxReqInterval = 0;
function readInboxMailList() {

    YAHOO.example.Data = {
        emails : {
            currStorage: 20,
            maxStorage: 400,
            page : 0,
            maxPage : 0,
            messages: []
        }
    };

    YAHOO.example.accountDisk = {
        useDiskSize: 0,
        limitDiskSize: -1,
        useDiskRatio: 0,
        warnDiskRatio: 0
    };

    setParamValue('CMD', 'getMailList');
    var formObject = document.getElementById('webmailForm');
    YAHOO.util.Connect.setForm(formObject);

    inboxTryCount = 1;
    inboxReqFinish = true;
    viewInboxMailList();
    showLoadingDialog();
    inboxReqInterval = setInterval('viewInboxMailList()', 300);
}

function viewInboxMailList() {

    if (inboxReqFinish == false) {
        return;
    }

    if (inboxTryCount > inboxMaxTryCount) {
        clearInterval(inboxReqInterval);
        closeLoadingDialog();
        setViewDelMail(0);
        return;
    }

    var success = false;
    inboxReqFinish = false;

    var request = YAHOO.util.Connect.asyncRequest('POST', '../webmail/wml010.do', {
        success: function(o) {
            try {
              success = setMailListView(o, 1);
            } finally {
              inboxReqFinish = true;
              if (success) {
                inboxTryCount = inboxMaxTryCount + 1;
              } else {
                inboxTryCount++;
              }
            }
        },

        //取得失敗時の処理
        failure: function(o) {
            inboxTryCount++;
        }
    }, getMailListPostData());
    request = null;
}
