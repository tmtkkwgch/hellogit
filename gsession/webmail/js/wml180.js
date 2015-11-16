function wml180ChangeDelKbn() {

    var delKbn = 0;
    for (i = 0; i < document.getElementsByName('wml180delKbn').length; i++) {
        if (document.getElementsByName('wml180delKbn')[i].checked) {
            delKbn = document.getElementsByName('wml180delKbn')[i].value;
        }
    }

    if (delKbn == 1) {
        $('#dateElement').hide();
        $('#dateAreaElement').show();
    } else if (delKbn == 2) {
        $('#dateElement').hide();
        $('#dateAreaElement').hide();
    } else {
        $('#dateElement').show();
        $('#dateAreaElement').hide();
    }
}
