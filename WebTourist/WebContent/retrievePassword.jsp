<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
<!--
.errorMessage {
	color: red;
	font-size: 1em;
}
-->
</style>
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
				<s:text name="global.lostPassword" />
				<s:form action="passwordRecovery">
					<s:textfield name="userName" key="global.username" />
					<s:submit key="global.send" />
				</s:form>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<img src="images/pic15.jpg" width="300" height="225"
					style="margin-left: 160px" align="center" />

			</div>
		</div>

	</div>
	<div id="footer"></div>
</body>
</html>
