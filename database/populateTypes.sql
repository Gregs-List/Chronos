# mysql -u root -p --local-infile GregsList
use GregsList;

# populate the ConditionLookup table
INSERT INTO ConditionLookup(conditionID, itemCondition) VALUES (0, 'unspecified');
INSERT INTO ConditionLookup(conditionID, itemCondition) VALUES (1, 'new');
INSERT INTO ConditionLookup(conditionID, itemCondition) VALUES (2, 'excellent');
INSERT INTO ConditionLookup(conditionID, itemCondition) VALUES (3, 'light wear');
INSERT INTO ConditionLookup(conditionID, itemCondition) VALUES (4, 'normal wear');
INSERT INTO ConditionLookup(conditionID, itemCondition) VALUES (5, 'damaged');

# populate the BikeType table
INSERT INTO BikeType(bikeTypeID, bikeType) VALUES (0, 'other');
INSERT INTO BikeType(bikeTypeID, bikeType) VALUES (1, 'road');
INSERT INTO BikeType(bikeTypeID, bikeType) VALUES (2, 'city');
INSERT INTO BikeType(bikeTypeID, bikeType) VALUES (3, 'mountain');
INSERT INTO BikeType(bikeTypeID, bikeType) VALUES (4, 'cruiser');
INSERT INTO BikeType(bikeTypeID, bikeType) VALUES (5, 'hybrid');
INSERT INTO BikeType(bikeTypeID, bikeType) VALUES (6, 'unicycle');
INSERT INTO BikeType(bikeTypeID, bikeType) VALUES (7, 'electric');

# populate the BookType table
INSERT INTO BookType(bookTypeID, bookType) VALUES (0, 'other');
INSERT INTO BookType(bookTypeID, bookType) VALUES (1, 'textbook');
INSERT INTO BookType(bookTypeID, bookType) VALUES (2, 'literature');
INSERT INTO BookType(bookTypeID, bookType) VALUES (3, 'poetry');
INSERT INTO BookType(bookTypeID, bookType) VALUES (4, 'reference');
INSERT INTO BookType(bookTypeID, bookType) VALUES (5, 'nonfiction');
INSERT INTO BookType(bookTypeID, bookType) VALUES (6, 'graphic novel');

# populate the ElectronicsType table
INSERT INTO ElectronicsType(electronicsTypeID, electronicsType) VALUES (0, 'other');
INSERT INTO ElectronicsType(electronicsTypeID, electronicsType) VALUES (1, 'TV');
INSERT INTO ElectronicsType(electronicsTypeID, electronicsType) VALUES (2, 'computer');
INSERT INTO ElectronicsType(electronicsTypeID, electronicsType) VALUES (3, 'portable music player');
INSERT INTO ElectronicsType(electronicsTypeID, electronicsType) VALUES (4, 'stereo');
INSERT INTO ElectronicsType(electronicsTypeID, electronicsType) VALUES (5, 'media player');

# populate the FurnitureType table
INSERT INTO FurnitureType(furnitureTypeID, furnitureType) VALUES (0, 'other');
INSERT INTO FurnitureType(furnitureTypeID, furnitureType) VALUES (1, 'couch');
INSERT INTO FurnitureType(furnitureTypeID, furnitureType) VALUES (2, 'bed');
INSERT INTO FurnitureType(furnitureTypeID, furnitureType) VALUES (3, 'chair');
INSERT INTO FurnitureType(furnitureTypeID, furnitureType) VALUES (4, 'table');
INSERT INTO FurnitureType(furnitureTypeID, furnitureType) VALUES (5, 'futon');
INSERT INTO FurnitureType(furnitureTypeID, furnitureType) VALUES (6, 'shelves');

# populate MeetupType table
INSERT INTO MeetupType(meetupTypeID, meetupType) VALUES (0, 'other');
INSERT INTO MeetupType(meetupTypeID, meetupType) VALUES (1, 'study group');
INSERT INTO MeetupType(meetupTypeID, meetupType) VALUES (2, 'social');
INSERT INTO MeetupType(meetupTypeID, meetupType) VALUES (3, 'group meeting');

