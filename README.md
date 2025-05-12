# **Hotel Booking Manager CSV**

## **Student Name**

**Mekishova Seyil**

---

## **📊 Presentation**

[Hotel Booking Manager Presentation (Canva)](https://www.canva.com/design/DAGk0SmC394/NQwT-JK1CXAO2Yw3PjdZOw/edit?utm_content=DAGk0SmC394&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

---

## **📝 Project Description**

The **Hotel Booking Manager CSV** is a console-based Java application designed to manage hotel room bookings efficiently using CSV file storage. It allows users to create, view, update, and delete bookings for specific room numbers (10–40), with room type determined automatically. The application provides real-time data persistence, validates input, and ensures that no duplicate room bookings occur.

---

## **💡 Overview**

This project offers a clean, structured, and beginner-friendly implementation of hotel booking functionality:

* Follows **object-oriented principles** using a `Booking` class.
* Uses **CSV file** (`bookings.csv`) for storage and persistence.
* Restricts booking to **room numbers 10–40**.
* Automatically maps room number to a **room type**.
* Ensures that each room can be booked **only once at a time**.
* Allows **modification, removal**, and **reporting** of bookings.

---

## **🚀 Features**

* ✅ Create new bookings (name, nights, price, room number).
* 📄 View all existing bookings with room types.
* ✏️ Update any field of a booking.
* ❌ Delete bookings by number.
* 📊 Generate total revenue and count report.
* 💾 Save/load bookings from `bookings.csv`.
* 🧠 Intelligent assignment of room types:

  * 10–19 → Single
  * 20–29 → Double
  * 30–34 → Family
  * 35–40 → Luxury
* 🔒 Prevents duplicate room bookings.

![The menu](https://github.com/sseellyy/Hotel/blob/master/Снимок%20экрана%202025-05-12%20082738.png)

---

## **🧠 Technical Overview**

### **1. Algorithms & Logic**

* **Room Validation:** Ensures entered room number is between 10 and 40 and is not already booked.
* **Room Type Assignment:** A method maps each room number range to a specific type.
* **CSV Handling:** On each booking change, the file is updated immediately.

### **2. Data Structures**

* `ArrayList<Booking>` – Dynamic list of current bookings.

### **3. Main Classes**

#### `Booking`

* Fields: `guestName`, `roomNumber`, `nights`, `pricePerNight`, `roomType`.
* `getRoomType(int)` – Static method to map room number to room type.
* `toCSV()` and `fromCSV()` – Methods to convert booking to/from CSV format.
* `display()` – User-friendly display of a booking.

#### `HotelBookingManagerCSV`

* `main()` – Entry point with menu loop.
* `createBooking()` – Validates and creates new booking.
* `viewBookings()` – Prints all bookings.
* `updateBooking()` – Updates guest name, room number, nights, or price.
* `deleteBooking()` – Deletes booking by index.
* `generateReport()` – Displays total bookings and revenue.
* `saveBookings()` / `loadBookings()` – File I/O with `bookings.csv`.

---

## **📁 File Format**

### `bookings.csv`

Each line contains:

```csv
guestName,roomNumber,nights,pricePerNight,roomType
```

Example:

```
Alice Johnson,12,3,50.0,Single
Bob Smith,27,2,70.0,Double
```

---

## **🛠 Running the Application**

### Requirements

* Java 8+
* Terminal or any IDE (e.g., IntelliJ IDEA, Eclipse)

### Steps

1. Save the project as `HotelBookingManagerCSV.java` (all code in one file).
2. Compile:

   ```bash
   javac HotelBookingManagerCSV.java
   ```
3. Run:

   ```bash
   java HotelBookingManagerCSV
   ```

---

## **📌 Sample Outputs**

### **Creating a Booking**

```
Имя гостя: Alice Johnson
Номер комнаты (10–40): 12
Тип номера: Single
Количество ночей: 3
Цена за ночь: 50
Бронирование добавлено!
```

### **Viewing Bookings**

```
Бронирование #1
Гость: Alice Johnson
Номер: 12 (Single)
Ночей: 3
Цена за ночь: 50.0
Общая стоимость: 150.0
```

### **Generating Report**

```
--- Отчёт ---
Количество бронирований: 2
Общая сумма по всем бронированиям: 310.0
```

---

## **✅ Validations**

* Room number must be from 10 to 40.
* Room must not already be booked.
* Input values like nights and price must be positive numbers.

---

## **⚠️ Error Handling**

* Invalid room input triggers warning and re-prompt.
* Booking index out of range handled gracefully.
* Try-catch blocks for file I/O to prevent crashes on missing or corrupted files.

---

## **🧪 Edge Cases**

| Case                          | Outcome                                |
| ----------------------------- | -------------------------------------- |
| Room number 41                | Rejected (must be 10–40)               |
| Booking room 15 twice         | Second attempt rejected                |
| Deleting from empty list      | Error message shown                    |
| Corrupted or missing CSV file | Program starts with empty booking list |

---

## **🔧 Challenges Faced**

* Creating a consistent mapping of room number ranges to types.
* Preventing duplicate bookings per room while keeping code simple.
* Immediate file persistence after any operation.
* Handling blank inputs during updates and keeping current values.

---
