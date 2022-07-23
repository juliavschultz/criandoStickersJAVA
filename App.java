
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        //BUSCAR DADOS
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //EXTRAIR OS DADOS QUE INTERESSAM --PARSER
        var parser = new jsonparserteste();
        List<Map<String, String>> listaDeFilmes = parser.parse(body); //faz a lista de filmes passar pelo parser, uma clase definida em JsonParser
        //System.out.println(listaDeFilmes.size()); //pra mostrar o tamanho da lista
        //System.out.println(listaDeFilmes.get(index: 0));

        //EXIBIR OS DADOS --GERAR FIGURINHAS--
        var geradora = new gerarFigurinha();
        for (int i=0; i<10; i++){
            Map<String,String> filme = listaDeFilmes.get(i); 

                String urlImagem = filme.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
                String titulo = filme.get("title");

                InputStream inputStream = new URL(urlImagem).openStream();
                String nomeArquivo = "saida/" + titulo + ".png";

                geradora.criar(inputStream, nomeArquivo);

                System.out.println(titulo);
                System.out.println();
        }
    }    
}
