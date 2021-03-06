<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
<%@ page import= "java.util.List" %>
<%@ page import= "com.javaex.vo.PhoneVo" %>
 --%>
<%-- 
	서블렛에서 html 쓰기 불편하니까 forward 받을 건 jsp로 만듦 
	phoneController에서 pList(데이터 꺼낼 때 이름은 perList)를 forward시킴.
	주소창으로 접근 못하게 파일 위치를 WebContent > WEB-INF로 함. 밖에서는 WEB-INF가 안 보이지만 안에서는 보이니까 forward시킬 주소 바꿔줌.
--%>

<%-- el, jstl로 바꿈 --> 라이브러리에 jstl 추가하기! --%>

<%--
<%
	// attribute된 데이터 받기 : 리퀘스트는 다 쓰라고 object로 들어오기 때문에 리스트에 맞게 자료형 변환.
			
	List<PhoneVo> pList = (List<PhoneVo>)request.getAttribute("perList");
	System.out.println("-----list.jsp-----");
	System.out.println(pList);
%>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>phone book model 2</title>
</head>
<body>

	<h1>전화번호 리스트</h1>
	<h3>입력한 정보 내역입니다</h3>
	
	<c:forEach items="${perList}" var="p" > <%--변수명 setAttribute했을 때 이름으로 넣어야 됨 --%>
		<table border="1">
			<tr>
				<td>이름(name)</td>
				<td>(${p.personId }) ${p.name }</td> <%--변수명 vo랑 같아야 됨 --%>
			</tr>
			<tr>
				<td>핸드폰(hp)</td>
				<td>${p.hp }</td>
			</tr>
			<tr>
				<td>회사(company)</td>
				<td>${p.company }</td>
			</tr>
			<tr>
				<td><a href="/phonebook2/pbc?action=uform&id=${p.personId }">수정</a></td> <!-- 수정할 때도 id 파라미터로 받음! 잊지말기! -->
				<td><a href="/phonebook2/pbc?action=delete&id=${p.personId }">삭제</a></td>
			</tr>
		</table>
		<br>
	</c:forEach>
	
	<a href="/phonebook2/pbc?action=wform">추가번호 등록</a> 
	<!-- 링크 걸 때 그냥 파일 위치 쓰면 안 되고 controller 거쳐서 action 파라미터값으로 들어가는 주소 써야됨 -->
</body>
</html>