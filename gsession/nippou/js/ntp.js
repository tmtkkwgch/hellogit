$(function() {

    $('.menu_not_select_tr').hover(

        function () {
            $(this).removeClass("menu_not_select_tr").addClass("menu_not_select_tr_hover");
          },
          function () {
              $(this).removeClass("menu_not_select_tr_hover").addClass("menu_not_select_tr");
          }
    );

    $('.ntp_chk').hover(

            function () {
                $(this).removeClass("ntp_chk").addClass("ntp_chk_hover");
              },
            function () {
                $(this).removeClass("ntp_chk_hover").addClass("ntp_chk");
            }
     );

    $('.ntp_chk2').hover(

            function () {
                $(this).removeClass("ntp_chk2").addClass("ntp_chk2_hover");
              },
            function () {
                $(this).removeClass("ntp_chk2_hover").addClass("ntp_chk2");
            }
     );

    /* 案件履歴 hover */
    $('.ankenHistoryArea').hover(
        function () {
            $(this).removeClass("ankenHistoryArea").addClass("ankenHistoryArea_hover");
        },
        function () {
            $(this).removeClass("ankenHistoryArea_hover").addClass("ankenHistoryArea");
        }
    );

    /* 企業・顧客履歴 hover */
    $('.companyHistoryArea').hover(
        function () {
            $(this).removeClass("companyHistoryArea").addClass("companyHistoryArea_hover");
        },
        function () {
            $(this).removeClass("companyHistoryArea_hover").addClass("companyHistoryArea");
        }
    );

    /* 案件履歴選択 */
    $(".ankenAreaSel").live("click", function(){
        var ankenSid = $(this).attr('id');
        document.forms[0].cmd.value='add';
        document.forms[0].CMD.value='add';
        document.forms[0].ntp010HistoryAnkenSid.value=ankenSid;
        document.forms[0].ntp010SelectUsrSid.value=$('input:hidden[name=ntp010SessionUsrId]').val();
        document.forms[0].ntp010SelectDate.value=$('input:hidden[name=ntp010DspDate]').val();
        document.forms[0].submit();
        return false;
    });

    /* 企業・顧客履歴選択 */
    $(".companyAreaSel").live("click", function(){
        var companySid = $(this).attr('id');
        var companyBaseSid = $(this).children().attr('id');
        document.forms[0].cmd.value='add';
        document.forms[0].CMD.value='add';
        document.forms[0].ntp010HistoryCompSid.value=companySid;
        document.forms[0].ntp010HistoryCompBaseSid.value=companyBaseSid;
        document.forms[0].ntp010SelectUsrSid.value=$('input:hidden[name=ntp010SessionUsrId]').val();
        document.forms[0].ntp010SelectDate.value=$('input:hidden[name=ntp010DspDate]').val();
        document.forms[0].submit();
        return false;
    });

});
