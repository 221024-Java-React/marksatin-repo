-- Lab 16 --

CREATE TABLE songs(
	id int PRIMARY KEY,
	name varchar(64) NOT NULL,
	artist varchar(64) NOT NULL,
	album varchar(256) NOT NULL,
	genre varchar(64) NOT NULL,
	duration decimal,
	fav boolean
);

INSERT INTO songs VALUES
	(1, 'Heart-Shaped Box', 'Nirvana', 'In Utero', 'Rock', 4.68, false),
	(2, 'Smells Like Teen Spirit', 'Nirvana', 'Rock', 'Nevermind', 3.65, false),
	(3, 'Higher', 'Creed', 'Human Clay', 'Rock', 5.43, false),
	(4, 'Hero', 'Skillet', 'Awake', 'Rock', 3.12, false),
	(5, 'Hotel California', 'Eagles', 'Hotel California', 'Rock', 3.47, false),
	(6, 'Austin', 'Blake Shelton', 'Blake Shelton', 'Country', 3.83, false),
	(7, 'Dirt Road Anthem', 'Jason Aldean', 'My Kinda Party', 'Country', 3.81, false),
	(8, 'Springsteen', 'Eric Church', 'Chief', 'Country', 4.38, false),
	(9, 'Watching Airplanes', 'Gary Allen', 'Living Hard', 'Country', 4.05, false),
	(10, 'Hey Girl', 'Billy Currington', 'We Are Tonight', 'Country', 3.35, false),
	(11, 'A Milli', 'Lil Wayne', 'Tha Carter III', 'Hip Hop', 3.70, false),
	(12, 'Over', 'Drake', 'Thank Me Later', 'Hip Hop', 3.90, false),
	(13, 'No Interruption', 'Hoodie Allen', 'All American', 'Hip Hop', 3.58, false),
	(14, 'It Girl', 'Jason Derulo', 'Future History', 'Hip Hop', 3.20, false),
	(15, 'Frick Park Market', 'Mac Miller', 'Blue Slide Park', 'Hip Hop', 3.30, false);
	
SELECT * FROM songs;