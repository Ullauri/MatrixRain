package ColoredStream;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.ThreadLocalRandom;

public class Symbol extends JLabel {
    // The hexadecimal value of the first character and how many characters exist in that alphabet
    public static final int SIZE = 20, CHARS_START = Integer.parseInt("FF66", 16), CHARS_LENGTH = 55;
    public final String[] CHARS;
    public final int ORIGIN_Y, CHANGE_RATE;
    private int x, y, speed;


    Symbol(int x, int y, int speed, int rate) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        ORIGIN_Y = y;
        CHANGE_RATE = rate;

        CHARS = new String[CHARS_LENGTH];

        for (int i = 0; i < CHARS_LENGTH; i++)
            CHARS[i] = String.valueOf((char) (CHARS_START + i));

        this.setOpaque(false);
        this.setFont(new Font(null, Font.PLAIN, SIZE));
        this.setBounds(x, y, SIZE, SIZE);
        this.generateCharacter();
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setColor(Color color) {
        this.setForeground(color);
    }

    public void generateCharacter() {
        this.setText(CHARS[ThreadLocalRandom.current().nextInt(0, CHARS_LENGTH)]);
    }

    public void update() {
        if (StreamFrame.inSync(CHANGE_RATE))
            generateCharacter();

        y += speed;
        this.setBounds(x, y, SIZE, SIZE);
    }

}
