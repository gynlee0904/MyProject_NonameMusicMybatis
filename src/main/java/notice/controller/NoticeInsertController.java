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
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/notice/insert.do")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글쓰기 버튼 누르면 작동하는 코드 (두겟) 단순 페이지 이동
		request.getRequestDispatcher("/WEB-INF/views/board/noticeInsert.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//작성페이지에서 글 올리기 버튼 누르면 작동하는 코드 
		request.setCharacterEncoding("UTF-8");
		String noticeSubject = request.getParameter("noticeSubject");
		String noticeContent = request.getParameter("noticeContent");
		
		Notice notice = new Notice(noticeSubject, noticeContent);
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		
		if (result > 0) {
			//성공하면 공지사항 리스트로 이동
			//without data 페이지 이동으로
			response.sendRedirect("/notice/detail.do");
			
		}else {
			//실패하면 실패메세지 출력, 이전페이지 이동 클릭시 작성페이지로 이동
			request.setAttribute("msg", "공지가 등록완료되지 않았습니다");
			request.setAttribute("url", "/notice/insert.do");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFailed.jsp").forward(request, response); 
	
		}
	}

}
