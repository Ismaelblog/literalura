package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.DadosResultados;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    
                    ---------------------------------------
                    *** Escolha o número de sua opção: ***
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    ---------------------------------------
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosNoAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo do LiterAlura. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivroWeb() {
        System.out.println("Digite o nome do livro que você deseja buscar:");
        var nomeLivro = leitura.nextLine();

        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        DadosResultados dados = conversor.obterDados(json, DadosResultados.class);

        if (dados.resultados().isEmpty()) {
            System.out.println("Livro não encontrado na API.");
        } else {
            var dadosLivro = dados.resultados().get(0);

            Autor autor = new Autor(dadosLivro.autores().get(0));
            autorRepository.save(autor);

            Livro livro = new Livro(dadosLivro);
            livro.setAutor(autor);
            livroRepository.save(livro);

            System.out.println("Livro Encontrado e Salvo com Sucesso!");
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + autor.getNome());
            System.out.println("Idioma: " + livro.getIdioma());
        }
    }

    private void listarLivrosRegistrados() {
        System.out.println("\n--- LIVROS REGISTRADOS NO BANCO ---");
        var livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
        } else {
            livros.forEach(l -> System.out.println(
                    "Título: " + l.getTitulo() +
                            " | Autor: " + l.getAutor().getNome() +
                            " | Idioma: " + l.getIdioma()
            ));
        }
    }

    private void listarAutoresRegistrados() {
        System.out.println("\n--- AUTORES REGISTRADOS NO BANCO ---");
        var autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado ainda.");
        } else {
            autores.forEach(a -> System.out.println(
                    "Autor: " + a.getNome() +
                            " | Nascimento: " + a.getAnoNascimento() +
                            " | Falecimento: " + a.getAnoFalecimento()
            ));
        }
    }

    private void listarAutoresVivosNoAno() {
        System.out.println("\n--- BUSCA DE AUTORES VIVOS POR ANO ---");
        System.out.println("Insira o ano que deseja pesquisar:");

        try {
            var ano = leitura.nextInt();
            leitura.nextLine();

            var autores = autorRepository.buscarAutoresVivosNoAno(ano);

            if (autores.isEmpty()) {
                System.out.println("Nenhum autor vivo encontrado nesse ano nos nossos registros.");
            } else {
                autores.forEach(a -> System.out.println(
                        "Autor: " + a.getNome() +
                                " | Nascimento: " + a.getAnoNascimento() +
                                " | Falecimento: " + a.getAnoFalecimento()
                ));
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida! Por favor, digite um ano numérico válido.");
            leitura.nextLine();
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("\n--- BUSCA DE LIVROS POR IDIOMA ---");
        System.out.println("Insira o idioma para realizar a busca:");
        System.out.println("es - espanhol");
        System.out.println("en - inglês");
        System.out.println("fr - francês");
        System.out.println("pt - português");

        var idioma = leitura.nextLine();

        var livros = livroRepository.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Não existem livros registrados nesse idioma no momento.");
        } else {
            livros.forEach(l -> System.out.println(
                    "Título: " + l.getTitulo() +
                            " | Autor: " + l.getAutor().getNome() +
                            " | Idioma: " + l.getIdioma()
            ));
        }
    }
}