import javax.swing.*;
import java.awt.*;

public class Maze extends JFrame {

    private Image backgroundImage;
    private Image rotateIcon; // For the rotate icon
    private Image rotateFont; // For the rotate font
    private Image settingsIcon; // For the settings icon
    private Image settingsFont; // For the settings font
    private Image scrollChargeIcon; // For the scroll charge icon
    private Image scrollChargeFont; // For the scroll charge font
    private static final int GRID_SIZE = 7; // Size of the grid
    private static final int CELL_SIZE = 70; // Size of each grid cell
    private JPanel[][] gridCells; // Array to hold the grid cells
    

    public Maze() {
        setupFrame();  
        loadBackgroundImage();  
        loadRotateIcon();  
        loadRotateFont(); 
        loadSettingsIcon(); // Load the settings icon
        loadSettingsFont(); // Load the settings font image
        loadScrollChargeIcon(); // Load the scroll charge icon
        loadScrollChargeFont(); // Load the scroll charge font image
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
        settingsIcon = new ImageIcon(getClass().getResource("/naruto_assets/settings.jpeg")).getImage(); // Load the settings icon
    }

    private void loadSettingsFont() {
        settingsFont = new ImageIcon(getClass().getResource("/naruto_assets/settings_font.png")).getImage(); // Load the settings font image
    }
    
    private void loadScrollChargeIcon() {
        scrollChargeIcon = new ImageIcon(getClass().getResource("/naruto_assets/scroll_charge_icon.jpeg")).getImage(); // Load the scroll charge icon
    }

    private void loadScrollChargeFont() {
        scrollChargeFont = new ImageIcon(getClass().getResource("/naruto_assets/scroll_charge_font.png")).getImage(); // Load the scroll charge font image
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

        int iconX = (getWidth() - (CELL_SIZE * GRID_SIZE)) + 150; // X position for both icons
        int rotateIconY = (getHeight() - (CELL_SIZE * GRID_SIZE)) / 2; // Y position for rotate icon
        int rotateFontY = rotateIconY + 60; // Y position for rotate font

        g.drawImage(rotateIcon, iconX, rotateIconY, 60, 60, this); // Draw rotate icon
        g.drawImage(rotateFont, iconX, rotateFontY, 50, 30, this); // Draw rotate font

        // Calculate the Y position for the settings icon and font
        int settingsIconY = rotateIconY; // Align settings icon with rotate icon Y position
        int settingsFontY = rotateFontY; // Align settings font with rotate font Y position

        // Adjust X position to be right next to the rotate images, moved further right
        int settingsIconX = iconX + 90; // Increased to move settings further right
        int settingsFontX = settingsIconX; // Align settings font with settings icon

        g.drawImage(settingsIcon, settingsIconX, settingsIconY, 60, 60, this); // Draw settings icon
        g.drawImage(settingsFont, settingsFontX, settingsFontY, 50, 30, this); // Draw settings font

        // Draw scroll charge icon and font
        int scrollChargeIconX = settingsIconX + 90; // Position next to settings icon
        int scrollChargeIconY = settingsIconY; // Align with settings icon Y position
        int scrollChargeFontY = settingsFontY; // Align scroll charge font with settings font

        g.drawImage(scrollChargeIcon, scrollChargeIconX, scrollChargeIconY, 60, 60, this); // Draw scroll charge icon
        g.drawImage(scrollChargeFont, scrollChargeIconX, scrollChargeFontY, 80, 40, this); // Draw larger scroll charge font
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
