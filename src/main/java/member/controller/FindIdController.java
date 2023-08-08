package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class FindIdController
 */
@WebServlet("/member/findId.do")
public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/findId.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//SELECT MEMBER_EMAIL FROM MEMBER_TBL WHERE MEMBER_PHONE =?
		String memberPhone = request.getParameter("checkPhone");
		
		Member member = new Member(memberPhone);

		MemberService service = new MemberService();
		Member mOne = service.selectCheckMemberId(member);
		
		if(mOne != null) {
			request.setAttribute("member",mOne);
			//웹인포메이션?은 .jsp로 이동 불가
			RequestDispatcher view
			= request.getRequestDispatcher("/WEB-INF/views/member/findid2.jsp");
			view.forward(request, response); 
//			request.setAttribute("msg", "아이디 찾기 성공!");
//			request.setAttribute("url", "/member/fiidId2.do?memberPhone="+memberPhone);
//			RequestDispatcher view
//			= request.getRequestDispatcher("/WEB-INF/views/common/serviceSuccess.jsp");
//			view.forward(request, response);
			
		}else {
			// 아이디찾기 실패
			request.setAttribute("msg", "확인되는 아이디가 없습니다.");
			RequestDispatcher view
			= request.getRequestDispatcher("/WEB-INF/views/common/serviceFailed.jsp");
			view.forward(request, response); // 누락주의
		}
		
	}

}
