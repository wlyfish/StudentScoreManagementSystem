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

import dao.CourseDao;
import dao.TeacherDao;
import model.Course;
import model.Teacher;
import util.StringUtil;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddCourseFrame extends JInternalFrame {
	private JTextField courseNameTextField;
	private JTextField studentNumTextField;
	private JComboBox teacherNameComboBox;
	private JTextArea courseInfoTextArea;
	private JButton submitButton;
	private JButton resetButton;

	/**
	 * Create the frame.
	 */
	public AddCourseFrame() {
		setTitle("添加课程");
		setBounds(100, 100, 364, 333);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("课程名称：");
		lblNewLabel.setIcon(new ImageIcon(AddCourseFrame.class.getResource("/images/课程.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		courseNameTextField = new JTextField();
		courseNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("授课教师：");
		lblNewLabel_1.setIcon(new ImageIcon(AddCourseFrame.class.getResource("/images/教师.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		teacherNameComboBox = new JComboBox();
		
		JLabel lblNewLabel_2 = new JLabel("学生人数：");
		lblNewLabel_2.setIcon(new ImageIcon(AddCourseFrame.class.getResource("/images/人数.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentNumTextField = new JTextField();
		studentNumTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("课程介绍：");
		lblNewLabel_3.setIcon(new ImageIcon(AddCourseFrame.class.getResource("/images/介绍.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		courseInfoTextArea = new JTextArea();
		courseInfoTextArea.setLineWrap(true);
		courseInfoTextArea.setBackground(SystemColor.info);
		courseInfoTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		submitButton = new JButton("确认添加");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourseAction(e);
			}
		});
		submitButton.setIcon(new ImageIcon(AddCourseFrame.class.getResource("/images/确认.png")));
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		resetButton = new JButton("重置信息");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		resetButton.setIcon(new ImageIcon(AddCourseFrame.class.getResource("/images/重置.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(55)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(submitButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(resetButton))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_3)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(courseInfoTextArea))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(studentNumTextField))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(teacherNameComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(courseNameTextField, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(courseNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(teacherNameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(studentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(courseInfoTextArea, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(resetButton))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setTeacherCombox();
	}

	protected void resetValue(ActionEvent e) {
		courseNameTextField.setText("");
		courseInfoTextArea.setText("");
		studentNumTextField.setText("");
		teacherNameComboBox.setSelectedItem(null);
	}

	protected void addCourseAction(ActionEvent e) {
		String courseName = courseNameTextField.getText().toString();
		String courseInfo = courseInfoTextArea.getText().toString();
		Teacher selectedTeacher = (Teacher)teacherNameComboBox.getSelectedItem();
		int studentMaxNum = 0;
		try {
			studentMaxNum = Integer.parseInt(studentNumTextField.getText());
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(this, "只能输入数字！");
			return;
		}
		if(StringUtil.isEmpty(courseName)) {
			JOptionPane.showMessageDialog(this, "请输入课程名称！");
			return;
		}
		if(studentMaxNum <= 0) {
			JOptionPane.showMessageDialog(this, "学生人数请输入大于0的数字！");;
			return;
		}
		Course course = new Course();
		course.setName(courseName);
		course.setMax_student_num(studentMaxNum);
		course.setInfo(courseInfo);
		course.setTeacher_id(selectedTeacher.getId());
		
		CourseDao courseDao = new CourseDao();
		if(courseDao.addCourse(course)) {
			JOptionPane.showMessageDialog(this, "添加成功！");
		}else {
			JOptionPane.showMessageDialog(this, "添加失败！");
		}
		courseDao.closeDao();
		resetValue(e);
	}
	private void setTeacherCombox() {
		if("教师".equals(MainFrame.userType.getName())) {
	    	Teacher t = (Teacher)MainFrame.userObject;
	    	teacherNameComboBox.addItem(t);
	    	teacherNameComboBox.setEnabled(false);
	    }
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teacherList = teacherDao.getTeacherList(new Teacher());
			for (Teacher t : teacherList) {
				teacherNameComboBox.addItem(t);
			}
		teacherDao.closeDao();
	}
}
