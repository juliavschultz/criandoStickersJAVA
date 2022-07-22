import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class gerarFigurinha {
    
    public void criar (InputStream inputStream, String nomeArquivo) throws Exception{

        //LER IMAGEM
        //BufferedImage imagemOriginal = ImageIO.read(new File("entrada/filme.jpg")); //entrada da imagem
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //CRIA NOVA IMAGEM -COM TRANSPARENCIA-
        int largura = imagemOriginal.getWidth(); //pega parametros da imagem
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 100; //acrescenta mais 200 px de altura
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT); //definição de parametros da nova imagem --aqui a nova imagem ainda está vazia
                
        //COPIAR A IMAGEM ORIGINAL PARA NOVA IMAGEM
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics(); //define que a novaimagem será trabalhada com grapichs2D
        graphics.drawImage(imagemOriginal, 0, 0, null); //acrescenta a imagem original na novaimagem
        
        //configurar fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 32); //fonte, italico/negrito, tamanho 
        graphics.setColor(Color.YELLOW); //aplica no grafico
        graphics.setFont(fonte); //aplica a var fonte

        //ESCREVER UMA FRASE NA NOVA IMAGEM
        graphics.drawString("TOP 250",0, novaAltura-50); //o que é escrito, posiçãoX, posiçãoY
        //ESCREVER A NOVA IMAGEM EM ARQUIVO
        ImageIO.write(novaImagem, "png", new File(nomeArquivo)); //escreve o arquivo png, caminho
    }
}
