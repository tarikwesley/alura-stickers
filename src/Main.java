import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
  public static void main(String[] args) throws Exception {
    //    String urlIMDB = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
    //    ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

    String urlNasa = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
    ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

    var http = new ClienteHttp();
    String json = http.buscaDados(urlNasa);

    List<Conteudo> conteudos = extrator.extraiConteudos(json);

    var geradora = new GeradorDeFigurinhas();

    for (int i = 0; i < 3; i++) {
      Conteudo conteudo = conteudos.get(i);

      InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
      String nomeArquivo = conteudo.getTitulo() + ".png";

      geradora.cria(inputStream, nomeArquivo);

      System.out.println(conteudo.getTitulo());
      System.out.println();
    }
  }
}
