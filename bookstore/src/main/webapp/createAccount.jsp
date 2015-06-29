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
	<h2>アカウント作成</h2>
	</center>

	<s:form action="/CreateAccount" method="post" theme="simple" >
		氏名:<s:textfield label="name" name="name" /><br>
		E-Mail: <s:textfield label="email" name="email "/><br>
		ログイン名: <s:textfield label="account" name="account" /><br>
		パスワード: <s:password label="passwd" name="passwd" /><br>
		パスワード(確認): <s:password label="passwd2" name="passwd2" /><br>
		<s:submit value="アカウント作成" />
		<s:reset  value="リセット"/>
	</s:form>

	<s:if test="errorMessage != null">
		<font color="red">
			<s:property value="errorMessage"/>
		</font>
	</s:if>
	<br>

	<a href="index.jsp">トップへ</a>

</body>

</html>