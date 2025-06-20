import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Exercise 2: E-commerce Platform Search Function
 * 
 * ▶ Big O Notation:
 * - Big O describes the time complexity of algorithms as input size grows.
 * - Linear Search: O(n) — goes through each element one-by-one.
 * - Binary Search: O(log n) — repeatedly divides the sorted list to find the element.
 *
 * ▶ Search Cases:
 * Linear Search:
 * - Best: O(1) (if found at first)
 * - Average: O(n/2)
 * - Worst: O(n) (if at end or not found)
 *
 * Binary Search:
 * - Always O(log n), but requires sorted input.
 */

class Product {
    int productId;
    String productName;
    String category;

    public Product(int id, String name, String category) {
        this.productId = id;
        this.productName = name;
        this.category = category;
    }

    public void display() {
        System.out.println("ID: " + productId + ", Name: " + productName + ", Category: " + category);
    }
}

public class Commerce {

    // Linear Search by product name
    public static Product linearSearch(Product[] products, String searchTerm) {
        for (Product product : products) {
            if (product.productName.equalsIgnoreCase(searchTerm)) {
                return product;
            }
        }
        return null;
    }

    // Binary Search by product name (must be sorted)
    public static Product binarySearch(Product[] products, String searchTerm) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = products[mid].productName.compareToIgnoreCase(searchTerm);

            if (cmp == 0) {
                return products[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Setup: Create product list
        Product[] products = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Shirt", "Clothing"),
            new Product(103, "Mouse", "Electronics"),
            new Product(104, "Book", "Education"),
            new Product(105, "Shoes", "Footwear")
        };

        // Create a sorted copy for binary search
        Product[] sortedProducts = Arrays.copyOf(products, products.length);
        Arrays.sort(sortedProducts, Comparator.comparing(p -> p.productName.toLowerCase()));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name to search: ");
        String searchTerm = scanner.nextLine();

        // Linear Search
        System.out.println("\n🔍 Linear Search:");
        Product linearResult = linearSearch(products, searchTerm);
        if (linearResult != null) {
            System.out.println("Product found:");
            linearResult.display();
        } else {
            System.out.println("Product not found.");
        }

        // Binary Search
        System.out.println("\n🔍 Binary Search (on sorted data):");
        Product binaryResult = binarySearch(sortedProducts, searchTerm);
        if (binaryResult != null) {
            System.out.println("Product found:");
            binaryResult.display();
        } else {
            System.out.println("Product not found.");
        }

        scanner.close();

        /*
         * ▶ Time Complexity Analysis:
         * Linear Search: O(n) - Less efficient for large datasets.
         * Binary Search: O(log n) - Much faster, but needs sorted data.
         *
         * ▶ Recommendation:
         * Use binary search if your product list can be kept sorted.
         * For real-world large platforms, consider using hash maps or databases with indexing.
         */
    }
}
