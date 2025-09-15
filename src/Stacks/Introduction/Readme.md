# L1. Introduction to Stack and Queue | Implementation using Data Structures

This video is an introductory session on **Stack and Queue** data structures, part of the "Striver's A to Z DSA Course". It covers the fundamental concepts, common operations, and various implementation techniques for both Stack and Queue, including advanced scenarios where one data structure is implemented using the other.

## Table of Contents

1. [What is a Stack?](#what-is-a-stack)
2. [What is a Queue?](#what-is-a-queue)
3. [Inbuilt Libraries for Stack and Queue](#inbuilt-libraries-for-stack-and-queue)
4. [Implementation Details](#implementation-details)

    * [Stack using Arrays](#stack-using-arrays)
    * [Queue using Arrays](#queue-using-arrays)
    * [Stack using Linked Lists](#stack-using-linked-lists)
    * [Queue using Linked Lists](#queue-using-linked-lists)
5. [Advanced Implementations](#advanced-implementations)

    * [Stack using Queue Data Structure](#stack-using-queue-data-structure)
    * [Queue using Stack Data Structure](#queue-using-stack-data-structure)

        * [Approach 1 (Push-Heavy)](#approach-1-push-heavy)
        * [Approach 2 (Pop/Top-Heavy)](#approach-2-poptop-heavy)

---

## 1. What is a Stack?

A **Stack** is a data structure that follows the **LIFO (Last In, First Out)** mechanism. This means the last element added to the stack is the first one to be removed. Think of a stack of plates; you add and remove plates from the top.

### Common Operations:

* **`push(value)`**: Adds a `value` to the top of the stack.
* **`pop()`**: Removes the element from the top of the stack.
* **`top()`**: Returns the topmost element without removing it.
* **`size()`**: Returns the number of elements currently in the stack.

### Example Walkthrough:

* `push(2)` -> Stack:
* `push(3)` -> Stack:
* `push(4)` -> Stack:
* `push(1)` -> Stack: (1 is the last-in)
* `pop()` -> Removes 1. Stack:
* `top()` -> Returns 4. Stack: (4 is the new top)
* `push(5)` -> Stack:
* `top()` -> Returns 5. Stack:
* `size()` -> Returns 3 (elements: 2, 3, 4).

### Data Types:

A stack can hold various data types such as integers, pairs, doubles, longs, characters, strings, or even customized types.

---

## 2. What is a Queue?

A **Queue** is a data structure that follows the **FIFO (First In, First Out)** mechanism. This means the first element added to the queue is the first one to be removed. Imagine a line of people; the first person in line is the first to be served.

### Common Operations:

* **`push(value)`**: Adds a `value` to the end (rear) of the queue.
* **`pop()`**: Removes the element from the front of the queue.
* **`top()`** (or `front()` in some libraries): Returns the frontmost element without removing it.
* **`size()`**: Returns the number of elements currently in the queue.

### Example Walkthrough:

* `push(2)` -> Queue:
* `push(1)` -> Queue:
* `push(3)` -> Queue:
* `push(4)` -> Queue:
* `pop()` -> Removes 2. Queue: (2 was first-in)
* `top()` -> Returns 1. Queue: (1 is the new front)
* `pop()` -> Removes 1. Queue:
* `push(7)` -> Queue:
* `top()` -> Returns 3. Queue:
* `size()` -> Returns 3 (elements: 3, 4, 7).

### Data Types:

Similar to stacks, queues can also store various data types like integers, pairs, customized types, characters, or strings.

---

## 3. Inbuilt Libraries for Stack and Queue

Most modern programming languages (C++, Java, Python) provide **inbuilt libraries** for both Stack and Queue data structures. This means for most problem-solving scenarios, you can use these pre-implemented structures (e.g., C++ STL, Java Collections).

* **Stack usage example**: `stack<int> s; s.push(6); s.pop(); s.top(); s.size();`
* **Queue usage example**: `queue<int> q; q.push(value); q.pop(); q.top(); q.size();`

While convenient, understanding the internal implementation is crucial for interviews and a deeper understanding of DSA.

---

## 4. Implementation Details

Both Stack and Queue can be implemented using arrays or linked lists.

### Stack using Arrays

```java
class StackUsingArray {
    private int[] stack;
    private int top;
    private int capacity;

    public StackUsingArray(int size) {
        capacity = size;
        stack = new int[capacity];
        top = -1;
    }

    public void push(int value) {
        if (top == capacity - 1) { System.out.println("Stack Overflow"); return; }
        stack[++top] = value;
    }

    public void pop() {
        if (top == -1) { System.out.println("Stack Underflow"); return; }
        top--;
    }

    public int top() {
        if (top == -1) { System.out.println("Stack is empty"); return -1; }
        return stack[top];
    }

    public int size() { return top + 1; }
}
```

### Queue using Arrays

```java
class QueueUsingArray {
    private int[] queue;
    private int start, end, currentSize, capacity;

    public QueueUsingArray(int size) {
        capacity = size;
        queue = new int[capacity];
        start = -1; end = -1; currentSize = 0;
    }

    public void push(int value) {
        if (currentSize == capacity) { System.out.println("Queue Overflow"); return; }
        if (currentSize == 0) { start = 0; end = 0; }
        else { end = (end + 1) % capacity; }
        queue[end] = value; currentSize++;
    }

    public void pop() {
        if (currentSize == 0) { System.out.println("Queue Underflow"); return; }
        if (currentSize == 1) { start = -1; end = -1; }
        else { start = (start + 1) % capacity; }
        currentSize--;
    }

    public int top() {
        if (currentSize == 0) { System.out.println("Queue is empty"); return -1; }
        return queue[start];
    }

    public int size() { return currentSize; }
}
```

### Stack using Linked Lists

```java
class StackUsingLinkedList {
    private class Node { int data; Node next; Node(int data) { this.data = data; } }
    private Node top;
    private int size;

    public StackUsingLinkedList() { top = null; size = 0; }

    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public void pop() {
        if (top == null) { System.out.println("Stack Underflow"); return; }
        top = top.next; size--;
    }

    public int top() { if (top == null) { System.out.println("Stack is empty"); return -1; } return top.data; }

    public int size() { return size; }
}
```

### Queue using Linked Lists

```java
class QueueUsingLinkedList {
    private class Node { int data; Node next; Node(int data) { this.data = data; } }
    private Node start, end;
    private int size;

    public QueueUsingLinkedList() { start = end = null; size = 0; }

    public void push(int value) {
        Node newNode = new Node(value);
        if (size == 0) start = end = newNode;
        else { end.next = newNode; end = newNode; }
        size++;
    }

    public void pop() {
        if (size == 0) { System.out.println("Queue Underflow"); return; }
        start = start.next; if (start == null) end = null; size--;
    }

    public int top() { if (size == 0) { System.out.println("Queue is empty"); return -1; } return start.data; }

    public int size() { return size; }
}
```

---

## 5. Advanced Implementations

### Stack using Queue Data Structure

```java
import java.util.LinkedList;
import java.util.Queue;

class StackUsingQueue {
    private Queue<Integer> q = new LinkedList<>();

    public void push(int value) {
        int sz = q.size(); q.add(value);
        for (int i = 0; i < sz; i++) q.add(q.remove());
    }

    public void pop() { if (q.isEmpty()) System.out.println("Stack Underflow"); else q.remove(); }

    public int top() { if (q.isEmpty()) { System.out.println("Stack is empty"); return -1; } return q.peek(); }

    public int size() { return q.size(); }
}
```

### Queue using Stack Data Structure

#### Approach 1 (Push-Heavy)

```java
import java.util.Stack;

class QueueUsingStackPushHeavy {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public void push(int value) {
        while (!s1.isEmpty()) s2.push(s1.pop());
        s1.push(value);
        while (!s2.isEmpty()) s1.push(s2.pop());
    }

    public void pop() { if (s1.isEmpty()) System.out.println("Queue Underflow"); else s1.pop(); }

    public int top() { if (s1.isEmpty()) { System.out.println("Queue is empty"); return -1; } return s1.peek(); }

    public int size() { return s1.size(); }
}
```

#### Approach 2 (Pop/Top-Heavy)

```java
class QueueUsingStackPopHeavy {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public void push(int value) { s1.push(value); }

    public void pop() {
        if (s2.isEmpty()) while (!s1.isEmpty()) s2.push(s1.pop());
        if (s2.isEmpty()) System.out.println("Queue Underflow");
        else s2.pop();
    }

    public int top() {
        if (s2.isEmpty()) while (!s1.isEmpty()) s2.push(s1.pop());
        if (s2.isEmpty()) { System.out.println("Queue is empty"); return -1; }
        return s2.peek();
    }

    public int size() { return s1.size() + s2.size(); }
}
```

---

**Thank you for watching!** If you found this video helpful, please consider liking the video and subscribing to the channel for more content.
