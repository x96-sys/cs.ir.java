package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.foundation.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.archetype.Archetype;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Natural;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

import java.util.Optional;

class CharacterizationTest {
    @Test
    void happy() {
        Characterization characterization =
                new Characterization(
                        Optional.empty(),
                        new Track(new Nucleus[] {new Natural(0xff), new Text("cs".getBytes())}));
        assertEquals("Characterization", new String(characterization.label().raw()));
        assertPrintLn(
                """
                Characterization
                    Track
                        Natural > 255
                        Text > cs\
                """,
                () -> characterization.prettyPrint(""));
    }

    @Test
    void happyShell() {
        Characterization characterization =
                new Characterization(
                        Optional.of(Archetype.Shell),
                        new Track(new Nucleus[] {new Natural(0xff), new Text("cs".getBytes())}));
        assertEquals("Characterization", new String(characterization.label().raw()));
        assertPrintLn(
                """
                Characterization [@]
                    Track
                        Natural > 255
                        Text > cs\
                """,
                () -> characterization.prettyPrint(""));
    }

    @Test
    void happyGhost() {
        Characterization characterization =
                new Characterization(
                        Optional.of(Archetype.Ghost),
                        new Track(new Nucleus[] {new Natural(0xff), new Text("cs".getBytes())}));
        assertEquals("Characterization", new String(characterization.label().raw()));
        assertPrintLn(
                """
                Characterization [_]
                    Track
                        Natural > 255
                        Text > cs\
                """,
                () -> characterization.prettyPrint(""));
    }
}
