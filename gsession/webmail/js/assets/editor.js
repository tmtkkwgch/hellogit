var smailInit = false;
var smailType = true;

function getWidth() {
    var width = getBasicWidth() - 240;
    if (width < 580) {
        width = 580;
    }
    return width;
}

function setSmailType(type) {
    smailType = type;
}

function setSmailInit(init) {
    smailInit = init;
}

function switchSendMailContent() {
    var bodyLimit = document.getElementById('wmlBodyLimit').value;
    if (smailType) {

        tinyMCE.execCommand('mceRemoveControl', false, 'wml010sendContent');
        var textBody = YAHOO.example.app.editor.value;
        textBody = textBody.replace(/<br \/>/gi, '\n');
        textBody = textBody.replace(/<\S[^><]*>/g, '');
        YAHOO.example.app.editor.value = htmlDecode(textBody);

        if (bodyLimit > 0) {
            document.getElementById('wmlBodySizeArea').style.display='block';
            if (textBody) {
                showLengthStr(textBody, bodyLimit, 'inputlength');
            } else {
                document.getElementById('inputlength').innerHTML = '0';
            }
        }

        if (YAHOO.example.EditorData.sendFormatChange == 1) {
            document.getElementById('wml010sendMailFormatType').innerHTML = msglist.htmlMail;
        }

        smailType = false;
    } else {
        var textBody = YAHOO.example.app.editor.value;
        YAHOO.example.app.editor.value = htmlEncode(textBody);
        if (smailInit) {
            tinyMceInit('wml010sendContent');
        } else {
            tinyMCE.execCommand('mceAddControl', false, 'wml010sendContent');
        }

        if (bodyLimit > 0) {
            document.getElementById('wmlBodySizeArea').style.display='none';
        }

        if (YAHOO.example.EditorData.sendFormatChange == 1) {
            document.getElementById('wml010sendMailFormatType').innerHTML = msglist.textFormat;
        }

        smailType = true;
    }
}

function setEditorTempFile(fileData) {
    if (!document.getElementById('composeTempFile').innerHTML) {
        if (YAHOO.example.EditorData.compressFileType == 1) {
          var compressCheck = '';
          if (YAHOO.example.EditorData.compressFilePlan != 0) {
              if (YAHOO.example.EditorData.compressFilePlan == 2) {
                  compressCheck = ' checked';
              }
          } else if (YAHOO.example.EditorData.compressFileDef == 2) { compressCheck = ' checked'; }
          document.getElementById('composeTempFile').innerHTML =
              '&nbsp;&nbsp;<input type=\"checkbox\" id=\"wmlsendTempfileCompress\" name=\"wml010sendTempfileCompress\" value=\"1\"' + compressCheck + '>';
          document.getElementById('composeTempFile').innerHTML +=
              '&nbsp;<label for=\"wmlsendTempfileCompress\">' + msglist["wmlAutoCompress"] + '</label><br>';
        }
    }
    document.getElementById('composeTempFile').innerHTML =
        document.getElementById('composeTempFile').innerHTML
        + '<span id=\"sendFile_' + fileData.saveFileName + '\">'
        + '&nbsp;&nbsp;<span class=\"tempfile\">'
        + '<a href=\"../webmail/wml010.do?CMD=sendFileDownload'
        + '&wmlViewAccount=' + getAccount()
        + '&wml010sendMailDownloadFile='
        + fileData.saveFileName
        + '\" name=\"sendFile_Links\">'
        + fileData.fileName + '&nbsp(' + fileData.fileSize + ')'
        + '</a>&nbsp[<a href="#" onClick=\"sendMailFileDelete(\'' + fileData.saveFileName
        + '\');\">' + msglist.delet + '</a>]</span><br></span>';

        var confirmId = 'sendFile_confirm_' + fileData.saveFileName;
        var confirmName = confirmId + '_check';
        var checkFileRow = '<span id=\"' + confirmId + '\">';
        if (getParamValue('wml010checkFile') == 1) {
            checkFileRow +=  '<input type=\"checkbox\" name=\"sendFile_confirm_check\" id=\"' + confirmName + '\">&nbsp;';
        }
        checkFileRow +=  '&nbsp;<label for=\"' + confirmName + '\" class=\"tempfile\">'
        + fileData.fileName + '&nbsp(' + fileData.fileSize + ')'
        + '</label><br></span>';

        $("#checkFileArea").append(checkFileRow);
}

(function() {
    var Dom = YAHOO.util.Dom;
    var editorHtml = '<div id=\"composeBarWrap\">'
        + '  <div id=\"composeBar\"></div>'
        + '  <div id=\"composeAddr\" align=\"left\">'
        + '    <table width="100%">'
        + '    <tr>'
        + '    <td align="center" nowrap>' + msglist.sender + '</td>'
        + '    <td align="left" width="90%">'
        + '      <select name="wml010sendAccount" style="width: 90%" onchange="changeSendAccount();">';

        for (saIndex = 0; saIndex < YAHOO.example.EditorData.sendAccount.length; saIndex++) {
            editorHtml += '        <option value="' + YAHOO.example.EditorData.sendAccount[saIndex].ID + '"';
            if (getAccount() == YAHOO.example.EditorData.sendAccount[saIndex].ID) {
                editorHtml += ' selected';
            }
            editorHtml += '>' + YAHOO.example.EditorData.sendAccount[saIndex].NAME + '</option>';
        }

        editorHtml += '      </select>'
        + '    </td>'
        + '    </tr>';

        editorHtml = setEditorAddrHtml(editorHtml, 0);
        editorHtml = setEditorAddrHtml(editorHtml, 1);
        editorHtml = setEditorAddrHtml(editorHtml, 2);

        editorHtml +=  '  </table>'
        + '  </div>'
        + '  <br>'
        + '  <div id=\"composeSubject\">'
        + '    <input type=\"hidden\" name=\"wml010sendMailHtml\">'
        + '    <span><label>' + msglist.mailSubject + ' </label><input type=\"text\" name=\"wml010sendSubject\" value=\"' + YAHOO.example.EditorData.subject + '\" onFocus=\"setEditorFocus(\'\');\">';
        editorHtml += '    </span>'
        + '  </div>';

        editorHtml +=  '  <div id=\"composeSendSign\">'
        + '    <span><label>' + msglist.mailSign + ' </label><select name="wml010sendSign" style="width: 435px!important" onchange="changeSendSign();" id=\"sendSignCombo\">'
        + createSignCombo(YAHOO.example.EditorData.sendSign)
        + '    </select>&nbsp;&nbsp<input id=\"sendSignSetting\" type=\"button\" value=\"' + msglist["setting"] +'\" onclick=\"changeSendSign();\" style=\"width: 70px\"></span>'
        + '  </div>';

        if (YAHOO.example.EditorData.mailTemplate.length > 0) {
            setTemplateList(YAHOO.example.EditorData.mailTemplate);
        }

//予約送信
        editorHtml += '  <div id=\"composeSendDate\" style=\"padding: 2px; margin: 5px;\">';
        editorHtml += '<input type=\"checkbox\" id=\"sendPlanDateKbnCheck\" name=\"sendMailPlanType\" value=\"1\"';
        if (YAHOO.example.EditorData.timeSent == "true") {editorHtml += ' checked'};
        editorHtml += ' onclick=\"viewSendPlanDate();\">';
        editorHtml += '&nbsp;<label for=\"sendPlanDateKbnCheck\">' + msglist.wmlSendPlan + '</label>';
        editorHtml += '<input type=\"hidden\" id=\"sendPlanDateKbn\" name=\"sendMailPlanType\" value=\"0\">';

        editorHtml += '<font id=\"sendPlanImmArea\" style="white-space: nowrap;">';
        if (YAHOO.example.EditorData.timeSentType == 1) {
            editorHtml += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"radio\" id=\"sendPlanImm2\" name=\"wml010sendMailPlanImm\" value=\"0\"';
            if (YAHOO.example.EditorData.timeSentDef == 2) {editorHtml += ' checked'};
            editorHtml += ' >';
            editorHtml += '&nbsp;<label for=\"sendPlanImm2\">' + msglist["wmlSendType2"] + '</label>';

            editorHtml += '&nbsp;&nbsp;<input type=\"radio\" id=\"sendPlanImm1\" name=\"wml010sendMailPlanImm\" value=\"1\"';
            if (YAHOO.example.EditorData.timeSentDef == 1) {editorHtml += ' checked'};
            editorHtml += ' >';
            editorHtml += '&nbsp;<label for=\"sendPlanImm1\">' + msglist["wmlSendType1"] + '</label>';

        }
        editorHtml += '</font>';

        var thisYear = (new Date()).getFullYear();
        editorHtml += '<font id=\"sendPlanDateValueArea\">';
        editorHtml = setSendplanCombo(editorHtml, "wml010sendMailPlanDateYear", msglist.year, thisYear, thisYear + 1, 1, YAHOO.example.EditorData.timeSentYear);
        editorHtml = setSendplanCombo(editorHtml, "wml010sendMailPlanDateMonth", msglist.month, 1, 12, 1, YAHOO.example.EditorData.timeSentMonth);
        editorHtml = setSendplanCombo(editorHtml, "wml010sendMailPlanDateDay", msglist.day, 1, 31, 1,YAHOO.example.EditorData.timeSentDay);
        editorHtml = setSendplanCombo(editorHtml, "wml010sendMailPlanDateHour", msglist.hour, 0, 23, 1, YAHOO.example.EditorData.timeSentHour);
        editorHtml = setSendplanCombo(editorHtml, "wml010sendMailPlanDateMinute", msglist.minute, 0, 55, 5, YAHOO.example.EditorData.timeSentMinute);
        editorHtml += '</font>';

        editorHtml += '    </span>'
        + '  </div>';

        editorHtml += '<div id=\"sendMailFormat\" style=\"text-align:right; padding: 0px 4px 4px 0px\">'
        if (YAHOO.example.EditorData.sendFormatChange == 1) {
            if (YAHOO.example.EditorData.sendFormat == 0) {
                editorHtml += '&nbsp;<a id="wml010sendMailFormatType" href="#" onClick="switchSendMailContent();">' + msglist.htmlMail + '</a>';
            } else {
                editorHtml += '&nbsp;<a id="wml010sendMailFormatType" href="#" onClick="switchSendMailContent();">' + msglist.textFormat + '</a>';
            }
        }
        editorHtml += '  </div>';
        editorHtml += '<div id=\"composeTempFile\"></div>'
        + '</div>';

        editorHtml += '<div style=\"height: 500px;\">'
        + '  <text'+'area id=\"wml010sendContent\" name="wml010sendContent" rows=\"15\" cols="100" style="height: 480px; width:100%;" onFocus=\"setEditorFocus(\'\');\"';
        var bodyLimit = document.getElementById('wmlBodyLimit').value;
        if (bodyLimit > 0) {
            editorHtml += ' onkeyup="showLengthId(\'#wml010sendContent\', ' + bodyLimit + ', \'inputlength\');"';
        }
        editorHtml += '>' + YAHOO.example.EditorData.content + '</text'+'area>';

        if (bodyLimit > 0) {
            editorHtml += '<div id="wmlBodySizeArea" style=\"width: 400px;height: 20px!important;background-color: #eeeeee; font-size: 110%;font-weight:bold\">'
                       + '&nbsp;&nbsp;<span class="font_string_count">' + msglist.numberWord + ':</span>'
                       + '<span id=\"inputlength\" class=\"font_string_count\">';
            if (YAHOO.example.EditorData.content) {
                editorHtml += YAHOO.example.EditorData.content.length;
            } else {
                editorHtml += '0';
            }

            editorHtml += '</span>&nbsp;/&nbsp;'
                       + '<span class=\"font_string_count_max\">'
                       + document.getElementById('wmlBodyLimit').value
                       + '&nbsp;' + msglist.character + '</span>'
                       + '</div>';
        }

        editorHtml += '</div>';

    Dom.get('newMail').innerHTML = editorHtml;
    if (YAHOO.example.EditorData.fileList.length > 0) {
        for (fileIdx = 0; fileIdx < YAHOO.example.EditorData.fileList.length; fileIdx++) {
            setEditorTempFile(YAHOO.example.EditorData.fileList[fileIdx]);
        }
    }
    YAHOO.example.EditorData.compressFilePlan = 0;

    $('#composeSendSign').show();
    $('#sendSignSetting').hide();
    if (YAHOO.example.EditorData.sendSign.length == 0) {
        $('#composeSendSign').hide();
    } else if (YAHOO.example.EditorData.sendSign.length == 1) {
        $('#sendSignSetting').show();
    }

    viewSendPlanDate();
    smailType = (YAHOO.example.EditorData.sendFormat == 2);

    var loader = new YAHOO.util.YUILoader({
        base: '../common/js/yui/',
        require: ['autocomplete', 'editor'],
        ignore: ['containercore'],
        onSuccess: function() {
            YAHOO.example.app.composeToolbar = new YAHOO.widget.Toolbar('composeBar', {
                buttons: [
                    { id: 'tb_send', type: 'push', label: msglist.send, value: 'send'},
                    { type:'separator'},
                    { id: 'tb_temp', type: 'push', label: msglist.attach, value: 'attach' },
                    { id: 'tb_soukou', type: 'push', label: msglist.saveDraft, value: 'savedraft' },
                    { id: 'tb_template', type: 'push', label: msglist.mailTemplate, value: 'mailtemplate' }
                ]
            });

            YAHOO.example.app.composeToolbar.on('buttonClick', function(ev) {
                if ((ev.button.id == 'tb_send')) {
                    if (smailType) {
                        setParamValue('wml010svSendContent', tinyMCE.get('wml010sendContent').getContent());
                    } else {
                        setParamValue('wml010svSendContent', getParamValue('wml010sendContent'));
                    }

                    setParamValue('CMD', 'sendMail');
                    if (smailType) {
                        setParamValue('wml010sendMailHtml', '1');
                    } else {
                        setParamValue('wml010sendMailHtml', '0');
                    }

                    //宛先の確認 or 添付ファイルの確認 == 確認する の場合、確認画面を表示する
                    if (getParamValue("wml010checkAddress") == 1
                    || getParamValue("wml010checkFile") == 1) {
                        showSendmailConfirmDialog();
                    } else {
                        sendWebmail();
                    }

                } else if ((ev.button.id == 'tb_temp')) {
                    var strUA = navigator.userAgent;
                    if (strUA.indexOf("GsMobileApps") != -1) {
                        //GSモバイルアシスト用ファイルアップデート機能
                        document.getElementById('sendMailTempFormCMD').value = "sendFileUpload";
                        var formObject = document.getElementById('sendMailTempForm');

                        var formValue = '?';
                        for(var i=0; i < formObject.elements.length; i++) {
                            var elm = formObject.elements[i];
                            if(formValue !== '?')formValue=formValue+'&';
                            formValue = formValue+elm.name+'='+elm.value;
                        }
                        formValue = formValue+'&wmlViewAccount=' + getAccount()

                        var iframe = document.createElement('IFRAME');

                        iframe.setAttribute('src', 'gscmd://wmltempfileupload/'+formValue);
                        document.documentElement.appendChild(iframe);
                        iframe.parentNode.removeChild(iframe);
                        iframe = null;

                    } else {
                        YAHOO.example.container.dialog4.show();
                    }
                } else if ((ev.button.id == 'tb_soukou')) {
                    if (smailType) {
                        setParamValue('wml010svSendContent', tinyMCE.get('wml010sendContent').getContent());
                    } else {
                        setParamValue('wml010svSendContent', getParamValue('wml010sendContent'));
                    }

                    setParamValue('CMD', 'draftMail');
                    sendWebmail();
                } else if ((ev.button.id == 'tb_template')) {
                    YAHOO.example.container.dialog7.show();
                }
            });

            setSmailInit(true);
            setSmailType(smailType);
            YAHOO.example.app.editor = document.getElementById('wml010sendContent');
            if (smailType) {
                tinyMceInit('wml010sendContent');
                changeMailBody(YAHOO.example.EditorData.content);
            }

            YAHOO.example.app.destroyEditor = function() {
                YAHOO.example.app.editor = null;
                YAHOO.example.EditorData = null;
            };

            var data = new Array();

            if (YAHOO.example.app.shainListData) {
                for (index = 0; index < YAHOO.example.app.shainListData.length; index++) {
                    data.push(YAHOO.example.app.shainListData[index].MAIL);
                }
            }

            if (YAHOO.example.app.addressListData) {
                for (index = 0; index < YAHOO.example.app.addressListData.length; index++) {
                    data.push(YAHOO.example.app.addressListData[index].MAIL);
                }
            }

            // Instantiate JS Array DataSource
            var oACDS2 = new YAHOO.widget.DS_JSArray(data);
            var oAutoComp = new YAHOO.widget.AutoComplete('composeTo','autoTo', oACDS2);
            oAutoComp.typeAhead = true;
            oAutoComp.useIFrame = true;
            oAutoComp.delimChar = ",";
            oAutoComp.queryDelay = 1;
            oAutoComp.minQueryLength = 2;

            var oAutoCompCC = new YAHOO.widget.AutoComplete('composeCC','autoCC', oACDS2);
            oAutoCompCC.typeAhead = true;
            oAutoCompCC.useIFrame = true;
            oAutoCompCC.delimChar = ",";
            oAutoCompCC.queryDelay = 1;
            oAutoCompCC.minQueryLength = 2;

            var oAutoCompBCC = new YAHOO.widget.AutoComplete('composeBCC','autoBCC', oACDS2);
            oAutoCompBCC.typeAhead = true;
            oAutoCompBCC.useIFrame = true;
            oAutoCompBCC.delimChar = ",";
            oAutoCompBCC.queryDelay = 1;
            oAutoCompBCC.minQueryLength = 2;
        }
    });

    loader.insert({}, 'js');
})();

function setEditorAddrHtml(html, type) {
    var title;
    var txtName;
    var completeName;
    var composeName;
    var autoName;
    var editorAddr;

    if (type == 0) {
        title = msglist.address;
        txtName = 'wml010sendAddressTo';
        completeName = 'autoCompleteTo';
        composeName = 'composeTo';
        autoName = 'autoTo';
        editorAddr = YAHOO.example.EditorData.to;
    } else if (type == 1) {
        title = 'CC';
        txtName = 'wml010sendAddressCc';
        completeName = 'autoCompleteCC';
        composeName = 'composeCC';
        autoName = 'autoCC';
        editorAddr = YAHOO.example.EditorData.cc;
    } else if (type == 2) {
        title = 'BCC';
        txtName = 'wml010sendAddressBcc';
        completeName = 'autoCompleteBCC';
        composeName = 'composeBCC';
        autoName = 'autoBCC';
        editorAddr = YAHOO.example.EditorData.bcc;
    }

    html += '<tr><td nowrap>';

    if (getParamValue('wml010pluginAddressUse') == 0) {
        html += '<a href=\"#\" onClick=\"openAddress(\''
             + txtName
             + '\');\"><img src=\"../webmail/images/to_address_icon.gif\" align="left"></a>';
    }
    if (getParamValue('wml010pluginUserUse') == 0) {
        html += '<a href=\"#\" onClick=\"openSyain(\''
             + txtName
             + '\');\"><img src=\"../webmail/images/to_syain_icon.gif\" align="left"></a>';
    }
    if (getParamValue('wml010pluginAddressUse') == 0 || getParamValue('wml010pluginUserUse') == 0) {
        html += '<a href=\"#\" onClick=\"openDestlistDialog(\''
            + txtName
            + '\');\"><img src=\"../webmail/images/to_destlist_icon.gif\" align="left"></a>';
    }

    html += title
         + ':</td><td align="left" width="90%"><span>'
         + '<div id=\"' + completeName
         + '\"><input type=\"text\" id=\"' + composeName
         + '\" name="' + txtName
         + '" style="width: 90%" value=\"'+ editorAddr
         + '\" onFocus=\"setEditorFocus(\'' + txtName + '\');\">'
         + '<div id=\"' + autoName + '\"></div>'
         + '</div></span></td></tr>';

    return html;
}

function editorResize() {
};

function sendWebmail() {
    if (getParamValue('CMD') == 'sendMail'
    && trim(getParamValue('wml010sendSubject')).length <= 0) {
        if (confirm(msglist.subjectEntered) == false) {
            return;
        }
    }

    showLoadingDialog();
    var formObject = document.getElementById('webmailForm');
    YAHOO.util.Connect.setForm(formObject);

    var request = YAHOO.util.Connect.asyncRequest('POST', '../webmail/wml010.do', {
        success: function(o) {
            try {
              var mailData = o.responseText;
              if (mailData != null) {
                  var data = JSON.parse(mailData);
                  if (data.error == 1) {
                      setErrorMessge(data.errorMessage);
                      closeLoadingDialog();
                      return;
                  }

                  clearErrorMessge();

                  YAHOO.example.app.tabView.set('activeTab', YAHOO.example.app.tabView.get('tabs')[0]);
                  YAHOO.example.app.destroyEditor();
                  YAHOO.example.app.tabView.removeTab(getTab('composeView'));

                  if (data.sendToDraft == true) {
//                      reloadMailList();
//                      redrawDirectoryTree(1);
                      if (!data.beforeMail) {
                          inboxMailClose();
                          if (YAHOO.example.app.search) {
                              searchMailClose();
                          }
                          reloadMailList();
                      } else {
                          redrawDirectoryTree(1);
                      }
                  }
              }
            } catch (e) {
                clearErrorMessge();
                closeLoadingDialog();
                if (getParamValue('CMD') == 'draftMail') {
                    setErrorMessge([msglist.failedSave]);
                } else {
                    setErrorMessge([msglist.failedSend]);
                }
            }

            closeLoadingDialog();
        },

        //取得失敗時の処理
        failure: function(o) {
            closeLoadingDialog();
        }
    });
}

function showSendmailConfirmDialog() {
    if (trim(getParamValue('wml010sendSubject')).length <= 0) {
            if (confirm(msglist.subjectEntered) == false) {
                return;
            }
    }
    showLoadingDialog();

    var formObject = document.getElementById('webmailForm');
    YAHOO.util.Connect.setForm(formObject);

    var request = YAHOO.util.Connect.asyncRequest('POST', '../webmail/wml012.do?wml012CMD=saveMail', {
        success: function(o) {
            try {
              var mailData = o.responseText;
              if (mailData != null) {
                  var data = JSON.parse(mailData);
                  if (data.error == 1) {
                      setErrorMessge(data.errorMessage);
                      closeLoadingDialog();
                      return;
                  }

                  clearErrorMessge();
                  setConfirmHeight();
                  window.document.getElementById("wml010SendConfirmArea").src = '../webmail/wml012.do?reload=' + (+new Date());
                  window.document.getElementById("wml010SendConfirmArea").src
                    = window.document.getElementById("wml010SendConfirmArea").src;
                  YAHOO.example.container.dialog6.show();
              }
            } catch (e) {
                clearErrorMessge();
                closeLoadingDialog();
                setErrorMessge([msglist.failedSend]);
            }

            closeLoadingDialog();
        },

        //取得失敗時の処理
        failure: function(o) {
            closeLoadingDialog();
        }
    });
}

function changeSendAccount() {
    showLoadingDialog();

    var formObject = document.getElementById('webmailForm');
    YAHOO.util.Connect.setForm(formObject);

    var request = YAHOO.util.Connect.asyncRequest('POST', '../webmail/wml010.do?CMD=changeSendAccount', {
        success: function(o) {
            try {
              var mailData = o.responseText;
              if (mailData != null) {
                var data = JSON.parse(mailData);
                document.getElementById('sendSignCombo').innerHTML = createSignCombo(data.sign);
                setTemplateList(data.template);
              }
            } catch (e) {
                clearErrorMessge();
                closeLoadingDialog();
            }

            closeLoadingDialog();
        },

        //取得失敗時の処理
        failure: function(o) {
            closeLoadingDialog();
        }
    });
}

function changeSendSign() {
    showLoadingDialog();

    setSendBody();
    if (smailType) {
        setParamValue('wml010sendMailHtml', '1');
    } else {
        setParamValue('wml010sendMailHtml', '0');
    }

    var formObject = document.getElementById('webmailForm');
    YAHOO.util.Connect.setForm(formObject);

    var request = YAHOO.util.Connect.asyncRequest('POST', '../webmail/wml010.do?CMD=changeSendSign', {
        success: function(o) {
            try {
              var mailData = o.responseText;
              if (mailData != null) {
                  var data = JSON.parse(mailData);
                  changeMailBody(decodeURIComponent(data.sendBody));
                  setParamValue('wml010sendSignOld', getSelectBoxValue('wml010sendSign'));
              }
            } catch (e) {
                clearErrorMessge();
                closeLoadingDialog();
            }

            closeLoadingDialog();
        },

        //取得失敗時の処理
        failure: function(o) {
            closeLoadingDialog();
        }
    });
}

function setSendplanCombo(editorHtml, paramName, elName, startValue, endValue, incValue, selectValue) {
    editorHtml += ' &nbsp;<select name=\"' + paramName + '\">';

    for (opValue = startValue; opValue <= endValue; opValue += incValue) {
        editorHtml += '<option value=\"' + opValue + '\"';
        if (opValue == selectValue) {
            editorHtml += " selected";
        }
        editorHtml += '>' + opValue + '</option>';
    }
    editorHtml += '</select>&nbsp;' + elName;
    return editorHtml;
}

function createSignCombo(signData) {
    var defSign = 0, comboHtml = '';

    $('#sendSignSetting').hide();
    if (signData.length <= 0) {
      $('#composeSendSign').hide();
    } else {
      $('#composeSendSign').show();
      if (signData.length == 1) {
        $('#sendSignSetting').show();
      }
    }

    for (saIndex = 0; saIndex < signData.length; saIndex++) {
        comboHtml += '        <option value="' + signData[saIndex].ID + '"';
        if (signData[saIndex].DEF == 1) {
            comboHtml += ' selected';
            defSign = signData[saIndex].ID;
        }
        comboHtml += '>' + signData[saIndex].NAME + '</option>';
    }
    setParamValue('wml010sendSignOld', defSign);
    return comboHtml;
}

function setMailTemplate(templateId) {
    showLoadingDialog();

    setSendBody();
    var formObject = document.getElementById('webmailForm');
    YAHOO.util.Connect.setForm(formObject);

    var templateUrl = '../webmail/wml010.do?CMD=setMailTemplate&wml010sendTemplate=' + templateId;
    var request = YAHOO.util.Connect.asyncRequest('POST', templateUrl, {
        success: function(o) {
            try {
              var mailData = o.responseText;
              if (mailData != null) {
                  var data = JSON.parse(mailData);
                  setParamValue('wml010sendSubject', decodeURIComponent(data.subject));
                  changeMailBody(decodeURIComponent(data.content));
                  var sendTempFile = data.fileList;
                  if (sendTempFile.length > 0) {
                      for (tempIdx = 0; tempIdx < sendTempFile.length; tempIdx++) {
                          setEditorTempFile(sendTempFile[tempIdx]);
                      }
                  }
              }
            } catch (e) {
                clearErrorMessge();
                closeLoadingDialog();
                YAHOO.example.container.dialog7.cancel();
                setErrorMessge([failedMailTemplate]);
            }

            closeLoadingDialog();
            YAHOO.example.container.dialog7.cancel();
        },

        //取得失敗時の処理
        failure: function(o) {
            closeLoadingDialog();
            YAHOO.example.container.dialog7.cancel();
            setErrorMessge([failedMailTemplate]);
        }
    });
}

function setTemplateList(templateData) {
    var listHtml = '';
    if (templateData.length <= 1) {
      $('#composeSendTemplate').hide();
    } else {
      $('#composeSendTemplate').show();
    }

    $('#sendTemplateList tbody').empty();
    for (tpIndex = 0; tpIndex < templateData.length; tpIndex++) {
        listHtml = '        <tr><td class="mailList_data' + (tpIndex % 2 + 1) + '">'
        + '<a href="#" onclick="setMailTemplate(' + templateData[tpIndex].ID + ');" class="template_sel_txt">'
        + templateData[tpIndex].NAME;

        listHtml += '<span class="tooltips display_none">'
            + '件名:' + templateData[tpIndex].TITLE + "<br>"
            + '内容:<br><br>' + templateData[tpIndex].BODY
            + '</span>';
        listHtml += '</a></td></tr>';
        $('#sendTemplateList tbody').append(listHtml);
    }

    $(".template_sel_txt").mouseover(function(e){

        var txtVal = $(this).children("span.tooltips").html();
        txtVal = txtVal.replace(/\r\n/g, "<br />");
        txtVal = txtVal.replace(/(\n|\r)/g, "<br />");

         $("#tooltip_area").append("<div id=\"ttp\">"+ (txtVal) +"</div>");
         $("#ttp")
          .css("position","absolute")
          .css("top",(e.pageY) + -90 + "px")
          .css("left",80 + "px")
          .removeClass('display_none')
          .css("filter","alpha(opacity=85)")
          .fadeIn("fast");
//    }).mousemove(function(e){
//         $("#ttp")
//         .css("top",(e.pageY) + -90 + "px")
//         .css("left",(e.pageX) + -270 + "px");
     }).mouseout(function(){
         $("#ttp").remove();
     });
}

//予約送信
function viewSendPlanDate() {
    if (document.getElementsByName('sendMailPlanType')[0].checked) {
        $('#sendPlanDateValueArea').show();
        $('#sendPlanImmArea').hide();
        $('#sendPlanImm').attr('checked', false);
    } else {
        $('#sendPlanDateValueArea').hide();
        $('#sendPlanImmArea').show();
    }
}

function setConfirmHeight() {
    var confirmHeight = $(window).height() - 100;
    if (confirmHeight < 100) {
        confirmHeight = 100;
    }
    window.document.getElementById("wml010SendConfirmArea").style.height = confirmHeight + "px";
}

function tinyMceInit(textareaId) {
    tinyMCE.init({
        // General options
        mode : "exact",
        elements : textareaId,
        theme : "advanced",
        plugins : "safari,style,layer,table,save,advhr,advimage,advlink,inlinepopups,insertdatetime,preview,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras",
        language : "ja",
        width:"100%",
        height:"400px",
        // Theme options
        theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,formatselect,fontselect,fontsizeselect,|,bullist,numlist,|,outdent,indent",
        theme_advanced_buttons2 : "undo,redo,|,preview,|,forecolor,backcolor,tablecontrols",
        theme_advanced_buttons3 : "",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom",
        theme_advanced_resizing : false

//        //入力時に改行が<p>にならないように
//        ,force_br_newlines : true,
//        force_p_newlines : false,
//        forced_root_block : ''
//
//        //テキストからコピペするときに改行が<p>にならないように
//        ,plugins: "paste",
//        paste_as_text: true
    });
}

function changeMailBody(sendBody) {
    var sendEditor = YAHOO.example.app.editor;
    var userAgent = window.navigator.userAgent.toLowerCase();
    if (smailType) {
      //IE
      if (userAgent.indexOf("msie") > -1 && navigator.userAgent.indexOf('Trident/7') !== -1) {
          sendEditor.value = sendBody.replace(/\r/g, '');
      }
      sendEditor.value = sendBody.replace(/\n/g, '<br>');

      if (tinyMCE.get(sendEditor.id)) {
          tinyMCE.get(sendEditor.id).setContent(sendEditor.value);
      }
    } else {
        sendEditor.value = sendBody;
        if (!window.getSelection) {
            sendEditor.select();
            var selection = document.selection.createRange();
            selection.setEndPoint("EndToStart", selection);
            selection.select();
        }
    }
}

function setSendBody() {
    if (smailType) {
        YAHOO.example.app.editor.value = tinyMCE.get('wml010sendContent').getContent();
    }
}

function textBr(txtVal) {
  txtVal = txtVal.replace(/\r?\n/g, "<br />");
  return txtVal;
}

function htmlEncode(txtVal) {
    return formatBodyText(txtVal, true);
}

function htmlDecode(txtVal) {
    return formatBodyText(txtVal, false);
}

function formatBodyText(txtVal, type) {
  var lines;
  if (txtVal.indexOf('\n') < 0) {
      lines = [txtVal];
  } else {
      lines = txtVal.split('\n');
  }

  var formatTxt = '';
  for (idx = 0; idx < lines.length; idx++) {
    if (idx >= 1) {
        if (type) {
            formatTxt += '<br />';
        } else {
            formatTxt += '\n';
        }
    }
    if (type) {
        formatTxt += $('<div/>').text(lines[idx]).html();
    } else {
        formatTxt += $('<div/>').html(lines[idx]).text();
    }
  }

  return formatTxt;
}
