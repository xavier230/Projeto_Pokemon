package br.unipar.trabalhosub;

/**
 *
 * @author renat
 */
public class Pokemon {

   
    public String nome;
    public int forca;
    public int ataque;
    public int defesa;
    public int agilidade;

    public Pokemon() {
    }

    public Pokemon( String nome, int forca, int ataque, int defesa, int agilidade) {
       
        this.nome = nome;
        this.forca = forca;
        this.ataque = ataque;
        this.defesa = defesa;
        this.agilidade = agilidade;
    }

    

   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    @Override
    public String toString() {
        return "Pokemon{" + "nome=" + nome + ", forca=" + forca + ", ataque=" + ataque + ", defesa=" + defesa + ", agilidade=" + agilidade + '}';
    }


   
    
 
}
