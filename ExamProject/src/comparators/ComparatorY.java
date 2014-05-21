/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comparators;

import extendables.Entity;
import java.util.Comparator;

/**
 *
 * @author PK
 */
public class ComparatorY implements Comparator<Entity>{

    @Override
    public int compare(Entity o1, Entity o2) {
        Float f = o1.getyPos();
        return f.compareTo(o2.getyPos());
    }
}
