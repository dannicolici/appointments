package ro.bitgloss.view;

import org.junit.jupiter.api.Test;
import ro.bitgloss.data.DataSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListViewTest {

  @Test
  public void display() {
    DataSource ds = new DataSource() {
      @Override
      public List<String> entryDetails() {
        return Arrays.asList("text, other text");
      }

      @Override
      public Stream<List<String>> stream() {
        return Stream.of(
            Arrays.asList("data", "other data"),
            Arrays.asList("x", "y"));
      }
    };
    String expected = "Details (text, other text):\n- data, other data;\n- x, y;\n";

    String actual = new ListView().display(ds);

    assertEquals(expected, actual);
  }
}