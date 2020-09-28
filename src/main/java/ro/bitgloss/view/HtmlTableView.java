package ro.bitgloss.view;

import ro.bitgloss.Types.View;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class HtmlTableView {
    public static View htmlTableFormat = (headers, content) ->
            String.format("<table>%s</table>",
                    header(headers).append(data(content)).toString());

    private static StringBuilder data(Supplier<Stream<Collection<String>>> content) {
        StringBuilder sb = new StringBuilder();
        content.get().forEach(row -> {
            sb.append("<tr>");
            row.forEach(item -> sb.append(String.format("<td>%s</td>", item)));
            sb.append("</tr>");
        });

        return sb;
    }

    private static StringBuilder header(Collection<String> hs) {
        StringBuilder sb = new StringBuilder();
        hs.forEach(d -> sb.append(String.format("<th>%s</th>", d)));

        return sb;
    }
}
