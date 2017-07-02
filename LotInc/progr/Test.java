package progr;



import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



import buttonListener.*;

public class Test {

	public static  Piattaforma piat=new Piattaforma();
	public static ArrayList<Dashboard> dashb=new ArrayList<Dashboard>();
	public static void main(String[] args)
	{	
		
		
		
		
		//frame menu principale
		final JFrame frame =new JFrame();
		frame.setSize(400,400);
		frame.setTitle("Piattaforma IOT Inc.");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		JPanel panel=new JPanel();
		//pulsanti interfaccia grafica menù principale
		
		JButton bimpianti=new JButton("gestione impianti");
		JButton bpiatt=new JButton("gestione piattaforma");
		ActionListener gestLys=new GestImpLyst();
		ActionListener piatLys=new PiattLyst();
		bimpianti.addActionListener(gestLys);
		bpiatt.addActionListener(piatLys);
	
		panel.add(bimpianti);
		panel.add(bpiatt);
		frame.add(panel);
		frame.setVisible(true);
		
	}


}

