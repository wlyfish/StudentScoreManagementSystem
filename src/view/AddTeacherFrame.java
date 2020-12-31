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
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.TeacherDao;
import model.Teacher;
import model.TeacherTitle;
import util.StringUtil;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTeacherFrame extends JInternalFrame {
	private JTextField teacherNameTextField;
	private JTextField teacherAgeTextField;
	private JTextField teacherPasswordTextField;
	
	private ButtonGroup sexButtonGroup;
	private JRadioButton teacherSexManRadioButton;
	private JRadioButton teacherSexFemalRadioButton;
	private JComboBox teacherTirleComboBox;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTeacherFrame frame = new AddTeacherFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public AddTeacherFrame() {
		setTitle("添加教师");
		setBounds(100, 100, 352, 329);
		
		setClosable(true); //可关闭
		setIconifiable(true); //最大化最小化
		
		JLabel lblNewLabel = new JLabel("教师姓名：");
		lblNewLabel.setIcon(new ImageIcon(AddTeacherFrame.class.getResource("/images/教师.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("教师性别：");
		lblNewLabel_1.setIcon(new ImageIcon(AddTeacherFrame.class.getResource("/images/性别.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("教师职称：");
		lblNewLabel_2.setIcon(new ImageIcon(AddTeacherFrame.class.getResource("/images/职称.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("教师年龄：");
		lblNewLabel_3.setIcon(new ImageIcon(AddTeacherFrame.class.getResource("/images/年龄.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_4 = new JLabel("登陆密码：");
		lblNewLabel_4.setIcon(new ImageIcon(AddTeacherFrame.class.getResource("/images/修改密码 (1).png")));
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		teacherNameTextField = new JTextField();
		teacherNameTextField.setColumns(10);
		
		teacherSexManRadioButton = new JRadioButton("男");
		teacherSexManRadioButton.setSelected(true);
		teacherSexManRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		teacherSexFemalRadioButton = new JRadioButton("女");
		teacherSexFemalRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		sexButtonGroup = new ButtonGroup();
		sexButtonGroup.add(teacherSexManRadioButton);
		sexButtonGroup.add(teacherSexFemalRadioButton);
		
		teacherAgeTextField = new JTextField();
		teacherAgeTextField.setColumns(10);
		
		teacherTirleComboBox = new JComboBox();
		teacherTirleComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		teacherTirleComboBox.setModel(new DefaultComboBoxModel(new TeacherTitle[] {TeacherTitle.PROFESSOR,TeacherTitle.PROFESSOR2,TeacherTitle.LECTURER,TeacherTitle.DEAN,TeacherTitle.DEAN2}));
		
		teacherPasswordTextField = new JTextField();
		teacherPasswordTextField.setColumns(10);
		
		JButton submitButton = new JButton("确认添加");
		submitButton.addActionListener(new ActionListener() {
			/*
			 * 添加教师
			 */
			public void actionPerformed(ActionEvent e) {
				addTeacherAction(e);
			}
		});
		submitButton.setIcon(new ImageIcon(AddTeacherFrame.class.getResource("/images/确认.png")));
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton resetButton = new JButton("重置表单");
		resetButton.addActionListener(new ActionListener() {
			/*
			 * 重置表单
			 */
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		resetButton.setIcon(new ImageIcon(AddTeacherFrame.class.getResource("/images/重置.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(submitButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(resetButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addGap(18)
							.addComponent(teacherPasswordTextField))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addGap(18)
							.addComponent(teacherAgeTextField))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(teacherTirleComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(teacherSexManRadioButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(teacherSexFemalRadioButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(teacherNameTextField)))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(teacherNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(teacherSexManRadioButton)
						.addComponent(teacherSexFemalRadioButton))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(teacherTirleComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(teacherAgeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(teacherPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(resetButton))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

	protected void resetValue(ActionEvent e) {
		// TODO Auto-generated method stub
		teacherNameTextField.setText("");
		teacherAgeTextField.setText("");
		teacherPasswordTextField.setText("");
		teacherTirleComboBox.setSelectedIndex(0);
		sexButtonGroup.clearSelection();
		teacherSexManRadioButton.setSelected(true);
	}

	protected void addTeacherAction(ActionEvent e) {
		// TODO Auto-generated method stub
		int teacherAge;
		String teacherName = teacherNameTextField.getText().toString();
		String teacherSex = teacherSexManRadioButton.isSelected() ? teacherSexManRadioButton.getText().toString() : teacherSexFemalRadioButton.getText().toString();
		String teacherTitle = teacherTirleComboBox.getSelectedItem().toString();
		
		if(StringUtil.isEmpty(teacherName)) {
			JOptionPane.showMessageDialog(this, "教师名称必须填写！");
			return;
		}
		if(StringUtil.isEmpty(teacherTitle)) {
			JOptionPane.showMessageDialog(this, "教师职称必须填写！");
			return;
		}
		try {
			teacherAge = Integer.parseInt(teacherAgeTextField.getText().toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "年龄只允许输入数字！");
			return;
		}
		if(teacherAge < 0 || teacherAge == 0) {
			JOptionPane.showMessageDialog(this, "教师年龄必须大于0！");
			return;
		}
		String teacherPassword = teacherPasswordTextField.getText().toString();
		if(StringUtil.isEmpty(teacherPassword)) {
			JOptionPane.showMessageDialog(this, "教师密码必须填写！");
			return;
		}
		Teacher teacher = new Teacher();
		teacher.setName(teacherName);
		teacher.setSex(teacherSex);
		teacher.setTitle(teacherTitle);
		teacher.setAge(teacherAge);
		teacher.setPassword(teacherPassword);
		TeacherDao teacherDao = new TeacherDao();
		if(teacherDao.addTeacher(teacher)) {
			JOptionPane.showMessageDialog(this, "教师添加成功！");
		}else {
			JOptionPane.showMessageDialog(this, "教师添加失败！");
		}
		resetValue(e);
	}
}
