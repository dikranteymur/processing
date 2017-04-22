/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

/*
  Part of the Processing project - http://processing.org

  Copyright (c) 2017 The Processing Foundation

  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 2 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software Foundation,
  Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package processing.app.ui;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import processing.app.Mode;


class ZoomTreeCellRenderer extends DefaultTreeCellRenderer {

  public ZoomTreeCellRenderer(Mode mode) {
    //setFont(Toolkit.getSansFont(Toolkit.zoom(14), Font.PLAIN));
    setFont(mode.getFont("tree.font"));
  }


  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                boolean selected,
                                                boolean expanded,
                                                boolean leaf, int row,
                                                boolean hasFocus) {

    // Adjust height for magnified displays. The font is scaled properly,
    // but the rows don't automatically use the scaled preferred size.
    // https://github.com/processing/processing/issues/4936
    int high = getPreferredSize().height;
    if (high != 0) {
      // add 15% for a little more spacing.. Source Sans leading is short
      high = (int) (high * 1.15f);
      int current = getSize().height;
      if (current != high) {
        tree.setRowHeight(high);
      }
    }
    return super.getTreeCellRendererComponent(tree, value, selected,
                                              expanded, leaf, row, hasFocus);
  }
}