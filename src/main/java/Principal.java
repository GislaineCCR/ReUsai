import dao.*;
import model.*;

import java.util.Scanner;

public class Principal {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private static final ItemDAO itemDAO = new ItemDAO();
    private static final FotoDAO fotoDAO = new FotoDAO();
    private static final ConversaDAO conversaDAO = new ConversaDAO();
    private static final MensagemDAO mensagemDAO = new MensagemDAO();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    gerenciarUsuarios();
                    break;
                case 2:
                    gerenciarCategorias();
                    break;
                case 3:
                    gerenciarItens();
                    break;
                case 4:
                    gerenciarFotos();
                    break;
                case 5:
                    gerenciarConversas();
                    break;
                case 6:
                    gerenciarMensagens();
                    break;
                case 0:
                    System.out.println("Saindo da aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Gerenciar Usuários");
        System.out.println("2. Gerenciar Categorias");
        System.out.println("3. Gerenciar Itens");
        System.out.println("4. Gerenciar Fotos");
        System.out.println("5. Gerenciar Conversas");
        System.out.println("6. Gerenciar Mensagens");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void exibirSubMenu(String entidade) {
        System.out.println("\n--- GERENCIAR " + entidade.toUpperCase() + " ---");
        System.out.println("1. Inserir");
        System.out.println("2. Atualizar");
        System.out.println("3. Remover");
        System.out.println("4. Listar");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            return -1;
        }
    }

    private static void gerenciarUsuarios() {
        int opcao;
        do {
            exibirSubMenu("Usuários");
            opcao = lerOpcao();
            switch (opcao) {
                case 1: {
                    Usuario u = new Usuario();
                    System.out.print("Nome completo: ");
                    u.setNomeCompleto(scanner.nextLine());
                    System.out.print("Email: ");
                    u.setEmail(scanner.nextLine());
                    System.out.print("Senha: ");
                    u.setSenha(scanner.nextLine());
                    System.out.print("Telefone: ");
                    u.setTelefone(scanner.nextLine());
                    System.out.print("Endereço: ");
                    u.setEndereco(scanner.nextLine());
                    usuarioDAO.inserir(u);
                    break;
                }
                case 2: {
                    System.out.print("ID do usuário a ser atualizado: ");
                    int id = lerOpcao();
                    if (id == -1) continue;
                    Usuario u = new Usuario();
                    u.setId(id);
                    System.out.print("Novo nome completo: ");
                    u.setNomeCompleto(scanner.nextLine());
                    System.out.print("Novo email: ");
                    u.setEmail(scanner.nextLine());
                    System.out.print("Nova senha: ");
                    u.setSenha(scanner.nextLine());
                    System.out.print("Novo telefone: ");
                    u.setTelefone(scanner.nextLine());
                    System.out.print("Novo endereço: ");
                    u.setEndereco(scanner.nextLine());
                    usuarioDAO.atualizar(u);
                    break;
                }
                case 3: {
                    System.out.print("ID do usuário a ser removido: ");
                    int id = lerOpcao();
                    if (id == -1) continue;
                    usuarioDAO.remover(id);
                    break;
                }
                case 4: {
                    System.out.println("\n--- LISTA DE USUÁRIOS ---");
                    for (Usuario u : usuarioDAO.listarTodos()) {
                        System.out.println("ID: " + u.getId() + 
                                         " | Nome: " + u.getNomeCompleto() + 
                                         " | Email: " + u.getEmail() + 
                                         " | Telefone: " + u.getTelefone());
                    }
                    System.out.println("-----------------------");
                    break;
                }
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void gerenciarCategorias() {
        int opcao;
        do {
            exibirSubMenu("Categorias");
            opcao = lerOpcao();
            switch (opcao) {
                case 1: {
                    Categoria c = new Categoria();
                    System.out.print("Nome da categoria: ");
                    c.setNome(scanner.nextLine());
                    System.out.print("Descrição: ");
                    c.setDescricao(scanner.nextLine());
                    categoriaDAO.inserir(c);
                    break;
                }
                case 2: {
                    System.out.print("ID da categoria a ser atualizada: ");
                    int id = lerOpcao();
                    if (id == -1) continue;
                    Categoria c = new Categoria();
                    c.setId(id);
                    System.out.print("Novo nome: ");
                    c.setNome(scanner.nextLine());
                    System.out.print("Nova descrição: ");
                    c.setDescricao(scanner.nextLine());
                    categoriaDAO.atualizar(c);
                    break;
                }
                case 3: {
                    System.out.print("ID da categoria a ser removida: ");
                    int id = lerOpcao();
                    if (id == -1) continue;
                    categoriaDAO.remover(id);
                    break;
                }
                case 4: {
                    System.out.println("\n--- LISTA DE CATEGORIAS ---");
                    for (Categoria c : categoriaDAO.listarTodos()) {
                        System.out.println("ID: " + c.getId() + 
                                         " | Nome: " + c.getNome() + 
                                         " | Descrição: " + c.getDescricao());
                    }
                    System.out.println("--------------------------");
                    break;
                }
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void gerenciarItens() {
        int opcao;
        do {
            exibirSubMenu("Itens");
            opcao = lerOpcao();
            switch (opcao) {
                case 1: {
                    Item i = new Item();
                    System.out.print("ID do usuário doador: ");
                    i.setIdUsuarioDoador(lerOpcao());
                    System.out.print("ID da categoria: ");
                    i.setIdCategoria(lerOpcao());
                    System.out.print("Título: ");
                    i.setTitulo(scanner.nextLine());
                    System.out.print("Descrição: ");
                    i.setDescricao(scanner.nextLine());
                    System.out.print("Status (Ex: Disponível): ");
                    i.setStatus(scanner.nextLine());
                    itemDAO.inserir(i);
                    break;
                }
                case 2: {
                    System.out.print("ID do item a ser atualizado: ");
                    int id = lerOpcao();
                    if (id == -1) continue;
                    Item i = new Item();
                    i.setId(id);
                    System.out.print("Novo ID do usuário doador: ");
                    i.setIdUsuarioDoador(lerOpcao());
                    System.out.print("Novo ID da categoria: ");
                    i.setIdCategoria(lerOpcao());
                    System.out.print("Novo título: ");
                    i.setTitulo(scanner.nextLine());
                    System.out.print("Nova descrição: ");
                    i.setDescricao(scanner.nextLine());
                    System.out.print("Novo status: ");
                    i.setStatus(scanner.nextLine());
                    itemDAO.atualizar(i);
                    break;
                }
                case 3: {
                    System.out.print("ID do item a ser removido: ");
                    int id = lerOpcao();
                    if (id == -1) continue;
                    itemDAO.remover(id);
                    break;
                }
                case 4: {
                    System.out.println("\n--- LISTA DE ITENS ---");
                    for (Item i : itemDAO.listarTodos()) {
                        System.out.println("ID: " + i.getId() + 
                                         " | Título: " + i.getTitulo() + 
                                         " | Doador ID: " + i.getIdUsuarioDoador() + 
                                         " | Categoria ID: " + i.getIdCategoria() + 
                                         " | Status: " + i.getStatus());
                    }
                    System.out.println("-------------------");
                    break;
                }
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void gerenciarFotos() {
        int opcao;
        do {
            exibirSubMenu("Fotos");
            opcao = lerOpcao();
            switch (opcao) {
                case 1: {
                    Foto f = new Foto();
                    System.out.print("ID do item: ");
                    f.setIdItem(lerOpcao());
                    System.out.print("URL da foto: ");
                    f.setUrlFoto(scanner.nextLine());
                    System.out.print("Descrição: ");
                    f.setDescricao(scanner.nextLine());
                    fotoDAO.inserir(f);
                    break;
                }
                case 2: {
                    System.out.print("ID da foto a ser atualizada: ");
                    int id = lerOpcao();
                    if (id == -1) continue;
                    Foto f = new Foto();
                    f.setId(id);
                    System.out.print("Novo ID do item: ");
                    f.setIdItem(lerOpcao());
                    System.out.print("Nova URL da foto: ");
                    f.setUrlFoto(scanner.nextLine());
                    System.out.print("Nova descrição: ");
                    f.setDescricao(scanner.nextLine());
                    fotoDAO.atualizar(f);
                    break;
                }
                case 3: {
                    System.out.print("ID da foto a ser removida: ");
                    int id = lerOpcao();
                    if (id == -1) continue;
                    fotoDAO.remover(id);
                    break;
                }
                case 4: {
                    System.out.println("\n--- LISTA DE FOTOS ---");
                    for (Foto f : fotoDAO.listarTodos()) {
                        System.out.println("ID: " + f.getId() + 
                                         " | Item ID: " + f.getIdItem() + 
                                         " | URL: " + f.getUrlFoto() + 
                                         " | Descrição: " + f.getDescricao());
                    }
                    System.out.println("-------------------");
                    break;
                }
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void gerenciarConversas() {
        int opcao;
        do {
            exibirSubMenu("Conversas");
            opcao = lerOpcao();
            switch (opcao) {
                case 1: {
                    Conversa c = new Conversa();
                    System.out.print("ID do item: ");
                    c.setIdItem(lerOpcao());
                    System.out.print("ID do usuário interessado: ");
                    c.setIdUsuarioInteressado(lerOpcao());
                    System.out.print("Status (Ex: Ativa): ");
                    c.setStatus(scanner.nextLine());
                    conversaDAO.inserir(c);
                    break;
                }
                case 2: {
                    System.out.print("ID da conversa a ser atualizada: ");
                    int idAtualizar = lerOpcao();
                    if (idAtualizar == -1) continue;
                    Conversa c = new Conversa();
                    c.setId(idAtualizar);
                    System.out.print("Novo ID do item: ");
                    c.setIdItem(lerOpcao());
                    System.out.print("Novo ID do usuário interessado: ");
                    c.setIdUsuarioInteressado(lerOpcao());
                    System.out.print("Novo status: ");
                    c.setStatus(scanner.nextLine());
                    conversaDAO.atualizar(c);
                    break;
                }
                case 3: {
                    System.out.print("ID da conversa a ser removida: ");
                    int idRemover = lerOpcao();
                    if (idRemover == -1) continue;
                    conversaDAO.remover(idRemover);
                    break;
                }
                case 4: {
                    System.out.println("\n--- LISTA DE CONVERSAS ---");
                    for (Conversa c : conversaDAO.listarTodos()) {
                        System.out.println("ID: " + c.getId() + 
                                         " | Item ID: " + c.getIdItem() + 
                                         " | Usuário Interessado ID: " + c.getIdUsuarioInteressado() + 
                                         " | Status: " + c.getStatus());
                    }
                    System.out.println("------------------------");
                    break;
                }
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void gerenciarMensagens() {
        int opcao;
        do {
            exibirSubMenu("Mensagens");
            opcao = lerOpcao();
            switch (opcao) {
                case 1: {
                    Mensagem m = new Mensagem();
                    System.out.print("ID da conversa: ");
                    m.setIdConversa(lerOpcao());
                    System.out.print("ID do remetente: ");
                    m.setIdRemetente(lerOpcao());
                    System.out.print("Conteúdo da mensagem: ");
                    m.setConteudo(scanner.nextLine());
                    System.out.print("Status (Ex: Não lida): ");
                    m.setStatusLeitura(scanner.nextLine());
                    mensagemDAO.inserir(m);
                    break;
                }
                case 2: {
                    System.out.print("ID da mensagem a ser atualizada: ");
                    int idAtualizar = lerOpcao();
                    if (idAtualizar == -1) continue;
                    Mensagem m = new Mensagem();
                    m.setId(idAtualizar);
                    System.out.print("Novo ID da conversa: ");
                    m.setIdConversa(lerOpcao());
                    System.out.print("Novo ID do remetente: ");
                    m.setIdRemetente(lerOpcao());
                    System.out.print("Novo conteúdo: ");
                    m.setConteudo(scanner.nextLine());
                    System.out.print("Novo status: ");
                    m.setStatusLeitura(scanner.nextLine());
                    mensagemDAO.atualizar(m);
                    break;
                }
                case 3: {
                    System.out.print("ID da mensagem a ser removida: ");
                    int idRemover = lerOpcao();
                    if (idRemover == -1) continue;
                    mensagemDAO.remover(idRemover);
                    break;
                }
                case 4: {
                    System.out.println("\n--- LISTA DE MENSAGENS ---");
                    for (Mensagem m : mensagemDAO.listarTodos()) {
                        System.out.println("ID: " + m.getId() + 
                                         " | Conversa ID: " + m.getIdConversa() + 
                                         " | Remetente ID: " + m.getIdRemetente() + 
                                         " | Conteúdo: " + (m.getConteudo().length() > 30 ? m.getConteudo().substring(0, 30) + "..." : m.getConteudo()) + 
                                         " | Status: " + m.getStatusLeitura());
                    }
                    System.out.println("------------------------");
                    break;
                }
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}