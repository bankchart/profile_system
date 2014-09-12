// login.js
//localStorage.clear();
/*
$("#submitx").click(function(){

	alert("test x");

});
*/
function isUser_Online(){
  document.getElementById('uname').value = '';
  document.getElementById('pass').value = '';
  var isOnline = getCookie('online');
  var url = window.location.href;

  url = url.substr(url.indexOf('=') + 1, 4);
  if(url == 'true'){
    setCookie("online", "", -1);
  }else if(isOnline != ''){
    window.location.href='profile.html';
  }

}
function validate(){ // login
  var un_txt = document.getElementById('uname');
  var pass_txt = document.getElementById('pass');
  var un_alert = document.getElementById('ua');
  var pass_alert = document.getElementById('pa');
  var btm_alert = document.getElementById('btm_alert');
  un_alert.style.display = 'none';
  pass_alert.style.display = 'none';
  btm_alert.style.display = 'none';
  if(un_txt.value != "" && pass_txt.value != ""){
    // validate by jsp & mysql -- begin

    $.ajax({

  	url : "Login2Profile",
	data : { username : un_txt.value, password : pass_txt.value, mode : "login" },
	type : "post",
	success : function(data){
		// setCookie username & redirect to profile		
		if(data != 'incorrect'){
			alert("let go to profile.html");
			setCookie("online", data, 1);
			window.location.href = "profile.html";	
		}else{
			btm_alert.style.display = 'block';
			btm_alert.style.color = 'red';
			btm_alert.innerHTML = 'Invalid Username or Password.';			
		}	
	}      

    });



  }else{
    if(un_txt.value == "")
      un_alert.style.display = 'inline';
    else
      un_alert.style.display = 'none';
    if(pass_txt.value == "")
      pass_alert.style.display = 'inline';
    else
      pass_alert.style.display = 'none';
  }
}

function enter(event){
    localStorage.clear();
  if(event.charCode == 13 || event.keyCode == 13){
    //validate();
  }
}

function logout(){
  if(confirm('Confirm logout?')){
        //document.cookie = 'online' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
        setCookie("online", "", -1);
  }
}

function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires="+d.toGMTString();
  document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0)==' ') c = c.substring(1);
      if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
  }
  return "";
}
// register
/*addEventListener*/

var uname_required = document.getElementById('username');
uname_required.addEventListener('blur', function(){
    var display = 'display : ';
    if(this.value == '')
        display += 'inline';
    else
        display += 'none'
    document.getElementById('req_uname').style = display;
});
var pwd_required = document.getElementById('passwd');
pwd_required.addEventListener('blur', function(){
    var display = 'display : ';
    if(this.value == '')
        display += 'inline';
    else
        display += 'none'
    document.getElementById('req_pwd').style = display;
});
var repwd_required = document.getElementById('repasswd');
repwd_required.addEventListener('blur', function(){
    var display = 'display : ';
    if(this.value == '')
        display += 'inline';
    else
        display += 'none'
    document.getElementById('req_repwd').style = display;
});
var fname_required = document.getElementById('fullname');
fname_required.addEventListener('blur', function(){
    var display = 'display : ';
    if(this.value == '')
        display += 'inline';
    else
        display += 'none'
    document.getElementById('req_fname').style = display;
});

//var submit_reg = document.getElementById('submit');
//submit_reg.addEventListener('click', mysubmit);

/*end addEventListener*/
// end register

//$("#register_form").submit(mysubmit);

// function
function mysubmit(){

        var data_required = ['', '', '', '', '', '',
                             '', '', '', '', '', '',
                             '', '', '', '', '', ''];
	var data_required_value = ['', '', '', '', '', '',
                             '', '', '', '', '', '',
                             '', '', '', '', '', ''];

        data_required[0] = document.getElementById('username'); // required
        data_required[1] = document.getElementById('passwd'); // required 
        data_required[2] = document.getElementById('repasswd'); // required 
        data_required[3] = document.getElementById('fullname'); // required 
        data_required[4] = document.getElementById('birthdate');
        data_required[5] = document.getElementById('height');
        data_required[6] = document.getElementById('weight');
        data_required[7] = document.getElementById('blood-type'); 
        data_required[8] = document.getElementById('age');
        data_required[9] = document.getElementById('hobby');
	data_required[10] = document.getElementById('education');
        data_required[11] = document.getElementById('faculty');
        
        data_required_value[0] = document.getElementById('username').value; // required
        data_required_value[1] = document.getElementById('passwd').value; // required 
        data_required_value[2] = document.getElementById('repasswd').value; // required 
        data_required_value[3] = document.getElementById('fullname').value; // required 
        data_required_value[4] = document.getElementById('birthdate').value;
        data_required_value[5] = document.getElementById('height').value;
        data_required_value[6] = document.getElementById('weight').value;
        data_required_value[7] = document.getElementById('blood-type').value; 
        data_required_value[8] = document.getElementById('age').value;
        data_required_value[9] = document.getElementById('hobby').value;
        data_required_value[10] = document.getElementById('education').value;
        data_required_value[11] = document.getElementById('faculty').value;
        data_required_value[12] = document.getElementById('major').value;
        data_required_value[13] = document.getElementById('phone').value;
        data_required_value[14] = document.getElementById('email').value; 
	data_required_value[12] = document.getElementById('major').value;
        data_required_value[13] = document.getElementById('phone').value;
        data_required_value[14] = document.getElementById('email').value; 
        
		
 
        var i;
        var requiredReg = true;
        var saveUser = false;
        //checking input required
        for(i = 0 ; i < 4 ; i++){
            if(data_required[i].value == ''){
                requiredReg = false;
                break;
            }
        }

        if(requiredReg){

            //checking username no-repeat ?
		alert("inside if check repeat.");
		$.ajax({
			url : "Login2Profile",
			dataType : "text",
			async : false,
			data : {
				memReg : data_required_value[0] , 
				mode : "isRepeatUsername"
				},
			type : "post",
			success : function(data){
				if(data == "no-repeat"){
	//				window.location.href = "index.html";
					saveUser = true;
				}
			}
		});
	
            if(saveUser){
                if(data_required[1].value != data_required[2].value){
                    alert('Password does not match the re-password.');
                    data_required[1].value = '';
                    data_required[2].value = '';
                    document.getElementById('req_pwd').style = 'display : inline';
                    document.getElementById('req_repwd').style = 'display : inline';
  
              }else{
				
						
  /*                  var data = {
                        'num' : parseInt(cnt) + 1,
                        'username' : data_required[0].value,
                        'passwd' : data_required[1].value,
                        'fullname' : data_required[3].value,
                        'birthdate' : data_required[4].value,
                        'height' : data_required[5].value,
                        'weight' : data_required[6].value,
                        'blood_type' : data_required[7].value,
                        'age' : data_required[8].value,
                        'hobby' : data_required[9].value,
                        'education' : data_required[10].value,
                        'faculty' : data_required[11].value,
                        'major' : data_required[12].value,
                        'phone' : data_required[13].value,
                        'email' : data_required[14].value,
                        'detail_edu' : '',
                        'detail_fav' : '',
                        'detail_littleskill' : ''
                    };
//                    localStorage.setItem('user-' + (parseInt(cnt) + 1), JSON.stringify(data));
  //                  localStorage.setItem('countUser', (parseInt(cnt) + 1));
                    alert('register completed.');
                    window.location.reload();
    */
	            }
			return true;
            }else{
                // username repeat....
                alert('username repeat....');
		return false;
            }
        }else{
            alert('enter all required data.');
		return false;
        }


}

function enterOnReg(event){
  if(event.charCode == 13 || event.keyCode == 13){
    alert("submit");
    //mysubmit();
  }
}
var toggle = true;
function toggleLogin(){
  //  $('input[name!="submit"]').val('');
   // $("#mode").val("register");
    $('select').val('choose');
    document.getElementById('btm_alert').style = 'display: none';
    if(toggle){
        toggle = false
        document.getElementById('loginContent').style = 'display: none';
        document.getElementById('register').style = 'display: block';
    }else{
        toggle = true;
        document.getElementById('register').style = 'display: none';
        document.getElementById('loginContent').style = 'display: inline';
    }
}
// end function

// enter event

// end enter event

// jquery-ui
$('#birthdate').datepicker({
  changeMonth: true,
  changeYear: true,
  dateFormat: 'dd/mm/yy',
  //defaultDate: new Date(01, 00, 1990),
  yearRange: "-80:+80"
});
