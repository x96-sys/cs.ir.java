package org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;

class IdentityTest {

    @Test
    void happy() {
        Identity identity = new Identity("cs".getBytes());
        assertEquals("Identity", new String(identity.label().raw()));
        assertPrintLn(":Identity > cs", () -> identity.prettyPrint(":"));
    }
}
