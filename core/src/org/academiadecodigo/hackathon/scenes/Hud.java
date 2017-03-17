package org.academiadecodigo.hackathon.scenes;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.hackathon.utils.Constants;


/**
 * Created by codecadet on 17/03/17.
 */
public class Hud {

    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    private Label countdownLabel;
    private Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label playerLabel;

    public Hud(SpriteBatch sb) {

        worldTimer = 0;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("lvl.1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerLabel = new Label("INDIANA GOMES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(playerLabel).expandX().padTop(5);
        table.add(levelLabel).expandX().padTop(5);
        table.add(timeLabel).expandX().padTop(5);
        table.add(countdownLabel).expandX().padTop(5);
        table.add(scoreLabel).expandX().padTop(5);

        stage.addActor(table);
    }

    public void update(float dt) {
        timeCount += dt;

        if(timeCount >= 1) {
            worldTimer++;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public int getTimeCount() {
        return worldTimer;
    }
    public void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }
}
