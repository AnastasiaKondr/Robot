package view;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame
{
    private final GameView view;
    public GameWindow(GameView view)
    {
        super("Игровое поле", true, true, true, true);
        this.view = view;
        //visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.view, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    public GameView getGameView() {
        return this.view;
    }
}
