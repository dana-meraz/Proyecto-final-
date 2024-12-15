/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.coyotas;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author Angel
 */
public class Coyotas {
    static Scanner scanner = new Scanner(System.in);
    static Pedido[] pedidos = new Pedido[999]; 
    static int totalPedidos = 0; 

    public static final int LUNES = 1;
    public static final int MARTES = 2;
    public static final int MIERCOLES = 3;
    public static final int JUEVES = 4;
    public static final int VIERNES = 5;
    public static final int SABADO = 6;
    public static final int DOMINGO = 7;
    
    public static final int ENERO = 1;
    public static final int FEBRERO = 2;
    public static final int MARZO = 3;
    public static final int ABRIL = 4;
    public static final int MAYO = 5;
    public static final int JUNIO = 6;
    public static final int JULIO = 7;
    public static final int AGOSTO = 8;
    public static final int SEPTIEMBRE = 9;
    public static final int OCTUBRE = 10;
    public static final int NOVIEMBRE = 11;
    public static final int DICIEMBRE = 12;
    
    public static void main(String[] args) {
        
        int mesAct = obtenerMesAuto();
        int diaAct = obtenerDiaAuto();
        int semana2 = obtenerDiaDeLaSemanaAuto();
        int opcionMenu, go = 0;

        // Menú principal
        do {
            
            mostrarMenuPrincipal();
            opcionMenu = leerOpcion("\nSelecciona una opcion: ");
            
            switch (opcionMenu) {
                case 1:
                    mostrarLimitesPorDia();
                    break;
                case 2:
                    mostrarPromociones();
                    break;
                case 3:
                    iniciarPedido(mesAct, diaAct, semana2, go);
                    continue;
                case 4:
                    mostrarPedidos();
                    continue;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                case 1212:
                    System.out.println("");
                    verificarContraseña();
                    break;
                default:
                    System.out.println("Por favor, selecciona una opcion valida (1-5).");
                    break;
            }
        } while (opcionMenu != 5);
    }

    public static int obtenerMesActual() {
        System.out.println("Ingrese el mes actual (1-12): ");
        int mes = scanner.nextInt();
        while (mes < 1 || mes > 12) {
            System.out.println("Mes invalido. Intentelo de nuevo.");
            mes = scanner.nextInt();
        }
        return mes;
    }

    public static int obtenerDiaDelMes(int mes) {
        int maxDias = diasDelMes(mes);
        System.out.println("Ingrese el dia actual (1-" + maxDias + "): ");
        int dia = scanner.nextInt();
        while (dia < 1 || dia > maxDias) {
            System.out.println("Dia invalido. Intentelo de nuevo.");
            dia = scanner.nextInt();
        }
        return dia;
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("\n=== MENU  ===");
        System.out.println("1. Ver limites de pedido por dia de la semana.");
        System.out.println("2. Ver promociones disponibles.");
        System.out.println("3. Realizar un pedido.");
        System.out.println("4. Ver pedidos.");
        System.out.println("5. Salir.");
    }

    public static void mostrarLimitesPorDia() {
        System.out.println("\n==== LIMITES POR DIA DE LA SEMANA ====");
        System.out.println("Lunes: Cajeta: 5, Jamoncillo: 3");
        System.out.println("Martes: Cajeta: 6, Jamoncillo: 7");
        System.out.println("Miercoles: Cajeta: 3, Jamoncillo: 6");
        System.out.println("Jueves: Cajeta: 5, Jamoncillo: 3");
        System.out.println("Viernes: Cajeta: 7, Jamoncillo: 3");
        System.out.println("Sabado: Cajeta: 4, Jamoncillo: 6");
        System.out.println("Domingo: Cajeta: 3, Jamoncillo: 5");
    }

    public static void mostrarPromociones() {
        System.out.println("\n=== PROMOCIONES ===");
        System.out.println("Enero: 2x1 en los dias 1 y 12.");
        System.out.println("Febrero: 2x1 el dia 5.");
        System.out.println("Marzo: 2x1 los dias 3 y 17.");
        System.out.println("Abril: 3x1 los dias 4 y 5.");
        System.out.println("Mayo: 2x1 el dia 13.");
        System.out.println("Junio y Julio: Sin promociones.");
        System.out.println("Agosto: 3x1 el dia 29.");
        System.out.println("Septiembre: 2x1 el dia 12.");
        System.out.println("Octubre: 2x1 entre los dias 5 y 12.");
        System.out.println("Noviembre: 2x1 los dias 13 al 21.");
        System.out.println("Diciembre: 3x1 los dias 1 y 2.");
    }

    public static void iniciarPedido(int mes, int dia, int semana, int salir) {
        System.out.println("\n=== INICIANDO PEDIDO ===");
        int limiteCaje = limitePorDiaCajeta(semana);
        int limiteJamon = limitePorDiaDeJamoncillo(semana);

        int cantidadCajeta = leerCantidad("Cuantas coyotas de cajeta deseas?", limiteCaje);
        int cantidadJamoncillo = leerCantidad("Cuantas coyotas de jamoncillo deseas?", limiteJamon);
        System.out.println("");

        int total = (cantidadCajeta + cantidadJamoncillo) * 25;

        // Promociones
        total = aplicarPromociones(mes, dia, total);

        System.out.println("Total a pagar: $" + total);
        System.out.println("Gracias por tu pedido!!");
        
         pedidos[totalPedidos++] = new Pedido(mes, dia, semana, cantidadCajeta, cantidadJamoncillo, total);
        
    }

    public static int diasDelMes(int mes) {
         switch (mes) {
            case ENERO, MARZO, MAYO, JULIO, AGOSTO, OCTUBRE, DICIEMBRE:
                return 31;
            case FEBRERO:
                return 28;
            case ABRIL, JUNIO, SEPTIEMBRE, NOVIEMBRE:
                return  30;
            default:
                return 31;
        }
    }

    public static int limitePorDiaCajeta(int limCaj) {
         switch (limCaj) {
            case LUNES:
                return 5;
            case MARTES:
                return 6;
            case MIERCOLES:
                return  3;
            case JUEVES:
                return 5;
            case VIERNES:
                return 7;
            case SABADO:
                return  4;
            default:
                return 3;
                }
         }

    public static int limitePorDiaDeJamoncillo(int limJam) {
        switch (limJam) {
            case LUNES:
                return 3;
            case MARTES:
                return 7;
            case MIERCOLES:
                return  6;
            case JUEVES:
                return 3;
            case VIERNES:
                return 3;
            case SABADO:
                return  6;
            default:
                return 5;
                }
        }
    
       public static int leerCantidad(String mensaje, int limite) {
        return leerNumeroConRango(mensaje + " (Maximo: " + limite + ")", 0, limite);
    }

    public static int leerNumeroConRango(String mensaje, int min, int max) {
        int numero;
        do {
            System.out.println(mensaje);
            numero = scanner.nextInt();
            if (numero < min || numero > max) {
                System.out.println("Numero fuera de rango, Intuntalo de nuevo.");
                System.out.println("");
            }
        } while (numero < min || numero > max);
        return numero;
    }

    public static int aplicarPromociones(int mes, int dia, int total) {
        if ((mes == ENERO && (dia == 1 || dia == 12)) ||
            (mes == FEBRERO && dia == 5) || 
            (mes == MARZO && (dia == 3 || dia == 17)) || 
            (mes == MAYO && dia == 18) || 
            (mes == OCTUBRE && (dia == 5 || dia == 12)) || 
            (mes == NOVIEMBRE && (dia >= 13 && dia <= 21))){
            System.out.println("Promocion aplicada: 2x1.");
            total /= 2;
        }else if((mes == ABRIL && (dia == 4 || dia == 5)) || (mes == AGOSTO && dia == 29) ||
                (mes == DICIEMBRE && (dia == 1 || dia == 2))){
            System.out.println("Promocion aplicada: 3x1.");
            total /= 3;
        }
        return total;
    }

    public static int leerOpcion(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }
    
    public static int obtenerDiaDeLaSemana() {
        System.out.println("Ingrese el dia actual de la semana (1-7): ");
        int sem = scanner.nextInt();
        while (sem < 1 || sem > 7) {
            System.out.println("Semana invalido. Intentelo de nuevo.");
            sem = scanner.nextInt();
        }
        return sem;
    }
    
    public static int diasDeLaSemana(int semana) {
         switch (semana) {
            case LUNES:
                System.out.println("Lunes");
                return LUNES;
            case MARTES:
                System.out.println("Martes");
                return MARTES;
            case MIERCOLES:
                System.out.println("Miercoles");
                return MIERCOLES;
            case JUEVES:
                System.out.println("Jueves");
                return JUEVES;
            case VIERNES:
                System.out.println("Viernes");
                return VIERNES;
            case SABADO:
                System.out.println("Sabado");
                return SABADO;
            default:
                System.out.println("Domingo");
                return DOMINGO;
                }
        }
    
    public static int obtenerDiaDeLaSemanaAuto() {
        DayOfWeek diaActual = LocalDate.now().getDayOfWeek();
        int numeroDia = diaActual.getValue();

        // Mapea los días al español
        Map<DayOfWeek, String> diasEnEspanol = Map.of(
            DayOfWeek.MONDAY, "Lunes",
            DayOfWeek.TUESDAY, "Martes",
            DayOfWeek.WEDNESDAY, "Miercoles",
            DayOfWeek.THURSDAY, "Jueves",
            DayOfWeek.FRIDAY, "Viernes",
            DayOfWeek.SATURDAY, "Sabado",
            DayOfWeek.SUNDAY, "Domingo"
        );
        // Traducción del día al español
        String diaEnEspanol = diasEnEspanol.get(diaActual);

        System.out.println("Hoy es " + diaEnEspanol);
        return numeroDia;
    }
    
    //Metodo para sacar el mes de la fecha del pc
    public static int obtenerMesAuto() {
        // Obtiene la fecha actual
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue(); // Número del mes (1-12)

        System.out.println("Mes actual: " + mesActual);
        return mesActual;
    }
    
     //Metodo para sacar el dia de la fecha del pc
    public static int obtenerDiaAuto() {
        // Obtiene la fecha actual
        LocalDate fechaActual = LocalDate.now();
        int diaDelMes = fechaActual.getDayOfMonth();

        // Imprime los resultados
        System.out.println("Dia del mes: " + diaDelMes);
        return diaDelMes;
    }
        
    public static void verificarContraseña() {
        boolean salir = false;
        while(!salir){
        String[] contraseñas = {"coyota#100", "no"};
        String contraseña = scanner.nextLine();
        boolean esValida = false;
        for (int i = 0; i < contraseñas.length; i++) {
            if (contraseña.equals(contraseñas[i])) {
                esValida = true;
                ejecutarAccion(i);
                salir = true;
                break;
            }
        }
        
        if (!esValida) {
            System.out.print("Escribe (no) si no tienes clave: ");
        }
    }
        }
    
    // Método que ejecuta una acción dependiendo del índice de la contraseña
    public static void ejecutarAccion(int indice) {
        switch (indice) {
            case 0:
                System.out.println("Bienvenido");
                iniciarPedidoComoAdmin();
                break;
            case 1:
                System.out.println("");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }
   
    public static void iniciarPedidoComoAdmin() {
        int mesAct = obtenerMesAuto();
        int diaAct = obtenerDiaAuto();
        int semana2 = obtenerDiaDeLaSemanaAuto();
        int opcionMenu, mesActual = 0, diaActual = 0, diaSemana, semana = 0, go = 0;
        do {
            mostrarMenuPrincipalAdmin();
            opcionMenu = leerOpcion("\nSelecciona una opcion: ");
            
            switch (opcionMenu) {
                case 1:
                    mostrarLimitesPorDia();
                    break;
                case 2:
                    mostrarPromociones();
                    break;
                case 3:
                    if (go == 1){
                        iniciarPedido(mesActual, diaActual, semana, go);
                        continue;
                        }
                    iniciarPedido(mesAct, diaAct, semana2, go);
                    continue;
                case 4:
                    mesActual = obtenerMesActual();
                    diaActual = obtenerDiaDelMes(mesActual);
                    diaSemana = obtenerDiaDeLaSemana();
                    semana = diasDeLaSemana(diaSemana);
                    go = 1;
                    break;
                case 5:
                    mostrarPedidos();
                    continue;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Por favor, selecciona una opcion valida (1-6).");
                    break;
            }
        } while (opcionMenu != 6);
    }
    
    public static void mostrarMenuPrincipalAdmin() {
        System.out.println("\n=== MENU  ===");
        System.out.println("1. Ver limites de pedido por dia de la semana.");
        System.out.println("2. Ver promociones disponibles.");
        System.out.println("3. Realizar un pedido.");
        System.out.println("4. Modificar dia.");
        System.out.println("5. Mostrar pedido.");
        System.out.println("6. Salir del programa.");
    }
    
    public static void mostrarPedidos() {
        System.out.println("\n=== PEDIDOS REALIZADOS ===");
         if (totalPedidos == 0) {
            System.out.println("No hay pedidos realizados aún.");
            return;
        }
        
        for (int i = 0; i < totalPedidos; i++) {
            Pedido p = pedidos[i];
            System.out.printf(
                "Pedido %d: Fecha: %d/%d - Día: %s - Cajeta: %d - Jamoncillo: %d - Total: $%d\n",
                i + 1, p.dia, p.mes, obtenerNombreDia(p.diaSemana), p.cantidadCajeta, p.cantidadJamoncillo, p.total
            );
        }
    }
    
    
    public static String obtenerNombreDia(int diaSemana) {
        return switch (diaSemana) {
            case LUNES -> "Lunes";
            case MARTES -> "Martes";
            case MIERCOLES -> "Miercoles";
            case JUEVES -> "Jueves";
            case VIERNES -> "Viernes";
            case SABADO -> "Sabado";
            default -> "Domingo";
        };
    }
    
    static class Pedido {
        int mes, dia, diaSemana, cantidadCajeta, cantidadJamoncillo, total;

        Pedido(int mes, int dia, int diaSemana, int cantidadCajeta, int cantidadJamoncillo, int total) {
            this.mes = mes;
            this.dia = dia;
            this.diaSemana = diaSemana;
            this.cantidadCajeta = cantidadCajeta;
            this.cantidadJamoncillo = cantidadJamoncillo;
            this.total = total;
        }
    }

} 

    //public static String obtenerNombreDia(int diaSemana) {
      //  return switch (diaSemana) {
        //    case LUNES: 
          //      System.out.println("Lunes");
            //case MARTES:
              //  System.out.println("Martes");
//            case MIERCOLES:
  //              System.out.println("Miércoles");
    //        case JUEVES:
      //          System.out.println("Jueves");
        //    case VIERNES:
          //      System.out.println("Viernes");
            //case SABADO: 
              //  System.out.println("Sábado");
//            default:
  //              System.out.println("Domingo");
    //    };
    //}