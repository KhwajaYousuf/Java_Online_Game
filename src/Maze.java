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
                int x = startX + (col * CELL_SIZE);
                int y = startY + (row * CELL_SIZE);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Maze::new);
    }
}
