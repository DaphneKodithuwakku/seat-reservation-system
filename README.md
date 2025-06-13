# Seat Reservation System 

A digital booking system developed in Java to manage seat reservations, cancellations, and ticket tracking. This system simulates a real-world seating plan with features for booking, viewing availability, and managing user and ticket data.

## Features
- Real-time seat availability and updates
- Booking and cancellation functionality
- Ticket search and detailed info display
- Seating plan visualization
- Ticket saving to `.txt` files
- Simple user input interface

##  Technologies Used
- Java
- Object-Oriented Programming (OOP)
- File Handling (`FileWriter`)
- CLI-based interface

## System Overview
The application allows users to:
- Book a seat by choosing row and seat number
- View first available seat
- Display full seating plan
- Cancel bookings and re-validate seat status
- View all ticket info and total revenue
- Search for specific tickets using row/seat

## Project Structure
- `Person.java`: Stores user details (name, surname, email)
- `Ticket.java`: Contains ticket and seat information, linked to `Person`
- `Main.java`: Core booking logic, seat validation, and UI interactions

