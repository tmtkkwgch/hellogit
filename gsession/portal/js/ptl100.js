function changeContentKn(contentKbn) {
    var checkValue = document.getElementsByName('ptl100contentType');

    if (contentKbn == 1) {
        $('#contentArea').hide();
        $('#contentAreaTitle').hide();
        $('#forcusDsp').hide();
        $('#contentSrcArea').show();
        $('#forcusHTML').show();

    } else {
        $('#contentArea').show();
        $('#contentAreaTitle').show();
        $('#forcusDsp').show();
        $('#contentSrcArea').hide();
        $('#forcusHTML').hide();
    }
}

function openPortletImagePopup(ptlCmdMode, imgId) {
    return openPtlImagePopup(ptlCmdMode,
                            document.forms[0].ptlPortletSid.value,
                            imgId);
}

function closePortletImagePopup() {
    return windowClose();
}

function deleteImage(imgId) {
    if (confirm('画像を削除します。よろしいですか?')) {
        document.forms[0].pltPortletImageSid.value=imgId;
        document.forms[0].CMD.value='deleteImage';
        document.forms[0].submit();
    }
    return false;
}

function selectPortletImage(imgId) {

    var content = '<img src="../pltimage/ptl990.do?ptlPortletSid=' + document.forms[0].ptlPortletSid.value
    + '&imgId=' + imgId
    + '" />'

    document.forms['ptl100Form'].ptl100contentPlusImage.value = content;
    document.forms['ptl100Form'].submit();
    return false;
}