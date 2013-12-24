/**
 * Aura
 */

/**
 * @author Brandon M. Essler
 *
 */

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

public class Aura extends JFrame {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -7172797892494267228L;

	private JLabel statusBar;
	private JPopupMenu popupMenu;
	private Toolkit toolkit;

	/**
	 * Constructor
	 */
	public Aura() {

		initUI();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * JLabel emptyLabel = new JLabel(); JFrame frame = new JFrame("Main");
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame.setSize(100,100); frame.getContentPane().add(emptyLabel,
		 * BorderLayout.CENTER); frame.pack(); frame.setVisible(true);
		 * frame.add(emptyLabel);
		 */

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Aura aura = new Aura();
				aura.setVisible(true);
			}
		});
	}

	private void initUI() {

		setTitle("Aura");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		// Quit Button
		JButton quitButton = new JButton("Quit");
		initQuitButton(quitButton);
		panel.add(quitButton);

		// Menu Bar
		JMenuBar menuBar = new JMenuBar();
		initMenuBar(menuBar);

		// Popup Menu
		popupMenu = new JPopupMenu();
		initPopupMenu();
	}

	private void initQuitButton(JButton button) {

		button.setBounds(getWidth() - 110, getHeight() - 130, 80, 30);
		button.setToolTipText("Quit Aura");

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
	}

	private void initMenuBar(JMenuBar menuBar) {

		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon iconNew = new ImageIcon(
				cl.getResource("images/new_small.png"));
		ImageIcon iconOpen = new ImageIcon(
				cl.getResource("images/open_small.png"));
		ImageIcon iconSave = new ImageIcon(
				cl.getResource("images/save_all_small.png"));
		ImageIcon iconExit = new ImageIcon(
				cl.getResource("images/exit_small.png"));

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		JMenu imp = new JMenu("Import");
		imp.setMnemonic(KeyEvent.VK_M);

		JMenu view = new JMenu("View");
		view.setMnemonic(KeyEvent.VK_V);

		JCheckBoxMenuItem sbar = new JCheckBoxMenuItem("Show StatusBar");
		sbar.setState(true);
		sbar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (statusBar.isVisible()) {
					statusBar.setVisible(false);
				} else {
					statusBar.setVisible(true);
				}
			}
		});

		view.add(sbar);

		JMenuItem newsf = new JMenuItem("Import newsfeed list...");
		JMenuItem bookm = new JMenuItem("Import bookmarks...");
		JMenuItem mail = new JMenuItem("Import mail...");

		imp.add(newsf);
		imp.add(bookm);
		imp.add(mail);

		JMenuItem fileNew = new JMenuItem("New", iconNew);
		fileNew.setMnemonic(KeyEvent.VK_N);

		JMenuItem fileOpen = new JMenuItem("Open", iconOpen);
		fileOpen.setMnemonic(KeyEvent.VK_O);

		JMenuItem fileSave = new JMenuItem("Save", iconSave);
		fileSave.setMnemonic(KeyEvent.VK_S);

		JMenuItem fileExit = new JMenuItem("Exit", iconExit);
		fileExit.setMnemonic(KeyEvent.VK_E);
		fileExit.setToolTipText("Exit Aura");

		fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				ActionEvent.CTRL_MASK));

		fileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		file.add(fileNew);
		file.add(fileOpen);
		file.add(fileSave);
		file.addSeparator();
		file.add(imp);
		file.addSeparator();
		file.add(fileExit);

		menuBar.add(file);
		menuBar.add(view);
		setJMenuBar(menuBar);

		statusBar = new JLabel("Status Bar");
		statusBar.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		add(statusBar, BorderLayout.SOUTH);
	}

	private void initPopupMenu() {

		toolkit = this.getToolkit();

		popupMenu = new JPopupMenu();

		JMenuItem menuItemBeep = new JMenuItem("Beep");
		menuItemBeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolkit.beep();
			}
		});
		popupMenu.add(menuItemBeep);

		JMenuItem menuItemClose = new JMenuItem("Close");
		menuItemClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		popupMenu.add(menuItemClose);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}
}
