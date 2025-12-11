package Stacks.LFUCache;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Node{

    int key;
    int value;
    int freq;
    Node prev;
    Node next;

    Node( int key , int val , int freq, Node prev , Node next ){
        this.key = key;
        this.value = val;
        this.freq = freq;
        this.prev = prev;
        this.next = next;
    }

    Node( int key , int val , int freq ){
        this.key = key;
        this.value = val;
        this.freq = freq;
        this.prev = null;
        this.next = null;
    }

}

class DLL{
    
    Node head;
    Node tail;
    private int size;

    DLL(){
        head = new Node(-1,-1,0);
        tail = new Node(-1,-1,0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    void insertFront(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
        size++;
    }

    void remove( Node node ){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    void removeLast(){
        Node last = tail.prev;
        last.prev.next = tail;
        tail.prev = last.prev;
        size--;
    }

    Node getLastNode(){
        return tail.prev;
    }

    boolean isEmpty(){
        return size == 0;
    }

}

public class LFUCache {

    private TreeMap<Integer,DLL> freqMap ;
    private Map<Integer,Node> map;
    private int size;

    public LFUCache(int capacity) {
        this.size = capacity;
        freqMap = new TreeMap<>();
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if( map.containsKey(key) ){

            Node node = map.get(key);

            
            //removing from the oldlist
            int oldfreq = node.freq;
            DLL oldlist = freqMap.get(oldfreq);
            oldlist.remove(node);

            //removing the key from the list
            if( oldlist.isEmpty() ){
                freqMap.remove( oldfreq );
            }

            //updating the node
            node.freq++;

            //adding in the newlist
            int newfreq = oldfreq+1;
            freqMap.computeIfAbsent( newfreq , k -> new DLL() );
            freqMap.get( newfreq ).insertFront(node);

            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if( size == 0 ) return ;

        if( map.containsKey(key) ){

            Node node = map.get(key);

            
            //removing from the oldlist
            int oldfreq = node.freq;
            DLL oldlist = freqMap.get(oldfreq);
            oldlist.remove(node);

            //removing the key from the list
            if( oldlist.isEmpty() ){
                freqMap.remove( oldfreq );
            }

            //updating the node
            node.freq++;
            node.value = value;

            //adding in the newlist
            int newfreq = oldfreq+1;
            freqMap.computeIfAbsent( newfreq , k -> new DLL() );
            freqMap.get( newfreq ).insertFront(node);
            // map.put( key , node );
            return;

        }
        
        if( map.size() == size ){

            //getting the LFU element
            int rmfreq = freqMap.firstKey();
            DLL rmlist = freqMap.get(rmfreq);
            Node last = rmlist.getLastNode();

            //removing the LFU
            int lastEleKey = last.key;
            rmlist.remove(last);
            if( rmlist.isEmpty() ) freqMap.remove(rmfreq);

            map.remove(lastEleKey);

        }
            Node node = new Node(key,value,1);
            freqMap.computeIfAbsent( 1 , k -> new DLL() );
            freqMap.get( 1 ).insertFront(node);
            map.put( key , node );

    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */