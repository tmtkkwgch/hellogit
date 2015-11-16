function changeEnableDisable() {
  if (document.forms[0].sch086EditType[0].checked) {
     document.getElementById('schEditArea1').rowspan=2;
     $('#schEditArea2').show();
  } else {
     document.getElementById('schEditArea1').rowspan=1;
     $('#schEditArea2').hide();
  }
}

function changeEnableDisablePublic() {
    if (document.forms[0].sch086PublicType[0].checked) {
       document.getElementById('schPublicArea1').rowspan=2;
       $('#schPublicArea2').show();
    } else {
       document.getElementById('schPublicArea1').rowspan=1;
       $('#schPublicArea2').hide();
    }
  }

function changeEnableDisableSame() {
    if (document.forms[0].sch086SameType[0].checked) {
       document.getElementById('schSameArea1').rowspan=2;
       $('#schSameArea2').show();
    } else {
       document.getElementById('schSameArea1').rowspan=1;
       $('#schSameArea2').hide();
    }
  }
