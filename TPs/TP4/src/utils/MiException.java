package utils;

public class MiException extends RuntimeException {

	private String msg;
	private String msg2;
	
	public MiException()
	{
		
	}
	public MiException(String msge,Exception e) 
	{
		this.setMsg(msge +" : "+ e.getMessage());
		
	}
	
	public void setMsg(String msg)
	{
		this.msg=msg;
	}
	
	public String getMsg()
	{
		return this.msg;
	}
	
}
