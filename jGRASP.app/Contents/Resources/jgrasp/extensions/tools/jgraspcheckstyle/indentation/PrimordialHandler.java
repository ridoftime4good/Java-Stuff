////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2005  Oliver Burn
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////
   package jgraspcheckstyle.indentation;

/**
 * A default no-op handler.
 *
 * @author jrichard
 */
    public class PrimordialHandler extends ExpressionHandler
   {
    /**
     * Construct an instance of this handler with the given indentation check.
     *
     * @param aIndentCheck   the indentation check
     */
       public PrimordialHandler(IndentationCheck aIndentCheck)
      {
         super(aIndentCheck, null, null, null);
      }
   
    /**
     * Check the indentation of the expression we are handling.
     */
       public void checkIndentation()
      {
        // nothing to check
      }
   
    /**
     * Indentation level suggested for a child element. Children don't have
     * to respect this, but most do.
     *
     * @param aChild  child AST (so suggestion level can differ based on child
     *                  type)
     *
     * @return suggested indentation for child
     */
       public IndentLevel suggestedChildLevel(ExpressionHandler aChild)
      {
         if (isCSDMode()) {
            return new IndentLevel(getBasicOffset());
         }
         return new IndentLevel(0);
      }
   
    /**
     * Compute the indentation amount for this handler.
     *
     * @return the expected indentation amount
     */
       protected IndentLevel getLevelImpl()
      {
         if (isCSDMode()) {
            return new IndentLevel(getBasicOffset());
         }
         return new IndentLevel(0);
      }
   }
