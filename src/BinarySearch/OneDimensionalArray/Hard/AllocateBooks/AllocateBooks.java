package BinarySearch.OneDimensionalArray.Hard.AllocateBooks;

import java.util.Arrays;

class AllocateBooks {

    // Brute Force AllocateBooks (Linear Search)
    public int findPagesBruteForce(int[] arr, int k) {
        int n = arr.length;
        if (k > n) return -1; // impossible case

        int low = Arrays.stream(arr).max().getAsInt(); // start from largest book
        int high = Arrays.stream(arr).sum();           // sum of all pages

        // Linear search from max(arr) to sum(arr)
        for (int maxPagesAllowed = low; maxPagesAllowed <= high; maxPagesAllowed++) {
            int studentsRequired = countStudents(arr, maxPagesAllowed);
            if (studentsRequired <= k) {
                return maxPagesAllowed; // first valid allocation
            }
        }

        return -1; // should not happen normally
    }

    // Binary Search AllocateBooks
    public int findPages(int[] arr, int k) {
        int n = arr.length;
        if (k > n) return -1; // impossible case

        int low = Arrays.stream(arr).max().getAsInt(); // start from largest book
        int high = Arrays.stream(arr).sum();           // sum of all pages

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int noOfStu = countStudents(arr, mid);

            if (noOfStu <= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // Helper function used in both approaches
    private int countStudents(int[] pages, int maxPages) {
        int students = 1; // Because it include the last books distribution
        long pagesSum = 0;

        for (int page : pages) {
            if (pagesSum + page <= maxPages) {
                pagesSum += page;
            } else {
                students++;
                pagesSum = page;
            }
        }
        return students;
    }

    // ✅ Main for testing
    public static void main(String[] args) {
        AllocateBooks sol = new AllocateBooks();
        int[] books = {12, 34, 67, 90};
        int students = 2;

        System.out.println("Brute Force Answer: " + sol.findPagesBruteForce(books, students));
        System.out.println("Binary Search Answer: " + sol.findPages(books, students));
    }
}
