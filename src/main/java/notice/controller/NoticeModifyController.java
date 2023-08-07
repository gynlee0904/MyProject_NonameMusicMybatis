package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeModifyController
 */
@WebServlet("/notice/modify.do")
public class NoticeModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//디테일에서 수정버튼을 누르면 단순 페이지 이동용
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		NoticeService service = new NoticeService();
		Notice notice = service.selectOneByNo(noticeNo);
		if(notice != null) {
			//데이터가 있으면 noticeModify.jsp로 이동 (원래 글 내용 가지고)
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/WEB-INF/views/board/noticeModify.jsp").forward(request, response);			
		}else {
			//데이터가 없으면 에러페이지로 이동 
			request.setAttribute("url", "/notice/detail.do.jsp");  //회원가입페이지로 이동(이동할 유알엘은 에러페이지.jsp에 있음)
			request.setAttribute("msg", "글 수정이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFailed.jsp").forward(request, response);
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String noticeSubject = request.getParameter("noticeSubject");
		String noticeContent = request.getParameter("noticeContent");
		Notice notice = new Notice(noticeNo, noticeSubject, noticeContent);
		NoticeService service = new NoticeService();
		int result = service.updateNotice(notice);
		if(result > 0 ) {
			//수정성공하면 상세페이지 이동 
			response.sendRedirect("/notice/detail.do?noticeNo="+noticeNo);
		}else {
			//실패하면 에러메세지 출력 
			request.setAttribute("url", "/notice/modify.do?noticeNo="+noticeNo);  
			request.setAttribute("msg", "글 수정이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
