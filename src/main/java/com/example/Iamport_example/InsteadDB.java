package com.example.Iamport_example;

public class InsteadDB {

    public static class item {
        public String name;
        public int price;

        public item() {
            this.name = "문성훈의 최신 로보트";
            this.price = 10000;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }
    }
}
