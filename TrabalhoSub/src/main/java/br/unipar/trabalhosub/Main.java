/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.unipar.trabalhosub;


import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author renat
 */
public class Main {


    private static Pokemon[] pokemons = new Pokemon[10];
    private static Pokemon[] player1 = new Pokemon[3];
    private static Pokemon[] player2 = new Pokemon[3];
    private static int num = 0;

    public static void main(String[] args) {
      
   
       

        int opcao;
        do {
        opcao = exibirMenuInicial();
        switch (opcao) {
        case 1:
            cadastrarPokemon();
            break;
        case 2:
            iniciarBatalha();
            break;
        case 3:
            mostrarPokemons();
            break;
        case 0:
            JOptionPane.showMessageDialog(
                    null, "Obrigado por jogar!");
            break;
        default:
            JOptionPane.showMessageDialog(
                    null, "Opção inválida!");
        }
        } while (opcao != 0);
    }
    
    
    
    

    private static int exibirMenuInicial() {
        String mensagem = "Escolha uma opção:\n" +
                "1 - Cadastrar Pokémon\n" +
                "2 - Iniciar batalha\n" +
                "3 - Mostrar Pokémon\n" +
                "0 - Sair";
        String strOpcao = JOptionPane.showInputDialog(
                null, mensagem);
        return Integer.parseInt(strOpcao);
    }





    private static void cadastrarPokemon() {
        if (num >= 10) {
            JOptionPane.showMessageDialog(null, 
    "Número máximo de Pokémons cadastrados atingido!");
            return;
        }

        String nome = JOptionPane.showInputDialog("Nome do Pokémon:");
        int forca = Integer.parseInt(JOptionPane.showInputDialog(
                "Força do Pokémon:"));
        int ataque = Integer.parseInt(JOptionPane.showInputDialog(
                "Ataque do Pokémon:"));
        int defesa = Integer.parseInt(JOptionPane.showInputDialog(
                "Defesa do Pokémon:"));
        int agilidade = Integer.parseInt(JOptionPane.showInputDialog(
                "Agilidade do Pokémon:"));

        Pokemon novoPokemon = new Pokemon( nome, forca, ataque, defesa, agilidade);
        pokemons[num] = novoPokemon;
        num++;

        JOptionPane.showMessageDialog(null, 
                "Pokémon cadastrado com sucesso!");
    }
    
    
    
    
    

    private static void iniciarBatalha() {
        if (num < 6) {
            JOptionPane.showMessageDialog(null, 
     "É necessário cadastrar pelo menos 6 Pokémons para iniciar a batalha!");
            return;
        }

        ordenarPokemons();
        selecionarPokemons(player1, "Jogador 1");
        selecionarPokemons(player2, "Jogador 2");

        ordenarPokemons(player1);
        ordenarPokemons(player2);

        exibirPokemons(player1, "Jogador 1");
        exibirPokemons(player2, "Jogador 2");
        batalha();
        
    }
    
    
    
    
    

    private static void ordenarPokemons() {
        for (int i = 1; i < num; i++) {
            Pokemon chave = pokemons[i];
            int j = i - 1;
            while (j >= 0 && pokemons[j].getForca() > chave.getForca()) {
                pokemons[j + 1] = pokemons[j];
                j--;
            }
            pokemons[j + 1] = chave;
        }
    }

    
    
    
    
    private static void selecionarPokemons(Pokemon[] jogador, String jogadorNome) {
        JOptionPane.showMessageDialog(null, jogadorNome + 
       ", selecione 3 Pokémons para a batalha.");

        for (int i = 0; i < 3; i++) {
            String mensagem = "Pokémons disponíveis:\n";
            for (int j = 0; j < num; j++) {
                mensagem += j + 1 + " - " + pokemons[j].getNome() + "\n";
            }
            int numPokemon = Integer.parseInt(JOptionPane.showInputDialog(
                    null, mensagem));

            jogador[i] = pokemons[numPokemon - 1];
        }
    }

    
    
    
    
    
    private static void ordenarPokemons(Pokemon[] jogador) {
        for (int i = 0; i < 2; i++) {
            int indiceMin = i;
        for (int j = i + 1; j < 3; j++) {
        if (jogador[j].getForca() < jogador[indiceMin].getForca()) 
        {
            indiceMin = j;
        }
        }
            Pokemon temp = jogador[i];
            jogador[i] = jogador[indiceMin];
            jogador[indiceMin] = temp;
        }
    }

    
    
    
    
    private static void exibirPokemons(Pokemon[] jogador, String jogadorNome) 
    {
        String mensagem = jogadorNome + ":\n";
        for (int i = 0; i < 3; i++) {
            mensagem += jogador[i].getNome() + "\n";
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }
    
    
    
    
    

    private static void batalha() {
    int rodada = 1;

    while (true) {
        JOptionPane.showMessageDialog(null, "Rodada " + rodada);

        Pokemon pokemonplayer1 = selecionarPokemon(player1,
                "Jogador 1");
        Pokemon pokemonplayer2 = selecionarPokemon(player2, 
                "Jogador 2");

        String atributo = selecionarAtributo();

        int valorAtributoplayer1 = obterValorAtributo(
                pokemonplayer1, atributo);
        int valorAtributoplayer2 = obterValorAtributo(
                pokemonplayer2, atributo);

        if (valorAtributoplayer1 > valorAtributoplayer2) {
            removerPokemon(player2);
            JOptionPane.showMessageDialog(null, 
                    "Jogador 1 venceu a rodada!");
        } else if (valorAtributoplayer1 < valorAtributoplayer2) {
            removerPokemon(player1);
            JOptionPane.showMessageDialog(null, 
                    "Jogador 2 venceu a rodada!");
        } else {
            JOptionPane.showMessageDialog(null, "Empate!");
        }

        if (player1[0] == null) {
            JOptionPane.showMessageDialog(null, 
                    "Player 2 venceu a batalha!");
            break;
        } else if (player2[0] == null) {
            JOptionPane.showMessageDialog(null, 
                    "Player 1 venceu a batalha!");
            break;
        }

        rodada++;
    }
    }

    
    
    
private static Pokemon selecionarPokemon(Pokemon[] jogador, String jogadorNome)
    {
        String mensagem = jogadorNome + ", selecione o Pokémon para a rodada.";
        String opcoes = "";
        for (int i = 0; i < 3; i++) {
        opcoes += i + 1 + " - " + jogador[i].getNome() + "\n";
        }
        int numPokemon = Integer.parseInt(JOptionPane.showInputDialog
        (null, mensagem, opcoes));
        return jogador[numPokemon - 1];
    }
    
    
    
    

    private static String selecionarAtributo() {
        String mensagem = "Selecione o atributo para a rodada:\n" +
                "1 - Força\n" +
                "2 - Ataque\n" +
                "3 - Defesa\n" +
                "4 - Agilidade";
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                null, mensagem));
        switch (opcao) {
            case 1:
                return "forca";
            case 2:
                return "ataque";
            case 3:
                return "defesa";
            case 4:
                return "agilidade";
            default:
                return "";
        }
    }

    
    
    
    
    private static int obterValorAtributo(Pokemon pokemon, String atributo) {
        switch (atributo) {
            case "forca":
                return pokemon.getForca();
            case "ataque":
                return pokemon.getAtaque();
            case "defesa":
                return pokemon.getDefesa();
            case "agilidade":
                return pokemon.getAgilidade();
            default:
                return 0;
        }
    }

    
    
    
    
    private static void removerPokemon(Pokemon[] jogador) {
        for (int i = 0; i < 2; i++) {
            jogador[i] = jogador[i + 1];
        }
        jogador[2] = null;
    }

    
    
    
    
    private static void mostrarPokemons() {
        
        String mensagem = "Escolha uma opção:\n" +
        "1 - Exibir todos os atributos\n" +
        "2 - Exibir pokémons ordenados pelo atributo\n" +
        "3 - Selecionar pokémon desejado\n" +
        "4 - Sequencial Fibonacci\n" +
        "0 - Voltar";
        
        
        
        
        
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                null, mensagem));

        switch (opcao) {
            case 1:
                exibirTodosAtributos();
                break;
            case 2:
                exibirPokemonsOrdenados();
                break;
            case 3:
                exibirPokemonDesejado();
                break;
            case 4:
                sequencialFibonacci();
                break;
            case 0:
                break;
            default:
                JOptionPane.showMessageDialog(null, 
         "Opção inválida!");
        }
    }

    
    
    
    
    private static void exibirTodosAtributos() {
        int numPokemons = Integer.parseInt(JOptionPane.showInputDialog(
 "Quantos Pokémons deseja exibir?"));

        String mensagem = "Pokémons:\n";
        for (int i = 0; i < numPokemons; i++) {
            Pokemon pokemon = pokemons[i];
            mensagem += "Nome: " + pokemon.getNome() + "\n" +
                    "Força: " + pokemon.getForca() + "\n" +
                    "Ataque: " + pokemon.getAtaque() + "\n" +
                    "Defesa: " + pokemon.getDefesa() + "\n" +
                    "Agilidade: " + pokemon.getAgilidade() + "\n\n";
        }

        JOptionPane.showMessageDialog(null, mensagem);
    }

    
    
    
    
    private static void exibirPokemonsOrdenados() {
        ordenarPokemons();

        int numPokemons = Integer.parseInt(JOptionPane.showInputDialog(
 "Quantos Pokémons deseja exibir?"));

        String mensagem = "Pokémons:\n";
        for (int i = 0; i < numPokemons; i++) {
            Pokemon pokemon = pokemons[i];
            mensagem += "Nome: " + pokemon.getNome() + "\n" +
                    "Força: " + pokemon.getForca() + "\n" +
                    "Ataque: " + pokemon.getAtaque() + "\n" +
                    "Defesa: " + pokemon.getDefesa() + "\n" +
                    "Agilidade: " + pokemon.getAgilidade() + "\n\n";
        }

        JOptionPane.showMessageDialog(null, mensagem);
    }
    
    
    
    
    
    

    private static void exibirPokemonDesejado() {
        int numPokemon = Integer.parseInt(JOptionPane.showInputDialog(
 "Digite o número do Pokémon desejado:"));
        
        Pokemon pokemon = pokemons[numPokemon - 1];

        String mensagem = "Nome: " + pokemon.getNome() + "\n" +
                "Força: " + pokemon.getForca() + "\n" +
                "Ataque: " + pokemon.getAtaque() + "\n" +
                "Defesa: " + pokemon.getDefesa() + "\n" +
                "Agilidade: " + pokemon.getAgilidade();

        JOptionPane.showMessageDialog(null, mensagem);
    }

    
    
    
    
    
    private static void sequencialFibonacci() {
        int n = Integer.parseInt(JOptionPane.showInputDialog(
 "Digite o valor de n:"));
        
        int primeiroTermo = 0;
        int segundoTermo = 1;

        String sequencia = "Sequência de Fibonacci: " + n + ":\n";
        sequencia += primeiroTermo + "\n";
        sequencia += segundoTermo + "\n";
        
        

        for (int i = 3; i <= n; i++) {
            int proximoTermo = primeiroTermo + segundoTermo;
            sequencia += proximoTermo + "\n";
            primeiroTermo = segundoTermo;
            segundoTermo = proximoTermo;
        }

        JOptionPane.showMessageDialog(null, sequencia);
    }
}

        
        
        
        
        
        


