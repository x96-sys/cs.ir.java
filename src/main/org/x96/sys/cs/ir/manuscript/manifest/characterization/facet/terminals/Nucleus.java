package org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.x96.sys.cs.ir.IR;

public sealed interface Nucleus extends IR permits Natural, Switch, Term, Trace, Text {
    void prettyPrint(String indent);
}
