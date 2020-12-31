package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Admin;

public class AdminDao extends BaseDao {
	/*
	 * 管理员登录
	 */
	public Admin login(Admin admin) {
		Admin adminRst = null;
		String sql = "select * from s_admin where name = ? and password = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);//把sql传给数据库操作对象
			pstmt.setString(1, admin.getName());
			pstmt.setString(2, admin.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				adminRst = new Admin();
				adminRst.setId(rs.getInt("id"));
				adminRst.setName(rs.getString("name"));
				adminRst.setPassword(rs.getString("password"));
				adminRst.setCreateDate(rs.getString("createDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adminRst;
	}
	
	/*
	 * 管理员修改密码
	 */
	public String editPassword(Admin admin,String newPassword) {
		String retString = "修改失败";
		String sql = "select * from s_admin where id=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);
			prst.setInt(1, admin.getId());
			prst.setString(2, admin.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(!executeQuery.next()){
				retString = "旧密码错误！";
				return retString;
			}
			id = executeQuery.getInt("id");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//把sql语句传给数据库操作对象
		
		String sqlString = "update s_admin set password = ? where id = ?";
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
			if(rst > 0){
				retString = "密码修改成功！";
				System.out.println("密码修改成功！");
				System.out.println("新密码：" + newPassword);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//把sql语句传给数据库操作对象
		return retString;
	}
}
