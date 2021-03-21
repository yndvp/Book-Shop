import java.awt.EventQueue;
import java.sql.*;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Crud {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crud window = new Crud();
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
	public Crud() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bookshop", "root", "");
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void table_load() {
		try {
			pst = con.prepareStatement("Select * from books");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1106, 564);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Bodoni MT", Font.BOLD, 50));
		lblNewLabel.setBounds(50, 10, 223, 68);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(50, 112, 485, 303);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Bodoni MT", Font.BOLD, 20));
		lblNewLabel_1.setBounds(53, 45, 126, 23);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname, edition, price;
				
				bname = txtname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				try {
					pst = con.prepareStatement("insert into books(name,edition,price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
		
					JOptionPane.showMessageDialog(null, "Record Added");
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtname.requestFocus();
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Gulim", Font.PLAIN, 13));
		btnNewButton.setBounds(91, 222, 114, 60);
		panel.add(btnNewButton);
		
		txtname = new JTextField();
		txtname.setBounds(207, 45, 201, 26);
		panel.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Bodoni MT", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(53, 97, 126, 23);
		panel.add(lblNewLabel_1_1);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(207, 97, 201, 26);
		panel.add(txtedition);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Bodoni MT", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(53, 151, 126, 23);
		panel.add(lblNewLabel_1_1_1);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(207, 151, 201, 26);
		panel.add(txtprice);
		
		// Exit the program
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Gulim", Font.PLAIN, 13));
		btnExit.setBounds(217, 222, 114, 60);
		panel.add(btnExit);
		
		// Clear the text field
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbid.setText("");
				txtname.requestFocus();
			}
		});
		btnClear.setFont(new Font("Gulim", Font.PLAIN, 13));
		btnClear.setBounds(343, 222, 114, 60);
		panel.add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(50, 432, 485, 68);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Book ID");
		lblNewLabel_1_2.setFont(new Font("Bodoni MT", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(37, 22, 97, 28);
		panel_1.add(lblNewLabel_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id = txtbid.getText();
					
					// Prepare Statement
					pst = con.prepareStatement("Select name, edition, price from books where id = ?");
					// Set parameter values for type and position
					pst.setString(1, id);
					// Execute the query
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true) {
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtname.setText(name);
						txtedition.setText(edition);
						txtprice.setText(price);
					} else {
						txtname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
				} catch (SQLException ex) {
					
				}
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(146, 24, 271, 26);
		panel_1.add(txtbid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(569, 112, 476, 303);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname, edition, price, bid;
				
				bname = txtname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid = txtbid.getText();
				
				try {
					pst = con.prepareStatement("Update books Set name = ?, edition = ?, price = ? Where id = ?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
		
					JOptionPane.showMessageDialog(null, "Record Updated");
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbid.setText("");
					txtname.requestFocus();
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Gulim", Font.PLAIN, 13));
		btnUpdate.setBounds(688, 440, 114, 60);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				
				bid = txtbid.getText();
				
				try {
					pst = con.prepareStatement("Delete from books Where id = ?");
					pst.setString(1, bid);
					pst.executeUpdate();
		
					JOptionPane.showMessageDialog(null, "Record Deleted");
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbid.setText("");
					txtname.requestFocus();
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Gulim", Font.PLAIN, 13));
		btnDelete.setBounds(814, 440, 114, 60);
		frame.getContentPane().add(btnDelete);
	}
}
