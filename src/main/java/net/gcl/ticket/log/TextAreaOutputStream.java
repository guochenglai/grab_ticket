package net.gcl.ticket.log;

import java.io.OutputStream;

import com.google.common.base.Strings;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by guochenglai on 2/24/17.
 */
public class TextAreaOutputStream extends OutputStream {

    private TextArea textArea;

    public TextAreaOutputStream(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        String str = Strings.nullToEmpty(String.valueOf((char) b));
        Platform.runLater(() -> textArea.appendText(str));

    }
}
