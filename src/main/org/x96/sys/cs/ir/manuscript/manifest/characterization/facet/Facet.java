package org.x96.sys.cs.ir.manuscript.manifest.characterization.facet;

import org.x96.sys.cs.ir.IR;

public sealed interface Facet extends IR permits Track, Mesh {
    void prettyPrint(String indent);
}
