package free.model.service;

import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.SqlSessionTemplate;
import free.model.dao.BoardDAO;
import free.model.vo.FreeBoard;
import free.model.vo.PageData;
import notice.model.vo.Notice;



public class BoardService {
	private BoardDAO bDao;
	
	
	
	public BoardService() {
		bDao = new BoardDAO();
		//jdbcTemplate = new JDBCTemplate(); 
		//싱글톤패턴 적용되어있어서 new로 객체 생성 불가 아무나 객체 생성 못하게하고 클래스내에서 객체 유무 확인하고 생성하기 위한게 싱글톤 
		
	}
	

	public int insertBoard(FreeBoard board) {
	
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = bDao.insertBoard(session, board);
		if(result > 0) {
			session.commit();
		}else {
			session.rollback();
		}session.close();
		return result; 
	}


	public PageData selectBoardList(int currentPage) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List<FreeBoard>bList = bDao.selectBoardList(session, currentPage);
		String pageNavi = bDao.generatePageNavi(session, currentPage);
		PageData pd = new PageData(bList, pageNavi);
		session.close();
		return pd; 
	}

	
	public FreeBoard selectOneByNo(int freeBoardNo) {	
		SqlSession session = SqlSessionTemplate.getSqlSession();
		FreeBoard board = bDao.selectOneByNo(session, freeBoardNo);
		session.close();
		return board;
	}

}
