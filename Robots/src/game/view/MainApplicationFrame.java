package game.view;
import game.model.GameVisualModel;
import game.view.MainApplicationFrame;

import log.Logger;
import log.view.LogWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainApplicationFrame(GameVisualModel gameVisualModel) {
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width  - inset*2,
                screenSize.height - inset*2);

        setContentPane(desktopPane);


        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow(gameVisualModel.getGameView());
        addWindow(gameWindow);

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10,10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    protected JMenu createMenuBar(String nameOfMenu, String description, int mnemonic) {
        JMenu lookAndFeelMenu = new JMenu(nameOfMenu);
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription(description);

        return lookAndFeelMenu;
    }

    private JMenuItem createMenuItem(String name, ActionListener l) {
        JMenuItem systemLookAndFeel = new JMenuItem(name, KeyEvent.VK_S);
        systemLookAndFeel.addActionListener(l);

        return systemLookAndFeel;
    }

    private JMenuBar generateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu lookAndFeelMenu = createMenuBar("Режим отображения",
                "Управление режимом отображения приложения", KeyEvent.VK_V);
        JMenuItem systemLookAndFeel = createMenuItem("Системная схема", (event) -> {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                this.invalidate();
        });
        lookAndFeelMenu.add(systemLookAndFeel);

        JMenuItem crossplatformLookAndFeel = new JMenuItem("Универсальная схема", KeyEvent.VK_S);
        crossplatformLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            this.invalidate();
        });
        lookAndFeelMenu.add(crossplatformLookAndFeel);

        JMenu testMenu = createMenuBar("Тесты", "Тестовые команды", KeyEvent.VK_S);

        JMenuItem addLogMessageItem = createMenuItem("Сообщение в лог",
                (event) -> Logger.debug("Новая строка"));
        testMenu.add(addLogMessageItem);

        menuBar.add(lookAndFeelMenu);
        menuBar.add(testMenu);
        return menuBar;
    }

    private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (ClassNotFoundException | InstantiationException
               | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }
}
