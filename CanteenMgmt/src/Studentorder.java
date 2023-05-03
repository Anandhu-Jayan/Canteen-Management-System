

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Studentorder {

	private JFrame frame;
	private JTable table;

	
	
	public static void main( final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final String ssid=args[0];
					Studentorder window = new Studentorder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 */
	public Studentorder() {
		Connect();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	Connection con;
	PreparedStatement pat;
	Statement st;
	private JTextField textField;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.text);
		frame.setBounds(100, 100, 802, 735);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 88, 530, 390);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("MENU");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(352, 33, 93, 25);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(267, 511, 139, 27);
		frame.getContentPane().add(textField);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Food Id:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(127, 518, 149, 20);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String str1="select max(orderid) from ordertable ";
				PreparedStatement pat = (PreparedStatement) con.prepareStatement(str1);
				
				ResultSet rs=pat.executeQuery();
				String ooid="";
				int oid=1;
				while(rs.next()) {
					 ooid=rs.getString(1);
				
				}
				if(ooid!=null)
					oid=Integer.parseInt(ooid)+1;
			
				String ssid="1";
				int sid=Integer.parseInt(ssid);
				String foid=textField.getText();
				int fiid=Integer.parseInt(foid);
				String str2="select price from foodtable where foodid=?";
				pat=con.prepareStatement(str2);
				pat.setInt(1,fiid);
				rs=pat.executeQuery();
				String price="";
				while(rs.next()) {
					 price=rs.getString(1);
				
				}
				int pri=Integer.parseInt(price);
				
				pat = con.prepareStatement("insert into ordertable(orderid,foodid,studentid,price) values(?,?,?,?)");
				pat.setInt(1, oid);
				pat.setInt(2, fiid);
				pat.setInt(3, sid);
				pat.setInt(4, pri);
				pat.executeUpdate();
				JOptionPane.showMessageDialog(frame, "Order Placed");
				
				
				}
				catch(Exception m) {
					m.printStackTrace();
				}
			}
		});
		btnOrder.setForeground(Color.WHITE);
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOrder.setBackground(SystemColor.textHighlight);
		btnOrder.setBounds(127, 558, 137, 38);
		frame.getContentPane().add(btnOrder);
		
		JButton btnCancelOrder = new JButton("Cancel Order");
		btnCancelOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ordercancel.main(null);
				frame.dispose();
			}
			
		});
		btnCancelOrder.setForeground(Color.WHITE);
		btnCancelOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelOrder.setBackground(SystemColor.textHighlight);
		btnCancelOrder.setBounds(285, 558, 137, 38);
		frame.getContentPane().add(btnCancelOrder);
		try {
			st=con.createStatement();
			String query="select * from foodtable";
			 

			ResultSet rs=st.executeQuery(query);
			ResultSetMetaData md=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			int col=md.getColumnCount();
			String[] colN=new String[col];
			for(int i=0;i<col;i++) {
				colN[i]=md.getColumnName(i+1);
				
			}
			model.setColumnIdentifiers(colN);
			String fid,fname,price,type;
			while(rs.next()) {
				fid=rs.getString(1);
				fname=rs.getString(2);
				price=rs.getString(3);
				type=rs.getString(4);
				String[] row= {fid,fname,price,type};
				model.addRow(row);
			}
			st.close();
			
			
		}
		catch(Exception d) {
			d.printStackTrace();
		}
	}
}
