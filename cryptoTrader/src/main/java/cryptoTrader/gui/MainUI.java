package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
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
	private AlertFactory alertFactory = AlertFactory.getInstance();
	
	private BrokerHandler brokerHandler = new BrokerHandler();
	// ArrayList holding Brokers, starts out empty
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
		
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);
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

	public static void main(String[] args) {
		Login login = Login.getInstance();
	}

	@Override
	/**
	 * Action handler for the main UI.
	 * @param e The current event the program has queued up
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// When the perform trade is selected, check if every row has valid data
		if ("refresh".equals(command)) {
			for (int count = 0; count < dtm.getRowCount(); count++){
					// Requires the user to fill in the Broker name section
					Object traderObject = dtm.getValueAt(count, 0);
					if (traderObject == null) {
						alertFactory.getAlert("emptyTrade", count);
						return;
					}
					String traderName = traderObject.toString();
					
					// Requires the user to fill in the coin list section
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						alertFactory.getAlert("emptyList", count);
						return;
					}
					String[] coinNames = coinObject.toString().split(",");
					
					// Requires the user to choose a strategy or choose none
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						alertFactory.getAlert("emptyStrategy", count);
						return;
					}
					String strategyName = strategyObject.toString();
					
					// Adds the Broker if same name doesn't exist
					boolean addResult = brokerHandler.addBroker(traderName, coinNames, strategyName);
					if (!addResult) {
						alertFactory.getAlert("brokerExist");
						return;
					}
					
					// Debugging line, remove after
					System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
					
	        }
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
