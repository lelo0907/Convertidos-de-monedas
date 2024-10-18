import com.google.gson.JsonObject;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Principal {
    public static void main(String[] args) {
        String monedaorigen = "";
        String monedadestino = "";
        ///conjunto de opciones validas
        Set<String> monedasValidas = new HashSet<>();
        monedasValidas.add("USD");
        monedasValidas.add("COP");
        monedasValidas.add("ARS");
        monedasValidas.add("CLP");

        Scanner lectura = new Scanner(System.in);
        BusquedaMoneda consulta = new BusquedaMoneda();
        System.out.println("\nHola Usuario");
        System.out.println("\nEscriba el código de moneda o código ISO 4217 de origen\n");


        String opciones = """
                Estas son las opciones:
                USD
                COP
                ARS
                CLP
                """;




        while (true) {
            System.out.println(opciones);
            monedaorigen = lectura.nextLine().toUpperCase();;

            if (monedasValidas.contains(monedaorigen)){
                try {
                    System.out.println("\nEscriba el código de moneda destino\n");
                    System.out.println(opciones);
                    monedadestino = lectura.nextLine().toUpperCase();;

                    System.out.println("\nEscriba el monto o valor origen que desea cambiar(El decimal es con coma ',') \n");
                    Double valorTransformar = lectura.nextDouble();

            Currency currency = consulta.buscarMoneda(monedaorigen);
            ///extrael la moneda destino del json
                    Map<String, Double> conversion = currency.conversion_rates();
                    Double tasaConversion = conversion.get(monedadestino);
                    System.out.println("La tasa de conversion del dia de "+monedaorigen+" a "+monedadestino+" es " +String.format("%.2f", tasaConversion));
              /// Aca se hace la multiplicacion
                    Double valorFinal = tasaConversion*valorTransformar;
                    System.out.println("El valor en "+monedadestino+" es "+ String.format("%.2f", valorFinal));

            }catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.out.println("Saliendo de la aplicacion");
              }break;
            } else if (!monedasValidas.contains(monedaorigen)) {
                System.out.println("Error en el ingreso");
                System.out.println(opciones);
                monedaorigen = lectura.nextLine();
            }


            }
}
}

