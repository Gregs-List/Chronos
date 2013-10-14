# mysql -u root -p --local-infile GregsList

# Create and switch to GregsList database
CREATE database if NOT EXISTS GregsList;
use GregsList;

# create the Users table
create table if not exists Users(
userID int primary key auto_increment,
email varchar(40) not null,
pass varchar(20), 
fullName varchar(50),  
feedbackRating INT, 
location varchar(20),
phoneNumber varchar(20),
UNIQUE(email)
);

# create the Listings table
create table if not exists Listings(
listingID INT primary key auto_increment,
userID INT,
title VARCHAR(30),
dateListed TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
category VARCHAR(20),
description VARCHAR(500),
FOREIGN KEY (userID) REFERENCES Users(userID)
) engine = innodb;

# create the ConditionLookup table
create table if not exists ConditionLookup(
conditionID INT primary key,
itemCondition VARCHAR(20)
);

# create the BikeType table
create table if not exists BikeType(
bikeTypeID INT primary key,
bikeType VARCHAR(20)
);

# create the Bikes table
create table if not exists Bikes(
listingID INT primary key,
bikeTypeID INT,
make VARCHAR(20),
model VARCHAR(20),
FOREIGN KEY (listingID) REFERENCES Listings(listingID),
FOREIGN KEY (bikeTypeID) REFERENCES BikeType(bikeTypeID)
) engine = innodb;

# create the BookType table
create table if not exists BookType(
bookTypeID INT primary key,
bookType VARCHAR (20)
);

# create the Books table
create table if not exists Books(
listingID INT primary key,
bookTypeID INT,
title VARCHAR(100),
author VARCHAR(100),
isbn VARCHAR(20),
assignedCourse VARCHAR(40),
conditionID INT,
FOREIGN KEY (listingID) REFERENCES Listings(listingID),
FOREIGN KEY (bookTypeID) REFERENCES BookType(bookTypeID),
FOREIGN KEY (conditionID) REFERENCES ConditionLookup(conditionID)
) engine = innodb;

# create the ElectronicsType table
create table if not exists ElectronicsType(
electronicsTypeID INT primary key,
electronicsType VARCHAR(30)
);

# create the Electronics table
create table if not exists Electronics(
listingID INT primary key,
electronicsTypeID INT,
make VARCHAR(20),
model VARCHAR(20),
size VARCHAR(20),
FOREIGN KEY (listingID) REFERENCES Listings(listingID),
FOREIGN KEY (electronicsTypeID) REFERENCES ElectronicsType(electronicsTypeID)
) engine = innodb;

# create the FurnitureType table
create table if not exists FurnitureType(
furnitureTypeID INT primary key,
furnitureType VARCHAR(30)
);

# create the Furniture table
create table if not exists Furniture(
listingID INT primary key,
furnitureTypeID INT,
conditionID INT,
FOREIGN KEY (listingID) REFERENCES Listings(listingID),
FOREIGN KEY (furnitureTypeID) REFERENCES FurnitureType(furnitureTypeID),
FOREIGN KEY (conditionID) REFERENCES ConditionLookup(conditionID)
) engine = innodb;

# create the MeetupType table
create table if not exists MeetupType(
meetupTypeID INT primary key,
meetupType VARCHAR(20)
);

# create the Meetups table
create table if not exists Meetups(
listingID INT primary key,
meetupTypeID INT,
location VARCHAR(50),
date DATE,
time TIME,
FOREIGN KEY (listingID) REFERENCES Listings(listingID),
FOREIGN KEY (meetupTypeID) REFERENCES MeetupType(meetupTypeID)
) engine = innodb;

# create the Miscellaneous table
create table if not exists Miscellaneous(
listingID INT primary key,
itemName VARCHAR(30),
FOREIGN KEY (listingID) REFERENCES Listings(listingID)
) engine = innodb;

# create the Rides table
create table if not exists Rides(
listingID INT primary key,
leavingFrom VARCHAR(100),
goingTo VARCHAR(100),
departureDate DATE,
departureTime TIME,
returnDate DATE,
returnTime TIME,
FOREIGN KEY (listingID) REFERENCES Listings(listingID)
) engine = innodb;

