package entities;
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
		 StringBuilder result = new StringBuilder();
		 String newLine = System.getProperty("line.separator");		 
		 result.append("CAJA DE VELOCIDADES");
		 result.append(newLine);
		 result.append("Marchas: "+this.getCantidadDeMarchas());
		 result.append(newLine);
		 result.append("Tipo de Caja: "+this.getTipoDeCaja_string());	 
		 result.append(newLine);
		 
		 return result.toString();
	}
}
