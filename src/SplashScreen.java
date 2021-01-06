import java.awt.*;
import javax.swing.*;
public class SplashScreen implements Runnable
{
	JFrame frame1;
	JPanel panel;
	JLabel splashLabel;
	ImageIcon splashIcon;
	Thread WelcomeThread;
	LoginPage lp;
	JProgressBar jb;
	int i=0;
	public SplashScreen()
	{
		frame1=new JFrame();
		panel=new JPanel();
		
		frame1.add(panel);
		panel.setLayout(null);
		splashIcon=new ImageIcon("Images/sgsplash1.jpg");
	
		splashLabel=new JLabel(splashIcon);
		splashLabel.setBounds(0,0,686,366);
		panel.add(splashLabel);
		jb=new JProgressBar(0,2000);    
		jb.setBounds(0,367,686,20);         
		jb.setValue(0);    
		jb.setStringPainted(false);
		jb.setBackground(null);
		jb.setForeground(Color.BLUE);
		panel.add(jb);    
		
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	
		frame1.setLocation((d.width-686)/2,(d.height-386)/2);
		frame1.setSize(686,386);
		frame1.setUndecorated(true);
		frame1.setResizable(false);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setVisible(true);
	
		WelcomeThread=new Thread(this);
		WelcomeThread.start();		
		}
	public static void main(String args[])
	{
		SplashScreen ss=new SplashScreen();
		ss.iterate();
	}
	public void iterate()
	{    
		while(i<=2000){    
		  jb.setValue(i);    
		  i=i+200;    
		  try{Thread.sleep(150);}catch(Exception e){}    
		}    
		}    
	public void run()
	{
		try 
		{
			Thread.sleep(2000);
			frame1.dispose();
			lp=new LoginPage();
		}
		catch (Exception e) 
		{
		}
	}
}