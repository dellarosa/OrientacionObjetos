package auto;
import auto.Definiciones.RelacionMarchas;
import auto.Definiciones.RelacionMarchas_string;

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
	
	public void setCantidadMarchas(int cantidadmarchas)
	{
		this.cantidadmarchas=cantidadmarchas;
	}
	public int getCantidadDeMarchas()
	{
		return this.cantidadmarchas;		
	}
	
	public void setTipoDeCaja(int tipodecaja)
	{
		this.tipodecaja=tipodecaja;
	}
	public int getTipoDeCaja()
	{		
		return this.tipodecaja; 
	}
	public String getTipoDeCaja_string()
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
	
	public String toString()
	{
		return "CAJA DE VELOCIDADES\nMarchas: " +this.getCantidadDeMarchas()+"\nTipo de Caja: "+this.getTipoDeCaja_string();
		 
	}
}
