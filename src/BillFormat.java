import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class BillFormat implements ActionListener
{
	JFrame f2;
	JPanel panel,centerPanel,topPanel,bottomPanel;
	Font headingFont;
	ImageIcon logoIcon;
	JLabel logoLabel,websiteLabel,compaddressLabel,compphoneLabel,compemailLabel,headingLabel,dateLabel,timeLabel,billLabel,customeridLabel,nameLabel, addressLabel,cityLabel,phoneLabel,emailLabel,totalqtyLabel,totalLabel;
	JLabel websiteLabel2,compaddressLabel2,compphoneLabel2,compemailLabel2,dateLabel2,timeLabel2,billLabel2,customeridLabel2,nameLabel2, addressLabel2,cityLabel2,phoneLabel2,emailLabel2,totalqtyLabel2,totalLabel2;
	JButton printButton,cancelButton;
	DefaultTableModel model;
	JTable jt1;
	JScrollPane spane;
	JSeparator sp,sp4;
	BorderLayout border,border2;
	String customerId=null;
	public BillFormat(String billing)
	{
		f2=new JFrame("Generate Bill");
		f2.setAlwaysOnTop(true);
		panel=new JPanel();
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 215));
		centerPanel=new JPanel();
		bottomPanel=new JPanel();
		bottomPanel.setPreferredSize(new Dimension(30,100));
		
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 40);
	    
		logoIcon=new ImageIcon("Images/billtop.png");
		
		logoLabel=new JLabel(logoIcon);
		websiteLabel=new JLabel("Website :");
		headingLabel=new JLabel("S.G. Billing Software");
		compaddressLabel=new JLabel("Address :");
		compphoneLabel=new JLabel("Contact :");
		compemailLabel=new JLabel("Email-Address :");
		dateLabel=new JLabel("Date :");
		timeLabel=new JLabel("Time :");
		customeridLabel=new JLabel("Customer Id     : ");
		billLabel=new JLabel("Invoice No.      : ");
		nameLabel=new JLabel("Full Name         : ");
		addressLabel=new JLabel("Address          : ");
		cityLabel=new JLabel("State/City       : ");
		phoneLabel=new JLabel("Phone Number : ");
		emailLabel=new JLabel("Email Address  : ");
		totalqtyLabel=new JLabel("Total Quantity :");
		totalLabel=new JLabel("Total :");

		websiteLabel2=new JLabel("www.sgbillings@google.co.in");
		compaddressLabel2=new JLabel("Shop No.308,Mughal Canal,Karnal-132001");
		compphoneLabel2=new JLabel("7015335614");
		compemailLabel2=new JLabel("sgbillings41@gmail.com");
		dateLabel2=new JLabel();
		timeLabel2=new JLabel();
		customeridLabel2=new JLabel();
		billLabel2=new JLabel();
		nameLabel2=new JLabel();
		addressLabel2=new JLabel();
		cityLabel2=new JLabel();
		phoneLabel2=new JLabel();
		emailLabel2=new JLabel();
		totalqtyLabel2=new JLabel();
		totalLabel2=new JLabel();

		printButton=new JButton("Print");
		printButton.setFocusPainted(false);
		cancelButton=new JButton("Cancel");
		cancelButton.setFocusPainted(false);
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select billno,customerID,totalquantity,total,date,time from CustomerPaymentDetails where billno=?");
			ps.setString(1,billing);
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			if(rs.next())
			{
				String billno=rs.getString("billno");
				customerId=rs.getString("customerID");
				String totalquantity=rs.getString("totalquantity");
				String total=rs.getString("total");
				String date=rs.getString("date");
				String time=rs.getString("time");

				billLabel2.setText(billno);
				totalqtyLabel2.setText(totalquantity);
				totalLabel2.setText(total);
				dateLabel2.setText(date);
				timeLabel2.setText(time);
			}
		}
		catch(Exception ee)
		{
		}
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from CustomerDetails where CustomerID=?");
			ps.setString(1,customerId);
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			if(rs.next())
			{
				String id=rs.getString("customerID");
				String name=rs.getString("name");
				String address=rs.getString("address");
				String city=rs.getString("city");
				String phone=rs.getString("phone");
				String email=rs.getString("email");
				
				customeridLabel2.setText(id);
				nameLabel2.setText(name);
				addressLabel2.setText(address);
				cityLabel2.setText(city);
				phoneLabel2.setText(phone);
				emailLabel2.setText(email);
			}
		}
		catch(Exception ee)
		{
		}		
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select name,price,quantity,netamt,gst,total from PurchasedItems where billno=?");
			ps.setString(1,billing);
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			model = new DefaultTableModel(new String[]{"Product Name", "Price", "Quantity","Net Amount","G.S.T","Total"}, 0);
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
				String name=rs.getString("name");
				String price=rs.getString("price");
				String quantity=rs.getString("quantity");
				String netamt=rs.getString("netamt");
				String gst=rs.getString("gst");
				String total=rs.getString("total");
				
				row.add(name.toString().trim());
				row.add(price.toString().trim());
				row.add(quantity.toString().trim());
				row.add(netamt.toString().trim());
				row.add(gst.toString().trim());
				row.add(total.toString().trim());
				model.addRow(row);
			}
		}
		catch(Exception ee)
		{
		}
		jt1.setBounds(100,40,1000,300);          
	    spane=new JScrollPane(jt1);    
	    spane.setBorder(BorderFactory.createEmptyBorder());
	    spane.getViewport().setBackground(Color.white);

		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		sp4 = new JSeparator();
		sp4.setBackground(Color.BLACK);
		border=new BorderLayout();
		border2=new BorderLayout();
		f2.add(panel);

		panel.setLayout(border);
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		panel.add(centerPanel,BorderLayout.CENTER);
	
		centerPanel.setLayout(border2);
		centerPanel.add(spane);
		topPanel.setLayout(null);
		bottomPanel.setLayout(null);
		
		logoLabel.setBounds(500,5,200,70);
		headingLabel.setBounds(10,0,500,50);
		headingLabel.setFont(headingFont);
		
		websiteLabel.setBounds(500,60,100,50);
		compaddressLabel.setBounds(10,30,100,50);
		compemailLabel.setBounds(10,48,100,50);
		compphoneLabel.setBounds(10,66,100,50);
		billLabel.setBounds(30,105,180,30);
		customeridLabel.setBounds(30,130,180,30);
		nameLabel.setBounds(30,155,180,30);
		phoneLabel.setBounds(30,180,100,30);
		emailLabel.setBounds(298,180,180,30);
		addressLabel.setBounds(298,105,180,30);
		cityLabel.setBounds(298,155,180,30);
		dateLabel.setBounds(550,105,180,30);
		timeLabel.setBounds(551,130,180,30);
		sp.setBounds(0,100,1500,30);		
		
		websiteLabel2.setBounds(550,60,150,50);
		compaddressLabel2.setBounds(60,30,250,50);
		compemailLabel2.setBounds(90,48,200,50);
		compphoneLabel2.setBounds(58,66,100,50);
		billLabel2.setBounds(150,105,180,30);
		customeridLabel2.setBounds(150,130,180,30);
		nameLabel2.setBounds(150,155,180,30);
		phoneLabel2.setBounds(150,180,100,30);
		emailLabel2.setBounds(390,180,200,30);
		addressLabel2.setBounds(390,105,180,30);
		cityLabel2.setBounds(390,155,180,30);
		dateLabel2.setBounds(600,105,180,30);
		timeLabel2.setBounds(600,130,180,30);
		
		topPanel.add(websiteLabel);
		topPanel.add(compaddressLabel);
		topPanel.add(compphoneLabel);
		topPanel.add(compemailLabel);
		topPanel.add(logoLabel);
		topPanel.add(headingLabel);
		topPanel.add(billLabel);
		topPanel.add(customeridLabel);
		topPanel.add(nameLabel);
		topPanel.add(addressLabel);
		topPanel.add(cityLabel);
		topPanel.add(phoneLabel);
		topPanel.add(emailLabel);
		topPanel.add(dateLabel);
		topPanel.add(timeLabel);
		topPanel.add(sp);
		
		topPanel.add(websiteLabel2);
		topPanel.add(compaddressLabel2);
		topPanel.add(compphoneLabel2);
		topPanel.add(compemailLabel2);
		topPanel.add(billLabel2);
		topPanel.add(customeridLabel2);
		topPanel.add(nameLabel2);
		topPanel.add(addressLabel2);
		topPanel.add(cityLabel2);
		topPanel.add(phoneLabel2);
		topPanel.add(emailLabel2);
		topPanel.add(dateLabel2);
		topPanel.add(timeLabel2);

		totalqtyLabel.setBounds(410,10,100,30);
		totalqtyLabel2.setBounds(500,10,100,30);		
		totalLabel.setBounds(550,10,100,30);
		totalLabel2.setBounds(600,10,100,30);
		sp4.setBounds(0,50,1500,30);
		printButton.setBounds(230,60,100,30);
		cancelButton.setBounds(350,60,100,30);
		
		bottomPanel.add(totalqtyLabel);
		bottomPanel.add(totalLabel);	
		bottomPanel.add(totalqtyLabel2);
		bottomPanel.add(totalLabel2);
		bottomPanel.add(sp4);
		bottomPanel.add(printButton);
		bottomPanel.add(cancelButton);
		printButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		topPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.BLACK));
		centerPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,Color.BLACK));
		bottomPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,Color.BLACK));
		
		topPanel.setBackground(Color.white);	
		bottomPanel.setBackground(Color.white);
		
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		
		f2.setLocation((d.width-700)/2,(d.height-715)/2);
		f2.setSize(700,700);
		f2.setUndecorated(true);
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setVisible(true);
	}
	public void actionPerformed(ActionEvent evt) 
	{
		final PrinterJob jpb = PrinterJob.getPrinterJob();
		jpb.setJobName("Print Data");
		if(evt.getSource()==printButton)
		{
			jpb.setPrintable(new Printable() 
			{
				public int print(Graphics pg, PageFormat pf, int pageNum) throws PrinterException 
				{
					if(pageNum>0)
					{
						return Printable.NO_SUCH_PAGE;
					}
					 	
						Dimension dim = f2.getSize();
				        double cHeight = dim.getHeight();
				        double cWidth = dim.getWidth();

				        // get the bounds of the printable area
				        double pHeight = pf.getImageableHeight();
				        double pWidth = pf.getImageableWidth();

				        double pXStart = pf.getImageableX();
				        double pYStart = pf.getImageableY();

				        double xRatio = pWidth / cWidth;
				        double yRatio = pHeight / cHeight;

				        Graphics2D g2 = (Graphics2D)pg;
				        g2.translate(pXStart, pYStart);
				        g2.scale(xRatio, yRatio);
				        f2.paint(g2);

				        return Printable.PAGE_EXISTS;
				}
			});
			boolean ok = jpb.printDialog();
			if(ok)
			{
				try
				{
					jpb.print();
				}
				catch (PrinterException kl) 
				{
				}
			}
		}
		else if(evt.getSource()==cancelButton)
		{
			f2.dispose();
		}
		}
	}