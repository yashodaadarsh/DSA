
# BS-18. Allocate Books or Book Allocation | Hard Binary Search


## Problem Description

You are given an **array containing the number of pages of `n` books** (e.g., `` for five books) and `m` **number of students** (e.g., `4` students). Your task is to **allocate these books among the `m` students**.

### Conditions for Book Allocation

There are **three specific conditions** that must be kept in mind during allocation:

1.  **Each student must get at least one book**. You cannot leave any student without a book.
2.  **Each book should be allocated to only one student**. A single book cannot be shared among multiple students.
3.  **Book allocation should be done in a contiguous manner**. If a student receives multiple books, those books must be adjacent in the original array. For example, a student can get `25, 46`, but not `25, 28` if `46` is between them.

### Goal

The primary objective is to **allocate books to `m` students such that the maximum number of pages assigned to any particular student is the minimum possible**. If allocation is not possible under the given conditions, return `-1`.

For instance, if the maximum pages assigned to students in different allocations were `73`, `77`, `74`, and `71`, the **minimum of these maximums is `71`**, which would be the answer.

## Impossible Case

The problem **returns `-1` if allocation is not possible**. This occurs when the **number of students (`m`) is greater than the number of books (`n`)**. In such a scenario, it's impossible to ensure that each student gets at least one book, as there aren't enough books to go around.

## Solution Approach

The problem structure, involving finding the "Max of Min" (or "Min of Max"), strongly suggests the application of **binary search**.

### 1. Linear Search (Brute Force - Explained for understanding, but not optimal)

The intuition is to **iterate through possible values for the maximum number of pages a student can be assigned**.

*   **Range of Possible Answers:**
    *   **Lowest possible answer:** The **maximum number of pages of a single book** in the array. This ensures that at least one student can hold even the largest book.
    *   **Highest possible answer:** The **sum of all pages** in the array. This represents the case where all books are allocated to a single student (e.g., if there's only one student).
    *   So, the search range is from `max(array)` to `sum(array)`.

*   **`countStudents` Helper Function:**
    *   This function takes the array of book pages and a `maxPagesAllowed` value as input.
    *   It simulates the allocation process: It iterates through the books, assigning them to the current student as long as the total pages for that student do not exceed `maxPagesAllowed`.
    *   If adding the next book would exceed `maxPagesAllowed`, a new student is assigned, and that book is given to the new student.
    *   The function **returns the total number of students required** to distribute all books given the `maxPagesAllowed` barrier.

*   **Linear Search Logic:**
    *   Start with `max(array)` as the potential `maxPagesAllowed`.
    *   Increment this value linearly up to `sum(array)`.
    *   For each `maxPagesAllowed` value, call `countStudents`.
    *   The **first `maxPagesAllowed` value for which `countStudents` returns exactly `m` students is the answer**.

*   **Time Complexity (Linear Search):** O((Sum - Max + 1) * N). This is inefficient for large ranges.

### 2. Binary Search (Optimized Approach)

Binary search is applied over the **range of possible answers** (from `max(array)` to `sum(array)`) to efficiently find the minimum possible maximum pages.

*   **Why Binary Search?**
    *   Linear search is too slow.
    *   The problem fits the "Max of Min" pattern.
    *   The `countStudents` function exhibits a monotonic property: as `maxPagesAllowed` increases, the number of students required either stays the same or decreases. This allows for efficient elimination of half the search space.

*   **Search Space Initialization:**
    *   `low = max(array)` (e.g., 49 in the example).
    *   `high = sum(array)` (e.g., 172 in the example).

*   **Binary Search Logic:**
    *   While `low <= high` (or `low < high` depending on implementation):
        *   Calculate `mid = low + (high - low) / 2`.
        *   Call `countStudents(array, mid)` to determine `studentsRequired`.
        *   **If `studentsRequired > m` (too many students are needed):** This means the `mid` (maximum pages allowed) is too small, so we need to allow more pages per student. **Eliminate the left half** by setting `low = mid + 1`.
        *   **If `studentsRequired <= m` (sufficient or fewer students are needed):** This means `mid` is a possible answer, or we can potentially do even better (find a smaller maximum). We try to **eliminate the right half** by setting `high = mid - 1`. *It's common to store `mid` as a potential answer here if an `answer` variable is used, but the explanation suggests simply returning `low` at the end.*

*   **Termination and Result:**
    *   The loop terminates when `low` crosses `high`.
    *   The **final value of `low` will be the answer**. This works because `low` will point to the smallest possible maximum page limit that allows allocation to `m` or fewer students, and `high` will point to a value just below it that resulted in too many students.

*   **Time Complexity (Binary Search):** O(log(Sum - Max + 1) * N). This is significantly more efficient than linear search.
*   **Space Complexity:** O(1) as no extra space is used.

```