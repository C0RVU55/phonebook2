<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- 
<%@page import="com.javaex.dao.PhoneDao" %>
<%@page import="com.javaex.vo.PhoneVo" %>
 --%>
 
<%-- 
<%
	int personId = Integer.parseInt(request.getParameter("id")); //id파라미터를 가지고 들어옴. 이게 수정할 데이터 조건이라서 필수.

	// 위 id 파라미터와 attribute한 PhoneVo를 받음. 
	PhoneVo pVo = (PhoneVo)request.getAttribute("pVo");
	
	// 포워드받는 jsp파일은 최대한 db랑 연결 안 하고 html로만 짜는 게 좋음.
	// PhoneDao pDao = new PhoneDao();
	// PhoneVo pVo = pDao.getPerson(personId); 
%>
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>phone book model 2</title>
</head>
<body>

	<h1>이다현 전화번호 수정화면</h1>
	<p>
		수정 화면입니다.
		아래 항목을 수정하고 "수정"버튼을 누르세요.
	</p>
	
	<form action="/phonebook2/pbc" method="get">
		이름(name) : <input type="text" name="name" value=""><br> 
		핸드폰(hp) : <input type="text" name="hp" value="${pVo.hp}"><br>
		회사(company) : <input type="text" name="company" value="${pVo.company}"><br>
		코드(id) : <input type="hidden" name="id" value="${id}"> <br>
		
		action <input type="text" name="action" value="update"> <br>
		
		<button type="submit">수정</button>
	</form>
	
</body>
</html>