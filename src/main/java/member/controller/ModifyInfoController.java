package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class ModifyInfoController
 */
@WebServlet("/member/modifyInfo.do")
public class ModifyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//마이페이지에서 수정하기 버튼을 눌렀을때 작동되는것(두겟)
		request.getRequestDispatcher("/WEB-INF/views/member/modifyInfo.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  //인코딩
		//쿼리문 : UPDATE MEMBER_TBL SET 레슨장소, 레슨, 레슨타입, 강사성별, 학생레벨, 하고싶은말 WHERE 이멜, 비번
		String memberEmail = request.getParameter("member-email");
		String memberPw = request.getParameter("member-pw");
		String [] places = request.getParameterValues("visit");
		String [] lessons = request.getParameterValues("lesson");
		String lessonType = request.getParameter("type");
		String tGender = request.getParameter("gender");
		String myLevel = request.getParameter("level");
		String freeWords = request.getParameter("freeWords");
		
		String place = String.join(",", places);
		String lesson = String.join(",", lessons);
		
		MemberService service = new MemberService();
		Member member = new Member(memberEmail, memberPw, place, lesson, lessonType, tGender, myLevel, freeWords);
		int result = service.modifyMemberInfo(member);
		if(result > 0) {
//			HttpSession session = request.getSession();
//			session.setAttribute("memberEmail", memberEmail);
//			session.setAttribute("memberPw", memberPw);
//			session.setAttribute("place", place);
//			session.setAttribute("lesson", lesson);
//			session.setAttribute("lessonType", lessonType);
//			session.setAttribute("tGender", tGender);
//			session.setAttribute("myLevel", myLevel);
//			session.setAttribute("freeWords", freeWords);
//			request.getRequestDispatcher("/WEB-INF/views/member/myPage.jsp")
//			.forward(request, response);
//			response.sendRedirect("/member/myInfo.do?memberEmail="+memberEmail);
			request.setAttribute("msg", "회원정보수정 성공!");
			request.setAttribute("url", "/member/myInfo.do?memberEmail="+memberEmail);
			RequestDispatcher view
			= request.getRequestDispatcher("/WEB-INF/views/common/serviceSuccess.jsp");
			view.forward(request, response);
			
		}else {
			// 실패
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFailed.jsp")
			.forward(request, response);
		}
	}

}
