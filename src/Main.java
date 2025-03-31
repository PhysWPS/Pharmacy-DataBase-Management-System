import controller.ItemController;
import model.Item;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema de Fármacia");
        System.out.println("***********************");

        ItemController controller = new ItemController();
        ArrayList<Item> itens = controller.read(); //sempre que inicializa, irá ler e armazenar as infos no itens.

        controller.modify(3, itens);


    }
}