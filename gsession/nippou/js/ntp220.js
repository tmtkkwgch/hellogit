function changeMode(cmd){
    document.forms[0].ntp220GroupSid.value= "";
    document.forms[0].ntp220SelectUsrSid.value= "";
    $("input[name=ntp220State]").val("-1");
    $("input[name=ntp220AnkenState]").val("-1");
    $("input[name=ntp220NowSelParentId]").val("main");
    $("input[name=ntp220NowSelChildId]").val("");
    document.forms[0].ntp220mode.value= cmd;
    document.forms[0].submit();
    return false;
}

$(function() {

    /* datepicker */
    var dates = jQuery( '#jquery-ui-datepicker-from, #jquery-ui-datepicker-to' ) . datepicker( {
        showAnim: 'blind',
        changeMonth: true,
        numberOfMonths: 2,
        showCurrentAtPos: 1,
        showButtonPanel: true,
        onSelect: function( selectedDate ) {
            var option = this . id == 'jquery-ui-datepicker-from' ? 'minDate' : 'maxDate',
                instance = jQuery( this ) . data( 'datepicker' ),
                date = jQuery . datepicker . parseDate(
                    instance . settings . dateFormat ||
                    jQuery . datepicker . _defaults . dateFormat,
                    selectedDate, instance . settings );
            dates . not( this ) . datepicker( 'option', option, date );

            /* 日付変更  */
            changeGraphDate($("#jquery-ui-datepicker-from").val(), $("#jquery-ui-datepicker-to").val());
        }
    });

    /* 集計グラフ選択 */
    $(".def_graph_title").live("click", function(){

        $(".state_label_title_def_graph").addClass("state_label_title_def_graph_not_sel");
        $(this).removeClass("state_label_title_def_graph_not_sel");
        $(this).removeClass("state_label_title_def_graph_not_sel_hover");


        if ($(this).attr('id') == 0) {
            $("#ankenGraph").removeClass("display_none");
            $(".def_graph_state_label").removeClass("display_none");
            $("#shodanStateGraph").addClass("display_none");
            $("#def_graph_val").val(0);
            $("input[name=def_graph_title]").val(0);
        } else {
            $("#ankenGraph").addClass("display_none");
            $(".def_graph_state_label").addClass("display_none");
            $("#shodanStateGraph").removeClass("display_none");
            $("#def_graph_val").val(1);
            $("input[name=def_graph_title]").val(1);
        }

        setWaitAnkenGraphArea();
        drawAnkenGraph();

    });


    /* メニュー選択 */
    $(".sel_menu_title").live("click", function(){

        resetParam();
        $(".sel_menu_area").removeClass('sel_menu_area_sel');
        $(".sel_menu_title").removeClass('sel_menu_title_sel');

        $('.anything_area').addClass('display_none');
        $('.anything_child_area').addClass('display_none');
        $('.anything_kadou_area').addClass('display_none');
        $('.anything_kadou_child_area').addClass('display_none');

        if ($(this).next().css('display') == "none") {

            $(this).addClass('sel_menu_title_sel');
            $(this).parent().addClass('sel_menu_area_sel');
            $(".sel_menu_content").addClass('display_none');
            $(".sel_menu_content_gyoushu").addClass('display_none');

            //集計画面処理
            resetStateLabel();
            $("input[name=ntp220NowSelParentId]").val($(this).attr('id'));
            $("input[name=ntp220NowSelChildId]").val("");
            anythingDispSet($(this).attr('id'));

            //メニュー項目取得
            $("#" + $(this).attr('id') + "_offset").val(1);

            setWaitAnythingArea($(this).attr('id'));

            showMenuList($(this), $(this).attr('id'));
            setTimeout('redrawAllGraph2("' + $(this).attr('id') + '")',500);

            $('.anken_list_other').addClass('display_none');
            $('.anken_list_' + $(this).attr('id')).removeClass('display_none');
            $("input[name=ankenListOther]").val(getListNumber($(this).attr('id')));
            $("#ankenListOther").val(getListNumber($(this).attr('id')));

            $(this).next().removeClass('display_none');

        } else {

            $(this).next().addClass('display_none');
            $(".sel_menu_content").addClass('display_none');

            //集計画面処理
            $('.anything_area').addClass('display_none');
            $('.anything_kadou_area').addClass('display_none');
            $('.anything_child_area').addClass('display_none');
            setWaitDefGraphArea();
            $('.def_graph').removeClass('display_none');
            resetStateLabel();
            $("input[name=ntp220NowSelParentId]").val("main");
            $("input[name=ntp220NowSelChildId]").val("");
            $("#" + $(this).attr('id') + "_already_sel").val("");
            setTimeout('redrawAllGraph()',500);

        }
    });

    /* メニュー 項目 もっと表示する*/
    $('.sel_menu_content_text_more').live("click", function(){
        showMenuList($(this), $(this).attr('id'));
    });

    /* メニュー 子要素 選択 */
    $(".sel_menu_content_text").live("click", function(){

          resetParam();
          resetStateLabel();

          if ($(this).attr('id') == $("input[name=ntp220NowSelChildId]").val()) {
              $("input[name=ntp220NowSelChildId]").val("");
              $(".child_sel_content_head_title").html("");
              $('.anything_child_area').addClass('display_none');
              $('.anything_kadou_child_area').addClass('display_none');
              anythingChildDispSet($("input[name=ntp220NowSelParentId]").val());
              $(".sel_menu_content_text").removeClass("sel_menu_content_text_sel");
              anythingAllGraph();
              redrawAnkenDataArea();
          } else {
              anythingChildDispSet2($("input[name=ntp220NowSelParentId]").val());
              $('.anything_area').addClass('display_none');
              $('.anything_kadou_area').addClass('display_none');
              $('.def_graph').addClass('display_none');
              $(".sel_menu_content_text").removeClass("sel_menu_content_text_sel");
              $("input[name=ntp220NowSelChildId]").val($(this).attr('id'));
              $(".child_sel_content_head_title").html($(this).children().val().replace("<br>", "&nbsp;"));
              $(this).addClass("sel_menu_content_text_sel");
              showKadouDetailArea();
              anythingChildAllGraph();
          }
    });

    /* 個人 メニュー サブコンテンツ選択 */
    $(".sel_menu_content_sub_text").live("click", function(){
        resetParam();
        if ($(this).next().css('display') == "none") {

            $(this).next().removeClass('display_none');
            $(this).addClass("sel_menu_content_sub_text_sel");
            $('.anything_area').addClass('display_none');
            $('.anything_kadou_area').addClass('display_none');
            anythingAllGraph();
            $('.def_graph').addClass('display_none');

        } else {

            $(this).next().addClass('display_none');
            $(this).removeClass("sel_menu_content_sub_text_sel");
            $('.anything_area').addClass('display_none');
            $('.anything_kadou_area').addClass('display_none');
            $('.anything_child_area').addClass('display_none');
            $('.def_graph').removeClass('display_none');
            redrawAllGraph();

        }
    });


    /* 集計グラフ  hover*/
    $('.state_label_title_def_graph_not_sel').live({
        mouseenter:function (e) {
            $(this).addClass("state_label_title_def_graph_not_sel_hover");
        },
        mouseleave:function (e) {
            $(this).removeClass("state_label_title_def_graph_not_sel_hover");
        }
    });

    /* メニュー  hover*/
    $('.sel_menu_title').hover(
        function () {
            $(this).addClass("sel_menu_title_on");
        },
        function () {
            $(this).removeClass("sel_menu_title_on");
        }
    );

    /* メニュー 項目 hover*/
    $('.sel_menu_content_text').live({
        mouseenter:function (e) {
            $(this).addClass("sel_menu_content_text_on");
            $("#tooltip_area").append("<div id=\"ttp\">"+ ($(this).children("span.tooltips").html()) +"</div>");
            $("#ttp")
             .css("position","absolute")
             .css("top",(e.pageY) + 15 + "px")
             .css("left",(e.pageX) + 15 + "px")
             .removeClass('display_none')
             .css("filter","alpha(opacity=85)")
             .fadeIn("fast");
             $(this).addClass("sel_menu_content_text_on");
        },
        mouseleave:function (e) {
            $(this).removeClass("sel_menu_content_text_on");
            $("#ttp").remove();
            $(this).removeClass("sel_menu_content_text_on");
        }
    });


    /* メニュー 項目 もっと表示する*/
    jQuery('.sel_menu_content_text_more').live({
        mouseenter:function(){
          $(this).addClass("sel_menu_content_text_more_on");
        },
        mouseleave:function(){
          $(this).removeClass("sel_menu_content_text_more_on");
        }
    });

    /* メニュー 項目 もっと表示する*/
    jQuery('.sel_menu_content_text_more2').live({
        mouseenter:function(){
          $(this).addClass("sel_menu_content_text_more_on");
        },
        mouseleave:function(){
          $(this).removeClass("sel_menu_content_text_more_on");
        }
    });

    /* Anything1もっと表示する*/
    jQuery('.sel_anything1_more').live({
        mouseenter:function(){
          $(this).addClass("sel_anything1_more_on");
        },
        mouseleave:function(){
          $(this).removeClass("sel_anything1_more_on");
        }
    });

    /* AnythingKadou1もっと表示する*/
    jQuery('.sel_anythingKadou1_more').live({
        mouseenter:function(){
          $(this).addClass("sel_anythingKadou1_more_on");
        },
        mouseleave:function(){
          $(this).removeClass("sel_anythingKadou1_more_on");
        }
    });

    /* AnythingKadou2もっと表示する*/
    jQuery('.sel_anythingKadou2_more').live({
        mouseenter:function(){
          $(this).addClass("sel_anythingKadou2_more_on");
        },
        mouseleave:function(){
          $(this).removeClass("sel_anythingKadou2_more_on");
        }
    });

    /* AnythingKadou3もっと表示する*/
    jQuery('.sel_anythingKadou3_more').live({
        mouseenter:function(){
          $(this).addClass("sel_anythingKadou3_more_on");
        },
        mouseleave:function(){
          $(this).removeClass("sel_anythingKadou3_more_on");
        }
    });

    /* AnythingKadou4もっと表示する*/
    jQuery('.sel_anythingKadou4_more').live({
        mouseenter:function(){
          $(this).addClass("sel_anythingKadou4_more_on");
        },
        mouseleave:function(){
          $(this).removeClass("sel_anythingKadou4_more_on");
        }
    });

    /* AnythingKadouChild0もっと表示する*/
    jQuery('.sel_anythingKadouChild0_more').live({
        mouseenter:function(){
          $(this).addClass("sel_anythingKadouChild0_more_on");
        },
        mouseleave:function(){
          $(this).removeClass("sel_anythingKadouChild0_more_on");
        }
    });

    /* メニュー 項目 検索*/
    $(".content_search_btn").live("click", function(){

        $('#searchBtnResultArea').children().remove();
        $('#searchBtnResultArea').append("<div style=\"padding-top:220px;height:100%;width:100%;text-align:center;\"><img src=\"../nippou/images/ajax-loader.gif\" /></div>");

        var contentSid = $(this).attr('id');
        var pageNum   = $("input:hidden[name='ntp220pageNum']").val();
        var searchWord = $(this).prev().val();

        /* 検索結果ポップアップ */
        $('#searchBtnResultPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 550,
            width: 400,
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

        //検索結果成形
        getSearchBtnResultList(contentSid, pageNum, searchWord);
    });


    //項目検索結果画面 次ページクリック
    $(".nextPageBtn").live("click", function(){
        var paramStr = $(this).attr('id').split(":");
        getSearchBtnResultList(paramStr[0], parseInt(paramStr[1]) + 1, $("." + paramStr[0] + "_search").val());
    });

    //項目検索結果画面前ページクリック
    $(".prevPageBtn").live("click", function(){
        var paramStr = $(this).attr('id').split(":");
        getSearchBtnResultList(paramStr[0], parseInt(paramStr[1]) - 1, $("." + paramStr[0] + "_search").val());
    });

    //項目検索結果画面  コンボ変更
    $(".selchange").live("change", function(){
        getSearchBtnResultList($(this).attr('id'), $(this).val(), $("." + $(this).attr('id') + "_search").val());
    });


    /* メニュー 格納用縦線 hover*/
    $('.menu_length_border').hover(
            function () {
                $(this).addClass("menu_length_border_on");
              },
              function () {
                  $(this).removeClass("menu_length_border_on");
              }
    );

    /* 金額項目 hover*/
    jQuery('.state_label_notsel_text').live({
        mouseenter:function(){
          $(this).addClass("state_label_notsel_text_on");
        },
        mouseleave:function(){
          $(this).removeClass("state_label_notsel_text_on");
        }
    });


    /* 状態  click*/
    $('.state_label_sel_text').live("click", function(){

        setWaitDefGraphArea();
        setWaitAnythingGraphArea();
        setWaitAnythingChildGraphArea();

        resetParam();
        $('.state_label_sel_text').removeClass("state_label_notsel_text_on");
        $("select[name='ankenListState']").val($(this).attr('id'));
        $("input[name=ankenListState]").val($(this).attr('id'));

        $(".state_label_sel_text").addClass("state_label_notsel_text");
        if ($(this).attr('id') == "-1") {
            $(".sel_label_sel_all").removeClass("state_label_notsel_text");
        } else if ($(this).attr('id') == "0") {
            $(".sel_label_sel_shodan").removeClass("state_label_notsel_text");
        }  else if ($(this).attr('id') == "1") {
            $(".sel_label_sel_jutyu").removeClass("state_label_notsel_text");
        }  else if ($(this).attr('id') == "2") {
            $(".sel_label_sel_sityu").removeClass("state_label_notsel_text");
        }

        $("input[name=ntp220AnkenState]").val($(this).attr('id'));
        setTimeout('redrawAllGraph()',500);

    });

    /* メニュー 格納用縦線 click*/
    $(".menu_length_border").live("click", function(){

        if ($("#sel_menu_wrapper").css('display') == "none") {

            $('#sel_menu_wrapper').removeClass('display_none');
            $('#menu_length_area').removeClass("menu_length_border_none");

        } else {

            $('#sel_menu_wrapper').addClass('display_none');
            $('#menu_length_area').addClass("menu_length_border_none");

        }

        resetParam();

        setWaitDefGraphArea();
        setWaitAnythingGraphArea();
        setWaitAnythingChildGraphArea();
        setTimeout('redrawAllGraph2("' + $("input[name=ntp220NowSelParentId]").val() + '")',500);
    });

    /* 状態  hover*/
    jQuery('.anken_state_notcheck').live({
        mouseenter:function(){
          $(this).addClass("anken_state_notcheck_on");
        },
        mouseleave:function(){
          $(this).removeClass("anken_state_notcheck_on");
        }
    });


    /* 状態  click*/
    $('.anken_state_check').live("click", function(){
        //resetParam();
        setWaitDefGraphArea();
        setWaitAnythingGraphArea();
        setWaitAnythingChildGraphArea();

        $(".anken_state_check").removeClass("anken_state_notcheck_on");
        $(".anken_state_check").addClass("anken_state_notcheck");
        $(this).removeClass("anken_state_notcheck");
        $("input[name=ntp220State]").val($(this).attr('id'));
        redrawAllGraph();
    });

    /* 商談結果グラフ変更クリック*/
    $("#resultGraphBtn").live("click", function(){
        if ($('#resultGraphVal').val() == 0) {
            drawResultGraph();
            $('#resultGraphVal').val('1');
        } else {
            drawResultHorizonGraph();
            $('#resultGraphVal').val('0');
        }
    });

    /* 金額グラフ変更クリック*/
    $("#shohinGraphBtn").live("click", function(){
        if ($('#shohinGraphVal').val() == 0) {
            drawShohinGraph();
            $('#shohinGraphVal').val('1');
        } else {
            drawShohinHorizonGraph();
            $('#shohinGraphVal').val('0');
        }
    });

    /* 活動分類グラフ変更クリック*/
    $("#kbunruiGraphBtn").live("click", function(){
        if ($('#kbunruiGraphVal').val() == 0) {
            drawKbunruiHorizonGraph();
            $('#kbunruiGraphVal').val('1');
        } else {
            drawKbunruiGraph();
            $('#kbunruiGraphVal').val('0');
        }
    });

    /* 活動方法グラフ変更クリック*/
    $("#khouhouGraphBtn").live("click", function(){
        if ($('#khouhouGraphVal').val() == 0) {
            drawKhouhouHorizonGraph();
            $('#khouhouGraphVal').val('1');
        } else {
            drawKhouhouGraph();
            $('#khouhouGraphVal').val('0');
        }
    });

    /* 可変エリアグラフ変更クリック*/
    $("#anythingGraph1Btn").live("click", function(){
        if ($('#anythingGraph1Val').val() == 1) {
            drawAnything1HorizonGraph();
            $('#anythingGraph1Val').val('0');
        } else {
            drawAnything1Graph();
            $('#anythingGraph1Val').val('1');
        }
    });

    /* 可変エリア金額グラフ変更クリック*/
    $("#anythingGraph2Btn").live("click", function(){
        if ($('#anythingGraph2Val').val() == 0) {
            drawAnything2HorizonGraph();
            $('#anythingGraph2Val').val('1');
        } else {
            drawAnything2Graph();
            $('#anythingGraph2Val').val('0');
        }
    });

    /* 可変エリア子要素  結果グラフ変更クリック*/
    $("#anythingChild0GraphBtn").live("click", function(){
        if ($('#anythingChild0GraphVal').val() == 0) {
            drawAnythingChild0Graph();
            $('#anythingChild0GraphVal').val('1');
        } else {
            drawAnythingChild0HorizonGraph();
            $('#anythingChild0GraphVal').val('0');
        }
    });

    /* 可変エリア子要素  結果グラフ変更クリック*/
    $("#anythingChild1GraphBtn").live("click", function(){
        if ($('#anythingChild1GraphVal').val() == 0) {
            drawAnythingChild1Graph();
            $('#anythingChild1GraphVal').val('1');
        } else {
            drawAnythingChild1HorizonGraph();
            $('#anythingChild1GraphVal').val('0');
        }
    });

    /* 可変エリア子要素 金額グラフ変更クリック*/
    $("#anythingChild2GraphBtn").live("click", function(){
        if ($('#anythingChild2GraphVal').val() == 0) {
            drawAnythingChild2Graph();
            $('#anythingChild2GraphVal').val('1');
        } else {
            drawAnythingChild2HorizonGraph();
            $('#anythingChild2GraphVal').val('0');
        }
    });

    /* 可変エリア 案件割合変更クリック*/
    $("#anythingKadou1GraphBtn").live("click", function(){
        if ($('#anythingKadou1GraphVal').val() == 0) {
            drawAnythingKadou1Graph();
            $('#anythingKadou1GraphVal').val('1');
        } else {
            drawAnythingKadou1HorizonGraph();
            $('#anythingKadou1GraphVal').val('0');
        }
    });

    /* 可変エリア 企業割合変更クリック*/
    $("#anythingKadou2GraphBtn").live("click", function(){
        if ($('#anythingKadou2GraphVal').val() == 0) {
            drawAnythingKadou2Graph();
            $('#anythingKadou2GraphVal').val('1');
        } else {
            drawAnythingKadou2HorizonGraph();
            $('#anythingKadou2GraphVal').val('0');
        }
    });

    /* 可変エリア 活動分類割合変更クリック*/
    $("#anythingKadou3GraphBtn").live("click", function(){
        if ($('#anythingKadou3GraphVal').val() == 0) {
            drawAnythingKadou3Graph();
            $('#anythingKadou3GraphVal').val('1');
        } else {
            drawAnythingKadou3HorizonGraph();
            $('#anythingKadou3GraphVal').val('0');
        }
    });

    /* 可変エリア 活動方法割合変更クリック*/
    $("#anythingKadou4GraphBtn").live("click", function(){
        if ($('#anythingKadou4GraphVal').val() == 0) {
            drawAnythingKadou4Graph();
            $('#anythingKadou4GraphVal').val('1');
        } else {
            drawAnythingKadou4HorizonGraph();
            $('#anythingKadou4GraphVal').val('0');
        }
    });


    /* 可変エリア子要素(稼働時間)  グラフ変更クリック*/
    $("#anythingKadouChild0GraphBtn").live("click", function(){
        if ($('#anythingKadouChild0GraphVal').val() == 0) {
            drawAnythingKadouChild0Graph();
            $('#anythingKadouChild0GraphVal').val('1');
        } else {
            drawAnythingKadouChild0HorizonGraph();
            $('#anythingKadouChild0GraphVal').val('0');
        }
    });

    /* 案件情報もっと表示 */
    $("#ankenDataMore").live("click", function(){
        drawAnkenDataArea();
    });

    /* 案件詳細情報(稼働時間)もっと表示 */
    $("#ankenKadouDataMore").live("click", function(){
        drawKadouDetailDataArea();
    });

    /* 企業詳細情報(稼働時間)もっと表示 */
    $("#kigyouKadouDataMore").live("click", function(){
        drawKadouDetailDataArea();
    });

    /* 活動分類詳細情報(稼働時間)もっと表示 */
    $("#kbunruiKadouDataMore").live("click", function(){
        drawKadouDetailDataArea();
    });

    /* 活動方法詳細(稼働時間)もっと表示 */
    $("#khouhouKadouDataMore").live("click", function(){
        drawKadouDetailDataArea();
    });

    /* 案件情報状態変更 */
    $("#ankenListState").change(function () {
        resetPage();
        $("input[name=ankenListState]").val($("#ankenListState option:selected").val());
        $("input[name=ntp220AnkenState]").val($("#ankenListState option:selected").val());
        $(".state_label_sel_text").addClass("state_label_notsel_text");
        if ($("#ankenListState option:selected").val() == "-1") {
            $(".sel_label_sel_all").removeClass("state_label_notsel_text");
        } else if ($("#ankenListState option:selected").val() == "0") {
            $(".sel_label_sel_shodan").removeClass("state_label_notsel_text");
        }  else if ($("#ankenListState option:selected").val() == "1") {
            $(".sel_label_sel_jutyu").removeClass("state_label_notsel_text");
        }  else if ($("#ankenListState option:selected").val() == "2") {
            $(".sel_label_sel_sityu").removeClass("state_label_notsel_text");
        }
        redrawAllGraph();
        $('html,body').animate({ scrollTop:$("#tooltip_area").offset().top }, 'slow');
    });


    /* 案件情報状態変更 */
    $("#ankenListMoney").change(function () {
        $("input[name=ankenListMoney]").val($("#ankenListMoney option:selected").val());
        if ($("#ankenListMoney option:selected").val() == 0) {
            $(".anken_list_jutyu").addClass("display_none");
            $(".anken_list_mitumori").removeClass("display_none");
        } else {
            $(".anken_list_mitumori").addClass("display_none");
            $(".anken_list_jutyu").removeClass("display_none");
        }
    });


    /* 案件情報状態変更 */
    $("#ankenKadouListMoney").change(function () {
        $("input[name=ankenKadouListMoney]").val($("#ankenKadouListMoney option:selected").val());
        if ($("#ankenKadouListMoney option:selected").val() == 0) {
            $(".anken_kadou_list_jutyu").addClass("display_none");
            $(".anken_kadou_list_mitumori").removeClass("display_none");
        } else {
            $(".anken_kadou_list_mitumori").addClass("display_none");
            $(".anken_kadou_list_jutyu").removeClass("display_none");
        }
    });


    /* 案件情報その他表示項目変更 */
    $("#ankenListOther").change(function () {
        $("input[name=ankenListOther]").val($("#ankenListOther option:selected").val());
        if ($("#ankenListOther option:selected").val() == 0) {
            $(".anken_list_other").addClass("display_none");
            $(".anken_list_kigyou").removeClass("display_none");
        } else if ($("#ankenListOther option:selected").val() == 1) {
            $(".anken_list_other").addClass("display_none");
            $(".anken_list_shohin").removeClass("display_none");
        } else if ($("#ankenListOther option:selected").val() == 2) {
            $(".anken_list_other").addClass("display_none");
            $(".anken_list_gyoushu").removeClass("display_none");
        } else if ($("#ankenListOther option:selected").val() == 3) {
            $(".anken_list_other").addClass("display_none");
            $(".anken_list_process").removeClass("display_none");
        } else if ($("#ankenListOther option:selected").val() == 4) {
            $(".anken_list_other").addClass("display_none");
            $(".anken_list_mikomido").removeClass("display_none");
        } else if ($("#ankenListOther option:selected").val() == 5) {
            $(".anken_list_other").addClass("display_none");
            $(".anken_list_kokyakugensen").removeClass("display_none");
        } else if ($("#ankenListOther option:selected").val() == 6) {
            $(".anken_list_other").addClass("display_none");
            $(".anken_list_tanto").removeClass("display_none");
        }
    });

    /* 案件情報その他表示項目変更 */
    $("#ankenKadouListOther").change(function () {
        $("input[name=ankenKadouListOther]").val($("#ankenKadouListOther option:selected").val());
        if ($("#ankenKadouListOther option:selected").val() == 0) {
            $(".anken_kadou_list_other").addClass("display_none");
            $(".anken_kadou_list_kigyou").removeClass("display_none");
        } else if ($("#ankenKadouListOther option:selected").val() == 1) {
            $(".anken_kadou_list_other").addClass("display_none");
            $(".anken_kadou_list_shohin").removeClass("display_none");
        } else if ($("#ankenKadouListOther option:selected").val() == 2) {
            $(".anken_kadou_list_other").addClass("display_none");
            $(".anken_kadou_list_gyoushu").removeClass("display_none");
        } else if ($("#ankenKadouListOther option:selected").val() == 3) {
            $(".anken_kadou_list_other").addClass("display_none");
            $(".anken_kadou_list_process").removeClass("display_none");
        } else if ($("#ankenKadouListOther option:selected").val() == 4) {
            $(".anken_kadou_list_other").addClass("display_none");
            $(".anken_kadou_list_mikomido").removeClass("display_none");
        } else if ($("#ankenKadouListOther option:selected").val() == 5) {
            $(".anken_kadou_list_other").addClass("display_none");
            $(".anken_kadou_list_kokyakugensen").removeClass("display_none");
        } else if ($("#ankenKadouListOther option:selected").val() == 6) {
            $(".anken_kadou_list_other").addClass("display_none");
            $(".anken_kadou_list_tanto").removeClass("display_none");
        }
    });


    /* Anything1もっと表示 */
    $("#anything1_more").live("click", function(){
        $("input[name=anything1page]").val(Number($("input[name=anything1page]").val()) + 1);
        drawAnything1HorizonGraph();
    });

    /* AnythingKadou1もっと表示 */
    $("#anythingKadou1_more").live("click", function(){
        $("input[name=anythingKadou1page]").val(Number($("input[name=anythingKadou1page]").val()) + 1);
        drawAnythingKadou1HorizonGraph();
    });

    /* AnythingKadou2もっと表示 */
    $("#anythingKadou2_more").live("click", function(){
        $("input[name=anythingKadou2page]").val(Number($("input[name=anythingKadou2page]").val()) + 1);
        drawAnythingKadou2HorizonGraph();
    });

    /* AnythingKadou3もっと表示 */
    $("#anythingKadou3_more").live("click", function(){
        $("input[name=anythingKadou3page]").val(Number($("input[name=anythingKadou3page]").val()) + 1);
        drawAnythingKadou3HorizonGraph();
    });

    /* AnythingKadou4もっと表示 */
    $("#anythingKadou4_more").live("click", function(){
        $("input[name=anythingKadou4page]").val(Number($("input[name=anythingKadou4page]").val()) + 1);
        drawAnythingKadou4HorizonGraph();
    });

    /* AnythingKadouChild0もっと表示 */
    $("#anythingKadouChild0_more").live("click", function(){
        $("input[name=anythingKadouChild0page]").val(Number($("input[name=anythingKadouChild0page]").val()) + 1);
        drawAnythingKadouChild0HorizonGraph();
    });

    /* ツールチップ*/
    $("span.tooltips").each(function() {
        $(this).addClass('display_none');
    });

    //メニューツールチップ
    $(".sel_menu_content_text:has(span.tooltips)").live("mouseover",function(e){
         $("#tooltip_area").append("<div id=\"ttp\">"+ ($(this).children("span.tooltips").html()) +"</div>");
         $("#ttp")
          .css("position","absolute")
          .css("top",(e.pageY) + -10 + "px")
          .css("left",(e.pageX) + 20 + "px")
          .removeClass('display_none')
          .css("filter","alpha(opacity=85)")
          .fadeIn("fast");
          $(this).addClass("sel_menu_content_text_on");
     }).live("mousemove",function(e){
         $("#ttp")
          .css("top",(e.pageY) + -10 + "px")
          .css("left",(e.pageX) + 20 + "px");
         $(this).addClass("sel_menu_content_text_on");
     }).live("mouseout",function(){
         $("#ttp").remove();
         $(this).removeClass("sel_menu_content_text_on");
     });

    //見積もり金額ツールチップ
    $(".mitumori_kin_str").live("mouseenter",function(e){
        $("#tooltip_area").append("<div id=\"ttp\">"+ ($(this).prev().html()) +"</div>");
        $("#ttp")
         .css("position","absolute")
         .css("top",(e.pageY) - 35 + "px")
         .css("left",(e.pageX) - 100 + "px")
         .removeClass('display_none')
         .css("filter","alpha(opacity=85)")
         .fadeIn("fast");
    }).live("mousemove",function(e){
        $("#ttp")
         .css("top",(e.pageY) - 35 + "px")
         .css("left",(e.pageX) - 100 + "px")
    }).live("mouseleave",function(e){
        $("#ttp").remove();
    });

    //受注金額ツールチップ
    $(".jutyu_kin_str").live("mouseenter",function(e){
        $("#tooltip_area").append("<div id=\"ttp\">"+ ($(this).prev().html()) +"</div>");
        $("#ttp")
         .css("position","absolute")
         .css("top",(e.pageY) - 35 + "px")
         .css("left",(e.pageX) - 100 + "px")
         .removeClass('display_none')
         .css("filter","alpha(opacity=85)")
         .fadeIn("fast");
    }).live("mousemove",function(e){
        $("#ttp")
         .css("top",(e.pageY) - 35 + "px")
         .css("left",(e.pageX) - 100 + "px")
    }).live("mouseleave",function(e){
        $("#ttp").remove();
    });

     //案件情報ポップアップ
     $(".anken_name_link").live("click", function(){
         var ankenSid = $(this).attr("id");
         openSubWindow("../nippou/ntp210.do?ntp210NanSid=" + ankenSid);
     });


     //案件情報ポップアップ
     $(".anken_name_link_his").live("click", function(){
         var ankenHisSid = $(this).attr("id");
         openSubWindow("../nippou/ntp210.do?ntp210HisFlg=1&ntp210NahSid=" + ankenHisSid);
     });

     //企業ポップアップ
     $(".comp_click").live("click", function(){
         var compSid = $(this).attr("id");
         openSubWindow("../address/adr250.do?adr250AcoSid=" + compSid);
     });

     /* 他のカテゴリの商品  hover*/
     jQuery('.other_category_itm').live({
         mouseenter:function(){
           $(this).addClass("other_category_itm_hover");
         },
         mouseleave:function(){
           $(this).removeClass("other_category_itm_hover");
         }
     });


     /* 状態  click*/
     $('.other_category_itm').live("click", function(){
         if ($(this).next().css("display") == "none") {
             $(this).next().removeClass("display_none");
             $(this).addClass("other_category_itm_sel");
         } else {
             $(this).next().addClass("display_none");
             $(this).removeClass("other_category_itm_sel");
         }
     });

     /* グラフ描画 */
     drawAllGraph();

});


function anythingDispSet(id) {

    if (id != 'kadou') {
        $('.anything_area').removeClass('display_none');
        $('.anything_child_area').addClass('display_none');
    } else {
        $('#kadouArea').removeClass("display_none");
        $('.anything_kadou_area').removeClass('display_none');
        $('#anken_state_menu_area').addClass('display_none');
    }

    $('.def_graph').addClass('display_none');
}

function anythingChildDispSet(id) {

    if (id != 'kadou') {
        $('.anything_area').removeClass('display_none');
        $('.anything_child_area').addClass('display_none');
    } else {
        $('.anything_kadou_area').removeClass('display_none');
        $('#anken_state_menu_area').addClass('display_none');
        $('.category_sel_area').addClass('display_none');
    }
}

function anythingChildDispSet2(id) {

    if (id != 'kadou') {
        $('.anything_child_area').removeClass('display_none');
        $('.anything_area').addClass('display_none');
    } else {
        $('#kadouArea').removeClass("display_none");
        $('.anything_kadou_child_area').removeClass('display_none');
        $('#anken_state_menu_area').addClass('display_none');
        $('#anythingKadouChild0GraphVal').val('0');
    }
}

function setWaitAnkenGraphArea() {

    $('#ankenGraph').remove();
    $('#shodanStateGraph').remove();

    if ($("input[name=def_graph_val]").val() == "0") {
        $('#ankenGraphArea').append('<div id=\"ankenGraph\" style="text-align:center;padding-top:110px;padding-bottom:110px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
    } else {
        $('#ankenGraphArea').append('<div id=\"shodanStateGraph\" style="text-align:center;padding-top:110px;padding-bottom:110px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
    }
}

function setWaitDefGraphArea() {

    $('#ankenGraph').remove();
    $('#shodanStateGraph').remove();
    $('#ankenGraphArea').append('<div id=\"ankenGraph\" style="text-align:center;padding-top:110px;padding-bottom:110px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');

    $('#resultGraph').remove();
    $('#resultGraphArea').append('<div id=\"resultGraph\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');

    $('#shohinGraph').remove();
    $('#shohinGraphArea').append('<div id=\"shohinGraph\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
}

function setWaitAnythingGraphArea() {


    if ($("input[name=ntp220NowSelChildId]").val() == "kadou") {
        $('#anythingGraph1').remove();
        $('#anything1_more').addClass("display_none");
        $('#anythingGraph1Area').append('<div id=\"anythingGraph1\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
        $('#anythingGraph2').remove();
        $('#anything2_more').addClass("display_none");
        $('#anythingGraph2Area').append('<div id=\"anythingGraph2\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
    } else {
        $('#kadouGraph').remove();
        $('#kadouGraphArea').append('<div id=\"kadouGraph\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
        $('#anythingKadouGraph1').remove();
        $('#anythingKadou1_more').addClass("display_none");
        $('#anythingKadouGraph1Area').append('<div id=\"anythingKadouGraph1\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
        $('#anythingKadouGraph2').remove();
        $('#anythingKadou2_more').addClass("display_none");
        $('#anythingKadouGraph2Area').append('<div id=\"anythingKadouGraph2\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
        $('#anythingKadouGraph3').remove();
        $('#anythingKadou3_more').addClass("display_none");
        $('#anythingKadouGraph3Area').append('<div id=\"anythingKadouGraph3\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
        $('#anythingKadouGraph4').remove();
        $('#anythingKadou4_more').addClass("display_none");
        $('#anythingKadouGraph4Area').append('<div id=\"anythingKadouGraph4\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
        $('#anken_state_menu_area').addClass('display_none');
    }

}


function setWaitAnythingArea(id) {

    if (id != "kadou") {
        $('#anythingGraph1').remove();
        $('#anything1_more').addClass("display_none");
        $('#anythingGraph1Area').append('<div id=\"anythingGraph1\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');

        $('#anythingGraph2').remove();
        $('#anything2_more').addClass("display_none");
        $('#anythingGraph2Area').append('<div id=\"anythingGraph2\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
    } else {

        $('#kadouGraph').remove();
        $('#kadouGraphArea').append('<div id=\"kadouGraph\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');

        $('#anythingKadouGraph1').remove();
        $('#anythingKadou1_more').addClass("display_none");
        $('#anythingKadouGraph1Area').append('<div id=\"anythingKadouGraph1\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');

        $('#anythingKadouGraph2').remove();
        $('#anythingKadou2_more').addClass("display_none");
        $('#anythingKadouGraph2Area').append('<div id=\"anythingKadouGraph2\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');

        $('#anythingKadouGraph3').remove();
        $('#anythingKadou3_more').addClass("display_none");
        $('#anythingKadouGraph3Area').append('<div id=\"anythingKadouGraph3\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');

        $('#anythingKadouGraph4').remove();
        $('#anythingKadou4_more').addClass("display_none");
        $('#anythingKadouGraph4Area').append('<div id=\"anythingKadouGraph4\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
    }

}


function setWaitAnythingChildGraphArea() {

    $('#anythingChild0Graph').remove();
    $('#anythingChild0GraphArea').append('<div id=\"anythingChild0Graph\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');

    $('#anythingChild1Graph').remove();
    $('#anythingChild1GraphArea').append('<div id=\"anythingChild1Graph\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');

    $('#anythingChild2Graph').remove();
    $('#anythingChild2GraphArea').append('<div id=\"anythingChild2Graph\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');

    $('#anythingKadouChild0Graph').remove();
    $('#anythingKadouChild0GraphArea').append('<div id=\"anythingKadouChild0Graph\" style="text-align:center;padding-top:50px;padding-bottom:50px;"><img src=\"../nippou/images/ajax-loader.gif\" /></div>');
}

function getListNumber(listId) {

    var selNum = 0;

    if (listId == "kigyou") {
        selNum = 0;
    } else if (listId == "shohin") {
        selNum = 1;
    } else if (listId == "gyoushu") {
        selNum = 2;
    } else if (listId == "process") {
        selNum = 3;
    } else if (listId == "mikomido") {
        selNum = 4;
    } else if (listId == "kokyakugensen") {
        selNum = 5;
    } else if (listId == "tanto") {
        selNum = 6;
    }
    return selNum;
}

//日付変更
function changeGraphDate(frdate, todate) {
    $("input[name=ntp220DateFrStr]").val(frdate);
    $("input[name=ntp220DateToStr]").val(todate);
    $("input[name=ntp220State]").val("-1");
    $("input[name=ntp220AnkenState]").val("-1");
    $("input[name=ntp220NowSelParentId]").val("main");
    $("input[name=ntp220NowSelChildId]").val("");
    document.forms[0].submit();
    return false;
}

//ラベル初期化
function resetStateLabel() {
    $(".state_label_sel_text").addClass("state_label_notsel_text");
    $(".sel_label_sel_all").removeClass("state_label_notsel_text");
    $("input[name=ntp220AnkenState]").val(-1);
    $("select[name='ankenListState']").val(-1);
}

//パラメータ初期化
function resetParam() {
  $(".category_sel_area").removeClass('display_none');
  $("input[name=anything1page]").val(1);
  $("input[name=anything1NowCount]").val(0);
  $("input[name=anythingKadou1page]").val(1);
  $("input[name=anythingKadou1NowCount]").val(0);
  $("input[name=anythingKadou2page]").val(1);
  $("input[name=anythingKadou2NowCount]").val(0);
  $("input[name=anythingKadou3page]").val(1);
  $("input[name=anythingKadou3NowCount]").val(0);
  $("input[name=anythingKadou4page]").val(1);
  $("input[name=anythingKadou4NowCount]").val(0);
  $("input[name=anythingKadouChild0page]").val(1);
  $("input[name=anythingKadouChild0NowCount]").val(0);
  $("input[name=ankenKadouDataPageNum]").val(1);
  $(".state_label_sel_text").addClass("state_label_notsel_text");
  $(".sel_label_sel_all").removeClass("state_label_notsel_text");
  $("input[name=ntp220AnkenState]").val(-1);
  $("select[name='ankenListState']").val(-1);
  $("input[name=ankenListState]").val(-1);
  $("input[name=ankenDataPageNum]").val(1);
  $('#anken_state_menu_area').removeClass('display_none');
  $('#anken_kadou_detail_area').addClass("display_none");
  $('#kigyou_kadou_detail_area').addClass("display_none");
  $('#kbunrui_kadou_detail_area').addClass("display_none");
  $('#khouhou_kadou_detail_area').addClass("display_none");
}

function resetPage() {
    $("input[name=anything1page]").val(1);
    $("input[name=anything1NowCount]").val(0);
    $("input[name=anythingKadou1page]").val(1);
    $("input[name=anythingKadou1NowCount]").val(0);
    $("input[name=anythingKadou2page]").val(1);
    $("input[name=anythingKadou2NowCount]").val(0);
    $("input[name=anythingKadou3page]").val(1);
    $("input[name=anythingKadou3NowCount]").val(0);
    $("input[name=anythingKadou4page]").val(1);
    $("input[name=anythingKadou4NowCount]").val(0);
    $("input[name=anythingKadouChild0page]").val(1);
    $("input[name=anythingKadouChild0NowCount]").val(0);
    $("input[name=ankenDataPageNum]").val(1);
    $("input[name=ankenKadouDataPageNum]").val(1);
  }

//左メニュー 表示項目取得
function showMenuList (selContent, contentName) {

    var selMenu = "";
    var dataFlg = false;

    var gyoushuSid = -1;
    if ($("input[name=ntp220NowSelParentId]").val() == "process") {
        if ($("select[name=ntp220GyoushuSid]").val() != null) {
            gyoushuSid = $("select[name=ntp220GyoushuSid]").val();
        }
    }

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"showContent",
                                   "CMD":"showContent",
                                   "contentName":contentName,
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "offset":$("#" + contentName + "_offset").val(),
                                   "alreadySelStr":$("#" + contentName + "_already_sel").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(data) {
        if (data != null && data != "") {

            for (i = 0; i < data.length; i++) {
                if (!data[i].alreadyFlg) {
                    var dspContentStr = data[i].contentName1.substring(0, 12);

                    selMenu += "<span class=\"sel_menu_content_text\" id=\""
                            +  contentName
                            +  "_"
                            +  data[i].contentSid1;
                            if (data[i].contentSid2 != null && data[i].contentSid2 != "") {
                                selMenu += "_" + data[i].contentSid2;
                            }
                    selMenu +=  "\">" + dspContentStr;
                    selMenu += "<input type=\"hidden\" value=\"" + data[i].contentName1 + "<br>" + data[i].contentName2 + "\" />";
                    selMenu += "<span class=\"tooltips display_none\"><span class=\"tooltip_txt\">" + data[i].contentName1;
                    if (data[i].contentName2 != null && data[i].contentName2 != "") {
                        selMenu += "<br />" + data[i].contentName2;
                    }
                    selMenu += "</span></span></span>";

                    if (!data[i].alreadyFlg) {
                        selMenu += "<br />";
                    }
                }
            }

            //表示件数が5件あった場合
            if (i == 5 && contentName != "mikomido") {
                selMenu += "<span id=\"" + contentName + "\" class=\"sel_menu_content_text_more\">▼もっと表示する</span>";
            }

            dataFlg = true;

        } else {
            $(".sel_menu_content_text_more").remove();
            if ($("#" + contentName + "_offset").val() == 1) {
                selMenu += "<span class=\"no_data_txt\">該当するデータはありません。</span>";
            }
        }
    });


    var searchBoxStr = "";
    if (contentName == "kigyou") {
        searchBoxStr = "企業コード・名";
    } else if (contentName == "shohin") {
        searchBoxStr = "商品コード・名";
    } else if (contentName == "gyoushu") {
        searchBoxStr = "業種コード・名";
    } else if (contentName == "process") {
        searchBoxStr = "プロセスコード";
    } else if (contentName == "kokyakugensen") {
        searchBoxStr = "源泉コード・名";
    }

    if ($("#" + contentName + "_offset").val() == 1) {
        $("#search_label").remove();
        $("#field_id").remove();
        selMenuContentStr = "<div class=\"sel_menu_content\" id=\""
                          + contentName
                          + "_sel_menu_content\">";
        if (dataFlg) {
            if (contentName == "kigyou") {
                selMenuContentStr += "<div class=\"textfield2 search_div\" id=\""
                              + contentName
                              + "_sel_menu_after_area\">";
                selMenuContentStr += "<label id=\"search_label\" for=\"field_id\">" + searchBoxStr + "</label>"
                selMenuContentStr += "<input Id=\"field_id\" style=\"width:100px;\" type=\"text\" class=\"" + contentName + "_search\" />"
                selMenuContentStr += "<input value=\"検索\" class=\"content_search_btn\" id=\"" + contentName + "\" type=\"button\"/>";
                selMenuContentStr += "</div>";
            }
        }
        selMenuContentStr += selMenu + "</div>";

        if (contentName != "process") {
            selContent.next().remove();
            selContent.after(selMenuContentStr);
        } else {
            selContent.next().next().remove();
            selContent.next().after(selMenuContentStr);
        }

    } else {

        if (contentName != "process") {
            selContent.after(selMenu);
            selContent.remove();
        } else {
            selContent.next().after(selMenu);
            selContent.next().remove();
        }
    }

    $("#" + contentName + "_offset").val(Number($("#" + contentName + "_offset").val()) + 1);

    $("#search_label").inFieldLabels();
}

function changeGyoushuCombo() {
    $("#process_offset").val(1);
    showMenuList ($(".sel_menu_title_process_area"), "process");
    $("input[name=ntp220NowSelChildId]").val("");
    $('.anything_area').removeClass('display_none');
    $('.anything_child_area').addClass('display_none');
    anythingAllGraph();
    $("input[name=ankenDataPageNum]").val(1);
    redrawAnkenDataArea();
}

function drawAllGraph() {

    /* 案件グラフ */
    drawAnkenGraph();
    /* 商談結果グラフ */
    drawResultGraph();
    /* 金額グラフ */
    drawShohinHorizonGraph();
//    /* 割合グラフ */
//    drawCompGraph();



    /* 案件情報表示 */
    drawAnkenDataArea();

}

function redrawAllGraph() {

    $('#anken_detail_area').removeClass('display_none');


    if ($("input[name=ntp220NowSelParentId]").val() == "main") {
        if ($('#resultGraphVal').val() == 0) {
            drawResultHorizonGraph();
        } else {
            drawResultGraph();
        }

        if ($('#shohinGraphVal').val() == 0) {
            drawShohinHorizonGraph();
        } else {
            drawShohinGraph();
        }

        drawAnkenGraph();

    } else if ($("input[name=ntp220NowSelChildId]").val() == ""){
        if ($('#anythingGraph1Val').val() == 1) {
            drawAnything1Graph();
        } else {
            drawAnything1HorizonGraph();
        }

        if ($('#anythingGraph2Val').val() == 0) {
            drawAnything2Graph();
        } else {
            drawAnything2HorizonGraph();
        }
    } else {
        if ($('#anythingChild0GraphVal').val() == 0) {
            drawAnythingChild0Graph();
        } else {
            drawAnythingChild0HorizonGraph();
        }

        if ($('#anythingChild1GraphVal').val() == 1) {
            drawAnythingChild1Graph();
        } else {
            drawAnythingChild1HorizonGraph();
        }

        if ($('#anythingChild2GraphVal').val() == 0) {
            drawAnythingChild2HorizonGraph();
        } else {
            drawAnythingChild2Graph();
        }
    }

    /* 案件情報再表示 */
    redrawAnkenDataArea();
}


function redrawAllGraph2(id) {

    if (id != "kadou") {

        $('#anken_detail_area').removeClass('display_none');

        if ($("input[name=ntp220NowSelParentId]").val() == "main") {
            if ($('#resultGraphVal').val() == 0) {
                drawResultHorizonGraph();
            } else {
                drawResultGraph();
            }

            if ($('#shohinGraphVal').val() == 0) {
                drawShohinHorizonGraph();
            } else {
                drawShohinGraph();
            }

            drawAnkenGraph();

        } else if ($("input[name=ntp220NowSelChildId]").val() == ""){
            if ($('#anythingGraph1Val').val() == 1) {
                drawAnything1Graph();
            } else {
                drawAnything1HorizonGraph();
            }

            if ($('#anythingGraph2Val').val() == 0) {
                drawAnything2Graph();
            } else {
                drawAnything2HorizonGraph();
            }
        } else {
            if ($('#anythingChild0GraphVal').val() == 0) {
                drawAnythingChild0Graph();
            } else {
                drawAnythingChild0HorizonGraph();
            }

            if ($('#anythingChild1GraphVal').val() == 1) {
                drawAnythingChild1Graph();
            } else {
                drawAnythingChild1HorizonGraph();
            }

            if ($('#anythingChild2GraphVal').val() == 0) {
                drawAnythingChild2HorizonGraph();
            } else {
                drawAnythingChild2Graph();
            }
        }

        /* 案件情報再表示 */
        redrawAnkenDataArea();

    } else {

        $(".category_sel_area").addClass('display_none');
        $('#anken_detail_area').addClass('display_none');

        if ($("input[name=ntp220NowSelChildId]").val() == ""){

            drawKadouGraph();

            if ($('#anythingKadou1GraphVal').val() == 0) {
                drawAnythingKadou1HorizonGraph();
                $('#anythingKadou1GraphVal').val('0');
            } else {
                drawAnythingKadou1Graph();
                $('#anythingKadou1GraphVal').val('1');
            }

            if ($('#anythingKadou2GraphVal').val() == 0) {
                drawAnythingKadou2HorizonGraph();
                $('#anythingKadou2GraphVal').val('0');
            } else {
                drawAnythingKadou2Graph();
                $('#anythingKadou2GraphVal').val('1');
            }

            if ($('#anythingKadou3GraphVal').val() == 0) {
                drawAnythingKadou3HorizonGraph();
                $('#anythingKadou3GraphVal').val('0');
            } else {
                drawAnythingKadou3Graph();
                $('#anythingKadou3GraphVal').val('1');
            }

            if ($('#anythingKadou4GraphVal').val() == 0) {
                drawAnythingKadou4HorizonGraph();
                $('#anythingKadou4GraphVal').val('0');
            } else {
                drawAnythingKadou4Graph();
                $('#anythingKadou4GraphVal').val('1');
            }

        } else {

            redrawKadouDetailDataArea();

            if ($('#anythingKadouChild0GraphVal').val() == 1) {
                drawAnythingKadouChild0Graph();
            } else {
                drawAnythingKadouChild0HorizonGraph();
            }

        }

    }
}


function redrawAnkenDataArea() {
    $("input[name=ankenDataPageNum]").val("1");
    $(".anken_val").remove();
    $(".anken_val2").remove();
    drawAnkenDataArea();
}



//項目(親要素)データ
function anythingAllGraph() {

    if ($("input[name=ntp220NowSelParentId]").val() != "kadou") {
        drawAnything1HorizonGraph();
        drawAnything2Graph();
    } else {
        drawKadouGraph();
        drawAnythingKadou1HorizonGraph();
        drawAnythingKadou2HorizonGraph();
        drawAnythingKadou3HorizonGraph();
        drawAnythingKadou4HorizonGraph();
    }

}

function anythingChildAllGraph() {

    if ($("input[name=ntp220NowSelParentId]").val() != "kadou") {
        redrawAnkenDataArea();
        drawAnythingChild1HorizonGraph();
        drawAnythingChild0HorizonGraph();
        drawAnythingChild2HorizonGraph();
    } else {
        redrawKadouDetailDataArea();
        drawAnythingKadouChild0HorizonGraph();
    }
}

function katudouAllGraph() {
    drawKbunruiHorizonGraph();
    drawKhouhouGraph();
}

function drawAnkenGraph() {

    var cmdStr = "getPeriodAnkenData";
    var defGraphMode = $("input[name=def_graph_val]").val();

    $.ajaxSetup({async:true});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "defGraphMode":defGraphMode,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {


        if (cntData != null && cntData != "" && cntData.length > 0) {

            var ankenClass = "";
            var shodanStateClass = "display_none";

            if ($("#def_graph_val").val() == 1) {
                ankenClass = "display_none";
                shodanStateClass = "";
            }

            $('#ankenGraph').remove();
            $('#shodanStateGraph').remove();
            $('#ankenGraphArea').append('<div id=\"ankenGraph\" style=\"height:260px;\"></div>');
            $('#ankenGraphArea').append('<div id=\"shodanStateGraph\" style=\"height:260px;\"></div>');

            var animateFlg = false;

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10){
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var dateStr      = "[";
            var jutyuStr     = "[";
            var mitumoriStr  = "[";
            var jutyuCntStr  = "[";
            var sityuCntStr  = "[";
            var shodanCntStr = "[";

            for (i = 0; i < cntData.length; i++) {
                var totalData = cntData[i];

                if (i != 0) {
                    dateStr      += ",";
                    jutyuStr     += ",";
                    mitumoriStr  += ",";
                    jutyuCntStr  += ",";
                    sityuCntStr  += ",";
                    shodanCntStr += ",";
                }
                dateStr      += "'" + totalData.dateStr + "'"
                jutyuStr     += totalData.sumJutyuPrice;
                mitumoriStr  += totalData.sumMitumoriPrice;
                jutyuCntStr  += totalData.sumJutyuAnken;
                sityuCntStr  += totalData.sumSityuAnken;
                shodanCntStr += totalData.sumShodanAnken;
            }

            var ticksOpsAngle = 0;
            var ticksOpsSize = '7pt';
            if (cntData.length > 13) {
                ticksOpsAngle = -30;
                ticksOpsSize  = '8pt';
            }

            dateStr      += "]";
            jutyuStr     += "]";
            mitumoriStr  += "]";
            jutyuCntStr  += "]";
            sityuCntStr  += "]";
            shodanCntStr += "]";

            dataStr      = eval(dateStr);
            jutyuStr     = eval(jutyuStr);
            mitumoriStr  = eval(mitumoriStr);
            jutyuCntStr  = eval(jutyuCntStr);
            sityuCntStr  = eval(sityuCntStr);
            shodanCntStr = eval(shodanCntStr);


            var mitumoriVal = mitumoriStr;
            var jutyuVal = jutyuStr;
            var tick = dataStr;

            if(defGraphMode == "0") {
                var ankenPlot = $.jqplot('ankenGraph', [jutyuVal, mitumoriVal], {
                    animate: animateFlg,
                    animateReplot: animateFlg,
                    legend: {
                       show: true,
                       location: 'nw',
                       renderer: jQuery . jqplot . EnhancedLegendRenderer,
                       rendererOptions:{ numberColumns: 3}
                    },

                    highlighter: {
                        show: true,
                        showMarker: false,
                        sizeAdjust: 0,
                        tooltipLocation: 'n',
                        tooltipAxes: 'y',
                        formatString: '%s'
                    },

                    series:[
//                      {
//                        label:'商談中',
//                        yaxis:'y2axis',
//                        rendererOptions:{
//                          highlightMouseOver: false,
//                          animation: {
//                              speed: 1500
//                          }
//                        }
//                      },
//                      {
//                        label:'受注',
//                        yaxis:'y2axis',
//                        rendererOptions:{
//                          highlightMouseOver: false,
//                          animation: {
//                              speed: 1500
//                          }
//                        }
//                      },
//                      {
//                          label:'失注',
//                          yaxis:'y2axis',
//                          rendererOptions:{
//                            highlightMouseOver: false,
//                            animation: {
//                                speed: 1500
//                            }
//                          }
//                        },
                      {
                        label:'受注金額(合計)',
                        yaxis:'yaxis',
                        rendererOptions: {
                                animation: {
                                    speed: 1500
                                }
                            }
                        },
                      {
                        label:'見積り金額(合計)',
                        yaxis:'yaxis',
                        rendererOptions: {
                                animation: {
                                    speed: 1500
                                }
                            }
                         }
                    ],
                    axes: {
                      xaxis: {
                        renderer: $.jqplot.CategoryAxisRenderer,
                        ticks: tick,
                        label: '',
                        tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
                        tickOptions: {angle:ticksOpsAngle,fontSize:ticksOpsSize}
                      },
                      yaxis: {
                        label: ''
                      },
                      y2axis: {
                        label: ''
                      }
                    }
                  });
            }

            if(defGraphMode == "1") {
                var ankenPlot = $.jqplot('shodanStateGraph', [shodanCntStr, jutyuCntStr, sityuCntStr], {
                    animate: animateFlg,
                    animateReplot: animateFlg,
                    legend: {
                       show: true,
                       location: 'nw',
                       renderer: jQuery . jqplot . EnhancedLegendRenderer,
                       rendererOptions:{ numberColumns: 3}
                    },

                    highlighter: {
                        show: true,
                        showMarker: false,
                        sizeAdjust: 0,
                        tooltipLocation: 'n',
                        tooltipAxes: 'y',
                        formatString: '%s'
                    },

                    series:[
                      {
                        label:'商談中',
                        yaxis:'yaxis',
                        rendererOptions:{
                          highlightMouseOver: false,
                          animation: {
                              speed: 1500
                          }
                        }
                      },
                      {
                        label:'受注',
                        yaxis:'yaxis',
                        rendererOptions:{
                          highlightMouseOver: false,
                          animation: {
                              speed: 1500
                          }
                        }
                      },
                      {
                          label:'失注',
                          yaxis:'yaxis',
                          rendererOptions:{
                            highlightMouseOver: false,
                            animation: {
                                speed: 1500
                            }
                          }
                        }
                    ],
                    axes: {
                      xaxis: {
                        renderer: $.jqplot.CategoryAxisRenderer,
                        ticks: tick,
                        label: '',
                        tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
                        tickOptions: {angle:ticksOpsAngle,fontSize:ticksOpsSize}
                      },
                      yaxis: {
                        label: ''
                      },
                      y2axis: {
                        label: ''
                      }
                    }
                  });
            }

            $('#ankenGraph').addClass(ankenClass);
            $(".def_graph_state_label").addClass(ankenClass);
            $('#shodanStateGraph').addClass(shodanStateClass);

        } else {
            $('#ankenGraph').remove();
            $('#shodanStateGraph').remove();
            $('#ankenGraphArea').append('<div id=\"ankenGraph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });

}


function drawResultGraph() {

    var cmdStr = "getAnkenStateCnt";

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":-1,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "" && cntData.syodanCnt != null) {

            $('#resultGraph').remove();
            $('#resultGraphArea').append('<div id=\"resultGraph\"></div>');

            var data = "";

            data = [['受注', cntData.jutyuCnt],['失注', cntData.sityuCnt], ['商談中', cntData.syodanCnt]];
            $(".state_label_area").addClass("display_none");

            var resultPlot = jQuery.jqplot ('resultGraph', [data],
              {
                seriesDefaults: {
                  renderer: jQuery.jqplot.PieRenderer,
                  rendererOptions: {
                    showDataLabels: true
                  }
                },
                legend: { show:true, location: 'e' }
              }
            );

            $("#resultGraph").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
              var chart_left = $('#resultGraph').offset().left,
              chart_top = $('#resultGraph').offset().top,
              x = chart_left + 110;
              y = chart_top + 100;
              var color = 'rgb(50%,50%,100%)';
              $('#tooltipResult').css({left:x, top:y});
              $('#tooltipResult').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + '件数: ' + data[1]);
              $('#tooltipResult').show();

            });

            $("#resultGraph").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
                $('#tooltipResult').empty();
                $('#tooltipResult').hide();
            });

            if (cntData.jutyuCnt == 0 && cntData.sityuCnt == 0 && cntData.syodanCnt == 0) {
                $('#resultGraph').remove();
                $('#resultGraphArea').append('<div id=\"resultGraph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
            }

        } else {
            $('#resultGraph').remove();
            $('#resultGraphArea').append('<div id=\"resultGraph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}

function drawResultHorizonGraph() {

    var cmdStr = "getAnkenStateCnt";

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":-1,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "" && cntData.syodanCnt != null) {

            $('#resultGraph').remove();
            $('#resultGraphArea').append('<div id=\"resultGraph\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10){
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            data = [[cntData.syodanCnt, '商談中'], [cntData.sityuCnt, '失注'], [cntData.jutyuCnt, '受注']];

            jQuery . jqplot(
                    'resultGraph',
                    [data],
                    {
                        animate: animateFlg,
                        animateReplot: animateFlg,
                        seriesColors:['#c5b47f', '#eaa228', '#4bb2c5'],
                        seriesDefaults: {
                            renderer: jQuery . jqplot . BarRenderer,
                            rendererOptions: {
                                barDirection: 'horizontal',
                                varyBarColor: true,
                                animation: {
                                    speed: 1500
                                }
                            }
                        },
                        axes: {
                            xaxis:{
                              label: '件数',
                              labelOptions: {fontSize: '8pt'}
                            },
                            yaxis: {
                                pad:0,
                                renderer: jQuery . jqplot . CategoryAxisRenderer
                            }
                        },
                        highlighter: {
                            show: true,
                            showMarker: false,
                            tooltipLocation: 'e',
                            tooltipAxes: 'x',
                            formatString: '　%s　'
                        }
                    }
                );

            if (cntData.jutyuCnt == 0 && cntData.sityuCnt == 0 && cntData.syodanCnt == 0) {
                $('#resultGraph').remove();
                $('#resultGraphArea').append('<div id=\"resultGraph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
            }

        } else {
            $('#resultGraph').remove();
            $('#resultGraphArea').append('<div id=\"resultGraph\" style=\"height:350px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });

}

function drawShohinGraph() {

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getShohinData",
                                   "CMD":"getShohinData",
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(shnData) {

        if (shnData != null && shnData != "" && shnData.length > 0) {

            var heightStr = 200;

            if ((28 * shnData.length) > 200) {
                heightStr = 28 * shnData.length;
            }

            $('#shohinGraph').remove();
            $('#shohinGraphArea').append('<div id=\"shohinGraph\" style=\"height:' + heightStr+ 'px;\"></div>');

            var shnStr    = "[";

            for (i=0; i < shnData.length; i++) {
                 if (i != 0 && i != shnData.length) {
                     shnStr += ",";
                 }
                 shnStr += "['"
                          +  shnData[i].nhnName
                          +  "',"
                          +  shnData[i].nshCnt
                          +  "]";
            }

            shnStr    += "]";

            var shohinObject = eval(shnStr);

            var resultPlot = jQuery.jqplot ('shohinGraph', [shohinObject],
              {
                seriesDefaults: {
                  renderer: jQuery.jqplot.PieRenderer,
                  rendererOptions: {
                    showDataLabels: true
                  }
                },
                legend: { show:true, location: 'e' }
              }
            );

            $("#shohinGraph").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
              var chart_left = $('#shohinGraph').offset().left,
              chart_top = $('#shohinGraph').offset().top,
              x = chart_left + 110;
              y = chart_top + 100;
              var color = 'rgb(50%,50%,100%)';
              $('#tooltipShohin').css({left:x, top:y});
              $('#tooltipShohin').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + '件数: ' + data[1]);
              $('#tooltipShohin').show();

            });

            $("#shohinGraph").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
                $('#tooltipShohin').empty();
                $('#tooltipShohin').hide();
            });

        } else {
            $('#shohinGraph').remove();
            $('#shohinGraphArea').append('<div id=\"shohinGraph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });

}


function drawShohinHorizonGraph() {

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getShohinData",
                                   "CMD":"getShohinData",
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(shnData) {

        if (shnData != null && shnData != "" && shnData.length > 0) {

            var heightStr = 200;

            if ((60 * shnData.length) > 200) {
                heightStr = 40 * shnData.length;
            }

            $('#shohinGraph').remove();
            $('#shohinGraphArea').append('<div id=\"shohinGraph\" style=\"height:' + heightStr+ 'px;\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10){
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var shnStr    = "[";

            for (i=0; i < shnData.length; i++) {
                 if (i != 0 && i != shnData.length) {
                     shnStr += ",";
                 }
                 shnStr += "["
                          +  shnData[i].nshCnt
                          +  ",'"
                          +  strCut(shnData[i].nhnName)
                          +  "']";
            }

            shnStr    += "]";

            var shohinObject = eval(shnStr);

            jQuery . jqplot(
                    'shohinGraph',
                    [
                        shohinObject
                    ],

                    {
                        animate: animateFlg,
                        animateReplot: animateFlg,
                        seriesColors:['#839557', '#579575', '#c5b47f', '#eaa228', '#4bb2c5'],
                        seriesDefaults: {
                            renderer: jQuery . jqplot . BarRenderer,
                            rendererOptions: {
                                barDirection: 'horizontal',
                                varyBarColor: true,
                                animation: {
                                    speed: 1500
                                }
                            }
                        },
                        axes: {
                            yaxis: {
                                pad:0,
                                renderer: jQuery . jqplot . CategoryAxisRenderer
                            }
                        },
                        highlighter: {
                            show: true,
                            showMarker: false,
                            tooltipLocation: 'e',
                            tooltipAxes: 'x',
                            formatString: '　%s　'
                        }
                    }
                );
        } else {
            $('#shohinGraph').remove();
            $('#shohinGraphArea').append('<div id=\"shohinGraph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });

}



function drawKbunruiGraph() {

    $('#kbunruiGraph').remove();
    $('#kbunruiGraphArea').append('<div id=\"kbunruiGraph\" style=\"\"></div>');

    var data = [['問合せ対応', 12],['クレーム対応', 9], ['会議', 14], ['資料作成', 3], ['セミナー', 7], ['引継ぎ', 8]];
    var resultPlot = jQuery.jqplot ('kbunruiGraph', [data],
      {
        seriesDefaults: {
          renderer: jQuery.jqplot.PieRenderer,
          rendererOptions: {
            showDataLabels: true
          }
        },
        legend: { show:true, location: 'e' }
      }
    );

    $("#kbunruiGraph").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
      var chart_left = $('#kbunruiGraph').offset().left,
      chart_top = $('#kbunruiGraph').offset().top,
      x = chart_left + 110;
      y = chart_top + 100;
      var color = 'rgb(50%,50%,100%)';
      $('#tooltipKbunrui').css({left:x, top:y});
      $('#tooltipKbunrui').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + '件数: ' + data[1]);
      $('#tooltipKbunrui').show();

    });

    $("#kbunruiGraph").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
        $('#tooltipKbunrui').empty();
        $('#tooltipKbunrui').hide();
    });

}




function drawKbunruiHorizonGraph() {

    $('#kbunruiGraph').remove();
    $('#kbunruiGraphArea').append('<div id=\"kbunruiGraph\" style=\"\"></div>');

    var animateFlg = false;

    //IE判定  6 7 8 9
    if($.browser.msie && $.browser.version < 10){
        animateFlg = false;
    } else {
        animateFlg = true;
    }

    jQuery . jqplot(
            'kbunruiGraph',
            [
                [ [8, '<font size="-3">引継ぎ</font>'],[7, '<font size="-2">セミナ<br>ー</font>'], [3, '<font size="-2">資料<br>作成</font>'],[14, '<font size="-2">会議</font>'] ,[9, '<font size="-2">クレー<br>ム対応</font>'] , [12, '<font size="-2">問合せ<br>対応</font>'] ]
            ],
            {
                animate: animateFlg,
                animateReplot: animateFlg,
                seriesColors:['#958c12', '#839557', '#579575', '#c5b47f', '#eaa228', '#4bb2c5'],
                seriesDefaults: {
                    renderer: jQuery . jqplot . BarRenderer,
                    rendererOptions: {
                        barDirection: 'horizontal',
                        varyBarColor: true,
                        animation: {
                            speed: 1500
                        }
                    }
                },
                axes: {
                    yaxis: {
                        renderer: jQuery . jqplot . CategoryAxisRenderer
                    }
                },
                highlighter: {
                    show: true,
                    showMarker: false,
                    tooltipLocation: 'e',
                    tooltipAxes: 'x',
                    formatString: '　%s　'
                }
            }
        );
}


function drawKhouhouGraph() {

    $('#khouhouGraph').remove();
    $('#khouhouGraphArea').append('<div id=\"khouhouGraph\" style=\"height:220px;\"></div>');

    var data = [['訪問', 6],['来社', 9], ['電話', 14], ['FAX', 6], ['メール', 7], ['郵送', 2], ['社内作業', 7]];
    var resultPlot = jQuery.jqplot ('khouhouGraph', [data],
      {
        seriesDefaults: {
          renderer: jQuery.jqplot.PieRenderer,
          rendererOptions: {
            showDataLabels: true,
            animation: {
                speed: 1500
            }
          }
        },
        legend: { show:true, location: 'e' }
      }
    );

    $("#khouhouGraph").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
      var chart_left = $('#khouhouGraph').offset().left,
      chart_top = $('#khouhouGraph').offset().top,
      x = chart_left + 110;
      y = chart_top + 100;
      var color = 'rgb(50%,50%,100%)';
      $('#tooltipKhouhou').css({left:x, top:y});
      $('#tooltipKhouhou').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + '件数: ' + data[1]);
      $('#tooltipKhouhou').show();

    });

    $("#khouhouGraph").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
        $('#tooltipKhouhou').empty();
        $('#tooltipKhouhou').hide();
    });
}


function drawKhouhouHorizonGraph() {

    $('#khouhouGraph').remove();
    $('#khouhouGraphArea').append('<div id=\"khouhouGraph\" style=\"\"></div>');

    //IE判定  6 7 8 9
    if($.browser.msie && $.browser.version < 10){
        animateFlg = false;
    } else {
        animateFlg = true;
    }

    jQuery . jqplot(
            'khouhouGraph',
            [
                [[7, '<font size="-2">社内作<br>業</font>'], [2, '<font size="-2">郵送</font>'], [7, '<font size="-2">メール</font>'], [6, '<font size="-2">FAX</font>'], [14, '<font size="-2">電話</font>'], [9, '<font size="-2">来社</font>'], [6, '<font size="-2">訪問</font>']]
            ],

            {
                animate: animateFlg,
                animateReplot: animateFlg,
                seriesColors:['#958c12', '#839557', '#579575', '#c5b47f', '#eaa228', '#4bb2c5', '#953579'],
                seriesDefaults: {
                    renderer: jQuery . jqplot . BarRenderer,
                    rendererOptions: {
                        barDirection: 'horizontal',
                        varyBarColor: true,
                        animation: {
                            speed: 1500
                        }
                    }
                },
                axes: {
                    yaxis: {
                        pad:0,
                        renderer: jQuery . jqplot . CategoryAxisRenderer
                    }
                },
                highlighter: {
                    show: true,
                    showMarker: false,
                    tooltipLocation: 'e',
                    tooltipAxes: 'x',
                    formatString: '　%s　'
                }
            }
        );

}

//
//function drawCompGraph() {
//
//    $('#compGraph').remove();
//    $('#compGraphArea').append('<div id=\"compGraph\" style=\"height:150px;\"></div>');
//
//    var data = [['2000未満', 6],['2000～3000', 9], ['3000～4000', 14], ['4000～5000', 3], ['5000以上', 3]];
//    var compPlot = jQuery.jqplot ('compGraph', [data],
//      {
//        seriesDefaults: {
//          renderer: jQuery.jqplot.PieRenderer,
//          rendererOptions: {
//            showDataLabels: true
//          }
//        },
//        legend: { show:false, location: 'e' }
//      }
//    );
//
//    $("#compGraph").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
//      var chart_left = $('#compGraph').offset().left,
//      chart_top = $('#compGraph').offset().top,
//      x = chart_left + 20;
//      y = chart_top + 50;
//      var color = 'rgb(50%,50%,100%)';
//      $('#tooltipComp').css({left:x, top:y});
//      $('#tooltipComp').html('<span style="font-size:10px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + '件数: ' + data[1]);
//      $('#tooltipComp').show();
//
//    });
//
//    $("#compGraph").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
//        $('#tooltipComp').empty();
//        $('#tooltipComp').hide();
//    });
//}


function drawAnything1Graph() {

    $('#anythingGraph1').remove();
    $('#anythingGraph1Area').append('<div id=\"anythingGraph1\" style=\"height:220px;\"></div>');

    var data = [['商品A', 6],['商品B', 9], ['商品C', 14], ['商品D', 6], ['商品E', 7], ['商品F', 2], ['商品G', 7]];
    var resultPlot = jQuery.jqplot ('anythingGraph1', [data],
      {
        seriesDefaults: {
          renderer: jQuery.jqplot.PieRenderer,
          rendererOptions: {
            showDataLabels: true
          }
        },
        legend: { show:true, location: 'e' }
      }
    );

    $("#anythingGraph1").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
      var chart_left = $('#anythingGraph1').offset().left,
      chart_top = $('#anythingGraph1').offset().top,
      x = chart_left + 110;
      y = chart_top + 100;
      var color = 'rgb(50%,50%,100%)';
      $('#tooltipAnything').css({left:x, top:y});
      $('#tooltipAnything').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + '件数: ' + data[1]);
      $('#tooltipAnything').show();

    });

    $("#anythingGraph1").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
        $('#tooltipAnything').empty();
        $('#tooltipAnything').hide();
    });
}

function drawAnything1HorizonGraph() {

    var gyoushuSid = -1;
    if ($("input[name=ntp220NowSelParentId]").val() == "process") {
        if ($("select[name=ntp220GyoushuSid]").val() != null) {
            gyoushuSid = $("select[name=ntp220GyoushuSid]").val();
        }
    }

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getContentData",
                                   "CMD":"getContentData",
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "pageNum":$("input[name=anything1page]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(data) {

        if (data != null && data != "" && data.contentDataList != null) {

            var dataList = data.contentDataList;

            if (dataList.length > 0 && dataList.length > Number($("input[name=anything1NowCount]").val())) {
                $('#anything1_more').removeClass("display_none");
            } else {
                $('#anything1_more').addClass("display_none");
            }

            $("input[name=anything1NowCount]").val(dataList.length);

            graphHeight = dataList.length * 90;

            $('#anythingGraph1').remove();
            $('#anythingGraph1Area').append('<div id=\"anythingGraph1\" style=\"height:' + graphHeight +  'px;\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10) {
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var juchuStr    = "[";
            var mitumoriStr = "[";

            for (i=0; i < dataList.length; i++) {

                 if (i != 0 && i != dataList.length) {
                     juchuStr += ",";
                     mitumoriStr += ",";
                 }
                 juchuStr += "["
                          +  dataList[i].nanKinJutyu
                          +  ",'"
                          +  strCut(dataList[i].cntName1 + " " + dataList[i].cntName2)
                          +  "']";

                 mitumoriStr += "["
                             +  dataList[i].nanKinMitumori
                             +  ",'"
                             +  strCut(dataList[i].cntName1 + " " + dataList[i].cntName2)
                             +  "']";
            }

            juchuStr    += "]";
            mitumoriStr += "]";

            var juchuObject    = eval(juchuStr);
            var mitumoriObject = eval(mitumoriStr);

            jQuery( function() {
                jQuery.jqplot(
                    'anythingGraph1',
                    [ mitumoriObject,juchuObject ],
                    {
                        axes: {
                            xaxis: {
                                tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
                                tickOptions: {
                                  angle: -30,
                                  fontSize: '8pt'
                                }
                            },
                            yaxis: {
                                renderer: jQuery . jqplot . CategoryAxisRenderer
                            }
                        },
                        animate: animateFlg,
                        animateReplot: animateFlg,
                        seriesDefaults: {
                            renderer: jQuery . jqplot . BarRenderer,
                            rendererOptions: {
                                barPadding: 8,
                                barMargin: 10,
                                barDirection: 'horizontal',
                                barWidth: null,
                                shadowOffset: 2,
                                shadowDepth: 5,
                                shadowAlpha: 0.08,
                                animation: {
                                    speed: 1500
                                }
                            }
                        },
                        highlighter: {
                            show: true,
                            showMarker: false,
                            tooltipLocation: 'e',
                            tooltipAxes: 'x',
                            formatString: '　%s　'
                        }
                    }
                );
            } );
        } else {
            $('#anythingGraph1').remove();
            $('#anything1_more').addClass("display_none");
            $('#anythingGraph1Area').append('<div id=\"anythingGraph1\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}

function drawAnything2Graph() {


    var cmdStr = "getAnkenStateCnt";

    var dataFlg = true;

    if ($("input[name=ntp220NowSelParentId]").val() != "kigyou") {
        var cmdStr = "getAnkenContentStateCnt";
    }

    var gyoushuSid = -1;
    if ($("input[name=ntp220NowSelParentId]").val() == "process") {
        if ($("select[name=ntp220GyoushuSid]").val() != null) {
            gyoushuSid = $("select[name=ntp220GyoushuSid]").val();
        }
    }

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "") {

            $('#anythingGraph2').remove();
            $('#anythingGraph2Area').append('<div id=\"anythingGraph2\"></div>');

            var data = "";

            if ($("input[name=ntp220NowSelParentId]").val() == "kigyou") {
                if (cntData.jutyuCnt != null) {
                    data = [['受注', cntData.jutyuCnt],['失注', cntData.sityuCnt], ['商談中', cntData.syodanCnt]];
                    $(".state_label_area").addClass("display_none");
                    dataFlg = true;
                }

            } else {
                if (cntData.length > 0) {
                    var dataStr = "";
                    dataStr += "[";
                    for (i=0; i < cntData.length; i++) {
                        var stateData = cntData[i];
                        if (i == 0) {
                            dataStr += "['";
                        } else {
                            dataStr += ",['";
                        }
                        dataStr += stateData.contentName
                                +  "',"
                                +  stateData.ankenCnt
                                +  "]";
                    }
                    dataStr += "]";
                    data = eval(dataStr);
                    dataFlg = true;
                }
                $(".state_label_area").removeClass("display_none");
            }


            var resultPlot = jQuery.jqplot ('anythingGraph2', [data],
              {
                seriesDefaults: {
                  renderer: jQuery.jqplot.PieRenderer,
                  rendererOptions: {
                    showDataLabels: true
                  }
                },
                legend: { show:true, location: 'e' }
              }
            );

            $("#anythingGraph2").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
              var chart_left = $('#anythingGraph2').offset().left,
              chart_top = $('#anythingGraph2').offset().top,
              x = chart_left + 110;
              y = chart_top + 100;
              var color = 'rgb(50%,50%,100%)';
              $('#tooltipAnythingMoney').css({left:x, top:y});
              $('#tooltipAnythingMoney').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + '件数: ' + data[1]);
              $('#tooltipAnythingMoney').show();

            });

            $("#anythingGraph2").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
                $('#tooltipAnythingMoney').empty();
                $('#tooltipAnythingMoney').hide();
            });

            if (!dataFlg) {
                $('#anythingGraph2').remove();
                $('#anythingGraph2Area').append('<div id=\"anythingGraph2\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
            }


            if ($("input[name=ntp220NowSelParentId]").val() == "kigyou") {
                if (cntData.jutyuCnt == 0 && cntData.sityuCnt == 0 && cntData.syodanCnt == 0) {
                    $('#anythingGraph2').remove();
                    $('#anythingGraph2Area').append('<div id=\"anythingGraph2\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
                }
            }

        } else {
            $('#anythingGraph2').remove();
            $('#anythingGraph2Area').append('<div id=\"anythingGraph2\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function drawAnything2HorizonGraph() {


    var cmdStr = "getAnkenStateCnt";

    var dataFlg = false;

    if ($("input[name=ntp220NowSelParentId]").val() != "kigyou") {
        var cmdStr = "getAnkenContentStateCnt";
    }

    var gyoushuSid = -1;
    if ($("input[name=ntp220NowSelParentId]").val() == "process") {
        if ($("select[name=ntp220GyoushuSid]").val() != null) {
            gyoushuSid = $("select[name=ntp220GyoushuSid]").val();
        }
    }

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "") {

            $('#anythingGraph2').remove();
            $('#anythingGraph2Area').append('<div id=\"anythingGraph2\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10){
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            if ($("input[name=ntp220NowSelParentId]").val() == "kigyou") {
                if (cntData.syodanCnt != null) {
                    data = [[cntData.syodanCnt, '商談中'], [cntData.sityuCnt, '失注'], [cntData.jutyuCnt, '受注']];
                    $(".state_label_area").addClass("display_none");
                    dataFlg = true;
                }
            } else {
                if (cntData.length > 0) {
                    var dataStr = "";
                    dataStr += "[";
                    for (i=0; i < cntData.length; i++) {
                        var stateData = cntData[i];
                        if (i == 0) {
                            dataStr += "[";
                        } else {
                            dataStr += ",[";
                        }
                        dataStr += stateData.ankenCnt
                                +  ",'"
                                +  stateData.contentName
                                +  "']";
                    }
                    dataStr += "]";
                    data = eval(dataStr);
                    dataFlg = true;
                }
                $(".state_label_area").removeClass("display_none");
            }

            jQuery . jqplot(
                    'anythingGraph2',
                    [data],
                    {
                        animate: animateFlg,
                        animateReplot: animateFlg,
                        seriesColors:['#c5b47f', '#eaa228', '#4bb2c5'],
                        seriesDefaults: {
                            renderer: jQuery . jqplot . BarRenderer,
                            rendererOptions: {
                                barDirection: 'horizontal',
                                varyBarColor: true,
                                animation: {
                                    speed: 1500
                                }
                            }
                        },
                        axes: {
                            xaxis:{
                              label: '件数',
                              labelOptions: {fontSize: '8pt'}
                            },
                            yaxis: {
                                pad:0,
                                renderer: jQuery . jqplot . CategoryAxisRenderer
                            }
                        },
                        highlighter: {
                            show: true,
                            showMarker: false,
                            tooltipLocation: 'e',
                            tooltipAxes: 'x',
                            formatString: '　%s　'
                        }
                    }
                );
            if (!dataFlg) {
                $('#anythingGraph2').remove();
                $('#anythingGraph2Area').append('<div id=\"anythingGraph2\" style=\"height:350px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
            }

            if ($("input[name=ntp220NowSelParentId]").val() == "kigyou") {
                if (cntData.jutyuCnt == 0 && cntData.sityuCnt == 0 && cntData.syodanCnt == 0) {
                    $('#anythingGraph2').remove();
                    $('#anythingGraph2Area').append('<div id=\"anythingGraph2\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
                }
            }

        } else {
            $('#anythingGraph2').remove();
            $('#anythingGraph2Area').append('<div id=\"anythingGraph2\" style=\"height:350px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });

}

function drawAnythingChild0Graph() {

}

function drawAnythingChild0HorizonGraph() {

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getAnkenData",
                                   "CMD":"getAnkenData",
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "ankenDataPageNum":-1,
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(data) {

        if (data != null && data != "" && data.length > 0) {


            $('#anythingChild0Graph').remove();
            $('#anythingChild0GraphArea').append('<div id=\"anythingChild0Graph\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10) {
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var grapthParamStr    = "[";
            var labelStr    ="";
            var sumOfJuchu = 0;
            var sumOfMitumori = 0;
            var brStr = "";

            for (i=0; i < data.length; i++) {

                if (i % 2 == 0) {
                    brStr =""
                } else {
                    brStr="<br><br>";
                }

                 var dataMdl = data[i];

                 if (i != 0 && i != data.length) {
                     grapthParamStr += ",";
                     labelStr += ",";
                 }

                 grapthParamStr += "[";

                 grapthParamStr += "["
                                +  dataMdl.nanKinMitumori
                                +  ","
                                +  1
                                +  ",'"
                                +  "<span style=\"font-weight:bold;color:#333333;\">"
                                +  brStr
                                +  dataMdl.nanName
                                +  "</span>"
                                +  "'],";

                 sumOfMitumori  += Number(dataMdl.nanKinMitumori);

                 grapthParamStr += "["
                                +  dataMdl.nanKinJutyu
                                +  ","
                                +  2
                                +  ",'"
                                +  "<span style=\"font-weight:bold;color:#333333;\">"
                                +  brStr
                                +  dataMdl.nanName
                                +  "</span>"
                                +  "'],";

                 sumOfJuchu  += Number(dataMdl.nanKinJutyu);

                 grapthParamStr += "]";
            }

            grapthParamStr      += "]";

            var grapthParamObject = eval(grapthParamStr);

            //var tooltipFormatStr = "<table class=\"jqplot-highlighter\"><tr><td>%s</td><td></td></tr><tr><td>金額:</td><td>%s</td><td>%s</td></tr><tr></tr></table>"

            jQuery( function() {
                jQuery.jqplot(
                    'anythingChild0Graph',
                    grapthParamObject,
                    {
                        axes: {
                            xaxis: {
                                tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
                                tickOptions: {
                                  angle: -30,
                                  fontSize: '8pt'
                                }
                            },
                            yaxis: {
                                renderer: jQuery . jqplot . CategoryAxisRenderer,
                                ticks:['見積り金額合計<br><span style="font-weight:bold;font-size:14px;color;#333333;">' + addFigure(sumOfMitumori) + '</span>円', '受注金額合計<br><span style="font-weight:bold;font-size:14px;color;#333333;">' + addFigure(sumOfJuchu) + '</span>円']
                            }
                        },
                        animate: animateFlg,
                        animateReplot: animateFlg,
                        stackSeries: true,
                        seriesDefaults: {
                            renderer: jQuery . jqplot . BarRenderer,
                            rendererOptions: {
                                barPadding: 8,
                                barMargin: 10,
                                barDirection: 'horizontal',
                                barWidth: null,
                                shadowOffset: 2,
                                shadowDepth: 5,
                                shadowAlpha: 0.08,
                                animation: {
                                    speed: 1500
                                }
                            },
                            pointLabels:{show:true,
                                escapeHTML:false}
                        },
                        highlighter: {
                            show: true,
                            showMarker: true,
                            tooltipLocation: 'ne',
                            tooltipAxes: 'x',
                            formatString:'%s'
                        }
                    }
                );
            } );
        } else {
            $('#anythingChild0Graph').remove();
            $('#anythingChild0GraphArea').append('<div id=\"anythingChild0Graph\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}

function drawAnythingChild1Graph() {


    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getAnkenStateCnt",
                                   "CMD":"getAnkenStateCnt",
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "" && !(cntData.syodanCnt == 0 && cntData.sityuCnt == 0 && cntData.jutyuCnt == 0)) {

            $('#anythingChild1Graph').remove();
            $('#anythingChild1GraphArea').append('<div id=\"anythingChild1Graph\"></div>');

            var data = [['受注', cntData.jutyuCnt],['失注', cntData.sityuCnt], ['商談中', cntData.syodanCnt]];
            var resultPlot = jQuery.jqplot ('anythingChild1Graph', [data],
              {
                seriesDefaults: {
                  renderer: jQuery.jqplot.PieRenderer,
                  rendererOptions: {
                    showDataLabels: true
                  }
                },
                legend: { show:true, location: 'e' }
              }
            );

            $("#anythingChild1Graph").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
              var chart_left = $('#anythingChild1Graph').offset().left,
              chart_top = $('#anythingChild1Graph').offset().top,
              x = chart_left + 110;
              y = chart_top + 100;
              var color = 'rgb(50%,50%,100%)';
              $('#tooltipAnythingChild1').css({left:x, top:y});
              $('#tooltipAnythingChild1').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + '件数: ' + data[1]);
              $('#tooltipAnythingChild1').show();

            });

            $("#anythingChild1Graph").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
                $('#tooltipAnythingChild1').empty();
                $('#tooltipAnythingChild1').hide();
            });


        } else {
            $('#anythingChild1Graph').remove();
            $('#anythingChild1GraphArea').append('<div id=\"anythingChild1Graph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });

}

function drawAnythingChild1HorizonGraph() {

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getAnkenStateCnt",
                                   "CMD":"getAnkenStateCnt",
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "" && !(cntData.syodanCnt == 0 && cntData.sityuCnt == 0 && cntData.jutyuCnt == 0)) {

            $('#anythingChild1Graph').remove();
            $('#anythingChild1GraphArea').append('<div id=\"anythingChild1Graph\"></div>');

            var animateFlg = false;

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10){
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            jQuery . jqplot(
                    'anythingChild1Graph',
                    [
                        [[cntData.syodanCnt, '商談中'], [cntData.sityuCnt, '失注'], [cntData.jutyuCnt, '受注']]
                    ],
                    {
                        animate: animateFlg,
                        animateReplot: animateFlg,
                        seriesColors:['#c5b47f', '#eaa228', '#4bb2c5'],
                        seriesDefaults: {
                            renderer: jQuery . jqplot . BarRenderer,
                            rendererOptions: {
                                barDirection: 'horizontal',
                                varyBarColor: true,
                                animation: {
                                    speed: 1500
                                }
                            }
                        },
                        axes: {
                            yaxis: {
                                renderer: jQuery . jqplot . CategoryAxisRenderer
                            }
                        },
                        highlighter: {
                            show: true,
                            showMarker: false,
                            tooltipLocation: 'e',
                            tooltipAxes: 'x',
                            formatString: '　%s　'
                        }
                    }
                );

        } else {
            $('#anythingChild1Graph').remove();
            $('#anythingChild1GraphArea').append('<div id=\"anythingChild1Graph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });

}

function drawAnythingChild2Graph() {

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getShohinData",
                                   "CMD":"getShohinData",
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(shnData) {

        if (shnData != null && shnData != "" && shnData.length > 0) {

            var heightStr = 200;

            if ((28 * shnData.length) > 200) {
                heightStr = 28 * shnData.length;
            }

            $('#anythingChild2Graph').remove();
            $('#anythingChild2GraphArea').append('<div id=\"anythingChild2Graph\" style=\"height:' + heightStr + 'px\"></div>');

            var shnStr    = "[";

            for (i=0; i < shnData.length; i++) {
                 if (i != 0 && i != shnData.length) {
                     shnStr += ",";
                 }
                 shnStr += "['"
                          +  shnData[i].nhnName
                          +  "',"
                          +  shnData[i].nshCnt
                          +  "]";
            }

            shnStr    += "]";

            var shohinObject = eval(shnStr);

            var resultPlot = jQuery.jqplot ('anythingChild2Graph', [shohinObject],
              {
                seriesDefaults: {
                  renderer: jQuery.jqplot.PieRenderer,
                  rendererOptions: {
                    showDataLabels: true
                  }
                },
                legend: { show:true, location: 'e' }
              }
            );

            $("#anythingChild2Graph").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
              var chart_left = $('#anythingChild2Graph').offset().left,
              chart_top = $('#anythingChild2Graph').offset().top,
              x = chart_left + 110;
              y = chart_top + 100;
              var color = 'rgb(50%,50%,100%)';
              $('#tooltipAnythingChild2').css({left:x, top:y});
              $('#tooltipAnythingChild2').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + '件数: ' + data[1]);
              $('#tooltipAnythingChild2').show();

            });

            $("#anythingChild2Graph").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
                $('#tooltipAnythingChild2').empty();
                $('#tooltipAnythingChild2').hide();
            });

        } else {
            $('#anythingChild2Graph').remove();
            $('#anythingChild2GraphArea').append('<div id=\"anythingChild2Graph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function drawAnythingChild2HorizonGraph() {


    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getShohinData",
                                   "CMD":"getShohinData",
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(shnData) {

        if (shnData != null && shnData != "" && shnData.length > 0) {

            var heightStr = 200;

            if ((60 * shnData.length) > 200) {
                heightStr = 60 * shnData.length;
            }

            $('#anythingChild2Graph').remove();
            $('#anythingChild2GraphArea').append('<div id=\"anythingChild2Graph\" style=\"height:' + heightStr + 'px\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10){
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var shnStr    = "[";

            for (i=0; i < shnData.length; i++) {
                 if (i != 0 && i != shnData.length) {
                     shnStr += ",";
                 }
                 shnStr += "["
                          +  shnData[i].nshCnt
                          +  ",'"
                          +  strCut(shnData[i].nhnName)
                          +  "']";
            }

            shnStr    += "]";

            var shohinObject = eval(shnStr);

            jQuery . jqplot(
                    'anythingChild2Graph',
                    [
                        shohinObject
                    ],

                    {
                        animate: animateFlg,
                        animateReplot: animateFlg,
                        seriesColors:['#839557', '#579575', '#c5b47f', '#eaa228', '#4bb2c5'],
                        seriesDefaults: {
                            renderer: jQuery . jqplot . BarRenderer,
                            rendererOptions: {
                                barDirection: 'horizontal',
                                varyBarColor: true,
                                animation: {
                                    speed: 1500
                                }
                            }
                        },
                        axes: {
                            yaxis: {
                                pad:0,
                                renderer: jQuery . jqplot . CategoryAxisRenderer
                            }
                        },
                        highlighter: {
                            show: true,
                            showMarker: false,
                            tooltipLocation: 'e',
                            tooltipAxes: 'x',
                            formatString: '　%s　'
                        }
                    }
                );
        } else {
            $('#anythingChild2Graph').remove();
            $('#anythingChild2GraphArea').append('<div id=\"anythingChild2Graph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}



//ユーザ一覧成形
function getSearchBtnResultList(contentSid, pageNum, searchWord) {

    //ユーザ一覧取得
    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getSearchBtnResultList",
                                   "CMD":"getSearchBtnResultList",
                                   "contentSid":contentSid,
                                   "pageNum":pageNum,
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "searchWord":searchWord,
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(data) {
        if (data != null || data != "") {

            $('#searchBtnResultArea').children().remove();

            if (data.menuParamList != null && data.menuParamList.length > 0) {

                var searchBtnResultInfstr = "";
                var maxpagesize = data.maxPageSize;
                pageNum = data.pageNum;

                //ページング
                if (parseInt(maxpagesize) > 1) {
                    searchBtnResultInfstr += "<div style=\"width:100%;text-align:right;\">"
                              +  "<img alt=\"前頁\" src=\"../common/images/arrow2_l.gif\" id=\"" + contentSid + ":" + pageNum + "\" class=\"prevPageBtn cursor_pointer\" border=\"0\" height=\"22\" width=\"22\">"
                              +  "<select name=\"searchBtnResultChange\" id=\"" + contentSid + "\" class=\"selchange text_i cursor_pointer\" style=\"height:22px;\">";

                    for (p=1; p <= parseInt(maxpagesize); p++) {
                        if (pageNum == p) {
                            searchBtnResultInfstr +=  "<option value=\"" + p + "\" selected=\"selected\">" + p + " / " + maxpagesize + "</option>";
                        } else {
                            searchBtnResultInfstr +=  "<option value=\"" + p + "\">" + p + " / " + maxpagesize + "</option>";
                        }
                    }

                    searchBtnResultInfstr +=  "</select>"
                              +   "<img src=\"../common/images/arrow2_r.gif\" name=\"btn_next\" alt=\"次頁\" id=\"" + contentSid + ":" + pageNum + "\" class=\"nextPageBtn cursor_pointer\" border=\"0\" height=\"22\" width=\"22\">"
                              +   "</div>";
                }

                //メニュー一覧
                searchBtnResultInfstr += "<table class=\"tl0\" style=\"border-collapse:collapse;\" width=\"100%\">";

                for (i=0; i<data.menuParamList.length; i++) {

                    searchBtnResultInfstr += "<tr height=\"40px\" class=\"cursor_pointer\" onclick=\"selDialogContent('"
                                          +  contentSid
                                          +  "','"
                                          +  contentSid
                                          +  "_"
                                          +  data.menuParamList[i].contentSid1;
                    if (data.menuParamList[i].contentSid2 != null && data.menuParamList[i].contentSid2 != "") {
                        searchBtnResultInfstr += "_" + data.menuParamList[i].contentSid2;
                    }
                    searchBtnResultInfstr += "','"
                                          +  data.menuParamList[i].contentName1
                                          +  "','";

                    if (data.menuParamList[i].contentName2 != null && data.menuParamList[i].contentName2 != "") {
                        searchBtnResultInfstr += data.menuParamList[i].contentName2;
                    }

                    searchBtnResultInfstr += "');\">"
                                          +  "<td class=\"usr_inf_area_top_bottom3 content_select_area\" align=\"left\" nowrap=\"nowrap\" width=\"100%\">"
                                          +  "<span class=\"text_link2\"><a>"
                                          +  data.menuParamList[i].contentName1;

                    if (data.menuParamList[i].contentName2 != null && data.menuParamList[i].contentName2 != "") {
                        searchBtnResultInfstr += "&nbsp;&nbsp;" + data.menuParamList[i].contentName2;
                    }

                    searchBtnResultInfstr += "\</a></span>"
                                          +  "</td>"
                                          +  "</tr>";
                }

                searchBtnResultInfstr += "</table>";


                //ページング
                if (parseInt(maxpagesize) > 1) {
                    searchBtnResultInfstr += "<div style=\"width:100%;text-align:right;\">"
                              +  "<img alt=\"前頁\"  src=\"../common/images/arrow2_l.gif\" id=\"" + contentSid + ":" + pageNum + "\" class=\"prevPageBtn img_bottom cursor_pointer\" border=\"0\" height=\"22\" width=\"22\">"
                              +  "<select name=\"usrInfChange\" id=\"" + contentSid + "\" class=\"selchange text_i cursor_pointer\" style=\"height:22px;\">";

                    for (p=1; p <= parseInt(maxpagesize); p++) {
                        if (pageNum == p) {
                            searchBtnResultInfstr +=  "<option value=\"" + p + "\" selected=\"selected\">" + p + " / " + maxpagesize + "</option>";
                        } else {
                            searchBtnResultInfstr +=  "<option value=\"" + p + "\">" + p + " / " + maxpagesize + "</option>";
                        }
                    }

                    searchBtnResultInfstr +=  "</select>"
                              +  "<img src=\"../common/images/arrow2_r.gif\" name=\"btn_next\" alt=\"次頁\" id=\"" + contentSid + ":" + pageNum + "\" class=\"nextPageBtn img_bottom cursor_pointer\" border=\"0\" height=\"22\" width=\"22\">"
                              +  "</div>";
                }

                $('#searchBtnResultArea').append(searchBtnResultInfstr);

                /* ユーザリンク hover */
                $('.content_select_area').hover(
                    function () {
                        $(this).removeClass("content_select_area").addClass("content_select_area_hover");
                      },
                      function () {
                          $(this).removeClass("content_select_area_hover").addClass("content_select_area");
                      }
                );
            } else {
                $('#searchBtnResultArea').append("<span style=\"padding-top:220px;height:100%;width:100%;font-weight:bold;text-align:center;\">該当するデータがありません。</span>");
            }
        }
    });
}

//ダイアログ項目選択
function selDialogContent(contentSid, paramStr, name1, name2) {

    $(".sel_menu_content_text").removeClass("sel_menu_content_text_sel");

    if ($("#" + paramStr).html() != null) {
        var htmlStr = $("#" + paramStr).clone(true);
        $("#" + paramStr).next().remove();
        $("#" + paramStr).remove();
        $("#" + contentSid + "_sel_menu_after_area").after(htmlStr);
        $("#" + contentSid + "_sel_menu_after_area").next().after("<br />");
        $(".sel_menu_content_text").removeClass("sel_menu_content_text_sel");
        $("#" + paramStr).addClass("sel_menu_content_text_sel");
    } else {
        var contentParam = paramStr.split("_");
        var dspContentStr = name1.substring(0, 12);
        var selMenu = "<span class=\"sel_menu_content_text sel_menu_content_text_sel\" id=\""
                    +  contentSid
                    +  "_"
                    +  contentParam[1];
                    if (contentParam[2] != null && contentParam[2] != "") {
                        selMenu += "_" + contentParam[2];
                    }
                    selMenu +=  "\">" + dspContentStr;
                    selMenu += "<input type=\"hidden\" value=\"" + name1 + "<br>" + name2 + "\" />";
                    selMenu += "<span style=\"display:none;\" class=\"tooltips\"><span class=\"tooltip_txt\">" + name1;
                    if (name2 != null && name2 != "") {
                        selMenu += "<br />" + name2;
                    }
                    selMenu += "</span></span></span><br />";
        $("#" + contentSid + "_sel_menu_after_area").after(selMenu);
        var alreadyStr = $("#" + contentSid + "_already_sel").val();
        alreadyStr += contentParam[1]
                   + "_";
        if (contentParam[2] != null && contentParam[2] != "") {
            alreadyStr += contentParam[2];
        } else {
            alreadyStr += "0";
        }
        alreadyStr += ",";
        $("#" + contentSid + "_already_sel").val(alreadyStr);
    }
    $('.anything_child_area').removeClass('display_none');
    $('.anything_area').addClass('display_none');
    $('.anything_kadou_area').addClass('display_none');
    $('.def_graph').addClass('display_none');
    $(".sel_menu_content_text").removeClass("sel_menu_content_text_sel");
    $("input[name=ntp220NowSelChildId]").val(paramStr);
//    $(".child_sel_content_title").html(name1 + "<br>" + name2);
    $(".child_sel_content_head_title").html(name1 + "&nbsp" + name2);
    $("#" + paramStr).addClass("sel_menu_content_text_sel");
    anythingChildAllGraph();
    $('#searchBtnResultPop').dialog('close');
}


function drawAnkenDataArea() {

    var gyoushuSid = -1;
    if ($("input[name=ntp220NowSelParentId]").val() == "process") {
        if ($("select[name=ntp220GyoushuSid]").val() != null) {
            gyoushuSid = $("select[name=ntp220GyoushuSid]").val();
        }
    }

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getAnkenData",
                                   "CMD":"getAnkenData",
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ankenListState]").val(),
                                   "ankenDataPageNum":$("input[name=ankenDataPageNum]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(data) {
        if (data != null && data != "" && data.length > 0) {
            var pageNum = $("input[name=ankenDataPageNum]").val();
            $("input[name=ankenDataPageNum]").val((Number(pageNum) + 1));

            var ankenDataStr = "";

            for (i=0; i < data.length; i++) {
                var dataMdl = data[i];
                var shohinStr = "";
                var tantoStr = "";
                var shohinStrHidden = "";

                //商品
                if (dataMdl.shohinList.length > 0) {
                    for (n=0; n < dataMdl.shohinList.length; n++) {
                      var shnMdl = dataMdl.shohinList[n];

                      if($("select[name=ntp220CatSid]").val() != "-1") {
                          if (shnMdl.nscSid == $("select[name=ntp220CatSid]").val()) {
                              shohinStr += shnMdl.nhnName
                              +  "<br>";
                          } else {
                              shohinStrHidden += shnMdl.nhnName
                              +  "<br>";
                          }
                      } else {
                          shohinStr += shnMdl.nhnName
                          +  "<br>";
                      }
                    }
                }

                //担当者
                if (dataMdl.tantoUsrInfList.length > 0) {
                    for (n=0; n < dataMdl.tantoUsrInfList.length; n++) {
                      var tantoMdl = dataMdl.tantoUsrInfList[n];
                      tantoStr += "<a href=\"javaScript:void(0);\" onClick=\"openUserInfoWindow("
                               +  tantoMdl.usrSid
                               +  ");\">"
                               +  tantoMdl.usiSei + "&nbsp;" + tantoMdl.usiMei
                               +  "</a>"
                               +  "<br>";
                    }
                }

                var trClassName = "anken_val";
                if ((((pageNum-1)*5) + i + 1) % 2 == 0) {
                    trClassName = "anken_val2";
                }

                ankenDataStr += "<tr class=\"" + trClassName + "\"><td align=\"center\">"
                             +  (((pageNum-1)*5) + i + 1)
                             +  "</td>";

                if (Number(dataMdl.nahSid) == -1) {
                    ankenDataStr += "<td align=\"left\" class=\"anken_name_link\" id=\"" + dataMdl.nanSid + "\">";
                } else {
                    ankenDataStr += "<td class=\"anken_name_link_his\" id=\"" + dataMdl.nahSid + "\">";
                }

                ankenDataStr +=  dataMdl.nanName
                             +  "</td><td align=\"center\">";

                var stateStr = "商談中";
                if (dataMdl.nanSyodan == 1) {
                    stateStr = "受&nbsp;&nbsp;注";
                } else if (dataMdl.nanSyodan == 2) {
                    stateStr = "失&nbsp;&nbsp;注";
                }

                ankenDataStr += stateStr
                             +  "</td><td align=\"right\" class=\"anken_list_mitumori ";

                if ($("input[name=ankenListMoney]").val() != 0) {
                    ankenDataStr += "display_none";
                }

                ankenDataStr +=  "\">"
                             +  "<span style=\"\" class=\"tooltips\"><span class=\"tooltip_txt2\">"
                             +  "提出日:" + dataMdl.nanMitumoriDateStr;

                if (dataMdl.nanMitumoriDateKbn == 1) {
                    ankenDataStr +=  "<br><span class=\"out_of_msg\">(見積もり日は指定期間の範囲外です。)</span>";
                }

                ankenDataStr +=  "</span></span>"
                             +  "<div class=\"mitumori_kin_str\">" + addFigure(dataMdl.nanKinMitumori) + "</div>"
                             +  "</td><td align=\"right\" class=\"anken_list_jutyu ";

                if ($("input[name=ankenListMoney]").val() == 0) {
                    ankenDataStr += "display_none";
                }

                ankenDataStr +=  "\">"
                             +  "<span style=\"display:none;\" class=\"tooltips\"><span class=\"tooltip_txt2\">"
                             +  "受注日:" + dataMdl.nanJutyuDateStr;

                             if (dataMdl.nanJutyuDateKbn == 1) {
                                 ankenDataStr +=  "<br><span class=\"out_of_msg\">(受注日は指定期間の範囲外です。)</span>";
                             }

               ankenDataStr  +=  "</span></span>"
                             +  "<div class=\"jutyu_kin_str\">" + addFigure(dataMdl.nanKinJutyu) + "</div>"
                             +  "</td>";


                ankenDataStr += "<td align=\"left\" class=\"anken_list_other anken_list_kigyou ";
                if ($("input[name=ankenListOther]").val() != 0) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  dataMdl.cntName1;
                if (dataMdl.cntName2 != null && dataMdl.cntName2 != "") {
                    ankenDataStr += "<br>" + dataMdl.cntName2;
                }
                ankenDataStr += "</td>";


                //商品
                ankenDataStr += "<td align=\"left\" class=\"anken_list_other anken_list_shohin ";
                if ($("input[name=ankenListOther]").val() != 1) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  shohinStr;

                if (shohinStrHidden != "") {
                    ankenDataStr += "<div class=\"other_category_itm\">▼他のカテゴリの商品</div>";
                    ankenDataStr +=  "<div class=\"display_none\">" + shohinStrHidden + "</div>";
                }

                ankenDataStr += "</td>";

                //業種
                ankenDataStr += "<td align=\"left\" class=\"anken_list_other anken_list_gyoushu ";
                if ($("input[name=ankenListOther]").val() != 2) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  dataMdl.ngyName;
                ankenDataStr += "</td>";

                //プロセス
                ankenDataStr += "<td align=\"left\" class=\"anken_list_other anken_list_process ";
                if ($("input[name=ankenListOther]").val() != 3) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  dataMdl.ngpName;
                ankenDataStr += "</td>";

                //見込み度
                ankenDataStr += "<td align=\"center\" class=\"anken_list_other anken_list_mikomido ";
                if ($("input[name=ankenListOther]").val() != 4) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  dataMdl.nanMikomiVal;
                ankenDataStr += "</td>";

                //顧客源泉
                ankenDataStr += "<td align=\"left\" class=\"anken_list_other anken_list_kokyakugensen ";
                if ($("input[name=ankenListOther]").val() != 5) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  dataMdl.ncnName;
                ankenDataStr += "</td>";

                //担当者
                ankenDataStr += "<td align=\"left\" class=\"anken_list_other anken_list_tanto ";
                if ($("input[name=ankenListOther]").val() != 6) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  tantoStr;
                ankenDataStr += "</td>";

                ankenDataStr += "</tr>";
            }

            if (i >= 5) {
                $(".anken_list_more_area").removeClass("display_none");
            } else {
                $(".anken_list_more_area").addClass("display_none");
            }

            if (pageNum != 1) {
                $('#anken_val_table').append(ankenDataStr);
            } else {
                $('#anken_val_title').after(ankenDataStr);
            }

        } else {
            $(".anken_list_more_area").addClass("display_none");
            $('#ankenDataArea').append('<div id=\"anythingGraph2\" style=\"height:350px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}

//期間稼働時間グラフ
function drawKadouGraph() {

    var cmdStr = "getPeriodKadouData";
    var defGraphMode = $("input[name=def_graph_val]").val();

    $.ajaxSetup({async:true});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "defGraphMode":defGraphMode,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {


        if (cntData != null && cntData != "" && cntData.length > 0) {

            $('#kadouGraph').remove();
            $('#kadouGraphArea').append('<div id=\"kadouGraph\" style=\"height:260px;\"></div>');

            var animateFlg = false;

            var sumKadouTime = 0;

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10){
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var dateStr      = "[";
            var kadouStr     = "[";

            var dayAddFlg = 0;

            for (i = 0; i < cntData.length; i++) {
                var totalData = cntData[i];

                if (i != 0) {
                    dateStr      += ",";
                    kadouStr     += ",";
                }
                dateStr      += "'" + totalData.dateStr + "'"
                kadouStr     += totalData.sumKadouTime;
                sumKadouTime += totalData.sumKadouTime;

                if (dayAddFlg == 0) {
                    $('#sum_kadou_days').children().remove();
                    $('#sum_kadou_days').append("<span style=\"text-decoration: underline;\" class=\"\">" + addFigure(totalData.totalKadouDays) + "</span>");
                    dayAddFlg = 1;
                }

            }

            var ticksOpsAngle = 0;
            var ticksOpsSize = '7pt';
            if (cntData.length > 13) {
                ticksOpsAngle = -30;
                ticksOpsSize  = '8pt';
            }

            dateStr      += "]";
            kadouStr     += "]";

            dataStr      = eval(dateStr);
            kadouStr     = eval(kadouStr);

            var jutyuVal = kadouStr;
            var tick = dataStr;

            $('#sum_kadou_time').children().remove();
            $('#sum_kadou_time').append("<span style=\"text-decoration: underline;\" class=\"\">" + addFigure(cntData[0].totalKadouTime) + "</span>");

            $('#sum_kadou_time_average').children().remove();
            $('#sum_kadou_time_average').append("<span style=\"text-decoration: underline;\" class=\"\">" + (sumKadouTime/totalData.totalKadouDays).toFixed(1) + "</span>");

            if(defGraphMode == "0") {
                var ankenPlot = $.jqplot('kadouGraph', [jutyuVal], {
                    animate: animateFlg,
                    animateReplot: animateFlg,
                    legend: {
                       show: true,
                       location: 'nw',
                       renderer: jQuery . jqplot . EnhancedLegendRenderer,
                       rendererOptions:{ numberColumns: 3}
                    },

                    highlighter: {
                        show: true,
                        showMarker: false,
                        sizeAdjust: 0,
                        tooltipLocation: 'n',
                        tooltipAxes: 'y',
                        formatString: '%s'
                    },

                    series:[
                      {
                        label:'稼働時間(h)',
                        yaxis:'yaxis',
                        rendererOptions: {
                                animation: {
                                    speed: 1500
                                }
                            }
                        }
                    ],
                    axes: {
                      xaxis: {
                        renderer: $.jqplot.CategoryAxisRenderer,
                        ticks: tick,
                        label: '',
                        tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
                        tickOptions: {angle:ticksOpsAngle,fontSize:ticksOpsSize}
                      },
                      yaxis: {
                        label: '',
                        tickOptions: {
                            formatString:'%#.1f'
                        }
                      },
                      y2axis: {
                        label: '',
                        tickOptions: {
                            formatString:'%#.1f'
                        }
                      }
                    }
                  });
            }

        } else {
            $('#kadouGraph').remove();
            $('#kadouGraphArea').append('<div id=\"kadouGraph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });

}



function drawAnythingKadou1Graph() {

    var cmdStr = "getKadouAnkenCnt";
    var dataFlg = true;

    var gyoushuSid = -1;

    $("input[name=anythingKadou1NowCount]").val(0);
    $("input[name=anythingKadou1page]").val(1)
    $('#anythingKadou1_more').addClass("display_none");

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "pageNum":-1,
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "") {


            var heightStr = 300;

            if (cntData.length > 0) {
                if ((40 * cntData.length) > 300) {
                    heightStr = 40 * cntData.length;
                }
            }

            $('#anythingKadouGraph1').remove();
            $('#anythingKadouGraph1Area').append('<div style=\"height:' + heightStr + 'px;\" id=\"anythingKadouGraph1\"></div>');


            var data = "";

            if (cntData.length > 0) {
                var dataStr = "";
                dataStr += "[";
                for (i = 0; i < cntData.length; i++) {

                    var totalData = cntData[i];
                    if (i == 0) {
                        dataStr += "['";
                    } else {
                        dataStr += ",['";
                    }

                    var ankenName = totalData.name;

                    if (ankenName == "noAnken") {
                        ankenName = "案件なし";
                    }

                    dataStr += ankenName
                            +  "',"
                            +  totalData.sumKadouTime
                            +  "]";
                }
                dataStr += "]";
                data = eval(dataStr);
                dataFlg = true;
            }

            var resultPlot = jQuery.jqplot ('anythingKadouGraph1', [data],
              {
                seriesDefaults: {
                  renderer: jQuery.jqplot.PieRenderer,
                  rendererOptions: {
                    showDataLabels: true
                  }
                },
                legend: { show:true, location: 'e' }
              }
            );

            $("#anythingKadouGraph1").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
              var chart_left = $('#anythingKadouGraph1').offset().left,
              chart_top = $('#anythingKadouGraph1').offset().top,
              x = chart_left + 110;
              y = chart_top + 100;
              var color = 'rgb(50%,50%,100%)';
              $('#tooltipAnythingKadou1').css({left:x, top:y});
              $('#tooltipAnythingKadou1').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + data[1] + '時間');
              $('#tooltipAnythingKadou1').show();

            });

            $("#anythingKadouGraph1").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
                $('#tooltipAnythingKadou1').empty();
                $('#tooltipAnythingKadou1').hide();
            });

            if (!dataFlg) {
                $('#anythingKadouGraph1').remove();
                $('#anythingKadouGraph1Area').append('<div id=\"anythingKadouGraph1\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
            }

        } else {
            $('#anythingKadouGraph1').remove();
            $('#anythingKadouGraph1Area').append('<div id=\"anythingKadouGraph1\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}



function drawAnythingKadou1HorizonGraph() {

    var cmdStr = "getKadouAnkenCnt";
    var dataFlg = true;

    var gyoushuSid = -1;
    var graphHeight = 100;

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "pageNum":$("input[name=anythingKadou1page]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

          if (cntData != null && cntData != "" && cntData.length > 0) {

            if ((cntData.length * 60) > 100) {
                graphHeight = cntData.length * 60;
            }

            if (cntData.length > 0 && cntData.length > Number($("input[name=anythingKadou1NowCount]").val())) {
                $('#anythingKadou1_more').removeClass("display_none");
            } else {
                $('#anythingKadou1_more').addClass("display_none");
            }

            $("input[name=anythingKadou1NowCount]").val(cntData.length);


            $('#anythingKadouGraph1').remove();
            $('#anythingKadouGraph1Area').append('<div id=\"anythingKadouGraph1\" style=\"height:' + graphHeight +  'px;\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10) {
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var kadouStr    = "[";

            for (i = cntData.length - 1; i >= 0; i--) {

                var totalData = cntData[i];

                 if (i != cntData.length - 1) {
                     kadouStr += ",";
                 }

                 var ankenName = totalData.name;

                 if (ankenName == "noAnken") {
                     ankenName = "案件なし";
                 }

                 kadouStr += "["
                          +  totalData.sumKadouTime
                          +  ",'"
                          +  strCut(ankenName)
                          +  "']";
            }

            kadouStr    += "]";

            var kadouObject    = eval(kadouStr);

            jQuery( function() {

                jQuery.jqplot(
                        'anythingKadouGraph1',
                        [kadouObject],

                        {
                            animate: animateFlg,
                            animateReplot: animateFlg,
                            seriesColors:['#839557', '#579575', '#c5b47f', '#eaa228', '#4bb2c5'],
                            seriesDefaults: {
                                renderer: jQuery . jqplot . BarRenderer,
                                rendererOptions: {
                                    barDirection: 'horizontal',
                                    varyBarColor: true,
                                    animation: {
                                        speed: 1500
                                    }
                                }
                            },
                            axes: {
                                xaxis: {
                                tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
                                tickOptions: {
                                  angle: -30,
                                  fontSize: '8pt',
                                  formatString:'%#.1f'
                                }
                                },
                                yaxis: {
                                    pad:0,
                                    renderer: jQuery . jqplot . CategoryAxisRenderer
                                }
                            },
                            highlighter: {
                                show: true,
                                showMarker: false,
                                tooltipLocation: 'n',
                                tooltipAxes: 'x',
                                formatString: '　%s　'
                            }
                        }
                    );



            } );
        } else {
            $('#anythingKadouGraph1').remove();
            $('#anythingKadou1_more').addClass("display_none");
            $('#anythingKadouGraph1Area').append('<div id=\"anythingKadouGraph1\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function drawAnythingKadou2Graph() {

    var cmdStr = "getKadouKigyouCnt";
    var dataFlg = true;

    var gyoushuSid = -1;

    $("input[name=anythingKadou2NowCount]").val(0);
    $("input[name=anythingKadou2page]").val(1)
    $('#anythingKadou2_more').addClass("display_none");

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "pageNum":-1,
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "") {

            var heightStr = 300;

            if (cntData.length > 0) {
                if ((40 * cntData.length) > 300) {
                    heightStr = 40 * cntData.length;
                }
            }

            $('#anythingKadouGraph2').remove();
            $('#anythingKadouGraph2Area').append('<div style=\"height:' + heightStr + 'px;\" id=\"anythingKadouGraph2\"></div>');

            var data = "";

            if (cntData.length > 0) {
                var dataStr = "";
                dataStr += "[";
                for (i = 0; i < cntData.length; i++) {
                    var totalData = cntData[i];
                    if (i == 0) {
                        dataStr += "['";
                    } else {
                        dataStr += ",['";
                    }

                    var ankenName = totalData.name;

                    if (ankenName == "noKigyou") {
                        ankenName = "企業なし";
                    }

                    dataStr += ankenName
                            +  "',"
                            +  totalData.sumKadouTime
                            +  "]";
                }
                dataStr += "]";
                data = eval(dataStr);
                dataFlg = true;
            }


            var resultPlot = jQuery.jqplot ('anythingKadouGraph2', [data],
              {
                seriesDefaults: {
                  renderer: jQuery.jqplot.PieRenderer,
                  rendererOptions: {
                    showDataLabels: true
                  }
                },
                legend: { show:true, location: 'e' }
              }
            );

            $("#anythingKadouGraph2").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
              var chart_left = $('#anythingKadouGraph2').offset().left,
              chart_top = $('#anythingKadouGraph2').offset().top,
              x = chart_left + 110;
              y = chart_top + 100;
              var color = 'rgb(50%,50%,100%)';
              $('#tooltipAnythingKadou2').css({left:x, top:y});
              $('#tooltipAnythingKadou2').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + data[1] + '時間');
              $('#tooltipAnythingKadou2').show();

            });

            $("#anythingKadouGraph2").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
                $('#tooltipAnythingKadou2').empty();
                $('#tooltipAnythingKadou2').hide();
            });

            if (!dataFlg) {
                $('#anythingKadouGraph2').remove();
                $('#anythingKadouGraph2Area').append('<div id=\"anythingKadouGraph2\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
            }

        } else {
            $('#anythingKadouGraph2').remove();
            $('#anythingKadouGraph2Area').append('<div id=\"anythingKadouGraph2\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function drawAnythingKadou2HorizonGraph() {

    var cmdStr = "getKadouKigyouCnt";
    var dataFlg = true;
    var graphHeight = 100;

    var gyoushuSid = -1;

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "pageNum":$("input[name=anythingKadou2page]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

          if (cntData != null && cntData != "" && cntData.length > 0) {

            if ((cntData.length * 60) > 100) {
                graphHeight = cntData.length * 60;
            }

            if (cntData.length > 0 && cntData.length > Number($("input[name=anythingKadou2NowCount]").val())) {
                $('#anythingKadou2_more').removeClass("display_none");
            } else {
                $('#anythingKadou2_more').addClass("display_none");
            }

            $("input[name=anythingKadou2NowCount]").val(cntData.length);

            $('#anythingKadouGraph2').remove();
            $('#anythingKadouGraph2Area').append('<div id=\"anythingKadouGraph2\" style=\"height:' + graphHeight +  'px;\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10) {
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var kadouStr    = "[";



            for (i = cntData.length - 1; i >= 0; i--) {

                var totalData = cntData[i];

                 if (i != cntData.length - 1) {
                     kadouStr += ",";
                 }

                 var ankenName = totalData.name;

                 if (ankenName == "noKigyou") {
                     ankenName = "企業なし";
                 }

                 kadouStr += "["
                          +  totalData.sumKadouTime
                          +  ",'"
                          +  strCut(ankenName)
                          +  "']";
            }


            kadouStr    += "]";

            var kadouObject    = eval(kadouStr);

            jQuery( function() {

                jQuery.jqplot(
                        'anythingKadouGraph2',
                        [kadouObject],

                        {
                            animate: animateFlg,
                            animateReplot: animateFlg,
                            seriesColors:['#839557', '#579575', '#c5b47f', '#eaa228', '#4bb2c5'],
                            seriesDefaults: {
                                renderer: jQuery . jqplot . BarRenderer,
                                rendererOptions: {
                                    barDirection: 'horizontal',
                                    varyBarColor: true,
                                    animation: {
                                        speed: 1500
                                    }
                                }
                            },
                            axes: {
                                xaxis: {
                                tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
                                tickOptions: {
                                  angle: -30,
                                  fontSize: '8pt',
                                  formatString:'%#.1f'
                                }
                                },
                                yaxis: {
                                    pad:0,
                                    renderer: jQuery . jqplot . CategoryAxisRenderer
                                }
                            },
                            highlighter: {
                                show: true,
                                showMarker: false,
                                tooltipLocation: 'e',
                                tooltipAxes: 'x',
                                formatString: '　%s　'
                            }
                        }
                    );



            } );
        } else {
            $('#anythingKadouGraph2').remove();
            $('#anythingKadou2_more').addClass("display_none");
            $('#anythingKadouGraph2Area').append('<div id=\"anythingKadouGraph2\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}





function drawAnythingKadou3Graph() {

    var cmdStr = "getKadouKbunruiCnt";
    var dataFlg = true;

    var gyoushuSid = -1;

    $("input[name=anythingKadou3NowCount]").val(0);
    $("input[name=anythingKadou3page]").val(1)
    $('#anythingKadou3_more').addClass("display_none");

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "pageNum":-1,
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "") {

            var heightStr = 300;

            if (cntData.length > 0) {
                if ((40 * cntData.length) > 300) {
                    heightStr = 40 * cntData.length;
                }
            }

            $('#anythingKadouGraph3').remove();
            $('#anythingKadouGraph3Area').append('<div style=\"height:' + heightStr + 'px;\" id=\"anythingKadouGraph3\"></div>');

            var data = "";

            if (cntData.length > 0) {
                var dataStr = "";
                dataStr += "[";
                for (i = 0; i < cntData.length; i++) {

                    var totalData = cntData[i];
                    if (i == 0) {
                        dataStr += "['";
                    } else {
                        dataStr += ",['";
                    }

                    var ankenName = totalData.name;

                    if (ankenName == "noKbunrui") {
                        ankenName = "指定なし";
                    }

                    dataStr += ankenName
                            +  "',"
                            +  totalData.sumKadouTime
                            +  "]";
                }
                dataStr += "]";
                data = eval(dataStr);
                dataFlg = true;
            }

            var resultPlot = jQuery.jqplot ('anythingKadouGraph3', [data],
              {
                seriesDefaults: {
                  renderer: jQuery.jqplot.PieRenderer,
                  rendererOptions: {
                    showDataLabels: true
                  }
                },
                legend: { show:true, location: 'e' }
              }
            );

            $("#anythingKadouGraph3").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
              var chart_left = $('#anythingKadouGraph3').offset().left,
              chart_top = $('#anythingKadouGraph3').offset().top,
              x = chart_left + 110;
              y = chart_top + 100;
              var color = 'rgb(50%,50%,100%)';
              $('#tooltipAnythingKadou3').css({left:x, top:y});
              $('#tooltipAnythingKadou3').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + data[1] + '時間');
              $('#tooltipAnythingKadou3').show();

            });

            $("#anythingKadouGraph3").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
                $('#tooltipAnythingKadou3').empty();
                $('#tooltipAnythingKadou3').hide();
            });

            if (!dataFlg) {
                $('#anythingKadouGraph3').remove();
                $('#anythingKadouGraph3Area').append('<div id=\"anythingKadouGraph3\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
            }

        } else {
            $('#anythingKadouGraph3').remove();
            $('#anythingKadouGraph3Area').append('<div id=\"anythingKadouGraph3\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}



function drawAnythingKadou3HorizonGraph() {

    var cmdStr = "getKadouKbunruiCnt";
    var dataFlg = true;

    var gyoushuSid = -1;
    var graphHeight = 100;

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "pageNum":$("input[name=anythingKadou3page]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

          if (cntData != null && cntData != "" && cntData.length > 0) {

            if ((cntData.length * 60) > 100) {
                graphHeight = cntData.length * 60;
            }

            if (cntData.length > 0 && cntData.length > Number($("input[name=anythingKadou3NowCount]").val())) {
                $('#anythingKadou3_more').removeClass("display_none");
            } else {
                $('#anythingKadou3_more').addClass("display_none");
            }

            $("input[name=anythingKadou3NowCount]").val(cntData.length);


            $('#anythingKadouGraph3').remove();
            $('#anythingKadouGraph3Area').append('<div id=\"anythingKadouGraph3\" style=\"height:' + graphHeight +  'px;\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10) {
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var kadouStr    = "[";

            for (i = cntData.length - 1; i >= 0; i--) {

                var totalData = cntData[i];

                 if (i != cntData.length - 1) {
                     kadouStr += ",";
                 }

                 var ankenName = totalData.name;

                 if (ankenName == "noKbunrui") {
                     ankenName = "指定なし";
                 }

                 kadouStr += "["
                          +  totalData.sumKadouTime
                          +  ",'"
                          +  strCut(ankenName)
                          +  "']";
            }

            kadouStr    += "]";

            var kadouObject    = eval(kadouStr);

            jQuery( function() {

                jQuery.jqplot(
                        'anythingKadouGraph3',
                        [kadouObject],

                        {
                            animate: animateFlg,
                            animateReplot: animateFlg,
                            seriesColors:['#839557', '#579575', '#c5b47f', '#eaa228', '#4bb2c5'],
                            seriesDefaults: {
                                renderer: jQuery . jqplot . BarRenderer,
                                rendererOptions: {
                                    barDirection: 'horizontal',
                                    varyBarColor: true,
                                    animation: {
                                        speed: 1500
                                    }
                                }
                            },
                            axes: {
                                xaxis: {
                                tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
                                tickOptions: {
                                  angle: -30,
                                  fontSize: '8pt',
                                  formatString:'%#.1f'
                                }
                                },
                                yaxis: {
                                    pad:0,
                                    renderer: jQuery . jqplot . CategoryAxisRenderer
                                }
                            },
                            highlighter: {
                                show: true,
                                showMarker: false,
                                tooltipLocation: 'n',
                                tooltipAxes: 'x',
                                formatString: '　%s　'
                            }
                        }
                    );



            } );
        } else {
            $('#anythingKadouGraph3').remove();
            $('#anythingKadou3_more').addClass("display_none");
            $('#anythingKadouGraph3Area').append('<div id=\"anythingKadouGraph3\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function drawAnythingKadou4Graph() {

    var cmdStr = "getKadouKhouhouCnt";
    var dataFlg = true;

    var gyoushuSid = -1;

    $("input[name=anythingKadou4NowCount]").val(0);
    $("input[name=anythingKadou4page]").val(1)
    $('#anythingKadou4_more').addClass("display_none");

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "pageNum":-1,
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "") {

            var heightStr = 300;

            if (cntData.length > 0) {
                if ((40 * cntData.length) > 300) {
                    heightStr = 40 * cntData.length;
                }
            }

            $('#anythingKadouGraph4').remove();
            $('#anythingKadouGraph4Area').append('<div style=\"height:' + heightStr + 'px;\" id=\"anythingKadouGraph4\"></div>');

            var data = "";

            if (cntData.length > 0) {
                var dataStr = "";
                dataStr += "[";
                for (i = 0; i < cntData.length; i++) {
                    var totalData = cntData[i];
                    if (i == 0) {
                        dataStr += "['";
                    } else {
                        dataStr += ",['";
                    }

                    var ankenName = totalData.name;

                    if (ankenName == "noKhouhou") {
                        ankenName = "指定なし";
                    }

                    dataStr += ankenName
                            +  "',"
                            +  totalData.sumKadouTime
                            +  "]";
                }
                dataStr += "]";
                data = eval(dataStr);
                dataFlg = true;
            }


            var resultPlot = jQuery.jqplot ('anythingKadouGraph4', [data],
              {
                seriesDefaults: {
                  renderer: jQuery.jqplot.PieRenderer,
                  rendererOptions: {
                    showDataLabels: true
                  }
                },
                legend: { show:true, location: 'e' }
              }
            );

            $("#anythingKadouGraph4").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
              var chart_left = $('#anythingKadouGraph4').offset().left,
              chart_top = $('#anythingKadouGraph4').offset().top,
              x = chart_left + 110;
              y = chart_top + 100;
              var color = 'rgb(50%,50%,100%)';
              $('#tooltipAnythingKadou4').css({left:x, top:y});
              $('#tooltipAnythingKadou4').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + data[1] + '時間');
              $('#tooltipAnythingKadou4').show();

            });

            $("#anythingKadouGraph4").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
                $('#tooltipAnythingKadou4').empty();
                $('#tooltipAnythingKadou4').hide();
            });

            if (!dataFlg) {
                $('#anythingKadouGraph4').remove();
                $('#anythingKadouGraph4Area').append('<div id=\"anythingKadouGraph4\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
            }

        } else {
            $('#anythingKadouGraph4').remove();
            $('#anythingKadouGraph4Area').append('<div id=\"anythingKadouGraph4\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function drawAnythingKadou4HorizonGraph() {

    var cmdStr = "getKadouKhouhouCnt";
    var dataFlg = true;
    var graphHeight = 100;

    var gyoushuSid = -1;

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "pageNum":$("input[name=anythingKadou4page]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

          if (cntData != null && cntData != "" && cntData.length > 0) {

            if ((cntData.length * 60) > 100) {
                graphHeight = cntData.length * 60;
            }

            if (cntData.length > 0 && cntData.length > Number($("input[name=anythingKadou4NowCount]").val())) {
                $('#anythingKadou4_more').removeClass("display_none");
            } else {
                $('#anythingKadou4_more').addClass("display_none");
            }

            $("input[name=anythingKadou4NowCount]").val(cntData.length);

            $('#anythingKadouGraph4').remove();
            $('#anythingKadouGraph4Area').append('<div id=\"anythingKadouGraph4\" style=\"height:' + graphHeight +  'px;\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10) {
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var kadouStr    = "[";



            for (i = cntData.length - 1; i >= 0; i--) {

                var totalData = cntData[i];

                 if (i != cntData.length - 1) {
                     kadouStr += ",";
                 }

                 var ankenName = totalData.name;

                 if (ankenName == "noKhouhou") {
                     ankenName = "指定なし";
                 }

                 kadouStr += "["
                          +  totalData.sumKadouTime
                          +  ",'"
                          +  strCut(ankenName)
                          +  "']";
            }


            kadouStr    += "]";

            var kadouObject    = eval(kadouStr);

            jQuery( function() {

                jQuery.jqplot(
                        'anythingKadouGraph4',
                        [kadouObject],

                        {
                            animate: animateFlg,
                            animateReplot: animateFlg,
                            seriesColors:['#839557', '#579575', '#c5b47f', '#eaa228', '#4bb2c5'],
                            seriesDefaults: {
                                renderer: jQuery . jqplot . BarRenderer,
                                rendererOptions: {
                                    barDirection: 'horizontal',
                                    varyBarColor: true,
                                    animation: {
                                        speed: 1500
                                    }
                                }
                            },
                            axes: {
                                xaxis: {
                                tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
                                tickOptions: {
                                  angle: -30,
                                  fontSize: '8pt',
                                  formatString:'%#.1f'
                                }
                                },
                                yaxis: {
                                    pad:0,
                                    renderer: jQuery . jqplot . CategoryAxisRenderer
                                }
                            },
                            highlighter: {
                                show: true,
                                showMarker: false,
                                tooltipLocation: 'e',
                                tooltipAxes: 'x',
                                formatString: '　%s　'
                            }
                        }
                    );



            } );
        } else {
            $('#anythingKadouGraph4').remove();
            $('#anythingKadou4_more').addClass("display_none");
            $('#anythingKadouGraph4Area').append('<div id=\"anythingKadouGraph4\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function drawAnythingKadouChild0Graph() {

    $('#sum_kadou_days_child').html("");
    $('#sum_kadou_time_child').html("");
    $('#sum_kadou_time_average_child').html("");

    var cmdStr = "getKadouContentData";
    var dataFlg = true;

    var gyoushuSid = -1;

    $("input[name=anythingKadouChild0NowCount]").val(0);
    $("input[name=anythingKadouChild0page]").val(1)
    $('#anythingKadouChild0_more').addClass("display_none");

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "pageNum":-1,
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

        if (cntData != null && cntData != "") {


            var heightStr = 300;

            if (cntData.length > 0) {
                if ((40 * cntData.length) > 300) {
                    heightStr = 40 * cntData.length;
                }
            }

            $('#anythingKadouChild0Graph').remove();
            $('#anythingKadouChild0GraphArea').append('<div style=\"height:' + heightStr + 'px;\" id=\"anythingKadouChild0Graph\"></div>');

            var data = "";

            if (cntData.length > 0) {

                var dataStr = "";
                dataStr += "[";
                for (i = 0; i < cntData.length; i++) {

                    var totalData = cntData[i];
                    if (i == 0) {
                        dataStr += "['";
                    } else {
                        dataStr += ",['";
                    }

                    var dataName = totalData.name;

                    dataStr += dataName
                            +  "',"
                            +  totalData.sumKadouTime
                            +  "]";
                }
                dataStr += "]";
                data = eval(dataStr);
                dataFlg = true;

                $('#sum_kadou_days_child').html(addFigure(cntData[0].totalKadouDays));
                $('#sum_kadou_time_child').html(addFigure(cntData[0].totalKadouTime));
                $('#sum_kadou_time_average_child').html((cntData[0].totalKadouTime/cntData[0].totalKadouDays).toFixed(1));
            }

            var resultPlot = jQuery.jqplot ('anythingKadouChild0Graph', [data],
              {
                seriesDefaults: {
                  renderer: jQuery.jqplot.PieRenderer,
                  rendererOptions: {
                    showDataLabels: true
                  }
                },
                legend: { show:true, location: 'e' }
              }
            );

            $("#anythingKadouChild0Graph").bind('jqplotDataHighlight', function(ev, seriesIndex, pointIndex, data, radius) {
              var chart_left = $('#anythingKadouChild0Graph').offset().left,
              chart_top = $('#anythingKadouChild0Graph').offset().top,
              x = chart_left + 110;
              y = chart_top + 100;
              var color = 'rgb(50%,50%,100%)';
              $('#tooltipAnythingKadouChild0').css({left:x, top:y});
              $('#tooltipAnythingKadouChild0').html('<span style="font-size:12px;font-weight:bold;color:#333333;">' + data[0] + '</span><br />' + data[1] + '時間');
              $('#tooltipAnythingKadouChild0').show();

            });

            $("#anythingKadouChild0Graph").bind('jqplotDataUnhighlight', function(ev, seriesIndex, pointIndex, data) {
                $('#tooltipAnythingKadouChild0').empty();
                $('#tooltipAnythingKadouChild0').hide();
            });

            if (!dataFlg) {
                $('#anythingKadouChild0Graph').remove();
                $('#anythingKadouGraphChild0Area').append('<div id=\"anythingKadouChild0Graph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
            }

        } else {
            $('#anythingKadouChild0Graph').remove();
            $('#anythingKadouChild0GraphArea').append('<div id=\"anythingKadouChild0Graph\" style=\"height:200px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}



function drawAnythingKadouChild0HorizonGraph() {

    $('#sum_kadou_days_child').html("");
    $('#sum_kadou_time_child').html("");
    $('#sum_kadou_time_average_child').html("");

    var cmdStr = "getKadouContentData";
    var dataFlg = true;

    var gyoushuSid = -1;
    var graphHeight = 100;

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":cmdStr,
                                   "CMD":cmdStr,
                                   "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                   "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                   "pageNum":$("input[name=anythingKadouChild0page]").val(),
                                   "state":$("input[name=ntp220State]").val(),
                                   "ankenState":$("input[name=ntp220AnkenState]").val(),
                                   "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                   "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                   "frdate":$("#jquery-ui-datepicker-from").val(),
                                   "todate":$("#jquery-ui-datepicker-to").val(),
                                   "gyoushuSid":gyoushuSid,
                                   "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(cntData) {

          if (cntData != null && cntData != "" && cntData.length > 0) {

            if ((cntData.length * 60) > 100) {
                graphHeight = cntData.length * 60;
            }

            if (cntData.length > 0 && cntData.length > Number($("input[name=anythingKadouChild0NowCount]").val())) {
                $('#anythingKadouChild0_more').removeClass("display_none");
            } else {
                $('#anythingKadouChild0_more').addClass("display_none");
            }

            $("input[name=anythingKadouChild0NowCount]").val(cntData.length);


            $('#anythingKadouChild0Graph').remove();
            $('#anythingKadouChild0GraphArea').append('<div id=\"anythingKadouChild0Graph\" style=\"height:' + graphHeight +  'px;\"></div>');

            //IE判定  6 7 8 9
            if($.browser.msie && $.browser.version < 10) {
                animateFlg = false;
            } else {
                animateFlg = true;
            }

            var kadouStr    = "[";

            for (i = cntData.length - 1; i >= 0; i--) {

                var totalData = cntData[i];

                 if (i != cntData.length - 1) {
                     kadouStr += ",";
                 }

                 var dataName = totalData.name;

                 kadouStr += "["
                          +  totalData.sumKadouTime
                          +  ",'"
                          +  strCut(dataName)
                          +  "']";
            }

            kadouStr    += "]";

            var kadouObject    = eval(kadouStr);

            $('#sum_kadou_days_child').html(addFigure(cntData[0].totalKadouDays));
            $('#sum_kadou_time_child').html(addFigure(cntData[0].totalKadouTime));
            $('#sum_kadou_time_average_child').html((cntData[0].totalKadouTime/cntData[0].totalKadouDays).toFixed(1));

            jQuery( function() {

                jQuery.jqplot(
                        'anythingKadouChild0Graph',
                        [kadouObject],

                        {
                            animate: animateFlg,
                            animateReplot: animateFlg,
                            seriesColors:['#839557', '#579575', '#c5b47f', '#eaa228', '#4bb2c5'],
                            seriesDefaults: {
                                renderer: jQuery . jqplot . BarRenderer,
                                rendererOptions: {
                                    barDirection: 'horizontal',
                                    varyBarColor: true,
                                    animation: {
                                        speed: 1500
                                    }
                                }
                            },
                            axes: {
                                xaxis: {
                                tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
                                tickOptions: {
                                  angle: -30,
                                  fontSize: '8pt',
                                  formatString:'%#.1f'
                                }
                                },
                                yaxis: {
                                    pad:0,
                                    renderer: jQuery . jqplot . CategoryAxisRenderer
                                }
                            },
                            highlighter: {
                                show: true,
                                showMarker: false,
                                tooltipLocation: 'n',
                                tooltipAxes: 'x',
                                formatString: '　%s　'
                            }
                        }
                    );

            } );

        } else {
            $('#anythingKadouChild0Graph').remove();
            $('#anythingKadouChild0_more').addClass("display_none");
            $('#anythingKadouChild0GraphArea').append('<div id=\"anythingKadouChild0Graph\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function showKadouDetailArea() {


    if ($("input[name=ntp220NowSelParentId]").val() == "kadou") {

        var childVal = $("input[name=ntp220NowSelChildId]").val();

        $(".category_sel_area").addClass('display_none');

        if (childVal == "kadou_0") {
            $('#anken_kadou_detail_area').removeClass("display_none");
        } else if (childVal == "kadou_1") {
            $('#kigyou_kadou_detail_area').removeClass("display_none");
        } else if (childVal == "kadou_2") {
            $('#kbunrui_kadou_detail_area').removeClass("display_none");
        } else if (childVal == "kadou_3") {
            $('#khouhou_kadou_detail_area').removeClass("display_none");
        }
    }

}

function drawKadouDetailDataArea() {

    var childVal = $("input[name=ntp220NowSelChildId]").val();

    if (childVal == "kadou_0") {
        drawKadouAnkenDataArea();
    } else if (childVal == "kadou_1") {
        drawKadouKigyouDataArea();
    } else if (childVal == "kadou_2") {
        drawKadouKbunruiDataArea();
    } else if (childVal == "kadou_3") {
        drawKadouKhouhouDataArea();
    }

}

function redrawKadouDetailDataArea() {

    var childVal = $("input[name=ntp220NowSelChildId]").val();

    if (childVal == "kadou_0") {
        $('#anken_kadou_detail_area').removeClass("display_none");
        $("input[name=ankenKadouDataPageNum]").val(1);
        $(".anken_val").remove();
        $(".anken_val2").remove();
        drawKadouAnkenDataArea();
    } else if (childVal == "kadou_1") {
        $('#kigyou_kadou_detail_area').removeClass("display_none");
        $("input[name=kigyouKadouDataPageNum]").val(1);
        $(".kigyou_val").remove();
        $(".kigyou_val2").remove();
        drawKadouKigyouDataArea();
    } else if (childVal == "kadou_2") {
        $('#kbunrui_kadou_detail_area').removeClass("display_none");
        $("input[name=kbunruiKadouDataPageNum]").val(1);
        $(".kbunrui_val").remove();
        $(".kbunrui_val2").remove();
        drawKadouKbunruiDataArea();
    } else if (childVal == "kadou_3") {
        $('#khouhou_kadou_detail_area').removeClass("display_none");
        $("input[name=khouhouKadouDataPageNum]").val(1);
        $(".khouhou_val").remove();
        $(".khouhou_val2").remove();
        drawKadouKhouhouDataArea();
    }

}


function drawKadouAnkenDataArea() {

    var gyoushuSid = -1;

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getKadouContentDetail",
                                    "CMD":"getKadouContentDetail",
                                    "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                    "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                    "pageNum":$("input[name=ankenKadouDataPageNum]").val(),
                                    "state":$("input[name=ntp220State]").val(),
                                    "ankenState":$("input[name=ntp220AnkenState]").val(),
                                    "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                    "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                    "frdate":$("#jquery-ui-datepicker-from").val(),
                                    "todate":$("#jquery-ui-datepicker-to").val(),
                                    "gyoushuSid":gyoushuSid,
                                    "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(data) {
        if (data != null && data != "" && data.length > 0) {

            var pageNum = $("input[name=ankenKadouDataPageNum]").val();
            $("input[name=ankenKadouDataPageNum]").val((Number(pageNum) + 1));

            var ankenDataStr = "";

            for (i=0; i < data.length; i++) {
                var dataMdl = data[i];
                var shohinStr = "";
                var tantoStr = "";
                var shohinStrHidden = "";


                //商品
                if (dataMdl.shohinList.length > 0) {
                    for (n=0; n < dataMdl.shohinList.length; n++) {
                      var shnMdl = dataMdl.shohinList[n];

                      if($("select[name=ntp220CatSid]").val() != "-1") {
                          if (shnMdl.nscSid == $("select[name=ntp220CatSid]").val()) {
                              shohinStr += shnMdl.nhnName
                              +  "<br>";
                          } else {
                              shohinStrHidden += shnMdl.nhnName
                              +  "<br>";
                          }
                      } else {
                          shohinStr += shnMdl.nhnName
                          +  "<br>";
                      }
                    }
                }

                //担当者
                if (dataMdl.tantoUsrInfList.length > 0) {
                    for (n=0; n < dataMdl.tantoUsrInfList.length; n++) {
                      var tantoMdl = dataMdl.tantoUsrInfList[n];
                      tantoStr += "<a href=\"javaScript:void(0);\" onClick=\"openUserInfoWindow("
                               +  tantoMdl.usrSid
                               +  ");\">"
                               +  tantoMdl.usiSei + "&nbsp;" + tantoMdl.usiMei
                               +  "</a>"
                               +  "<br>";
                    }
                }

                var trClassName = "anken_val";
                if ((((pageNum-1)*5) + i + 1) % 2 == 0) {
                    trClassName = "anken_val2";
                }

                ankenDataStr += "<tr class=\"" + trClassName + "\"><td align=\"center\">"
                             +  (((pageNum-1)*5) + i + 1)
                             +  "</td>";

                if (Number(dataMdl.nahSid) == -1 && dataMdl.nanSid != 0) {
                    ankenDataStr += "<td align=\"left\" class=\"anken_name_link\" id=\"" + dataMdl.nanSid + "\">";
                } else if (Number(dataMdl.nahSid) != -1 && dataMdl.nanSid != 0) {
                    ankenDataStr += "<td class=\"anken_name_link_his\" id=\"" + dataMdl.nahSid + "\">";
                } else {
                    ankenDataStr += "<td class=\"\" id=\"\">";
                }

                var ankenName = dataMdl.nanName;

                if (dataMdl.nanSid == 0) {
                    ankenName="指定なし";
                }

                ankenDataStr +=  ankenName
                             +  "</td><td align=\"right\" class=\"kadou_text\">";

                ankenDataStr +=  addFigure(dataMdl.kadouHours)
                             +  "</td><td align=\"right\" class=\"kadou_text\">";

                var parcentNum = (dataMdl.kadouHours/dataMdl.totalKadouHours) * 100

                ankenDataStr +=  parcentNum.toFixed(2);

                ankenDataStr += "%";

                ankenDataStr += "</td><td align=\"center\">";

                var stateStr = "商談中";
                if (dataMdl.nanSyodan == 1) {
                    stateStr = "受&nbsp;&nbsp;注";
                } else if (dataMdl.nanSyodan == 2) {
                    stateStr = "失&nbsp;&nbsp;注";
                }

                if (dataMdl.nanSid == 0) {
                    stateStr = "-";
                }

                ankenDataStr += stateStr
                             +  "</td>";


                if (dataMdl.nanSid != 0) {
                    ankenDataStr +=  "<td align=\"right\" class=\"anken_kadou_list_mitumori ";

                    if ($("input[name=ankenKadouListMoney]").val() != 0) {
                        ankenDataStr += "display_none";
                    }

                    ankenDataStr +=  "\">"
                                 +  "<span style=\"\" class=\"tooltips\"><span class=\"tooltip_txt2\">"
                                 +  "提出日:" + dataMdl.nanMitumoriDateStr;

                    if (dataMdl.nanMitumoriDateKbn == 1) {
                        ankenDataStr +=  "<br><span class=\"out_of_msg\">(見積もり日は指定期間の範囲外です。)</span>";
                    }

                    ankenDataStr +=  "</span></span>"
                                 +  "<div class=\"mitumori_kin_str\">" + addFigure(dataMdl.nanKinMitumori) + "</div>"
                                 +  "</td><td align=\"right\" class=\"anken_kadou_list_jutyu ";

                    if ($("input[name=ankenKadouListMoney]").val() == 0) {
                        ankenDataStr += "display_none";
                    }

                    ankenDataStr +=  "\">"
                                 +  "<span style=\"display:none;\" class=\"tooltips\"><span class=\"tooltip_txt2\">"
                                 +  "受注日:" + dataMdl.nanJutyuDateStr;

                                 if (dataMdl.nanJutyuDateKbn == 1) {
                                     ankenDataStr +=  "<br><span class=\"out_of_msg\">(受注日は指定期間の範囲外です。)</span>";
                                 }

                   ankenDataStr  +=  "</span></span>"
                                 +  "<div class=\"jutyu_kin_str\">" + addFigure(dataMdl.nanKinJutyu) + "</div>"
                                 +  "</td>";
                } else {
                    ankenDataStr += "<td align=\"center\">-</td>";
                }

                ankenDataStr += "<td align=\"left\" class=\"anken_kadou_list_other anken_kadou_list_kigyou ";
                if ($("input[name=ankenKadouListOther]").val() != 0) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  dataMdl.cntName1;
                if (dataMdl.cntName2 != null && dataMdl.cntName2 != "") {
                    ankenDataStr += "<br>" + dataMdl.cntName2;
                }
                ankenDataStr += "</td>";


                //商品
                ankenDataStr += "<td align=\"left\" class=\"anken_kadou_list_other anken_kadou_list_shohin ";
                if ($("input[name=ankenKadouListOther]").val() != 1) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  shohinStr;

                if (shohinStrHidden != "") {
                    ankenDataStr += "<div class=\"other_category_itm\">▼他のカテゴリの商品</div>";
                    ankenDataStr +=  "<div class=\"display_none\">" + shohinStrHidden + "</div>";
                }

                ankenDataStr += "</td>";

                //業種
                ankenDataStr += "<td align=\"left\" class=\"anken_kadou_list_other anken_kadou_list_gyoushu ";
                if ($("input[name=ankenKadouListOther]").val() != 2) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  dataMdl.ngyName;
                ankenDataStr += "</td>";

                //プロセス
                ankenDataStr += "<td align=\"left\" class=\"anken_kadou_list_other anken_kadou_list_process ";
                if ($("input[name=ankenKadouListOther]").val() != 3) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  dataMdl.ngpName;
                ankenDataStr += "</td>";

                //見込み度
                ankenDataStr += "<td align=\"center\" class=\"anken_kadou_list_other anken_kadou_list_mikomido ";
                if ($("input[name=ankenKadouListOther]").val() != 4) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  dataMdl.nanMikomiVal;
                ankenDataStr += "</td>";

                //顧客源泉
                ankenDataStr += "<td align=\"left\" class=\"anken_kadou_list_other anken_kadou_list_kokyakugensen ";
                if ($("input[name=ankenKadouListOther]").val() != 5) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  dataMdl.ncnName;
                ankenDataStr += "</td>";

                //担当者
                ankenDataStr += "<td align=\"left\" class=\"anken_kadou_list_other anken_kadou_list_tanto ";
                if ($("input[name=ankenKadouListOther]").val() != 6) {
                    ankenDataStr += "display_none";
                }
                ankenDataStr +=  "\">"
                             +  tantoStr;
                ankenDataStr += "</td>";

                ankenDataStr += "</tr>";
            }

            if (i >= 5) {
                $(".anken_kadou_list_more_area").removeClass("display_none");
            } else {
                $(".anken_kadou_list_more_area").addClass("display_none");
            }

            if (pageNum != 1) {
                $('#anken_kadou_val_table').append(ankenDataStr);
            } else {
                $('#anken_kadou_val_title').after(ankenDataStr);
            }

        } else {
            $(".anken_kadou_list_more_area").addClass("display_none");
            $('#ankenKadouDataArea').append('<div id=\"\" style=\"height:350px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function drawKadouKigyouDataArea() {

    var gyoushuSid = -1;

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getKadouContentDetail",
                                    "CMD":"getKadouContentDetail",
                                    "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                    "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                    "pageNum":$("input[name=kigyouKadouDataPageNum]").val(),
                                    "state":$("input[name=ntp220State]").val(),
                                    "ankenState":$("input[name=ntp220AnkenState]").val(),
                                    "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                    "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                    "frdate":$("#jquery-ui-datepicker-from").val(),
                                    "todate":$("#jquery-ui-datepicker-to").val(),
                                    "gyoushuSid":gyoushuSid,
                                    "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(data) {
        if (data != null && data != "" && data.length > 0) {

            var pageNum = $("input[name=kigyouKadouDataPageNum]").val();
            $("input[name=kigyouKadouDataPageNum]").val((Number(pageNum) + 1));

            var kigyouDataStr = "";

            for (i=0; i < data.length; i++) {
                var dataMdl = data[i];

                var trClassName = "kigyou_val";
                if ((((pageNum-1)*5) + i + 1) % 2 == 0) {
                    trClassName = "kigyou_val2";
                }

                kigyouDataStr += "<tr class=\"" + trClassName + "\"><td align=\"center\">"
                             +  (((pageNum-1)*5) + i + 1)
                             +  "</td>";


                var kigyouSid  = dataMdl.sid;
                var kigyouName = dataMdl.name;

                if (kigyouSid != 0) {
                    kigyouDataStr += "<td><span class=\"sc_link_7 comp_click\" id=\""
                                  + kigyouSid
                                  + "\">"
                                  + kigyouName
                                  + "</span>"
                } else {
                    kigyouDataStr += "<td>指定なし";
                }


                kigyouDataStr += "</td><td class=\"kadou_text\" style=\"padding-right:20px;\" align=\"right\">";

                kigyouDataStr +=  addFigure(dataMdl.sumKadouTime)
                             +  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";


                var parcentNum = (dataMdl.sumKadouTime/dataMdl.totalKadouTime) * 100

                kigyouDataStr +=  "<td class=\"kadou_text\" align=\"right\">"

                kigyouDataStr +=  parcentNum.toFixed(2);

                kigyouDataStr += "%&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
            }

            if (i >= 5) {
                $(".kigyou_kadou_list_more_area").removeClass("display_none");
            } else {
                $(".kigyou_kadou_list_more_area").addClass("display_none");
            }

            if (pageNum != 1) {
                $('#kigyou_kadou_val_table').append(kigyouDataStr);
            } else {
                $('#kigyou_kadou_val_title').after(kigyouDataStr);
            }

        } else {
            $(".kigyou_kadou_list_more_area").addClass("display_none");
            $('#kigyouKadouDataArea').append('<div id=\"\" style=\"height:350px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function drawKadouKbunruiDataArea() {

    var gyoushuSid = -1;

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getKadouContentDetail",
                                    "CMD":"getKadouContentDetail",
                                    "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                    "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                    "pageNum":$("input[name=kbunruiKadouDataPageNum]").val(),
                                    "state":$("input[name=ntp220State]").val(),
                                    "ankenState":$("input[name=ntp220AnkenState]").val(),
                                    "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                    "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                    "frdate":$("#jquery-ui-datepicker-from").val(),
                                    "todate":$("#jquery-ui-datepicker-to").val(),
                                    "gyoushuSid":gyoushuSid,
                                    "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(data) {
        if (data != null && data != "" && data.length > 0) {

            var pageNum = $("input[name=kbunruiKadouDataPageNum]").val();
            $("input[name=kbunruiKadouDataPageNum]").val((Number(pageNum) + 1));

            var kbunruiDataStr = "";

            for (i=0; i < data.length; i++) {
                var dataMdl = data[i];

                var trClassName = "kbunrui_val";
                if ((((pageNum-1)*5) + i + 1) % 2 == 0) {
                    trClassName = "kbunrui_val2";
                }

                kbunruiDataStr += "<tr class=\"" + trClassName + "\"><td align=\"center\">"
                             +  (((pageNum-1)*5) + i + 1)
                             +  "</td>";

                if (Number(dataMdl.nahSid) == -1 && dataMdl.nanSid != 0) {
                    kbunruiDataStr += "<td align=\"left\" class=\"kbunrui_name_link\" id=\"" + dataMdl.nanSid + "\">";
                } else {
                    kbunruiDataStr += "<td class=\"\" id=\"\">";
                }

                var kbunruiName = dataMdl.name;

                if (dataMdl.name == "") {
                    kbunruiName="指定なし";
                }

                kbunruiDataStr +=  kbunruiName
                             +  "</td><td class=\"kadou_text\" style=\"padding-right:20px;\" align=\"right\">";

                kbunruiDataStr +=  addFigure(dataMdl.sumKadouTime)
                             +  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";

                var parcentNum = (dataMdl.sumKadouTime/dataMdl.totalKadouTime) * 100

                kbunruiDataStr +=  "<td class=\"kadou_text\" align=\"right\">"

                kbunruiDataStr +=  parcentNum.toFixed(2);

                kbunruiDataStr += "%&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
            }

            if (i >= 5) {
                $(".kbunrui_kadou_list_more_area").removeClass("display_none");
            } else {
                $(".kbunrui_kadou_list_more_area").addClass("display_none");
            }

            if (pageNum != 1) {
                $('#kbunrui_kadou_val_table').append(kbunruiDataStr);
            } else {
                $('#kbunrui_kadou_val_title').after(kbunruiDataStr);
            }

        } else {
            $(".kbunrui_kadou_list_more_area").addClass("display_none");
            $('#kbunruiKadouDataArea').append('<div id=\"\" style=\"height:350px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}


function drawKadouKhouhouDataArea() {

    var gyoushuSid = -1;

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp220.do', {"cmd":"getKadouContentDetail",
                                    "CMD":"getKadouContentDetail",
                                    "contentName":$("input[name=ntp220NowSelParentId]").val(),
                                    "childVal":$("input[name=ntp220NowSelChildId]").val(),
                                    "pageNum":$("input[name=khouhouKadouDataPageNum]").val(),
                                    "state":$("input[name=ntp220State]").val(),
                                    "ankenState":$("input[name=ntp220AnkenState]").val(),
                                    "selGrpSid":$("select[name=ntp220GroupSid]").val(),
                                    "selUsrSid":$("select[name=ntp220SelectUsrSid]").val(),
                                    "frdate":$("#jquery-ui-datepicker-from").val(),
                                    "todate":$("#jquery-ui-datepicker-to").val(),
                                    "gyoushuSid":gyoushuSid,
                                    "shohinCategory":$("select[name=ntp220CatSid]").val()},
      function(data) {
        if (data != null && data != "" && data.length > 0) {

            var pageNum = $("input[name=khouhouKadouDataPageNum]").val();
            $("input[name=khouhouKadouDataPageNum]").val((Number(pageNum) + 1));

            var khouhouDataStr = "";

            for (i=0; i < data.length; i++) {
                var dataMdl = data[i];

                var trClassName = "khouhou_val";
                if ((((pageNum-1)*5) + i + 1) % 2 == 0) {
                    trClassName = "khouhou_val2";
                }

                khouhouDataStr += "<tr class=\"" + trClassName + "\"><td align=\"center\">"
                             +  (((pageNum-1)*5) + i + 1)
                             +  "</td>";

                if (Number(dataMdl.nahSid) == -1 && dataMdl.nanSid != 0) {
                    khouhouDataStr += "<td align=\"left\" class=\"khouhou_name_link\" id=\"" + dataMdl.nanSid + "\">";
                } else {
                    khouhouDataStr += "<td class=\"\" id=\"\">";
                }

                var khouhouName = dataMdl.name;

                if (dataMdl.name == "") {
                    khouhouName="指定なし";
                }

                khouhouDataStr +=  khouhouName
                             +  "</td><td class=\"kadou_text\" align=\"right\">";

                khouhouDataStr +=  addFigure(dataMdl.sumKadouTime)
                             +  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";

                var parcentNum = (dataMdl.sumKadouTime/dataMdl.totalKadouTime) * 100

                khouhouDataStr +=  "<td class=\"kadou_text\" align=\"right\">"

                khouhouDataStr +=  parcentNum.toFixed(2);

                khouhouDataStr += "%&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
            }


            if (i >= 5) {
                $(".khouhou_kadou_list_more_area").removeClass("display_none");
            } else {
                $(".khouhou_kadou_list_more_area").addClass("display_none");
            }

            if (pageNum != 1) {
                $('#khouhou_kadou_val_table').append(khouhouDataStr);
            } else {
                $('#khouhou_kadou_val_title').after(khouhouDataStr);
            }

        } else {
            $(".khouhou_kadou_list_more_area").addClass("display_none");
            $('#khouhouKadouDataArea').append('<div id=\"\" style=\"height:350px;\"><span style="font-size:12px;color:#ff0000;padding-left:12px;">該当するデータがありません。</span></div>');
        }
    });
}







//グループ変更
function changeShukeiGroupCombo() {
    $("input[name=ntp220State]").val("-1");
    $("input[name=ntp220AnkenState]").val("-1");
    $("input[name=ntp220NowSelParentId]").val("main");
    $("input[name=ntp220NowSelChildId]").val("");
    document.forms[0].submit();
}
function changeKojinGroupCombo() {
    $("input[name=ntp220State]").val("-1");
    $("input[name=ntp220AnkenState]").val("-1");
    $("input[name=ntp220NowSelParentId]").val("main");
    $("input[name=ntp220NowSelChildId]").val("");
    document.forms[0].submit();
}

//ユーザ変更
function changeUsrCmb() {
    $("input[name=ntp220State]").val("-1");
    $("input[name=ntp220AnkenState]").val("-1");
    $("input[name=ntp220NowSelParentId]").val("main");
    $("input[name=ntp220NowSelChildId]").val("");
    document.forms[0].submit();
}

//商品カテゴリ変更
function changeCategoryCombo() {
    $("input[name=ntp220State]").val("-1");
    $("input[name=ntp220AnkenState]").val("-1");
    $("input[name=ntp220NowSelParentId]").val("main");
    $("input[name=ntp220NowSelChildId]").val("");
    document.forms[0].submit();
}


//8文字区切りにする
function strCut(cutStr) {

    var cutTime = cutStr.length/8;
    var newStr = "";
    for (n=0; n < cutTime; n++) {
      newStr += cutStr.substring(n*8, (n*8)+8);
      newStr += "<br>";
    }

    newStr += cutStr.substring(n*8);

    return newStr;
}

//3桁区切り
function addFigure(str) {
    var num = new String(str).replace(/,/g, "");
    while(num != (num = num.replace(/^(-?\d+)(\d{3})/, "$1,$2")));
    return num;
}
