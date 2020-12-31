package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CourseDao;
import dao.TeacherDao;
import model.Course;
import model.Teacher;
import util.StringUtil;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageCourseFrame extends JInternalFrame {
	private JTextField searcheCourseNameTextField;
	private JTable courseListTable;
	private JTextField editCourseNameTextField;
	private JTextField editCourseStudentNumTextField;
	private JComboBox searchTeacherComboBox;
	private JButton searchButton;
	private JButton lookAllButton;
	private JComboBox editCourseTeacherComboBox;
	private JTextArea editCourseInfotextArea;
	private JButton submitEditButton;
	private JButton deleteButton;
	private List<Teacher> teacherList = new ArrayList<Teacher>();

	/**
	 * Create the frame.
	 */
	public ManageCourseFrame() {
		setTitle("课程信息管理");
		setBounds(100, 100, 786, 512);
		setLocation(0, 0);
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("课程名称：");
		lblNewLabel.setIcon(new ImageIcon(ManageCourseFrame.class.getResource("/images/课程.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searcheCourseNameTextField = new JTextField();
		searcheCourseNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("授课教师：");
		lblNewLabel_1.setIcon(new ImageIcon(ManageCourseFrame.class.getResource("/images/教师.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchTeacherComboBox = new JComboBox();
		
		searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {
			/*
			 * 点击查询按钮事件
			 */
			public void actionPerformed(ActionEvent e) {
				searchCourse(e);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageCourseFrame.class.getResource("/images/查询.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		lookAllButton = new JButton("浏览全部");
		lookAllButton.addActionListener(new ActionListener() {
			/*
			 * 浏览全部课程
			 * 按钮点击事件
			 */
			public void actionPerformed(ActionEvent e) {
				searcheCourseNameTextField.setText("");
				searchTeacherComboBox.setSelectedIndex(0);
				setCourseListTable(new Course());
			}
		});
		lookAllButton.setIcon(new ImageIcon(ManageCourseFrame.class.getResource("/images/浏览.png")));
		lookAllButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u7F16\u8F91\u8BFE\u7A0B\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.info);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(searcheCourseNameTextField, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(searchTeacherComboBox, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(searchButton)
								.addGap(18)
								.addComponent(lookAllButton))))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(searcheCourseNameTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(searchTeacherComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton)
						.addComponent(lookAllButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_2 = new JLabel("课程名称：");
		lblNewLabel_2.setIcon(new ImageIcon(ManageCourseFrame.class.getResource("/images/课程.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editCourseNameTextField = new JTextField();
		editCourseNameTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("授课教师：");
		lblNewLabel_3.setIcon(new ImageIcon(ManageCourseFrame.class.getResource("/images/教师.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editCourseTeacherComboBox = new JComboBox();
		
		JLabel lblNewLabel_4 = new JLabel("学生人数：");
		lblNewLabel_4.setIcon(new ImageIcon(ManageCourseFrame.class.getResource("/images/用户类型.png")));
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editCourseStudentNumTextField = new JTextField();
		editCourseStudentNumTextField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("课程介绍：");
		lblNewLabel_5.setIcon(new ImageIcon(ManageCourseFrame.class.getResource("/images/介绍.png")));
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editCourseInfotextArea = new JTextArea();
		editCourseInfotextArea.setLineWrap(true);
		
		submitEditButton = new JButton("确认修改");
		submitEditButton.addActionListener(new ActionListener() {
			/*
			 * 确认提交修改按钮事件
			 */
			public void actionPerformed(ActionEvent e) {
				editCourseSubmit(e);
			}
		});
		submitEditButton.setIcon(new ImageIcon(ManageCourseFrame.class.getResource("/images/确认.png")));
		submitEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		deleteButton = new JButton("删除课程");
		deleteButton.addActionListener(new ActionListener() {
			/*
			 * 点击删除按钮事件
			 */
			public void actionPerformed(ActionEvent e) {
				deleteCourse(e);
			}
		});
		deleteButton.setIcon(new ImageIcon(ManageCourseFrame.class.getResource("/images/删 除.png")));
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(editCourseNameTextField, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addGap(18)
							.addComponent(editCourseStudentNumTextField)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addGap(18)
							.addComponent(editCourseTeacherComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addGap(18)
							.addComponent(editCourseInfotextArea, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(submitEditButton)
						.addComponent(deleteButton))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(editCourseTeacherComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(editCourseNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(submitEditButton))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(editCourseStudentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5)))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(editCourseInfotextArea, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(29)
							.addComponent(deleteButton)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		courseListTable = new JTable();
		courseListTable.addMouseListener(new MouseAdapter() {
			/*
			 * 点击课程显示内容事件
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedCourse(e);
			}
		});
		courseListTable.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		courseListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"课程编号", "课程名称", "授课教师", "课程最大人数", "课程介绍"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		courseListTable.getColumnModel().getColumn(3).setPreferredWidth(93);
		scrollPane.setViewportView(courseListTable);
		getContentPane().setLayout(groupLayout);
		setTeacherCombox();
		setCourseListTable(new Course());
		setAuthority();
	}
	
	protected void editCourseSubmit(ActionEvent e) {
		int row = courseListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选中要修改的数据！");
			return;
		}
		int courseId = Integer.parseInt(courseListTable.getValueAt(row, 0).toString());
		Teacher teacher = (Teacher)editCourseTeacherComboBox.getSelectedItem();
		String courseName = editCourseNameTextField.getText().toString();
		if(StringUtil.isEmpty(courseName)) {
			JOptionPane.showMessageDialog(this, "课程名称不能为空！");
			return;
		}
		int maxStudentNum = 0;
		try {
			maxStudentNum = Integer.parseInt(editCourseStudentNumTextField.getText().toString());
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(this, "学生人数请输入大于0的整数！");
			return;
		}
		if(maxStudentNum <= 0) {
			JOptionPane.showMessageDialog(this, "学生人数请输入大于0的整数！");
			return;
		}
		String courseInfo = editCourseInfotextArea.getText().toString();
		Course course = new Course();
		course.setId(courseId);
		course.setName(courseName);
		course.setTeacher_id(teacher.getId());
		course.setMax_student_num(maxStudentNum);
		course.setInfo(courseInfo);
		CourseDao courseDao = new CourseDao();
		if(courseDao.update(course)) {
			JOptionPane.showMessageDialog(this, "修改成功！");
		}else {
			JOptionPane.showMessageDialog(this, "修改失败！");
		}
		courseDao.closeDao();
		setCourseListTable(new Course());
	}

	protected void selectedCourse(MouseEvent e) {
		int row = courseListTable.getSelectedRow();
		String courseName = courseListTable.getValueAt(row, 1).toString();
		int teacherId = getTeacherIdByName(courseListTable.getValueAt(row, 2).toString());
		int maxStudentNum = Integer.parseInt(courseListTable.getValueAt(row, 3).toString());
		String courseInfo = courseListTable.getValueAt(row, 4).toString();
		editCourseNameTextField.setText(courseName);
		editCourseStudentNumTextField.setText(maxStudentNum + "");
		editCourseInfotextArea.setText(courseInfo);
		for (int i = 0; i < editCourseTeacherComboBox.getItemCount(); i++) {
			Teacher t = (Teacher)editCourseTeacherComboBox.getItemAt(i);
			if(t.getId() == teacherId) {
				editCourseTeacherComboBox.setSelectedIndex(i);
				break;
			}
		}
	}

	protected void searchCourse(ActionEvent e) {
		String searchCourseName = searcheCourseNameTextField.getText().toString();
		Teacher teacher = (Teacher)searchTeacherComboBox.getSelectedItem();
		Course course = new Course();
		if(searchTeacherComboBox.getSelectedItem() == null) {
			course.setTeacher_id(0);
		}else {
			course.setTeacher_id(teacher.getId());
		}
		course.setName(searchCourseName);
		setCourseListTable(course);
	}

	protected void deleteCourse(ActionEvent e) {
		int row = courseListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选中要删除的数据！");;
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "是否确定删除？") != JOptionPane.OK_OPTION) {
			System.out.println("取消删除");
			return;
		}
		int courseId = Integer.parseInt(courseListTable.getValueAt(row, 0).toString());
		CourseDao courseDao = new CourseDao();
		if(courseDao.delete(courseId)) {
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		courseDao.closeDao();
		setCourseListTable(new Course());
	}

	private void setCourseListTable(Course course) {
		 if("教师".equals(MainFrame.userType.getName())) {
		    	Teacher t = (Teacher)MainFrame.userObject;
		    	course.setTeacher_id(t.getId());
		    	searchTeacherComboBox.addItem(t);
		    	searchTeacherComboBox.setEnabled(false);
		    }else {
		    	editCourseNameTextField.setText("");
				editCourseStudentNumTextField.setText("");
				editCourseInfotextArea.setText("");
				searchTeacherComboBox.setSelectedItem(null);
		    }
		
		CourseDao courseDao = new CourseDao();
		List<Course> courseList = courseDao.getCourseList(course);
		DefaultTableModel dft = (DefaultTableModel)courseListTable.getModel();
		dft.setRowCount(0);
		for (Course c : courseList) {
			Vector v = new Vector();
			v.add(c.getId());
			v.add(c.getName());
			v.add(getTeacherNameById(c.getTeacher_id()));
			v.add(c.getMax_student_num());
			v.add(c.getInfo());
			dft.addRow(v);
		}
		courseDao.closeDao();
	}
	
	private void setTeacherCombox() {
		TeacherDao teacherDao = new TeacherDao();
		teacherList = teacherDao.getTeacherList(new Teacher());
		teacherDao.closeDao();
		for (Teacher teacher : teacherList) {
			editCourseTeacherComboBox.addItem(teacher);
			searchTeacherComboBox.addItem(teacher);
		}
	}
	
	private String getTeacherNameById(int teacher_id) {
		String retString = "";
		for (Teacher teacher : teacherList) {
			if(teacher.getId() == teacher_id) {
				retString = teacher.getName();
				break;
			}
		}
		return retString;
	}
	
	private int getTeacherIdByName(String teacherName) {
		int retId = -1;
		for (Teacher teacher : teacherList) {
			if(teacherName.equals(teacher.getName())) {
				retId = teacher.getId();
				break;
			}
		}
		return retId;
	}
	
	private void setAuthority() {
		if("学生".equals(MainFrame.userType.getName())) {
			editCourseNameTextField.setEnabled(false);
			editCourseInfotextArea.setEnabled(false);
			editCourseStudentNumTextField.setEnabled(false);
			editCourseTeacherComboBox.setEnabled(false);
			submitEditButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}
		if("教师".equals(MainFrame.userType.getName())) {
			editCourseTeacherComboBox.setEnabled(false);
		}
	}
}
