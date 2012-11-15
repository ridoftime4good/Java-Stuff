   package jgraspvex;


    public class LinkedList {
    
      private int size; 
   
      private LinkedNode head;
   
   
       public LinkedList() {
      }
   
   
       public void add(Object value) {
         LinkedNode node = new LinkedNode(value);
         node.next = head;
         head = node;
         size++;
      }
   
   
       public void insert(Object value, int index) {
         if (index == 0) {
            add(value);
            return;
         }
         LinkedNode node = new LinkedNode(value);
      
         LinkedNode prev = head;
         for (int i = 1; i < index; i++) {
            prev = prev.next;
         }
         node.next = prev.next;
         prev.next = node;
      
         size++;
      }
      
   
       public Object remove(int index) {
         if (index == 0) {
            Object result = head;
            head = head.next;
            size--;
            return head;
         }
         LinkedNode prev = head;
         for (int i = 1; i < index; i++) {
            prev = prev.next;
         }
         Object result = prev.next;
         prev.next = prev.next.next;
         size--;
         return result;
      }
      
      
       public int size() {
         return size;
      }
   
   
       public Object get(int index) {
         LinkedNode node = head;
         for (int i = 0; i < index; i++) {
            node = node.next;
         }
         return node.value;
      }
   }

