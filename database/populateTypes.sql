# mysql -u root -p --local-infile GregsList
use GregsList;

# populate the ConditionLookup table
INSERT INTO ConditionLookup(0, "unspecified");
INSERT INTO ConditionLookup(1, "new");
INSERT INTO ConditionLookup(2, "excellent");
INSERT INTO ConditionLookup(3, "light wear");
INSERT INTO ConditionLookup(4, "normal wear");
INSERT INTO ConditionLookup(5, "damaged");

# populate the BikeType table
INSERT INTO BikeType(0, "other");
INSERT INTO BikeType(1, "road");
INSERT INTO BikeType(2, "city");
INSERT INTO BikeType(3, "mountain");
INSERT INTO BikeType(4, "cruiser");
INSERT INTO BikeType(5, "hybrid");
INSERT INTO BikeType(6, "unicycle");
INSERT INTO BikeType(7, "electric");

# populate the BookType table
INSERT INTO BookType(0, "other");
INSERT INTO BookType(1, "textbook");
INSERT INTO BookType(2, "literature");
INSERT INTO BookType(3, "poetry");
INSERT INTO BookType(4, "reference");
INSERT INTO BookType(5, "nonfiction");
INSERT INTO BookType(6, "graphic novel");

# populate the ElectronicsType table
INSERT INTO ElectronicsType(0, "other");
INSERT INTO ElectronicsType(1, "TV");
INSERT INTO ElectronicsType(2, "computer");
INSERT INTO ElectronicsType(3, "portable music player");
INSERT INTO ElectronicsType(4, "stereo");
INSERT INTO ElectronicsType(5, "media player");

# populate the FurnitureType table
INSERT INTO FurnitureType(0, "other");
INSERT INTO FurnitureType(1, "couch");
INSERT INTO FurnitureType(2, "bed");
INSERT INTO FurnitureType(3, "chair");
INSERT INTO FurnitureType(4, "table");
INSERT INTO FurnitureType(5, "futon");
INSERT INTO FurnitureType(6, "shelves");

# populate MeetupType table
INSERT INTO MeetupType(0, "other");
INSERT INTO MeetupType(1, "study group");
INSERT INTO MeetupType(2, "social");


