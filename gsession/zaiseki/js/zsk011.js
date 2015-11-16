function parentReload(closeFlg){
    if (closeFlg == true) {
        var CMD = parent.document.getElementsByName('CMD');
        CMD[0].value = 'posisionSet';

        parentEnable();
        parent.document.forms[0].submit();
    }
}
function posclose(innerflg){

    clearScreenParent(innerflg);

    parent.YAHOO.subbox.subPanel.hide();
    parentEnable();
}
function parentReadOnly(){

    setAllReadOnly(parent.document, true);

    var childframe = parent.document.getElementsByName('childframe');
    if (childframe != null && childframe.length > 0) {

        for (i = 0; i < childframe.length; i++) {
            var childdoc = parent.document.getElementsByName(childframe[i].value);
            setAllReadOnly(parent[childframe[i].value].document, true);
        }
    }
}
function parentEnable(){
    setAllReadOnly(parent.document, false);

    var childframe = parent.document.getElementsByName('childframe');
    if (childframe != null && childframe.length > 0) {

        for (i = 0; i < childframe.length; i++) {
            var childdoc = parent.document.getElementsByName(childframe[i].value);
            setAllReadOnly(parent[childframe[i].value].document, false);
        }
    }
}

// 登録ボタンイベント
function checkStatus() {
    var paramStr = "";
    for(var i = 0; i < document.forms[0].elements.length; i++) {
        var key = document.forms[0].elements[i].name;  // パラメータキー
        var val = document.forms[0].elements[i].value; // パラメータ値

        // 要素がラジオボックスの場合→チェック項目のみ抽出
        if (document.forms[0].elements[i].type == "radio") {
            if (!document.forms[0].elements[i].checked) {
                continue;
            }
        }
        
        // form にあるパラメータを文字列として連結し取得
        if (key.length > 0 && val.length > 0) {
            if (paramStr.length > 0) paramStr += "&";
            paramStr += (key + "=" + val);
        }
    }
    
    if (paramStr.length > 0) {
        // データ更新通信
        $.ajax({
            async: false,
            url:"../zaiseki/zsk011.do",
            type: "post",
            data: paramStr
        }).done(function( data ) {
           // データ更新が完了した為、ウィンドウを閉じる
            window.opener.document.forms[0].CMD.value='reload';
            window.opener.document.forms[0].submit();
            window.close();
        }).fail(function(data){
            alert('error');
        });
    }
    return false;
}
