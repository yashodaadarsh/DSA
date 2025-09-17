package POTD.LeetCode.LC_2353_DesignAFoodRatingSystem;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class FoodItem {
    public String food;
    public String cuisine;
    public int rating;

    public FoodItem(String food, String cuisine, int rating) {
        this.food = food;
        this.cuisine = cuisine;
        this.rating = rating;
    }
}

class DesignAFoodRatingSystem {

    private Map<String, FoodItem> foodList;
    private Map<String, PriorityQueue<FoodItem>> cuisineList;

    public DesignAFoodRatingSystem(String[] foods, String[] cuisines, int[] ratings) {
        foodList = new HashMap<>();
        cuisineList = new HashMap<>();
        int n = foods.length;
        for (int i = 0; i < n; i++) {
            FoodItem foodItem = new FoodItem(foods[i], cuisines[i], ratings[i]);
            foodList.put(foods[i], foodItem);
            if (cuisineList.containsKey(cuisines[i])) {
                PriorityQueue<FoodItem> list = cuisineList.get(cuisines[i]);
                list.add(foodItem);
            } else {
                Comparator<FoodItem> comparator = new Comparator<FoodItem>() {
                    @Override
                    public int compare(FoodItem a, FoodItem b) {
                        if (a.rating == b.rating) {
                            if (a.food.compareTo(b.food) < 0) {
                                return -1;
                            } else
                                return 1;
                        }
                        return b.rating - a.rating;
                    }
                };
                PriorityQueue<FoodItem> list = new PriorityQueue<>(comparator);
                list.add(foodItem);
                cuisineList.put(cuisines[i], list);
            }
        }
    }

    public void changeRating(String food, int newRating) {
        // 1. Get the food item to be updated
        FoodItem foodToUpdate = foodList.get(food);

        // 2. We need to create a new object with the updated rating, because 
        // simply changing the rating of the old object won't trigger the PriorityQueue to re-sort.
        // However, a more robust solution would be to create a "dirty" flag or a map to track invalid entries.
        // Let's create a new FoodItem and rely on the highestRated method to handle "stale" entries.
        FoodItem updatedFood = new FoodItem(food, foodToUpdate.cuisine, newRating);

        // 3. Update the foodList with the new object
        foodList.put(food, updatedFood);

        // 4. Get the correct PriorityQueue for the cuisine
        PriorityQueue<FoodItem> cuisineQueue = cuisineList.get(foodToUpdate.cuisine);

        // 5. Add the new FoodItem to the PriorityQueue. 
        // This will correctly place it based on the new rating.
        cuisineQueue.add(updatedFood);

        // NOTE: The previous `foodToUpdate` object (with the old rating) is still in the queue.
        // We will handle this in the `highestRated` method.
    }

    public String highestRated(String cuisine) {
        PriorityQueue<FoodItem> pq = cuisineList.get(cuisine);

        // 1. We must handle "stale" entries. A stale entry is an old version of a food item 
        // that is no longer the current version in the foodList map.
        while (true) {
            FoodItem highest = pq.peek();

            // 2. Compare the item from the queue with the current version from the map.
            // If they have the same rating, it's the valid, highest-rated food.
            if (foodList.get(highest.food) == highest) {
                return highest.food;
            }

            // 3. If the item from the queue is not the current version (meaning its rating was changed),
            // remove it from the queue and check the next item.
            pq.poll();
        }
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */