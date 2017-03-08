package ColoredStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StreamFrame extends JFrame {
    public static final int FRAME_RATE = 25, WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 800;
    private static int frameCount;
    private Stream[] streams;
    private JPanel panel;

    public StreamFrame() throws InterruptedException {
        frameCount = 0;
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        streams = new Stream[WINDOW_WIDTH / Symbol.SIZE];
        int x = Symbol.SIZE / 3;

        for (int i = 0; i < streams.length; i++) {
            streams[i] = new Stream(x);
            for (Symbol symbol : streams[i].getSymbols())
                panel.add(symbol);
            x += Symbol.SIZE;
        }

        this.add(panel);
        this.setVisible(true);
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                panel.setVisible(false);
                panel.removeAll();

                for (int i = 0; i < streams.length; i++)
                    streams[i].update();

                for (int i = 0; i < streams.length; i++)
                    for (Symbol symbol : streams[i].getSymbols())
                        panel.add(symbol);

                frameCount++;
                panel.setVisible(true);
            }
        };
        new Timer(FRAME_RATE, taskPerformer).start();
    }

    public static boolean inSync(int rate) {
        return frameCount % rate == 0;
    }

}