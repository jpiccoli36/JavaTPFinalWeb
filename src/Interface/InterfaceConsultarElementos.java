package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorUsuario;
import Controlador.ControladorElementos;

import Entidades.Elemento;

import javax.swing.event.PopupMenuEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InterfaceConsultarElementos extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cboxTipos;
	ControladorElementos cre = new ControladorElementos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceConsultarElementos frame = new InterfaceConsultarElementos();
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
	public InterfaceConsultarElementos() {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Consultar Elementos");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		cboxTipos = new JComboBox();
		cboxTipos.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				AgregarTipos();
			}
		});
		cboxTipos.setModel(new DefaultComboBoxModel(new String[] { "Tipo Elementos" }));

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BuscarElementos();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
								.createSequentialGroup().addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
												.addComponent(cboxTipos, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(87).addComponent(btnBuscar))
										.addGroup(gl_contentPane.createSequentialGroup().addGap(22).addComponent(
												scrollPane, GroupLayout.PREFERRED_SIZE, 381,
												GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(21, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(25)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cboxTipos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscar))
						.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addGap(27)));

		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	protected void BuscarElementos() {

	
		Object TipoEl = cboxTipos.getSelectedItem();
		ArrayList<Elemento> el = cre.ConsultaTodosTiposElementos();
		el = cre.ConsultaTodosElementos(TipoEl);
		DefaultTableModel dfm= new DefaultTableModel();	
		table = this.table;
		table.setModel(dfm);		
		dfm.setColumnIdentifiers(new Object[]{"ID Elemento","Nombre Elemento","Tipo Elemento"});				
		Object[] id = el.toArray();

		for (int i = 0; i < el.size(); i++) {
			dfm.addRow(new Object[] { el.get(i).getIdElementosReserva(), el.get(i).getNombreElementoReserva(),
					el.get(i).getTipoElementoReserva() });
		}				
						}						
						
					

	
				

	protected void AgregarTipos() {
		ArrayList<Elemento> el = new ArrayList<Elemento>();	
		el = cre.AgregarTipos();

		cboxTipos.removeAllItems();


		for (int i = 0; i < el.size(); i++) {
			
				
				cboxTipos.addItem(el.get(i).getNombre_elemento());
			}

	}
}
