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
			<s:url id="editProfile" action="profileRedirect" />
			<s:a href="%{editProfile}">
				<s:i18n name="global">
					<s:text name="global.profile" />
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
				<img src="images/<s:property value="messageImage"/>" />
				<hr/>
				<h2>
					<s:i18n name="global">
						<s:text name="global.createPortfolio" />
					</s:i18n>
				</h2>
				<br />
				<s:actionerror />
				<s:form action="uploadImage.action" method="post"
					enctype="multipart/form-data">

					<s:textarea rows="10" cols="40" name="description"
						key="global.description" />
					<s:textfield name="portfolioName" key="global.title" />
					<s:file name="userImages" key="global.userImage" />
					<s:file name="userImages" key="global.userImage" />
					<s:file name="userImages" key="global.userImage" />
					<s:file name="userImages" key="global.userImage" />
					<s:file name="userImages" key="global.userImage" />
					<s:file name="userImages" key="global.userImage" />
					<s:submit key="global.upload" align="center" />
				</s:form>

				<s:form action="viewPortfolio">
					<s:select name="title" list="titleList" key="global.chooseTitle"  />
					<s:submit key="global.viewPictures" />
				</s:form>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<img src="images/pic11.jpg" width="300" height="225"
					style="margin-left: 160px" align="center" />

			</div>
		</div>

	</div>
	<div id="footer"></div>
</body>
</html>
