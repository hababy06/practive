package com.example.practice;

public class ccc {
    ccc
    cccccccccccccc
}


// ❌ 把所有操作都塞在 Book 類別裡
class Book {
    private double price;
    // 計價
    public double calculatePrice() { return price; }
    // 折扣
    public double applyDiscount() { return price * 0.9; }
    // 列印明細
    public void printDetails() {
        System.out.println("Book: " + price);
    }
    // 計算運費
    public double calculateShipping() {
        return price > 500 ? 0 : 50;
    }
    // 要加新操作就要改這個類別 😱
}
// 痛點：Book 變很肥、職責不清楚

// 訪問者介面
interface Visitor {
    void visit(Book book);   // 處理書本
    void visit(Fruit fruit); // 處理水果
}
// 商品介面
interface Item {
    void accept(Visitor visitor); // 接受訪問者
}
// 具體商品
class Book implements Item {
    private double price;
    public void accept(Visitor v) { v.visit(this); } // 雙重分派
    public double getPrice() { return price; }
}
class Fruit implements Item {
    private double pricePerKg, weight;
    public void accept(Visitor v) { v.visit(this); }
    public double getPricePerKg() { return pricePerKg; }
    public double getWeight() { return weight; }
}
// 具體訪問者：計算總價
class PriceCalculator implements Visitor {
    private double total = 0;
    public void visit(Book b) { total += b.getPrice(); }         // 固定價
    public void visit(Fruit f) { total += f.getPricePerKg() * f.getWeight(); } // 重量價
}
// 使用：訪問者去處理每個商品
for (Item item : cart) {
        item.accept(priceCalc); // Book 自動呼叫 visit(Book)、Fruit 自動呼叫 visit(Fruit)
}

