package org.academiadecodigo.hackathon.utils;

public class Constants {

    // TODO: All the game sizes, numbers... etc

    /*
    Screen Size and Box2D Scale (Pixels Per Meter)
     */
    public static final int VIEW_WIDTH = 480;
    public static final int VIEW_HEIGHT = 208;
    public static final float PPM = 100;

    public static final int GRAVITY = -10;
    public static final int PROJECTILE_SPEED = 0; //TODO: DEFINE SPEED


    public static final int LADDER_INDEX = 3;
    public static final int GROUND_INDEX = 2;
    public static final int TREASURE_INDEX = 4;
    public static final int ENEMY_INDEX = 5;

    public static final String LEVEL1_TMX = "level1.tmx";
    public static final String LADDER_SPRITE_NAME = "ladder";

    public static final float PLAYER_X_SPEED = 2;


    public static final String PROJECTILE_RIGHT = "bullet_right.png";
    public static final String PROJECTILE_LEFT = "bullet_left.png";

    public static final float PROJECTILE_HEIGHT = 16; // Pixels
    public static final float PROJECTILE_WIDTH = 16; // Pixels

    public static final int HUMAN_SIZE = 16;
    public static final String PLAYER_REGION = "player";
}
