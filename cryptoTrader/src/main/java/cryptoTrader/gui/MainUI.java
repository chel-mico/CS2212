package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import cryptoTrader.utils.BrokerHandler;
import cryptoTrader.utils.DataVisualizationCreator;

public class MainUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainUI instance;
	private JPanel stats, chartPanel, tablePanel;

	private DefaultTableModel dtm;
	private JTable table;

	// Alert Factory to create a pop-up if an error is shown
	private AlertFactory alertFactory;
	private BrokerHandler brokerHandler;
	
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {

		// Set window title
		super("Crypto Trading Tool");

		// Set top bar
		JPanel north = new JPanel();

		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("refresh");
		trade.addActionListener(this);

		JPanel south = new JPanel();
		
		alertFactory = AlertFactory.getInstance();
		brokerHandler = new BrokerHandler();
		
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);
		
		// Adds an actionListener to the table
		table.getModel().addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent event) {
				// Sanity check every cell that gets updated
				if(event.getType() == event.UPDATE) {
					validate(event.getFirstRow(), event.getColumn());
				}
			}
				
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		

		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
//		getContentPane().add(west, BorderLayout.WEST);
	}

	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	/**
	 * This method alerts the user if they leave a cell empty
	 * @param row The row of the cell
	 * @param col The column of the cell
	 * @return The cell's value
	 */
	private Object validate(int row, int col) {
		
		Object selectedObject = dtm.getValueAt(row, col);
		if (selectedObject == null || selectedObject.equals("")) {
			// Different alert based on the type of cell
			table.setRowSelectionInterval(row, row);
			if (col == 0) {
				alertFactory.getAlert("emptyTrade", row);
			}
			else if (col == 1) {
				alertFactory.getAlert("emptyList", row);
			}
			else if (col == 2) {
				alertFactory.getAlert("emptyStrategy", row);
			}
			return null;
		}
		return selectedObject;
	}
	public static void main(String[] args) {
		Login login = Login.getInstance();
	}

	@Override
	/**
	 * Action handler for the main UI's buttons.
	 * @param e The current event the program has queued up
	 */
	public void actionPerformed(ActionEvent e) {
		// Determine what type of command
		String command = e.getActionCommand();
		
		// Array used to create the row
		String[] rowValues = new String[3];
		// When the perform trade is selected, check if every row has valid data
		if ("refresh".equals(command)) {
			brokerHandler = new BrokerHandler();
			for (int row = 0; row < dtm.getRowCount(); row++){
				for (int col = 0; col < dtm.getColumnCount(); col++) {
					Object columnValue = validate(row, col);
					// If the cell is okay, then add it to the array used to create the row
					if (columnValue != null) {
						rowValues[col] = String.valueOf(columnValue);
					}
					// Check the next row if there are errors
					else {
						return;
					}
				}
				// Adds the Broker if same name doesn't exist
				List<String> coinList = new ArrayList<String>();
				coinList = Arrays.asList(rowValues[1].split("\\s*,\\s*"));
				boolean addResult = brokerHandler.addBroker(rowValues[0], coinList.toArray(), rowValues[2]);
				if (!addResult) {
					alertFactory.getAlert("brokerExist");
				}	
			}				
			
			// Debug line, remove this after
			System.out.println(brokerHandler);	
			// Recreate the visual graphs
			stats.removeAll();
			DataVisualizationCreator creator = new DataVisualizationCreator();
			creator.createCharts();
		} 
		// Adds a new table row when button is clicked
		else if ("addTableRow".equals(command)) {
			dtm.addRow(new String[3]);
		} 
		// Removes a selected row when button is clicked
		else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
	}

}
