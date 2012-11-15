   package jgraspvex;


   public class Queue {
   
      private Object[] elements = new Object[10];
   
      private int start;
   
      private int end;
   
   
      public void add(Object v) {
         elements[end++] = v;
         if (end == elements.length) {
            end = 0;
         }
         if (end == start) {
            Object[] newElements = new Object[elements.length * 2];
            if (start < end) {
               System.arraycopy(elements, start, newElements, 0,
                     end - start);
            }
            else {
               System.arraycopy(elements, start, newElements, 0,
                     elements.length - start);
               System.arraycopy(elements, 0, newElements,
                     elements.length - start, end);
            }
            if (end > start) {
               end = end - start;
            }
            else {
               end = elements.length - start + end;
            }
            elements = newElements;
            start = 0;
         }
      }
   
   
      public Object remove() {
         if (start == end) {
            return null;
         }
         Object result = elements[start];
         elements[start] = null;
         start++;
         if(start == elements.length) {
            start = 0;
         }
         return result;
      }
   
   
      public void clear() {
         elements = new Object[10];
         start = 0;
         end = 0;
      }
   }