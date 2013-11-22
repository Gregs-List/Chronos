/* Register a new user */
INSERT INTO Users(email, pword, fullName) VALUES ($email, $pword, $fullName);
/* Update user info */
UPDATE Users SET /*col_name1={expr1|DEFAULT} [, col_name2={expr2|DEFAULT}] ... [WHERE where_condition]*/;

/* Return user info */
SELECT * FROM Users WHERE userID = /*userID*/;

/* View user listings */
SELECT * FROM Listings WHERE userID = /*userID*/;

/* Insert listing into miscellaneous */
INSERT INTO Listings(userID, title, category, description) VALUES (/*userID, title, category, description) */;
INSERT INTO Miscellaneous(listingID, itemName) VALUES (mysql_insert_id(), /*val2/);

/*Insert listing into any category*/
INSERT INTO Listings(userID, title, category, description) VALUES (/*userID, title, category, description) */;
INSERT INTO /*Category*/(listingID, /*col2,...*/) VALUES (mysql_insert_id(), /*val2,...*/);
