package progr;
import java.util.ArrayList;

/**
 * 
 * classe che gestisce una tabella riassuntiva delle rilevazione di tutti i sensori di un impianto
 *
 */
public class Dashboard 
{
private   ArrayList<String> dashboard=new ArrayList<String>();
private String impianto=null;
private String psw=null;
/**
 * costruttore dashboard
 * @param x array di stringhe contenenti le rilevazioni in formato standard
 * @param y	nome dell'impianto
 * @param p password impianto
 */
public Dashboard(ArrayList<String> x,String y,String p)
{
	dashboard=x;
	impianto=y;
	psw=p;
}
/**
 * 
 * @return array delle rilevazioni di un impianto
 */
public ArrayList<String> getdash()
{
	return dashboard;
}
/**
 * 
 * @return nome dell'impianto
 */
public String getNomeImp()
{
	return impianto;
}
/**
 * 
 * @return password impianto
 */
public String getPswImp()
{
	return psw;
}


}
