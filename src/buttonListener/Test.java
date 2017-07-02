package buttonListener;




import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import progr.Dashboard;
import progr.Piattaforma;
/**
 * 
 * classe che gestisce  l'interafaccia principale e il main
 *
 */
public class Test {
/**
 * rappresenta un elenco di dashboard, una per ogni impianto
 */
	public static ArrayList<Dashboard> dashb=new ArrayList<Dashboard>();
	/**
	 * rappresenta un istanza di piattaforma
	 */
	public static  Piattaforma piat=new Piattaforma();
	
	public static void main(String[] args)
	{	
		
		
		
		
		//
	//frame menu principale
		final JFrame frame =new JFrame();
		frame.setSize(400,400);
		frame.setTitle("Piattaforma IOT Inc.");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		JPanel panel=new JPanel();
		//pulsanti interfaccia grafica men� principale
		
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

