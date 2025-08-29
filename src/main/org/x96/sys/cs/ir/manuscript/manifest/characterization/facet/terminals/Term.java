package org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.x96.sys.cs.ir.IR;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;

import java.util.Optional;

public record Term(boolean negate, Identity identity, Optional<Occurrence> occurrence)
        implements Nucleus, Fleck, IR {
    @Override
    public Text label() {
        return new Text("Term".getBytes());
    }

    @Override
    public void prettyPrint(String indent) {
        String n = negate ? " [!]" : "";
        String qtf = occurrence.map(o -> " [" + o.decor() + "]").orElse("");
        if (!n.equals(qtf)) {
            System.out.printf("%s%s%s%s%n", indent, new String(label().raw()), n, qtf);
            indent = " ".repeat(4) + indent;
            identity.prettyPrint(indent);
            if (occurrence.isPresent()) {
                occurrence.get().prettyPrint(indent);
            }
        } else {
            System.out.printf(
                    "%s%s > %s > %s%n",
                    indent,
                    new String(label().raw()),
                    new String(identity.label().raw()),
                    new String(identity.raw()));
        }
    }
}
