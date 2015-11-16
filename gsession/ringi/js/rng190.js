$(function() {
    /* 管理者が設定する選択 */
    $("#smlNtf_01").live("click", function(){
        $("#smlNoticeKbnArea").show();
    });

    /* 各ユーザが設定する選択 */
    $("#smlNtf_02").live("click", function(){
        $("#smlNoticeKbnArea").hide();
    });

    //初期表示  日報通知
    if ($("#smlNtf_01").is(':checked')) {
        //管理者が設定する
        $("#smlNoticeKbnArea").show();
    } else {
        //各ユーザが設定する
        $("#smlNoticeKbnArea").hide();
    }
});