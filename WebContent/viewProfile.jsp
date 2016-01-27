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
		&nbsp;
		<div class="topNaviagationLink">
			<s:url id="redirect" action="createPortfolioRedirect" />
			<s:a href="%{redirect}">
				<s:i18n name="global">
					<s:text name="global.createPortfolio" />
				</s:i18n>
			</s:a>
		</div>
		<div class="topNaviagationLink">
			<s:url id="editProfile" action="profileRedirect" />
			<s:a href="%{editProfile}">
				<s:i18n name="global">
					<s:text name="global.profile" />
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
				<s:property value="userName" />
				<br /> <img src="<s:property value="avatarName"/>" width="120"
					height="120" /><br />
				<s:text name="global.aboutMe" />
				&nbsp;
				<pre>
					<s:property value="aboutMe" />
				</pre>

				<br />
				<hr />
				<br />
				<s:i18n name="global">
					<s:text name="global.sendMessage" />
				</s:i18n>
				<br />
				<s:form action="sendMessage">
					<s:textarea cols="40" rows="10" name="message" />
					<s:submit key="global.send" />
				</s:form>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<hr />
				<s:form action="viewPortfolio">
					<s:select name="title" list="titleList" key="global.PortfolioUploadedByUser" />
					<s:submit key="global.viewPictures" />
				</s:form>
				<br /> <img src="images/pic4.jpg" width="300" height="225"
					style="margin-left: 160px" align="center" />

			</div>
		</div>

	</div>
	<div id="footer"></div>
</body>
</html>
