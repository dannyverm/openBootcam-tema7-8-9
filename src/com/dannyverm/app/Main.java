package com.dannyverm.app;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static String reverse(String texto) {
        String reverso = "";
        for (int i = texto.length(); i > 0; i--) {
            reverso += texto.toCharArray()[i-1];
        }
        return reverso;
    }

    public static byte[] concatArray(byte[] array1, byte[] array2) {
        byte[] concat = new byte[array1.length + array2.length];
        int c = 0;
        for (byte n : array1) {
            concat[c] = n;
            c++;
        }
        for (byte n : array2) {
            concat[c] = n;
            c++;
        }

        return concat;
    }

    public static void guardar() {
        boolean ban = false;

        String edad = "";
        String nombre = "";
        Scanner scanner;
        while (!ban) {
            scanner = new Scanner(System.in);
            System.out.print("Ingrese su edad: ");
            edad = scanner.next();
            try {
                int num = Integer.parseInt(edad);
                if (num > 17) {
                    scanner = new Scanner(System.in);
                    System.out.print("Ingrese su Nombre: ");
                    nombre = scanner.next();
                    ban = true;
                } else System.out.println("La edad no puede ser menor a 18");
            } catch (NumberFormatException e) {
                System.out.println("Solo puede ingresar numeros");
            }
        }

        try {
            String registro = "Nombre: " + nombre + " ---> Edad: " + edad + "\n";
            InputStream original = new FileInputStream("db.txt");
            byte[] datos = original.readAllBytes();
            datos = concatArray(datos, registro.getBytes(StandardCharsets.UTF_8));
            PrintStream copia = new PrintStream("db.txt");
            copia.write(datos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buscar() {
        InputStream original = null;
        try {
            original = new FileInputStream("db.txt");
            byte[] datos = original.readAllBytes();
            for (byte d : datos) {
                System.out.print((char) d);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void DividePorCero(Integer numero) {
        int num = numero / 0;
    }

    public static void copiarFichero(String fileIn, String fileOut) {
        try {
            InputStream original = new FileInputStream(fileIn);
            byte[] datos = original.readAllBytes();
            PrintStream copia = new PrintStream(fileOut);
            copia.write(datos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //Imprimiendo reverse
        System.out.println(reverse("hola como estas"));

        //1- Crea un array unidimensional de Strings y recórrelo, mostrando únicamente sus valores.
        String[] cadenaChar = {"Este", "es", "un", "array", "unidimensional"};
        for (String cadena :
                cadenaChar) {
            System.out.print(cadena + " ");
        }

        System.out.println();
        //2- Crea un array bidimensional de enteros y recórrelo, mostrando la posición
        // y el valor de cada elemento en ambas dimensiones.
        System.out.println("Lectura de array bidimensional ");
        int[][] arrayBi = {{3, 5, 6, 4}, {9, 8, 7, 2}};
        for (int i = 0; i < arrayBi.length; i++) {
            System.out.println("Fila: " + i);
            for (int j = 0; j < arrayBi[i].length; j++) {
                System.out.print("Col. " + j + "= " + arrayBi[i][j] + " ");
            }
            System.out.println();
        }

        //3- Crea un "Vector" del tipo de dato que prefieras, y añádele 5 elementos.
        // Elimina el 2o y 3er elemento y muestra el resultado final.
        Vector<String> vector = new Vector(5, 3);
        vector.add("primero");
        vector.add("segundo");
        vector.add("tercero");
        vector.add("cuarto");
        vector.add("quinto");
        System.out.println("Vector inicial: " + vector.toString());
        vector.remove(1);
        vector.remove(1);
        System.out.println("Vector final: " + vector.toString());

        //4- Indica cuál es el problema de utilizar un Vector con la capacidad por
        // defecto si tuviésemos 1000 elementos para ser añadidos al mismo.
        //Respuesta: el problema sería que la dimensión del mismo se duplicaria
        //terminando en un vector final con una dimensón muy amplia

        //5- Crea un ArrayList de tipo String, con 4 elementos. Cópialo en una LinkedList.
        // Recorre ambos mostrando únicamente el valor de cada elemento.
        ArrayList<String> array1 = new ArrayList<>(Arrays.asList("Uno", "Dos", "Tres", "Cuatro"));
        LinkedList<String> array2 = new LinkedList<>(array1);
        System.out.println("Recorriendo ArrayList");
        for (String a1 :
                array1) {
            System.out.print(a1 + " ");
        }
        System.out.println();
        System.out.println("Recorriendo LinkedList");
        for (String a2 :
                array2) {
            System.out.print(a2 + " ");
        }
        System.out.println();
        //6- Crea un ArrayList de tipo int, y utilizando un bucle rellénalo con elementos 1..10. A continuación,
        // con otro bucle, recórrelo y elimina los numeros pares. Por último, vuelve a recorrerlo y muestra
        // el ArrayList final. Si te atreves, puedes hacerlo en menos pasos, siempre y cuando cumplas el
        // primer "for" de relleno.
        System.out.println("Recorriendo numeros impares");
        ArrayList<Integer> arrayEnteros = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            arrayEnteros.add(i);
        }
        arrayEnteros.removeIf(i -> i % 2 == 0);
        arrayEnteros.forEach(System.out::println);
        //7- Crea una función DividePorCero. Esta, debe generar una excepción ("throws") a su llamante
        // del tipo ArithmeticException que será capturada por su llamante (desde "main", por ejemplo).
        // Si se dispara la excepción, mostraremos el mensaje "Esto no puede hacerse". Finalmente,
        // mostraremos en cualquier caso: "Demo de código".
        try {
            DividePorCero(4);
        } catch (ArithmeticException e) {
            System.out.println("Esto no puede hacerse");
        } finally {
            System.out.println("Demo de código");
        }
        System.out.println("Copiando fichero...");
        //8- Utilizando InputStream y PrintStream, crea una función que reciba dos parámetros: "fileIn" y "fileOut".
        // La tarea de la función será realizar la copia del fichero dado en el parámetro "fileIn" al fichero
        // dado en "fileOut".
        copiarFichero("./nombre.txt", "./copia_nombre.txt");


        //9- Sorpréndenos creando un programa de tu elección que utilice InputStream, PrintStream, excepciones, un
        // HashMap y un ArrayList, LinkedList o array.
        System.out.println("Sistema de guardado y busqueda de Registros");
        boolean ban = false;

        String opcion = "";
        Scanner scanner;
        while (!ban) {
            scanner = new Scanner(System.in);
            System.out.println("Para ver presione 1 <--> Para Guardar Presione 2 <--> Salir precione 3");
            System.out.print("Opcion: ");
            opcion = scanner.next();
            try {
                int num = Integer.parseInt(opcion);
                if (num == 1) ban = true;
                else if (num == 2) ban = true;
                else if (num == 3) System.exit(0);
                else System.out.println("El codigo ingresado es incorrecto");
            } catch (NumberFormatException e) {
                System.out.println("Solo puede ingresar numeros");
            }
        }

        if (opcion.equals("1")) {
            buscar();
        } else {
            guardar();
        }

    }
}

