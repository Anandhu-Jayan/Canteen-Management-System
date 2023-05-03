import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.Font;

public class Studentreg extends JFrame {

	private JPanel contentPane;
	private JTextField tname;
	private JTextField tid;
	private JTextField temail;
	private JTextField tage;
	private JTextField tbatch;
	private JTextField tno;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton m;
	private JRadioButton f;
	private JButton submit;
	private JButton reset;
	private JLabel lblUsername;
	private JTextField un;
	private JTextField pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Studentreg frame = new Studentreg();
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
	public Studentreg() {
		String name,email,batch;
		int id,no,age;
		setVisible(true);
		setForeground(new Color(255, 255, 255));
		setBackground(Color.DARK_GRAY);
		setTitle("Student Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 30, 533, 711);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lname = new JLabel("Student name");
		lname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lname.setBounds(77, 28, 175, 32);
		contentPane.add(lname);
		
		tname = new JTextField();
		lname.setLabelFor(tname);
		tname.setBounds(262, 30, 200, 32);
		contentPane.add(tname);
		tname.setColumns(10);
		
		JLabel lid = new JLabel("Student ID");
		lid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lid.setBounds(77, 89, 175, 32);
		contentPane.add(lid);
		
		tid = new JTextField();
		tid.setBounds(262, 91, 200, 32);
		contentPane.add(tid);
		tid.setColumns(10);
		
		JLabel lemail = new JLabel("Email ID");
		lemail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lemail.setBounds(77, 148, 175, 32);
		contentPane.add(lemail);
		
		temail = new JTextField();
		temail.setBounds(262, 150, 200, 32);
		contentPane.add(temail);
		temail.setColumns(10);
		
		JLabel lage = new JLabel("Age");
		lage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lage.setBounds(77, 210, 175, 32);
		contentPane.add(lage);
		
		JLabel lgender = new JLabel("Gender");
		lgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lgender.setBounds(77, 272, 175, 32);
		contentPane.add(lgender);
		
		JLabel lbatch = new JLabel("Batch");
		lbatch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbatch.setBounds(77, 325, 175, 32);
		contentPane.add(lbatch);
		
		JLabel lno = new JLabel("Mobile no.");
		lno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lno.setBounds(77, 387, 175, 32);
		contentPane.add(lno);
		
		tage = new JTextField();
		tage.setBounds(262, 212, 200, 32);
		contentPane.add(tage);
		tage.setColumns(10);
		
		tbatch = new JTextField();
		tbatch.setBounds(262, 327, 200, 32);
		contentPane.add(tbatch);
		tbatch.setColumns(10);
		
		tno = new JTextField();
		tno.setBounds(262, 389, 200, 32);
		contentPane.add(tno);
		tno.setColumns(10);
		
		m = new JRadioButton("Male");
		m.setFont(new Font("Tahoma", Font.PLAIN, 16));
		m.setOpaque(false);
		buttonGroup.add(m);
		m.setBounds(262, 272, 99, 32);
		contentPane.add(m);
		
		f = new JRadioButton("Female");
		f.setFont(new Font("Tahoma", Font.PLAIN, 16));
		f.setOpaque(false);
		buttonGroup.add(f);
		f.setBounds(363, 272, 99, 32);
		contentPane.add(f);
		
		submit = new JButton("SUBMIT");
		submit.setForeground(new Color(255, 255, 255));
		submit.setBorderPainted(false);
		submit.setBackground(new Color(0, 0, 255));
		submit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
					String sql="insert into studenttable values(?,?,?,?,?,?,?)";
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(tid.getText()));
					ps.setString(2, tname.getText());
					ps.setString(3, temail.getText());
					ps.setInt(4, Integer.parseInt(tage.getText()));
					if(m.isSelected())
						ps.setString(5, "M");
					else 
						ps.setString(5, "F");
					ps.setString(6, tno.getText());
					ps.setString(7, tbatch.getText());
					int i=ps.executeUpdate();
					String sql1="insert into logintable values(?,?,?)";
					PreparedStatement ps1=con.prepareStatement(sql1);
					ps1.setString(1, un.getText());
					ps1.setString(2, pw.getText());
					ps1.setInt(3, Integer.parseInt(tid.getText()));
					int k=ps1.executeUpdate();
					JOptionPane.showMessageDialog(submit, "Registration completed" );
					ps.close();
					con.close();
					tname.setText("");
					tid.setText("");
					tage.setText("");
					temail.setText("");
					tno.setText("");
					tbatch.setText("");
					un.setText("");
					pw.setText("");
					buttonGroup.clearSelection();
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		submit.setBounds(92, 582, 130, 40);
		contentPane.add(submit);
		
		reset = new JButton("RESET");
		reset.setForeground(new Color(255, 255, 255));
		reset.setBackground(new Color(0, 0, 255));
		reset.setBorderPainted(false);
		reset.setFont(new Font("Tahoma", Font.PLAIN, 18));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tname.setText("");
				tid.setText("");
				tage.setText("");
				temail.setText("");
				tno.setText("");
				tbatch.setText("");
				un.setText("");
				pw.setText("");
				buttonGroup.clearSelection();
			}
		});
		
		reset.setBounds(279, 582, 130, 40);
		contentPane.add(reset);
		
		lblUsername = new JLabel("Username\r\n");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(77, 448, 175, 32);
		contentPane.add(lblUsername);
		
		un = new JTextField();
		un.setColumns(10);
		un.setBounds(262, 451, 200, 32);
		contentPane.add(un);
		
		JLabel lblUsername_1 = new JLabel("Password\r\n");
		lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername_1.setBounds(77, 513, 175, 32);
		contentPane.add(lblUsername_1);
		
		pw = new JTextField();
		pw.setColumns(10);
		pw.setBounds(262, 516, 200, 32);
		contentPane.add(pw);
		//contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lname, tname, lid, tid}));
	}
}

