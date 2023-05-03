import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;



public class Ordercancel {
	private JFrame frame;
	private JTextField txtOrder;
	private JTextField txtStudent;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ordercancel window = new Ordercancel();
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
	public Ordercancel() {
		initialize();
		Connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	Connection con;
	PreparedStatement pat;
	ResultSet rs;

	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
		} catch (ClassNotFoundException e)
		{
			 e.printStackTrace();

		}
		catch (SQLException e)
        {
            e.printStackTrace();
        }
	}
	
	  public void table_load()
	   {
		 String stid=txtStudent.getText();
	     try
	     {
	    pat = con.prepareStatement("select * from ordertable where studentid = ?");
	    pat.setString(1, stid);
	    rs = pat.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	     }
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	     }
	  }
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 40));
		frame.setBounds(100, 100, 846, 572);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Order cancel");
		lblNewLabel.setBounds(283, 22, 226, 75);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student Id:");
		lblNewLabel_1.setBounds(37, 121, 125, 42);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		frame.getContentPane().add(lblNewLabel_1);
		
		txtOrder = new JTextField();
		txtOrder.setBounds(222, 435, 137, 30);
		frame.getContentPane().add(txtOrder);
		txtOrder.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Order Id:");
		lblNewLabel_2.setBounds(37, 435, 106, 30);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		frame.getContentPane().add(lblNewLabel_2);
		
		txtStudent = new JTextField();
		txtStudent.setBounds(222, 130, 137, 27);
		frame.getContentPane().add(txtStudent);
		txtStudent.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel order");
		btnCancel.setBounds(369, 434, 106, 33);
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBorder(null);
		btnCancel.setBackground(new Color(0, 128, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String bid;
				  bid  = txtOrder.getText();
				  try {
				  pat = con.prepareStatement("delete from ordertable where orderid =?");
				              pat.setString(1, bid);
				              pat.executeUpdate();
				              JOptionPane.showMessageDialog(null, "Order Deleted");
				              table_load();
				              txtOrder.setText("");
				            
				           
				  }
				   
				              catch (SQLException e1) {
				  e1.printStackTrace();
				  }
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		frame.getContentPane().add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 187, 483, 214);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JButton btnView = new JButton("View orders");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load();
				
			}
		});
		btnView.setForeground(Color.WHITE);
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnView.setBorder(null);
		btnView.setBackground(new Color(0, 128, 255));
		btnView.setBounds(369, 128, 106, 33);
		frame.getContentPane().add(btnView);
	}
}
