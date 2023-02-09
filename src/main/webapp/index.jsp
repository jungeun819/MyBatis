<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
	<meta charset="utf-8"/>
	<title>Hello Maven & Mybatis</title>
	<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>
</head>
<body>
	<h1>Hello Maven & Mybatis</h1>
	<button id="btn">ajax 테스트</button>
	<script>
	document.querySelector("#btn").addEventListener('click', () => {
		$.ajax({
			url : '${pageContext.request.contextPath}/gson/test',
			success(data){
				console.log(data);
			},
			error : console.log
		});
	});
	</script>
	
	<hr />
	
	<button onclick="location.href = '${pageContext.request.contextPath}/student/selectList.do';">/student/selectList.do</button>
	<button onclick="location.href = '${pageContext.request.contextPath}/student/redirect.do';">/student/redirect.do</button>
	
	<hr />
	
	<h2>mybatis</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/student/studentEnroll.do">학생등록</a></li>
		<li><a href="${pageContext.request.contextPath}/student/selectOne.do">한건 조회</a></li>
	</ul>

</body>
</html>
