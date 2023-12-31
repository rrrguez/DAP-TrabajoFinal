package es.ull.patrones.strategy;

import es.ull.patrones.model.Product;
import es.ull.patrones.strategy.JSONParserStrategy;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductJSONParser implements JSONParserStrategy {

  private List<Product> productList;

  public ProductJSONParser(String data, int noOfFavourites) {
    parse(data, noOfFavourites);
  }

  public void parse(String data, Object... parameters) {
    // Check if the parameters provided are correct
    if (parameters.length < 1 || !(parameters[0] instanceof Integer)) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    int noOfFavouritesValue = (int) parameters[0];

    // Start the parsing
    JSONArray productsArray = new JSONArray(data);
    productList = new ArrayList<>();
    // Assuming each item in the array represents a product
    for (int i = 0; i < productsArray.length(); i++) {
      JSONObject productJson = productsArray.getJSONObject(i);
      System.out.println(productJson);
      int favourites = productJson.getInt("favourites");

      // Extract product details from the JSON and create corresponding objects
      if (favourites >= noOfFavouritesValue) {
        int productId = productJson.getInt("productId");
        String title = productJson.getString("title");
        String url = productJson.getString("url");
        JSONObject price = productJson.getJSONObject("price");
        String totalAmount = price.getString("totalAmount");
        String currency = price.getString("currency");
        String imageUrl = productJson.getString("image");
        Product product = new Product(productId, title, url, favourites, totalAmount, currency, imageUrl);
        productList.add(product);
      }
    }
  }

  public List<Product> getProductList() {
    return productList;
  }
}
