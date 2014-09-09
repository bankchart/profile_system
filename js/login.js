// login.js
//var username = 'root';
//var password = '1234';

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

        

    });

    // validate by jsp & mysql -- end

/*    
    var users = localStorage.getItem('countUser');
    //alert(users);
//    var xx = JSON.parse(localStorage.getItem('user-' + 1));
//    alert(xx.username);
    if(users == null){
        btm_alert.style.display = 'block';
        btm_alert.style.color = 'red';
        btm_alert.innerHTML = 'Invalid Username or Password.'
    }else{
        var i;
        for(i = 1 ; i <= users ; i++){
            var tmp = JSON.parse(localStorage.getItem('user-' + i));
            if(tmp.username == un_txt.value && tmp.passwd == pass_txt.value){
            //    alert('username and password are match.');
                setCookie('online', tmp.username, 1);
                window.location.href = 'profile.html'; 
                break;
            }
            if(i == users){
                btm_alert.style.display = 'block';
                btm_alert.style.color = 'red';
                btm_alert.innerHTML = 'Invalid Username or Password.'
            }
        }
    }
*/

/*    if(un_txt.value == username && pass_txt.value == password){ // old version

      document.cookie = 'online' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';

      //	document.getElementById('welcome').innerHTML = '.....Welcome ' + username + '.....';
      //	document.getElementById('logout').style.display = 'block';
      setCookie('online', username, 1);
      //alert('online : ' + getCookie('online'));
      //window.location.href='../profile/version5-drag_drop/profile.html';

    }else{
      btm_alert.style.display = 'block';
      btm_alert.style.color = 'red';
      btm_alert.innerHTML = 'Invalid Username or Password.'
  }*/
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
//    localStorage.clear();
  if(event.charCode == 13 || event.keyCode == 13){
    validate();
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

var submit_reg = document.getElementById('submit');
submit_reg.addEventListener('click', mysubmit);

/*end addEventListener*/
// end register

// function
function mysubmit(){
    var cnt = localStorage.getItem('countUser');
    if(cnt === null){
        localStorage.setItem('countUser', 1);
        var firstUser = {
                'num' : 1,
                'username' : 'root',
                'passwd' : 1234,
                'fullname' : 'นายแบงค์ชาติ อาลัย',
                'birthdate' : '15 มี.ค. 2533',
                'height' : 182,
                'weight' : 65,
                'blood_type' : 'B',
                'age' : 24,
                'hobby' : 'ดูการ์ตูน',
                'education' : 'กำลังศึกษาในระดับปริญญาตรี',
                'faculty' : 'วิศวกรรมศาสตร์และสถาปัตยกรรมศาสตร์',
                'major' : 'วิศวกรรมคอมพิวเตอร์',
                'phone' : '094-534-655',
                'email' : 'kyo_chero@hotmail.com',
                'detail_edu' : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; กำลังศึกษาในระดับปริญญาตรี'+ 
                                'ชั้นปีที่ 4 คณะวิศวกรรมศาสตร์และสถาปัตยกรรมศาสตร์ สาขาวิศวกรรมคอมพิวเตอร์ '+ 
                                'มหาวิทยาลัยเทคโนโลยีราชมงคลอีสาน จังหวัดนครราชสีมา '+
                                '.........ชีวิตการเรียน โดยรวมแล้วทั้งหนักทั้งยากและลำบากมหาศาล แต่ถ้าผ่านมันไปได้ก็คงภูมิใจมิใช่น้อยจ้า....!!!',
                'detail_fav' : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ความชื่นชอบเป็นอีกเรื่องที่อธิบาย ' + 
                              ' ได้ยากมากครับ จริงๆ ผมมีหลายเรื่องที่ชอบแต่ถ้าจะให้ชอบแบบที่สุด ๆ' + 
                               ' แล้วหรือทำกิจกรรมแบบนั้นแล้วไม่เคยเบื่อเลยก็คงต้องขอตอบว่า การดูการ์ตูนนั่นเอง แต่ต้อง ' + 
                                'เป็นการ์ตูนของญี่ปุ่นบางเรื่องเท่านั้นครับ เช่น Hunter x Hunter ' + 
                                'One Piece อะไรประมาณนี้แต่ก็มีอีกเรื่องที่ต้องยกให้เป็น The Best กันเลยทีเดียวก็คือ' +  
                                'โคนัน ยอดนักสืบ ตั้งแต่จำความได้ไม่ว่าจะสืบหรือจะเป็น' + 
                                'แผนฆาตกรรมบอกได้เลยว่า อะไรของมานนนนว้าาาา ไอไม่เข้าจายยยยย !!!!!! T-T',  

                'detail_littleskill' : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; สกิล ๆ ๆ ๆ เฮ้อออ' + 
                                'จะมีกับเขาไหมเนี่ยครับผม ก็ต้องขอเวลานั่งคิดไตร่ตรองนานพอสมควร ขณะคิดไปก็ปั่นเพิ่ม สกิล ตัวเองไปในตัว' +  
                                'เอิ๊ก ๆ ๆ ๆ โอเค ครับมาเข้าเรื่องกันสักที ก็จะพูดถึงสกิลทางด้านโปรแกรมมิ่งล่ะกัน ก็เคยเขียน java' +  
                                'php javascript sql มาก่อนในระดับนึงหรือระดับพื้นฐานง่ายๆ' +  
                                'อะนะครับผม ถ้ามองไปทางด้านฐานข้อมูลเท่าที่เคยใช้ ก็จะเป็น MySQL ซึ่งก็ใช้ร่วมกับ java ' + 
                                'กับ php เอาเป็นว่าเรื่องของสกิลเอาไว้แค่นี้ก่อน ขอปั่นเพิ่มเยอะ ๆ แล้วจะมาเล่าให้ฟังใหม่นะ ' + 
                                'ครับโพ้มมมมม ^^'
            };

        localStorage.setItem('user-1', JSON.stringify(firstUser));
        var test = JSON.parse(localStorage.getItem('user-1'));
        //alert('first user.');
    }else{

        var data_required = ['', '', '', '', '', '',
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
        data_required[12] = document.getElementById('major');
        data_required[13] = document.getElementById('phone');
        data_required[14] = document.getElementById('email'); 
         
        var i;
        var requiredReg = true;
        var saveUser = true;
        //checking input required
        for(i = 0 ; i < 4 ; i++){
            if(data_required[i].value == ''){
                requiredReg = false;
                break;
            }
        }

        if(requiredReg){
            //checking username no-repeat ?
            var j;
            for(j = 1 ; j <= cnt ; j++){
                var tmp = JSON.parse(localStorage.getItem('user-' + j));

                if(data_required[0].value == tmp.username){
                    saveUser = false;
                    break;
                }
            }

            if(saveUser){
                if(data_required[1].value != data_required[2].value){
                    alert('Password does not match the re-password.');
                    data_required[1].value = '';
                    data_required[2].value = '';
                    document.getElementById('req_pwd').style = 'display : inline';
                    document.getElementById('req_repwd').style = 'display : inline';
                }else{
                    var data = {
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
                    localStorage.setItem('user-' + (parseInt(cnt) + 1), JSON.stringify(data));
                    localStorage.setItem('countUser', (parseInt(cnt) + 1));
                    alert('register completed.');
                    window.location.reload();
                }
            }else{
                // username repeat....
                alert('username repeat....');
            }
        }else{
            alert('enter all required data.');
        }

    }

}

function enterOnReg(event){
  if(event.charCode == 13 || event.keyCode == 13){
    mysubmit();
  }
}
var toggle = true;
function toggleLogin(){
    $('input').val('');
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
