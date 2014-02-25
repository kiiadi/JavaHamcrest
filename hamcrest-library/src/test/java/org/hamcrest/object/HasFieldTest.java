package org.hamcrest.object;

import static org.hamcrest.AbstractMatcherTest.assertDescription;
import static org.hamcrest.AbstractMatcherTest.assertDoesNotMatch;
import static org.hamcrest.AbstractMatcherTest.assertMatches;
import static org.hamcrest.AbstractMatcherTest.assertMismatchDescription;
import static org.hamcrest.AbstractMatcherTest.assertNullSafe;
import static org.hamcrest.AbstractMatcherTest.assertUnknownTypeSafe;
import static org.hamcrest.object.HasField.hasField;

import org.hamcrest.Matcher;
import org.junit.Test;

/**
 * @author Kyle Thomson
 */
public final class HasFieldTest {

    private final ObjectWithAField object = new ObjectWithAField();

    @Test public void
    copesWithNullsAndUnknownTypes() {
        Matcher<Object> matcher = hasField("irrelevant");

        assertNullSafe(matcher);
        assertUnknownTypeSafe(matcher);
    }

    @Test public void
    matchesWhenTheFieldExists() {
        assertMatches(hasField("foo"), object);
    }

    @Test public void
    doesNotMatchIfFieldDoesNotExist() {
        assertDoesNotMatch(hasField("aNonExistentField"), object);
    }

    @Test public void
    describesItself() {
        assertDescription("hasField(\"field\")", hasField("field"));
    }

    @Test public void
    describesAMismatch() {
        assertMismatchDescription("no public field \"aNonExistentField\" on <[ObjectWithAField: foo]>",
                hasField("aNonExistentField"), object);
    }

    private class ObjectWithAField {
        public final String foo = "foo";

        @Override
        public String toString() {
            return "[ObjectWithAField: " + foo + "]";
        }
    }
}
