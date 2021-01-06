import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class ViewVendorDetails implements KeyListener
{	JPanel panel,topPanel;
	JLabel headingLabel;
	JSeparator sep;
	JLabel searchidLabel,searchnameLabel,orLabel;
	JTextField searchidText,searchnameText;
	JTable jt1;
	JScrollPane sp;
	BorderLayout border;
	Font headingFont;
	DefaultTableModel model;
	public ViewVendorDetails()
	{
		panel=new JPanel();
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 140));
			    
		headingLabel=new JLabel("View Vendor Details");
	    headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
		sep = new JSeparator();
		sep.setBackground(Color.BLACK);
		 	
		searchidLabel=new JLabel("Search By Id : ");
		searchnameLabel=new JLabel("Search By Name : ");
		orLabel=new JLabel("Or");
		
		searchidText=new JTextField(10);
		searchnameText=new JTextField(10);
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from VendorDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			model = new DefaultTableModel(new String[]{"Vendor Id","Vendor Name", "Vendor Address","Vendor City", "Vendor Contact","Vendor Email"}, 0);
			model.setRowCount(0);
			jt1 =new JTable(model)
			{
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int row, int column) {                
				return false;               
		        };
		    };
			while(rs.next())
			{
				Vector<String> row=new Vector<String>();
				String vendorid=rs.getString("vendorID");
				String name=rs.getString("name");
				String address=rs.getString("address");
				String city=rs.getString("city");
				String phone=rs.getString("phone");
				String email=rs.getString("email");
	
				row.add(vendorid);
				row.add(name);
				row.add(address);
				row.add(city);
				row.add(phone);
				row.add(email);
				model.addRow(row);
			}
		}
		catch(Exception ee)
		{
		}
		jt1.setBounds(100,40,1000,300);          
	    sp=new JScrollPane(jt1);    
	
	    headingLabel.setBounds(10,10,500,50);
	    headingLabel.setFont(headingFont);
	    sep.setBounds(0,80,1500,30);
	    searchidLabel.setBounds(10,90,100,30);
	    searchidText.setBounds(93,90,180,30);
	    orLabel.setBounds(315,90,100,30);
	    searchnameLabel.setBounds(360,90,100,30);
	    searchnameText.setBounds(460,90,180,30);
	    
	    border=new BorderLayout();
	    panel.setLayout(border);
	    panel.add(topPanel, BorderLayout.NORTH);
			
	    topPanel.setLayout(null);
	    topPanel.add(headingLabel);
	    topPanel.add(sep);
	    topPanel.add(searchidLabel);
	    topPanel.add(searchidText);
	    topPanel.add(orLabel);
	    topPanel.add(searchnameLabel);
	    topPanel.add(searchnameText);
	    panel.add(sp,BorderLayout.CENTER);
	    searchidText.addKeyListener(this);
	    searchnameText.addKeyListener(this);
	 }
	public JPanel getPanel()
	{
		return panel;
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent keyevt) {
		if(keyevt.getSource()==searchidText|| keyevt.getSource()==searchnameText)
		{	
			if(searchidText.getText().equals("") && searchnameText.getText().equals(""))
			{
			try
			{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("select * from VendorDetails");
				ResultSet rs=ps.executeQuery();	//insert, update, delete
				model.setRowCount(0);
				jt1 =new JTable(model)
				{
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {                
	                return false;               
			        };
			    };
				while(rs.next())
				{	
				Vector<String> row=new Vector<String>();
				String vendorid=rs.getString("vendorID");
				String name=rs.getString("name");
				String address=rs.getString("address");
				String city=rs.getString("city");
				String phone=rs.getString("phone");
				String email=rs.getString("email");
	
				row.add(vendorid);
				row.add(name);
				row.add(address);
				row.add(city);
				row.add(phone);
				row.add(email);
				model.addRow(row);
				}
			}
			catch(Exception ee)
			{
			}			
		}
		else if(searchnameText.getText().toString()!=null && searchidText.getText().equals(""))
		{
			try
			{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("select * from VendorDetails where name =?");
				ps.setString(1,searchnameText.getText().toString().trim());
				ResultSet rs=ps.executeQuery();	//insert, update, delete
				model.setRowCount(0);	
				while(rs.next())
				{
					Vector<String> row=new Vector<String>();
					String vendorid=rs.getString("vendorID");
					String name=rs.getString("name");
					String address=rs.getString("address");
					String city=rs.getString("city");
					String phone=rs.getString("phone");
					String email=rs.getString("email");
		
					row.add(vendorid);
					row.add(name);
					row.add(address);
					row.add(city);
					row.add(phone);
					row.add(email);
					model.addRow(row);
				}
		}
			catch(Exception ee)
			{
			}
		}
		else if(searchidText.getText().toString()!=null && searchnameText.getText().equals(""))
		{
			try
			{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("select * from VendorDetails where vendorID =?");
				ps.setString(1,searchidText.getText().toString().trim());
				ResultSet rs=ps.executeQuery();	//insert, update, delete
				if(rs.next())
				{
					model.setRowCount(0);
					Vector<String> row=new Vector<String>();
					String vendorid=rs.getString("vendorID");
					String name=rs.getString("name");
					String address=rs.getString("address");
					String city=rs.getString("city");
					String phone=rs.getString("phone");
					String email=rs.getString("email");
		
					row.add(vendorid);
					row.add(name);
					row.add(address);
					row.add(city);
					row.add(phone);
					row.add(email);
					model.addRow(row);
				}
			}
			catch(Exception ee)
			{
			}
		}
	}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	}