Ultimate Book Collection Service

This project implements a service to retrieve the top 10 books from a dataset containing the best 100 books for each year from 1980 to 2023, based on the Goodreads rating. The dataset is provided in CSV format.

API Endpoint
Endpoint: /api/top10
HTTP Method: GET
Request Parameters
year (optional): Filter books by the specified publication year.
column (required): Name of the field by which the data should be sorted. Possible values: book, author, numPages, publicationDate, rating, numberOfVoters.
sort (required): Sorting order, either ASC (ascending) or DESC (descending).

Sample Request
GET http://localhost:8080/api/top10?year=2010&column=author&sort=ASC

Sample Response
{
   "id":2507,
   "book":"Looking for Alaska",
   "series":"",
   "releaseNumber":"",
   "author":"John Green",
   "description":"Before.\n Miles “Pudge” Halter is done with his safe life at home. His whole life has been one big non-event, and his obsession with famous last words has only made him crave “the Great Perhaps” even more (Francois Rabelais, poet). He heads off to the sometimes crazy and anything-but-boring world of Culver Creek Boarding School, and his life becomes the opposite of safe. Because down the hall is Alaska Young. The gorgeous, clever, funny, sexy, self-destructive, screwed up, and utterly fascinating Alaska Young. She is an event unto herself. She pulls Pudge into her world, launches him into the Great Perhaps, and steals his heart. Then. . . . \nAfter.\n Nothing is ever the same.",
   "numPages":221,
   "format":"Paperback",
   "genres":[
      "Young Adult",
      "Fiction",
      "Contemporary",
      "Romance",
      "Realistic Fiction",
      "Coming Of Age",
      "Teen"
   ],
   "publicationDate":"2005-03-03",
   "rating":3.97,
   "numberOfVoters":1571933
}

Building and Running the Service

To build and run the service, follow these instructions:

1. Clone the repository from GitHub. 
2. Open a terminal and navigate to the project directory.
3. Execute the following command to build the project: 
./gradlew build

Run the service using: 
./gradlew bootRun

Run the Docker container:

docker run -p 8080:8080 ultimate-book-service

Now the service should be accessible at http://localhost:8080
