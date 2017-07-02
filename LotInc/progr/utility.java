package progr;
import java.util.Date;

/**
 * 
 *utility generiche
 *
 */
public class utility 
{
public utility()
{
	
}
/**
 * 
 * @param x : una data nel formato Date
 * @return una stringa contenente la data nel formato giorno+mese+anno+ora+min+sec
 */
public String calStringaData(Date x)
{
	String y=null;
	Date data =x;
	String dat=data.toString();
	String mese="";
	String giorno;
	String anno="";
	String ora = "";
	String min = "";
	String sec = "";
	String[] spli=dat.split(" ");
	giorno=spli[2];
	if(spli[1].equalsIgnoreCase("jan")) mese="01";
	else if(spli[1].equalsIgnoreCase("feb")) mese="02";
	else if(spli[1].equalsIgnoreCase("mar")) mese="03";
	else if(spli[1].equalsIgnoreCase("apr")) mese="04";
	else if(spli[1].equalsIgnoreCase("may")) mese="05";
	else if(spli[1].equalsIgnoreCase("jun")) mese="06";
	else if(spli[1].equalsIgnoreCase("jul")) mese="07";
	else if(spli[1].equalsIgnoreCase("aug")) mese="08";
	else if(spli[1].equalsIgnoreCase("sep")) mese="09";
	else if(spli[1].equalsIgnoreCase("oct")) mese="10";
	else if(spli[1].equalsIgnoreCase("nov")) mese="11";
	else if(spli[1].equalsIgnoreCase("dec")) mese="12";
	anno=spli[5];
	ora=spli[3].substring(0,2);
	min=spli[3].substring(3,5);
	sec=spli[3].substring(6,8);
	ora=ora.replace(".", "");
	y=giorno+mese+anno+ora+min+sec;
	return y;
}
/**
 * 
 * @param adat : una stringa contenente una rilevazione di un sensore
 * @return una stringa contenente le informazioni di una rilevazione(idsensore,data,rilevazione,messaggio)
 */
public String calDataString(String adat)
{
	String y=null;
	adat=adat.replace("<", "");
	adat=adat.replace('>', '°');
	String[] split=adat.split("°");
	y=split[0];
	String x=split[1];
	if(!x.contains("errore"))
	{	String h=y;
		y=x.substring(0, 2)+"/"+x.substring(2, 4)+"/"+x.substring(4, 8)+
			"  "+x.substring(8,10)+"."+x.substring(10, 12)+"."+x.substring(12, 14)+
			"  "+h+" valore ril:"+x.substring(14, x.length())+" "+split[2];
	}else
		y=adat.replace("°", " ");
	return y;
}
}
