var Fader = function( id, wait ) {
    this.id = id;
    this.elm = document.getElementById(id);
    this.wait = wait;
    this.tid = null;

    this.init();
};

Fader.prototype = {
    fadeOut: new YAHOO.util.Anim( this.id, {
      opacity: { to: 0.01 }
    }, 1, YAHOO.util.Easing.easeOut),

    init: function() {
        var faderObj = this; 
        this.fadeOut.onComplete.subscribe(function(){
            faderObj.elm.className = "owAddressMsgArea_hide";
            faderObj.elm.innerHTML = '';
        });
        this.setFadeout();
    },

    setFadeout : function () {
        var faderObj = this; 
        this.tid = setTimeout(function(){
            faderObj.fadeOut.animate();
        }, this.wait );
    }
};
