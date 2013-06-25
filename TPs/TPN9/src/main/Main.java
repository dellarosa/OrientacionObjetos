package main;


import handler.Handler;
import bo.GeneralBO;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		GeneralBO generalBo=new GeneralBO();
		generalBo.crearTablas();		//INICIAL

		Handler handler = new Handler();
		final MiFrame frame = new MiFrame(handler);
		handler.setFrame(frame);
		
		frame.setVisible(true);
	}

}
