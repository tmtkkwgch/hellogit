function bbsPtl010changePage(id, formId){
    var frm = document.getElementById(formId);

    var url = '../bulletin/bbsptl010.do';
    var pars = '';
    pars= '?CMD=init';
    pars= pars  + '&bbsPtlBfiSid=' + frm.bbsPtlBfiSid.value;
    pars= pars  + '&bbsPtl010ItemId=' + frm.bbsPtl010ItemId.value;
    pars= pars  + '&bbsPtl010page1=' + frm.bbsPtl010page1.value;
    url = url + pars;
    $('#bulletin_' + frm.bbsPtl010ItemId.value).load(url);

    return false;
}

function bbsPtl010buttonPush(cmd, formId){

    var frm = document.getElementById(formId);

    var url = '../bulletin/bbsptl010.do';
    var pars = '';
    pars= '?CMD=' + cmd;
    pars= pars  + '&bbsPtlBfiSid=' + frm.bbsPtlBfiSid.value;
    pars= pars  + '&bbsPtl010ItemId=' + frm.bbsPtl010ItemId.value;
    pars= pars  + '&bbsPtl010page1=' + frm.bbsPtl010page1.value;
    url = url + pars;
    $('#bulletin_' + frm.bbsPtl010ItemId.value).load(url);

    return false;
}
