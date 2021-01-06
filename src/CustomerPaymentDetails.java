import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class CustomerPaymentDetails implements MouseListener,ActionListener,KeyListener
{
	JPanel panel,topPanel,bottomPanel;
	JLabel headingLabel;
	JSeparator sep;
	JLabel searchidLabel,searchnameLabel,orLabel;
	JTextField searchidText,searchnameText;
	JTable jt1;
	JScrollPane sp;
	JButton viewButton;
	BorderLayout border;
	Font headingFont;
	DefaultTableModel model;
	String s=null;
	public CustomerPaymentDetails() 
	{
		panel=new JPanel();
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 140));
		bottomPanel=new JPanel();
		bottomPanel.setPreferredSize(new Dimension(30,50));
		
		headingLabel=new JLabel("Customer Payment Details");
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
			PreparedStatement ps=con.prepareStatement("select * from CustomerPaymentDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			model = new DefaultTableModel(new String[]{"Invoice No","Customer Id","Customer Name","Total Qty","Total","Date","Time"}, 0);
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
				String billno=rs.getString("billno");
				String id=rs.getString("customerID");
				String name=rs.getString("name");
				String totalquantity=rs.getString("totalquantity");
				String total=rs.getString("total");
				String date=rs.getString("date");
				String time=rs.getString("time");
	
				row.add(billno);
				row.add(id);
				row.add(name);
				row.add(totalquantity);
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
		    
	    headingLabel.setBounds(10,10,600,60);
	    headingLabel.setFont(headingFont);
	    sep.setBounds(0,80,1500,30);
	    searchidLabel.setBounds(10,90,100,30);
	    searchidText.setBounds(93,90,180,30);
	    orLabel.setBounds(315,90,100,30);
	    searchnameLabel.setBounds(360,90,100,30);
	    searchnameText.setBounds(460,90,180,30);
	
		viewButton=new JButton("View Further Details");
		viewButton.setFocusPainted(false);

	    border=new BorderLayout();
	   
	    panel.setLayout(border);
	    panel.add(topPanel, BorderLayout.NORTH);
	    panel.add(bottomPanel, BorderLayout.SOUTH);
	    
	    topPanel.setLayout(null);
	    topPanel.add(headingLabel);
	    topPanel.add(sep);
	    topPanel.add(searchidLabel);
	    topPanel.add(searchidText);
	    topPanel.add(orLabel);
	    topPanel.add(searchnameLabel);
	    topPanel.add(searchnameText);
	
	    panel.add(sp,BorderLayout.CENTER);
	    bottomPanel.setLayout(null);
	    viewButton.setBounds(900,10,180,30);
	    bottomPanel.add(viewButton);
		bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,Color.BLACK));
		viewButton.addActionListener(this);
		jt1.addMouseListener(this);
	    searchidText.addKeyListener(this);
	    searchnameText.addKeyListener(this);	 
	}
	public JPanel getPanel()
	{
		return panel;
	}
	public void mouseClicked(MouseEvent mouevt) {
			if(mouevt.getSource()==jt1)
			{  
			int row = jt1.rowAtPoint( mouevt.getPoint() );
            s=jt1.getModel().getValueAt(row, 0)+"";			
			}
		}
		public void mouseEntered(MouseEvent arg0) 
		{
		}
		public void mouseExited(MouseEvent arg0)
		{
		}
		public void mousePressed(MouseEvent arg0) 
		{
		}
		public void mouseReleased(MouseEvent arg0)
		{
		}
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource()==viewButton)
			{
				if(s==null)
				{
					JOptionPane.showMessageDialog(panel, "Please Select Invoice Number To View Further Details...");
				}
				else
				{	
				new ViewPurchasedItems(s);
				}
			}
		}
		public void keyPressed(KeyEvent arg0)
		{
		}
		public void keyReleased(KeyEvent keyevt) 
		{
			if(keyevt.getSource()==searchidText|| keyevt.getSource()==searchnameText)
			{	
				if(searchidText.getText().equals("") && searchnameText.getText().equals(""))
				{
					try
					{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
						PreparedStatement ps=con.prepareStatement("select * from CustomerPaymentDetails");
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
					String billno=rs.getString("billno");
					String id=rs.getString("customerID");
					String name=rs.getString("name");
					String totalquantity=rs.getString("totalquantity");
					String total=rs.getString("total");
					String date=rs.getString("date");
					String time=rs.getString("time");
		
					row.add(billno);
					row.add(id);
					row.add(name);
					row.add(totalquantity);
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
			else if(searchnameText.getText().toString()!=null && searchidText.getText().equals(""))
			{
				try
				{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					PreparedStatement ps=con.prepareStatement("select * from CustomerPaymentDetails where name =?");
					ps.setString(1,searchnameText.getText().toString().trim());
					ResultSet rs=ps.executeQuery();	//insert, update, delete
					model.setRowCount(0);
					while(rs.next())
					{		 
							 	Vector<String> row=new Vector<String>();
								String billno=rs.getString("billno");
								String id=rs.getString("customerID");
								String name=rs.getString("name");
								String totalquantity=rs.getString("totalquantity");
								String total=rs.getString("total");
								String date=rs.getString("date");
								String time=rs.getString("time");
					
								row.add(billno);
								row.add(id);
								row.add(name);
								row.add(totalquantity);
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
			else if(searchidText.getText().toString()!=null && searchnameText.getText().equals(""))
			{
				try
				{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					PreparedStatement ps=con.prepareStatement("select * from CustomerPaymentDetails where billno =?");
					ps.setString(1,searchidText.getText().toString().trim());
					ResultSet rs=ps.executeQuery();	//insert, update, delete
					if(rs.next())
					{
							 model.setRowCount(0);
							 Vector<String> row=new Vector<String>();
								String billno=rs.getString("billno");
								String id=rs.getString("customerID");
								String name=rs.getString("name");
								String totalquantity=rs.getString("totalquantity");
								String total=rs.getString("total");
								String date=rs.getString("date");
								String time=rs.getString("time");
					
								row.add(billno);
								row.add(id);
								row.add(name);
								row.add(totalquantity);
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
		public void keyTyped(KeyEvent arg0) 
		{
		}
}