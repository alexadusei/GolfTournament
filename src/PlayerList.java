/**NAME: Alex Adusei
 * DATE: Monday, June 4, 2012
 * COURSE CODE: ICS4U1
 * PROGRAM: PlayerList Class *run via Golf Tournament Program*.
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Collections;

public class PlayerList extends JFrame implements ActionListener
{
	//Declare global variables
	private JList jlPlayers;
	private JButton btnRemove, btnAdd, btnSort;
	private DefaultListModel dlm;

	public static String choice;

	public PlayerList() 
	{
		//Declare Components
		JLabel lblTitle = new JLabel("PLAYERS LIST");
		lblTitle.setFont(new Font("Britannic Bold", Font.BOLD, 36));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setPreferredSize(new Dimension(350, 50));

		//Declare DefaultListModel
		dlm = new DefaultListModel();

		//Add combined LinkedList to DLM
		for (int i = 0; i < GolfTournament.golfers.size(); i++)
		{
			dlm.addElement(GolfTournament.golfers.get(i));
		}

		//Set details to JList
		jlPlayers = new JList(dlm);
		jlPlayers.setVisibleRowCount(20);
		jlPlayers.setFont(new Font("Courier New", Font.PLAIN, 12));
		jlPlayers.setFixedCellWidth(300);
		jlPlayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//Declare Components and details
		btnRemove = new JButton("REMOVE");
		btnRemove.setPreferredSize(new Dimension(100, 30));
		btnRemove.setBackground(new Color(0, 100, 0));
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setBorder(BorderFactory.createRaisedBevelBorder());
		btnRemove.addActionListener(this);

		btnAdd = new JButton("ADD");
		btnAdd.setPreferredSize(new Dimension(100, 30));
		btnAdd.setBackground(new Color(0, 100, 0));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBorder(BorderFactory.createRaisedBevelBorder());
		btnAdd.addActionListener(this);

		btnSort = new JButton("SORT");
		btnSort.setPreferredSize(new Dimension(100, 30));
		btnSort.setBackground(new Color(0, 100, 0));
		btnSort.setForeground(Color.WHITE);
		btnSort.setBorder(BorderFactory.createRaisedBevelBorder());
		btnSort.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 100, 0));
		panel.add(lblTitle);
		panel.add(new JScrollPane(jlPlayers, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		panel.add(btnRemove);
		panel.add(btnAdd);
		panel.add(btnSort);

		setContentPane(panel);
		setTitle("Player List");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setSize(350, 460);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		/* If the remove button is clicked when an item is highlighted, the program first checks an item has been selected to begin with.
		 * If not, an error message will appear asking the user to select something. If a selection has been made, the program then
		 * asks the user if he/she really wants to remove said player from the list. If the user says NO, he/she will be return to the JList.
		 * If they click YES, the program checks the gender data to see if it was a male or female, and according to that data, will remove said
		 * player from the respective gender LinkedList along with the combined golfer LinkedList, and from the DLM.
		 */
		if (e.getSource() == btnRemove)
		{
			if (jlPlayers.getSelectedIndex() >= 0)
			{
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove " + 
						(GolfTournament.golfers.get(jlPlayers.getSelectedIndex()).getName()) + "?", "Remove Player", 
						JOptionPane.YES_NO_OPTION);

				if (option == JOptionPane.YES_OPTION)
				{				
					if (GolfTournament.golfers.get(jlPlayers.getSelectedIndex()).getGender().contains("M"))
					{
						GolfTournament.maleGolfers.remove(jlPlayers.getSelectedIndex());
					}
					else if (GolfTournament.golfers.get(jlPlayers.getSelectedIndex()).getGender().contains("F"))
					{
						GolfTournament.femaleGolfers.remove(jlPlayers.getSelectedIndex());
					}

					GolfTournament.golfers.remove(jlPlayers.getSelectedIndex());
					dlm.remove(jlPlayers.getSelectedIndex());
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please make a selection!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		/* If the user wants to add something to the JList, the program first checks if the golfers LinkedList is greater than or equal to 64,
		 * which is the maximum amount of players. If so, it lets the use know he/she cannot add anymore players and they should delete existing
		 * players to add new ones. If this is not the case, the program will ask the user to enter the player's name. The program then checks
		 * if that name already exists. If this is not the case, the program then asks the user to specify the player's gender. The program then
		 * adds the information to the respective gender LinkedList according to the gender the user picks, along with adding it to the combined
		 * golfers LinkedList and the DLM.
		 */
		else if (e.getSource() == btnAdd)
		{
			if (GolfTournament.golfers.size() >= 64)
			{
				JOptionPane.showMessageDialog(null, "You cannot add anymore players. Please remove players" +
						"to add new ones.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				String name = JOptionPane.showInputDialog(null, "Enter player's name:", "Add Player", JOptionPane.INFORMATION_MESSAGE);

				if (name != null)
				{
					if (dlm.contains(name))
					{
						JOptionPane.showMessageDialog(null, name +  " already exists!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Object[] gender = {"Male", "Female"};

						String decision = (String)JOptionPane.showInputDialog(null, "Enter " + name + "'s gender:", "Add Player", 
								JOptionPane.QUESTION_MESSAGE, null, gender, gender[0]);

						if (decision != null)
						{
							if (decision.equals(gender[0])) //MALE
							{
								decision = "M";
								GolfTournament.maleGolfers.add(new Golfer(name, decision));
							}
							else //FEMALE
							{
								decision = "F";
								GolfTournament.femaleGolfers.add(new Golfer(name, decision));
							}

							GolfTournament.golfers.add(new Golfer(name, decision));
							dlm.addElement(GolfTournament.golfers.get(GolfTournament.golfers.size() - 1));
						}
					}
				}
			}
		}
		
		/* When the user clicks the sort button, the program asks how the user wants to sort the data (by Name or by Gender). The program will send
		 * special static data (choice) to the Golfer class and from there the Golfer class will determine which sorting method the user desires 
		 * and will continue its job from there.
		 */
		else
		{
			Object[] options = {"Name", "Gender"};

			choice = (String)JOptionPane.showInputDialog(null, "Sort by...", "sort", JOptionPane.QUESTION_MESSAGE, 
					null, options, options[0]);

			if (choice != null)
			{
				Collections.sort(GolfTournament.golfers); //Sorts the data according to the user's choice

				dlm.removeAllElements(); //Removes all information from DLM

				//Reformats the data into DLM according to sorted data
				for (int i = 0; i < GolfTournament.golfers.size(); i++)
				{
					dlm.addElement(GolfTournament.golfers.get(i));
				}
			}
		}
	}
}