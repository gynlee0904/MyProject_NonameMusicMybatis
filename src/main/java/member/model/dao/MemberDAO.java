package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import member.model.vo.Member;

public class MemberDAO {

	public int insertMember(SqlSession session, Member member) {
		int result = session.insert("MemberMapper.insertMember",member);
		return result;
	}

	public int modifyMemberInfo(SqlSession session, Member member) {
		int result = session.update("MemberMapper.modifyMemberInfo",member);
		return result;
	}

	public int deleteMember(SqlSession session, String memberEmail) {
		int result = session.delete("MemberMapper.deleteMember",memberEmail);
		return result;
	}

	public Member selectCheckLogin(SqlSession session, Member member) {
		Member mOne = session.selectOne("MemberMapper.selectCheckLogin", member);  
		return mOne;
	}


	public Member selectOneById(SqlSession session, String memberEmail) { //마이페이지
		Member member = session.selectOne("MemberMapper.selectOneById", memberEmail);  
		return member;
	}

	public Member selectCheckMemberId(SqlSession session, Member member) {
		Member mOne = session.selectOne("MemberMapper.selectCheckMemberId", member);  
		return mOne;
	}

	
	
	

	
	
	
	
	
	
	
}
