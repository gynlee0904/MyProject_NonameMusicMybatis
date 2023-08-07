package member.model.service;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;

import common.JDBCTemplate;
import common.SqlSessionTemplate;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {
	private MemberDAO mDao;
	
	public MemberService() {
		mDao = new MemberDAO();
	}

	public int insertMember(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = mDao.insertMember(session, member);
		if(result>0) {
			session.commit();
		}else {
			session.rollback();
		}session.close();
		return result;
	}

	public int modifyMemberInfo(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession(); 
		int result = mDao.modifyMemberInfo(session, member);
		if(result > 0) {
				session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return result;  //결과값 리턴
	}



	public Member loginCheck(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Member mOne = mDao.selectCheckLogin(session, member);
		session.close();
		return mOne;
	}

	

	public Member selectOneById(String memberEmail) {  	//마이인포
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Member member = mDao.selectOneById(session, memberEmail);
		session.close();	
		return member;
		
	
	}

	public int deleteMember(String memberEmail) {
		SqlSession session = SqlSessionTemplate.getSqlSession();    
		int result = mDao.deleteMember(session, memberEmail);
		if(result > 0) {
				session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return result;  
	}

	

	


	
	
}
