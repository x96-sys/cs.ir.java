package org.x96.sys.cs.ir.manuscript.manifest;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.Characterization;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.archetype.Archetype;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Identity;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Natural;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

import java.util.Optional;

class ManifestTest {
    @Test
    void happy() {
        Manifest manifest =
                new Manifest(
                        new Identity("sc".getBytes()),
                        new Characterization(
                                Optional.of(Archetype.Ghost),
                                new Track(
                                        new Nucleus[] {
                                            new Natural(0x3f), new Text("sc".getBytes())
                                        })));
        assertEquals("Manifest", new String(manifest.label().raw()));
        assertPrintLn(
                """
                Manifest
                    primor: sc
                    Characterization [_]
                        Track
                            Natural > 63
                            Text > sc\
                """,
                () -> manifest.prettyPrint(""));
    }
}
