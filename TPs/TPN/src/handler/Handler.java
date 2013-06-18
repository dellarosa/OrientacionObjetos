package handler;

import java.util.List;

import javax.swing.JPanel;

import utils.MiException;

import entities.Cliente;
import entities.Filtro;
import entities.Usuario;

import bo.AceiteBO;
import bo.AutoparteBO;
import bo.ClienteBO;
import bo.FiltroBO;
import bo.GeneralBO;
import bo.LamparaBO;
import bo.ReparacionBO;
import bo.UsuarioBO;
import main.MiFrame;

public class Handler {

	private MiFrame frame;
	private UsuarioBO usuarioBO;
	private ClienteBO clienteBO;
	private AutoparteBO autoparteBO;
	private ReparacionBO reparacionBO;
	private GeneralBO generalBO;
	private FiltroBO filtroBO;
	private AceiteBO aceiteBO;
	private LamparaBO lamparaBO;
	
	
	public Handler() {
		this.usuarioBO = new UsuarioBO();
	}
	
	public void setFrame(MiFrame frame) {
		this.frame = frame;
	}
	public MiFrame getFrame()
	{
		return this.frame;
	}
	
	public void backToPrincipal() {
		frame.switchPanel(new JPanel());
	}
	
	public void eliminarUsuario(Usuario usuario) throws MiException {
		usuarioBO.eliminarUsuario(usuario);		
	}

	public List<Usuario> cargarUsuarios() throws MiException {
		return usuarioBO.cargarUsuarios();
	}

	public Usuario buscarUsuarioPorApodo(String userName) throws MiException {
		return usuarioBO.buscarUsuarioPorApodo(userName);
	}

	public int buscarUltimoUsuarioId() throws MiException{
		
		return usuarioBO.buscarUltimoUsuarioId();
	}

	public boolean updateUsuario(Usuario usuario) throws MiException {
		
		return usuarioBO.updateUsuario(usuario);
	}

	public boolean insertarUsuario(Usuario usuario) throws MiException {
		
		return usuarioBO.insertarUsuario(usuario);
	}

	public int buscarUltimoClienteId() throws MiException {
		return clienteBO.buscarUltimoClienteId();
	}

	public boolean insertarCliente(Cliente cliente) throws MiException {

		return clienteBO.insertarCliente(cliente);
	}

	public boolean eliminarCliente(Cliente cliente) throws MiException {
		
		return clienteBO.eliminarCliente(cliente);
	}

	public List<Cliente> cargaClientes() throws MiException {
		return clienteBO.cargaClientes();
	}

	public Cliente buscarClientePorApodo(String text) throws MiException {
		return clienteBO.buscarClientePorApodo(text);
	}

	public boolean updateCliente(Cliente cliente) throws MiException {
		return clienteBO.updateCliente(cliente);
	}

	public int buscarUltimaAutoparteId() throws MiException {
		return autoparteBO.buscarUltimaAutoparteId();
	}

	public int buscarUltimoFiltroId() throws MiException {
		return filtroBO.buscarUltimoFiltroId();
	}

	public boolean insertarFiltro(Filtro filtro) throws MiException {
		return filtroBO.insertarFiltro(filtro);
	}
}
