🏦 Our Bank - Java Banking Management System
Our Bank is a professional desktop application developed in Java Swing that simulates a real-world banking environment. It utilizes a MySQL database for persistence and a structured object-oriented architecture to handle financial transactions securely.

🚀 Key Features <br>
👤 Client Dashboard
Secure Authentication: Login system verified against the MySQL Compte_Bancaire table.

Live Balance Inquiry: Real-time display of current funds.

Financial Operations: Automated Deposits and Withdrawals with instant database synchronization.

Peer-to-Peer Transfer: Transfer funds to other users using their unique CIN (ID number).

Local Statement Logging: Generates a personal history_[id].txt file to track every transaction.

🔑 Administrator Portal
Centralized Management: Dedicated interface for bank overseers.

Account Oversight: A dynamic JTable that joins the clients and Compte_Bancaire tables to display all user data.

Global Logging: Monitor bank-wide activity and system logs.

🛠️ Technical Stack
Language: Java (JDK 17+)

GUI: Java Swing / AWT

Database: MySQL 8.0+

JDBC Driver: MySQL Connector/J 9.5.0

Architecture: Inheritance-based UI components (Extending Initial_interface)

📂 Project Structure
Plaintext

Our-Bank/ <br>
├── Images/              # UI Assets (Backgrounds, Icons, Buttons) <br>
├── lib/                 # External Libraries (MySQL JDBC Connector) <br>
├── src/                 # Java Source Files (.java) <br>
├── database.sql         # Database Schema and Initial Seed Data <br>
└── README.md            # Project Documentation <br>
⚙️ Setup & Installation
1. Database Setup
Ensure your MySQL server is running. Create the database and tables by importing the provided SQL file:

Bash
```
mysql -u root -p < database.sql
```
2. Compilation
Compile all source files while linking the JDBC driver located in the lib folder:

Bash
```
javac -cp ".;lib/mysql-connector-j-9.5.0.jar" src/*.java
```
3. Execution
To start the Client application:

Bash
```
java -cp "src;lib/mysql-connector-j-9.5.0.jar" ClientLogin
```
To start the Admin application:

Bash
```
java -cp "src;lib/mysql-connector-j-9.5.0.jar" AdminLogin
```
📊 Database Schema
The system uses a relational model to separate personal client data from sensitive banking credentials.

clients table: Stores personal identifiers (Name, CIN, Email).

Compte_Bancaire table: Stores financial data (RIB, Balance, Password, ID_Client).

admins table: Stores administrative credentials.
