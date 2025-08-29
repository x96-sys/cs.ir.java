package org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.x96.sys.cs.ir.IR;

public record Natural(int b) implements Nucleus, IR {
    @Override
    public Text label() {
        return new Text("Natural".getBytes());
    }

    public void prettyPrint(String indent) {
        System.out.printf("%s%s > %s%n", indent, new String(label().raw()), b);
    }
}
