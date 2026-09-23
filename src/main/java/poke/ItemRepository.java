package poke;

import java.util.ArrayList;
import java.util.List;
import model.Item;

public class ItemRepository {
 
  public static List<Item> itens = new ArrayList<>();

  static {
    itens.add(new Item("Potion", 20));
    itens.add(new Item("Super Potion", 60));
    itens.add(new Item("Antídodo", 15));
  }

}
