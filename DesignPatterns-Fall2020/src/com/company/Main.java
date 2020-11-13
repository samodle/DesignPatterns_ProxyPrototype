package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {

        ShoppingCart CartA = new ShoppingCart();
        ShoppingCart CartB = (ShoppingCart) CartA.clone();
        ShoppingCart CartC = (ShoppingCart) CartB.clone();

        Tomato tomatoA = new Tomato("Bob the Tomato");
        Tomato tomatoB = new Tomato("Steve the Tomato");
        Tomato tomatoC = new Tomato("Larry the Tomato");

        System.out.println("Basic Vegetable Functions:");
        tomatoB.Decompose();
        tomatoB.Ripen();
        System.out.println("");

        System.out.println("Basic Ice Cream Functions:");
        VanillaBean vbCream = new VanillaBean("Vanilla Bean Ice Cream");
        vbCream.Freeze();
        vbCream.Melt();
        System.out.println("");

        //remember, the cart can only take vegetables so we use the adapter
        CartC.addItem(tomatoA);
        CartC.addItem(new IceCreamAdapter(vbCream));
        CartC.addItem(tomatoC);
        System.out.println("");

        System.out.println("Let's print them all as vegetables (or vegetable adapters):");
        CartC.PrintCart();
    }
}

//Adapter Implementation
interface IceCream {
    AtomicReference<String> Flavor = new AtomicReference<String>();
    String getFlavor();
    void Freeze();
    void Melt();
}

interface Vegetable{
    AtomicReference<String> Name = new AtomicReference<String>();
    String getName();
    void Ripen();
    void Decompose();
}

class Tomato implements Vegetable{

    @Override
    public String getName() {
        return Name.get();
    }

    @Override
    public void Ripen() {
        System.out.println(Name + " is 10% riper!");
    }

    @Override
    public void Decompose() {
        System.out.println(Name + " is decomposing");
    }

    public void MakeSalsa(){
        System.out.println("I'm salsa!");
    }

    public Tomato(String n){
        Name.set(n);
    }
}

class VanillaBean implements IceCream{

    @Override
    public String getFlavor() {
        return Flavor.get();
    }

    @Override
    public void Freeze() {
        System.out.println(Flavor + " is becoming frozen");
    }

    @Override
    public void Melt() {
        System.out.println(Flavor + " is 10% soupier");
    }

    public void Scoop(){}

    public VanillaBean(String f){
        Flavor.set(f);
    }
}

class IceCreamAdapter implements Vegetable{
    IceCream iceCream;

    @Override
    public String getName() {
        return iceCream.getFlavor();
    }

    @Override
    public void Ripen() {
        iceCream.Freeze(); //yes, i'm aware freezing != ripening
    }

    @Override
    public void Decompose() {
        iceCream.Melt(); //this one is closer
    }

    public IceCreamAdapter(IceCream i){
        iceCream = i;
    }
}

//Prototype Implementation
abstract class CartPrototype implements Cloneable {
    static int counter = 1;
    int cartID;
    List<Vegetable> Veggies = new ArrayList<Vegetable>();

    public abstract CartPrototype clone();
}

class ShoppingCart extends CartPrototype implements Cloneable {
    public CartPrototype clone() { return new ShoppingCart(); }

    void addItem(Vegetable v){
        Veggies.add(v);
    }

    public ShoppingCart(){
        counter++;
        cartID = counter;
    }

    void PrintCart(){
        for(Vegetable v : Veggies){
            System.out.println(v.getName());
            v.Ripen();
        }
    }
}

