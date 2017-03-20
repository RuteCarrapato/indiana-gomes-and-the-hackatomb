package org.academiadecodigo.hackathon.utils;

public class Constants {

    //CONFIG, DO NOT TOUCH OR ELSE I KILL YOU. Ruben
    public static final int CONFIG_WIDTH = 1080;
    public static final int CONFIG_HEIGHT = 608;

    /*
    Screen Size and Box2D Scale (Pixels Per Meter)
     */
    public static final int VIEW_WIDTH = 480;
    public static final int VIEW_HEIGHT = 208;
    public static final float PPM = 100;

    public static final int GRAVITY = -10;

    public static final int LADDER_INDEX = 3;
    public static final int GROUND_INDEX = 2;
    public static final int TREASURE_INDEX = 4;

    public static final String LEVEL1_TMX = "level1.tmx";

    public static final int HUMAN_SIZE = 16;

    public static final int ENEMY_INDEX = 5;
    public static final String ENEMY_REGION = "zenemy";

    public static final String PLAYER_REGION = "player";
    public static final float PLAYER_X_SPEED = 2;
    public static final float PLAYER_RUN_FORCE = 0.075f;
    public static final float PLAYER_JUMP_FORCE = 2.8f;

    public static final float PROJECTILE_RADIUS = 2;
    public static final String PROJECTILE_RIGHT = "sprites/bullet_right.png";
    public static final String PROJECTILE_LEFT = "sprites/bullet_left.png";
    public static final float PROJECTILE_HEIGHT = 16; // Pixels
    public static final float PROJECTILE_WIDTH = 16; // Pixels
    public static final float PROJECTILE_SPEED = 2;
    public static final int ENEMY_POINTS = 100;

    public static final float SPRITE_POSITION_FIX = 0.012f;
}
