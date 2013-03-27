package entities;

import utils.Definiciones.MaterialRueda;
import utils.Definiciones.MaterialRueda_string;

public class Rueda 
{
	// DECALARACIÓN DE ATRIBUTOS
	private double radio;
	private String color;
	private int materialrueda;
	
	// COMIENZO DE LOS METODOS
	
	public Rueda()
	{
		
	}
	
	public void setRadio(double radiomeasure)
	{
		this.radio=radiomeasure;
	}
	public double getRadio()
	{	
		return this.radio;
	}
	
	public void setColor(String color)
	{
		this.color=color;
	}
	public String getColor()
	{
		return this.color;
	}
	
	public void setMaterialRueda(int matrueda)
	{
		this.materialrueda=matrueda;
	}
	
	public int getMaterialRuedaint()
	{
		return this.materialrueda;
	}
	public String getMaterialRuedaString()
	{
		switch(this.getMaterialRuedaint())		
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
	
	public String toString()
	{
		return "La rueda tiene las siguientes caracteristicas: \nRadio: "+this.getRadio()+"\nColor: "+
				this.getColor()+"\nMaterial: "+this.getMaterialRuedaString();
	}
}
