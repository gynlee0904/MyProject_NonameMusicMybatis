package notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;


/**
 * Servlet implementation class noticeController
 */
@WebServlet("/notice/list.do")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService service = new NoticeService();
		String page = request.getParameter("currentPage")!=null ? request.getParameter("currentPage"):"1";
		int currentPage = Integer.parseInt(page);
		List<Notice>nList = service.selectNoticeList(currentPage);
		if(!nList.isEmpty()) {
			request.setAttribute("nList", nList);
			request.getRequestDispatcher("/WEB-INF/views/board/noticeList.jsp").forward(request,  response);
		}else {
			request.setAttribute("url", "/index.jsp");  //회원가입페이지로 이동(이동할 유알엘은 에러페이지.jsp에 있음)
			request.setAttribute("msg", "데이터조회가 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFailed.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
