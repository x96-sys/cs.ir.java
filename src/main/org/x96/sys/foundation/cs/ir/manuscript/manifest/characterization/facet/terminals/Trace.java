package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.x96.sys.foundation.cs.ir.IR;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;

import java.util.Optional;

public record Trace(Fleck[] flecks, Optional<Occurrence> occurrence) implements Nucleus, Fleck, IR {
    @Override
    public Text label() {
        return new Text("Trace".getBytes());
    }

    public void prettyPrint(String indent) {
        labelWOcc(occurrence, indent);
        for (Fleck fleck : flecks) {
            fleck.prettyPrint(" ".repeat(4) + indent);
        }
    }
}
