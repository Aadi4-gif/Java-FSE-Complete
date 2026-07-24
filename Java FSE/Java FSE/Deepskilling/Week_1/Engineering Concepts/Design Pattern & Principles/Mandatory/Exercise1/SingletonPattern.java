public class SingletonPattern{

    private static SingletonPattern instance;
    private SingletonPattern() {
        System.out.println("[System] Singleton Instance Initialized.");
    }
    public static SingletonPattern getInstance() {
        if (instance == null) {
            instance = new SingletonPattern();
        }
        return instance;
    }
    public void log(String message) {
        System.out.println("Log Output -> " + message);
    }
    public static void main(String[] args) {
        System.out.println("--- Starting Singleton Test ---");

        SingletonPattern logger1 = SingletonPattern.getInstance();
        logger1.log("Processing payment transaction...");
        
        SingletonPattern logger2 = SingletonPattern.getInstance();
        logger2.log("Fetching user profile data...");

        System.out.println("\n--- Verification Check ---");
        
        if (logger1 == logger2) {
            System.out.println("SUCCESS: Both references point to the exact same instance.");
            System.out.println("Instance 1 Hash code: " + logger1.hashCode());
            System.out.println("Instance 2 Hash code: " + logger2.hashCode());
        } else {
            System.out.println("FAILURE: Different instances were created.");
        }
    }
}