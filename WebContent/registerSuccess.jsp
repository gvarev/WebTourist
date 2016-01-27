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
			<a href="index.html">Home</a>
		</div>
		<div class="topNaviagationLink">
			<a href="index.html">About</a>
		</div>
		<div class="topNaviagationLink">
			<a href="index.html">Portfolio</a>
		</div>
		<div class="topNaviagationLink">
			<a href="index.html">Services</a>
		</div>
		<div class="topNaviagationLink">
			<a href="index.html">Contact</a>
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
			<div class="contentText">
				<s:i18n name="global">
					<s:text name="global.registerSuccess" />
				</s:i18n>
				&nbsp;&nbsp;
				<s:property value="userName" />
				<br /> &nbsp;&nbsp;
				<s:i18n name="global">
					<s:text name="global.youCan" />
				</s:i18n>
				<s:url id="login" action="redirectLogin" />
				<s:a href="%{login}">
					<s:i18n name="global">
						<s:text name="global.login" />
					</s:i18n>
				</s:a>
				<p>&nbsp;</p>
				<img src="images/pic19.jpg" width="300" height="225"
					style="margin-left: 160px" align="center" />

			</div>
		</div>

	</div>
	<div id="footer"></div>
</body>
</html>
