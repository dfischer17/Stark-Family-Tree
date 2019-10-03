
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dfischer17
 */
public class Main {
    public static void main(String[] args) {
        /* 
        Aufgrund eines Bugs bei den Unit-Tests (statische Map blieb null obwohl sie initalisiert wurde), 
        sah ich mich gezwungen, die Testfälle in einer anderen Form innerhalb der main-Methode zu implementieren.       
        
        Arbeitsteilung:
        Die Grundsidee eines Stammbaumes wurde von uns allen innerhalb der ersten Uebungseinheit
        überlegt.
        Danach hat Daniel Fischer die Idee finalisiert, Tests erstellt und in Codeform uebertragen.
        */
        
        System.out.println("isParentTest");
        Main.isParentTest();
        
        System.out.println("isParentTest2");
        Main.isParentTest2();
        
        System.out.println("isFemaleTest");
        Main.isFemaleTest();
        
        System.out.println("isFemaleTest2");
        Main.isFemaleTest2();
        
        System.out.println("isGrandparentTest");
        Main.isGranparentTest();
        
        System.out.println("isGrandparentTest2");
        Main.isGranparentTest2();
        
        System.out.println("getAllGrandparentsTest");
        Main.getAllGrandparentsTest();
        
        System.out.println("getAllGrandchildrenTest");
        Main.getAllGrandchildrenTest();
        
        System.out.println("getAllSiblingsTest");
        Main.getAllSiblingsTest();        
        
        System.out.println("getAllGrandmasTest");
        Main.getAllGrandmasTest();
    }
    
    public static void isParentTest() {
        T.setup();
        if (T.isParent(T.rickard, T.eddard)){
            System.out.println("Richtig");
        }
        else {
            System.out.println("Falsch");
        }
    }
    
    public static void isParentTest2() {
        //T.setup();
        if (!T.isParent(T.rickard, T.bran)){
            System.out.println("Richtig");
        }
        else {
            System.out.println("Falsch");
        }
    }
    
    public static void isFemaleTest() {
        if (T.isFemale(T.arya)) {
            System.out.println("Richtig");
        }
        else {
            System.out.println("Falsch");
        }
    }
    
    public static void isFemaleTest2() {
        if (!T.isFemale(T.brandon)) {
            System.out.println("Richtig");
        }
        else {
            System.out.println("Falsch");
        }
    }
    
    public static void isGranparentTest() {
        if (T.isGrandparent(T.lyarra, T.sansa)) {
            System.out.println("Richitg");
        }
        else {
            System.out.println("Falsch");
        }
    }
    
    public static void isGranparentTest2() {
        if (!T.isGrandparent(T.lyarra, T.lyanna)) {
            System.out.println("Richitg");
        }
        else {
            System.out.println("Falsch");
        }
    }
    
    public static void getAllGrandparentsTest() {
        List<T> temp = T.getAllGrandparents(T.bran);
        if (temp.contains(T.rickard) && temp.contains(T.lyarra)) {
            System.out.println("Richtig");
        }
        else {
            System.out.println("Falsch");
        }
    }
    
    public static void getAllGrandchildrenTest() {
        List<T> temp = T.getAllGrandchildren(T.rickard);
        if (temp.contains(T.robb) && temp.contains(T.sansa) && temp.contains(T.arya) && temp.contains(T.bran) && temp.contains(T.rickon)) {
            System.out.println("Richtig");
        }
        else {
            System.out.println("Falsch");
        }
    }
    
    public static void getAllSiblingsTest() {
        Map<T, List<T>> temp = T.getAllSiblings(T.benjen);
        Map<T, List<T>> exp = new HashMap<>();
        
        exp.put(T.benjen, Arrays.asList(T.eddard, T.brandon, T.lyanna)); 
        
        if (temp.keySet().contains(T.benjen) && temp.values().contains(Arrays.asList(T.eddard, T.brandon, T.lyanna))) {
            System.out.println("Richtig");
        }
        else {
            System.out.println("Falsch");
        }
    }
    
    public static void getAllGrandmasTest() {
        List<T> temp = T.getAllGrandmas(T.arya);
        if (temp.containsAll(Arrays.asList(T.lyarra))) {
            System.out.println("Richtig");
        }
        else {
            System.out.println("Falsch");
        }
    }    
}
