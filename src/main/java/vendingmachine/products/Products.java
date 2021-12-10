package vendingmachine.products;

import vendingmachine.ValidatorMessage;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private static final String PRODUCT_INFO_SEPARATE_UNIT = ",";
    private static final int PRODUCT_NAME_INFO_INDEX = 0;
    private static final int PRODUCT_PRICE_INFO_INDEX = 1;
    private static final int PRODUCT_COUNTS_INFO_INDEX = 2;

    private final List<Product> products;

    public Products() {
        products = new ArrayList<>();
    }

    public void setProducts(List<String> productList) {
        for (String product : productList) {
            products.add(setProduct(product));
        }
    }

    public Product setProduct(String productInfo) {
        String[] productInfos = productInfo.split(PRODUCT_INFO_SEPARATE_UNIT);
        String name = productInfos[PRODUCT_NAME_INFO_INDEX];
        int price = Integer.parseInt(productInfos[PRODUCT_PRICE_INFO_INDEX]);
        int counts = Integer.parseInt(productInfos[PRODUCT_COUNTS_INFO_INDEX]);
        return new Product(name, price, counts);
    }

    public Product findProduct(String productName) {
        return products.stream()
                .filter(product -> product.getName().equals(productName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ValidatorMessage.ERROR_MESSAGE
                        + ValidatorMessage.NULL_PRODUCT_MESSAGE));
    }

    public boolean findCanBuyProducts(int amount) {
        return products.stream()
                .anyMatch(product -> product.getPrice() <= amount && product.getCounts() > 0);
    }

    public void buyProduct(Product product) {
        product.minusCount();
    }
}
