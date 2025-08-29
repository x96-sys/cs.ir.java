package org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.x96.sys.cs.ir.IR;

public record Range(Natural from, Natural to) implements Fleck, IR {
    @Override
    public Text label() {
        return new Text("Range".getBytes());
    }

    public void prettyPrint(String indent) {
        System.out.println(indent + new String(label().raw()));
        from.prettyPrint(" ".repeat(4) + indent + ":from ");
        to.prettyPrint(" ".repeat(4) + indent + ":to ");
    }
}
