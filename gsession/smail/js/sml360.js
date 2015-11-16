function selectPage(id){
    if (id == 1) {
        $('#sml360mailListPageTop')[0].value = $('#sml360mailListPageBottom')[0].value;
    }

    $('#CMD')[0].value = 'init';
    document.forms[0].submit();
    return false;
}

function changeFilterInput() {
    var index;
    for (index = 1; index <= 5; index++) {
        if (getElement('sml360condition' + index).checked) {
            getElement('sml360conditionType' + index).disabled = false;
            getElement('sml360conditionExs' + index).disabled = false;
            getElement('sml360conditionText' + index).disabled = false;
        } else {
            getElement('sml360conditionType' + index).disabled = true;
            getElement('sml360conditionExs' + index).disabled = true;
            getElement('sml360conditionText' + index).disabled = true;
        }
    }
}
function getElement(name) {
    return document.getElementsByName(name)[0];
}

function sml360Sort(sortKey, order) {
    document.getElementsByName('sml360mailListSortKey')[0].value = sortKey;
    document.getElementsByName('sml360mailListOrder')[0].value = order;
    document.forms[0].submit();
    return false;
}

function addFwAddress() {
    var fwAddressTbl = document.getElementById('sml360fwAddressArea');
    fwAddressTbl.innerHTML
        += '<tr>'
        + '<td><img src=\"../common/images/delete.gif\" alt=\"' + msglist['delet'] + '" border=\"0\" onClick="\deleteFwAddress(' + fwAddressTbl.rows.length + ');\"></td>'
        +'<td><input type=\"text\" name=\"sml340actionSendValue\" value=\"\"  maxlength=\"256\" style=\"width:60%;\"></td></tr>';
}

function deleteFwAddress(rowIdx) {
    document.forms['sml360Form'].sml360actionSendValueDelIdx.value=rowIdx;
    return buttonPush('delFwAddress');
}


function openDetail(sid) {

    var sacSid = $('input:hidden[name=smlAccountSid]').val();

    $.ajaxSetup({async:false});
    $.post('../smail/sml030.do', {"CMD":'getDetail',
                                  "sml010ProcMode":0,
                                  "sml010SelectedSid":sid,
                                  "smlViewAccount":sacSid
                                 },
      function(data) {

        if (data != null && data != "") {

            //宛先・件名
            var smsTitle = "";
            var atesakiKenmeiStr = "";

            atesakiKenmeiStr = "<td width=\"100%\" class=\"\" style=\"padding: 5px 0px;\">";

            if (data.sml030SmlList.length > 0) {

                var kakuninTxtClass = "mail_check_txt";

                for (s = 0; s < data.sml030SmlList.length; s++) {

                    var mailData = data.sml030SmlList[s];

                    smsTitle = mailData.smsTitle;

                    if (!data.sml030SosinFlg) {

                        atesakiKenmeiStr += "<table width=\"100%\" class=\"clear_table\">"
                                         +  "<tr>"
                                         +  "<td style=\"width:80px;padding-top:5px;padding-right:5px;padding-bottom:5px;padding-left:20px;\" valign=\"top\">";

                        if (mailData.photoFileDsp == "1") {
                            atesakiKenmeiStr += "<div align=\"center\" class=\"photo_hikokai2\"><span style=\"color:#fc2929;\">非公開</span></div>";
                        }

                        if (mailData.photoFileDsp == "0") {

                            if (mailData.binFileSid == "0") {
                                atesakiKenmeiStr += "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                                 +  mailData.smlSid
                                                 +  "\" alt=\"写真\" border=\"1\" class=\"photo_width\" onload=\"initImageView130('userImage"
                                                 +  mailData.smlSid
                                                 +  "');\">";
                            }

                            if (mailData.binFileSid != "0") {
                                atesakiKenmeiStr += "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                                 +  mailData.binFileSid
                                                 +   "&smlViewAccount="
                                                 +   $("#account_comb_box").val()
                                                 +  "\" name=\"userImage"
                                                 +  mailData.smlSid
                                                 +  "\" alt=\"写真\" border=\"1\" class=\"photo_width\" onload=\"initImageView130('userImage"
                                                 +  mailData.smlSid
                                                 +  "');\">";
                            }
                        }

                        if (mailData.photoFileDsp == "2") {
                            atesakiKenmeiStr += "<img src=\"../smail/images/icon_sml.jpg\" border=\"0\" alt=\"ショートメール\" class=\"img_bottom\">";
                        }

                        atesakiKenmeiStr += "</td>"
                                         +  "<td valign=\"top\">";


                        atesakiKenmeiStr += "<div class=\"mail_check_title\">";

                        if (mailData.fwKbn != "0") {
                            atesakiKenmeiStr += "<img alt=\"Fw\" src=\"../smail/images/img_forward.gif\" class=\img_bottom\">";
                        }
                        if (mailData.returnKbn != "0") {
                            atesakiKenmeiStr += "<img alt=\"Re\" src=\"../smail/images/img_henshin.gif\" class=\"img_bottom\">";
                        }

                        atesakiKenmeiStr += mailData.smsTitle;

                        atesakiKenmeiStr += "</div>";


                        atesakiKenmeiStr += "<div class=\"mail_check_txt\">送信者：";

                        if (mailData.usrSid > 0) {
                            if (mailData.usrJkbn == "0") {
                                atesakiKenmeiStr += htmlEscape(mailData.usiSei) + "&nbsp;&nbsp;" + htmlEscape(mailData.usiMei);
                            }

                            if (mailData.usrJkbn == "9" || mailData.usrJkbn == "1") {
                                atesakiKenmeiStr += "<del>" + htmlEscape(mailData.usiSei) + "&nbsp;&nbsp;" + htmlEscape(mailData.usiMei) + "</del>";
                            }
                        } else {
                            if (mailData.accountJkbn == "0") {
                                atesakiKenmeiStr += htmlEscape(mailData.accountName);
                            }

                            if (mailData.accountJkbn == "1") {
                                atesakiKenmeiStr += "<del>" + htmlEscape(mailData.accountName) + "</del>";
                            }
                        }


                        atesakiKenmeiStr += "</div>";

                    }


                    if (data.sml030SosinFlg) {

                        kakuninTxtClass = "mail_check_txt2";

                        if (mailData.atesakiList.length > "0") {

                            atesakiKenmeiStr += "<table width=\"100%\" class=\"clear_table\">"
                                             +  "<tr><td width=\"50px\" valign=\"top\">";

                            atesakiKenmeiStr += "<div class=\"mail_check_txt2\">宛先</div>";

                            atesakiKenmeiStr += "</td>";

                            atesakiKenmeiStr += "<td style=\"padding-top:5px;padding-right:5px;padding-bottom:0px;padding-left:5px;\" valign=\"middle\" align=\"left\">";

                            for (m = 0; m < mailData.atesakiList.length; m++) {

                                atesakiKenmeiStr += "<span class=\"photoList\">";

                                var atesaki = mailData.atesakiList[m];

                                if (atesaki.photoFileDsp == "1") {
                                    atesakiKenmeiStr += "<div align=\"center\" class=\"photo_hikokai2\"><span style=\"color:#fc2929;\">非公開</span></div>";
                                }

                                if (atesaki.photoFileDsp == "0") {
                                    if (atesaki.binFileSid == "0") {
                                        atesakiKenmeiStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                                    }
                                    if (atesaki.binFileSid != "0") {
                                        atesakiKenmeiStr += "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                                         +  atesaki.binFileSid
                                                         +   "&smlViewAccount="
                                                         +   $("#account_comb_box").val()
                                                         +  "\" name=\"userImage"
                                                         +  atesaki.usrSid
                                                         +  "\" alt=\"写真\" border=\"0\" width=\"50px\" onload=\"initImageView50('userImage"
                                                         +  atesaki.usrSid
                                                         +  "');\" class=\"img_bottom\">";
                                    }
                                }

                                atesakiKenmeiStr += "<div class=\"text_base\" align=\"center\">";
                                atesakiKenmeiStr += "<span class=\"mail_check_txt\" style=\"white-space: nowrap\">";

                                if (atesaki.usrSid > 0) {
                                    if (atesaki.usrJkbn == "0") {
                                        atesakiKenmeiStr += htmlEscape(atesaki.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesaki.usiMei);
                                    }

                                    if (atesaki.usrJkbn == "9") {
                                        atesakiKenmeiStr += "<del>" + htmlEscape(atesaki.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesaki.usiMei) + "</del>";
                                    }
                                } else {
                                    if (atesaki.accountJkbn == "0") {
                                        atesakiKenmeiStr += htmlEscape(atesaki.accountName);
                                    }

                                    if (atesaki.accountJkbn == "1") {
                                        atesakiKenmeiStr += "<del>" + htmlEscape(atesaki.accountName) + "</del>";
                                    }
                                }





                                atesakiKenmeiStr += "</span></div></span>";

                            }

                            atesakiKenmeiStr += "</td></tr>"
                                             +  "</table>";
                        }


                        if (mailData.ccList.length > "0") {

                           atesakiKenmeiStr += "<table width=\"100%\" class=\"clear_table mail_check_txt_border_top\">"
                                            +  "<tr><td width=\"50px\" valign=\"top\">";

                           atesakiKenmeiStr += "<div class=\"mail_check_txt2\">CC</div>";

                           atesakiKenmeiStr += "</td>";

                           atesakiKenmeiStr += "<td style=\"padding-top:5px;padding-right:5px;padding-bottom:0px;padding-left:5px;\" valign=\"middle\" align=\"left\">";

                            for (n = 0; n < mailData.ccList.length; n++) {

                                atesakiKenmeiStr += "<span class=\"photoList\">";

                                var cc = mailData.ccList[n];

                                if (cc.photoFileDsp == "1") {
                                    atesakiKenmeiStr += "<div align=\"center\" class=\"photo_hikokai2\"><span style=\"color:#fc2929;\">非公開</span></div>";
                                }

                                if (cc.photoFileDsp == "0") {
                                    if (cc.binFileSid == "0") {
                                        atesakiKenmeiStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                                    }
                                    if (cc.binFileSid != "0") {
                                        atesakiKenmeiStr += "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                                         +  cc.binFileSid
                                                         +   "&smlViewAccount="
                                                         +   $("#account_comb_box").val()
                                                         +  "\" name=\"userImage"
                                                         +  cc.usrSid
                                                         +  "\" alt=\"写真\" border=\"0\" width=\"50px\" onload=\"initImageView50('userImage"
                                                         +  cc.usrSid
                                                         +  "');\" class=\"img_bottom\">";
                                    }
                                }

                                atesakiKenmeiStr += "<div class=\"text_base\" align=\"center\">";
                                atesakiKenmeiStr += "<span class=\"mail_check_txt\" style=\"white-space: nowrap\">";

                                if (cc.usrSid > 0) {
                                    if (cc.usrJkbn == "0") {
                                        atesakiKenmeiStr += htmlEscape(cc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(cc.usiMei);
                                    }

                                    if (cc.usrJkbn == "9") {
                                        atesakiKenmeiStr += "<del>" + htmlEscape(cc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(cc.usiMei) + "</del>";
                                    }
                                } else {
                                    if (cc.accountJkbn == "0") {
                                        atesakiKenmeiStr += htmlEscape(cc.accountName);
                                    }

                                    if (cc.accountJkbn == "1") {
                                        atesakiKenmeiStr += "<del>" + htmlEscape(cc.accountName) + "</del>";
                                    }
                                }



                                atesakiKenmeiStr += "</span></div></span>";
                            }

                            atesakiKenmeiStr += "</td></tr>"
                                             +  "</table>";
                        }


                        if (mailData.bccList.length > "0") {

                            atesakiKenmeiStr += "<table width=\"100%\" class=\"clear_table mail_check_txt_border_top\">"
                                             +  "<tr><td width=\"50px\" valign=\"top\">";

                           atesakiKenmeiStr += "<div class=\"mail_check_txt2\">BCC</div>";

                           atesakiKenmeiStr += "</td>";

                           atesakiKenmeiStr += "<td style=\"padding-top:5px;padding-right:5px;padding-bottom:0px;padding-left:5px;\" valign=\"middle\" align=\"left\">";

                            for (o = 0; o < mailData.bccList.length; o++) {

                                atesakiKenmeiStr += "<span class=\"photoList\">";

                                var bcc = mailData.bccList[o];

                                if (bcc.photoFileDsp == "1") {
                                    atesakiKenmeiStr += "<div align=\"center\" class=\"photo_hikokai2\"><span style=\"color:#fc2929;\">非公開</span></div>";
                                }

                                if (bcc.photoFileDsp == "0") {
                                    if (bcc.binFileSid == "0") {
                                        atesakiKenmeiStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                                    }
                                    if (bcc.binFileSid != "0") {
                                        atesakiKenmeiStr += "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                                         +  bcc.binFileSid
                                                         +   "&smlViewAccount="
                                                         +   $("#account_comb_box").val()
                                                         +  "\" name=\"userImage"
                                                         +  bcc.usrSid
                                                         +  "\" alt=\"写真\" border=\"0\" width=\"50px\" onload=\"initImageView50('userImage"
                                                         +  bcc.usrSid
                                                         +  "');\" class=\"img_bottom\">";
                                    }
                                }

                                atesakiKenmeiStr += "<div class=\"text_base\" align=\"center\">";
                                atesakiKenmeiStr += "<span class=\"mail_check_txt\" style=\"white-space: nowrap\">";

                                if (bcc.usrSid > 0) {
                                    if (bcc.usrJkbn == "0") {
                                        atesakiKenmeiStr += htmlEscape(bcc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(bcc.usiMei);
                                    }

                                    if (bcc.usrJkbn == "9") {
                                        atesakiKenmeiStr += "<del>" + htmlEscape(bcc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(bcc.usiMei) + "</del>";
                                    }
                                } else {
                                    if (bcc.accountJkbn == "0") {
                                        atesakiKenmeiStr += htmlEscape(bcc.accountName);
                                    }

                                    if (bcc.accountJkbn == "1") {
                                        atesakiKenmeiStr += "<del>" + htmlEscape(bcc.accountName) + "</del>";
                                    }
                                }


                                atesakiKenmeiStr += "</span></div></span>";

                            }

                            atesakiKenmeiStr += "</td></tr>"
                                             +  "</table>";
                        }


                        atesakiKenmeiStr += "<table width=\"100%\" class=\"clear_table\">"
                                         +  "<tr><td class=\"mail_check_txt_border_top\">";
                        atesakiKenmeiStr += "<div style=\"padding-top:5px\" class=\""
                                         + kakuninTxtClass + "\">件名　：";
                        atesakiKenmeiStr += mailData.smsTitle + "</div>";
                        atesakiKenmeiStr += "</td></tr></table>";
                    }

                    atesakiKenmeiStr += "<div class=\""
                                     + kakuninTxtClass + "\">";

                    atesakiKenmeiStr += "日時　：";

                    if (data.sml010ProcMode == "4" || data.sml010ProcMode == "5") {
                        if (mailData.mailKbn == "2") {
                            atesakiKenmeiStr += "&nbsp;";
                        }
                        if (mailData.mailKbn != "2") {
                            atesakiKenmeiStr += mailData.smsSdateStr;
                        }
                    }

                    if (data.sml010ProcMode != "4" && data.sml010ProcMode != "5") {
                        atesakiKenmeiStr += mailData.smsSdateStr;
                    }

                    atesakiKenmeiStr += "</div>";

                    if (data.sml010ProcMode != "1") {


                        if (data.sml010ProcMode != "4" && data.sml010ProcMode != "5") {
                            if (mailData.atesakiList.length > 0) {
                                atesakiKenmeiStr += "<div class=\"mail_check_txt\">";
                                atesakiKenmeiStr += "宛先　：";
                                for (t = 0; t < mailData.atesakiList.length; t++) {
                                   var atesakiMdl = mailData.atesakiList[t];

                                   if (atesakiMdl.usrSid > 0) {
                                       if (atesakiMdl.usrJkbn == "0") {
                                           atesakiKenmeiStr += htmlEscape(atesakiMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiMdl.usiMei);
                                       }
                                       if (atesakiMdl.usrJkbn == "9") {
                                           atesakiKenmeiStr += "<del>" + htmlEscape(atesakiMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiMdl.usiMei) + "</del>";

                                       }
                                   } else {
                                       if (atesakiMdl.accountJkbn == "0") {
                                           atesakiKenmeiStr += htmlEscape(atesakiMdl.accountName);
                                       }
                                       if (atesakiMdl.accountJkbn == "1") {
                                           atesakiKenmeiStr += "<del>" + htmlEscape(atesakiMdl.accountName) + "</del>";

                                       }
                                   }



                                   if (t != (mailData.atesakiList.length) - 1) {
//                                       atesakiKenmeiStr += "<br>　　　　";
                                       atesakiKenmeiStr += ",&nbsp;&nbsp;";
                                   }

                                }
                                atesakiKenmeiStr += "</div>";
                             }


                             if (mailData.ccList.length > 0) {
                                 atesakiKenmeiStr += "<div class=\"mail_check_txt\">";
                                 atesakiKenmeiStr += "CC　&nbsp;&nbsp;：";
                                 for (c = 0; c < mailData.ccList.length; c++) {
                                    var ccMdl = mailData.ccList[c];

                                    if (ccMdl.usrSid > 0) {
                                        if (ccMdl.usrJkbn == "0") {
                                            atesakiKenmeiStr += htmlEscape(ccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(ccMdl.usiMei);
                                        }
                                        if (ccMdl.usrJkbn == "9") {
                                            atesakiKenmeiStr += "<del>" + htmlEscape(ccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(ccMdl.usiMei) + "</del>";

                                        }
                                    } else {
                                        if (ccMdl.accountJkbn == "0") {
                                            atesakiKenmeiStr += htmlEscape(ccMdl.accountName);
                                        }
                                        if (ccMdl.accountJkbn == "1") {
                                            atesakiKenmeiStr += "<del>" + htmlEscape(ccMdl.accountName) + "</del>";

                                        }
                                    }



                                    if (c != (mailData.ccList.length) - 1) {
//                                        atesakiKenmeiStr += "<br>　　　　";
                                        atesakiKenmeiStr += ",&nbsp;&nbsp;";
                                    }

                                 }
                                 atesakiKenmeiStr += "</div>";
                            }
                        }
                    }

                    atesakiKenmeiStr += "</div>";



                    if ($('#markKey_' + mailData.smsMark).attr('id') != null
                                                     && $('#markKey_' + mailData.smsMark).val() != "") {
                        atesakiKenmeiStr += "<div class=\""
                                         + kakuninTxtClass + "\">"
                                         + "マーク："
                                         + $('#markKey_' + mailData.smsMark).val()
                                         + "</div>";
                    }

                    if (!data.sml030SosinFlg) {
                        atesakiKenmeiStr += "</td>"
                                         +  "</tr>";
                                         +  "<tr>";
                                         +  "<td colspan=\"2\">";
                    }



                    if (data.sml030FileList.length > 0) {
                        atesakiKenmeiStr += "<table width=\"100%\">";

                        for (e = 0; e < data.sml030FileList.length; e++) {

                            atesakiKenmeiStr += "<tr><td style=\"padding-top:5px;padding-bottom:5px;padding-left:20px;\" class=\"mail_check_txt\" width=\"100%\">";

                            var fileMdl = data.sml030FileList[e];

                            if ($('input:hidden[name=tempDspFlg]').val() == "0") {

                                if (fileMdl.binFileExtension != null && fileMdl.binFileExtension != "") {
                                    var fext = fileMdl.binFileExtension;

                                     if (fext != null) {
                                         fext = fext.toLowerCase();
                                         if (isViewFile(fext)) {
                                             atesakiKenmeiStr += "<img src=\"../smail/sml030.do?CMD=tempview&sml010SelectedSid="
                                                              + data.sml010SelectedSid
                                                              + "&sml030binSid="
                                                              + fileMdl.binSid
                                                              + "&smlViewAccount="
                                                              + $("#account_comb_box").val()
                                                              + "\" name=\"pictImage"
                                                              + fileMdl.binSid
                                                              + "\" onload=\"initImageView('pictImage"
                                                              + fileMdl.binSid
                                                              + "');\">";
                                             atesakiKenmeiStr += "<br>";
                                         }
                                     }
                                }
                            }

                            atesakiKenmeiStr += data.sml010SelectedSid
                                             +  "<span class=\"\">"
                                             +  fileMdl.binFileName + fileMdl.binFileSizeDsp
                                             +  "</span>";

                            atesakiKenmeiStr += "</td></tr>";
                        }

                    }


                    if (!data.sml030SosinFlg) {
                        atesakiKenmeiStr += "</td>"
                                         +  "</tr>"
                                         +  "</table>";
                    }

                    $(".mail_check_body_txt").html("");
                    $(".mail_check_body_txt").append(mailData.smsBody);

                }
            }

            atesakiKenmeiStr += "</td>";

            $('#mail_kakunin_body_tr').children().remove();
            $('#mail_kakunin_body_tr').append(atesakiKenmeiStr);

            $('#mail_detail_pop').dialog({
                autoOpen: true,  // hide dialog
                bgiframe: true,   // for IE6
                resizable: false,
                height: 600,
                width: 600,
                modal: true,
                overlay: {
                  backgroundColor: '#000000',
                  opacity: 0.5
                },
                buttons: {
                  閉じる: function() {
                      $(this).dialog('close');
                  }
                }
            });

        } else {
            //該当するデータがありません。
            alert('データなし');
        }

    });
}

function htmlEscape(s){
    s=s.replace(/&/g,'&amp;');
    s=s.replace(/>/g,'&gt;');
    s=s.replace(/</g,'&lt;');
    return s;
}