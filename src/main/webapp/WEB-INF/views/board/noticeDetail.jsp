<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판 내용 보기</title>
	</head>
	<body>
	<div>
		<ul>
			<li>
				<label>글번호</label>
				<span>${requestScope.notice.noticeNo }</span> 
			</li>
			<li>
				<label>작성일</label>
				<span>${notice.noticeDate }</span>
			</li>
			<li>
				<label>글쓴이</label>
				<span>${notice.noticeWriter }</span>
			</li>
			<li>
				<label>제목</label>
				<span>${notice.noticeSubject }</span>
			</li>
			<li>
				<label>내용</label>
				<p>${ notice.noticeContent }</p> 
			</li>	
		</ul>
	</div>
	<div>
		<button type="button" name="goToList" value="" onclick="noticeList();">목록으로</button>
		<button type="button" name="modify" value="" onclick="noticeModify();">수정</button>
		<button type="button" name="delete" value="" onclick="noticedelete();">삭제</button>
	</div>
	
	
	<script>
	
		function noticeList(){
			location.href = "/notice/list.do?currentPage=1";
		}
	
	
	
		function noticeModify(){
			const noticeNo = '${notice.noticeNo}'
			location.href = "/notice/modify.do?noticeNo="+noticeNo;
		}
		
		function noticedelete(){
			const noticeNo = '${notice.noticeNo}'
			location.href = "/notice/delete.do?noticeNo="+noticeNo;
		}
	
	</script>
		
	</body>
</html>