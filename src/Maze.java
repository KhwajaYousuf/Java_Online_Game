import javax.swing.*;
import java.awt.*;
import java.util.Random;


/**
 * Maze class that extends JFrame to create a maze game.
 */
public class Maze extends JFrame {
	private static final long serialVersionUID = 1L;
	private MazeController controller;
    // Background and UI assets
    /** Background image for the maze */
	private Image backgroundImage;
	
    /** Logo image for the game */
    private Image gameLogo;
    
    /** Second Logo image for the game */
    private Image lostMazeImage;
    
    /** Font image for displaying game statistics */
    private Image gameStatsFont;
    
    /** Image for a scroll in the game */
    private Image finalScrollImage; 
    
    /** Font image for displaying scrolls */
    private Image scrollsFont; 
    
    /** Font image for displaying secrets */
    private Image secretFont; 
    
    /** Font image for collected items */
    private Image collectedFont; 

    // Grid and UI settings
    /** Size of the grid (number of cells) */
    private static final int GRID_SIZE = 9;
    
    /** Size of each cell in pixels */
    private static final int CELL_SIZE = 60;

    /** Text field for player 1's name */
    private JTextField player1TextField;
    
    /** Text field for player 2's name */
    private JTextField player2TextField;
    
    /** Text field for player 3's name */
    private JTextField player3TextField;
    
    /** Text field for player 4's name */
    private JTextField player4TextField;
    
    private JMenuBar menuBar;
    
    


    // Array of tile image paths
    /** Array containing paths to tile images used in the maze */
    private final String[] tileImages = {
        "brick_SE.png",
        "brick_SW.png",
        "brick_Teast.png",
        "brick_Tnorth.png",
        "brick_Tsouth.png",
        "brick_Twest.png",
        "brick_NE.png",
        "brick_NW.png"
    };

    /**
     * Maze constructor. Initializes the frame and sets up the maze.
     */
    public Maze(MazeController controller) {
        setupFrame();  
        loadAssets();  
        this.controller = controller;
        this.menuBar = createMenuBar();
        setJMenuBar(createMenuBar());
        
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout()); 
        backgroundPanel.setPreferredSize(new Dimension(1920, 1080)); 
        
        // Creating a JPanel for the grid
        JPanel gridPanel = createGridPanel();
        
        // Adding chat area panel to the right side
        JPanel chatAreaPanel = createChatAreaPanel();
        
        // GridBag constraints for placing the grid and chat panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        gbc.anchor = GridBagConstraints.LINE_END; 
        gbc.insets = new Insets(-50, 0, 0, 50); 
        backgroundPanel.add(gridPanel, gbc);
        
        gbc.gridx = 1; 
        gbc.gridy = 0; 
        gbc.weightx = 0; 
        gbc.weighty = 1.0; 
        gbc.anchor = GridBagConstraints.CENTER; 
        backgroundPanel.add(chatAreaPanel, gbc);

        add(backgroundPanel, BorderLayout.CENTER);
        
        setJMenuBar(createMenuBar());
        pack(); 
        setVisible(true);
    }

    
    public void setController(MazeController controller) {
        this.controller = controller;
    }

    /**
     * Sets up the JFrame properties for the maze game.
     */
    private void setupFrame() {
        setTitle("Lost Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public JPanel createGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        Random random = new Random();

        // Array of gold coin image paths
        String[] coinImages = new String[] {
            "gold_1.png", "gold_2.png", "gold_3.png", "gold_4.png", "gold_5.png",
            "gold_6.png", "gold_7.png", "gold_8.png", "gold_9.png", "gold_10.png",
            "gold_11.png", "gold_12.png", "gold_13.png", "gold_14.png", "gold_15.png",
            "gold_16.png", "gold_17.png", "gold_18.png", "gold_19.png", "gold_20.png",
            "gold_25.png"
        };
        

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                JButton cellButton = new JButton();
                cellButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellButton.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                cellButton.setLayout(new OverlayLayout(cellButton));

                if ((row >= 2 && row <= 6) && (col >= 2 && col <= 6) && 
                        !((row == 3 && col == 3) || (row == 3 && col == 5) || 
                          (row == 5 && col == 3) || (row == 5 && col == 5))) {
                        // Randomly assign a coin icon from the array to the button
                        String randomCoinImage = coinImages[random.nextInt(coinImages.length)];
                        ImageIcon coinIcon = new ImageIcon(getClass().getResource("/naruto_assets/" + randomCoinImage)); // Use your coin image path here
                        JButton coinButton = new JButton(coinIcon);
                        coinButton.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                        coinButton.setOpaque(false); // Ensure transparency
                        coinButton.setContentAreaFilled(false); // Make sure background is transparent
                        coinButton.setBorderPainted(false); // Remove border
                        cellButton.add(coinButton);
                        
                    }
                
                // Corner and border cells logic
                if ((row == 0 && (col == 0 || col == GRID_SIZE - 1)) || 
                    (row == GRID_SIZE - 1 && (col == 0 || col == GRID_SIZE - 1))) { 
                    ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/brick_full.png"));
                    cellButton.setIcon(tileIcon);
                    
                } else if (row == 0) { 
                    if (col == 0 || col == 1 || col == 3 || col == 5 || col == 7 || col == 8) {
                        ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/brick_full.png"));
                        cellButton.setIcon(tileIcon);
                    } else {
                        ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/insert_down.png"));
                        cellButton.setIcon(tileIcon);
                    }

                } else if (row == GRID_SIZE - 1) { 
                    if (col == 0 || col == 1 || col == 3 || col == 5 || col == 7 || col == 8) {
                        ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/brick_full.png"));
                        cellButton.setIcon(tileIcon);
                    } else {
                        ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/insert_up.png"));
                        cellButton.setIcon(tileIcon);
                    }

                } else if (col == 0) {
                    if (row == 0 || row == 1 || row == 3 || row == 5 || row == 7 || row == 8) {
                        ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/brick_full.png"));
                        cellButton.setIcon(tileIcon);
                    } else {
                        ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/insert_right.png"));
                        cellButton.setIcon(tileIcon);
                    }

                } else if (col == GRID_SIZE - 1) { 
                    if (row == 0 || row == 1 || row == 3 || row == 5 || row == 7 || row == 8) {
                        ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/brick_full.png"));
                        cellButton.setIcon(tileIcon);
                    } else {
                        ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/insert_left.png"));
                        cellButton.setIcon(tileIcon);
                    }

                } else { 
                    // Create a JButton for the random tile
                    JButton randomTileButton = new JButton();
                    String randomTile = tileImages[random.nextInt(tileImages.length)];
                    ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/" + randomTile));
                    randomTileButton.setIcon(tileIcon);
                    randomTileButton.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));

                    // Player Icons
                    String playerIconPath = null;
                    JButton playerButton = new JButton(); // Create a JButton for the player

                    if ((row == 3 && col == 3) || (row == 3 && col == 5) || 
                        (row == 5 && col == 3) || (row == 5 && col == 5)) {
                        
                        if (row == 3 && col == 3) {
                            playerIconPath = "/naruto_assets/player1_icon.png"; 
                        } else if (row == 3 && col == 5) {
                            playerIconPath = "/naruto_assets/player2_icon.png"; 
                        } else if (row == 5 && col == 3) {
                            playerIconPath = "/naruto_assets/player3_icon.png"; 
                        } else if (row == 5 && col == 5) {
                            playerIconPath = "/naruto_assets/player4_icon.png"; 
                        }

                        if (playerIconPath != null) {
                            ImageIcon playerIcon = new ImageIcon(getClass().getResource(playerIconPath));
                            Image resizedImage = playerIcon.getImage().getScaledInstance(CELL_SIZE / 2, CELL_SIZE / 2, Image.SCALE_SMOOTH);
                            ImageIcon resizedPlayerIcon = new ImageIcon(resizedImage);

                            playerButton.setIcon(resizedPlayerIcon); // Set the icon directly on the button
                            playerButton.setLayout(new BorderLayout());
                            playerButton.setContentAreaFilled(false); // Optional: Make button background transparent

                            // Assuming randomTileButton is the container where you want to add the player button
                            randomTileButton.add(playerButton, BorderLayout.CENTER);
                            
                        }
                    }

                    
                    // Add the random tile button to the cellButton
                    cellButton.add(randomTileButton);
                    cellButton.setLayout(new OverlayLayout(cellButton)); // Allow layering
                }

                // Add coins only in a 5x5 grid, skipping player icon locations
                
                gridPanel.add(cellButton);
            }
        }
        gridPanel.setOpaque(false);
        return gridPanel;
    }

    


    /**
     * Creates the chat area panel with player text fields.
     *
     * @return JPanel the panel for the chat area.
     */
    private JPanel createChatAreaPanel() {
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.setPreferredSize(new Dimension(300, 600));
        chatPanel.setBorder(BorderFactory.createTitledBorder("Chatbox"));

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setBackground(new Color(255, 204, 204)); 
        chatArea.setForeground(Color.BLACK);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setPreferredSize(new Dimension(300, 500));
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); 

        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 

        JTextField chatInput = new JTextField();
        chatInput.addActionListener(e -> {
            String message = chatInput.getText();
            if (!message.isEmpty()) {
                String timestamp = java.time.LocalTime.now().toString().substring(0, 5);
                chatArea.append(String.format("%s: %s [%s]%n", "Player", message, timestamp));
                chatInput.setText("");
                chatArea.setCaretPosition(chatArea.getDocument().getLength()); 
            }
        });

        chatPanel.add(scrollPane, BorderLayout.CENTER);
        chatPanel.add(chatInput, BorderLayout.SOUTH);

        return chatPanel;
    }
    

    /**
     * Creates the menu bar for the game.
     *
     * @return JMenuBar the menu bar.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // Settings menu
        JMenu fileMenu1 = new JMenu("Settings");
        JMenuItem settingsItem1 = new JMenuItem("Save Game");
        settingsItem1.addActionListener(e -> controller.handleSaveGame());
        JMenuItem settingsItem2 = new JMenuItem("Restart Game");
        settingsItem2.addActionListener(e -> controller.handleRestartGame());
        JMenuItem settingsItem3 = new JMenuItem("Toggle Music");
        settingsItem3.addActionListener(e -> controller.handleToggleMusic());
        fileMenu1.add(settingsItem1);
        fileMenu1.add(settingsItem2);
        fileMenu1.add(settingsItem3);
        
        // Change Language menu
        JMenu fileMenu2 = new JMenu("Change Language");
        
        // Network menu
        JMenu fileMenu3 = new JMenu("Network");
        
        // Help menu
        JMenu fileMenu4 = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Show Help");
        helpItem.addActionListener(e -> controller.handleShowHelp()); // This calls the controller to show help
        fileMenu4.add(helpItem);
        
        // Insert Tile menu
        JMenu fileMenu5 = new JMenu("INSERT TILE");
        
        // Add all menus to the menu bar
        menuBar.add(fileMenu1);
        menuBar.add(fileMenu2);
        menuBar.add(fileMenu3);
        menuBar.add(fileMenu4); // Help menu with the "Show Help" item
        menuBar.add(fileMenu5);
        
        return menuBar;
    }

    
    public JMenuBar getJMenuBarComponent() {
        return this.menuBar;
    }

    /**
     * Loads assets like images for the game.
     */
    private void loadAssets() {
        backgroundImage = new ImageIcon(getClass().getResource("/naruto_assets/game_bg.png")).getImage();
        gameLogo = new ImageIcon(getClass().getResource("/naruto_assets/game_logo-removebg-preview.png")).getImage();
        lostMazeImage = new ImageIcon(getClass().getResource("/naruto_assets/lost_maze.png")).getImage();
        gameStatsFont = new ImageIcon(getClass().getResource("/naruto_assets/game_stats_font.png")).getImage();
        finalScrollImage = new ImageIcon(getClass().getResource("/naruto_assets/final_scroll.png")).getImage();
        scrollsFont = new ImageIcon(getClass().getResource("/naruto_assets/scrolls_font.png")).getImage();
        secretFont = new ImageIcon(getClass().getResource("/naruto_assets/secret_font.png")).getImage();
        collectedFont = new ImageIcon(getClass().getResource("/naruto_assets/collected_font.png")).getImage();
    }

    /**
     * Background panel class to draw the game background.
     */
    private class BackgroundPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            drawImages(g); 
        }
    }

    /**
     * Draws images onto the game panel.
     * This method uses the provided Graphics object to render images like 
     * the game logo, lost maze stats, and other game assets on the screen.
     *
     * @param g the Graphics object used to draw the images on the panel.
     */
    private void drawImages(Graphics g) {
        int logoX = 3; 
        int logoY = 0; 
        g.drawImage(gameLogo, logoX, logoY, 100, 100, this);

        int lostMazeX = logoX + 110; 
        int lostMazeY = logoY + 20; 
        g.drawImage(lostMazeImage, lostMazeX, lostMazeY, 145, 25, this); 

        int gameStatsX = logoX; 
        int gameStatsY = logoY + 100; 
        g.drawImage(gameStatsFont, gameStatsX, gameStatsY, 150, 50, this); 

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.setColor(Color.WHITE);
        g.drawString("Player 1: " + player1TextField.getText(), logoX, gameStatsY + 70);
        g.drawString("Player 2: " + player2TextField.getText(), logoX, gameStatsY + 100);
        g.drawString("Player 3: " + player3TextField.getText(), logoX, gameStatsY + 130);
        g.drawString("Player 4: " + player4TextField.getText(), logoX, gameStatsY + 160);

        int finalScrollX = logoX; 
        int finalScrollY = gameStatsY + 200; 
        g.drawImage(finalScrollImage, finalScrollX, finalScrollY, 300, 250, this); 

        int scrollsFontY = finalScrollY + 50; 
        int scrollsFontX = finalScrollX + 56; 
        g.drawImage(scrollsFont, scrollsFontX, scrollsFontY, 100, 30, this); 

        int secretFontY = scrollsFontY + 50; 
        g.drawImage(secretFont, scrollsFontX, secretFontY, 100, 30, this); 

        int collectedFontY = secretFontY + 50; 
        g.drawImage(collectedFont, scrollsFontX, collectedFontY, 100, 30, this); 
    }

    /**
     * Creates a player text field.
     *
     */
    private void setupPlayerTextFields() {
        player1TextField = new JTextField(15);
        player2TextField = new JTextField(15);
        player3TextField = new JTextField(15);
        player4TextField = new JTextField(15);
    }


    /**
     * Main method to start the Maze game.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the model first
            
            // Create the view (Maze UI) by passing null for the controller for now
            Maze ui = new Maze(null);  // Temporarily pass null, we'll set the controller later
            MazeModel model = new MazeModel(ui); // Initialize the model

            // Create the controller, now that the view is initialized
            MazeController controller = new MazeController(model, ui);
            
            // Set the controller in the view
            ui.setController(controller);  // Set the controller reference in the view
            
            // Set up additional UI elements
            ui.setupPlayerTextFields();  // Assuming this method exists in Maze class
            
            // Make the view visible after everything is set up
            ui.setVisible(true);
        });
    }
}