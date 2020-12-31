package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.ClassDao;
import dao.StudentDao;
import model.Classes;
import model.Student;
import util.StringUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddStudentFrame extends JInternalFrame {
	private JTextField studentNameTextField;
	private JPasswordField studentPasswordField;
	
	private JComboBox classNameComboBox;
	private ButtonGroup sexButtonGroup;
	
	private JRadioButton studentSexManRadioButton;
	private JRadioButton studentSexFeamlRadioButton;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentFrame frame = new AddStudentFrame();
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
	public AddStudentFrame() {
		setTitle("添加学生");
		setBounds(100, 100, 392, 286);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("学生姓名：");
		lblNewLabel.setIcon(new ImageIcon(AddStudentFrame.class.getResource("/images/学生.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentNameTextField = new JTextField();
		studentNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("所属班级：");
		lblNewLabel_1.setIcon(new ImageIcon(AddStudentFrame.class.getResource("/images/班级信息.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("登录密码：");
		lblNewLabel_2.setIcon(new ImageIcon(AddStudentFrame.class.getResource("/images/密 码.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentPasswordField = new JPasswordField();
		
		classNameComboBox = new JComboBox();
		
		JLabel lblNewLabel_3 = new JLabel("学生性别：");
		lblNewLabel_3.setIcon(new ImageIcon(AddStudentFrame.class.getResource("/images/性别.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentSexManRadioButton = new JRadioButton("男");
		
		studentSexFeamlRadioButton = new JRadioButton("女");
		
		sexButtonGroup = new ButtonGroup();
		sexButtonGroup.add(studentSexManRadioButton);
		sexButtonGroup.add(studentSexFeamlRadioButton);
		
		JButton confirmButton = new JButton("确认");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentAddAction(e);
			}
		});
		confirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		confirmButton.setIcon(new ImageIcon(AddStudentFrame.class.getResource("/images/确认.png")));
		
		JButton resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		resetButton.setIcon(new ImageIcon(AddStudentFrame.class.getResource("/images/重置.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(studentPasswordField, 142, 142, 142))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(classNameComboBox, 0, 142, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addGap(18)
									.addComponent(studentSexManRadioButton))
								.addComponent(confirmButton))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(resetButton)
								.addComponent(studentSexFeamlRadioButton))))
					.addGap(61))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(classNameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(studentPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(studentSexManRadioButton)
						.addComponent(studentSexFeamlRadioButton))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmButton)
						.addComponent(resetButton))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

		setStudentClassInfo();
	}

	protected void studentAddAction(ActionEvent e) {
		// TODO Auto-generated method stub
		String studentName = studentNameTextField.getText().toString();
		String studentPassword = studentPasswordField.getText().toString();
		if(StringUtil.isEmpty(studentName)) {
			JOptionPane.showMessageDialog(this, "请填写学生姓名!");
			return;
		}
		if(StringUtil.isEmpty(studentPassword)) {
			JOptionPane.showMessageDialog(this, "请填写学生密码!");
			return;
		}
		Classes cls = (Classes)classNameComboBox.getSelectedItem();
		String sex = studentSexManRadioButton.isSelected() ? studentSexManRadioButton.getText() : studentSexFeamlRadioButton.getText();
		Student student = new Student();
		student.setName(studentName);
		student.setClassId(cls.getId());
		student.setPassword(studentPassword);
		student.setSex(sex);
		StudentDao studentDao = new StudentDao();
		if(studentDao.addStudent(student)) {
			JOptionPane.showMessageDialog(this, "添加成功!");
		}else {
			JOptionPane.showMessageDialog(this, "添加失败!");
		}
		resetValue(e);
	}

	protected void resetValue(ActionEvent e) {
		// TODO Auto-generated method stub
		studentNameTextField.setText("");
		studentPasswordField.setText("");
		sexButtonGroup.clearSelection();
		studentSexManRadioButton.setSelected(true);
	}
	
	private void setStudentClassInfo() {
		ClassDao classDao = new ClassDao();
		List<Classes> classList = classDao.getClassList(new Classes());
		for(Classes cls:classList) {
			classNameComboBox.addItem(cls);
		}
		classDao.closeDao();
	}
}
