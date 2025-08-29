package org.x96.sys.cs.ir.manuscript.manifest.characterization.archetype;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;

class ArchetypeTest {
    @Test
    void happy() {
        assertArrayEquals("Archetype".getBytes(), Archetype.Ghost.label().raw());
        assertArrayEquals("Archetype".getBytes(), Archetype.Shell.label().raw());

        assertArrayEquals("_".getBytes(), Archetype.Ghost.decor().raw());
        assertArrayEquals("@".getBytes(), Archetype.Shell.decor().raw());

        assertPrintLn(":Archetype > @", () -> Archetype.Shell.prettyPrint(":"));
        assertPrintLn(":Archetype > _", () -> Archetype.Ghost.prettyPrint(":"));
    }
}
