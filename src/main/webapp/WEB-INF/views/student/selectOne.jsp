<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 실습</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>
<style>
div#student-container{text-align:center;}
table.tbl-student{margin:10px auto;border:1px solid; border-collapse:collapse;}
table.tbl-student th,table.tbl-student td{
	border:1px solid;
	padding:5px;
	line-height: 2em; /*input태그로 인해 cell이 높이가 길어져 border 두줄현상방지 */
}
table.tbl-student th{text-align:right;}
table.tbl-student td{text-align:left;}
table.tbl-student tr:last-of-type td:first-child{text-align:center;}
</style>
</head>
<body>
	<div id="student-container">
		<h2>학생정보 검색</h2>
		<p>등록된 학생수는 <span id="totalCount"></span>명입니다.</p>
		
		<h3>DTO로 가져오기</h3>
		<form name="studentSearchFrm">
			<table class="tbl-student">
				<tr>
					<th>학생번호</th>
					<td>
						<input type="number" name="no" value="" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
		
		<h3>MAP으로 가져오기</h3>
		<form name="studentMapSearchFrm">
			<table class="tbl-student">
				<tr>
					<th>학생번호</th>
					<td>
						<input type="number" name="no" value="" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
		
		<h2>학생 정보 수정</h2>
        <form name="studentUpdateFrm">
            <table class="tbl-student">
                <tr>
                    <th>학생번호</th>
                    <td>
                        <input type="number" name="no" required readonly/>
                    </td>
                </tr>
                <tr>
                    <th>학생이름</th>
                    <td>
                        <input type="text" name="name" required/>
                    </td>
                </tr>
                <tr>
                    <th>학생전화번호</th>
                    <td>
                        <input type="tel" name="tel"  required/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" value="수정" onclick="updateStudent();"/>
                        <input type="button" value="삭제" onclick="deleteStudent();" />
                    </td>
                </tr>
            </table>
        </form>
		
	</div>
	<script>
	/* 학생정보삭제 */
	const deleteStudent = () => {
		if(!confirm("해당 학생 정보를 정말 삭제하겠습니까?")){
			return;
		}
		
		const frm = document.studentUpdateFrm;
		const no = frm.no.value;
		
		$.ajax({
			url : "${pageContext.request.contextPath}/student/studentDelete.do",
			method : "POST",
			data : {no},
			success(data){
				console.log(data);
				if(data > 0) alert("해당 학생의 정보를 삭제했습니다🥲");
				else alert("해당 학생의 정보 삭제에 실패했습니다😣");
				
				/* 초기화 */
				frm.no.value = "";
				frm.name.value = "";
				frm.tel.value = "";
			},
			error : console.log
		});
	};
	
	/* 학생정보수정 */
	const updateStudent = () => {
		const frm = document.studentUpdateFrm;
		const no = frm.no.value;
		const name = frm.name.value;
		const tel = frm.tel.value;
		$.ajax({
			url: "${pageContext.request.contextPath}/student/studentUpdate.do",
			method : "POST",
			data: {no, name, tel},
			success(data){
				console.log(data);
				if(data > 0) alert("해당 학생의 정보를 수정했습니다😆");
				else alert("해당 학생 정보 수정에 실패했습니다😣")
				
				/* 초기화 */
				frm.no.value = "";
				frm.name.value = "";
				frm.tel.value = "";
			},
			error: console.log
		});	
	};
	
	/* map으로 조회 */
	document.studentMapSearchFrm.addEventListener('submit', (e) => {
		e.preventDefault();
		const no = e.target.no;
		if(!no.value) return;
		
		$.ajax({
			url : '${pageContext.request.contextPath}/student/studentMap.do',
			data : {no : no.value},
			success(data){
				console.log(data);
			},
			error : console.log
		});
	});
	
	/* dto로 조회 */
	document.studentSearchFrm.addEventListener('submit', (e) => {
		e.preventDefault(); // 폼제출 방지
		
		const no = e.target.no;
		if(!no.value) return;
		
		$.ajax({
			url: "${pageContext.request.contextPath}/student/student.do",
			data: {no : no.value},
			success(data){
				console.log(data);
				if(data){
					const {no, name, tel} = data;
					const frm = document.studentUpdateFrm;
					frm.no.value = no;
					frm.name.value = name;
					frm.tel.value = tel;
				}
				else {
					alert("해당 학생은 존재하지 않습니다.");
				}
				
				no.value = ''; // 초기화
			},
			error : console.log
		});
	});
	
	// iife immediately invoked function expression
	(() => {
		$.ajax({
			url: "${pageContext.request.contextPath}/student/getTotalCount.do",
			success(data){
				console.log(data);
				const {totalCount} = data;
				
				document.querySelector("#totalCount").innerHTML = totalCount;
				
			},
			error: console.log
		});
	})();	
	</script>
</body>
</html>
