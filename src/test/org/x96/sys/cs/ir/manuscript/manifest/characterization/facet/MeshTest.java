package org.x96.sys.cs.ir.manuscript.manifest.characterization.facet;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Natural;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

import java.util.Optional;

class MeshTest {
    @Test
    void happy() {
        Mesh mesh =
                new Mesh(
                        new Nucleus[] {new Text("cs".getBytes()), new Natural(0x73)},
                        Optional.empty());
        assertEquals("Mesh", new String(mesh.label().raw()));
        assertPrintLn(
                """
                Mesh
                    Text > cs
                    Natural > 115\
                """,
                () -> mesh.prettyPrint(""));
    }

    @Test
    void happyOccurrence() {
        Mesh mesh =
                new Mesh(
                        new Nucleus[] {new Text("cs".getBytes()), new Natural(0x73)},
                        Optional.of(Occurrence.OneOrMore));
        assertEquals("Mesh", new String(mesh.label().raw()));
        assertPrintLn(
                """
                Mesh [+]
                    Text > cs
                    Natural > 115\
                """,
                () -> mesh.prettyPrint(""));
    }
}
