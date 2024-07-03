package org.seismic.arcade;

public class Example {
    // nächste Zeile egal
    public static void main(String[] args) {
        // diese Zeilen werden ausgeführt
        // Objekt der Klasse Example wird erstellt und erhält den Namen "example"
        Example example = new Example();
        example.greet("Simon");
    }

    public void greet(String name) {
        System.out.println("Hello " + name + "!");
    }
}
