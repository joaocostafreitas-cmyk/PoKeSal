package model;

public class PokeSal {

    private final String pokeSal;
    private int hp;
    private int atk;
    private int def;
    private int spd;
    private TipoElemental tipoElemental;
    private Acessorio acessorio;
    private StatusEfeito status = StatusEfeito.NENHUM;

    public PokeSal(String pokemon, int hp, int atk, int def, int spd, TipoElemental tipoElemental) {
        this.pokeSal = pokemon;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.tipoElemental = tipoElemental;
    }

    public String getPokeSal() { return pokeSal; }
    public int getHP() { return hp; }
    public void setATK(int atk) {this.atk = atk; }
    public void setHP(int hp) { this.hp = hp; }
    public void setSPD(int spd) {this.spd = spd;}
    public void setDefesa(int def) {this.def = def;}
    public int getATK() { return atk; }
    public int getDEF() { return def; }
    public int getSPD() { return spd; }
    public TipoElemental getTipoElemental() { return tipoElemental; }

    public void setAcessorio(Acessorio acessorio){
        this.acessorio = acessorio;
    }

    public Acessorio getAcessorio(Acessorio acessorioEscolhido){
        return acessorio;
    }

    public StatusEfeito getStatus() {
        return status;
    }

    public void setStatus(StatusEfeito status) {
        this.status = status;
    }

    public int getATKComStatus() {
        int atkBase = (status == StatusEfeito.QUEIMADO) ? (int)(atk * 0.5) : atk;
        if (acessorio != null && acessorio.getAtributo() == AtributoBonus.ATAQUE) {
            atkBase += acessorio.getValorBonus();
        }
        return atkBase;
    }

    public int getSPDComStatus() {
        int spdBase = (status == StatusEfeito.PARALISADO) ? spd / 2 : spd;
        if (acessorio != null && acessorio.getAtributo() == AtributoBonus.VELOCIDADE) {
            spdBase += acessorio.getValorBonus();
        }
        return spdBase;
    }
    public int getDEFComAcessorio() {
        if (acessorio != null && acessorio.getAtributo() == AtributoBonus.DEFESA) {
            return def + acessorio.getValorBonus();
        }
        return def;
    }
    @Override
    public String toString() {
       return pokeSal + " [HP=" + hp + ", ATK=" + atk + ", DEF=" + def + ", SPD=" + spd + ", Tipo=" + tipoElemental + "]";

    }

    }