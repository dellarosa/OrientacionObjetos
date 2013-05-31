package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import bo.AceiteBO;
import bo.AutoparteBO;
import bo.ClienteBO;
import bo.FiltroBO;
import bo.LamparaBO;
import bo.ReparacionBO;
import bo.UsuarioBO;

import paneles.Panel;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;
import entities.Usuario;

import utils.Definiciones;
import utils.Dentre;
import utils.MetodosGrl;
import utils.MiException;

public class Menu 
{
	
	MetodosGrl metgral;
	MenuInicio menuInicio;
	
	Panel panelGestor;
	
	
	
	
	
	public Menu()
	{		
		//menuInicio=new MenuInicio();
		
	}
	public MenuInicio getMenuInicio()
	{
		return this.menuInicio;
	}
	public void setMenuInicio(MenuInicio menuInicio)
	{
		this.menuInicio=menuInicio;
	}
	
//////################################### MENU SISTEMA ########################################################
	public class MenuSistema
	{
		JFrame frameSistema;
		
		List<Autoparte> autopartesG;
		List<Cliente> clientesG;
		List<Reparacion> reparacionesG;
		
		private Filtro filtroAux;
    	private Aceite aceiteAux;
    	private Lampara lamparaAux;
    	private Reparacion reparacionAux;
		public Filtro getFiltroAux() {
			return filtroAux;
		}
		public void setFiltroAux(Filtro filtroAux) {
			this.filtroAux = filtroAux;
		}
		public Aceite getAceiteAux() {
			return aceiteAux;
		}
		public void setAceiteAux(Aceite aceiteAux) {
			this.aceiteAux = aceiteAux;
		}
		public Lampara getLamparaAux() {
			return lamparaAux;
		}
		public void setLamparaAux(Lampara lamparaAux) {
			this.lamparaAux = lamparaAux;
		}
		public JFrame getFrameSistema() {
			return frameSistema;
		}
		public void setFrameSistema(JFrame frame) {
			this.frameSistema = frame;
		}			
		public Reparacion getReparacionAux() {
			return reparacionAux;
		}
		public void setReparacionAux(Reparacion reparacionAux) {
			this.reparacionAux = reparacionAux;
		}
		public MenuSistema()
		{
			metgral=new MetodosGrl();
		}
		public MenuSistema(JFrame frame,final Usuario user)	throws MiException,SQLException
		{
			panelGestor=new Panel();
			filtroAux=new Filtro();
			aceiteAux=new Aceite();
			lamparaAux=new Lampara();
			reparacionAux=new Reparacion();
			final ClienteBO clienteBo=new ClienteBO();
			final AceiteBO aceiteBo=new AceiteBO();
			final FiltroBO filtroBo=new FiltroBO();
			final LamparaBO lamparaBo=new LamparaBO();
			final AutoparteBO autoparteBo=new AutoparteBO();
			final ReparacionBO reparacionBo=new ReparacionBO();
			
			
			try
			{
			
				metgral=new MetodosGrl();
				this.setFrameSistema(frame);
				this.getFrameSistema().getContentPane().removeAll();
				this.getFrameSistema().getContentPane().repaint();
				
				JPanel panel = (JPanel)this.getFrameSistema().getContentPane();
		        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));	        
		        panel.setBackground(Color.cyan);
		        
		        JPanel panelOperaciones = new JPanel();
		        panelOperaciones.setLayout(new GridLayout(Definiciones.cantidadOpcionesMenu,1));	        
		        panelOperaciones.setBackground(Color.cyan);
		        
		        final JPanel panelEnd = new JPanel();
		        panelEnd.setLayout(new GridLayout(2,1));
		        panelEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
		        panelEnd.setBackground(Color.cyan);
		        	
		        
		        JPanel panelTitulo = new JPanel();
		        panelTitulo.setLayout(new BorderLayout());	        
		        panelTitulo.setBackground(Color.BLACK);
		        panelTitulo.setMaximumSize(new Dimension(400,50));
		        JLabel labelTitulo=new JLabel("MENU DEL SISTEMA",JLabel.CENTER);	        
		        labelTitulo.setFont(new Font(Font.SERIF,Font.BOLD,15));
		        labelTitulo.setForeground(Color.white);
		        panelTitulo.add("Center",labelTitulo);
		        
		        JPanel panelResto = new JPanel();
		        panelResto.setLayout(new GridLayout(1,1));	        
		        panelResto.setBackground(Color.cyan);
		        panelResto.setPreferredSize(new Dimension(400,300));
		       
		    	      
				///######################## CARGA DE ENTIDADES ######################
				autopartesG=new ArrayList<Autoparte>();
				clientesG=new ArrayList<Cliente>();
				reparacionesG=new ArrayList<Reparacion>();
				
				
				try
				{	
					System.out.print("\n\n***CARGA DE ENTIDADES***\n");
					clientesG=clienteBo.cargaClientes();
					reparacionesG=reparacionBo.cargaReparaciones();
					autopartesG=autoparteBo.cargaAutopartes();
				}
				catch(MiException e)
				{
					throw  e;
				}catch(Exception e)
				{
					throw new MiException("[MENU] Error al cargar: "+e);
				}
				//#########################################################################
				
				
		//1-		//########################### CARGA CLIENTE ###########################
				JButton btCargaCliente=new JButton();
				btCargaCliente.setText("CARGAR CLIENTE");								
				btCargaCliente.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {	            	
		            	try
						{
		            		getFrameSistema().getContentPane().removeAll();
		            		getFrameSistema().getContentPane().repaint();
		    				
		            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);	    				
		            		JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(3, 2), Definiciones.line_blackline,Color.cyan,null,null);
		            		JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3,1),null,Color.blue,null,null);
		    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CARGA DE CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
					        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
		    				
							final JTextField fieldNombre=new JTextField();
							fieldNombre.setBorder(Definiciones.line_blackline);						
							final JTextField fieldAuto=new JTextField();
							fieldAuto.setBorder(Definiciones.line_blackline);
							final JTextField fieldEmail=new JTextField();
							fieldEmail.setBorder(Definiciones.line_blackline);
							
							
							JLabel labelNombre=new JLabel("Nombre");
							labelNombre.setBorder(Definiciones.line_blackline);
							JLabel labelEmail=new JLabel("Email");
							labelEmail.setBorder(Definiciones.line_blackline);
							JLabel labelAuto=new JLabel("Auto");
							labelAuto.setBorder(Definiciones.line_blackline);
																																			
							panelGrid.add(labelNombre);
							panelGrid.add(fieldNombre);
							
							panelGrid.add(labelEmail);
							panelGrid.add(fieldEmail);
							
							panelGrid.add(labelAuto);
							panelGrid.add(fieldAuto);
							
							///################# SUBMIT ##########################
							JButton btSubmitCreate=new JButton();
							btSubmitCreate.setText("SUBMIT");								
							btSubmitCreate.addMouseListener(new MouseAdapter() {												
				            @Override
				            public void mouseReleased(MouseEvent evt) {
				            	Cliente cliente= new Cliente();								
								cliente.setNombre(fieldNombre.getText());
								cliente.setMail(fieldEmail.getText());
								cliente.setAuto(fieldAuto.getText());
								
				            	try {
				            		cliente.setId(clienteBo.buscarUltimoClienteId());
				            		if(cliente.getNombre().equals("")||cliente.getMail().equals("")||cliente.getAuto().equals(""))
				            		{
				            			System.out.print("\nDATOS VACIOS");				//DESPUES HACER CUADROS INFORMATIVOS
				            			Thread.sleep(2000);				            			
				            		}else
				            		{
				            			if(clienteBo.insertarCliente(cliente))
										{
											System.out.print("\nCLIENTE INGRESADO CORRECTAMENTE");
											Thread.sleep(2000);
											clientesG.add(cliente);
										}else
										{
											System.out.print("\nFALLO INGRESO CLIENTE");
											Thread.sleep(2000);											
										}			            			
				            		}
				            		MenuSistema menuSistema=new MenuSistema(getFrameSistema(),user);
									menuSistema.mostrar();
								} catch (MiException e) {
										throw e;
								} catch (SQLException e) {
										throw new MiException("\n[CREATE USER] SQL EXCEPTION : "+e);
								} catch (InterruptedException e) {
									throw new MiException("\n[CREATE USER] INTERRUPTED EXCEPTION : "+e);
					            } catch (Exception e) {
									throw new MiException("\n[CREATE USER]EXCEPTION : "+e);												
								}
				            }
							});
							panelEnd.add(btSubmitCreate);
							
							///####################### VOLVER #############################
							JButton btVolver=new JButton();
							btVolver.setText("VOLVER");								
							btVolver.addMouseListener(new MouseAdapter() {												
				            @Override
				            public void mouseReleased(MouseEvent evt) {
				            	 
								try {
									MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
									menuSistema.mostrar();	
								} catch (MiException e) {
									throw e;
								}catch(SQLException e)
								{
									throw new MiException("\n[MENU SISTEMA] CREAR CLIENTE EXCEPTION :"+e);
								}
												            	
				            }
							});
							panelEnd.add(btVolver);
							///######################## SALIR ##########################
							JButton btSalir=new JButton();
							btSalir.setText("SALIR");								
							btSalir.addMouseListener(new MouseAdapter() {												
				            @Override
				            public void mouseReleased(MouseEvent evt) {
				            	System.exit(0);					            	
				            }
							});
							panelEnd.add(btSalir);
	
							panel.add(panelTitulo);
							panel.add(panelGrid);
							panel.add(panelResto);
							panel.add(panelEnd);
													
							getFrameSistema().setContentPane(panel);
			            }catch (MiException e) {
							throw e;
						}			            
			            catch (Exception e) {
							throw new MiException("\n[CREATE USER]EXCEPTION : "+e);												
						}
	            	}
	            
				});
				panelOperaciones.add(btCargaCliente);
				
	//2-		//########################### MODIFICAR CLIENTE ###########################
				JButton btModifCliente=new JButton();
				btModifCliente.setText("MODIFICAR CLIENTE");								
				btModifCliente.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	try
					{
	            		getFrameSistema().getContentPane().removeAll();
	            		getFrameSistema().getContentPane().repaint();
	    				
	            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);	    				
	            		JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,Color.cyan,null,null);
	            		JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3,1),null,Color.blue,null,null);
	    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICAR CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
				        
				        final JTextField fieldNombre=new JTextField();
						fieldNombre.setBorder(Definiciones.line_blackline);	
						
						JLabel labelNombre=new JLabel("Ingrese Nombre cliente");
						labelNombre.setBorder(Definiciones.line_blackline);
						
						panelGrid.add(labelNombre);
						panelGrid.add(fieldNombre);
						
						///################# SUBMIT ##########################
						JButton btSubmit=new JButton();
						btSubmit.setText("SUBMIT");								
						btSubmit.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	try
			            	{
				            	final Cliente clienteEncontrado=metgral.buscarClientePorApodo(fieldNombre.getText(),clientesG);
								if(clienteEncontrado==null)
								{
									System.out.print("\nCLIENTE NO ENCONTRADO");
									Thread.sleep(2000);
									MenuSistema menuSistema=new MenuSistema(getFrameSistema(),user);
									menuSistema.mostrar();
								}
								getFrameSistema().getContentPane().removeAll();
			            		getFrameSistema().getContentPane().repaint();
			    				
			            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);	    				
			            		JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(3, 2), Definiciones.line_blackline,Color.cyan,null,null);
			            		JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3,1),null,Color.blue,null,null);
			    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICAR CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
						        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
						        
								final JTextField fieldNombre=new JTextField();
								fieldNombre.setBorder(Definiciones.line_blackline);						
								final JTextField fieldAuto=new JTextField();
								fieldAuto.setBorder(Definiciones.line_blackline);
								final JTextField fieldEmail=new JTextField();
								fieldEmail.setBorder(Definiciones.line_blackline);
								
								
								JLabel labelNombre=new JLabel("Nombre ("+clienteEncontrado.getNombre()+")");
								labelNombre.setBorder(Definiciones.line_blackline);
								JLabel labelEmail=new JLabel("Email ("+clienteEncontrado.getMail()+")");
								labelEmail.setBorder(Definiciones.line_blackline);
								JLabel labelAuto=new JLabel("Auto ("+clienteEncontrado.getAuto()+")");
								labelAuto.setBorder(Definiciones.line_blackline);
																																				
								panelGrid.add(labelNombre);
								panelGrid.add(fieldNombre);
								
								panelGrid.add(labelEmail);
								panelGrid.add(fieldEmail);
								
								panelGrid.add(labelAuto);
								panelGrid.add(fieldAuto);
								
								///################# SUBMIT ##########################
								JButton btSubmit=new JButton();
								btSubmit.setText("SUBMIT");								
								btSubmit.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	try{					            	
									
					            		if(fieldNombre.getText().equals("")&&fieldEmail.getText().equals("")&&fieldAuto.getText().equals(""))
					            		{	
					            				System.out.print("\n\nDATOS VACIOS - FALLO MODIFICACION CLIENTE");	//AGREGAR AVISO EMERGENTE
					            				Thread.sleep(2000);
												MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
												menuSistema.mostrar();	
					            		}else
					            		{
						            		Cliente clienteAux=clienteEncontrado;
										
										
											clienteAux.setNombre(fieldNombre.getText());
											clienteAux.setMail(fieldEmail.getText());
											clienteAux.setAuto(fieldAuto.getText());
											
											if(clienteBo.updateCliente(clienteAux))
											{
												System.out.print("\nCLIENTE MODIFICADO CORRECTAMENTE");
												Thread.sleep(2000);
												clientesG=metgral.eliminarClienteDeLista(clientesG,clienteEncontrado);									
												clientesG.add(clienteAux);												
											}else
											{
												System.out.print("\nFALLO MODIFICACION CLIENTE");
												Thread.sleep(2000);												
											}								
											MenuSistema menuSistema=new MenuSistema(getFrameSistema(),user);
											menuSistema.mostrar();
					            		}
									} catch (MiException e) {
											throw e;
									} catch (SQLException e) {
											throw new MiException("\n[MODIFICAR CLIENTE] SQL EXCEPTION : "+e);
									} catch (InterruptedException e) {
										throw new MiException("\n[MODIFICAR CLIENTE] INTERRUPTED EXCEPTION : "+e);
						            } catch (Exception e) {
										throw new MiException("\n[MODIFICAR CLIENTE]EXCEPTION : "+e);												
									}
					            }
								});
								panelEnd.add(btSubmit);
								
								
								///####################### VOLVER #############################
								JButton btVolver=new JButton();
								btVolver.setText("VOLVER");								
								btVolver.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	MenuSistema menuSistema;
									try {
										menuSistema = new MenuSistema(getFrameSistema(),user);
										menuSistema.mostrar();	
									} catch (MiException e) {
										throw e;
									}catch(SQLException e)
									{
										throw new MiException("\n[MENU SISTEMA] MODIFICAR CLIENTE EXCEPTION :"+e);
									}				            	
					            }
								});
								panelEnd.add(btVolver);
								///######################## SALIR ##########################
								JButton btSalir=new JButton();
								btSalir.setText("SALIR");								
								btSalir.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	System.exit(0);					            	
					            }
								});
								panelEnd.add(btSalir);
		
								panel.add(panelTitulo);
								panel.add(panelGrid);
								panel.add(panelResto);
								panel.add(panelEnd);								
								getFrameSistema().setContentPane(panel);
			            		
							} catch (MiException e) {
									throw e;
							} catch (SQLException e) {
									throw new MiException("\n[MODIF CLIENTE] SQL EXCEPTION : "+e);
							} catch (InterruptedException e) {
								throw new MiException("\n[MODIF CLIENTE] INTERRUPTED EXCEPTION : "+e);
				            } catch (Exception e) {
								throw new MiException("\n[MODIF CLIENTE]EXCEPTION : "+e);												
							}
			            }
						});
						panelEnd.add(btSubmit);						
						
						
						///####################### VOLVER #############################
						JButton btVolver=new JButton();
						btVolver.setText("VOLVER");								
						btVolver.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	MenuSistema menuSistema;
							try {
								menuSistema = new MenuSistema(getFrameSistema(),user);
								menuSistema.mostrar();	
							} catch (MiException e) {
								throw e;
							}catch(SQLException e)
							{
								throw new MiException("\n[MENU SISTEMA] MODIFICAR CLIENTE EXCEPTION :"+e);
							}				            	
			            }
						});
						panelEnd.add(btVolver);
						///######################## SALIR ##########################
						JButton btSalir=new JButton();
						btSalir.setText("SALIR");								
						btSalir.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	System.exit(0);					            	
			            }
						});
						panelEnd.add(btSalir);
						
						panel.add(panelTitulo);
						panel.add(panelGrid);
						panel.add(panelResto);
						panel.add(panelEnd);
						
						getFrameSistema().setContentPane(panel);
						
					}catch(MiException e)
					{
						throw e;
					}catch(Exception e)
					{
						throw new MiException("\n[MENU SISTEMA] MODIFICAR CLIENTE EXCEPTION :"+e);
					}
	            }
				});			
				panelOperaciones.add(btModifCliente);
				
	//3-			//########################### BAJA CLIENTE ###########################
				JButton btBajaCliente=new JButton();
				btBajaCliente.setText("BAJA CLIENTE");								
				btBajaCliente.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	
	            	try
					{
	            		getFrameSistema().getContentPane().removeAll();
	            		getFrameSistema().getContentPane().repaint();
	    				
	            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);	    				
	            		JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,Color.cyan,null,null);
	            		JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3,1),null,Color.blue,null,null);
	    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
				        				        
				        final JTextField fieldNombre=new JTextField();
						fieldNombre.setBorder(Definiciones.line_blackline);	
						
						JLabel labelNombre=new JLabel("Ingrese Nombre cliente Baja");
						labelNombre.setBorder(Definiciones.line_blackline);
						
						panelGrid.add(labelNombre);
						panelGrid.add(fieldNombre);
						
						///################# SUBMIT ##########################
						JButton btSubmit=new JButton();
						btSubmit.setText("SUBMIT");								
						btSubmit.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	try
			            	{
				            	final Cliente clienteEncontrado=metgral.buscarClientePorApodo(fieldNombre.getText(),clientesG);
								if(clienteEncontrado==null)
								{
									System.out.print("\nCLIENTE NO ENCONTRADO");		//DEBUG
									Thread.sleep(2000);
									MenuSistema menuSistema=new MenuSistema(getFrameSistema(),user);
									menuSistema.mostrar();
								}else
								{
										getFrameSistema().getContentPane().removeAll();
										getFrameSistema().getContentPane().repaint();
										
										JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
					    				JPanel panelOperaciones=panelGestor.crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,Color.cyan,null,null);
					    				JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);					    				
					    				JPanel panelLabel=panelGestor.crearPanelBox(BoxLayout.X_AXIS,Color.black,null);
					    				
					    				JLabel labelCliente=panelGestor.crearTitulo("BAJA DE 'CLIENTE' - '"+clienteEncontrado.getNombre()+"'",JLabel.CENTER,Color.white,new Font(Font.SERIF,Font.BOLD,20));
								        panelLabel.add(labelCliente);
								        
								        ///############### SUBMIT NO ####################################
								        JButton btSubmitNo=new JButton();
								        btSubmitNo.setText("NO");								
								        btSubmitNo.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {
								            	try
								            	{
									            	System.out.print("\nSE CANCELO BAJA CLIENTE");		//DEBUG
													Thread.sleep(2000);
													MenuSistema menuSistema=new MenuSistema(getFrameSistema(),user);
													menuSistema.mostrar();
								            	}catch(MiException e)
												{
													throw e;
												}catch(Exception e)
												{
													throw new MiException("\n[MENU SISTEMA] SUBMIT NO BAJA CLIENTE EXCEPTION :"+e);
												}
												
								            }
										});
								        panelOperaciones.add(btSubmitNo);
								        
								        
								        ///############### SUBMIT YES####################################
								        JButton btSubmitYes=new JButton();
								        btSubmitYes.setText("YES");								
								        btSubmitYes.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {								            	
												try
								            	{
									            	if(clienteBo.eliminarCliente(clienteEncontrado))
													{
														System.out.print("\nCLIENTE ELIMINADO CORRECTAMENTE");		//DEBUG
														Thread.sleep(2000);
														clientesG=metgral.eliminarClienteDeLista(clientesG,clienteEncontrado);
														MenuSistema menuSistema=new MenuSistema(getFrameSistema(),user);
														menuSistema.mostrar();
													}else
													{
														System.out.print("\nFALLO BAJA CLIENTE");		//DEBUG
														Thread.sleep(2000);
														MenuSistema menuSistema=new MenuSistema(getFrameSistema(),user);
														menuSistema.mostrar();
													}
								            	}catch(MiException e)
												{
													throw e;
												}catch(Exception e)
												{
													throw new MiException("\n[MENU SISTEMA] SUBMIT YES BAJA CLIENTE EXCEPTION :"+e);
												}
								            }
										});
								        panelOperaciones.add(btSubmitYes);
								        
								        panel.add(panelLabel);
								        panel.add(panelResto);
								        panel.add(panelOperaciones);
								        getFrameSistema().setContentPane(panel);
								}
			            	}catch(MiException e)
							{
								throw e;
							}catch(Exception e)
							{
								throw new MiException("\n[MENU SISTEMA] BAJA CLIENTE EXCEPTION :"+e);
							}
			            }
			            });
	            		panelEnd.add(btSubmit);
	            		
						///####################### VOLVER #############################
						JButton btVolver=new JButton();
						btVolver.setText("VOLVER");								
						btVolver.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	MenuSistema menuSistema;
							try {
								menuSistema = new MenuSistema(getFrameSistema(),user);
								menuSistema.mostrar();	
							} catch (MiException e) {
								throw e;
							}catch(SQLException e)
							{
								throw new MiException("\n[MENU SISTEMA] BAJA CLIENTE EXCEPTION :"+e);
							}				            	
			            }
						});
						panelEnd.add(btVolver);
						
						///######################## SALIR ##########################
						JButton btSalir=new JButton();
						btSalir.setText("SALIR");								
						btSalir.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	System.exit(0);					            	
			            }
						});
						panelEnd.add(btSalir);
						
						panel.add(panelTitulo);
						panel.add(panelGrid);
						panel.add(panelResto);
						panel.add(panelEnd);
						
						getFrameSistema().setContentPane(panel);
						
					}catch(MiException e)
					{
						throw e;
					}catch(Exception e)
					{
						throw new MiException("\n[MENU SISTEMA] BAJA CLIENTE EXCEPTION :"+e);
					}
	            }
				});			
				panelOperaciones.add(btBajaCliente);
				
	//4-			//########################### CARGA AUTOPARTE ###########################
				JButton btCargaAutoparte=new JButton();
				btCargaAutoparte.setText("CARGAR AUTOPARTES");								
				btCargaAutoparte.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	try
	            	{
		            	getFrameSistema().getContentPane().removeAll();
	            		getFrameSistema().getContentPane().repaint();
	    				
	            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
	    				JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,null,null,null);
	    				JPanel panelGridLabel=panelGestor.crearPanelGrid(new GridLayout(3, 2), Definiciones.line_blackline,null,null,null);
	    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);						
	    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CARGA DE AUTOPARTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
				        
				        
						final JTextField fieldTipoAutoparte=new JTextField();
						fieldTipoAutoparte.setBorder(Definiciones.line_blackline);	
						JLabel labelTipoAutoparte=new JLabel("TIPO AUTOPARTE",JLabel.CENTER);
						labelTipoAutoparte.setBorder(Definiciones.line_blackline);						
						panelGrid.add(labelTipoAutoparte);
						panelGrid.add(fieldTipoAutoparte);
												
						panelGridLabel=panelGestor.labelsTiposAutopartes(panelGridLabel);
						
						
						///################# SUBMIT ##########################
						JButton btSubmit=new JButton();
						btSubmit.setText("SUBMIT");								
						btSubmit.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	
			            	try
			            	{
			            		getFrameSistema().getContentPane().removeAll();
			            		getFrameSistema().getContentPane().repaint();
			    				
			    				JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
			    				JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(8, 2), Definiciones.line_blackline,null,null,null);
								JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);
								//JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CARGA DE AUTOPARTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
								JPanel panelTitulo = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.black,new Dimension(400,50));								
								JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
								
								JLabel labelTitulo=panelGestor.crearTitulo("", JLabel.CENTER,Color.white, new Font(Font.SERIF,Font.BOLD,15));
						        
						        final JTextField fieldMarca=new JTextField();
								fieldMarca.setBorder(Definiciones.line_blackline);						
								final JTextField fieldModelo=new JTextField();
								fieldModelo.setBorder(Definiciones.line_blackline);
								final JTextField fieldCantidad=new JTextField();
								fieldCantidad.setBorder(Definiciones.line_blackline);
								final JTextField fieldCosto=new JTextField();
								fieldCosto.setBorder(Definiciones.line_blackline);
								
								final JTextField fieldTipoMaterial=new JTextField();
								final JTextField fieldTamanio=new JTextField();
								
								final JTextField fieldCantLitros=new JTextField();
								final JTextField fieldTipoAceite=new JTextField();
								
								final JTextField fieldColor=new JTextField();
								final JTextField fieldTamanioL=new JTextField();
								
								JLabel labelMarca=new JLabel("INGRESE MARCA",JLabel.CENTER);
								labelMarca.setBorder(Definiciones.line_blackline);
								JLabel labelModelo=new JLabel("INGRESE MODELO",JLabel.CENTER);
								labelModelo.setBorder(Definiciones.line_blackline);
								JLabel labelCantidad=new JLabel("INGRESE CANTIDAD",JLabel.CENTER);
								labelCantidad.setBorder(Definiciones.line_blackline);
								JLabel labelCosto=new JLabel("INGRESE COSTO",JLabel.CENTER);
								labelCosto.setBorder(Definiciones.line_blackline);
								
			            		if(Integer.valueOf(fieldTipoAutoparte.getText())>3||Integer.valueOf(fieldTipoAutoparte.getText())<1)	//EN EL FUTURO DEBERIA SER DINAMICO COMO EL TIPO DE AUTOPARTE
			            		{
				            		System.out.print("\n[MENU SISTEMA] NO EXISTE ESE TIPO DE AUTOPARTE "); //ENEL FUTURO AGREGAR MENU EMERGENTE
									try {
										MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
										menuSistema.mostrar();	
									} catch (MiException e) {
										throw e;
									}catch(SQLException e)
									{
										throw new MiException("\n[MENU SISTEMA] CARGA AUTOPARTE EXCEPTION :"+e);
									}	
			            		}else
					            {
									
									switch(Integer.valueOf(fieldTipoAutoparte.getText()))
									{
										case Definiciones.FILTRO_INDICE:
											labelTitulo.setText("CARGA DE FILTRO");
											panelTitulo.add("Center",labelTitulo);
									
											fieldTipoMaterial.setBorder(Definiciones.line_blackline);										
											fieldTamanio.setBorder(Definiciones.line_blackline);										
											
											JLabel labelTipoMaterial=new JLabel("INGRESE TIPO MATERIAL",JLabel.CENTER);
											labelTipoMaterial.setBorder(Definiciones.line_blackline);									
											JLabel labelTamanio=new JLabel("INGRESE TAMA�O",JLabel.CENTER);
											labelTamanio.setBorder(Definiciones.line_blackline);										
											
											panelGrid.add(labelTipoMaterial);
											panelGrid.add(fieldTipoMaterial);
											panelGrid.add(labelTamanio);
											panelGrid.add(fieldTamanio);
										break;
										case Definiciones.ACEITE_INDICE:
											labelTitulo.setText("CARGA DE ACEITE");
											panelTitulo.add("Center",labelTitulo);
											
											
											fieldCantLitros.setBorder(Definiciones.line_blackline);										
											fieldTipoAceite.setBorder(Definiciones.line_blackline);										
											
											JLabel labelCantLitros=new JLabel("INGRESE TIPO MATERIAL",JLabel.CENTER);
											labelCantLitros.setBorder(Definiciones.line_blackline);									
											JLabel labelTamanioA=new JLabel("INGRESE TIPO DE ACEITE",JLabel.CENTER);
											labelTamanioA.setBorder(Definiciones.line_blackline);										
											
											panelGrid.add(labelCantLitros);
											panelGrid.add(fieldCantLitros);
											panelGrid.add(labelTamanioA);
											panelGrid.add(fieldTipoAceite);
											break;
										case Definiciones.LAMPARA_INDICE:
											labelTitulo.setText("CARGA DE LAMPARA");
											panelTitulo.add("Center",labelTitulo);
											
											fieldColor.setBorder(Definiciones.line_blackline);										
											fieldTamanioL.setBorder(Definiciones.line_blackline);										
											
											JLabel labelColor=new JLabel("INGRESE COLOR",JLabel.CENTER);
											labelColor.setBorder(Definiciones.line_blackline);									
											JLabel labelTamanioL=new JLabel("INGRESE TAMA�O",JLabel.CENTER);
											labelTamanioL.setBorder(Definiciones.line_blackline);										
											
											panelGrid.add(labelColor);
											panelGrid.add(fieldColor);
											panelGrid.add(labelTamanioL);
											panelGrid.add(fieldTamanioL);
											
											break;
											default:
												System.out.print("\n[MENU SISTEMA] NO EXISTE ESE TIPO DE AUTOPARTE "); //ENEL FUTURO AGREGAR MENU EMERGENTE
												try {
													MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
													menuSistema.mostrar();	
												} catch (MiException e) {
													throw e;
												}catch(SQLException e)
												{
													throw new MiException("\n[MENU SISTEMA] CARGA AUTOPARTE EXCEPTION :"+e);
												}			
												break;
										}
										
										panelGrid.add(labelMarca);
										panelGrid.add(fieldMarca);										
										panelGrid.add(labelModelo);
										panelGrid.add(fieldModelo);
										panelGrid.add(labelCantidad);
										panelGrid.add(fieldCantidad);
										panelGrid.add(labelCosto);
										panelGrid.add(fieldCosto);
										
											///################# SUBMIT ##########################
											JButton btSubmit=new JButton();
											btSubmit.setText("SUBMIT");								
											btSubmit.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) 
								            {
								            	
								            	try
								            	{
									            	switch(Integer.valueOf(fieldTipoAutoparte.getText()))
													{
														case Definiciones.FILTRO_INDICE:														
															if(!fieldMarca.getText().equals("")&&!fieldModelo.getText().equals("")&&!fieldCantidad.getText().equals("")&&!fieldCosto.getText().equals("")
																&&!fieldTipoMaterial.getText().equals("")&&!fieldTamanio.getText().equals(""))										
															{
																Filtro filtro =new Filtro();															
																filtro.setId(autoparteBo.buscarUltimaAutoparteId());									
																filtro.setFiltro_ID(filtroBo.buscarUltimoFiltroId());
																filtro.setMaterial(fieldTipoMaterial.getText());
																filtro.setTamanio(fieldTamanio.getText());
																filtro.setMarca(fieldMarca.getText());
																filtro.setModelo(fieldModelo.getText());
																filtro.setTipoAutoparte("filtro");
																filtro.setCantDisponible(Integer.valueOf(fieldCantidad.getText()));
																filtro.setCosto(Double.valueOf(fieldCosto.getText()));
																
																filtroBo.insertarFiltro(filtro);
																//sqlinserts.insertarAutoparte(filtro.getAutoparteID(),filtro.getTipoAutoparte(),filtro.getMarca(),filtro.getModelo(),filtro.getCosto(),filtro.getCantDisponible());
																autoparteBo.insertarAutoparte(filtro);
																autopartesG.add(filtro);
																
																System.out.print("\n[MENU SISTEMA]FILTRO INGRESADO CORRECTAMENTE");
																MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																menuSistema.mostrar();
															}else
															{
																System.out.print("\n[MENU SISTEMA]validacion: DATO INGRESADO INCORRECTO O FALTAN DATOS");
																try {
																	MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																	menuSistema.mostrar();	
																} catch (MiException e) {
																	throw e;
																}catch(SQLException e)
																{
																	throw new MiException("\n[MENU SISTEMA]IF VALIDAR DATOS INGRESADOS - CREAR AUTOPARTE EXCEPTION :"+e);
																}
															}
															break;
															
														case Definiciones.ACEITE_INDICE:
															if(!fieldMarca.getText().equals("")&&!fieldModelo.getText().equals("")&&!fieldCantidad.getText().equals("")&&!fieldCosto.getText().equals("")
																	&&!fieldCantLitros.getText().equals("")&&!fieldTipoAceite.getText().equals(""))										
															{
																Aceite aceite =new Aceite();															
																aceite.setId(autoparteBo.buscarUltimaAutoparteId());
																
																aceite.setAceite_ID(aceiteBo.buscarUltimoAceiteId());
																aceite.setCantidadlitros(Integer.valueOf(fieldCantLitros.getText()));
																aceite.setTipoAceite(fieldTipoAceite.getText());
																aceite.setMarca(fieldMarca.getText());
																aceite.setModelo(fieldModelo.getText());
																aceite.setTipoAutoparte("aceite");
																aceite.setCantDisponible(Integer.valueOf(fieldCantidad.getText()));
																aceite.setCosto(Double.valueOf(fieldCosto.getText()));
																
																aceiteBo.insertarAceite(aceite);
																//sqlinserts.insertarAutoparte(aceite.getAutoparteID(),aceite.getTipoAutoparte(),aceite.getMarca(),aceite.getModelo(),aceite.getCosto(),aceite.getCantDisponible());
																autoparteBo.insertarAutoparte(aceite);
																autopartesG.add(aceite);
																
																System.out.print("\n[MENU SISTEMA]ACEITE INGRESADO CORRECTAMENTE");
																MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																menuSistema.mostrar();	
															}else
															{
																System.out.print("\n[MENU SISTEMA]validacion: DATO INGRESADO INCORRECTO O FALTAN DATOS");
																try {
																	MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																	menuSistema.mostrar();	
																} catch (MiException e) {
																	throw e;
																}catch(SQLException e)
																{
																	throw new MiException("\n[MENU SISTEMA]IF VALIDAR DATOS INGRESADOS - CREAR AUTOPARTE EXCEPTION :"+e);
																}
															}
															break;
															
														case Definiciones.LAMPARA_INDICE:
															if(!fieldMarca.getText().equals("")&&!fieldModelo.getText().equals("")&&!fieldCantidad.getText().equals("")&&!fieldCosto.getText().equals("")
																&&!fieldColor.getText().equals("")&&!fieldTamanioL.getText().equals(""))										
															{
																Lampara lampara =new Lampara();															
																lampara.setId(autoparteBo.buscarUltimaAutoparteId());															
																lampara.setLampara_ID(lamparaBo.buscarUltimoLamparaId());
																lampara.setColor(fieldColor.getText());
																lampara.setTamanio(fieldTamanioL.getText());
																lampara.setMarca(fieldMarca.getText());
																lampara.setModelo(fieldModelo.getText());
																lampara.setTipoAutoparte("lampara");
																lampara.setCantDisponible(Integer.valueOf(fieldCantidad.getText()));
																lampara.setCosto(Double.valueOf(fieldCosto.getText()));
																
																lamparaBo.insertarLampara(lampara);
																//sqlinserts.insertarAutoparte(lampara.getAutoparteID(),lampara.getTipoAutoparte(),lampara.getMarca(),lampara.getModelo(),lampara.getCosto(),lampara.getCantDisponible());
																autoparteBo.insertarAutoparte(lampara);
																autopartesG.add(lampara);
																
																System.out.print("\n[MENU SISTEMA]LAMPARA INGRESADA CORRECTAMENTE");
																MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																menuSistema.mostrar();
															}else{
																		System.out.print("[MENU SISTEMA]validacion: DATO INGRESADO INCORRECTO O FALTAN DATOS");
																		try {
																			MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																			menuSistema.mostrar();	
																		} catch (MiException e) {
																			throw e;
																		}catch(SQLException e)
																		{
																			throw new MiException("[MENU SISTEMA]IF VALIDAR DATOS INGRESADOS - CREAR AUTOPARTE EXCEPTION :"+e);
																		}
																}
															break;
														
														default:
															System.out.print("[MENU SISTEMA] DATO INGRESADO INCORRECTO");												
															break;
													}
								            	} catch (MiException e) {
													throw e;
												}catch(SQLException e)
												{
													throw new MiException("[MENU SISTEMA] SUBMIT - CREAR AUTOPARTE EXCEPTION :"+e);
												}
								            	
								            }
											});
											panelEnd.add(btSubmit);
											
							            	///####################### VOLVER #############################
											JButton btVolver=new JButton();
											btVolver.setText("VOLVER");								
											btVolver.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {
								            	 
												try {
													MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
													menuSistema.mostrar();	
												} catch (MiException e) {
													throw e;
												}catch(SQLException e)
												{
													throw new MiException("[MENU SISTEMA] VOLVER - CREAR AUTOPARTE EXCEPTION :"+e);
												}			            	
								            }
											});
											panelEnd.add(btVolver);
											///######################## SALIR ##########################
											JButton btSalir=new JButton();
											btSalir.setText("SALIR");								
											btSalir.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {
								            	System.exit(0);					            	
								            }
											});
											panelEnd.add(btSalir);
						
											panel.add(panelTitulo);
											panel.add(panelGrid);
											panel.add(panelResto);
											panel.add(panelEnd);
																	
											getFrameSistema().setContentPane(panel);
										
					            	}
			            		}catch(MiException e)
								{
									throw e;
								}catch(Exception e)
								{
									throw new MiException("[MENU SISTEMA] CARGAR AUTOPARTE Exception : "+e);
								}				            	
			            	
			            }
						});
						panelEnd.add(btSubmit);
						
						///####################### VOLVER #############################
						JButton btVolver=new JButton();
						btVolver.setText("VOLVER");								
						btVolver.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	 
							try {
								MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
								menuSistema.mostrar();	
							} catch (MiException e) {
								throw e;
							}catch(SQLException e)
							{
								throw new MiException("[MENU SISTEMA] VOLVER - CREAR AUTOPARTE EXCEPTION :"+e);
							}
											            	
			            }
						});
						panelEnd.add(btVolver);
						///######################## SALIR ##########################
						JButton btSalir=new JButton();
						btSalir.setText("SALIR");								
						btSalir.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	System.exit(0);					            	
			            }
						});
						panelEnd.add(btSalir);
	
						panel.add(panelTitulo);
						panel.add(panelGridLabel);
						panel.add(panelGrid);
						panel.add(panelResto);
						panel.add(panelEnd);
												
						getFrameSistema().setContentPane(panel);
		            }catch (MiException e) {
						throw e;
					}			            
		            catch (Exception e) {
						throw new MiException("[MENU SISTEMA] SALIR - CREAR AUTOPARTE EXCEPTION : "+e);												
					}		            					            	
	            }
				});			
				panelOperaciones.add(btCargaAutoparte);

	//5-			//########################### MODIFICACION AUTOPARTE ###########################
				JButton btModifAutoparte=new JButton();
				btModifAutoparte.setText("MODIFICAR AUTOPARTES");								
				btModifAutoparte.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            		try
		            	{
			            	getFrameSistema().getContentPane().removeAll();
		            		getFrameSistema().getContentPane().repaint();
		    				
		            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);		    				
		    				JPanel panelGridLabel=panelGestor.crearPanelGrid(new GridLayout(3, 2), Definiciones.line_blackline,null,null,null);
		    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);						
		    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE AUTOPARTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
					        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
					        
					        final JTextField fieldTipoAutoparte=new JTextField();
							JPanel panelGridOpcion=panelGestor.crearPanelOpcion(new String("TIPO AUTOPARTE"),fieldTipoAutoparte);
													
							panelGridLabel=panelGestor.labelsTiposAutopartes(panelGridLabel);
							
							
							///################# SUBMIT ##########################
							JButton btSubmit=new JButton();
							btSubmit.setText("SUBMIT");								
							btSubmit.addMouseListener(new MouseAdapter() {												
				            @Override
				            public void mouseReleased(MouseEvent evt) {
				            	
				            	try
				            	{
				            		if(fieldTipoAutoparte.getText().equals("")||Integer.valueOf(fieldTipoAutoparte.getText())>3||Integer.valueOf(fieldTipoAutoparte.getText())<1)	
				            		{
					            		System.out.print("\n\n[MENU SISTEMA] NO EXISTE ESE TIPO DE AUTOPARTE \n"); //ENEL FUTURO AGREGAR MENU EMERGENTE
										try {
											MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
											menuSistema.mostrar();	
										} catch (MiException e) {
											throw e;
										}catch(SQLException e)
										{
											throw new MiException("\n[MENU SISTEMA] CARGA AUTOPARTE EXCEPTION :"+e);
										}	
				            		}else
						            {
					            		getFrameSistema().getContentPane().removeAll();
					            		getFrameSistema().getContentPane().repaint();
					    				
					            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
					    				JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,null,null,null);
					    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);						
					    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE AUTOPARTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
								        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
								        JPanel panelGridLabel=new JPanel();
								        
								    	JPanel panelTituloTipo = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.black,new Dimension(400,50));
										JLabel labelTitulo=panelGestor.crearTitulo("", JLabel.CENTER,Color.white, new Font(Font.SERIF,Font.BOLD,15));
								        
										final JTextField fieldId=new JTextField();
										fieldId.setBorder(Definiciones.line_blackline);	
										JLabel labelId=new JLabel("INGRESE ID",JLabel.CENTER);
										labelId.setBorder(Definiciones.line_blackline);						
										panelGrid.add(labelId);
										panelGrid.add(fieldId);
											
											switch(Integer.valueOf(fieldTipoAutoparte.getText()))
											{
												case Definiciones.FILTRO_INDICE:
													labelId.setText("INGRESE ID FILTRO");
													labelTitulo.setText("MODIFICAR FILTRO");
													panelTituloTipo.add("Center",labelTitulo);
																																		
													panelGridLabel=panelGestor.cargarFiltrosEnPanel(autopartesG);
													
																
													break;
												case Definiciones.ACEITE_INDICE:
													labelId.setText("INGRESE ID ACEITE");
													labelTitulo.setText("MODIFICAR ACEITE");
													panelTituloTipo.add("Center",labelTitulo);
																																		
													panelGridLabel=panelGestor.cargarAceitesEnPanel(autopartesG);
													
																
													break;
												case Definiciones.LAMPARA_INDICE:
													labelId.setText("INGRESE ID LAMPARA");
													labelTitulo.setText("MODIFICAR LAMPARA");
													panelTituloTipo.add("Center",labelTitulo);
																																		
													panelGridLabel=panelGestor.cargarLamparasEnPanel(autopartesG);
													
																
													break;
												default:
													break;
											}
											///################# SUBMIT ##########################
											JButton btSubmit=new JButton();
											btSubmit.setText("SUBMIT");								
											btSubmit.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {
								            	boolean okId=false;
								            	if(fieldId.getText().equals(""))
								            	{
								            		okId=false;
								            	}else
								            	{
								            		switch(Integer.valueOf(fieldTipoAutoparte.getText()))
													{
														case Definiciones.FILTRO_INDICE:
															setFiltroAux(metgral.obtenerFiltroEnListaByID(autopartesG,Integer.valueOf(fieldId.getText())));
															if(getFiltroAux()!=null)
																okId=true;
															else
																okId=false;
															
														break;
														case Definiciones.ACEITE_INDICE:
															setAceiteAux(aceiteAux=metgral.obtenerAceiteEnListaByID(autopartesG,Integer.valueOf( fieldId.getText())));
															if(getAceiteAux()!=null)
																okId=true;
																else
																	okId=false;
														break;
														case Definiciones.LAMPARA_INDICE:
															setLamparaAux(metgral.obtenerLamparaEnListaByID(autopartesG, Integer.valueOf(fieldId.getText())));
															if(getLamparaAux()!=null)
																okId=true;
																else
																	okId=false;
														break;
														default:
																
															break;
													}
								            	}
										        
								            	
												if(okId)
												{													
									            	getFrameSistema().getContentPane().removeAll();
								            		getFrameSistema().getContentPane().repaint();
								    				
								            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
								    				JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(7, 2), Definiciones.line_blackline,null,null,null);
								    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);
											        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
											        
											    	JPanel panelTitulo = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.black,new Dimension(400,50));
													final JLabel labelTitulo=panelGestor.crearTitulo("", JLabel.CENTER,Color.white, new Font(Font.SERIF,Font.BOLD,15));
											        panelTitulo.add(labelTitulo);
											        
											        final JTextField fieldMarca=new JTextField();
													fieldMarca.setBorder(Definiciones.line_blackline);						
													final JTextField fieldModelo=new JTextField();
													fieldModelo.setBorder(Definiciones.line_blackline);
													final JTextField fieldCantidad=new JTextField();
													fieldCantidad.setBorder(Definiciones.line_blackline);
													final JTextField fieldCosto=new JTextField();
													fieldCosto.setBorder(Definiciones.line_blackline);
													
													final JTextField fieldTipoMaterial=new JTextField();
													final JTextField fieldTamanio=new JTextField();
													
													final JTextField fieldCantLitros=new JTextField();
													final JTextField fieldTipoAceite=new JTextField();
													
													final JTextField fieldColor=new JTextField();
													final JTextField fieldTamanioL=new JTextField();
												
													JLabel labelMarca=new JLabel("",JLabel.CENTER);
													labelMarca.setBorder(Definiciones.line_blackline);
													JLabel labelModelo=new JLabel("",JLabel.CENTER);
													labelModelo.setBorder(Definiciones.line_blackline);
													JLabel labelCantidad=new JLabel("",JLabel.CENTER);
													labelCantidad.setBorder(Definiciones.line_blackline);
													JLabel labelCosto=new JLabel("",JLabel.CENTER);
													labelCosto.setBorder(Definiciones.line_blackline);
													
													panelGrid.add(labelMarca);
													panelGrid.add(fieldMarca);										
													panelGrid.add(labelModelo);
													panelGrid.add(fieldModelo);
													panelGrid.add(labelCantidad);
													panelGrid.add(fieldCantidad);
													panelGrid.add(labelCosto);
													panelGrid.add(fieldCosto);
													
											        switch(Integer.valueOf(fieldTipoAutoparte.getText()))
													{
														case Definiciones.FILTRO_INDICE:
															labelTitulo.setText("MODIFICAR FILTRO");
															panelTitulo.add("Center",labelTitulo);
															
															fieldTipoMaterial.setBorder(Definiciones.line_blackline);										
															fieldTamanio.setBorder(Definiciones.line_blackline);										
															
															JLabel labelTipoMaterial=new JLabel("MODIFICAR TIPO MATERIAL ("+getFiltroAux().getMaterial()+")",JLabel.CENTER);
															labelTipoMaterial.setBorder(Definiciones.line_blackline);									
															JLabel labelTamanio=new JLabel("MODIFICAR TAMA�O ("+getFiltroAux().getTamanio()+")",JLabel.CENTER);	//PODRIA COLOCARLO EN EL FIELD TAMBIEN, POR SI NO SE DESEA MODIFICAR...
															labelTamanio.setBorder(Definiciones.line_blackline);										
															labelMarca.setText("MODIFICAR MARCA ("+getFiltroAux().getMarca()+")");
															labelModelo.setText("MODIFICAR MODELO ("+getFiltroAux().getModelo()+")");
															labelCantidad.setText("MODIFICAR CANTIDAD ("+getFiltroAux().getCantDisponible()+")");
															labelCosto.setText("MODIFICAR COSTO ("+getFiltroAux().getCosto()+")");
															
															panelGrid.add(labelTipoMaterial);
															panelGrid.add(fieldTipoMaterial);
															panelGrid.add(labelTamanio);
															panelGrid.add(fieldTamanio);
												
														break;
														case Definiciones.ACEITE_INDICE:
															labelTitulo.setText("CARGA DE ACEITE");
															panelTitulo.add("Center",labelTitulo);
															
															fieldCantLitros.setBorder(Definiciones.line_blackline);										
															fieldTipoAceite.setBorder(Definiciones.line_blackline);										
															
															JLabel labelCantLitros=new JLabel("MODIFICAR CANT LITROS ("+getAceiteAux().getCantidadlitros()+")",JLabel.CENTER);
															labelCantLitros.setBorder(Definiciones.line_blackline);									
															JLabel labelTamanioA=new JLabel("MODIFICAR TIPO DE ACEITE ("+getAceiteAux().getTipoAceite()+")",JLabel.CENTER);
															labelTamanioA.setBorder(Definiciones.line_blackline);										
															
															labelMarca.setText("MODIFICAR MARCA ("+getAceiteAux().getMarca()+")");
															labelModelo.setText("MODIFICAR MODELO ("+getAceiteAux().getModelo()+")");
															labelCantidad.setText("MODIFICAR CANTIDAD ("+getAceiteAux().getCantDisponible()+")");
															labelCosto.setText("MODIFICAR COSTO ("+getAceiteAux().getCosto()+")");
															
															panelGrid.add(labelCantLitros);
															panelGrid.add(fieldCantLitros);
															panelGrid.add(labelTamanioA);
															panelGrid.add(fieldTipoAceite);
														
															break;
														case Definiciones.LAMPARA_INDICE:
															labelTitulo.setText("CARGA DE LAMPARA");
															panelTitulo.add("Center",labelTitulo);
															
															fieldColor.setBorder(Definiciones.line_blackline);										
															fieldTamanioL.setBorder(Definiciones.line_blackline);										
															
															JLabel labelColor=new JLabel("MODIFICAR COLOR ("+getLamparaAux().getColor()+")",JLabel.CENTER);
															labelColor.setBorder(Definiciones.line_blackline);									
															JLabel labelTamanioL=new JLabel("MODIFICAR TAMA�O ("+getLamparaAux().getTamanio()+")",JLabel.CENTER);
															labelTamanioL.setBorder(Definiciones.line_blackline);										
															
															labelMarca.setText("MODIFICAR MARCA ("+getLamparaAux().getMarca()+")");
															labelModelo.setText("MODIFICAR MODELO ("+getLamparaAux().getModelo()+")");
															labelCantidad.setText("MODIFICAR CANTIDAD ("+getLamparaAux().getCantDisponible()+")");
															labelCosto.setText("MODIFICAR COSTO ("+getLamparaAux().getCosto()+")");
															
															panelGrid.add(labelColor);
															panelGrid.add(fieldColor);
															panelGrid.add(labelTamanioL);
															panelGrid.add(fieldTamanioL);
																													
															break;
															default:																
															break;
														}
														
														///################# SUBMIT ##########################
														JButton btSubmit=new JButton();
														btSubmit.setText("SUBMIT");								
														btSubmit.addMouseListener(new MouseAdapter() {												
											            @Override
											            public void mouseReleased(MouseEvent evt) {
														
											            	
											            	switch(Integer.valueOf(fieldTipoAutoparte.getText()))
															{
																case Definiciones.FILTRO_INDICE:
																	if(!fieldCantidad.getText().equals("")&&!fieldMarca.getText().equals("")&&!fieldModelo.getText().equals("")
																		&&!fieldTipoMaterial.getText().equals("")&&!fieldTamanio.getText().equals(""))
																	{
																		labelTitulo.setText("MODIFICAR FILTRO");																	
																			filtroAux.setMaterial(fieldTipoMaterial.getText());
																			filtroAux.setTamanio(fieldTamanio.getText());
																			
																			filtroAux.setMarca(fieldMarca.getText());
																			filtroAux.setModelo(fieldModelo.getText());
																			//filtro.setTipoAutoparte("filtro");
																			filtroAux.setCantDisponible(Integer.valueOf(fieldCantidad.getText()));
																			filtroAux.setCosto(Double.valueOf(fieldCosto.getText()));
																			
																			try {
																				filtroBo.updateFiltro(filtroAux);
																				autoparteBo.updateAutoparte(filtroAux);
																				
																				autopartesG=metgral.eliminarAutoparteDeLista(autopartesG,getFiltroAux());
																				//autopartesG.remove(index);
																				autopartesG.add(filtroAux);
																							
																			} catch (MiException e) {
																				throw e;
																			} 
																			
																	}else
																	{
																		System.out.print("\n[MENU SISTEMA]FALTAN DATOS O DATOS VACIOS");		//DEBUG
																		
																	}
																	
																break;
																case Definiciones.ACEITE_INDICE:
																	if(!fieldCantidad.getText().equals("")&&!fieldMarca.getText().equals("")&&!fieldModelo.getText().equals("")
																		&&!fieldCantLitros.getText().equals("")&&!fieldTipoAceite.getText().equals(""))
																	{
																		labelTitulo.setText("MODIFICAR ACEITE");
																
																		
																					aceiteAux.setCantidadlitros(Integer.valueOf(fieldCantLitros.getText()));
																					aceiteAux.setTipoAceite(fieldTipoAceite.getText());
																					
																					aceiteAux.setMarca(fieldMarca.getText());
																					aceiteAux.setModelo(fieldModelo.getText());
																					aceiteAux.setCantDisponible(Integer.valueOf(fieldCantidad.getText()));
																					aceiteAux.setCosto(Double.valueOf(fieldCosto.getText()));
																					
																					try{
																						aceiteBo.updateAceite(aceiteAux);
																						autoparteBo.updateAutoparte(aceiteAux);
																						//autopartesG.remove(index);
																						autopartesG=metgral.eliminarAutoparteDeLista(autopartesG,getAceiteAux());
																						autopartesG.add(aceiteAux);
																					} catch (MiException e) {
																						throw e;
																					} 
																					
																					break;
																	}else
																	{
																		System.out.print("\n[MENU SISTEMA]FALTAN DATOS O DATOS VACIOS");		//DEBUG
																	
																	}
																	break;
																	
																case Definiciones.LAMPARA_INDICE:
																	if(!fieldCantidad.getText().equals("")&&!fieldMarca.getText().equals("")&&!fieldModelo.getText().equals("")
																			&&!fieldColor.getText().equals("")&&!fieldTamanioL.getText().equals(""))
																	{
																			labelTitulo.setText("MODIFICAR LAMPARA");
																			
																				lamparaAux.setColor(fieldColor.getText());
																				lamparaAux.setTamanio(fieldTamanioL.getText());
																				
																				lamparaAux.setMarca(fieldMarca.getText());
																				lamparaAux.setModelo(fieldModelo.getText());									
																				lamparaAux.setCantDisponible(Integer.valueOf(fieldCantidad.getText()));
																				lamparaAux.setCosto(Double.valueOf(fieldCosto.getText()));
																				try{
																					lamparaBo.updateLampara(lamparaAux);
																					autoparteBo.updateAutoparte(lamparaAux);
																					autopartesG=metgral.eliminarAutoparteDeLista(autopartesG,getLamparaAux());																					
																					//autopartesG.remove(index);
																					autopartesG.add(lamparaAux);
																				} catch (MiException e) {
																					throw e;
																				} 
																				break;
																					
																				
																	}else
																	{
																		System.out.print("\n[MENU SISTEMA]FALTAN DATOS O DATOS VACIOS");		//DEBUG
																	
																	}
																	break;								
																default:
																	
																	
																	break;
															}
											            	
											            	try {
																MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																menuSistema.mostrar();	
															} catch (MiException e) {
																throw e;
															}catch(SQLException e)
															{
																throw new MiException("\n[MENU SISTEMA] MODIFICAR AUTOPARTE EXCEPTION :"+e);
															}
											            }});
														panelEnd.add(btSubmit);
														
														///####################### VOLVER #############################
														JButton btVolver=new JButton();
														btVolver.setText("VOLVER");								
														btVolver.addMouseListener(new MouseAdapter() {												
											            @Override
											            public void mouseReleased(MouseEvent evt) {
											            	 
															try {
																MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																menuSistema.mostrar();	
															} catch (MiException e) {
																throw e;
															}catch(SQLException e)
															{
																throw new MiException("\n[MENU SISTEMA] VOLVER - MODIFICAR AUTOPARTE EXCEPTION :"+e);
															}
																			            	
											            }
														});
														panelEnd.add(btVolver);
														///######################## SALIR ##########################
														JButton btSalir=new JButton();
														btSalir.setText("SALIR");								
														btSalir.addMouseListener(new MouseAdapter() {												
											            @Override
											            public void mouseReleased(MouseEvent evt) {
											            	System.exit(0);					            	
											            }
														});
														panelEnd.add(btSalir);
														
														panel.add(panelTitulo);
														panel.add(panelGrid);
														panel.add(panelResto);
														panel.add(panelEnd);
														getFrameSistema().setContentPane(panel);
												}else
												{
													System.out.print("\n[MENU SISTEMA] ID DE LA AUTOPARTE INCORRECTO");		//AGREGAR MENU EMERGENTE
													try {
														MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
														menuSistema.mostrar();	
													} catch (MiException e) {
														throw e;
													}catch(SQLException e)
													{
														throw new MiException("\n[MENU SISTEMA] VOLVER - MODIFICAR AUTOPARTE EXCEPTION :"+e);
													}
												}
								            	
								            }});
											panelEnd.add(btSubmit);
											
											///####################### VOLVER #############################
											JButton btVolver=new JButton();
											btVolver.setText("VOLVER");								
											btVolver.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {
								            	 
												try {
													MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
													menuSistema.mostrar();	
												} catch (MiException e) {
													throw e;
												}catch(SQLException e)
												{
													throw new MiException("\n[MENU SISTEMA] VOLVER - MODIFICAR AUTOPARTE EXCEPTION :"+e);
												}
																            	
								            }
											});
											panelEnd.add(btVolver);
											///######################## SALIR ##########################
											JButton btSalir=new JButton();
											btSalir.setText("SALIR");								
											btSalir.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {
								            	System.exit(0);					            	
								            }
											});
											panelEnd.add(btSalir);
											
											panel.add(panelTitulo);
											panel.add(panelTituloTipo);
											panel.add(panelGridLabel);
											panel.add(panelGrid);
											panel.add(panelResto);
											panel.add(panelEnd);
											getFrameSistema().setContentPane(panel);
											
						            }	
										
				            		}catch(MiException e)
									{
										throw e;
									}catch(Exception e)
									{
										throw new MiException("\n[MENU SISTEMA] MODIFICAR AUTOPARTE Exception : "+e);
									}	
				            }});							
							panelEnd.add(btSubmit);
							
							///####################### VOLVER #############################
							JButton btVolver=new JButton();
							btVolver.setText("VOLVER");								
							btVolver.addMouseListener(new MouseAdapter() {												
				            @Override
				            public void mouseReleased(MouseEvent evt) {
				            	 
								try {
									MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
									menuSistema.mostrar();	
								} catch (MiException e) {
									throw e;
								}catch(SQLException e)
								{
									throw new MiException("\n[MENU SISTEMA] VOLVER - MODIFICAR AUTOPARTE EXCEPTION :"+e);
								}
												            	
				            }
							});
							panelEnd.add(btVolver);
							///######################## SALIR ##########################
							JButton btSalir=new JButton();
							btSalir.setText("SALIR");								
							btSalir.addMouseListener(new MouseAdapter() {												
				            @Override
				            public void mouseReleased(MouseEvent evt) {
				            	System.exit(0);					            	
				            }
							});
							panelEnd.add(btSalir);
		
							panel.add(panelTitulo);
							panel.add(panelGridLabel);
							panel.add(panelGridOpcion);
							panel.add(panelResto);
							panel.add(panelEnd);
							
							getFrameSistema().setContentPane(panel);						
							
					}
					catch(MiException e)
					{
						throw e;
					}catch(Exception e)
					{
						throw new MiException("\n[MENU SISTEMA]MODIFICAR AUTOPARTE Exception : "+e);
					}				            	
	            }
				});			
				panelOperaciones.add(btModifAutoparte);
				
	//6-			//########################### BAJA AUTOPARTE ###########################
				JButton btBajaAutoparte=new JButton();
				btBajaAutoparte.setText("BAJA AUTOPARTES");								
				btBajaAutoparte.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	try
					{	
	            		getFrameSistema().getContentPane().removeAll();					
            			getFrameSistema().getContentPane().repaint();
    				
	            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
	    				JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,null,null,null);
	    				JPanel panelGridLabel=panelGestor.crearPanelGrid(new GridLayout(3, 2), Definiciones.line_blackline,null,null,null);
	    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);						
	    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA DE AUTOPARTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
				        
				        
						final JTextField fieldTipoAutoparte=new JTextField();
						fieldTipoAutoparte.setBorder(Definiciones.line_blackline);	
						JLabel labelTipoAutoparte=new JLabel("TIPO AUTOPARTE",JLabel.CENTER);
						labelTipoAutoparte.setBorder(Definiciones.line_blackline);						
						panelGrid.add(labelTipoAutoparte);
						panelGrid.add(fieldTipoAutoparte);
												
						panelGridLabel=panelGestor.labelsTiposAutopartes(panelGridLabel);
						
						
						///################# SUBMIT ##########################
						JButton btSubmit=new JButton();
						btSubmit.setText("SUBMIT");								
						btSubmit.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	
			            	try
			            	{
			            		if(fieldTipoAutoparte.getText().equals("")||Integer.valueOf(fieldTipoAutoparte.getText())>3||Integer.valueOf(fieldTipoAutoparte.getText())<1)	
			            		{
				            		System.out.print("\n\n[MENU SISTEMA] NO EXISTE ESE TIPO DE AUTOPARTE \n"); //ENEL FUTURO AGREGAR MENU EMERGENTE
									try {
										MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
										menuSistema.mostrar();	
									} catch (MiException e) {
										throw e;
									}catch(SQLException e)
									{
										throw new MiException("\n[MENU SISTEMA] CARGA AUTOPARTE EXCEPTION :"+e);
									}	
			            		}else
					            {
				            		getFrameSistema().getContentPane().removeAll();
				            		getFrameSistema().getContentPane().repaint();
				    				
				            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
				    				JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,null,null,null);
				    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);						
				    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA DE AUTOPARTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
							        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
							        JPanel panelGridLabel=new JPanel();
							        
							    	JPanel panelTituloTipo = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.black,new Dimension(400,50));
									JLabel labelTitulo=panelGestor.crearTitulo("", JLabel.CENTER,Color.white, new Font(Font.SERIF,Font.BOLD,15));
							        
									final JTextField fieldId=new JTextField();
									fieldId.setBorder(Definiciones.line_blackline);	
									JLabel labelId=new JLabel("INGRESE ID",JLabel.CENTER);
									labelId.setBorder(Definiciones.line_blackline);						
									panelGrid.add(labelId);
									panelGrid.add(fieldId);
										
									switch(Integer.valueOf(fieldTipoAutoparte.getText()))
									{
										case Definiciones.FILTRO_INDICE:
											labelId.setText("INGRESE ID FILTRO");
											labelTitulo.setText("BAJA FILTRO");
											panelTituloTipo.add("Center",labelTitulo);
																																
											panelGridLabel=panelGestor.cargarFiltrosEnPanel(autopartesG);
											
														
											break;
										case Definiciones.ACEITE_INDICE:
											labelId.setText("INGRESE ID ACEITE");
											labelTitulo.setText("MODIFICAR ACEITE");
											panelTituloTipo.add("Center",labelTitulo);
																																
											panelGridLabel=panelGestor.cargarAceitesEnPanel(autopartesG);
											
														
											break;
										case Definiciones.LAMPARA_INDICE:
											labelId.setText("INGRESE ID LAMPARA");
											labelTitulo.setText("MODIFICAR LAMPARA");
											panelTituloTipo.add("Center",labelTitulo);
																																
											panelGridLabel=panelGestor.cargarLamparasEnPanel(autopartesG);
											
														
											break;
										default:
											break;
									}
									///################# SUBMIT ##########################
									JButton btSubmit=new JButton();
									btSubmit.setText("SUBMIT");								
									btSubmit.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	boolean okId=false;
						            	if(fieldId.getText().equals(""))
						            	{
						            		okId=false;
						            	}else
						            	{
						            		switch(Integer.valueOf(fieldTipoAutoparte.getText()))
											{
												case Definiciones.FILTRO_INDICE:
													setFiltroAux(metgral.obtenerFiltroEnListaByID(autopartesG,Integer.valueOf(fieldId.getText())));
													if(getFiltroAux()!=null)
														okId=true;
													else
														okId=false;
													
												break;
												case Definiciones.ACEITE_INDICE:
													setAceiteAux(aceiteAux=metgral.obtenerAceiteEnListaByID(autopartesG,Integer.valueOf( fieldId.getText())));
													if(getAceiteAux()!=null)
														okId=true;
														else
															okId=false;
												break;
												case Definiciones.LAMPARA_INDICE:
													setLamparaAux(metgral.obtenerLamparaEnListaByID(autopartesG, Integer.valueOf(fieldId.getText())));
													if(getLamparaAux()!=null)
														okId=true;
														else
															okId=false;
												break;
												default:
														
													break;
											}
						            	}
										if(okId)
										{		
											boolean elimino=false;
											switch(Integer.valueOf(fieldTipoAutoparte.getText()))
											{
												case Definiciones.FILTRO_INDICE:
													try {
														if(autoparteBo.eliminarAutoparte(getFiltroAux()))
														{	
															elimino=true;
														}else{
															elimino=false;
														}												
														if(filtroBo.eliminarFiltro(getFiltroAux()))
														{
															elimino=true;
														}else{
															elimino=false;
														}
														if(elimino)
														{
															System.out.print("\nFILTRO ELIMINADO CORRECTAMENTE");	//DEBUG														
															metgral.eliminarAutoparteDeLista(autopartesG, getFiltroAux());														
														}
														else
														{
															System.out.print("\nFALLO ELIMINACION FILTRO");	//DEBUG														
														}
													} catch (MiException e) {
														throw e;													
													} catch (Exception e) {
														throw new MiException("[MENU SISTEMA] FALLO ELIMINACION DE AUTOPARTE: "+ e);													
													}
													
												break;
												case Definiciones.ACEITE_INDICE:
													try {
														if(autoparteBo.eliminarAutoparte(getAceiteAux()))
														{	
															elimino=true;
														}else{
															elimino=false;
														}												
														if(aceiteBo.eliminarAceite(getAceiteAux()))
														{
															elimino=true;
														}else{
															elimino=false;
														}
														if(elimino)
														{
															System.out.print("\nACEITE ELIMINADO CORRECTAMENTE");	//DEBUG														
															metgral.eliminarAutoparteDeLista(autopartesG, getAceiteAux());														
														}
														else
														{
															System.out.print("\nFALLO ELIMINACION ACEITE");	//DEBUG															
														}
													} catch (MiException e) {
														throw e;													
													} catch (Exception e) {
														throw new MiException("[MENU SISTEMA] FALLO ELIMINACION DE AUTOPARTE: "+ e);													
													}
												break;
												case Definiciones.LAMPARA_INDICE:
													try {
														if(autoparteBo.eliminarAutoparte(getLamparaAux()))
														{	
															elimino=true;
														}else{
															elimino=false;
														}												
														if(lamparaBo.eliminarLampara(getLamparaAux()))
														{
															elimino=true;
														}else{
															elimino=false;
														}
														if(elimino)
														{
															System.out.print("\nLAMPARA ELIMINADA CORRECTAMENTE");	//DEBUG														
															metgral.eliminarAutoparteDeLista(autopartesG, getLamparaAux());														
														}
														else
														{
															System.out.print("\nFALLO ELIMINACION LAMPARA");	//DEBUG															
														}
													} catch (MiException e) {
														throw e;													
													} catch (Exception e) {
														throw new MiException("[MENU SISTEMA] FALLO ELIMINACION DE AUTOPARTE: "+ e);													
													}
												break;
												default:
													break;
											}											
											try {
												MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
												menuSistema.mostrar();	
											} catch (MiException e) {
												throw e;
											}catch(SQLException e)
											{
												throw new MiException("\n[MENU SISTEMA] VOLVER - BAJA AUTOPARTE EXCEPTION :"+e);
											}
										}else
										{
											System.out.print("\n[MENU SISTEMA] ID DE LA AUTOPARTE INCORRECTO");		//AGREGAR MENU EMERGENTE
											try {
												MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
												menuSistema.mostrar();	
											} catch (MiException e) {
												throw e;
											}catch(SQLException e)
											{
												throw new MiException("\n[MENU SISTEMA] VOLVER - BAJA AUTOPARTE EXCEPTION :"+e);
											}
										}
						            	
						              }});
									  panelEnd.add(btSubmit);
									
										///####################### VOLVER #############################
										JButton btVolver=new JButton();
										btVolver.setText("VOLVER");								
										btVolver.addMouseListener(new MouseAdapter() {												
							            @Override
							            public void mouseReleased(MouseEvent evt) {
							            	 
											try {
												MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
												menuSistema.mostrar();	
											} catch (MiException e) {
												throw e;
											}catch(SQLException e)
											{
												throw new MiException("\n[MENU SISTEMA] VOLVER - BAJA AUTOPARTE EXCEPTION :"+e);
											}
															            	
							            }
										});
										panelEnd.add(btVolver);
										///######################## SALIR ##########################
										JButton btSalir=new JButton();
										btSalir.setText("SALIR");								
										btSalir.addMouseListener(new MouseAdapter() {												
							            @Override
							            public void mouseReleased(MouseEvent evt) {
							            	System.exit(0);					            	
							            }
										});
										panelEnd.add(btSalir);
										
										panel.add(panelTitulo);
										panel.add(panelTituloTipo);
										panel.add(panelGridLabel);
										panel.add(panelGrid);
										panel.add(panelResto);
										panel.add(panelEnd);
										getFrameSistema().setContentPane(panel);
									}
			            		}catch(MiException e)
								{
									throw e;
								}catch(Exception e)
								{
									throw new MiException("\n[MENU SISTEMA] BAJA AUTOPARTE Exception : "+e);
								}	
			            }});							
						panelEnd.add(btSubmit);
						
						///####################### VOLVER #############################
						JButton btVolver=new JButton();
						btVolver.setText("VOLVER");								
						btVolver.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	 
							try {
								MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
								menuSistema.mostrar();	
							} catch (MiException e) {
								throw e;
							}catch(SQLException e)
							{
								throw new MiException("\n[MENU SISTEMA] VOLVER - BAJA AUTOPARTE EXCEPTION :"+e);
							}
											            	
			            }
						});
						panelEnd.add(btVolver);
						
						///######################## SALIR ##########################
						JButton btSalir=new JButton();
						btSalir.setText("SALIR");								
						btSalir.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	System.exit(0);					            	
			            }
						});
						panelEnd.add(btSalir);
	
						panel.add(panelTitulo);
						panel.add(panelGridLabel);
						panel.add(panelGrid);
						panel.add(panelResto);
						panel.add(panelEnd);
						
						getFrameSistema().setContentPane(panel);						
						
					}
					catch(MiException e)
					{
						throw e;
					}catch(Exception e)
					{
						throw new MiException("\n[MENU SISTEMA]BAJA AUTOPARTE Exception : "+e);
					}
				}
	            });			
				panelOperaciones.add(btBajaAutoparte);
				
				
	//7-			//########################### CARGA REPARACION ###########################
				JButton btCargarReparacion=new JButton();
				btCargarReparacion.setText("CARGAR REPARACIONES");								
				btCargarReparacion.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	try
	            	{		            			
	            		getFrameSistema().getContentPane().removeAll();					
            			getFrameSistema().getContentPane().repaint();
    				
	            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);	    				
	    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);						
	    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CARGA DE REPARACION",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
						
						final JTextField fieldCliente=new JTextField();
						JPanel panelGridOpcion=panelGestor.crearPanelOpcion(new String("NOMBRE CLIENTE"),fieldCliente);
							
						///################# SUBMIT ##########################
						JButton btSubmit=new JButton();
						btSubmit.setText("SUBMIT");								
						btSubmit.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	
			            	if(fieldCliente.getText().equals(""))	
		            		{
			            		System.out.print("\n\n[MENU SISTEMA] NO COLOCO NINGUN CLIENTE\n"); //ENEL FUTURO AGREGAR MENU EMERGENTE
								try {
									MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
									menuSistema.mostrar();	
								} catch (MiException e) {
									throw e;
								}catch(SQLException e)
								{
									throw new MiException("\n[MENU SISTEMA] CARGA REPARACION EXCEPTION :"+e);
								}	
		            		}else
				            {
		            			final Cliente clientereparacion=metgral.buscarClientePorApodo(fieldCliente.getText(),clientesG);
								if(clientereparacion==null)
								{
									System.out.print("\n\nCLIENTE NO ENCONTRADO\n");		//DEBUG AGREGAR EMERGENTE								
									try {
										MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
										menuSistema.mostrar();	
									} catch (MiException e) {
										throw e;
									}catch(SQLException e)
									{
										throw new MiException("\n[MENU SISTEMA] CARGA REPARACION EXCEPTION :"+e);
									}	
								}else
								{		
										System.out.print("\n\nCLIENTE ENCONTRADO\n");		//DEBUG
										
										getFrameSistema().getContentPane().removeAll();
										getFrameSistema().getContentPane().repaint();
										
										JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
					    				JPanel panelOperaciones=panelGestor.crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,Color.cyan,null,null);
					    				JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);					    				
					    				JPanel panelLabel=panelGestor.crearPanelBox(BoxLayout.X_AXIS,Color.black,null);
					    				
					    				JLabel labelCliente=panelGestor.crearTitulo("CARGA DE REPARACION 'Cliente: "+clientereparacion.getNombre()+"' - 'Auto: "+clientereparacion.getAuto(),JLabel.CENTER,Color.white,new Font(Font.SERIF,Font.BOLD,18));
								        panelLabel.add(labelCliente);
								        
								        try{     
									        ///############### SUBMIT NO ####################################
									        JButton btSubmitNo=new JButton();
									        btSubmitNo.setText("NO");								
									        btSubmitNo.addMouseListener(new MouseAdapter() {												
									            @Override
									            public void mouseReleased(MouseEvent evt) {
									            	try
									            	{
										            	System.out.print("\nSE CANCELO CARGA REPARACION");		//DEBUG
														Thread.sleep(2000);
														MenuSistema menuSistema=new MenuSistema(getFrameSistema(),user);
														menuSistema.mostrar();
									            	}catch(MiException e)
													{
														throw e;
													}catch(Exception e)
													{
														throw new MiException("\n[MENU SISTEMA] SUBMIT NO CARGA REPARACION EXCEPTION :"+e);
													}
													
									            }
											});
									        panelOperaciones.add(btSubmitNo);
									        
									        
									        ///############### SUBMIT YES####################################
									        JButton btSubmitYes=new JButton();
									        btSubmitYes.setText("YES");								
									        btSubmitYes.addMouseListener(new MouseAdapter() {												
									            @Override
									            public void mouseReleased(MouseEvent evt) {								            	
									            	try {
										            	Reparacion nuevareparacion=new Reparacion();
														
													    Calendar c = new GregorianCalendar();
													    Date d1 = c.getTime(); 
														nuevareparacion.setFechainicio(d1.toString());
														nuevareparacion.setCliente(clientereparacion);
														nuevareparacion.setEntregado(0);
														
														nuevareparacion.setId(reparacionBo.buscarUltimaReparacionId());
														if(reparacionBo.insertarReparacionInicio(nuevareparacion))
														{
															reparacionesG.add(nuevareparacion);
															
															System.out.print("\nCARGA DE NUEVA REPARACION CORRECTA\n");
															
														}else{
															System.out.print("\nFALLO CARGA DE NUEVA REPARACION\n");
														}
														
														try {
															MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
															menuSistema.mostrar();	
														} catch (MiException e) {
															throw e;
														}catch(SQLException e)
														{
															throw new MiException("\n[MENU SISTEMA] VOLVER - CARGA REAPARACION EXCEPTION :"+e);
														}
													
													}catch (MiException e) {
														
														throw e;
													}catch (Exception e) {
														
														throw new MiException("\n[MENU SISTEMA] CARGA REPARACION Exception : "+e);
													}
									            }
											});
									        panelOperaciones.add(btSubmitYes);
									        
									        panel.add(panelLabel);
									        panel.add(panelResto);
									        panel.add(panelOperaciones);
									        getFrameSistema().setContentPane(panel);
	
										} catch (MiException e) {
											throw e;
										}catch(Exception e)
										{
											throw new MiException("\n[MENU SISTEMA] CARGA REPARACION EXCEPTION :"+e);
										}
								}
				            }
			            }});							
						panelEnd.add(btSubmit);
						
						///####################### VOLVER #############################
						JButton btVolver=new JButton();
						btVolver.setText("VOLVER");								
						btVolver.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	 
							try {
								MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
								menuSistema.mostrar();	
							} catch (MiException e) {
								throw e;
							}catch(SQLException e)
							{
								throw new MiException("\n[MENU SISTEMA] VOLVER - CARGA REAPARACION EXCEPTION :"+e);
							}
											            	
			            }
						});
						panelEnd.add(btVolver);
						
						///######################## SALIR ##########################
						JButton btSalir=new JButton();
						btSalir.setText("SALIR");								
						btSalir.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	System.exit(0);					            	
			            }
						});
						panelEnd.add(btSalir);
						
						panel.add(panelTitulo);						
						panel.add(panelGridOpcion);
						panel.add(panelResto);
						panel.add(panelEnd);
						
						getFrameSistema().setContentPane(panel);						
						
					}
					catch(MiException e)
					{
						throw e;
					}catch(Exception e)
					{
						throw new MiException("\n[MENU SISTEMA]CARGA REPARACION Exception : "+e);
					}            	
	            }
				});			
				panelOperaciones.add(btCargarReparacion);
				
		//8-		//########################### FINALIZAR REPARACION ###########################
				JButton btFinalizarReparacion=new JButton();
				btFinalizarReparacion.setText("FINALIZAR REPARACIONES");								
				btFinalizarReparacion.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	
	            	
	            	try
	            	{		            			
	            		getFrameSistema().getContentPane().removeAll();					
            			getFrameSistema().getContentPane().repaint();
    				
	            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);	    				
	    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);						
	    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"FINALIZAR REPARACION",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
				        
						final JTextField fieldCliente=new JTextField();
						JPanel panelGridOpcion=panelGestor.crearPanelOpcion(new String("NOMBRE CLIENTE"),fieldCliente);
						
						///################# SUBMIT ##########################
						JButton btSubmit=new JButton();
						btSubmit.setText("SUBMIT");								
						btSubmit.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	
			            	try
			            	{
				            	if(fieldCliente.getText().equals(""))	
			            		{
				            		System.out.print("\n\n[MENU SISTEMA] NO COLOCO NINGUN CLIENTE\n"); //ENEL FUTURO AGREGAR MENU EMERGENTE
									try {
										MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
										menuSistema.mostrar();	
									} catch (MiException e) {
										throw e;
									}catch(SQLException e)
									{
										throw new MiException("\n[MENU SISTEMA] FINALIZAR REPARACION EXCEPTION :"+e);
									}	
			            		}else
					            {
			            			final Cliente cliente=metgral.buscarClientePorApodo(fieldCliente.getText(),clientesG);
									if(cliente==null)
									{
										System.out.print("\n\nCLIENTE NO ENCONTRADO\n");		//DEBUG AGREGAR EMERGENTE								
										try {
											MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
											menuSistema.mostrar();	
										} catch (MiException e) {
											throw e;
										}catch(SQLException e)
										{
											throw new MiException("\n[MENU SISTEMA] FINALIZAR REPARACION EXCEPTION :"+e);
										}	
									}else
									{											
										for(Reparacion reparaL:reparacionesG)
										{
											if(reparaL.getCliente().getId()==cliente.getId())
											{	
												if(reparaL.getEntregado()==1)												
												{
													System.out.print("\nCLIENTE NO POSEE NINGUNA REPARACION ABIERTA\n");	//DEBUG
													//SI NO COLOCO ESTO , eL CLIENTE PUEDE TENER MAS DE UNA REPARACION, PODRIA SER, PERO POR AHORA...
													try {
														MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
														menuSistema.mostrar();	
													} catch (MiException e) {
														throw e;
													}catch(SQLException e)
													{
														throw new MiException("\n[MENU SISTEMA] FINALIZAR REPARACION EXCEPTION :"+e);
													}	
												}
												else
												{	
													setReparacionAux(reparaL);
													Calendar c = new GregorianCalendar();
												    Date d1 = c.getTime(); 
													getReparacionAux().setFechaentrega(d1.toString());
													getReparacionAux().setEntregado(1);
													getReparacionAux().setCliente(cliente);			
													
													getFrameSistema().getContentPane().removeAll();					
							            			getFrameSistema().getContentPane().repaint();
							    				
								            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
								    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(2, 2), null, Color.blue,null,null);						
								    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"FINALIZAR REPARACION",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
											        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
											        
													
											        JPanel panelTituloFiltro = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"FILTROS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,11),Color.white);																																		
													JPanel panelGridLabelF=panelGestor.cargarFiltrosEnPanel(autopartesG);
													
													JPanel panelTituloAceite = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"ACEITES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,11),Color.white);															        																																		
													JPanel panelGridLabelA=panelGestor.cargarAceitesEnPanel(autopartesG);
													
													JPanel panelTituloLampara = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"LAMPARAS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,11),Color.white);															        																																		
													JPanel panelGridLabelL=panelGestor.cargarLamparasEnPanel(autopartesG);
													 
													 final JTextField fieldOpcion=new JTextField();
													JPanel panelGridOpcion=panelGestor.crearPanelOpcion(new String("INGRESE ID AUTOPARTE"),fieldOpcion);
													
													///################# AGREGAR ##########################
													JButton btAdd=new JButton();
													btAdd.setText("AGREGAR");								
													btAdd.addMouseListener(new MouseAdapter() {												
										            @Override
										            public void mouseReleased(MouseEvent evt) {
										            	
										            	try
										            	{
										            		if(fieldOpcion.getText().equals("")||fieldOpcion==null)	
										            		{
											            		System.out.print("\n\n[MENU SISTEMA] NO COLOCO NINGUNA OPCION\n"); //ENEL FUTURO AGREGAR MENU EMERGENTE
																try {
																	MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																	menuSistema.mostrar();	
																} catch (MiException e) {
																	throw e;
																}catch(SQLException e)
																{
																	throw new MiException("\n[MENU SISTEMA]AGREGAR AUTOPARTE- FINALIZAR REPARACION EXCEPTION :"+e);
																}	
										            		}else
												            {	
										            			for (Autoparte autoparte : autopartesG)
																{										
																	if(autoparte.getId()==Integer.valueOf(fieldOpcion.getText()))
																	{	
																		if(getReparacionAux().getCosto()!=0)
																		{
																			getReparacionAux().setCosto(getReparacionAux().getCosto()+autoparte.getCosto());
																		}
																		else
																		{
																			getReparacionAux().setCosto(autoparte.getCosto());
																		}
																		getReparacionAux().getAutopartes().add(autoparte);
																		fieldOpcion.setText("");
																		break;
																	}
																}
																
												            	System.out.print("\n\nSE AGREGO AUTOPARTE\n"); //PODRIA SER EMERGENTE	
															}										            		
										            	} catch (MiException e) {
															throw e;
														}catch(Exception e)
														{
															throw new MiException("\n[MENU SISTEMA] AGREGAR AUTOPARTE- FINALIZAR REPARACION EXCEPTION :"+e);
														}
										            }});
													panelEnd.add(btAdd);
													
													///################# SUBMIT ##########################
													JButton btSubmit=new JButton();
													btSubmit.setText("SUBMIT");								
													btSubmit.addMouseListener(new MouseAdapter() {												
										            @Override
										            public void mouseReleased(MouseEvent evt) {
										            	
										            	try
										            	{
										            		if(getReparacionAux().getAutopartes()==null||getReparacionAux().getAutopartes().isEmpty())	
										            		{
											            		System.out.print("\n\n[MENU SISTEMA]NO INGRESO AUTOPARTE\n"); //ENEL FUTURO AGREGAR MENU EMERGENTE
																try {
																	MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																	menuSistema.mostrar();	
																} catch (MiException e) {
																	throw e;
																}catch(SQLException e)
																{
																	throw new MiException("\n[MENU SISTEMA] SUBMIT - FINALIZAR REPARACION EXCEPTION :"+e);
																}	
										            		}else
												            {    
																//Podria validar si se cargo autopartes o no se cargo nada
										            			reparacionBo.insertarupdateReparacionFinal(getReparacionAux(),reparacionBo.buscarUltimaReparacionAutoparteId());
																int indice=0;
																for(Reparacion repara:reparacionesG)
																{
																	if(repara.getCliente().getId()==getReparacionAux().getCliente().getId())
																	{
																		break;
																	}
																	indice++;
																}
																reparacionesG.remove(indice);
																reparacionesG.add(getReparacionAux());	
																System.out.print("\n\nSE FINALIZO REPARACION\n");														
																
																try {
																	MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
																	menuSistema.mostrar();	
																} catch (MiException e) {
																	throw e;
																}catch(SQLException e)
																{
																	throw new MiException("\n[MENU SISTEMA] SUBMIT -  VOLVER - FINALIZAR REAPARACION EXCEPTION :"+e);
																}
												            }
															
										            	} catch (MiException e) {
															throw e;
														}catch(Exception e)
														{
															throw new MiException("\n[MENU SISTEMA] SUBMIT - FINALIZAR REPARACION EXCEPTION :"+e);
														}
													
													
										            }});
													panelEnd.add(btSubmit);
													
													///####################### VOLVER #############################
													JButton btVolver=new JButton();
													btVolver.setText("VOLVER");								
													btVolver.addMouseListener(new MouseAdapter() {												
													@Override
													public void mouseReleased(MouseEvent evt) {
														 
														try {
															MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
															menuSistema.mostrar();	
														} catch (MiException e) {
															throw e;
														}catch(SQLException e)
														{
															throw new MiException("\n[MENU SISTEMA] VOLVER - FINALIZAR REAPARACION EXCEPTION :"+e);
														}
																		            	
													}
													});
													panelEnd.add(btVolver);
													
													///######################## SALIR ##########################
													JButton btSalir=new JButton();
													btSalir.setText("SALIR");								
													btSalir.addMouseListener(new MouseAdapter() {												
													@Override
													public void mouseReleased(MouseEvent evt) {
														System.exit(0);					            	
													}
													});
													panelEnd.add(btSalir);
													
													panel.add(panelTitulo);		
													panel.add(panelTituloFiltro);
													panel.add(panelGridLabelF);
													panel.add(panelTituloAceite);
													panel.add(panelGridLabelA);
													panel.add(panelTituloLampara);
													panel.add(panelGridLabelL);
													
													panel.add(panelGridOpcion);
													
													panel.add(panelResto);
													panel.add(panelEnd);
													
													getFrameSistema().setContentPane(panel);				
																						
												}
											}
										}
									}
					            }
							} catch (MiException e) {
								throw e;
							}catch(Exception e)
							{
								throw new MiException("\n[MENU SISTEMA] FINALIZAR REPARACION EXCEPTION :"+e);
							}
					    
						}});							
						panelEnd.add(btSubmit);
						
						///####################### VOLVER #############################
						JButton btVolver=new JButton();
						btVolver.setText("VOLVER");								
						btVolver.addMouseListener(new MouseAdapter() {												
						@Override
						public void mouseReleased(MouseEvent evt) {
							 
							try {
								MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
								menuSistema.mostrar();	
							} catch (MiException e) {
								throw e;
							}catch(SQLException e)
							{
								throw new MiException("\n[MENU SISTEMA] VOLVER - FINALIZAR REAPARACION EXCEPTION :"+e);
							}
											            	
						}
						});
						panelEnd.add(btVolver);
						
						///######################## SALIR ##########################
						JButton btSalir=new JButton("SALIR");									
						btSalir.addMouseListener(new MouseAdapter() {												
						@Override
						public void mouseReleased(MouseEvent evt) {
							System.exit(0);					            	
						}
						});
						panelEnd.add(btSalir);
						
						panel.add(panelTitulo);						
						panel.add(panelGridOpcion);
						panel.add(panelResto);
						panel.add(panelEnd);
						
						getFrameSistema().setContentPane(panel);						
						
					}catch(MiException e)
					{
					throw e;
					}catch(Exception e)
					{
					throw new MiException("\n[MENU SISTEMA]FINALIZAR REPARACION Exception : "+e);
					}     
            			            	
	            }
				});			
				panelOperaciones.add(btFinalizarReparacion);
				
		//9-		//########################### MOSTRAR DATOS ###########################
				JButton btMostrarDatos=new JButton();
				btMostrarDatos.setText("MOSTRAR DATOS");								
				btMostrarDatos.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	try
					{			            			
	            		getFrameSistema().getContentPane().removeAll();					
            			getFrameSistema().getContentPane().repaint();
    				
	            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
	            		JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);						
	    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CARGA DE REPARACION",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
				        
				        final JTextField fieldOpcion=new JTextField();
						JPanel panelGridOpcion=panelGestor.crearPanelOpcion(new String("INGRESE OPCION"),fieldOpcion);
						
						JPanel panelGridLabel=panelGestor.cargarLabelsMostrarDatos();
						
						///################# SUBMIT ##########################
						JButton btSubmit=new JButton("SUBMIT");									
						btSubmit.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	
			            	if(fieldOpcion.getText().equals(""))	
		            		{
			            		System.out.print("\n\n[MENU SISTEMA] NO COLOCO NINGUNA OPCION\n"); //ENEL FUTURO AGREGAR MENU EMERGENTE
								try {
									MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
									menuSistema.mostrar();	
								} catch (MiException e) {
									throw e;
								}catch(SQLException e)
								{
									throw new MiException("\n[MENU SISTEMA] MOSTRAR DATOS EXCEPTION :"+e);
								}	
		            		}else
				            {
		            			switch(Integer.valueOf(fieldOpcion.getText()))
		            			{
		            				case 1:			//CLIENTES
		            					try
		        		            	{
		            						getFrameSistema().getContentPane().removeAll();
    					            		getFrameSistema().getContentPane().repaint();
    					    				
    					            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
    					    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(2, 1), null, Color.blue,null,null);						
    					    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CLIENTES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
    								        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
    								        JPanel panelGridLabel=new JPanel();
    								     
    								        panelGridLabel=panelGestor.cargarClientesEnPanel(clientesG);
    								    											
											///####################### VOLVER #############################
											JButton btVolver=new JButton("VOLVER");
											btVolver.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {
								            	 
												try {
													MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
													menuSistema.mostrar();	
												} catch (MiException e) {
													throw e;
												}catch(SQLException e)
												{
													throw new MiException("\n[MENU SISTEMA] VOLVER - MOSTRAR AUTOPARTE EXCEPTION :"+e);
												}
																            	
								            }
											});
											panelEnd.add(btVolver);
											
											///######################## SALIR ##########################
											JButton btSalir=new JButton();
											btSalir.setText("SALIR");								
											btSalir.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {
								            	System.exit(0);					            	
								            }
											});
											panelEnd.add(btSalir);
						
											panel.add(panelTitulo);
											panel.add(panelGridLabel);
											panel.add(panelResto);
											panel.add(panelEnd);
											
											getFrameSistema().setContentPane(panel);	
											
						            	}catch(MiException e)
										{
											throw e;
										}catch(Exception e)
										{
											throw new MiException("\n[MENU SISTEMA]MOSTRAR DATOS Exception : "+e);
										}
		            					break;
		            				case 2:			//AUTOPARTES
		            					try
		        		            	{
		        			            	getFrameSistema().getContentPane().removeAll();
		        		            		getFrameSistema().getContentPane().repaint();
		        		    				
		        		            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
		        		    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(3, 1), null, Color.blue,null,null);						
		        		    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR AUTOPARTES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		        					        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
		        					        
		        					        JPanel panelGridLabel=panelGestor.crearPanelGrid(new GridLayout(3, 2), Definiciones.line_blackline,null,null,null);
		        					        panelGridLabel=panelGestor.labelsTiposAutopartes(panelGridLabel);
		        					        
		        					        final JTextField fieldOpcion=new JTextField();
		        							JPanel panelGridOpcion=panelGestor.crearPanelOpcion(new String("TIPO AUTOPARTE"),fieldOpcion);
		        					        
		        							///################# SUBMIT ##########################
		        							JButton btSubmit=new JButton();
		        							btSubmit.setText("SUBMIT");								
		        							btSubmit.addMouseListener(new MouseAdapter() {												
		        				            @Override
		        				            public void mouseReleased(MouseEvent evt) {
		        				            	
		        				            	try
		        				            	{
		        				            		if(fieldOpcion.getText().equals("")||Integer.valueOf(fieldOpcion.getText())>3||Integer.valueOf(fieldOpcion.getText())<1)	
		        				            		{
		        					            		System.out.print("\n\n[MENU SISTEMA] NO EXISTE ESE TIPO DE AUTOPARTE \n"); //ENEL FUTURO AGREGAR MENU EMERGENTE
		        										try {
		        											MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
		        											menuSistema.mostrar();	
		        										} catch (MiException e) {
		        											throw e;
		        										}catch(SQLException e)
		        										{
		        											throw new MiException("\n[MENU SISTEMA] MOSTRAR DATOS AUTOPARTES EXCEPTION :"+e);
		        										}	
		        				            		}else
		        						            {
		        					            		getFrameSistema().getContentPane().removeAll();
		        					            		getFrameSistema().getContentPane().repaint();
		        					    				
		        					            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
		        					    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(2, 1), null, Color.blue,null,null);						
		        					    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR DATOS AUTOPARTES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		        								        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
		        								        JPanel panelGridLabel=new JPanel();
		        								        
		        								    	JPanel panelTituloTipo = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.black,new Dimension(400,50));
		        										JLabel labelTitulo=panelGestor.crearTitulo("", JLabel.CENTER,Color.white, new Font(Font.SERIF,Font.BOLD,15));
		        								        
		        										
		        											
		        											switch(Integer.valueOf(fieldOpcion.getText()))
		        											{
		        												case Definiciones.FILTRO_INDICE:
		        													labelTitulo.setText("MODIFICAR FILTRO");
		        													panelTituloTipo.add("Center",labelTitulo);
		        													panelGridLabel=panelGestor.crearPanelGrid(new GridLayout(metgral.obtenerCantidadFiltros(autopartesG),1), Definiciones.line_blackline,null,null,null);	//PODRIA PREGUNTAR SI POR L OMENOS HAY ALGUN FILTRO																					
		        													panelGridLabel=panelGestor.cargarFiltrosEnPanel(autopartesG);
		        													
		        																
		        													break;
		        												case Definiciones.ACEITE_INDICE:
		        													labelTitulo.setText("MODIFICAR ACEITE");
		        													panelTituloTipo.add("Center",labelTitulo);
		        													panelGridLabel=panelGestor.crearPanelGrid(new GridLayout(metgral.obtenerCantidadAceites(autopartesG),1), Definiciones.line_blackline,null,null,null);	//PODRIA PREGUNTAR SI POR L OMENOS HAY ALGUN FILTRO		        																																		
		        													panelGridLabel=panelGestor.cargarAceitesEnPanel(autopartesG);
		        													
		        																
		        													break;
		        												case Definiciones.LAMPARA_INDICE:
		        													labelTitulo.setText("MODIFICAR LAMPARA");
		        													panelTituloTipo.add("Center",labelTitulo);
		        													panelGridLabel=panelGestor.crearPanelGrid(new GridLayout(metgral.obtenerCantidadLamparas(autopartesG),1), Definiciones.line_blackline,null,null,null);	//PODRIA PREGUNTAR SI POR L OMENOS HAY ALGUN FILTRO		        																																		
		        													panelGridLabel=panelGestor.cargarLamparasEnPanel(autopartesG);
 		        																
		        													break;
		        												default:
		        													break;
		        											}
		        											///####################### VOLVER #############################
		        		        							JButton btVolver=new JButton();
		        		        							btVolver.setText("VOLVER");								
		        		        							btVolver.addMouseListener(new MouseAdapter() {												
		        		        				            @Override
		        		        				            public void mouseReleased(MouseEvent evt) {
		        		        				            	 
		        		        								try {
		        		        									MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
		        		        									menuSistema.mostrar();	
		        		        								} catch (MiException e) {
		        		        									throw e;
		        		        								}catch(SQLException e)
		        		        								{
		        		        									throw new MiException("\n[MENU SISTEMA] VOLVER - MOSTRAR AUTOPARTE EXCEPTION :"+e);
		        		        								}
		        		        												            	
		        		        				            }
		        		        							});
		        		        							panelEnd.add(btVolver);
		        		        							
		        		        							///######################## SALIR ##########################
		        		        							JButton btSalir=new JButton();
		        		        							btSalir.setText("SALIR");								
		        		        							btSalir.addMouseListener(new MouseAdapter() {												
		        		        				            @Override
		        		        				            public void mouseReleased(MouseEvent evt) {
		        		        				            	System.exit(0);					            	
		        		        				            }
		        		        							});
		        		        							panelEnd.add(btSalir);
		        		        		
		        		        							panel.add(panelTitulo);
		        		        							panel.add(panelGridLabel);		        		        							
		        		        							panel.add(panelResto);
		        		        							panel.add(panelEnd);
		        		        							
		        		        							getFrameSistema().setContentPane(panel);
		        											
		        						            }
		        				            	}catch(MiException e)
		        								{
		        									throw e;
		        								}catch(Exception e)
		        								{
		        									throw new MiException("\n[MENU SISTEMA]MOSTRAR DATOS Exception : "+e);
		        								}
		        				            }});
		        							panelEnd.add(btSubmit);
		        							
		        							///####################### VOLVER #############################
		        							JButton btVolver=new JButton();
		        							btVolver.setText("VOLVER");								
		        							btVolver.addMouseListener(new MouseAdapter() {												
		        				            @Override
		        				            public void mouseReleased(MouseEvent evt) {
		        				            	 
		        								try {
		        									MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
		        									menuSistema.mostrar();	
		        								} catch (MiException e) {
		        									throw e;
		        								}catch(SQLException e)
		        								{
		        									throw new MiException("\n[MENU SISTEMA] VOLVER - MOSTRAR AUTOPARTE EXCEPTION :"+e);
		        								}
		        												            	
		        				            }
		        							});
		        							panelEnd.add(btVolver);
		        							
		        							///######################## SALIR ##########################
		        							JButton btSalir=new JButton();
		        							btSalir.setText("SALIR");								
		        							btSalir.addMouseListener(new MouseAdapter() {												
		        				            @Override
		        				            public void mouseReleased(MouseEvent evt) {
		        				            	System.exit(0);					            	
		        				            }
		        							});
		        							panelEnd.add(btSalir);
		        		
		        							panel.add(panelTitulo);
		        							panel.add(panelGridLabel);
		        							panel.add(panelGridOpcion);
		        							panel.add(panelResto);
		        							panel.add(panelEnd);
		        							
		        							getFrameSistema().setContentPane(panel);	
		        							
		        		            	}catch(MiException e)
		        						{
		        							throw e;
		        						}catch(Exception e)
		        						{
		        							throw new MiException("\n[MENU SISTEMA]MOSTRAR DATOS Exception : "+e);
		        						}
		        							
		        				            	
		        				            	
		            					break;
		            				case 3:			//REPARACIONEs
		            					try
		        		            	{
		            						getFrameSistema().getContentPane().removeAll();
    					            		getFrameSistema().getContentPane().repaint();
    					    				
    					            		JPanel panel=panelGestor.crearPanelBoxGral(getFrameSistema(),Color.lightGray,BoxLayout.Y_AXIS);
    					    				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(2, 1), null, Color.blue,null,null);						
    					    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR REPARACIONES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
    								        JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
    								        JPanel panelGridLabelEntregados=new JPanel();
    								        JPanel panelGridLabelEnReparacion=new JPanel();
    								        
    								        
    								        JPanel panelTituloE = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"EN REPARACION",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
    								        panelGridLabelEnReparacion=panelGestor.cargarPanelReparacionesEnReparacion(reparacionesG);
    								        JPanel panelTituloF = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"FINALIZADOS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);    								        
    								        panelGridLabelEntregados=panelGestor.cargarPanelReparacionesEntregadas(reparacionesG);
    								        
    								        
    								        
    								        
											///####################### VOLVER #############################
											JButton btVolver=new JButton();
											btVolver.setText("VOLVER");								
											btVolver.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {
								            	 
												try {
													MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
													menuSistema.mostrar();	
												} catch (MiException e) {
													throw e;
												}catch(SQLException e)
												{
													throw new MiException("\n[MENU SISTEMA] VOLVER - MOSTRAR AUTOPARTE EXCEPTION :"+e);
												}
																            	
								            }
											});
											panelEnd.add(btVolver);
											
											///######################## SALIR ##########################
											JButton btSalir=new JButton();
											btSalir.setText("SALIR");								
											btSalir.addMouseListener(new MouseAdapter() {												
								            @Override
								            public void mouseReleased(MouseEvent evt) {
								            	System.exit(0);					            	
								            }
											});
											panelEnd.add(btSalir);
						
											panel.add(panelTitulo);											
											panel.add(panelTituloE);
											panel.add(panelGridLabelEnReparacion);
											panel.add(panelTituloF);
											panel.add(panelGridLabelEntregados);											
											panel.add(panelResto);
											panel.add(panelEnd);
											
											getFrameSistema().setContentPane(panel);	
											
						            	}catch(MiException e)
										{
											throw e;
										}catch(Exception e)
										{
											throw new MiException("\n[MENU SISTEMA]MOSTRAR DATOS Exception : "+e);
										}
		            					
		            					break;
	            					default:
	            						System.out.print("\nNO EXISTE OPCION INGRESADA");
	            						try {
	        								MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
	        								menuSistema.mostrar();	
	        							} catch (MiException e) {
	        								throw e;
	        							}catch(SQLException e)
	        							{
	        								throw new MiException("\n[MENU SISTEMA] VOLVER - MOSTRAR DATOS EXCEPTION :"+e);
	        							}
	            						break;
		            			}
				            }
	            		
						
						}});							
						panelEnd.add(btSubmit);
						
						///####################### VOLVER #############################
						JButton btVolver=new JButton();
						btVolver.setText("VOLVER");								
						btVolver.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	 
							try {
								MenuSistema menuSistema = new MenuSistema(getFrameSistema(),user);
								menuSistema.mostrar();	
							} catch (MiException e) {
								throw e;
							}catch(SQLException e)
							{
								throw new MiException("\n[MENU SISTEMA] VOLVER - MOSTRAR DATOS EXCEPTION :"+e);
							}
											            	
			            }
						});
						panelEnd.add(btVolver);
						
						///######################## SALIR ##########################
						JButton btSalir=new JButton();
						btSalir.setText("SALIR");								
						btSalir.addMouseListener(new MouseAdapter() {												
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	System.exit(0);					            	
			            }
						});
						panelEnd.add(btSalir);
						
						panel.add(panelTitulo);
						panel.add(panelGridLabel);
						panel.add(panelGridOpcion);
						panel.add(panelResto);
						panel.add(panelEnd);
						
						getFrameSistema().setContentPane(panel);						
						
					}catch(MiException e)
					{
						throw e;
					}catch(Exception e)
					{
						throw new MiException("\n[MENU SISTEMA]MOSTRAR DATOS Exception : "+e);
					}  			            	
	            }
				});			
				panelOperaciones.add(btMostrarDatos);
		
				  //#################### VOLVER ##########################
		        JButton btVolver=new JButton();
				btVolver.setText("VOLVER");								
				btVolver.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	
					try {
						MenuInicio	menuInicio = new MenuInicio(getFrameSistema());
						menuInicio.mostrar();
					} catch (MiException e) {
						throw e;
					}
					catch(SQLException e)
					{
						throw new MiException("[MENU SISTEMA] VOLVER EXCEPTION: "+e);
					}
									            	
	            }
				});
				//########################### SALIR ###########################
				JButton btSalir=new JButton();
				btSalir.setText("SALIR");								
				btSalir.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	System.exit(0);					            	
	            }
				});
				
				panelEnd.add(btVolver);
				panelEnd.add(btSalir);
				
				panel.add(panelTitulo);
				panel.add(panelOperaciones);
				panel.add(panelResto);
				panel.add(panelEnd);
			}catch(MiException e)
			{
				throw e;
			}catch(Exception e)
			{
				throw new MiException("[MENU SISTEMA] EXCEPTION: "+e);
			}
					
		}
		public void mostrar() {
			this.getFrameSistema().setVisible(true);
			
		}
		
	}
	//#############################################################################################################
	//################################### MENU ADMINISTRADOR ####################################################
	public class MenuAdministrador
	{	
		
		JPanel panelOp;
		JPanel panelSalir;
		JPanel panel;
		JTextField fieldNameUser, fieldApodoUser,fieldPassUser,fieldMailUser,fieldJerarquiaUser,fieldLogginUser;
		JFrame frameAdmin;
		JButton  btCrearUsuario,btModificarUsuario,btBajaUsuario,btMostrarUsuarios;
		JButton btAtras;		
		JButton btSalir;

		public JFrame getFrameAdmin() 
		{
			return frameAdmin;
		}

		public void setFrameAdmin(JFrame frameAdmin) 
		{
			this.frameAdmin = frameAdmin;
		}

		public MenuAdministrador(JFrame frame,final List<Usuario> usuarios)
		{		
			final UsuarioBO usuarioBo=new UsuarioBO();
			try
			{	
				metgral=new MetodosGrl();				
						
				this.setFrameAdmin(frame);		
				this.getFrameAdmin().getContentPane().removeAll();
				this.getFrameAdmin().getContentPane().repaint();
				
				panel=new JPanel();					
		        panel =(JPanel) this.getFrameAdmin().getContentPane();
		        panel.removeAll();
		        panel.setBackground(Color.cyan);
		        panel.setLayout(new BorderLayout());
		        
		        panelOp = new JPanel();
		        panelOp.setBackground(Color.black);
		        panelOp.setLayout(new GridLayout(6, 1));
		        panelOp.setBorder(new EmptyBorder(4, 4, 4, 4));
		        panelOp.removeAll();
		        
		        panelSalir = new JPanel();
		        panelSalir.setLayout(new GridLayout(2, 1));
		        panelSalir.setBorder(new EmptyBorder(4, 4, 4, 4));
		        panelSalir.setBackground(Color.blue);
		        panelSalir.removeAll();
		        
			}catch(MiException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				throw new MiException("[MENU ADMINISTRADOR] Exception : "+e);
			}
	        try
			{
	        	
		        ///################### CREAR USER #############
		        btCrearUsuario=new JButton();
				btCrearUsuario.setText("Crear Usuario");
				btCrearUsuario.addMouseListener(new MouseAdapter() {
								
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	try
							{
			            		getFrameAdmin().getContentPane().removeAll();
			    				getFrameAdmin().getContentPane().repaint();
			    				
			    				JPanel panelCrearUser=new JPanel();					
								panelCrearUser =(JPanel) getFrameAdmin().getContentPane();
								panelCrearUser.removeAll();
								panelCrearUser.setBackground(Color.lightGray);
								panelCrearUser.setLayout(new BorderLayout());
								
								JPanel panelGridUser=new JPanel();			
								panelGridUser.setLayout(new GridLayout(6, 2));
								panelGridUser.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridUser.setBackground(Color.pink);
								panelGridUser.removeAll();
											
								JPanel panelGridUserEnd = new JPanel();
								panelGridUserEnd.setLayout(new GridLayout(3, 1));
								panelGridUserEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridUserEnd.setBackground(Color.blue);
								panelGridUserEnd.removeAll();
								
								final JTextArea areaName=new JTextArea();
								areaName.setBorder(Definiciones.line_blackline);
								JTextField fieldName=new JTextField("Nombre");
								final JTextArea areaEmail=new JTextArea();
								areaEmail.setBorder(Definiciones.line_blackline);
								JTextField fieldEmail=new JTextField("Email");
								final JTextArea areaUserName=new JTextArea();
								areaUserName.setBorder(Definiciones.line_blackline);
								JTextField fieldUserName=new JTextField("Nombre de Usuario");
								final JTextArea areaPassword=new JTextArea();
								areaPassword.setBorder(Definiciones.line_blackline);
								JTextField fieldPassword=new JTextField("Contrase�a");
								final JTextArea areaJerarquia=new JTextArea();
								areaJerarquia.setBorder(Definiciones.line_blackline);
								JTextField fieldJerarquia=new JTextField("Jerarquia");
																								
								panelGridUser.add(areaName);
								panelGridUser.add(fieldName);
								
								panelGridUser.add(areaEmail);
								panelGridUser.add(fieldEmail);
								
								panelGridUser.add(areaUserName);
								panelGridUser.add(fieldUserName);
								
								panelGridUser.add(areaPassword);
								panelGridUser.add(fieldPassword);
								
								panelGridUser.add(areaJerarquia);
								panelGridUser.add(fieldJerarquia);
								
								JButton btSubmitCreate=new JButton();
								btSubmitCreate.setText("SUBMIT");								
								btSubmitCreate.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	Usuario userCreate=new Usuario();
					            	
					            	userCreate.setName(areaName.getText());							            	
									userCreate.setEmail(areaEmail.getText());							
									userCreate.setUsername(areaUserName.getText());							
									userCreate.setPassword(areaPassword.getText());							
									userCreate.setJerarquia(areaJerarquia.getText());
									userCreate.setLogueado(0);
									
					            	try {					            		
					            		
					            		userCreate.setId(usuarioBo.buscarUltimoUsuarioId());			//podria buscar en la lista, pero ...
					            		if(userCreate.getName().equals("")||userCreate.getPassword().equals("")||userCreate.getUsername().equals(""))
					            		{
					            			System.out.print("\nDATOS VACIOS");				//DESPUES HACER CUADROS INFORMATIVOS
					            			Thread.sleep(2000);
					            			MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
											menuAdministrador.mostrar();
					            		}else
					            		{
											if(usuarioBo.insertarUsuario(userCreate))
											{
												System.out.print("\nUSUARIO CREADO CORRECTAMENTE");
												Thread.sleep(2000);							
												usuarios.add(userCreate);
												MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
												menuAdministrador.mostrar();
											}else
											{
												System.out.print("\nFALLO CREACION USUARIO");
												Thread.sleep(2000);
											}
					            		}
									} catch (MiException e) {
											throw e;
									} catch (InterruptedException e) {
										throw new MiException("[CREATE USER] INTERRUPTED EXCEPTION : "+e);
						            } catch (Exception e) {
										throw new MiException("[CREATE USER]EXCEPTION : "+e);												
									}
					            }
							});
									
								JButton btVolver=new JButton();
								btVolver.setText("VOLVER");								
								btVolver.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
									menuAdministrador.mostrar();					            	
					            }
								});
								
								JButton btSalir=new JButton();
								btSalir.setText("SALIR");								
								btSalir.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	System.exit(0);					            	
					            }
								});
								
								//panelGridUser.add(btSubmitCreate);
								panelCrearUser.add("North",panelGridUser);
								panelGridUserEnd.add(btSubmitCreate);
								panelGridUserEnd.add(btVolver);
								panelGridUserEnd.add(btSalir);
								panelCrearUser.add("South",panelGridUserEnd);
								
								getFrameAdmin().setContentPane(panelCrearUser);
							}
							catch(MiException e)
							{							
								throw e;
							}
							catch(Exception e)
							{							
								throw new MiException("[btCrearUsuario]CREATE USER EXCEPTION: "+e);
							}
			            }
			        });
					panelOp.add(btCrearUsuario);
					
					///###################### MODIFICAR USER ##########################
					btModificarUsuario=new JButton();
					btModificarUsuario.setText("Modificar Usuario");
					btModificarUsuario.addMouseListener(new MouseAdapter() {
						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	
			            	try
							{
			            		getFrameAdmin().getContentPane().removeAll();
			    				getFrameAdmin().getContentPane().repaint();
			    				
			    				JPanel panelModificarUser=new JPanel();					
			    				panelModificarUser =(JPanel) getFrameAdmin().getContentPane();			    				
			    				panelModificarUser.setBackground(Color.lightGray);
			    				panelModificarUser.setLayout(new BorderLayout());
								
								JPanel panelGridUserEnd = new JPanel();
								panelGridUserEnd.setLayout(new GridLayout(3, 1));
								panelGridUserEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridUserEnd.setBackground(Color.blue);
								
								JPanel panelGridModif=new JPanel();			
								panelGridModif.setLayout(new GridLayout(1,2));
								panelGridModif.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridModif.setBackground(Color.pink);
								
								final JTextArea areaToSeek=new JTextArea();
								JTextField fieldToSeek=new JTextField("INGRESE APODO USUARIO A MODIFICAR");
																	
								panelGridModif.add(areaToSeek);
								panelGridModif.add(fieldToSeek);
									
								JButton btSubmitModif=new JButton();
								btSubmitModif.setText("SUBMIT");								
								btSubmitModif.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	try
						            	{
							            	if(areaToSeek.getText().equals(""))
							            	{
							            		System.out.print("[SUBMIT MODIFICAR] DATOS VACIOS");		//DEBUG AGREGAR VENTANA EMERG.
							            		Thread.sleep(2000);
							            		return;
							            	}else
							            	{
							            		final Usuario usuarioModif=metgral.buscarUsuarioPorApodo(areaToSeek.getText(),usuarios);
							            		if(usuarioModif==null)
												{
													System.out.print("\nNO SE ENCONTRO EL USUARIO");
													Thread.sleep(2000);
													return;
												}else
												{
													getFrameAdmin().getContentPane().removeAll();
								    				getFrameAdmin().getContentPane().repaint();
								    				
								    				JPanel panelModificacion=new JPanel();					
								    				panelModificacion =(JPanel) getFrameAdmin().getContentPane();
								    				panelModificacion.removeAll();
								    				panelModificacion.setBackground(Color.lightGray);
								    				panelModificacion.setLayout(new BorderLayout());
								    				
													final JPanel panelGridModifUser=new JPanel();			
													panelGridModifUser.setLayout(new GridLayout(5,2));
													panelGridModifUser.setBorder(new EmptyBorder(4, 4, 4, 4));
													panelGridModifUser.setBackground(Color.pink);				
													
													final JPanel panelGridUserEnd = new JPanel();
													panelGridUserEnd.setLayout(new GridLayout(3, 1));
													panelGridUserEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
													panelGridUserEnd.setBackground(Color.blue);
													
													
													final JTextArea areaName=new JTextArea();
													areaName.setBorder(Definiciones.line_blackline);
													JTextField fieldName=new JTextField("Nombre ("+usuarioModif.getName()+")");
													final JTextArea areaEmail=new JTextArea();
													areaEmail.setBorder(Definiciones.line_blackline);
													JTextField fieldEmail=new JTextField("Email ("+usuarioModif.getEmail()+")");
													final JTextArea areaUserName=new JTextArea();
													areaUserName.setBorder(Definiciones.line_blackline);
													JTextField fieldUserName=new JTextField("Nombre de Usuario ("+usuarioModif.getUsername()+")");
													final JTextArea areaPassword=new JTextArea();
													areaPassword.setBorder(Definiciones.line_blackline);
													JTextField fieldPassword=new JTextField("Contrase�a ("+usuarioModif.getPassword()+")");
													final JTextArea areaJerarquia=new JTextArea();
													areaJerarquia.setBorder(Definiciones.line_blackline);
													JTextField fieldJerarquia=new JTextField("Jerarquia ("+usuarioModif.getJerarquia()+")");
																													
													panelGridModifUser.add(areaName);
													panelGridModifUser.add(fieldName);
													
													panelGridModifUser.add(areaEmail);
													panelGridModifUser.add(fieldEmail);
													
													panelGridModifUser.add(areaUserName);
													panelGridModifUser.add(fieldUserName);
													
													panelGridModifUser.add(areaPassword);
													panelGridModifUser.add(fieldPassword);
													
													panelGridModifUser.add(areaJerarquia);
													panelGridModifUser.add(fieldJerarquia);
													
													final JButton btSubmitModificar=new JButton();
													btSubmitModificar.setText("SUBMIT");								
													btSubmitModificar.addMouseListener(new MouseAdapter() {												
														@Override
														public void mouseReleased(MouseEvent evt) {
															try
															{
											            		//PODRIA MEJORARSE PREGUNTANDO SI DESEA MODIFICAR
																//TODO: AGREGAR JERARQUIAS DE USUARIO
																Usuario userAux=new Usuario();		
																userAux=usuarioModif;
																userAux.setName(areaName.getText());							            	
																userAux.setEmail(areaEmail.getText());							
																userAux.setUsername(areaUserName.getText());							
																userAux.setPassword(areaPassword.getText());							
																userAux.setJerarquia(areaJerarquia.getText());
																userAux.setLogueado(0);
																userAux.setId(usuarioModif.getId());
																
																if(usuarioBo.updateUsuario(userAux))
																{
																	System.out.print("\nUSUARIO MODIFICADO CORRECTAMENTE");
																	Thread.sleep(2000);
																	int i=0;
																	for(Usuario user:usuarios)
																	{
																		if(usuarioModif.getName().equals(user.getName()))
																		{
																			break;
																		}
																		i++;
																	}
																	usuarios.remove(i);								
																	//usuarios=metgral.eliminarUsuarioDeLista(usuarios,usuarioModif);										
																	usuarios.add(userAux);
																	
																	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
																	menuAdministrador.mostrar();
																	
																}else
																{
																	System.out.print("\nFALLO MODIFICACION USUARIO");
																	Thread.sleep(2000);
																	return;
																}
															}catch(MiException e)
															{
																throw e;
															}catch(Exception e)
															{
																throw new MiException("[MODIFICAR USER] EXCEPTION : "+e);
															}
										            	
										            	}
													});
													JButton btVolver=new JButton();
													btVolver.setText("VOLVER");								
													btVolver.addMouseListener(new MouseAdapter() {												
										            @Override
										            public void mouseReleased(MouseEvent evt) {
										            	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
														menuAdministrador.mostrar();				            	
										            }
													});
													
													JButton btSalir=new JButton();
													btSalir.setText("SALIR");								
													btSalir.addMouseListener(new MouseAdapter() {												
										            @Override
										            public void mouseReleased(MouseEvent evt) {
										            	System.exit(0);					            	
										            }
													});
													
													panelModificacion.add("North",panelGridModifUser);
													panelGridUserEnd.add(btSubmitModificar);
													panelGridUserEnd.add(btVolver);
													panelGridUserEnd.add(btSalir);
													panelModificacion.add("South",panelGridUserEnd);												
													getFrameAdmin().setContentPane(panelModificacion);
													
													
													
												}
											
											}
						            	}
						            	catch(MiException e)
						            	{
						            		throw e;
						            	}catch(Exception e)
						            	{
						            		throw new MiException("[BAJA USUARIO] MI EXCEPTION : "+e);
						            	} 
						            }
									});
									JButton btVolver=new JButton();
									btVolver.setText("VOLVER");								
									btVolver.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
										menuAdministrador.mostrar();				            	
						            }
									});
									
									JButton btSalir=new JButton();
									btSalir.setText("SALIR");								
									btSalir.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	System.exit(0);					            	
						            }
									});
									
									//panelGridUser.add(btSubmitCreate);
									panelModificarUser.add("North",panelGridModif);
									panelGridUserEnd.add(btSubmitModif);
									panelGridUserEnd.add(btVolver);
									panelGridUserEnd.add(btSalir);
									panelModificarUser.add("South",panelGridUserEnd);
									
									getFrameAdmin().setContentPane(panelModificarUser);
								}
								catch(MiException e)
								{							
									throw e;
								}
								catch(Exception e)
								{							
									throw new MiException("[btCrearUsuario]CREATE USER EXCEPTION: "+e);
								}			            				            	
			            }
			        });
					panelOp.add(btModificarUsuario);
					
					///###################### ELIMINAR USER ##########################
					btBajaUsuario=new JButton();
					btBajaUsuario.setText("Baja Usuario");
					btBajaUsuario.addMouseListener(new MouseAdapter() {				 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            									
			            		try
								{
				            		getFrameAdmin().getContentPane().removeAll();
				    				getFrameAdmin().getContentPane().repaint();
				    				
				    				JPanel panelBajaUser=new JPanel();					
				    				panelBajaUser =(JPanel) getFrameAdmin().getContentPane();
				    				panelBajaUser.removeAll();
				    				panelBajaUser.setBackground(Color.lightGray);
				    				panelBajaUser.setLayout(new BorderLayout());
									
									JPanel panelGridUserEnd = new JPanel();
									panelGridUserEnd.setLayout(new GridLayout(3, 1));
									panelGridUserEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
									panelGridUserEnd.setBackground(Color.blue);
									panelGridUserEnd.removeAll();
									
									
									JPanel panelGridSeek=new JPanel();			
									panelGridSeek.setLayout(new GridLayout(1,2));
									panelGridSeek.setBorder(new EmptyBorder(4, 4, 4, 4));
									panelGridSeek.setBackground(Color.pink);
									panelGridSeek.removeAll();
									
									final JTextArea areaToSeek=new JTextArea();
									areaToSeek.setBorder(Definiciones.line_blackline);
									JTextField fieldToSeek=new JTextField("INGRESE APODO USUARIO A DAR DE BAJA");
																		
									panelGridSeek.add(areaToSeek);
									panelGridSeek.add(fieldToSeek);
										
									JButton btSubmitBaja=new JButton();
									btSubmitBaja.setText("SUBMIT");								
									btSubmitBaja.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	try
						            	{
							            	if(areaToSeek.getText().equals(""))
							            	{
							            		System.out.print("[SUBMIT BAJA] DATOS VACIOS");		//DEBUG AGREGAR VENTANA EMERG.
							            		Thread.sleep(2000);
							            		return;
							            	}else
							            	{
							            		Usuario usuarioBaja=metgral.buscarUsuarioPorApodo(areaToSeek.getText(),usuarios);
							            		if(usuarioBaja==null)
												{
													System.out.print("\nNO SE ENCONTRO USER");
													return;
												}
							            		//SE PUEDE AGREGAR PREGUNTA DE SI ES EL USUARIO BUSCADO
							            		if(usuarioBo.eliminarUsuario(usuarioBaja))
												{
													System.out.print("\nUSUARIO ELIMINADO CORRECTAMENTE");		//DEBUG AGREGAR VENTANA EMERG.
													Thread.sleep(2000);
													int i=0;
													for(Usuario user:usuarios)
													{
														if(usuarioBaja.getName().equals(user.getName()))
														{
															break;
														}
														i++;
													}
													usuarios.remove(i);												
													//usuarios=metgral.eliminarUsuarioDeLista(usuarios,usuarioBaja);						
													MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
													menuAdministrador.mostrar();
												}else
												{
													System.out.print("\nFALLO BAJA USUARIO");
													Thread.sleep(2000);
												}
							            	}
						            	}
						            	catch(MiException e)
						            	{
						            		throw e;
						            	}catch(Exception e)
						            	{
						            		throw new MiException("[BAJA USUARIO] MI EXCEPTION : "+e);
						            	} 
						            }
									});
									JButton btVolver=new JButton();
									btVolver.setText("VOLVER");								
									btVolver.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
										menuAdministrador.mostrar();				            	
						            }
									});
									
									JButton btSalir=new JButton();
									btSalir.setText("SALIR");								
									btSalir.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	System.exit(0);					            	
						            }
									});
									
									//panelGridUser.add(btSubmitCreate);
									panelBajaUser.add("North",panelGridSeek);
									panelGridUserEnd.add(btSubmitBaja);
									panelGridUserEnd.add(btVolver);
									panelGridUserEnd.add(btSalir);
									panelBajaUser.add("South",panelGridUserEnd);
									
									getFrameAdmin().setContentPane(panelBajaUser);
								}
								catch(MiException e)
								{							
									throw e;
								}
								catch(Exception e)
								{							
									throw new MiException("[btCrearUsuario]CREATE USER EXCEPTION: "+e);
								}			            				            	
			            }
			        });
					panelOp.add(btBajaUsuario);
						
					///###################### MOSTRAR USER ##########################
					btMostrarUsuarios=new JButton();
					btMostrarUsuarios.setText("Mostrar Usuarios");
					btMostrarUsuarios.addMouseListener(new MouseAdapter() {				 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	try
							{
			            		getFrameAdmin().getContentPane().removeAll();
			    				getFrameAdmin().getContentPane().repaint();
			    				
			    				JPanel panelCrearUser=new JPanel();					
								panelCrearUser =(JPanel) getFrameAdmin().getContentPane();
								panelCrearUser.removeAll();
								panelCrearUser.setBackground(Color.lightGray);
								panelCrearUser.setLayout(new BorderLayout());
								
								JPanel panelGridUserEnd = new JPanel();
								panelGridUserEnd.setLayout(new GridLayout(2, 1));
								panelGridUserEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridUserEnd.setBackground(Color.blue);
								panelGridUserEnd.removeAll();
								
								int cantUser=0;
								for(Usuario usuario : usuarios)
								{
									cantUser++;
									System.out.print("\n"+usuario.toString());
								}
								
								JPanel panelGridUser=new JPanel();			
								panelGridUser.setLayout(new GridLayout(cantUser,1));
								panelGridUser.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridUser.setBackground(Color.pink);
								panelGridUser.removeAll();
											
								for(Usuario usuario : usuarios)
								{
									JTextField fieldUser=new JTextField();
									fieldUser.setText(usuario.toString());
									panelGridUser.add(fieldUser);
								}
								
									
								JButton btVolver=new JButton();
								btVolver.setText("VOLVER");								
								btVolver.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
									menuAdministrador.mostrar();				            	
					            }
								});
								
								JButton btSalir=new JButton();
								btSalir.setText("SALIR");								
								btSalir.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	System.exit(0);					            	
					            }
								});
								
								//panelGridUser.add(btSubmitCreate);
								panelCrearUser.add("North",panelGridUser);
								
								panelGridUserEnd.add(btVolver);
								panelGridUserEnd.add(btSalir);
								panelCrearUser.add("South",panelGridUserEnd);
								
								getFrameAdmin().setContentPane(panelCrearUser);
							}
							catch(MiException e)
							{							
								throw e;
							}
							catch(Exception e)
							{							
								throw new MiException("[btMOSTRAR USER]MOSTRAR USER EXCEPTION: "+e);
							}
			            }
			        });
					panelOp.add(btMostrarUsuarios);					

					/////##################### VOLVER ####################
					btAtras=new JButton();
					btAtras.setText("VOLVER");			
					btAtras.addMouseListener(new MouseAdapter() {						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	
							try {
								MenuInicio menuInicio = new MenuInicio(getFrameAdmin());
								menuInicio.mostrar();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								throw new MiException("[MENU ADMINISTRADOR] EXCEPTION :"+e);
							}
							catch(MiException e)
							{
								throw e;
							}
			            }
			        });	
					
			        /////###################### SALIR ###################
					btSalir=new JButton();
					btSalir.setText("SALIR");			
					btSalir.addMouseListener(new MouseAdapter() {
						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			                System.exit(0);
			            }
			        });					
					panelSalir.add(btAtras);
					panelSalir.add(btSalir);
					
					panel.add("South",panelSalir);
					//panelOperaciones.add(btSalir);
					panel.add("North",panelOp);
					
			}catch(MiException e)
			{							
				throw e;
			}
			catch(Exception e)
			{							
				throw new MiException("[MENU ADMINISTRADOR]ADMINISTRADOR EXCEPTION: "+e);
			}	
			return;
		}

		public void mostrar() 
		{
			  
	           this.getFrameAdmin().setVisible(true);
				  
       }
		
	}
	
////////######################################### MENU INICIO ##################################################################
	public class MenuInicio 
	{
		List<Usuario> usuariosL;
		
		JFrame frameInicio;

		public void setFrameInicio(JFrame frameInicio)
		{                                        
			this.frameInicio=frameInicio;
		}
		public JFrame getFrameInicio()
		{
			return this.frameInicio;
		}
		public MenuInicio(final JFrame frame) throws MiException, SQLException
		{
			UsuarioBO usuarioBo=new UsuarioBO();
			panelGestor=new Panel();
			try
			{
					
				usuariosL=new ArrayList<Usuario>();
				usuariosL=usuarioBo.cargarUsuarios();
				
				this.setFrameInicio(frame);
				this.getFrameInicio().getContentPane().removeAll();
				
				JPanel panel = (JPanel)this.getFrameInicio().getContentPane();
		        panel.setLayout(new BorderLayout());	        
		        panel.setBackground(Color.cyan);		        							
				JPanel panelOperaciones=panelGestor.crearPanelGrid(new GridLayout(6, 1), Definiciones.line_blackline,Color.cyan,null,new Dimension(400,100));
				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODO DE USO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,25),Color.white);
					
					//###################################### BOTON TECNICO	//##########################
					JButton btModoTecnico=new JButton();
					btModoTecnico.setMaximumSize(new Dimension(400,50));
					btModoTecnico.setText("MODO TECNICO");			
					//btModoTecnico.addActionListener(this);
					btModoTecnico.addMouseListener(new MouseAdapter() {						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	System.out.print("\n MODO TECNICO");
							
			            	ValidarSenha validarSenha=new ValidarSenha(frame,usuariosL);
			            	validarSenha.mostrar();								                
			            }
			        });
					panelOperaciones.add(btModoTecnico);
					
					//########################################## BOTON SISTEMA ################################
					JButton btModoSistema=new JButton();
					btModoSistema.setText("MODO SISTEMA");			
					//btModoSistema.addActionListener(this);
					btModoSistema.addMouseListener(new MouseAdapter() {						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
		            		try 
							{
		            			LogginUsuario logginUser=new LogginUsuario(getFrameInicio(),usuariosL);
		            			logginUser.mostrar();
							}catch(MiException  e) {
								System.out.print("\n[MenuInicio]EXCEPTION SISTEMA : "+e);
								throw e;
							}
							catch(Exception  e) {
								System.out.print("\n[MenuInicio]EXCEPTION GENERAL SISTEMA : "+e);
								throw new MiException("\n[MenuInicio]EXCEPTION GENERAL SISTEMA : "+e);
							}
						}
			        });
					
					panelOperaciones.add(btModoSistema);
					
					//################################################### BOTON SALIR ####################
					JButton btSalir=new JButton();
					btSalir.setText("SALIR");			
					//btSalir.addActionListener(this);
					btSalir.addMouseListener(new MouseAdapter() {						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			        		System.out.print("\nSALIR");							
							System.exit(0);
			            }
			        });
					
					panel.add("North",panelTitulo);
					panel.add("Center",panelOperaciones);
					panel.add("South",btSalir);			
					
					
				}catch(MiException e)
				{
					throw e;
				}
				catch(Exception e)
				{							
					throw new MiException("[MenuInicio]MENU TIPO SISTEMA EXCEPTION: "+e);
				}
			
		}	
	   public void mostrar() 
	   {
		  
           this.getFrameInicio().setVisible(true);
			  
       }

        
	}
///###################################################### LOGGIN USUARIO #########################################
	public class LogginUsuario 
	{		
		JFrame frameLoggin;
		
		
		public JFrame getFrameLoggin() {
			return frameLoggin;
		}
		public void setFrameLoggin(JFrame frameLoggin) {
			this.frameLoggin = frameLoggin;
		}
		public LogginUsuario()
		{
			
		}
		public LogginUsuario(JFrame frame,final List<Usuario> usuariosL) throws MiException
		{	
			panelGestor=new Panel();
			
			this.setFrameLoggin(frame);
								
			//frame.getContentPane().removeAll();			
			this.getFrameLoggin().getContentPane().removeAll();
			this.getFrameLoggin().getContentPane().repaint();
			

			JPanel panel=panelGestor.crearPanelBoxGral(getFrameLoggin(),Color.cyan,BoxLayout.Y_AXIS);			
			JPanel panelOperaciones=panelGestor.crearPanelGrid(new GridLayout(2, 2), Definiciones.line_blackline,Color.cyan,null,new Dimension(400,50));
			JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(1,3), null, Color.cyan,null,new Dimension(400,50));
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"VALIDACION DE CLAVE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,25),Color.white);
			JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.cyan,new Dimension(400,300),null);
				        
	        JLabel labelUser=new JLabel("USER: ",JLabel.RIGHT);	        
	        JLabel labelPass=new JLabel("PASS: ",JLabel.RIGHT);
	        final JTextField fieldPass=new JTextField();
	        fieldPass.setBorder(Definiciones.line_blackline);
	        final JTextField fieldUser=new JTextField();
	        fieldUser.setBorder(Definiciones.line_blackline);	        
								
	        
	        panelOperaciones.add(labelUser);
	        panelOperaciones.add(fieldUser);
	        panelOperaciones.add(labelPass);
	        panelOperaciones.add(fieldPass);
	        
	        ///############### SUBMIT ####################################
	        JButton btSubmit=new JButton();
	        btSubmit.setText("SUBMIT");								
	        btSubmit.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	try
	            	{
	            		if(!fieldPass.getText().equals("")&&!fieldUser.getText().equals(""))
	            		{
	            			
	            			Usuario usuarioEncontrado=null;	            			
		            		for(Usuario usuario : usuariosL)	            		
							{	
		            			if(usuario.getUsername().equals(fieldUser.getText())&&usuario.getPassword().equals(fieldPass.getText()))
								{	
		            				usuarioEncontrado=usuario;
		            				break;
								}								
							}
		            		if(usuarioEncontrado!=null)
		            		{
			            		System.out.print("\nUSUARIO LOGUEADO");			//DEBUG			
								Thread.sleep(1000);
								MenuSistema menuSistema=new MenuSistema(getFrameLoggin(),usuarioEncontrado);
								menuSistema.mostrar();
		            		}else{
								System.out.print("\nCONTRASE�A INCORRECTA"); 	//DEBUG
								MenuInicio menuInicio=new MenuInicio(getFrameLoggin());
				            	menuInicio.mostrar();
		            		}
		            		
	            		}else
	            		{
	            			System.out.print("\nERROR - DATOS VACIOS");								//AGREGAR CARTEL INFORMATIVO EMERGENTE
	            			Thread.sleep(2000);
	            			MenuInicio menuInicio=new MenuInicio(getFrameLoggin());
			            	menuInicio.mostrar();
	            		}
	            	}
	            	catch(MiException e)
	            	{
	            		throw e;
	            	}catch(Exception e)
	            	{
	            		throw new MiException("[VALIDAR LoGGIN USER] MI EXCEPTION : "+e);
	            	} 
	            }
			});
			
	    	/////##################### VOLVER ####################
			JButton btAtras=new JButton();
			btAtras.setText("VOLVER");			
			btAtras.addMouseListener(new MouseAdapter() {						 
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	
	            	MenuInicio menuInicio;
					try {
						menuInicio = new MenuInicio(getFrameLoggin());
					} catch (MiException e) {
						throw e;
						
					}
					catch(SQLException e)
					{
						throw new MiException("[VALIDAR LOGIN USER] VOLVER EXCEPTION : "+e);
					}
	            	menuInicio.mostrar();
	            }
	        });	
	        
	    	//################################ SALIR ################### BOTON SALIR
			JButton btSalir=new JButton();
			btSalir.setText("SALIR");			
			//btSalir.addActionListener(this);
			btSalir.addMouseListener(new MouseAdapter() {						 
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	        		System.out.print("\nSALIR");					
					System.exit(0);
	            }
	        });
						
			panelEnd.add(btSalir);
			panelEnd.add(btAtras);
			panelEnd.add(btSubmit);
			
			panel.add(panelTitulo);			
			panel.add(panelOperaciones);
			panel.add(panelResto);
			panel.add(panelEnd);
			
			//getFrameSenha().setContentPane(panel);
			
		}
		
		public void mostrar()
		{
			this.getFrameLoggin().setVisible(true);
		}
	}
	
	//############### VALIDAR SENHA ####################################################################################
	public class ValidarSenha	
	{
		JFrame frameSenha;
		public void setFrameSenha(JFrame frameSenha)
		{
			this.frameSenha=frameSenha;
		}
		public JFrame getFrameSenha()
		{
			return this.frameSenha;
		}
		public ValidarSenha()
		{
			
		}
		public ValidarSenha(final JFrame frame,final List<Usuario> usuariosL)throws MiException
		{
			panelGestor=new Panel();
			try
			{
				this.setFrameSenha(frame);
									
				//frame.getContentPane().removeAll();			
				this.getFrameSenha().getContentPane().removeAll();
				this.getFrameSenha().getContentPane().repaint();
				
				JPanel panel=panelGestor.crearPanelBoxGral(getFrameSenha(),Color.cyan,BoxLayout.Y_AXIS);
				JPanel panelOperaciones=panelGestor.crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,Color.cyan,null,new Dimension(400,50));
				JPanel panelEnd=panelGestor.crearPanelGrid(new GridLayout(1,3), null, Color.cyan,null,new Dimension(400,50));
				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"VALIDACION DE CLAVE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,25),Color.white);
				
		        JTextField fieldPass=new JTextField("PASS TECNICA: ");
		        fieldPass.setEnabled(false);
		        final JTextArea areaPass=new JTextArea("",3,8);
		        areaPass.setBorder(Definiciones.line_blackline);
		        
		        ///############### SUBMIT ####################################
		        JButton btSubmitSenha=new JButton();
		        btSubmitSenha.setText("SUBMIT");								
		        btSubmitSenha.addMouseListener(new MouseAdapter() {												
		            @Override
		            public void mouseReleased(MouseEvent evt) {
		            	try
		            	{
		            		if((!areaPass.getText().equals(""))&&(Integer.valueOf(areaPass.getText())==Definiciones.PASS_TECNICO))
							{
		            			System.out.print("\nCONTRASE�A CORRECTA");
		            			MenuAdministrador menuAdministrador=new MenuAdministrador(frame,usuariosL);
		            			menuAdministrador.mostrar();
							}else
							{
								System.out.print("\nCONTRASE�A INCORRECTA");
								MenuInicio menuInicio=new MenuInicio(getFrameSenha());
				            	menuInicio.mostrar();
							}
		            	}
		            	catch(MiException e)
		            	{
		            		throw e;
		            	}catch(Exception e)
		            	{
		            		throw new MiException("[VALIDAR TECNICO] MI EXCEPTION : "+e);
		            	} 
		            }
				});
				
		    	/////##################### VOLVER ####################
				JButton btAtras=new JButton();
				btAtras.setText("VOLVER");			
				btAtras.addMouseListener(new MouseAdapter() {						 
		            @Override
		            public void mouseReleased(MouseEvent evt) {
		            	
		            	MenuInicio menuInicio;
						try {
							menuInicio = new MenuInicio(frame);
							menuInicio.mostrar();
						} catch (MiException e) {
							throw e;

						} catch (SQLException e) {

							throw new MiException("[VALIDAR TECNICO] EXCEPTION SQL"+ e);
						}
		            	
		            }
		        });	
		        
		    	//################################ SALIR ################### BOTON SALIR
				JButton btSalir=new JButton();
				btSalir.setText("SALIR");			
				//btSalir.addActionListener(this);
				btSalir.addMouseListener(new MouseAdapter() {						 
		            @Override
		            public void mouseReleased(MouseEvent evt) {
		        		System.out.print("\nSALIR");					
						System.exit(0);
		            }
		        });
												
				
				panelOperaciones.add(fieldPass);
				panelOperaciones.add(areaPass);					
						        				
				panelEnd.add(btSalir);
				panelEnd.add(btAtras);				
				panelEnd.add(btSubmitSenha);
								
				panel.add(panelTitulo);
				panel.add(panelOperaciones);
				panel.add(panelEnd);
				//getFrameSenha().setContentPane(panel);
			}catch(MiException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				throw new MiException("[VALIDAR SENHA] EXCEPTION : "+e);
			}
		}
		public void mostrar()
		{
			this.getFrameSenha().setVisible(true);
		}

	}
	
	//############################## MENU YES OR NOT ###########################################################
	//#########################################################################################POR EL MOMENTO NO IMPLEMENTADA
	public class MenuYesOrNot
	{
		private boolean yesOrNot;
		public boolean isYesOrNot() {
			return yesOrNot;
		}
		public void setYesOrNot(boolean boYesOrNot) {
			this.yesOrNot = boYesOrNot;
		}
		private JFrame frame;
		public JFrame getFrame() {
			return frame;
		}
		public void setFrame(JFrame frame) {
			this.frame = frame;
		}
		public MenuYesOrNot()
		{
			JFrame frame=new JFrame();
			frame.setSize(300, 200);
	        frame.setTitle("YES OR NOT");		        
	        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	        frame.setResizable(false);
	        this.setFrame(frame);
	        
		}
		public boolean menuYesOrNotBaja(String tipo,String info)
		{
			panelGestor=new Panel();
			
			try
			{
				this.getFrame().getContentPane().removeAll();
				this.getFrame().getContentPane().repaint();
				
				JPanel panel=panelGestor.crearPanelBoxGral(getFrame(),Color.lightGray,BoxLayout.Y_AXIS);				
				JPanel panelOperaciones=panelGestor.crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,null,null,null);				
		        JLabel labelCliente=new JLabel("BAJA DE '"+tipo+"' - '"+info+"'",JLabel.CENTER);

		        ///############### SUBMIT NO ####################################
		        JButton btSubmitNo=new JButton();
		        btSubmitNo.setText("NO");								
		        btSubmitNo.addMouseListener(new MouseAdapter() {												
		            @Override
		            public void mouseReleased(MouseEvent evt) {
		            	setYesOrNot(false);
		            }
				});
		        panel.add(btSubmitNo);
		        
		        
		        ///############### SUBMIT YES####################################
		        JButton btSubmitYes=new JButton();
		        btSubmitYes.setText("YES");								
		        btSubmitYes.addMouseListener(new MouseAdapter() {												
		            @Override
		            public void mouseReleased(MouseEvent evt) {
		            	setYesOrNot(true);
		            }
				});
		        panelOperaciones.add(btSubmitYes);
		        
		        panel.add(labelCliente);
		        panel.add(panelOperaciones);
				//getFrameSenha().setContentPane(panel);
			}catch(MiException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				throw new MiException("[VALIDAR SENHA] EXCEPTION : "+e);
			}
			return isYesOrNot();
		}
	}
}

