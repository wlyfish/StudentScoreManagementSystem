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
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.ClassDao;
import model.Classes;
import util.StringUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddClassFrame extends JInternalFrame {
	private JTextField ClassNameTextField;
	private JTextArea ClassInfoTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddClassFrame frame = new AddClassFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddClassFrame() {
		setTitle("添加班级信息");
		setBounds(100, 100, 324, 275);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("  班级名称：");
		lblNewLabel.setIcon(new ImageIcon(AddClassFrame.class.getResource("/images/班级信息.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("班级信息：");
		lblNewLabel_1.setIcon(new ImageIcon(AddClassFrame.class.getResource("/images/班级信息 (1).png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		ClassNameTextField = new JTextField();
		ClassNameTextField.setColumns(10);
		
		ClassInfoTextArea = new JTextArea();
		
		JButton submitButton = new JButton("提交");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitClass(e);
			}
		});
		submitButton.setIcon(new ImageIcon(AddClassFrame.class.getResource("/images/确认.png")));
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		resetButton.setIcon(new ImageIcon(AddClassFrame.class.getResource("/images/重置.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(ClassNameTextField)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(ClassInfoTextArea, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(196, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(submitButton)
					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
					.addComponent(resetButton)
					.addGap(27))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(ClassNameTextField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(ClassInfoTextArea, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(resetButton))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}

	protected void submitClass(ActionEvent e) {
		// TODO Auto-generated method stub
		String className = ClassNameTextField.getText().toString();
		String classInfo = ClassInfoTextArea.getText().toString();
		if(StringUtil.isEmpty(className)){
			JOptionPane.showMessageDialog(this, "班级名称不能为空！");
			return;
		}
		Classes cls = new Classes();
		cls.setName(className);
		cls.setInfo(classInfo);
		ClassDao classDao = new ClassDao();
		if(classDao.addClass(cls)) {
			JOptionPane.showMessageDialog(this, "班级添加成功！");
			System.out.println("添加班级名称：" + cls.getName());
		}else {
			JOptionPane.showMessageDialog(this, "班级添加失败！");
			System.out.println("添加班级失败");
		}
		classDao.closeDao();
		resetValue(e);
	}

	protected void resetValue(ActionEvent e) {
		// TODO Auto-generated method stub
		ClassNameTextField.setText("");
		ClassInfoTextArea.setText("");
	}
}
