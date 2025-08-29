package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.foundation.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;

import java.util.Optional;

class TraceTest {
    @Test
    void happy() {
        Trace trace =
                new Trace(
                        new Fleck[] {
                            new Range(new Natural(0x80), new Natural(0xFF)),
                            new Text("cs".getBytes()),
                        },
                        Optional.empty());
        assertEquals("Trace", new String(trace.label().raw()));
        assertPrintLn(
                """
                Trace
                    Range
                        :from Natural > 128
                        :to Natural > 255
                    Text > cs\
                """,
                () -> trace.prettyPrint(""));
    }

    @Test
    void happy2() {
        Trace trace =
                new Trace(
                        new Fleck[] {
                            new Range(new Natural(0x80), new Natural(0xFF)),
                            new Text("cs".getBytes()),
                        },
                        Optional.of(Occurrence.ZeroOrOne));
        assertEquals("Trace", new String(trace.label().raw()));
        assertPrintLn(
                """
                Trace [?]
                    Range
                        :from Natural > 128
                        :to Natural > 255
                    Text > cs\
                """,
                () -> trace.prettyPrint(""));
    }
}
