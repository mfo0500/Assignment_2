/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author krist
 */
public class ListOfGroceries{
    
    private LinkedList<GroceryItems> Groceries;
    private String state;

    ListOfGroceries()
    {
        setGroceries(new LinkedList<GroceryItems>());
    }
    
    /**
     * @return the Groceries
     */
    public LinkedList<GroceryItems> getGroceries() {
        return this.Groceries;
    }

    /**
     * @param Groceries the Groceries to set
     */
    public void setGroceries(LinkedList<GroceryItems> Groceries) {
        this.Groceries = Groceries;
    }


    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

}