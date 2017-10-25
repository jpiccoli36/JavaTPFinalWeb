package Interface;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controlador.ControladorElementos;
import Entidades.Elemento;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class InterfaceConsultarTodosTiposElementos extends JInternalFrame {

	private JPanel contentPane;
	ControladorElementos cre = new ControladorElementos();

	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceConsultarTodosTiposElementos frame = new InterfaceConsultarTodosTiposElementos();
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
	public InterfaceConsultarTodosTiposElementos() {
		setTitle("Buscar Todos Tipo Elementos");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarClick();
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnBuscar)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 413,
										GroupLayout.PREFERRED_SIZE))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(11).addComponent(btnBuscar).addGap(11)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)));

		table = new JTable();
		scrollPane_1.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	protected void buscarClick() {

		DefaultTableModel dfm = new DefaultTableModel();
		table = this.table;
		table.setModel(dfm);
		ArrayList<Elemento> el = cre.ConsultaTodosTiposElementos();

		dfm.setColumnIdentifiers(new Object[] { "ID", "Nombre", "Cantidad" });
		el = cre.ConsultaTodosTiposElementos();

		for (int i = 0; i < el.size(); i++) {
			dfm.addRow(new Object[] { el.get(i).getId_elemento(), el.get(i).getNombre_elemento(),
					el.get(i).getCantidad_elemento() });

		}

	}

}
