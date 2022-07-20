import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
public class Main {
  public static void main(String[] args) throws Exception {
    // fazer uma conexão HTTP e buscar os top 250 filmes
    String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
    URI endereco = URI.create(url);
    var client = HttpClient.newBuilder().build();
    var request = HttpRequest.newBuilder(endereco).GET().build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    String body = response.body();

    // extrair só os dados que interessam (titulo, poster, classificação)
    var parser = new JsonParser();
    List<Map<String, String>> listaDeFilmes = parser.parse(body);
    System.out.println(listaDeFilmes.size());

    // exibir e manipular os dados
    var geradora = new GeradorDeFigurinhas();
    for (Map<String, String> filme : listaDeFilmes) {
      String urlImagem = filme.get("image");
      String titulo = filme.get("title");
      String nomeArquivo = titulo + ".png";

      InputStream inputStream = new URL(urlImagem).openStream();
      geradora.cria(inputStream, nomeArquivo);

      System.out.println(filme.get("title"));
      System.out.println();
    }
  }
}
