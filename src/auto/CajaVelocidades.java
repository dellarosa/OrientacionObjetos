package auto;
import utils.Definiciones.RelacionMarchas_string;
import utils.Definiciones.RelacionMarchas;

public class CajaVelocidades {

	private int cantidadmarchas;
	private int tipodecaja;
	
	public CajaVelocidades()
	{}
	
	public CajaVelocidades(int cantmarchas,int tipocaja)
	{
		this.cantidadmarchas=cantmarchas;
		this.tipodecaja=tipocaja;
		
	}
	
	public void setcantidadmarchas(int cantidadmarchas)
	{
		this.cantidadmarchas=cantidadmarchas;
	}
	public int getcantidaddemarchas()
	{
		return this.cantidadmarchas;		
	}
	
	public void settipodecaja(int tipodecaja)
	{
		this.tipodecaja=tipodecaja;
	}
	public int gettipodecaja()
	{		
		return this.tipodecaja; 
	}
	public String gettipodecaja_string()
	{
		switch(this.tipodecaja)
		{
			case RelacionMarchas.relacionmarcha_cajacorta:
				return RelacionMarchas_string.relacionmarcha_cajacorta;
			case RelacionMarchas.relacionmarcha_cajamediana:
				return RelacionMarchas_string.relacionmarcha_cajamediana;
			case RelacionMarchas.relacionmarcha_cajalarga:
				return RelacionMarchas_string.relacionmarcha_cajalarga;
			default:
				return "Caja sin definir";
		}
	}
}
