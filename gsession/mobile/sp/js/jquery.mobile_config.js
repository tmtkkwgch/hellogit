/* ajax link false */

$(document).bind("mobileinit", function(){
    $.mobile.ajaxEnabled = false;
    $.mobile.defaultPageTransition = 'none';
    $.mobile.pushStateEnabled = false;
});
