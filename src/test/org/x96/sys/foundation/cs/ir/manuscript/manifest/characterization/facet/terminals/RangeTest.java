package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.junit.jupiter.api.Test;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.archetype.Archetype;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.x96.sys.foundation.io.TestUtils.assertPrintLn;

class RangeTest {
    @Test
    void happy() {
        Range range = new Range(new Natural(0x73), new Natural(0x74));
        assertArrayEquals("Range".getBytes(), range.label().raw());
        assertPrintLn("""
                Range
                    :from Natural > 115
                    :to Natural > 116\
                """, () -> range.prettyPrint(""));

        assertPrintLn("""
                :Range
                    ::from Natural > 115
                    ::to Natural > 116\
                """, () -> range.prettyPrint(":"));

    }
}