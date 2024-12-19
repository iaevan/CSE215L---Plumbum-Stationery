public class DataStorage {
    public static Products[] products;
    public static int productsSize;
    public static User[] users;
    public static int usersSize;
    public static Order[] orders;
    public static int ordersSize;
    public static Sales[] sales;
    public static int salesSize;

    static {
        products = new Products[100];
        productsSize = 0;
        users = new User[50];
        usersSize = 0;
        orders = new Order[200];
        ordersSize = 0;
        sales = new Sales[200];
        salesSize = 0;

    }

    private DataStorage() {
    }

    public static void addProduct(Products product) {
        if (productsSize < products.length) {
            products[productsSize++] = product;
        } else {

        }
    }

    public static void addUser(User user) {
        if (usersSize < users.length) {
            users[usersSize++] = user;
        } else {

        }
    }

    public static void addOrder(Order order) {
        if (ordersSize < orders.length) {
            orders[ordersSize++] = order;
        } else {

        }
    }

    public static void addSales(Sales sale) {
        if (salesSize < sales.length) {
            sales[salesSize++] = sale;
        } else {

        }
    }

    public static Products[] getProducts() {
        return products;
    }

    public static User[] getUsers() {
        return users;
    }

    public static Order[] getOrders() {
        return orders;
    }

    public static Sales[] getSales() {
        return sales;
    }

    public static User getUser(String username) {
        for (int i = 0; i < usersSize; i++) {
            if (users[i] != null && users[i].getUsername().equals(username)) {
                return users[i];
            }
        }
        return null;
    }

    static {
        products = new Products[100];
        productsSize = 0;

        addProduct(new Products("Sticky Cartoon Tabs (SCT)", 120, 29));
        addProduct(new Products("Space Notebooks", 799, 16));
        addProduct(new Products("Vintage Notebook (VNB)", 299, 21));
        addProduct(new Products("3D Sticker Set with Tweezers", 640, 9));
        addProduct(new Products("Erasable Gel Pens", 79, 35));
        addProduct(new Products("Transparent Sticky Notes (TSN)", 100, 195));
        addProduct(new Products("Washi Tape (WAT)", 499, 16));
        addProduct(new Products("Scrapbooking Set (SBS)", 499, 28));
        addProduct(new Products("Sticky Highlighter Strips (SHS)", 149, 37));
        addProduct(new Products("Metal Bookmarks (MBM)", 199, 3));
        addProduct(new Products("Mini Poem Books (MPB)", 599, 1));
        addProduct(new Products("Mini Note Books (MNB)", 15, 30));
        addUser(new Admin("admin", "12345"));
        addUser(new Salesperson("sale", "12345"));

    }

    public static void updateStock(Products product, int quantityChange) {
        for (int i = 0; i < productsSize; i++) {
            if (products[i] != null && products[i].equals(product)) {
                int newStock = products[i].getStockQuantity() + quantityChange;

                products[i].setStockQuantity(newStock);
                return;
            }
        }
    }

}