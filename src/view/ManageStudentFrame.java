package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClassDao;
import dao.StudentDao;
import model.Classes;
import model.Student;
import util.StringUtil;

import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageStudentFrame extends JInternalFrame {
	private JTextField searchStudentNameTextField;
	private JTable searchStudentListTable;
	private JTextField editStudentNameTextField;
	private JComboBox editStudentClassComboBox;
	private JComboBox searchStudentClassComboBox;
	private List<Classes> classList;
	private ButtonGroup editSexButtonGroup;
	private JRadioButton editStudentSexManRadioButton;
	private JRadioButton editStudentSexFemalRadioButton;
	private JTextField editStudentPasswordField;
	
	private JButton deleteStudentButton;
	private JButton searchAllButton;
	private JButton searchButton;
	private JButton submitEditButton;

	/**
	 * Create the frame.
	 */
	public ManageStudentFrame() {
		setTitle("学生信息管理");
		setBounds(100, 100, 833, 456);
		
		setLocation(0, 0);
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("学生姓名：");
		lblNewLabel.setIcon(new ImageIcon(ManageStudentFrame.class.getResource("/images/学生.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchStudentNameTextField = new JTextField();
		searchStudentNameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("所属班级：");
		lblNewLabel_1.setIcon(new ImageIcon(ManageStudentFrame.class.getResource("/images/班级信息.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchStudentClassComboBox = new JComboBox();
		
		JScrollPane scrollPane = new JScrollPane();
		
		searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {
			/*
			 * 点击查询按钮
			 */
			public void actionPerformed(ActionEvent e) {
				searchStudent(e);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageStudentFrame.class.getResource("/images/查询.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("学生姓名：");
		lblNewLabel_2.setIcon(new ImageIcon(ManageStudentFrame.class.getResource("/images/学生.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editStudentNameTextField = new JTextField();
		editStudentNameTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("所属班级：");
		lblNewLabel_3.setIcon(new ImageIcon(ManageStudentFrame.class.getResource("/images/班级信息.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editStudentClassComboBox = new JComboBox();
		
		JLabel lblNewLabel_4 = new JLabel("学生性别：");
		lblNewLabel_4.setIcon(new ImageIcon(ManageStudentFrame.class.getResource("/images/性别.png")));
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editSexButtonGroup = new ButtonGroup();
		
		
		editStudentSexManRadioButton = new JRadioButton("男");
		editStudentSexManRadioButton.setSelected(true);
		editStudentSexManRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editStudentSexFemalRadioButton = new JRadioButton("女");
		editStudentSexFemalRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editSexButtonGroup.add(editStudentSexManRadioButton);
		editSexButtonGroup.add(editStudentSexFemalRadioButton);
		
		JLabel lblNewLabel_5 = new JLabel("登录密码：");
		lblNewLabel_5.setIcon(new ImageIcon(ManageStudentFrame.class.getResource("/images/密 码.png")));
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		submitEditButton = new JButton("确认修改");
		submitEditButton.addActionListener(new ActionListener() {
			/*
			 * 点击修改按钮
			 */
			public void actionPerformed(ActionEvent e) {
				submitEdit(e);
			}
		});
		submitEditButton.setIcon(new ImageIcon(ManageStudentFrame.class.getResource("/images/确认.png")));
		submitEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		deleteStudentButton = new JButton("删除学生");
		deleteStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteStudent(e);
			}
		});
		deleteStudentButton.setIcon(new ImageIcon(ManageStudentFrame.class.getResource("/images/删 除.png")));
		deleteStudentButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchAllButton = new JButton("浏览全部");
		searchAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchStudentNameTextField.setText("");
				searchStudentClassComboBox.setSelectedItem(null);
				setTable(new Student());
			}
		});
		searchAllButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editStudentPasswordField = new JTextField();
		editStudentPasswordField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchStudentNameTextField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchStudentClassComboBox, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(searchButton)
							.addGap(18)
							.addComponent(searchAllButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(editStudentClassComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(editStudentNameTextField, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_5))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(editStudentSexManRadioButton)
									.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
									.addComponent(editStudentSexFemalRadioButton))
								.addComponent(editStudentPasswordField, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(submitEditButton)
								.addComponent(deleteStudentButton))
							.addGap(24))
						.addComponent(scrollPane))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(searchStudentNameTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(searchStudentClassComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton)
						.addComponent(searchAllButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(editStudentNameTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(editStudentSexManRadioButton)
						.addComponent(lblNewLabel_2)
						.addComponent(editStudentSexFemalRadioButton)
						.addComponent(submitEditButton))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(editStudentClassComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(editStudentPasswordField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteStudentButton))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		
		searchStudentListTable = new JTable();
		searchStudentListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectTableRow(e);
			}
		});
		searchStudentListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751F\u7F16\u53F7", "\u5B66\u751F\u59D3\u540D", "\u6240\u5C5E\u73ED\u7EA7", "\u5B66\u751F\u6027\u522B", "\u767B\u5F55\u5BC6\u7801"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		searchStudentListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane.setViewportView(searchStudentListTable);
		getContentPane().setLayout(groupLayout);

		setStudentClassInfo();
		setTable(new Student());
		setAuthority();
	}
	
	protected void submitEdit(ActionEvent e) {
		int row = searchStudentListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选中要修改的学生！");
			return;
		}
		String studentName = editStudentNameTextField.getText().toString();
		String studentPassword = editStudentPasswordField.getText().toString();
		if(StringUtil.isEmpty(studentName)) {
			JOptionPane.showMessageDialog(this, "请填写学生姓名！");
			return;
		}
		if(StringUtil.isEmpty(studentPassword)) {
			JOptionPane.showMessageDialog(this, "请填写学生密码！");
			return;
		}
		
		Student student = new Student();
		
		student.setId(Integer.parseInt(searchStudentListTable.getValueAt(row, 0).toString()));
		student.setName(studentName);
		Classes cls = (Classes)editStudentClassComboBox.getSelectedItem();
		student.setClassId(cls.getId());
		student.setPassword(studentPassword);
		
		if(editStudentSexManRadioButton.isSelected()) {
			student.setSex(editStudentSexManRadioButton.getText().toString());
		}
		if(editStudentSexFemalRadioButton.isSelected()) {
			student.setSex(editStudentSexFemalRadioButton.getText().toString());
		}
        StudentDao studentDao = new StudentDao();
		
		if(studentDao.update(student)) {
			JOptionPane.showMessageDialog(this, "信息修改成功！");
		}else {
			JOptionPane.showMessageDialog(this, "信息修改失败！");
		}
		studentDao.closeDao();
		
		setTable(new Student());
		
	}

	protected void selectTableRow(MouseEvent e) {
		DefaultTableModel dft = (DefaultTableModel) searchStudentListTable.getModel();
		editStudentNameTextField.setText(dft.getValueAt(searchStudentListTable.getSelectedRow(), 1).toString());
		editStudentPasswordField.setText(dft.getValueAt(searchStudentListTable.getSelectedRow(), 4).toString());
		String className = dft.getValueAt(searchStudentListTable.getSelectedRow(), 2).toString();
		int i = 0;
		for(i = 0; i < editStudentClassComboBox.getItemCount(); i++) {
			Classes cls = (Classes)editStudentClassComboBox.getItemAt(i);
			if(className.equals(cls.getName())) {
				editStudentClassComboBox.setSelectedIndex(i);
				break;
			}
		}
		if(i == editStudentClassComboBox.getItemCount()) {
			editStudentClassComboBox.setSelectedItem(null);
		}
		String sex = dft.getValueAt(searchStudentListTable.getSelectedRow(), 3).toString();
		editSexButtonGroup.clearSelection();
		if(sex.equals(editStudentSexManRadioButton.getText()))
			editStudentSexManRadioButton.setSelected(true);
		if(sex.equals(editStudentSexFemalRadioButton.getText()))
			editStudentSexFemalRadioButton.setSelected(true);
	}

	/*
	 * 删除选定学生
	 */
	protected void deleteStudent(ActionEvent e) {
		int row = searchStudentListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选中要删除的学生！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "是否确定删除？") != JOptionPane.OK_OPTION) {
			System.out.println("取消删除");
			return;
		}
		StudentDao studentDao = new StudentDao();
		
		if(studentDao.delete(Integer.parseInt(searchStudentListTable.getValueAt(row, 0).toString()))) {
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		studentDao.closeDao();
		
		setTable(new Student());
	}

	protected void searchStudent(ActionEvent e) {
		Student student = new Student();
		student.setName(searchStudentNameTextField.getText().toString());
		Classes cls = (Classes)searchStudentClassComboBox.getSelectedItem();
		if(searchStudentClassComboBox.getSelectedItem() == null) {
			cls = new Classes();
			cls.setId(0);
		}
		student.setClassId(cls.getId());
		setTable(student);
	}

	private void setTable(Student student) {
		if("学生".equals(MainFrame.userType.getName())) {
			Student s = (Student)MainFrame.userObject;
			student.setName(s.getName());
		}else {
		editStudentNameTextField.setText("");
		searchStudentClassComboBox.setSelectedItem(null);
		editStudentClassComboBox.setSelectedItem(null);
		//searchStudentNameTextField.setText("");
		editSexButtonGroup.clearSelection();
		editStudentPasswordField.setText("");}
		
		DefaultTableModel dft = (DefaultTableModel)searchStudentListTable.getModel();
		dft.setRowCount(0);
		StudentDao studentDao = new StudentDao();
		List<Student> studentList = studentDao.getStudentList(student);
		for(Student s:studentList) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(s.getName());
			v.add(getClassNameById(s.getClassId()));
			v.add(s.getSex());
			v.add(s.getPassword());
			dft.addRow(v);
		}
		studentDao.closeDao();
	}
	
	/*
	 * 设置班级信息下拉框
	 */
	private void setStudentClassInfo() {
		ClassDao classDao = new ClassDao();
		classList = classDao.getClassList(new Classes());
		for(Classes cls:classList) {
			searchStudentClassComboBox.addItem(cls);
			editStudentClassComboBox.addItem(cls);
		}
	}
	
	/*
	 * 根据对应id显示班级名称
	 */
	private String getClassNameById(int id) {
		for(Classes cls:classList) {
			if(cls.getId() == id)
				return cls.getName();
		}
		return "";
	}
	
	private void setAuthority() {
		if("学生".equals(MainFrame.userType.getName())) {
			Student s = (Student)MainFrame.userObject;
			searchStudentNameTextField.setText(s.getName());
			searchStudentNameTextField.setEnabled(false);
			searchStudentClassComboBox.setEnabled(false);
			deleteStudentButton.setEnabled(false);
			searchAllButton.setEnabled(false);
			searchButton.setEnabled(false);
			editStudentClassComboBox.setEnabled(false);
		}
		if("教师".equals(MainFrame.userType.getName())) {
			deleteStudentButton.setEnabled(false);
			editStudentClassComboBox.setEnabled(false);
			editStudentNameTextField.setEnabled(false);
			editStudentPasswordField.setEnabled(false);
			submitEditButton.setEnabled(false);
		}
	}
}
