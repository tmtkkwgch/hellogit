$(function() {
    /* パラメータ設定 設定しないを選択 */
    $("#man340paramKbn0").live("click", function(){
        $("#paramSetArea").hide();
    });

    /* パラメータ設定 設定するを選択 */
    $("#man340paramKbn1").live("click", function(){
        $("#paramSetArea").show();
    });

    //初期表示  パラメータ設定
    if ($("#man340paramKbn0").is(':checked')) {
        //設定しない
        $("#paramSetArea").hide();
    } else {
        //設定する
        $("#paramSetArea").show();
    }
});