package notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import notice.model.vo.Notice;

public class NoticeDAO {

	public List<Notice> selectNoticeList(SqlSession session, int currentPage) {
		int limit = 10; 
		int offset = (currentPage-1) *limit ;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice>nList = session.selectList("NoticeMapper.selectNoticeList",null, rowBounds); 
		return nList;
	}

	public int insertNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.insertNotice",notice);
		return result;
	}

	public Notice selectOneByNo(SqlSession session, int noticeNo) {
		Notice notice = session.selectOne("NoticeMapper.selectOneByNo",noticeNo);
		return notice;
	}

	public String generatePageNavi(SqlSession session, int currentPage) {
		//전체게시물의 갯수 
		int totalCount = getTotalCount(session);  //게시글이 늘어날때마다 네비 수 늘어나도록 메소드처리
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		int totalNaviCount;
		
		if(totalCount % recordCountPerPage > 0 ) {
			totalNaviCount = totalCount / recordCountPerPage +1;
		}else {
			totalNaviCount = totalCount / recordCountPerPage;
		}
		
		int startNavi = ((currentPage -1)/naviCountPerPage)* naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if(endNavi > totalNaviCount) {
			endNavi = totalNaviCount;
		}
		StringBuffer result = new StringBuffer();
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi !=1) {
			result.append("<a href='/notice/list.do?currentPage="+(startNavi-1)+"'>[이전]</a>"); 
		}
		
		for(int i = startNavi; i <= endNavi; i++) {
			result.append("<a href='/notice/list.do?currentPage="+i+"'>"+i+"</a>&nbsp;&nbsp;&nbsp;");  
		}
		if (endNavi != totalNaviCount) {
			result.append("<a href='/notice/list.do?currentPage="+(endNavi+1)+"'>[다음]</a>");
		}
		return result.toString();

	}


	private int getTotalCount(SqlSession session) {
		int totalCount = session.selectOne("NoticeMapper.getTotalCount");
		return totalCount;
	}

	public int updateNotice(SqlSession session, Notice notice) {
		int result = session.update("NoticeMapper.updateNotice",notice);
		return result;
	}

	public int deleteNoticeByNo(SqlSession session, int noticeNo) {
		int result = session.delete("NoticeMapper.deleteNoticeByNo",noticeNo);
		return result;
	}

}
