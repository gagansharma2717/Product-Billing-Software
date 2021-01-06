import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
public class PrintBill implements ActionListener
{
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,billidLabel;
	JTextField billidText;
	JButton printButton, resetButton;
	JSeparator sp;
	String billno=null;
	public PrintBill()
	{
		panel=new JPanel();
		
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
	    
		headingLabel=new JLabel("Print Bill");
		billidLabel=new JLabel("Enter Invoice Number : ");

		billidText=new JTextField(10);
		
		printButton=new JButton("Print");
		printButton.setFocusPainted(false);
		resetButton=new JButton("Reset");
		resetButton.setFocusPainted(false);
		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		 	
		sp.setBounds(0,80,1500,30);
		headingLabel.setBounds(10,10,500,50);
		headingLabel.setFont(headingFont);
		billidLabel.setBounds(10,120,180,30);
		billidText.setBounds(150,120,180,30);
		printButton.setBounds(100,170,80,30);
		resetButton.setBounds(200,170,80,30);
		
		printButton.addActionListener(this);
		resetButton.addActionListener(this);
		
		panel.setLayout(null);
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(billidLabel);
		panel.add(billidText);
		panel.add(printButton);
		panel.add(resetButton);
	}
	public JPanel getPanel()
	{
		return panel;
	}
	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource()==printButton)
		{
			try
			{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("select billno from CustomerPaymentDetails where billno=?");
				ps.setString(1,billidText.getText().toString());
				ResultSet rs=ps.executeQuery();	//insert, update, delete
				if(rs.next())
				{
					billno=rs.getString("billno");
				}
			}
			catch(Exception ee)
			{
			}
			if(billidText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Invoice Number is required!");
				billidText.requestFocus();
			}
			else if(billidText.getText().equals(billno))
			{
				String billing=billidText.getText();
				new BillFormat(billing);
			}
			else
			JOptionPane.showMessageDialog(panel,"Invoie Number Not Found...");
			billidText.setText("");
			billidText.requestFocus();
		}
		if(evt.getSource()==resetButton)
		{
			billidText.setText("");
			billidText.requestFocus();
		}
}
}