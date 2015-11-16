function MoveToRootFolderListForPtl030(cabSid, dirSid) {
    document.forms['filptl030Form'].backDsp.value='main';
    document.forms['filptl030Form'].fil010SelectCabinet.value=cabSid;
    document.forms['filptl030Form'].fil010SelectDirSid.value=dirSid;
    document.forms['filptl030Form'].CMD.value='selectCabinet';
    document.forms['filptl030Form'].submit();
    return false;
}