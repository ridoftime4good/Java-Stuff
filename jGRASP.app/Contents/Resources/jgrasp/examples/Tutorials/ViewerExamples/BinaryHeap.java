    public class BinaryHeap {
   
      private Comparable[] heap;
      
      private int size;
   
   
       public BinaryHeap() {
         heap = new Comparable[210];
         size = 0;
      }
   
   
       public boolean isEmpty() {
         return size == 0;
      }
   
   
       public int size() {
         return size;
      }
   
   
       public Comparable getMax() {
         return (size == 0)? null : heap[1];
      }
   
   
       public void add(Comparable element) {
         if (size == heap.length - 1) {
            Comparable[] newHeap = new Comparable[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
         }
         size++;
         int pos = size;
         while (pos != 1 && heap[pos / 2].compareTo(element) < 0) {
            // Move parent down.
            heap[pos] = heap[pos / 2];
            pos /= 2;
         }
      
         heap[pos] = element;
      }
   
   
       public Comparable remove() {
         if (size == 0) {
            return null;
         }
         Comparable max = heap[1];
      
         Comparable last = heap[size--];
      
         int node = 1;
         int child = 2;
         while (child <= size) {
            if (child < size
                  && heap[child].compareTo(heap[child + 1]) < 0) {
               child++;
            }
            if (last.compareTo(heap[child]) >= 0) {
               break;
            }
            // Move child up.
            heap[node] = heap[child];
            node = child;
            child *= 2;
         }
         heap[node] = last;
      
         return max;
      }
   
   
       public static void main(String [] args) {
         BinaryHeap hp = new BinaryHeap();
         Object ot = hp;
         hp.add("C");
         hp.add("D");
         hp.add("b");
         hp.add("a");
         hp.add("d");
         hp.remove();
         hp.add("z");
         hp.remove();
         hp.add("k");
      }
   }
