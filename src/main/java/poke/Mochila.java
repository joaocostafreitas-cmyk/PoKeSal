package poke;

import model.Item;
import model.PokeSal;

public class Mochila {

    private static final int  Limite_itens_por_turno = 2;

    private int itensUsados = 0;

    public boolean usarItem(Item item, PokeSal pokeSal, int hpMaximo ){
         if (itensUsados >= Limite_itens_por_turno){
             System.out.println("Você já usou o limite máximo de itens nesse turno");
             return  false;
         }
         item.usar(pokeSal, hpMaximo);
         itensUsados++;
         System.out.println(pokeSal.getPokeSal() + "usou" + item.getnome() + "!!");
         return true;
    }

    public void resetarParaNovoTurno(){
        itensUsados = 0;
    }


}
