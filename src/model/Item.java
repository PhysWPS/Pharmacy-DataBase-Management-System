package model;

public class Item {
    // nome, quantidade, codigo e tipo.
    private int code;
    private String name;
    private double quantity;
    private String type;

    // para inicializar o item no código
    public Item(String name, double quantity, String type){
        this.name = name;
        this.quantity = quantity;
        this.type = type;

    }




//==============================


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
