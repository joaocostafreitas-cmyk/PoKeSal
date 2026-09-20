package model;

public class PokeSal {

    private final String Pokesal;
    private String pokeSal;
    private int hp;
    private int atk;
    private int def;
    private int spd;
    private TipoElemental tipoElemental;
    private Acessorio acessorio;

    public PokeSal(String pokemon, int hp, int atk, int def, int spd, TipoElemental tipoElemental) {
        this.Pokesal = pokemon;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.tipoElemental = tipoElemental;
    }

    public String getPokeSal() { return Pokesal; }
    public int getHP() { return hp; }
    public void setHP(int hp) { this.hp = hp; }
    public int getATK() { return atk; }
    public int getDEF() { return def; }
    public int getSPD() { return spd; }
    public TipoElemental getTipoElemental() { return tipoElemental; }
    public void SetAcessorio(Acessorio acessorio){
        this.acessorio = acessorio;
    }
    public Acessorio getAcessorio(Acessorio acessorioEscolhido){
        return acessorio;
    }

    @Override
    public String toString() {
        return Pokesal + " [HP=" + hp + ", ATK=" + atk + ", Tipo=" + tipoElemental + "]";
    }
}