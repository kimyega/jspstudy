/**
 * 본문을 모두 읽은 뒤 JavaScript를 수행할 수 있도록 load 이벤트를 처리한다.
 * 방법1. window.onload = function(){}
 * 방법2. $(document).ready(function(){})
 * 방법3. $(function(){})
 */
 var cp = getContextPath();
$(function(){
  $('.gnb a').mouseover(function() {
    $(this).css('background-color', 'silver').css('border-bottom', '5px solid green');
  })
  $('.gnb a').mouseout(function() {
    $(this).css('background-color', '').css('border-bottom', '');
  })
  var img = new Image();
  $(img).attr('src', cp + '/resource/image/naver.png');
  $(img).css('width', '150px');
  $('.logo').html(img);  
})

// 컨텍스트 패스를 반환하는 함수
function getContextPath(){
  var begin = location.href.indexOf(location.host) + location.host.length;
  var end = location.href.indexOf('/', begin + 1);
  return location.href.substring(begin, end);
}