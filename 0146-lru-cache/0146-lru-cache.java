class LRUCache {

    private DLL dll;
    Map<Integer,Node> map;
    int capacity;
    public LRUCache(int capacity) {
        dll = new DLL();
        map = new HashMap<>();
        this.capacity = capacity;   
    }
    
    public int get(int key) {
        if( map.containsKey(key) ){
            Node node = map.get(key);
            dll.remove( node );

            node = dll.addLast( key, node.value );
            map.put( key,node );
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if( map.containsKey(key) ){
            Node node = map.get( key );
            dll.remove( node );

            node = dll.addLast( key,value );
            map.put( key,node );
            System.out.println( node.value );
        }
        else if( map.size() == capacity ){
            Node removed = dll.removeFirst();
            // System.out.println( removed.key + "   coa " + key );
            map.remove( removed.key );

            Node node = dll.addLast( key,value );
            map.put( key,node );
        }
        else{
            Node node = dll.addLast( key,value );
            map.put( key,node );
        }
    }
}

class Node{
    int key;
    int value;
    Node prev;
    Node next;
    Node( int key, int value ){
        this.key = key;
        this.value = value;
    }
}

class DLL{

    private Node head, tail ;
    DLL(){
        head = null;
        tail = null;
    }

    void remove( Node node ){
        
        if( node == head ){
            head = head.next;
            if( head != null ) head.prev = null;
            return ;
        }
        if( node == tail ){
            tail = tail.prev;
            if( tail != null ){
                tail.next = null;
            }
            return;
        }
        // if( node.prev != null )
        // if( node.next != null )
            node.prev.next = node.next;
            node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
    }

    Node removeFirst(){

        if( head == tail ){
            Node delete = head;
            head = null;
            tail = null;
            return delete;
        }

        Node delete = head;
        head = head.next;
        head.prev = null;
        delete.next = null;
        delete.prev = null;

        return delete;

    }

    Node addLast( int key, int value ){
        Node node = new Node(key,value);
        if( head == null ){
            head = node;
            tail = node;
            return node;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
        return node;
    }


}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */