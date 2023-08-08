package free.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import common.SqlSessionTemplate;
import free.model.vo.FreeBoard;
import notice.model.vo.Notice;


public class BoardDAO {

	public int insertBoard(SqlSession session, FreeBoard board) {
		int result = session.insert("freeBoardMapper.insertBoard",board);
		return result;
	}


	public List<FreeBoard> selectBoardList(SqlSession session, int currentPage) {
		int limit = 10; 
		int offset = (currentPage-1) *limit ;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<FreeBoard>bList = session.selectList("freeBoardMapper.selectBoardList",null, rowBounds); 
		return bList;	
	}


	public FreeBoard selectOneByNo(SqlSession session, int freeBoardNo) {
		FreeBoard board = session.selectOne("freeBoardMapper.selectOneByNo",freeBoardNo);
		return board;
	}


	public String generatePageNavi(SqlSession session, int currentPage) {
		//전체게시물 102 
		//1페이지당 보여줄 게시물의 수 : 20 
		//page범위(네비게이터)의 수 : 6pages
		
		int totalCount = 102;  //전체 게시물의 갯수 //*이후 해야할 것 : 전체 게시물의 갯수를 동적으로 가져와야 함  
		int recordCountPerPage = 20;  //1페이지당 보여줄 게시물 수
		int naviTotalCount = 0;  //네비게이터의 갯수 우선 0으로 초기화
		if(totalCount % recordCountPerPage > 0 ) { //나머지가 있으면 +1
			naviTotalCount = totalCount / recordCountPerPage +1;
		} else {
			naviTotalCount = totalCount / recordCountPerPage;
		}
		//스타트네비, 엔드네비 구하기 
		int naviCountPerPage = 5;  //한 장면에 나오는 숫자네비 숫자를 5까지 한다는 뜻 
		
		//currentPage    startNavi    endNavi
		// 1,2,3,4,5          1			5
		// 6,7,8,9,10         6			10
		// 11,12,13,14,15     11		15
		// 16,17,18,19,20     16  		20  //공차 5인 등차수열     
		int startNavi = ((currentPage-1)/naviCountPerPage)* naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		
		//엔드네비값이 총 범위의 갯수보다 커지는 것을 막기위한 코드 
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		} 
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi ==1) {
			needPrev = false;  //꺼버림
		}
		if (endNavi == naviTotalCount) {
			needNext = false;  //꺼버림
		}
	
		StringBuilder result = new StringBuilder();
		if(needPrev) {
			result.append("<a href='/board/list.do?currentPage="+(currentPage-1)+"'>[이전]</a> ");  
		}
		for(int i = startNavi; i <= endNavi; i++) {
			result.append("<a href='/board/list.do?currentPage="+i+"'>"+i+"</a>&nbsp;&nbsp;&nbsp;");  
		}
		if(needNext) {
			result.append("<a href='/board/list.do?currentPage="+(currentPage+1)+"'>[다음]</a>"); 
		}
		return result.toString();  //int인 리절트를 스트링으로 변환해 반환
	}
	
	


	

	
	



}


