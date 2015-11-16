function loginChangeSearchMode(mode) {
    var webObj = document.getElementById('loginSearchWeb');
    var imgObj = document.getElementById('loginSearchImage');
    var mapObj = document.getElementById('loginSearchMap');

    if (mode == 1) {
        changeStyle(webObj, 'web mode_font_not_select');
        changeStyle(imgObj, 'image mode_font_select');
        changeStyle(mapObj, 'map mode_font_not_select');
    } else if (mode == 2) {
        changeStyle(webObj, 'web mode_font_not_select');
        changeStyle(imgObj, 'image mode_font_not_select');
        changeStyle(mapObj, 'map mode_font_select');
    } else {
        changeStyle(webObj, 'web mode_font_select');
        changeStyle(imgObj, 'image mode_font_not_select');
        changeStyle(mapObj, 'map mode_font_not_select');
    }

    document.getElementsByName('loginSearchMode')[0].value = mode;
}

function changeBackColor(name, className) {
    changeStyle(document.getElementsByName(name)[0], className);
}
