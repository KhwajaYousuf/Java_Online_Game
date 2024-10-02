import javax.swing.*;
import java.awt.*;

public class Maze extends JFrame {

    private Image backgroundImage;
    private Image rotateIcon;
    private Image rotateFont;
    private Image settingsIcon;
    private Image settingsFont;
    private Image scrollChargeIcon;
    private Image scrollChargeFont;
    private Image gameLogo;
    private Image lostMazeImage;
    private Image gameStatsFont;
    private Image player1Font;
    private Image player2Font;
    private Image player3Font;
    private Image player4Font;
    private Image finalScrollImage; // Changed from scroll1Image to finalScrollImage
    private static final int GRID_SIZE = 7;
    private static final int CELL_SIZE = 70;
    private JPanel[][] gridCells;

    public Maze() {
        setupFrame();  
        loadBackgroundImage();  
        loadRotateIcon();  
        loadRotateFont(); 
        loadSettingsIcon(); 
        loadSettingsFont(); 
        loadScrollChargeIcon(); 
        loadScrollChargeFont(); 
        loadGameLogo(); 
        loadLostMazeImage(); 
        loadGameStatsFont(); 
        loadPlayerFonts(); // Load player images
        loadFinalScrollImage(); // Load final_scroll image
        createGrid();  
        setVisible(true);
    }

    private void setupFrame() {
        setTitle("Fullscreen Background with Grid");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void loadBackgroundImage() {
        backgroundImage = new ImageIcon(getClass().getResource("/naruto_assets/game_background.jpg")).getImage();
    }

    private void loadRotateIcon() {
        rotateIcon = new ImageIcon(getClass().getResource("/naruto_assets/rotate.jpg")).getImage();
    }

    private void loadRotateFont() {
        rotateFont = new ImageIcon(getClass().getResource("/naruto_assets/rotate_font.png")).getImage();
    }

    private void loadSettingsIcon() {
        settingsIcon = new ImageIcon(getClass().getResource("/naruto_assets/settings.jpeg")).getImage();
    }

    private void loadSettingsFont() {
        settingsFont = new ImageIcon(getClass().getResource("/naruto_assets/settings_font.png")).getImage();
    }

    private void loadScrollChargeIcon() {
        scrollChargeIcon = new ImageIcon(getClass().getResource("/naruto_assets/scroll_charge_icon.jpeg")).getImage();
    }

    private void loadScrollChargeFont() {
        scrollChargeFont = new ImageIcon(getClass().getResource("/naruto_assets/scroll_charge_font.png")).getImage();
    }

    private void loadGameLogo() {
        gameLogo = new ImageIcon(getClass().getResource("/naruto_assets/game_logo-removebg-preview.png")).getImage();
    }

    private void loadLostMazeImage() {
        lostMazeImage = new ImageIcon(getClass().getResource("/naruto_assets/lost_maze.png")).getImage();
    }

    private void loadGameStatsFont() {
        gameStatsFont = new ImageIcon(getClass().getResource("/naruto_assets/game_stats_font.png")).getImage();
    }

    private void loadPlayerFonts() {
        player1Font = new ImageIcon(getClass().getResource("/naruto_assets/player1_font.png")).getImage();
        player2Font = new ImageIcon(getClass().getResource("/naruto_assets/player2_font.png")).getImage();
        player3Font = new ImageIcon(getClass().getResource("/naruto_assets/player3_font.png")).getImage();
        player4Font = new ImageIcon(getClass().getResource("/naruto_assets/player4_font.png")).getImage();
    }

    private void loadFinalScrollImage() {
        finalScrollImage = new ImageIcon(getClass().getResource("/naruto_assets/final_scroll.png")).getImage(); // Load final_scroll image
    }

    private void createGrid() {
        gridCells = new JPanel[GRID_SIZE][GRID_SIZE];
        JPanel mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setBackground(new Color(0, 0, 0, 0));
                mazePanel.add(cell);
            }
        }

        mazePanel.setOpaque(false);
        add(mazePanel);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        drawGrid(g);

        // Top-left corner: game logo and lost maze
        int logoX = 30; // X position for logo
        int logoY = 30; // Y position for logo
        g.drawImage(gameLogo, logoX, logoY, 100, 100, this); // Draw game logo

        int lostMazeX = logoX + 110; // X position for lost maze next to logo
        int lostMazeY = logoY + 40; // Lower the Y position for lost maze
        g.drawImage(lostMazeImage, lostMazeX, lostMazeY, 145, 25, this); // Draw lost maze image (reduced size)

        // Game stats font below game logo
        int gameStatsX = logoX; // Align X with game logo
        int gameStatsY = logoY + 110; // Position right below the game logo
        g.drawImage(gameStatsFont, gameStatsX, gameStatsY, 150, 50, this); // Draw game stats font

        // Players fonts below game stats font with added spacing
        int playerX = logoX; // Align X with game logo
        int player1Y = gameStatsY + 60; // Position player1 right below game stats
        int player2Y = player1Y + 60; // Position player2 below player1 with additional spacing
        int player3Y = player2Y + 60; // Position player3 below player2 with additional spacing
        int player4Y = player3Y + 60; // Position player4 below player3 with additional spacing

        g.drawImage(player1Font, playerX, player1Y, 150, 30, this); // Draw player1 font
        g.drawImage(player2Font, playerX, player2Y, 150, 30, this); // Draw player2 font
        g.drawImage(player3Font, playerX, player3Y, 150, 30, this); // Draw player3 font
        g.drawImage(player4Font, playerX, player4Y, 150, 30, this); // Draw player4 font

        // Final scroll image below player4 with a larger size
        int finalScrollX = playerX; // Align X with players
        int finalScrollY = player4Y + 40; // Position below player4 with some space
        g.drawImage(finalScrollImage, finalScrollX, finalScrollY, 150, 150, this); // Draw final scroll image (larger size)

        // Drawing rotate, settings, and scroll charge icons and fonts
        int iconX = (getWidth() - (CELL_SIZE * GRID_SIZE)) + 150; // X position for icons
        int rotateIconY = (getHeight() - (CELL_SIZE * GRID_SIZE)) / 2; // Y position for rotate icon
        int rotateFontY = rotateIconY + 60; // Y position for rotate font

        g.drawImage(rotateIcon, iconX, rotateIconY, 60, 60, this); // Draw rotate icon
        g.drawImage(rotateFont, iconX, rotateFontY, 50, 30, this); // Draw rotate font

        // Settings icon and font right next to rotate
        int settingsIconY = rotateIconY;
        int settingsFontY = rotateFontY;

        int settingsIconX = iconX + 90; // Move settings further right
        int settingsFontX = settingsIconX; // Align settings font with settings icon

        g.drawImage(settingsIcon, settingsIconX, settingsIconY, 60, 60, this); // Draw settings icon
        g.drawImage(settingsFont, settingsFontX, settingsFontY, 50, 30, this); // Draw settings font

        // Scroll charge icon and font right next to settings
        int scrollChargeIconX = settingsIconX + 90; // Move scroll charge further right
        int scrollChargeIconY = settingsIconY; // Align with settings Y position
        int scrollChargeFontY = settingsFontY; // Align font Y position with icon

        g.drawImage(scrollChargeIcon, scrollChargeIconX, scrollChargeIconY, 60, 60, this); // Draw scroll charge icon
        g.drawImage(scrollChargeFont, scrollChargeIconX, scrollChargeFontY, 80, 40, this); // Draw scroll charge font (larger size)
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);

        int startX = (getWidth() - (GRID_SIZE * CELL_SIZE)) / 2;
        int startY = (getHeight() - (GRID_SIZE * CELL_SIZE)) / 2;

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                g.drawRect(startX + col * CELL_SIZE, startY + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Maze());
    }
}
