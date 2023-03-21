package game.view;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame
{
    private final GameVisualizer visualizer;
    public GameWindow(GameVisualizer visualizer)
    {
        super("Игровое поле", true, true, true, true);
        this.visualizer = visualizer;
        //visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    public GameVisualizer getGameView() {
        return this.visualizer;
    }
}
