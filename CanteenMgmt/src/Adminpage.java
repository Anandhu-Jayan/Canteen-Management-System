import java.awt.EventQueue;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.util.Enumeration;
import java.awt.SystemColor;
import java.awt.Color;
public class Adminpage {

	private JFrame frame;
	private JTextField idtxt;
	private JTextField fidtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adminpage window = new Adminpage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Adminpage() {
		initialize();
		Connect();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	Connection con;
	PreparedStatement pat;
	private JTextField nametxt;
	private JTextField pricetxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField oidtxt;

	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
		} catch (Exception e) {

		}
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.text);
		frame.setBounds(100, 100, 950, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Food Id:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(33, 92, 123, 75);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Food Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(33, 184, 123, 54);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(33, 264, 123, 35);
		frame.getContentPane().add(lblNewLabel_2);
		
		idtxt = new JTextField();
		idtxt.setBounds(179, 116, 139, 27);
		frame.getContentPane().add(idtxt);
		idtxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Order Id:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(511, 116, 174, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton dltob = new JButton("Delete");
		dltob.setForeground(SystemColor.text);
		dltob.setBackground(SystemColor.textHighlight);
		dltob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String strf=oidtxt.getText();
					int oid=Integer.parseInt(strf);  
					pat = con.prepareStatement("delete from ordertable where orderid= ?");
					pat.setInt(1, oid);
					pat.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Order Removed");
					oidtxt.setText("");
					
					}
				catch (Exception d) {
					d.printStackTrace();
				}
				
			}
			
		});
		dltob.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dltob.setBounds(511, 175, 137, 38);
		frame.getContentPane().add(dltob);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Food Id:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(511, 314, 149, 20);
		frame.getContentPane().add(lblNewLabel_4);
		
		fidtxt = new JTextField();
		fidtxt.setBounds(695, 307, 134, 27);
		frame.getContentPane().add(fidtxt);
		fidtxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Add Food");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(92, 26, 149, 41);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Remove Order");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_6.setBounds(568, 26, 199, 41);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Remove  Item");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_7.setBounds(568, 249, 199, 27);
		frame.getContentPane().add(lblNewLabel_7);
		
		JButton dltb = new JButton("Delete");
		dltb.setForeground(SystemColor.text);
		dltb.setBackground(SystemColor.textHighlight);
		dltb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String strf=fidtxt.getText();
					int fid=Integer.parseInt(strf);  
					pat = con.prepareStatement("delete from foodtable where foodid= ?");
					pat.setInt(1, fid);
					pat.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Item Removed From Menu");
					fidtxt.setText("");
					
					}
				catch (Exception d) {
					d.printStackTrace();
				}
			}
		});
		dltb.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dltb.setBounds(511, 378, 137, 38);
		frame.getContentPane().add(dltb);
		
		nametxt = new JTextField();
		nametxt.setColumns(10);
		nametxt.setBounds(179, 202, 139, 27);
		frame.getContentPane().add(nametxt);
		
		pricetxt = new JTextField();
		pricetxt.setColumns(10);
		pricetxt.setBounds(179, 272, 139, 27);
		frame.getContentPane().add(pricetxt);
		
		JLabel typetxt = new JLabel("Type");
		typetxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		typetxt.setBounds(33, 326, 123, 35);
		frame.getContentPane().add(typetxt);
		
		JRadioButton veg = new JRadioButton("Veg");
		veg.setBackground(SystemColor.text);
		buttonGroup.add(veg);
		veg.setSelected(true);
		veg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		veg.setBounds(177, 337, 103, 21);
		frame.getContentPane().add(veg);
		
		JRadioButton nveg = new JRadioButton("Non Veg");
		nveg.setBackground(SystemColor.text);
		buttonGroup.add(nveg);
		nveg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nveg.setBounds(179, 371, 109, 27);
		frame.getContentPane().add(nveg);
		
		JButton addb = new JButton("Add Item");
		addb.setForeground(SystemColor.text);
		addb.setBackground(SystemColor.textHighlight);
		addb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String str1=idtxt.getText();
					int id=Integer.parseInt(str1);  
					String str2=nametxt.getText();
					String str3=pricetxt.getText();
					int pri=Integer.parseInt(str3);  
					String str4="Veg";
					for (Enumeration<AbstractButton> Buttons = buttonGroup.getElements(); Buttons.hasMoreElements();) {
			            AbstractButton button = Buttons.nextElement();

			            if (button.isSelected()) {
			                str4=button.getText();
			            }
			        }

					
					pat = con.prepareStatement("insert into foodtable(foodid,foodname,price,type) values(?,?,?,?)");
					pat.setInt(1, id);
					pat.setString(2, str2);
					pat.setInt(3, pri);
					pat.setString(4, str4);
					pat.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Food Added to Menu");
					idtxt.setText("");
					nametxt.setText("");
					pricetxt.setText("");
					
					
					}
				catch (Exception d) {
					d.printStackTrace();
				}
				
			}
		});
		addb.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addb.setBounds(33, 410, 137, 38);
		frame.getContentPane().add(addb);
		
		oidtxt = new JTextField();
		oidtxt.setColumns(10);
		oidtxt.setBounds(695, 116, 134, 27);
		frame.getContentPane().add(oidtxt);
		
		JButton btnNewButton = new JButton("View Menu");
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.main(null);
				
			}
			
		});
		btnNewButton.setBounds(695, 378, 137, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnViewOrders = new JButton("View Orders");
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orders.main(null);
			}
		
			
		});
		btnViewOrders.setForeground(Color.WHITE);
		btnViewOrders.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnViewOrders.setBackground(SystemColor.textHighlight);
		btnViewOrders.setBounds(695, 175, 137, 38);
		frame.getContentPane().add(btnViewOrders);
	}
}
