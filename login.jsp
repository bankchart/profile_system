<!DOCTYPE html>
<!--login.jsp-->
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel='stylesheet' type='text/css' href='jquery-ui/jquery-ui.css'/>
		<link rel='stylesheet' type='text/css' href='css/login.css' />
		<title>LOGIN PAGE</title>
</head>
<body >
	<div class='login'>

		<div class='inputBlock'>
			<div id='loginContent'>
				<div class='inputLabel'>
					<h2>Let's get started.</h2>
					<label>This will be an amazing experience</label>
				</div>
				<div id='welcome' class='inputText'>
						<div class='imgLabel'></div>
						<input name='uname' id='uname' onkeypress="enter(event)"
							placeholder="Username" type='text'/>
						<span id='ua' class='alert'>*insert please.</span><br/>
						<div class='imgLabel'></div>
						<input name='pass' id='pass' onkeypress="enter(event)"
							placeholder="Password" type='password'/>
						<span id='pa' class='alert'>*insert please.</span><br/>
						<input type="button" onclick="validate()" id="loginButton" value="LOGIN" />
					
					<div id='btm_alert'></div>
					<div><a onclick='toggleLogin();' style='color: blue;' href='#'><ins>Register</ins></a></div>
				</div>

			</div>
			<div id='register'>
				<p id='labelRegister'>create your profile account</p>
				<form method="post" onsubmit="return mysubmit();" action="Login2ProfileController">
				<input type="hidden" name="mode" value="register" />
				<div class='table'>
					<div class='table-row'>
						<div class='table-cell table-cell-right table-col-left'>Username<span>&nbsp;:</span></div>
						<div class='table-cell'>
							<input  class='txt_reg' type='text' id='username' name='username' />
							<span id='req_uname' class='required'>*required</span>
						</div>
					</div>
					<div class='table-row'>
						<div class='table-cell table-cell-right table-col-left'>Password<span>&nbsp;:</span></div>
						<div class='table-cell'>
							<input  class='txt_reg' type='password' id='passwd' name='passwd' />
							<span id='req_pwd' class='required'>*required</span>
						</div>
					</div>
					<div class='table-row'>
						<div class='table-cell table-cell-right table-col-left'>Re-enter Password<span>&nbsp;:</span></div>
						<div class='table-cell'>
							<input  class='txt_reg' type='password' id='repasswd' name='repasswd' />
							<span id='req_repwd' class='required'>*required</span>
						</div>
					</div>
					<div class='table-row'>
						<div class='table-cell table-cell-right table-col-left'>Full Name<span>&nbsp;:</span></div>
						<div class='table-cell'>
							<input  class='txt_reg' type='text' id='fullname' name='fullname'/>
							<span id='req_fname' class='required'>*required</span>
						</div>
					</div>
					<div class='table-row'>
						<div class='table-cell table-cell-right table-col-left'>Birthdate<span>&nbsp;:</span></div>
						<div class='table-cell'><input  class='txt_reg' type='text' id='birthdate' name='birthdate'/></div>
					</div>
					<div class='table-row'>
						<div class='table-cell table-cell-right table-col-left'>Height<span>&nbsp;:</span></div>
						<div class='table-cell'><input class='txt_reg'
							placeholder='number only' min='1' type='number'
							style='width: 105px;' id='height' name='height'/>
							cm.
						</div>
					</div>
					<div class='table-row'>
						<div class='table-cell table-cell-right table-col-left'>Weight<span>&nbsp;:</span></div>
						<div class='table-cell'><input  class='txt_reg'
							placeholder='number only' min='1' type='number'
							style='width: 105px;' id='weight' name='weight'/>
							kg.
						</div>
					</div>
					<div class='table-row'>
						<div class='table-cell table-cell-right table-col-left'>Blood type<span>&nbsp;:</span></div>
						<div class='table-cell'>
							<select   class='sel_reg' id='blood-type' name='blood-type'>
								<option>choose</options>
									<option>O</option>
									<option>A</option>
									<option>B</option>
									<option>AB</option>
								</select>
							</div>
						</div>
						<div class='table-row'>
							<div class='table-cell table-cell-right table-col-left'>Hoby<span>&nbsp;:</span></div>
							<div class='table-cell'>
								<textarea id='hobby' name='hobby' cols='31' rows='5' style='resize: none;'></textarea>
							</div>
						</div>
						<div class='table-row'>
							<div class='table-cell table-cell-right table-col-left'>Education<span>&nbsp;:</span></div>
							<div class='table-cell'>
								<input  id='education' name='education' class='txt_reg' type='text'/>
							</div>
						</div>
						<div class='table-row'>
							<div class='table-cell table-cell-right table-col-left'>Faculty<span>&nbsp;:</span></div>
							<div class='table-cell'>
								<input  id='faculty' name='faculty' class='txt_reg' type='text'/>
							</div>
						</div>
						<div class='table-row'>
							<div class='table-cell table-cell-right table-col-left'>Major<span>&nbsp;:</span></div>
							<div class='table-cell'>
								<input  id='major' name='major' class='txt_reg' type='text'/>
							</div>
						</div>
						<div class='table-row'>
							<div class='table-cell table-cell-right table-col-left'>Phone<span>&nbsp;:</span></div>
							<div class='table-cell'>
								<input  id='phone' name='phone' class='txt_reg' type='text'/>
							</div>
						</div>
						<div class='table-row'>
							<div class='table-cell table-cell-right table-col-left'>Email<span>&nbsp;:</span></div>
							<div class='table-cell'>
								<input  id='email' name='email' class='txt_reg' type='text'/>
							</div>
						</div>
						<div class='table-row'>
							<div class='table-cell table-cell-right table-col-left'><a onclick='toggleLogin();' style='color: blue;' href='#'><ins>login</ins></a></div>
							<div class='table-cell'>
							<input name="submit" id="submit" type="submit" value="SUBMIT"/>
							</div>
						</div>
					</div>
				</form>
				</div>

				<!--div class='register'>test</div-->
			</div>
			<div id='myProfile'></div>
		</div>
		</body>
		<script type='text/javascript' src='jquery/jquery.js'></script>
		<script type='text/javascript' src='jquery-ui/jquery-ui.min.js'></script>
		<script type='text/javascript' src='js/login.js'></script>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>
<script>
$(function(){
$( ".inputBlock" ).draggable();
});



</script>	
		</html>

