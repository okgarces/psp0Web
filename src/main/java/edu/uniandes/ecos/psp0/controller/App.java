package edu.uniandes.ecos.psp0.controller;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;
import java.net.URI;
import java.net.URISyntaxException;

import edu.uniandes.ecos.psp0.model.FileInput;
import edu.uniandes.ecos.psp0.model.StatisticCalculator;
import edu.uniandes.ecos.psp0.view.MainView;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main Application
 */
public class App extends HttpServlet{

    public static void main(String[] args) {
        Server server = new Server(Integer.getInteger(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new App()), "/*");
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        MainView.showHome(req,resp);
        MainView.showResults(req,resp,0.0," ",0.0);       
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                
        try {
            MainView.showHome(req,resp);
            consoleInput(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   
    /**
     * Method to set the console input for the numbers
     */
    public void consoleInput(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //System.out.println("Por favor, ingrese la cantidad de numeros que desea ingresar:");
        //Scanner input = new Scanner(System.in);
        String calc = req.getParameter("calc");

        String[] strElements = calc.split(" ");
        Double nextElement = 0D;
        LinkedList<Double> numbersList = new LinkedList<Double>();
        StatisticCalculator calculator = new StatisticCalculator();

        for (String strElement : strElements) {
            try {
                nextElement = Double.valueOf(strElement);
                numbersList.add(nextElement);
                
            }catch(NumberFormatException ex){
                MainView.error(req, resp);
            }
        }

        calculator.setInputData(numbersList);
        
        MainView.showResults(req, resp, calculator.calculateStdDev(), numbersList.toString(), calculator.calculateMean());
    }

    /**
     * Method to set the file input for the numbers
     */
    public void fileInput() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        FileInput inputReader = new FileInput();
        inputReader.getFile(classLoader.getResource("inputs.txt").getFile());
        inputReader.readNumbersFromFile();
        LinkedList<Double> values = inputReader.getValuesFromFile();

        StatisticCalculator calculator = new StatisticCalculator();
        calculator.setInputData(values);
        
        System.out.println("Leyendo desde el archivo inputs.txt");
        System.out.println("Lista: " + values.toString());
        System.out.println("Media: " + calculator.calculateMean());
        System.out.println("Desviacion Estandar: " + calculator.calculateStdDev());

    }

}
