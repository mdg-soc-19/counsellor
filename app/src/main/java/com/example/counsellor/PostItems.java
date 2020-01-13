package com.example.counsellor;

public class PostItems {

    // public String category;
    public String itemQuestion;
    public int itemImage;

    public PostItems() {

    }

    public PostItems(String itemQuestion, int itemImage) {
        this.itemQuestion = itemQuestion;
        this.itemImage = itemImage;
    }


    public String getItemQuestion() {
        return itemQuestion;
    }

    public int getItemImage() {
        return itemImage;
    }

    public static class ChildLevel2 {
        public String answer;

        public ChildLevel2() {
        }

        public ChildLevel2(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }




}