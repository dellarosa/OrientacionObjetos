package entities;

public class Usuario {

	private String email;
	private String username;
	private String password;
	private String name;
	private int id;
	private int logueado;
	private String jerarquia;
	
	public int getLogueado() {
		return logueado;
	}
	public void setLogueado(int logueado) {
		this.logueado = logueado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJerarquia() {
		return jerarquia;
	}
	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString()
	{
		return "ID_USUARIO: "+this.getId()+" - NOMBRE: "+this.getName()+" - USER: "+this.getUsername()+" - PASS: "+this.getPassword()+" - EMAIL: "+this.getEmail()+" - JERARQUIA: "+this.getJerarquia();
	}
}
