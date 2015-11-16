/**
 * Change Style
 */
function changeStyle(obj, clsname) {
    ver=navigator.appVersion.toLowerCase();
    if (ver.indexOf('msie 9')>=1 || ver.indexOf('msie 8')>=1) {
        obj.setAttribute('className', clsname);
    } else {
        obj.setAttribute('class', clsname);
    }
}