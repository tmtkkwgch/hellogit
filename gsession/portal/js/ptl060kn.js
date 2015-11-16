function initChgArea() {
    areaShowOrHide('Top', document.forms[0].ptl060area1.value);
    areaShowOrHide('Left', document.forms[0].ptl060area3.value);
    areaShowOrHide('Center', document.forms[0].ptl060area4.value);
    areaShowOrHide('Right', document.forms[0].ptl060area5.value);
    areaShowOrHide('Bottom', document.forms[0].ptl060area2.value);

    setAreaWidth();
    spaceShowOrHide();
}

function areaShowOrHide(areaType, value) {
    if (value == 1) {
        $('#mainScreenList' + areaType).show();
    } else {
        $('#mainScreenList' + areaType).hide();
    }
}

function setAreaWidth() {


    var leftFlg = (document.forms[0].ptl060area3.value == "1");
    var centerFlg = (document.forms[0].ptl060area4.value == "1");
    var rightFlg = (document.forms[0].ptl060area5.value == "1");

    var leftArea = document.getElementById('mainScreenListLeft');
    var centerArea = document.getElementById('mainScreenListCenter');
    var rightArea = document.getElementById('mainScreenListRight');

    if (leftFlg) {
        if (centerFlg && rightFlg) {
           leftArea.width = '33%';
           centerArea.width = '33%';
           rightArea.width = '33%';
        } else {

           if (centerFlg && !rightFlg) {
               leftArea.width = '33%';
               centerArea.width = '67%';
               rightArea.width = '1';
           } else if (!centerFlg && rightFlg) {
               leftArea.width = '50%';
               centerArea.width = '1';
               rightArea.width = '50%';
           } else {
               leftArea.width = '100%';
               centerArea.width = '1';
               rightArea.width = '1';
           }
        }

     } else {
         leftArea.width = '1';
         if (centerFlg && rightFlg) {
             centerArea.width = '67%';
             rightArea.width = '33%';
         } else if (centerFlg && !rightFlg) {
             centerArea.width = '100%';
             rightArea.width = '1';
         } else if (!centerFlg && rightFlg) {
             centerArea.width = '1';
             rightArea.width = '100%';
         }
     }
}

function spaceShowOrHide() {
    var leftFlg = (document.forms[0].ptl060area3.value == "1");
    var centerFlg = (document.forms[0].ptl060area4.value == "1");
    var rightFlg = (document.forms[0].ptl060area5.value == "1");

    var leftSpaceDspFlg = false;
    if (leftFlg) {
        if (centerFlg || rightFlg) {
            leftSpaceDspFlg = true;
        }
    }

    var rightSpaceDspFlg = false;
    if (rightFlg) {
        if (centerFlg || leftFlg) {
            rightSpaceDspFlg = true;
        }
    }

    if (leftSpaceDspFlg == true) {
        $('#left_space').show();
    } else {
        $('#left_space').hide();
    }

    if (rightSpaceDspFlg == true) {
        $('#right_space').show();
    } else {
        $('#right_space').hide();
    }
}