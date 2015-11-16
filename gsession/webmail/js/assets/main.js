var tryCount = 1;
var MaxTryCount = 2;
var reqFinish = true;
var reqInterval = 0;

var DIRTYPE_INBOX = 1,
    DIRTYPE_SENT = 2,
    DIRTYPE_UNSENT = 3,
    DIRTYPE_DRAFT = 4,
    DIRTYPE_DUST = 5;

function resize() {

    //メール一覧のリサイズ
    if (YAHOO.example.app.dt) {
        inboxResize();
    }

    //エディターのリサイズ
    if (YAHOO.example.app.editor != null) {
        editorResize();
    }

    //検索結果のリサイズ
    if (YAHOO.example.app.search && YAHOO.example.app.search.dt) {
        searchResultResize();
    }
}

(function() {
    YAHOO.util.Dom.setStyle(document.body, 'visibility', 'hidden');
    document.getElementById('top_detailSearch').style.display='none';
    document.getElementById('searchDateArea').style.display='none';
    var c = YAHOO.util.Dom.getElementsByClassName("opening_hidden");
    YAHOO.util.Dom.setStyle(c, 'display', 'block');

    readMailList(0);

    YAHOO.example.app = {
        init: true,
        inboxLoaded: false,
        inboxLoading: false,
        getFeed: function(u) {
            if (!YAHOO.example.app.inboxLoading) {
                var reload = true;
                YAHOO.example.app.inboxLoading = true;
                YAHOO.util.Dom.addClass(YAHOO.example.app.tabView._tabParent, 'loading');
                if (!YAHOO.example.app.inboxLoaded) {
                    var transactionObj = YAHOO.util.Get.script('../webmail/js/assets/inbox.js?reload=' + (+new Date()), { autopurge: true });
                } else {
                    if (reload) {
                        YAHOO.example.app.reloadData(u);
                    } else {
                        YAHOO.util.Dom.removeClass(YAHOO.example.app.tabView._tabParent, 'loading');
                        YAHOO.example.app.inboxLoading = false;
                    }
                }
            }
        }
    };

    //Call loader the first time
    var loader = new YAHOO.util.YUILoader({
        base: '../common/js/yui/',
        //Get these modules
        require: ['reset-fonts-grids', 'utilities', 'button', 'tabview', 'selector', 'resize', 'layout','treeview','menu'],
        rollup: true,
        onSuccess: function() {
            showLoadingDialog();

            //Load the global CSS file.
            var loadDate = '?reload=' + (+new Date());
            YAHOO.util.Get.css('../webmail/css/example1.css' + loadDate);
            YAHOO.util.Get.css('../webmail/css/search.css' + loadDate);

            YAHOO.util.Get.script('../webmail/js/assets/buttons.js' + loadDate);
            YAHOO.util.Get.script('../webmail/js/assets/tree.js' + loadDate);
            YAHOO.util.Get.script('../webmail/js/assets/popup.js' + loadDate);
            YAHOO.util.Get.script('../webmail/js/assets/context.js' + loadDate);
            loadDate = null;

            //Setup the click listeners on the folder list
            YAHOO.util.Event.on('folder_list', 'click', function(ev) {
                var tar = YAHOO.util.Event.getTarget(ev);
                if (tar.tagName.toLowerCase() != 'a') {
                    tar = null;
                }
                //Make sure we are a link in the list's
                if (tar && YAHOO.util.Selector.test(tar, '#treediv * a')) {
                    //if the href is a '#' then select the proper tab and change it's label
                    var folderLinkStr = tar.getAttribute('href', 2);
                    var folderLinkLength = folderLinkStr.length;
                    if (tar && folderLinkLength > 0 && folderLinkStr.lastIndexOf('#') == folderLinkLength - 1) {
                        YAHOO.util.Dom.removeClass(YAHOO.util.Selector.query('#treediv * span'), 'selected');
                        var feedName = tar.parentNode.className;
                        YAHOO.util.Dom.addClass(tar.parentNode, 'selected');
                        YAHOO.util.Event.stopEvent(ev);
                        var title = tar.innerHTML;
                        var t = YAHOO.example.app.tabView.get('tabs');
                        for (var i = 0; i < t.length; i++) {
                            if (t[i].get('id') == 'inBox') {
                                t[i].set('label', title);
                                YAHOO.example.app.tabView.set('activeTab', t[i]);
                            }
                        }
                    }
                }
            });

//            YAHOO.util.Get.script('../webmail/js/assets/inbox.js');
//            YAHOO.example.app.tabView = new YAHOO.widget.TabView('mailBox');

        }

    });

    loader.insert();

    changeSearchDateType();

    //リサイズ時のイベント
    window.onresize = function() {
        resize();
    };
})();

function readMailList(type) {
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

    //メール一覧取得処理
    if (type == 1) {
        showLoadingDialog();
    }

    setParamValue('CMD', 'getMailList');
    var formObject = document.getElementById('webmailForm');
    YAHOO.util.Connect.setForm(formObject);

    tryCount = 1;
    reqFinish = true;
    viewMailList(type);
    reqInterval = setInterval('viewMailList(' + type + ')', 300);
}

function viewMailList(type) {
    if (reqFinish == false) {
        return;
    }

    if (tryCount > MaxTryCount) {
        clearInterval(reqInterval);
        if (type == 1) {
            closeLoadingDialog();
        }
        setViewDelMail(0);
        return;
    }

//    if (getMailListViewMode() == 1) {
//        setParamValue('wml010mailListType', MAILLIST_TYPE_SEARCH);
//    } else {
//        setParamValue('wml010mailListType', MAILLIST_TYPE_NORMAL);
//    }

    var success = false;
    reqFinish = false;

    var request = YAHOO.util.Connect.asyncRequest('POST', '../webmail/wml010.do', {
        success: function(o) {
            try {
              success = setMailListView(o, type);
            } finally {
              reqFinish = true;
              if (success) {
                tryCount = MaxTryCount + 1;
              } else {
                tryCount++;
              }
            }
        },

        //取得失敗時の処理
        failure: function(o) {
            tryCount++;
        }
    }, getMailListPostData());
    request = null;
}

function searchResultLoad(searchType) {

    YAHOO.example.SearchData = {
        sortKey: 0,
        order: 0,
        emails : {
            currStorage: 20,
            maxStorage: 200,
            page : 0,
            maxPage : 0,
            messages: []
        }
    };

    //メール一覧取得処理
    var url = getMailListUrl();
    showLoadingDialog();

    setParamValue('CMD', 'detailSearch');
    setParamValue('wml010searchType', searchType);
    var formObject = document.getElementById('webmailForm');
    YAHOO.util.Connect.setForm(formObject);

    var request = YAHOO.util.Connect.asyncRequest('POST', '../webmail/wml010.do', {
        success: function(o) {
            var mailData = o.responseText;
            if (mailData != null) {
                var data = JSON.parse(mailData);
                if (data.error == 1) {
                    setErrorMessge(data.errorMessage);
                    closeLoadingDialog();
                    return;
                }

                clearErrorMessge();
                YAHOO.example.SearchData.emails.page = data.page;
                YAHOO.example.SearchData.emails.maxPage = data.maxPage;

                for (idx = 0; idx < data.messages.length; idx++) {
                    data.messages[idx].From = decodeURIComponent(data.messages[idx].From);
                    data.messages[idx].ListFrom = decodeURIComponent(data.messages[idx].ListFrom);
                    data.messages[idx].Subject = decodeURIComponent(data.messages[idx].Subject);
                    data.messages[idx].ListTo = decodeURIComponent(data.messages[idx].ListTo);

                    if (data.messages[idx].fileName) {
                       for (fIdx = 0; fIdx < data.messages[idx].fileName.length; fIdx++) {
                           data.messages[idx].fileName[fIdx] =
                             decodeURIComponent(data.messages[idx].fileName[fIdx]);
                       }
                    }
                }
                YAHOO.example.SearchData.emails.messages = data.messages;

                YAHOO.example.accountDisk.useDiskSize = data.useDiskSize;
                YAHOO.example.accountDisk.limitDiskSize = data.limitDiskSize;
                YAHOO.example.accountDisk.useDiskRatio = data.useDiskRatio;
                YAHOO.example.accountDisk.warnDiskRatio = data.warnDiskRatio;

                YAHOO.example.SearchData.sortKey = data.sortKey;
                YAHOO.example.SearchData.order = data.order;

                writeAccountDiskData();
            }

            if (YAHOO.example.app.search == null) {
                viewSearchResult();
            } else {
                YAHOO.example.app.search.dt.getRecordSet().replaceRecords(YAHOO.example.SearchData.emails.messages);
                YAHOO.example.app.search.dt.refreshView();
                if (getMailListViewMode() == 0) {
                    setSearchResultPageCombo();
                }
                var t = YAHOO.example.app.tabView.get('tabs');
                for (var i = 0; i < t.length; i++) {
                    if (t[i].get('id') == 'searchView') {
                        YAHOO.example.app.tabView.set('activeTab', t[i]);
                    }
                }
            }

            closeLoadingDialog();
        },

        //取得失敗時の処理
        failure: function(o) {
            alert('メールの検索に失敗しました。');
            closeLoadingDialog();
        }
    });

}

function searchResultLoadInit(searchType) {
    setParamValue('wml010searchSortKey', 0);
    setParamValue('wml010searchOrder', 0);
    searchResultLoad(searchType);
}

function viewSearchResult() {

    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;

    if (YAHOO.example.app.search) {
        return false;
    }

    YAHOO.example.app.search = {
        searchLoaded: false,
        searchLoading: false,
        getFeed: function(u) {
            var reload = true;
            YAHOO.example.app.search.searchLoading = true;
            YAHOO.util.Dom.addClass(YAHOO.example.app.tabView._tabParent, 'loading');
            if (!YAHOO.example.app.search.searchLoaded) {
                var transactionObj = YAHOO.util.Get.script('../webmail/js/assets/searchResult.js?reload=' + (+new Date()), { autopurge: true });
            } else {
                if (reload) {
                    YAHOO.example.app.search.searchReloadData(u);
                    YAHOO.example.app.search.searchLoading = false;
                } else {
                    YAHOO.util.Dom.removeClass(YAHOO.example.app.tabView._tabParent, 'loading');
                    YAHOO.example.app.search.searchLoading = false;
                }
            }
        }
    };

    if(!YAHOO.example.app.searchResult) {
        var cTab = new YAHOO.widget.Tab({
            label: '<span class="close"></span><span class="icon"></span>検索結果',
            id: 'searchView',
            active: true,
            content: '<div id="searchResult"></div>'
        });
        //Add the close button to the tab
        Event.on(cTab.get('labelEl').getElementsByTagName('span')[0], 'click', function(ev) {
            Event.stopEvent(ev);
            YAHOO.example.app.tabView.set('activeTab', YAHOO.example.app.tabView.get('tabs')[0]);
            YAHOO.example.app.search.destroySearch();
            YAHOO.example.app.tabView.removeTab(cTab);

        });
        YAHOO.example.app.tabView.addTab(cTab);
        window.setTimeout(function() {
            var transactionObj = YAHOO.util.Get.script('../webmail/js/assets/searchResult.js?reload=' + (+new Date()), { autopurge: false });
        }, 0);
    }

    var t = YAHOO.example.app.tabView.get('tabs');
    for (var i = 0; i < t.length; i++) {
        if (t[i].get('id') == 'searchView') {
            YAHOO.example.app.tabView.set('activeTab', t[i]);
        }
    }

    return false;
}

function getMailListUrl() {
    return '../webmail/wml010.do';
}

function getMailListPostData() {
    var postData = 'CMD=getMailList';

    //選択されているアカウント
    postData += '&wmlViewAccount=' + getAccount();

    //選択されているディレクトリ
    postData += '&wml010viewDirectory=' + getDirectory();

    //選択されているラベル
    postData += '&wml010viewLabel=' + getLabel();

    //ページ
    postData += '&wml010selectPage=' + getInboxPage();

    //ソートキー
    postData += '&wml010sortKey=' + getParamValue('wml010sortKey');

    //並び順
    postData += '&wml010order=' + getParamValue('wml010order');

    return postData;
}


function addDebugMsg(msg) {
//document.getElementsByName('mailTextArea')[0].value = "\r\n" + document.getElementsByName('mailTextArea')[0].value + msg;
}

function setDebugMsg(msg) {
//document.getElementsByName('mailTextArea')[0].value = msg;
}

function setMailListView(o, type) {
    var success = false;
    var mailData = o.responseText;
    if (mailData != null) {
        var data = JSON.parse(mailData);
        YAHOO.example.Data.emails.page = data.page;
        YAHOO.example.Data.emails.maxPage = data.maxPage;

        for (idx = 0; idx < data.messages.length; idx++) {
            data.messages[idx].From = decodeURIComponent(data.messages[idx].From);
            data.messages[idx].ListFrom = decodeURIComponent(data.messages[idx].ListFrom);
            data.messages[idx].Subject = decodeURIComponent(data.messages[idx].Subject);

            if (data.messages[idx].fileName) {
               for (fIdx = 0; fIdx < data.messages[idx].fileName.length; fIdx++) {
                   data.messages[idx].fileName[fIdx] =
                     decodeURIComponent(data.messages[idx].fileName[fIdx]);
               }
            }
        }
        YAHOO.example.Data.emails.messages = data.messages;

        YAHOO.example.accountDisk.useDiskSize = data.useDiskSize;
        YAHOO.example.accountDisk.limitDiskSize = data.limitDiskSize;
        YAHOO.example.accountDisk.useDiskRatio = data.useDiskRatio;
        YAHOO.example.accountDisk.warnDiskRatio = data.warnDiskRatio;
        setParamValue('wml010viewAccountAddress', decodeURIComponent(data.viewAccountFrom));
    }

    //初期表示の場合、メール一覧の初期表示処理を行う
    if (type == 0 && !YAHOO.example.app.inboxLoaded && !reqFinish) {
        YAHOO.util.Get.script('../webmail/js/assets/inbox.js');
        YAHOO.example.app.tabView = new YAHOO.widget.TabView('mailBox');
    }

    if (mailData != null) {
        if (getParamValue('wml010sortKey') <= 0) {
            setParamValue('wml010sortKey', data.sortKey);
            setParamValue('wml010order', data.order);

            if (YAHOO.widget.DataTable) {
                var order = YAHOO.widget.DataTable.CLASS_ASC;
                if (data.order == 1) {
                   order = YAHOO.widget.DataTable.CLASS_DESC;
                }
                var sortColumnName = getSortColumnName(data.sortKey);
                if (YAHOO.example.app.dt) {
                    YAHOO.example.app.dt.set("sortedBy", {key:sortColumnName, dir:order, column:YAHOO.example.app.dt.getColumn(sortColumnName)});
                }

                order = null;
                sortColumnName = null;
            }
        }

        writeAccountDiskData();

        if (data.autoDelKbn == 1 || data.autoDelKbn == 2) {
            document.getElementById('autoDeleteArea').style.display='block';
            if (data.autoDelKbn == 1) {
                document.getElementById('autoDelDirName2').innerHTML = data.dirName;

                $('#autoDelLogout').show();
                $('#autoDelDate').hide();
            } else if (data.autoDelKbn == 2) {
                document.getElementById('autoDelDirName').innerHTML = data.dirName;
                document.getElementById('autoDelDateValue').innerHTML = data.autoDelDate;

                $('#autoDelLogout').hide();
                $('#autoDelDate').show();
            }
        } else {
            document.getElementById('autoDeleteArea').style.display='none';
        }

        if (type == 1) {
            if (getMailListViewMode() == 0) {
                setInboxPageCombo();
            }

            if (getMailListType() == 1) {
                document.getElementById('wml010searchAllSelect').checked = false;
            } else {
                document.getElementById('wml010AllSelect').checked = false;
            }

            YAHOO.example.app.dt.getRecordSet().replaceRecords(YAHOO.example.Data.emails.messages);
            YAHOO.example.app.dt.refreshView();
        }

        success = true;
    }

    clearErrorMessge();

    return success;
}
