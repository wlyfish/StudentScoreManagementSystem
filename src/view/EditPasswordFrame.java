package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AdminDao;
import dao.StudentDao;
import dao.TeacherDao;
import model.Admin;
import model.Student;
import model.Teacher;
import model.UserType;
import util.StringUtil;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditPasswordFrame extends JInternalFrame {

	private JPanel contentPane;
	private JTextField oldPasswordTextField;
	private JTextField confirmPasswordTextField;
	private JTextField newPasswordTextField;
	private JLabel currentUserLabel;
	

	/**
	 * Create the frame.
	 */
	public EditPasswordFrame() {
		
		//setDesktopIcon(Toolkit.getDefaultToolkit().getImage(EditPasswordFrame.class.getResource("/images/修改密码 (1).png")));
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setTitle("修改密码");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置用户在此窗体上发起 "close" 时默认执行的操作
		                                                  //EXIT_ON_CLOSE（在 JFrame 中定义）：使用 System exit 方法退出应用程序。仅在应用程序中使用
		setBounds(100, 100, 438, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("当前用户：");
		lblNewLabel.setIcon(new ImageIcon(EditPasswordFrame.class.getResource("/images/用户名.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("原密码：");
		lblNewLabel_1.setIcon(new ImageIcon(EditPasswordFrame.class.getResource("/images/密 码.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("新密码：");
		lblNewLabel_2.setIcon(new ImageIcon(EditPasswordFrame.class.getResource("/images/修改密码 (1).png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("确认密码：");
		lblNewLabel_3.setIcon(new ImageIcon(EditPasswordFrame.class.getResource("/images/修改密码 (1).png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton submitButton = new JButton("确认");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitEdit(e);
			}
		});
		submitButton.setIcon(new ImageIcon(EditPasswordFrame.class.getResource("/images/确认.png")));
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		resetButton.setIcon(new ImageIcon(EditPasswordFrame.class.getResource("/images/重置.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		currentUserLabel = new JLabel("");
		
		oldPasswordTextField = new JTextField();
		oldPasswordTextField.setColumns(10);
		
		confirmPasswordTextField = new JTextField();
		confirmPasswordTextField.setColumns(10);
		
		newPasswordTextField = new JTextField();
		newPasswordTextField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(submitButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(resetButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(oldPasswordTextField)
									.addComponent(currentUserLabel, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
								.addComponent(confirmPasswordTextField)
								.addComponent(newPasswordTextField, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(currentUserLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(newPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(confirmPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(oldPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(submitButton)
						.addComponent(resetButton))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		if("系统管理员".equals(MainFrame.userType.getName())) {
			Admin admin = (Admin)MainFrame.userObject;
			currentUserLabel.setText("【系统管理员】" + admin.getName());
		}else if("学生".equals(MainFrame.userType.getName())){
			Student student = (Student)MainFrame.userObject;
			currentUserLabel.setText("【学生】" + student.getName());
		}else {
			Teacher teacher = (Teacher)MainFrame.userObject;
			currentUserLabel.setText("【教师】" + teacher.getName());
		}
	}

	protected void submitEdit(ActionEvent e) {
		// TODO Auto-generated method stub
		String oldPassword = oldPasswordTextField.getText().toString();
		String newPassword = newPasswordTextField.getText().toString();
		String confirmPassword = confirmPasswordTextField.getText().toString();
		if(StringUtil.isEmpty(oldPassword)) {
			JOptionPane.showMessageDialog(contentPane, "请填写旧密码！");
			return;
		}
		if(StringUtil.isEmpty(newPassword)) {
			JOptionPane.showMessageDialog(contentPane, "请填写新密码！");
			return;
		}
		if(StringUtil.isEmpty(confirmPassword)) {
			JOptionPane.showMessageDialog(contentPane, "请确认新密码！");
			return;
		}
		if(!(newPassword.equals(confirmPassword))) {
			JOptionPane.showMessageDialog(contentPane, "两次密码输入不一致！");
			return;
		}else {
			if("系统管理员".equals(MainFrame.userType.getName())) {
				AdminDao adminDao = new AdminDao();
				Admin adminTmp = new Admin();
				Admin admin = (Admin)MainFrame.userObject;
				adminTmp.setName(admin.getName());
				adminTmp.setId(admin.getId());
				adminTmp.setPassword(oldPassword);
				JOptionPane.showMessageDialog(contentPane, adminDao.editPassword(adminTmp, newPassword));
//				System.out.println("密码修改成功！");
//				System.out.println("新密码：" + newPassword);
				adminDao.closeDao();
				return;
			}
			if("学生".equals(MainFrame.userType.getName())){
				StudentDao studentDao = new StudentDao();
				Student studentTmp = new Student();
				Student student = (Student)MainFrame.userObject;
				studentTmp.setName(student.getName());
				studentTmp.setPassword(oldPassword);
				studentTmp.setId(student.getId());
				JOptionPane.showMessageDialog(this, studentDao.editPassword(studentTmp, newPassword));
				studentDao.closeDao();
				return;
			}
			if("教师".equals(MainFrame.userType.getName())){
				TeacherDao teacherDao = new TeacherDao();
				Teacher teacherTmp = new Teacher();
				Teacher teacher = (Teacher)MainFrame.userObject;
				teacherTmp.setName(teacher.getName());
				teacherTmp.setPassword(oldPassword);
				teacherTmp.setId(teacher.getId());
				JOptionPane.showMessageDialog(this, teacherDao.editPassword(teacherTmp, newPassword));
				teacherDao.closeDao();
				return;
			}
		}
	}

	protected void resetValue(ActionEvent e) {
		// TODO Auto-generated method stub
		oldPasswordTextField.setText("");
		newPasswordTextField.setText("");
		confirmPasswordTextField.setText("");
	}

}
