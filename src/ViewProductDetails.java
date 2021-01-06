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
public class ViewProductDetails implements KeyListener
{	
	JPanel panel,topPanel;
	JLabel headingLabel;
	JSeparator sep;
	JLabel searchidLabel,searchnameLabel,searchvdrLabel,orLabel1,orLabel2;
	JTextField searchidText,searchnameText,searchvdrText;
	JTable jt1;
	JScrollPane sp;
	BorderLayout border;
	Font headingFont;
	DefaultTableModel model;
	public ViewProductDetails()
	{
		panel=new JPanel();
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30,140));
			    
		headingLabel=new JLabel("View Product Details");
		sep = new JSeparator();
		sep.setBackground(Color.BLACK);
		 	
		searchidLabel=new JLabel("Search By Id : ");
		searchnameLabel=new JLabel("Search By Product's Name : ");
		searchvdrLabel=new JLabel("Search By Vendor's Name : ");
		orLabel1=new JLabel("Or");
		orLabel2=new JLabel("Or");
		
		searchidText=new JTextField(10);
		searchnameText=new JTextField(10);
		searchvdrText=new JTextField(10);
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from ProductDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			model = new DefaultTableModel(new String[]{"Product Id","Product Name","Vendor Name","Price", "Quantity","Net Amount","G.S.T","Total","Date","Time"}, 0);
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
				String id=rs.getString("productID");
				String name=rs.getString("name");
				String vendorname=rs.getString("vendorname");
				String price=rs.getString("price");
				String quantity=rs.getString("quantity");
				String netamt=rs.getString("netamt");
				String gst=rs.getString("gst");
				String total=rs.getString("total");
				String date=rs.getString("date");
				String time=rs.getString("time");
	
				row.add(id);
				row.add(name);
				row.add(vendorname);
				row.add(price);
				row.add(quantity);
				row.add(netamt);
				row.add(gst);
				row.add(total);
				row.add(date);
				row.add(time);
				model.addRow(row);
			}
		}
		catch(Exception ee)
		{
		}
	    jt1.setBounds(100,40,1000,300);          
	    sp=new JScrollPane(jt1);    
	    headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
		    
	    headingLabel.setBounds(10,10,500,50);
	    headingLabel.setFont(headingFont);
	    sep.setBounds(0,80,1500,30);
	    searchidLabel.setBounds(10,90,100,30);
	    searchidText.setBounds(93,90,180,30);
	    orLabel1.setBounds(315,90,100,30);
	    searchnameLabel.setBounds(360,90,150,30);
	    searchnameText.setBounds(520,90,180,30);
	    orLabel2.setBounds(735,90,100,30);
	    searchvdrLabel.setBounds(770,90,150,30);
	    searchvdrText.setBounds(930,90,180,30);
	    border=new BorderLayout();
	    panel.setLayout(border);
	    panel.add(topPanel, BorderLayout.NORTH);
			
	    topPanel.setLayout(null);
	    topPanel.add(headingLabel);
	    topPanel.add(sep);
	    topPanel.add(searchidLabel);
	    topPanel.add(searchidText);
	    topPanel.add(orLabel1);
	    topPanel.add(searchnameLabel);
	    topPanel.add(searchnameText);
	    topPanel.add(orLabel2);
	    topPanel.add(searchvdrLabel);
	    topPanel.add(searchvdrText);
	    panel.add(sp,BorderLayout.CENTER);
	    
	    searchidText.addKeyListener(this);
	    searchnameText.addKeyListener(this);
	    searchvdrText.addKeyListener(this);
	 }
	public JPanel getPanel()
	{
		return panel;
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent keyevt) {
		if(keyevt.getSource()==searchidText|| keyevt.getSource()==searchnameText || keyevt.getSource()==searchvdrText)
		{	
			if(searchidText.getText().equals("") && searchnameText.getText().equals("")&& searchvdrText.getText().equals(""))
			{		
				try
				{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					PreparedStatement ps=con.prepareStatement("select * from ProductDetails");
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
				String id=rs.getString("productID");
				String name=rs.getString("name");
				String vendorname=rs.getString("vendorname");
				String price=rs.getString("price");
				String quantity=rs.getString("quantity");
				String netamt=rs.getString("netamt");
				String gst=rs.getString("gst");
				String total=rs.getString("total");
				String date=rs.getString("date");
				String time=rs.getString("time");
	
				row.add(id);
				row.add(name);
				row.add(vendorname);
				row.add(price);
				row.add(quantity);
				row.add(netamt);
				row.add(gst);
				row.add(total);
				row.add(date);
				row.add(time);
				model.addRow(row);
			}
		}
		catch(Exception ee)
		{
		}
		}
		else if(searchnameText.getText().toString()!=null && searchidText.getText().equals("")&& searchvdrText.getText().equals(""))
		{
			try
			{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("select * from ProductDetails where name =?");
				ps.setString(1,searchnameText.getText().toString().trim());
				ResultSet rs=ps.executeQuery();	//insert, update, delete
				model.setRowCount(0);	
				while(rs.next())
				{
				Vector<String> row=new Vector<String>();
				String id=rs.getString("productID");
				String name=rs.getString("name");
				String vendorname=rs.getString("vendorname");
				String price=rs.getString("price");
				String quantity=rs.getString("quantity");
				String netamt=rs.getString("netamt");
				String gst=rs.getString("gst");
				String total=rs.getString("total");
				String date=rs.getString("date");
				String time=rs.getString("time");
	
				row.add(id);
				row.add(name);
				row.add(vendorname);
				row.add(price);
				row.add(quantity);
				row.add(netamt);
				row.add(gst);
				row.add(total);
				row.add(date);
				row.add(time);
				model.addRow(row);
				}
		}
			catch(Exception ee)
			{
			}
		}
			else if(searchidText.getText().toString()!=null && searchnameText.getText().equals("")&& searchvdrText.getText().equals(""))
		{
			try
			{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("select * from ProductDetails where productID =?");
				ps.setString(1,searchidText.getText().toString().trim());
				ResultSet rs=ps.executeQuery();	//insert, update, delete
				if(rs.next())
				{
					model.setRowCount(0);
					Vector<String> row=new Vector<String>();
					String id=rs.getString("productID");
					String name=rs.getString("name");
					String vendorname=rs.getString("vendorname");
					String price=rs.getString("price");
					String quantity=rs.getString("quantity");
					String netamt=rs.getString("netamt");
					String gst=rs.getString("gst");
					String total=rs.getString("total");
					String date=rs.getString("date");
					String time=rs.getString("time");
		
					row.add(id);
					row.add(name);
					row.add(vendorname);
					row.add(price);
					row.add(quantity);
					row.add(netamt);
					row.add(gst);
					row.add(total);
					row.add(date);
					row.add(time);
					model.addRow(row);
					}
			}
			catch(Exception ee)
			{
			}
		}
		else if(searchvdrText.getText().toString()!=null && searchidText.getText().equals("") && searchnameText.getText().equals(""))
			{
				try
				{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					PreparedStatement ps=con.prepareStatement("select * from ProductDetails where vendorname=?");
					ps.setString(1,searchvdrText.getText().toString().trim());
					ResultSet rs=ps.executeQuery();	//insert, update, delete
					model.setRowCount(0);	
					while(rs.next())
					{
					Vector<String> row=new Vector<String>();
					String id=rs.getString("productID");
					String name=rs.getString("name");
					String vendorname=rs.getString("vendorname");
					String price=rs.getString("price");
					String quantity=rs.getString("quantity");
					String netamt=rs.getString("netamt");
					String gst=rs.getString("gst");
					String total=rs.getString("total");
					String date=rs.getString("date");
					String time=rs.getString("time");
		
					row.add(id);
					row.add(name);
					row.add(vendorname);
					row.add(price);
					row.add(quantity);
					row.add(netamt);
					row.add(gst);
					row.add(total);
					row.add(date);
					row.add(time);
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