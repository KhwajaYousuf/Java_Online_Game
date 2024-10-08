import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Maze extends JFrame {

    private Image backgroundImage;
    private Image gameLogo;
    private Image lostMazeImage;
    private Image gameStatsFont;
    private Image finalScrollImage; 
    private Image scrollsFont; 
    private Image secretFont; 
    private Image collectedFont; 

    private static final int GRID_SIZE = 9;
    private static final int CELL_SIZE = 60;

    private JTextField player1TextField;
    private JTextField player2TextField;
    private JTextField player3TextField;
    private JTextField player4TextField;
    private final ImageIcon[] coinImages = new ImageIcon[20];

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

    public Maze() {
        setupFrame();  
        loadAssets();  
        
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout()); 
        backgroundPanel.setPreferredSize(new Dimension(1920, 1080)); 
        
        // Creating a JPanel for the grid
        JPanel gridPanel = createGridPanel();
        
        // Adding chat area panel to the right side
        JPanel chatAreaPanel = createChatAreaPanel();
        
        // Adding grid panel to background panel with adjusted constraints
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


    private void setupFrame() {
        setTitle("Wizard's Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private JPanel createGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        int[] coinNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
                             11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25};
        Random random = new Random();
        int coinIndex = 0; // To index through the coinNumbers array

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                JButton cellButton = new JButton();
                cellButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellButton.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));

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
                    String randomTile = tileImages[random.nextInt(tileImages.length)];
                    ImageIcon tileIcon = new ImageIcon(getClass().getResource("/naruto_assets/" + randomTile));
                    cellButton.setIcon(tileIcon);

                    // Player Icons
                    String playerIconPath = null;
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

                            cellButton.setLayout(new BorderLayout());
                            JLabel playerLabel = new JLabel(resizedPlayerIcon);
                            cellButton.add(playerLabel, BorderLayout.CENTER);
                        }
                    } 

                    // Coins - placing them in a square around player icons, avoiding player positions
                    if (coinIndex < coinNumbers.length) {
                        // Check all positions around Player 1 (row 3, col 3 and 5; row 5, col 3 and 5)
                        if ((row == 2 && (col == 2 || col == 3 || col == 4)) || 
                            (row == 3 && (col == 2 || col == 4)) || 
                            (row == 4 && (col == 2 || col == 3 || col == 4))) {
                            // Ensure no coin is placed where player icons are located
                            if (!((row == 3 && col == 5) || (row == 5 && col == 3) || (row == 5 && col == 5))) {
                                String coinPath = "/naruto_assets/gold_" + coinNumbers[coinIndex] + ".png"; 
                                ImageIcon coinIcon = new ImageIcon(getClass().getResource(coinPath));
                                Image resizedCoinImage = coinIcon.getImage().getScaledInstance(CELL_SIZE / 4, CELL_SIZE / 4, Image.SCALE_SMOOTH);
                                JLabel coinLabel = new JLabel(new ImageIcon(resizedCoinImage));

                                // Add a more significant offset to the right
                                JPanel coinPanel = new JPanel();
                                coinPanel.setLayout(new OverlayLayout(coinPanel));
                                coinLabel.setPreferredSize(new Dimension(CELL_SIZE / 4, CELL_SIZE / 4));
                                coinLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left
                                coinPanel.add(coinLabel);
                                coinPanel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                                coinPanel.setOpaque(false);
                                coinPanel.setBounds(70, 0, CELL_SIZE / 4, CELL_SIZE / 4); // Increased bounds for a more noticeable right shift
                                cellButton.add(coinPanel); // Add the coin panel on top of the button

                                coinIndex++; // Increment the coin index
                            }
                        }

                        // Check all positions around Player 2 (row 3, col 5; row 5, col 5)
                        if ((row == 2 && (col == 4 || col == 5 || col == 6)) || 
                            (row == 3 && (col == 4 || col == 6)) || 
                            (row == 4 && (col == 4 || col == 5 || col == 6))) {
                            // Ensure no coin is placed where player icons are located
                            if (!((row == 3 && col == 3) || (row == 5 && col == 3))) {
                                String coinPath = "/naruto_assets/gold_" + coinNumbers[coinIndex] + ".png"; 
                                ImageIcon coinIcon = new ImageIcon(getClass().getResource(coinPath));
                                Image resizedCoinImage = coinIcon.getImage().getScaledInstance(CELL_SIZE / 4, CELL_SIZE / 4, Image.SCALE_SMOOTH);
                                JLabel coinLabel = new JLabel(new ImageIcon(resizedCoinImage));

                                // Add a more significant offset to the right
                                JPanel coinPanel = new JPanel();
                                coinPanel.setLayout(new OverlayLayout(coinPanel));
                                coinLabel.setPreferredSize(new Dimension(CELL_SIZE / 4, CELL_SIZE / 4));
                                coinLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left
                                coinPanel.add(coinLabel);
                                coinPanel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                                coinPanel.setOpaque(false);
                                coinPanel.setBounds(70, 0, CELL_SIZE / 4, CELL_SIZE / 4); // Increased bounds for a more noticeable right shift
                                cellButton.add(coinPanel); // Add the coin panel on top of the button

                                coinIndex++; // Increment the coin index
                            }
                        }
                    }
                }

                gridPanel.add(cellButton);
            }
        }
        gridPanel.setOpaque(false);
        return gridPanel;
    }







    

    private JPanel createChatAreaPanel() {
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.setPreferredSize(new Dimension(300, 600));
        chatPanel.setBorder(BorderFactory.createTitledBorder("Chatbox"));

        // Creating a JTextArea for the chat messages
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setBackground(new Color(255, 204, 204)); 
        chatArea.setForeground(Color.BLACK);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Creating a JScrollPane for the JTextArea
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setPreferredSize(new Dimension(300, 500));
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); 

        // Set the scroll pane's viewport border for rounded corners
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 

        // Creating a JTextField for input
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

        // Adding components to the panel
        chatPanel.add(scrollPane, BorderLayout.CENTER);
        chatPanel.add(chatInput, BorderLayout.SOUTH);

        return chatPanel;
    }
    

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu1 = new JMenu("Settings");
        JMenuItem settingsItem1 = new JMenuItem("Save Game");
        JMenuItem settingsItem2 = new JMenuItem("Restart Game");
        JMenuItem settingsItem3 = new JMenuItem("Toggle Music");
        fileMenu1.add(settingsItem1);
        fileMenu1.add(settingsItem2);
        fileMenu1.add(settingsItem3);
        JMenu fileMenu2 = new JMenu("Change Language");
        JMenu fileMenu3 = new JMenu("Network");
        JMenu fileMenu4 = new JMenu("Help");
        menuBar.add(fileMenu1);
        menuBar.add(fileMenu2);
        menuBar.add(fileMenu3);
        menuBar.add(fileMenu4);
        return menuBar;
    }

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

    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            drawImages(g); 
        }
    }

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

    private void setupPlayerTextFields() {
        player1TextField = new JTextField(15);
        player2TextField = new JTextField(15);
        player3TextField = new JTextField(15);
        player4TextField = new JTextField(15);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Maze ui = new Maze();
            ui.setupPlayerTextFields(); 
            ui.setVisible(true);
        });
    }
}
