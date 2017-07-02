package buttonListener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import progr.Impianto;
import progr.Test;

/**
 * 
 *classe che gestisce l'inserimento di un impianto nella piattaforma
 */
public class InsImpiLyst extends Test implements ActionListener 
{
	
	
	public static int idImpianto=0;
	
	public void actionPerformed(ActionEvent arg0)
	{
		final JFrame frame =new JFrame();
		frame.setSize(400,400);
		frame.setTitle("Piattaforma IOT Inc.");
		frame.setLocation(100, 100);
		 final JPasswordField psw=new JPasswordField(30);
		 final JPasswordField pswconf=new JPasswordField(30);
		JPanel panel=new JPanel();	
		JLabel lab1=new JLabel("nome impianto");
		final JTextField lab1Field=new JTextField(30);
		JLabel lab2=new JLabel("nome proprietario");
		final JTextField lab2Field=new JTextField(30);
		JLabel lab3=new JLabel("password");
		JLabel lab4=new JLabel("conferma password");
		class insImp implements ActionListener 
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{	
				
			if(lab1Field.getText().isEmpty()||lab2Field.getText().isEmpty()||
			   String.copyValueOf(psw.getPassword()).isEmpty()||String.copyValueOf(pswconf.getPassword()).isEmpty())
				{
					
					JOptionPane.showMessageDialog(null, "non sono stati inseriti tutti i dati");
				}
				else
				{
				  if(!String.copyValueOf(psw.getPassword()).equals(String.copyValueOf(pswconf.getPassword())))
				  { 
					JOptionPane.showMessageDialog(null, "password non corrispondente");
				  }	
				  else
				  {  
				   idImpianto++;
				
			       boolean flag=true;
				   for(Impianto x:piat.ImpList)
					{
					if((x.getNomeIm().equalsIgnoreCase(lab1Field.getText())))
						flag=false;		
					}		
				   if(flag==true)
				    {
					 try 
					  {
						piat.insIm(new Impianto(idImpianto,lab1Field.getText(),lab2Field.getText(),String.copyValueOf(psw.getPassword())));
					  } catch (NoSuchAlgorithmException e) {e.printStackTrace();}
					  catch (IOException e) {e.printStackTrace(); }
					JOptionPane.showMessageDialog(null, "impianto aggiunto");
					// una volta inserito un impianto ogni x tempo le rilevazioni dei sensori
					//installati nell'impianto vengono salvate in una dashboard
					ProdDashbLyst myLystDash=new ProdDashbLyst();
					Timer t=new Timer(5000,myLystDash);
					t.start();
					frame.dispose();
				   }
					else
					JOptionPane.showMessageDialog(null, "nome impianto esistente");
				  }
				 }
			}
		}
		JButton ins=new JButton("Inserisci impianto");
		ins.addActionListener(new insImp());
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
		panel.add(lab1Field);
		panel.add(lab2);
		panel.add(lab2Field);
		panel.add(lab3);
		panel.add(psw);
		panel.add(lab4);
		panel.add(pswconf);
		panel.add(ins);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}
}
