package Stacks.LRUCache;

import java.util.HashMap;
import java.util.Map;

class DLL{

    int key;
    int value;
    DLL prev ; 
    DLL next;

    DLL(){

    }
    DLL( int key , int val , DLL prev , DLL next ){
        this.value = val;
        this.prev = prev;
        this.next = next;
        this.key = key;
    }

    DLL( int key , int val ){
        this.key = key;
        this.value = val;
        this.prev = null;
        this.next = null;
    }

    public String toString(){
        return "[ " + key + " " + value + " ]";
    }


}
public class LRUCache {

    private DLL dll;
    private DLL head;
    private DLL tail;
    private Map<Integer,DLL> map;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.dll = new DLL(); 
        this.map = new HashMap<>();
        this.head = new DLL(-1,-1);
        this.tail = new DLL(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if( map.containsKey(key) ){
            // System.out.println(map);
            DLL dl = map.get(key);
            dl.prev.next = dl.next;
            dl.next.prev = dl.prev;

            dl.next = head.next;
            head.next.prev = dl;

            dl.prev = head; 
            head.next = dl;
            return dl.value;
        }
        else{
            return -1;
        }

    }
    
    public void put(int key, int value) {
        if( map.containsKey(key) ){
            DLL dl = map.get(key);
            dl.value = value;
            dl.prev.next = dl.next;
            dl.next.prev = dl.prev;

            dl.next = head.next;
            head.next.prev = dl;

            dl.prev = head; 
            head.next = dl;
        }
        else{
            if( map.size() == capacity ){

                DLL remove = tail.prev;
                remove.prev.next = tail;
                tail.prev = remove.prev;
                map.remove(remove.key);

            }
                DLL dl = new DLL(key,value);
                dl.next = head.next;
                dl.prev = head;

                head.next.prev = dl;
                head.next = dl;

                map.put(key,dl);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */