package org.x96.sys.foundation.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestUtils {

    /** Captura a saída de System.out durante a execução de runnable e compara com o esperado. */
    public static void assertPrintLn(String expected, Runnable action) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            action.run();
            assertEquals(expected + "\n", outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }
}
