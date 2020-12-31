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
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.StudentDao;
import dao.TeacherDao;
import model.Classes;
import model.Student;
import model.Teacher;
import model.TeacherTitle;
import util.StringUtil;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageTeacherFrame extends JInternalFrame {
	private JTextField searchTeacherNameTextField;
	private JTable teacherListTable;
	private JTextField editTeacherNameTextField;
	private JTextField editTeacherAgeTextField;
	private JTextField editTeacherPasswordTextField;
	private JButton searchTeacherButton;
	
	private ButtonGroup sexButtonGroup;
	private JRadioButton editTeacherSexManRadioButton;
	private JRadioButton editTeacherSexFemalRadioButton;
	private JComboBox editTeacherTitleComboBox;
	private JButton searchAllButton;
	private JButton deleteTeacherButton;


	/**
	 * Create the frame.
	 */
	public ManageTeacherFrame() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("教师信息管理");
		setBounds(100, 100, 710, 509);
		
		setLocation(0, 0);
		setClosable(true); //可关闭
		setIconifiable(true); //最大化最小化
		
		JLabel lblNewLabel = new JLabel("教师姓名：");
		lblNewLabel.setIcon(new ImageIcon(ManageTeacherFrame.class.getResource("/images/教师.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchTeacherNameTextField = new JTextField();
		searchTeacherNameTextField.setColumns(10);
		
		searchTeacherButton = new JButton("查询");
		searchTeacherButton.addActionListener(new ActionListener() {
			/*
			 * 查询教师姓名
			 */
			public void actionPerformed(ActionEvent e) {
				searchTeacher(e);
			}
		});
		searchTeacherButton.setIcon(new ImageIcon(ManageTeacherFrame.class.getResource("/images/查询.png")));
		searchTeacherButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u6559\u5E08\u4FE1\u606F\u4FEE\u6539", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(119, 136, 153)));
		panel.setBackground(new Color(245, 245, 220));
		
		searchAllButton = new JButton("浏览全部");
		searchAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTeacherNameTextField.setText("");
				setTable(new Teacher());
			}
		});
		searchAllButton.setIcon(new ImageIcon(ManageTeacherFrame.class.getResource("/images/学生管理.png")));
		searchAllButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchTeacherNameTextField, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addGap(72)
							.addComponent(searchTeacherButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(searchAllButton))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 584, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(searchTeacherNameTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchTeacherButton)
						.addComponent(searchAllButton))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_1 = new JLabel("教师姓名：");
		lblNewLabel_1.setIcon(new ImageIcon(ManageTeacherFrame.class.getResource("/images/教师.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editTeacherNameTextField = new JTextField();
		editTeacherNameTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("教师性别：");
		lblNewLabel_2.setIcon(new ImageIcon(ManageTeacherFrame.class.getResource("/images/性别.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editTeacherSexManRadioButton = new JRadioButton("男");
		editTeacherSexManRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editTeacherSexManRadioButton.setSelected(true);
		
		editTeacherSexFemalRadioButton = new JRadioButton("女");
		editTeacherSexFemalRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		sexButtonGroup = new ButtonGroup();
		sexButtonGroup.add(editTeacherSexFemalRadioButton);
		sexButtonGroup.add(editTeacherSexManRadioButton);
		
		JLabel lblNewLabel_3 = new JLabel("教师职称：");
		lblNewLabel_3.setIcon(new ImageIcon(ManageTeacherFrame.class.getResource("/images/职称.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editTeacherTitleComboBox = new JComboBox();
		editTeacherTitleComboBox.setModel(new DefaultComboBoxModel(new TeacherTitle[] {TeacherTitle.PROFESSOR,TeacherTitle.PROFESSOR2,TeacherTitle.LECTURER,TeacherTitle.DEAN,TeacherTitle.DEAN2}));
		editTeacherTitleComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_4 = new JLabel("教师年龄：");
		lblNewLabel_4.setIcon(new ImageIcon(ManageTeacherFrame.class.getResource("/images/年龄.png")));
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editTeacherAgeTextField = new JTextField();
		editTeacherAgeTextField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("登录密码：");
		lblNewLabel_5.setIcon(new ImageIcon(ManageTeacherFrame.class.getResource("/images/密 码.png")));
		
		editTeacherPasswordTextField = new JTextField();
		editTeacherPasswordTextField.setColumns(10);
		
		JButton editTeacherButton = new JButton("确认修改");
		editTeacherButton.addActionListener(new ActionListener() {
			/*
			 * 提交修改
			 */
			public void actionPerformed(ActionEvent e) {
				editTeacherAction(e);
			}
		});
		editTeacherButton.setIcon(new ImageIcon(ManageTeacherFrame.class.getResource("/images/确认.png")));
		editTeacherButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		deleteTeacherButton = new JButton("删除信息");
		deleteTeacherButton.addActionListener(new ActionListener() {
			/*
			 * 删除教师
			 */
			public void actionPerformed(ActionEvent e) {
				deleteTeacher(e);
			}
		});
		deleteTeacherButton.setIcon(new ImageIcon(ManageTeacherFrame.class.getResource("/images/删 除.png")));
		deleteTeacherButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editTeacherNameTextField, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_5))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(editTeacherPasswordTextField, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
								.addComponent(editTeacherTitleComboBox, 0, 171, Short.MAX_VALUE))))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editTeacherSexManRadioButton)
							.addGap(18)
							.addComponent(editTeacherSexFemalRadioButton))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editTeacherAgeTextField))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editTeacherButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(deleteTeacherButton)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(editTeacherNameTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(editTeacherSexManRadioButton)
						.addComponent(editTeacherSexFemalRadioButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4)
						.addComponent(editTeacherAgeTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(editTeacherTitleComboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(editTeacherButton)
						.addComponent(editTeacherPasswordTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteTeacherButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		teacherListTable = new JTable();
		teacherListTable.addMouseListener(new MouseAdapter() {
			/*
			 * 显示选中教师信息
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				selectTableRow(e);
			}
		});
		teacherListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u6559\u5E08\u7F16\u53F7", "\u6559\u5E08\u59D3\u540D", "\u6559\u5E08\u6027\u522B", "\u6559\u5E08\u804C\u79F0", "\u6559\u5E08\u5E74\u9F84", "\u767B\u5F55\u5BC6\u7801"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		teacherListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane.setViewportView(teacherListTable);
		getContentPane().setLayout(groupLayout);

		setTable(new Teacher());
		setAuthority();
	}
	
protected void editTeacherAction(ActionEvent e) {
		// TODO Auto-generated method stub
	int row = teacherListTable.getSelectedRow();
	if(row == -1) {
		JOptionPane.showMessageDialog(this, "请选择要修改的数据！");
		return;
	}
	String teacherName = editTeacherNameTextField.getText().toString();
	String teacherSex = editTeacherSexManRadioButton.isSelected() ? editTeacherSexManRadioButton.getText().toString() : editTeacherSexFemalRadioButton.getText().toString();
	String teacherTitle = editTeacherTitleComboBox.getSelectedItem().toString();
	int teacherAge = 0;
	try {
		teacherAge = Integer.parseInt(editTeacherAgeTextField.getText().toString());
	} catch (NumberFormatException e1) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(this, "年龄只允许输入数字！");
		return;
	}
	String teacherPassword = editTeacherPasswordTextField.getText().toString();
	if(StringUtil.isEmpty(teacherName)) {
		JOptionPane.showMessageDialog(this, "教师姓名必须填写！");
		return;
	}
	if(teacherAge == 0 || teacherAge < 0) {
		JOptionPane.showMessageDialog(this, "教师年龄必须大于0！");
		return;
	}
	if(StringUtil.isEmpty(teacherPassword)) {
		JOptionPane.showMessageDialog(this, "教师密码必须填写！");
		return;
	}
	Teacher teacher = new Teacher();
	teacher.setId(Integer.parseInt(teacherListTable.getValueAt(row, 0).toString()));
	teacher.setName(teacherName);
	teacher.setSex(teacherSex);
	teacher.setTitle(teacherTitle);
	teacher.setAge(teacherAge);
	teacher.setPassword(teacherPassword);
	TeacherDao teacherDao = new TeacherDao();
	if(teacherDao.update(teacher)) {
		JOptionPane.showMessageDialog(this, "修改成功！");
	}else {
		JOptionPane.showMessageDialog(this, "修改失败！");
	}
	teacherDao.closeDao();
	setTable(new Teacher());
    }

protected void selectTableRow(MouseEvent e) {
		// TODO Auto-generated method stub
	DefaultTableModel dft = (DefaultTableModel) teacherListTable.getModel();
	editTeacherNameTextField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 1).toString());
	editTeacherPasswordTextField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 5).toString());
	editTeacherAgeTextField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 4).toString());
	String teacherTitle = dft.getValueAt(teacherListTable.getSelectedRow(), 3).toString();
	int i = 0;
	for(i = 0; i < editTeacherTitleComboBox.getItemCount(); i++) {
		TeacherTitle title = (TeacherTitle)editTeacherTitleComboBox.getItemAt(i);
		if(teacherTitle.equals(title.getTitleName())) {
			editTeacherTitleComboBox.setSelectedIndex(i);
			break;
		}
	}
	if(i == editTeacherTitleComboBox.getItemCount()) {
		editTeacherTitleComboBox.setSelectedItem(null);
	}
	String sex = dft.getValueAt(teacherListTable.getSelectedRow(), 2).toString();
	sexButtonGroup.clearSelection();
	if(sex.equals(editTeacherSexFemalRadioButton.getText()))
		editTeacherSexFemalRadioButton.setSelected(true);
	if(sex.equals(editTeacherSexManRadioButton.getText()))
		editTeacherSexManRadioButton.setSelected(true);
	
	}

protected void searchTeacher(ActionEvent e) {
		// TODO Auto-generated method stub
		String teacherNameString = searchTeacherNameTextField.getText().toString();
		Teacher teacher = new Teacher();
		teacher.setName(teacherNameString);
		setTable(teacher);
	}

protected void deleteTeacher(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = teacherListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选择要删除的数据！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "确定要删除吗？") != JOptionPane.OK_OPTION) {
			return;
		}
		int id = Integer.parseInt(teacherListTable.getValueAt(row, 0).toString());
		TeacherDao teacherDao = new TeacherDao();
		if(teacherDao.delete(id)) {
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		teacherDao.closeDao();
		setTable(new Teacher());
	}

private void setTable(Teacher teacher) {
	    if("教师".equals(MainFrame.userType.getName())) {
	    	Teacher t = (Teacher)MainFrame.userObject;
	    	teacher.setName(t.getName());
	    	searchTeacherNameTextField.setText(teacher.getName());
	    }else {
		editTeacherNameTextField.setText("");
		editTeacherAgeTextField.setText("");
		sexButtonGroup.clearSelection();
		editTeacherSexManRadioButton.setSelected(true);
		editTeacherTitleComboBox.setSelectedIndex(1);
		editTeacherPasswordTextField.setText("");}
		
		DefaultTableModel dft = (DefaultTableModel)teacherListTable.getModel();
		dft.setRowCount(0);
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teacherList = teacherDao.getTeacherList(teacher);
		for(Teacher t:teacherList) {
			Vector v = new Vector();
			v.add(t.getId());
			v.add(t.getName());
			v.add(t.getSex());
			v.add(t.getTitle());
			v.add(t.getAge());
			v.add(t.getPassword());
			dft.addRow(v);
		}
		teacherDao.closeDao();
	}

private void setAuthority() {
	if("教师".equals(MainFrame.userType.getName())) {
		deleteTeacherButton.setEnabled(false);
		searchAllButton.setEnabled(false);
		searchTeacherNameTextField.setEnabled(false);
		editTeacherTitleComboBox.setEnabled(false);
	}
}
}
