package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.foundation.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;

class TextTest {

    @Test
    void happy() {
        Text text = new Text("cs".getBytes());
        assertEquals("Text", new String(text.label().raw()));
        assertEquals("cs", new String(text.raw()));
        assertPrintLn(":Text > cs", () -> text.prettyPrint(":"));
    }
}
