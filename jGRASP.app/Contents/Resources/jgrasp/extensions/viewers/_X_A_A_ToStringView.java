   import jgrasp.viewer.ViewerCreateData;
   import jgrasp.viewer.ViewerException;
   import jgrasp.viewer.ViewerInfo;
   import jgrasp.viewer.ViewerUpdateData;
   import jgrasp.viewer.ViewerValueData;

   import jgrasp.viewer.jgrdi.DebugContext;
   import jgrasp.viewer.jgrdi.Value;

   import jgrasp.viewer.text.StringTableViewWSV;


   /** A 2D array viewer that displays the elements as a table of
    *  strings. **/
    public class _X_A_A_ToStringView extends StringTableViewWSV {
   
   
      /** Creates a new string list viewer for 2D arrays.
       *
       *  @param vcd viewer creation data. **/
       public _X_A_A_ToStringView(final ViewerCreateData vcd) {
         super(false);
      }
       
         
      /** {@inheritDoc} **/
       public String getViewName() {
         return "2D Array Elements";
      }
   
   
      /** {@inheritDoc} **/
       public int getPriority() {
         return 11;
      }
   
   
      /** {@inheritDoc} **/
       public void update(final ViewerValueData valueData,
            final ViewerUpdateData data, final DebugContext context,
            final int rowOffset, final int colOffset, final int numRowsShown,
            final int numColsShown, final int selectedRow,
            final int selectedCol, final String[][] textOut,
            final Value[] selectedValuesOut,
            final int[] rowsOut, final int[] colsOut,
            final boolean[] fixedColsOut, final String[] errorOut)
            throws ViewerException {
      
         Value value = valueData.getValue();
         int rows = value.getArrayLength(context);
         rowsOut[0] = rows;
         
         int longestCol = 0;
         boolean fixedCols = true;
         for (int i = 0; i < numRowsShown; i++) {
            if (i + rowOffset < rows) {
               Value row = value.getArrayElement(context, i + rowOffset);
               int cols = 0;
               if (!row.isNull()) {
                  cols = row.getArrayLength(context);
               }
               if (i > 0 && cols != longestCol) {
                  fixedCols = false;
               }
               if (cols > longestCol) {
                  longestCol = cols;
               }
               for (int j = 0; j < numColsShown; j++) {
                  if (j + colOffset < cols) {
                     Value element = row.getArrayElement(context,
                           j + colOffset);
                     if (i == selectedRow && j == selectedCol) {
                        selectedValuesOut[0] = element;
                     }
                     String str = element.toString(context);
                     if (str.length() > 100) {
                        str = str.substring(0, 96) + " ...";
                     }
                     textOut[i][j] = str;
                  }
                  else {
                     textOut[i][j] = null;
                  }
               }
            }
            else {
               for (int j = 0; j < numColsShown; j++) {
                  textOut[i][j] = null;
               }
            }
         }
         colsOut[0] = longestCol;
         fixedColsOut[0] = fixedCols;
      }
      
      
      /** {@inheritDoc} **/
       public String getSubviewerLabel(final int index,
            final String viewerLabel, final int rowIndex,
            final int colIndex) {
         return viewerLabel + " [" + rowIndex + "," + colIndex + "]";
      }
   
   
      /** {@inheritDoc} **/
       public String getSubviewerTreeLabel(final int index,
            final String viewerLabel, final int rowIndex,
            final int colIndex) {
         return "[" + rowIndex + "," + colIndex + "]";
      }
   
   
      /** {@inheritDoc} **/
       public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("2D array \"toString()\" viewer");
         vi.setLongDescription("This viewer displays the toString() value "
               + "for array element in a 2D grid of cells. Selecting a "
               + "cell will cause its value to be displayed in a subviewer.");
      }
   
   }
