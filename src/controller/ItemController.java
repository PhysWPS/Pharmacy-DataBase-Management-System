package controller;

import model.Item;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    //metodos que serão aplicados: Adicionar, excluir, modificar, pesquisar
    private Item item;

    public ItemController() {

    }
    // só aceitar itens da classe Item
    public void add(Item item) {
        try {
            // arquivo: localizar -> abrir -> preparar -> escrita -> fechar
            OutputStream os = new FileOutputStream("DataBase.txt", true); // localizar arquivo
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

    public void remove(int code) {

    }

    public void modify(int code) {

    }

    public void search(int code) {

    }


    public ArrayList<Item> read(){
        try(BufferedReader reader= new BufferedReader(new FileReader("DataBase.txt"))){

            String linha = reader.readLine();
            ArrayList<String> linhas = new ArrayList<>();

            while (linha != null){
                System.out.println(linha);
                linhas.add(linha); // adiciona ao tal arquivo temporario. Um arraylisit.
                linha = reader.readLine();
            }
            System.out.println("DataBase Archive was read sucessfully");
            /*
            Converter linhas em objetos.
             */
            ArrayList<Item> itens = new ArrayList<>();
            Item item;
            String[] elementos;




            for (int i = 0; i < linhas.size(); i++) {

                elementos = linhas.get(i).split(",");
                int quantidade = Integer.parseInt(elementos[1]);
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
