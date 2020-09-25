package ro.bitgloss.view;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ListView {

  public static BiFunction<List<String>, Supplier<Stream<List<String>>>, String> listFormat =
          (headers, content) -> header(headers).append(data(content)).toString();

  private static StringBuilder data(Supplier<Stream<List<String>>> content) {
    StringBuilder sb = new StringBuilder();
    content.get().forEach(row -> {
      sb.append("- ");
      row.forEach(item -> sb.append(item).append(", "));
      sb.delete(sb.length() - 2, sb.length()).append(";\n");
    });

    return sb;
  }

  private static StringBuilder header(List<String> hs) {
    StringBuilder sb = new StringBuilder();    
    sb.append("Details (");
    hs.forEach(d -> sb.append(d).append(", "));
    sb.delete(sb.length() - 2, sb.length()).append("):\n");

    return sb;
  }
}
