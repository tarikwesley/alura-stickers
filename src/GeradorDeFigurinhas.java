import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradorDeFigurinhas {
  public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

    BufferedImage imagemOriginal = ImageIO.read(inputStream);
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;

    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);

    graphics.drawString("TOPZERA", 100, novaAltura - 100);
    ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
  }
}
