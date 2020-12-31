package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClassDao;
import model.Classes;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageClassFrame extends JInternalFrame {
	private JTextField searchClassNameTextField;
	private JTable classListTable;
	private JTextField editClassNameTextField;
	
	private JTextArea EditClassInfoTextArea;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassManagerFrame frame = new ClassManagerFrame();
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
	public ManageClassFrame() {
		setTitle("班级信息管理");
		setBounds(100, 100, 526, 439);
		
		setLocation(0, 0);
		
		setClosable(true);
		setIconifiable(true);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("班级名称：");
		lblNewLabel.setIcon(new ImageIcon(ManageClassFrame.class.getResource("/images/班级列表.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchClassNameTextField = new JTextField();
		searchClassNameTextField.setColumns(10);
		
		JButton searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes cls = new Classes();
				cls.setName(searchClassNameTextField.getText().toString());
				setTable(cls);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageClassFrame.class.getResource("/images/查询.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("    班级名称：");
		lblNewLabel_1.setIcon(new ImageIcon(ManageClassFrame.class.getResource("/images/班级信息.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("班级信息：");
		lblNewLabel_2.setIcon(new ImageIcon(ManageClassFrame.class.getResource("/images/班级信息 (1).png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editClassNameTextField = new JTextField();
		editClassNameTextField.setColumns(10);
		
		EditClassInfoTextArea = new JTextArea();
		
		JButton submitEidtButton = new JButton("确认修改");
		submitEidtButton.addActionListener(new ActionListener() {
			/*
			 * 点击提交修改按钮的事件
			 */
			public void actionPerformed(ActionEvent e) {
				submitEditAction(e);
			}
		});
		submitEidtButton.setIcon(new ImageIcon(ManageClassFrame.class.getResource("/images/确认.png")));
		submitEidtButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton submitDeleteButton = new JButton("删除");
		submitDeleteButton.addActionListener(new ActionListener() {
			/*
			 * 删除按钮事件
			 */
			public void actionPerformed(ActionEvent e) {
				deleteClassAction(e);
			}
		});
		submitDeleteButton.setIcon(new ImageIcon(ManageClassFrame.class.getResource("/images/删 除.png")));
		submitDeleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(EditClassInfoTextArea))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editClassNameTextField, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(submitEidtButton)
								.addComponent(submitDeleteButton, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchClassNameTextField, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(searchButton)))
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(searchClassNameTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(editClassNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(submitEidtButton)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(EditClassInfoTextArea, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
									.addContainerGap())
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(submitDeleteButton)
									.addGap(22))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblNewLabel_2)
							.addContainerGap())))
		);
		
		classListTable = new JTable();
		classListTable.addMouseListener(new MouseAdapter() {
			/*
			 * 鼠标点击列表
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				//JOptionPane.showMessageDialog(null, "clik");
				selectedTableRow(e);
			}
		});
		classListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		classListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u73ED\u7EA7\u7F16\u53F7", "\u73ED\u7EA7\u540D\u79F0", "\u73ED\u7EA7\u4FE1\u606F\u4ECB\u7ECD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		classListTable.getColumnModel().getColumn(2).setPreferredWidth(265);
		scrollPane.setViewportView(classListTable);
		getContentPane().setLayout(groupLayout);
        
		//默认显示所有列表
		setTable(new Classes());
	}
	
	protected void deleteClassAction(ActionEvent e) {
		// TODO Auto-generated method stub
		
		int index = classListTable.getSelectedRow();
		if(index == -1) {
			System.out.println("未选中要删除的班级！");
			JOptionPane.showMessageDialog(this, "请选中要删除的数据！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "是否确定删除？") != JOptionPane.OK_OPTION) {
			System.out.println("取消删除");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel)classListTable.getModel();
		int id = Integer.parseInt(dft.getValueAt(classListTable.getSelectedRow(), 0).toString());
		ClassDao classDao = new ClassDao();
		if(classDao.delete(id)) {
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		classDao.closeDao();
		//刷新列表
		setTable(new Classes());
		//classDao.closeDao();
	}

	protected void submitEditAction(ActionEvent e) {
		// TODO Auto-generated method stub
		ClassDao classDao = new ClassDao();
		int index = classListTable.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(this, "请选中要修改的数据！");
			return;
		}
		DefaultTableModel defaultTableModel = (DefaultTableModel) classListTable.getModel();
		String className = defaultTableModel.getValueAt(classListTable.getSelectedRow(), 1).toString();
		String classInfo = defaultTableModel.getValueAt(classListTable.getSelectedRow(), 2).toString();
		String editClassName = editClassNameTextField.getText().toString();
		String editClassInfo = EditClassInfoTextArea.getText().toString();
		if(StringUtil.isEmpty(editClassName)) {
			JOptionPane.showMessageDialog(this, "请填写要修改的班级名称！");
			return;
		}
		if(className.equals(editClassName) && classInfo.equals(editClassInfo)) {
			JOptionPane.showMessageDialog(this, "您未做任何修改！");
			return;
		}
		int id = Integer.parseInt(defaultTableModel.getValueAt(classListTable.getSelectedRow(), 0).toString());
		Classes cls = new Classes();
		cls.setId(id);
		cls.setName(editClassName);
		cls.setInfo(editClassInfo);
		if(classDao.update(cls)) {
			JOptionPane.showMessageDialog(this, "更新成功！");
		}else {
			JOptionPane.showMessageDialog(this, "更新失败！");
		}
		classDao.closeDao();
		setTable(new Classes());
	}

	protected void selectedTableRow(MouseEvent e) {
		// TODO Auto-generated method stub
		DefaultTableModel defaultTableModel = (DefaultTableModel) classListTable.getModel();
		//editClassNameTextField.setText(defaultTableModel.getValueAt(classListTable.getSelectedRow(), classListTable.getSelectedColumn()).toString());
		//在文本框内显示点中的班级信息
		editClassNameTextField.setText(defaultTableModel.getValueAt(classListTable.getSelectedRow(), 1).toString());
		EditClassInfoTextArea.setText(defaultTableModel.getValueAt(classListTable.getSelectedRow(), 2).toString());
	}

	/*
	 * 设置班级列表，显示查询到的列表
	 */
	private void setTable(Classes classes) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) classListTable.getModel();
		defaultTableModel.setRowCount(0);
		ClassDao classDao = new ClassDao();
		List<Classes> classList = classDao.getClassList(classes);
		for (Classes cls : classList) {
			Vector v = new Vector();
			v.add(cls.getId());
			v.add(cls.getName());
			v.add(cls.getInfo());
			defaultTableModel.addRow(v);
		}
		
		editClassNameTextField.setText("");
		EditClassInfoTextArea.setText("");
		
		classDao.closeDao();
	}
}
