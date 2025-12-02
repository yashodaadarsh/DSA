
# **LeetCode 2141 — Maximum Running Time of N Computers**

## 📝 **Problem Summary**

You're given:

* An integer **n** → number of computers.
* An integer array **batteries**, where `batteries[i]` = minutes battery *i* can supply.

Each computer initially gets **at most one battery**.
You may:

* Swap batteries between computers **at any time**.
* Insert **fresh** or **previously used** batteries instantly.
* Batteries **cannot** be recharged.

🔹 **Goal**: Run all **n** computers *simultaneously* for the **maximum number of minutes**.

---


# ✔️ **Approach 1 — Greedy + Sorting (Optimized Mathematical Strategy)**

### 🔍 **Idea**

Sort the batteries.
Some batteries will be too small to contribute significantly, so we treat them as **extra energy** (`extra`).
Larger batteries contribute to forming a balanced distribution.

We try to *even out* all the largest **n** batteries to run for the maximum possible time.

---

## ⚡ Algorithm Steps

### **1. Sort all batteries**

This helps us separate:

* The **largest n batteries** → primary contributors
* The **smaller leftovers** → extra energy buffer

### **2. Compute `extra` energy**

Extra energy = sum of all batteries not in the top `n`.

### **3. Leveling Process**

We iteratively try to “level up” the running time of the largest n batteries:

* For each battery index `i`
* Check if extra energy can raise `batteries[i]` to `batteries[i+1]`
* If yes → spend the energy
* If not → return the current achievable runtime

### **4. Final Answer**

After leveling all except the largest one:

[
\text{result} = \text{largest battery value} + \frac{\text{extra}}{n}
]

---

## 👍 **Time Complexity**

* Sorting → `O(m log m)`
* Leveling → `O(n)`

Where **m = batteries.length**

---

## 🟩 **Code — Approach 1 (C++)**

```cpp
class Solution {
public:
    long long maxRunTime(int n, vector<int>& batteries) {
        long long sum = accumulate(batteries.begin(), batteries.end(), 0LL);
        sort(batteries.begin(), batteries.end());

        long long extra = 0;
        for (int i = 0; i < batteries.size() - n; i++) {
            extra += batteries[i];
        }

        int base = batteries.size() - n;

        for (int i = 0; i < n - 1; i++) {
            long long need = (long long)(i + 1) *
                             (batteries[base + i + 1] - batteries[base + i]);
            if (extra >= need) {
                extra -= need;
            } else {
                return batteries[base + i] + extra / (i + 1);
            }
        }

        return batteries.back() + extra / n;
    }
};
```

---

# ✔️ **Approach 2 — Binary Search + Feasibility Check**

### 🔍 **Idea**

Binary search the **maximum time T** such that all n computers can run T minutes.

We check if:

[
\sum \min(b_i, T) \ge n \cdot T
]

If true → T is feasible → search higher.
If false → search lower.

---

## ⚡ Algorithm Steps

### **1. Compute total power**

[
\text{max possible time} = \left\lfloor \frac{\text{sum of batteries}}{n} \right\rfloor
]

### **2. Binary search from 1 to max possible time**

For each `mid`:

* Cap each battery at `mid`
* Sum contributions
* Check feasibility

### **3. Final Answer**

The highest feasible `mid`.

---

## 👍 **Time Complexity**

* Feasibility check → `O(m)`
* Binary search → `O(log(sum/n))`

Total:
[
O(m \log(sum))
]

---

## 🟦 **Code — Approach 2 (Java)**

```java
class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for (int b : batteries) sum += b;

        long st = 1;
        long end = sum / n;

        while (st <= end) {
            long mid = st + (end - st) / 2;

            if (check(mid, batteries, n)) {
                st = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end;
    }

    boolean check(long target, int[] batteries, int n) {
        long total = 0;
        for (int b : batteries) {
            total += Math.min((long)b, target);
            if (total >= (long)n * target)
                return true;
        }
        return total >= (long)n * target;
    }
}
```

---

# 🆚 **Approach Comparison**

| Feature        | Approach 1 (Greedy)      | Approach 2 (Binary Search) |
| -------------- | ------------------------ | -------------------------- |
| Complexity     | `O(m log m)`             | `O(m log(sum))`            |
| Implementation | Harder but very fast     | Simpler & intuitive        |
| Logic          | Energy leveling          | Feasibility search         |
| Best Use Case  | When sorting fits nicely | Easier correctness proof   |

---

# ✅ **Final Notes**

Both approaches are efficient and accepted for LeetCode 2141.

* **Approach 1** is optimal in practice due to fewer iterations.
* **Approach 2** is easier to understand and implement.

---

