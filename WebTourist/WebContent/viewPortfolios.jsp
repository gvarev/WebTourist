<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
.errorMessage {
	color: red;
	font-size: 1em;
}
/* ----------------------------------------------------------------------------------------------------------------*/
/* -------->> global settings needed for thickbox <<<-----------------------------------------------------------*/
/* ----------------------------------------------------------------------------------------------------------------*/
* {
	padding: 0;
	margin: 0;
}

/* ----------------------------------------------------------------------------------------------------------------*/
/* -------->> thickbox specific link and font settings <<<------------------------------------------------------*/
/* ----------------------------------------------------------------------------------------------------------------*/
#TB_window {
	font: 12px Arial, Helvetica, sans-serif;
	color: #333333;
}

#TB_secondLine {
	font: 10px Arial, Helvetica, sans-serif;
	color: #666666;
}

#TB_window a:link {
	color: #666666;
}

#TB_window a:visited {
	color: #666666;
}

#TB_window a:hover {
	color: #000;
}

#TB_window a:active {
	color: #666666;
}

#TB_window a:focus {
	color: #666666;
}

/* ----------------------------------------------------------------------------------------------------------------*/
/* -------->> thickbox settings <<<-----------------------------------------------------------------------------*/
/* ----------------------------------------------------------------------------------------------------------------*/
#TB_overlay {
	position: fixed;
	z-index: 100;
	top: 0px;
	left: 0px;
	height: 100%;
	width: 100%;
}

.TB_overlayMacFFBGHack {
	background: url(macFFBgHack.png) repeat;
}

.TB_overlayBG {
	background-color: #000;
	filter: alpha(opacity =             75);
	-moz-opacity: 0.75;
	opacity: 0.75;
}

* html #TB_overlay { /* ie6 hack */
	position: absolute;
	height: expression(document.body.scrollHeight >           
		   document.body.offsetHeight ?     
		  
		      document.body.scrollHeight :             
		 document.body.offsetHeight +   
		     
		     'px');
}

#TB_window {
	position: fixed;
	background: #ffffff;
	z-index: 102;
	color: #000000;
	display: none;
	border: 4px solid #525252;
	text-align: left;
	top: 50%;
	left: 50%;
}

* html #TB_window { /* ie6 hack */
	position: absolute;
	margin-top: expression(0 -               parseInt(this.offsetHeight/ 2)+
		 
		     (TBWindowMargin=   
		
		
		
		 document.documentElement&&        document.documentElement.scrollTop||
		
		
		 
		   document.body.scrollTop )+        'px' );
}

#TB_window img#TB_Image {
	display: block;
	margin: 15px 0 0 15px;
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	border-top: 1px solid #666;
	border-left: 1px solid #666;
}

#TB_caption {
	height: 25px;
	padding: 7px 30px 10px 25px;
	float: left;
}

#TB_closeWindow {
	height: 25px;
	padding: 11px 25px 10px 0;
	float: right;
}

#TB_closeAjaxWindow {
	padding: 7px 10px 5px 0;
	margin-bottom: 1px;
	text-align: right;
	float: right;
}

#TB_ajaxWindowTitle {
	float: left;
	padding: 7px 0 5px 10px;
	margin-bottom: 1px;
}

#TB_title {
	background-color: #e8e8e8;
	height: 27px;
}

#TB_ajaxContent {
	clear: both;
	padding: 2px 15px 15px 15px;
	overflow: auto;
	text-align: left;
	line-height: 1.4em;
}

#TB_ajaxContent.TB_modal {
	padding: 15px;
}

#TB_ajaxContent p {
	padding: 5px 0px 5px 0px;
}

#TB_load {
	position: fixed;
	display: none;
	height: 13px;
	width: 208px;
	z-index: 103;
	top: 50%;
	left: 50%;
	margin: -6px 0 0 -104px; /* -height/2 0 0 -width/2 */
}

* html #TB_load { /* ie6 hack */
	position: absolute;
	margin-top: expression(0 -               parseInt(this.offsetHeight/ 2)+
		 
		     (TBWindowMargin=   
		
		
		
		 document.documentElement&&        document.documentElement.scrollTop||
		
		
		 
		   document.body.scrollTop )+        'px' );
}

#TB_HideSelect {
	z-index: 99;
	position: fixed;
	top: 0;
	left: 0;
	background-color: #fff;
	border: none;
	filter: alpha(opacity =             0);
	-moz-opacity: 0;
	opacity: 0;
	height: 100%;
	width: 100%;
}

* html #TB_HideSelect { /* ie6 hack */
	position: absolute;
	height: expression(document.body.scrollHeight >           
		   document.body.offsetHeight ?     
		  
		      document.body.scrollHeight :             
		 document.body.offsetHeight +   
		     
		     'px');
}

#TB_iframeContent {
	clear: both;
	border: none;
	margin-bottom: -1px;
	margin-top: 1px;
	_margin-bottom: 1px;
}

.thickbox img {
	border: none;
}

<!--
.errorMessage {
	color: red;
	font-size: 1em;
}
-->
</style>
<link rel="stylesheet" type="text/css" href="anoceanofsky.css" />
<title>WebTourist</title>
<script src="jquery-latest.js" type="text/javascript"></script>
<script src="thickbox.js" type="text/javascript"></script>
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
				<s:i18n name="global">
					<s:text name="global.uploadedBy" />
				</s:i18n>
				<br />
				<table width="628" border="">
					<tr>
						<td width="132" height="22" scope="col"><s:url id="profile"
								action="viewProfile" />&nbsp; <s:a href="%{profile}">
								<s:property value="userName" />
							</s:a> <br /> &nbsp;&nbsp; <img src="<s:property value="userAvatar"/>"
							width="120" height="120" /></td>
						<td width="480" scope="col">
								<div
									style="width: 490px; height: 130px; overflow-y: scroll; padding: 4px; border: 1px;">
									<pre>
										<s:property value="description" />
									</pre>
								</div>

							</td>
					</tr>
				</table>
				<br />
				<hr />
				<br />
				<s:iterator value="pictureNames">&nbsp;&nbsp;
					<a href="<s:property/>" class="thickbox" rel="thickbox_slide1"><img
						src="<s:property/>" width="100" height="66" /></a>
				</s:iterator>
				<p>&nbsp;</p>
				<s:form action="viewPortfolio">
					<s:select name="title" list="titleList" key="global.chooseTitle" />
					<s:submit key="global.viewPictures" />
				</s:form>
				<hr />
				<p>&nbsp;</p>
				<s:form action="insertComment" requiredposition="left">
					<s:textarea cols="40" rows="10" name="comment" />
					<s:submit key="global.addComment" />
				</s:form>
				<p>&nbsp;</p>
				<p>&nbsp;</p>

				<s:iterator value="comments" status="st">
					<table width="628" border="" height="130">
						<tr>
							<td width="132" height="22" scope="col"><img
								src="<s:property value="value"/>" width="120" height="120" /> <s:form
									action="viewUserProfile">
									<s:hidden name="commentId" value="%{#st.index}" />
									<s:submit key="global.viewProfile" />
								</s:form></td>
							<td width="480" scope="col">
								<div
									style="width: 490px; height: 130px; overflow-y: scroll; padding: 4px; border: 1px;">
									<pre>
										<s:property value="key" />
									</pre>
								</div>

							</td>
						</tr>
					</table>&nbsp;
				</s:iterator>

				<p>&nbsp;</p>
				<s:url id="redirect" action="createPortfolioRedirect" />
				<s:a href="%{redirect}">
					<s:i18n name="global">
						<s:text name="global.createPortfolio" />
					</s:i18n>
				</s:a>
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
