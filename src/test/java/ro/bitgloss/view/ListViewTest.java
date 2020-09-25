package ro.bitgloss.view;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListViewTest {

  @Test
  public void display() {
    var expected = "Details (text, other text):\n- data, other data;\n- x, y;\n";

    var actual = ListView.listFormat.apply(
            Collections.singletonList("text, other text"),
            () -> Stream.of(
                    Arrays.asList("data", "other data"),
                    Arrays.asList("x", "y")));

    assertEquals(expected, actual);
  }

  @Test
  public void calling_format_twice() {
    var headers = Collections.singletonList("text, other text");
    Supplier<Stream<List<String>>> content = () -> Stream.of(
            Arrays.asList("data", "other data"),
            Arrays.asList("x", "y"));

    ListView.listFormat.apply(headers, content);
    ListView.listFormat.apply(headers, content);
  }
}