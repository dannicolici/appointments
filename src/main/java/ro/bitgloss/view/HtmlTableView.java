package ro.bitgloss.view;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class HtmlTableView {
    public static BiFunction<List<String>, Supplier<Stream<List<String>>>, String> htmlTableFormat =
            (headers, content) ->
                    String.format("<table>%s</table>",
                            header(headers).append(data(content)).toString());

    private static StringBuilder data(Supplier<Stream<List<String>>> content) {
        StringBuilder sb = new StringBuilder();
        content.get().forEach(row -> {
            sb.append("<tr>");
            row.forEach(item -> sb.append(String.format("<td>%s</td>", item)));
            sb.append("</tr>");
        });

        return sb;
    }

    private static StringBuilder header(List<String> hs) {
        StringBuilder sb = new StringBuilder();
        hs.forEach(d -> sb.append(String.format("<th>%s</th>", d)));

        return sb;
    }
}
