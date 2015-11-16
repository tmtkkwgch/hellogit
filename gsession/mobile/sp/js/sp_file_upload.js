var fileObjName = "";

$(function() {

    /* 添付ボタン */
    $("#tmp_button").live("click", function(e){
        var y = e.pageY;
        tmpPopupMenu(y);
    });

    /* 添付ファイル削除 */
    $(".del_file_div").live("click", function(){
        var delFileName = $(this).attr('id');
        delTmpFile(delFileName);
    });

    fileObjName = $("#tmp_file_obj_name").val();


    if ($("#file_use_check")[0].disabled) {
        $("#tmp_button_area").css("display","none");
    }

});

function tmpPopupMenu(y){
    if($('#tmp_pop')[0].style.display == 'none'){
      $('#tmp_pop')[0].style.display="block";
      if (y != 0) {
         var popTop = y - 150;
         $('.tmppopupmenu').css("top",popTop + "px");
      }
    } else {
      $('#tmp_pop')[0].style.display="none";
      redrawPop();
    }
}

function updateProgress(evt) {
    var percentComplete = evt.loaded / evt.total;
}

function performAjaxSubmit() {

         if (document.getElementById(fileObjName).files.length > 0) {
             $("#error_msg_area").html("");
             $.each(
                     document.getElementById(fileObjName).files,
                     function(i,e){
                         var fd = new FormData();
                         fd.append('CMD', 'fileUpLoad');
                         fd.append('cmd', 'fileUpLoad');
                         fd.append(fileObjName + '[' + i + ']', document.getElementById(fileObjName).files[i]);
                         $.ajax({
                             async: true,
                             xhr : function(){
                                             $("#uploadBtn").css("display","none");
                                             $("#progress_bar_wrapper").css("display","block");
                                             $("#progress_bar").width("0%");
                                             $("#progress_bar").height("30px");
                                             $("#progress_text").html("0%");
                                             XHR = $.ajaxSettings.xhr();
                                             if(XHR.upload){
                               XHR.upload.addEventListener('progress',function(e){
                                     progre = parseInt(e.loaded/e.total*10000)/100 ;
                                     console.log(progre+"%") ;
                                     $("#progress_bar").width(parseInt(progre)+"%");
                                     $("#progress_text").html(progre+"%");
                                                                                       }, false);
                                      }
                     return XHR;
                     },
                             url:  $('form').attr('action'),
                             type: "post",
                             data:fd,
                             contentType: false,
                             processData: false
                         }).done(function( data ) {

                             try {
                                 data = JSON.parse(data);
                                 if (data[0].error == 1) {
                                     addPopError(data[0].errorMessage);
                                 } else {
                                     $("#tmp_file_area").append("<div style=\"width:100%;\" id=\"file_"
                                                                + data[0].saveFileName
                                                                + "\"><div class=\"del_file_txt\">"
                                                                + data[0].fileName
                                                                + "(" + data[0].fileSize
                                                                + ")</div><div id=\""
                                                                + data[0].saveFileName
                                                                + "\" class=\"del_file_div\">&nbsp;&nbsp;</div></div><div style=\"clear:both;padding-top:10px;\"></div>");
                                     tmpPopupMenu(0);
                                     redrawPop();
                                 }
                             } catch (ae) {
                                 addPopError('添付に失敗しました。');
                             }

                         }).fail(function(data){
                             addPopError('添付に失敗しました。');
                         });
                     }
                 )
         } else {
           addPopError('ファイルを選択して下さい。');
         }
}

function updateProgress(evt) {
  if (evt.lengthComputable) {
      var percentComplete = evt.loaded / evt.total;

      var req = new XMLHttpRequest();

      req.addEventListener("progress", updateProgress, false);
      req.addEventListener("load", transferComplete, false);
      req.addEventListener("error", transferFailed, false);
      req.addEventListener("abort", transferCanceled, false);

      req.open();

  } else {

      var req = new XMLHttpRequest();

      req.addEventListener("progress", updateProgress, false);
      req.addEventListener("load", transferComplete, false);
      req.addEventListener("error", transferFailed, false);
      req.addEventListener("abort", transferCanceled, false);

      req.open();

  }
}

function transferComplete(evt) {
  alert("The transfer is complete.");
}

function transferFailed(evt) {
  alert("An error occurred while transferring the file.");
}

function transferCanceled(evt) {
  alert("The transfer has been canceled by the user.");
}

function redrawPop() {
    $("#uploadBtn").css("display","block");
    $("#progress_bar_wrapper").css("display","none");
    $("#" + fileObjName).remove();
    $("#fileUpArea").append("<input type=\"file\" id=\"" + fileObjName + "\" name=\"" + fileObjName + "\" data-clear-btn=\"true\" />");
    $("#progress_text").html("");
    $("#error_msg_area").html("");
}

function addPopError(errorStr) {
    $("#error_msg_area").html(errorStr);
    $("#progress_bar_wrapper").css("display","none");
    $("#progress_text").html("");
    $("#uploadBtn").css("display","block");
}

function delTmpFile(delFileName) {
    $("#file_" + delFileName).remove();
    $.ajaxSetup({async:true});
    $.post($('form').attr('action'), {"cmd":"fileDelete",
                                      "CMD":"fileDelete",
                                      "delFileName":delFileName},
                                      function(data) {});
}
