# TaskManager

A compact, interview-friendly README that explains a working solution for a **Task Manager** problem where you must support the following operations:

* Construct from an initial list of tasks
* `add(userId, taskId, priority)` — add a new task
* `edit(taskId, newPriority)` — change priority of an existing task
* `rmv(taskId)` — remove a task
* `execTop()` — execute (remove & return) the highest-priority task's **userId**

The solution uses a **max-heap (PriorityQueue)** for quick access to the highest-priority task and a **lazy-deletion** strategy to support edits/removals efficiently in Java (because `PriorityQueue` doesn’t support decrease-key / direct update).

---

## Problem statement (concise)

Implement `TaskManager` with these operations:

* `TaskManager(List<List<Integer>> tasks)` — `tasks` is a list of `[userId, taskId, priority]`
* `void add(int userId, int taskId, int priority)`
* `void edit(int taskId, int newPriority)`
* `void rmv(int taskId)`
* `int execTop()` — removes and returns the `userId` of the current highest-priority task, or `-1` if none

Priority ordering: larger `priority` is higher. For equal priorities, larger `taskId` wins.

---

## Key observations

1. Java's `PriorityQueue` stores references and does **not** reheapify when you mutate elements inside it. So updating a `Task` object that already sits in the PQ will not change the queue order reliably.
2. Removing an arbitrary element from a `PriorityQueue` is `O(n)`, which is too slow for many operations.
3. A common pattern is **lazy deletion**: when we "update" or "remove" a task we add a marker (a small record) that indicates that the heap entry is stale. When a stale entry reaches the top, we pop and discard it.

---

## Data structures used

* `PriorityQueue<Task> pq` — max-heap, comparator: `priority` desc, then `taskId` desc
* `Map<Integer, Task> taskMap` — maps `taskId` → current `Task` object (the canonical/latest copy)
* `Set<String> oldTask` — set of stale keys for lazy deletion. Each stale key is `taskId + "#" + priority` (string composite key)

Using the composite key ensures exact identification of which heap entry is stale (because multiple versions of the same `taskId` can exist in the heap after edits).

---

## Step-by-step approach

### 1. Initialization (`TaskManager(List<List<Integer>> tasks)`)

* Create `pq`, `taskMap`, `oldTask`.
* For each `task` in `tasks` create a `Task` object and `offer` it to `pq` and insert it in `taskMap` keyed by `taskId`.

### 2. `add(userId, taskId, priority)`

* Create a `Task` instance with the provided values.
* `pq.offer(task)` and `taskMap.put(taskId, task)`.

### 3. `edit(taskId, newPriority)`

* Look up current `Task old = taskMap.get(taskId)`.
* If `old == null` → nothing to edit (task already removed).
* If `old.priority == newPriority` → nothing to do.
* Otherwise:

    * Add `old.taskId + "#" + old.priority` to `oldTask` set (mark old heap entry stale).
    * Create new `Task updated = new Task(old.userId, taskId, newPriority)`
    * `pq.offer(updated)` and `taskMap.put(taskId, updated)`

### 4. `rmv(taskId)`

* Look up `Task t = taskMap.get(taskId)`.
* If `t != null` add `t.taskId + "#" + t.priority` to `oldTask` and **remove the mapping** from `taskMap` (so further `edit` calls won't resurrect it accidentally).

### 5. `execTop()`

* While `pq` not empty:

    * Let `highest = pq.peek()` and `key = highest.taskId + "#" + highest.priority`.
    * If `oldTask.contains(key)` → `pq.poll()` and `oldTask.remove(key)` (discard stale).
    * Else if `taskMap.get(highest.taskId) == null` → the canonical was removed; discard `pq.poll()` (no need to remove `oldTask` because rmv already added the stale key when removing).
    * Else if `highest.priority != taskMap.get(highest.taskId).priority` → this heap entry is an older-version copy; `pq.poll()` and continue.
    * Else → this is the valid top entry: `pq.poll()` and return `highest.userId`.
* If loop finishes, return `-1`.

---

## Recommended clean & safe implementation (Java)

```java
class TaskManager {
    class Task {
        int userId, taskId, priority;
        public Task(int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }
    }

    private PriorityQueue<Task> pq;
    private Map<Integer, Task> taskMap;
    private Set<String> oldTask; // stores "taskId#priority"

    public TaskManager(List<List<Integer>> tasks) {
        pq = new PriorityQueue<>((a, b) -> {
            if (a.priority == b.priority) return b.taskId - a.taskId;
            return b.priority - a.priority;
        });
        taskMap = new HashMap<>();
        oldTask = new HashSet<>();

        for (List<Integer> t : tasks) {
            Task task = new Task(t.get(0), t.get(1), t.get(2));
            pq.offer(task);
            taskMap.put(t.get(1), task);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        pq.offer(task);
        taskMap.put(taskId, task);
    }

    public void edit(int taskId, int newPriority) {
        Task old = taskMap.get(taskId);
        if (old == null || old.priority == newPriority) return;
        oldTask.add(old.taskId + "#" + old.priority);
        Task updated = new Task(old.userId, taskId, newPriority);
        pq.offer(updated);
        taskMap.put(taskId, updated);
    }

    public void rmv(int taskId) {
        Task task = taskMap.get(taskId);
        if (task != null) {
            oldTask.add(task.taskId + "#" + task.priority);
            taskMap.remove(taskId);
        }
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            Task highest = pq.peek();
            String key = highest.taskId + "#" + highest.priority;

            if (oldTask.contains(key)) {
                // stale heap entry
                oldTask.remove(key);
                pq.poll();
                continue;
            }

            Task canonical = taskMap.get(highest.taskId);
            if (canonical == null) {
                // this task was removed from map (rmv), discard
                pq.poll();
                continue;
            }

            if (highest.priority != canonical.priority) {
                // an older version; discard
                pq.poll();
                continue;
            }

            // valid top entry
            pq.poll();
            return highest.userId;
        }
        return -1;
    }
}
```

---

## Complexity analysis

* `add`: `O(log n)` for `pq.offer` (amortized)
* `edit`: `O(log n)` for `pq.offer` (plus `O(1)` for set/map updates)
* `rmv`: `O(1)` for marking stale and removing from map
* `execTop`: each stale heap entry is removed at most once. Each `pq.poll()` is `O(log n)` amortized, so the total work is proportional to the number of heap operations. Getting the valid top is effectively `O(log n)` amortized.

Space: `O(n)` (heap + map + stale set). Note stale set temporarily holds keys until stale heap entries bubble up and are removed.

---

## Examples

Initial tasks: `[[10, 101, 5], [11, 102, 7], [12, 103, 7]]` (format: `[userId, taskId, priority]`)

* `execTop()` → highest priority is `7`. Among `taskId` 102 and 103, larger `taskId` wins → returns userId `12` (taskId 103).
* `edit(102, 8)` → mark old `(102#7)`, insert `(102#8)`.
* `execTop()` → returns userId `11` (taskId 102, priority 8).
* `rmv(101)` → mark `(101#5)` and remove map entry

---

## Edge cases & pitfalls

* **String key collisions**: using `taskId + "" + priority` can cause ambiguity (e.g., `1 + "" + 23` vs `12 + "" + 3`). Use a delimiter, e.g., `"#"`, or encode as a `long` composite key `((long)taskId << 32) | (priority & 0xffffffffL)` to avoid collisions.
* **Not removing taskMap entry on `rmv`** can cause `edit` to resurrect removed tasks.
* **Concurrency**: the class is not thread-safe. Synchronize externally if used across threads.

---

## Alternative approaches

* **Version counter**: keep a `Map<Integer, Integer> version` and increment version on each edit; push `(taskId, priority, version)` to heap. When popping, compare version with `version.get(taskId)`.
* **Indexed heap / custom heap**: build a heap that supports direct updates (a.k.a heap with index map) to avoid duplicates, but is more code to implement.
* **TreeSet / TreeMap**: store active tasks in a balanced BST keyed by `(priority, taskId)` so removals and updates can be done by removing the old key and inserting new — but you need a way to map `taskId` → current key.

---

## Testing checklist

* Add many tasks and run repeated `execTop()` until empty.
* Edit tasks multiple times before they reach top.
* Remove tasks after edits and ensure they never reappear.
* Edge cases: edit non-existent task, remove non-existent task, add a task with same `taskId` after removal.

---

If you'd like, I can:

* provide unit tests (JUnit) for the class,
* produce a version returning `(userId, taskId)` instead of just `userId`, or
* show the `versioning`-based implementation instead of string keys.
