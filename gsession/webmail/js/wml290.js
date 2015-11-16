function wml290SelectDestlist(destId) {
    document.forms['wml290Form'].wmlEditDestList.value = destId;
    document.forms['wml290Form'].wml290initFlg.value = 0;
    buttonPush('init');
}

function setWml290WebmailData() {
    //ユーザ情報
    var addressAtsk = document.getElementsByName('wml290Atsk');
    var addressCc = document.getElementsByName('wml290Cc');
    var addressBcc = document.getElementsByName('wml290Bcc');
    setWebmailAddress2(addressAtsk, addressCc, addressBcc, 'wml290SetMailMsg');
}
