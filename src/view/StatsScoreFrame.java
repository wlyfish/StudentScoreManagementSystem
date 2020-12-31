package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CourseDao;
import dao.ScoreDao;
import dao.StudentDao;
import model.Course;
import model.Score;
import model.Student;
import model.Teacher;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatsScoreFrame extends JInternalFrame {
	private JTable scoreListTable;
	private JTextField maxScoreTextField;
	private JTextField minScoreTextField;
	private JTextField averageScoreTextField;
	private JTextField totalStudentNumTextField;
	private JComboBox courseNameComboBox;
	private JButton searchButton;
	
	
	private List<Course>courseList;
	private List<Student>studentList;

	/**
	 * Create the frame.
	 */
	public StatsScoreFrame() {
		setTitle("成绩统计界面");
		setBounds(100, 100, 504, 467);
		
		setLocation(0, 0);
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("课程名称：");
		lblNewLabel.setIcon(new ImageIcon(StatsScoreFrame.class.getResource("/images/课程.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		courseNameComboBox = new JComboBox();
		
		searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchScoreStatsInfo();
			}
		});
		searchButton.setIcon(new ImageIcon(StatsScoreFrame.class.getResource("/images/查询.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "成绩统计信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.info);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(courseNameComboBox, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(searchButton))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(courseNameComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_1 = new JLabel("最高分：");
		lblNewLabel_1.setIcon(new ImageIcon(StatsScoreFrame.class.getResource("/images/最大值.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		maxScoreTextField = new JTextField();
		maxScoreTextField.setEnabled(false);
		maxScoreTextField.setEditable(false);
		maxScoreTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("最低分：");
		lblNewLabel_2.setIcon(new ImageIcon(StatsScoreFrame.class.getResource("/images/最小值.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		minScoreTextField = new JTextField();
		minScoreTextField.setEnabled(false);
		minScoreTextField.setEditable(false);
		minScoreTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("平均分：");
		lblNewLabel_3.setIcon(new ImageIcon(StatsScoreFrame.class.getResource("/images/平均.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		averageScoreTextField = new JTextField();
		averageScoreTextField.setEnabled(false);
		averageScoreTextField.setEditable(false);
		averageScoreTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("总人数：");
		lblNewLabel_4.setIcon(new ImageIcon(StatsScoreFrame.class.getResource("/images/总人数.png")));
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		totalStudentNumTextField = new JTextField();
		totalStudentNumTextField.setEnabled(false);
		totalStudentNumTextField.setEditable(false);
		totalStudentNumTextField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(maxScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(averageScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(41)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(minScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(totalStudentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(maxScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(minScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(averageScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(totalStudentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		scoreListTable = new JTable();
		scoreListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"记录编号", "学生姓名", "课程名称", "课程成绩"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(scoreListTable);
		getContentPane().setLayout(groupLayout);

		setCourseCombox();
		initTable();
	}
	
	protected void searchScoreStatsInfo() {
		Course course = (Course)courseNameComboBox.getSelectedItem();
		ScoreDao scoreDao = new ScoreDao();
		Map<String, String> statsInfo = scoreDao.getStatsInfo(course.getId());
		resetText();
		if(statsInfo.size() > 0){
			setStatsInfoPanel(statsInfo.get("student_num"),statsInfo.get("max_score"),statsInfo.get("min_score"),statsInfo.get("mid_score"));
		}
		Score score = new Score();
		score.setCourse_id(course.getId());
		score.setStudent_id(0);
		StudentDao studentDao = new StudentDao();
		studentList = studentDao.getStudentList(new Student());
		studentDao.closeDao();
		getScoreList(score);
		scoreDao.closeDao();
	}

	private void setStatsInfoPanel(String studentNum,String maxScore, String minScore,String midScore) {
		maxScoreTextField.setText(maxScore);
		minScoreTextField.setText(minScore);
		averageScoreTextField.setText(midScore);
		totalStudentNumTextField.setText(studentNum);
	}

	private void initTable(){
		StudentDao studentDao = new StudentDao();
		studentList = studentDao.getStudentList(new Student());
		studentDao.closeDao();
		getScoreList(new Score());
	}
	
	private void setCourseCombox(){
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		courseDao.closeDao();
		for (Course course : courseList) {
			if("教师".equals(MainFrame.userType.getName())){
				Teacher teacher = (Teacher)MainFrame.userObject;
				if(course.getTeacher_id() == teacher.getId()){
					courseNameComboBox.addItem(course);
				}
				continue;
			}
			//执行到这里一定是超级管理员身份
			courseNameComboBox.addItem(course);
		}
	}
	
	private void getScoreList(Score score){
		ScoreDao scoreDao = new ScoreDao();
		List<Score> scoreList = scoreDao.getScoreList(score);
		//进行排序
		Collections.sort(scoreList);
		DefaultTableModel dft = (DefaultTableModel) scoreListTable.getModel();
		dft.setRowCount(0);
		for (Score s : scoreList) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(getStudentNameById(s.getStudent_id()));
			v.add(getCourseById(s.getCourse_id()));
			v.add(s.getScore());
			dft.addRow(v);
		}
		scoreDao.closeDao();
	}
	
	private Course getCourseById(int id){
		for (int i = 0; i < courseList.size(); i++) {
			if(id == courseList.get(i).getId())return courseList.get(i);
		}
		return null;
	}
	
	private String getStudentNameById(int id){
		for(Student student :studentList){
			if(student.getId() == id)return student.getName();
		}
		return null;
	}
	
	private void resetText() {
		maxScoreTextField.setText("");
		minScoreTextField.setText("");
		averageScoreTextField.setText("");
		totalStudentNumTextField.setText("");
	}

}
