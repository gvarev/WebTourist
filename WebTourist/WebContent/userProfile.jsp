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
			<s:url id="redirect" action="createPortfolioRedirect" />
			<s:a href="%{redirect}">
				<s:i18n name="global">
					<s:text name="global.createPortfolio" />
				</s:i18n>
			</s:a>
		</div>
		<div class="topNaviagationLink">
			<s:url id="viewMessages" action="viewMessages" />
			<s:a href="%{viewMessages}">
				<s:i18n name="global">
					<s:text name="global.viewMessages" />
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
				<h2>
					<s:i18n name="global">
						<s:text name="global.edit" />
					</s:i18n>
				</h2>
				<br /> <img src="<s:property value="avatarName"/>" width="120"
					height="120" /><br />
				<s:i18n name="global">
					<s:text name="global.aboutMe" />
				</s:i18n>
				<pre>
						<s:property value="aboutMe" />
					</pre>
				<hr />
				<s:actionerror />
				<s:form action="editProfile">
					<s:textarea rows="10" cols="40" name="interests"
						key="global.aboutMe" />
					<s:submit key="global.editAboutMe" />
				</s:form>
				<hr/>
					<s:form action="editUserAvatar" method="post"
					enctype="multipart/form-data">
					<s:file name="avatar" key="global.userImage" />
					<s:submit key="global.upload" />
				</s:form>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<hr />
				<s:form action="changePassword">
					<s:password name="changedPassword" key="global.changePassword" />
					<s:submit key="global.change" />
				</s:form>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<img src="images/pic18.jpg" width="300" height="225"
					style="margin-left: 160px" align="center" />

			</div>
		</div>

	</div>
	<div id="footer"></div>
</body>
</html>
