package ColoredStream;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class Stream {
    public static final int MIN_SPEED = 3, MAX_SPEED = 7, MIN_CHANGE_RATE = 15, MAX_CHANGE_RATE = 25;
    public final int x;
    private Symbol[] symbols;


    Stream(int x) {
        this.x = x;

        generateStream();
    }

    private void generateStream() {
        final int maxSymbols = (StreamFrame.WINDOW_HEIGHT / Symbol.SIZE);
        final int symbolsSize = ThreadLocalRandom.current().nextInt(maxSymbols / 2, maxSymbols);
        symbols = new Symbol[symbolsSize];

        int y = ThreadLocalRandom.current().nextInt(-StreamFrame.WINDOW_HEIGHT * 2, -StreamFrame.WINDOW_HEIGHT);
        final int speed = ThreadLocalRandom.current().nextInt(MIN_SPEED, MAX_SPEED);
        int changeRate = ThreadLocalRandom.current().nextInt(MIN_CHANGE_RATE, MAX_CHANGE_RATE);
        int colorHue = 75;
        final int hueRange = (255 - colorHue) / symbols.length;

        for (int i = 0; i < symbols.length - 1; i++) {
            symbols[i] = new Symbol(x, y, speed, changeRate);
            symbols[i].setColor(new Color(0, colorHue, 0));
            y += Symbol.SIZE;
            changeRate = ThreadLocalRandom.current().nextInt(MIN_CHANGE_RATE, MAX_CHANGE_RATE);
            colorHue += hueRange;
        }

        symbols[symbolsSize - 1] = new Symbol(x, y, speed, changeRate);
        symbols[symbolsSize - 1].setColor(new Color(179, colorHue, 212));
    }

    public void update() {
        for (Symbol symbol : symbols)
            symbol.update();

        if (symbols[0].getY() > StreamFrame.WINDOW_HEIGHT)
            generateStream();
    }

    public Symbol[] getSymbols() {
        return symbols;
    }

}