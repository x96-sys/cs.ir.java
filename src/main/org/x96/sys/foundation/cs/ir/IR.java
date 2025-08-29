package org.x96.sys.foundation.cs.ir;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

import java.util.Optional;

public interface IR {
    public Text label();

    void prettyPrint(String indent);

    public default void labelWOcc(Optional<Occurrence> occurrence, String indent) {
        String qtf = occurrence.map(o -> " [" + o.decor() + "]").orElse("");
        System.out.printf("%s%s%s%n", indent, new String(label().raw()), qtf);
    }
}
