package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.junit.jupiter.api.Test;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.archetype.Archetype;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.foundation.io.TestUtils.assertPrintLn;

class IdentityTest {

    @Test
    void happy() {
        Identity identity = new Identity("cs".getBytes());
        assertEquals("Identity", new String(identity.label().raw()));
        assertPrintLn(":Identity > cs", () -> identity.prettyPrint(":"));
    }

}