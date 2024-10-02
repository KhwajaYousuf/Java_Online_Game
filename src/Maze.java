import javax.swing.*;
import java.awt.*;

public class Maze extends JFrame {

    private Image backgroundImage;

    public Maze() {
        setupFrame();  // Set up the frame properties
        loadBackgroundImage();  // Load background image
        setVisible(true);
    }

    private void setupFrame() {
        setTitle("Fullscreen Background with Grid");
        setSize(1920, 1080);  // Set window size to 1920x1080
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Make the window unresizable
    }

    private void loadBackgroundImage() {
        backgroundImage = new ImageIcon(getClass().getResource("/naruto_assets/game_background.jpg")).getImage();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Fill the entire window with the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
        // Draw the grid
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK); // Set grid color

        int gridSize = 7; // Number of rows/columns
        int cellSize = 70; // Size of each cell in pixels

        // Calculate starting position to center the grid
        int startX = (getWidth() - (gridSize * cellSize)) / 2;
        int startY = (getHeight() - (gridSize * cellSize)) / 2;

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int x = startX + (col * cellSize);
                int y = startY + (row * cellSize);
                g.drawRect(x, y, cellSize, cellSize); // Draw each grid cell
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Maze::new); // Run the application
    }
}
