package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import controller.controlador;
import models.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal {

	private JFrame frmParkingMlaga;
	private JTextField txtMatricula;
	private JTextField txtMatriculaPago;
	private JTextField txtHoraEntrada;
	private JTextField txtHoraSalida;
	private JTextField txtImporte;
	private JPanel panelEntrada;
	private JPanel panelPago;
	private JPanel panelCaja;
	private JLabel labelTotal;
	private JLabel lblTotalEnCaja;
	private JLabel labelTotalCaja;
	private JLabel lblTotalAPagar;
	private JLabel lblCambio;
	private JLabel labelCambio;
	private float PagoTotalillo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmParkingMlaga.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
		PagoTotalillo = 0;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmParkingMlaga = new JFrame();
		frmParkingMlaga.setTitle("Parking M\u00E1laga");
		frmParkingMlaga.setBounds(100, 100, 604, 486);
		frmParkingMlaga.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmParkingMlaga.getContentPane().setLayout(null);
		
		JButton btnEntrada = new JButton("Entrada Vehiculo");
		btnEntrada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panelEntrada.setVisible(true);
				panelCaja.setVisible(false);
				panelPago.setVisible(false);
			}
		});
		btnEntrada.setBounds(45, 30, 158, 88);
		frmParkingMlaga.getContentPane().add(btnEntrada);
		
		JButton btnPagoYSalida = new JButton("Pago y Salida\r\nVehiculo");
		btnPagoYSalida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panelPago.setVisible(true);
				panelEntrada.setVisible(false);
				panelCaja.setVisible(false);
			}
		});
		btnPagoYSalida.setBounds(213, 30, 171, 88);
		frmParkingMlaga.getContentPane().add(btnPagoYSalida);
		
		JButton btnCajaDelDia = new JButton("Caja del dia");
		btnCajaDelDia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panelCaja.setVisible(true);
				panelPago.setVisible(false);
				panelEntrada.setVisible(false);
				labelTotalCaja.setText("" + controlador.pagoTotalDia + "€");
			}
		});
		btnCajaDelDia.setBounds(394, 30, 143, 88);
		frmParkingMlaga.getContentPane().add(btnCajaDelDia);
		
		panelPago = new JPanel();
		panelPago.setBounds(78, 129, 444, 308);
		frmParkingMlaga.getContentPane().add(panelPago);
		panelPago.setLayout(null);
		
		txtMatriculaPago = new JTextField();
		txtMatriculaPago.setText("Matricula");
		txtMatriculaPago.setBounds(30, 35, 185, 32);
		panelPago.add(txtMatriculaPago);
		txtMatriculaPago.setColumns(10);
		
		txtHoraEntrada = new JTextField();
		txtHoraEntrada.setText("Hora entrada(MINUTOS SOLO)");
		txtHoraEntrada.setColumns(10);
		txtHoraEntrada.setBounds(30, 86, 185, 32);
		panelPago.add(txtHoraEntrada);
		
		txtHoraSalida = new JTextField();
		txtHoraSalida.setText("Hora salida(MINUTOS SOLO)");
		txtHoraSalida.setColumns(10);
		txtHoraSalida.setBounds(30, 135, 185, 32);
		panelPago.add(txtHoraSalida);
		
		lblTotalAPagar = new JLabel("Total a pagar");
		lblTotalAPagar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalAPagar.setBounds(299, 53, 88, 14);
		panelPago.add(lblTotalAPagar);
		
		labelTotal = new JLabel("0\u20AC");
		labelTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelTotal.setBounds(299, 86, 88, 14);
		panelPago.add(labelTotal);
		
		txtImporte = new JTextField();
		txtImporte.setText("Importe");
		txtImporte.setColumns(10);
		txtImporte.setBounds(249, 119, 185, 32);
		panelPago.add(txtImporte);
		
		
		JButton btnCalcular = new JButton("Calcular precio");
		btnCalcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//EVENTO QUE CALCULA EL PRECIO UNICAMENTE EN MINUTOS A PARTIR DE UN TIEMPO
				Coche aux = controlador.Buscar(txtMatriculaPago.getText());
				PagoTotalillo = aux.getPrecio();
				int tiempoEntrada = 0;
				int tiempoSalida = 0;
				int tiempoTotal = 0;
				float pagoTotal = 0f;
				boolean error = false;
				
				if(aux != null){
					try{
						tiempoEntrada = Integer.parseInt(txtHoraEntrada.getText());
						if(tiempoEntrada >= 0){
						aux.setTiempoIni(tiempoEntrada);
						}else{
							error = true;
							JOptionPane.showMessageDialog(null, "El tiempo de entrada tiene que ser mayor o igual a 0", "ERROR PAGO", JOptionPane.ERROR_MESSAGE);
						}
					}catch(Exception e){
						error = true;
						JOptionPane.showMessageDialog(null, "El tiempo de entrada no es válido, (tiene que introducirse en minutos)", "ERROR PAGO", JOptionPane.ERROR_MESSAGE);
					}
					
					try{
						tiempoSalida = Integer.parseInt(txtHoraSalida.getText());
						if(tiempoSalida >= 0){
						aux.setTiempoFin(tiempoSalida);
						}else{
							error = true;
							JOptionPane.showMessageDialog(null, "El tiempo de salida tiene que ser mayor o igual a 0", "ERROR PAGO", JOptionPane.ERROR_MESSAGE);
						}
						
					}catch(Exception e){
						error = true;
						JOptionPane.showMessageDialog(null, "El tiempo de salida no es válido, (tiene que introducirse en minutos)", "ERROR PAGO", JOptionPane.ERROR_MESSAGE);
					}
					
					if(!error){
						tiempoTotal = tiempoSalida - tiempoEntrada;
						if(tiempoTotal >=  0){
							if(tiempoTotal <= 30){
								labelTotal.setText("" + (float)(tiempoTotal * 0.06f));
							}else{
								pagoTotal = (float)(30 * 0.06f);
								tiempoTotal -= 30;
								if(tiempoTotal <= 60){
									pagoTotal = pagoTotal + (float)(tiempoTotal*0.01f);
									labelTotal.setText("" + pagoTotal + "€");
									aux.setPrecio(pagoTotal);
									PagoTotalillo = pagoTotal;
								}else{
									pagoTotal = pagoTotal + (float)(60 * 0.01f);
									tiempoTotal -= 60;
									if(tiempoTotal <= 720){
										pagoTotal = pagoTotal + (float)(tiempoTotal * 0.03f);
										labelTotal.setText("" + pagoTotal + "€");
										aux.setPrecio(pagoTotal);
										PagoTotalillo = pagoTotal;
									}else{
										pagoTotal = pagoTotal + (float)(720*0.03f);
										tiempoTotal -= 720;
										pagoTotal = pagoTotal + (float)(tiempoTotal * 0.15f);
										labelTotal.setText("" + pagoTotal + "€");
										aux.setPrecio(pagoTotal);
										PagoTotalillo = pagoTotal;
									}
								}
							}
						}else{
							JOptionPane.showMessageDialog(null, "El tiempo de salida no puede ser menor que el tiempo de entrada", "ERROR PAGO", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "No se encontro un vehiculo con esa matricula", "ERROR PAGO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCalcular.setBounds(60, 178, 129, 42);
		panelPago.add(btnCalcular);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//EVENTO PARA EFECTUAR EL PAGO QUE INCREMENTA EL PAGO TOTAL DEL DÍA Y CALCULA EL CAMBIO
				float importe = 0f;
				boolean error = false;
				
				try{
					importe = Float.parseFloat(txtImporte.getText());
				}catch(Exception e){
					error = true;
					JOptionPane.showMessageDialog(null, "El importe introducido no es válido", "ERROR PAGO", JOptionPane.ERROR_MESSAGE);
				}
				
				
				if(!error){
					if(importe >= PagoTotalillo){
						labelCambio.setText("" + (importe-PagoTotalillo) + "€");
						controlador.pagoTotalDia += PagoTotalillo;
						JOptionPane.showMessageDialog(null, "Pago efectuado con exito!");
					}else{
						JOptionPane.showMessageDialog(null, "El importe tiene que ser igual o mayor que el pago total", "ERROR PAGO", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnPagar.setBounds(274, 162, 129, 42);
		panelPago.add(btnPagar);
		
		lblCambio = new JLabel("Cambio");
		lblCambio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCambio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCambio.setBounds(299, 225, 88, 14);
		panelPago.add(lblCambio);
		
		labelCambio = new JLabel("0\u20AC");
		labelCambio.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCambio.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelCambio.setBounds(299, 248, 88, 14);
		panelPago.add(labelCambio);
		panelPago.setVisible(false);
		
		panelCaja = new JPanel();
		panelCaja.setBounds(78, 129, 444, 239);
		frmParkingMlaga.getContentPane().add(panelCaja);
		panelCaja.setLayout(null);
		
		lblTotalEnCaja = new JLabel("Total en caja");
		lblTotalEnCaja.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalEnCaja.setBounds(141, 58, 111, 19);
		panelCaja.add(lblTotalEnCaja);
		
		labelTotalCaja = new JLabel("0\u20AC");
		labelTotalCaja.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelTotalCaja.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTotalCaja.setBounds(34, 96, 199, 14);
		panelCaja.add(labelTotalCaja);
		
		//PANELES VISIBLES
		panelCaja.setVisible(false);
		
		panelEntrada = new JPanel();
		panelEntrada.setBounds(78, 129, 444, 239);
		frmParkingMlaga.getContentPane().add(panelEntrada);
		panelEntrada.setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Coche aux = new Coche();
				if(controlador.Buscar(txtMatricula.getText()) == null){
				controlador.Parking.add(aux);
				aux.setMatricula(txtMatricula.getText());
				JOptionPane.showMessageDialog(null, "Vehiculo añadido con exito");
				}else{
					JOptionPane.showMessageDialog(null, "Ya existe un vehiculo con esa matricula", "ERROR REGISTRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRegistrar.setBounds(156, 107, 141, 104);
		panelEntrada.add(btnRegistrar);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(44, 37, 368, 38);
		panelEntrada.add(txtMatricula);
		txtMatricula.setText("Matricula...");
		txtMatricula.setColumns(10);
	}
}
