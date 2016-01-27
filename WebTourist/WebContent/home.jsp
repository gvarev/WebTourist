<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="anoceanofsky.css" />
<title>WebTourist</title>
</head>

<body>
	<div id="page">
		<div class="topNaviagationLink">
			<s:url id="login" action="redirectLogin" />
			<s:a href="%{login}">
				<s:i18n name="global">
					<s:text name="global.login" />
				</s:i18n>
			</s:a>
		</div>
		<div class="topNaviagationLink">
			<s:url id="register" action="redirectRegister" />
			<s:a href="%{register}">
				<s:i18n name="global">
					<s:text name="global.register" />
				</s:i18n>
			</s:a>
		</div>
		<div class="topNaviagationLink">
			<a href="index.jsp"><s:text name="global.index" /></a>
		</div>
	</div>
	<div id="mainPicture">
		<div class="picture">
			<div id="headerTitle">WebTourist</div>
		</div>
	</div>
	<div class="contentBox">
		<div class="innerBox">
			<div class="contentTitle"></div>
			<div class="contentText"></div>
			WebTourist is a web application where you can share your experiences
			with other users.<br /> Every picture you upload is part of a
			portfolio.<br /> Every portfolio has title and description by which
			you help people who will later browse through your photos,<br /> get
			a general idea of the place you have visited.<br />
			Here you can upload pictures not only from nature sightings, but from<br/>
			cultural and architectural. You can send personal messages to every user, especially to <br/>
			the once uploaded a particular set of pictures, to ask them about more <br/>
			information about the place which the have taken pictures on.
			<br/> &nbsp;<p></p><img
				src="images/pic14.jpg" width="300" height="225"
				style="margin-left: 160px" align="center" /></p>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
