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

}
