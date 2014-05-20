/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.Random;

/**
 *
 * @author PK
 */
public class RandomTool {

    private static Random random;

    public static Random getRandom() {
        if (random == null) {
            random = new Random();
        }
        return random;
    }
}
