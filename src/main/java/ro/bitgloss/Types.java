package ro.bitgloss;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Types {
    interface ReadWriter<R, W> extends Function<Consumer<R>, Consumer<W>> {}

    interface View extends BiFunction<Collection<String>, Supplier<Stream<Collection<String>>>, String> {}

    interface ViewWriter<W> extends BiFunction<Collection<String>, Supplier<Stream<Collection<String>>>, Consumer<W>> {}
}
