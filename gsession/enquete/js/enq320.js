function enq320viewAnswer(userSid) {
    document.forms[0].enq110answer.value = userSid;
    return buttonPush('enq320detail');
}

function enqClickTitle(sortKey, order) {
    document.forms[0].CMD.value='init';
    document.forms[0].enq320sortKey.value=sortKey;
    document.forms[0].enq320order.value=order;
    document.forms[0].enq320scrollQuestonFlg.value = '1';
    document.forms[0].submit();
    return false;
}

function enq320changePage(id){
    if (id == 0) {
        document.forms[0].enq320pageBottom.value=document.forms[0].enq320pageTop.value;
    } else {
        document.forms[0].enq320pageTop.value=document.forms[0].enq320pageBottom.value;
    }

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}

jQuery( function() {

    if (document.forms[0].enq320scrollQuestonFlg.value == '1') {
        window.location.hash='enq320ansListArea';
        document.forms[0].enq320scrollQuestonFlg.value = '';
    }
});
