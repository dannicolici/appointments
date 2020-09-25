package ro.bitgloss;

public class Application {
    public static void main(String[] args) {
        HttpApplication.startHttpEndpoints();
        ConsoleApplication.loop();
    }
}
