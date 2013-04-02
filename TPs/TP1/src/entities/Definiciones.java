package entities;

public class Definiciones {


	public class Color_string {

		public static final String colorverde="verde";
		public static final String colorazul="azul";
		public static final String colorrosa="rosa";
		public static final String colornegro="negro";
		public static final String colorblanco="blanco";
		public static final String colorrojo="rojo";
		public static final String coloramarillo="amarillo";
		public static final String colorgris="gris";
	}
	
	public class MaterialRueda {

		public static final int materiarueda_chapa=0x00;				//CHAPA
		public static final int materiarueda_aleacion=0x01;			//ALEACION
		public static final int materiarueda_acero=0x02;				//ACERO
		public static final int materiarueda_fibracarbono=0x03;		//FIBRA CARBONO
		
	}
	
	public class MaterialRueda_string {

		public static final String materiarueda_chapa="Chapa";							//CHAPA
		public static final String materiarueda_aleacion="Aleación";					//ALEACION
		public static final String materiarueda_acero="Acero";							//ACERO
		public static final String materiarueda_fibracarbono="Fibra de Carbono";		//FIBRA CARBONO
		
	}
	
	public class CantidadMarchasCaja
	{
		public static final int cantmarchascaja_1=1;
		public static final int cantmarchascaja_2=2;
		public static final int cantmarchascaja_3=3;
		public static final int cantmarchascaja_4=4;
		public static final int cantmarchascaja_5=5;
		public static final int cantmarchascaja_6=6;
	}
	
	public class RelacionMarchas {

		public static final int relacionmarcha_cajacorta=0x00;
		public static final int relacionmarcha_cajamediana=0x01;					
		public static final int relacionmarcha_cajalarga=0x02;				
		
	}
	
	public class RelacionMarchas_string{

		public static final String relacionmarcha_cajalarga="Caja Larga";
		public static final String relacionmarcha_cajamediana="Caja Mediana";
		public static final String relacionmarcha_cajacorta="Caja Corta";		
		
	}
	
	public class CilindradaMotor{

		public static final double cilindradamotor_1400= 1.4;
		public static final double cilindradamotor_1600=1.6;
		public static final double cilindradamotor_1800=1.8;				
		public static final double cilindradamotor_2000=2.0;				
		
	}
	public class CaballosdeFuerza{

		public static final int cantcaballosfuerza_100= 100;
		public static final int cantcaballosfuerza_120=120;
		public static final int cantcaballosfuerza_140=140;				
		public static final int cantcaballosfuerza_200=200;				
		public static final int cantcaballosfuerza_250=250;
		
	}
	
	public class PulgadasRueda{

		public static final double pulgadarueda15= 15;
		public static final double pulgadarueda16=16;
		public static final double pulgadarueda17=17;				
		public static final double pulgadarueda17_5=17.5;				
		public static final double pulgadarueda18=18;
		
	}
	public class MarcasMotor{

		public static final String marcaAudi="Audi";
		public static final String marcaFord="Ford";
		public static final String marcaPeugeot="Peugeot";
		public static final String marcaKia="Kia";
		
	}
	
	
}
