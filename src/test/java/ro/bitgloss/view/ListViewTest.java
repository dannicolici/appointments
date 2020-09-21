package ro.bitgloss.view;

import org.junit.jupiter.api.Test;
import ro.bitgloss.data.DataSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListViewTest {

  @Test
  public void display() {
    var ds = new DataSource() {
      @Override
      public List<String> entryDetails() {
        return Collections.singletonList("text, other text");
      }

      @Override
      public Stream<List<String>> stream() {
        return Stream.of(
            Arrays.asList("data", "other data"),
            Arrays.asList("x", "y"));
      }
    };
    var expected = "Details (text, other text):\n- data, other data;\n- x, y;\n";

    var actual = new ListView().apply(ds);

    assertEquals(expected, actual);
  }
}