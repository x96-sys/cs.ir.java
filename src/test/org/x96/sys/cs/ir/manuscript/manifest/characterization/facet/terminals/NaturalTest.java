package org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;

class NaturalTest {
    @Test
    void happy() {
        Natural natural = new Natural(0x73);
        assertEquals("Natural", new String(natural.label().raw()));
        assertPrintLn(":Natural > 115", () -> natural.prettyPrint(":"));
    }
}
