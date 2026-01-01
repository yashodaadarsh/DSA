# Food Ratings System

This project implements a system to manage and query food ratings. It allows you to initialize a list of food items with their cuisines and ratings, update a food's rating, and find the highest-rated food for a given cuisine.

---

## 🚀 Getting Started

The core functionality is encapsulated within the **`FoodRatings`** class. This class uses a combination of data structures to efficiently handle rating changes and queries.

### Prerequisites

* Java Development Kit (JDK) 8 or higher
* Basic understanding of Java data structures like **`HashMap`** and **`PriorityQueue`**.

---

## 🛠️ Implementation Details

The `FoodRatings` class uses two primary data structures:

* **`foodList`**: A `HashMap<String, FoodItem>` that maps a food's name to its `FoodItem` object. This provides a quick way to retrieve a specific food item's data.

* **`cuisineList`**: A `HashMap<String, PriorityQueue<FoodItem>>` that maps a cuisine to a **priority queue** of `FoodItem` objects. Each priority queue is sorted based on ratings (in descending order) and then by food name (in ascending order) for tie-breaking.

### Class: `FoodItem`

This is a simple helper class that holds a food's `name`, `cuisine`, and `rating`. It's a fundamental building block for the system.

### Constructor: `FoodRatings(String[] foods, String[] cuisines, int[] ratings)`

This constructor initializes the data structures. It iterates through the input arrays to:
1.  Create a new `FoodItem` for each food.
2.  Store each `FoodItem` in the `foodList` map for quick access.
3.  Add each `FoodItem` to the correct `PriorityQueue` within the `cuisineList`. If a cuisine's queue doesn't exist, it creates a new one with a custom **`Comparator`** to define the sorting logic.

---

### Method: `changeRating(String food, int newRating)`

This method updates a food's rating. Instead of directly modifying the `FoodItem` object inside the priority queue, which doesn't trigger a re-sort, this method employs a strategy called **lazy deletion**:

1.  It retrieves the `FoodItem` from the `foodList` map.
2.  It creates a **new** `FoodItem` object with the updated rating.
3.  It updates the `foodList` map with this new `FoodItem`.
4.  It adds the new `FoodItem` to the appropriate `PriorityQueue` in `cuisineList`.

The old `FoodItem` object with the outdated rating remains in the priority queue. This is a crucial optimization, as re-sorting a queue is more expensive than a simple insertion. The "stale" entries are handled efficiently in the `highestRated` method.

---

### Method: `highestRated(String cuisine)`

This method finds the highest-rated food for a given cuisine. It accesses the corresponding `PriorityQueue` and implements the logic to handle "stale" entries left by `changeRating`:

1.  It repeatedly inspects the top element of the priority queue using `pq.peek()`.
2.  It compares this element with the current `FoodItem` object stored in `foodList` for the same food name.
3.  If the objects are the same (which implies the ratings are current), it returns the food name.
4.  If the objects are different, it means the one in the queue is an old, "stale" entry. The method then removes this stale entry using `pq.poll()` and checks the next element.

This loop continues until it finds a valid, up-to-date food item at the top of the queue, ensuring the returned result is always accurate.