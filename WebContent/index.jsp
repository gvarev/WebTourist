<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="anoceanofsky.css" />
<title>WebTourist</title>
</head>

<body>
	
	<div id="mainPicture">
		<div class="picture">
			<div id="headerTitle">WebTourist</div>
		</div>
	</div>
	<div class="contentBox">
		<div class="innerBox">
			<div class="contentTitle"></div>
			<div class="contentText">
				<p>Welcome to WebTourist, please select your language!</p>
				<p>Добре дошли в WebTourist, моля изберете своя език!</p>

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:url id="localeEN" namespace="/" action="locale">
					<s:param name="request_locale">en</s:param>
				</s:url>

				&nbsp;&nbsp;
				<s:url id="localeBG" namespace="/" action="locale">
					<s:param name="request_locale">bg</s:param>
				</s:url>
				<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:a href="%{localeEN}">
					<img src="images/english_flag.gif" />
				</s:a>
				&nbsp;&nbsp;
				<s:a href="%{localeBG}">
					<img src="images/BG_flag.gif" />
				</s:a>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<img src="images/pic6.jpg" width="300" height="225"
					style="margin-left: 160px" align="center" />

			</div>
		</div>

	</div>
	<div id="footer"></div>
</body>
</html>
