package br.com.alura.desafio;

import br.com.alura.netflix.modelos.Titulo;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsultaLivroGoogleBooks {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite o título do livro para a busca: ");
        var tituloLivro = leitura.nextLine();

        String chave = "AIzaSyAEXfHq8P0VAFx7IbKjUq-Qv-ygK40OW-g";
        String endereco = "https://www.googleapis.com/books/v1/volumes?q=" + tituloLivro + "?key=" + chave;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request,  HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        Gson gson = new Gson();

        Titulo titulo = gson.fromJson(json, Titulo.class);
        System.out.println("Titulo: " + titulo.getNome());



    }
}
