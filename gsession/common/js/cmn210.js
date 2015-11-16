function unload(){
    document.forms[0].CMD.value = 'formUnload';
    document.forms[0].formUnload.value='true';
    document.forms[0].submit();
    return false;
}