import Modelos.Moneda;
import Modelos.Server;
import MyExceptions.MyException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Para buenas prácticas es recomendable guardar esta key en una variable de entorno
        String apikey = "3d4d05037d58d1ba2219af12";
        Scanner user = new Scanner(System.in);

        String[] coins = {"USD", "PEN", "EUR", "ARS", "GPB", "JPY", "MXN"};

        //Creando el objeto server para realizar la conexiòn con la API
        Server server = new Server(apikey);

        header();
        while (true) {
            //Construyendo la url
            StringBuilder url = new StringBuilder("https://v6.exchangerate-api.com/v6/pair/");

            System.out.println("¡Vamos a convertir!");
            try {
                showCoins(coins);
                System.out.print("Escoge el número de tu moneda inicial: ");
                int initialMoney = user.nextInt();
                System.out.println();

                showCoins(coins);
                System.out.print("Escoge el número de tu moneda final: ");
                int finalMoney = user.nextInt();

                if (initialMoney == 0 && finalMoney == 0) {
                    System.out.println("Programa finalizado...");
                    break;

                } else if ((initialMoney > 0 && initialMoney < 8) && (finalMoney > 0 && finalMoney < 8)) {
                    System.out.print("\nEscoge el monto a convertir: ");
                    float amount = user.nextFloat();

                    url.append(coins[initialMoney-1]).append('/').append(coins[finalMoney-1]).append('/').append(amount);
                    Moneda convertedCurrency = new Moneda(server.consultaDeConversion(String.valueOf(url)));

                    header2();
                    System.out.printf("De %.2f %s a %s", amount, coins[initialMoney-1], coins[finalMoney-1] + " es: " + convertedCurrency + "\n");
                    footer();

                } else {
                    System.out.println("Elije una opción correcta...\n");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error en el ingreso del teclado (Ingrese solo números)...\n");
                user.nextLine();
            } catch (MyException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    public static void showCoins(String[] moneys) {
        for (int i = 0; i < moneys.length; i++) {
            System.out.println((i+1) + ". " + moneys[i]);
        }
    }

    public static void header() {
        System.out.println("***********************************************************");
        System.out.println("             BIENVENIDO AL CONVERSOR DE MONEDA             ");
        System.out.println("***********************************************************");
    }

    public static void header2() {
        System.out.println();
        System.out.println("***********************************************************");
        System.out.println("                   CONVERSIÓN FINALIZADA                   ");
        System.out.println("***********************************************************");
    }

    public static void footer() {
        System.out.println("-----------------------------------------------------------\n");
    }
}