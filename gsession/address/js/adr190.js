function init(initFlg) {

    var initFlg = document.forms['adr190Form'].adr190initFlg;
    if (initFlg.value == 0) {

        var parentLabel = parent.document.getElementsByName(getParentLabelName());
        var selectLabel = document.forms['adr190Form'].adr190selectLabel;
        if (parentLabel != null && selectLabel != null) {

            if (selectLabel.type == 'checkbox') {
                for (parentIndex = 0; parentIndex < parentLabel.length; parentIndex++) {
                    if (selectLabel.value == parentLabel[parentIndex].value) {
                        selectLabel.checked = true;
                        break;
                    } else {
                        selectLabel.checked = false;
                    }
                }

            } else {
                for (index = 0; index < selectLabel.length; index++) {
                    for (parentIndex = 0; parentIndex < parentLabel.length; parentIndex++) {
                        if (selectLabel[index].value == parentLabel[parentIndex].value) {
                            selectLabel[index].checked = true;
                            break;
                        } else {
                            selectLabel[index].checked = false;
                        }
                    }
                }
            }
        }

        initFlg.value = '1';
    }
}

function setParentLabel() {
    var CMD = parent.document.getElementsByName('CMD');
    CMD[0].value = 'init';

    var labelParam = '';

    var selectLabel = document.forms['adr190Form'].adr190selectLabel;
    if (selectLabel != null) {
        if (selectLabel.type == 'checkbox') {
            if (selectLabel.checked == true) {
                labelParam = addLabelParam(labelParam, selectLabel.value);
            }
        } else {
            for (index = 0; index < selectLabel.length; index++) {
                if (selectLabel[index].checked == true || selectLabel[index].type == 'hidden') {
                    labelParam = addLabelParam(labelParam, selectLabel[index].value);
                }
            }
        }
    }

    labelSelectClose(false);
    parent.document.getElementById('adr020labelArea').innerHTML = labelParam;
    parent.document.forms[0].submit();
}



function getParentLabelName() {
    return document.forms['adr190Form'].adr190parentLabelName.value;
}

function addLabelParam(paramString, labelValue) {
    var parentLabelName = getParentLabelName();
    paramString += '<input type="hidden" name="'
    paramString += parentLabelName;
    paramString += '" value="' + labelValue + '">';
    return paramString;
}

function labelSelectClose(innerflg) {
    clearScreenParent(innerflg);
    parent.YAHOO.labelbox.labelPanel.hide();
    parentEnable();
}

function parentReadOnly() {
    setAllReadOnly(parent.document, true);
}

function parentEnable() {
    setAllReadOnly(parent.document, false);
}