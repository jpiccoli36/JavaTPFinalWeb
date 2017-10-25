package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorReservas;

import Entidades.Reservas;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InterfaceCancelarReservaUsuarios extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfIDReserva;
	ControladorReservas crr = new ControladorReservas();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceCancelarReservaUsuarios frame = new InterfaceCancelarReservaUsuarios();
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
	public InterfaceCancelarReservaUsuarios() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Cancelar Reserva Usuario");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnBuscarReservas = new JButton("Buscar Reservas");
		btnBuscarReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BuscarReservas();
			}
		});

		JButton btnCancelarReserva = new JButton("Cancelar Reserva");
		btnCancelarReserva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CancelarReservaUsuario();
			}
		});

		tfIDReserva = new JTextField();
		tfIDReserva.setEnabled(false);
		tfIDReserva.setColumns(10);

		JLabel lblIdReserva = new JLabel("ID Reserva");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(scrollPane,
								GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnBuscarReservas)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGap(32)
								.addComponent(lblIdReserva).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tfIDReserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCancelarReserva).addPreferredGap(ComponentPlacement.RELATED)))
				.addContainerGap(18, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(btnBuscarReservas)
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnCancelarReserva)
						.addComponent(lblIdReserva).addComponent(tfIDReserva, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SeleccionarReserva();
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	protected void SeleccionarReserva() {
		int i = table.getSelectedRow();
		if (i != -1) {

			tfIDReserva.setText(table.getValueAt(i, 0).toString());

		}

	}

	protected void CancelarReservaUsuario() {
		
		int idRerserva = Integer.parseInt(tfIDReserva.getText());
		crr.CancelarReserva(idRerserva);
		JOptionPane.showMessageDialog(null, "Reserva Cancelada");

	}

	protected void BuscarReservas() {
		ArrayList<Reservas> re = new ArrayList<Reservas>();
		String usuario = InterfaceLogin.Usuario();
		re = crr.ConsultaTodosReservasUsuario(usuario);
		DefaultTableModel dfm = new DefaultTableModel();
		table = this.table;
		table.setModel(dfm);
		dfm.setColumnIdentifiers(new Object[] { "ID Reserva", "Usuario", "Elemento ", "Tipo Elemento", "Detalle" });

		for (int i = 0; i < re.size(); i++) {
			{
				dfm.addRow(new Object[] { re.get(i).getIdreservas(), re.get(i).getUsuario(), re.get(i).getElemento(),
						re.get(i).getTipoElemento(), re.get(i).getDetalle() });
			}
		}
	}
}
