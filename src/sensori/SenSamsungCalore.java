package sensori;

import java.util.Date;
import java.util.Random;

import progr.utility;
public class SenSamsungCalore extends Sensore implements SensoreInt
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
	public SenSamsungCalore(int idsen,String pos)
	{
		
		marca="Samsung";
		protocolloTrasm="AB1";
		
		tipoSensore="Rilevatore_di_calore";
		id=marca+tipoSensore+idsen;
		posizione=pos;
	}
	
	private int valoreril()
	{
		valoreRilevazione=new Random().nextInt(500);
		return valoreRilevazione;
		
	}
	
	
	@Override
	public String adattatore()
		{
		int err =new Random().nextInt(100);
		utility u=new utility();
		if(err<=80) codErrore="00";
		else    codErrore="01"; 
			if(codErrore.equalsIgnoreCase("00"))
			{
				data=new Date();
				
				String dataString=u.calStringaData(new Date());
				String stringadec=dataString+valoreril();
				rilevazione="<id:"+id+">"+"<"+stringadec+">"+"<Rilevatore_calore>"; 
			}
			else
			{
			String dataString=u.calStringaData(new Date());
			rilevazione="<id"+id+">"+"<"+dataString+">"+" < errore :"+ codErrore+ "<Rilevazione_calore>";	
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
