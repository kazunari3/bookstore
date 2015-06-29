<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
	<center>
	<h2>ログイン</h2>
	</center>

	<s:form action="/Login" method="post" theme="simple" >
		ログイン名: <s:textfield label="ログイン名" name="account" /> <br>
		パスワード: <s:password label="パスワード" name="passwd" /> <br>
    	<s:submit value="ログイン" />   
	</s:form>

	<s:if test="errorMessage != null">
		<font color="red">
			<s:property value="errorMessage"/>
		</font>
	</s:if>
	<br>

	アカウントを作成していない人は
	<a href="createAccount.jsp">こちら</a>
</body>
</html> 