package org.x96.sys.cs.ir.manuscript;

import org.x96.sys.cs.ir.IR;
import org.x96.sys.cs.ir.manuscript.manifest.Manifest;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Identity;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

public record Manuscript(Text version, Identity identity, Manifest[] manifests) implements IR {

    @Override
    public Text label() {
        return new Text("Manuscript".getBytes());
    }

    public void prettyPrint(String indent) {
        System.out.println(indent + new String(label().raw()));

        indent = indent + " ".repeat(4);

        System.out.println(indent + "version: " + new String(version.raw()));
        System.out.println(indent + "primor: " + new String(identity.raw()));

        for (Manifest manifest : manifests) {
            manifest.prettyPrint(" ".repeat(4) + indent);
        }
    }
}
