package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet;

import org.x96.sys.foundation.cs.ir.IR;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

public record Track(Nucleus[] nuclei) implements Facet, IR {
    @Override
    public Text label() {
        return new Text("Track".getBytes());
    }

    public void prettyPrint(String indent) {
        System.out.println(indent + new String(label().raw()));
        for (Nucleus n : nuclei) n.prettyPrint(" ".repeat(4) + indent);
    }
}
