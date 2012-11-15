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

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Handler for assignements.
 *
 * @author o_sukhodolsky
 */
public class AssignHandler extends BlockParentHandler
{
    /**
     * Construct an instance of this handler with the given indentation check,
     * abstract syntax tree, and parent handler.
     *
     * @param aIndentCheck   the indentation check
     * @param aAst           the abstract syntax tree
     * @param aParent        the parent handler
     */
    public AssignHandler(IndentationCheck aIndentCheck,
        DetailAST aAst, ExpressionHandler aParent)
    {
        super(aIndentCheck, "assign", aAst, aParent);
    }

    /**
     * Check the indentation of the expression we are handling.
     */
    public void checkIndentation()
    {
        final IndentLevel expectedLevel = getChildrenExpectedLevel();

        // check indentation of assign if it starts line
        final DetailAST assign = getMainAst();
        if (startsLine(assign)
            && !expectedLevel.accept(expandedTabsColumnNo(assign)))
        {
            logError(assign, "" , expandedTabsColumnNo(assign), expectedLevel);
        }

        // check indentation of rvalue
        DetailAST child = (DetailAST) assign.getFirstChild();

        // if this is assign in expression then skip first child,
        // because it's lvalue.
        final DetailAST parent = assign.getParent();
        if ((parent != null) && (parent.getType() == TokenTypes.EXPR)) {
            child = (DetailAST) child.getNextSibling();
        }
        if ((parent != null)
            && (parent.getType() == TokenTypes.ANNOTATION_MEMBER_VALUE_PAIR))
        {
            child = (DetailAST) assign.getNextSibling();
        }

        checkExpressionSubtree(child, expectedLevel, false, true);
    }

    /**
     * @return true if indentation should be increased after
     *              fisrt line in checkLinesIndent()
     *         false otherwise
     */
    protected boolean shouldIncreaseIndent()
    {
        return false;
    }

    /** {@inheritDoc} */
    public IndentLevel suggestedChildLevel(ExpressionHandler aChild)
    {
        final DetailAST assign = getMainAst();
        final DetailAST child = aChild.getMainAst();

        if (child == assign.getFirstChild()) {
            // left side of assignment should have the same
            // indentation as "assignment"
            return getLevel();
        }
        if (startsLine(assign)) {
            return new IndentLevel(expandedTabsColumnNo(assign));
        }
        return super.suggestedChildLevel(aChild);
    }
}
