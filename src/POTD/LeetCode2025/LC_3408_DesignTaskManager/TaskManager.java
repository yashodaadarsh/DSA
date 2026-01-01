package POTD.LeetCode2025.LC_3408_DesignTaskManager;
import java.util.*;

class TaskManager {
    class Task {
        int userId;
        int taskId;
        int priority;

        public Task(int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }
    }

    private PriorityQueue<Task> pq;
    Map<Integer, Task> taskMap;
    Set<String> oldTask;

    public TaskManager(List<List<Integer>> tasks) {
        pq = new PriorityQueue<>((a, b) -> {
            if (a.priority == b.priority)
                return b.taskId - a.taskId;
            return b.priority - a.priority;
        });
        taskMap = new HashMap<>();
        oldTask = new HashSet<>();

        for (List<Integer> task : tasks) {
            Task taskk = new Task(task.get(0), task.get(1), task.get(2));
            pq.offer(taskk);
            taskMap.put(task.get(1), taskk);
        }

    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        pq.offer(task);
        taskMap.put(taskId, task);
    }

    public void edit(int taskId, int newPriority) {
        Task old = taskMap.get(taskId);
        if( old.priority == newPriority ) return ;
        oldTask.add(old.taskId+"#"+old.priority);
        Task updated = new Task(old.userId, taskId, newPriority);
        pq.offer(updated);
        taskMap.put(taskId, updated);
    }

    public void rmv(int taskId) {
        Task task = taskMap.get(taskId);
        oldTask.add(task.taskId+"#"+task.priority);
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            Task highest = pq.peek();
            String checkIsOld = highest.taskId+"#"+highest.priority;
            if( oldTask.contains(checkIsOld) ){
                pq.poll();
                oldTask.remove(checkIsOld);
            } else {
                pq.poll();
                return highest.userId;
            }
        }
        return -1;
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */