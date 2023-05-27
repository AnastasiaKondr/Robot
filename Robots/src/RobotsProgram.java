import model.EntityStateProvider;
import model.GameModel;
import viewmodel.GameViewModel;
import view.GameView;
import view.GameWindow;
import view.MainApplicationFrame;

import java.awt.Frame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class RobotsProgram
{
    public static void main(String[] args) {

        GameModel gameModel = new GameModel();
        EntityStateProvider provider = new EntityStateProvider(gameModel.getGameState());
        GameView gameView = new GameView(provider);
        GameViewModel viewModel = new GameViewModel(gameModel, gameView);

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
