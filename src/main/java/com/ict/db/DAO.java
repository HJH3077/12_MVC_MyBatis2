package com.ict.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class DAO {
	private static SqlSession ss;

	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession(false); // 수동 commit
		}
		return ss;
	}

	// list
	public static List<VO> getSelectAll() {
		List<VO> list = new ArrayList<VO>();
		list = getSession().selectList("list");
		return list;
	}

	public static int getInsert(VO vo) {
		int result = 0;
		result = getSession().insert("insert", vo);
		ss.commit();
		return result;
	}

	// 상세 보기
	public static VO selectOne(String idx) {
		VO vo = null;
		vo = getSession().selectOne("onelist", idx);
		return vo;
	}

	public static int getUpdate(VO vo) {
		int result = 0;
		result = getSession().update("update", vo);
		ss.commit();
		return result;
	}

	public static int getDelete(VO vo) {
		int result = 0;
		result = getSession().delete("delete", vo);
		ss.commit();
		return result;
	}
}
