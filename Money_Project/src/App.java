import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class App {

	private JFrame frame;
	private JTextField ID;
	private JPasswordField password;
	private final String User_ID = "chung0513";
	private final String User_PASS = "1234";
	private JPanel currPanel;
	private JTextField Name_txt;
	private JTextField Amount_txt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		TableData td = new TableData();

		// frame 생성
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// logInPanel 생성
		ImagePanel loginPanel = new ImagePanel(
				new ImageIcon("C:\\javacode\\space\\Money\\Money_Project\\picture\\theme.jpg").getImage());
		currPanel = loginPanel;
		// translationPanel 생성
		ImagePanel tranPanel = new ImagePanel(
				new ImageIcon("C:\\javacode\\space\\Money\\Money_Project\\picture\\Activation.jpg").getImage());

		frame.setSize(loginPanel.getDim());
		frame.setPreferredSize(loginPanel.getDim());

		// SummaryPanel 생성
		ImagePanel sumPanel = new ImagePanel(
				new ImageIcon("C:\\javacode\\space\\Money\\Money_Project\\picture\\Activation.jpg").getImage());
		frame.getContentPane().add(sumPanel);
		sumPanel.setVisible(false);

		// 누르면 translation으로 가는 버튼 (trainBtn)
		JButton trainBtn = new JButton();
		trainBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				tranPanel.setVisible(true);
				currPanel = tranPanel;
			}
		});

		trainBtn.setIcon(new ImageIcon("C:\\javacode\\space\\Money\\Money_Project\\picture\\Transaction200.jpg"));
		trainBtn.setBorder(null);
		trainBtn.setBounds(22, 137, 196, 31);
		sumPanel.add(trainBtn);

		JLabel Search = new JLabel("Search : ");
		Search.setFont(new Font("굴림", Font.BOLD, 20));
		Search.setBounds(255, 92, 132, 31);
		sumPanel.add(Search);

		JTextField searchInput = new JTextField();
		searchInput.setFont(new Font("굴림", Font.BOLD, 20));
		searchInput.setBounds(354, 92, 777, 31);
		sumPanel.add(searchInput);
		searchInput.setColumns(10);

		JPanel tp = new JPanel();
		tp.setBounds(255, 158, 877, 283);
		sumPanel.add(tp);

		table = new JTable(td);
		table.setBounds(255, 158, 853, 263);
		table.setRowHeight(30);
		table.setFont(new Font("Sansserif", Font.BOLD, 15));
		table.setPreferredScrollableViewportSize(new Dimension(853, 248));
		tp.add(new JScrollPane(table));
		tp.setOpaque(false);

		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(92, 179, 255));
		header.setForeground(new Color(255, 255, 255));
		header.setFont(new Font("Sansserif", Font.BOLD, 15));

		searchInput.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String search = searchInput.getText();
				TableRowSorter<AbstractTableModel> trs = new TableRowSorter<>(td);
				table.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(search));
			}
		});

		frame.getContentPane().add(tranPanel);

		tranPanel.setVisible(false);
		frame.getContentPane().add(loginPanel);

		// ID TextField
		ID = new JTextField();
		ID.setBounds(936, 236, 209, 32);
		ID.setFont(new Font("굴림", Font.BOLD, 15));
		loginPanel.add(ID);
		ID.setBorder(null);
		ID.setColumns(10);

		// password TextField
		password = new JPasswordField();
		password.setBounds(936, 294, 209, 32);
		password.setFont(new Font("굴림", Font.BOLD, 15));
		loginPanel.add(password);
		password.setBorder(null);

		// CheckBox
		JCheckBox RememberBox = new JCheckBox("");
		RememberBox.setBounds(893, 331, 21, 21);
		loginPanel.add(RememberBox);

		// Login Button
		JButton LoginBtn = new JButton("");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (User_ID.equals(ID.getText()) && User_PASS.equals(password.getText())) {
					JOptionPane.showMessageDialog(null, "로그인 성공.");
					currPanel.setVisible(false);
					sumPanel.setVisible(true);
					currPanel = sumPanel;
				} else {
					JOptionPane.showMessageDialog(null, "아이디나 비밀번호가 틀립니다.");
				}
			}
		});

		LoginBtn.setIcon(new ImageIcon("C:\\javacode\\space\\Money\\Money_Project\\picture\\button.jpg"));
		LoginBtn.setBounds(893, 353, 255, 30);
		LoginBtn.setBorder(null);
		LoginBtn.setPressedIcon(
				new ImageIcon("C:\\\\javacode\\\\space\\\\Money\\\\Money_Project\\\\picture\\\\btnClicked.jpg"));
		loginPanel.add(LoginBtn);

		// 누르면 Summary로 가는 버튼 (SumBtn)
		JButton SumBtn = new JButton("");
		SumBtn.setIcon(new ImageIcon("C:\\javacode\\space\\Money\\Money_Project\\picture\\Summary200.jpg"));
		SumBtn.setBorder(null);
		SumBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				sumPanel.setVisible(true);
				currPanel = sumPanel;
			}
		});

		SumBtn.setBounds(22, 93, 195, 31);
		tranPanel.add(SumBtn);

		JLabel Name_lb = new JLabel("Name");
		Name_lb.setFont(new Font("굴림", Font.BOLD, 30));
		Name_lb.setBounds(306, 93, 133, 31);
		tranPanel.add(Name_lb);

		JLabel Type_lb = new JLabel("Type");
		Type_lb.setFont(new Font("굴림", Font.BOLD, 30));
		Type_lb.setBounds(306, 176, 133, 31);
		tranPanel.add(Type_lb);

		JLabel Amount_lb = new JLabel("Amount");
		Amount_lb.setFont(new Font("굴림", Font.BOLD, 30));
		Amount_lb.setBounds(306, 260, 133, 31);
		tranPanel.add(Amount_lb);

		JLabel Note_lb = new JLabel("Note");
		Note_lb.setFont(new Font("굴림", Font.BOLD, 30));
		Note_lb.setBounds(306, 334, 133, 31);
		tranPanel.add(Note_lb);

		Name_txt = new JTextField();
		Name_txt.setFont(new Font("굴림", Font.BOLD, 20));
		Name_txt.setBounds(454, 93, 236, 31);
		tranPanel.add(Name_txt);
		Name_txt.setColumns(10);

		String[] typeArr = new String[] { "Withdraw", "Deposit" };
		JComboBox Type_combo = new JComboBox(typeArr);
		Type_combo.setFont(new Font("굴림", Font.BOLD, 20));
		Type_combo.setBounds(453, 176, 180, 31);
		tranPanel.add(Type_combo);
		Type_combo.setBackground(Color.WHITE);

		Amount_txt = new JTextField();
		Amount_txt.setFont(new Font("굴림", Font.BOLD, 20));
		Amount_txt.setColumns(10);
		Amount_txt.setBounds(472, 260, 210, 31);
		tranPanel.add(Amount_txt);

		JTextArea note_txt = new JTextArea();
		note_txt.setFont(new Font("Monospaced", Font.BOLD, 20));
		note_txt.setBounds(472, 334, 236, 113);
		tranPanel.add(note_txt);
		note_txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JButton Submit_btn = new JButton("Submit");

		Submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean FileExists = new File("./data.csv").exists();
					FileWriter fw = new FileWriter("./data.csv", true);
//					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./data.csv"), "MS949"));
					if (!FileExists) {
						fw.append("Name, Type, Amount, Note\n");
					}

					String name = Name_txt.getText();
					String type = (String) Type_combo.getSelectedItem();
					String amount = Amount_txt.getText();
					String note = note_txt.getText();
					fw.append(name + "," + type + "," + amount + "," + note + "\n");
					Name_txt.setText("");
					Type_combo.setSelectedIndex(0);
					Amount_txt.setText("");
					note_txt.setText("");
					JOptionPane.showMessageDialog(null, "성공적으로 데이터가 입력되었습니다.");
					fw.close();
					td.refresh();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "오류 발생");
				}
			}
		});

		Submit_btn.setFont(new Font("굴림", Font.BOLD, 25));
		Submit_btn.setBounds(862, 389, 171, 54);
		tranPanel.add(Submit_btn);
		
		frame.pack();
	}
}
