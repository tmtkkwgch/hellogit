function buttonPush(cmd) {

//  if (cmd == "pdf" || cmd == "eml") {
//  $("#smlForm").attr("action", "../smail/sml030.do");
//  }

//  document.forms[0].CMD.value=cmd;
//  document.forms[0].submit();

//  if (cmd == "pdf" || cmd == "eml") {
//  $("#smlForm").attr("action", "../smail/sml010.do");
//  }
    if (cmd == "pdf" || cmd == "eml") {
        document.getElementById('sml010Export').src = "../smail/sml030.do"
            + '?CMD=' + cmd
            + '&smlViewAccount=' + document.forms[0].smlViewAccount.value
            + '&sml010SelectedSid=' + document.forms[0].sml010SelectedSid.value
            + '&sml010ProcMode=' + document.forms[0].sml010ProcMode.value
            + '&sml010SelectedMailKbn=' + document.forms[0].sml010SelectedMailKbn.value
            + '&sml010SelectLabelSid=' + document.forms[0].sml010SelectLabelSid.value
            + '&sml010Sort_key=' + document.forms[0].sml010Sort_key.value
            + '&sml010Order_key=' + document.forms[0].sml010Order_key.value;
    } else {
        document.forms[0].CMD.value=cmd;
        document.forms[0].submit();
        return false;
    }
}

function onTitleLinkSubmit(fid, order) {
    document.forms[0].CMD.value='sortDetail';
    document.forms[0].sml010Sort_key.value = fid;
    document.forms[0].sml010Order_key.value = order;
    document.forms[0].submit();
    return false;
}

function changePage1() {
    document.forms[0].CMD.value='';
    for (i = 0; i < document.forms[0].sml010Slt_page1.length; i++) {
        if (document.forms[0].sml010Slt_page1[i].selected) {
            document.forms[0].sml010Slt_page2.value=document.forms[0].sml010Slt_page1[i].value;
            document.forms[0].sml010PageNum.value=document.forms[0].sml010Slt_page1[i].value;
        }
    }
    document.forms[0].submit();
    return false;
}

function changePage2() {
    document.forms[0].CMD.value='';
    for (i = 0; i < document.forms[0].sml010Slt_page2.length; i++) {
        if (document.forms[0].sml010Slt_page2[i].selected) {
            document.forms[0].sml010Slt_page1.value=document.forms[0].sml010Slt_page2[i].value;
            document.forms[0].sml010PageNum.value=document.forms[0].sml010Slt_page2[i].value;
        }
    }
    document.forms[0].submit();
    return false;
}

function changeChk(){

    var chkFlg;
    if (document.forms[0].allCheck.checked) {
        chkFlg = true;
    } else {
        chkFlg = false;
    }

    delAry = document.getElementsByName("sml010DelSid");
    for(i = 0; i < delAry.length; i++) {
        delAry[i].checked = chkFlg;
    }
}

function changeSearchChk(){

    var chkFlg;
    if (document.forms[0].allSearchCheck.checked) {
        chkFlg = true;
    } else {
        chkFlg = false;
    }

    delAry = document.getElementsByName("sml090DelSid");
    for(i = 0; i < delAry.length; i++) {
        delAry[i].checked = chkFlg;
    }
}

function changeMode(mode) {
    document.forms[0].CMD.value='changeMode';
    document.forms[0].sml010ProcMode.value = mode;
    document.forms[0].submit();
    return false;
}

function moveDetail(sid, kbn) {
    document.forms[0].CMD.value='moveDetail';
    document.forms[0].sml010SelectedSid.value = sid;
    document.forms[0].sml010SelectedMailKbn.value = kbn;
    document.forms[0].submit();
    return false;
}

function moveMessage(sid) {
    document.forms[0].CMD.value='moveMessage';
    document.forms[0].sml010SelectedSid.value = sid;
    document.forms[0].submit();
    return false;
}

function hinaEdit(hinaKbn) {
    document.forms[0].CMD.value='hina_edit';
    document.forms[0].sml050HinaKbn.value = hinaKbn;
    document.forms[0].submit();
    return false;
}

function sml010ChangeGrp() {
    document.forms[0].CMD.value = 'changeGrp';
    document.forms[0].submit();
    return false;
}

function changeChkAdd(){
    var chkFlg;
    if (document.forms[0].usrAllCheck.checked) {
        chkFlg = true;
    } else {
        chkFlg = false;
    }
    delAry = document.getElementsByName("sml010usrSids");
    for(i = 0; i < delAry.length; i++) {
        delAry[i].checked = chkFlg;
    }
}

function usrNameClick(usrsid) {
    document.forms[0].CMD.value='addUsr';
    document.forms[0].sml010usrSid.value = usrsid;
    document.forms[0].submit();
    return false;
}

function dispStyle() {

    $('#btnSearchAtesakiSelect')[0].disabled = $('#radio_jushin')[0].checked;
    $('#btnSearchAtesakiClear')[0].disabled = $('#radio_jushin')[0].checked;

    if ($('#radio_jushin')[0].checked) {
        $('#btnSearchAtesakiSelect').addClass('btn_base_disabled');
        $('#btnSearchAtesakiClear').addClass('btn_base_disabled');
        $('#btnSearchAtesakiSelect').removeClass('btn_base');
        $('#btnSearchAtesakiClear').removeClass('btn_base');
    } else {
        $('#btnSearchAtesakiSelect').removeClass('btn_base_disabled');
        $('#btnSearchAtesakiClear').removeClass('btn_base_disabled');
        $('#btnSearchAtesakiSelect').addClass('btn_base');
        $('#btnSearchAtesakiClear').addClass('btn_base');
    }

    $('#head_menu_search_list_label_add_btn').prev().removeClass('display_none');
    $('#head_menu_search_list_label_add_btn').removeClass('display_none');
    $('#head_menu_search_list_label_del_btn').removeClass('display_none');

    if ($('#radio_gomi')[0].checked) {
        $('#head_menu_search_list_label_add_btn').prev().addClass('display_none');
        $('#head_menu_search_list_label_add_btn').addClass('display_none');
        $('#head_menu_search_list_label_del_btn').addClass('display_none');
    }

    $('#head_menu_search_list_kidoku_btn').prev().addClass('display_none');
    $('#head_menu_search_list_kidoku_btn').addClass('display_none');
    $('#head_menu_search_list_midoku_btn').addClass('display_none');

    if ($('#radio_jushin')[0].checked || $('#radio_gomi')[0].checked) {
        $('#head_menu_search_list_kidoku_btn').prev().removeClass('display_none');
        $('#head_menu_search_list_kidoku_btn').removeClass('display_none');
        $('#head_menu_search_list_midoku_btn').removeClass('display_none');
    }

    $('#sml090SltGroup')[0].disabled = false;
    $('#sml090SltUser')[0].disabled = false;

    $('.search_grp_sel_btn').removeClass('display_none');
    $('.search_grp_sel_btn_hide').addClass('display_none');

    if ($('#radio_soukou')[0].checked || $('#radio_soushin')[0].checked) {
        $('#sml090SltGroup')[0].disabled = true;
        $('#sml090SltUser')[0].disabled = true;
        $('#sml090SltGroup')[0].selectedIndex = 0;
        $('#sml090SltUser')[0].selectedIndex = 0;
        $('.search_grp_sel_btn').addClass('display_none');
        $('.search_grp_sel_btn_hide').removeClass('display_none');
    }

//  $('#radio_gomi')[0].checked;
}

/* 新規作成フラグ */
var mail_create_flg = false;
/* 新規作成確認フラグ  */
var mail_create_kakunin_flg = false;
/* メール確認フラグ  */
var mail_kakunin_flg = false;
/* 確認メール 区分  */
var mail_kakunin_kbn = 0;
/* メール検索フラグ  */
var mail_search_list_flg = false;
/* テンポラリディレクトリ削除フラグ  */
var tmp_del_flg = true;
/* 選択中タブ 一覧 or 検索 */
var now_sel_tab = 'mail_list_tab';
/* 左メニューラベル開閉フラグ */
var left_menu_label_opnkbn = true;
/* 選択メール親画面フラグ  0:通常 1:検索*/
var sel_mail_parent_kbn = 0;
/* 宛先 表示区分 0:スクロール表示 1:全て表示*/
var dsp_to_kbn = 0;
/* 宛先TO 表示区分 0:スクロール表示 1:全て表示*/
var dsp_cc_kbn = 0;
/* 宛先BCC 表示区分 0:スクロール表示 1:全て表示*/
var dsp_bcc_kbn = 0;
/* 受信メール 宛先 表示区分 0:スクロール表示 1:全て表示*/
var jmail_dsp_to_kbn = 0;
/* 受信メール 宛先TO 表示区分 0:スクロール表示 1:全て表示*/
var jmail_dsp_cc_kbn = 0;
/* 確認メッセージ取得通信中フラグ */
var load_kakunin_message_flg = false;
/* チェックボックス未選択エラーメッセージ*/
var errMsg_noselect_reload = "リロードにより選択が解除されました。";
/* 詳細メールSID */
var detail_mail_sid = 0;

function changeHelpParam(mode, submode) {

    if (submode == 0) {
        $('input:hidden[name=helpPrm]').val(mode);
    } else if (submode == 1) {
        if (!mail_create_kakunin_flg) {
            $('input:hidden[name=helpPrm]').val(6);
        } else {
            $('input:hidden[name=helpPrm]').val('6kn');
        }
    } else if (submode == 2) {
        if (mail_kakunin_kbn == 0) {
            //受信
            $('input:hidden[name=helpPrm]').val(8);
        } else if (mail_kakunin_kbn == 1) {
            //送信
            $('input:hidden[name=helpPrm]').val(9);
        } else if (mail_kakunin_kbn == 2) {
            //ゴミ箱 受信
            $('input:hidden[name=helpPrm]').val(10);
        } else if (mail_kakunin_kbn == 3) {
            //ゴミ箱 送信
            $('input:hidden[name=helpPrm]').val(11);
        } else if (mail_kakunin_kbn == 4) {
            //ゴミ箱 草稿
            $('input:hidden[name=helpPrm]').val(12);
        } else {
            $('input:hidden[name=helpPrm]').val(8);
        }
    } else if (submode == 3) {
        $('input:hidden[name=helpPrm]').val(7);
    }

}

$(function() {

    /* 初期データ取得 */
    document.forms[0].CMD.value='getInitData';
    loadingPop("読み込み中");
    getMailData();
    deleteTmpDir();
    tinyMceInit();

    /* 検索エリア初期化 */
    dispStyle();

    /* ひな形パラメータ初期化 */
    $('input:hidden[name=sml050HinaKbn]').val(1);

    /* 再読み込み */
    $("#btn_reload").live("click", function(){
        changeTab($('#mail_list_tab'));
        loadingPop("読み込み中");
        reloadData();
    });

    /* 左メニュー ラベル */
    $(".changeLabelDir").live("click", function(){
        document.forms[0].CMD.value='changeDir';
        document.forms[0].sml010ProcMode.value = "5";
        document.forms[0].sml010SelectLabelSid.value = $(this).attr('id');

        var labelTxt = $(this).next().val();

        if (labelTxt.length > 10) {
            labelTxt = labelTxt.substring(0, 10) + "…";
        }

        $("#mail_list_tab").html(labelTxt);
        $('.gomibako_area').remove();
        $("#head_menu_list_label_add_btn").removeClass('display_none');
        $("#head_menu_list_label_del_btn").removeClass('display_none');
        $("#head_menu_list_label_add_btn").prev().removeClass('display_none');
        $("#head_menu_list_label_add_btn").removeClass('display_none');
        $("#head_menu_list_label_del_btn").removeClass('display_none');
        $("#head_menu_list_kidoku_btn").prev().removeClass('display_none');
        $("#head_menu_list_kidoku_btn").removeClass('display_none');
        $("#head_menu_list_midoku_btn").removeClass('display_none');
        changeTab($('#mail_list_tab'));
        loadingPop("読み込み中");
        getMailData();

    });

    /* ページング next */
    $(".sml_page_right_btn").live("click", function(){
        document.forms[0].CMD.value='page_right';
        loadingPop("読み込み中");
        getMailData();
    });

    /* ページング prev */
    $(".sml_page_left_btn").live("click", function(){
        document.forms[0].CMD.value='page_left';
        loadingPop("読み込み中");
        getMailData();
    });

    /* ページング コンボ変更  上*/
    $("#sml010Slt_page1").live("change", function(){
        document.forms[0].CMD.value='getInitData';
        for (i = 0; i < document.forms[0].sml010Slt_page1.length; i++) {
            if (document.forms[0].sml010Slt_page1[i].selected) {
                document.forms[0].sml010Slt_page2.value=document.forms[0].sml010Slt_page1[i].value;
                document.forms[0].sml010PageNum.value=document.forms[0].sml010Slt_page1[i].value;
            }
        }
        loadingPop("読み込み中");
        getMailData();
    });

    /* ページング コンボ変更 下*/
    $("#sml010Slt_page2").live("change", function(){
        document.forms[0].CMD.value='getInitData';
        for (i = 0; i < document.forms[0].sml010Slt_page2.length; i++) {
            if (document.forms[0].sml010Slt_page2[i].selected) {
                document.forms[0].sml010Slt_page1.value=document.forms[0].sml010Slt_page2[i].value;
                document.forms[0].sml010PageNum.value=document.forms[0].sml010Slt_page2[i].value;
            }
        }
        loadingPop("読み込み中");
        getMailData();
    });


    /* アカウント hover */
    $(".menu_head_area").live({
        mouseenter:function (e) {
            $(this).addClass("menu_head_area_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("menu_head_area_on");
        }
    });

    /* ひな形 追加 hover */
    $("#hinagata_add").live({
        mouseenter:function (e) {
            $(this).addClass("hinagata_add_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("hinagata_add_on");
        }
    });

    /* メールボックスエリア hover */
    $(".account_sel_txt").live({
        mouseenter:function (e) {
            $(this).addClass("account_sel_txt_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("account_sel_txt_on");
        }
    });


    /* アカウントエリア クリック */
    $("#sml_account_area").live("click", function(){
        $("#sml_account_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
        changeSelImg($(this).children());
    });

    /* メールボックスエリア クリック */
    $("#sml_mailbox_area").live("click", function(){
        $("#sml_mailbox_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
        changeSelImg($(this).children());
    });


    /* ユーザ情報エリア クリック */
    $("#sml_shain_area").live("click", function(){
        $("#sml_shain_child_area").animate( { height: 'toggle'}, 'middle' );
        changeSelImg($(this).children());
    });

    /* ひな形(共通)エリア クリック */
    $("#sml_hina_kyotu_area").live("click", function(){
        $("#sml_hina_kyotu_child_area").animate( { height: 'toggle'}, 'middle' );
        changeSelImg($(this).children());
    });

    /* ひな形(個人)エリア クリック */
    $("#sml_hina_kojin_area").live("click", function(){
        $("#sml_hina_kojin_child_area").animate( { height: 'toggle'}, 'middle' );
        changeSelImg($(this).children());
    });


    /* ラベル クリック */
    $("#lable_top").live("click", function(){
        $(this).prev().prev().click();

        if (left_menu_label_opnkbn) {
            left_menu_label_opnkbn = false;
        } else {
            left_menu_label_opnkbn = true;
        }
    });


    /* プラス、マイナス要素クリック */
    $(".line_plus_minus").live("click", function(){

        $(this).parent().next().toggle();

        if ($(this).hasClass("mail_left_line_plus")) {
            $(this).removeClass("mail_left_line_plus");
            $(this).addClass("mail_left_line_minus");
        } else if ($(this).hasClass("mail_left_line_plus_top")) {
            $(this).removeClass("mail_left_line_plus_top");
            $(this).addClass("mail_left_line_minus_top");
        } else if ($(this).hasClass("mail_left_line_plus_bottom")) {
            $(this).removeClass("mail_left_line_plus_bottom");
            $(this).addClass("mail_left_line_minus_bottom");
        } else if ($(this).hasClass("mail_left_line_minus")) {
            $(this).removeClass("mail_left_line_minus");
            $(this).addClass("mail_left_line_plus");
        } else if ($(this).hasClass("mail_left_line_minus_top")) {
            $(this).removeClass("mail_left_line_minus_top");
            $(this).addClass("mail_left_line_plus_top");
        } else if ($(this).hasClass("mail_left_line_minus_bottom")) {
            $(this).removeClass("mail_left_line_minus_bottom");
            $(this).addClass("mail_left_line_plus_bottom");
        }

    });

    /* メールボックステキスト  hover*/
    $('.mail_box_txt').live({
        mouseenter:function (e) {
            $(this).addClass("mail_box_txt_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("mail_box_txt_on");
        }
    });

    /* メール 宛先  CC BCC hover*/
    $('.mail_btn').live({
        mouseenter:function (e) {
            $(this).addClass("mail_btn_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("mail_btn_on");
        }
    });

    /* メールボタン hover*/
    $('.mail_menu_btn').live({
        mouseenter:function (e) {
            $(this).children(".head_menu_btn_left").addClass("head_menu_btn_left_on");
            $(this).children(".head_menu_btn_right").addClass("head_menu_btn_right_on");
            $(this).children(".head_menu_btn_bg").addClass("head_menu_btn_bg_on");
        },
        mouseleave:function (e) {
            $(this).children(".head_menu_btn_left").removeClass("head_menu_btn_left_on");
            $(this).children(".head_menu_btn_right").removeClass("head_menu_btn_right_on");
            $(this).children(".head_menu_btn_bg").removeClass("head_menu_btn_bg_on");
        }
    });

    /* 検索ボタン hover*/
    $('.head_menu_search_btn').live({
        mouseenter:function (e) {
            $(this).addClass("head_menu_search_btn_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("head_menu_search_btn_on");
        }
    });

    /* 送信者 件名 日時  hover */
    $('.mail_list_head_sel').live({
        mouseenter:function (e) {
            if ($(this).children().attr('id') != 'mail_from') {
                $(this).addClass("mail_list_head_on");
            }
        },
        mouseleave:function (e) {
            $(this).removeClass("mail_list_head_on");
        }
    });

    /* 送信者 件名 日時  click */
    $('.mail_list_head_sel').live("click", function(){
        if ($(this).children().attr('id') != 'mail_from') {
            onTitleLinkClick($(this).children().next().attr('id'),$(this).children().next().next().attr('id'));
        }
    });

    /* メール 行  hover */
    $('.mail_list_line').live({
        mouseenter:function (e) {
            $(this).children().addClass("mail_list_content_on");
            $(this).addClass("mail_list_line_on");
        },
        mouseleave:function (e) {
            $(this).children().removeClass("mail_list_content_on");
            $(this).removeClass("mail_list_line_on");
        }
    });


    /* メール  click */
    $('.mail_list_content').live("click", function(){

        var procMode = $("input[name=sml010ProcMode]").val();
        var searchFlg = false;

        sel_mail_parent_kbn = 0;

        if ($(this).parent().attr('id') != null && $(this).parent().attr('id') == 'search_list_line') {
            procMode = $("input:hidden[name=sml090SvMailSyubetsu]").val();
            searchFlg = true;
            sel_mail_parent_kbn = 1;
        }

        var mailKbn = $('#mailkbn_' + $(this).attr('id')).val();

        if (procMode == "0") {
            //受信
            getDetail($(this).attr('id'), '-1', searchFlg, true, 0);
        } else if (procMode == "1") {
            //送信
            getDetail($(this).attr('id'), '-1', searchFlg, true, 0)
        } else if (procMode == "2" || (procMode != "4" && mailKbn == "2")) {
            //草稿
            getMessage($(this).attr('id'))
        } else if (procMode == "4" || procMode == "5") {
            //ゴミ箱
            getDetail($(this).attr('id'), mailKbn, searchFlg, true, 0)
        }


    });

    /* メール確認 前へ  click */
    $('.head_menu_prev_btn').live("click", function(){

        loadingPop("読み込み中");

        var procMode = $("input[name=sml010ProcMode]").val();
        var searchFlg = false;

        if (sel_mail_parent_kbn == 1) {
            procMode = $("input:hidden[name=sml090SvMailSyubetsu]").val();
            searchFlg = true;
        }

        var mailKbn = $('input:hidden[name=sml010SelectedMailKbn]').val();

        var smlSid = $('input:hidden[name=sml010SelectedSid]').val();

        if (procMode == "0") {
            //受信
            getDetail(smlSid, '-1', searchFlg, true, 1);
        } else if (procMode == "1") {
            //送信
            getDetail(smlSid, '-1', searchFlg, true, 1)
//          } else if (procMode == "2" || (procMode != "4" && mailKbn == "2")) {
//          //草稿
//          getMessage($(this).attr('id'))
        } else if (procMode == "4" || procMode == "5") {
            //ゴミ箱
            getDetail(smlSid, mailKbn, searchFlg, true, 1)
        }


    });

    /* メール確認 次へ  click */
    $('.head_menu_next_btn').live("click", function(){

        loadingPop("読み込み中");

        var procMode = $("input[name=sml010ProcMode]").val();
        var searchFlg = false;

        if (sel_mail_parent_kbn == 1) {
            procMode = $("input:hidden[name=sml090SvMailSyubetsu]").val();
            searchFlg = true;
        }

        var mailKbn = $('input:hidden[name=sml010SelectedMailKbn]').val();
        var smlSid = $('input:hidden[name=sml010SelectedSid]').val();

        if (procMode == "0") {
            //受信
            getDetail(smlSid, '-1', searchFlg, true, 2);
        } else if (procMode == "1") {
            //送信
            getDetail(smlSid, '-1', searchFlg, true, 2)
//          } else if (procMode == "2" || (procMode != "4" && mailKbn == "2")) {
//          //草稿
//          getMessage($(this).attr('id'))
        } else if (procMode == "4" || procMode == "5") {
            //ゴミ箱
            getDetail(smlSid, mailKbn, searchFlg, true, 2)
        }


    });

    /* ページング 左  hover */
    $('.sml_page_left_btn').live({
        mouseenter:function (e) {
            $(this).addClass("sml_page_left_btn_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("sml_page_left_btn_on");
        }
    });

    /* ページング 右  hover */
    $('.sml_page_right_btn').live({
        mouseenter:function (e) {
            $(this).addClass("sml_page_right_btn_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("sml_page_right_btn_on");
        }
    });

    /* メール一覧  PDF出力 */
    $('#head_menu_list_pdf_btn').live("click", function(){
        pdfListMail();
    });

    /* メール一覧  eml出力 */
    $('#head_menu_list_eml_btn').live("click", function(){
        emlListMail();
    });

    /* メール一覧  既読にする */
    $('#head_menu_list_kidoku_btn').live("click", function(){
        kidokuListMail();
    });

    /* メール一覧 未読にする */
    $('#head_menu_list_midoku_btn').live("click", function(){
        midokuListMail();
    });

    /* メール一覧  削除ボタン */
    $('#head_menu_list_del_btn').live("click", function(){
        deleteListMail();
    });

    /* メール一覧  元に戻すボタン */
    $('#head_menu_return_btn').live("click", function(){
        revivedListMail();
    });

    /* メール一覧  ゴミ箱を空にするボタン */
    $('#head_menu_empty_trash_btn').live("click", function(){
        emptyTrash();
    });

    /* メール一覧  ラベル追加ボタン */
    $('#head_menu_list_label_add_btn').live("click", function(){
        addLabelListMail();
    });

    /* メール一覧  ラベル削除ボタン */
    $('#head_menu_list_label_del_btn').live("click", function(){
        deleteLabelListMail();
    });

    /* メール一覧  ラベル追加ポップアップ ラジオボタン変更 */
    $('input:radio[name=sml010addLabelT]').live("change", function(){
        changeAddLabelType();
    });


    /* 新規作成ボタン click */
    $('.head_menu_add_btn').live("click", function(){
        if (mail_create_flg) {
            delKakuninPopup('newMail', 0);
        } else {
            paramStr = 'CMD=getNewmail&smlViewAccount='
                +   $("#account_comb_box").val();
          //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送)
            $('input:hidden[name=sml020ProcMode]').val(0);


            $.ajax({
                async: true,
                url:  "../smail/sml020.do",
                type: "post",
                data: paramStr
            }).done(function( data ) {
                tmp_del_flg = false;
//                $('.head_menu_add_btn').click();
                    $('input:hidden[name=sml020ProcMode]').val(0);

                openSendTab();
                setCreateMail(0, data);
            }).fail(function(data){
                alert('error');
            });

        }
    });


    /* 新規作成 削除 hover */
    $('.sml_mail_del_btn_mini').live({
        mouseenter:function (e) {
            $(this).addClass("sml_mail_del_btn_mini_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("sml_mail_del_btn_mini_on");
        }
    });

    /*メール確認 閉じる hover */
    $('.sml_mail_close_btn_mini').live({
        mouseenter:function (e) {
            $(this).addClass("sml_mail_close_btn_mini_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("sml_mail_close_btn_mini_on");
        }
    });


    /* 削除ボタンフラグ */
    var del_btn_mini_flg = false;

    /* 新規作成 削除  click */
    $('.sml_mail_del_btn_mini').live("click", function(){
        delNewCreateMail($(this), 0, -1);
    });

    /* メール確認 閉じる  click */
    $('.sml_mail_close_btn_mini').live("click", function(){

        del_btn_mini_flg = true;
        $(this).parent().parent().parent().parent().parent().prev().remove();
        $(this).parent().parent().parent().parent().parent().remove();
        changeTab($('#' + now_sel_tab));
        if ($(this).attr('id') == "mail_kakunin") {
            mail_kakunin_flg = false;
        } else {
            mail_search_list_flg = false;
        }
        reloadData();

    });

    /*メール検索 閉じる hover */
    $('.sml_search_mail_close_btn_mini').live({
        mouseenter:function (e) {
            $(this).addClass("sml_mail_close_btn_mini_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("sml_mail_close_btn_mini_on");
        }
    });

    /* メール検索 閉じる  click */
    $('.sml_search_mail_close_btn_mini').live("click", function(){

        del_btn_mini_flg = true;
        $(this).parent().parent().parent().parent().parent().prev().remove();
        $(this).parent().parent().parent().parent().parent().remove();
        changeTab($('#mail_list_tab'));
        if ($(this).attr('id') == "mail_kakunin") {
            mail_kakunin_flg = false;
        } else {
            mail_search_list_flg = false;
        }

    });

    /* 上部タブ hover */
    $('.mail_area_head_hide').live({
        mouseenter:function (e) {
            $(this).addClass("mail_area_head_hide_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("mail_area_head_hide_on");
        }
    });

    /* 上部タブ click */
    $('.mail_area_head_hide').live("click", function(){

        if (!del_btn_mini_flg) {
            changeTab($(this));
        } else {
            del_btn_mini_flg = false;
            if ($(this).attr('id') == 'mail_list_tab') {
                changeTab($(this));
            }
        }

    });


    /* 添付ファイルボタン マークボタン  hover */
    $('.mail_create_bttm').live({
        mouseenter:function (e) {
            $(this).addClass("mail_create_bottom_sel_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("mail_create_bottom_sel_on");
        }
    });

    /* 添付ファイルボタン click */
    $('.mail_create_bottom_sel_tmp').live("click", function(){
        $('#mail_create_bottom_mark_area').addClass('display_none');
        $('#mail_create_bottom_tmp_area').removeClass('display_none');
        $('.mail_create_bttm').removeClass('mail_create_bottom_select');
        $(this).addClass('mail_create_bottom_select');
    });

    /* マークボタン click */
    $('.mail_create_bottom_sel_mark').live("click", function(){
        $('#mail_create_bottom_mark_area').removeClass('display_none');
        $('#mail_create_bottom_tmp_area').addClass('display_none');
        $('.mail_create_bttm').removeClass('mail_create_bottom_select');
        $(this).addClass('mail_create_bottom_select');
    });

    /* テキスト形式 hover */
    $('.mail_create_bottom_sel_text_form').live({
        mouseenter:function (e) {
            $(this).addClass("mail_create_bottom_sel_text_form_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("mail_create_bottom_sel_text_form_on");
        }
    });

    /* テキスト形式 click */
    $('.mail_create_bottom_sel_text_form').live("click", function(){
        changeSendMailType(0);
    });

    /* エクスポートリンク hover */
    $(".mail_check_body_bottom_txt").live({
        mouseenter:function (e) {
            $(this).addClass("mail_check_body_bottom_txt_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("mail_check_body_bottom_txt_on");
        }
    });

    /* 宛先名リンク hover */
    $(".atesaki_link").live({
        mouseenter:function (e) {
            $(this).addClass("atesaki_link_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("atesaki_link_on");
        }
    });

    /* アカウントクリック */
    $('.account_sel_txt').live("click", function(){

        if (mail_create_flg) {
            delNewCreateMail($('.sml_mail_del_btn_mini'), 2, $(this));
        } else {
            var accountId = $(this).attr('id');
            resetParam();
            $('select[name=smlViewAccount]').val(accountId);
            buttonPush();
        }

    });

    /* アカウント変更 */
    $('#account_comb_box').live("change", function(){

        if (mail_create_flg) {
            delNewCreateMail($('.sml_mail_del_btn_mini'), 3, -1);
        } else {
            resetParam();
            buttonPush();
        }

    });

    /* 右 click (受信)*/
    $('#menu_jushin_txt').contextMenu('context_menu1',
            {
        bindings: {
            'all_read': function(t) {
                contextAllRead(0);
            },
            'all_no_read': function(t) {
                contextAllRead(1);
            }
        }
            });


    //グループコンボ変更
    $("#sml010ChangeGrp").live("change", function(){
        var paramStr = "CMD=changeGrpData&";
        paramStr += getFormData($('#smlForm'));
        getGroupUsrData(paramStr);
    });

    //グループコンボ変更
    $("#fakeGrpButton").live("click", function(){
        $("#sml010ChangeGrp").change();
    });


    //ユーザ選択
    $('.syain_sel_check_txt').live("click", function(){
        var selUsrId = $(this).attr('id');
        var selUsrTxt = $(this).text();
        drawSelUsr(selUsrId, selUsrTxt);
    });

    //宛先、CC、BCCボタン
    $('.mail_atesaki_add_btn').live("click", function(){
        setAtesakiSelectUsr($(this).attr('id'));
    });


    //ひな形選択
    $('.hina_sel_txt').click(function(){
        var selHinaId = $(this).attr('id');
        drawSelHina(selHinaId, 0);
    });

    //ひな形ツールチップ
    $(".hina_sel_txt").mouseover(function(e){

        var txtVal = $(this).children("span.tooltips").html();
        txtVal = txtVal.replace(/\r\n/g, "<br />");
        txtVal = txtVal.replace(/(\n|\r)/g, "<br />");

        $("#tooltip_area").append("<div id=\"ttp\">"+ (txtVal) +"</div>");
        $("#ttp")
        .css("position","absolute")
        .css("top",(e.pageY) + -10 + "px")
        .css("left",(e.pageX) + 20 + "px")
        .removeClass('display_none')
        .css("filter","alpha(opacity=85)")
        .fadeIn("fast");
    }).mousemove(function(e){
        $("#ttp")
        .css("top",(e.pageY) + -10 + "px")
        .css("left",(e.pageX) + 20 + "px");
    }).mouseout(function(){
        $("#ttp").remove();
    });


    //ひな形選択(ポップアップ時)
    $('.hina_sel_txt2').click(function(){
        var selHinaId = $(this).attr('id');
        drawSelHina(selHinaId, 1);
    });

    //ひな形ツールチップ(ポップアップ時)
    $(".hina_sel_txt3").mouseover(function(e){

        var txtVal = $(this).children("span.tooltips").html();
        txtVal = txtVal.replace(/\r\n/g, "<br />");
        txtVal = txtVal.replace(/(\n|\r)/g, "<br />");

        $("#tooltip_area").append("<div id=\"ttp\">"+ (txtVal) +"</div>");
        $("#ttp")
        .css("position","absolute")
        .css("top",(e.pageY) + -10 + "px")
        .css("left",(e.pageX) + 20 + "px")
        .removeClass('display_none')
        .css("filter","alpha(opacity=85)")
        .fadeIn("fast");
    }).mousemove(function(e){
        $("#ttp")
        .css("top",(e.pageY) + -10 + "px")
        .css("left",(e.pageX) + 20 + "px");
    }).mouseout(function(){
        $("#ttp").remove();
    });



    //次へボタン
    $('#head_menu_next').live("click", function(){
        buttonPush('next');
    });

    //前へボタン
    $('#head_menu_prev').live("click", function(){
        buttonPush('next');
    });


//  //pdf出力リンク
//  $('.mail_check_body_bottom_pdf').live("click", function(){
//  buttonPush('pdf');
//  });
//
//  //eml出力リンク
//  $('.mail_check_body_bottom_eml').live("click", function(){
//  buttonPush('eml');
//  });


    //宛先,CC,BCC選択ボタン
    $(".mail_send_sel_btn").live("click", function(){

        //選択ボタン名
        var functinBtnName = $("#" + $(this).attr('id') + "Val").val();
        var functinBtnKbn = $("#" + $(this).attr('id') + "Kbn").val();
        var paramStr = "CMD=getInitData";


        paramStr += getNowSelUsr(functinBtnKbn);

        paramStr += getNowBanUsr(functinBtnKbn);

        //宛先一覧取得
        getSelAtesakiData(paramStr, functinBtnName, functinBtnKbn);

        /* テンプレートポップアップ */
        $('#atesakiSelPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 560,
            width: 750,
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
    });


    //ユーザ選択  グループコンボ変更
    $("#fakeButton").live("click", function(){
        var paramStr = "CMD=changeGrpData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });

    //ユーザ選択  グループコンボ変更
    $("#cmn120ChangeGrp").live("change", function(){
        var paramStr = "CMD=changeGrpData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });

    //ユーザ選択  ユーザ追加
    $("#adduserBtn").live("click", function(){
        var paramStr = "CMD=addUserData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });

    //ユーザ選択  ユーザ削除
    $("#deluserBtn").live("click", function(){
        var paramStr = "CMD=removeUserData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });


    //ユーザ選択  ユーザ削除
    $("#myGrpAddBtn").live("click", function(){
        var paramStr = "CMD=addMyGrpUserData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });

    //ユーザ選択  再読み込み
    $("#fakeButton2").live("click", function(){
        var paramStr = "CMD=getInitData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });

    //ユーザ選択  選択ボタンクリック
    $("#sel_usr_pop_btn").live("click", function(){
        drawPopUsr();
    });

    //選択ユーザ  削除
    $(".add_usr_del").live("click", function(){
        $(this).parent().remove();

        if ($(this).parent().attr("class") == "atesaki_to_user") {
            if (3 < $("#atesaki_to_area div").length) {
                $('#alldsp_to_area').show();
            } else {
                $('#alldsp_to_area').hide();

                $("#atesaki_area").removeClass("atesaki_scroll_on");
                $("#atesaki_area").removeClass("atesaki_scroll_area_height");
            }
        } else if ($(this).parent().attr("class") == "atesaki_cc_user") {
            if (3 < $("#atesaki_cc_area div").length) {
                $('#alldsp_cc_area').show();
            } else {
                $('#alldsp_cc_area').hide();

                $("#cc_area").removeClass("atesaki_scroll_on");
                $("#cc_area").removeClass("atesaki_scroll_area_height");
            }
        } else if ($(this).parent().attr("class") == "atesaki_bcc_user") {
            if (3 < $("#atesaki_bcc_area div").length) {
                $('#alldsp_bcc_area').show();
            } else {
                $('#alldsp_bcc_area').hide();

                $("#bcc_area").removeClass("atesaki_scroll_on");
                $("#bcc_area").removeClass("atesaki_scroll_area_height");
            }
        }
    });

    //宛先 全て表示・スクロール表示をクリック
    $("#all_dsp_to_link").live("click", function(){

        //スクロール表示だった場合
        if (dsp_to_kbn == 0) {
            $("#all_dsp_to_link").html("閉じる");
            $("#atesaki_area").removeClass("atesaki_scroll_on");
            $("#atesaki_area").removeClass("atesaki_scroll_area_height");
            dsp_to_kbn = 1;
        } else {
            $("#all_dsp_to_link").html("全て表示");
            $("#atesaki_area").addClass("atesaki_scroll_on");
            $("#atesaki_area").addClass("atesaki_scroll_area_height");
            dsp_to_kbn = 0;
        }
    });

    //宛先CC 全て表示・スクロール表示をクリック
    $("#all_dsp_cc_link").live("click", function(){

        //スクロール表示だった場合
        if (dsp_cc_kbn == 0) {
            $("#all_dsp_cc_link").html("閉じる");
            $("#cc_area").removeClass("atesaki_scroll_on");
            $("#cc_area").removeClass("atesaki_scroll_area_height");
            dsp_cc_kbn = 1;
        } else {
            $("#all_dsp_cc_link").html("全て表示");
            $("#cc_area").addClass("atesaki_scroll_on");
            $("#cc_area").addClass("atesaki_scroll_area_height");
            dsp_cc_kbn = 0;
        }
    });

    //宛先BCC 全て表示・スクロール表示をクリック
    $("#all_dsp_bcc_link").live("click", function(){

        //スクロール表示だった場合
        if (dsp_bcc_kbn == 0) {
            $("#all_dsp_bcc_link").html("閉じる");
            $("#bcc_area").removeClass("atesaki_scroll_on");
            $("#bcc_area").removeClass("atesaki_scroll_area_height");
            dsp_bcc_kbn = 1;
        } else {
            $("#all_dsp_bcc_link").html("全て表示");
            $("#bcc_area").addClass("atesaki_scroll_on");
            $("#bcc_area").addClass("atesaki_scroll_area_height");
            dsp_bcc_kbn = 0;
        }
    });

    /* ユーザ選択   グループ区分  hover*/
    jQuery('.sel_group_notcheck').live({
        mouseenter:function(){
            $(this).addClass("sel_group_notcheck_on");
        },
        mouseleave:function(){
            $(this).removeClass("sel_group_notcheck_on");
        }
    });

    /* ユーザ選択  グループ区分  変更*/
    $('.sel_group_check').live("click", function(){

        var grpKbn = $(this).attr('id');

        if (grpKbn == "groupTab") {
            $('#cmn120MyGroupSidSel').addClass("display_none");
            $('#cmn120ChangeGrpBtn').removeClass("display_none");
            $('#cmn120ChangeGrpBtn2').removeClass("display_none");
            $('#cmn120ChangeGrp').removeClass("display_none");
            $(this).removeClass("sel_group_notcheck_on");
            $(this).removeClass("sel_group_notcheck");
            $('#mygroupTab').addClass("sel_group_notcheck");
            $('#deptAccountTab').addClass("sel_group_notcheck");
            $('#cmn120DspKbn').val(1);
        } else if  (grpKbn == "mygroupTab") {
            $('#cmn120MyGroupSidSel').removeClass("display_none");
            $('#cmn120ChangeGrpBtn').addClass("display_none");
            $('#cmn120ChangeGrpBtn2').addClass("display_none");
            $('#cmn120ChangeGrp').addClass("display_none");
            $(this).removeClass("sel_group_notcheck_on");
            $(this).removeClass("sel_group_notcheck");
            $('#groupTab').addClass("sel_group_notcheck");
            $('#deptAccountTab').addClass("sel_group_notcheck");
            $('#cmn120DspKbn').val(2);
        } else if  (grpKbn == "deptAccountTab") {
            $('#cmn120MyGroupSidSel').addClass("display_none");
            $('#cmn120ChangeGrpBtn').addClass("display_none");
            $('#cmn120ChangeGrpBtn2').addClass("display_none");
            $('#cmn120ChangeGrp').addClass("display_none");
            $(this).removeClass("sel_group_notcheck_on");
            $(this).removeClass("sel_group_notcheck");
            $('#groupTab').addClass("sel_group_notcheck");
            $('#mygroupTab').addClass("sel_group_notcheck");
            $('#cmn120DspKbn').val(3);
        }

        var paramStr = "CMD=changeTabData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());

    });

    //添付ボタン
    $('input[name=attacheBtn]').live("click", function(){
        opnTemp('sml020selectFiles', 'smail', '0', '0');
    });

    //添付削除ボタン
    $('input[name=dellTmpBtn]').live("click", function(){
        deleteTmp();
    });

    //新規作成 OKボタン
    $('#head_menu_send_btn').live("click", function(){
        doMailSend();
    });

    //新規作成確認 戻るボタン
    $('#head_menu_back_kakunin_btn').live("click", function(){
        mail_create_kakunin_flg = false;
        changeHelpParam(1,1);
        $('.mail_create_kakunin_area').addClass('display_none');
        $('.mail_create_area').removeClass('display_none');
        delCreateKakuninArea();
    });

    //新規作成確認 送信ボタン
    $('#head_menu_send_kakunin_btn').live("click", function(){
        doMailSendSoushin();
    });

    //新規作成 草稿に保存ボタン
    $('#head_menu_soko_btn').live("click", function(){
        doMailSoko();
    });

    //新規作成 ひな形ボタン
    $('#head_menu_hinagata_btn').live("click", function(){
        popHinaSelDsp();
    });

    /* 新規作成 ひな形POP ひな形 hover */
    $(".hinagataSelTr").live({
        mouseenter:function (e) {
            $(this).addClass("hinagata_sel_tr_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("hinagata_sel_tr_on");
        }
    });

    /* 新規作成 ひな形  タブ hover */
    $(".hinagata_pop_head_no_sel").live({
        mouseenter:function (e) {
            $(this).addClass("hinagata_pop_head_no_sel_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("hinagata_pop_head_no_sel_on");
        }
    });

    /* 新規作成 ひな形  タブ 共通 */
    $("#hina_kyotu_tab").live("click", function(){
        $("#popHinaKyotu").removeClass('display_none');
        $("#popHinaKojin").addClass('display_none');
        $('#hina_kyotu_tab').removeClass('hinagata_pop_head_no_sel');
        $('#hina_kojin_tab').addClass('hinagata_pop_head_no_sel');
        $('#hina_kyotu_tab').removeClass('hinagata_pop_head_no_sel_on');
        $('#hina_kojin_tab').removeClass('hinagata_pop_head_no_sel_on');
    });

    /* 新規作成 ひな形  タブ 個人 */
    $("#hina_kojin_tab").live("click", function(){
        $("#popHinaKyotu").addClass('display_none');
        $("#popHinaKojin").removeClass('display_none');
        $('#hina_kyotu_tab').addClass('hinagata_pop_head_no_sel');
        $('#hina_kojin_tab').removeClass('hinagata_pop_head_no_sel');
        $('#hina_kyotu_tab').removeClass('hinagata_pop_head_no_sel_on');
        $('#hina_kojin_tab').removeClass('hinagata_pop_head_no_sel_on');
    });

    /* 新規作成 ひな形POP ひな形 click */
    $(".accountSelTr").live("click", function(){
        selAccount($(this));
    });

    //メール確認   削除ボタン
    $('#head_menu_del_btn').live("click", function(){
        deleteMail();
    });

    //メール確認   すべて削除ボタン
    $('#head_menu_alldel_btn').live("click", function(){
        deleteMailAll();
    });

    //メール確認   返信ボタン
    $('#head_menu_replay_btn').live("click", function(){
        if (!mail_create_flg) {
            //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送)
            $('input:hidden[name=sml020ProcMode]').val(1);
        }
        replayMail(1);
    });

    //メール確認   全返信ボタン
    $('#head_menu_all_replay_btn').live("click", function(){
        if (!mail_create_flg) {
            //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送)
            $('input:hidden[name=sml020ProcMode]').val(2);
        }
        replayMail(2);
    });

    //メール確認   転送ボタン
    $('#head_menu_forward_btn').live("click", function(){
        if (!mail_create_flg) {
            //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送)
            $('input:hidden[name=sml020ProcMode]').val(3);
        }
        replayMail(3);
    });

    //メール確認   複写して新規作成
    $('#head_menu_copy_btn').live("click", function(){
        if (!mail_create_flg) {
            //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送)
            $('input:hidden[name=sml020ProcMode]').val(0);
        }
        replayMail(11);
    });

    //メール確認   元に戻す
    $('#head_menu_revived_btn').live("click", function(){
        revivedMail();
    });

//  //PDF形式でエクスポート
//  $('.mail_check_body_bottom_pdf').live("click", function(){
//  doExportPdf();
//  });

    //草稿  削除ボタン
    $('#head_menu_del_soko_btn').live("click", function(){
        deleteSokoMail();
    });


    //検索ボタン
    $('#sml_search_btn').live("click", function(){
        $('#search_area_table').removeClass('display_none');
        $('input[name=sml090KeyWord]').val($('#search_text_val').val());

        $('#sml090SltGroup').val(-1);
        $('#sml090SltUser').val(-1);


        var procMode = $('input:hidden[name=sml010ProcMode]').val();
        var nextSyubetu;
        if (procMode < 5) {
            nextSyubetu = procMode;
        } else {
            nextSyubetu = 0;
        }
        if (nextSyubetu != $('input:radio[name=sml090MailSyubetsu]:checked').val()) {
            $('input:radio[name=sml090MailSyubetsu]').val([nextSyubetu]);
            $('select[name=sml090SearchSortKey1]').val(3);
            $('select[name=sml090SearchSortKey2]').val(0);
            changeSearchShubetu();
        }

        dispStyle();
        document.forms[0].CMD.value='smlSearchData';
        getSearchData();
        $('#sml090SltGroup').val(-1);
        $('#sml090SltUser').val(-1);

        $('#sml090SltGroup').val(-1);
        $('#sml090SltUser').val(-1);
    });

    //検索ボタン 詳細
    $('#head_menu_search_btn2').live("click", function(){
        //入力チェックを行う
        checkSearchKeyword();
    });

    /* 検索エリアメール種別変更*/
    $("input[name=sml090MailSyubetsu]").live("change", function(){
        dispStyle();
        changeSearchShubetu();
        $('#head_menu_search_btn2').click();
    });

    /* 検索エリア グループコンボ変更*/
    $("#sml090SltGroup").live("change", function(){
        getSearchGroupUser();
        $('#head_menu_search_btn2').click();
    });

    /*  検索エリア グループコンボ変更 */
    $("#fakeSearchGrpButton").live("click", function(){
        $("#sml090SltGroup").change();
    });

    /* 検索エリア ユーザコンボ変更*/
    $("#sml090SltUser").live("change", function(){
        $('#head_menu_search_btn2').click();
    });

    /* 検索エリア マーク変更*/
    $("input:radio[name=sml090MailMark]").live("change", function(){
        $('#head_menu_search_btn2').click();
    });

    /* 検索エリア キーワード 検索条件*/
    $("input:radio[name=sml090KeyWordkbn]").live("change", function(){
        $('#head_menu_search_btn2').click();
    });

    /* 検索エリア 検索対象*/
    $("input:checkbox[name=sml090SearchTarget]").live("change", function(){
        $('#head_menu_search_btn2').click();
    });


    /* 検索エリア 第1キー条件*/
    $("select[name=sml090SearchSortKey1]").live("change", function(){
        $('#head_menu_search_btn2').click();
    });

    /* 検索エリア 第1キー並び順*/
    $("input:radio[name=sml090SearchOrderKey1]").live("change", function(){
        $('#head_menu_search_btn2').click();
    });

    /* 検索エリア 第2キー条件*/
    $("select[name=sml090SearchSortKey2]").live("change", function(){
        $('#head_menu_search_btn2').click();
    });

    /* 検索エリア 第2キー並び順*/
    $("input:radio[name=sml090SearchOrderKey2]").live("change", function(){
        $('#head_menu_search_btn2').click();
    });

    /* 検索エリア クリアボタン クリック*/
    $('#btnSearchAtesakiClear').live("click", function(){
        clearSearchAtesaki();
        $('#head_menu_search_btn2').click();
    });

    //選択ユーザ  削除
    $(".add_search_usr_del").live("click", function(){
        $(this).parent().remove();
        $('#head_menu_search_btn2').click();
    });

    //検索エリア 前ページクリック
    $('.sml_search_page_left_btn').live("click", function(){
        document.forms[0].CMD.value='searchPrevPage';
        getSearchData();
    });

    //検索エリア 次ページクリック
    $('.sml_search_page_right_btn').live("click", function(){
        document.forms[0].CMD.value='searchNextPage';
        getSearchData();
    });

    /* 検索エリア ページング コンボ変更  上*/
    $("#sml090page1").live("change", function(){
        document.forms[0].CMD.value='changePageComboData';
        $('#sml090page2')[0].value = $('#sml090page1')[0].value;
        $('input:hidden[name=sml090page1]').val($('#sml090page1')[0].value);
        $('input:hidden[name=sml090page2]').val($('#sml090page1')[0].value);
        getSearchData();
    });

    /* 検索エリア ページング コンボ変更 下*/
    $("#sml090page2").live("change", function(){
        document.forms[0].CMD.value='changePageComboData';
        $('#sml090page1')[0].value = $('#sml090page2')[0].value;
        $('input:hidden[name=sml090page1]').val($('#sml090page2')[0].value);
        $('input:hidden[name=sml090page2]').val($('#sml090page2')[0].value);
        getSearchData();
    });

    /* 検索エリア サイズ変更*/
    $(".search_area_del_btn").live("click", function(){
        $('.search_toggle_area').toggle();
    });

    /* 検索エリア メール一覧  削除ボタン */
    $('#head_menu_search_list_del_btn').live("click", function(){
        deleteSearchListMail();
    });

    /* 検索エリア 送信者 宛先 件名 日時  hover */
    $('.mail_search_list_head_sel').live({
        mouseenter:function (e) {
            if ($(this).children().attr('id') != 'mail_from') {
                $(this).addClass("mail_list_head_on");
            }
        },
        mouseleave:function (e) {
            $(this).removeClass("mail_list_head_on");
        }
    });

    /* 検索エリア 送信者 件名 日時  click */
    $('.mail_search_list_head_sel').live("click", function(){
        if ($(this).children().attr('id') != 'mail_from') {
            onSearchTitleLinkClick($(this).children().next().attr('id'),$(this).children().next().next().attr('id'));
        }
    });

    /* 検索エリア  閉じるボタン hover */
    $(".search_area_del_btn").live({
        mouseenter:function (e) {
            $(this).addClass("search_area_del_btn_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("search_area_del_btn_on");
        }
    });

    /* メール一覧  ラベル追加ボタン */
    $('#head_menu_search_list_label_add_btn').live("click", function(){
        addLabelSearchListMail();
    });

    /* メール一覧  ラベル削除ボタン */
    $('#head_menu_search_list_label_del_btn').live("click", function(){
        deleteLabelSearchListMail();
    });

    /* メール一覧  PDF出力 */
    $('#head_menu_search_list_pdf_btn').live("click", function(){
        pdfListSearchMail();
    });

    /* メール一覧  eml出力 */
    $('#head_menu_search_list_eml_btn').live("click", function(){
        emlListSearchMail();
    });

    /* メール一覧  既読にする */
    $('#head_menu_search_list_kidoku_btn').live("click", function(){
        kidokuSearchListMail();
    });

    /* メール一覧 未読にする */
    $('#head_menu_search_list_midoku_btn').live("click", function(){
        midokuSearchListMail();
    });

    /* 他プラグインから遷移時 */
    if ($('input:hidden[name=sml010scriptFlg]').val() == 1) {

        if ($('input:hidden[name=sml010scriptKbn]').val() == 1) {
            //選択メール表示
            getDetail($('input:hidden[name=sml010SelectedSid]').val(), '-1', false, false, 0);
        } else if ($('input:hidden[name=sml010scriptKbn]').val() == 3) {
            //WEBメール共有
            replayMail(12);
        } else {

            //メール作成
            var sml010scriptSelUsrSidVal = $('#sml010scriptSelUsrSid').val();
            var sml010scriptSelUsrNameVal = $('#sml010scriptSelUsrName').val()
            var sml010scriptSelSacSidVal = $('#sml010scriptSelSacSid').val();
            var selUsrId = null;
            var selUsrTxt = null;

            if (sml010scriptSelUsrSidVal != ""
                && sml010scriptSelUsrNameVal != ""
                    && sml010scriptSelSacSidVal == "") {
                selUsrId = sml010scriptSelUsrSidVal;
                selUsrTxt = sml010scriptSelUsrNameVal;
            } else if (sml010scriptSelSacSidVal != ""
                && sml010scriptSelUsrNameVal != "") {
                selUsrId = "sac" + sml010scriptSelSacSidVal;
                selUsrTxt = sml010scriptSelUsrNameVal;
            }
            drawSelUsr(selUsrId, selUsrTxt);

            $('.scriptSelUsrParams').each(function(){
                var selUsrArray = $(this).val().split(":");
                if (selUsrArray[0] != null && selUsrArray[0] != ""
                    && selUsrArray[1] != null && selUsrArray[1] != "") {
                    drawSelUsr(selUsrArray[0], selUsrArray[1]);
                }
            });
            $('#scriptSelUsrArea').remove();
            closeloadingPop();
        }
        $('input:hidden[name=sml010scriptFlg]').val(0);
        $('input:hidden[name=sml010scriptKbn]').val(0);
        $('input:hidden[name=]sml010scriptSelUsrSid').val("");
        $('input:hidden[name=sml010scriptSelUsrName]').val("");
    }

    /* メール一覧  ラベル追加ボタン */
    $('#hogebtn').live("click", function(){
        //54
        alert($("#jmail_atesaki_cell").height());
    });

    /* 受信メール確認画面 宛先 全て表示リンククリック */
    $('#jmail_atesaki_alldisp_area').live("click", function() {

        if (jmail_dsp_to_kbn == 0) {
            $('#jmail_atesaki_area').removeClass('atesaki_scroll_on');
            $('#jmail_atesaki_area').removeClass('jmail_atesaki_scroll_area_height');
            $('#jmail_atesaki_alldisp_area').html('閉じる');
            jmail_dsp_to_kbn = 1;
        } else {
            $('#jmail_atesaki_area').addClass('atesaki_scroll_on');
            $('#jmail_atesaki_area').addClass('jmail_atesaki_scroll_area_height');
            $('#jmail_atesaki_alldisp_area').html('全て表示');
            jmail_dsp_to_kbn = 0;
        }

    });

    /* 受信メール確認画面 cc宛先 全て表示リンククリック */
    $('#jmail_cc_alldisp_area').live("click", function() {

        if (jmail_dsp_cc_kbn == 0) {
            $('#jmail_cc_area').removeClass('atesaki_scroll_on');
            $('#jmail_cc_area').removeClass('jmail_atesaki_scroll_area_height');
            $('#jmail_cc_alldisp_area').html('閉じる');
            jmail_dsp_cc_kbn = 1;
        } else {
            $('#jmail_cc_area').addClass('atesaki_scroll_on');
            $('#jmail_cc_area').addClass('jmail_atesaki_scroll_area_height');
            $('#jmail_cc_alldisp_area').html('全て表示');
            jmail_dsp_cc_kbn = 0;
        }

    });

});

//"新規作成"タブ表示
function openSendTab() {
    changeLeftMenu(0);
    if (!mail_create_flg) {

        mail_create_kakunin_flg = false;
        changeHelpParam(1,1);
        delCreateKakuninArea();

        if (tmp_del_flg) {
            deleteTmpDir();
        } else {
            tmp_del_flg = true;
        }

        $('#inputlength').html("0");

        $("#text_input").val('');

        resetAllDispLink();

        try {
            tinyMCE.get('html_input').setContent('');
        } catch (ae) {
        }

        $('#mail_list_tab').after('<td class=\"mail_area_head_space\" style=\"width:5px;\"><table style=\"width:5px;\" class=\"mail_area_head_space_table\"><tr><td></td></tr></table></td>'
                + '<td id=\"mail_create_tab\" class=\"mail_area_head2\">'
                + '<table><tr><td class=\"sml_mail_create_tab\" nowrap>'
                + '新規作成'
                + '</td>'
                + '<td class="padding_right_0">'
                + '<div class=\"sml_mail_del_btn_mini\"></div>'
                + '</div>'
                + '</td>'
                + '</tr></table>'
                + '</td>');

        $('.mail_list_area').addClass('display_none');
        $('.mail_search_list_area').addClass('display_none');
        $('.mail_kakunin_area').addClass('display_none');
        $('.mail_area_head').addClass('mail_area_head_hide');

        $('.mail_create_area').removeClass('display_none');
        $('.mail_create_kakunin_area').addClass('display_none');

        $(this).removeClass('mail_area_head_hide');

        mail_create_flg = true;

        changeTab($('#mail_create_tab'));

        changeSendMailType(1);

    } else {
        $('#mail_create_tab').click();
    }
}


//検索エリア  メール種別変更
function changeSearchShubetu() {

    $('#atesaki_search_area').children().remove();
    $('#sml090SltGroup').val(-1);
    $('#sml090SltUser').val(-1);

    document.forms[0].CMD.value='changeMailShubetu';
    //フォームデータ成形
    var formData = getFormData($('#smlForm'));

    //データ取得
    $.ajax({
        async: true,
        url:"../smail/sml090.do",
        type: "post",
        data:formData
    }).done(function( data ) {

        try {
            if (data != null && data.sml090SortKeyLabelList.length > 0) {

                $('select[name=sml090SearchSortKey1]').children().remove();
                $('select[name=sml090SearchSortKey2]').children().remove();

                for (i = 0; i < data.sml090SortKeyLabelList.length; i++) {

                    var optStr = "<option value=\""
                        + data.sml090SortKeyLabelList[i].value
                        + "\">"
                        + data.sml090SortKeyLabelList[i].label
                        + "</option>";

                    $('select[name=sml090SearchSortKey1]').append(optStr);
                    $('select[name=sml090SearchSortKey2]').append(optStr);

                }
                $('select[name=sml090SearchSortKey1]').val(3);
                $('select[name=sml090SearchSortKey2]').val(0);
            }
        } catch (ae) {
            alert(ae);
        }

    }).fail(function(data){
        alert('error');
    });

}


//検索エリア 選択ユーザ削除
function clearSearchAtesaki() {
    $('#atesaki_search_area').children().remove();
}

//検索結果データ取得
function getSearchGroupUser() {

    document.forms[0].CMD.value='getSearchGrpUsr';
    //フォームデータ成形
    var formData = getFormData($('#smlForm'));

    //データ取得
    $.ajax({
        async: true,
        url:"../smail/sml010.do",
        type: "post",
        data:formData
    }).done(function( data ) {

        try {

            $('select[name=sml090SltUser]').children().remove();
            var optStr = "";
            if (data != null && data.sml090UserLabel != null && data.sml090UserLabel.length > 0) {

                for (i = 0; i < data.sml090UserLabel.length; i++) {
                    var usrLabelMdl = data.sml090UserLabel[i];
                    optStr += "<option value=\""
                        + usrLabelMdl.value
                        + "\">"
                        + usrLabelMdl.label
                        + "</option>";
                }
            }

            $('select[name=sml090SltUser]').append(optStr);

        } catch (ae) {
            alert(ae);
        }

    }).fail(function(data){
        alert('error');
    });

}

//検索エリア ソート
function onSearchTitleLinkClick(fid, order) {

    document.forms[0].CMD.value='changeSortData';
    $('select[name=sml090SearchSortKey1]').val(fid);
    $('input:radio[name=sml090SearchOrderKey1]').val([order]);
    $('input:hidden[name=sml090page1]').val(1);
    $('input:hidden[name=sml090page2]').val(1);
    $('#head_menu_search_btn2').click();
}


//検索結果データ取得
function getSearchData() {

    loadingPop("読み込み中");

    //一覧ヘッド削除
    $('#mail_search_list_head_tr').remove();
    //一覧削除
    $('#mail_search_list_draw_table').children().remove();
    //ページング削除
    $("#sml_search_page_top_area").children().remove();

    //フォームデータ成形
    var formData = getFormData($('#smlForm'));

    //データ取得
    $.ajax({
        async: true,
        url:"../smail/sml090.do",
        type: "post",
        data:formData
    }).done(function( data ) {

        try {

            if (data.sml090SearchResultList != null && data.sml090SearchResultList.length > 0) {
                for (i = 0; i < data.sml090SearchResultList.length; i++) {
                    var mailData = data.sml090SearchResultList[i];
                    //alert(mailData.smsTitle);
                }

                //hidden項目設定
                $('#svAtesakiArea').children().remove();
                $('#svTargetArea').children().remove();
                $('input:hidden[name=sml090SvSltGroup]').val(data.sml090SvSltGroup);
                $('input:hidden[name=sml090SvSltUser]').val(data.sml090SvSltUser);
                $('input:hidden[name=sml090SvMailSyubetsu]').val(data.sml090SvMailSyubetsu);
                $('input:hidden[name=sml090SvMailMark]').val(data.sml090SvMailMark);
                $('input:hidden[name=sml090SvKeyWord]').val(data.sml090SvKeyWord);
                $('input:hidden[name=sml090SvKeyWordkbn]').val(data.sml090SvKeyWordkbn);
                $('input:hidden[name=sml090SvSearchOrderKey1]').val(data.sml090SvSearchOrderKey1);
                $('input:hidden[name=sml090SvSearchSortKey1]').val(data.sml090SvSearchSortKey1);
                $('input:hidden[name=sml090SvSearchOrderKey2]').val(data.sml090SvSearchOrderKey2);
                $('input:hidden[name=sml090SvSearchSortKey2]').val(data.sml090SvSearchSortKey2);
                if (data.sml090page1 > 0) {
                    $('input:hidden[name=sml090page1]').val(data.sml090page1);
                } else {
                    $('input:hidden[name=sml090page1]').val(1);
                }

                if (data.sml090page2 > 0) {
                    $('input:hidden[name=sml090page2]').val(data.sml090page2);
                } else {
                    $('input:hidden[name=sml090page2]').val(1);
                }


                if (data.sml090SvAtesaki != null && data.sml090SvAtesaki.length > 0) {
                    for (sva = 0; sva < data.sml090SvAtesaki.length; sva++) {
                        $('#svAtesakiArea').append("<input type=\"hidden\" name=\"sml090SvAtesaki\" value=\"" + data.sml090SvAtesaki[sva]+ "\">");
                    }
                }

                if (data.sml090SvSearchTarget != null && data.sml090SvSearchTarget.length > 0) {
                    for (svt = 0; svt < data.sml090SvSearchTarget.length; svt++) {
                        $('#svAtesakiArea').append("<input type=\"hidden\" name=\"sml090SvSearchTarget\" value=\"" + data.sml090SvSearchTarget[svt]+ "\">");
                    }
                }

                /* 一覧タイトル  */
                var listHeadStr = "";

                listHeadStr += "<tr id=\"mail_search_list_head_tr\" style=\"clear:both;\">"
                    +  "<td width=\"3%\" class=\"mail_list_head_checkbox\">"
                    +  "<input style=\"cursor:pointer;\" type=\"checkbox\" name=\"allSearchCheck\" onClick=\"changeSearchChk();\">"
                    +  "</td>"
                    +  "<td width=\"3%\" class=\"mail_list_head\">&nbsp;"
                    +  "</td>"
                    +  "<td width=\"17%\" class=\"mail_list_head mail_search_list_head_sel\">";


                //送信者・宛先
                if (data.sml090SvMailSyubetsu == "0") {
                    if (data.sml090SvSearchSortKey1 == "2") {
                        if (data.sml090SvSearchOrderKey1 == "0") {
                            listHeadStr += "<a href=\"#\"><span class=\"\">送信者▲</span></a>";
                            listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                            listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                        }
                        if (data.sml090SvSearchOrderKey1 == "1") {
                            listHeadStr += "<a href=\"#\"><span class=\"\">送信者▼</span></a>";
                            listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                            listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                        }
                    }
                    if (data.sml090SvSearchSortKey1 != "2") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">送信者</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                    }
                }

                if (data.sml090SvMailSyubetsu == "4") {
                    if (data.sml090SvSearchSortKey1 == "2") {
                        if (data.sml090SvSearchOrderKey1 == "0") {
                            listHeadStr += "<a href=\"#\"><span class=\"\">送信者▲</span></a>";
                            listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                            listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                        }
                        if (data.sml090SvSearchOrderKey1 == "1") {
                            listHeadStr += "<a href=\"#\"><span class=\"\">送信者▼</span></a>";
                            listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                            listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                        }
                    }
                    if (data.sml090SvSearchSortKey1 != "2") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">送信者</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                    }
                }

                if (data.sml090SvMailSyubetsu != "0") {
                    if (data.sml090SvMailSyubetsu != "4") {
                        listHeadStr += "<span id=\"mail_from\">宛先</span>";
                    }
                }

                listHeadStr += "</td>";


                //件名
                listHeadStr += "<td width=\"56%\" class=\"mail_list_head mail_search_list_head_sel\">";


                if (data.sml090SvSearchSortKey1 == "1") {
                    if (data.sml090SvSearchOrderKey1 == "0") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">件名▲</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                    }
                    if (data.sml090SvSearchOrderKey1 == "1") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">件名▼</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                    }
                }

                if (data.sml090SvSearchSortKey1 != "1") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">件名</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                }
                listHeadStr += "</td>";


                //サイズ
                listHeadStr += "<td width=\"8%\" class=\"mail_list_head mail_search_list_head_sel\">";
                if (data.sml090SvSearchSortKey1 == "5") {
                    if (data.sml090SvSearchOrderKey1 == "0") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">サイズ▲</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"5\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                    }
                    if (data.sml090SvSearchOrderKey1 == "1") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">サイズ▼</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"5\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                    }
                }

                if (data.sml090SvSearchSortKey1 != "5") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">サイズ</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"5\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                }
                listHeadStr += "</td>";


                //日時
                listHeadStr += "<td width=\"13%\" class=\"mail_list_head mail_search_list_head_sel\">";
                if (data.sml090SvSearchSortKey1 == "3") {
                    if (data.sml090SvSearchOrderKey1 == "0") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">日時▲</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"3\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                    }
                    if (data.sml090SvSearchOrderKey1 == "1") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">日時▼</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"3\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                    }
                }

                if (data.sml090SvSearchSortKey1 != "3") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">日時</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"3\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                }
                listHeadStr += "</td>";
                listHeadStr += "</tr>";

                $('#mail_search_list_head_menu_area').append(listHeadStr);


                /* メール一覧 */
                var listStr = "";

                for (i = 0; i < data.sml090SearchResultList.length; i++) {

                    var smlMdl = data.sml090SearchResultList[i];
                    var mod = 0;
                    listStr +=  "<tr id=\"search_list_line\" class=\"mail_list_line\">";

                    //チェックボックス
                    listStr +=  "<td width=\"3%\" class=\"mail_list_content_checkbox mail_list_content_left_border\">"
                        +   "<input class=\"syain_checkbox\" type=\"checkbox\" name=\"sml090DelSid\" value=\""
                        +   smlMdl.mailKey
                        +   "\" />"
                        +   "</td>";


                    //未読・返信・転送
                    listStr +=  "<td width=\"3%\" id=\""
                        +   smlMdl.smlSid
                        +   "\" class=\"mail_list_content mail_list_content_center\">";

                    if (data.sml090SvMailSyubetsu == "0" || (smlMdl.mailKbn == "0" && data.sml090SvMailSyubetsu == "4")) {
                        if (smlMdl.smjOpkbn == "0") {

                            listStr +=  "<table width=\"100%\" class=\"clear_table\"><tr><td width=\"100%\" align=\"center\">"
                                +   "<div class=\"mail_no_read\"></div>"
                                +   "</td></tr></table>";
                        }

                        if (smlMdl.fwKbn != "0") {
                            listStr +=  "<img alt=\"Fw\" width=\"15px\" src=\"../smail/images/img_forward.gif\" class=\"img_bottom\">";
                        }

                        if (smlMdl.returnKbn != "0") {
                            listStr +=  "<img alt=\"Re\" width=\"15px\" src=\"../smail/images/img_henshin.gif\" class=\"img_bottom\">";
                        }
                    }
                    listStr +=  "</td>";


                    //件名カラー
                    var titleColor = "";
                    if (data.sml090SvMailSyubetsu == "0" || (smlMdl.mailKbn == "0" && data.sml090SvMailSyubetsu == "4")) {
                        if (smlMdl.smjOpkbn == "0") {
                            titleColor="text_blue_120 text_bold";
                        }
                        if (smlMdl.smjOpkbn == "1") {
                            titleColor="text_p_120";
                        }
                    } else {
                        titleColor = "text_p_120";
                    }

                    //送信者・宛先
                    listStr +=  "<td width=\"17%\" id=\""
                        +   smlMdl.smlSid
                        +   "\" class=\"mail_list_content\">"
                        +   "<table class=\"clear_table\">"
                        +   "<tr>"
                        +   "<td style=\"padding-left:10px;padding-right:3px;padding-top:0px;padding-bottom:0px;\">";

                    var nameColor = "";
                    if (data.sml090SvMailSyubetsu == "0") {

                        if (smlMdl.smjOpkbn == "0") {
                            nameColor="text_p";
                        }
                        if (smlMdl.smjOpkbn == "1") {
                            nameColor="text_p";
                        }
                    } else {
                        nameColor = "text_p";
                    }

                    //受信
                    if (data.sml090SvMailSyubetsu == "0") {
                        if (data.photoSearchDspFlg == "0") {

                            listStr +=  "<table>"
                                +   "<tr>"
                                +   "<td align=\"left\" nowrap>";


                            if (data.photoDspFlg=="0"){
                                //写真表示する
                                if (smlMdl.photoFileDsp == "1") {
                                    listStr +=  "<div align=\"center\" class=\"photo_hikokai4\"><span style=\"color:#fc2929;\">非公</span></div>";
                                }

                                if (smlMdl.photoFileDsp == "0") {


                                    if (smlMdl.binFileSid == "0") {
                                        listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                            +   smlMdl.smlSid
                                            +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                            +   smlMdl.smlSid
                                            +   "');\">";
                                    }

                                    if (smlMdl.binFileSid != "0") {
                                        if (smlMdl.usrJkbn == "9" || smlMdl.usrJkbn == "1") {
                                            listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                                +   smlMdl.smlSid
                                                +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                                +   smlMdl.smlSid
                                                +   "');\">";
                                        } else {
                                            listStr +=  "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                                +   smlMdl.binFileSid
                                                +   "&smlViewAccount="
                                                +   $("#account_comb_box").val()
                                                +   "\" name=\"userImage"
                                                +   smlMdl.smlSid
                                                +   "\" alt=\"ユーザ\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                                +   smlMdl.smlSid
                                                +   "');\">";
                                        }
                                    }
                                }
                            }
                            listStr +=  "</td></tr></table>";
                        }
                    }


                    //ゴミ箱
                    if (data.sml090SvMailSyubetsu == "4") {
                        if (data.photoSearchDspFlg == "0") {

                            listStr +=  "<table><tr><td align=\"left\" nowrap>";

                            if (data.photoDspFlg=="0"){
                                //写真表示する
                                if (smlMdl.photoFileDsp == "1") {
                                    listStr +=  "<div align=\"center\" class=\"photo_hikokai4\"><span style=\"color:#fc2929;\">非公</span></div>";
                                }

                                if (smlMdl.photoFileDsp == "0") {

                                    if (smlMdl.binFileSid == "0") {
                                        listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                            +   smlMdl.smlSid
                                            +   "\" alt=\"写真\" width=\"25px\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                            +   smlMdl.smlSid
                                            +   "');\">";
                                    }

                                    if (smlMdl.binFileSid != "0") {

                                        if (smlMdl.usrJkbn == "9" || smlMdl.usrJkbn == "1") {
                                            listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                                +   smlMdl.smlSid
                                                +   "\" alt=\"写真\" width=\"25px\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                                +   smlMdl.smlSid
                                                +   "');\">";
                                        } else {
                                            listStr +=  "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                                +   smlMdl.binFileSid
                                                +   "&smlViewAccount="
                                                +   $("#account_comb_box").val()
                                                +   "\" name=\"userImage"
                                                +   smlMdl.smlSid
                                                +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                                +   smlMdl.smlSid
                                                +   "');\">";
                                        }
                                    }
                                }
                            }
                            listStr +=  "</td></tr></table>";
                        }
                    }

                    //送信、草稿
                    if (data.sml090SvMailSyubetsu != "0") {
                        if (data.sml090SvMailSyubetsu != "4") {
                            if (data.photoSearchDspFlg == "0") {
                                if (data.sml090SvMailSyubetsu != "2") {
                                    if (smlMdl.atesakiList != null) {

                                        var listSize = smlMdl.atesakiList.length;
                                        listStr +=  "<table><tr><td align=\"left\" nowrap>";


                                        for (n = 0; n < listSize; n++) {

                                            if (listSize < 2) {

                                                var atesaki = smlMdl.atesakiList[n];

                                                if (data.photoDspFlg=="0"){
                                                    //写真表示する
                                                    if (atesaki.photoFileDsp == "1") {
                                                        listStr +=  "<div align=\"center\" class=\"photo_hikokai4\"><span style=\"color:#fc2929;\">非公</span></div>";
                                                    }

                                                    if (atesaki.photoFileDsp == "0") {



                                                        if (atesaki.binFileSid == "0") {
                                                            listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                                                +   smlMdl.smlSid
                                                                +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                                                +   smlMdl.smlSid
                                                                +   "');\">";
                                                        }

                                                        if (atesaki.binFileSid != "0") {

                                                            if (atesaki.usrJkbn == "9") {
                                                                listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                                                    +   smlMdl.smlSid
                                                                    +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                                                    +   smlMdl.smlSid
                                                                    +   "');\">";
                                                            } else {
                                                                listStr +=  "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                                                    +   atesaki.binFileSid
                                                                    +   "&smlViewAccount="
                                                                    +   $("#account_comb_box").val()
                                                                    +   "\" name=\"userImage"
                                                                    +   smlMdl.smlSid
                                                                    +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                                                    +   smlMdl.smlSid
                                                                    +   "');\">";
                                                            }
                                                        }
                                                    }
                                                }
                                                listStr +=  "<br>";
                                            }
                                        }
                                        listStr +=  "</td></tr></table>";
                                    }
                                }
                            }
                        }
                    }


                    listStr +=  "</td><td>";


                    //ユーザ名 受信・ゴミ箱
                    if (smlMdl.atesakiList != null) {
                        if (data.sml090SvMailSyubetsu != "1" && data.sml090SvMailSyubetsu != "2") {

                            listStr +=  "<div><span class=\"text_mail_list_username\">";

                            if (smlMdl.usrSid > 0) {
                                if (smlMdl.usrJkbn == "0") {
                                    listStr +=  htmlEscape(smlMdl.usiSei)
                                    +   "&nbsp;&nbsp;"
                                    +   htmlEscape(smlMdl.usiMei);
                                }

                                if (smlMdl.usrJkbn == "9" || smlMdl.usrJkbn == "1") {

                                    listStr +=  "<del>"
                                        +   htmlEscape(smlMdl.usiSei)
                                        +   "&nbsp;&nbsp;"
                                        +   htmlEscape(smlMdl.usiMei)
                                        +   "</del>";
                                }
                            } else {
                                if (smlMdl.accountJkbn == "0") {
                                    listStr +=  htmlEscape(smlMdl.accountName);
                                }

                                if (smlMdl.accountJkbn == "1") {
                                    listStr +=  "<del>"
                                        +   htmlEscape(smlMdl.accountName)
                                        +   "</del>";
                                }
                            }


                            listStr +=  "</span></div>";

                        }
                    }


                    //ユーザ名 送信・草稿
                    if (smlMdl.atesakiList != null) {

                        var listSize = smlMdl.atesakiList.length;
                        listStr +=  "<div>";

                        var atesakiNameStr = "";

                        var cutFlg = 0;
                        if (listSize > 3) {
                            cutFlg = 1;
                        }

                        for (l = 0; l < listSize; l++) {

                            var atesakiMdl = smlMdl.atesakiList[l];

                            atesakiNameStr +=  "<span class=\"text_mail_list_username\">";

                            if (atesakiMdl.usrSid > 0) {
                                if (atesakiMdl.usrJkbn == "0") {
                                    atesakiNameStr +=  htmlEscape(atesakiMdl.usiSei)
                                    +   "&nbsp;&nbsp;"
                                    +   htmlEscape(atesakiMdl.usiMei);

                                    if (l != listSize - 1) {
                                        if ((cutFlg == 0) || (cutFlg != 0 && l != 2)) {
                                            atesakiNameStr +=  ";";
                                        }
                                        atesakiNameStr +=  "&nbsp;";
                                    }

                                }

                                if (atesakiMdl.usrJkbn == "9") {
                                    atesakiNameStr +=  "<del>"
                                        +   atesakiMdl.usiSei
                                        +   "&nbsp;&nbsp;"
                                        +   atesakiMdl.usiMei
                                        +   "</del>";

                                    if (l != listSize - 1) {
                                        if ((cutFlg == 0) || (cutFlg != 0 && l != 2)) {
                                            atesakiNameStr +=  ";";
                                        }
                                    }
                                }
                            } else {
                                if (atesakiMdl.accountJkbn == "0") {
                                    atesakiNameStr +=  htmlEscape(atesakiMdl.accountName);

                                    if (l != listSize - 1) {
                                        if ((cutFlg == 0) || (cutFlg != 0 && l != 2)) {
                                            atesakiNameStr +=  ";";
                                        }
                                        atesakiNameStr +=  "&nbsp;";
                                    }

                                }

                                if (atesakiMdl.accountJkbn == "1") {
                                    atesakiNameStr +=  "<del>"
                                        +   atesakiMdl.accountName
                                        +   "</del>";

                                    if (l != listSize - 1) {
                                        if ((cutFlg == 0) || (cutFlg != 0 && l != 2)) {
                                            atesakiNameStr +=  ";";
                                        }
                                    }
                                }
                            }


                            atesakiNameStr +=  "</span>";

                            if (l == 2 && cutFlg == 1) {
                                atesakiNameStr +=  "…";
                                break;
                            }
                        }

                        listStr +=  atesakiNameStr;
                        listStr +=  "</div>";
                    }

                    listStr +=  "</td></tr></table></td>";

                    listStr +=  "<td width=\"56%\" id=\""
                        +   smlMdl.smlSid
                        +   "\" class=\"mail_list_content mail_list_content_left mail_list_content_title\">"
                        +   "<input type=\"hidden\" id=\"mailkbn_"
                        +   smlMdl.smlSid
                        +   "\" value=\""
                        +   smlMdl.mailKbn
                        +   "\" />";

                    //次のメールのSID取得
                    if (i < data.sml090SearchResultList.length) {
                        var nextNum = i + 1;
                        var nextSmlMdl = data.sml090SearchResultList[nextNum];
                        if (nextSmlMdl != null) {
                            listStr +=   "<input type=\"hidden\" id=\"searchNextmail_"
                                +   smlMdl.smlSid
                                +   "\" value=\""
                                +   nextSmlMdl.smlSid
                                +   "\" />"
                                +   "<input type=\"hidden\" id=\"searchNextmailKbn_"
                                +   smlMdl.smlSid
                                +   "\" value=\""
                                +   nextSmlMdl.mailKbn
                                +   "\" />";
                        }
                    }

                    //前のメールのSID取得
                    if (i != 0 && data.sml090SearchResultList.length > 1) {
                        var prevNum = i - 1;
                        var prevSmlMdl = data.sml090SearchResultList[prevNum];
                        if (prevSmlMdl != null) {
                            listStr +=   "<input type=\"hidden\" id=\"searchPrevmail_"
                                +   smlMdl.smlSid
                                +   "\" value=\""
                                +   prevSmlMdl.smlSid
                                +   "\" />"
                                +   "<input type=\"hidden\" id=\"searchPrevmailKbn_"
                                +   smlMdl.smlSid
                                +   "\" value=\""
                                +   prevSmlMdl.mailKbn
                                +   "\" />";
                        }
                    }


                    //マーク
                    var imgMark = smlMdl.smsMark;
                    listStr +=  "<span>";

                    var imgId = imgMark;
                    var imgStr = "";

                    if ($('#markKey_' + imgId).attr('id') != null) {
                        imgStr = $('#markKey_' + imgId).val();
                    }

                    listStr +=  imgStr;
                    listStr +   "</span>";

                    //添付
                    listStr +=  "<span>";
                    if (smlMdl.binCnt != 0) {
                        listStr +=  "<img alt=\"添付ファイル\" src=\"../smail/images/temp_file.gif\" class=\"img_bottom\">";
                    }
                    listStr +=  "</span>";

                    //ラベル
                    if (smlMdl.labelList != null && smlMdl.labelList.length > 0) {
                        listStr +=  "<span class=\"mailLabel\">";

                        for (l = 0; l < smlMdl.labelList.length; l++) {

                            if (l != 0) {
                                listStr +=  ",";
                            }

                            var lblMdl = smlMdl.labelList[l];
                            listStr +=  lblMdl.slbName;
                        }

                        listStr +=  "</span>";
                    }

                    //件名
                    if (data.sml090SvMailSyubetsu == "0") {
                        listStr +=  "<a href=\"#\"><span class=\""
                            +   titleColor
                            +   " mail_list_title\">"
                            +   smlMdl.smsTitle
                            +   "</span></a>";
                    }

                    if (data.sml090SvMailSyubetsu == "1") {
                        listStr +=  "<a href=\"#\"><span class=\""
                            +   titleColor
                            +   " mail_list_title\">"
                            +   smlMdl.smsTitle
                            +   "</span></a>";
                    }

                    if (data.sml090SvMailSyubetsu == "2") {
                        listStr +=  "<a href=\"#\"><span class=\""
                            +   titleColor
                            +   " mail_list_title\">"
                            +   smlMdl.smsTitle
                            +   "</span></a>";
                    }

                    if (data.sml090SvMailSyubetsu == "4") {
                        listStr +=  "<a href=\"#\"><span class=\""
                            +   titleColor
                            +   " mail_list_title\">";

                        if (smlMdl.mailKbn == "0") {
                            listStr +=  "[受信]";
                        }
                        if (smlMdl.mailKbn == "1") {
                            listStr +=  "[送信]";
                        }
                        if (smlMdl.mailKbn == "2") {
                            listStr +=  "[草稿]";
                        }

                        listStr +=   smlMdl.smsTitle
                        +   "</span></a>";
                    }

                    listStr +=  "</td>";


                    //容量
                    listStr +=  "<td width=\"8%\" id=\""
                        +   smlMdl.smlSid
                        +   "\" class=\"mail_list_content mail_list_content_left mail_list_content_title\">"
                        +   "<div class=\"text_base_mini\" style=\"\">"
                        +   smlMdl.smlSizeStr
                        +   "</div></td>"


                        //日時
                        listStr +=  "<td width=\"13%\" id=\""
                            +   smlMdl.smlSid
                            +   "\" class=\"mail_list_content mail_list_content_right_border\">"
                            +   "<div class=\"text_base_mini\" style=\"padding-left:13px;\"><div class=\"clear_div\">"
                            +   smlMdl.strSdate.substring(0, 11)
                            +   "</div><div class=\"clear_div\" style=\"padding-left:12px;\">"
                            +   smlMdl.strSdate.substring(11)
                            +   "</div></div></td></tr>";

                    $('#mail_search_list_draw_table').append(listStr);
                    listStr ="";
                }


                //ページ 上
                var pageStr = "";
                pageStr +=  "<div style=\"float:left;\">";
                var count1 = data.smlPageLabel.length;

                if (count1 > 1) {
                    pageStr +=  "<table><tr><td align=\"right\"><div class=\"sml_search_page_left_btn\"></div></td><td align=\"right\">";
                    pageStr +=  "<select id=\"sml090page1\" name=\"sml090page1\" class=\"text_i\">";

                    for (p = 0; p < count1; p++) {
                        var labelValue = data.smlPageLabel[p];
                        pageStr +=  "<option value=\""
                            +   labelValue.value
                            +   "\">"
                            +   labelValue.label
                            +   "</option>";
                    }

                    pageStr +=  "</select>";

                    pageStr +=  "</td><td align=\"right\"><div class=\"sml_search_page_right_btn\"></div></td></tr></table>";
                }

                pageStr +=  "<span style=\"clear:both;\"></span></div>";

                $("#sml_search_page_top_area").append(pageStr);


                //ページ 下
                listStr +=  "<tr><td colspan=\"6\" align=\"right\" style=\"padding-top:3px;\"><div align=\"right\" style=\"folat:left;\"><div style=\"float:right;\">";
                var count2 = data.smlPageLabel.length;
                if (count2 > 1) {

                    listStr +=  "<table><tr><td align=\"right\"><div class=\"sml_search_page_left_btn\"></div></td><td align=\"right\">";
                    listStr +=  "<select id=\"sml090page2\" name=\"sml090page2\" class=\"text_i\">";

                    for (a = 0; a < count2; a++) {
                        var labelValue = data.smlPageLabel[a];
                        listStr +=  "<option value=\""
                            +   labelValue.value
                            +   "\">"
                            +   labelValue.label
                            +   "</option>";
                    }

                    listStr +=  "</select>";

                    listStr +=  "</td><td align=\"right\"><div class=\"sml_search_page_right_btn\"></div></td></tr></table>";
                }
                listStr +=  "</div><span style=\"clear:both;\"></span></div></td></tr>";

                //html出力
                $('#mail_search_list_draw_table').append(listStr);

                if (!mail_search_list_flg) {
                    $('#mail_list_tab').after('<td class=\"mail_area_head_space\" style=\"width:5px;\"><table class=\"mail_area_head_space_table\"><tr><td></td></tr></table></td></td>'
                            + '<td id=\"mail_search_list_tab\" class=\"mail_area_head2\">'
                            + '<table><tr><td class=\"sml_mail_search_list_tab\" nowrap>'
                            + '検索結果'
                            + '</td>'
                            + '<td class="padding_right_0">'
                            + '<div id=\"mail_search\" class=\"sml_search_mail_close_btn_mini\"></div>'
                            + '</td>'
                            + '</tr></table>'
                            + '</td>');

                    $('.mail_list_area').addClass('display_none');
                    $('.mail_create_area').addClass('display_none');
                    $('.mail_create_kakunin_area').addClass('display_none');
                    $('.mail_kakunin_area').addClass('display_none');
                    $('.mail_area_head').addClass('mail_area_head_hide');
                    $('.mail_search_list_area').removeClass('display_none');
                    $(this).removeClass('mail_area_head_hide');

                    mail_search_list_flg = true;
                    changeTab($('#mail_search_list_tab'));

                } else {
                    if (document.forms[0].CMD.value != 'reloadSearchData'
                        && document.forms[0].CMD.value != 'getLabelData') {
                        $('#mail_search_list_tab').click();
                    }
                }

                //ページ設定
                if ($("#sml090page1").attr('id') != null) {

                    if (data.sml090page1 > 0) {
                        $("#sml090page1").val(data.sml090page1);
                    } else {
                        $("#sml090page1").val(1);
                    }
                }
                if ($("#sml090page2").attr('id') != null) {
                    if (data.sml090page1 > 0) {
                        $("#sml090page2").val(data.sml090page1);
                    } else {
                        $("#sml090page2").val(1);
                    }

                }

                //チェックボックスリセット
                document.forms[0].allSearchCheck.checked = false;

                //未読メール件数設定
                $('#midoku_txt').html("");
                if (data.sml010MidokuCnt != 0) {
                    $('#midoku_txt').append("(" + data.sml010MidokuCnt + ")");
                }
                //草稿メール件数設定
                $('#soko_txt').html("");
                if (data.sml010SokoCnt != 0) {
                    $('#soko_txt').append("(" + data.sml010SokoCnt + ")");
                }
                //未読メール件数(ゴミ箱)設定
                $('#gomi_txt').html("");
                $('#kara_area').html("");
                if (data.sml010GomiMidokuCnt != 0) {
                    $('#gomi_txt').append("(" + data.sml010GomiMidokuCnt + ")");
                }
                $('#kara_area').append('　[<span class="all_del_txt" id="head_menu_empty_trash_btn">空にする</span>]');

                //ラベル再描画
                resetLabelArea();

                //ディスク容量設定
                $('#disk_use').html("");
                $('#disk_use').append(data.sml010AccountDisk);

            } else {

                if (!mail_search_list_flg) {
                    $('#mail_list_tab').after('<td class=\"mail_area_head_space\" style=\"width:5px;\"><table class=\"mail_area_head_space_table\"><tr><td></td></tr></table></td></td>'
                            + '<td id=\"mail_search_list_tab\" class=\"mail_area_head2\">'
                            + '<table><tr><td class=\"sml_mail_search_list_tab\" nowrap>'
                            + '検索結果'
                            + '</td>'
                            + '<td class="padding_right_0">'
                            + '<div id=\"mail_search\" class=\"sml_search_mail_close_btn_mini\"></div>'
                            + '</td>'
                            + '</tr></table>'
                            + '</td>');

                    $('.mail_list_area').addClass('display_none');
                    $('.mail_create_area').addClass('display_none');
                    $('.mail_create_kakunin_area').addClass('display_none');
                    $('.mail_kakunin_area').addClass('display_none');
                    $('.mail_area_head').addClass('mail_area_head_hide');
                    $('.mail_search_list_area').removeClass('display_none');
                    $(this).removeClass('mail_area_head_hide');

                    mail_search_list_flg = true;
                    changeTab($('#mail_search_list_tab'));

                } else {
                    if (document.forms[0].CMD.value != 'reloadSearchData'
                        && document.forms[0].CMD.value != 'getLabelData') {
                        $('#mail_search_list_tab').click();
                    }
                }

                $('#mail_search_list_draw_table').append("<span class=\"errorMsgStr\">" + data.errorsList[0] + "</span>");
            }

        } catch (ae) {
            alert(ae);
        } finally {
            closeloadingPop();
        }

    }).fail(function(data){
        closeloadingPop();
        alert('error');
    });

}



function deleteSearchListMail() {

    document.forms[0].CMD.value='msg_deleteData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;

    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsgStr += data.errorsList[e];
            }
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                deleteMailPop(data, 5);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function doDeleteSearchListMail() {

    document.forms[0].CMD.value='deleteDataOk';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    var checked = false;
    $('input:checkbox[name=sml090DelSid]:checked').each(function(){
        checked = true;
    });
    if (!checked) {
        messagePop(errMsg_noselect_reload, 400, 150);
        return;
    }

    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.messageList != null && data.messageList.length > 0) {
            messagePop(data.messageList[0], 350, 160);
            reloadData();
        }
    }).fail(function(data){
        alert('error');
    });
}


function changeLeftMenu(mode) {

    //0:新規作成
    //1:新規作成以外

    if (mode == 0) {
        if ($("#sml_account_area").children().hasClass("menu_head_sel")) {
            $("#sml_account_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($("#sml_account_area").children());
        }

        if ($("#sml_mailbox_area").children().hasClass("menu_head_sel")) {
            $("#sml_mailbox_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($("#sml_mailbox_area").children());
        }

        if ($("#sml_shain_area").children().hasClass("menu_head_not_sel")) {
            $("#sml_shain_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($("#sml_shain_area").children());
        }

        if ($("#sml_hina_kyotu_area").children().hasClass("menu_head_not_sel")) {
            $("#sml_hina_kyotu_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($("#sml_hina_kyotu_area").children());
        }

        if ($("#sml_hina_kojin_area").children().hasClass("menu_head_not_sel")) {
            $("#sml_hina_kojin_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($("#sml_hina_kojin_area").children());
        }

    } else {
        if ($("#sml_account_area").children().hasClass("menu_head_not_sel")) {
            $("#sml_account_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($("#sml_account_area").children());
        }

        if ($("#sml_mailbox_area").children().hasClass("menu_head_not_sel")) {
            $("#sml_mailbox_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($("#sml_mailbox_area").children());
        }

        if ($("#sml_hina_kyotu_area").children().hasClass("menu_head_sel")) {
            $("#sml_hina_kyotu_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($("#sml_hina_kyotu_area").children());
        }

        if ($("#sml_hina_kojin_area").children().hasClass("menu_head_sel")) {
            $("#sml_hina_kojin_child_area").animate( { height: 'toggle', opacity: 'toggle' }, 'middle' );
            changeSelImg($("#sml_hina_kojin_area").children());
        }
    }

}


function changeSelImg(id) {
    if (id.hasClass("menu_head_not_sel")) {
        id.removeClass("menu_head_not_sel");
        id.addClass("menu_head_sel");
    } else {
        id.removeClass("menu_head_sel");
        id.addClass("menu_head_not_sel");
    }
}

function changeTab(elm) {

    $('.mail_area_head').addClass('mail_area_head_hide');
    $('.mail_area_head2').addClass('mail_area_head_hide');
    $('.mail_area_head').removeClass('mail_area_head_hide_on');
    $('.mail_area_head2').removeClass('mail_area_head_hide_on');
    elm.removeClass('mail_area_head_hide');

    if (elm.attr('id') == 'mail_list_tab') {
        //メール一覧タブ
        changeLeftMenu(1);
        $('.mail_create_area').addClass('display_none');
        $('.mail_create_kakunin_area').addClass('display_none');
        $('.mail_kakunin_area').addClass('display_none');
        $('.mail_search_list_area').addClass('display_none');
        $('.mail_list_area').removeClass('display_none');
        $('.sml_search_box_area').removeClass('display_none');
        $('#search_area_table').addClass('display_none');
        now_sel_tab = 'mail_list_tab';


        changeHelpParam($('input:hidden[name=sml010ProcMode]').val(), 0);

    } else if (elm.attr('id') == 'mail_create_tab') {
        //新規作成タブ
        changeLeftMenu(0);
        $('.mail_list_area').addClass('display_none');
        $('.mail_kakunin_area').addClass('display_none');
        $('.mail_search_list_area').addClass('display_none');
        $('#search_area_table').addClass('display_none');
        $('.sml_search_box_area').removeClass('display_none');

        if (mail_create_kakunin_flg) {
            $('.mail_create_kakunin_area').removeClass('display_none');
            $('.mail_create_area').addClass('display_none');
        } else {
            $('.mail_create_kakunin_area').addClass('display_none');
            $('.mail_create_area').removeClass('display_none');
        }

        changeHelpParam($('input:hidden[name=sml010ProcMode]').val(), 1);

    } else if (elm.attr('id') == 'mail_kakunin_tab') {
        //確認タブ
        changeLeftMenu(1);
        $('.mail_list_area').addClass('display_none');
        $('.mail_search_list_area').addClass('display_none');
        $('.mail_create_area').addClass('display_none');
        $('.mail_create_kakunin_area').addClass('display_none');
        $('.mail_kakunin_area').removeClass('display_none');
        $('.sml_search_box_area').removeClass('display_none');
        $('#search_area_table').addClass('display_none');

        changeHelpParam($('input:hidden[name=sml010ProcMode]').val(), 2);

    } else if (elm.attr('id') == 'mail_search_list_tab') {
        //検索結果タブ
        changeLeftMenu(1);
        $('.mail_list_area').addClass('display_none');
        $('.mail_create_area').addClass('display_none');
        $('.mail_create_kakunin_area').addClass('display_none');
        $('.mail_kakunin_area').addClass('display_none');
        $('.sml_search_box_area').addClass('display_none');
        $('.mail_search_list_area').removeClass('display_none');
        $('#search_area_table').removeClass('display_none');
        now_sel_tab = 'mail_search_list_tab';

        changeHelpParam($('input:hidden[name=sml010ProcMode]').val(), 3);
    }
}

function delNewCreateMail(id, kbn, elm) {
    if (kbn == 0 || kbn == 2 || kbn == 3) {

        $('#delKakuninPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height:160,
            width: 400,
            modal: true,
            overlay: {
                backgroundColor: '#000000',
                opacity: 0.5
            },
            buttons: {
                はい: function() {
                    //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送 4:草稿)
                    $('input:hidden[name=sml020ProcMode]').val(0);

                    if (kbn == 2) {
                        var accountId = elm.attr('id');
                        resetParam();
                        $('select[name=smlViewAccount]').val(accountId);
                        buttonPush();
                    } else if (kbn == 3) {
                        resetParam();
                        buttonPush();
                    } else {
                        deleteNewCreateMail(id)
                        $(this).dialog('close');
                    }
                },
                いいえ: function() {

                    if (kbn == 3) {
                        $('select[name=smlViewAccount]').val($('#smlViewAccountSv').val());
                    }
//                  $('.head_menu_add_btn').click();
                    openSendTab();
                    $(this).dialog('close');
                }
            }
        });

    } else {
        deleteNewCreateMail(id);
    }
}

function deleteNewCreateMail(id) {
    changeLeftMenu(1);
    del_btn_mini_flg = true;
    id.parent().parent().parent().parent().parent().prev().remove();
    id.parent().parent().parent().parent().parent().remove();
    resetMailCreate();
    changeTab($('#mail_list_tab'));
    mail_create_flg = false;
    mail_create_kakunin_flg = false;
    changeHelpParam(1,1);
    delCreateKakuninArea();

    dsp_to_kbn = 0;
    dsp_cc_kbn = 0;
    dsp_bcc_kbn = 0;
}

var reloading_flg = false;
function reloadData() {

    if (!reloading_flg) {

        var dialodIsOpenDel = $('#delMailMsgPop').dialog("isOpen");
        var dialodIsOpenLabelAdd = $('#labelAddPop').dialog("isOpen");
        var dialodIsOpenLabelDel = $('#labelDelPop').dialog("isOpen");
        //確認メッセージロード中は更新しない
        if (load_kakunin_message_flg) {
            return;
        }
        //ダイアログが表示されていた場合は更新しない
        if (dialodIsOpenDel != true
                && dialodIsOpenLabelAdd != true
                && dialodIsOpenLabelDel != true) {

            document.forms[0].CMD.value='getInitData';
            getMailData();

            if(mail_search_list_flg) {
                document.forms[0].CMD.value='reloadSearchData';
                getSearchData();
            }
        }
    }

}

var html_input_flg = false;
function changeSendMailType(kbn) {


    if (kbn != 0) {
        //ユーザのデフォルトの形式
        var sendMailType = $('input:hidden[name=sml010AccountSendMailType]').val();
        if (sendMailType == 0) {
            $("#text_input_area").addClass('display_none');
            $("#html_input_area").removeClass('display_none');
            $("#text_text").removeClass('display_none');
            $("#text_html").addClass('display_none');
        } else {
            $("#text_input_area").removeClass('display_none');
            $("#html_input_area").addClass('display_none');
            $("#text_text").addClass('display_none');
            $("#text_html").removeClass('display_none');
        }
    }

    if ($("#html_input_area").hasClass('display_none')) {
        $("#html_input_area").removeClass('display_none');
        $("#text_input_area").addClass('display_none');
        $("#text_count_area").addClass('display_none');
    } else {
        $("#text_input_area").removeClass('display_none');
        $("#text_count_area").removeClass('display_none');
        $("#html_input_area").addClass('display_none');
    }

    if (!html_input_flg) {
        tinyMceInit();
    }

    if ($("#text_html").hasClass('display_none')) {
        $("#text_text").addClass('display_none');
        $("#text_html").removeClass('display_none');
        $('input:hidden[name=sml020MailType]').val(0);

        var htmlAreaStr = "";
        if (tinyMCE.get('html_input') != null) {


//          tinyMCE.get('html_input').selection.select(tinyMCE.get('html_input').getBody(), true);
//          htmlAreaStr = tinyMCE.get('html_input').selection.getContent({format : 'text'});

//          tinyMCE.activeEditor.selection.select(tinyMCE.activeEditor.dom.select('p')[0]);
//          htmlAreaStr = tinyMCE.activeEditor.selection.getContent({format : 'text'});

//          try {
            htmlAreaStr = tinyMCE.activeEditor.getContent({format : 'text'});
//          } catch (ae) {
//          }

        }

        if (htmlAreaStr != null && htmlAreaStr.length > 0 && htmlAreaStr != "\n") {
            $("#text_input").val(htmlAreaStr);
            $('#inputlength').html(htmlAreaStr.length);
        }

    } else {
        $("#text_html").addClass('display_none');
        $("#text_text").removeClass('display_none');
        $('input:hidden[name=sml020MailType]').val(1);
        setCopyTextAreaStr();
    }

}

function setCopyTextAreaStr() {
    if ($("#text_input").val() != "" && $("#text_input").val() != null) {
//      try {
        tinyMCE.get('html_input').setContent(textBr(htmlEscape($("#text_input").val())));
//      } catch (ae) {
//      tinyMceInit();
//      tinyMCE.get('html_input').setContent(textBr(htmlEscape($("#text_input").val())));
//      }
    }
    $('html,body').scrollTop(0);
}

function setTextHtmlAreaStr() {

    tinyMCE.get('html_input').setContent($('input:hidden[name=sml020BodyHtml]').val());
    $('html,body').scrollTop(0);




}

function tinyMceInit() {

    tinyMCE.init({
        // General options
        mode : "exact",
        elements : "html_input",
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
    });
    setTimeout('setCopyTextAreaStr()', 200);
    html_input_flg = true;

}


//初期データ取得
function getMailData() {

    reloading_flg = true;

    //一覧ヘッド削除
    $('#mail_list_head_tr').remove();
    //一覧削除
    $('#mail_list_draw_table').children().remove();
    //ページング削除
    $("#sml_page_top_area").children().remove();

    //フォームデータ成形
    var formData = getFormData($('#smlForm'));

    //データ取得
    $.ajax({
        async: true,
        url:"../smail/sml010.do",
        type: "post",
        data:formData
    }).done(function( data ) {

        try {

            /* 一覧タイトル  */
            var listHeadStr = "";

            listHeadStr += "<tr id=\"mail_list_head_tr\" style=\"clear:both;\">"
                +  "<td width=\"3%\" class=\"mail_list_head_checkbox\">"
                +  "<input style=\"cursor:pointer;\" type=\"checkbox\" name=\"allCheck\" onClick=\"changeChk();\">"
                +  "</td>"
                +  "<td width=\"3%\" class=\"mail_list_head\">&nbsp;"
                +  "</td>"
                +  "<td width=\"17%\" class=\"mail_list_head mail_list_head_sel\">";


            //送信者・宛先
            if (data.sml010ProcMode == "0") {
                if (data.sml010Sort_key == "2") {
                    if (data.sml010Order_key == "0") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">送信者▲</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                    }
                    if (data.sml010Order_key == "1") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">送信者▼</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                    }
                }
                if (data.sml010Sort_key != "2") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">送信者</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                }
            }

            if (data.sml010ProcMode == "4" || data.sml010ProcMode == "5") {
                if (data.sml010Sort_key == "2") {
                    if (data.sml010Order_key == "0") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">送信者▲</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                    }
                    if (data.sml010Order_key == "1") {
                        listHeadStr += "<a href=\"#\"><span class=\"\">送信者▼</span></a>";
                        listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                        listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                    }
                }
                if (data.sml010Sort_key != "2") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">送信者</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"2\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                }
            }

            if (data.sml010ProcMode != "0") {
                if (data.sml010ProcMode != "4" && data.sml010ProcMode != "5") {
                    listHeadStr += "<span id=\"mail_from\">宛先</span>";
                }
            }

            listHeadStr += "</td>";


            //件名
            listHeadStr += "<td width=\"56%\" class=\"mail_list_head mail_list_head_sel\">";
            if (data.sml010Sort_key == "1") {
                if (data.sml010Order_key == "0") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">件名▲</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                }
                if (data.sml010Order_key == "1") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">件名▼</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                }
            }

            if (data.sml010Sort_key != "1") {
                listHeadStr += "<a href=\"#\"><span class=\"\">件名</span></a>";
                listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                listHeadStr += "<input type=\"hidden\" id=\"0\" />";
            }
            listHeadStr += "</td>";


            //サイズ
            listHeadStr += "<td width=\"8%\" class=\"mail_list_head mail_list_head_sel\">";
            if (data.sml010Sort_key == "5") {
                if (data.sml010Order_key == "0") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">サイズ▲</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"5\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                }
                if (data.sml010Order_key == "1") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">サイズ▼</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"5\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                }
            }

            if (data.sml010Sort_key != "5") {
                listHeadStr += "<a href=\"#\"><span class=\"\">サイズ</span></a>";
                listHeadStr += "<input type=\"hidden\" id=\"5\" />";
                listHeadStr += "<input type=\"hidden\" id=\"0\" />";
            }
            listHeadStr += "</td>";


            //日時
            listHeadStr += "<td width=\"13%\" class=\"mail_list_head mail_list_head_sel\">";
            if (data.sml010Sort_key == "3") {
                if (data.sml010Order_key == "0") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">日時▲</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"3\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"1\" />";
                }
                if (data.sml010Order_key == "1") {
                    listHeadStr += "<a href=\"#\"><span class=\"\">日時▼</span></a>";
                    listHeadStr += "<input type=\"hidden\" id=\"3\" />";
                    listHeadStr += "<input type=\"hidden\" id=\"0\" />";
                }
            }

            if (data.sml010Sort_key != "3") {
                listHeadStr += "<a href=\"#\"><span class=\"\">日時</span></a>";
                listHeadStr += "<input type=\"hidden\" id=\"3\" />";
                listHeadStr += "<input type=\"hidden\" id=\"0\" />";
            }
            listHeadStr += "</td>";
            listHeadStr += "</tr>";

            $('#mail_list_head_menu_area').append(listHeadStr);


            /* メール一覧 */
            var listStr = "";

            for (i = 0; i < data.sml010SmlList.length; i++) {

                var smlMdl = data.sml010SmlList[i];
                var mod = 0;
                listStr +=  "<tr class=\"mail_list_line\">";

                //チェックボックス
                listStr +=  "<td width=\"3%\" class=\"mail_list_content_checkbox mail_list_content_left_border\">"
                    +   "<input class=\"syain_checkbox\" type=\"checkbox\" name=\"sml010DelSid\" value=\""
                    +   smlMdl.mailKey
                    +   "\" />"
                    +   "</td>";


                //既読・未読
                var mailReadClass = "mail_list_content_read";
                if (data.sml010ProcMode == "0" || (data.sml010ProcMode == "5" && smlMdl.mailKbn == "0") || (data.sml010ProcMode == "4" && smlMdl.mailKbn == "0")) {
                    if (smlMdl.smjOpkbn == "0") {
                        mailReadClass="mail_list_content_no_read";
                    }
                    if (smlMdl.smjOpkbn == "1") {
                        mailReadClass="mail_list_content_read";
                    }
                }

                //未読・返信・転送
                listStr +=  "<td width=\"3%\" id=\""
                    +   smlMdl.smlSid
                    +   "\" class=\"mail_list_content "
                    +   mailReadClass
                    +   " mail_list_content_center\">";

                if (data.sml010ProcMode == "0" || (data.sml010ProcMode == "5" && smlMdl.mailKbn == "0") || (data.sml010ProcMode == "4" && smlMdl.mailKbn == "0")) {
                    if (smlMdl.smjOpkbn == "0") {

                        listStr +=  "<table width=\"100%\" class=\"clear_table\"><tr><td width=\"100%\" align=\"center\">"
                            +   "<div class=\"mail_no_read\"></div>"
                            +   "</td></tr></table>";
                    }

                    if (smlMdl.fwKbn != "0") {
                        listStr +=  "<img alt=\"Fw\" width=\"15px\" src=\"../smail/images/img_forward.gif\" class=\"img_bottom\">";
                    }

                    if (smlMdl.returnKbn != "0") {
                        listStr +=  "<img alt=\"Re\" width=\"15px\" src=\"../smail/images/img_henshin.gif\" class=\"img_bottom\">";
                    }
                }
                listStr +=  "</td>";

                //件名カラー
                var titleColor = "";
                if (data.sml010ProcMode == "0" || (data.sml010ProcMode == "5" && smlMdl.mailKbn == "0") || (data.sml010ProcMode == "4" && smlMdl.mailKbn == "0")) {
                    if (smlMdl.smjOpkbn == "0") {
                        titleColor="text_blue_120 text_bold";
                    }
                    if (smlMdl.smjOpkbn == "1") {
                        titleColor="text_p_120";
                    }
                } else {
                    titleColor = "text_p_120";
                }



                //送信者・宛先
                listStr +=  "<td width=\"17%\" id=\""
                    +   smlMdl.smlSid
                    +   "\" class=\"mail_list_content "
                    +   mailReadClass
                    +  "\">"
                    +   "<table class=\"clear_table\">"
                    +   "<tr>"
                    +   "<td style=\"padding-left:10px;padding-right:3px;padding-top:0px;padding-bottom:0px;\">";

                var nameColor = "";
                if (data.sml010ProcMode == "0") {

                    if (smlMdl.smjOpkbn == "0") {
                        nameColor="text_p";
                    }
                    if (smlMdl.smjOpkbn == "1") {
                        nameColor="text_p";
                    }
                } else {
                    nameColor = "text_p";
                }

                //受信
                if (data.sml010ProcMode == "0") {
                    if (data.photoDspFlg == "0") {

                        listStr +=  "<table>"
                            +   "<tr>"
                            +   "<td align=\"left\" nowrap>";


                        if (data.photoDspFlg=="0"){
                            //写真表示する
                            if (smlMdl.photoFileDsp == "1") {
                                listStr +=  "<div align=\"center\" class=\"photo_hikokai4\"><span style=\"color:#fc2929;\">非公</span></div>";
                            }

                            if (smlMdl.photoFileDsp == "0") {


                                if (smlMdl.binFileSid == "0") {
                                    listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                        +   smlMdl.smlSid
                                        +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                        +   smlMdl.smlSid
                                        +   "');\">";
                                }

                                if (smlMdl.binFileSid != "0") {
                                    if (smlMdl.usrJkbn == "9" || smlMdl.usrJkbn == "1") {
                                        listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                            +   smlMdl.smlSid
                                            +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                            +   smlMdl.smlSid
                                            +   "');\">";

                                    } else {

                                        listStr +=  "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                            +   smlMdl.binFileSid
                                            +   "&smlViewAccount="
                                            +   $("#account_comb_box").val()
                                            +   "\" name=\"userImage"
                                            +   smlMdl.smlSid
                                            +   "\" alt=\"ユーザ\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                            +   smlMdl.smlSid
                                            +   "');\">";
                                    }

                                }
                            }
                        }
                        listStr +=  "</td></tr></table>";
                    }
                }


                //ゴミ箱
                if (data.sml010ProcMode == "4" || data.sml010ProcMode == "5") {
                    if (data.photoDspFlg == "0") {

                        listStr +=  "<table><tr><td align=\"left\" nowrap>";

                        if (data.photoDspFlg=="0"){
                            //写真表示する
                            if (smlMdl.photoFileDsp == "1") {
                                listStr +=  "<div align=\"center\" class=\"photo_hikokai4\"><span style=\"color:#fc2929;\">非公</span></div>";
                            }

                            if (smlMdl.photoFileDsp == "0") {

                                if (smlMdl.binFileSid == "0") {
                                    listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                        +   smlMdl.smlSid
                                        +   "\" alt=\"写真\" width=\"25px\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                        +   smlMdl.smlSid
                                        +   "');\">";
                                }

                                if (smlMdl.binFileSid != "0") {
                                    if (smlMdl.usrJkbn == "9" || smlMdl.usrJkbn == "1") {
                                        listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                            +   smlMdl.smlSid
                                            +   "\" alt=\"写真\" width=\"25px\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                            +   smlMdl.smlSid
                                            +   "');\">";
                                    } else {
                                        listStr +=  "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                            +   smlMdl.binFileSid
                                            +   "&smlViewAccount="
                                            +   $("#account_comb_box").val()
                                            +   "\" name=\"userImage"
                                            +   smlMdl.smlSid
                                            +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                            +   smlMdl.smlSid
                                            +   "');\">";
                                    }

                                }
                            }
                        }
                        listStr +=  "</td></tr></table>";
                    }
                }



                //送信、草稿
                if (data.sml010ProcMode != "0") {
                    if (data.sml010ProcMode != "4") {
                        if (data.photoDspFlg == "0") {
                            if (data.sml010ProcMode != "2") {
                                if (smlMdl.atesakiList != null) {

                                    var listSize = smlMdl.atesakiList.length;
                                    listStr +=  "<table><tr><td align=\"left\" nowrap>";


                                    for (n = 0; n < listSize; n++) {

                                        if (listSize < 2) {

                                            var atesaki = smlMdl.atesakiList[n];

                                            if (data.photoDspFlg=="0"){
                                                //写真表示する
                                                if (atesaki.photoFileDsp == "1") {
                                                    listStr +=  "<div align=\"center\" class=\"photo_hikokai4\"><span style=\"color:#fc2929;\">非公</span></div>";
                                                }

                                                if (atesaki.photoFileDsp == "0") {



                                                    if (atesaki.binFileSid == "0") {
                                                        listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                                            +   smlMdl.smlSid
                                                            +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                                            +   smlMdl.smlSid
                                                            +   "');\">";
                                                    }

                                                    if (atesaki.binFileSid != "0") {
                                                        if (atesaki.usrJkbn == "9") {
                                                            listStr +=  "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                                                +   smlMdl.smlSid
                                                                +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                                                +   smlMdl.smlSid
                                                                +   "');\">";

                                                        } else {
                                                            listStr +=  "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                                                +   atesaki.binFileSid
                                                                +   "&smlViewAccount="
                                                                +   $("#account_comb_box").val()
                                                                +   "\" name=\"userImage"
                                                                +   smlMdl.smlSid
                                                                +   "\" alt=\"写真\" border=\"1\" width=\"25px\" onload=\"initImageView50('userImage"
                                                                +   smlMdl.smlSid
                                                                +   "');\">";
                                                        }

                                                    }
                                                }
                                            }
//                                          listStr +=  "<br>";
                                        }
                                    }
                                    listStr +=  "</td></tr></table>";
                                }
                            }
                        }
                    }
                }


                listStr +=  "</td><td>";


                //ユーザ名 受信・ゴミ箱
                if (smlMdl.atesakiList != null) {
                    if (data.sml010ProcMode != "1" && data.sml010ProcMode != "2") {
                        listStr +=  "<div><span class=\"text_mail_list_username\">";

                        if (smlMdl.usrSid > 0) {
                            if (smlMdl.usrJkbn == "0") {
                                listStr +=  htmlEscape(smlMdl.usiSei)
                                +   "&nbsp;&nbsp;"
                                +   htmlEscape(smlMdl.usiMei);
                            }

                            if (smlMdl.usrJkbn == "9" || smlMdl.usrJkbn == "1") {

                                listStr +=  "<del>"
                                    +   htmlEscape(smlMdl.usiSei)
                                    +   "&nbsp;&nbsp;"
                                    +   htmlEscape(smlMdl.usiMei)
                                    +   "</del>";
                            }
                        } else {
                            if (smlMdl.accountJkbn == "0") {

                                listStr +=  htmlEscape(smlMdl.accountName);
                            }

                            if (smlMdl.accountJkbn == "1") {

                                listStr +=  "<del>"
                                    +   htmlEscape(smlMdl.accountName)
                                    +   "</del>";
                            }
                        }


                        listStr +=  "</span></div>";

                    }
                }


                //ユーザ名 送信・草稿
                if (smlMdl.atesakiList != null) {

                    var listSize = smlMdl.atesakiList.length;
                    listStr +=  "<div>";

                    var atesakiNameStr = "";

                    var cutFlg = 0;
                    if (listSize > 3) {
                        cutFlg = 1;
                    }

                    for (l = 0; l < listSize; l++) {

                        var atesakiMdl = smlMdl.atesakiList[l];

                        atesakiNameStr +=  "<span class=\"text_mail_list_username\">";

                        if (atesakiMdl.usrSid > 0) {
                            if (atesakiMdl.usrJkbn == "0") {
                                atesakiNameStr +=  htmlEscape(atesakiMdl.usiSei)
                                +   "&nbsp;&nbsp;"
                                +   htmlEscape(atesakiMdl.usiMei);

                                if (l != listSize - 1) {
                                    if ((cutFlg == 0) || (cutFlg != 0 && l != 2)) {
                                        atesakiNameStr +=  ";";
                                    }
                                    atesakiNameStr +=  "&nbsp;";
                                }

                            }

                            if (atesakiMdl.usrJkbn == "9") {
                                atesakiNameStr +=  "<del>"
                                    +   htmlEscape(atesakiMdl.usiSei)
                                    +   "&nbsp;&nbsp;"
                                    +   htmlEscape(atesakiMdl.usiMei)
                                    +   "</del>";

                                if (l != listSize - 1) {
                                    if ((cutFlg == 0) || (cutFlg != 0 && l != 2)) {
                                        atesakiNameStr +=  ";";
                                    }
                                }
                            }
                        } else {
                            if (atesakiMdl.accountJkbn == "0") {
                                atesakiNameStr +=  htmlEscape(atesakiMdl.accountName);

                                if (l != listSize - 1) {
                                    if ((cutFlg == 0) || (cutFlg != 0 && l != 2)) {
                                        atesakiNameStr +=  ";";
                                    }
                                    atesakiNameStr +=  "&nbsp;";
                                }

                            }

                            if (atesakiMdl.accountJkbn == "1") {
                                atesakiNameStr +=  "<del>"
                                    +   htmlEscape(atesakiMdl.accountName)
                                    +   "</del>";

                                if (l != listSize - 1) {
                                    if ((cutFlg == 0) || (cutFlg != 0 && l != 2)) {
                                        atesakiNameStr +=  ";";
                                    }
                                }
                            }
                        }


                        atesakiNameStr +=  "</span>";

                        if (l == 2 && cutFlg == 1) {
                            atesakiNameStr +=  "…";
                            break;
                        }

                    }

                    listStr +=  atesakiNameStr;
                    listStr +=  "</div>";
                }

                listStr +=  "</td></tr></table></td>";

                listStr +=  "<td width=\"56%\" id=\""
                    +   smlMdl.smlSid
                    +   "\" class=\"mail_list_content "
                    +   mailReadClass
                    + " mail_list_content_left mail_list_content_title\">"
                    +   "<input type=\"hidden\" id=\"mailkbn_"
                    +   smlMdl.smlSid
                    +   "\" value=\""
                    +   smlMdl.mailKbn
                    +   "\" />";


                //マーク
                var imgMark = smlMdl.smsMark;
                listStr +=  "<span>";

                var imgId = imgMark;
                var imgStr = "";

                if ($('#markKey_' + imgId).attr('id') != null) {
                    imgStr = $('#markKey_' + imgId).val();
                }

                listStr +=  imgStr;
                listStr +   "</span>";

                //添付
                listStr +=  "<span>";
                if (smlMdl.binCnt != 0) {
                    listStr +=  "<img alt=\"添付ファイル\" src=\"../smail/images/temp_file.gif\" class=\"img_bottom\">";
                }
                listStr +=  "</span>";

                //ラベル
                if (smlMdl.labelList != null && smlMdl.labelList.length > 0) {
                    listStr +=  "<span class=\"mailLabel\">";

                    for (l = 0; l < smlMdl.labelList.length; l++) {

                        if (l != 0) {
                            listStr +=  ",";
                        }

                        var lblMdl = smlMdl.labelList[l];
                        listStr +=  lblMdl.slbName;
                    }

                    listStr +=  "</span>";
                }


                //件名
                if (data.sml010ProcMode == "0") {
                    listStr +=  "<a href=\"#\"><span class=\""
                        +   titleColor
                        +   " mail_list_title\">"
                        +   smlMdl.smsTitle
                        +   "</span></a>";
                }

                if (data.sml010ProcMode == "1") {
                    listStr +=  "<a href=\"#\"><span class=\""
                        +   titleColor
                        +   " mail_list_title\">"
                        +   smlMdl.smsTitle
                        +   "</span></a>";
                }

                if (data.sml010ProcMode == "2") {
                    listStr +=  "<a href=\"#\"><span class=\""
                        +   titleColor
                        +   " mail_list_title\">"
                        +   smlMdl.smsTitle
                        +   "</span></a>";
                }

                if (data.sml010ProcMode == "4" || data.sml010ProcMode == "5") {
                    listStr +=  "<a href=\"#\"><span class=\""
                        +   titleColor
                        +   " mail_list_title\">";

                    if (smlMdl.mailKbn == "0") {
                        listStr +=  "[受信]";
                    }
                    if (smlMdl.mailKbn == "1") {
                        listStr +=  "[送信]";
                    }
                    if (smlMdl.mailKbn == "2") {
                        listStr +=  "[草稿]";
                    }

                    listStr +=   smlMdl.smsTitle
                    +   "</span></a>";
                }

                listStr +=  "</td>";


                //容量
                listStr +=  "<td width=\"8%\" id=\""
                    +   smlMdl.smlSid
                    +   "\" class=\"mail_list_content "
                    +   mailReadClass
                    + " mail_list_content_left mail_list_content_title\">"
                    +   "<div class=\"text_base_mini\" style=\"\">"
                    +   smlMdl.smlSizeStr
                    +   "</div></td>"


                    //日時
                    listStr +=  "<td width=\"13%\" id=\""
                        +   smlMdl.smlSid
                        +   "\" class=\"mail_list_content "
                        +   mailReadClass
                        + " mail_list_content_right_border\">"
                        +   "<div class=\"text_base_mini\" style=\"padding-left:13px;\"><div class=\"clear_div\">"
                        +   smlMdl.strSdate.substring(0, 11)
                        +   "</div><div class=\"clear_div\" style=\"padding-left:12px;\">"
                        +   smlMdl.strSdate.substring(11)
                        +   "</div></div></td></tr>";

                $('#mail_list_draw_table').append(listStr);
                listStr ="";
            }


            //ページ 上
            var pageStr = "";
            pageStr +=  "<div style=\"float:left;\">";
            var count1 = data.sml010PageLabel.length;

            if (count1 > 1) {
                pageStr +=  "<table><tr><td align=\"right\"><div class=\"sml_page_left_btn\"></div></td><td align=\"right\">";
                pageStr +=  "<select id=\"sml010Slt_page1\" name=\"sml010Slt_page1\" class=\"text_i\">";

                for (p = 0; p < count1; p++) {
                    var labelValue = data.sml010PageLabel[p];
                    pageStr +=  "<option value=\""
                        +   labelValue.value
                        +   "\">"
                        +   labelValue.label
                        +   "</option>";
                }

                pageStr +=  "</select>";

                pageStr +=  "</td><td align=\"right\"><div class=\"sml_page_right_btn\"></div></td></tr></table>";
            }

            pageStr +=  "<span style=\"clear:both;\"></span></div>";

            $("#sml_page_top_area").append(pageStr);


            //ページ 下
            listStr +=  "<tr><td colspan=\"6\" align=\"right\" style=\"padding-top:3px;\"><div align=\"right\" style=\"folat:left;\"><div style=\"float:right;\">";
            var count2 = data.sml010PageLabel.length;
            if (count2 > 1) {

                listStr +=  "<table><tr><td align=\"right\"><div class=\"sml_page_left_btn\"></div></td><td align=\"right\">";
                listStr +=  "<select id=\"sml010Slt_page2\" name=\"sml010Slt_page2\" class=\"text_i\">";

                for (a = 0; a < count2; a++) {
                    var labelValue = data.sml010PageLabel[a];
                    listStr +=  "<option value=\""
                        +   labelValue.value
                        +   "\">"
                        +   labelValue.label
                        +   "</option>";
                }

                listStr +=  "</select>";

                listStr +=  "</td><td align=\"right\"><div class=\"sml_page_right_btn\"></div></td></tr></table>";
            }
            listStr +=  "</div><span style=\"clear:both;\"></span></div></td></tr>";

            //html出力
            $('#mail_list_draw_table').append(listStr);

            //ページ設定
            $('input:hidden[name=sml010PageNum]').val(data.sml010PageNum);
            if ($("#sml010Slt_page1").attr('id') != null) {
                $("#sml010Slt_page1").val(data.sml010PageNum);
            }
            if ($("#sml010Slt_page2").attr('id') != null) {
                $("#sml010Slt_page2").val(data.sml010PageNum);
            }

            //ソート設定
            $('input:hidden[name=sml010Sort_key]').val(data.sml010Sort_key);
            $('input:hidden[name=sml010Order_key]').val(data.sml010Order_key);

            //チェックボックスリセット
            document.forms[0].allCheck.checked = false;

            //未読メール件数設定
            $('#midoku_txt').html("");
            if (data.sml010MidokuCnt != 0) {
                $('#midoku_txt').append("(" + data.sml010MidokuCnt + ")");
            }
            //草稿メール件数設定
            $('#soko_txt').html("");
            if (data.sml010SokoCnt != 0) {
                $('#soko_txt').append("(" + data.sml010SokoCnt + ")");
            }
            //未読メール件数(ゴミ箱)設定
            $('#gomi_txt').html("");
            $('#kara_area').html("");
            if (data.sml010GomiMidokuCnt != 0) {
                $('#gomi_txt').append('(' + data.sml010GomiMidokuCnt + ')');
            }

            $('#kara_area').append('　[<span class="all_del_txt" id="head_menu_empty_trash_btn">空にする</span>]');



            //ラベル再描画
            resetLabelArea();

            //ディスク容量設定
            $('#disk_use').html("");
            $('#disk_use').append(data.sml010AccountDisk);

            if (data.sml010ProcMode == "0") {
                /* 右 click (メール行) 既読にする*/
                $('.mail_list_content_no_read').contextMenu('context_menu2',
                        {
                    bindings: {
                        'mail_read': function(t) {
                            contextRead(0, t);
                        }
                    }
                        });
                /* 右 click (メール行) 未読にする*/
                $('.mail_list_content_read').contextMenu('context_menu3',
                        {
                    bindings: {
                        'mail_no_read': function(t) {
                            contextRead(1, t);
                        }
                    }
                        });
            }
            //送信先制限設定
            banGroupStr = "";
            if (data.sml010disableGroupSid != null && data.sml010disableGroupSid != "") {
                for (i = 0; i < data.sml010disableGroupSid.length; i++) {
                    banGroupStr += "<input type=\"hidden\" name=\"sml010disableGroupSid\" value=\""
                        +   data.sml010disableGroupSid[i] + "\">";
                }
            }
            $('#sml010disableGroupSidArea').children().remove();
            $('#sml010disableGroupSidArea').append(banGroupStr);
            banUserStr = "";
            if (data.sml010banUserSid != null && data.sml010banUserSid != "") {
                for (i = 0; i < data.sml010banUserSid.length; i++) {
                    banUserStr += "<input type=\"hidden\" name=\"sml010banUserSid\" value=\""
                        +   data.sml010banUserSid[i] + "\">";
                }
            }
            $('#sml010banUserSidArea').children().remove();
            $('#sml010banUserSidArea').append(banUserStr);


        } catch (ae) {
            alert(ae);
        } finally {
            closeloadingPop();
        }

    }).fail(function(data){
        closeloadingPop();
        alert('error');
    });

    reloading_flg = false;

}

function getFormData(formObj) {

    var formData = "";
    formData = formObj.serialize();

    return formData;
}



//メール情報取得(受信・送信・ごみ箱)
function getDetail(sid, kbn, searchFlg, reloadFlg, getMailKbn) {

    detail_mail_sid = sid;
    var dspMailKbn = 0;

    var cmdStr = "getDetail";

//  var lblSid = $('input:hidden[name=sml010SelectLabelSid]').val();

    var procMode = $("input[name=sml010ProcMode]").val();
    if (searchFlg) {
        procMode = $("input:hidden[name=sml090SvMailSyubetsu]").val();
    }

    //前のメール取得
    if (getMailKbn == 1) {
        cmdStr = "prevData";

//      //検索時
//      if(sel_mail_parent_kbn != 0) {
//      cmdStr = "getDetail";
//      if ($('#searchPrevmail_' + sid).val() != null && $('#searchPrevmailKbn_' + sid).val() != null) {
//      sid = $('#searchPrevmail_' + sid).val();
//      kbn = $('#searchPrevmailKbn_' + sid).val();
//      procMode = $("input:hidden[name=sml090SvMailSyubetsu]").val();
//      }
//
//      }

        //次のメール取得
    } else if (getMailKbn == 2) {
        cmdStr = "nextData";

//      //検索時
//      if(sel_mail_parent_kbn != 0) {
//      cmdStr = "getDetail";
//      if ($('#searchNextmail_' + sid).val() != null && $('#searchNextmailKbn_' + sid).val() != null) {
//      sid = $('#searchNextmail_' + sid).val();
//      kbn = $('#searchNextmailKbn_' + sid).val();
//      procMode = $("input:hidden[name=sml090SvMailSyubetsu]").val();
//      }
//      }
    }

    var accountSid = $("#account_comb_box").val();

    try {

        document.forms[0].CMD.value=cmdStr;
        document.forms[0].SERCHFLG.value=searchFlg;

        document.forms[0].PROCMODE.value=procMode;
        document.forms[0].SELECTSID.value=sid;
        document.forms[0].SELECTKBN.value=kbn;
        document.forms[0].ACCOUNT.value=accountSid;

        var paramStr = "";
        paramStr += getFormData($('#smlForm'));

        $.ajax({
            async: true,
            url:  "../smail/sml030.do",
            type: "post",
            data: paramStr
        }).done(function( data ) {

            $(".mail_check_title").html("");
            $(".mail_check_from").html("");
            $(".mail_check_mark").html("");
            $(".mail_check_tmp").html("");
            $(".mail_check_body_txt").html("");
            $('#mail_kakunin_open_tr').html("");

            if (data != null && data != "") {

                //ボタン
                var btnAreaStr = "";
                btnAreaStr += "<td class=\"mail_list_head_menu\">";
                if (data.sml030SmlList.length > 0) {


                    if (data.sml010ProcMode != "4") {
                        if (data.sml010ProcMode == "1" || data.sml010SelectedMailKbn == 1) {
                            btnAreaStr += "<div class=\"head_menu_spacer head_menu_main\"></div>"
                                +  "<div class=\"mail_menu_btn\" id=\"head_menu_copy_btn\">"
                                +  "<div class=\"head_menu_btn_left\"></div>"
                                +  "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_copy_btn\">複写して新規作成</div></div>"
                                +  "<div class=\"head_menu_btn_right\"></div>"
                                +  "</div>";
                        }

                        if (data.sml030HensinDspFlg) {

                            btnAreaStr += "<div class=\"head_menu_spacer head_menu_main\"></div>"
                                +  "<div class=\"mail_menu_btn\" id=\"head_menu_replay_btn\">"
                                +  "<div class=\"head_menu_btn_left\"></div>"
                                +  "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_replay_btn\">返信</div></div>"
                                +  "<div class=\"head_menu_btn_right\"></div>"
                                +  "</div>"
                                +  "<div class=\"head_menu_spacer head_menu_main\"></div>"
                                +  "<div class=\"mail_menu_btn\" id=\"head_menu_all_replay_btn\">"
                                +  "<div class=\"head_menu_btn_left\"></div>"
                                +  "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_all_replay_btn\">全返信</div></div>"
                                +  "<div class=\"head_menu_btn_right\"></div>"
                                +  "</div>";
                        } else {
                            btnAreaStr += "<div class=\"head_menu_spacer head_menu_main\"></div>"
                                +  "<div class=\"mail_menu_btn\">"
                                +  "<div class=\"head_menu_btn_left_hide\"></div>"
                                +  "<div class=\"head_menu_btn_bg_hide\"><div class=\"head_menu_btn_hide\">返信</div></div>"
                                +  "<div class=\"head_menu_btn_right_hide\"></div>"
                                +  "</div>"
                                +  "<div class=\"head_menu_spacer head_menu_main\"></div>"
                                +  "<div class=\"mail_menu_btn\">"
                                +  "<div class=\"head_menu_btn_left_hide\"></div>"
                                +  "<div class=\"head_menu_btn_bg_hide\"><div class=\"head_menu_btn_hide\">全返信</div></div>"
                                +  "<div class=\"head_menu_btn_right_hide\"></div>"
                                +  "</div>";
                        }

                        btnAreaStr += "<div class=\"head_menu_spacer head_menu_main\"></div>"
                            +  "<div class=\"mail_menu_btn\" id=\"head_menu_forward_btn\">"
                            +  "<div class=\"head_menu_btn_left\"></div>"
                            +  "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_forward_btn\">転送</div></div>"
                            +  "<div class=\"head_menu_btn_right\"></div>"
                            +  "</div>";
                    }


                    if (data.sml010ProcMode == "4") {
                        btnAreaStr += "<div class=\"head_menu_spacer head_menu_main\"></div>"
                            +  "<div class=\"mail_menu_btn\" id=\"head_menu_revived_btn\">"
                            +  "<div class=\"head_menu_btn_left\"></div>"
                            +  "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_revived_btn\">元に戻す</div></div>"
                            +  "<div class=\"head_menu_btn_right\"></div>"
                            +  "</div>";
                    }

                    btnAreaStr += "<div class=\"head_menu_spacer head_menu_main\"></div>"
                        +  "<div class=\"mail_menu_btn\" id=\"head_menu_del_btn\">"
                        +  "<div class=\"head_menu_btn_left\"></div>"
                        +  "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_del_btn\">削除</div></div>"
                        +  "<div class=\"head_menu_btn_right\"></div>"
                        +  "</div>";

                    if (data.sml010ProcMode == "1" || (data.sml010ProcMode != "4" && data.sml010SelectedMailKbn == 1)) {
                        btnAreaStr += "<div class=\"head_menu_spacer head_menu_main\"></div>"
                            +  "<div class=\"mail_menu_btn\" id=\"head_menu_alldel_btn\">"
                            +  "<div class=\"head_menu_btn_left\"></div>"
                            +  "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_alldel_btn\">すべて削除</div></div>"
                            +  "<div class=\"head_menu_btn_right\"></div>"
                            +  "</div>";
                    }

                    if (data.sml010ProcMode != "4" || sel_mail_parent_kbn == 0) {
                        btnAreaStr += "<div class=\"head_menu_spacer head_menu_main\"></div>"
                            +  "<div class=\"mail_menu_btn\" id=\"head_menu_prev_btn\">"
                            +  "<div class=\"head_menu_btn_left\"></div>"
                            +  "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_prev_btn\">前へ</div></div>"
                            +  "<div class=\"head_menu_btn_right\"></div>"
                            +  "</div>"
                            +  "<div class=\"head_menu_spacer head_menu_main\"></div>"
                            +  "<div class=\"mail_menu_btn\" id=\"head_menu_next_btn\">"
                            +  "<div class=\"head_menu_btn_left\"></div>"
                            +  "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_next_btn\">次へ</div></div>"
                            +  "<div class=\"head_menu_btn_right\"></div>"
                            +  "</div>";
                    }


                }
                btnAreaStr += "</td>";

                $('#mail_kakunin_head_menu').children().remove();
                $('#mail_kakunin_head_menu').append(btnAreaStr);

//              <logic:messagesPresent message="false">
//              <br><html:errors /><br>
//              </logic:messagesPresent>

                //宛先・件名
                var smsTitle = "";
                var atesakiKenmeiStr = "";

                atesakiKenmeiStr = "<td width=\"100%\" class=\"mail_check_body\">";

                if (data.sml030SmlList.length > 0) {

                    var kakuninTxtClass = "mail_check_txt";

                    for (s = 0; s < data.sml030SmlList.length; s++) {

                        var mailData = data.sml030SmlList[s];

                        $('input:hidden[name=sml010SelectedSid]').val(mailData.smlSid);
                        $('input:hidden[name=sml010SelectedMailKbn]').val(mailData.mailKbn);

                        smsTitle = mailData.smsTitle;

                        if (!data.sml030SosinFlg) {

                            atesakiKenmeiStr += "<table width=\"100%\" class=\"clear_table\">";
                            atesakiKenmeiStr += "<tr>";

                            if (data.photoDspFlg=="0") {
                                //写真表示する
                                atesakiKenmeiStr += "<td style=\"width:80px;padding-top:5px;padding-right:5px;padding-bottom:5px;padding-left:20px;\" valign=\"top\">";

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

                                        if (mailData.usrJkbn == "9" || mailData.usrJkbn == "1") {
                                            atesakiKenmeiStr += "<img src=\"../user/images/photo.gif\" name=\"userImage"
                                                +  mailData.smlSid
                                                +  "\" alt=\"写真\" border=\"1\" class=\"photo_width\" onload=\"initImageView130('userImage"
                                                +  mailData.smlSid
                                                +  "');\">";
                                        } else {
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
                                }

                                if (mailData.photoFileDsp == "2") {
                                    atesakiKenmeiStr += "<img src=\"../smail/images/icon_sml.jpg\" border=\"0\" alt=\"ショートメール\" class=\"img_bottom\">";
                                }

                                atesakiKenmeiStr += "</td>";
                                atesakiKenmeiStr += "<td valign=\"top\">";

                            } else {
                                //写真表示しない
                                atesakiKenmeiStr += "<td style=\"width:20px;padding-top:5px;padding-bottom:5px;padding-left:0px;\" valign=\"top\">";
                                atesakiKenmeiStr += "</td>";
                                atesakiKenmeiStr += "<td valign=\"top\">";
                            }

                            atesakiKenmeiStr += "<div class=\"mail_check_title\">";

                            if (mailData.fwKbn != "0") {
                                atesakiKenmeiStr += "<img alt=\"Fw\" src=\"../smail/images/img_forward.gif\" class=\"img_bottom\">";
                            }
                            if (mailData.returnKbn != "0") {
                                atesakiKenmeiStr += "<img alt=\"Re\" src=\"../smail/images/img_henshin.gif\" class=\"img_bottom\">";
                            }

                            atesakiKenmeiStr += mailData.smsTitle;

                            atesakiKenmeiStr += "</div>";


                            atesakiKenmeiStr += "<div class=\"mail_check_txt\">送信者：";

                            if (mailData.usrSid > 0) {
                                if (mailData.usrJkbn == "0") {

                                    if (mailData.usrSid > 100) {
                                        atesakiKenmeiStr += "<a href=\"javaScript:void(0);\" class=\"atesaki_link\" onClick=\"openUserInfoWindow(" + mailData.usrSid + ");\">"
                                        + htmlEscape(mailData.usiSei) + "&nbsp;&nbsp;" + htmlEscape(mailData.usiMei)
                                        + "</a>";
                                    } else {
                                        atesakiKenmeiStr += htmlEscape(mailData.usiSei) + "&nbsp;&nbsp;" + htmlEscape(mailData.usiMei);
                                    }
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

                                atesakiKenmeiStr += "<table width=\"100%\" class=\"clear_table\">";
                                atesakiKenmeiStr += "<tr>";

                                if(data.photoDspFlg=="0"){
                                    //写真表示する
                                    atesakiKenmeiStr += "<td width=\"50px\" valign=\"top\">";

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
                                                if (atesaki.usrJkbn == "9") {
                                                    atesakiKenmeiStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                                                } else {
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

                                    atesakiKenmeiStr += "</td>";

                                } else {
                                    //写真表示しない
                                    atesakiKenmeiStr += "<td class=\"mail_check_txt_border_top\">";
                                    atesakiKenmeiStr += "<table class=\"mail_check_txt\"><tr><td valign=\"top\" nowrap>";
                                    atesakiKenmeiStr += "<div style=\"padding-top:5px;padding-right:0px \" class=\"mail_check_txt2\">";
                                    atesakiKenmeiStr += "宛先　：";
                                    atesakiKenmeiStr += "</div></td><td>";
                                    atesakiKenmeiStr += "<div style=\"padding-top:5px\">";

                                    //宛先一覧
                                    for (m = 0; m < mailData.atesakiList.length; m++) {
                                        var atesaki = mailData.atesakiList[m];

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

                                        if (m != (mailData.atesakiList.length) - 1) {
                                            atesakiKenmeiStr += ",&nbsp;&nbsp;";
                                        }
                                    }

                                    atesakiKenmeiStr += "</div></td></tr></table>";
                                    atesakiKenmeiStr += "</td>";
                                }

                                atesakiKenmeiStr += "</tr>";
                                atesakiKenmeiStr += "</table>";
                            }


                            if (mailData.ccList.length > "0") {

                                atesakiKenmeiStr += "<table width=\"100%\" class=\"clear_table mail_check_txt_border_top\">";
                                atesakiKenmeiStr += "<tr>";

                                if(data.photoDspFlg=="0"){
                                    //写真表示する
                                    atesakiKenmeiStr += "<td width=\"50px\" valign=\"top\">";

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
                                                if (cc.usrJkbn == "9") {
                                                    atesakiKenmeiStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                                                } else {
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

                                    atesakiKenmeiStr += "</td>";

                                } else {
                                    //写真表示しない
                                    atesakiKenmeiStr += "<td class=\"mail_check_txt_border_top\">";
                                    atesakiKenmeiStr += "<table class=\"mail_check_txt\"><tr><td valign=\"top\" nowrap>";
                                    atesakiKenmeiStr += "<div style=\"padding-top:5px;padding-right:0px \" class=\"mail_check_txt2\">";
                                    atesakiKenmeiStr += "CC　&nbsp;&nbsp;：";
                                    atesakiKenmeiStr += "</div></td><td>";
                                    atesakiKenmeiStr += "<div style=\"padding-top:5px\">";

                                    //CC一覧
                                    for (n = 0; n < mailData.ccList.length; n++) {
                                        var cc = mailData.ccList[n];

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

                                        if (n != (mailData.ccList.length) - 1) {
                                            atesakiKenmeiStr += ",&nbsp;&nbsp;";
                                        }
                                    }

                                    atesakiKenmeiStr += "</div></td></tr></table>";
                                    atesakiKenmeiStr += "</td>";
                                }

                                atesakiKenmeiStr += "</tr>";
                                atesakiKenmeiStr += "</table>";
                            }


                            if (mailData.bccList.length > "0") {

                                atesakiKenmeiStr += "<table width=\"100%\" class=\"clear_table mail_check_txt_border_top\">";
                                atesakiKenmeiStr += "<tr>";

                                if(data.photoDspFlg=="0"){
                                    //写真表示する
                                    atesakiKenmeiStr += "<td width=\"50px\" valign=\"top\">";

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
                                                if (bcc.usrJkbn == "9") {
                                                    atesakiKenmeiStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                                                } else {
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

                                    atesakiKenmeiStr += "</td>";

                                } else {
                                    //写真表示しない
                                    atesakiKenmeiStr += "<td class=\"mail_check_txt_border_top\">";
                                    atesakiKenmeiStr += "<table class=\"mail_check_txt\"><tr><td valign=\"top\" nowrap>";
                                    atesakiKenmeiStr += "<div style=\"padding-top:5px;padding-right:0px \" class=\"mail_check_txt2\">";
                                    atesakiKenmeiStr += "BCC　：";
                                    atesakiKenmeiStr += "</div></td><td>";
                                    atesakiKenmeiStr += "<div style=\"padding-top:5px\">";


                                    //BCC一覧
                                    for (o = 0; o < mailData.bccList.length; o++) {
                                        var bcc = mailData.bccList[o];

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

                                        if (o != (mailData.bccList.length) - 1) {
                                            atesakiKenmeiStr += ",&nbsp;&nbsp;";
                                        }
                                    }

                                    atesakiKenmeiStr += "</div></td></tr></table>";
                                    atesakiKenmeiStr += "</td>";
                                }

                                atesakiKenmeiStr += "</tr>";
                                atesakiKenmeiStr += "</table>";
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
                            if (data.sml010ProcMode == "4" || data.sml010ProcMode == "5") {
                                if (mailData.mailKbn != "1") {
                                    if (mailData.atesakiList.length > 0) {

                                        atesakiKenmeiStr += "<div class=\""
                                            +  kakuninTxtClass + "\">";

                                        atesakiKenmeiStr += "<table class=\"mail_check_txt\"><tr><td valign=\"top\" nowrap>";
                                        atesakiKenmeiStr += "宛先　：";
                                        atesakiKenmeiStr += "</td><td id=\"jmail_atesaki_cell\">";
                                        atesakiKenmeiStr += "<div id=\"jmail_atesaki_area\">";

                                        for (t = 0; t < mailData.atesakiList.length; t++) {
                                            var atesakiMdl = mailData.atesakiList[t];

                                            if (atesakiMdl.usrSid > 0) {
                                                if (atesakiMdl.usrJkbn == "0") {

                                                    atesakiKenmeiStr += "<a href=\"javaScript:void(0);\" class=\"atesaki_link\" onClick=\"openUserInfoWindow(" + atesakiMdl.usrSid + ");\">"
                                                    + atesakiMdl.usiSei + "&nbsp;&nbsp;" + atesakiMdl.usiMei
                                                    + "</a>";
                                                }
                                                if (atesakiMdl.usrJkbn == "9") {
                                                    atesakiKenmeiStr += "<del>" + atesakiMdl.usiSei + "&nbsp;&nbsp;" + atesakiMdl.usiMei + "</del>";
                                                }
                                            } else {
                                                if (atesakiMdl.accountJkbn == "0") {
                                                    atesakiKenmeiStr += atesakiMdl.accountName;
                                                }
                                                if (atesakiMdl.accountJkbn == "1") {
                                                    atesakiKenmeiStr += "<del>" + atesakiMdl.accountName + "</del>";
                                                }
                                            }


                                            if (t != (mailData.atesakiList.length) - 1) {
//                                              atesakiKenmeiStr += "<br>　　　　";
                                                atesakiKenmeiStr += ",&nbsp;&nbsp;";
                                            }

                                        }

                                        atesakiKenmeiStr += "</div>";
                                        atesakiKenmeiStr += "<span id=\"jmail_atesaki_alldisp_area\" class=\"all_disp_txt\" style=\"font-weight:normal;\"></span>";
                                        atesakiKenmeiStr += "</td></tr></table>";
                                        atesakiKenmeiStr += "</div>";
                                    }

                                    if (mailData.ccList.length > 0) {

                                        atesakiKenmeiStr += "<div class=\""
                                            +  kakuninTxtClass + "\">";

                                        atesakiKenmeiStr += "<table class=\"mail_check_txt\"><tr><td valign=\"top\" nowrap>";
                                        atesakiKenmeiStr += "CC　&nbsp;&nbsp;：";
                                        atesakiKenmeiStr += "</td><td id=\"jmail_cc_cell\">";
                                        atesakiKenmeiStr += "<div id=\"jmail_cc_area\">";
                                        for (c = 0; c < mailData.ccList.length; c++) {
                                            var ccMdl = mailData.ccList[c];

                                            if (ccMdl.usrSid > 0) {
                                                if (ccMdl.usrJkbn == "0") {
                                                    atesakiKenmeiStr += "<a href=\"javaScript:void(0);\" class=\"atesaki_link\" onClick=\"openUserInfoWindow(" + ccMdl.usrSid + ");\">"
                                                    + ccMdl.usiSei + "&nbsp;&nbsp;" + ccMdl.usiMei
                                                    + "</a>";

                                                }
                                                if (ccMdl.usrJkbn == "9") {
                                                    atesakiKenmeiStr += "<del>" + ccMdl.usiSei + "&nbsp;&nbsp;" + ccMdl.usiMei + "</del>";

                                                }
                                            } else {
                                                if (ccMdl.accountJkbn == "0") {
                                                    atesakiKenmeiStr += ccMdl.accountName;
                                                }
                                                if (ccMdl.accountJkbn == "1") {
                                                    atesakiKenmeiStr += "<del>" + ccMdl.accountName + "</del>";

                                                }
                                            }


                                            if (c != (mailData.ccList.length) - 1) {
//                                              atesakiKenmeiStr += "<br>　　　　";
                                                atesakiKenmeiStr += ",&nbsp;&nbsp;";
                                            }

                                        }

                                        atesakiKenmeiStr += "</div>";
                                        atesakiKenmeiStr += "<span id=\"jmail_cc_alldisp_area\" class=\"all_disp_txt\" style=\"font-weight:normal;\"></span>";
                                        atesakiKenmeiStr += "</td></tr></table>";
                                        atesakiKenmeiStr += "</div>";
                                    }


                                    if (mailData.bccList.length > 0) {

                                        atesakiKenmeiStr += "<div class=\""
                                            +  kakuninTxtClass + "\">";

                                        atesakiKenmeiStr += "BCC：";
                                        for (b = 0; b < mailData.bccList.length; b++) {
                                            var bccMdl = mailData.bccList[b];

                                            if (bccMdl.usrSid > 0) {
                                                if (bccMdl.usrJkbn == "0") {
                                                    atesakiKenmeiStr += bccMdl.usiSei + "&nbsp;&nbsp;" + bccMdl.usiMei;
                                                }
                                                if (bccMdl.usrJkbn == "9") {
                                                    atesakiKenmeiStr += "<del>" + bccMdl.usiSei + "&nbsp;&nbsp;" + bccMdl.usiMei + "</del>";

                                                }
                                            } else {
                                                if (bccMdl.accountJkbn == "0") {
                                                    atesakiKenmeiStr += bccMdl.accountName;
                                                }
                                                if (bccMdl.accountJkbn == "1") {
                                                    atesakiKenmeiStr += "<del>" + bccMdl.accountName + "</del>";

                                                }
                                            }


                                            if (b != mailData.bccList.length - 1) {
                                                atesakiKenmeiStr += "<br>";
                                            }

                                            if (b != (mailData.bccList.length) - 1) {
//                                              atesakiKenmeiStr += "<br>　　　　";
                                                atesakiKenmeiStr += ",&nbsp;&nbsp;";
                                            }

                                        }
                                        atesakiKenmeiStr += "</div>";
                                    }
                                }
                            }


                            if (data.sml010ProcMode != "4" && data.sml010ProcMode != "5") {
                                if (mailData.atesakiList.length > 0) {
                                    atesakiKenmeiStr += "<div class=\"mail_check_txt\">";
                                    atesakiKenmeiStr += "<table class=\"mail_check_txt\"><tr><td valign=\"top\" nowrap>";
                                    atesakiKenmeiStr += "宛先　：";
                                    atesakiKenmeiStr += "</td><td id=\"jmail_atesaki_cell\">";
                                    atesakiKenmeiStr += "<div id=\"jmail_atesaki_area\">";
                                    for (t = 0; t < mailData.atesakiList.length; t++) {
                                        var atesakiMdl = mailData.atesakiList[t];

                                        if (atesakiMdl.usrSid > 0) {
                                            if (atesakiMdl.usrJkbn == "0") {
                                                atesakiKenmeiStr += "<a href=\"javaScript:void(0);\" class=\"atesaki_link\" onClick=\"openUserInfoWindow(" + atesakiMdl.usrSid + ");\">"
                                                + htmlEscape(atesakiMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiMdl.usiMei)
                                                + "</a>";

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
//                                          atesakiKenmeiStr += "<br>　　　　";
                                            atesakiKenmeiStr += ",&nbsp;&nbsp;";
                                        }

                                    }

                                    atesakiKenmeiStr += "</div>";
                                    atesakiKenmeiStr += "<span id=\"jmail_atesaki_alldisp_area\" class=\"all_disp_txt\" style=\"font-weight:normal;\"></span>";
                                    atesakiKenmeiStr += "</td></tr></table>";
                                    atesakiKenmeiStr += "</div>";
                                }


                                if (mailData.ccList.length > 0) {
                                    atesakiKenmeiStr += "<div class=\"mail_check_txt\">";
                                    atesakiKenmeiStr += "<table class=\"mail_check_txt\"><tr><td valign=\"top\" nowrap>";
                                    atesakiKenmeiStr += "CC　&nbsp;&nbsp;：";
                                    atesakiKenmeiStr += "</td><td id=\"jmail_cc_cell\">";
                                    atesakiKenmeiStr += "<div id=\"jmail_cc_area\">";
                                    for (c = 0; c < mailData.ccList.length; c++) {
                                        var ccMdl = mailData.ccList[c];

                                        if (ccMdl.usrSid > 0) {
                                            if (ccMdl.usrJkbn == "0") {
                                                atesakiKenmeiStr += "<a href=\"javaScript:void(0);\" class=\"atesaki_link\" onClick=\"openUserInfoWindow(" + ccMdl.usrSid + ");\">"
                                                + htmlEscape(ccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(ccMdl.usiMei)
                                                + "</a>";
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
//                                          atesakiKenmeiStr += "<br>　　　　";
                                            atesakiKenmeiStr += ",&nbsp;&nbsp;";
                                        }

                                    }

                                    atesakiKenmeiStr += "</div>";
                                    atesakiKenmeiStr += "<span id=\"jmail_cc_alldisp_area\" class=\"all_disp_txt\" style=\"font-weight:normal;\"></span>";
                                    atesakiKenmeiStr += "</td></tr></table>";
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

                                atesakiKenmeiStr += "<tr><td style=\"padding-top:5px;padding-bottom:5px;padding-left:20px;\" class=\"mail_check_txt_border_top\" width=\"100%\">";

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

                                atesakiKenmeiStr += "<a href=\"javascript:void(0);\" onClick=\"return fileLinkClick('downLoad',"
                                    +  data.sml010SelectedSid
                                    +  ","
                                    +  fileMdl.binSid
                                    +  ");\"><span class=\"text_link_min\">"
                                    +  fileMdl.binFileName + fileMdl.binFileSizeDsp
                                    +  "</span></a>";

                                atesakiKenmeiStr += "</td></tr>";
                            }

                        }


                        if (!data.sml030SosinFlg) {
                            atesakiKenmeiStr += "</td>"
                                +  "</tr>"
                                +  "</table>";
                        }

                        $(".mail_check_body_txt").append(mailData.smsBody);


                        //開封状況
                        var opnSituation = "";
                        if (data.sml010ProcMode == 1) {

                            if (mailData.atesakiList.length > 0) {

                                opnSituation += "<div class=\"open_situation_txt\">開封状況</div>";

                                opnSituation += "<table><tr>";

                                opnSituation += "<td class=\"open_situation_head_td\">宛先</td>"
                                    +  "<td class=\"open_situation_head_td\">開封日</td>"
                                    +  "<td class=\"open_situation_head_td\">E-mail転送</td>";

                                opnSituation += "</tr>";

                                for (at = 0; at < mailData.atesakiList.length; at++) {

                                    opnSituation += "<tr>";

                                    var atesakiMdl = mailData.atesakiList[at];

                                    if (atesakiMdl.usrSid > 0) {
                                        if (atesakiMdl.usrJkbn == "0") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(atesakiMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiMdl.usiMei) + "</span></td>";
                                        }

                                        if (atesakiMdl.usrJkbn == "9") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(atesakiMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiMdl.usiMei) + "</del></span></td>";
                                        }
                                    } else {
                                        if (atesakiMdl.accountJkbn == "0") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(atesakiMdl.accountName) + "</span></td>";
                                        }

                                        if (atesakiMdl.accountJkbn == "1") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(atesakiMdl.accountName) + "</del></span></td>";
                                        }
                                    }


                                    opnSituation += "<td class=\"open_situation_td\">" + atesakiMdl.smlOpdateStr + "</td>";

                                    if (atesakiMdl.smjFwkbn == "1") {
                                        opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">転送済み</span></td>";
                                    } else {
                                        opnSituation += "<td class=\"open_situation_td\"></td>";
                                    }

                                    opnSituation += "</tr>";

                                }

                                opnSituation += "</table>";

                            }

                            if (mailData.ccList.length > 0) {

                                opnSituation += "<div class=\"bottom_spacer\"></div>";

                                opnSituation += "<table><tr>";

                                opnSituation += "<td class=\"open_situation_head_td\">宛先(CC)</td>"
                                    +  "<td class=\"open_situation_head_td\">開封日</td>"
                                    +  "<td class=\"open_situation_head_td\">E-mail転送</td>";

                                opnSituation += "</tr>";

                                for (cc = 0; cc < mailData.ccList.length; cc++) {

                                    var ccMdl = mailData.ccList[cc];

                                    if (ccMdl.usrSid > 0) {
                                        if (ccMdl.usrJkbn == "0") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(ccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(ccMdl.usiMei) + "</span></td>";
                                        }

                                        if (ccMdl.usrJkbn == "9") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(ccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(ccMdl.usiMei) + "</del></span></td>";
                                        }
                                    } else {
                                        if (ccMdl.accountJkbn == "0") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(ccMdl.accountName) + "</span></td>";
                                        }

                                        if (ccMdl.accountJkbn == "1") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(ccMdl.accountName) + "</del></span></td>";
                                        }
                                    }


                                    opnSituation += "<td class=\"open_situation_td\">" + ccMdl.smlOpdateStr + "</td>";

                                    if (ccMdl.smjFwkbn == "1") {
                                        opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">転送済み</span></td>";
                                    } else {
                                        opnSituation += "<td class=\"open_situation_td\"></td>";
                                    }


                                    opnSituation += "</tr>";

                                }

                                opnSituation += "</table>";

                            }



                            if (mailData.bccList.length > 0) {

                                opnSituation += "<div class=\"bottom_spacer\"></div>";

                                opnSituation += "<table><tr>";

                                opnSituation += "<td class=\"open_situation_head_td\">宛先(BCC)</td>"
                                    +  "<td class=\"open_situation_head_td\">開封日</td>"
                                    +  "<td class=\"open_situation_head_td\">E-mail転送</td>";

                                opnSituation += "</tr>";

                                for (bcc = 0; bcc < mailData.bccList.length; bcc++) {

                                    var bccMdl = mailData.bccList[bcc];

                                    if (bccMdl.usrSid > 0) {
                                        if (bccMdl.usrJkbn == "0") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(bccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(bccMdl.usiMei) + "</span></td>";
                                        }

                                        if (bccMdl.usrJkbn == "9") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(bccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(bccMdl.usiMei) + "</del></span></td>";
                                        }
                                    } else {
                                        if (bccMdl.accountJkbn == "0") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(bccMdl.accountName) + "</span></td>";
                                        }

                                        if (bccMdl.accountJkbn == "1") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(bccMdl.accountName) + "</del></span></td>";
                                        }
                                    }


                                    opnSituation += "<td class=\"open_situation_td\">" + bccMdl.smlOpdateStr + "</td>";

                                    if (bccMdl.smjFwkbn == "1") {
                                        opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">転送済み</span></td>";
                                    } else {
                                        opnSituation += "<td class=\"open_situation_td\"></td>";
                                    }


                                    opnSituation += "</tr>";

                                }

                                opnSituation += "</table>";

                            }

                        }


                        if (data.sml010ProcMode == 4 || data.sml010ProcMode == 5) {
                            if (mailData.mailKbn == "1") {

                                if (mailData.atesakiList.length > 0) {

                                    opnSituation += "<div class=\"open_situation_txt\">開封状況</div>";

                                    opnSituation += "<table><tr>";

                                    opnSituation += "<td class=\"open_situation_head_td\">宛先</td>"
                                        +  "<td class=\"open_situation_head_td\">開封日</td>"
                                        +  "<td class=\"open_situation_head_td\">E-mail転送</td>";

                                    opnSituation += "</tr>";

                                    for (at = 0; at < mailData.atesakiList.length; at++) {

                                        opnSituation += "<tr>";

                                        var atesakiMdl = mailData.atesakiList[at];

                                        if (atesakiMdl.usrSid > 0) {
                                            if (atesakiMdl.usrJkbn == "0") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(atesakiMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiMdl.usiMei) + "</span></td>";
                                            }

                                            if (atesakiMdl.usrJkbn == "9") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(atesakiMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiMdl.usiMei) + "</del></span></td>";
                                            }
                                        } else {
                                            if (atesakiMdl.accountJkbn == "0") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(atesakiMdl.accountName) + "</span></td>";
                                            }

                                            if (atesakiMdl.accountJkbn == "1") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(atesakiMdl.accountName) + "</del></span></td>";
                                            }
                                        }


                                        opnSituation += "<td class=\"open_situation_td\">" + atesakiMdl.smlOpdateStr + "</td>";

                                        if (atesakiMdl.smjFwkbn == "1") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">転送済み</span></td>";
                                        } else {
                                            opnSituation += "<td class=\"open_situation_td\"></td>";
                                        }

                                        opnSituation += "</tr>";

                                    }

                                    opnSituation += "</table>";

                                }

                                if (mailData.ccList.length > 0) {

                                    opnSituation += "<div class=\"bottom_spacer\"></div>";

                                    opnSituation += "<table><tr>";

                                    opnSituation += "<td class=\"open_situation_head_td\">宛先(CC)</td>"
                                        +  "<td class=\"open_situation_head_td\">開封日</td>"
                                        +  "<td class=\"open_situation_head_td\">E-mail転送</td>";

                                    opnSituation += "</tr>";

                                    for (cc = 0; cc < mailData.ccList.length; cc++) {

                                        var ccMdl = mailData.ccList[cc];

                                        if (ccMdl.usrSid > 0) {
                                            if (ccMdl.usrJkbn == "0") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(ccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(ccMdl.usiMei) + "</span></td>";
                                            }

                                            if (ccMdl.usrJkbn == "9") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(ccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(ccMdl.usiMei) + "</del></span></td>";
                                            }
                                        } else {
                                            if (ccMdl.accountJkbn == "0") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(ccMdl.accountName) + "</span></td>";
                                            }

                                            if (ccMdl.accountJkbn == "1") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(ccMdl.accountName) + "</del></span></td>";
                                            }
                                        }


                                        opnSituation += "<td class=\"open_situation_td\">" + ccMdl.smlOpdateStr + "</td>";

                                        if (ccMdl.smjFwkbn == "1") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">転送済み</span></td>";
                                        } else {
                                            opnSituation += "<td class=\"open_situation_td\"></td>";
                                        }


                                        opnSituation += "</tr>";

                                    }

                                    opnSituation += "</table>";

                                }



                                if (mailData.bccList.length > 0) {

                                    opnSituation += "<div class=\"bottom_spacer\"></div>";

                                    opnSituation += "<table><tr>";

                                    opnSituation += "<td class=\"open_situation_head_td\">宛先(BCC)</td>"
                                        +  "<td class=\"open_situation_head_td\">開封日</td>"
                                        +  "<td class=\"open_situation_head_td\">E-mail転送</td>";

                                    opnSituation += "</tr>";

                                    for (bcc = 0; bcc < mailData.bccList.length; bcc++) {

                                        var bccMdl = mailData.bccList[bcc];

                                        if (bccMdl.usrSid > 0) {
                                            if (bccMdl.usrJkbn == "0") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(bccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(bccMdl.usiMei) + "</span></td>";
                                            }

                                            if (bccMdl.usrJkbn == "9") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(bccMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(bccMdl.usiMei) + "</del></span></td>";
                                            }
                                        } else {
                                            if (bccMdl.accountJkbn == "0") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">" + htmlEscape(bccMdl.accountName) + "</span></td>";
                                            }

                                            if (bccMdl.accountJkbn == "1") {
                                                opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\"><del>" + htmlEscape(bccMdl.accountName) + "</del></span></td>";
                                            }
                                        }


                                        opnSituation += "<td class=\"open_situation_td\">" + bccMdl.smlOpdateStr + "</td>";

                                        if (bccMdl.smjFwkbn == "1") {
                                            opnSituation += "<td class=\"open_situation_td\"><span class=\"text_base2\">転送済み</span></td>";
                                        } else {
                                            opnSituation += "<td class=\"open_situation_td\"></td>";
                                        }


                                        opnSituation += "</tr>";

                                    }

                                    opnSituation += "</table>";

                                }

                            }

                        }

                        if (opnSituation != "") {
                            $('#mail_kakunin_open_tr').children().remove();
                            $('#mail_kakunin_open_tr').append("<td style=\"padding-top:10px;padding-right:20px;padding-bottom:10px;padding-left:20px;\" class=\"mail_check_body mail_check_body_opn\">"
                                    + opnSituation
                                    + "</td>");
                        }

                        //メール判別
                        if (data.sml010ProcMode == 0) {

                            dspMailKbn = 0;

                        } else if (data.sml010ProcMode == 1) {

                            dspMailKbn = 1;

                        } else if (data.sml010ProcMode == 4) {

                            if (mailData.mailKbn == "0") {

                                dspMailKbn = 2;
                            } else if (mailData.mailKbn == "1") {

                                dspMailKbn = 3;
                            } else {

                                dspMailKbn = 4;
                            }

                        } else if (data.sml010ProcMode == 5) {

                            if (mailData.mailKbn == "0") {

                                dspMailKbn = 0;
                            } else if (mailData.mailKbn == "1") {

                                dspMailKbn = 1;
                            }

                        }
                        mail_kakunin_kbn = dspMailKbn;

                    }

                }

                atesakiKenmeiStr += "</td>";

                $('#mail_kakunin_body_tr').children().remove();
                $('#mail_kakunin_body_tr').append(atesakiKenmeiStr);

                var mailTabTxt = "";
                var maxTabTxt = 15;
                if (smsTitle.length > maxTabTxt) {
                    var mailTabTxtRep = smsTitle;
                    mailTabTxtRep = replaceAll(mailTabTxtRep, "&nbsp;", " ");
                    if (mailTabTxtRep.length > maxTabTxt) {
                        mailTabTxt = mailTabTxtRep.substring(0, maxTabTxt) + "…";
                    } else {
                        mailTabTxt = smsTitle;
                    }
                } else {
                    mailTabTxt = smsTitle;
                }


                if (!mail_kakunin_flg) {
                    $('#mail_list_tab').after('<td class=\"mail_area_head_space\" style=\"width:5px;\"><table class=\"mail_area_head_space_table\"><tr><td></td></tr></table></td></td>'
                            + '<td id=\"mail_kakunin_tab\" class=\"mail_area_head2\">'
                            + '<table><tr><td class=\"sml_mail_kakunin_tab\" nowrap>'
                            + mailTabTxt
                            + '</td>'
                            + '<td class="padding_right_0">'
                            + '<div id=\"mail_kakunin\" class=\"sml_mail_close_btn_mini\"></div>'
                            + '</td>'
                            + '</tr></table>'
                            + '</td>');

                    $('.mail_list_area').addClass('display_none');
                    $('.mail_search_list_area').addClass('display_none');
                    $('.mail_create_area').addClass('display_none');
                    $('.mail_create_kakunin_area').addClass('display_none');
                    $('.mail_kakunin_area').removeClass('display_none');
                    $('.mail_area_head').addClass('mail_area_head_hide');
                    $(this).removeClass('mail_area_head_hide');

                    mail_kakunin_flg = true;
                    changeTab($('#mail_kakunin_tab'));

                } else {
                    $('.sml_mail_kakunin_tab').html("");
                    $('.sml_mail_kakunin_tab').append(mailTabTxt);
                    //$('#mail_kakunin_tab').click();
                    changeTab($('#mail_kakunin_tab'));
                }


                //メール確認画面の宛先にスクロールを設定
                if ($("#jmail_atesaki_cell").height() > 54) {
                    $('#jmail_atesaki_area').addClass('atesaki_scroll_on');
                    $('#jmail_atesaki_area').addClass('jmail_atesaki_scroll_area_height');
                    $('#jmail_atesaki_alldisp_area').html('全て表示');
                    jmail_dsp_to_kbn = 0;
                }

                //メール確認画面のCCにスクロールを設定
                if ($("#jmail_cc_cell").height() > 54) {
                    $('#jmail_cc_area').addClass('atesaki_scroll_on');
                    $('#jmail_cc_area').addClass('jmail_atesaki_scroll_area_height');
                    $('#jmail_cc_alldisp_area').html('全て表示');
                    jmail_dsp_cc_kbn = 0;
                }

                if (reloadFlg) {
                    reloadData();
                } else {
                    setTimeout('reloadData()', 1000);
                }

            } else {
                //該当するデータがありません。
                alert('データなし');
            }

        });

    } catch(exp) {
        messagePop("該当するメールが存在しません。", 500, 200);
    }

    setTimeout('changeHelpParam(0, 2)', 1500);
}

//メール情報取得(草稿)
function getMessage(sid) {
    document.forms[0].sml010SelectedSid.value = sid;
    $('#sml020ProcModeArea').append("<input type=\"hidden\" name=\"sml020ProcMode\" value=\"4\" />");
    $('#head_menu_del_soko_btn').removeClass('display_none');
    $('#head_menu_del_soko_btn_spacer').removeClass('display_none');

    if (!mail_create_flg) {
        //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送)
        $('input:hidden[name=sml020ProcMode]').val(4);
    }

    replayMail(4);
}


function changeModeDir(mode) {
    document.forms[0].CMD.value='changeDir';
    document.forms[0].sml010ProcMode.value = mode;

    $("#mail_list_tab").html("");
    if (mode == 0) {
        $("#mail_list_tab").html("受&nbsp;&nbsp;&nbsp;信");
        $('.gomibako_area').remove();
        $("#head_menu_list_label_add_btn").prev().removeClass('display_none');
        $("#head_menu_list_label_add_btn").removeClass('display_none');
        $("#head_menu_list_label_del_btn").removeClass('display_none');
        $("#head_menu_list_kidoku_btn").prev().removeClass('display_none');
        $("#head_menu_list_kidoku_btn").removeClass('display_none');
        $("#head_menu_list_midoku_btn").removeClass('display_none');

    } else if (mode == 1) {
        $("#mail_list_tab").html("送&nbsp;&nbsp;&nbsp;信");
        $('.gomibako_area').remove();
        $("#head_menu_list_label_add_btn").prev().removeClass('display_none');
        $("#head_menu_list_label_add_btn").removeClass('display_none');
        $("#head_menu_list_label_del_btn").removeClass('display_none');
        $("#head_menu_list_kidoku_btn").prev().addClass('display_none');
        $("#head_menu_list_kidoku_btn").addClass('display_none');
        $("#head_menu_list_midoku_btn").addClass('display_none');
    } else if (mode == 2) {
        $("#mail_list_tab").html("草&nbsp;&nbsp;&nbsp;稿");
        $('.gomibako_area').remove();
        $("#head_menu_list_label_add_btn").prev().removeClass('display_none');
        $("#head_menu_list_label_add_btn").removeClass('display_none');
        $("#head_menu_list_label_del_btn").removeClass('display_none');
        $("#head_menu_list_kidoku_btn").prev().addClass('display_none');
        $("#head_menu_list_kidoku_btn").addClass('display_none');
        $("#head_menu_list_midoku_btn").addClass('display_none');
    } else if (mode == 4) {
        $("#mail_list_tab").html("ごみ箱");
        $('.gomibako_area').remove();
        $("#head_menu_list_label_add_btn").prev().addClass('display_none');
        $("#head_menu_list_label_add_btn").addClass('display_none');
        $("#head_menu_list_label_del_btn").addClass('display_none');
        $("#head_menu_list_kidoku_btn").prev().removeClass('display_none');
        $("#head_menu_list_kidoku_btn").removeClass('display_none');
        $("#head_menu_list_midoku_btn").removeClass('display_none');
        var gomiStr = "";
//      gomiStr += "<div class=\"head_menu_spacer head_menu_main gomibako_area\"></div>"
//      +   "<div class=\"mail_menu_btn gomibako_area\"  id=\"head_menu_empty_trash_btn\">"
//      +   "<div class=\"head_menu_btn_left\"></div>"
//      +   "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_empty_trash_btn\">ゴミ箱を空にする</div></div>"
//      +   "<div class=\"head_menu_btn_right\"></div>"
//      +   "</div>";
//      $('#mail_list_head_menu_area_tr').prepend(gomiStr);
//
//      gomiStr = "";
        gomiStr +=  "<div class=\"head_menu_spacer head_menu_main gomibako_area\"></div>"
            +   "<div class=\"mail_menu_btn gomibako_area\" id=\"head_menu_return_btn\">"
            +   "<div class=\"head_menu_btn_left\"></div>"
            +   "<div class=\"head_menu_btn_bg\"><div class=\"head_menu_return_btn\">元に戻す</div></div>"
            +   "<div class=\"head_menu_btn_right\"></div>"
            +   "</div>";
        $('#head_menu_list_del_btn').after(gomiStr);
    }

    changeTab($('#mail_list_tab'));
    loadingPop("読み込み中");
    getMailData();

}

function onTitleLinkClick(fid, order) {
    document.forms[0].CMD.value='getInitData';
    document.forms[0].sml010Sort_key.value = fid;
    document.forms[0].sml010Order_key.value = order;
    loadingPop("読み込み中");
    getMailData();
}


function isViewFile(ext) {
    if (".gif" == ext
            || ".jpeg" == ext
            || ".jpg" == ext
            || ".png" == ext) {
        return true;
    }
    return false;
}

function fileLinkClick(cmd, sid, binSid) {

    $('#sendHiddenParam').remove();

    $('#tempSendForm').append("<div id=\"sendHiddenParam\">");
    $('#tempSendForm').append("</div>");

    $('#sendHiddenParam').append("<input type=\"hidden\" name=\"CMD\" value=\""
            + cmd
            + "\" />");

    $('#sendHiddenParam').append("<input type=\"hidden\" name=\"sml010SelectedSid\" value=\""
            + sid
            + "\" />");

    $('#sendHiddenParam').append("<input type=\"hidden\" name=\"sml030binSid\" value=\""
            + binSid
            + "\" />");

    $('#sendHiddenParam').append("<input type=\"hidden\" name=\"smlViewAccount\" value=\""
            + $("#account_comb_box").val()
            + "\" />");

    $("#tempSendForm").attr("action", "../smail/sml030.do");
//  $("#tempSendForm").submit();

    document.getElementById('sml010Export').src = "../smail/sml030.do"
        + '?CMD=' + cmd
        + '&sml010SelectedSid=' + sid
        + '&sml030binSid=' + binSid
        + '&smlViewAccount=' + $("#account_comb_box").val();
}

function fileLinkClickKn(binSid) {

    $('#sendHiddenParam').remove();

    $('#tempSendForm').append("<div id=\"sendHiddenParam\">");
    $('#tempSendForm').append("</div>");

    $('#sendHiddenParam').append("<input type=\"hidden\" name=\"CMD\" value=\"fileDownload\" />");

    $('#sendHiddenParam').append("<input type=\"hidden\" name=\"sml020knBinSid\" value=\""
            + binSid
            + "\" />");

    $('#sendHiddenParam').append("<input type=\"hidden\" name=\"smlViewAccount\" value=\""
            + $("#account_comb_box").val()
            + "\" />");

    $("#tempSendForm").attr("action", "../smail/sml020kn.do");
//  $("#tempSendForm").submit();


    document.getElementById('sml010Export').src = "../smail/sml020kn.do"
        + '?CMD=fileDownload'
        + '&sml020knBinSid=' + binSid
        + '&smlViewAccount=' + $("#account_comb_box").val();
}

function getSelAtesakiData(paramStr, functionName, functionKbn) {

    paramStr += "&cmn120BackUrl=smail"

        $.ajax({
            async: true,
            url:"../common/cmn120.do",
            type: "post",
            data: paramStr
        }).done(function( data ) {

            var atesakiSelStr = "";

            try {
                atesakiSelStr += "<form id=\"atesakiSelForm\" name=\"atesakiSelForm\">";

                atesakiSelStr += "<input type=\"button\" class=\"display_none\" id=\"fakeButton\" name=\"fakeButton\" />";
                atesakiSelStr += "<input type=\"button\" class=\"display_none\" id=\"fakeButton2\" name=\"fakebutton2\" />";

                if (data.cmn120userSid != null && data.cmn120userSid != "") {
                    for (i = 0; i < data.cmn120userSid.length; i++) {
                        atesakiSelStr += "<input type=\"hidden\" name=\"cmn120userSid\" value=\""
                            +   data.cmn120userSid[i] + "\">";
                    }
                }

                if (data.cmn120userSidOld != null && data.cmn120userSidOld != "") {
                    for (i = 0; i < data.cmn120userSidOld.length; i++) {
                        atesakiSelStr += "<input type=\"hidden\" name=\"cmn120userSidOld\" value=\""
                            +   data.cmn120userSidOld[i] + "\">";
                    }
                }

                if (data.cmn120paramName != null && data.cmn120paramName != "") {
                    if (data.cmn120userSid != null && data.cmn120userSid.length > 0) {
                        for (i = 0; i < data.cmn120userSid.length; i++) {

                            atesakiSelStr += "<input type=\"hidden\" name=\""
                                +  data.cmn120paramName
                                + "/>\" value=\""
                                +  data.cmn120userSid[i]
                            +  "/>\">";
                        }
                    }
                }

                //
                //送信先制限設定
                if (data.cmn120banGroupSid != null && data.cmn120banGroupSid != "") {
                    for (i = 0; i < data.cmn120banGroupSid.length; i++) {
                        atesakiSelStr += "<input type=\"hidden\" name=\"cmn120banGroupSid\" value=\""
                            +   data.cmn120banGroupSid[i] + "\">";
                    }
                }
                if (data.cmn120disableGroupSid != null && data.cmn120disableGroupSid != "") {
                    for (i = 0; i < data.cmn120disableGroupSid.length; i++) {
                        atesakiSelStr += "<input type=\"hidden\" name=\"cmn120disableGroupSid\" value=\""
                            +   data.cmn120disableGroupSid[i] + "\">";
                    }
                }
                if (data.cmn120banUserSid != null && data.cmn120banUserSid != "") {
                    for (i = 0; i < data.cmn120banUserSid.length; i++) {
                        atesakiSelStr += "<input type=\"hidden\" name=\"cmn120banUserSid\" value=\""
                            +   data.cmn120banUserSid[i] + "\">";
                    }
                }

                atesakiSelStr += "<input type=\"hidden\" id=\"funcBtnName\" value=\""
                    +  functionName
                    +  "\" />"
                    +  "<input type=\"hidden\" id=\"funcBtnKbn\" value=\""
                    +  functionKbn
                    +  "\" />"
                    +  "<input type=\"hidden\" id=\"cmn120DspKbn\" name=\"cmn120DspKbn\" value=\""
                    +  data.cmn120DspKbn
                    +  "\" />"
                    +  "<table width=\"100%\" border=\"0\">"
                    +  "<tr>"
                    +  "<td width=\"100%\" align=\"center\">"
                    +  "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"650px\">"
                    +  "<tr>"
                    +  "<td>"
                    +  "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">"
                    +  "<tr>"
                    +  "<td width=\"50%\">"
                    + "</td>"
                    +  "<td width=\"0%\"></td>"
                    +  "<td class=\"\" align=\"right\" width=\"50%\">"
                    +  "<input type=\"button\" id=\"sel_usr_pop_btn\" value=\"選択\" class=\"btn_base1\">"
                    +  "<td width=\"0%\"></td>"
                    +  "</tr>"
                    +  "</table>"
                    +  "<img src=\"../common/images/spacer.gif\" alt=\"\" height=\"10px\" width=\"10px\">"
                    +  "<table style=\"padding:0px;margin:0px;\" width=\"100%\">"
                    +  "<tr>"
                    +  "<td nowrap align=\"left\" class=\"sel_group_space\" width=\"350px\"></td>"
                    +  "<td nowrap id=\"groupTab\" class=\"group_select_state sel_group_check\"><span>グループ</span></td>"
                    +  "<td nowrap id=\"mygroupTab\" class=\"sel_group_check sel_group_notcheck\"><span>マイグループ</span></td>"
                    +  "<td nowrap id=\"deptAccountTab\" class=\"sel_group_check sel_group_notcheck\"><span>"+ msglist["sml.189"] + "</span></td>"
                    +  "<td nowrap align=\"left\" class=\"sel_group_space\"></td>"
                    +  "</tr>"
                    +  "</table>"
                    +  "<table width=\"100%\" class=\"\" border=\"0\" cellpadding=\"5\">"
                    +  "<tr>"
                    +  "<td align=\"left\" class=\"td_smail_wt\">"
                    +  "<table width=\"0%\" border=\"0\">"
                    +  "<tr>"
                    +  "<td width=\"35%\" class=\"table_bg_A5B4E1\" align=\"center\"><span class=\"text_bb1\">" + functionName + "</span></td>"
                    +  "<td width=\"20%\" align=\"center\">&nbsp;</td>"
                    +  "<td width=\"35%\" align=\"left\">"
                    +  "<input id=\"cmn120ChangeGrpBtn\" class=\"btn_group_n1\" onclick=\"return openAllGroup_no_submit(this.form.cmn120groupSid, 'cmn120groupSid', '-1', '0', 'changeGrp', 'cmn120userSid', '-1', '0', '0', '0', '0', 'atesakiSelForm', 'fakeButton2', 'cmn120banUserSid', 'cmn120banGroupSid', 'cmn120disableGroupSid')\" value=\"全グループから選択\"  type=\"button\"><br>";


                //グループ
                var grpSelStr = "";
                if (data.cmn120DspKbn == 0 || data.cmn120DspKbn == 1) {
                    grpSelStr += "<select name=\"cmn120groupSid\" id=\"cmn120ChangeGrp\" class=\"select01\">";
                    if (data.cmn120GroupList.length > 0) {
                        for (k = 0; k < data.cmn120GroupList.length; k++) {
                            var grpMdl = data.cmn120GroupList[k];

                            if (grpMdl.value != "sac") {
                                grpSelStr += "<option value=\""
                                    +  grpMdl.value
                                    +  "\"";
                                grpSelStr += ">"
                                    +  grpMdl.label
                                    +  "</option>";
                            } else {
                                grpSelStr += "<option class=\"select03\" value=\""
                                    +  grpMdl.value
                                    +  "\">"
                                    +  grpMdl.label
                                    +  "</option>";
                            }

                        }
                    }
                    grpSelStr += "</select>";
                    grpSelStr += "<input type=\"hidden\" name=\"cmn120MyGroupSid\" value=\""
                        +  data.cmn120MyGroupSid
                        +  "\" />";
                }

                //マイグループ
                var myGrpSelStr = "";
                if (data.cmn120DspKbn == 2) {
                    if (data.cmn120GroupList.length > 0) {
                        myGrpSelStr += "<select  id=\"cmn120ChangeGrp\" name=\"cmn120MyGroupSid\" class=\"select01\">";

                        for (j = 0; j < data.cmn120GroupList.length; j++) {
                            var myGrpMdl = data.cmn120GroupList[j];
                            myGrpSelStr += "<option value=\""
                                +  myGrpMdl.value
                                +  "\">"
                                +  myGrpMdl.label
                                +  "</option>";
                        }
                        myGrpSelStr += "</select>";
                    } else {
                        myGrpSelStr += "<input id=\"cmn120ChangeGrp\" type=\"hidden\" name=\"cmn120MyGroupSid\" value=\""
                            +  data.cmn120MyGroupSid
                            +  "\" />";
                        myGrpSelStr += "<span class=\"text_r1\">マイグループが作成されていません。</span>";
                    }
                    myGrpSelStr += "<input type=\"hidden\" name=\"cmn120groupSid\" value=\""
                        +  data.cmn120groupSid
                        +  "\" />";
                }

                //代表アカウント
                var deptAccountSelStr = "";
                if (data.cmn120DspKbn == 3) {
                    deptAccountSelStr += "<input type=\"hidden\" name=\"cmn120groupSid\" value=\""
                        +  data.cmn120groupSid
                        +  "\" />";
                    deptAccountSelStr += "<input type=\"hidden\" name=\"cmn120MyGroupSid\" value=\""
                        +  data.cmn120MyGroupSid
                        +  "\" />";
                    if (data.cmn120GroupList.length == 0) {
                        deptAccountSelStr += "<span class=\"text_r1\">代表アカウントが作成されていません。</span>";
                    }
                }

                atesakiSelStr += myGrpSelStr;
                atesakiSelStr += grpSelStr;
                atesakiSelStr += deptAccountSelStr;

                atesakiSelStr += "</td>"
                    +  "<td width=\"10%\" align=\"center\" valign=\"bottom\">"
                    +  "<input id=\"cmn120ChangeGrpBtn2\" type=\"button\" onclick=\"openGroupWindow(this.form.cmn120groupSid, 'cmn120groupSid', '0', 'changeGrp', '1', 'fakeButton', 'cmn120disableGroupSid')\" class=\"group_btn2\" value=\"&nbsp;&nbsp;\" id=\"cmn120GroupBtn\">"
                    +  "</td>"
                    +  "</tr>"
                    +  "<tr>"
                    +  "<td>";


                //グループ ユーザ 右
                var selUsrRightStr = "";
                selUsrRightStr += "<select id=\"cmn120SelectRightUser\" name=\"cmn120SelectRightUser\" multiple=\"multiple\" size=\"15\" class=\"select01\">";
                if (data.cmn120RightUserList.length > 0) {

                    for (l = 0; l < data.cmn120RightUserList.length; l++) {
                        var selGrpMdl = data.cmn120RightUserList[l];
                        selUsrRightStr += "<option value=\""
                            +  selGrpMdl.value
                            +  "\">"
                            +  htmlEscape(selGrpMdl.label)
                            +  "</option>";
                    }
                    selUsrRightStr += "<option value=\"-1\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>";
                }
                selUsrRightStr += "</select>";
                atesakiSelStr  += selUsrRightStr;


                atesakiSelStr += "</td>"
                    +  "<td align=\"center\">"
                    +  "<input type=\"button\" class=\"btn_base1add\" value=\"追加\" name=\"adduserBtn\" id=\"adduserBtn\">"
                    +  "<br><br>"
                    +  "<input type=\"button\" class=\"btn_base1del\" value=\"削除\" name=\"deluserBtn\" id=\"deluserBtn\">"
                    +  "<br>"
                    +  "</td>"
                    +  "<td align=\"center\">";


                //グループ ユーザ 左
                var selUsrLeftStr = "";
                selUsrLeftStr += "<select name=\"cmn120SelectLeftUser\" multiple=\"multiple\" size=\"15\" class=\"select01\">";
                if (data.cmn120LeftUserList.length > 0) {
                    for (m = 0; m < data.cmn120LeftUserList.length; m++) {
                        var selGrpLeftMdl = data.cmn120LeftUserList[m];
                        selUsrLeftStr += "<option value=\""
                            +  selGrpLeftMdl.value
                            +  "\">"
                            +  htmlEscape(selGrpLeftMdl.label)
                            +  "</option>";
                    }
                    selUsrLeftStr += "<option value=\"-1\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>";
                }

                selUsrLeftStr += "</select>";
                atesakiSelStr += selUsrLeftStr;

                atesakiSelStr += "</td>"
                    +  "</tr>"
                    +  "</table>"
                    +  "</td>"
                    +  "</tr>"
                    +  "</table>"
                    +  "<img src=\"../common/images/spacer.gif\" alt=\"\" height=\"10px\" width=\"10px\">"
                    +  "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">"
                    +  "<tr>"
                    +  "<td align=\"right\">"
                    +  "<input type=\"button\" id=\"sel_usr_pop_btn\" value=\"選択\" class=\"btn_base1\">"
                    +  "</td>"
                    +  "</tr>"
                    +  "</table>"
                    +  "</td>"
                    +  "</tr>"
                    +  "</table>"
                    +  "</td>"
                    +  "</tr>"
                    +  "</table>"
                    +  "</form>";

                $("#atesakiSelArea").children().remove();
                $("#atesakiSelArea").append(atesakiSelStr);

                if (data.cmn120DspKbn == 0 || data.cmn120DspKbn == 1) {
                    $('#cmn120ChangeGrpBtn').removeClass("display_none");
                    $('#cmn120ChangeGrpBtn2').removeClass("display_none");
                    $('#groupTab').removeClass("sel_group_notcheck");
                    $('#mygroupTab').addClass("sel_group_notcheck");
                    $('#deptAccountTab').addClass("sel_group_notcheck");
                    $('#cmn120DspKbn').val(1);
                } else if (data.cmn120DspKbn == 2) {
                    $('#cmn120ChangeGrpBtn').addClass("display_none");
                    $('#cmn120ChangeGrpBtn2').addClass("display_none");
                    $('#mygroupTab').removeClass("sel_group_notcheck");
                    $('#groupTab').addClass("sel_group_notcheck");
                    $('#deptAccountTab').addClass("sel_group_notcheck");
                    $('#cmn120DspKbn').val(2);
                } else if (data.cmn120DspKbn == 3) {
                    $('#cmn120ChangeGrpBtn').addClass("display_none");
                    $('#cmn120ChangeGrpBtn2').addClass("display_none");
                    $('#deptAccountTab').removeClass("sel_group_notcheck");
                    $('#groupTab').addClass("sel_group_notcheck");
                    $('#mygroupTab').addClass("sel_group_notcheck");
                    $('#cmn120DspKbn').val(3);
                }

                //コンボ設定
                if (data.cmn120DspKbn == 0 || data.cmn120DspKbn == 1) {
                    $("#cmn120ChangeGrp").val(data.cmn120groupSid);
                } else if (data.cmn120DspKbn == 2) {
                    $("#cmn120ChangeGrp").val(data.cmn120MyGroupSid);
                }

            } catch (ae) {
                alert(ae);
            }

        }).fail(function(data){
            alert('error');
        });

}



function getGroupUsrData(paramStr) {

    $.ajax({
        async: true,
        url:"../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        try {

            $("#selGrpUsrArea").children().remove();

            if (data.sml010userList.length > 0) {
                for (u = 0; u < data.sml010userList.length; u ++) {
                    var usrMdl = data.sml010userList[u];

                    if (usrMdl.usrSid != null && usrMdl.usrSid > 0) {
                        //通常ユーザ
                        $("#selGrpUsrArea").append("<div class=\"syain_checkbox_area\">"
                                + "<input class=\"syain_checkbox\" type=\"checkbox\" name=\"sml010usrSids\" value=\""
                                + usrMdl.usrSid
                                + "\" />"
                                + "<a class=\"syain_sel_check_txt syain_sel_txt\" id=\""
                                + usrMdl.usrSid
                                + "\">"
                                + htmlEscape(usrMdl.usiSei)
                                + "&nbsp;"
                                + htmlEscape(usrMdl.usiMei)
                                + "</a>"
                                + "</div>");
                    } else {
                        //作成アカウント
                        $("#selGrpUsrArea").append("<div class=\"syain_checkbox_area\">"
                                + "<input class=\"syain_checkbox\" type=\"checkbox\" name=\"sml010usrSids\" value=\"sac"
                                + usrMdl.sacSid
                                + "\" />"
                                + "<a class=\"syain_sel_check_txt syain_sel_txt\" id=\"sac"
                                + usrMdl.sacSid
                                + "\">"
                                + htmlEscape(usrMdl.sacName)
                                + "</a>"
                                + "</div>");
                    }

                }
            }

            $("input:checkbox[name=usrAllCheck]:checked").attr("checked", false)

        } catch (ae) {
            alert(ae);
        }

    }).fail(function(data){
        alert('error');
    });

}


function setAtesakiSelectUsr(id) {

    if ($('input:checkbox[name=sml010usrSids]:checked').length > 0) {


//      $('.head_menu_add_btn').click();
        if (!mail_create_flg) {
            //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送)
            $('input:hidden[name=sml020ProcMode]').val(0);
            paramStr = 'CMD=getNewmail&smlViewAccount='
                +   $("#account_comb_box").val();

            $.ajax({
                async: true,
                url:  "../smail/sml020.do",
                type: "post",
                data: paramStr
            }).done(function( data ) {
                tmp_del_flg = false;
//                $('.head_menu_add_btn').click();
                $('input:hidden[name=sml020ProcMode]').val(0);

                openSendTab();
                setCreateMail(0, data);
                setAtesakiSelectUsr(id);
            }).fail(function(data){
                alert('error');
            });
            return;
        }
        //新規作成確認時に編集画面を表示
        $('#head_menu_back_kakunin_btn').click();

        openSendTab();

        var inputName = "sml020userSid";
        var tagName = "atesaki_to_user";
        var areaName = "atesaki_to_area";

        if (id == 1) {
            inputName = "sml020userSidCc";
            tagName = "atesaki_cc_user";
            areaName = "atesaki_cc_area";
        } else if (id == 2) {
            inputName = "sml020userSidBcc";
            tagName = "atesaki_bcc_user";
            areaName = "atesaki_bcc_area";
        }

        $('input:checkbox[name=sml010usrSids]:checked').each(function(){

            var usrId = $(this).val();
            var usrTxt = $(this).next().text();

            if (usrId != null) {

                var selFlg = false;
                $('input:hidden[name=' + inputName + ']').each(function(){
                    if ($(this).val() == usrId) {
                        selFlg = true;
                    }
                })

                if (!selFlg) {

                    var selUsrText = "<div class=\""
                        + tagName
                        + "\" id=\""
                        + id
                        + "\">"
                        + htmlEscape(usrTxt)
                        + "<input type=\"hidden\" name=\""
                        + inputName
                        + "\" value=\""
                        + usrId
                        + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>";

                    $('#' + areaName).append(selUsrText);

                }
            }
        })

        if (id == 0) {
            if ($('#atesaki_to_area div').length > 3) {
                $('#alldsp_to_area').show();
                if (dsp_to_kbn == 1) {
                    $('#all_dsp_to_link').html('閉じる');
                } else {
                    $('#all_dsp_to_link').html('全て表示');
                    $('#atesaki_area').addClass('atesaki_scroll_on');
                    $('#atesaki_area').addClass('atesaki_scroll_area_height');

                    //IE9以下対応
                    //宛先がある一定数あると見た目上appendしていかない不具合対策
                    //クリックイベントを２回発生させて宛先のスクロールの開閉を実行する
                    var clickMe = document.getElementById('all_dsp_to_link');
                    clickMe.click();
                    clickMe.click();
                }
            }
        } else if (id == 1) {
            if ($('#atesaki_cc_area div').length > 3) {
                $('#alldsp_cc_area').show();
                if (dsp_cc_kbn == 1) {
                    $('#all_dsp_cc_link').html('閉じる');
                } else {
                    $('#all_dsp_cc_link').html('全て表示');
                    $('#cc_area').addClass('atesaki_scroll_on');
                    $('#cc_area').addClass('atesaki_scroll_area_height');

                    //IE9以下対応
                    //宛先がある一定数あると見た目上appendしていかない不具合対策
                    //クリックイベントを２回発生させて宛先のスクロールの開閉を実行する
                    var clickMe = document.getElementById('all_dsp_cc_link');
                    clickMe.click();
                    clickMe.click();
                }
            }
        } else if (id == 2) {
            if ($('#atesaki_bcc_area div').length > 3) {
                $('#alldsp_bcc_area').show();
                if (dsp_bcc_kbn == 1) {
                    $('#all_dsp_bcc_link').html('閉じる');
                } else {
                    $('#all_dsp_bcc_link').html('全て表示');
                    $('#bcc_area').addClass('atesaki_scroll_on');
                    $('#bcc_area').addClass('atesaki_scroll_area_height');

                    //IE9以下対応
                    //宛先がある一定数あると見た目上appendしていかない不具合対策
                    //クリックイベントを２回発生させて宛先のスクロールの開閉を実行する
                    var clickMe = document.getElementById('all_dsp_bcc_link');
                    clickMe.click();
                    clickMe.click();
                }
            }
        }

        $('input:checkbox[name=usrAllCheck]:checked').attr("checked", false);
        $('input:checkbox[name=sml010usrSids]:checked').attr("checked", false);

    } else {

        messagePop("ユーザを選択して下さい。", 250, 130);

    }

}


function drawSelUsr(id, txt) {


//  $('.head_menu_add_btn').click();

    if (!mail_create_flg) {
        //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送)
        $('input:hidden[name=sml020ProcMode]').val(0);
        paramStr = 'CMD=getNewmail&smlViewAccount='
            +   $("#account_comb_box").val();

        $.ajax({
            async: true,
            url:  "../smail/sml020.do",
            type: "post",
            data: paramStr
        }).done(function( data ) {
            tmp_del_flg = false;
//            $('.head_menu_add_btn').click();
            $('input:hidden[name=sml020ProcMode]').val(0);

            openSendTab();
            setCreateMail(0, data);
            drawSelUsr(id, txt);
        }).fail(function(data){
            alert('error');
        });
        return;
    }
    //新規作成確認時に編集画面を表示
    $('#head_menu_back_kakunin_btn').click();

    openSendTab();

    var selFlg = false;
    $('input:hidden[name=sml020userSid]').each(function(){
        if ($(this).val() == id) {
            selFlg = true;
        }
    })

    if (!selFlg) {
        if (id != null) {
            $('#atesaki_to_area').append("<div class=\"atesaki_to_user\" id=\"0\">"
                    + htmlEscape(txt)
                    + "<input type=\"hidden\" name=\"sml020userSid\" value=\""
                    + id
                    + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");


            if (dsp_to_kbn == 0) {

                if (3 < $('#atesaki_to_area div').length) {
                    $('#atesaki_area').addClass('atesaki_scroll_area_height');
                    $('#atesaki_area').addClass('atesaki_scroll_on');
                    $('#alldsp_to_area').show();
                    $('#all_dsp_to_link').html('全て表示');

                    //IE9以下対応
                    //宛先がある一定数あると見た目上appendしていかない不具合対策
                    //クリックイベントを２回発生させて宛先のスクロールの開閉を実行する
                    var clickMe = document.getElementById('all_dsp_to_link');
                    clickMe.click();
                    clickMe.click();
                }

            } else {
                if (3 < $('#atesaki_to_area div').length) {
                    $('#alldsp_to_area').show();
                    $('#all_dsp_to_link').html('閉じる');
                }
            }

        }
    }
}

function resetAllDispLink() {
    $('#alldsp_to_area').hide();
    $('#atesaki_area').removeClass('atesaki_scroll_on');
    $('#atesaki_area').removeClass('atesaki_scroll_area_height');

    $('#alldsp_cc_area').hide();
    $('#cc_area').removeClass('atesaki_scroll_on');
    $('#cc_area').removeClass('atesaki_scroll_area_height');

    $('#alldsp_bcc_area').hide();
    $('#bcc_area').removeClass('atesaki_scroll_on');
    $('#bcc_area').removeClass('atesaki_scroll_area_height');
}

function drawPopUsr() {

    if ($('#funcBtnKbn').val() == 0) {
        $('.atesaki_to_user').remove();
        $('#alldsp_to_area').hide();
        $("#atesaki_area").removeClass("atesaki_scroll_on");
        $("#atesaki_area").removeClass("atesaki_scroll_area_height");
        $('#cmn120SelectRightUser option').each(function(){

            if ($(this).val() != null && $(this).val() != 0 && $(this).val() != -1 && $(this).val() != "") {
                $('#atesaki_to_area').append("<div class=\"atesaki_to_user\" id=\"0\">" + htmlEscape($(this).text())
                        + "<input type=\"hidden\" name=\"sml020userSid\" value=\""
                        + $(this).val()
                        + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");

                if (3 < $('#atesaki_to_area div').length) {
                    $('#alldsp_to_area').show();
                    if (dsp_to_kbn == 1) {
                        $('#all_dsp_to_link').html('閉じる');
                    } else {
                        $('#all_dsp_to_link').html('全て表示');
                        $('#atesaki_area').addClass('atesaki_scroll_on');
                        $('#atesaki_area').addClass('atesaki_scroll_area_height');

                        //IE9以下対応
                        //宛先がある一定数あると見た目上appendしていかない不具合対策
                        //クリックイベントを２回発生させて宛先のスクロールの開閉を実行する
                        var clickMe = document.getElementById('all_dsp_to_link');
                        clickMe.click();
                        clickMe.click();
                    }
                } else {
                    $('#alldsp_to_area').hide();
                    if (dsp_to_kbn == 1) {
                        $('#all_dsp_to_link').html('閉じる');
                    } else {
                        $('#all_dsp_to_link').html('全て表示');
                    }
                }
            }
        });
    } else if ($('#funcBtnKbn').val() == 1) {
        $('.atesaki_cc_user').remove();
        $('#alldsp_cc_area').hide();
        $('#cc_area').removeClass('atesaki_scroll_on');
        $('#cc_area').removeClass('atesaki_scroll_area_height');
        $('#cmn120SelectRightUser option').each(function(){

            if ($(this).val() != null && $(this).val() != 0 && $(this).val() != -1 && $(this).val() != "") {
                $('#atesaki_cc_area').append("<div class=\"atesaki_cc_user\" id=\"1\">" + htmlEscape($(this).text())
                        + "<input type=\"hidden\" name=\"sml020userSidCc\" value=\""
                        + $(this).val()
                        + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");

                if (3 < $('#atesaki_cc_area div').length) {
                    $('#alldsp_cc_area').show();
                    if (dsp_cc_kbn == 1) {
                        $('#all_dsp_cc_link').html('閉じる');
                    } else {
                        $('#all_dsp_cc_link').html('全て表示');
                        $('#cc_area').addClass('atesaki_scroll_on');
                        $('#cc_area').addClass('atesaki_scroll_area_height');

                        //IE9以下対応
                        //宛先がある一定数あると見た目上appendしていかない不具合対策
                        //クリックイベントを２回発生させて宛先のスクロールの開閉を実行する
                        var clickMe = document.getElementById('all_dsp_cc_link');
                        clickMe.click();
                        clickMe.click();
                    }
                } else {
                    $('#alldsp_cc_area').hide();
                    if (dsp_cc_kbn == 1) {
                        $('#all_dsp_cc_link').html('閉じる');
                    } else {
                        $('#all_dsp_cc_link').html('全て表示');
                    }
                }
            }
        });
    } else if ($('#funcBtnKbn').val() == 2) {
        $('.atesaki_bcc_user').remove();
        $('#alldsp_bcc_area').hide();
        $('#bcc_area').removeClass('atesaki_scroll_on');
        $('#bcc_area').removeClass('atesaki_scroll_area_height');

        $('#cmn120SelectRightUser option').each(function(){

            if ($(this).val() != null && $(this).val() != 0 && $(this).val() != -1 && $(this).val() != "") {
                $('#atesaki_bcc_area').append("<div class=\"atesaki_bcc_user\" id=\"2\">" + htmlEscape($(this).text())
                        + "<input type=\"hidden\" name=\"sml020userSidBcc\" value=\""
                        + $(this).val()
                        + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");

                if (3 < $('#atesaki_bcc_area div').length) {
                    $('#alldsp_bcc_area').show();
                    if (dsp_bcc_kbn == 1) {
                        $('#all_dsp_bcc_link').html('閉じる');
                    } else {
                        $('#all_dsp_bcc_link').html('全て表示');
                        $('#bcc_area').addClass('atesaki_scroll_on');
                        $('#bcc_area').addClass('atesaki_scroll_area_height');

                        //IE9以下対応
                        //宛先がある一定数あると見た目上appendしていかない不具合対策
                        //クリックイベントを２回発生させて宛先のスクロールの開閉を実行する
                        var clickMe = document.getElementById('all_dsp_bcc_link');
                        clickMe.click();
                        clickMe.click();
                    }
                } else {
                    $('#alldsp_bcc_area').hide();
                    if (dsp_bcc_kbn == 1) {
                        $('#all_dsp_bcc_link').html('閉じる');
                    } else {
                        $('#all_dsp_bcc_link').html('全て表示');
                    }
                }
            }
        });
    } else if ($('#funcBtnKbn').val() == 3) {
        $('.atesaki_search_user').remove();
        $('#cmn120SelectRightUser option').each(function(){

            if ($(this).val() != null && $(this).val() != 0 && $(this).val() != -1 && $(this).val() != "") {
                $('#atesaki_search_area').append("<div class=\"atesaki_search_user\" id=\"3\">" + htmlEscape($(this).text())
                        + "<input type=\"hidden\" name=\"sml090Atesaki\" value=\""
                        + $(this).val()
                        + "\" />&nbsp;&nbsp;[<span class=\"add_search_usr_del\">削除</span>]</div>");
            }
        });
        $('#head_menu_search_btn2').click();
    }

    $('#atesakiSelPop').dialog('close');

}


function getNowSelUsr(functinBtnKbn) {

    var paramStr = "";

    if (functinBtnKbn == 0) {

        $('input:hidden[name=sml020userSid]').each(function(){
            paramStr += "&cmn120userSid=" + $(this).val();
        });

    } else if (functinBtnKbn == 1) {

        $('input:hidden[name=sml020userSidCc]').each(function(){
            paramStr += "&cmn120userSid=" + $(this).val();
        });

    } else if (functinBtnKbn == 2) {

        $('input:hidden[name=sml020userSidBcc]').each(function(){
            paramStr += "&cmn120userSid=" + $(this).val();
        });

    } else if (functinBtnKbn == 3) {

        $('input:hidden[name=sml090Atesaki]').each(function(){
            paramStr += "&cmn120userSid=" + $(this).val();
        });

    }

    return paramStr;

}
function getNowBanUsr() {
    var paramStr = "";
    $('input:hidden[name=sml010banUserSid]').each(function(){
        paramStr += "&cmn120banUserSid=" + $(this).val();
    });
    $('input:hidden[name=sml010disableGroupSid]').each(function(){
        paramStr += "&cmn120disableGroupSid=" + $(this).val();
    });
    return paramStr;

}

function drawSelHina(hinaSid, kbn) {

    document.forms[0].CMD.value='hinagataSetData';
    $('#sml020SelectHinaIdArea').append("<input type=\"hidden\" name=\"sml020SelectHinaId\" value=\"" + hinaSid + "\" />")

    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:"../smail/sml020.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        try {

            if (data != null) {

                var htmlAreaStr = "";
                if ($('input:hidden[name=sml020MailType]').val() == 1) {
                    //HTMLテキスト取得
                    if (tinyMCE.get('html_input') != null) {
                        try {
                            htmlAreaStr = tinyMCE.activeEditor.getContent().replace(/<[^>]*>/g, "");
                        } catch (ae) {
                        }
                    }
                }

                if ($('input[name=sml020Title]').val() != ""
                    || $('input[name=sml020Mark]:checked').val() != 0
                    || $('textarea[name=sml020Body]').val() != ""
                        || htmlAreaStr != ""
                ) {

                    $('#hinaOverWritePop').dialog({
                        autoOpen: true,  // hide dialog
                        bgiframe: true,   // for IE6
                        resizable: false,
                        height:160,
                        width: 400,
                        modal: true,
                        overlay: {
                            backgroundColor: '#000000',
                            opacity: 0.5
                        },
                        buttons: {
                            はい: function() {
                                //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送)
                                $('input:hidden[name=sml020ProcMode]').val(0);

                                //新規作成確認時に編集画面を表示
                                $('#head_menu_back_kakunin_btn').click();
//                              $('.head_menu_add_btn').click();
                                openSendTab();

                                $('input[name=sml020Title]').val(data.sml020Title);
                                $('input[name=sml020Mark]').val([data.sml020Mark]);
                                $('textarea[name=sml020Body]').val(data.sml020Body);

                                if ($('input:hidden[name=sml020MailType]').val() == 1) {
                                    //HTMLテキスト取得
                                    if (tinyMCE.get('html_input') != null) {
                                        try {
                                            htmlAreaStr = tinyMCE.get('html_input').setContent(textBr(data.sml020Body));
                                        } catch (ae) {
                                        }
                                    }
                                }

                                $(this).dialog('close');

                                if (kbn != 0) {
                                    $('#hinagata_pop').dialog('close');
                                }

                            },
                            いいえ: function() {
                                $(this).dialog('close');
                            }
                        }
                    });

                } else {
                    //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送)
                    $('input:hidden[name=sml020ProcMode]').val(0);

                    //新規作成確認時に編集画面を表示
                    $('#head_menu_back_kakunin_btn').click();
//                    $('.head_menu_add_btn').click();
                    var crFlg = mail_create_flg;
                    openSendTab();

                    $('input[name=sml020Title]').val(data.sml020Title);
                    $('input[name=sml020Mark]').val([data.sml020Mark]);
                    $('textarea[name=sml020Body]').val(data.sml020Body);
                    //HTMLテキスト取得
                    if (tinyMCE.get('html_input') != null) {
                        try {
                            htmlAreaStr = tinyMCE.get('html_input').setContent(textBr(data.sml020Body));
                        } catch (ae) {
                        }
                    }

                    if (kbn != 0) {
                        $('#hinagata_pop').dialog('close');
                    }
                    if (!crFlg) {
                        document.forms[0].CMD.value='getNewmail';
                        var paramStr = "";
                        paramStr += getFormData($('#smlForm'));

                        $.ajax({
                            async: true,
                            url:  "../smail/sml020.do",
                            type: "post",
                            data: paramStr
                        }).done(function( data ) {
                            tmp_del_flg = false;
//                            $('.head_menu_add_btn').click();
                            $('input:hidden[name=sml020ProcMode]').val(0);

                            openSendTab();
                            setCreateMail(0, data);
                        }).fail(function(data){
                            alert('error');
                        });

                    }

                }
            }

        } catch (ae) {
            alert(ae);
        }

    }).fail(function(data){
        alert('error');
    });

    $('#sml020SelectHinaIdArea').children().remove();
}



function resetMailCreate() {

    deleteTmpDir();
    $('.atesaki_to_user').remove();
    $('.atesaki_cc_user').remove();
    $('.atesaki_bcc_user').remove();
    $('input[name=sml020Title]').val('');
    $('input[name=sml020Mark]').val(['0']);

    if ($('input:hidden[name=sml020BodyHtml]').val() != ""
        && tinyMCE.get('html_input') != null) {
        tinyMCE.get('html_input').setContent('');
    }

    $('#text_input').val('');
    $('#html_input').val('');
    $('input:hidden[name=sml020BodyHtml]').val('');
    $('#sml020selectFiles').children().remove();
    $('#sml020ProcModeArea').children().remove();
    $('#head_menu_del_soko_btn').addClass('display_none');
    $('#head_menu_del_soko_btn_spacer').addClass('display_none');
}

function resetLabelArea() {

    document.forms[0].CMD.value='getLabelData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        var labelAreaStr = "";

        if (data != null && data.sml010LabelList != null && data.sml010LabelList.length > 0) {

            $('#gomibako_bottom_div').addClass("mail_left_line");

            var dspClass = "";
            var line_class = "mail_left_line_minus_bottom";
            if (!left_menu_label_opnkbn) {
                dspClass = "display_none";
                line_class = "mail_left_line_plus_bottom";
            }

            var labelAreaStr = "";

            labelAreaStr += "<div class=\"content_div\">"
                +  "<div class=\"" + line_class + " line_plus_minus folder_clear_float\"></div>"
                +  "<div class=\"mail_folder folder_float\"></div>"
                +  "<div class=\"mail_box_txt folder_float\" id=\"lable_top\">ラベル</div>"
                +  "</div>"
                +  "<div class=\"clear_div "
                +  dspClass
                +  "\">";

            var labelsize = data.sml010LabelList.length;

            for (i = 0; i < data.sml010LabelList.length; i++) {
                var lblMdl = data.sml010LabelList[i];
                var leftLineClass = "mail_left_line";

                if ((i + 1) == labelsize) {
                    leftLineClass = "mail_left_line_bottom";
                }

                var labelTxt = lblMdl.slbName;

                if (labelTxt.length > 10) {
                    labelTxt = labelTxt.substring(0, 9) + "…";
                }

                labelAreaStr += "<div class=\"content_div\">"
                    +  "<div class=\"clear_div\">"
                    +  "<div class=\"mail_folder_null folder_clear_float\"></div>"
                    +  "<div class=\""
                    +  leftLineClass
                    +  " folder_float\"></div>"
                    +  "<div class=\"mail_box_txt mail_box_lable_txt folder_float changeLabelDir\" id=\""
                    +  lblMdl.slbSid
                    +  "\">"
                    +  labelTxt;

                if (lblMdl.slbCount > 0) {
                    labelAreaStr += "("
                        +  lblMdl.slbCount
                        +  ")";
                }

                labelAreaStr += "</div>"
                    +  "<input type=\"hidden\" name=\"left_menu_label_name\" value=\""
                    +  lblMdl.slbName
                    +  "\" />"
                    +  "</div>"
                    +  "</div>";

            }

            labelAreaStr += "</div>";

            $('#labelArea').children().remove();
            $('#gomibako_bottom_div').removeClass("mail_left_line_bottom");

        } else {
            $('#gomibako_bottom_div').addClass("mail_left_line_bottom");
        }

        $('#labelArea').append(labelAreaStr);

    }).fail(function(data){
        alert('error');
    });

}

function deleteTmp() {

    var paramStr = "";

    paramStr += "CMD=deleteTmpData"
        +  "&sml020pluginId=smail";

    if ($('#sml020selectFiles').val() != null) {
        var fileList = $('#sml020selectFiles').val();
        for (p = 0; p < fileList.length; p++) {
            paramStr += "&sml020selectFiles="
                +  fileList[p];
        }
    }

    $.ajax({
        async: true,
        url:"../smail/sml020.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        try {

            if (data.sml020selectFiles.length > 0) {
                for (s = 0; s < data.sml020selectFiles.length; s++) {
                    $('select#sml020selectFiles option[value=' + data.sml020selectFiles[s] + ']').remove();
                }
            }

        } catch (ae) {
            alert(ae);
        }

    }).fail(function(data){
        alert('error');
    });

}

function deleteTmpDir() {

    var paramStr = "";

    paramStr += "CMD=deleteTmpDirData";

    $.ajax({
        async: true,
        url:"../smail/sml020.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

    }).fail(function(data){
        alert('error');
    });
}

function doMailSend() {

    loadingPop("処理中");

    document.forms[0].CMD.value='sendCheck';

    if ($('input:hidden[name=sml020MailType]').val() != 0) {
        if (tinyMCE.get('html_input') != null) {
            $('input:hidden[name="sml020BodyHtml"]').val(tinyMCE.get('html_input').getContent());
        }
    }


    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:"../smail/sml020.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        try {

            if (data.errorsList != null && data.errorsList.length > 0) {
                var dialogWidth = 350;
                var dialogHeight = 140;
                var errorMsg = "";
                for (e = 0; e < data.errorsList.length; e++) {
                    errorMsg += "<div class=\"errorMsgStr\">" + data.errorsList[e] + "</div>";
                    if (e != 0) {
                        dialogHeight += 25;
                    }
                    if (data.errorsList[e].length > 30) {
                        dialogWidth = 630;
                        dialogHeight += 40;
                    }
                }
                if (dialogHeight > 200) {
                    dialogWidth = 630;
                    dialogHeight = 340;
                    errorMsg = "<div style=\"vertical-align:middle;overflow:auto;width:500px;height:200px;font-weight:bold;font-size:14px;\">" + errorMsg + "</div>";

                }
                messagePop(errorMsg, dialogWidth, dialogHeight);

            } else {

                doMailSendKakunin();

            }

        } catch (ae) {
            alert(ae);
        } finally {
            $('#loading_pop').dialog('close');
        }

    }).fail(function(data){
        alert('error');
        $('#loading_pop').dialog('close');
    });

}

function doMailSoko() {

    if ($('input:hidden[name=sml020MailType]').val() != 0) {
        if (tinyMCE.get('html_input') != null) {
            $('input:hidden[name="sml020BodyHtml"]').val(tinyMCE.get('html_input').getContent());
        }
    }

    document.forms[0].CMD.value='sokoData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:"../smail/sml020.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        try {

            if (data.errorsList != null && data.errorsList.length > 0) {
                var dialogWidth = 350;
                var dialogHeight = 140;
                var errorMsg = "";
                for (e = 0; e < data.errorsList.length; e++) {
                    errorMsg += "<div class=\"errorMsgStr\">" + data.errorsList[e] + "</div>";
                    if (e != 0) {
                        dialogHeight += 25;
                    }
                    if (data.errorsList[e].length > 30) {
                        dialogWidth = 630;
                        dialogHeight += 40;
                    }
                }

                messagePop(errorMsg, dialogWidth, dialogHeight);

            } else {

                sendMailPop("草稿に保存しました。", 220, 150);

            }

        } catch (ae) {
            alert(ae);
        }

    }).fail(function(data){
        alert('error');
    });
}


function doMailSendKakunin() {

    document.forms[0].CMD.value='getInitData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml020kn.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        mail_create_kakunin_flg = true;
        changeHelpParam(1,1);

        $('.mail_create_area').addClass('display_none');
        $('.mail_create_kakunin_area').removeClass('display_none');

        //宛先
        if (data.sml020Atesaki != null) {
            var detail = data.sml020Atesaki;
            if (detail.atesakiList.length > 0) {
                var atesakiStr = "";

                atesakiStr += "<td class=\"mail_create_sel_send\">";

                if (data.photoDspFlg=="0") {
                    //写真表示する
                    atesakiStr += "<table class=\"clear_table\"><tr><td width=\"50px\" valign=\"top\">";

                    atesakiStr += "<div class=\"mail_check_txt2\">宛先</div>";

                    atesakiStr += "</td>";

                    atesakiStr += "<td style=\"padding-top:5px;padding-right:5px;padding-bottom:0px;padding-left:5px;\" valign=\"middle\" align=\"left\">";

                    for (d = 0; d < detail.atesakiList.length; d++) {

                        var atesaki = detail.atesakiList[d];

                        atesakiStr += "<span class=\"photoList\">";

                        if (atesaki.photoFileDsp == "1") {
                            atesakiStr += "<div align=\"center\" class=\"photo_hikokai2\"><span style=\"color:#fc2929;\">非公開</span></div>";
                        }

                        if (atesaki.photoFileDsp == "0") {
                            if (atesaki.binFileSid == "0") {
                                atesakiStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                            }
                            if (atesaki.binFileSid != "0") {
                                if (atesaki.usrJkbn == "9") {
                                    atesakiStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                                } else {
                                    atesakiStr += "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
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
                        }

                        atesakiStr += "<div class=\"text_base\" align=\"center\">";
                        atesakiStr += "<span class=\"mail_check_txt\" style=\"white-space: nowrap\">";

                        if (atesaki.usrSid > 0) {
                            if (atesaki.usrJkbn == "0") {
                                atesakiStr += htmlEscape(atesaki.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesaki.usiMei);
                            }

                            if (atesaki.usrJkbn == "9") {
                                atesakiStr += "<del>" + htmlEscape(atesaki.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesaki.usiMei) + "</del>";
                            }
                        } else {
                            if (atesaki.accountJkbn == "0") {
                                atesakiStr += htmlEscape(atesaki.accountName);
                            }

                            if (atesaki.accountJkbn == "1") {
                                atesakiStr += "<del>" + htmlEscape(atesaki.accountName) + "</del>";
                            }
                        }


                        atesakiStr += "</span></div></span>";

                    }

                    atesakiStr += "</td></tr></table>";

                } else {
                    //写真表示しない
                    atesakiStr += "<table class=\"mail_check_txt\"><tr><td valign=\"top\" nowrap>";
                    atesakiStr += "<div style=\"padding-top:5px\" class=\"mail_check_txt2\">";
                    atesakiStr += "宛先　：";
                    atesakiStr += "</td><td>"
                    atesakiStr += "<div style=\"padding-top:5px\">";


                    //宛先一覧
                    for (d = 0; d < detail.atesakiList.length; d++) {
                        var atesaki = detail.atesakiList[d];

                        if (atesaki.usrSid > 0) {
                            if (atesaki.usrJkbn == "0") {
                                atesakiStr += htmlEscape(atesaki.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesaki.usiMei);
                            }
                            if (atesaki.usrJkbn == "9") {
                                atesakiStr += "<del>" + htmlEscape(atesaki.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesaki.usiMei) + "</del>";
                            }
                        } else {
                            if (atesaki.accountJkbn == "0") {
                                atesakiStr += htmlEscape(atesaki.accountName);
                            }
                            if (atesaki.accountJkbn == "1") {
                                atesakiStr += "<del>" + htmlEscape(atesaki.accountName) + "</del>";
                            }
                        }

                        if (d != (detail.atesakiList.length) - 1) {
                            atesakiStr += ",&nbsp;&nbsp;";
                        }
                    }

                    atesakiStr += "</div>";
                    atesakiStr += "</td></tr></table>"
                }

                atesakiStr += "</td>";
            }
            $('#atesaki_to_kakunin_area').append(atesakiStr);
        }

        //CC
        if (data.sml020AtesakiCc != null) {
            var detailCc = data.sml020AtesakiCc;
            if (detailCc.atesakiList.length > 0) {
                var atesakiCcStr = "";

                atesakiCcStr += "<td class=\"mail_create_sel_send\">";

                if (data.photoDspFlg=="0") {
                    //写真表示する
                    atesakiCcStr += "<table class=\"clear_table\"><tr><td width=\"50px\" valign=\"top\">";

                    atesakiCcStr += "<div class=\"mail_check_txt2\">CC</div>";

                    atesakiCcStr += "</td>";

                    atesakiCcStr += "<td style=\"padding-top:5px;padding-right:5px;padding-bottom:0px;padding-left:5px;\" valign=\"middle\" align=\"left\">";

                    for (d = 0; d < detailCc.atesakiList.length; d++) {

                        var atesakiCc = detailCc.atesakiList[d];

                        atesakiCcStr += "<span class=\"photoList\">";



                        if (atesakiCc.photoFileDsp == "1") {
                            atesakiCcStr += "<div align=\"center\" class=\"photo_hikokai2\"><span style=\"color:#fc2929;\">非公開</span></div>";
                        }

                        if (atesakiCc.photoFileDsp == "0") {
                            if (atesakiCc.binFileSid == "0") {
                                atesakiCcStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                            }
                            if (atesakiCc.binFileSid != "0") {
                                if (atesakiCc.usrJkbn == "9") {
                                    atesakiCcStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                                } else {
                                    atesakiCcStr += "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                        +  atesakiCc.binFileSid
                                        +   "&smlViewAccount="
                                        +   $("#account_comb_box").val()
                                        +  "\" name=\"userImage"
                                        +  atesakiCc.usrSid
                                        +  "\" alt=\"写真\" border=\"0\" width=\"50px\" onload=\"initImageView50('userImage"
                                        +  atesakiCc.usrSid
                                        +  "');\" class=\"img_bottom\">";
                                }
                            }
                        }

                        atesakiCcStr += "<div class=\"text_base\" align=\"center\">";
                        atesakiCcStr += "<span class=\"mail_check_txt\" style=\"white-space: nowrap\">";

                        if (atesakiCc.usrSid > 0) {
                            if (atesakiCc.usrJkbn == "0") {
                                atesakiCcStr += htmlEscape(atesakiCc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiCc.usiMei);
                            }

                            if (atesakiCc.usrJkbn == "9") {
                                atesakiCcStr += "<del>" + htmlEscape(atesakiCc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiCc.usiMei) + "</del>";
                            }
                        } else {
                            if (atesakiCc.accountJkbn == "0") {
                                atesakiCcStr += htmlEscape(atesakiCc.accountName);
                            }

                            if (atesakiCc.accountJkbn == "1") {
                                atesakiCcStr += "<del>" + htmlEscape(atesakiCc.accountName) + "</del>";
                            }
                        }


                        atesakiCcStr += "</span></div></span>";
                    }

                    atesakiCcStr += "</td></tr></table>";

                } else {
                    //写真表示しない
                    atesakiCcStr += "<table class=\"mail_check_txt\"><tr><td valign=\"top\" nowrap>";
                    atesakiCcStr += "<div style=\"padding-top:5px\" class=\"mail_check_txt2\">";
                    atesakiCcStr += "CC　&nbsp;&nbsp;：";
                    atesakiCcStr += "</td><td>"
                    atesakiCcStr += "<div style=\"padding-top:5px\">";



                    //CC一覧
                    for (d = 0; d < detailCc.atesakiList.length; d++) {
                        var atesakiCc = detailCc.atesakiList[d];

                        if (atesakiCc.usrSid > 0) {
                            if (atesakiCc.usrJkbn == "0") {
                                atesakiCcStr += htmlEscape(atesakiCc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiCc.usiMei);
                            }
                            if (atesakiCc.usrJkbn == "9") {
                                atesakiCcStr += "<del>" + htmlEscape(atesakiCc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiCc.usiMei) + "</del>";
                            }
                        } else {
                            if (atesakiCc.accountJkbn == "0") {
                                atesakiCcStr += htmlEscape(atesakiCc.accountName);
                            }
                            if (atesakiCc.accountJkbn == "1") {
                                atesakiCcStr += "<del>" + htmlEscape(atesakiCc.accountName) + "</del>";
                            }
                        }

                        if (d != (detailCc.atesakiList.length) - 1) {
                            atesakiCcStr += ",&nbsp;&nbsp;";
                        }
                    }

                    atesakiCcStr += "</div>";
                    atesakiCcStr += "</td></tr></table>";

                }

                atesakiCcStr += "</td>";
            }
            $('#atesaki_cc_kakunin_area').append(atesakiCcStr);
        }


        //BCC
        if (data.sml020AtesakiBcc != null) {
            var detailBcc = data.sml020AtesakiBcc;
            if (detailBcc.atesakiList.length > 0) {
                var atesakiBccStr = "";

                atesakiBccStr += "<td class=\"mail_create_sel_send\">";

                if (data.photoDspFlg=="0") {
                    //写真表示する
                    atesakiBccStr += "<table class=\"clear_table\"><tr><td width=\"50px\" valign=\"top\">";

                    atesakiBccStr += "<div class=\"mail_check_txt2\">BCC</div>";

                    atesakiBccStr += "</td>";

                    atesakiBccStr += "<td style=\"padding-top:5px;padding-right:5px;padding-bottom:0px;padding-left:5px;\" valign=\"middle\" align=\"left\">";

                    for (d = 0; d < detailBcc.atesakiList.length; d++) {

                        var atesakiBcc = detailBcc.atesakiList[d];

                        atesakiBccStr += "<span class=\"photoList\">";

                        if (atesakiBcc.photoFileDsp == "1") {
                            atesakiBccStr += "<div align=\"center\" class=\"photo_hikokai2\"><span style=\"color:#fc2929;\">非公開</span></div>";
                        }

                        if (atesakiBcc.photoFileDsp == "0") {
                            if (atesakiBcc.binFileSid == "0") {
                                atesakiBccStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                            }
                            if (atesakiBcc.binFileSid != "0") {
                                if (atesakiBcc.usrJkbn == "9") {
                                    atesakiBccStr += "<img src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\" class=\"img_bottom\">";
                                } else {
                                    atesakiBccStr += "<img src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                        +  atesakiBcc.binFileSid
                                        +   "&smlViewAccount="
                                        +   $("#account_comb_box").val()
                                        +  "\" name=\"userImage"
                                        +  atesakiBcc.usrSid
                                        +  "\" alt=\"写真\" border=\"0\" width=\"50px\" onload=\"initImageView50('userImage"
                                        +  atesakiBcc.usrSid
                                        +  "');\" class=\"img_bottom\">";
                                }
                            }
                        }

                        atesakiBccStr += "<div class=\"text_base\" align=\"center\">";
                        atesakiBccStr += "<span class=\"mail_check_txt\" style=\"white-space: nowrap\">";

                        if (atesakiBcc.usrSid > 0) {
                            if (atesakiBcc.usrJkbn == "0") {
                                atesakiBccStr += htmlEscape(atesakiBcc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiBcc.usiMei);
                            }

                            if (atesakiBcc.usrJkbn == "9") {
                                atesakiBccStr += "<del>" + htmlEscape(atesakiBcc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiBcc.usiMei) + "</del>";
                            }
                        } else {
                            if (atesakiBcc.accountJkbn == "0") {
                                atesakiBccStr += htmlEscape(atesakiBcc.accountName);
                            }

                            if (atesakiBcc.accountJkbn == "1") {
                                atesakiBccStr += "<del>" + htmlEscape(atesakiBcc.accountName) + "</del>";
                            }
                        }


                        atesakiBccStr += "</span></div></span>";
                    }

                    atesakiBccStr += "</td></tr></table>"
                } else {
                    //写真表示しない
                    atesakiBccStr += "<table class=\"mail_check_txt\"><tr><td valign=\"top\" nowrap>";
                    atesakiBccStr += "<div style=\"padding-top:5px\" class=\"mail_check_txt2\">";
                    atesakiBccStr += "BCC　：";
                    atesakiBccStr += "</td><td>"
                    atesakiBccStr += "<div style=\"padding-top:5px\">";

                    //BCC一覧
                    for (d = 0; d < detailBcc.atesakiList.length; d++) {
                        var atesakiBcc = detailBcc.atesakiList[d];

                        if (atesakiBcc.usrSid > 0) {
                            if (atesakiBcc.usrJkbn == "0") {
                                atesakiBccStr += htmlEscape(atesakiBcc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiBcc.usiMei);
                            }
                            if (atesakiBcc.usrJkbn == "9") {
                                atesakiBccStr += "<del>" + htmlEscape(atesakiBcc.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiBcc.usiMei) + "</del>";
                            }
                        } else {
                            if (atesakiBcc.accountJkbn == "0") {
                                atesakiBccStr += htmlEscape(atesakiBcc.accountName);
                            }
                            if (atesakiBcc.accountJkbn == "1") {
                                atesakiBccStr += "<del>" + htmlEscape(atesakiBcc.accountName) + "</del>";
                            }
                        }

                        if (d != (detailBcc.atesakiList.length) - 1) {
                            atesakiBccStr += ",&nbsp;&nbsp;";
                        }
                    }

                    atesakiBccStr += "</div>";
                    atesakiBccStr += "</td></tr></table>"

                }

                atesakiBccStr += "</td>";

            }
            $('#atesaki_bcc_kakunin_area').append(atesakiBccStr);
        }

        //件名
        var mailTitle = "";
        mailTitle += "<td class=\"mail_create_sel_send\">"
            +  "<div style=\"padding-top:5px\" class=\"mail_create_sel_send_title\">"
            +  "<span class=\"mail_create_title_kakunin\">"
            +  htmlEscape(data.sml020Title)
            +  "</span>"
            +  "</div>"
            +  "</td>";
        $('#mail_create_title_kakunin_area').append(mailTitle);


        //マーク
        var mailMark = "";
        if ($('#markKey_' + data.sml020Mark).attr('id') != null && $('#markKey_' + data.sml020Mark).val() != "") {
            mailMark += "<td class=\"mail_create_sel_send\">"
                +  "<div style=\"padding-top:5px\" class=\"mail_create_sel_send_title\">"
                +  $('#markKey_' + data.sml020Mark).val()
                +  "</div>"
                +  "</td>";
        }
        $('#mail_create_mark_kakunin_area').append(mailMark);

        //添付ファイル
        var tmpStr = "";
        if (data.sml020FileLabelList.length > 0) {
            tmpStr += "<td class=\"mail_create_sel_send\"><table class=\"clear_table\">"
                for (e = 0; e < data.sml020FileLabelList.length; e++) {
                    var fileMdl = data.sml020FileLabelList[e];
                    tmpStr +=  "<tr><td><div style=\"\" class=\"mail_create_kakunin_temp\">"
                        +   "<div class=\"\"><a class=\"text_link\" href=\"javascript:void(0);\" onClick=\"return fileLinkClickKn('"
                        +   fileMdl.value
                        +   "');\">"
                        +   fileMdl.label
                        +   "</a></div></div></td></tr>";
                }
            tmpStr += "</table></td>";
            $('#mail_create_tmp_kakunin_area').append(tmpStr);
        }

        //本文
        var mailbody = "";
        mailbody += "<td class=\"mail_create_sel_send2 mail_create_sel_send_top mail_create_sel_send_bottom\">"
            +  "<div style=\"padding-top:5px;padding-bottom:5px;\" class=\"mail_create_sel_send_body2\">"
            +  data.sml020knSmsBody
            +  "</div>"
            +  "</td>";
        $('#mail_create_body_kakunin_area').append(mailbody);



    }).fail(function(data){
        alert('error');
    });
}

function delCreateKakuninArea() {
    $('#atesaki_to_kakunin_area').html("");
    $('#atesaki_cc_kakunin_area').html("");
    $('#atesaki_bcc_kakunin_area').html("");
    $('#mail_create_mark_kakunin_area').html("");
    $('#mail_create_title_kakunin_area').html("");
    $('#mail_create_tmp_kakunin_area').html("");
    $('#mail_create_body_kakunin_area').html("");
}

function doMailSendSoushin() {

    document.forms[0].CMD.value='sendData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    loadingPop("送信中");

    $.ajax({
        async: true,
        url:  "../smail/sml020kn.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.errorsList != null && data.errorsList.length > 0) {
            var dialogWidth = 350;
            var dialogHeight = 140;
            var errorMsg = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsg += "<div class=\"errorMsgStr\">" + data.errorsList[e] + "</div>";
                if (e != 0) {
                    dialogHeight += 25;
                }
                if (data.errorsList[e].length > 30) {
                    dialogWidth = 630;
                    dialogHeight += 40;
                }
            }

            $('#loading_pop').dialog('close');
            messagePop(errorMsg, dialogWidth, dialogHeight);

        } else {
            $('#loading_pop').dialog('close');
            sendMailPop("[メッセージ]の送信が完了しました。", 320, 150);
        }
    }).fail(function(data){
        alert('error');
    });
}


function deleteMail() {

    document.forms[0].CMD.value='deleteData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml030.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.messageList != null && data.messageList.length > 0) {
            deleteMailPop(data, 0);
        }
    }).fail(function(data){
        alert('error');
    });
}

function exportMail(cmd, searchFlg) {
    var exportUrl = "../smail/sml010.do";
    if (searchFlg) {
        exportUrl = "../smail/sml090.do";
    }
    exportUrl += '?CMD=' + cmd
    + '&smlViewAccount=' + document.forms[0].smlViewAccount.value
    + '&sml010ProcMode=' + document.forms[0].sml010ProcMode.value
    + '&sml010SelectedMailKbn=' + document.forms[0].sml010SelectedMailKbn.value
    + '&sml010SelectLabelSid=' + document.forms[0].sml010SelectLabelSid.value
    + '&sml010Sort_key=' + document.forms[0].sml010Sort_key.value
    + '&sml010Order_key=' + document.forms[0].sml010Order_key.value;

    var delSidName = 'sml010DelSid';
    if (searchFlg) {
        delSidName = 'sml090DelSid';
        exportUrl += '&sml090SvMailSyubetsu=' + document.forms[0].sml090SvMailSyubetsu.value;
    }

    var delSidList = document.getElementsByName(delSidName);
    var checked = false;

    for (sidIdx = 0; sidIdx < delSidList.length; sidIdx++) {
        if (delSidList[sidIdx].checked) {
            checked = true;
            exportUrl += "&" + delSidName + "=" + delSidList[sidIdx].value;
        }
    }
    if (!checked) {
        messagePop(errMsg_noselect_reload, 400, 150);
        return;
    }

    document.getElementById('sml010Export').src = exportUrl;
}

function doExportByPdfMail() {
//  document.forms[0].CMD.value='exportByPdfData';
//  document.forms[0].submit();
    exportMail('exportByPdfData', false);
}

function doExportByEmlMail() {
//  document.forms[0].CMD.value='exportByEmlData';
//  document.forms[0].submit();
    exportMail('exportByEmlData', false);
}

function doExportByPdfSearchMail() {

//  $("#smlForm").attr("action", "../smail/sml090.do");

//  document.forms[0].CMD.value='exportByPdfData';
//  document.forms[0].submit();

//  $("#smlForm").attr("action", "../smail/sml010.do");
    exportMail('exportByPdfData', true);
}

function doExportByEmlSearchMail() {

//  $("#smlForm").attr("action", "../smail/sml090.do");

//  document.forms[0].CMD.value='exportByEmlData';
//  document.forms[0].submit();

//  $("#smlForm").attr("action", "../smail/sml010.do");
    exportMail('exportByEmlData', true);
}

function doDeleteMail() {

    document.forms[0].CMD.value='deleteOkData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml030.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.messageList != null && data.messageList.length > 0) {
            delMessagePop(data.messageList[0], 350, 160, 0);
        }
    }).fail(function(data){
        alert('error');
    });
}

function doDeleteMailAll() {

    document.forms[0].CMD.value='deleteAllOkData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml030.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.messageList != null && data.messageList.length > 0) {
            delMessagePop(data.messageList[0], 350, 160, 0);
        }

    }).fail(function(data){
        alert('error');
    });
}

function deleteMailAll() {

    document.forms[0].CMD.value='deleteAllData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml030.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsgStr += data.errorsList[e];
            }
            messagePop(errorMsgStr, 450, 300);

        } else if (data.messageList != null && data.messageList.length > 0) {
            if (data.messageList.length > 0) {
                deleteMailPop(data, 1);
            }
        }
    }).fail(function(data){
        alert('error');
    });
}

function doKidokuMail() {

    document.forms[0].CMD.value='kidokuOkData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        reloadData();
    }).fail(function(data){
        alert('error');
    });
}

function doMidokuMail() {

    document.forms[0].CMD.value='midokuOkData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        reloadData();
    }).fail(function(data){
        alert('error');
    });
}

function doKidokuSearchMail() {

    document.forms[0].CMD.value='kidokuOkData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        reloadData();
    }).fail(function(data){
        alert('error');
    });
}

function doMidokuSearchMail() {

    document.forms[0].CMD.value='midokuOkData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        reloadData();
    }).fail(function(data){
        alert('error');
    });
}


function deleteSokoMail() {

    document.forms[0].CMD.value='deleteKnData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml020.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.messageList != null && data.messageList.length > 0) {
            deleteSokoMailPop(data, 0);
        }
    }).fail(function(data){
        alert('error');
    });
}

function doDeleteSokoMail() {

    document.forms[0].CMD.value='deleteOkData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml020.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.messageList != null && data.messageList.length > 0) {
            delMessagePop(data.messageList[0], 350, 160, 2);
        }
    }).fail(function(data){
        alert('error');
    });
}

function pdfListMail() {

    document.forms[0].CMD.value='msg_pdfData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "PDF出力するメッセージを選択して下さい。";
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                expMailPop(data, 1);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function emlListMail() {

    document.forms[0].CMD.value='msg_emlData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "eml出力するメッセージを選択して下さい。";
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                expMailPop(data, 2);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function pdfListSearchMail() {

    document.forms[0].CMD.value='msg_pdfData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "PDF出力するメッセージを選択して下さい。";
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                expMailSearchPop(data, 1);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function emlListSearchMail() {

    document.forms[0].CMD.value='msg_emlData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "eml出力するメッセージを選択して下さい。";
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                expMailSearchPop(data, 2);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function kidokuListMail() {

    document.forms[0].CMD.value='msg_kidokuData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "既読にするメッセージを選択して下さい。";
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                readKbnMailPop(data, 1);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function midokuListMail() {

    document.forms[0].CMD.value='msg_midokuData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "未読にするメッセージを選択して下さい。";
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                readKbnMailPop(data, 2);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function kidokuSearchListMail() {

    document.forms[0].CMD.value='msg_kidokuData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "既読にするメッセージを選択して下さい。";
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                readKbnSearchMailPop(data, 1);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function midokuSearchListMail() {

    document.forms[0].CMD.value='msg_midokuData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "未読にするメッセージを選択して下さい。";
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                readKbnSearchMailPop(data, 2);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function deleteListMail() {

    document.forms[0].CMD.value='msg_deleteData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsgStr += data.errorsList[e];
            }
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                deleteMailPop(data, 2);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function doDeleteListMail() {

    document.forms[0].CMD.value='deleteDataOk';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    //選択パラメータの最終チェック
    var checked = false;
    $('input:checkbox[name=sml010DelSid]:checked').each(function(){
        checked = true;
    });
    if (!checked) {
        messagePop(errMsg_noselect_reload, 400, 150);
        return;
    }
    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.messageList != null && data.messageList.length > 0) {
            delMessagePop(data.messageList[0], 350, 160, 1);
        }
    }).fail(function(data){
        alert('error');
    });
}


function revivedListMail() {

    document.forms[0].CMD.value='revivedData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsgStr += data.errorsList[e];
            }
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                deleteMailPop(data, 3);
            }
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}

function doRevivedListMail() {

    document.forms[0].CMD.value='revivedDataOk';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    var checked = false;
    $('input:checkbox[name=sml010DelSid]:checked').each(function(){
        checked = true;
    });
    if (!checked) {
        messagePop(errMsg_noselect_reload, 400, 150);
        return;
    }

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.messageList != null && data.messageList.length > 0) {
            delMessagePop(data.messageList[0], 400, 160, 1);
        }
    }).fail(function(data){
        alert('error');
    });
}

function emptyTrash() {

    document.forms[0].CMD.value='gomibakoDataClear';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsgStr += data.errorsList[e];
            }
            messagePop(errorMsgStr, 400, 150);
        } else {
            if (data.messageList != null && data.messageList.length > 0) {
                deleteMailPop(data, 4);
            } else {
                readPop("ゴミ箱にメールがありません。", 400, 150);
            }
        }

    }).fail(function(data){
        alert('error');
    });
}

function doEmptyTrash() {

    document.forms[0].CMD.value='clearDataOk';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.messageList != null && data.messageList.length > 0) {
            messagePop(data.messageList[0], 400, 160);
            reloadData();
        }
    }).fail(function(data){
        alert('error');
    });
}


function addLabelListMail() {

    document.forms[0].CMD.value='msg_addLabel';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsgStr += data.errorsList[e];
            }
            messagePop(errorMsgStr, 400, 150);
        } else {
            labelAddPop();
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });

}

function deleteLabelListMail() {

    document.forms[0].CMD.value='msg_deleteLabel';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsgStr += data.errorsList[e];
            }
            messagePop(errorMsgStr, 400, 150);
        } else {
            labelDeletePop();
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}


function labelAddPop() {

    document.forms[0].CMD.value='getLabelData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {


        $('#labelAddContentArea').children().remove();
        var labelContentStr = "";

        if (data != null && data.labelCombo != null && data.labelCombo.length > 0) {

            var optionStr = "";
            for (i = 0; i < data.labelCombo.length; i++) {
                var labelData = data.labelCombo[i];
                optionStr += "<option value=\""
                    +  labelData.value
                    +  "\">"
                    +  labelData.label
                    +  "</option>";
            }

            labelContentStr = "<tr>"
                + "<td>"
                + "<input type=\"radio\" name=\"sml010addLabelT\" value=\"0\" id=\"addLabelType0\" checked=\"\"><label for=\"addLabelType0\">ラベルを選択</label>"
                + "</td>"
                + "<td>"
                + "<select id=\"label_dialog_sel\" style=\"width:180px;\">"
                + optionStr
                + "</select>"
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "<input type=\"radio\" name=\"sml010addLabelT\" id=\"addLabelType1\" value=\"1\"><label for=\"addLabelType1\">新規ラベル</label>"
                + "</td>"
                + "<td>"
                + "<input type=\"text\" id=\"label_dialog_new\" maxlength=\"100\" disabled=\"\">"
                + "<span id=\"addLabelParam\"></span>"
                + "</td>"
                + "</tr>";

        } else {

            labelContentStr = "<tr>"
                + "<td>"
                + "新規ラベル<input type=\"text\" id=\"label_dialog_new\" maxlength=\"100\" />"
                + "<span id=\"addLabelParam\"></span>"
                + "<input type=\"hidden\" name=\"sml010addLabelType\" value=\"1\">"
                + "</td>"
                + "</tr>";

        }

        $('#labelAddContentArea').append(labelContentStr);

        $('#labelAddPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height:160,
            width: 400,
            modal: true,
            overlay: {
                backgroundColor: '#000000',
                opacity: 0.5
            },
            buttons: {
                追加: function() {
                    doLabel(0);
                },
                閉じる: function() {

                    $(this).dialog('close');

                }
            }
        });

    }).fail(function(data){
        alert('error');
    });

}


function labelDeletePop() {

    document.forms[0].CMD.value='getLabelData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {


        $('#labelDelContentArea').children().remove();
        var labelContentStr = "";

        if (data != null && data.labelCombo != null && data.labelCombo.length > 0) {

            var optionStr = "";
            for (i = 0; i < data.labelCombo.length; i++) {
                var labelData = data.labelCombo[i];
                optionStr += "<option value=\""
                    +  labelData.value
                    +  "\">"
                    +  labelData.label
                    +  "</option>";
            }

            labelContentStr = "<tr>"
                + "<td>"
                + "<select id=\"label_del_dialog_sel\" style=\"width:180px;\">"
                + optionStr
                + "</select>"
                + "</td>"
                + "</tr>";


            $('#labelDelContentArea').append(labelContentStr);

            $('#labelDelPop').dialog({
                autoOpen: true,  // hide dialog
                bgiframe: true,   // for IE6
                resizable: false,
                height:160,
                width: 220,
                modal: true,
                overlay: {
                    backgroundColor: '#000000',
                    opacity: 0.5
                },
                buttons: {
                    削除: function() {
                        doLabel(1);
                    },
                    閉じる: function() {

                        $(this).dialog('close');

                    }
                }
            });

        } else {
            messagePop('ラベルが登録されていません。', 400, 150);
        }

    }).fail(function(data){
        alert('error');
    });

}


function doLabel(kbn) {

    var widthStr = 400;
    var heightStr = 300;

    if (kbn == 0) {

        if ($('#label_dialog_sel') != null) {
            $('input:hidden[name=sml010addLabel]').val($('#label_dialog_sel').val());
        }

        if ($('#label_dialog_new') != null) {
            $('input:hidden[name=sml010addLabelName]').val($('#label_dialog_new').val());
        }

        if ($('input:radio[name=sml010addLabelT]') != null
                && $('input:radio[name=sml010addLabelT]:checked').val() != null) {
            $('input:hidden[name=sml010addLabelType]').val($('input:radio[name=sml010addLabelT]:checked').val());
        } else {
            $('input:hidden[name=sml010addLabelType]').val(1);
        }

    } else {

        if ($('#label_del_dialog_sel') != null) {
            $('input:hidden[name=sml010delLabel]').val($('#label_del_dialog_sel').val());
        }

    }

    //ラベルタブ時
    if ($('input:hidden[name=sml010ProcMode]').val() == 5) {
        $('input[name=sml010DelSid]:checked').each(function(){
            $('#sml010LabelTabSelArea').append(
                    "<input type=\"hidden\" name=\"sml010LabelDelSid\" value=\""
                    + $('#mailkbn_' + $(this).parent().next().next().attr('id')).val()
                    + ":"
                    + $(this).parent().next().next().attr('id')
                    + "\" />");
        });
    }

    var checked = false;
    $('input:checkbox[name=sml010DelSid]:checked').each(function(){
        checked = true;
    });
    if (!checked) {
        messagePop(errMsg_noselect_reload, 400, 150);
        return;
    }


    var cmd = "";

    if (kbn == 0) {
        cmd = "addMessageLabel";
    } else {
        cmd = "delMessageLabel";
    }

    document.forms[0].CMD.value = cmd;
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        $('#labelAddPop').dialog('close');
        $('#labelDelPop').dialog('close');
        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (i = 0; i < data.errorsList.length; i++) {
                if (i != 0) {
                    errorMsgStr += "<br>";
                }
                errorMsgStr += data.errorsList[i];
            }
            messagePop(errorMsgStr, 400, 150);
        } else {
            reloadData();
        }

    }).fail(function(data){
        alert('error');
    });

    $('#sml010LabelTabSelArea').children().remove();
}


function addLabelSearchListMail() {

    document.forms[0].CMD.value='msg_addLabel';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsgStr += data.errorsList[e];
            }
            messagePop(errorMsgStr, 400, 150);
        } else {
            labelAddSearchPop();
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });

}

function deleteLabelSearchListMail() {

    document.forms[0].CMD.value='msg_deleteLabel';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    load_kakunin_message_flg = true;
    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsgStr += data.errorsList[e];
            }
            messagePop(errorMsgStr, 400, 150);
        } else {
            labelDeleteSearchPop();
        }

    }).fail(function(data){
        alert('error');
    }).always(function(data){
        load_kakunin_message_flg = false;
    });
}


function labelAddSearchPop() {

    document.forms[0].CMD.value='getLabelData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {


        $('#labelAddContentArea').children().remove();
        var labelContentStr = "";

        if (data != null && data.labelCombo != null && data.labelCombo.length > 0) {

            var optionStr = "";
            for (i = 0; i < data.labelCombo.length; i++) {
                var labelData = data.labelCombo[i];
                optionStr += "<option value=\""
                    +  labelData.value
                    +  "\">"
                    +  labelData.label
                    +  "</option>";
            }

            labelContentStr = "<tr>"
                + "<td>"
                + "<input type=\"radio\" name=\"sml010addLabelT\" value=\"0\" id=\"addLabelType0\" checked=\"\"><label for=\"addLabelType0\">ラベルを選択</label>"
                + "</td>"
                + "<td>"
                + "<select id=\"label_dialog_sel\">"
                + optionStr
                + "</select>"
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "<input type=\"radio\" name=\"sml010addLabelT\" id=\"addLabelType1\" value=\"1\"><label for=\"addLabelType1\">新規ラベル</label>"
                + "</td>"
                + "<td>"
                + "<input type=\"text\" id=\"label_dialog_new\" maxlength=\"100\" disabled=\"\">"
                + "<span id=\"addLabelParam\"></span>"
                + "</td>"
                + "</tr>";

        } else {

            labelContentStr = "<tr>"
                + "<td>"
                + "新規ラベル<input type=\"text\" id=\"label_dialog_new\" maxlength=\"100\" />"
                + "<span id=\"addLabelParam\"></span>"
                + "<input type=\"hidden\" name=\"sml010addLabelType\" value=\"1\">"
                + "</td>"
                + "</tr>";

        }

        $('#labelAddContentArea').append(labelContentStr);

        $('#labelAddPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height:160,
            width: 400,
            modal: true,
            overlay: {
                backgroundColor: '#000000',
                opacity: 0.5
            },
            buttons: {
                追加: function() {
                    doSearchLabel(0);
                },
                閉じる: function() {

                    $(this).dialog('close');

                }
            }
        });

    }).fail(function(data){
        alert('error');
    });

}


function labelDeleteSearchPop() {

    document.forms[0].CMD.value='getLabelData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));
    var checked = false;

    $.ajax({
        async: true,
        url:  "../smail/sml010.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {


        $('#labelDelContentArea').children().remove();
        var labelContentStr = "";

        if (data != null && data.labelCombo != null && data.labelCombo.length > 0) {

            var optionStr = "";
            for (i = 0; i < data.labelCombo.length; i++) {
                var labelData = data.labelCombo[i];
                optionStr += "<option value=\""
                    +  labelData.value
                    +  "\">"
                    +  labelData.label
                    +  "</option>";
            }

            labelContentStr = "<tr>"
                + "<td>"
                + "<select id=\"label_del_dialog_sel\">"
                + optionStr
                + "</select>"
                + "</td>"
                + "</tr>";


            $('#labelDelContentArea').append(labelContentStr);

            $('#labelDelPop').dialog({
                autoOpen: true,  // hide dialog
                bgiframe: true,   // for IE6
                resizable: false,
                height:160,
                width: 220,
                modal: true,
                overlay: {
                    backgroundColor: '#000000',
                    opacity: 0.5
                },
                buttons: {
                    削除: function() {
                        doSearchLabel(1);
                    },
                    閉じる: function() {

                        $(this).dialog('close');

                    }
                }
            });

        } else {
            messagePop('ラベルが登録されていません。', 400, 150);
        }

    }).fail(function(data){
        alert('error');
    });

}


function doSearchLabel(kbn) {

    var widthStr = 400;
    var heightStr = 300;

    if (kbn == 0) {

        if ($('#label_dialog_sel') != null) {
            $('input:hidden[name=sml010addLabel]').val($('#label_dialog_sel').val());
        }

        if ($('#label_dialog_new') != null) {
            $('input:hidden[name=sml010addLabelName]').val($('#label_dialog_new').val());
        }

        if ($('input:radio[name=sml010addLabelT]') != null
                && $('input:radio[name=sml010addLabelT]:checked').val() != null) {
            $('input:hidden[name=sml010addLabelType]').val($('input:radio[name=sml010addLabelT]:checked').val());
        } else {
            $('input:hidden[name=sml010addLabelType]').val(1);
        }

    } else {

        if ($('#label_del_dialog_sel') != null) {
            $('input:hidden[name=sml010delLabel]').val($('#label_del_dialog_sel').val());
        }

    }
    var checked = false;
    $('input:checkbox[name=sml090DelSid]:checked').each(function(){
        checked = true;
    });
    if (!checked) {
        messagePop(errMsg_noselect_reload, 400, 150);
        return;
    }

    var cmd = "";

    if (kbn == 0) {
        cmd = "addMessageLabel";
    } else {
        cmd = "delMessageLabel";
    }

    document.forms[0].CMD.value = cmd;
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml090.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        $('#labelAddPop').dialog('close');
        $('#labelDelPop').dialog('close');
        if (data.errorsList != null && data.errorsList.length > 0) {
            var errorMsgStr = "";
            for (i = 0; i < data.errorsList.length; i++) {
                if (i != 0) {
                    errorMsgStr += "<br>";
                }
                errorMsgStr += data.errorsList[i];
            }
            messagePop(errorMsgStr, 400, 150);
        } else {
            reloadData();
        }

    }).fail(function(data){
        alert('error');
    });
}


function changeAddLabelType() {
    if ($('input:radio[name=sml010addLabelT]:checked').val() == 1) {
        $('#label_dialog_sel').attr("disabled", "disabled");
        $('#label_dialog_new').removeAttr("disabled");
    } else {
        $('#label_dialog_sel').removeAttr("disabled");
        $('#label_dialog_new').attr("disabled", "disabled");
    }
}

function replayMail(mailkbn) {

    var cmdStr = 'hensinData';

    if (mailkbn == 1) {
        cmdStr = 'hensinData';
    } else if (mailkbn == 2) {
        cmdStr = 'zenhensinData';
    } else if (mailkbn == 3) {
        cmdStr = 'tensoData';
    } else if (mailkbn == 4) {
        cmdStr = 'getSokoData';
    } else if (mailkbn == 11) {
        cmdStr = 'copyData';
    } else if (mailkbn == 12) {
        cmdStr = 'getCalledWebmail';
        //WEBメールからの場合は強制的にテキスト形式で表示する
        $('input:hidden[name=sml010AccountSendMailType]').val(0);
    }

    var paramStr = "";
    document.forms[0].CMD.value=cmdStr;

    if (mail_create_flg) {
        delKakuninPopup(paramStr, mailkbn);
    } else {
        document.forms[0].sml010EditSid.value=document.forms[0].sml010SelectedSid.value;
        paramStr += getFormData($('#smlForm'));

        $.ajax({
            async: true,
            url:  "../smail/sml020.do",
            type: "post",
            data: paramStr
        }).done(function( data ) {
            tmp_del_flg = false;
//          $('.head_menu_add_btn').click();
            openSendTab();
            setCreateMail(mailkbn, data);

        }).fail(function(data){
            alert('error');
        });
    }

}

function delKakuninPopup(paramStr, mailkbn) {
    $('#delKakuninPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height:160,
        width: 400,
        modal: true,
        overlay: {
          backgroundColor: '#000000',
          opacity: 0.5
        },
        buttons: {
          はい: function() {

            if (paramStr != 'newMail') {
                if (mailkbn === 1 || mailkbn === 2 || mailkbn === 3 || mailkbn === 11) {
                    document.forms[0].sml010SelectedSid.value=detail_mail_sid;
                }
                document.forms[0].sml010EditSid.value=document.forms[0].sml010SelectedSid.value;
                paramStr += getFormData($('#smlForm'));
            } else {
                paramStr = 'CMD=getNewmail&smlViewAccount='
                    +   $("#account_comb_box").val();
            }

            deleteNewCreateMail($('.sml_mail_del_btn_mini'));
            $(this).dialog('close');

                $.ajax({
                  async: true,
                  url:  "../smail/sml020.do",
                  type: "post",
                  data: paramStr
              }).done(function( data ) {
                  tmp_del_flg = false;
//                  $('.head_menu_add_btn').click();
                  if (mailkbn === 1 || mailkbn === 2 || mailkbn === 3 || mailkbn === 4) {
                      //メール作成時の状態区分(0:新規 1:返信 2:全返信 3:転送 4:草稿)
                      $('input:hidden[name=sml020ProcMode]').val(mailkbn);
                  } else {
                      $('input:hidden[name=sml020ProcMode]').val(0);
                  }

                  openSendTab();
                  setCreateMail(mailkbn, data);
              }).fail(function(data){
                  alert('error');
              });
          },
          いいえ: function() {
//            $('.head_menu_add_btn').click();
//            openSendTab();
            $(this).dialog('close');
          }
        }
    });
}

function setCreateMail(mailkbn, mailData) {
    if (mailData != null) {

        if (mailData.sml020Atesaki != null && mailData.sml020Atesaki.atesakiList.length > 0) {
            for (u = 0; u < mailData.sml020Atesaki.atesakiList.length; u++) {

                var atesakiMdl = mailData.sml020Atesaki.atesakiList[u];
                var selFlg = false;
                $('input:hidden[name=sml020userSid]').each(function(){
                    if (atesakiMdl.usrSid > 0) {
                        if ($(this).val() == atesakiMdl.usrSid) {
                            selFlg = true;
                        }
                    } else {
                        if ($(this).val() == "sac"+atesakiMdl.accountSid) {
                            selFlg = true;
                        }
                    }
                })

                if (!selFlg) {

                    if (atesakiMdl.usrSid > 0) {
                        $('#atesaki_to_area').append("<div class=\"atesaki_to_user\" id=\"0\">" + htmlEscape(atesakiMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiMdl.usiMei)
                                + "<input type=\"hidden\" name=\"sml020userSid\" value=\""
                                + atesakiMdl.usrSid
                                + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");
                    } else {
                        $('#atesaki_to_area').append("<div class=\"atesaki_to_user\" id=\"0\">" + htmlEscape(atesakiMdl.accountName)
                                + "<input type=\"hidden\" name=\"sml020userSid\" value=\""
                                + "sac"
                                + atesakiMdl.accountSid
                                + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");
                    }
                }
            }

            if (mailData.sml020Atesaki.atesakiList.length > 3) {
                $('#alldsp_to_area').show();
                $('#all_dsp_to_link').html('全て表示');
                $('#atesaki_area').addClass('atesaki_scroll_on');
                $('#atesaki_area').addClass('atesaki_scroll_area_height');
            }
        }

        if ( mailData.sml020AtesakiCc != null && mailData.sml020AtesakiCc.atesakiList.length > 0) {
            for (u = 0; u < mailData.sml020AtesakiCc.atesakiList.length; u++) {

                var atesakiMdl = mailData.sml020AtesakiCc.atesakiList[u];
                var selFlg = false;
                $('input:hidden[name=sml020userSidCc]').each(function(){
                    if (atesakiMdl.usrSid > 0) {
                        if ($(this).val() == atesakiMdl.usrSid) {
                            selFlg = true;
                        }
                    } else {
                        if ($(this).val() == "sac"+atesakiMdl.accountSid) {
                            selFlg = true;
                        }
                    }
                })

                if (!selFlg) {

                    if (atesakiMdl.usrSid > 0) {
                        $('#atesaki_cc_area').append("<div class=\"atesaki_cc_user\" id=\"0\">" + htmlEscape(atesakiMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiMdl.usiMei)
                                + "<input type=\"hidden\" name=\"sml020userSidCc\" value=\""
                                + atesakiMdl.usrSid
                                + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");
                    } else {
                        $('#atesaki_cc_area').append("<div class=\"atesaki_cc_user\" id=\"0\">" + htmlEscape(atesakiMdl.accountName)
                                + "<input type=\"hidden\" name=\"sml020userSidCc\" value=\""
                                + "sac"
                                + atesakiMdl.accountSid
                                + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");
                    }
                }

            }

            if (mailData.sml020AtesakiCc.atesakiList.length > 3) {
                $('#alldsp_cc_area').show();
                $('#all_dsp_cc_link').html('全て表示');
                $('#cc_area').addClass('atesaki_scroll_on');
                $('#cc_area').addClass('atesaki_scroll_area_height');
            }
        }

        if (mailData.sml020AtesakiBcc != null && mailData.sml020AtesakiBcc.atesakiList.length > 0) {
            for (u = 0; u < mailData.sml020AtesakiBcc.atesakiList.length; u++) {

                var atesakiMdl = mailData.sml020AtesakiBcc.atesakiList[u];
                var selFlg = false;
                $('input:hidden[name=sml020userSidBcc]').each(function(){
                    if (atesakiMdl.usrSid > 0) {
                        if ($(this).val() == atesakiMdl.usrSid) {
                            selFlg = true;
                        }
                    } else {
                        if ($(this).val() == "sac"+atesakiMdl.accountSid) {
                            selFlg = true;
                        }
                    }
                })

                if (!selFlg) {

                    if (atesakiMdl.usrSid > 0) {
                        $('#atesaki_bcc_area').append("<div class=\"atesaki_bcc_user\" id=\"0\">" + htmlEscape(atesakiMdl.usiSei) + "&nbsp;&nbsp;" + htmlEscape(atesakiMdl.usiMei)
                                + "<input type=\"hidden\" name=\"sml020userSidBcc\" value=\""
                                + atesakiMdl.usrSid
                                + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");
                    } else {
                        $('#atesaki_bcc_area').append("<div class=\"atesaki_bcc_user\" id=\"0\">" + htmlEscape(atesakiMdl.accountName)
                                + "<input type=\"hidden\" name=\"sml020userSidBcc\" value=\""
                                + "sac"
                                + atesakiMdl.accountSid
                                + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");
                    }
                }

            }

            if (mailData.sml020AtesakiBcc.atesakiList.length > 3) {
                $('#alldsp_bcc_area').show();
                $('#all_dsp_bcc_link').html('全て表示');
                $('#bcc_area').addClass('atesaki_scroll_on');
                $('#bcc_area').addClass('atesaki_scroll_area_height');
            }
        }

        $('input[name=sml020Title]').val(mailData.sml020Title);

        if (mailkbn == 3 || mailkbn == 4 || mailkbn == 11 || mailkbn == 12) {
            $('input[name="sml020Mark"]').val([mailData.sml020Mark]);

            if (mailData.sml020FileLabelList != null && mailData.sml020FileLabelList.length) {
                for (f = 0; f < mailData.sml020FileLabelList.length; f++) {

                    var fileMdl = mailData.sml020FileLabelList[f];

                    $('select[name=sml020selectFiles]').append("<option value=\""
                            + fileMdl.value
                            + "\">"
                            + fileMdl.label
                            + "</option>");
                }
            }
        }

        //tinyMceInit();


        $('textarea[name=sml020Body]').val(mailData.sml020Body);
        $('#inputlength').html(mailData.sml020Body.length);

        if (mailkbn == 4 || mailkbn == 11) {
            //草稿  複写
            if (mailData.sml020MailType != 0) {

                if ($('input:hidden[name=sml010AccountSendMailType]').val() != 0) {
                    changeSendMailType(1);
                } else {
                    changeSendMailType(0);
                }
                $('input:hidden[name=sml020BodyHtml]').val(mailData.sml020Body);
                setTextHtmlAreaStr();

            } else {

                if ($('input:hidden[name=sml010AccountSendMailType]').val() != 0) {
                    changeSendMailType(0);
                } else {
                    changeSendMailType(1);
                }
                $('textarea[name=sml020Body]').val(mailData.sml020Body);
                $('#inputlength').html(mailData.sml020Body.length);

            }

        } else {

            if ($('input:hidden[name=sml010AccountSendMailType]').val() != 0) {
                changeSendMailType(1);
            }

        }
        if (mailData.sml020AtesakiDeletedMessage) {
            var dialogWidth = 630;
            var dialogHeight = 340;
            var errorMsg = "<div style=\"vertical-align:middle;overflow:auto;width:500px;height:200px;font-weight:bold;font-size:14px;\">" + mailData.sml020AtesakiDeletedMessage + "</div>";
            messagePop(errorMsg, dialogWidth, dialogHeight);
        }

    }
}

function revivedMail() {

    document.forms[0].CMD.value='revivedData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml030.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        if (data.messageList != null && data.messageList.length > 0) {
            revivedMailPop(data);
        }

    }).fail(function(data){
        alert('error');
    });

}

function dorevivedMail() {

    document.forms[0].CMD.value='revivedOkData';
    var paramStr = "";
    paramStr += getFormData($('#smlForm'));

    $.ajax({
        async: true,
        url:  "../smail/sml030.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.messageList != null && data.messageList.length > 0) {
            delMessagePop(data.messageList[0], 450, 160, 0);
        }
    }).fail(function(data){
        alert('error');
    });
}

function expMailPop(data, kbn) {

    var widthStr = 650;
    var heightStr = 350;

    $('#delMailMsgArea').html("");

    for (t = 0; t < data.messageList.length; t++) {
        var titleMsg = data.messageList[t];
        $('#delMailMsgArea').append(titleMsg);
    }

    $('#delMailMsgPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: heightStr,
        width: widthStr,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            はい: function() {
                $(this).dialog('close');

                if (kbn == 1) {
                    doExportByPdfMail();
                } else if (kbn == 2) {
                    doExportByEmlMail();
                }

            },
            いいえ: function() {
                $(this).dialog('close');
            }
        }
    });
}

function expMailSearchPop(data, kbn) {

    var widthStr = 650;
    var heightStr = 350;

    $('#delMailMsgArea').html("");

    for (t = 0; t < data.messageList.length; t++) {
        var titleMsg = data.messageList[t];
        $('#delMailMsgArea').append(titleMsg);
    }

    $('#delMailMsgPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: heightStr,
        width: widthStr,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            はい: function() {
                $(this).dialog('close');

                if (kbn == 1) {
                    doExportByPdfSearchMail();
                } else if (kbn == 2) {
                    doExportByEmlSearchMail();
                }

            },
            いいえ: function() {
                $(this).dialog('close');
            }
        }
    });
}

function readKbnMailPop(data, kbn) {

    var widthStr = 650;
    var heightStr = 350;

    $('#delMailMsgArea').html("");

    for (t = 0; t < data.messageList.length; t++) {
        var titleMsg = data.messageList[t];
        $('#delMailMsgArea').append(titleMsg);
    }

    $('#delMailMsgPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: heightStr,
        width: widthStr,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            はい: function() {
                $(this).dialog('close');
                var checked = false;
                $('input:checkbox[name=sml010DelSid]:checked').each(function(){
                    checked = true;
                });
                if (!checked) {
                    messagePop(errMsg_noselect_reload, 400, 150);
                    return;
                }

                if (kbn == 1) {
                    doKidokuMail();
                } else if (kbn == 2) {
                    doMidokuMail();
                }

            },
            いいえ: function() {
                $(this).dialog('close');
            }
        }
    });
}

function readKbnSearchMailPop(data, kbn) {

    var widthStr = 650;
    var heightStr = 350;

    $('#delMailMsgArea').html("");

    for (t = 0; t < data.messageList.length; t++) {
        var titleMsg = data.messageList[t];
        $('#delMailMsgArea').append(titleMsg);
    }

    $('#delMailMsgPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: heightStr,
        width: widthStr,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            はい: function() {
                $(this).dialog('close');
                var checked = false;
                $('input:checkbox[name=sml090DelSid]:checked').each(function(){
                    checked = true;
                });
                if (!checked) {
                    messagePop(errMsg_noselect_reload, 400, 150);
                    return;
                }

                if (kbn == 1) {
                    doKidokuSearchMail();
                } else if (kbn == 2) {
                    doMidokuSearchMail();
                }

            },
            いいえ: function() {
                $(this).dialog('close');
            }
        }
    });
}


function deleteMailPop(data, delkbn) {

    var widthStr = 650;
    var heightStr = 350;

//  if (delkbn == 1) {
//  widthStr = 500;
//  heightStr = 300;
//  } else if (delkbn == 2 || delkbn == 3) {
//  widthStr = 600;
//  heightStr = 300;
//  $('input[name=sml010DelSid]:checked').each(function(){
//  heightStr += 15;
//  });
//  } else if (delkbn == 4) {
//  widthStr = 250;
//  heightStr = 180;
//  } else if (delkbn == 5) {
//  widthStr = 600;
//  heightStr = 300;
//  $('input[name=sml090DelSid]:checked').each(function(){
//  heightStr += 15;
//  });
//  }

    $('#delMailMsgArea').html("");

    for (t = 0; t < data.messageList.length; t++) {
        var titleMsg = data.messageList[t];
        $('#delMailMsgArea').append(titleMsg);
    }

    $('#delMailMsgPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: heightStr,
        width: widthStr,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            はい: function() {

                $(this).dialog('close');

                if (delkbn == 0) {
                    doDeleteMail();
                } else if (delkbn == 1) {
                    doDeleteMailAll();
                } else if (delkbn == 2) {
                    doDeleteListMail();
                } else if (delkbn == 3) {
                    doRevivedListMail();
                } else if (delkbn == 4) {
                    doEmptyTrash();
                } else if (delkbn == 5) {
                    doDeleteSearchListMail();
                }

            },
            いいえ: function() {
                $(this).dialog('close');
            }
        }
    });

}


function deleteSokoMailPop(data) {

    var widthStr = 400;
    var heightStr = 300;

    $('#delMailMsgArea').html("");

    for (t = 0; t < data.messageList.length; t++) {
        var titleMsg = data.messageList[t];
        $('#delMailMsgArea').append(titleMsg);
    }

    $('#delMailMsgPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: heightStr,
        width: widthStr,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            はい: function() {
                doDeleteSokoMail();
                $(this).dialog('close');
            },
            いいえ: function() {
                $(this).dialog('close');
            }
        }
    });

}


function revivedMailPop(data) {

    var widthStr = 400;
    var heightStr = 300;

    $('#revivedMailMsgArea').html("");

    for (t = 0; t < data.messageList.length; t++) {
        var titleMsg = data.messageList[t];
        $('#revivedMailMsgArea').append(titleMsg);
    }

    $('#revivedMailPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: heightStr,
        width: widthStr,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            はい: function() {
                dorevivedMail();
                $(this).dialog('close');
            },
            いいえ: function() {
                $(this).dialog('close');
            }
        }
    });

}


function sendMailPop(msg, digWidth, digHeight) {

    $('#sendMailPopMsg').html(msg);

    $('#sendMailPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: digHeight,
        width: digWidth,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            OK: function() {
                delNewCreateMail($('.sml_mail_del_btn_mini'), 1, -1);
                reloadData();
                $(this).dialog('close');
            }
        }
    });
}

function messagePop(msg, width, height) {

    $('#messageArea').html("");
    $('#messageArea').append(msg);

    $('#messagePop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: height,
        width: width,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            OK: function() {
                $(this).dialog('close');
                $('#errorMsgArea').html("");
            }
        }
    });
}

function delMessagePop(msg, width, height, kbn) {

    $('#messageArea').html("");
    $('#messageArea').append(msg);

    $('#messagePop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: height,
        width: width,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            OK: function() {
                $(this).dialog('close');
                $('#errorMsgArea').html("");
                if (kbn == 1) {
                    reloadData();
                } else if (kbn == 2) {
                    delNewCreateMail($('.sml_mail_del_btn_mini'), 1, -1);
                    reloadData();
                } else {
                    $('.sml_mail_close_btn_mini').click();
                }

            }
        }
    });
}

function popHinaSelDsp() {

    $('#hinagata_pop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: 400,
        width: 650,
        modal: true,
        title: "ひな形選択",
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

}

function loadingPop(popTxt) {

    $('#loading_pop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: 95,
        width: 250,
        modal: true,
        title: popTxt,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            hideBtn: function() {
            }
        }
    });

    $('.ui-button-text').each(function() {
        if ($(this).text() == 'hideBtn') {
            $(this).parent().parent().parent().addClass('border_top_none');
            $(this).parent().parent().parent().addClass('border_bottom_none');
            $(this).parent().after("<div style=\"border-top:0px;line-height:10px\" class=\"\">&nbsp;&nbsp;&nbsp;&nbsp;</div>");
            $(this).parent().remove();
        }
    })

}

function closeloadingPop() {
    if ($('#loading_pop') != null) {
        setTimeout('closeloading();',150)
    }
}

function closeloading() {
    if ($('#loading_pop') != null) {
        $('#loading_pop').dialog('close');
    }
}


//function doExportPdf() {

//$("#smlForm").attr("action", "../smail/sml030.do");
//$("#smlForm").submit();
//$("#smlForm").attr("action", "../smail/sml010.do");

//}

function contextRead(kbn, elm) {

    try {
        contextReadPop(kbn, "", elm.id);

//      document.forms[0].CMD.value='getDetail';
//      document.forms[0].sml010SelectedSid.value=elm.id;
//      var paramStr = "";
//      paramStr += getFormData($('#smlForm'));
//
//      $.ajax({
//      async: true,
//      url:  "../smail/sml030.do",
//      type: "post",
//      data: paramStr
//      }).done(function( data ) {
//
//      if (data != null) {
//      if (data.sml030SmlList.length > 0) {
//
//      var mailTitle = "";
//      var mailSid = 0;
//
//      for (s = 0; s < data.sml030SmlList.length; s++) {
//      var mailData = data.sml030SmlList[s];
//      mailSid = mailData.smlSid;
//      mailTitle = mailData.smsTitle;
//      }
//      contextReadPop(kbn, mailTitle, mailSid);
//      }
//      }
//
//      }).fail(function(data){
//      alert('error');
//      });
//
    } catch (ae) {
        alert(ae);
    }

}

function contextReadPop(kbn, title, mSid) {

//  $('#allReadMsgArea').html("");
//
//  if (kbn == 0) {
//  $('#allReadMsgArea').append('以下の受信メールを既読にしてもよろしいですか？<br><br>');
//  } else {
//  $('#allReadMsgArea').append('以下の受信メールを未読にしてもよろしいですか？<br><br>');
//  }
//
//  $('#allReadMsgArea').append("・" + title);
//
//
//  $('#contextAllReadPop').dialog({
//  autoOpen: true,  // hide dialog
//  bgiframe: true,   // for IE6
//  resizable: false,
//  height: 200,
//  width: 450,
//  modal: true,
//  overlay: {
//  backgroundColor: '#000000',
//  opacity: 0.5
//  },
//  buttons: {
//  はい: function() {
//  $(this).dialog('close');
    doContextRead(kbn, mSid);
//  },
//  いいえ: function() {
//  $(this).dialog('close');
//  }
//  }
//  });

}

function contextAllRead(kbn) {

    $('#allReadMsgArea').html("");

    if (kbn == 0) {
        $('#allReadMsgArea').append('すべての受信メールを既読にしてもよろしいですか？');
    } else {
        $('#allReadMsgArea').append('すべての受信メールを未読にしてもよろしいですか？');
    }


    $('#contextAllReadPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: 200,
        width: 450,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            はい: function() {
                $(this).dialog('close');
                doContextAllRead(kbn);
            },
            いいえ: function() {
                $(this).dialog('close');
            }
        }
    });

}

function doContextRead(kbn, smlSid) {

    var cmdStr = "read";
    var msgPopStr = "既読にしました。";

    if (kbn != 0) {
        cmdStr = "noRead";
        msgPopStr = "未読にしました。";
    }

    try {

        loadingPop("");

        document.forms[0].CMD.value=cmdStr;
        document.forms[0].sml010SelectedSid.value=smlSid;
        var paramStr = "";
        paramStr += getFormData($('#smlForm'));

        $.ajax({
            async: true,
            url:  "../smail/sml010.do",
            type: "post",
            data: paramStr
        }).done(function( data ) {

            closeloading();
            reloadData();
            //readPop(msgPopStr, 200, 150);

        }).fail(function(data){
            alert('error');
        });

    } catch (ae) {
        alert(ae);
    } finally {
        closeloading();
    }

}


function doContextAllRead(kbn) {

    var cmdStr = "allRead";
    var msgPopStr = "すべての受信メールを既読にしました。";

    if (kbn != 0) {
        cmdStr = "allNoRead";
        msgPopStr = "すべての受信メールを未読にしました。";
    }

    try {

        loadingPop("");

        document.forms[0].CMD.value=cmdStr;
        var paramStr = "";
        paramStr += getFormData($('#smlForm'));

        $.ajax({
            async: true,
            url:  "../smail/sml010.do",
            type: "post",
            data: paramStr
        }).done(function( data ) {

            closeloading();
            readPop(msgPopStr, 400, 150);

        }).fail(function(data){
            alert('error');
        });

    } catch (ae) {
        alert(ae);
    } finally {
        closeloading();
    }

}


function readPop(msg, width, height) {

    $('#messageArea').html("");
    $('#messageArea').append(msg);

    $('#messagePop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: height,
        width: width,
        modal: true,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            OK: function() {
                $(this).dialog('close');
                $('#errorMsgArea').html("");
                reloadData();
            }
        }
    });
}

function resetParam() {
    $('input:hidden[name=sml010ProcMode]').val(0);
    $('input:hidden[name=sml010Sort_key]').val(3);
    $('input:hidden[name=sml010Order_key]').val(1);
    $('input:hidden[name=sml010PageNum]').val(1);
    $('input:hidden[name=sml010SelectedSid]').val(0);
    $('input:hidden[name=sml010SelectedSid]').val(0);
    $('input:hidden[name=sml010SelectedMailKbn]').val("");
    $('input:hidden[name=sml010scriptSelUsrSid]').val("");
    $('input:hidden[name=sml010scriptSelUsrName]').val("");
    $('input:hidden[name=sml010usrSid]').val(0);
    $('input:hidden[name=sml010scriptFlg]').val(0);
    $('input:hidden[name=sml010scriptKbn]').val(0);
    $('input:hidden[name=sml050HinaKbn]').val(0);
    $('input:hidden[name=tempDspFlg]').val(0);
}

function htmlEscape(s){
    s=s.replace(/&/g,'&amp;');
    s=s.replace(/>/g,'&gt;');
    s=s.replace(/</g,'&lt;');
    return s;
}

function textBr(txtVal){
    txtVal = txtVal.replace(/\r?\n/g, "<br />");
    return txtVal;
}

function replaceAll(expression, org, dest){
    return expression.split(org).join(dest);
}

function checkSearchKeyword() {

    document.forms[0].CMD.value='smlCkeckKeyword';

    //フォームデータ成形
    var formData = "";
    formData += getFormData($('#smlForm'));

    //データ取得
    $.ajax({
        async: true,
        url:"../smail/sml090.do",
        type: "post",
        data:formData
    }).success(function( data ) {

        if (data.errorsList != null && data.errorsList.length > 0) {
            var dialogWidth = 350;
            var dialogHeight = 140;
            var errorMsg = "";
            for (e = 0; e < data.errorsList.length; e++) {
                errorMsg += "<div class=\"errorMsgStr\">" + data.errorsList[e] + "</div>";
                if (e != 0) {
                    dialogHeight += 25;
                }
                if (data.errorsList[e].length > 30) {
                    dialogWidth = 630;
                    dialogHeight += 40;
                }
            }

            messagePop(errorMsg, dialogWidth, dialogHeight);

        } else {
            document.forms[0].CMD.value='smlSearchData';
            getSearchData();
        }
    }).fail(function(data){
        alert('error');
    });
}