package dao;

import java.util.List;

import utils.MiException;

import entities.Cliente;



public interface ClienteDAO {

	public List<Cliente> cargaClientes() throws MiException;
	
	public boolean crearTablaCliente() throws MiException;
	public boolean eliminarCliente(Cliente cliente) throws MiException;
	public boolean insertarCliente(int clienteID,String nombre,String auto,String mail) throws MiException;
	
	public boolean insertarCliente(Cliente cliente) throws MiException;
	
	public boolean updateCliente(Cliente cliente) throws MiException;
	public Cliente buscarClientePorApodo(String userToFind) throws MiException;
	public int buscarUltimoClienteId() throws MiException;
	
}
