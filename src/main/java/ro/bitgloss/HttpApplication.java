package ro.bitgloss;

import io.TypedIO;
import spark.Request;

import java.util.Arrays;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static ro.bitgloss.Appointments.addNew;
import static ro.bitgloss.Appointments.display;
import static ro.bitgloss.dao.AppointmentDAO.*;
import static ro.bitgloss.view.HtmlTableView.htmlTableFormat;
import static spark.Spark.get;

public class HttpApplication {

    public static void startHttpEndpoints() {
        get("/appointments", (req, res) -> {
            StringBuilder sb = new StringBuilder();
            display.apply(htmlTableFormat).apply(HEADERS, content).accept(httpIo(req, sb));
            return sb.toString();
        });
        get("/appointment", (req, res) -> {
            StringBuilder sb = new StringBuilder();
            addNew.apply(save).accept(httpIo(req, sb));
            return "created by interaction flow:<br/>"+sb.toString();
        });
    }

    private static TypedIO httpIo(Request req, StringBuilder sb) {
        var params = Optional.ofNullable(req.queryString())
                .map(qs -> Arrays.stream(qs.split("&"))
                        .map(p -> p.substring(p.indexOf("=") + 1))
                        .collect(toList())
                        .iterator());
        return new TypedIO() {
            @Override
            public void print(Object o) { sb.append(o); }

            @Override
            public void printLine(Object o) { print(o + "<br/>"); }

            @Override
            public Optional<String> readString(String prompt, String errorMessage) {
                return params.map(p -> {
                    var val = p.next();
                    print(prompt+val+"<br/>"); // not necessary, but added to show IO interaction behind scenes
                    return val;
                });
            }
        };
    }
}
