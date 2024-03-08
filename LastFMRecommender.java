/***********************************************************
* Name: Monica Nguyen
* Date: 16 Jun 2023
* Course Code: CS401
* Description: This is used to run all the method being indicated
* in the program prompt the answer that being asked in the 
* assignment.
*
***********************************************************/

import java.io.*;
import java.util.*;

public class LastFMRecommender {
    private Map<Integer, String> artistNames;
    private Map<Integer, Set<Integer>> userFriends;
    private Map<Integer, Map<Integer, Integer>> userArtists;

    public LastFMRecommender() {
        artistNames = new HashMap<>();
        userFriends = new HashMap<>();
        userArtists = new HashMap<>();
    }

    public void loadData(String artistsFile, String userArtistsFile, String userFriendsFile) {
        loadArtists(artistsFile);
        loadUserArtists(userArtistsFile);
        loadUserFriends(userFriendsFile);
    }

    private void loadArtists(String artistsFile) {
        try (Scanner scanner = new Scanner(new File(artistsFile))) {
            //First line is the tag name of the column
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length >= 4) {
                    try {
                        int artistId = Integer.parseInt(parts[0]);
                        String artistName = parts[1];
                        artistNames.put(artistId, artistName);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid artist ID: " + parts[0]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Artists file not found: " + artistsFile);
        }
    }

    private void loadUserArtists(String userArtistsFile) {
        try (Scanner scanner = new Scanner(new File(userArtistsFile))) {
            //First line is the tag name of the column
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                
                if (parts.length >= 3) {
                    try {
                        int userId = Integer.parseInt(parts[0]);
                        int artistId = Integer.parseInt(parts[1]);
                        int weight = Integer.parseInt(parts[2]);
                        userArtists.putIfAbsent(userId, new HashMap<>());
                        userArtists.get(userId).put(artistId, weight);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid user or artist ID: " + parts[0] + ", " + parts[1]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User artists file not found: " + userArtistsFile);
        }
    }

    private void loadUserFriends(String userFriendsFile) {
        try (Scanner scanner = new Scanner(new File(userFriendsFile))) {
            //First line is the tag name of the column
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    try {
                        int userId = Integer.parseInt(parts[0]);
                        int friendId = Integer.parseInt(parts[1]);
                        userFriends.putIfAbsent(userId, new HashSet<>());
                        userFriends.get(userId).add(friendId);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid user or friend ID: " + parts[0] + ", " + parts[1]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User friends file not found: " + userFriendsFile);
        }
    }

    public void listFriends(int user) {
    if (user <= 0 || !userFriends.containsKey(user)) {
        throw new IndexOutOfBoundsException("Invalid user ID: " + user);
    }

    Set<Integer> friends = userFriends.get(user);
    if (friends != null) {
        System.out.print("Friends of user " + user + ": ");
        boolean isFirst = true;
        for (int friendId : friends) {
            if (!isFirst) {
                System.out.print(", ");
            }
            System.out.print(friendId);
            isFirst = false;
        }
        System.out.println();
    } else {
        System.out.println("User " + user + " has no friends.");
    }
}

    public void commonFriends(int user1, int user2) {
        Set<Integer> friends1 = userFriends.get(user1);
        Set<Integer> friends2 = userFriends.get(user2);
        if (friends1 == null || friends2 == null) {
            System.out.println("No common friends found between user " + user1 + " and user " + user2);
        } else {
            Set<Integer> commonFriends = new HashSet<>(friends1);
            commonFriends.retainAll(friends2);
            if (commonFriends.isEmpty()) {
                System.out.println("No common friends found between user " + user1 + " and user " + user2);
            } else {
                System.out.println("Common friends of user " + user1 + " and user " + user2 + ": " + commonFriends);
            }
        }
    }

    public void listArtists(int user1, int user2) {
        Map<Integer, Integer> artists1 = userArtists.get(user1);
        Map<Integer, Integer> artists2 = userArtists.get(user2);
        if (artists1 == null || artists2 == null) {
            System.out.println("No artists found for user " + user1 + " or user " + user2);
        } else {
            Set<Integer> commonArtists = new HashSet<>(artists1.keySet());
            commonArtists.retainAll(artists2.keySet());
            if (commonArtists.isEmpty()) {
                System.out.println("No common artists found between user " + user1 + " and user " + user2);
            } else {
                System.out.println("Artists listened by both user " + user1 + " and user " + user2 + ":");
                for (int artistId : commonArtists) {
                    System.out.println(artistNames.get(artistId));
                }
            }
        }
    }

    public void listTop10() {
        Map<Integer, Integer> artistListeningCount = new HashMap<>();
        for (Map<Integer, Integer> userArtist : userArtists.values()) {
            for (Map.Entry<Integer, Integer> entry : userArtist.entrySet()) {
                int artistId = entry.getKey();
                int listeningCount = entry.getValue();
                artistListeningCount.put(artistId, artistListeningCount.getOrDefault(artistId, 0) + listeningCount);
            }
        }

        List<Map.Entry<Integer, Integer>> topArtists = new ArrayList<>(artistListeningCount.entrySet());
        topArtists.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("Top 10 most popular artists:");
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : topArtists) {
            int artistId = entry.getKey();
            int listeningCount = entry.getValue();
            System.out.println(artistNames.get(artistId) + " (" + listeningCount + " listens)");
            count++;
            if (count >= 10) {
                break;
            }
        }
    }

      public void recommend10(int user) {
        Map<Integer, Integer> artistListeningCount = new HashMap<>();
        Set<Integer> friends = userFriends.get(user);
        if (friends != null) {
            for (int friendId : friends) {
                Map<Integer, Integer> friendArtists = userArtists.get(friendId);
                if (friendArtists != null) {
                    for (Map.Entry<Integer, Integer> entry : friendArtists.entrySet()) {
                        int artistId = entry.getKey();
                        int listeningCount = entry.getValue();
                        artistListeningCount.put(artistId, artistListeningCount.getOrDefault(artistId, 0) + listeningCount);
                    }
                }
            }
        }

        List<Map.Entry<Integer, Integer>> recommendedArtists = new ArrayList<>(artistListeningCount.entrySet());
        recommendedArtists.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("Recommended artists for user " + user + ":");
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : recommendedArtists) {
            int artistId = entry.getKey();
            int listeningCount = entry.getValue();
            System.out.println(artistNames.get(artistId) + " (" + listeningCount + " listens)");
            count++;
            if (count >= 10) {
                break;
            }
        }
    }
}