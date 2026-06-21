public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing Decorator Pattern Notification System ===\n");

        System.out.println("[Scenario A: Standard Base Email Notification]");
        Notifier baseNotification = new EmailNotifier();
        baseNotification.send("System maintenance starting soon.");

        System.out.println("\n------------------------------------------------\n");

        System.out.println("[Scenario B: Email + SMS Combined]");
        Notifier emailAndSMS = new SMSNotifierDecorator(new EmailNotifier());
        emailAndSMS.send("Security Alert: New login detected.");

        System.out.println("\n------------------------------------------------\n");

        System.out.println("[Scenario C: All Channels Active (Email + SMS + Slack)]");
        Notifier fullStackNotification = new SlackNotifierDecorator(
                                            new SMSNotifierDecorator(
                                                new EmailNotifier()
                                            )
                                         );
        fullStackNotification.send("CRITICAL ERROR: Server database down!");
    }
}