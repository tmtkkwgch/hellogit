function setParentLabel() {
    var CMD = parent.document.getElementsByName('CMD');
    CMD[0].value = 'bmk040ok';

    var labelParam = '';
    var labelName = '';
    var selectLabel = document.forms['bmk040Form'].bmk040selectLabel;

    if (selectLabel != null) {
        if (selectLabel.type == 'checkbox') {
            if (selectLabel.checked == true) {
                labelName = selectLabel.value;
            }
        } else {
            for (index = 0; index < selectLabel.length; index++) {
                if (selectLabel[index].checked == true) {
                    labelName = labelName + selectLabel[index].value + " ";
                }
            }
        }

    }

    labelParam = addLabelParam(labelParam, labelName);
    labelSelectClose(false);
    parent.document.getElementById('bmk040labelArea').innerHTML = labelParam;
    parent.document.forms[0].submit();
}

function getParentLabelName() {
    return document.forms['bmk040Form'].bmk040parentLabelName.value;
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

function pushCancel(innerflg) {
    var CMD = parent.document.getElementsByName('CMD');
    CMD[0].value = 'bmk040cancel';
    labelSelectClose(innerflg);
    parent.document.forms[0].submit();
}

function parentReadOnly() {
    setAllReadOnly(parent.document, true);
}

function parentEnable() {
    setAllReadOnly(parent.document, false);
}