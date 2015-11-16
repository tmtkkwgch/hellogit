(function() {
  initTree();
})();

var dirAreaIdStr = 'dirArea';
var labelAreaIdStr = 'labelArea';
var wmlEditorFocusParamName;

function drawShainList(shainList) {
    document.getElementById('wml010ShainList').innerHTML = '';

    if (shainList) {
        var shainListHTML = '';
        var index;
        for (index = 0; index < shainList.length; index++) {
            shainListHTML += '<li><a href="javascript:setAdrListAddress(\'' + shainList[index].NAME + '\', \'' + shainList[index].MAIL + '\');">'
                                             + shainList[index].NAME + '<br><span class="tree_address">' + shainList[index].MAIL
                                             + '</span></a></li>';
            if ((index + 1) / 50 == 0) {
                document.getElementById('wml010ShainList').innerHTML += shainListHTML;
                shainListHTML = null;
            }
        }

        if (shainListHTML) {
            document.getElementById('wml010ShainList').innerHTML += shainListHTML;
            shainListHTML = null;
        }
    }

    var shainTree = new YAHOO.widget.TreeView("popular_shain");
    shainTree.draw();
}

function drawAddressList(addressList) {

    document.getElementById('wml010AddressList').innerHTML = '';

    if (addressList) {
        var addressListHTML = '';
        var index;
        for (index = 0; index < addressList.length; index++) {
            addressListHTML += '<li><a href="javascript:setAdrListAddress(\'' + addressList[index].NAME + '\', \'' + addressList[index].MAIL + '\');">'
                             + addressList[index].NAME + '<br><span class="tree_address">' + addressList[index].MAIL
                             + '</span></a></li>';
            if ((index + 1) / 50 == 0) {
                document.getElementById('wml010AddressList').innerHTML += addressListHTML;
                addressListHTML = null;
            }
        }

        if (addressListHTML) {
            document.getElementById('wml010AddressList').innerHTML += addressListHTML;
            addressListHTML = null;
        }
    }

    var tree = new YAHOO.widget.TreeView("popular_address");
    var root = tree.getRoot();
    tree.draw();
}

function drawDestList(destList) {

    document.getElementById('wml010DestList').innerHTML = '';

    if (destList) {
        var destListHTML = '';
        var index;
        for (index = 0; index < destList.length; index++) {
            destListHTML += '<li><a href="javascript:openDestlist(\'' + destList[index].SID + '\');">'
                             + destList[index].NAME + '</a></li>';
            if ((index + 1) / 50 == 0) {
                document.getElementById('wml010DestList').innerHTML += destListHTML;
                destListHTML = null;
            }
        }

        if (destListHTML) {
            document.getElementById('wml010DestList').innerHTML += destListHTML;
            destListHTML = null;
        }
    }

    var tree = new YAHOO.widget.TreeView("popular_destlist");
    var root = tree.getRoot();
    tree.draw();
}

function drawTree(treeData) {
    drawDirectoryTree(treeData);
    drawAddressTree(treeData);
}

function drawDirectoryTree(treeData) {
    var init = function() {

        var tree = new YAHOO.widget.TreeView("treediv");
        var root = tree.getRoot();

        //ディレクトリ
        var index;
        var directoryList = treeData.directory;
        var dirAreaIdList = new Array();
        for (index = 0; index < directoryList.length; index++) {
            var directory = treeData.directory[index];
            var dirId = 'directory' + index;
            var dirStyle = null;
            var dustLink = '';

            if (directory.TYPE == DIRTYPE_INBOX) {
                dirId = "directoryInbox";
//                dirStyle = "inbox selected";
                dirStyle = "inbox";
            } else if (directory.TYPE == DIRTYPE_SENT) {
                dirId = "directorySent";
                dirStyle = "sent";
            } else if (directory.TYPE == DIRTYPE_UNSENT) {
                dirId = "directoryUnSent";
                dirStyle = "unsent";
            } else if (directory.TYPE == DIRTYPE_DUST) {
                dirId = "directoryTrash";
                dirStyle = "trash";
                dustLink = '　[<span><a href=\"javascript:emptyTrash();\" class=\"emptyDustBox\">' + msglist.empty + '</a></span>]';
            }

            var dirAreaId = dirAreaIdStr + directory.ID;
            dirAreaIdList.push(dirAreaId);
            var dir = new YAHOO.widget.HTMLNode("<span class=\"htmlnodelabel\" id=\"" + dirAreaId + "\"><em></em><a href='#' onClick='selectDirectory(" + directory.ID + "," + directory.TYPE + ");' id=\"" + dirId + "\">" + directory.NAME + "</a></span>", root, false, true);
            if (dirStyle != null) {
                dir.contentStyle = dirStyle;
            }

            dir.setHtml(dir.html
                     + '<span id="dirNrCnt' + directory.ID
                     + '" class="notRead_count">');
            if (directory.NRCNT > 0) {
                dir.setHtml(dir.html + '&nbsp;(' + directory.NRCNT + ')');
            }
            dir.setHtml(dir.html + '</span>');

            if (directory.TYPE == DIRTYPE_DUST) {
                dir.setHtml(dir.html + dustLink);
            }
        }

        //ラベル
        var label = new YAHOO.widget.TextNode(msglist.label, root, true);
        var labelList = treeData.label;
        for (index = 0; index < labelList.length; index++) {
            var labelData = treeData.label[index];

            var label1 = new YAHOO.widget.HTMLNode("<span class=\"treelabel\"><em></em><a href='#' onClick='selectLabel(" + labelData.ID + ");'\">" + labelData.NAME + "</a></span>", label, false, true);
            label1.contentStyle = "notepad";


            label1.setHtml(label1.html
                     + '<span id="labelNrCnt' + treeData.label[index].ID
                     + '" class="notRead_count">');
            if (labelData.NRCNT > 0) {
                label1.setHtml(label1.html + '&nbsp;(' + labelData.NRCNT + ')');
            }
            label1.setHtml(label1.html + '</span>');
        }

        tree.draw();

        //コンテキストメニューを設定
        for (dirIdx = 0; dirIdx < dirAreaIdList.length; dirIdx++) {
            setDirContextMenu(dirAreaIdList[dirIdx]);
        }
    }

    YAHOO.util.Event.onDOMReady(init);
}

function drawAddressTree(treeData) {

    //ユーザ情報
    YAHOO.example.app.shainListData = treeData.shain;
    var initShain = function() {
        drawShainList(YAHOO.example.app.shainListData);
    }

    //アドレス帳情報
    YAHOO.example.app.addressListData = treeData.address;
    var initAddressList = function() {
        drawAddressList(YAHOO.example.app.addressListData);
    }

    //送信先リスト
    YAHOO.example.app.destListData = treeData.destlist;
    var initDestList = function() {
        drawDestList(YAHOO.example.app.destListData);
    }

    YAHOO.util.Event.onDOMReady(initShain);
    YAHOO.util.Event.onDOMReady(initAddressList);
    YAHOO.util.Event.onDOMReady(initDestList);
}

function redrawShainList() {
    var url = '../webmail/wml010.do';
    url += '?CMD=getShainTreeData';
    url += '&wmlViewAccount=' + getAccount();
    url += '&wml010shainGroup=' + getSelectBoxValue('wml010shainGroup');

    var treeData;
    // XMLデータ取得処理
    var request = YAHOO.util.Connect.asyncRequest('GET', url, {
      success: function(o) {

        var data = o.responseText;
        if (data != null && data.length > 0) {
          treeData = JSON.parse(data);
        }
        data = null;

        YAHOO.example.app.shainListData = treeData.shain;
        drawShainList(YAHOO.example.app.shainListData);
      },

      //取得失敗時の処理
      failure: function(o) {
        alert(msglist.failUserInfo);
      }
  });
}

function redrawAddressList() {
    var url = '../webmail/wml010.do';
    url += '?CMD=getAddressTreeData';
    url += '&wmlViewAccount=' + getAccount();
    url += '&wml010addressType=' + getRadioValue('wml010addressType');

    var treeData;
    // XMLデータ取得処理
    var request = YAHOO.util.Connect.asyncRequest('GET', url, {
      success: function(o) {

        var data = o.responseText;
        if (data != null && data.length > 0) {
          treeData = JSON.parse(data);
        }
        data = null;

        YAHOO.example.app.addressListData = treeData.address;
        searchAddressList(YAHOO.example.app.addressListData);
      },

      //取得失敗時の処理
      failure: function(o) {
        alert(msglist.failAddress);
      }
  });
}

function redrawDestList() {
    var url = '../webmail/wml010.do';
    url += '?CMD=getDestlistTreeData';
    url += '&wmlViewAccount=' + getAccount();

    var treeData;
    // XMLデータ取得処理
    var request = YAHOO.util.Connect.asyncRequest('GET', url, {
      success: function(o) {

        var data = o.responseText;
        if (data != null && data.length > 0) {
          treeData = JSON.parse(data);
        }
        data = null;

        YAHOO.example.app.destListData = treeData.destlist;
        drawDestList(YAHOO.example.app.destListData);
      },

      //取得失敗時の処理
      failure: function(o) {
        alert(msglist.failDestlist);
      }
  });
}

function searchAddressList() {
    var searchText = getParamValue('wml010searchTextAddressList');
    if (isNullZeroString(searchText)) {
        drawAddressList(YAHOO.example.app.addressListData);
    } else {
        searchText = trim(searchText);
        var searchAddressListData = new Array();
        for (i = 0; i < YAHOO.example.app.addressListData.length; i++) {
            if (YAHOO.example.app.addressListData[i].NAME.indexOf(searchText) >= 0) {
                searchAddressListData.push(YAHOO.example.app.addressListData[i]);
            }
        }
        drawAddressList(searchAddressListData);

        searchAddressListData = null;
    }
    searchText = null;
}

function searchAddressExe() {

    var searchText = getParamValue('wml010searchTextAddressList');

    if (isSpaceOnly(searchText)) {
        drawAddressList(YAHOO.example.app.addressListData);
        alert(msglist.cantOnlySpase);

    } else if (isNullZeroString(searchText)) {
        drawAddressList(YAHOO.example.app.addressListData);
        alert(msglist.enterKeyWord);

    } else if (isSpaceStart(searchText)) {
        drawAddressList(YAHOO.example.app.addressListData);
        alert(msglistcantStartSpace);

    } else if (searchText.length > 100) {
        drawAddressList(YAHOO.example.app.addressListData);
        alert(msglist.inputSearchWord);

    } else {
        searchAddressList();
    }
}

function setAdrListAddress(adrName, address) {
    if (address != null && address.length > 0) {
        var adrAddress;
        if (adrName != null && adrName.length > 0) {
            var nbsp = String.fromCharCode( 160 );
            adrName = adrName.replace( nbsp, " " );
//            adrName = adrName.split('\\').join('\\\\');
            adrName = adrName.split('"').join('\\"');
            adrAddress = '\"' + adrName + "\" <" + address + ">";
        } else {
            adrAddress = address;
        }

        if (!isOpenEditor()) {
            initEditor(YAHOO.example.app.dt, 0);
            YAHOO.util.Event.onContentReady('composeTo', function () {
                document.getElementById('composeTo').focus();
                document.getElementsByName('wml010sendAddressTo')[0].value = adrAddress;
            });
        } else if(wmlEditorFocusParamName == 'wml010sendAddressTo'
            || wmlEditorFocusParamName == 'wml010sendAddressCc'
            || wmlEditorFocusParamName == 'wml010sendAddressBcc')
        {
            var addressElement;
            addressElement = document.getElementsByName(wmlEditorFocusParamName)[0];
            if (addressElement.value != null && addressElement.value.length > 0) {
                addressElement.value = addressElement.value + ',' + adrAddress;
            } else {
                addressElement.value = adrAddress;
            }
        }
    }
}

function initTree() {
    var url = '../webmail/wml010.do';
    url += '?CMD=getTreeData';
    url += '&wmlViewAccount=' + getAccount();
    url += '&wml010shainGroup=' + getSelectBoxValue('wml010shainGroup');

    // XMLデータ取得処理
    var request = YAHOO.util.Connect.asyncRequest('GET', url, {
      success: function(o) {

        var data = o.responseText;
        if (data != null && data.length > 0) {

            var treeData = JSON.parse(data);
            data = null;

            drawTree(treeData);

            var treeMap = new Object();
            for (i = 0; i < treeData.directory.length; i++) {
                treeMap['dir' + treeData.directory[i].ID] = treeData.directory[i];
            }

            if (treeData.label) {
                for (i = 0; i < treeData.label.length; i++) {
                    treeMap['label' + treeData.label[i].ID] = treeData.label[i];
                }
            }

            YAHOO.example.app.treeMap = treeMap;
        }
      },

      //取得失敗時の処理
      failure: function(o) {
          alert(msglist.failedTree);
      }
  });
}

function redrawDirectoryTree(reloadType) {
    var url = '../webmail/wml010.do';
    url += '?CMD=getTreeData';
    url += '&wmlViewAccount=' + getAccount();
    url += '&wml010shainGroup=' + getSelectBoxValue('wml010shainGroup');

    // XMLデータ取得処理
    var request = YAHOO.util.Connect.asyncRequest('GET', url, {
      success: function(o) {
          if (reloadType == 1) {
              drawDirectoryTree(JSON.parse(o.responseText));
          } else {
              setDirectoryTreeData(o.responseText);
          }
      },

      //取得失敗時の処理
      failure: function(o) {
        alert(msglist.failedTree);
      }
  });
}

function selectDirectory(dirId, dirType) {
    setDirectory(dirId);
    setDirectoryType(dirType);
    setLabel(0);
    setInboxSelectPage(1);
    setInboxSort(0, 0);
    readMailList(1);

    if (getMailListType() == 1) {
        searchMailClose();
    } else {
        inboxMailClose();

        var headEl = YAHOO.example.app.dt.getColumnSet().flat[3].getThEl().children[0].children[0].children[0];
        if (isSendDir()) {
            headEl.innerHTML = msglist.address;
        } else {
            headEl.innerHTML = msglist.mailsender;
        }
    }
}

function selectLabel(labelId) {
    setDirectory(0);
    setLabel(labelId);
    setViewDelMail(1);
    setInboxSelectPage(1);
    setInboxSort(0, 0);
    readMailList(1);

    if (getMailListType() == 1) {
        searchMailClose();
    } else {
        inboxMailClose();
    }

    var headEl = YAHOO.example.app.dt.getColumnSet().flat[3].getThEl().children[0].children[0].children[0];
    headEl.innerHTML = msglist.mailsender;
}

function getEditorFocus() {
    return wmlEditorFocusParamName;
}

function setEditorFocus(paramName) {
    wmlEditorFocusParamName = paramName;
}

function changeDirNoRead(dirId, type) {
    var dirType = YAHOO.example.app.treeMap['dir' + dirId].TYPE;
    if ((dirType != 3 && dirType != 4)
    && YAHOO.example.app.treeMap['dir' + dirId].NRCNT >= 0) {
        var nrCnt = YAHOO.example.app.treeMap['dir' + dirId].NRCNT;
        if (type == 1) {
            nrCnt = nrCnt + 1;
        } else {
            if (nrCnt > 0) {
                nrCnt = nrCnt - 1;
            }
        }
        YAHOO.example.app.treeMap['dir' + dirId].NRCNT = nrCnt
        if (nrCnt == 0) {
            document.getElementById('dirNrCnt' + dirId).innerHTML = '';
        } else {
            document.getElementById('dirNrCnt' + dirId).innerHTML = '&nbsp;(' + nrCnt + ')';
        }
        nrCnt = null;
    }
    dirType = null;
}

function changeLabelNoRead(labelIdList, type) {

    if (labelIdList) {
        for (nrLblIdx = 0; nrLblIdx < labelIdList.length; nrLblIdx++) {
            var nrLabelId = labelIdList[nrLblIdx];
            var nrCnt = YAHOO.example.app.treeMap['label' + nrLabelId].NRCNT;
            if (type == 1) {
                nrCnt = nrCnt + 1;
            } else {
                if (nrCnt > 0) {
                    nrCnt = nrCnt - 1;
                }
            }

            YAHOO.example.app.treeMap['label' + nrLabelId].NRCNT = nrCnt;
            if (YAHOO.example.app.treeMap['label' + nrLabelId].NRCNT == 0) {
                document.getElementById('labelNrCnt' + nrLabelId).innerHTML = '';
            } else {
                document.getElementById('labelNrCnt' + nrLabelId).innerHTML = '&nbsp;(' + nrCnt + ')';
            }
            nrCnt = null;
        }
    }
}

function openDestlist(destlistId) {
    if (!isOpenEditor()) {
        initEditor(YAHOO.example.app.dt, 0);
        wmlEditorFocusParamName = 'wml010sendAddressTo';
    }

    openDestlistSubWindow(destlistId, wmlEditorFocusParamName);
}