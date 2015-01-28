package edu.uniandes.ecos.psp0;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Calculo de Media y Desviacion Estandar");
        System.out.println("Por favor, ingrese la cantidad de numeros que desea ingresar:");
        
        Scanner input = new Scanner(System.in);
        String strElements = input.nextLine();
        Integer elements = 0;
        Double nextElement = 0D;
        LinkedList<Double> numbersList = new LinkedList<Double>();
        StatisticCalculator calculator = new StatisticCalculator();
        
        try{
        	elements = Integer.parseInt(strElements);
        } catch (NumberFormatException ex){
        	System.out.println("Entrada invalida. Cerrando...");
        	System.exit(0);
        }
        for(int i = 0; i < elements; i++){
        	System.out.println("Ingrese Numero " + i + ": ");
        	try{
        		nextElement = Double.valueOf(input.nextLine());
        		numbersList.add(nextElement);
        		System.out.println();
        	} 
        	catch (Exception ex){
        		System.out.println("Entrada invalida. No agregada a la lista.");
        	}
        }
        
        calculator.setInputData(numbersList);
        System.out.println("Numeros ingresados efectivamente: " + numbersList.size());
        System.out.println("Lista: " + numbersList.toString());
        System.out.println("Media: " + calculator.calculateMean());
        System.out.println("Desviacion Estandar: " + calculator.calculateStdDev());
        input.close();
    }
}
