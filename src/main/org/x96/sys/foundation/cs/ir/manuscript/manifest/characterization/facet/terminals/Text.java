package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.x96.sys.foundation.cs.ir.IR;

public record Text(byte[] raw) implements Nucleus, Fleck, IR {
    @Override
    public Text label() {
        return new Text("Text".getBytes());
    }

    public void prettyPrint(String indent) {
        System.out.printf("%s%s > %s%n", indent, new String(label().raw()), new String(raw));
    }
}
