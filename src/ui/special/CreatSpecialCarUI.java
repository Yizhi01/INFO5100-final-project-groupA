package ui.special;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import org.jdesktop.swingx.JXDatePicker;


public class CreatSpecialCarUI extends JDialog{
	
	private static final long serialVersionUID = -279332465989743454L;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel southPanel;
	private JScrollPane centerScrollPane;
	private GridBagLayout centerLayout;
	private GridBagConstraints centerConstraints;
	private ArrayList<JComboBox> flList;
	
	public CreatSpecialCarUI(JFrame parent) {
		super(parent, true);
		initData();
		initUI();
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		display();
	}

	private void initUI() {
		BorderLayout mainLayout = new BorderLayout();
		getContentPane().setLayout(mainLayout);
		northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(750, 20));
		centerPanel = new JPanel();
		centerScrollPane = new JScrollPane();
		centerScrollPane.setViewportView(centerPanel);
		northPanel.setPreferredSize(new Dimension(750, 400));
		southPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(750, 100));
		JLabel temp1 = new JLabel("                  ");
		JLabel temp2 = new JLabel("      ");
		JLabel temp3 = new JLabel("                  ");
		
		
		{
			GridLayout northLayout = new GridLayout(3, 2);
			northPanel.setLayout(northLayout);
			JLabel title = new JLabel("*Title:");
			JTextField titleText = new JTextField(); 
			JLabel discription = new JLabel("Discription:");
			JTextField discriptionText = new JTextField();
			JLabel disclalmer = new JLabel("Disclalmer:");
			JTextField disclalmerText = new JTextField();
			
			northPanel.add(title);
			northPanel.add(titleText);
			northPanel.add(discription);
			northPanel.add(discriptionText);
			northPanel.add(disclalmer);
			northPanel.add(disclalmerText);
		}
		
		{
			centerPanel.setBorder(BorderFactory.createTitledBorder("DISCOUNT INFORMATION"));
			centerLayout = new GridBagLayout();
			centerPanel.setLayout(centerLayout);
			centerConstraints = new GridBagConstraints();
			centerConstraints.fill = GridBagConstraints.BOTH;
			
			{
				JLabel scope = new JLabel("*Scope:");
				JComboBox scopeContent = new JComboBox(new String[] {"---Select Brand---", 
						"Bentley",
						"BMW", 
						"Aston Martin", 
						"Maybach"});
				
				scopeContent.setSize(new Dimension(10, 30));
				centerPanel.add(scope);
				centerPanel.add(scopeContent);
				centerPanel.add(temp1);
				centerPanel.add(temp2);
				centerPanel.add(temp3);
				add(centerLayout, scope, centerConstraints, 0, 0, 1, 0, 0);
				add(centerLayout, scopeContent, centerConstraints, 1, 0, 1, 1, 0);
				add(centerLayout, temp1, centerConstraints, 0, 0, 1, 2, 0);
				add(centerLayout, temp2, centerConstraints, 0, 0, 1, 3, 0);
				add(centerLayout, temp3, centerConstraints, 0, 0, 1, 4, 0);
			}
			
			
			{
				JLabel discountType = new JLabel("*Discount Type:");
				JComboBox<String> discountTypeContent = new JComboBox<String>();
				JTextField perOff = new JTextField(1);
				perOff.setVisible(false);
				JLabel perOffContent = new JLabel("% off");
				perOffContent.setVisible(false);
				JTextField perStart = new JTextField(1);
				perStart.setVisible(false);
				discountTypeContent.addItem("---Select Type---");
				discountTypeContent.addItem("Percentage Off");
				discountTypeContent.addItem("One Time Cash Back");
				discountTypeContent.setMaximumSize(new Dimension(10, 30));
				centerPanel.add(discountType);
				centerPanel.add(discountTypeContent);
				centerPanel.add(perOff);
				centerPanel.add(perOffContent);
				centerPanel.add(perStart);
				discountTypeContent.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							String text = (String) discountTypeContent.getSelectedItem();
							if(text == "Percentage Off") {
								perOff.setVisible(true);
								perOffContent.setVisible(true);
								perOffContent.setText("% off");
								perStart.setVisible(false);
								
							} else if (text == "One Time Cash Back") {
								perOff.setVisible(true);
								perOffContent.setVisible(true);
								perOffContent.setText("out of");
								
								perStart.setVisible(true);
							}
							else {
								perOff.setVisible(false);
								perOffContent.setVisible(false);
								perStart.setVisible(false);
							}
						}
						
					}
				});
				add(centerLayout, discountType, centerConstraints, 0, 0, 1, 0, 1);
				add(centerLayout, discountTypeContent, centerConstraints, 0, 0, 1, 1, 1);
				add(centerLayout, perOff, centerConstraints, 0, 0, 1, 2, 1);
				add(centerLayout, perOffContent, centerConstraints, 0, 0, 1, 3, 1);
				add(centerLayout, perStart, centerConstraints, 0, 0, 1, 4, 1);
				
			}
			
			
			{
				JLabel startDate = new JLabel("*Start Date:");
				Date dateStart = new Date();
				final JXDatePicker datepickStart = new JXDatePicker();
				datepickStart.setDate(dateStart);
				datepickStart.setMaximumSize(new Dimension(10, 30));
				datepickStart.setBounds(137, 83, 177, 24);
				
				JLabel together = new JLabel("Can this special be used");
				JLabel together2 = new JLabel(" together with others?");
				
				JLabel endDate = new JLabel("*End Date:");
				Date dateEnd = new Date();
				final JXDatePicker datepickEnd = new JXDatePicker();
				datepickEnd.setMaximumSize(new Dimension(0, 30));
				datepickEnd.setDate(dateEnd);
				datepickEnd.setBounds(137, 83, 177, 24);
				
				
				
				ButtonGroup bg = new ButtonGroup();
				JRadioButton yes = new JRadioButton("YES");
				bg.add(yes);
				JRadioButton no = new JRadioButton("NO");
				bg.add(no);
				
				centerPanel.add(startDate);
				centerPanel.add(datepickStart);
				centerPanel.add(together);
				centerPanel.add(together2);
				centerPanel.add(yes);
				centerPanel.add(endDate);
				centerPanel.add(datepickEnd);
				centerPanel.add(no);
				

				
				add(centerLayout, startDate, centerConstraints, 0, 0, 1, 0, 2);
				add(centerLayout, datepickStart, centerConstraints, 0, 0, 1, 1, 2);
				
				
				add(centerLayout, endDate, centerConstraints, 0, 0, 1, 0, 3);
				add(centerLayout, datepickEnd, centerConstraints, 0, 0, 1, 1, 3);
				
				add(centerLayout, together, centerConstraints, 0, 0, 1, 0, 4);
				add(centerLayout, yes, centerConstraints, 0, 0, 0, 1, 4);
				add(centerLayout, together2, centerConstraints, 0, 0, 1, 0, 5);
				add(centerLayout, no, centerConstraints, 0, 0, 0, 1, 5);
				
			}
			
			
		}
		
		{
			GridLayout southLayout = new GridLayout(1, 2);
			southPanel.setLayout(southLayout);
			JButton add = new JButton("Add New Special");
			JButton modify = new JButton("Modify New Special");
			southPanel.add(add);
			southPanel.add(modify);
		}
		
		getContentPane().add(northPanel, BorderLayout.NORTH);
		getContentPane().add(centerScrollPane, BorderLayout.CENTER);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		{
			this.setResizable(true);
			this.setSize(800, 600);
			int windowWidth = this.getWidth();
			int windowHeight = this.getHeight();
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension ScreenSize = kit.getScreenSize();
			int screenWidth = ScreenSize.width;
			int screenHeight = ScreenSize.height;
			this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 + windowHeight / 2);
					
		}

	}

	private void add(GridBagLayout layout, 
					Component c, 
					GridBagConstraints constraints, 
					int weightx, 
					int weighty,
					int gridwidth,
					int gridx,
					int gridy) {
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.gridwidth = gridwidth;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		layout.setConstraints(c, constraints);
	}

	private void initData() {
		
		
	}

	private void display(){
		this.setVisible(true);
	}



}
