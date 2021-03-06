function initChgArea() {

    areaShowOrHide('portalTopId', 'Top');
    areaShowOrHide('portalLeftId', 'Left');
    areaShowOrHide('portalCenterId', 'Center');
    areaShowOrHide('portalRightId', 'Right');
    areaShowOrHide('portalBottomId', 'Bottom');

    setAreaWidth();
    spaceShowOrHide();
}

function chgArea(checkId, areaType) {

    areaShowOrHide(checkId, areaType);
    setAreaWidth();
    spaceShowOrHide();
}

function areaShowOrHide(checkId, areaType) {
    var checkValue = document.getElementById(checkId).checked;
    if (checkValue) {
        $('#mainScreenList' + areaType).show();
    } else {
        $('#mainScreenList' + areaType).hide();
    }
}

function setAreaWidth() {
    var leftFlg = document.getElementById('portalLeftId').checked;
    var centerFlg = document.getElementById('portalCenterId').checked;
    var rightFlg = document.getElementById('portalRightId').checked;

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
    var leftFlg = document.getElementById('portalLeftId').checked;
    var centerFlg = document.getElementById('portalCenterId').checked;
    var rightFlg = document.getElementById('portalRightId').checked;

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