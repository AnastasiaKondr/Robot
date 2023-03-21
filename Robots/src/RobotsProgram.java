import game.model.GameModel;
import game.model.GameVisualModel;
import game.view.GameVisualizer;
import game.view.GameWindow;
import game.view.GameVisualizer;
import game.view.MainApplicationFrame;

import java.awt.Frame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class RobotsProgram
{
    public static void main(String[] args) {

        GameModel gameModel = new GameModel();
        GameVisualizer gameView = new GameVisualizer(gameModel);
        GameWindow gameWindow = new GameWindow(gameView);
        GameVisualModel viewModel = new GameVisualModel(gameModel, gameWindow);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            MainApplicationFrame frame = new MainApplicationFrame(viewModel);
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }}
