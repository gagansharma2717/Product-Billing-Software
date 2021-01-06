import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class ViewPurchasedItems implements ActionListener
{
	JFrame f;
	JPanel panel,topPanel,bottomPanel;
	JLabel headingLabel;
	JTable jt1;
	JScrollPane sp;
	BorderLayout border;
	JButton okButton,cancelButton;
	Font headingFont;
	CustomerPaymentDetails cpd1;
	public ViewPurchasedItems(String id)
	{
		f=new JFrame();   
		panel=new JPanel();	    
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 80));
		bottomPanel=new JPanel();
		bottomPanel.setPreferredSize(new Dimension(30,50));
		
		headingLabel=new JLabel("View Purchased Items");
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select name,price,quantity,netamt,gst,total from PurchasedItems where billno=?");
			ps.setString(1,id);
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			DefaultTableModel model = new DefaultTableModel(new String[]{"Product Name", "Price", "Quantity","Net Amount","G.S.T","Total"}, 0);
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
	    sp=new JScrollPane(jt1); 
	    okButton=new JButton("Ok");
	    cancelButton=new JButton("Cancel");
	    headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
	    headingLabel.setBounds(10,10,500,50);
	    headingLabel.setFont(headingFont);
	    border=new BorderLayout();
	  
	    f.add(panel);
	    panel.setLayout(border);
	    panel.add(topPanel, BorderLayout.NORTH);
	    panel.add(bottomPanel, BorderLayout.SOUTH);
	    bottomPanel.add(okButton);
	    bottomPanel.add(cancelButton);
		bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,Color.BLACK));
	    topPanel.setLayout(null);
	    topPanel.add(headingLabel);
	    panel.add(sp,BorderLayout.CENTER);
	    cpd1=new CustomerPaymentDetails();
	   
	    okButton.addActionListener(this);
	    cancelButton.addActionListener(this);
	    
	    Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		
		f.setLocation((d.width-700)/2,(d.height-400)/2);
	    f.setSize(700,400);
	    f.setResizable(false);
		f.setVisible(true); 
}
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==okButton)
		{
			f.dispose();
		}
		else if(evt.getSource()==cancelButton)
		{
			  String message = " Do You Want to Quit ? ";
              String title = "Quit";
              int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
              if (reply == JOptionPane.YES_OPTION)
              {
                  f.dispose();
              }
		}
	}
}