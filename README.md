<p align="center">
  <img src="https://www.carpemundi.com.br/wp-content/uploads/2018/06/livros-de-viagem-capa-1.jpg.webp" alt="Print do Menu" width="700">
</</p>

# 📚 LiterAlura

Bem-vindo ao **LiterAlura**, um catálogo de livros interativo via linha de comando (CLI) construído com Java e Spring Boot! Este projeto foi desenvolvido como parte do Challenge Back-End da Alura.

O LiterAlura consome a API pública do [Gutendex](https://gutendex.com/) (baseada no Projeto Gutenberg) para buscar informações reais sobre livros de domínio público, filtrá-los e armazená-los em um banco de dados local para consultas futuras.

## 🚀 Funcionalidades

Através de um menu interativo no console, o usuário pode:
- [x] **1.** Buscar livros pelo título na API web e salvar no banco de dados.
- [x] **2.** Listar todos os livros já registrados no banco de dados local.
- [x] **3.** Listar todos os autores registrados.
- [x] **4.** Buscar autores que estavam vivos em um determinado ano.
- [x] **5.** Filtrar livros registrados por idioma (ex: Inglês, Português, Espanhol).

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** (Configuração, Injeção de Dependências e Command Line Runner)
- **Spring Data JPA** (Persistência de dados e Consultas Derivadas/JPQL)
- **Banco de Dados H2** (Banco em memória para testes rápidos)
- **Jackson (ObjectMapper)** (Desserialização de JSON)
- **HttpClient** (Requisições HTTP para a API Gutendex)

## ⚙️ Como Executar o Projeto

1. **Clone este repositório:**
   ```bash
   git clone [https://github.com/ismaelblog/literalura.git](https://github.com/ismaelblog/literalura.git)
   
   
2. **Abra o projeto na sua IDE favorita** (IntelliJ IDEA, Eclipse, VS Code).

3. **Atualize as dependências do Maven** para garantir que o Spring Boot e o banco H2 sejam baixados.

4. **Execute a classe principal** ``LiteraluraApplication.java.``

5. O aplicativo será iniciado no terminal/console da sua IDE. Basta digitar os números correspondentes no menu para interagir com o sistema!

## 🧠 Estrutura e Arquitetura
O projeto segue o padrão **MVC (Model-View-Controller)** adaptado para console:

**Model:** Classes ``Livro`` e ``Autor`` mapeadas com ``@Entity`` para o banco de dados, e Records para captura do JSON.

**Repository:** Interfaces estendendo ``JpaRepository`` para comunicação com o banco.

**Service:** Classes responsáveis pelo consumo da API e conversão dos dados.

**Principal:** Classe que gerencia o menu de iteração com o usuário.


---


Desenvolvido com ☕ e dedicação durante a formação Back-End da Alura & Oracle.
