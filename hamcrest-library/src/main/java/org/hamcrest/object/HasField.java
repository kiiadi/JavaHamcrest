package org.hamcrest.object;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * A Matcher that checks that an object has a public field
 * with the specified name.
 *
 * @author Kyle Thomson
 */
public class HasField<T> extends TypeSafeMatcher<T> {
    private final String fieldName;

    public HasField(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    protected boolean matchesSafely(T object) {
        try {
            return object.getClass().getField(fieldName) != null;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("hasField(").appendValue(fieldName).appendText(")");
    }

    @Override
    public void describeMismatchSafely(T item, Description mismatchDescription) {
        mismatchDescription.appendText("no public field ").appendValue(fieldName).appendText(" on ").appendValue(item);
    }

    /**
     * Creates a matcher that matches when the examined object has a public field
     * with the specified name.
     * <p/>
     * For example:
     * <pre>assertThat(myObject, hasField("foo"))</pre>
     *
     * @param fieldName
     *     the name of the public field that examined object should possess
     */
    @Factory
    public static <T> Matcher<T> hasField(String fieldName) {
        return new HasField<T>(fieldName);
    }
}
