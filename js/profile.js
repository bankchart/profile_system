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
                //document.cookie = 'online' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
                // setCookie("online", "", -1);
                //  setTimeout('loginPage()', 1000);
                loginPage();
            }
        }
        function loginPage(){
            window.location.href='Profile?mode=logout';
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
// *************************************   CODING NOW   ****************************************************
function isUser_Online(){
	
}

function editDetail(){
	document.getElementById('editBlock').style = 'display: inline';	
}

function editCancel(){
   document.getElementById('editBlock').style = 'display: none;';
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
// admin
function adminButton(){
	window.location.href = "Profile?mode=adminviewer";
}
function careerSearch(){
	window.location.href = "Profile?mode=adminviewer&career=" + document.getElementById("selectCareer").value;
}
$('#birthdate').datepicker({
  changeMonth: true,
  changeYear: true,
  dateFormat: 'dd/mm/yy',
  //defaultDate: new Date(01, 00, 1990),
  yearRange: "-80:+80"
});

