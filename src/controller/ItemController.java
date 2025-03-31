package controller;

import model.Item;

import java.io.*;
import java.util.ArrayList;


public class ItemController {
    //metodos que serão aplicados: Adicionar, excluir, modificar, pesquisar
    private Item item;

    public ItemController() {

    }
    // só aceitar itens da classe Item
    public void add(Item item, boolean opcao) {
        try {
            // arquivo: localizar -> abrir -> preparar -> escrita -> fechar
            OutputStream os = new FileOutputStream("DataBase.txt", opcao); // localizar arquivo
            OutputStreamWriter osw = new OutputStreamWriter(os); // prepara para escrita
            BufferedWriter bw = new BufferedWriter(osw); // escreve no arquivo preparado.

            String linha = item.getName()+','+item.getQuantity()+","+item.getType();

            bw.write(linha); // escreveu no arquivo bw
            bw.newLine(); // pula uma linha

            bw.close();
            osw.close();
            os.close();

            System.out.println("The medicine "+item.getName()+ " was added.");
        } catch (Exception e){
            System.out.println("DataBase archive not found.");
            System.out.println(e);
        }

    }

    public void remove(int code, ArrayList<Item> itens) {
        /*
        se eu quero remover apenas um item, posso só itens.remove(code), onde code é o index.
        agora posso reescrever tudo, ou seja, cadastrar novamente.
         */
        itens.remove(code);
        //reescrever o arquivo
        //true para append, false para overwrite
        for (int i = 0; i < itens.size(); i++) {
            if (i == 0) {
                add(itens.get(i), false);
            } else {
                add(itens.get(i), true);

            }
        }

    }

    public void modify(int code, ArrayList<Item> itens) {
        Item item = itens.get(code); // seleciona o que eu quero
        itens.remove(code);
        //colocar isso nos argumentos do método.
        item.setName("Ciprofloxacino");
        item.setQuantity(300);
        item.setType("Vidro pó");

        itens.add(code, item);

        //armazena aquilo que ta no objeto item, no arquivo txt
        for (int i = 0; i < itens.size(); i++) {
            if (i == 0) {
                add(itens.get(i), false);
            } else {
                add(itens.get(i), true);
            }
        }

    }

    public Item search(int code, ArrayList<Item> itens){
        try {
            Item item = itens.get(code);
            return item;
        } catch (Exception e){
            return null;
        }
    }


    public ArrayList<Item> read(){
        /*
        Esta função deve ser inicializada sempre que o app rodar.
        Nota: sempre trabalhar com um arquivo que não seja diretamente o database.
        neste caso, iremos trabalhar com um item ArrayList que contém tipos Item. ArrayList<Item>.
         */
        try(BufferedReader reader= new BufferedReader(new FileReader("DataBase.txt"))){

            String linha = reader.readLine();
            ArrayList<String> linhas = new ArrayList<>();

            while (linha != null){
                System.out.println(linha);
                linhas.add(linha); // adiciona ao tal arquivo temporario. Um arraylist.
                linha = reader.readLine();
            }
            System.out.println("DataBase Archive was read sucessfully");
            /*
            Converter linhas em objetos.
             */
            ArrayList<Item> itens = new ArrayList<>(); // Arraylist de Item, cujo nome é itens.
            Item item;
            String[] elementos;

            for (int i = 0; i < linhas.size(); i++) {

                elementos = linhas.get(i).split(",");
                double quantidade = Double.parseDouble(elementos[1]);
                item = new Item(elementos[0], quantidade, elementos[2]);
                itens.add(item);
            }

            System.out.println("Linhas convertidas para objetos com sucesso");
            return itens;

        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo: " +e.getMessage());
            return null;
        }
    }



}
