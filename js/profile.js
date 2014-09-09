// profile.js
$(function() {
    $("#menu_sortable").sortable();
    $("#menu_sortable").disableSelection();
    $("#footer2").draggable({cursor: "move"});
$("#profile").draggable({cursor: "move"/*, cursorAt: {top: 50, left: 50}*/});
$("#mprofile").draggable({cursor: "move"/*, cursorAt: {top: 50, left: 60}*/});
$("#menu_sortable").disableSelection();

$("#img_sortable").sortable();
$("#img_sortable").disableSelection();
});

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname){
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
            if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
            }
            return "";

        }

        function logout(){
            if(confirm('Confirm logout?')){
                document.cookie = 'online' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
                // setCookie("online", "", -1);
                //  setTimeout('loginPage()', 1000);
                loginPage();
            }
        }
        function loginPage(){
            window.location.href='index.html?logout=true';
        }

        function opacity(mid){
            if(mid == 'out'){
                document.getElementById('edu').setAttribute('style', 'display: none;');
                document.getElementById('fav').setAttribute('style', 'display: none;');
                document.getElementById('lsk').setAttribute('style', 'display: none;');
                document.getElementById('aboutme').setAttribute('style', 'display: none;');
            }else{
                document.getElementById(mid).setAttribute('style', 'display: block;');
            }
        }
        function closeAutoAbt(){
            document.getElementById('aboutme_auto').setAttribute('style', 'display: none;');
        }

// new version
function isUser_Online(){
  //  localStorage.clear(); 
    var cnt = JSON.parse(localStorage.getItem('countUser'));
    var i;
    if(cnt != null){ 
        cnt = parseInt(cnt); 
        for(i = 1 ; i <= cnt ; i++){
              var tmp = JSON.parse(localStorage.getItem('user-' + i));
              if(getCookie('online') == tmp.username){
                   document.getElementById('fname').innerHTML = tmp.fullname;
                   $('.fullname').text(' ' + tmp.fullname); 
            	   $('.birthdate').text(' ' + tmp.birthdate);
            	   $('.height').text(' ' + tmp.height); 
            	   $('.weight').text(' ' + tmp.weight); 
            	   $('.blood_type').text(' ' + tmp.blood_type); 
            	   $('.age').text(' ' + tmp.age); 
            	   $('.hobby').text(' ' + tmp.hobby); 
            	   $('.education').text(' ' + tmp.education);
            	   $('.faculty').text(' ' + tmp.faculty); 
            	   $('.major').text(' ' + tmp.major); 
            	   $('.phone').text(' ' + tmp.phone); 
            	   $('.email').text(' ' + tmp.email); 
            	   if(tmp.detail_edu == '') 
                         $('#edu').text('click edit button for add detail.'); 
                   else
                         $('#edu').html(tmp.detail_edu); 
                   if(tmp.detail_fav == '')
                         $('#fav').text('click edit button for add detail.');
                   else 
                         $('#fav').html(tmp.detail_fav);
		   if(tmp.detail_littleskill == '')
                         $('#lsk').text('click edit button for add detail.');
                   else 
                         $('#lsk').html(tmp.detail_littleskill); 
                   break;
             }else if(i == cnt){
	            window.location.href='index.html?logout=true';
             }
        }
   }else{//end if cnt != null  
       window.location.href='index.html?logout=true'; 
   }
}

function editDetail(){
    var cnt = JSON.parse(localStorage.getItem('countUser'));
    var i;
    if(cnt != null){
        cnt = parseInt(cnt);
        for(i = 1 ; i <= cnt ; i++){
              var tmp = JSON.parse(localStorage.getItem('user-' + i));
              if(getCookie('online') == tmp.username){
                 $('textarea[name=edu_edit]').html(tmp.detail_edu);
                 document.getElementById('fav_edit').innerHTML = tmp.detail_fav;
                 document.getElementById('lsk_edit').innerHTML = tmp.detail_littleskill;
                 document.getElementById('editBlock').style = 'display: block;';
                     
                 break; 
              }
        }
    }  
}

function editCancel(){
   document.getElementById('editBlock').style='display: none;';
}

function editSave(){

var cnt = JSON.parse(localStorage.getItem('countUser'));
    var i;
    if(cnt != null){
        cnt = parseInt(cnt);
        for(i = 1 ; i <= cnt ; i++){
              var tmp = JSON.parse(localStorage.getItem('user-' + i));
              if(getCookie('online') == tmp.username){
                 tmp.detail_edu = $('textarea[name=edu_edit]').val();
                 tmp.detail_fav = $('textarea[name=fav_edit]').val();
                 tmp.detail_littleskill = $('textarea[name=lsk_edit]').val();
                 document.getElementById('editBlock').style = 'display: none;';
                 localStorage.setItem('user-' + tmp.num, JSON.stringify(tmp)); 
                 alert('save completed.'); 
                 window.location.reload(); 
                 break;
              }
        }
    }


}
// modify to day
var img_toggle = true;
function activity_Button(){
     if(img_toggle){
          document.getElementById('img_sortable').style = 'display: none'; 
          img_toggle = false;
    } else{
          document.getElementById('img_sortable').style = 'display: block'; 
          img_toggle = true;
     }
}
