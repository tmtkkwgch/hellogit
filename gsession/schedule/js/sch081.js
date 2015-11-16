$(function() {

    /* タイトルカラー追加しないクリック */
    $('#sch081colorKbn0').click(
        function () {
          $('.add_title_color').addClass('display_none');
        }
    );

    /* タイトルカラー追加するクリック */
    $('#sch081colorKbn1').click(
        function () {
            $('.add_title_color').removeClass('display_none');
        }
    );

    //初期処理
    if ($('input:radio[name=sch081colorKbn]:checked').val() == 1) {
        $('#sch081colorKbn1').click();
    }

});