/* Japanese initialisation for the jQuery UI date picker plugin. */

jQuery(function($){
        $.datepicker.regional['ja'] = {
                closeText: '閉じる',
                prevText: '&#x3c;前',
                nextText: '次&#x3e;',
                currentText: '今日',
                monthNames: ['年 1月','年 2月','年 3月','年 4月','年 5月','年 6月',
                '年 7月','年 8月','年 9月','年 10月','年 11月','年 12月'],
                monthNamesShort: ['年 1月','年 2月','年 3月','年 4月','年 5月','年 6月',
                '年 7月','年 8月','年 9月','年 10月','年 11月','年 12月'],
                dayNames: ['日曜日','月曜日','火曜日','水曜日','木曜日','金曜日','土曜日'],
                dayNamesShort: ['日','月','火','水','木','金','土'],
                dayNamesMin: ['日','月','火','水','木','金','土'],
                dateFormat: 'yy年mm月dd日', firstDay: 0,
                isRTL: false,
                showMonthAfterYear: true};
        $.datepicker.setDefaults($.datepicker.regional['ja']);
});
