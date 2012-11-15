
   import java.awt.BorderLayout;
   import java.awt.Color;

   import java.util.ArrayList;
   import java.util.List;

   import javax.swing.BoxLayout;
   import javax.swing.JFrame;
   import javax.swing.JLabel;
   import javax.swing.JPanel;


  /** CollectionsExample -- This example is intended to
   *  illustrate the capabilities of the jGRASP viewers for
   *  the Components. Set a breakpoint at the indicated line.
   *  After the program stops at the breakpoint, open viewers on
   *  one or more variables by "dragging" the variable out of the
   *  Debug tab and releasing it. **/
    public class ComponentExample {
   
       public static void main(String[] args) {
         JPanel panel = new JPanel(new BorderLayout(5, 5));
         JLabel l1 = new JLabel(" ");
         l1.setOpaque(true);
         panel.add(l1, "South");
         JPanel center = new JPanel();
         center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
         panel.add(center, "Center");
         JLabel l2 = new JLabel("1");
         l2.setOpaque(true);
         JLabel l3 = new JLabel("2");
         l3.setOpaque(true);
         center.add(l2);
         center.add(l3);
      
         JFrame frame = new JFrame();
      
         boolean b = true;
         Boolean bl = Boolean.TRUE;
         int i = 0xfffffff0;
         Integer it = new Integer(i);
      
         frame.setContentPane(panel);
         frame.setAlwaysOnTop(true);
         frame.pack();
         frame.setVisible(true);
         l1.setBackground(Color.white);
         center.setBackground(Color.red);
         l2.setBackground(Color.green);
         l3.setBackground(Color.cyan);
         List<String> lst = new ArrayList<String>();
         List lst2 = new ArrayList();
         ArrayList lst3 = new ArrayList();
         ArrayList<String> lst4 = new ArrayList<String>();
      
         // Set a breakpoint on the next line.
      }
   
   }
