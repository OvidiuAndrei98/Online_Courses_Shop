package com.codecool.shop.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

public class LineItem implements Serializable {

        private   int orderId;
        private   int lineNumber;
        private  int quantity;
        private  int itemId;
        private  Currency currency;
        private  float unitPrice;
        private  Product product;
        private  float total;

        public LineItem() {
        }

        public LineItem(int lineNumber, Product cartItem) {
            this.lineNumber = lineNumber;
            this.quantity = 1;
            this.itemId = cartItem.getId();
            this.unitPrice = cartItem.getDefaultPrice();
            this.product = cartItem;
        }

        public  int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public float getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(float unitprice) {
            this.unitPrice = unitprice;
        }

        public float getTotal() {
            return total;
        }

        public Product getItem() {
            return product;
        }

        public void setItem(Product product) {
            this.product = product;
            calculateTotal();
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
            calculateTotal();
        }

        private void calculateTotal() {
            if (product != null) {
                total = product.getDefaultPrice() * quantity;
            } else {
                total = Float.parseFloat(null);
            }
        }

        public Product getProduct() {
            return product;
        }

    }

