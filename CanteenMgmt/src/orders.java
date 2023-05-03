import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class orders extends JFrame {

	private JPanel contentPane;
	private JTable tblm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orders frame = new orders();
					frame.setUndecorated(true);
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
	Connection con;
	Statement st;
	private static Point point = new Point();
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public orders() {
		Connect();
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
            }
        });
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 521);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setForeground(new Color(0, 0, 0));
		scrollpane.setBackground(SystemColor.text);
		scrollpane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollpane.setBounds(83, 47, 556, 331);
		contentPane.add(scrollpane);
		
		tblm = new JTable();
		scrollpane.setViewportView(tblm);
		
		JLabel lblNewLabel = new JLabel("ORDERS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(326, 10, 81, 25);
		contentPane.add(lblNewLabel);
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.setBackground(SystemColor.textHighlight);
		close.setForeground(SystemColor.text);
		close.setFont(new Font("Tahoma", Font.PLAIN, 17));
		close.setBounds(309, 405, 98, 25);
		contentPane.add(close);
		try {
			st=con.createStatement();
			String query="select * from ordertable";
			ResultSet rs=st.executeQuery(query);
			ResultSetMetaData md=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel)tblm.getModel();
			int col=md.getColumnCount();
			String[] colN=new String[col];
			for(int i=0;i<col;i++) {
				colN[i]=md.getColumnName(i+1);
				
			}
			model.setColumnIdentifiers(colN);
			String oid,fid,price,sid;
			while(rs.next()) {
				oid=rs.getString(1);
				fid=rs.getString(2);
				sid=rs.getString(3);
				price=rs.getString(4);
				String[] row= {oid,fid,sid,price};
				model.addRow(row);
			}
			st.close();
			con.close();
			
		}
		catch(Exception d) {
			d.printStackTrace();
		}
	}
}
