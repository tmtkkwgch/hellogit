function changeSendServerAuth(auth) {
  $('#wml190sendServerUser')[0].disabled = (auth != 1);
  $('#wml190sendServerPassword')[0].disabled = (auth != 1);
}