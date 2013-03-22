package entities;

public abstract class Persona {
	private String nombre;
	private String apellido;
	private char sexo;
	private int dni;
	protected int edad;
	
	public Persona()	
	{
		
	}
	public Persona(String nombre,String apellido,char sexo, int dni)
	{
		this.nombre=nombre;
		this.sexo=sexo;
		this.apellido=apellido;
		this.dni=dni;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre=nombre;		
	}
	
	public String getApellido()
	{
		return this.apellido;
	}
	public void setApellido(String apellido)
	{
		this.apellido=apellido;
	}
	public void setSexo(char sexo)
	{
		this.sexo=sexo;
	}
	public char getSexo()
	{
		return this.sexo;
	}
	
	public void setDni(int dni)
	{
		this.dni=dni;
	}
	public int getDni()
	{
		return this.dni;
	}
	
	public void setEdad(int edad)
	{
		this.edad=edad;
	}
	public int getEdad()
	{
		return this.edad;
	}
	
	//public abstract  Persona altaDeNuevaPersonaEnEmpresa();
}
