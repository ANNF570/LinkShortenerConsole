import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinkShortenerConsole {
    
    private static Map<String, String> urlMap = new HashMap<>();
    private static long id = 1; // Simulating an auto-incrementing ID
    private static final String BASE_URL = "http://short.ly/";

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Shorten URL");
            System.out.println("2. Retrieve Original URL");
            System.out.println("3. Exit");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            
            switch (choice) {
                case 1:
                    System.out.println("Enter the URL to be shortened:");
                    String longUrl = scanner.nextLine();
                    String shortUrl = shortenUrl(longUrl);
                    System.out.println("Shortened URL: " + shortUrl);
                    break;
                case 2:
                    System.out.println("Enter the shortened URL:");
                    String shortUrlInput = scanner.nextLine();
                    String originalUrl = expandUrl(shortUrlInput);
                    System.out.println("Original URL: " + originalUrl);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to shorten the URL
    public static String shortenUrl(String longUrl) {
        String shortUrl = BASE_URL + encodeBase62(id++);
        urlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Method to retrieve the original URL
    public static String expandUrl(String shortUrl) {
        return urlMap.getOrDefault(shortUrl, "URL not found");
    }

    // Base62 Encoding
    private static String encodeBase62(long id) {
        StringBuilder shortUrl = new StringBuilder();
        while (id > 0) {
            shortUrl.append(CHARACTERS.charAt((int) (id % 62)));
            id /= 62;
        }
        return shortUrl.reverse().toString();
    }
}
