package sensori;

import java.util.Date;
import java.util.Random;

import progr.utility;
public class SenPhilipsUmidità extends Sensore implements SensoreInt
{
	//variabili locali
	private  String id;
	private String marca;
	@SuppressWarnings("unused")
	private String protocolloTrasm;
	private String rilevazione;
	@SuppressWarnings("unused")
	private Date data;
	private String codErrore;
	private int valoreRilevazione=0;
	private String tipoSensore;
	private String posizione;

	//costruttore
	public SenPhilipsUmidità(int idsen,String pos)
	{
		
		marca="philips";
		protocolloTrasm="AB2";
		
		tipoSensore="Rilevatore_di_umidità";
		id=marca+tipoSensore+idsen;
		posizione=pos;
	}
	
	private int valoreril()
	{
		valoreRilevazione=new Random().nextInt(200);
		return valoreRilevazione;
		
	}
	@Override
	public String adattatore()
		{
		utility u=new utility();
		int err =new Random().nextInt(100);
		if(err<=80) codErrore="00";
		else    codErrore="01"; 
			
			if(codErrore.equalsIgnoreCase("00"))
			{
				data=new Date();
				
				String dataString=u.calStringaData(new Date());
				String stringadec=dataString+valoreril();
				rilevazione="<id:"+id+">"+"<"+stringadec+">"+"<Rilevatore_umidità>"; 
			}
			else
			{
			String dataString=u.calStringaData(new Date());
			rilevazione="<id"+id+">"+"<"+dataString+">"+" < errore :"+ codErrore+ "<Rilevazione_umidità>";	
			}
		return rilevazione;	
		
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String GetPos() {
		// TODO Auto-generated method stub
		return posizione;
	}

}
