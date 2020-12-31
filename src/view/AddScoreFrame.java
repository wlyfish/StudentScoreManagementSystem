package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.CourseDao;
import dao.ScoreDao;
import dao.StudentDao;
import model.Course;
import model.Score;
import model.Student;
import model.Teacher;
import util.StringUtil;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddScoreFrame extends JInternalFrame {
	private JTextField studentNameTextField;
	private JTextField scoreTextField;
	private JComboBox courseComboBox;
	private JButton submitButton;
	private JButton resetButton;
	
	private List<Course> courseList;
	private List<Student> studentList;
	

	/**
	 * Create the frame.
	 */
	public AddScoreFrame() {
		setTitle("成绩录入界面");
		setBounds(100, 100, 319, 282);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("学生姓名：");
		lblNewLabel.setIcon(new ImageIcon(AddScoreFrame.class.getResource("/images/学生.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentNameTextField = new JTextField();
		studentNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("课程名称：");
		lblNewLabel_1.setIcon(new ImageIcon(AddScoreFrame.class.getResource("/images/课程.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		courseComboBox = new JComboBox();
		
		JLabel lblNewLabel_2 = new JLabel("所得成绩：");
		lblNewLabel_2.setIcon(new ImageIcon(AddScoreFrame.class.getResource("/images/我的成绩.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		scoreTextField = new JTextField();
		scoreTextField.setColumns(10);
		
		submitButton = new JButton("录入成绩");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitAction(e);
			}
		});
		submitButton.setIcon(new ImageIcon(AddScoreFrame.class.getResource("/images/确认.png")));
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		resetButton = new JButton("重置信息");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentNameTextField.setText("");
				scoreTextField.setText("");
				courseComboBox.setSelectedIndex(0);
			}
		});
		resetButton.setIcon(new ImageIcon(AddScoreFrame.class.getResource("/images/重置.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scoreTextField))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(courseComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(87)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(resetButton)
								.addComponent(submitButton))))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(scoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(submitButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(resetButton)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

		setCourseCombox();
	}
	
	protected void submitAction(ActionEvent e) {
		String studentName = studentNameTextField.getText().toString();
		if(StringUtil.isEmpty(studentName)) {
			JOptionPane.showMessageDialog(this, "学生姓名不能为空！");
			return;
		}
		StudentDao studentDao = new StudentDao();
		if(studentDao.isExist(getStudentIdByName(studentName)) == false) {
			JOptionPane.showMessageDialog(this, "学生不存在！");
			return;
		}
		studentDao.closeDao();
		int score = 0;
		try {
			score = Integer.parseInt(scoreTextField.getText().toString());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "成绩必须输入大于0的整数！");
			return;
		}
		if(score <= 0){
			JOptionPane.showMessageDialog(this, "成绩必须输入大于0的整数！");
			return;
		}
		
		Course course = (Course)courseComboBox.getSelectedItem();
		Score scoreObj = new Score();
		scoreObj.setStudent_id(getStudentIdByName(studentName));
		scoreObj.setCourse_id(course.getId());
		scoreObj.setScore(score);
		ScoreDao scoreDao = new ScoreDao();
		if(scoreDao.isAdd(scoreObj)){
			JOptionPane.showMessageDialog(this, "成绩已经录入，请勿重复录入！");
			return;
		}
		if(scoreDao.addScore(scoreObj)){
			JOptionPane.showMessageDialog(this, "成绩录入成功！");
			scoreTextField.setText("");
		}else{
			JOptionPane.showMessageDialog(this, "成绩录入失败！");
		}
		scoreDao.closeDao();
	}

	private void setCourseCombox(){
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		courseDao.closeDao();
		for (Course course : courseList) {
			if("教师".equals(MainFrame.userType.getName())){
				Teacher teacher = (Teacher)MainFrame.userObject;
				if(course.getTeacher_id() == teacher.getId()){
					courseComboBox.addItem(course);
				}
				continue;
			}
			//执行到这里一定是超级管理员身份
			courseComboBox.addItem(course);
		}
	}
	
	private int getStudentIdByName(String studentName) {
		StudentDao studentDao = new StudentDao();
		studentList = studentDao.getStudentList(new Student());
		studentDao.closeDao();
		int retId = 0;
		for(Student student :studentList){
			if(studentName.equals(student.getName())) {
				retId = student.getId();
				break;
			}
		}
		return retId;
	}
}
