package es.ull.patrones.model;

import es.ull.patrones.strategy.ProductJSONParser;
import es.ull.patrones.view.Observer;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class VintedApiModel {
  private String data;
  private List<Observer> observers = new ArrayList<>();

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void notifyObservers(List<Product> productList) {
    for (Observer observer : observers) {
      observer.update(productList);
    }
  }

  // Método que realiza la solicitud HTTP y procesa la respuesta.
  public void fetchData(int page, String object, int priceMin, int priceMax, int favourites) {
    try {
      String obj = convertir(object);
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create("https://vinted3.p.rapidapi.com/getSearch?country=es&page=" + page + "&order=relevance&keyword=" + obj + "&minPrice=" + priceMin + "&maxPrice=" + priceMax))
              .header("X-RapidAPI-Key", "d20abb9bbemshed65cab5be0377ep1037c2jsneed168838a15")
              .header("X-RapidAPI-Host", "vinted3.p.rapidapi.com")
              .method("GET", HttpRequest.BodyPublishers.noBody())
              .build();
      HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
      // Notifica a los observadores con los datos procesados
      data = response.body();
      ProductJSONParser parser = new ProductJSONParser(data, favourites);
      notifyObservers(parser.getProductList());

    } catch (InterruptedException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String convertir(String objeto) {
    return objeto.replace(" ", "%20");
  }
}
