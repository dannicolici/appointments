package ro.bitgloss.view;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HtmlTableViewTest {

  @Test
  public void display() {
    var expected = "<table><th>text</th><tr><td>data</td></tr></table>";
    
    var actual = HtmlTableView.htmlTableFormat.apply(
            Collections.singletonList("text"),
            () -> Stream.of(Collections.singletonList("data")));
    
    assertEquals(expected, actual);
  }
}