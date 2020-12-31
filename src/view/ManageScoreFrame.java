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
import dao.ScoreDao;
import dao.StudentDao;
import model.Course;
import model.Score;
import model.Student;
import model.Teacher;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import java.util.List;
import java.util.Vector;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

public class ManageScoreFrame extends JInternalFrame {
	private JTextField studentNameTextField;
	private JTable scoreListTable;
	private JTextField editScoreTextField;
	private JComboBox searchCourseComboBox;
	private JButton searchButton;
	private JButton submitEditButton;
	private JButton deleteScoreButton;
	private List<Course>courseList;
	private List<Student>studentList;
	private JRadioButton isSearchCourseRadioButton;

	/**
	 * Create the frame.
	 */
	public ManageScoreFrame() {
		setTitle("成绩管理界面");
		setBounds(100, 100, 572, 421);
		setLocation(0, 0);
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("学生：");
		lblNewLabel.setIcon(new ImageIcon(ManageScoreFrame.class.getResource("/images/学生.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentNameTextField = new JTextField();
		studentNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("课程：");
		lblNewLabel_1.setIcon(new ImageIcon(ManageScoreFrame.class.getResource("/images/课程.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchCourseComboBox = new JComboBox();
		
		searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchScore(e);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageScoreFrame.class.getResource("/images/查询.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.info);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u4FEE\u6539\u6210\u7EE9", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		isSearchCourseRadioButton = new JRadioButton("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, 0, 0, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(searchCourseComboBox, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(isSearchCourseRadioButton)
								.addGap(18)
								.addComponent(searchButton))
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(lblNewLabel_1)
							.addComponent(searchCourseComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addComponent(searchButton)
						.addComponent(isSearchCourseRadioButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_2 = new JLabel("成绩：");
		lblNewLabel_2.setIcon(new ImageIcon(ManageScoreFrame.class.getResource("/images/我的成绩.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editScoreTextField = new JTextField();
		editScoreTextField.setColumns(10);
		
		submitEditButton = new JButton("确认修改");
		submitEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editSubmitAction(e);
			}
		});
		submitEditButton.setIcon(new ImageIcon(ManageScoreFrame.class.getResource("/images/确认.png")));
		submitEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		deleteScoreButton = new JButton("删除成绩");
		deleteScoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAction(e);
			}
		});
		deleteScoreButton.setIcon(new ImageIcon(ManageScoreFrame.class.getResource("/images/删 除.png")));
		deleteScoreButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editScoreTextField, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(submitEditButton)
					.addGap(29)
					.addComponent(deleteScoreButton)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(editScoreTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteScoreButton)
						.addComponent(submitEditButton))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		scoreListTable = new JTable();
		scoreListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableItemClick(e);
			}
		});
		scoreListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"记录编号", "学生姓名", "课程名称", "成绩"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scoreListTable.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		scrollPane.setViewportView(scoreListTable);
		getContentPane().setLayout(groupLayout);

		setCourseCombox();
		initTable();
		setAuthority();
	}
	
	protected void editSubmitAction(ActionEvent e) {
		int row = scoreListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请先选择一行！");
			return;
		}
		int scoreId = Integer.parseInt(scoreListTable.getValueAt(row, 0).toString());
		int score = Integer.parseInt(editScoreTextField.getText().toString());
		ScoreDao scoreDao = new ScoreDao();
		if(scoreDao.update(scoreId, score)){
			JOptionPane.showMessageDialog(this, "更新成功！");
			editScoreTextField.setText("");
			initTable();
		}else{
			JOptionPane.showMessageDialog(this, "更新失败！");
		}
		scoreDao.closeDao();
	}

	protected void deleteAction(ActionEvent e) {
		int row = scoreListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请先选择一行！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "确定要删除么？") == JOptionPane.OK_OPTION){
			int scoreId = Integer.parseInt(scoreListTable.getValueAt(row, 0).toString());
			ScoreDao scoreDao = new ScoreDao();
			if(scoreDao.delete(scoreId)){
				JOptionPane.showMessageDialog(this, "删除成功！");
				editScoreTextField.setText("");
				initTable();
			}else{
				JOptionPane.showMessageDialog(this, "删除失败！");
			}
			scoreDao.closeDao();
		}
	}

	protected void tableItemClick(MouseEvent e) {
		String score = (scoreListTable.getValueAt(scoreListTable.getSelectedRow(), 3).toString());
		editScoreTextField.setText(score);
	}

	protected void searchScore(ActionEvent e) {
		String searchStudentName = studentNameTextField.getText().toString();
		Course course = (Course)searchCourseComboBox.getSelectedItem();
		Score score = new Score();
		if(searchCourseComboBox.getSelectedItem() == null) {
			score.setCourse_id(0);
		}else {
			score.setCourse_id(course.getId());
		}
		score.setStudent_id(getStudentIdByName(searchStudentName));
		if(isSearchCourseRadioButton.isSelected() == false) { //是否与课程进行联合搜索
			score.setCourse_id(0);
		}
		setTable(score);
	}

	private void initTable(){
		StudentDao studentDao = new StudentDao();
		studentList = studentDao.getStudentList(new Student());
		studentDao.closeDao();
		getScoreList(new Score());
	}
	private void setTable(Score score){
		
		getScoreList(score);
	}
	private void getScoreList(Score score){
		if("学生".equals(MainFrame.userType.getName())) {
			Student s = (Student)MainFrame.userObject;
			studentNameTextField.setText(s.getName());
			score.setStudent_id(getStudentIdByName(s.getName()));
		}
		ScoreDao scoreDao = new ScoreDao();
		List<Score> scoreList = scoreDao.getScoreList(score);
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
	
	private void setCourseCombox(){
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		courseDao.closeDao();
		for (Course course : courseList) {
			if("教师".equals(MainFrame.userType.getName())){
				Teacher teacher = (Teacher)MainFrame.userObject;
				if(course.getTeacher_id() == teacher.getId()){
					searchCourseComboBox.addItem(course);
				}
				continue;
			}
			//执行到这里一定是超级管理员身份
			searchCourseComboBox.addItem(course);
		}
		
	}
	
	private int getStudentIdByName(String studentName) {
		int retId = 0;
		for(Student student :studentList){
			if(studentName.equals(student.getName())) {
				retId = student.getId();
				break;
			}
		}
		return retId;
	}
	
	private void setAuthority() {
		if("学生".equals(MainFrame.userType.getName())) {
			editScoreTextField.setEnabled(false);
			submitEditButton.setEnabled(false);
			deleteScoreButton.setEnabled(false);
			studentNameTextField.setEnabled(false);
		}
		if("教师".equals(MainFrame.userType.getName())) {
			isSearchCourseRadioButton.setSelected(true);
			editScoreTextField.setEnabled(false);
			submitEditButton.setEnabled(false);
			deleteScoreButton.setEnabled(false);
		}
	}
}
