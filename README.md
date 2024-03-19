# Social-Network_recommend_Graphs
This project involves designing and implementing a music recommendation engine based on social network data from last.fm. The system analyzes user interactions and listening habits to provide personalized music recommendations. The dataset provided contains social networking, tagging, and music artist listening information from a set of 2K users.

## Files Included

1. **LastFMRecommender.java**: This file contains the implementation of the LastFMRecommender class, which processes the dataset and provides various functionalities to interact with the data. The class includes methods such as `listFriends`, `commonFriends`, `listArtists`, `listTop10`, and `recommend10` as specified in the functional requirements.

2. **LastFMRecommenderTest.java**: This file contains JUnit test cases for the LastFMRecommender class. It includes sample test cases to ensure that the implemented functionalities work as expected. The test cases cover scenarios such as listing friends, finding common friends, listing artists, listing top artists, and recommending artists.

## Functionalities Provided by LastFMRecommender Class

The LastFMRecommender class provides the following public functionalities:

- **listFriends(int user)**: Prints the list of friends of the given user.
- **commonFriends(int user1, int user2)**: Prints the common friends of user1 with user2.
- **listArtists(int user1, int user2)**: Prints the list of artists listened to by both users.
- **listTop10()**: Prints the list of top 10 most popular artists listened to by all users.
- **recommend10(int user)**: Recommends the 10 most popular artists listened to by the given user and his/her friends.

## How to Run

To run the LastFMRecommender class and test its functionalities, follow these steps:

1. Compile both LastFMRecommender.java and LastFMRecommenderTest.java files.
2. Run the LastFMRecommenderTest class to execute the provided test cases.
3. Ensure that the dataset files (artists.dat, user_artists.dat, user_friends.dat) are available in the appropriate directory for the LastFMRecommender class to process.
