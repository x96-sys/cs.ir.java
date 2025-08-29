package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet;

import org.x96.sys.foundation.cs.ir.IR;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

import java.util.Optional;

public record Mesh(Nucleus[] nuclei, Optional<Occurrence> occurrence) implements Facet, IR {
    @Override
    public Text label() {
        return new Text("Mesh".getBytes());
    }

    public void prettyPrint(String indent) {
        labelWOcc(occurrence, indent);
        for (Nucleus n : nuclei) n.prettyPrint(" ".repeat(4) + indent);
    }
}
