$(function() {

    /* 管理者が設定する選択 */
    $("#ntp085NoticeKbn_00").live("click", function(){
        $("#smlNoticeKbnArea").show();
        //通知する場合は表示
        if ($("#ntp085SmlNoticeKbn_01").is(':checked')) {
            $("#smlNoticeKbnPlace").show();
        }
    });

    /* 各ユーザが設定する選択 */
    $("#ntp085NoticeKbn_01").live("click", function(){
        $("#smlNoticeKbnArea").hide();
        $("#smlNoticeKbnPlace").hide();
    });

    /* 通知しない選択 */
    $("#ntp085SmlNoticeKbn_00").live("click", function(){
        $("#smlNoticeKbnPlace").hide();
    });

    /* 通知する選択 */
    $("#ntp085SmlNoticeKbn_01").live("click", function(){
        $("#smlNoticeKbnPlace").show();
    });

    /* 管理者が設定する選択 */
    $("#ntp085CmtNoticeKbn_00").live("click", function(){
        $("#cmtSmlNoticeKbnArea").show();
    });

    /* 各ユーザが設定する選択 */
    $("#ntp085CmtNoticeKbn_01").live("click", function(){
        $("#cmtSmlNoticeKbnArea").hide();
    });

    /* 管理者が設定する選択 */
    $("#ntp085GoodNoticeKbn_00").live("click", function(){
        $("#goodSmlNoticeKbnArea").show();
    });

    /* 各ユーザが設定する選択 */
    $("#ntp085GoodNoticeKbn_01").live("click", function(){
        $("#goodSmlNoticeKbnArea").hide();
    });

    //初期表示  日報通知
    if ($("#ntp085NoticeKbn_00").is(':checked')) {
        //管理者が設定する
        $("#smlNoticeKbnArea").show();

        if ($("#ntp085SmlNoticeKbn_01").is(':checked')) {
            //通知する
            $("#smlNoticeKbnPlace").show();
        } else {
          //通知しない
            $("#smlNoticeKbnPlace").hide();
        }
    } else {
        //各ユーザが設定する
        $("#smlNoticeKbnArea").hide();
        $("#smlNoticeKbnPlace").hide();

    }

    //初期表示  コメント通知
    if ($("#ntp085CmtNoticeKbn_00").is(':checked')) {
        //管理者が設定する
        $("#cmtSmlNoticeKbnArea").show();
    } else {
        //各ユーザが設定する
        $("#cmtSmlNoticeKbnArea").hide();
    }

    //初期表示  コメント通知
    if ($("#ntp085GoodNoticeKbn_00").is(':checked')) {
        //管理者が設定する
        $("#goodSmlNoticeKbnArea").show();
    } else {
        //各ユーザが設定する
        $("#goodSmlNoticeKbnArea").hide();
    }

});