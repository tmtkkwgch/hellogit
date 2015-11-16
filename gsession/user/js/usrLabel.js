
function openlabel(){
  lab.location='../user/usr210.do?usr210parentLabelName=usrLabel';
  YAHOO.labelbox.labelPanel.show();
  return false;
}

function deleteLabel(labSid) {
    document.forms['usr031Form'].delLabel.value = labSid;
    buttonPush('deleteLabel');
}
