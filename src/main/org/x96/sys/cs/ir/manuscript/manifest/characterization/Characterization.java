package org.x96.sys.cs.ir.manuscript.manifest.characterization;

import org.x96.sys.cs.ir.IR;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.archetype.Archetype;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Facet;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

import java.util.Optional;

public record Characterization(Optional<Archetype> archetype, Facet facet) implements IR {
    @Override
    public Text label() {
        return new Text("Characterization".getBytes());
    }

    public void prettyPrint(String indent) {
        String a = archetype.map(o -> " [" + new String(o.decor().raw()) + "]").orElse("");
        System.out.printf("%s%s%s%n", indent, new String(label().raw()), a);
        facet.prettyPrint(" ".repeat(4) + indent);
    }
}
