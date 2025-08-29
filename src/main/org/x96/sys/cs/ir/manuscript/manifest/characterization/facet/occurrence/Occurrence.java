package org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.occurrence;

import org.x96.sys.cs.ir.IR;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

public enum Occurrence implements IR {
    ZeroOrOne,
    ZeroOrMore,
    OneOrMore,
    ;

    @Override
    public Text label() {
        return new Text("Occurrence".getBytes());
    }

    @Override
    public void prettyPrint(String indent) {
        System.out.printf("%s%s > %s%n", indent, new String(label().raw()), decor());
    }

    public String decor() {
        return switch (this) {
            case ZeroOrOne -> "?";
            case ZeroOrMore -> "*";
            case OneOrMore -> "+";
        };
    }
}
