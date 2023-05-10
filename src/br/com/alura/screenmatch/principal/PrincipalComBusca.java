package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite um filme para busca:");
        var busca = leitura.nextLine();

        String baseUrl = System.getenv("OMDB_BASE_URL");
        String apiKey = System.getenv("OMDB_API_KEY");
        URL url = new URL(baseUrl + URLEncoder.encode(busca, StandardCharsets.UTF_8) + apiKey);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.valueOf(url)))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            String json = response.body();

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);

            if(meuTituloOmdb.getResponse().equals("True")) {
                if(meuTituloOmdb.getYear().length() != 4) {
                    meuTituloOmdb.setYear(String.valueOf(1900));
                    System.out.println("## Atenção: Ano de lançamento inválido na base de dados ##");
                }

                String regex = "[a-zA-Z]";
                if(meuTituloOmdb.getRuntime().substring(0,1).matches(regex)){
                    meuTituloOmdb.setRuntime(String.valueOf(1));
                    System.out.println("## Atenção: Duração em minutos inválida na base de dados ##");
                }

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Titulo convertido:");
                System.out.println(meuTitulo);

            }
        } catch (NumberFormatException e) {
            System.out.println("Aconteceu um erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento. Verifique o endereço digitado.");
            System.out.println("Mensagem de Erro: " + e.getMessage());
//        }  catch (RuntimeException e) {
//            System.out.println(e.getMessage());
        }

    }
}