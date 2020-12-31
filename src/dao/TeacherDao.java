package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import model.Teacher;
import util.StringUtil;

public class TeacherDao extends BaseDao {
	public boolean addTeacher(Teacher teacher) {
		String sql = "insert into s_teacher values(null,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, teacher.getName());
			pstmt.setString(2, teacher.getSex());
			pstmt.setString(3, teacher.getTitle());
			pstmt.setInt(4, teacher.getAge());
			pstmt.setString(5, teacher.getPassword());
			if(pstmt.executeUpdate() > 0) {
				System.out.println("添加一名教师。");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Teacher> getTeacherList(Teacher teacher) {
		List<Teacher> retList = new ArrayList<Teacher>();
		StringBuffer sqlString = new StringBuffer("select * from s_teacher");
		if(!(StringUtil.isEmpty(teacher.getName()))) {
			sqlString.append(" where name like '%" + teacher.getName() + "%'");
		}
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlString.toString().replaceFirst("and", "where"));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Teacher t = new Teacher();
				t.setId(rs.getInt("id"));
				t.setName(rs.getString("name"));
				t.setSex(rs.getString("sex"));
				t.setTitle(rs.getString("title"));
				t.setAge(rs.getInt("age"));
				t.setPassword(rs.getString("password"));
				retList.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retList;
	}
	
	/*
	 * 删除指定id的教师信息
	 */
	public boolean delete(int id) {
		String sql = "delete from s_teacher where id = ?";
		String sql2 = "SET FOREIGN_KEY_CHECKS=0";
		String sql3 = "SET foreign_key_checks = 1";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			PreparedStatement pstmt3 = con.prepareStatement(sql3);
			pstmt2.execute();
			pstmt.setInt(1, id);
			if(pstmt.executeUpdate() > 0) {
				pstmt3.execute();
				System.out.println("删除教师：id = " + id);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 修改选定教师信息
	 */
	public boolean update(Teacher teacher) {
		String sql = "update s_teacher set name = ? ,sex = ? ,title = ?, age = ?, password = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, teacher.getName());
			pstmt.setString(2, teacher.getSex());
			pstmt.setString(3, teacher.getTitle());
			pstmt.setInt(4, teacher.getAge());
			pstmt.setString(5, teacher.getPassword());
			pstmt.setInt(6, teacher.getId());
			if(pstmt.executeUpdate() > 0) {
				System.out.println("教师信息修改成功！");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*
	 * 教师登陆
	 */
	public Teacher login(Teacher teacher){
		String sql = "select * from s_teacher where name=? and password=?";
		Teacher teacherRst = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
			prst.setString(1, teacher.getName());
			prst.setString(2, teacher.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				teacherRst = new Teacher();
				teacherRst.setId(executeQuery.getInt("id"));
				teacherRst.setName(executeQuery.getString("name"));
				teacherRst.setPassword(executeQuery.getString("password"));
				teacherRst.setSex(executeQuery.getString("sex"));
				teacherRst.setAge(executeQuery.getInt("age"));
				teacherRst.setTitle(executeQuery.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherRst;
	}
	
	/*
	 * 教师修改密码
	 */
	public String editPassword(Teacher teacher,String newPassword){
		String sql = "select * from s_teacher where id=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);
			prst.setInt(1, teacher.getId());
			prst.setString(2, teacher.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(!executeQuery.next()){
				String retString = "旧密码错误！";
				return retString;
			}
			id = executeQuery.getInt("id");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}//把sql语句传给数据库操作对象
		String retString = "修改失败";
		String sqlString = "update s_teacher set password = ? where id = ?";
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
			if(rst > 0){
				retString = "密码修改成功！";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}//把sql语句传给数据库操作对象
		return retString;
	}
}
