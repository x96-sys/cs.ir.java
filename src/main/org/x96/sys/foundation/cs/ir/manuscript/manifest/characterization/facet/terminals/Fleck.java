package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.x96.sys.foundation.cs.ir.IR;

public sealed interface Fleck extends IR permits Range, Switch, Term, Trace, Text {
    void prettyPrint(String indent);
}
