<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 수정</title>
<style type="text/css">
a {
	text-decoration: none;
}

table {
	width: 600px;
	border-collapse: collapse;;
	text-align: center;
}

table, th, td {
	border: 1px solid black;
	padding: 5px;
	margin: auto;
}

div {
	width: 600px;
	margin: 100px auto;
	text-align: center;
}

.bg {
	background-color: #99ccff
}

input {
	padding: 5px;
}
</style>
<script type="text/javascript">
	function update_ok(f) {
		// 비밀번호 체크
		if (f.pw.value == "${vo.pw}") {
			f.action="${pageContext.request.contextPath}/MyController?cmd=update_ok";
			alert("수정완료");
			f.submit();
		} else {
			alert("비밀번호틀림");
			f.pw.value = "";
			f.pw.focus();
			return;
		}
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 수정화면</h2>
		<hr>
		<p>[ <a href="${pageContext.request.contextPath}/MyController?cmd=list">목록으로 이동</a> ]</p>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tbody>
					<tr><th class="bg">작성자</th> <td><input type="text" name="name" value="${vo.name }"></td></tr>
					<tr><th class="bg">제목</th> <td><input type="text" name="title" value="${vo.title }"></td></tr>
					<tr><th class="bg">email</th> <td><input type="text" name="email" value="${vo.email }"></td></tr>
					<tr><th class="bg">첨부파일</th> 
						<td>
							<c:choose>
								<c:when test="${empty vo.f_name}">
									<input type="file" name="f_name">이전파일없음
									<input type="hidden" name="f_name2" value="">
								</c:when>
								<c:otherwise>
								    <input type="file" name="f_name">
									<input type="hidden" name="f_name2" value="${vo.f_name}">이전파일(${vo.f_name})
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr><th class="bg">비밀번호</th> <td><input type="password" name="pw" ></td></tr>
					<tr>
					   <td colspan="2">
					   		<textarea rows="10" cols="60" name="content">${vo.content }</textarea>
					   </td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="button" value="수정" onclick="update_ok(this.form)"> 
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>