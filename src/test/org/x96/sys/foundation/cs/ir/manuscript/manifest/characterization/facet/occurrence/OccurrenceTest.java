package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.occurrence;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.foundation.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;

class OccurrenceTest {
    @Test
    void happy() {
        assertArrayEquals("Occurrence".getBytes(), Occurrence.ZeroOrOne.label().raw());
        assertArrayEquals("Occurrence".getBytes(), Occurrence.ZeroOrMore.label().raw());
        assertArrayEquals("Occurrence".getBytes(), Occurrence.OneOrMore.label().raw());

        assertArrayEquals("?".getBytes(), Occurrence.ZeroOrOne.decor().getBytes());
        assertArrayEquals("*".getBytes(), Occurrence.ZeroOrMore.decor().getBytes());
        assertArrayEquals("+".getBytes(), Occurrence.OneOrMore.decor().getBytes());

        assertPrintLn(":Occurrence > ?", () -> Occurrence.ZeroOrOne.prettyPrint(":"));
        assertPrintLn(":Occurrence > *", () -> Occurrence.ZeroOrMore.prettyPrint(":"));
        assertPrintLn(":Occurrence > +", () -> Occurrence.OneOrMore.prettyPrint(":"));
    }
}
