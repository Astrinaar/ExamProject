/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import Player.Player;
import extendables.Entity;
import org.newdawn.slick.Input;

/**
 *
 * @author PK
 */
public class MathTool {

    private static Player player;

    public static void setPlayer(Player player) {
        MathTool.player = player;
    }

    public static float getAngle(Input input, float projectileXOffset) {
        float deltax = input.getMouseX() - projectileXOffset - (player.getxPos());
        float deltay = input.getMouseY() - projectileXOffset - (player.getyPos());
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }

    public static float getAngleBetweenTwoPoints(float xOrigin, float yOrigin, float xTarget, float yTarget, float XOffset) {
        float deltax = xTarget - XOffset - xOrigin;
        float deltay = yTarget - XOffset - yOrigin;
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }

    public static float getAngleBetweenTwoPointsInvY(float xOrigin, float yOrigin, float xTarget, float yTarget, float projectileXOffset) {
        float deltax = xTarget - projectileXOffset - xOrigin;
        float deltay = -yTarget - projectileXOffset + yOrigin;
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }

    public static float getAngleInvY(Input input, float projectileXOffset) {
        float deltax = input.getMouseX() - projectileXOffset - (player.getxPos());
        float deltay = -input.getMouseY() - projectileXOffset + (player.getyPos());
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }

    public static float getAngleInvX(Input input, float projectileXOffset) {
        float deltax = -input.getMouseX() + projectileXOffset + (player.getxPos());
        float deltay = input.getMouseY() + projectileXOffset - (player.getyPos());
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }

    public static float getAngleToPlayer(float xPos, float yPos) {
        float deltax = (player.getxPos() + player.getTexture().getWidth() / 2) - xPos;
        float deltay = (player.getyPos() + player.getTexture().getHeight() / 2) - yPos;
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }

    public static float getAngleToPlayerInvY(float xPos, float yPos) {
        float deltax = (player.getxPos() + player.getTexture().getWidth() / 2) - xPos;
        float deltay = (-player.getyPos() + player.getTexture().getHeight() / 2) + yPos;
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }

    public static float getAngleToPlayerInvX(float xPos, float yPos) {
        float deltax = (player.getxPos() + player.getTexture().getWidth() / 2) + xPos;
        float deltay = (player.getyPos() + player.getTexture().getHeight() / 2) - yPos;
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }

    public static float getDistanceToPlayer(float xPos, float yPos) {
        float deltax = xPos - (player.getxPos() + (player.getTexture().getWidth() / 2));
        float deltay = yPos - (player.getyPos() + (player.getTexture().getHeight() / 2));
        float distance = deltax * deltax + deltay * deltay;
        distance = (float) Math.sqrt(distance);
        return distance;
    }

    public static float getDistanceBetweenEntities(Entity e1, Entity e2) {
        float deltax = e1.getxPos() + e1.getTexture().getWidth() / 2 - (e2.getxPos() + e2.getTexture().getWidth() / 2);
        float deltay = e1.getyPos() + e1.getTexture().getHeight() / 2 - (e2.getyPos() + e2.getTexture().getHeight() / 2);
        float distance = deltax * deltax + deltay * deltay;
        distance = (float) Math.sqrt(distance);
        return distance;
    }

    public static float getDistanceBetweenPoints(float x1, float y1, float x2, float y2) {
        float deltax = x1 - x2;
        float deltay = y1 - y2;
        float distance = deltax * deltax + deltay * deltay;
        distance = (float) Math.sqrt(distance);
        return distance;
    }

}
