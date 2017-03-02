package net.gcl.ticket.log;

import ch.qos.logback.core.OutputStreamAppender;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by guochenglai on 2/24/17.
 */
public class JavaFxStreamAppender<E> extends OutputStreamAppender<E> {
    private static final DelegatingOutputStream DELEGATING_OUTPUT_STREAM = new DelegatingOutputStream(null);

    @Override
    public void start() {
        setOutputStream(DELEGATING_OUTPUT_STREAM);
        super.start();
    }

    public static void setStaticOutputStream(OutputStream outputStream) {
        DELEGATING_OUTPUT_STREAM.setOutputStream(outputStream);
    }

    private static class DelegatingOutputStream extends FilterOutputStream {

        /**
         * Creates a delegating outputstream with a NO-OP delegate
         */
        public DelegatingOutputStream(OutputStream out){
            super(new OutputStream() {
                @Override
                public void write(int b) throws IOException {}
            });
        }

        void setOutputStream(OutputStream outputStream) {
            this.out = outputStream;
        }
    }

}
