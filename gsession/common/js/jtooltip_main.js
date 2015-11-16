$(function(){
 jQuery("a:has(span.tooltips)").live({
     mouseenter:function(e){
     $(this).append("<div id=\"ttp\"><table><tr><td nowrap>"+ ($(this).children("span.tooltips").html()) +"</td></tr></table></div>");
     $("#ttp")
      .css("position","absolute")
      .css("text-align","left")
      .css("font-size","12px")
      .css("top",(e.pageY) + 15 + "px")
      .css("left",(e.pageX) + 15 + "px")
      .css("display","none")
      .css("filter","alpha(opacity=85)")
      .fadeIn("fast");
     },
     mousemove:function(e){
      $("#ttp")
      .css("top",(e.pageY) + 15 + "px")
      .css("left",(e.pageX) + 15 + "px");
     },
     mouseleave:function(){
         $("#ttp").remove();
     }
 });
});