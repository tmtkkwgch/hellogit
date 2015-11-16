function sendLabelRequest(url, type, reqType) {
    var labelErrorMessage = msglist.faildedAddLabel;
    if (type == 1) {
        labelErrorMessage = msglist.faildedRmvLabel;
    }

    var reqTypeStr = 'GET';
    if (reqType && reqType == 1) {
        reqTypeStr = 'POST';
        YAHOO.util.Connect.setForm(document.getElementsByName('addLabelForm')[0]);
    }

    YAHOO.util.Connect.asyncRequest(reqTypeStr, url, {

        success: function(o) {
            var message = o.responseText;
            if (message != null) {
                var data = JSON.parse(message);
                if (data.message == null) {
                    alert(labelErrorMessage);
                } else if (data.message == 'success') {
                    if (type == 0) {
                        YAHOO.example.container.dialog1.cancel();
                    } else if (type == 1) {
                        YAHOO.example.container.dialog2.cancel();
                    }

                    if (getSelectTabId() == 'searchView') {
                        searchMailClose();
                    } else {
                        inboxMailClose();
                    }

                    if (reqType == 1) {
                        reloadMailListWithTree(1);
                        if (data.addLabelValue) {
                           document.getElementById('dialog_sel').options[document.getElementById('dialog_sel').options.length]
                              = new Option(data.addLabelValue.name, data.addLabelValue.id);
                        }
                    } else {
                        reloadMailList();
                    }
                } else {
                    alert(data.message);
                }
            } else {
                alert(labelErrorMessage);
            }
        },
        failure: function(o) {
            alert(labelErrorMessage);
        }
    });
}

function init() {

  if (YAHOO.example.container == null) {
      YAHOO.namespace("example.container");
  }

  var labelAddSubmit = function() {
    var recordData;
    if (getSelectTabId() == 'searchView') {
        recordData = getSelectRecordData(YAHOO.example.app.search.dt);
    } else {
        recordData = getSelectRecordData(YAHOO.example.app.dt);
    }

    if (recordData == null || recordData.length == 0) {
        alert(msglist.mailSelect);
    } else {
        var formData = this.getData();
        var labelType = getRadioValue('wml010addLabelType');
        var labelName = getParamValue('wml010addLabelName');

        if (labelType == 1) {
            if (labelName == null || labelName.length <= 0) {
                alert(msglist.enterLabel);
                return;
            } else if (labelName.length > 100) {
                alert(msglist.enterLabelPlease);
                return;
            }
        }

        var url = '../webmail/wml010.do';
        document.getElementById('addLabelParam').innerHTML = '<input type="hidden" name="CMD" value="addMessageLabel">';
        document.getElementById('addLabelParam').innerHTML += '<input type="hidden" name="wmlViewAccount" value="' + getAccount() + '">';

        for (i = 0; i < recordData.length; i++) {
            document.getElementById('addLabelParam').innerHTML += '<input type="hidden" name="wml010selectMessageNum" value="' + recordData[i].XID + '">';
        }

        recordData = null;
        sendLabelRequest(url, 0, 1);
    }
  };

  var labelDelSubmit = function() {
    var recordData;
    if (getSelectTabId() == 'searchView') {
        recordData = getSelectRecordData(YAHOO.example.app.search.dt);
    } else {
        recordData = getSelectRecordData(YAHOO.example.app.dt);
    }

    if (recordData == null || recordData.length == 0) {
        alert(msglist.mailSelect);
    } else {
        var formData = this.getData();
        var url = '../webmail/wml010.do';
        url += '?CMD=delMessageLabel';
        url += '&wmlViewAccount=' + getAccount();
        url += '&wml010delLabel=' + formData.wml010delLabel;
        for (i = 0; i < recordData.length; i++) {
            url += '&wml010selectMessageNum=' + recordData[i].XID;
        }
        recordData = null;

        sendLabelRequest(url, 1);
    }
  };

  //添付ファイルのアップロードを行う
  var fileUploadSubmit = function() {
    var fd;
    try { fd = new FormData(); } catch (fdE) {}

    if (fd) {
      if ($('#wml010sendMailFile')[0].files.length <= 0) {
        alert(msglist["fileSelect"]);
        return;
      }
      fd.append('CMD', 'sendFileUpload');
      fd.append('wmlViewAccount', getAccount());
      fd.append('wml010sendMailFile[0]',  $('#wml010sendMailFile')[0].files[0]);

      this.cancel();
      showLoadingDialog();

      var formUrl = '../webmail/wml010.do';
      $.ajax({
          url: formUrl,
          type: "POST",
          data: fd,
          processData: false,
          contentType: false,
          success: function(data) {
            try {
                var tempFileData = data;
                if (tempFileData != null) {
                    var data = JSON.parse(tempFileData);
                    if (data[0].error == 1) {
                        alert(data[0].errorMessage);
                        closeLoadingDialog();
                        YAHOO.example.container.dialog4.show();
                    } else {
                        setEditorTempFile(data[0]);
                        closeLoadingDialog();
                    }
                }

                tempFileData = null;
            } catch (ae) {
                closeLoadingDialog();
                alert(ae);
            }
          },
          error: function() {
              alert('Upload ERROR');
              closeLoadingDialog();
          }
      });
    } else {
        document.getElementById('sendMailTempFormCMD').value = "sendFileUpload";
        var formObject = document.getElementById('sendMailTempForm');
        YAHOO.util.Connect.setForm(formObject, true);

        this.cancel();
        showLoadingDialog();

        YAHOO.util.Connect.asyncRequest('post', '../webmail/wml010.do?wmlViewAccount=' + getAccount(), {
            upload : function(o) {
                var tempFileData = o.responseText;
                if (tempFileData != null) {
                    var data = JSON.parse(tempFileData);

                    if (data[0].error == 1) {
                        alert(data[0].errorMessage);
                        YAHOO.example.container.dialog4.show();
                        closeLoadingDialog();
                    } else {
                        setEditorTempFile(data[0]);
                        closeLoadingDialog();
                    }
                }

                tempFileData = null;
            },
            //取得失敗時の処理
            failure: function(o) {
                alert(msglist.failedAttach);
                closeLoadingDialog();
            }
        });
    }

    document.getElementById('wml010sendMailFile').value = '';
  };

  var moveMailSubmit = function() {
    var recordData;
    if (getSelectTabId() == 'searchView') {
        recordData = getSelectRecordData(YAHOO.example.app.search.dt);
    } else {
        recordData = getSelectRecordData(YAHOO.example.app.dt);
    }

    if (recordData == null || recordData.length == 0) {
        alert(msglist.mailSelect);
    } else {
        var formData = this.getData();
        var url = '../webmail/wml010.do';
        url += '?CMD=moveMessage';
        url += '&wmlViewAccount=' + getAccount();
        url += '&wml010moveFolder=' + getRadioValue('wml010moveFolder');
        for (i = 0; i < recordData.length; i++) {
            url += '&wml010selectMessageNum=' + recordData[i].XID;
        }

        recordData = null;

        YAHOO.util.Connect.asyncRequest('POST', url, {

            success: function(o) {
                var message = o.responseText;
                if (message != null) {
                    if (message == 'success') {
                        YAHOO.example.container.dialog5.cancel();
                        reloadMailListWithTree(1);
                    } else {
                        var data = JSON.parse(message);
                        if (data.message == null) {
                            alert(msglist.faildedMoveMail);
                        } else {
                            alert(data.message);
                        }
                    }
                } else {
                    alert(msglist.faildedMoveMail);
                }
            },
            failure: function(o) {
                alert(msglist.faildedMoveMail);
            }
        });
    }
  };

  var checkAddressSubmit = function() {
    var noCheckElement = '';
    if (getParamValue("wml010checkAddress") == 1) {
      if (checkSendElement("checkTo") == false) noCheckElement += "・" + msglist.address + "\n";
      if (checkSendElement("checkCc") == false) noCheckElement += "・CC\n";
      if (checkSendElement("checkBcc") == false) noCheckElement += "・BCC\n";
    }
    if (getParamValue("wml010checkFile") == 1) {
      if (checkSendElement("checkFile") == false) noCheckElement += "・" + msglist.attachFile + "\n";
    }

    if (noCheckElement.length > 0) {
      alert("以下の項目のチェックが完了していません。\r\n" + noCheckElement);
      return;
    }

    closeCheckAddressDialog();
    sendWebmail();
  };

  var handleCancel = function() {
    this.cancel();
  };
  var handleSuccess = function(o) {
//    var response = o.responseText;
//    response = response.split("<!")[0];
//    document.getElementById("resp").innerHTML = response;
  };
  var handleFailure = function(o) {
    alert("Submission failed: " + o.status);
  };

  //ラベル追加ダイアログ
  YAHOO.example.container.dialog1 = new YAHOO.widget.Dialog("dialog1",
              { width : "30em",
                fixedcenter : true,
                visible : false,
                constraintoviewport : false,
                modal: true,
                buttons : [
                  { text:msglist.add, handler:labelAddSubmit, isDefault:true },
                  { text:msglist.Cancellation, handler:handleCancel }
                ]
              });

  //ラベル削除ダイアログ
  YAHOO.example.container.dialog2 = new YAHOO.widget.Dialog("dialog2",
              { width : "30em",
                fixedcenter : true,
                visible : false,
                constraintoviewport : false,
                modal: true,
                buttons : [
                  { text:msglist.delet, handler:labelDelSubmit, isDefault:true },
                  { text:msglist.Cancellation, handler:handleCancel }
                ]
              });

  //添付ファイルダイアログ
  YAHOO.example.container.dialog4 = new YAHOO.widget.Dialog("dialog4",
              { width : "35em",
                fixedcenter : true,
                visible : false,
                constraintoviewport : false,
                modal: true,
                buttons : [
                  { text:msglist.attach, handler:fileUploadSubmit, isDefault:true },
                  { text:msglist.Cancellation, handler:handleCancel }
                ]
              });

  //メールの移動ダイアログ
  YAHOO.example.container.dialog5 = new YAHOO.widget.Dialog("dialog5",
              { width : "15em",
                fixedcenter : true,
                visible : false,
                constraintoviewport : false,
                modal: true,
                buttons : [
                  { text:msglist.mailMove, handler:moveMailSubmit, isDefault:true },
                  { text:msglist.Cancellation, handler:handleCancel }
                ]
              });

  //確認(メール情報)ダイアログ
  YAHOO.example.container.dialog6 = new YAHOO.widget.Dialog("dialog6",
              { width : "800px",
                fixedcenter : true,
                visible : false,
                constraintoviewport : false,
                modal: true,
                buttons : [
                  { text:msglist.send, handler:checkAddressSubmit, isDefault:true },
                  { text:msglist.Cancellation, handler:handleCancel }
                ]
              });

  //メールテンプレートダイアログ
  YAHOO.example.container.dialog7 = new YAHOO.widget.Dialog("dialog7",
          { width : "600px",
          fixedcenter : true,
          visible : false,
          constraintoviewport : false,
          modal: true,
          buttons : [
            { text:msglist.Cancellation, handler:handleCancel }
          ]
    });
  YAHOO.util.Dom.setStyle(YAHOO.example.container.dialog7.body, "height", "500px");

  //”共有"ダイアログ
  YAHOO.example.container.dialog8 = new YAHOO.widget.Dialog("dialog8",
          { width : "330px",
          fixedcenter : true,
          visible : false,
          constraintoviewport : false,
          modal: true,
          buttons : [
            { text:msglist.Cancellation, handler:handleCancel }
          ]
    });
  YAHOO.util.Dom.setStyle(YAHOO.example.container.dialog8.body, "height", "150px");

  //メール情報登録ダイアログ
  YAHOO.example.container.dialog9 = new YAHOO.widget.Dialog("dialog9",
              { width : "1000px",
                fixedcenter : true,
                visible : false,
                constraintoviewport : false,
                modal: true,
                buttons : []
              });

  //編集中メールが存在する状態での”送信メール確認"ダイアログ
  YAHOO.example.container.dialog10 = new YAHOO.widget.Dialog("dialog10",
          { width : "330px",
          fixedcenter : true,
          visible : false,
          constraintoviewport : false,
          modal: true,
          buttons : [
//             { text:msglist.Cancellation, handler:handleCancel }
               { text:"OK", handler:handleCancel }
          ]
    });
  YAHOO.util.Dom.setStyle(YAHOO.example.container.dialog8.body, "height", "150px");

  function disabledForm(event, dEml) {

    for (var i = 0; i < dEml.length; i++) {
      if(dEml[i].checked == true) {
        if(dEml[i].value == 0) {
          document.getElementById("dialog_sel").disabled = false;
          document.getElementById("dialog_new").disabled = true;
        } else if(dEml[i].value == 1){
          document.getElementById("dialog_sel").disabled = true;
          document.getElementById("dialog_new").disabled = false;
        }
      }
    }
  };

  YAHOO.example.container.dialog1.validate = function() {
  };

  // Wire up the success and failure handlers
  YAHOO.example.container.dialog1.callback = { success: handleSuccess,
                 failure: handleFailure };

  YAHOO.example.container.dialog1.render();
  YAHOO.example.container.dialog2.render();

  if (YAHOO.example.container.dialog3 == null) {
      initLoadingDialog();
  }

  YAHOO.example.container.dialog4.render();
  YAHOO.example.container.dialog5.render();

  YAHOO.example.container.dialog6.render();
  YAHOO.example.container.dialog7.render();
  YAHOO.example.container.dialog8.render();
  YAHOO.example.container.dialog9.render();
  YAHOO.example.container.dialog10.render();

  document.getElementById("dialog_new").disabled = true;

  YAHOO.util.Event.addListener("show", "click", YAHOO.example.container.dialog1.show, YAHOO.example.container.dialog1, true);
  var addLabelType = document.getElementsByName("wml010addLabelType");
  YAHOO.util.Event.addListener(addLabelType, "click", disabledForm, addLabelType);


  //Attachment settings
  attachmentSettings();
}

var uploadFiles;
function attachmentSettings() {
  if (window.File) {
    uploadFiles = function (files) {
        var fd = new FormData();
        fd.append('CMD', 'sendFileUpload');
        fd.append('wmlViewAccount', getAccount());

        for (var i = 0; i < files.length; i++) {
            fd.append('wml010sendMailFile[' + i + ']', files[i]);
        }

        YAHOO.example.container.dialog4.cancel();
        showLoadingDialog();

        var formUrl = '../webmail/wml010.do';
        $.ajax({
            url: formUrl,
            type: "POST",
            data: fd,
            processData: false,
            contentType: false,
            success: function(data) {
              try {
                  var tempFileData = data;
                  if (tempFileData != null) {
                      var data = JSON.parse(tempFileData);

                      var tempErrorCount = 0;
                      for (tempIdx = 0; tempIdx < data.length; tempIdx++) {
                          if (data[tempIdx].error == 1) {
                              alert(data[tempIdx].errorMessage);
                              tempErrorCount++;
                          } else {
                              setEditorTempFile(data[tempIdx]);
                          }
                      }

                      if (tempErrorCount > 0) {
                          YAHOO.example.container.dialog4.show();
                      }
                      closeLoadingDialog();
                  }

                  tempFileData = null;
              } catch (ae) {
                  closeLoadingDialog();
                  alert(ae);
              }
            },
            error: function() {
                alert('Upload ERROR');
                closeLoadingDialog();
            }
        });
    };

    var dropbox;
    dropbox = document.getElementById("uploadArea");
    dropbox.addEventListener("dragenter", uploadDragenter, false);
    dropbox.addEventListener("dragover", uploadDragover, false);
    dropbox.addEventListener("drop", uploadDrop, false);
  } else {
    $('#wml010DragArea').hide();
  }
}

function uploadDragenter(e) {
  e.stopPropagation();
  e.preventDefault();
}

function uploadDragover(e) {
  e.stopPropagation();
  e.preventDefault();
}

function uploadDrop(e) {
  e.stopPropagation();
  e.preventDefault();

  var files = e.dataTransfer.files;
  uploadFiles(files);
}

function checkSendElement(checkName) {
    var checkElList = null;
    checkElList = document.getElementById('wml010SendConfirmArea');
    if (checkElList.contentWindow != null && checkElList.contentWindow.document != null) {
        checkElList = checkElList.contentWindow.document.getElementsByName(checkName);
    } else {
        checkElList = checkElList.document.getElementsByName(checkName);
    }

    for (checkIdx = 0; checkIdx < checkElList.length; checkIdx++) {
      if (checkElList[checkIdx].checked == false) {
        return false;
      }
    }
    return true;
}

YAHOO.util.Event.onDOMReady(init);
