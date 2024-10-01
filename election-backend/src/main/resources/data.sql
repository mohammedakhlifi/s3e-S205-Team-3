-- Creating the Region table
CREATE TABLE region (
                        RegionID INT AUTO_INCREMENT PRIMARY KEY,
                        Name VARCHAR(255) NOT NULL,
                        Population INT,
                        District VARCHAR(100)
);

-- Creating the Party table
CREATE TABLE party (
                       PartyID INT AUTO_INCREMENT PRIMARY KEY,
                       Name VARCHAR(255) NOT NULL,
                       Description TEXT,
                       FoundedDate DATE
);

-- Creating the User table (escaping the table name 'user' because it is a reserved keyword)
CREATE TABLE "user" (
                        UserID INT AUTO_INCREMENT PRIMARY KEY,
                        Name VARCHAR(255) NOT NULL,
                        Email VARCHAR(255) NOT NULL,
                        Password VARCHAR(255) NOT NULL,
                        Role ENUM('ADMIN', 'USER', 'MODERATOR') NOT NULL,
                        RegisteredDate DATETIME NOT NULL,
                        RegionID INT,
                        FOREIGN KEY (RegionID) REFERENCES region(RegionID)
);
