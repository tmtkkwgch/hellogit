function rsvSchChange() {
    var viewgroup=document.all && document.all('rsvSchGroup') || document.getElementById && document.getElementById('rsvSchGroup');
    var viewuser=document.all && document.all('rsvSchUser') || document.getElementById && document.getElementById('rsvSchUser');

    if ($('#rsvSchKbn0')[0].checked) {
        viewgroup.style.display="none";
        viewuser.style.display="block";

        $('#rsvSchKbn0')[0].checked = true;

    } else if ($('#rsvSchKbn1')[0].checked) {
        viewgroup.style.display="block";
        viewuser.style.display="none";

        $('#rsvSchKbn0').checked = true;
    }
}


function rsvSchDisabled() {

    if ($('#refOk')[0].checked) {

        $('#rsvSchKbn0')[0].disabled = false;
        $('#rsvSchKbn0')[0].style.color = '#000066';
        $('#rsvSchKbn1')[0].disabled = false;
        $('#rsvSchKbn1')[0].style.color = '#000066';

        $('#rsvSchGrpSid')[0].disabled = false;
        $('#rsvSchGrpSid')[0].style.color = '#000000';

        $('#rsvSchGrpLabel')[0].disabled = false;
        $('#rsvSchGrpLabel')[0].style.color = '#000000';

        $('#select_user')[0].disabled = false;
        $('#select_user')[0].style.color = '#000066';
        $('#rsvSchUsers_r')[0].disabled = false;
        $('#rsvSchUsers_r')[0].style.color = '#000000';
        $('#rsvSchUsers_l')[0].disabled = false;
        $('#rsvSchUsers_l')[0].style.color = '#000000';

        $('#rsvSchBtn')[0].disabled = false;
        $('#rsvSchBtn')[0].style.color = '#000066';

        $('#rsvSchGrpBtn1')[0].disabled = false;
        $('#rsvSchGrpBtn2')[0].disabled = false;

    } else if ($('#refNo')[0].checked) {

        $('#rsvSchKbn0')[0].disabled = true;
        $('#rsvSchKbn0')[0].style.color = '#e0e0e0';
        $('#rsvSchKbn1')[0].disabled = true;
        $('#rsvSchKbn1')[0].style.color = '#e0e0e0';

        $('#rsvSchGrpSid')[0].disabled = true;
        $('#rsvSchGrpSid')[0].style.color = '#e0e0e0';

        $('#rsvSchGrpLabel')[0].disabled = true;
        $('#rsvSchGrpLabel')[0].style.color = '#e0e0e0';

        $('#select_user')[0].disabled = true;
        $('#select_user')[0].style.color = '#e0e0e0';
        $('#rsvSchUsers_r')[0].disabled = true;
        $('#rsvSchUsers_r')[0].style.color = '#e0e0e0';
        $('#rsvSchUsers_l')[0].disabled = true;
        $('#rsvSchUsers_l')[0].style.color = '#e0e0e0';

        $('#rsvSchBtn')[0].disabled = true;
        $('#rsvSchBtn')[0].style.color = '#e0e0e0';

        $('#rsvSchGrpBtn1')[0].disabled = true;
        $('#rsvSchGrpBtn2')[0].disabled = true;

    }

}
