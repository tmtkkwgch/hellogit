(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;

    var loader = new YAHOO.util.YUILoader({
        base: '../common/js/yui/',
        require: ['button'],
        ignore: ['containercore'],
        onSuccess: function() {
            var b1 = new YAHOO.widget.Button({
                label: msglist.checkNewMail,
                id: 'checkButton',
                container: Dom.get('check_buttons')
            });

            var icon = document.createElement('span');
            icon.className = 'icon';
            b1.appendChild(icon);
            b1.on('click', function() {
                var t = YAHOO.example.app.tabView.get('tabs');
                for (var i = 0; i < t.length; i++) {
                    if (t[i].get('id') == 'inboxView') {
                        YAHOO.example.app.tabView.set('activeTab', t[i]);
                    }
                }

                showLoadingDialog();
                buttonPush('getNewMail');
            });

            Event.on('query', 'click', function() {
                this.value = '';
            });
            Event.on('query', 'blur', function() {
                if (this.value === '') {
                    this.value = 'Search the Web..';
                }
            });
        }
    });

    loader.insert({}, 'js');
})();
