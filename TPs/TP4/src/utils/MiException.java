package utils;

public class MiException extends RuntimeException {

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
