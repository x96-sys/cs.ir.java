package org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.x96.sys.cs.ir.IR;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;

import java.util.Optional;

public record Switch(Nucleus[] nuclei, Optional<Occurrence> occurrence)
        implements Nucleus, Fleck, IR {
    @Override
    public Text label() {
        return new Text("Switch".getBytes());
    }

    public void prettyPrint(String indent) {
        labelWOcc(occurrence, indent);
        for (Nucleus n : nuclei) {
            n.prettyPrint(" ".repeat(4) + indent);
        }
    }
}
