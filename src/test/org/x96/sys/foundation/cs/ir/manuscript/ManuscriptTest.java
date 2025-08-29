package org.x96.sys.foundation.cs.ir.manuscript;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.foundation.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.Manifest;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.Characterization;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.archetype.Archetype;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Identity;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Natural;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

import java.util.Optional;

class ManuscriptTest {
    @Test
    void happy() {
        Text version = new Text("0.1.2".getBytes());
        Identity identity = new Identity("cs".getBytes());
        Manuscript manuscript =
                new Manuscript(
                        version,
                        identity,
                        new Manifest[] {
                            new Manifest(
                                    new Identity("sc".getBytes()),
                                    new Characterization(
                                            Optional.of(Archetype.Ghost),
                                            new Track(
                                                    new Nucleus[] {
                                                        new Natural(0x3f), new Text("sc".getBytes())
                                                    })))
                        });
        assertEquals("Manuscript", new String(manuscript.label().raw()));
        assertPrintLn(
                """
                Manuscript
                    version: 0.1.2
                    primor: cs
                        Manifest
                            primor: sc
                            Characterization [_]
                                Track
                                    Natural > 63
                                    Text > sc\
                """,
                () -> manuscript.prettyPrint(""));
    }
}
