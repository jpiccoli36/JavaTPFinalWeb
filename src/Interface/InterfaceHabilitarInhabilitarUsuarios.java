package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorUsuario;
import Datos.DatosUsuarios;
import Entidades.Persona;

import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;

public class InterfaceHabilitarInhabilitarUsuarios extends JInternalFrame {
	

	private JPanel contentPane;
	private JTextField tfApellido;
	private JTextField tfNombre;
	private JLabel lblNombre;
	private JTextField tfIDUsuario;
	private JCheckBox chckbxHabilitado;
	private JButton btnInhabilitar;
	ControladorUsuario cru= new ControladorUsuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceHabilitarInhabilitarUsuarios frame = new InterfaceHabilitarInhabilitarUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceHabilitarInhabilitarUsuarios() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnUsuariosHabilitados = new JButton("Estado Usuario");
		btnUsuariosHabilitados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ShowEstadoUsuario();
			}
		});
		btnUsuariosHabilitados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel lblIDUsuario = new JLabel("ID Usuario");

		tfIDUsuario = new JTextField();
		tfIDUsuario.setColumns(10);

		lblNombre = new JLabel("Nombre");

		JLabel lblApellido = new JLabel("Apellido");

		JLabel lblEstado = new JLabel("Estado");

		chckbxHabilitado = new JCheckBox("Habilitado");

		JButton btnHabilitar = new JButton("Habilitar");
		btnHabilitar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HabilitarUsuario();
			}
		});

		btnInhabilitar = new JButton("Inhabilitar");
		btnInhabilitar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InhabiltarUsuario();
			}
		});

		tfNombre = new JTextField();
		tfNombre.setColumns(10);

		tfApellido = new JTextField();
		tfApellido.setText("");
		tfApellido.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblApellido)
								.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING).addComponent(btnUsuariosHabilitados)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblIDUsuario)
												.addGap(86).addComponent(tfIDUsuario, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnHabilitar)
												.addGap(42).addComponent(btnInhabilitar))
										.addGroup(
												gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(lblEstado).addComponent(lblNombre))
														.addGap(31)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(chckbxHabilitado).addComponent(tfApellido,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(192, Short.MAX_VALUE)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(27)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIDUsuario).addComponent(tfIDUsuario, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnUsuariosHabilitados).addGap(30)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre).addComponent(tfNombre, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblApellido)
								.addComponent(tfApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblEstado)
								.addComponent(chckbxHabilitado))
						.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnHabilitar)
								.addComponent(btnInhabilitar))
						.addGap(26)));
		contentPane.setLayout(gl_contentPane);
	}

	protected void HabilitarUsuario() {
		Persona p = new Persona();
		p.setIdUsuario( Integer.parseInt(tfIDUsuario.getText()));				
		cru.HabilitarUsuario(p);

	}

	protected void InhabiltarUsuario() {
		Persona p = new Persona();
		p.setIdUsuario(Integer.parseInt(tfIDUsuario.getText()));				
		cru.InhabilitarUsuario(p);	
		

	}

	protected void ShowEstadoUsuario() {		
		int ID = Integer.parseInt(tfIDUsuario.getText());
		Persona p = new Persona();
		p=cru.ConsultarEstado(ID);
		
		
		
			tfNombre.setText(p.getNombre());
			tfApellido.setText(p.getApellido());
			if (p.getEstados().equals("habilitado")) {
				chckbxHabilitado.setSelected(true);
			} else {
				chckbxHabilitado.setSelected(false);
			}
			if(p.getEstados().equals("admin"))
			{
				btnInhabilitar.setEnabled(false);
			}
		

	}	
	
	
		
}
