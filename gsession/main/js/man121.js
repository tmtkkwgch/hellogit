function editClick(pluginid) {
    document.forms['man121Form'].man120pluginId.value = pluginid;
    document.forms['man121Form'].CMD.value = 'man121edit';
    document.forms['man121Form'].man280backId.value = 'man121';
    document.forms['man121Form'].submit();
    return false;
}

function defaultImg(imgName) {
    document.getElementById(imgName).src="../common/images/spacer.gif";
}