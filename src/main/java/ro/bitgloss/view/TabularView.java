package ro.bitgloss.view;

import ro.bitgloss.Types.View;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TabularView {

    private static final String PAD = "          ";

    public static View tabularFormat = (headers, content) -> {
        String rowSeparator = rowSeparator(headers);
        return rowSeparator +
                headers(headers) +
                rowSeparator +
                data(content) +
                rowSeparator;
    };

    private static StringBuilder data(Supplier<Stream<Collection<String>>> content) {
        StringBuilder sb = new StringBuilder();
        content.get().forEach(row -> {
            row.forEach(d -> sb.append(cell(d)));
            sb.append("\n");
        });
        return sb;
    }

    private static StringBuilder headers(Collection<String> hs) {
        StringBuilder sb = new StringBuilder();
        hs.forEach(d -> sb.append("|").append(cell(d)));
        sb.append("|\n");

        return sb;
    }

    private static String cell(String data) {
        return PAD + data + PAD;
    }

    private static String rowSeparator(Collection<String> headers) {
        int headersCharCount = headers.stream().mapToInt(String::length).sum();
        int rowSize = headersCharCount + PAD.length() * 2 * headers.size() + headers.size() + 1;
        return "-".repeat(Math.max(0, rowSize)) + "\n";
    }
}
