/**NAME: Alex Adusei
 * PROGRAM: Golf Tournament Program.
 */

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GolfTournament extends JFrame implements ActionListener 
{
	
	private JTextArea[]txtTeams;
	private JButton btnPlayers, btnGenerate, btnSave, btnClear, btnExit, btnView;
	private JFileChooser fc, fc1;
	private File textFile, docFile;
	private boolean cleared, fileCreated;

	public static LinkedList<Golfer> golfers, maleGolfers, femaleGolfers;

	public static void main(String[] args) 
	{
		new GolfTournament();
	}

	public GolfTournament() 
	{
		//Boolean to check if the data has been cleared. If it has, there is no point saving data when the user exits (no data to save).
		cleared = true; 

		//Declare 3 LinkedLists; one for males, one for females, and one for the combined two.
		golfers = new LinkedList<Golfer>();
		maleGolfers = new LinkedList<Golfer>();
		femaleGolfers = new LinkedList<Golfer>();

		//Declare JFileChoosers according to specific data types
		fc = new JFileChooser("C:\\Users\\user\\Desktop\\Computer Studies\\Eclipse~\\Workspace\\UNIT 4\\Golf Tournament");
		fc1 = new JFileChooser("C:\\Users\\user\\Desktop\\Computer Studies\\Eclipse~\\Workspace\\UNIT 4\\Golf Tournament");
		
		//Declare filters according to specific JFileChooser types
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Text Document", "txt"));
		fc1.addChoosableFileFilter(new FileNameExtensionFilter("Word Document", "doc"));

		//Declare components and their settings
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		mainPanel.setBackground(new Color(0, 100, 0));

		JLabel lblTitle = new JLabel();
		lblTitle.setPreferredSize(new Dimension(675, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Britannic Bold", Font.BOLD, 36));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setText("CAMPION GOLF TOURNAMENT");

		mainPanel.add(lblTitle);

		JPanel teamPanel = new JPanel();
		teamPanel.setLayout(new GridLayout(4, 4, 10, 10));
		teamPanel.setBackground(new Color(0, 100, 0));

		//Declare array of JTextAreas
		txtTeams = new JTextArea[16];

		//Set data settings for JTextAreas
		for (int i = 0; i < txtTeams.length; i++)
		{
			txtTeams[i] = new JTextArea();
			txtTeams[i].setPreferredSize(new Dimension(120, 105));
			txtTeams[i].setEditable(false);
			txtTeams[i].setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtTeams[i].setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), 
					"Team #" + (i + 1), TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, 
					new Font("Arial", Font.BOLD, 12), Color.BLACK));
			teamPanel.add(txtTeams[i]);
		}

		//Declare and set more components
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setBackground(new Color(0, 100, 0));

		Dimension d = new Dimension(120, 40);

		btnPlayers = new JButton();
		btnPlayers.setText("GET PLAYERS");
		btnPlayers.setPreferredSize(d);
		btnPlayers.setMinimumSize(d);
		btnPlayers.setMaximumSize(d);
		btnPlayers.setBackground(new Color(0, 100, 0));
		btnPlayers.setAlignmentX(CENTER_ALIGNMENT);
		btnPlayers.setForeground(Color.WHITE);
		btnPlayers.setFocusable(false);
		btnPlayers.setBorder(BorderFactory.createRaisedBevelBorder());
		btnPlayers.addActionListener(this);

		btnView = new JButton("VIEW PLAYERS");
		btnView.setPreferredSize(d);
		btnView.setMinimumSize(d);
		btnView.setMaximumSize(d);
		btnView.setBackground(new Color(0, 100, 0));
		btnView.setAlignmentX(CENTER_ALIGNMENT);
		btnView.setForeground(Color.WHITE);
		btnView.setFocusable(false);
		btnView.setBorder(BorderFactory.createRaisedBevelBorder());
		btnView.addActionListener(this);

		btnGenerate = new JButton();
		btnGenerate.setText("GENERATE");
		btnGenerate.setPreferredSize(d);
		btnGenerate.setMinimumSize(d);
		btnGenerate.setMaximumSize(d);
		btnGenerate.setBackground(new Color(0, 100, 0));
		btnGenerate.setAlignmentX(CENTER_ALIGNMENT);
		btnGenerate.setForeground(Color.WHITE);
		btnGenerate.setFocusable(false);
		btnGenerate.setBorder(BorderFactory.createRaisedBevelBorder());
		btnGenerate.addActionListener(this);
		btnGenerate.setEnabled(false);

		btnSave = new JButton();
		btnSave.setText("SAVE TEAMS");
		btnSave.setPreferredSize(d);
		btnSave.setMinimumSize(d);
		btnSave.setMaximumSize(d);
		btnSave.setBackground(new Color(0, 100, 0));
		btnSave.setAlignmentX(CENTER_ALIGNMENT);
		btnSave.setForeground(Color.WHITE);
		btnSave.setFocusable(false);
		btnSave.setBorder(BorderFactory.createRaisedBevelBorder());
		btnSave.addActionListener(this);
		btnSave.setEnabled(false);

		btnClear = new JButton();
		btnClear.setText("CLEAR");
		btnClear.setPreferredSize(d);
		btnClear.setMinimumSize(d);
		btnClear.setMaximumSize(d);
		btnClear.setBackground(new Color(0, 100, 0));
		btnClear.setAlignmentX(CENTER_ALIGNMENT);
		btnClear.setForeground(Color.WHITE);
		btnClear.setFocusable(false);
		btnClear.setBorder(BorderFactory.createRaisedBevelBorder());
		btnClear.addActionListener(this);

		btnExit = new JButton();
		btnExit.setText("EXIT");
		btnExit.setPreferredSize(d);
		btnExit.setMinimumSize(d);
		btnExit.setMaximumSize(d);
		btnExit.setBackground(new Color(0, 100, 0));
		btnExit.setAlignmentX(CENTER_ALIGNMENT);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFocusable(false);
		btnExit.setBorder(BorderFactory.createRaisedBevelBorder());
		btnExit.addActionListener(this);

		ImageIcon imgLogo = new ImageIcon("images\\EC_Logo.png");
		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(imgLogo);
		lblLogo.setPreferredSize(new Dimension(120, 164));
		lblLogo.setMinimumSize(new Dimension(120, 164));
		lblLogo.setMaximumSize(new Dimension(120, 164));
		lblLogo.setAlignmentX(CENTER_ALIGNMENT);

		sidePanel.add(btnPlayers);
		sidePanel.add(Box.createVerticalStrut(10));
		sidePanel.add(btnView);
		sidePanel.add(Box.createVerticalStrut(10));
		sidePanel.add(btnGenerate);
		sidePanel.add(Box.createVerticalStrut(10));
		sidePanel.add(btnSave);
		sidePanel.add(Box.createVerticalStrut(10));
		sidePanel.add(btnClear);
		sidePanel.add(Box.createVerticalStrut(10));
		sidePanel.add(btnExit);
		sidePanel.add(Box.createVerticalStrut(10));
		sidePanel.add(lblLogo);

		mainPanel.add(teamPanel);
		mainPanel.add(sidePanel);

		setContentPane(mainPanel);
		setSize(675, 575);
		setTitle("EC Golf Tournament");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new closeListener());
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		/* If the user clicks the GET PLAYERS button, the JFileChooser will appear and allow the user to choose a file (text documents only).
		 * When the user selects desired file, the GENERATE button will be enabled. 
		 */
		if (e.getSource() == btnPlayers)
		{
			int result = fc.showOpenDialog(this);

			if (result == JFileChooser.APPROVE_OPTION)
			{
				textFile = new File(fc.getSelectedFile().toString());

				btnGenerate.setEnabled(true);
			}

			/* The data from the text file will be read and entered into the LinkedLists according to the gender of the players. Males go into the
			 * male LinkedList and females into the female LinkedList.
			 */
			if (textFile != null)
			{
				try
				{
					BufferedReader in = new BufferedReader(new FileReader(textFile));

					String line;
					String[] data;

					line = in.readLine();

					while (line != null)
					{
						data = line.split(",");

						if (data[1].equals("M"))
						{
							maleGolfers.add(new Golfer(data[0], data[1]));
						}
						else if (data[1].equals("F"))
						{
							femaleGolfers.add(new Golfer(data[0], data[1]));
						}

						line = in.readLine();
					}

					in.close();

				}
				catch(IOException ex)
				{
					JOptionPane.showMessageDialog(null, "IOException: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			/* When the entered data is complete for both gender LinkedLists, they will both be combined into the LinkedLists for all golfers, 
			 * which will then be shuffled for variation for as many times as the use CLEARS and reselects the text file.
			 */
			golfers.addAll(maleGolfers);
			golfers.addAll(femaleGolfers);

			Collections.shuffle(golfers);
		}
		
		//Click the VIEW PLAYERS button will activate the PlayerList class and bring the player to the JList
		else if (e.getSource() == btnView)
		{
			new PlayerList();
		}
		
		/* After the user selects the text file, the GENERATE button will activate, allowing the user to generate players into teams and output
		 * them to the JTextAreas. The data is split according to gender (max 4 players per team, 2 males, 2 females) with the gender-separated 
		 * LinkedLists. If there are extra players, the last few teams will be evened out with a possible uneven amount of males or females on
		 * the team. LinkedLists work with a boolean called 'male'. When 'male' is true, the first male in the male LinkedList will be added
		 * to a JTextArea, and then removed from the LinkedList to avoid repetition. Then the 'male' boolean will be false, meaning a the first
		 * female from the female LinkedList will be placed into a JTextArea and removed for repetition, and the cycle repeats until the 
		 * LinkedLists are empty, to which the loop will break, unless there are extra players being generated.
		 */
		else if (e.getSource() == btnGenerate)
		{
			Collections.shuffle(maleGolfers);
			Collections.shuffle(femaleGolfers);

			int num = 4;

			for (int i = 0; i < txtTeams.length; i++)
			{
				boolean male = true; //false = Female, true = Male

				if (golfers.size() == 61)
				{
					if (i >= 13)
					{
						num = 3;
					}
				}
				else if (golfers.size() == 62)
				{
					if (i >= 14)
					{
						num = 3;
					}
				}
				else if (golfers.size() == 63)
				{
					if (i >= 15)
					{
						num = 3;
					}
				}

				for (int k = 0; k < num; k++)
				{
					if (male)
					{
						try
						{
							txtTeams[i].append(maleGolfers.getFirst().getName() + "\n");
							male = false;
							maleGolfers.remove();
						}
						catch(NoSuchElementException ex)
						{
							if (golfers.size() <= 60)
							{
								break;
							}
							else
							{
								txtTeams[i].append(femaleGolfers.getFirst().getName() + "\n");
								femaleGolfers.remove();
							}
						}
					}
					else if (!male)
					{
						try
						{
							txtTeams[i].append(femaleGolfers.getFirst().getName() + "\n");
							male = true;
							femaleGolfers.remove();
						}
						catch(NoSuchElementException ex)
						{
							if (golfers.size() <= 60)
							{
								break;
							}
							else
							{
								txtTeams[i].append(maleGolfers.getFirst().getName() + "\n");
								maleGolfers.remove();
							}
						}
					}
				}
			}

			/* GENERATE button set to false to prevent unnecessary regenerating. SAVE button now enabled to save data into a document file.
			 * The 'cleared' boolean is now set to false, so that when the user exits, the data will be re-saved to the document file (since there
			 * is now data to be saved).
			 */
			btnGenerate.setEnabled(false);
			btnSave.setEnabled(true);
			
			cleared = false;
		}
		
		/* The SAVE button becomes enabled after GENERATE is clicked. Another JFileChooser to save a file rather than open it is enabled. The filter
		 * is set to only see and save document files. The boolean 'fileCreated' is then set to true so that the program knows that a file has been
		 * created for the first time during the program's execution. This is to prevent the program from saving when the user exits, even if
		 * 'cleared' is set to false, because there might be data to save, but if no file was made originally to save it, then the program would
		 * malfunction.
		 */
		else if (e.getSource() == btnSave)
		{
			int result = fc1.showSaveDialog(this);

			if (result == JFileChooser.APPROVE_OPTION)
			{
				docFile = new File(fc1.getSelectedFile().toString() + ".doc");

				try
				{
					docFile.createNewFile();
					fileCreated = true;
				} 
				catch (IOException ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage() + "!", "Error", JOptionPane.ERROR_MESSAGE);
				}

				// The inner method 'save()' writes the created document file (see below)
				save();

				btnGenerate.setEnabled(true); //Set the GENERATE button to enabled again, for continuous use
			}
		}
		
		/* If the user clicks the CLEAR button, all LinkedLists will be cleared, which allows the DLM to clear itself. This makes it so that
		 * clicking VIEW PLAYERS will reveal no players on the JList. SAVE button will be disabled, to prevent saving nothing or clearing already
		 * saved data. JTextFields will be cleared. 'cleared' boolean will be set to true to indicate that the data has been cleared (which will
		 * prevent the program from saving should the user exit).
		 */
		else if (e.getSource() == btnClear)
		{
			for (int i = 0; i < txtTeams.length; i++)
			{
				txtTeams[i].setText("");
			}
			
			maleGolfers.clear();
			femaleGolfers.clear();
			golfers.clear();
			
			btnSave.setEnabled(false);
			cleared = true;
		}
		
		/* EXIT and CLOSE buttons initiate the program to ask if the user wants to exit. Certain conditions must be met for the program to save
		 * to the document file, otherwise the program will exit without saving unnecessarily.
		 */
		else
		{
			int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", 
					"EC Golf Tournament", JOptionPane.YES_NO_OPTION);

			if (answer == JOptionPane.YES_OPTION)
			{
				if (!cleared && fileCreated)
				{
					save();
				}
				
				System.exit(0);
			}
		}
	}

	//Make a nested class for the top exit button of the frame without implementing unnecessary methods
	class closeListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			btnExit.doClick(); //Repeat execution of exit button, which initiates termination of program
		}
	};
	
	/* The inner method 'save()' writes to the created document file. The file splits the data into its respective teams with the players on
	 * said team. If there is no 'Team #16', the for loop will break before that piece of information can be written.
	 */
	public void save()
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(docFile));

			for (int i = 0; i < txtTeams.length; i++)
			{			
				if (txtTeams[i].getText().equals(""))
				{
					break;
				}
				else
				{
					out.write("TEAM #" + (i + 1));
					out.newLine();
					out.newLine();
					out.write(txtTeams[i].getText());

					out.newLine();
				}
			}

			out.close();

		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "IOException: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}