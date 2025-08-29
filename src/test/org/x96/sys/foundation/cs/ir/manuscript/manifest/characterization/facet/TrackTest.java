package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.foundation.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Natural;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

class TrackTest {
    @Test
    void happyTrack() {
        Track track = new Track(new Nucleus[] {new Natural(0xff), new Text("cs".getBytes())});
        assertEquals("Track", new String(track.label().raw()));
        assertPrintLn(
                """
                :Track
                    :Natural > 255
                    :Text > cs\
                """,
                () -> track.prettyPrint(":"));
    }
}
