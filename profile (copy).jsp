<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%
	String username = (String)request.getAttribute("username");
	String fullname = (String)request.getAttribute("fullname");
	String birthdate = (String)request.getAttribute("birthdate");
	String height = (String)request.getAttribute("height");
	String weight = (String)request.getAttribute("weight");
	String blood_type = (String)request.getAttribute("blood_type");
	String hobby = (String)request.getAttribute("hobby");
	String phone = (String)request.getAttribute("phone");
	String email = (String)request.getAttribute("email");
	String detail_edu = (String)request.getAttribute("detail_edu");
	String detail_fav = (String)request.getAttribute("detail_fav");
	String detail_lsk = (String)request.getAttribute("detail_lsk");
	String picture_path = (String)request.getAttribute("picture_path");
	String admin = (String)request.getAttribute("admin");
	fullname = (fullname == null)? "-" : fullname;
	birthdate = (birthdate == null)? "-" : birthdate;
	if(!"-".equals(birthdate)){
		String[] tmp = birthdate.split("-");
		birthdate = tmp[2] + "/" + tmp[1] + "/" + tmp[0];	
	}
	height = (height == null)? "-" : height;
	weight = (weight == null)? "-" : weight;
	blood_type = (blood_type == null)? "-" : blood_type;	
	hobby = (hobby == null)? "-" : hobby;
	phone = (phone == null)? "-" : phone;
	email = (email == null)? "-" : email;
	detail_edu = (detail_edu == null)? "" : detail_edu;
	detail_fav = (detail_fav == null)? "" : detail_fav;
	detail_lsk = (detail_lsk == null)? "" : detail_lsk;
	picture_path = (picture_path == null)? "-" : picture_path;

	String user = (String)session.getAttribute("user");
		
%>
<!DOCTYPE html>
<!--profile.html-->
<html>
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel='stylesheet' type='text/css' href='jquery-ui/jquery-ui.css'/>

  <link rel='stylesheet' type='text/css' href='css/profile.css' />
  <link rel="stylesheet" href="css/lightbox.css" type="text/css" media="screen" />
    <title>Profile</title>

  </head>
  <body id ='mybody' bgcolor='#f2f2eb' onload='isUser_Online()'>
    <div id='container'>
      <div id='base-header'>
        <div id='header'>
          <label id='profile'style='float: left;'>
            PROFILE 
          </label>
          <div id='menu_sortable' class='menu' style='text-align: right;'>
            <div class='nmenu' onmouseover='opacity("aboutme")' onmouseout='opacity("out")'>&nbsp;About&nbsp;Me&nbsp;</div>
<div>|</div><div class='nmenu' onmouseover='opacity("edu")' onmouseout='opacity("out")' >&nbsp;Education&nbsp;</div>
<div>|</div><div class='nmenu' onmouseover='opacity("fav")' onmouseout='opacity("out")' >&nbsp;Favorite&nbsp;</div>
<div>|</div><div class='nmenu' onmouseover='opacity("lsk")' onmouseout='opacity("out")' >&nbsp;Little&nbsp;Skill&nbsp;</div>
      	    <div>|</div>
<!-- *********************admin scope***************** -->
	<% if("1".equals(admin)){ %>
		<button onclick='adminButton()' style="color:#000;">Management System</button>
	    	<div>|</div> 
	<% } %>
	    
	    <% if(!username.equals(user)){%>
		<button onclick='adminButton()' style='color: #000;'>Management System</button>
		<div>|</div>
	    <% } %>	
            <button <% if(!username.equals(user)) out.print("disabled"); %> onclick='editDetail();' style='color:#000;'>Edit</button> 
            <div>|</div>
            <button <% if(!username.equals(user)) out.print("disabled"); %> onclick='logout();' style='color:#000;'>Logout</button>
          </div>
        </div>
        <!--content-->
        <div id='content'>
          <!--label id='mprofile' style='font-family: thaisanslite; color:
          orange; font-size: 30px; font-weight: bold;
          text-shadow: 0 0 0.2em #003366, 0 0 0.2em #87F,
          0 0 0.2em #87F;'-->
	  
	<div id ="mprofile" style='font-family: thaisanslite; color:
          orange; font-size: 30px; font-weight: bold;
          text-shadow: 0 0 0.2em #003366, 0 0 0.2em #87F,
          0 0 0.2em #87F;'>
		<a id='activity_click'onClick='activity_Button();'>ACTIVITY</a> :
		<a id="fname" onClick='opacity("aboutme")'> <%= fullname  %></a>
          </div>
	  <!--/label--> 
	   <center>
            <!--table id="img_sortable" border='0'style='border-spacing:8px; width: 600px; margin-left: -10px;' >
            <tr>
            <td>
	    <div title='Bank' id='me1'></div>
          	empty 
          </td>
          <td>
          <div title='Bank' id='me2'></div>
         	empty
        </td>
        <td>
        <div title='Bank' id='me3'></div>
        	empty
      </td>
      <td>
      <div title='Bank' id='me4'></div>
		empty
    </td>

  </tr>
  <tr>
  <td>
  <a id='education'></a>

  <div title='education' id='edu1'></div>
  empty
</td>
<td>
<div title='education' id='edu2'></div>
empty
</td>
<td>

<div title='education' id='edu3'></div>
	empty
</td>
<td>
<div title='education' id='edu4'></div>
	empty
</td>
</tr>
<tr>
<td>
<a id='favorite'></a>
<div title='favorite' id='fav1'></div>
	empty
</td>
<td>
<div  title='favorite' id='fav2'></div>
	empty
</td>
<td>
<div  title='favorite' id='fav3'></div>
	empty
</td>
<td>
<div  title='favorite' id='fav4'></div>
	empty
</td>

</tr>
<tr>
<td>
<a id='littleskill'></a> <div title='littleskill' id='sk1'></div>
	empty
</td>
<td>
<div title='littleskill' id='sk2'></div>
	empty
</td>
<td>
<div title='littleskill' id='sk3'></div>
	empty
</td>
<td>
<div title='littleskill' id='sk4'></div>
	empty
</td>

</tr>
</table-->
<!-- new images -->
<div id='img_sortable'>
  <div>
    <a href="images/profile/user-1/img1-1.jpg" rel="lightbox"><div id='me1'></div></a>
    <br/>	empty 
  </div>
  <div>
    <a href="images/profile/user-1/img2-1.jpg" rel="lightbox"><div id='me2'></div></a>
    <br/>  empty
  </div>
  <div>
    <a href="images/profile/user-1/img3-1.jpg" rel="lightbox"><div id='me3'></div></a>
    <br/> empty
  </div>
  <div>
    <a href="images/profile/user-1/img4-1.jpg" rel="lightbox"><div id='me4'></div></a>
    <br/>  empty
  </div>

  <div>
    <a href="images/profile/user-1/rmuti3-1.jpg" rel="lightbox"><div id='edu1'></div></a>
    <br/>empty
  </div>
  <div>
    <a href="images/profile/user-1/rmuti5-1.jpg" rel="lightbox"><div id='edu2'></div></a>
    <br/>empty
  </div>
  <div>
    <a href="images/profile/user-1/rmuti4-1.jpg" rel="lightbox"><div id='edu3'></div></a>
    <br/>	empty
  </div>
  <div>
    <a href="images/profile/user-1/rmuti6-1.jpg" rel="lightbox"><div id='edu4'></div></a>
    <br/>	empty
  </div>

  <div>
    <a href="images/profile/user-1/fav1-1.jpg" rel="lightbox"><div id='fav1'></div></a>
    <br/>    	empty
  </div>
  <div>
    <a href="images/profile/user-1/fav2-1.jpg" rel="lightbox"><div id='fav2'></div></a>
    <br/>empty
  </div>
  <div>
    <a href="images/profile/user-1/fav3-1.jpg" rel="lightbox"><div id='fav3'></div></a>
    <br/> 	empty
  </div>
  <div>
    <a href="images/profile/user-1/fav4-1.jpg" rel="lightbox"><div id='fav4'></div></a>
    <br/>	empty
  </div>

  <div>
    <a href="images/profile/user-1/sk1-1.png" rel="lightbox"><div id='sk1'></div></a>
    <br/> 	empty
  </div>
  <div>
    <a href="images/profile/user-1/sk2-1.jpg" rel="lightbox"><div id='sk2'></div></a>
    <br/>	empty
  </div>
  <div>
    <a href="images/profile/user-1/sk3-1.jpg" rel="lightbox"><div id='sk3'></div></a>
    <br/>	empty
  </div>
  <div>
    <a href="images/profile/user-1/sk4-1.jpg" rel="lightbox"><div id='sk4'></div></a>
    <br/>	empty
  </div>

</div>

<br/>

<div style='position: absolute; width: 317px; height: 3px;
line-height: 50px;
background: linear-gradient(
CCC, #FFF);
left: 130px; top: 1230px;
'></div>

<div id='footer2' style='
background-image: url("images/profile/user-1/footer2.gif");
background-size: 416px 35px;
width: 416px; top: 1215px; left: 445px;
position: absolute;
height: 35px;
'></div>
<!--label style='margin-left: 0px; font-family: thaisanslite; color:
orange; font-size: 30px; font-weight: bold;
text-shadow: 0 0 0.2em #003366, 0 0 0.2em #87F,
0 0 0.2em #87F; '>Powered by Bank@Korat</label-->

<div style='display: inline; position: absolute; width: 300px; height: 3px;
line-height: 50px;
background: linear-gradient(#CCC ,#FFF); left: 870px; top: 1230px; '></div>


</center>
</div>
<!--end content-->
<div id='second_bg'></div>
<div id='edu' class='detail-dialog'>click edit data please.
</div>
<div id='fav' class='detail-dialog'>click edit data please.
</div>
<div id='lsk' class='detail-dialog'>click edit data please.
</div>
<div id='aboutme' class='detail-dialog' onClick='opacity("out")'>
	  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>fullname :</b><span class='fullname'> <%= fullname %></span> 
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>birthdate :</b><span class='birthdate'> <%= birthdate %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>height :</b><span class='height'> <%= height %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>weight :</b><span class='weight'> <%= weight %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>blood type :</b><span class='blood_type'> <%= blood_type %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>hobby :</b><span class='hobby'> <%= hobby %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>education :</b><span class='education'> <%= detail_edu %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>faculty :</b><span class='faculty'> -  </span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>major :</b><span class='major'> - </span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>phone :</b><span class='phone'> <%= phone %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>email :</b><span class='email'> <%= email %></span>

  <br/><br/>
</div>

<!-- auto aboutme -->
<div id='aboutme_auto' class='detail-dialog' style='display: block;'>
          <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>fullname :</b><span class='fullname'> <%= fullname %></span> 
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>birthdate :</b><span class='birthdate'> <%= birthdate %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>height :</b><span class='height'> <%= height %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>weight :</b><span class='weight'> <%= weight %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>blood type :</b><span class='blood_type'> <%= blood_type %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>hobby :</b><span class='hobby'> <%= hobby %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>education :</b><span class='education'> <%= detail_edu %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>faculty :</b><span class='faculty'> -  </span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>major :</b><span class='major'> - </span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>phone :</b><span class='phone'> <%= phone %></span>
  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>email :</b><span class='email'> <%= email %></span>

  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' style='color: blue;' onclick='closeAutoAbt()'><ins>view others...click</ins></a>
</div>

<div class='detail-dialog' id='editBlock'> 
      <ins><h2>Edit</h2></ins>
<form method="post" action="EditMember">
     <input type="hidden" name="mode" value="edit"/>
     <div>Fullname</div>
     <div>
	<input type="text" value="<%= fullname %>" name="fullname" id="fullname"/>
     </div>
     <div>Birthdate</div>
     <div>
   	<input type="text" name="birthdate" id="birthdate" value="<%= birthdate %>"/>
     </div>
     <div>Height</div>
     <div>
	<input type="text" name="height" id="height" value="<%= height %>"/>	
     </div>
     <div>Weight</div>
     <div>
   	<input type="text" name="weight" id="weight" value="<%= weight %>"/>
     </div>
     <div>Blood type</div>
     <div>
	<select name="blood_type">
		<option value="-">choose</option>
		<option value="A" <% if(blood_type.equals("A")) out.print("selected=selected"); %>>A</option>
		<option value="B" <% if(blood_type.equals("B")) out.print("selected=selected"); %>>B</option>
		<option value="AB" <% if(blood_type.equals("AB")) out.print("selected=selected"); %>>AB</option>
		<option value="O" <% if(blood_type.equals("O")) out.print("selected=selected"); %>>O</option>
	</select>
     </div>
     <div>Hobby</div>
     <div>
	<textarea name="hobby" id="hobby"cols="48" row="5"><%= hobby %></textarea>
     </div>
     <div>Phone</div>
     <div>
	<input type="text" name="phone" id="phone" value="<%= phone %>"/>
     </div>
     <div>Email</div>
     <div>
	<input type="text" name="email" id="email" value="<%= email %>"/>
     </div>
     <div>Education</div>
     <div>
        <textarea name='detail_edu' id='detali_edu' cols='48' rows='5' style='resize: none'>
		<%= detail_edu  %>
	</textarea>
     </div>
     <div>Favorite</div>
     <div> 
        <textarea name='detail_fav' id='detail_fav' cols='48' rows='5' style='resize: none'>
		<%= detail_fav %>
	</textarea>
     </div>
     <div>Little Skill</div> 
     <div>
        <textarea name='detail_lsk' id='detail_lsk' cols='48' rows='5' style='resize: none'>
		<%= detail_lsk %>
	</textarea>
     </div>
     <input type="submit" value="save"/>
     <input onclick='editCancel();' id='edit-cancel' value="cancel" type="button"/>
</form>
</div><!-- end edit form -->

<!--div class="detail-dialog" id="adminViewer">
	<div id="member-list">
		<div class="table">
		</div>
	</div>
	<input type="button" value="close" onclick="document.getElementById('adminViewer').style='display: none'"/>	
</div-->

</body>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous.js"></script>
<script type="text/javascript" src="js/lightbox.js"></script>
</html>
<script type='text/javascript' src='jquery/jquery.js'></script>
<script type='text/javascript' src='jquery-ui/jquery-ui.js'></script>
<script type='text/javascript' src='js/profile.js'></script>




