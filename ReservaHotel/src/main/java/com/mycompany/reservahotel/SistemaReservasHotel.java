/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reservahotel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SistemaReservasHotel {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Habitacion> habitaciones = new ArrayList<>();
    private static List<Reserva> reservas = new ArrayList<>();
    private static Usuario usuarioActual = null;

    public static void main(String[] args) {
        habitaciones.add(new Habitacion("Individual", 50.0, 1, "Wi-Fi, TV"));
        habitaciones.add(new Habitacion("Doble", 80.0, 2, "Wi-Fi, TV, Balcón"));
        habitaciones.add(new Habitacion("Suite", 150.0, 4, "Wi-Fi, TV, Jacuzzi"));

        while (true) {
            System.out.println("Bienvenido al Sistema de Reservas de Hotel");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private static void registrarUsuario() {
        System.out.println("Ingrese su nombre de usuario:");
        String username = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();
        System.out.println("¿Es administrador? (s/n)");
        boolean isAdmin = scanner.nextLine().equalsIgnoreCase("s");

        Usuario nuevoUsuario = new Usuario(username, password, isAdmin);
        usuarios.add(nuevoUsuario);

        System.out.println("Usuario registrado con éxito.");
    }

    private static void iniciarSesion() {
        System.out.println("Ingrese su nombre de usuario:");
        String username = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                usuarioActual = usuario;
                menuPrincipal();
                return;
            }
        }

        System.out.println("Nombre de usuario o contraseña incorrectos.");
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("Menú Principal");
            if (usuarioActual.isAdmin()) {
                System.out.println("1. Gestionar habitaciones");
                System.out.println("2. Ver reservas");
            } else {
                System.out.println("1. Buscar habitaciones disponibles");
                System.out.println("2. Ver mis reservas");
                System.out.println("3. Cancelar reserva");
            }
            System.out.println("4. Cerrar sesión");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    if (usuarioActual.isAdmin()) {
                        gestionarHabitaciones();
                    } else {
                        buscarHabitacionesDisponibles();
                    }
                    break;
                case 2:
                    if (usuarioActual.isAdmin()) {
                        verReservas();
                    } else {
                        verMisReservas();
                    }
                    break;
                case 3:
                    if (!usuarioActual.isAdmin()) {
                        cancelarReserva();
                    } else {
                        System.out.println("Opción inválida");
                    }
                    break;
                case 4:
                    usuarioActual = null;
                    return;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private static void gestionarHabitaciones() {
        while (true) {
            System.out.println("Gestionar Habitaciones");
            System.out.println("1. Agregar habitación");
            System.out.println("2. Eliminar habitación");
            System.out.println("3. Modificar detalles de habitación");
            System.out.println("4. Volver al menú principal");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    agregarHabitacion();
                    break;
                case 2:
                    eliminarHabitacion();
                    break;
                case 3:
                    modificarDetallesHabitacion();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private static void agregarHabitacion() {
        System.out.println("Ingrese el tipo de habitación (Individual/Doble/Suite):");
        String tipo = scanner.nextLine();
        System.out.println("Ingrese el precio por noche:");
        double precioNoche = Double.parseDouble(scanner.nextLine());
        System.out.println("Ingrese el número máximo de huéspedes:");
        int maxHuespedes = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese las comodidades:");
        String comodidades = scanner.nextLine();

        Habitacion nuevaHabitacion = new Habitacion(tipo, precioNoche, maxHuespedes, comodidades);
        habitaciones.add(nuevaHabitacion);
        System.out.println("Habitación agregada con éxito.");
    }

    private static void eliminarHabitacion() {
        System.out.println("Ingrese el tipo de habitación a eliminar (Individual/Doble/Suite):");
        String tipo = scanner.nextLine();

        Habitacion habitacionEliminar = null;
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo().equalsIgnoreCase(tipo)) {
                habitacionEliminar = habitacion;
                break;
            }
        }

        if (habitacionEliminar != null) {
            habitaciones.remove(habitacionEliminar);
            System.out.println("Habitación eliminada con éxito.");
        } else {
            System.out.println("Habitación no encontrada.");
        }
    }

    private static void modificarDetallesHabitacion() {
        System.out.println("Ingrese el tipo de habitación a modificar (Individual/Doble/Suite):");
        String tipo = scanner.nextLine();

        Habitacion habitacionModificar = null;
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo().equalsIgnoreCase(tipo)) {
                habitacionModificar = habitacion;
                break;
            }
        }

        if (habitacionModificar != null) {
            System.out.println("Ingrese el nuevo precio por noche:");
            double nuevoPrecio = Double.parseDouble(scanner.nextLine());
            System.out.println("Ingrese el nuevo número máximo de huéspedes:");
            int nuevoMaxHuespedes = Integer.parseInt(scanner.nextLine());
            System.out.println("Ingrese las nuevas comodidades:");
            String nuevasComodidades = scanner.nextLine();

            habitacionModificar.setPrecioNoche(nuevoPrecio);
            habitacionModificar.maxHuespedes = nuevoMaxHuespedes;
            habitacionModificar.comodidades = nuevasComodidades;

            System.out.println("Detalles de la habitación modificados con éxito.");
        } else {
            System.out.println("Habitación no encontrada.");
        }
    }

    
    private static void buscarHabitacionesDisponibles() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy):");
    Date fechaInicio;
    try {
        fechaInicio = sdf.parse(scanner.nextLine());
    } catch (Exception e) {
        System.out.println("Fecha inválida.");
        return;
    }
    System.out.println("Ingrese la fecha de fin (dd/MM/yyyy):");
    Date fechaFin;
    try {
        fechaFin = sdf.parse(scanner.nextLine());
    } catch (Exception e) {
        System.out.println("Fecha inválida.");
        return;
    }

    List<Habitacion> habitacionesDisponibles = new ArrayList<>();
    for (Habitacion habitacion : habitaciones) {
        boolean disponible = true;
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion() == habitacion &&
                (fechaInicio.before(reserva.getFechaFin()) && fechaFin.after(reserva.getFechaInicio()))) {
                disponible = false;
                break;
            }
        }
        if (disponible) {
            habitacionesDisponibles.add(habitacion);
        }
    }

    if (habitacionesDisponibles.isEmpty()) {
        System.out.println("No hay habitaciones disponibles para esas fechas.");
    } else {
        System.out.println("Habitaciones disponibles:");
        int index = 1;
        for (Habitacion habitacion : habitacionesDisponibles) {
            System.out.println(index + ". " + habitacion.getTipo() + " - Precio: $" + habitacion.getPrecioNoche() + "/noche");
            index++;
        }
        
        System.out.println("Seleccione una habitación:");
        int opcionHabitacion = Integer.parseInt(scanner.nextLine());
        if (opcionHabitacion > 0 && opcionHabitacion <= habitacionesDisponibles.size()) {
            Habitacion habitacionSeleccionada = habitacionesDisponibles.get(opcionHabitacion - 1);
            System.out.println("¿Desea reservar la habitación " + habitacionSeleccionada.getTipo() + " por $" + habitacionSeleccionada.getPrecioNoche() + "/noche? (s/n)");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                reservarHabitacion(usuarioActual, habitacionSeleccionada, fechaInicio, fechaFin);
            }
        } else {
            System.out.println("Opción inválida.");
        }
    }
}

private static void reservarHabitacion(Usuario usuario, Habitacion habitacion, Date fechaInicio, Date fechaFin) {
    Reserva nuevaReserva = new Reserva(usuario, habitacion, fechaInicio, fechaFin);
    reservas.add(nuevaReserva);
    habitacion.setDisponible(false);
    System.out.println("Reserva realizada con éxito.");
}

    private static void verReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas realizadas.");
        } else {
            System.out.println("Reservas:");
            for (Reserva reserva : reservas) {
                System.out.println("Usuario: " + reserva.getUsuario().getUsername() +
                                   ", Habitación: " + reserva.getHabitacion().getTipo() +
                                   ", Fecha inicio: " + reserva.getFechaInicio() +
                                   ", Fecha fin: " + reserva.getFechaFin());
            }
        }
    }

    private static void verMisReservas() {
        List<Reserva> misReservas = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getUsuario() == usuarioActual) {
                misReservas.add(reserva);
            }
        }

        if (misReservas.isEmpty()) {
            System.out.println("No tienes reservas realizadas.");
        } else {
            System.out.println("Tus reservas:");
            for (Reserva reserva : misReservas) {
                System.out.println("Habitación: " + reserva.getHabitacion().getTipo() +
                                   ", Fecha inicio: " + reserva.getFechaInicio() +
                                   ", Fecha fin: " + reserva.getFechaFin());
            }
        }
    }

    private static void cancelarReserva() {
        System.out.println("Ingrese el tipo de habitación de la reserva a cancelar (Individual/Doble/Suite):");
        String tipo = scanner.nextLine();

        Reserva reservaCancelar = null;
        for (Reserva reserva : reservas) {
            if (reserva.getUsuario() == usuarioActual && reserva.getHabitacion().getTipo().equalsIgnoreCase(tipo)) {
                reservaCancelar = reserva;
                break;
            }
        }

        if (reservaCancelar != null) {
            reservas.remove(reservaCancelar);
            reservaCancelar.getHabitacion().setDisponible(true);
            System.out.println("Reserva cancelada con éxito.");
        } else {
            System.out.println("Reserva no encontrada.");
        }
    }
}
