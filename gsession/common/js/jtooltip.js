$(function(){
 $("span.tooltips").each(function() {
  $(this).css("display","none");
 });
 $("a:has(span.tooltips)").mouseover(function(e){
  $("form").append("<div id=\"ttp\">"+ ($(this).children("span.tooltips").html()) +"</div>");
   $("#ttp")
    .css("position","absolute")
    .css("text-align","left")
    .css("font-size","12px")
    .css("top",(e.pageY) + 15 + "px")
    .css("left",(e.pageX) + 15 + "px")
    .css("display","none")
    .css("filter","alpha(opacity=85)")
    .fadeIn("fast")
 }).mousemove(function(e){
   $("#ttp")
    .css("top",(e.pageY) + 15 + "px")
    .css("left",(e.pageX) + 15 + "px")
 }).mouseout(function(){

   $("#ttp").remove();
 });
});