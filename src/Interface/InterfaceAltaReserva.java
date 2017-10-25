package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorUsuario;
import Controlador.ControladorElementos;
import Controlador.ControladorReservas;
import Entidades.Elemento;
import Entidades.Reservas;

import java.sql.Date;
import java.sql.Time;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class InterfaceAltaReserva extends JInternalFrame {

	private JPanel contentPane;
	private JTextField tfFechayHoraIni;
	private JTextField tfFechayHoraFin;
	private JComboBox cboxTipos;
	private JTable table;
	private JTextField tfElemento;
	private JTextField tfTipo;
	private JLabel lblElemento;
	private JLabel lblTipoElemento;
	private JButton btnReservar;
	private JTextArea taDetalle;
	ControladorElementos cre = new ControladorElementos();
	ControladorReservas crr = new ControladorReservas();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceAltaReserva frame = new InterfaceAltaReserva();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfaceAltaReserva() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Reserva");
		setBounds(100, 100, 570, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tfFechayHoraIni = new JTextField();
		tfFechayHoraIni.setBounds(101, 18, 113, 20);
		tfFechayHoraIni.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(315, 136, 86, 23);
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ShowBuscar();

			}
		});

		JLabel lblFechayHoraIni = new JLabel("Fecha y Hora Inicio");
		lblFechayHoraIni.setBounds(5, 21, 92, 14);

		JLabel lblFechayHoraFin = new JLabel("Fecha y Hora Fin");
		lblFechayHoraFin.setBounds(5, 75, 81, 14);

		tfFechayHoraFin = new JTextField();
		tfFechayHoraFin.setToolTipText("");
		tfFechayHoraFin.setBounds(101, 72, 114, 20);
		tfFechayHoraFin.setColumns(10);

		cboxTipos = new JComboBox();
		cboxTipos.setBounds(5, 110, 102, 20);
		cboxTipos.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				AgregarElementos();

			}
		});
		cboxTipos.setModel(new DefaultComboBoxModel(new String[] { "Tipos Elementos" }));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 177, 484, 150);

		tfElemento = new JTextField();
		tfElemento.setBounds(69, 137, 47, 20);
		tfElemento.setEditable(false);
		tfElemento.setColumns(10);

		tfTipo = new JTextField();
		tfTipo.setBounds(211, 137, 86, 20);
		tfTipo.setEditable(false);
		tfTipo.setColumns(10);

		lblElemento = new JLabel("Elemento");
		lblElemento.setBounds(15, 140, 44, 14);

		lblTipoElemento = new JLabel("Tipo Elemento");
		lblTipoElemento.setBounds(134, 140, 67, 14);

		btnReservar = new JButton("Reservar");
		btnReservar.setBounds(393, 17, 77, 23);
		btnReservar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservarClick();
			}
		});

		JLabel lblNewLabel = new JLabel("Detalle");
		lblNewLabel.setBounds(224, 21, 55, 14);

		taDetalle = new JTextArea();
		taDetalle.setWrapStyleWord(true);
		taDetalle.setBackground(SystemColor.window);
		taDetalle.setLineWrap(true);
		taDetalle.setBounds(289, 16, 86, 59);
		taDetalle.setRows(5);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Agregar();
			}
		});
		contentPane.setLayout(null);
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		contentPane.add(lblElemento);
		contentPane.add(tfElemento);
		contentPane.add(lblTipoElemento);
		contentPane.add(tfTipo);
		contentPane.add(btnBuscar);
		contentPane.add(cboxTipos);
		contentPane.add(lblFechayHoraIni);
		contentPane.add(lblFechayHoraFin);
		contentPane.add(tfFechayHoraIni);
		contentPane.add(lblNewLabel);
		contentPane.add(taDetalle);
		contentPane.add(btnReservar);
		contentPane.add(tfFechayHoraFin);
	}

	protected void ReservarClick() {
		Elemento e = new Elemento();
		Reservas r = new Reservas();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String tipo = tfTipo.getText();
		String usuario = InterfaceLogin.Usuario();
		int cant;
		int cont;
		String elemento = tfElemento.getText();
		java.util.Date FechaHoraIni=null;
		java.util.Date FechaHoraFin=null;
		
			try {
				FechaHoraIni = f.parse(this.tfFechayHoraIni.getText());
				FechaHoraFin = f.parse(this.tfFechayHoraFin.getText());
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
			String detalle = taDetalle.getText();	
			
			e=cre.CantidadMaxReservas(tipo);
			
			cant = e.getCantidad_elemento();
			r = crr.ContarReservas(tipo, usuario);
		
			cont = (r.getCantidadReservas());
			if (cant > cont) {
				crr.ReservarElemento(usuario, FechaHoraIni, FechaHoraFin, elemento, tipo, detalle);
				tfElemento.setText(null);
				tfTipo.setText(null);
				tfFechayHoraFin.setText(null);
				tfFechayHoraIni.setText(null);
				table.removeAll();
			}
			}

	protected void Agregar() {
		int i = table.getSelectedRow();
		if (i != -1) {

			tfElemento.setText(table.getValueAt(i, 0).toString());
			tfTipo.setText(table.getValueAt(i, 1).toString());
		}

	}

	protected void AgregarElementos() {
		ArrayList<Elemento> el = new ArrayList<Elemento>();
		el = cre.AgregarTipos();

		cboxTipos.removeAllItems();

		for (int i = 0; i < el.size(); i++) {

			cboxTipos.addItem(el.get(i).getNombre_elemento());
		}

	}

	protected void ShowBuscar() {

		ArrayList<Reservas> re = new ArrayList<Reservas>();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {

			java.util.Date FechaHoraIni = f.parse(this.tfFechayHoraIni.getText());
			java.util.Date FechaHoraFin = f.parse(this.tfFechayHoraFin.getText());

			Object TipoEl = cboxTipos.getSelectedItem();

			re=crr.ConsultaElementosDisponibles(FechaHoraIni, FechaHoraFin, TipoEl);

		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Falta una fecha y hora");

		}
		DefaultTableModel dfm = new DefaultTableModel();
	
		table.setModel(dfm);
		dfm.setColumnIdentifiers(new Object[] { "Elementos Disponibles", "Tipo Elemento" });

		for (int i=0;i<re.size();i++) {						
			{							
				
				
				dfm.addRow(new Object[]{re.get(i).getElemento(),re.get(i).getTipoElemento(),});
				
				
			}			

			

		}

	}

}
