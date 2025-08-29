package org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals;

import static org.junit.jupiter.api.Assertions.*;
import static org.x96.sys.foundation.io.TestUtils.assertPrintLn;

import org.junit.jupiter.api.Test;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;

import java.util.Optional;

class SwitchTest {
    @Test
    void happy() {
        Switch s =
                new Switch(
                        new Nucleus[] {new Text("cs".getBytes()), new Text("sc".getBytes())},
                        Optional.empty());
        assertEquals("Switch", new String(s.label().raw()));
        assertPrintLn(
                """
                :Switch
                    :Text > cs
                    :Text > sc\
                """,
                () -> s.prettyPrint(":"));
    }

    @Test
    void happyOccurrence() {
        Switch s =
                new Switch(
                        new Nucleus[] {new Text("cs".getBytes()), new Text("sc".getBytes())},
                        Optional.of(Occurrence.ZeroOrOne));
        assertEquals("Switch", new String(s.label().raw()));
        assertPrintLn(
                """
                :Switch [?]
                    :Text > cs
                    :Text > sc\
                """,
                () -> s.prettyPrint(":"));
    }

    @Test
    void happyNaturalTermText() {
        Switch s =
                new Switch(
                        new Nucleus[] {
                            new Natural(0xFF),
                            new Term(false, new Identity("cs".getBytes()), Optional.empty()),
                            new Text("sc".getBytes())
                        },
                        Optional.empty());
        assertEquals("Switch", new String(s.label().raw()));
        assertPrintLn(
                """
                :Switch
                    :Natural > 255
                    :Term > Identity > cs
                    :Text > sc\
                """,
                () -> s.prettyPrint(":"));
    }

    @Test
    void happyNaturalTermNegateText() {
        Switch s =
                new Switch(
                        new Nucleus[] {
                            new Natural(0xFF),
                            new Term(true, new Identity("cs".getBytes()), Optional.empty()),
                            new Text("sc".getBytes())
                        },
                        Optional.empty());
        assertEquals("Switch", new String(s.label().raw()));
        assertPrintLn(
                """
                :Switch
                    :Natural > 255
                    :Term [!]
                        :Identity > cs
                    :Text > sc\
                """,
                () -> s.prettyPrint(":"));
    }

    @Test
    void happyRecursive() {
        Switch s =
                new Switch(
                        new Nucleus[] {
                            new Natural(0xFF),
                            new Term(true, new Identity("cs".getBytes()), Optional.empty()),
                            new Text("sc".getBytes()),
                            new Switch(
                                    new Nucleus[] {
                                        new Natural(0xFF),
                                        new Term(
                                                true,
                                                new Identity("cs".getBytes()),
                                                Optional.empty()),
                                        new Text("sc".getBytes())
                                    },
                                    Optional.empty())
                        },
                        Optional.empty());
        assertEquals("Switch", new String(s.label().raw()));
        assertPrintLn(
                """
                :Switch
                    :Natural > 255
                    :Term [!]
                        :Identity > cs
                    :Text > sc
                    :Switch
                        :Natural > 255
                        :Term [!]
                            :Identity > cs
                        :Text > sc\
                """,
                () -> s.prettyPrint(":"));
    }

    @Test
    void happyTrace() {
        Switch s =
                new Switch(
                        new Nucleus[] {
                            new Trace(
                                    new Fleck[] {
                                        new Range(new Natural(0x80), new Natural(0xFF)),
                                        new Text("cs".getBytes()),
                                    },
                                    Optional.empty()),
                            new Trace(
                                    new Fleck[] {
                                        new Range(new Natural(0x40), new Natural(0x50)),
                                        new Text("cs".getBytes()),
                                    },
                                    Optional.empty())
                        },
                        Optional.empty());
        assertPrintLn(
                """
                Switch
                    Trace
                        Range
                            :from Natural > 128
                            :to Natural > 255
                        Text > cs
                    Trace
                        Range
                            :from Natural > 64
                            :to Natural > 80
                        Text > cs\
                """,
                () -> s.prettyPrint(""));
    }
}
