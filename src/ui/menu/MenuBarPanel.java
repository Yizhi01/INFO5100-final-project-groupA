package ui.menu;

//import com.sun.tools.javac.comp.Flow;
//import sun.tools.jps.Jps;
import ui.Setting;
import ui.VehicleSearchMainView;
import ui.button.BeautifulButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MenuBarPanel extends JPanel {

    public static BeautifulButton searchDealerButton;
    public static BeautifulButton searchVehicleButton;
    public JPanel buttonsPanel;

    public MenuBarPanel() {
        initialize();
    }

    private void initialize() {

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Setting.MENU_BAR_COLOR);
//        this.setBackground(Setting.MENU_BAR_COLOR);
//        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, Setting.BUTTON_H_GAP, Setting.BUTTON_V_GAP));

        searchDealerButton = new BeautifulButton("src/ui/resources/ddealer");
        searchDealerButton.setBackground(Setting.MENU_BAR_COLOR);
        searchDealerButton.setBorderPainted(false);// border invisible
        searchDealerButton.setNormalIcon();
        searchDealerButton.setToolTipText("Click here to search Dealer ...");

        searchVehicleButton = new BeautifulButton("src/ui/resources/ccar");
        searchVehicleButton.setBackground(Setting.MENU_BAR_COLOR);
        searchVehicleButton.setBorderPainted(false);// border invisible
        searchVehicleButton.InitChangeIcon();
        searchVehicleButton.setToolTipText("Please choose a dealer to search car ...");

        Dimension size = new Dimension(Setting.MENU_BAR_WIDTH, Setting.MAIN_FRAM_HEIGHT);
        this.setPreferredSize(size);
        this.setBackground(Setting.MENU_BAR_COLOR);
        this.setLayout(new GridLayout(2, 1));
        buttonsPanel.add(searchDealerButton);
        buttonsPanel.add(searchVehicleButton);

        JPanel windowControlPanel = new JPanel();
        windowControlPanel.setPreferredSize(new Dimension(Setting.MENU_BAR_WIDTH, 30));
        windowControlPanel.setBackground(Setting.MENU_BAR_COLOR);
        windowControlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
/*
        JButton closeButton = new BeautifulButton("src/ui/resources/close_button", 12, 12);
        closeButton.setBackground(Setting.MENU_BAR_COLOR);
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(new CloseActionListener());

        JButton minimizeButton = new BeautifulButton("src/ui/resources/minimize", 12, 12);
        minimizeButton.setBackground(Setting.MENU_BAR_COLOR);
        minimizeButton.setBorderPainted(false);
        minimizeButton.addActionListener(new MinimizeActionListener()); //暂时有问题

        windowControlPanel.add(closeButton, FlowLayout.LEFT);
        windowControlPanel.add(minimizeButton, FlowLayout.CENTER);
*/
        this.setLayout(new BorderLayout());
        this.setActionListener();
        this.add(windowControlPanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);
    }


    private void setActionListener() {
        searchVehicleButton.addActionListener(new VehicleButtonActionListener());
        searchDealerButton.addActionListener(new DealerButtonActionListener());
    }

    class VehicleButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            searchVehicleButton.setToolTipText("just test test test ...");
            VehicleSearchMainView.mainEastPanel.removeAll();
            VehicleSearchMainView.mainEastPanel.repaint();
            VehicleSearchMainView.searchVehiclePanel.removeAll();
            VehicleSearchMainView.searchVehiclePanel.init(null);
            //VehicleSearchMainView.mainMenuBarPanel.buttonsPanel.setBackground(Setting.MENU_BAR_COLOR);
            VehicleSearchMainView.mainEastPanel.add(VehicleSearchMainView.searchVehiclePanel, BorderLayout.CENTER);
            VehicleSearchMainView.mainEastPanel.repaint();
            VehicleSearchMainView.mainEastPanel.revalidate();
        }
    }

    class DealerButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            searchDealerButton.setToolTipText("Click again to change Dealer ...");
            searchDealerButton.AftChangeIcon();
            searchVehicleButton.InitChangeIcon();
            VehicleSearchMainView.mainEastPanel.removeAll();
            VehicleSearchMainView.mainEastPanel.repaint();
            VehicleSearchMainView.searchDealerPanel.removeAll();
            VehicleSearchMainView.searchDealerPanel.init();
//            VehicleSearchMainView.mainMenuBarPanel.buttonsPanel.setBackground(Setting.MENU_BAR_COLOR);
            VehicleSearchMainView.mainEastPanel.add(VehicleSearchMainView.searchDealerPanel, BorderLayout.CENTER);
            VehicleSearchMainView.mainEastPanel.repaint();
            VehicleSearchMainView.mainEastPanel.revalidate();
        }
    }
/*
// 最小化有问题
    class MinimizeActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (VehicleSearchMainView.mainFrame.getExtendedState() == JFrame.ICONIFIED) {
                // restore
                openFrame();
            } else {
                // minimize
                VehicleSearchMainView.mainFrame.setExtendedState(JFrame.ICONIFIED);
            }

        }
    }

    class CloseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.exit(0);
        }
    }
*/
}

