// package pixelEscape;

// import javax.swing.*;
// import java.awt.event.*;

// import java.awt.*;

// /**
//  * This is the frame contains all different panels
//  *
//  */
// public class Frame_Main extends JFrame {
//     // Constants
//     private final String TITLE = "Game Of Live";

//     // Variables
//     public MenuBar menuBar;
//     public PanelStart startPanel;
//     public PanelGameSetup gameSetupPanel;
//     public ToolBarGameSetup gameSetupToolBar;
    
//     public PanelStatictics staticticsPanel;
//     public PanelMySide mySidePanel;
//     public PanelTimer timerPanel;
//     public PanelEnemySide enemySidePanel;
//     public ToolBarGameRun gameRunToolBar;
    
//     public Frame_Main() {
//         // Initialize
//         menuBar = new MenuBar();
//         startPanel = new PanelStart();
//         gameSetupToolBar = new ToolBarGameSetup();
        
//         // Add continers
//         this.setLayout(new BorderLayout());
//         this.add(startPanel, BorderLayout.CENTER);
//         this.setJMenuBar(menuBar);

//         // Final setting
//         this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//         this.setTitle(TITLE);
//         this.pack();
//         this.setLocationRelativeTo(null);
//         this.setResizable(false);
//         this.setVisible(true);
//     }

//     public void gameSetUp(MouseListener eml, ReadyBListener sbl, String graphics) {
        
//         this.setJMenuBar(null);
//         this.remove(startPanel);
//         gameSetupPanel = new PanelGameSetup(eml, graphics);
//         gameSetupPanel.setPreferredSize(gameSetupPanel.getSize());
//         this.add(gameSetupPanel, BorderLayout.CENTER);
//         gameSetupToolBar = new ToolBarGameSetup();
//         gameSetupToolBar.addReadyBListener(sbl);
//         this.add(gameSetupToolBar, BorderLayout.SOUTH);
        
//         this.invalidate();
//         this.validate();
//         this.repaint();
//         this.pack();
//     }
    
//     public void gameRun(MouseListener eml, FireBListener fbl, String graphics, int timer) {
//         this.remove(gameSetupPanel);
//         this.remove(gameSetupToolBar);
        
//         staticticsPanel = new PanelStatictics();
//         this.add(staticticsPanel, BorderLayout.PAGE_START);
        
//         BattleGrid myGrid = gameSetupPanel.getGrid();
//         mySidePanel = new PanelMySide(myGrid);
//         mySidePanel.setPreferredSize(mySidePanel.getSize());
//         this.add(mySidePanel, BorderLayout.LINE_START);
        
//         timerPanel = new PanelTimer(timer);
//         timerPanel.setPreferredSize(timerPanel.getSize());
//         this.add(timerPanel, BorderLayout.CENTER);
        
//         enemySidePanel = new PanelEnemySide(eml, graphics);
//         enemySidePanel.setPreferredSize(enemySidePanel.getSize());
//         this.add(enemySidePanel, BorderLayout.LINE_END);
        
//         gameRunToolBar = new ToolBarGameRun();
//         gameRunToolBar.addFireBListener(fbl);
//         this.add(gameRunToolBar, BorderLayout.PAGE_END);
        
//         this.invalidate();
//         this.validate();
//         this.repaint();
//         this.pack();
//     }
// }