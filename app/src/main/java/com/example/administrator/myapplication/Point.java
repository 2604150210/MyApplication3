package com.example.administrator.myapplication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

class Tree{
    char op;
    double value;
    String s;
    Tree left, right;

    public Tree(char op, Tree left, Tree right) {
        this.op = op;
        this.left = left;
        this.right = right;
        this.value = op(left.value,op, right.value);
        if(op == '*' || op == '/'){
            if(left.op == '+' || left.op == '-'){
                left.s = String.format("(%s)",left.s);
            }
            if(right.op == '+' || right.op == '-'){
                right.s = String.format("(%s)",right.s);
            }
        }
        if (op == '/' && (right.op == '/' || right.op == '*')){
            right.s = String.format("(%s)",right.s);
        }
        if (op == '-' && (right.op == '-' || right.op == '+')){
            right.s = String.format("(%s)",right.s);
        }
        this.s = String .format("%s%c%s",left.s, op, right.s);
    }
    public Tree(double value){
        this.op = '\0';
        this.value = value;
        this.s = String.format("%.0f",value);
        this.left = null;
        this.right = null;
    }
    public Tree(char op, double left, double right) {
        this.op = op;
        this.left = new Tree(left);
        this.right = new Tree(right);
        this.value = op(left, op, right);
        this.s = String.format("%.0f%c%.0f",left, op, right);
    }

    private double op(double left, char op, double right) {
        if(op == '+'){
            return left + right;
        }
        else if(op == '-'){
            return left - right;
        }
        else if(op == '*'){
            return left * right;
        }else{
            if (right==0)return Double.MAX_VALUE;
            return left / right;
        }
    }

    public Tree() {
    }
}

public class Point {
    double []arr, num = new double[4];
    char[]out = new char[3];
    int []book = new int[4];
    char[] ops = "+-*/".toCharArray();
    HashSet<String> results = new HashSet<>();

    public Point(double a, double b, double c, double d) {
        arr = new double[]{a, b, c, d};
        dfs2(0);
    }

    private void dfs2(int step) {
        if(step >= 4){//四个数字的全排列
            for(int i = 1; i <= 3; i++){
                dfs(i,0);
            }
            return;
        }
        for (int i = 0; i < 4; i++){
            if(book[i] == 0){
                book[i] = 1;
                num[step] = arr[i];
                dfs2(step+1);
                book[i] = 0;
            }
        }
    }

    private void dfs(int type, int step) {
        if(step >= 3){
            double a = num[0], b = num[1], c = num[2], d = num[3];
            Tree root;
            if(type == 1){
                root = new Tree(out[2], new Tree(out[0], a, b), new Tree(out[1], c, d));
            }else if(type == 2){
                root = new Tree(out[2], new Tree(a), new Tree(out[1], new Tree(out[0],b, c),new Tree(d)));
            }else if(type == 3){
                root  = new Tree(out[2], new Tree(a), new Tree(out[1], new Tree(b), new Tree(out[0], c, d)));
            }else if(type == 4){
                root = new Tree(out[2], new Tree(out[1], new Tree(out[0], a, b), new Tree(c)), new Tree(d));
            }else{
                root = new Tree(out[2], new Tree(out[1], new Tree(a), new Tree(out[0], b, c)), new Tree(d));
            }
            getLastFix(root);
            return;
        }
        for(int i = 0; i < 4; i++){
            out[step] = ops[i];
            dfs(type, step+1);
        }
    }
    private void getLastFix(Tree root){
        if(Math.abs(root.value - VALUE) < 1e-5){
            results.add(String.format("%.0f=%s",VALUE,root.s));
        }
    }

    final double VALUE = 24;
//    public String getResult(){
//        if (results.size() <= 0){
//            return "无解";
//        }else{
//            int random = ((int)(Math.random()*results.size()))%results.size();
//            return results.get(random);
//        }
//    }

    public String[] getResults(){
        if (results.size() <= 0){
            return new String []{"无解"};
        }else{
            int n = results.size();
            String[]ret = new String[n];
            int cnt = 0;
            for (String s:results
                 ) {
                ret[cnt++] = s;
            }
            return ret;
        }
    }

}
