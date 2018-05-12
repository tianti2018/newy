var Tags=document.getElementById('NewsTop_tit').getElementsByTagName('p'); 
var TagsCnt=document.getElementById('NewsTop_cnt').getElementsByTagName('span'); 
var len=Tags.length; 
var flag=1;//修改默认值
for(i=1;i<len;i++){
Tags[i].value = i;
Tags[i].onmouseover=function(){changeNav(this.value)}; 
TagsCnt[i].className='undis'; 
}
Tags[flag].className='topC1';
TagsCnt[flag].className='dis';
function changeNav(v){ 
Tags[flag].className='topC0';
TagsCnt[flag].className='undis';
flag=v; 
Tags[v].className='topC1';
TagsCnt[v].className='dis';
}
