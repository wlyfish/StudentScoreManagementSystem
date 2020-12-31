package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Student;
import util.StringUtil;

/*
 * 课程信息操作数据库
 */
public class CourseDao extends BaseDao {
	public boolean addCourse(Course course) {
		String sql = "insert into s_course values(null,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course.getName());
			pstmt.setInt(2, course.getTeacher_id());
			pstmt.setInt(3, course.getMax_student_num());
			pstmt.setString(4, course.getInfo());
			if(pstmt.executeUpdate() > 0) {
				System.out.println("添加一名课程。");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 课程信息列表
	 */
	public List<Course> getCourseList(Course course){
		List<Course> retList = new ArrayList<Course>();
		StringBuffer sqlString = new StringBuffer("select * from s_course");
		if(!(StringUtil.isEmpty(course.getName()))) {
			sqlString.append(" and name like '%" + course.getName() + "%'");
		}
		if(course.getTeacher_id() != 0) {
			sqlString.append(" and teacher_id = " + course.getTeacher_id());
		}
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlString.toString().replaceFirst("and", "where"));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Course c = new Course();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setTeacher_id(rs.getInt("teacher_id"));;;
				c.setMax_student_num(rs.getInt("max_student_num"));
				c.setInfo(rs.getString("info"));
				retList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retList;
	}
	
	/*
	 * 删除指定id的课程信息
	 */
	public boolean delete(int id) {
		String sql = "delete from s_course where id = ?";
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
				System.out.println("删除课程：id = " + id);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 修改选定课程信息
	 */
	public boolean update(Course course) {
		String sql = "update s_course set name = ? , teacher_id = ?, max_student_num = ? , info = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course.getName());
			pstmt.setInt(2, course.getTeacher_id());
			pstmt.setInt(3, course.getMax_student_num());
			pstmt.setString(4, course.getInfo());
			pstmt.setInt(5, course.getId());
			if(pstmt.executeUpdate() > 0) {
				System.out.println("课程信息修改成功！");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
