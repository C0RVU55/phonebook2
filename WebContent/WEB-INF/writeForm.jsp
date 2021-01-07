<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 등록</h1>
	
	<p>
		전화번호를 등록하려면<br>
		아래 항목을 기입하고 "등록"버튼을 누르세요.
	</p>
	
	<form action="/phonebook2/pbc" method="get">
		이름(name): <input type="text" name="name" value=""> <br> <!-- name, value값 없으면 비워둠 -->
		핸드폰(hp): <input type="text" name="hp" value=""> <br>
		회사(company): <input type="text" name="company" value=""> <br>
		
		<!-- action: --> <input type="text" name="action" value="insert"> <br> 
		<!-- 주소창에 pbc까지는 위에 쓰는데 action값이 없으니까 전에 받은 거 써놓고 숨김. 그래야 controller의 insert실행문으로 연결됨.-->
		
		<button type="submit">등록</button>
	</form>
	
	<br>
	
	<a href="/phonebook2/pbc?action=list">리스트 바로가기</a>
	
</body>
</html>