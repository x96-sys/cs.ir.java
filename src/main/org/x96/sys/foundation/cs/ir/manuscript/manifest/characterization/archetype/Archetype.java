package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.archetype;

import org.x96.sys.foundation.cs.ir.IR;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

public enum Archetype implements IR {
    Ghost,
    Shell;

    @Override
    public Text label() {
        return new Text("Archetype".getBytes());
    }

    public Text decor() {
        return switch (this) {
            case Ghost -> new Text("_".getBytes());
            case Shell -> new Text("@".getBytes());
        };
    }

    public void prettyPrint(String indent) {
        System.out.printf(
                "%s%s > %s%n", indent, new String(label().raw()), new String(decor().raw()));
    }
}
