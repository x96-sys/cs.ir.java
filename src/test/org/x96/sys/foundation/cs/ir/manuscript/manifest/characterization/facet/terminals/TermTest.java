package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals;

import org.junit.jupiter.api.Test;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.foundation.io.TestUtils.assertPrintLn;

class TermTest {

    @Test
    void happy(){
        Term term = new Term(false, new Identity("cs".getBytes()), Optional.empty());
        assertEquals("Term", new String(term.label().raw()));
        assertPrintLn("""
                :Term > Identity > cs\
                """, () -> term.prettyPrint(":"));
        assertPrintLn("""
                Term > Identity > cs\
                """, () -> term.prettyPrint(""));
    }

    @Test
    void happyNegate(){
        Term term = new Term(true, new Identity("cs".getBytes()), Optional.empty());
        assertEquals("Term", new String(term.label().raw()));
        assertPrintLn("""
                :Term [!]
                    :Identity > cs\
                """, () -> term.prettyPrint(":"));

    }

    @Test
    void happyNegateOneOrMore(){
        Term term = new Term(true, new Identity("cs".getBytes()), Optional.of(Occurrence.OneOrMore));
        assertEquals("Term", new String(term.label().raw()));
        assertPrintLn("""
                :Term [!] [+]
                    :Identity > cs
                    :Occurrence > +\
                """, () -> term.prettyPrint(":"));

    }
}