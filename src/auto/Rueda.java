package auto;

import utils.Definiciones.MaterialRueda;
import utils.Definiciones.MaterialRueda_string;

public class Rueda 
{
	// DECALARACIÓN DE ATRIBUTOS
	private double radio;
	public String color;
	private int materialrueda;
	
	// COMIENZO DE LOS METODOS
	
	public Rueda()
	{
		
	}
	
	public void setradio(double radiomeasure)
	{
		this.radio=radiomeasure;
	}
	public double getradio()
	{	
		return this.radio;
	}
	
	public void setcolor(String color)
	{
		this.color=color;
	}
	public String getcolor()
	{
		return this.color;
	}
	
	public void setmaterialrueda(int matrueda)
	{
		this.materialrueda=matrueda;
	}
	
	public int getmaterialruedaint()
	{
		return this.materialrueda;
	}
	public String getmaterialruedastring()
	{
		switch(this.getmaterialruedaint())		
		{
			case MaterialRueda.materiarueda_chapa:
				return MaterialRueda_string.materiarueda_chapa;
			case MaterialRueda.materiarueda_aleacion:
				return MaterialRueda_string.materiarueda_aleacion;
			case MaterialRueda.materiarueda_acero:
				return MaterialRueda_string.materiarueda_acero;
			case MaterialRueda.materiarueda_fibracarbono:
				return MaterialRueda_string.materiarueda_fibracarbono;
			default:
				return "No existe material rueda";		
		}
		
	}
	
	
}
