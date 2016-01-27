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
			<s:url id="redirect" action="createPortfolioRedirect" />
			<s:a href="%{redirect}">
				<s:i18n name="global">
					<s:text name="global.createPortfolio" />
				</s:i18n>
			</s:a>
		</div>
		<div class="topNaviagationLink">
			<s:url id="profileRedirect" action="profileRedirect" />
			<s:a href="%{profileRedirect}">
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
				<h2>
					<s:i18n name="global">
						<s:text name="global.messages" />
					</s:i18n>
				</h2>
				<br />
				<s:iterator value="messages" status="st">
					<table width="628" border="">
						<tr>
							<td width="132" height="22" scope="col"><img
								src="<s:property value="value"/>" width="120" height="120" /> <s:form
									action="viewSenderProfile">
									<s:hidden name="messageId" value="%{#st.index}" />
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
					</table>
					<s:form action="deleteMsg">
						<s:hidden name="messageId" value="%{#st.index}" />
						<s:submit value="Delete" align="right" />
					</s:form>
					<s:form action="reply">
						<s:hidden name="messageId" value="%{#st.index}" />
						<s:submit value="Reply" align="right" />
					</s:form>
					<hr />
				</s:iterator>
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
