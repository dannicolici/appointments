package ro.bitgloss.view;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TabularView {

    private static final String PAD = "          ";

    public static BiFunction<List<String>, Supplier<Stream<List<String>>>, String> tabularFormat =
            (headers, content) -> {
                String rowSeparator = rowSeparator(headers);
                return rowSeparator +
                        headers(headers) +
                        rowSeparator +
                        data(content) +
                        rowSeparator;
            };

    private static StringBuilder data(Supplier<Stream<List<String>>> content) {
        StringBuilder sb = new StringBuilder();
        content.get().forEach(row -> {
            row.forEach(d -> sb.append(cell(d)));
            sb.append("\n");
        });
        return sb;
    }

    private static StringBuilder headers(List<String> hs) {
        StringBuilder sb = new StringBuilder();
        hs.forEach(d -> sb.append("|").append(cell(d)));
        sb.append("|\n");

        return sb;
    }

    private static String cell(String data) {
        return PAD + data + PAD;
    }

    private static String rowSeparator(List<String> headers) {
        int headersCharCount = headers.stream().mapToInt(String::length).sum();
        int rowSize = headersCharCount + PAD.length() * 2 * headers.size() + headers.size() + 1;
        return "-".repeat(Math.max(0, rowSize)) + "\n";
    }
}
