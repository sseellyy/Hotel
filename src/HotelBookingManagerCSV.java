import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Booking {
    String guestName;
    int roomNumber;
    int nights;
    double pricePerNight;
    String roomType;


    public Booking(String guestName, int roomNumber, int nights, double pricePerNight) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
        this.roomType = getRoomType(roomNumber);
    }

    public static String getRoomType(int roomNumber) {
        if (roomNumber >= 10 && roomNumber <= 19) return "Single";
        if (roomNumber >= 20 && roomNumber <= 29) return "Double";
        if (roomNumber >= 30 && roomNumber <= 34) return "Family";
        if (roomNumber >= 35 && roomNumber <= 40) return "Luxury";
        return "Unknown";
    }

    public double getTotalPrice() {
        return nights * pricePerNight;
    }

    public String toCSV() {
        return guestName + "," + roomNumber + "," + nights + "," + pricePerNight + "," + roomType;
    }

    public static Booking fromCSV(String line) {
        String[] parts = line.split(",");
        Booking b = new Booking(
                parts[0],
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Double.parseDouble(parts[3])
        );
        //сохраняем тип из файла
        if (parts.length >= 5) b.roomType = parts[4];
        return b;
    }

    public String display() {
        return "Гость: " + guestName +
                "\nНомер: " + roomNumber +
                " (" + roomType + ")" +
                "\nНочей: " + nights +
                "\nЦена за ночь: " + pricePerNight +
                "\nОбщая стоимость: " + getTotalPrice() + "\n";
    }
}

public class HotelBookingManagerCSV {
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "bookings.csv";

    public static void main(String[] args) {
        loadBookings();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Менеджер бронирований ---");
            System.out.println("1. Создать бронирование");
            System.out.println("2. Посмотреть все бронирования");
            System.out.println("3. Обновить бронирование");
            System.out.println("4. Удалить бронирование");
            System.out.println("5. Сгенерировать отчёт");
            System.out.println("6. Выход");
            System.out.print("Выбор: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": createBooking(); break;
                case "2": viewBookings(); break;
                case "3": updateBooking(); break;
                case "4": deleteBooking(); break;
                case "5": generateReport(); break;
                case "6": running = false; break;
                default: System.out.println("Неверный выбор.");
            }
        }

        System.out.println("Выход из программы.");
    }

    static void createBooking() {
        System.out.print("Имя гостя: ");
        String name = scanner.nextLine();

        int room;
        while (true) {
            System.out.print("Номер комнаты (10–40): ");
            room = Integer.parseInt(scanner.nextLine());

            if (room < 10 || room > 40) {
                System.out.println("Ошибка: номер должен быть от 10 до 40.");
                continue;
            }

            if (isRoomOccupied(room)) {
                System.out.println("Ошибка: номер уже занят.");
                continue;
            }

            break;
        }

        String roomType = Booking.getRoomType(room);
        System.out.println("Тип номера: " + roomType);

        System.out.print("Количество ночей: ");
        int nights = Integer.parseInt(scanner.nextLine());
        System.out.print("Цена за ночь: ");
        double price = Double.parseDouble(scanner.nextLine());

        bookings.add(new Booking(name, room, nights, price));
        saveBookings();
        System.out.println("Бронирование добавлено!");
    }

    static boolean isRoomOccupied(int roomNumber) {
        for (Booking b : bookings) {
            if (b.roomNumber == roomNumber) {
                return true;
            }
        }
        return false;
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("Нет бронирований.");
            return;
        }

        int i = 1;
        for (Booking booking : bookings) {
            System.out.println("Бронирование #" + i++);
            System.out.println(booking.display());
        }
    }

    static void updateBooking() {
        viewBookings();
        System.out.print("Введите номер бронирования для обновления: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= bookings.size()) {
            System.out.println("Неверный индекс.");
            return;
        }

        Booking b = bookings.get(index);

        System.out.println("Обновление данных бронирования (оставь пустым, чтобы не менять):");

        System.out.print("Новое имя (" + b.guestName + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) b.guestName = name;

        System.out.print("Новый номер комнаты (" + b.roomNumber + "): ");
        String roomInput = scanner.nextLine();
        if (!roomInput.isEmpty()) {
            int newRoom = Integer.parseInt(roomInput);
            if (newRoom < 10 || newRoom > 40) {
                System.out.println("Ошибка: номер должен быть от 10 до 40.");
            } else if (newRoom != b.roomNumber && isRoomOccupied(newRoom)) {
                System.out.println("Ошибка: номер уже занят.");
            } else {
                b.roomNumber = newRoom;
                b.roomType = Booking.getRoomType(newRoom); // обновить тип
            }
        }

        System.out.print("Новое количество ночей (" + b.nights + "): ");
        String nightsInput = scanner.nextLine();
        if (!nightsInput.isEmpty()) b.nights = Integer.parseInt(nightsInput);

        System.out.print("Новая цена за ночь (" + b.pricePerNight + "): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) b.pricePerNight = Double.parseDouble(priceInput);

        saveBookings();
        System.out.println("Бронирование обновлено.");
    }

    static void deleteBooking() {
        viewBookings();
        System.out.print("Введите номер бронирования для удаления: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= bookings.size()) {
            System.out.println("Неверный индекс.");
            return;
        }
        bookings.remove(index);
        saveBookings();
        System.out.println("Бронирование удалено.");
    }

    static void generateReport() {
        double total = 0;
        for (Booking b : bookings) {
            total += b.getTotalPrice();
        }

        System.out.println("\n--- Отчёт ---");
        System.out.println("Количество бронирований: " + bookings.size());
        System.out.println("Общая сумма по всем бронированиям: " + total);
    }

    static void saveBookings() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Booking b : bookings) {
                writer.println(b.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении CSV: " + e.getMessage());
        }
    }

    static void loadBookings() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bookings.add(Booking.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении CSV: " + e.getMessage());
        }
    }
}
