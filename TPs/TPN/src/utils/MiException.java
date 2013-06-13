package utils;

public class MiException extends Exception {

	public MiException()
	{
		super();
	}
	public MiException(String msg)
	{
		super(msg);
	}
	public MiException(String msge,Exception e) 
	{
		super(msge,e);
		
	}

	
}
