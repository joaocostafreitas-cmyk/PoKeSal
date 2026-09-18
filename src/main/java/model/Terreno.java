package model;

public enum Terreno {
    ASFALTO_QUENTE, // Fogo +15% de dano
    POCA_CHUVA,     // Agua +10% de dano
    CANTEIRO_CENTRAL, // Planta recupera 5% de HP por turno
    NENHUM;

    public double bonusDano(TipoElemental tipo) {
        if (this == ASFALTO_QUENTE && tipo == TipoElemental.FOGO) return 1.15;
        if (this == POCA_CHUVA && tipo == TipoElemental.AGUA) return 1.10;
        return 1.0;
    }
}