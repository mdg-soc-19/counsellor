package com.example.counsellor;

public class PostItems {

    private String itemQuestion;
    private int itemImage;
    private int itemButtonVA;
    private int itemButtonA;

    public PostItems(){
        
    }

    public PostItems(String itemQuestion, int itemImage) {
        this.itemQuestion = itemQuestion;
        this.itemImage = itemImage;
        this.itemButtonVA = itemButtonVA;
        this.itemButtonA = itemButtonA;
    }

    public String getItemQuestion() {
        return itemQuestion;
    }

    public int getItemImage() {
        return itemImage;
    }

   /* public int getItemButtonVA() {
        return itemButtonVA;
    }

    public int getItemButtonA() {
        return itemButtonA;
    }*/
}
