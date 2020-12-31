package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import model.Classes;
import model.Score;
import model.Student;
import util.StringUtil;

public class StudentDao extends BaseDao {
	public boolean addStudent(Student student) {
		String sql = "insert into s_student values(null,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getClassId());
			pstmt.setString(3, student.getSex());
			pstmt.setString(4, student.getPassword());
			if(pstmt.executeUpdate() > 0) {
				System.out.println("添加一名学生。");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 学生信息列表
	 */
	public List<Student> getStudentList(Student student){
		List<Student> retList = new ArrayList<Student>();
		StringBuffer sqlString = new StringBuffer("select * from s_student");
		if(!(StringUtil.isEmpty(student.getName()))) {
			sqlString.append(" and name like '%" + student.getName() + "%'");
		}
		if(student.getClassId() != 0) {
			sqlString.append(" and classId = " + student.getClassId());
		}
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlString.toString().replaceFirst("and", "where"));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setClassId(rs.getInt("classId"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getString("sex"));
				retList.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	
	/*
	 * 删除指定id的学生信息
	 */
	public boolean delete(int id) {
		String sql = "delete from s_student where id = ?";
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
				System.out.println("删除学生：id = " + id);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 修改选定学生信息
	 */
	public boolean update(Student student) {
		String sql = "update s_student set name = ? , classId = ?, sex = ? , password = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getClassId());
			pstmt.setString(3, student.getSex());
			pstmt.setString(4, student.getPassword());
			pstmt.setInt(5, student.getId());
			if(pstmt.executeUpdate() > 0) {
				System.out.println("学生信息修改成功！");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*
	 * 学生修改自己的密码
	 */
	public boolean updatePassword(Student student) {
		String sql = "update s_student set password = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getPassword());
			pstmt.setInt(2, student.getId());
			if(pstmt.executeUpdate() > 0) {
				System.out.println("学生密码修改成功");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*
	 * 学生修改密码
	 */
	public String editPassword(Student student,String newPassword){
		String sql = "select * from s_student where id=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);
			prst.setInt(1, student.getId());
			prst.setString(2, student.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(!executeQuery.next()){
				String retString = "旧密码错误！";
				return retString;
			}
			id = executeQuery.getInt("id");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//把sql语句传给数据库操作对象
		String retString = "修改失败";
		String sqlString = "update s_student set password = ? where id = ?";
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
			if(rst > 0){
				retString = "密码修改成功！";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//把sql语句传给数据库操作对象
		return retString;
	}
	
	/*
	 * 学生登陆
	 */
	public Student login(Student student){
		String sql = "select * from s_student where name=? and password=?";
		Student studentRst = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
			prst.setString(1, student.getName());
			prst.setString(2, student.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				studentRst = new Student();
				studentRst.setId(executeQuery.getInt("id"));
				studentRst.setClassId(executeQuery.getInt("classId"));
				studentRst.setName(executeQuery.getString("name"));
				studentRst.setPassword(executeQuery.getString("password"));
				studentRst.setSex(executeQuery.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentRst;
	}
	
	public boolean isExist(int studentId){
		String sql = "select * from s_student where id=?";
		try {
			PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
			prst.setInt(1, studentId);
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
