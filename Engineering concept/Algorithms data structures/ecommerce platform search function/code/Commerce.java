import java.util.*;

class Product {
    int productId;
    String productName;
    String category;
    double price;

    public Product(int id, String name, String category, double price) {
        this.productId = id;
        this.productName = name;
        this.category = category;
        this.price = price;
    }

    public void display() {
        System.out.println("ID: " + productId + ", Name: " + productName + ", Category: " + category + ", Price:Rs." + price);
    }
}

public class Commerce {

    // Normalize for plural handling (e.g., "shoes" → "shoe")
    public static String normalize(String word) {
        if (word.toLowerCase().endsWith("s") && word.length() > 3) {
            return word.substring(0, word.length() - 1).toLowerCase();
        }
        return word.toLowerCase();
    }

    public static Product searchByName(Product[] products, String searchTerm) {
        String normalizedTerm = normalize(searchTerm);
        for (Product product : products) {
            if (normalize(product.productName).equals(normalizedTerm)) {
                return product;
            }
        }
        return null;
    }

    public static Product searchById(Product[] products, int id) {
        for (Product product : products) {
            if (product.productId == id) {
                return product;
            }
        }
        return null;
    }

    public static void searchByCategory(Product[] products, String category) {
        boolean found = false;
        for (Product product : products) {
            if (product.category.equalsIgnoreCase(category)) {
                product.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products found in this category.");
        }
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product(101, "Laptop", "Electronics", 65000),
            new Product(102, "Shirt", "Clothing", 1200),
            new Product(103, "Mouse", "Electronics", 800),
            new Product(104, "Book", "Education", 500),
            new Product(105, "Shoes", "Footwear", 2200),
            new Product(106, "Phone", "Electronics", 30000),
            new Product(107, "T-shirt", "Clothing", 700),
            new Product(108, "Keyboard", "Electronics", 1500),
            new Product(109, "Pen", "Education", 50),
            new Product(110, "Notebook", "Education", 150),
            new Product(111, "Watch", "Accessories", 4000),
            new Product(112, "Backpack", "Accessories", 1800),
            new Product(113, "Smartwatch", "Electronics", 5500),
            new Product(114, "Dress", "Clothing", 2500),
            new Product(115, "Socks", "Footwear", 300)
        };

        Product[] sortedProducts = Arrays.copyOf(products, products.length);
        Arrays.sort(sortedProducts, Comparator.comparing(p -> p.productName.toLowerCase()));

        Scanner scanner = new Scanner(System.in);
        System.out.println("===== E-Commerce Product Search =====");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Search by Product Name");
            System.out.println("2. Search by Product ID");
            System.out.println("3. Search by Category");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    Product byName = searchByName(products, name);
                    System.out.println("\n🔍 Linear Search:");
                    if (byName != null) {
                        System.out.println("Product found:");
                        byName.display();
                    } else {
                        System.out.println("Product not found.");
                    }

                    System.out.println("\n🔍 Binary Search (on sorted data):");
                    Product byNameBinary = searchByName(sortedProducts, name);
                    if (byNameBinary != null) {
                        System.out.println("Product found:");
                        byNameBinary.display();
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter product ID: ");
                    int id = scanner.nextInt();
                    Product byId = searchById(products, id);
                    if (byId != null) {
                        System.out.println("Product found:");
                        byId.display();
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.println("Products in category \"" + category + "\":");
                    searchByCategory(products, category);
                    break;

                case 4:
                    System.out.println("Exiting program. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
