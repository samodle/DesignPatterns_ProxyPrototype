package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("its working!!!");
    }
}


//Adapter Implementation

interface Socket {
    String getOutput();
}
class Plug {
    private String specification = "5 AMP";
    public String getInput() { return specification; }
}
class Adapter implements Socket {
    public String getOutput(int input) {
// could use “input” to determine “right” interface/class
        Plug plug = new Plug();
        return plug.getInput(); }

    @Override
    public String getOutput() {
        return null;
    }
}

//Prototype Implementation
abstract class CartPrototype implements Cloneable {
    public abstract CartPrototype clone();
}

class ShoppingCart extends CartPrototype implements Cloneable {
    public CartPrototype clone() { return new ShoppingCart(); }
}

//class Client {
   // public static void main(String[] args) {
// could use input “type” to determine “right” concrete prototype
   ///     Prototype myPrototype = new ConcretePrototype1();
   //     Prototype result = myPrototype.clone(); }
//}
