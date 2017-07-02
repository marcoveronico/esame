package buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import progr.Dashboard;
import progr.Impianto;
import progr.utility;

public class TrasDatiLyst extends Test implements ActionListener
{
	
	public void actionPerformed(ActionEvent arg0)
	{
		if(dashb.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "nessun impianto/sensore installato");
		}
		else
		{	
			
			final JFrame frame =new JFrame();
			frame.setSize(400,400);
			frame.setTitle("dashBoard");
			frame.setLocation(100, 100);
			JPanel panel=new JPanel();
			JLabel lab1=new JLabel("lista Impianti");
			JTextArea text=new JTextArea(2,20);
			for(Impianto x:piat.ImpList)
			 text.append(x.getNomeIm()+"\n");
			JLabel lab2=new JLabel("inserire nome impianto ");
			final JTextField lab1Field=new JTextField(15);
			JLabel lab3=new JLabel("inserire password impianto");
			final JPasswordField psw=new JPasswordField(15);
			JButton sel=new JButton("trasferisci dashbord impianto selezionato");
			final JProgressBar progressBar = new JProgressBar();
			progressBar.setMaximum(100);
			progressBar.setValue(0);
			progressBar.setIndeterminate(false);
			
			class SelLyst implements ActionListener 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					boolean flag =true;
					for(Dashboard s:dashb)
				   {
					if(s.getNomeImp().equalsIgnoreCase(lab1Field.getText())&&s.getPswImp().equalsIgnoreCase(String.copyValueOf(psw.getPassword())))
					{ 
					flag=false;	
					}
				   }
				if(flag==false) 
				{
					
					final utility ut=new utility();
					final JFrame frame =new JFrame();
					frame.setSize(400,400);
					frame.setTitle("Trasferisci file ");
				    frame.setLocation(100, 100);
					JPanel panel=new JPanel();
					JLabel lab1=new JLabel("selezionare modalità trasferimento");
					JButton testo=new JButton("formato txt");
					class TestoLyst implements ActionListener 
					{
						public void actionPerformed(ActionEvent arg0)
						{	
							JFileChooser fc=new JFileChooser();
						int returnVal = fc.showSaveDialog(null);
					    if(returnVal == JFileChooser.APPROVE_OPTION) {
					      
					           
					           boolean flag=true;
							PrintWriter out = null;
							try {
								 out =new PrintWriter(fc.getSelectedFile().getPath()+".txt");
								} catch (FileNotFoundException e) {e.printStackTrace();}
							for(Dashboard s:dashb)
							{
								if(s.getNomeImp().equalsIgnoreCase(lab1Field.getText())&&s.getPswImp().equalsIgnoreCase(String.copyValueOf(psw.getPassword())))
								{ 
							     progressBar.setValue(0);
							     for(String x:s.getdash())
									{ 
									flag=false;
									 out.println(s.getNomeImp()+" : "+ut.calDataString(x));
									}
								}
							}
							out.close();
							if(flag==false) JOptionPane.showMessageDialog(null, "file txt creato in "+fc.getSelectedFile().getPath() );
						  
					           
					           
					           
					           
					    }
	
						}
					  }
					//gestione tasto crea database
					JButton database=new JButton("formato db");
						class DBLyst implements ActionListener 
						{
							
							public void actionPerformed(ActionEvent arg0)
							{  
								JFileChooser fc=new JFileChooser();
								int returnVal = fc.showSaveDialog(null);
							    if(returnVal == JFileChooser.APPROVE_OPTION)
							    {
							    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
								String dbName=fc.getSelectedFile().getPath();
								String connectionURL = "jdbc:derby:" + dbName + ";create=true";
								for(Dashboard s:dashb)
								{
									if(s.getNomeImp().equalsIgnoreCase(lab1Field.getText())&&s.getPswImp().equalsIgnoreCase(String.copyValueOf(psw.getPassword())))
									{
										progressBar.setValue(0);
										progressBar.setIndeterminate(true);
										try {	
											Connection conn = null;
										      Statement st;
										      PreparedStatement psInsert = null;
										      String createString = "CREATE TABLE SENSORI   "
										    	        +  " (RILEVAZIONE VARCHAR(100) NOT NULL,"
										    	        + "  IDIMPIANTO VARCHAR(32) NOT NULL ) " ;
										      Class.forName(driver);
										      conn = DriverManager.getConnection(connectionURL);
										      st = conn.createStatement();
										      try {
										    	   st.execute(createString) ;
										    	   progressBar.setValue(70);
										          }catch(SQLException e1){ }
										      psInsert = conn.prepareStatement(
										                "insert into SENSORI values (?, ?)");
											  for(String x:s.getdash())
											  { psInsert.setString(2, s.getNomeImp());
											    psInsert.setString(1, ut.calDataString(x));
											    psInsert.executeUpdate();
											   }
											  
											   
											   conn.commit();
											   st.close();
											   if (conn != null) 
											   {
										         conn.close();
										         conn = null;
										       }
											  
											 }
										     catch (ClassNotFoundException e){ e.printStackTrace();}
										     catch (SQLException e1) {e1.printStackTrace(); }
									}
								}
								progressBar.setValue(100);
								progressBar.setIndeterminate(false);
							}
							}
						}	
				JButton back=new JButton("menù precedente");
				class BackLyst implements ActionListener 
				{
				   public void actionPerformed(ActionEvent arg0)
					{
					frame.dispose();
					}
				}
				testo.addActionListener(new TestoLyst());
				database.addActionListener(new DBLyst());
				back.addActionListener(new BackLyst());
				panel.add(lab1);
				panel.add(testo);
				panel.add(database);
				panel.add(progressBar);
				panel.add(back);
				frame.add(panel);
				frame.setVisible(true);
				}
				else JOptionPane.showMessageDialog(null,"nome o password errati");
				}
			}
		    sel.addActionListener(new SelLyst());
			JButton back=new JButton("menù precedente");
			class backLyst implements ActionListener 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					frame.dispose();
				}
			}	
			back.addActionListener(new backLyst());
			panel.add(lab1);
			panel.add(text);
			panel.add(lab2);
			panel.add(lab1Field);
			panel.add(lab3);
			panel.add(psw);
			panel.add(sel);
			panel.add(back);
			frame.add(panel);
			frame.setVisible(true);
		}
	}
}
