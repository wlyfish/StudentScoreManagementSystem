package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.UserType;
import util.StringUtil;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	public static UserType userType;
	public static Object userObject;
	
	private JMenu manageTeacherMenu;
	private JMenu manageStudentMenu;
	private JMenuItem addStudentMenuItem;
	private JMenu manageClassMenu;
	private JMenuItem addTeacherMenuItem;
	private JMenuItem teacherListrMenuItem;
	private JMenu manageCourseMenu;
	private JMenuItem addClassMenuItem;
	private JMenuItem addCourseMenuItem;
	private JMenuItem courseListMenuItem;
	private JMenu manageScoreMenu;
	private JMenuItem manageScoreMenuItem;
	private JMenuItem addScoreMenuItem;
	private JMenuItem statsScoreMenuItem;
	

	/**
	 * Create the frame.
	 */
	public MainFrame(UserType userType, Object userObject) {
		this.userType = userType;
		this.userObject = userObject;
		
		setTitle("学生信息系统主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 603);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("系统设置");
		mnNewMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/系统设置.png")));
		mnNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("修改密码");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPassword(e);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/修改密码.png")));
		mntmNewMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("退出系统");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(MainFrame.this, "是否确定退出？") == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/退出系统.png")));
		mntmNewMenuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		manageTeacherMenu = new JMenu("教师管理");
		manageTeacherMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/教师 (1).png")));
		manageTeacherMenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(manageTeacherMenu);
		
		addTeacherMenuItem = new JMenuItem("教师添加");
		addTeacherMenuItem.addActionListener(new ActionListener() {
			/*
			 * 教师添加
			 */
			public void actionPerformed(ActionEvent e) {
				AddTeacherFrame addTeacherFrame = new AddTeacherFrame();
				addTeacherFrame.setVisible(true);
				desktopPane.add(addTeacherFrame);
			}
		});
		addTeacherMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/添加.png")));
		addTeacherMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		manageTeacherMenu.add(addTeacherMenuItem);
		
		teacherListrMenuItem = new JMenuItem("教师列表");
		teacherListrMenuItem.addActionListener(new ActionListener() {
			/*
			 * 显示教师列表
			 */
			public void actionPerformed(ActionEvent e) {
				ManageTeacherFrame manageTeacherFrame = new ManageTeacherFrame();
				manageTeacherFrame.setVisible(true);
				desktopPane.add(manageTeacherFrame);
			}
		});
		teacherListrMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/用户列表.png")));
		teacherListrMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		manageTeacherMenu.add(teacherListrMenuItem);
		
		manageStudentMenu = new JMenu("学生管理");
		manageStudentMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/学生管理.png")));
		manageStudentMenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(manageStudentMenu);
		
		addStudentMenuItem = new JMenuItem("学生添加");
		addStudentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentFrame addStudentFrame = new AddStudentFrame();
				addStudentFrame.setVisible(true);
				desktopPane.add(addStudentFrame);
			}
		});
		addStudentMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/添加.png")));
		addStudentMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		manageStudentMenu.add(addStudentMenuItem);
		
		JMenuItem studentListMenuItem = new JMenuItem("学生列表");
		studentListMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageStudentFrame studentManagerFrame = new ManageStudentFrame();
				studentManagerFrame.setVisible(true);
				desktopPane.add(studentManagerFrame);
			}
		});
		studentListMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/用户列表.png")));
		studentListMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		manageStudentMenu.add(studentListMenuItem);
		
		manageClassMenu = new JMenu("班级管理");
		manageClassMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/班级管理.png")));
		manageClassMenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(manageClassMenu);
		
		addClassMenuItem = new JMenuItem("班级添加");
		addClassMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewClass(e);
			}
		});
		addClassMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/添加.png")));
		addClassMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		manageClassMenu.add(addClassMenuItem);
		
		JMenuItem classListMenuItem = new JMenuItem("班级管理");
		classListMenuItem.addActionListener(new ActionListener() {
			/*
			 * 班级管理：
			 * 显示列表
			 * 修改信息
			 */
			public void actionPerformed(ActionEvent e) {
				ManageClassFrame classManagerFrame = new ManageClassFrame();
				classManagerFrame.setVisible(true);
				desktopPane.add(classManagerFrame);
			}
		});
		classListMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/班级列表.png")));
		classListMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		manageClassMenu.add(classListMenuItem);
		
		manageCourseMenu = new JMenu("课程管理");
		manageCourseMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/课程 (1).png")));
		manageCourseMenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(manageCourseMenu);
		
		addCourseMenuItem = new JMenuItem("添加课程");
		addCourseMenuItem.addActionListener(new ActionListener() {
			/*
			 * 添加班级
			 */
			public void actionPerformed(ActionEvent e) {
				addNewCourse(e);
			}
		});
		addCourseMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/添加.png")));
		addCourseMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		manageCourseMenu.add(addCourseMenuItem);
		
		courseListMenuItem = new JMenuItem("课程列表");
		courseListMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageCourseFrame manageCourseFrame = new ManageCourseFrame();
				manageCourseFrame.setVisible(true);
				desktopPane.add(manageCourseFrame);
			}
		});
		courseListMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/课程列表.png")));
		courseListMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		manageCourseMenu.add(courseListMenuItem);
		
		manageScoreMenu = new JMenu("成绩管理");
		manageScoreMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/选课.png")));
		manageScoreMenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(manageScoreMenu);
		
		manageScoreMenuItem = new JMenuItem("成绩管理");
		manageScoreMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageScoreFrame manageScoreFrame = new ManageScoreFrame();
				manageScoreFrame.setVisible(true);
				desktopPane.add(manageScoreFrame);
			}
		});
		
		addScoreMenuItem = new JMenuItem("添加成绩");
		addScoreMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddScoreFrame addScoreFrame = new AddScoreFrame();
				addScoreFrame.setVisible(true);
				desktopPane.add(addScoreFrame);
			}
		});
		addScoreMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/添加 (1).png")));
		addScoreMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		manageScoreMenu.add(addScoreMenuItem);
		manageScoreMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/选课.png")));
		manageScoreMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		manageScoreMenu.add(manageScoreMenuItem);
		
		statsScoreMenuItem = new JMenuItem("成绩统计");
		statsScoreMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatsScoreFrame statsScoreFrame = new StatsScoreFrame();
				statsScoreFrame.setVisible(true);
				desktopPane.add(statsScoreFrame);
			}
		});
		statsScoreMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/统计.png")));
		statsScoreMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		manageScoreMenu.add(statsScoreMenuItem);
		
		JMenu mnNewMenu_3 = new JMenu("帮助");
		mnNewMenu_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/帮 助.png")));
		mnNewMenu_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("关于我们");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutUs(e);
			}
		});
		mntmNewMenuItem_6.setIcon(new ImageIcon(MainFrame.class.getResource("/images/关于我们 (1).png")));
		mntmNewMenuItem_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu_3.add(mntmNewMenuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 191, 255));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setAuthority();
	}

	protected void addNewCourse(ActionEvent e) {
		AddCourseFrame addCourseFrame = new AddCourseFrame();
		addCourseFrame.setVisible(true);
		desktopPane.add(addCourseFrame);
	}

	protected void addNewClass(ActionEvent e) {
		AddClassFrame classAddFrame = new AddClassFrame();
		classAddFrame.setVisible(true);
		desktopPane.add(classAddFrame);
	}

	protected void editPassword(ActionEvent e) {
		EditPasswordFrame editPasswordFrame = new EditPasswordFrame();
		editPasswordFrame.setVisible(true);
		desktopPane.add(editPasswordFrame);
	}

	protected void aboutUs(ActionEvent e) {
		JOptionPane.showMessageDialog(contentPane, "欢迎使用学生成绩管理系统！\nby 韦利余");
	}
	
	private void setAuthority() {
		if("学生".equals(userType.getName())) {
			//addStudentMenuItem.setVisible(false);
			addStudentMenuItem.setEnabled(false);
			manageClassMenu.setEnabled(false);
			manageTeacherMenu.setEnabled(false);
			addCourseMenuItem.setEnabled(false);
			addScoreMenuItem.setEnabled(false);
			statsScoreMenuItem.setEnabled(false);
		}
		if("教师".equals(userType.getName())) {
			addTeacherMenuItem.setEnabled(false);
			addStudentMenuItem.setEnabled(false);
		}
	}
}
