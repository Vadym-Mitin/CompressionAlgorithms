package com.myCompany.HuffmanCompression;

import java.util.Map;

public class Node {
    private Node leftNode;
    private Node rightNode;
    private int freq;
    private String code;
    private String chars;
    private Character symbol;

    private Node(Node left, int freq, Node right) {
        this.leftNode = left;
        this.rightNode = right;
        this.freq = freq;
    }

    private Node(Node left, int freq, Node right, Character symbol) {
        this.leftNode = left;
        this.rightNode = right;
        this.freq = freq;
        this.chars = symbol.toString();
        this.symbol = symbol;

    }

    public Character getSymbol() {
        return symbol;
    }

    public void setChars(String s) {
        this.chars = s;
    }

    public String getChars() {
        return chars;
    }

    public static Node createLeaf(Map.Entry<Character, Integer> symbol) {
        return new Node(null, symbol.getValue(), null, symbol.getKey());
    }

    public static Node createNode(Node left, int freq, Node right) {
        return new Node(left, freq, right);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code.equals("1") || code.equals("0")) {
            this.code = code;
        } else throw new IllegalStateException("this wrong code type 0 or 1");
    }

    public void setLeafCode(String code) {
        if (isLeaf()) {
            this.code = code;
        }
    }

    public boolean isLeaf() {
        return leftNode == null && rightNode == null ;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public Integer getFreq() {
        return freq;
    }
}
