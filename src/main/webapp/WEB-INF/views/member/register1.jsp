<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>회원가입</title>
        <link rel="stylesheet" href="../resources/css/register1.css">
        <link rel="stylesheet" href="../resources/css/reset.css">
        <!-- 헤더 폰트 -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Serif:wght@500&display=swap" rel="stylesheet">
        <!-- 본문 폰트 -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Hahmlet:wght@300&display=swap" rel="stylesheet">
    </head>
    <body>
        <div id="container">
            <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>  		<!-- 헤더 -->
            <jsp:include page="/WEB-INF/views/include/nav.jsp"></jsp:include>  			<!-- 나브 -->
            
            <main>
                <section id="main-layer1" class="">
                    <h1>회원 가입</h1>
                </section>
				<form action="" method="">
                <section id="main-layer2" class="">
                    <div id="textarea">약관</div> <br>
                   
                        <label id="agree"><div id="check"><input type="checkbox" name="check_agr" id="check_agr" >동의합니다(필수)</div></label>
                </section>

                <section id="main-layer3" class="">
	                <div id="findBox">
	                    <div id="box_header2">
	                        <div id="check_member">회원 인증</div>
	                        <div id="check_phone">
	                            <input type="text" name="checkPhnNum" id="checkNum" placeholder="휴대폰 번호를 입력하세요">
	                        </div>
	                        <div id="check_btn">
	                            <button type="button" name="phoneChk" id="btn" onclick="">휴대폰인증</button>
	                        </div>
	                    </div>
	                </div>
                </section>

                <section id="main-layer4" class="">
					<button type="submit" class="next_btn" onclick="navigateToSelectedPage" name="position" value="std">학생으로 가입&nbsp&nbsp>></button>
                    <button type="submit" class="next_btn" onclick="navigateToSelectedPage" name="position" value="tch">강사로 가입&nbsp&nbsp>></button>
                </section>
             </form> 
            </main>     
            
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
            
        </div>
        <script>
        <!-- 		function navigateToSelectedPage() { -->
        <!--             var selectedOption = document.querySelector('input[name="position"]:checked').value; -->
        <!--             var form = document.getElementById('myForm'); -->
        <!--             if (selectedOption === "std") { -->
        <!--                 form.action = "/member/register2.do?position=std";  -->
        <!--             } else if (selectedOption === "tch") { -->
        <!--                 form.action = "/member/register3.do?position=tch";  -->
        <!--             } -->
        <!--             form.submit(); -->
        <!--         } -->
                
             		function navigateToSelectedPage() {
        	            var selectedOption = document.querySelector('input[name="position"]:clicked').value;
        	            var form = document.getElementById('myForm');
        	            if (selectedOption === "std") {
        	                form.action = "/member/register2.do?position=std";
        	                form.method = "post"
        	            } else if (selectedOption === "tch") {
        	                form.action = "/member/register3.do?position=tch"; 
        	                form.method = "post"
        	            }
        	            form.submit();
        <!-- 	            ?check_agr=on&checkPhnNum=01001010202&position=std -->
        	        }

		</script>



    
    </body>
    </html>

