function clickMenuGs(menuUrl) {
    document.forms.cmn002Form.url.value = menuUrl;
    document.forms.cmn002Form.submit();
    return false;
}

//ターゲットはフレーム内（開かない）
function clickMenuTarFrame(pluginId, paramKbn, sendKbn) {

    //URL、パラメータ情報を取得する
    $.ajax({
        async: true,
        url:"../common/cmn003.do",
        type: "post",
        data: {
            CMD: "getClickUrl",
            pid: pluginId}
    }).done(function( data ) {
        if (data != null || data != "") {

            //POST形式の場合かつパラメータ設定するの場合
            if (sendKbn == 0 && paramKbn != 0) {

                var formName = 'fname' + pluginId;

                var form = $('<form/>', {action: data.url, method: 'post', target:'body', name:formName});

                if (data.paramList != null && data.paramList.length > 0) {
                    for (row=0; row<data.paramList.length; row++) {
                        var paramName = data.paramList[row].cppName;
                        var paramValue = data.paramList[row].cppValue;
                        form.append($('<input/>',{type:'hidden',name:paramName ,value:paramValue}));
                    }
                }


                //body内にformを追加(IE対応)
                $('body').append(form);
                //サブミット
                form.submit();
                //formの削除（jsp内の既存のformは削除されない）
                form.remove();


            } else {
                //パラメータセットしない場合、又は、パラメータセットする且つGET形式の場合
                //パラメータセットしない場合は現仕様通りGETで行う
                document.forms.cmn002Form.url.value = data.url;
                document.forms.cmn002Form.submit();
                return false;
            }
        }
    }).fail(function(data){
        //JSONデータ失敗時の処理
    });
}


//ターゲットはウィンドウ （開く）
function clickMenuTarWindow(pluginId, paramKbn, sendKbn) {

    //URL、パラメータ情報を取得する
    $.ajax({
        async: true,
        url:"../common/cmn003.do",
        type: "post",
        data: {
            CMD: "getClickUrl",
            pid: pluginId}
    }).done(function( data ) {
        if (data != null || data != "") {

            //POST形式の場合かつパラメータ設定するの場合
            if (sendKbn == 0 && paramKbn != 0) {

                var formName = 'fname' + pluginId;

                var form = $('<form/>', {action: data.url, method: 'post', target:pluginId, name:formName});

                if (data.paramList != null && data.paramList.length > 0) {
                    for (row=0; row<data.paramList.length; row++) {
                        var paramName = data.paramList[row].cppName;
                        var paramValue = data.paramList[row].cppValue;
                        form.append($('<input/>',{type:'hidden',name:paramName ,value:paramValue}));
                    }
                }


                //body内にformを追加(IE対応)
                $('body').append(form);

//                alert($('body').children('form').attr('name'));
//                $('form').each(function() {
//                $('body').children('form').each(function() {
//                    alert($(this).attr('name'));
//                });

                //サブミット
                form.submit();
                //formの削除（jsp内の既存のformは削除されない）
                form.remove();

//              $('form').each(function() {
//                  alert($(this).attr('name'));
//              });

            } else {
                //パラメータセットしない場合、又は、パラメータセットする且つGET形式の場合
                //パラメータセットしない場合は現仕様通りGETで行う
                window.open(data.url);
            }
        }
    }).fail(function(data){
        //JSONデータ失敗時の処理
    });
}

function clickMenu(menuUrl) {
  document.forms.cmn002Form.url.value = menuUrl;
  document.forms.cmn002Form.submit();
  return false;
}

function changePage(page) {
  parent.menu.location.href='../common/cmn003.do?menuPage=' + page;
}

$(function() {
  /* ユーザプラグイン オンマウス時 */
  $(".user_plugin_link").live("mouseenter", function(){
      //ユーザプラグイン オンマウス時href属性の更新を行う。(タイムスタンプ対策)
      var pid = $(this).attr("id");
      setHrefUrl(pid);
  });
});


function setHrefUrl(pid) {

  $.ajax({
      async: true,
      url:"../common/cmn003.do",
      type: "post",
      data: { CMD: "getHrefUrl", pid: pid }

  }).done(function( data ) {
      if (data != null || data != "") {
          $('#' + pid).attr("href", data.hrefUrl);
      }

  }).fail(function(data){
      //JSONデータ失敗時の処理
  });
}

