package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JComboBox userTypeComboBox;
	private JPasswordField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("系统登陆界面");  //设置窗口标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //窗口关闭
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setLocationRelativeTo(null);//设置窗口相对于指定组件的位置,如果组件当前未显示或者 c 为 null，则此窗口将置于屏幕的中央
		
		JLabel lblNewLabel = new JLabel("学生信息系统登陆界面");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/鱼.png")));
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 25));
		
		JLabel lblNewLabel_1 = new JLabel(" 用户名：");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/用户名.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		JLabel lblNewLabel_2 = new JLabel(" 密   码：");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/密 码.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		JLabel lblNewLabel_3 = new JLabel("用户类型：");
		lblNewLabel_3.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/用户类型.png"))); 
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		userNameTextField = new JTextField();
		userNameTextField.setColumns(10);
		
		userTypeComboBox = new JComboBox();
		userTypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		userTypeComboBox.setModel(new DefaultComboBoxModel(new UserType[] {UserType.ADMIN, UserType.TEACHER, UserType.STUDENT}));
		
		JButton lgionButton = new JButton("登录");
		lgionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userNameTextField.getText().toString();  //获取输入的姓名字符串
				String password = passwordTextField.getText().toString();  //获取密码框内的字符串
				UserType selectedItem = (UserType)userTypeComboBox.getSelectedItem();  //获取用户类型,枚举类型
				if(StringUtil.isEmpty(userName)){
					JOptionPane.showMessageDialog(contentPane, "用户名不能为空！");
					return;
				}
				if(StringUtil.isEmpty(password)){
					JOptionPane.showMessageDialog(contentPane, "密码名不能为空！");
					return;
				}
				
				Admin admin = null;
				Student student = null; 
				
				if("系统管理员".equals(selectedItem.getName())){
					System.out.println("用户类型：管理员");
					//系统管理员登陆
					AdminDao adminDao = new AdminDao();
					Admin adminTmp = new Admin();
					adminTmp.setName(userName);
					adminTmp.setPassword(password);
					admin = adminDao.login(adminTmp);
					adminDao.closeDao();
					if(admin == null) {
						JOptionPane.showMessageDialog(contentPane, "用户名或密码错误！");
						return;
					}else {
						JOptionPane.showMessageDialog(contentPane, "欢迎【" + selectedItem.getName() + "】：" + admin.getName() + "登录本系统！");
						System.out.println("管理员：" + admin.getName());
						setVisible(false);
						new MainFrame(selectedItem, admin).setVisible(true);
					}
				}else if("教师".equals(selectedItem.getName())) {
					//教师登录
					Teacher teacher = null;
					TeacherDao teacherDao = new TeacherDao();
					Teacher teacherTmp = new Teacher();
					teacherTmp.setName(userName);
					teacherTmp.setPassword(password);
					teacher = teacherDao.login(teacherTmp);
					teacherDao.closeDao();
					if(teacher == null){
						JOptionPane.showMessageDialog(contentPane, "用户名或密码错误！");
						return;
					}
					JOptionPane.showMessageDialog(contentPane, "欢迎【"+selectedItem.getName()+"】："+teacher.getName()+"登录本系统！");
					setVisible(false);
					new MainFrame(selectedItem, teacher).setVisible(true);
				}else {
					//学生登陆
					StudentDao studentDao = new StudentDao();
					Student studentTmp = new Student();
					studentTmp.setName(userName);
					studentTmp.setPassword(password);
					student = studentDao.login(studentTmp);
					studentDao.closeDao();
					if(student == null){
						JOptionPane.showMessageDialog(contentPane, "用户名或密码错误！");
						return;
					}
					JOptionPane.showMessageDialog(contentPane, "欢迎【"+selectedItem.getName()+"】："+student.getName()+"登录本系统！");
					setVisible(false);
					new MainFrame(selectedItem, student).setVisible(true);
				}
				
			}
		});
		lgionButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/登录.png")));
		lgionButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		JButton resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		resetButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/重置.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		passwordTextField = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_2)
								.addComponent(lgionButton, Alignment.TRAILING))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(passwordTextField, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(resetButton)
									.addComponent(userNameTextField, Alignment.LEADING)
									.addComponent(userTypeComboBox, Alignment.LEADING, 0, 129, Short.MAX_VALUE)))
							.addGap(40)))
					.addGap(55))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(userTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lgionButton)
						.addComponent(resetButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected void resetValue() {
		userNameTextField.setText("");
		passwordTextField.setText("");
		userTypeComboBox.setSelectedIndex(0);
	}
}
