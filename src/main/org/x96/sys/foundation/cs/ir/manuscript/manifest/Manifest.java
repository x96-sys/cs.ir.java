package org.x96.sys.foundation.cs.ir.manuscript.manifest;

import org.x96.sys.foundation.cs.ir.IR;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.Characterization;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Identity;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

public record Manifest(Identity identity, Characterization characterization) implements IR {
    @Override
    public Text label() {
        return new Text("Manifest".getBytes());
    }

    public void prettyPrint(String indent) {
        System.out.println(indent + new String(label().raw()));
        indent = " ".repeat(4) + indent;
        System.out.println(indent + "primor: " + new String(identity.raw()));
        characterization.prettyPrint(indent);
    }
}
