//var magicNum = 423,
var magicNum = 510,
    mailListHeight = 600;

function searchTableWidth() {
    var width = getBasicWidth() - 240;
    if (width < 780) {
        width = 780;
    }
    return width;
}

function getToolbarHeight() {
    var toolbarHeight = YAHOO.util.Dom.get('searchToolBar').clientHeight;
    if (toolbarHeight > 38) { toolbarHeight = 38; }
    return toolbarHeight;
}

function getSearchResultSortKey() {
    return getSortColumnName(getParamValue('wml010searchSortKey'));
}

function getSearchResultOrder() {
    if (getParamValue('wml010searchOrder') == 1) {
        return YAHOO.widget.DataTable.CLASS_DESC;
    }
    return YAHOO.widget.DataTable.CLASS_ASC;
}

function doSearchResultSort(sortKey, order) {
    if (sortKey == 'Attach') {
        setParamValue('wml010searchSortKey', 1);
    } else if (sortKey == 'Subject') {
        setParamValue('wml010searchSortKey', 2);
    } else if (sortKey == 'From') {
        setParamValue('wml010searchSortKey', 3);
    } else if (sortKey == 'Date') {
        setParamValue('wml010searchSortKey', 4);
    } else if (sortKey == 'Readed') {
        setParamValue('wml010searchSortKey', 5);
    } else if (sortKey == 'MailSize') {
        setParamValue('wml010searchSortKey', 6);
    }
    setParamValue('wml010searchOrder', order);

    reviewSearchResultTable();
}

(function() {

    var searchResultTable = null;
    var searchResults = {
        currStorage: 20,
        maxStorage: 200,
        messages: []
    };
    var Dom = YAHOO.util.Dom;
    var data_search = YAHOO.example.SearchData.emails;

    var searchHtml = '<div id="searchBarWrap" style="background-color:#eeeeee;">'
                   + '  <div id="searchToolBar">'
                   + '<span id="searchResultControl">'
                   + createSearchResultPageComboHtml(0)
                   + '</span>'
                   + '</div>'
                   + '  <div id="searchResultList"></div>'
                   + '</div>'
                   + '<div id="searchBody" class="mailContentArea">'
                   + '  <div id=\"' + 'searchPreviewHeader' + '\" class=\"' + 'mailHeader' + '\"><br><br><br><br></div>'
                   + '  <div id=\"' + 'searchPreviewBody' + '\" class=\"' + 'mailBody' + '\">'
                   + '    <p><b>' + msglist.clickMail + '</b></p><p>' + msglist.selectDisp + '</p>'
                   + '  </div>'
                   + '  <div id="searchTailHeader" class="mailHeader" style="text-align: right!important; padding-right: 5px;">'
                   + '    <a href="#" onClick="exportPdf(YAHOO.example.app.search.dt);"><img src="./images/assets/pdf_export.gif">&nbsp;' + msglist.pdfExport + '</a>'
                   + '    <a href="#" onClick="exportMail(YAHOO.example.app.search.dt);"><img src="./images/assets/mail_export.gif">&nbsp;' + msglist.emlExport + '</a>'
                   + '  </div>'
                   + '</div>';

    searchHtml += '<div id="bottom2" style="width: 100%">'
               + '  <div id="searchToolBarBottom">'
               + '  <span id="searchResultControlBottom">'
               + createSearchResultPageComboHtml(1)
               + '  </span>'
               + '  </div>'
               + '</div>';

    YAHOO.util.Dom.get('searchResult').innerHTML = searchHtml;

    var initSearchDataTable = function(h, w) {
        //============================= 検索結果一覧の表示 ======================================
        var myColumnDefs = [
//          {key:"XID", label:"　", width: 15, sortable:false, formatter: formatMailCheckBox },
          {key:"XID", label:"<input type=\"checkbox\" id=\"wml010searchAllSelect\" name=\"wml010searchAllSelect\" onClick=\"mailListAllSelect(1)\">", width: "15px", sortable:false, formatter: formatMailCheckBox },
//          {key:"Attach", label:"&nbsp;", sortable:true, formatter: formatMailListAttach },
//          {key:"Readed", label:"&nbsp;", sortable:true, formatter: formatMailListReaded },
          {key:"Attach", label:"<img src=\"../webmail/images/assets/attach2.gif\">", width: "20px", sortable:true, formatter: formatMailListAttach },
          {key:"Readed", label:"<img src=\"../webmail/images/assets/readed.gif\">", width: "20px", sortable:true, formatter: formatMailListReaded },
          {key:"From", label:msglist.sender, width:146, sortable:true, resizeable:false, formatter: formatMailListText},
          {key:"Subject", label:msglist.subject, width: (w - magicNum), sortable:true, resizeable:false, formatter: formatMailListSubject},
          {key:"Date", label:msglist.dateTime, width:130, sortable:true, resizeable:false, formatter: formatMailListText},
          {key:"MailSize", label:msglist.txtSize, width: 60, sortable:true, resizeable:false, formatter: formatMailListMailSize}
        ];

        //Create the datasource
        var myDataSource = new YAHOO.util.DataSource(data_search);
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
        myDataSource.responseSchema = {
            resultsList: 'messages',
            fields: getReceiveMailFields()
        };

        //Give the DT a custom Height
        var dtH = (h - 27 - getToolbarHeight());
        searchResultTable = new YAHOO.widget.DataTable("searchResultList",
                myColumnDefs, myDataSource,
//                { scrollable: true, height: dtH + 'px', width: w + 'px',
                { scrollable: false, height: '100%', width: w + 'px',
                  selectionMode:'single',
                  sortedBy:{key:getSearchResultSortKey(),
                            dir:getSearchResultOrder()}
                });
        //ソート
        searchResultTable.sortColumn = function(oColumn) {
            var sDir = 0
            var sortDir = YAHOO.widget.DataTable.CLASS_ASC;

            if(oColumn.key == this.get("sortedBy").key) {
                if (this.get("sortedBy").dir === YAHOO.widget.DataTable.CLASS_ASC) {
                    sDir = 1;
                    sortDir = YAHOO.widget.DataTable.CLASS_DESC;
                }
            }

            this.set("sortedBy", {key:oColumn.key, dir:sortDir, column:oColumn});

            doSearchResultSort(oColumn.key, sDir);
        };

        searchResultTable.subscribe("rowMouseoverEvent",
                                    searchResultTable.onEventHighlightRow);
        searchResultTable.subscribe("rowMouseoutEvent",
                                    searchResultTable.onEventUnhighlightRow);
//        searchResultTable.subscribe("rowClickEvent", searchResultTable.onEventSelectRow);

        var searchTooltip = new YAHOO.widget.Tooltip("searchMailTooltip");

        searchResultTable.subscribe("cellMouseoverEvent", function(oArgs) {
          try {
            var column = this.getColumn(oArgs.target);
            var columnKey = column.key;
            if (columnKey == 'From') {
              var cellRecord = this.getRecordSet().getRecord(oArgs);
              var data = this.getRecord(oArgs.target)._oData;
              var viewAddress = createTooltipAddress(data);
              if (viewAddress) {
                  searchTooltip.setBody(viewAddress);

                  var xy = [parseInt(oArgs.event.clientX,10) + 10 + document.body.scrollLeft ,parseInt(oArgs.event.clientY,10) + 10 + document.body.scrollTop];
                  searchTooltip.cfg.setProperty('xy',xy);
                  searchTooltip.show();
              } else {
                  searchTooltip.hide();
              }
            } else {
                searchTooltip.hide();
            }
          } catch (e) {}
        }, searchResultTable, true);

        searchResultTable.subscribe("cellMouseoutEvent", function(oArgs) {
            searchTooltip.hide();
        }, searchResultTable, true);

        searchResultTable.subscribe("cellClickEvent", function(oArgs) {
            var target = oArgs.target;
            var columnKey = searchResultTable.getColumn(target).key;
            if (columnKey == 'XID') {
                document.getElementById(oArgs.target.id).children[0].children[0].checked =
                    (document.getElementById(oArgs.target.id).children[0].children[0].checked == false);
                this.getRecord(oArgs.target).setData('selectedMail',
                                                     document.getElementById(oArgs.target.id).children[0].children[0].checked);
            } else {
                searchResultTable.onEventSelectRow(oArgs);
            }
        }, searchResultTable, true);

        //checkboxクリック時のイベントハンドラ
        searchResultTable.subscribe("checkboxClickEvent", function(oArgs) {
//            this.getRecord(oArgs.target).setData('selectedMail', oArgs.target.checked);
            oArgs.target.checked = (oArgs.target.checked == false);
        }, searchResultTable, true);

        searchResultTable.subscribe("rowSelectEvent", function(el, Record) {

            var data = this.getRecordSet().getRecord(this.getSelectedRows()[0])._oData;
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
                          + '<span style=\"font-weight: bold\">' + msglist.subject + ': </span>' + '<span style=\"font-weight: bold\">' + subject + '</span>'
                          + '<br><span style=\"font-weight: bold\">' + msglist.mailSender2 + ': </span>' + '<span style=\"font-weight: bold\">' + data.From + '</span>';
            if (data.From.length > 0 && getParamValue('wml010pluginAddressUse') == 0) {
                headerHtml += '&nbsp;&nbsp;<a href=\"#\" onClick=\"openEntryAddress(' + data.XID + ');\">'
                    + '<img src=\"../webmail/images/add_address_icon.gif\" valign=\"bottom\" alt="\"registerAddress\""></a>';
            }

            headerHtml += '<br><span style=\"font-weight: bold\">' + msglist.mailSendDate + ': </span>' + '<span style=\"font-weight: bold\">' + data.Date + '</span>'
                  + '<br><span style=\"font-weight: bold\">' + msglist.address + ': </span>' + '<span style=\"font-weight: bold\">' + data.To + '</span>';
            if (data.Cc.length > 0) {
                headerHtml += '<br><span style=\"font-weight: bold\">CC: </span>' + data.Cc+ '</span>';
            }
            if (data.Bcc.length > 0) {
                headerHtml += '<br><span style=\"font-weight: bold\">BCC: </span>' + data.Bcc+ '</span>';
            }

            if (data.SendPlanDate && data.SendPlanDate.length > 0) {
                headerHtml += '<br><span style=\"font-weight: bold\">' + msglist.wmlSendPlan + ': </span>' + '<span style=\"font-weight: bold; color:#ff0000;\">' + data.SendPlanDate + '</span>';
            }
            headerHtml += '<br><span style=\"font-weight: bold\">' + msglist.label + ': </span>' + data.Label+ '</span>';
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
                        headerHtml += 'target=\"_black\"';
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

            Dom.get('searchPreviewHeader').innerHTML = headerHtml;
            Dom.get('searchPreviewBody').innerHTML = '<p>' + decodeURIComponent(data.Body) + '</p>';

            searchMailShow(data);
            readedMail(1);
        }, searchResultTable, true);

        //=======================================================================================

        YAHOO.example.app.search.dt = searchResultTable;
        YAHOO.example.app.search.searchLoaded = true;
        searchResultResize();

        if (getParamValue('wml010searchSortKey') <= 0) {
            setParamValue('wml010searchSortKey', YAHOO.example.SearchData.sortKey);
            setParamValue('wml010searchOrder', YAHOO.example.SearchData.order);

            if (YAHOO.widget.DataTable) {
                var order = YAHOO.widget.DataTable.CLASS_ASC;
                if (YAHOO.example.SearchData.order == 1) {
                   order = YAHOO.widget.DataTable.CLASS_DESC;
                }
                var sortColumnName = getSortColumnName(YAHOO.example.SearchData.sortKey);
                if (YAHOO.example.app.search.dt) {
                    YAHOO.example.app.search.dt.set("sortedBy", {key:sortColumnName, dir:order, column:YAHOO.example.app.search.dt.getColumn(sortColumnName)});
                }

                order = null;
                sortColumnName = null;
            }
        }

        //コンテキストメニューの設定
        //setContextMenu();
        setMailListContextMenu("searchResultList", searchResultTable);

    };

    //Pipes callback
    YAHOO.example.app.search.searchDataReady = function(d) {
        setTimeout(function() {
            initSearchDataTable(mailListHeight, searchTableWidth());
            //init the datatable
            //Setup some sizes
            YAHOO.util.Dom.removeClass(YAHOO.example.app.tabView._tabParent, 'loading');
            YAHOO.example.app.search.searchLoading = false;
        }, 1000);
    };

    YAHOO.example.app.search.searchReloadData = function(empty) {
        if (empty === false) {
            var d = {
                value: {
                    items: []
                }
            };
            YAHOO.example.app.search.searchDataReady(d);
        }
    };

    //Use loader to load the Editor
    var loader = new YAHOO.util.YUILoader({
        base: '../common/js/yui/',
        require: ['datatable', 'editor'],
        ignore: ['containercore'],
        onSuccess: function() {
            YAHOO.util.Get.css('../webmail/css/search.css');
            YAHOO.example.app.search.searchDataReady(false);

            //==================================== Toolbarの設定 ====================================
            YAHOO.example.app.search.searchToolBar = new YAHOO.widget.Toolbar('searchToolBar', {
                buttons: [
                    { id: 'newButton', type: 'push', label: msglist.createMail, value: 'new'},
                    { id: 'closeButton', type: 'push', label: msglist.goBack, value: 'mailClose'},
                    { id: 'moveBoxButton', type: 'push', label: msglist.mailSave, value: 'mailMove'},
                    { id: 'tb_changeMailDir', type: 'push', label: msglist.mailMove, value: 'changeMailDir' },
                    { id: 'editBoxButton', type: 'push', label: msglist.mailEdit, value: 'mailEdit'},
                    { type: 'separator' },
                    { id: 'tb_delete', type: 'push', label: msglist.delet, value: 'delete'},
                    { id: 'tb_reply', type: 'push', label: msglist.reply, value: 'reply' },
                    { id: 'tb_allReply', type: 'push', label: msglist.replyAll, value: 'allReply' },
                    { id: 'tb_forward', type: 'push', label: msglist.trasfer, value: 'forward' },
                    { type: 'separator' },
                    { id: 'tb_addLabel', type: 'push', label: msglist.addLabel, value: 'label'},
                    { id: 'tb_delLabel', type: 'push', label: msglist.deleteLabel, value: 'label'},
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

            YAHOO.example.app.search.searchToolbarHeight = getToolbarHeight() + 3;

            //=======================================================================================

            //=================================== ボタンの設定 ======================================

            YAHOO.example.app.search.searchToolBar.on('buttonClick', function(ev) {
                if (ev.button.id=='tb_delete') {
                    window.setTimeout(function() {
                        dustMail(YAHOO.example.app.search.dt);
                        if (getMailListViewMode() == 1) {
                            searchMailClose();
                        } else {
                            reviewSearchResultTable();
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
                    changeReadState(YAHOO.example.app.search.dt, 0);
                }
                if (ev.button.id=='tb_midoku') {
                    changeReadState(YAHOO.example.app.search.dt, 1);
                }

                if (ev.button.id=='tb_emlOutput') {
                    emlOutput(YAHOO.example.app.search.dt);
                }

                if (ev.button.id=='closeButton') {
                    searchMailClose();
                }

                if (ev.button.id=='moveBoxButton') {
                    keepMail(YAHOO.example.app.search.dt);
                    searchMailClose();
                }

                if (ev.button.id=='tb_changeMailDir') {
                    window.setTimeout(function() {
                        showMoveMailDialog();
                    }, 0);
                }

                if (ev.button.id=='tb_share') {
                    openShareDialog(YAHOO.example.app.search.dt);
                }

                if (ev.button.id=='editBoxButton') {
                    initEditor(YAHOO.example.app.search.dt, 4);
                }

                if (ev.button.id=='movePrevMailBtn') {
                    moveSelectRecord(1, -1);
                }

                if (ev.button.id=='moveNextMailBtn') {
                    moveSelectRecord(1, 1);
                }
            });

            YAHOO.example.app.search.searchToolBar.on('buttonClick', function(ev) {
                if (ev.button.id == 'newButton') {
                    initEditor(YAHOO.example.app.search.dt, 0);
                } else if (ev.button.id == 'tb_reply') {
                    initEditor(YAHOO.example.app.search.dt, 1);
                } else if (ev.button.id == 'tb_allReply') {
                    initEditor(YAHOO.example.app.search.dt, 2);
                } else if (ev.button.id == 'tb_forward') {
                    initEditor(YAHOO.example.app.search.dt, 3);
                }

                YAHOO.util.Event.onContentReady('composeTo', function () {
                    document.getElementById('composeTo').focus();
                });
            });


            //Toolbar(下)
            YAHOO.example.app.search.searchToolBarBottom = new YAHOO.widget.Toolbar('searchToolBarBottom', {
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
            YAHOO.example.app.search.searchToolBarBottom.on('buttonClick', function(ev) {
                if (ev.button.id=='tb_delete') {
                    window.setTimeout(function() {
                        dustMail(YAHOO.example.app.search.dt);
                        if (getMailListViewMode() == 1) {
                            searchMailClose();
                            document.body.scrollTop = '50';
                        } else {
                            reviewSearchResultTable();
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
                    changeReadState(YAHOO.example.app.search.dt, 0);
                }
                if (ev.button.id=='tb_midoku') {
                    changeReadState(YAHOO.example.app.search.dt, 1);
                }

                if (ev.button.id=='tb_emlOutput') {
                    emlOutput(YAHOO.example.app.search.dt);
                }

                if (ev.button.id=='closeButton') {
                    searchMailClose();
                    document.body.scrollTop = '50';
                }

                if (ev.button.id=='moveBoxButton') {
                    keepMail(YAHOO.example.app.search.dt);
                    if (getMailListViewMode() == 1) {
                        searchMailClose();
                        document.body.scrollTop = '50';
                    }
                }

                if (ev.button.id=='editBoxButton') {
                    initEditor(YAHOO.example.app.search.dt, 4);
                }

                if (ev.button.id=='tb_changeMailDir') {
                    window.setTimeout(function() {
                        showMoveMailDialog();
                    }, 0);
                }

                if (ev.button.id=='tb_share') {
                    openShareDialog(YAHOO.example.app.search.dt);
                }

                if (ev.button.id=='tb_export') {
                    exportMail(YAHOO.example.app.search.dt);
                }

                if (ev.button.id=='movePrevMailBtn') {
                    moveSelectRecord(1, -1);
                }

                if (ev.button.id=='moveNextMailBtn') {
                    moveSelectRecord(1, 1);
                }
            });

            YAHOO.example.app.search.searchToolBarBottom.on('buttonClick', function(ev) {
                if (ev.button.id == 'newButton') {
                    initEditor(YAHOO.example.app.search.dt, 0);
                } else if (ev.button.id == 'tb_reply') {
                    initEditor(YAHOO.example.app.search.dt, 1);
                } else if (ev.button.id == 'tb_allReply') {
                    initEditor(YAHOO.example.app.search.dt, 2);
                } else if (ev.button.id == 'tb_forward') {
                    initEditor(YAHOO.example.app.search.dt, 3);
                }

                YAHOO.util.Event.onContentReady('composeTo', function () {
                    document.getElementById('composeTo').focus();
                });
            });
            //=======================================================================================


            //========================= 削除(タブの×ボタン)時の処理 ================================

            var searchStartResize = function() {
                YAHOO.example.app.search.set('disabled', true);
            };
            //Custom search result resize method
            var searchResize = function() {
                var h = YAHOO.util.Dom.get('composeViewSearch').parentNode.clientHeight - (YAHOO.util.Dom.get('searchBarWrap').clientHeight);
                var newH = (h - getToolbarHeight());
                YAHOO.example.app.search.set('height', newH + 'px');
                YAHOO.example.app.search.set('width', 750 + 'px');
                YAHOO.example.app.search.set('disabled', false);
            };

            //Method to destroy the search result.
            YAHOO.example.app.search.destroySearch = function() {
                YAHOO.example.app.search = null;
            };
            //=======================================================================================

            searchMailClose();
        }
    });

    //Have loader only insert the js files..
    loader.insert({}, 'js');
})();

function searchResultResize() {
    var dataTable = YAHOO.example.app.search.dt;
    dataTable.set('width', searchTableWidth() + 'px');
    dataTable.setColumnWidth(dataTable.getColumn('Subject'), (searchTableWidth() - magicNum));
    dataTable._syncColWidths();
    dataTable._syncScrollPadding();
}

function changeSearchResultPage(value, comboType) {
    var pageList = document.getElementsByName('wml010searchPageTop')[0];
    var pageListBottom = document.getElementsByName('wml010searchPageBottom')[0];

    var selectPage = getSearchResultSelectPage(comboType);
    selectPage += value;

    if (selectPage > 0 && selectPage <= YAHOO.example.SearchData.emails.maxPage) {
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
        reviewSearchResultTable();
    }

    setParamValue('wml010selectPage', selectPage);
}

function getSearchResultSelectPage(comboType) {
    var selectPage = 0;

    var pageList = document.getElementsByName('wml010searchPageTop')[0];
    var pageListBottom = document.getElementsByName('wml010searchPageBottom')[0];

    for (page = 1; page <= pageList.length; page++) {
        if ((comboType == 0 && pageList[page - 1].selected == true)
            || (comboType == 1 && pageListBottom[page - 1].selected == true)) {
            selectPage = page;
            break;
        }
    }

    return selectPage;
}

function reviewSearchResultTable() {
    searchResultLoad(getParamValue('wml010searchType'));
    YAHOO.example.app.search.dt.getColumnSet().flat[0].getThEl().children[0].children[0].children[0].checked = false;
}

function setSearchResultPageCombo() {
    document.getElementById('searchResultControl').innerHTML = createSearchResultPageComboHtml(0);
    document.getElementById('searchResultControlBottom').innerHTML = createSearchResultPageComboHtml(1);
}

function createSearchResultPageComboHtml(comboType) {

    var maxPage = YAHOO.example.SearchData.emails.maxPage;
    var mailListHeaderHtml = '';

    if (maxPage > 1) {

        mailListHeaderHtml += '<img alt="' + msglist.prevPage + '" src="../common/images/arrow2_l.gif" width="20" height="20" onClick="changeSearchResultPage(-1,' + comboType + ');" style="vertical-align:middle;">'

        if (comboType == 1) {
            mailListHeaderHtml += '<select name="wml010searchPageBottom" onchange="changeSearchResultPage(0,' + comboType + ');" style="vertical-align:middle;">';
        } else {
            mailListHeaderHtml += '<select name="wml010searchPageTop" onchange="changeSearchResultPage(0,' + comboType + ');" style="vertical-align:middle;">';
        }

        for (page = 1; page <= maxPage; page++) {
            var pageSelected = '';
            if (YAHOO.example.SearchData.emails.page == page) {
                pageSelected = ' selected';
            }
            mailListHeaderHtml += '<option value="' + page + '"' + pageSelected + '>' + page + ' / ' + maxPage + '</option>'
        }
        mailListHeaderHtml += '</select>'
                           + '<img alt="' + msglist.nextPage + '" src="../common/images/arrow2_r.gif" width="20" height="20" onClick="changeSearchResultPage(1,' + comboType + ');" style="vertical-align:middle;">'
    }
    return mailListHeaderHtml;
}

function searchMailShow(data) {
    mailDetailShow('searchResultList', 'searchBody', YAHOO.example.app.search.searchToolBar, YAHOO.example.app.search.searchToolBarBottom, data);
    document.getElementById('searchResultControl').innerHTML = '';
    document.getElementById('searchResultControlBottom').innerHTML = '';
    setOpenMailData(1, data);
}

function searchMailClose() {
    mailDetailClose('searchResultList', 'searchBody', YAHOO.example.app.search.searchToolBar, YAHOO.example.app.search.searchToolBarBottom);
    setOpenMailData(1, null);
    setSearchResultPageCombo();
}
