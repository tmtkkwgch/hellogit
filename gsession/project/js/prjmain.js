function updatePrj(sortKey, order) {
  var url = '../project/prjmain.do';
  var pars = '';//getHidden(); 

  pars='?prjMainSort=' + sortKey;
  pars= pars + '&' + 'prjMainOrder=' + order;
  url= url + pars;
  $('#project_prjmain').load(url);
}