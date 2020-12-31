package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Classes;
import util.StringUtil;

public class ClassDao extends BaseDao {
	public boolean addClass(Classes cl) {
		String sql = "insert into s_class values(null,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cl.getName());
			pstmt.setString(2, cl.getInfo());
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Classes> getClassList(Classes classes){
		List<Classes> retList = new ArrayList<Classes>();
		String sqlString = "select * from s_class";
		if(!(StringUtil.isEmpty(classes.getName()))) {
			sqlString += " where name like '%" + classes.getName() + "%'";
		}
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlString);
			//pstmt.setString(1, "%" + classes.getName() + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Classes cls = new Classes();
				cls.setId(rs.getInt("id"));
				cls.setName(rs.getString("name"));
				cls.setInfo(rs.getNString("info"));
				retList.add(cls);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	
	/*
	 * 删除数据库中指定id的班级信息
	 */
	public boolean delete(int id) {
		/*
		 * SET foreign_key_checks = 0;  // 先设置外键约束检查关闭
           drop table mytable;  // 删除数据，表或者视图
           SET foreign_key_checks = 1; // 开启外键约束检查，以保持表结构完整性
		 */
		String sqlString = "delete from s_class where id = ?";
		String sqlString2 = "SET FOREIGN_KEY_CHECKS=0";
		String sqlString3 = "SET foreign_key_checks = 1";
		try {
			PreparedStatement pstmt2 = con.prepareStatement(sqlString2);
			PreparedStatement pstmt3 = con.prepareStatement(sqlString3);
			pstmt2.execute();
			PreparedStatement pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, id);
			if(pstmt.executeUpdate() > 0) {
				pstmt3.execute();
				System.out.println("删除成功，id = " + id);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 修改选中的班级信息
	 */
	public boolean update(Classes cls) {
		String sql = "update s_class set name = ? , info = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cls.getName());
			pstmt.setString(2, cls.getInfo());
			pstmt.setInt(3, cls.getId());
			if(pstmt.executeUpdate() > 0) {
				System.out.println("班级信息修改成功！");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
