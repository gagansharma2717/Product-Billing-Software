import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
class ForgotPassword implements ActionListener
{
	JFrame frame;
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,rightsgLabel,serialLabel,passwordLabel;
	JTextField serialText;
	ImageIcon sgIcon;
	JPasswordField passwordText;
	JCheckBox showpassCheck;
	JButton saveButton,resetButton,cancelButton;
	JSeparator sp,sp2; 
	String dbserial;
	public ForgotPassword()
	{
		frame=new JFrame("Forgot Password Frame");
	
		panel=new JPanel(); 
		panel.setBounds(40,80,200,200);        

		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
	    		
		headingLabel=new JLabel("Forgot Password");
		serialLabel=new JLabel("Enter Serial Number  : ");
		passwordLabel=new JLabel("Enter New Password : ");
				
		serialText=new JTextField(10);
		passwordText=new JPasswordField(15);
		passwordText.setEchoChar('*');
		showpassCheck=new JCheckBox("Show Password");
		
		sgIcon=new ImageIcon("Images/sgtop.png");
		
		rightsgLabel=new JLabel(sgIcon);
		
		saveButton=new JButton("Save");
		saveButton.setFocusPainted(false);
		resetButton=new JButton("Reset");
		resetButton.setFocusPainted(false);
		cancelButton=new JButton("Cancel");
		cancelButton.setFocusPainted(false);
		headingLabel.setBounds(10,10,500,60);
		headingLabel.setFont(headingFont);
		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		sp2 = new JSeparator();
		sp2.setBackground(Color.BLACK);
		 	
		sp.setBounds(0,100,700,30);
		rightsgLabel.setBounds(470,120,191,140);
		serialLabel.setBounds(50,120,150,30);
		serialText.setBounds(190,120,200,30);
		passwordLabel.setBounds(50,160,150,30);
		passwordText.setBounds(190,160,200,30);
		showpassCheck.setBounds(185,200,180,30);
		saveButton.setBounds(50,240,100,30);
		resetButton.setBounds(170,240,100,30);
		cancelButton.setBounds(290,240,100,30);
		sp2.setBounds(0,300,700,30);	
		
		saveButton.addActionListener(this);
		resetButton.addActionListener(this);
		cancelButton.addActionListener(this);
		showpassCheck.addActionListener(this);
		
		frame.add(panel);
		panel.setLayout(null);
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(rightsgLabel);
		panel.add(serialLabel);
		panel.add(serialText);
		panel.add(passwordLabel);
		panel.add(passwordText);	
		panel.add(showpassCheck);
		panel.add(saveButton);
		panel.add(resetButton);
		panel.add(cancelButton);
		panel.add(sp2);
	
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setLocation((d.width-686)/2,(d.height-366)/2);
		frame.setSize(686,386);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
public void actionPerformed(ActionEvent evt1) 
{
	if(evt1.getSource()==saveButton)
	{		String serial=new String(serialText.getText());
			String Pass=new String(passwordText.getPassword());
			if(serial.equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Serial Number is required!");
				serialText.requestFocus();
			}
			else if(Pass.equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Password is required!");
				passwordText.requestFocus();
			}
			else
			{	
				try
				{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					PreparedStatement ps=con.prepareStatement("select * from LoginDetails where serial=?");
					ps.setString(1,serial);
					ResultSet rs=ps.executeQuery();	//insert, update, delete
					if(rs.next())
					{
						
						dbserial = rs.getString("serial"); 								
					}
				}
				catch(Exception e3)
				{	
				}
				if(serial.equals(dbserial)) 
				{ 
					int len=Pass.length();
					if(len<10|| len>10)
					{
						JOptionPane.showMessageDialog(panel,"Password Should Be 10 Characters Long...");
					}
					else
					{	
					try
					{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
						PreparedStatement ps=con.prepareStatement("Update LoginDetails set password=?");
						ps.setString(1,Pass);
						int i=ps.executeUpdate();//insert, update, delete
						if(i>0)
						{
							JOptionPane.showMessageDialog(panel, "Password Updated Successfully!");
						}
					}
					catch(Exception e3)
					{	
					}
					new LoginPage(); 
					frame.dispose();
					}
				} 
				else
				{ 
					JOptionPane.showMessageDialog(panel,"Incorrect Serial Number"); 
					serialText.setText("");
					passwordText.setText("");
					serialText.requestFocus();
				} 
			}
	}
	else if(evt1.getSource()==cancelButton)
		{
		  String message = " Do You Want to Quit ? ";
          String title = "Quit";
          int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
          if (reply == JOptionPane.YES_OPTION)
          {
              System.exit(0);
          }
		}
	else if(evt1.getSource()==resetButton)
	{
		serialText.setText(null);
		passwordText.setText(null);
	}
	else if(evt1.getSource()==showpassCheck)
		{
			if(showpassCheck.isSelected())
			{
				passwordText.setEchoChar((char)0);
			}
			else
			{
				passwordText.setEchoChar('*');
			}
		}
	}
}