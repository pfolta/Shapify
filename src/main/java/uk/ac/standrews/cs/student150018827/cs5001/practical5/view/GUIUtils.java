package uk.ac.standrews.cs.student150018827.cs5001.practical5.view;

import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

public final class GUIUtils {

    private GUIUtils() {
    }

    public static TextFormatter<Integer> getIntegerTextFormatter(int minimum, int maximum) {
        NumberFormat format = NumberFormat.getIntegerInstance();
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (c.isContentChange()) {
                ParsePosition parsePosition = new ParsePosition(0);
                format.parse(c.getControlNewText(), parsePosition);

                if (parsePosition.getIndex() == 0 || parsePosition.getIndex() < c.getControlNewText().length() || Integer.parseInt(c.getControlNewText())  < minimum || Integer.parseInt(c.getControlNewText()) > maximum) {
                    return null;
                }
            }
            return c;
        };
        return new TextFormatter<Integer>(new IntegerStringConverter(), 0, filter);
    }

}