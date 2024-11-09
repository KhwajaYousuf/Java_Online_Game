import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MazeController {
    private MazeModel model;
    private Maze view;

    public MazeController(MazeModel model, Maze view) {
        this.model = model;
        this.view = view;  // Use the view passed into the constructor
        view.setVisible(true);

        JMenuBar menuBar = view.getJMenuBar();  // Get the JMenuBar from the view
        initMenuActions(menuBar);  // Initialize menu actions
    }
    
    

    
    private void initMenuActions(JMenuBar menuBar) {
        // Access the menu items from the view's menu bar
        JMenu settingsMenu = menuBar.getMenu(0); // Assuming Settings is the first menu

        JMenuItem saveGameItem = settingsMenu.getItem(0);
        JMenuItem restartGameItem = settingsMenu.getItem(1);
        JMenuItem toggleMusicItem = settingsMenu.getItem(2);

        saveGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveGame();
            }
        });

        restartGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRestartGame();
            }
        });

        toggleMusicItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleToggleMusic();
            }
        });
    }
    
    // Method to set the view after both controller and view are created
    public void setView(Maze view) {
        this.view = view;
        initMenuActions(null); // Initialize the menu actions after setting the view
    }

    // Action for saving the game
 // Action for saving the game
    public void handleSaveGame() {
        try {
            model.saveGame("saved_game.dat"); // Save game using the model's saveGame method
            JOptionPane.showMessageDialog(null, "Game has been saved!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to save the game: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    
    
    // Action for restarting the game
 // Action for restarting the game
    public void handleRestartGame() {
        // Show a confirmation dialog
        int response = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to restart the game?", 
                "Restart Game", 
                JOptionPane.YES_NO_OPTION);

        // If the user chooses 'Yes', call the resetGame method from the model
        if (response == JOptionPane.YES_OPTION) {
            model.resetGame();  // This will reset the game and refresh the view
        }
    }


    // Action for toggling music
    public void handleToggleMusic() {
        model.toggleMusic(); // Toggle the music (method needs to exist in your model)
        String musicStatus = model.isMusicOn() ? "Music On" : "Music Off"; // Check if music is on
        JOptionPane.showMessageDialog(null, musicStatus);
    }
    
    public void handleShowHelp() {
        String helpText = "Welcome to Wizards Maze!\n\n" +
                          "Objective:\n" +
                          "The goal is to capture ingredients scattered across the maze while navigating through it.\n" +
                          "Capture ingredients by moving your wizard onto the tiles containing them.\n\n" +
                          "Gameplay Rules:\n" +
                          "1. Each player starts in one of the four assigned positions on the maze board.\n" +
                          "2. Ingredients are placed randomly, and only the lowest-numbered ingredient can be captured first.\n" +
                          "3. The player may rotate the 50th piece of the maze at will.\n" +
                          "4. To change the maze, click an arrow to insert the 50th piece at the indicated side, pushing other pieces off.\n" +
                          "5. Players and ingredients that are pushed off the maze wrap around to the other side.\n" +
                          "6. The player can then move anywhere on the maze, provided the maze allows it.\n" +
                          "7. The player must capture ingredients by landing on them, and the next ingredient becomes capturable.\n" +
                          "8. Each turn, a player may use a wand charge to rotate the maze again before moving.\n" +
                          "9. The game ends when all ingredients are captured.\n\n" +
                          "Scoring:\n" +
                          "1. Ingredients captured are worth their face value in points.\n" +
                          "2. Each unspent wand is worth 3 points.\n" +
                          "3. Secret ingredients are worth 20 extra points.\n" +
                          "4. The player with the most points wins the game. Ties are possible.\n\n" +
                          "Ending the Game:\n" +
                          "1. Once the game ends, the scores are displayed in a notification dialog.\n" +
                          "2. You may restart the game or perform other actions through the menu.\n\n" +
                          "Good luck, wizard!";

        // Show the help text in a dialog box
        JOptionPane.showMessageDialog(view, helpText, "Game Rules and Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

}
