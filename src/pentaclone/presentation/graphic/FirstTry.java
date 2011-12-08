/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pentaclone.presentation.graphic;

import com.brackeen.javagamebook.graphics.Animation;
import com.brackeen.javagamebook.graphics.RotatingSprite;
import com.brackeen.javagamebook.input.GameAction;
import com.brackeen.javagamebook.input.InputManager;
import com.brackeen.javagamebook.test.GameCore;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Formatter;

/**
 *
 * @author diego
 */
public class FirstTry extends GameCore {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        new FirstTry().run();
    }
    protected GameAction exit;
    protected GameAction pause;
    protected InputManager inputManager;
    private Image bgImage;
    private boolean paused;
    private RotatingSprite board1,  board2,  board3,  board4;

    @Override
    public void init() {
        super.init();
        Window window = screen.getFullScreenWindow();
        inputManager = new InputManager(window);

        createGameActions();
        createSprite();
        paused = false;
    }

    /**
    Tests whether the game is paused or not.
     */
    public boolean isPaused() {
        return paused;
    }

    /**
    Sets the paused state.
     */
    public void setPaused(boolean p) {
        if (paused != p) {
            this.paused = p;
            inputManager.resetAllGameActions();
        }
    }

    @Override
    public void update(long elapsedTime) {
        // check input that can happen whether paused or not
        checkSystemInput();

        if (!isPaused()) {
            // check game input
            checkGameInput();

            // update sprite
//            player.update(elapsedTime);
            board1.update(elapsedTime);
            board2.update(elapsedTime);
            board3.update(elapsedTime);
            board4.update(elapsedTime);
        }
    }

    /**
    Checks input from GameActions that can be pressed
    regardless of whether the game is paused or not.
     */
    public void checkSystemInput() {
        if (pause.isPressed()) {
            setPaused(!isPaused());
        }
        if (exit.isPressed()) {
            stop();
        }
    }

    /**
    Checks input from GameActions that can be pressed
    only when the game is not paused.
     */
    public void checkGameInput() {
        float velocityX = 0;
//        if (moveLeft.isPressed()) {
//            velocityX -= Player.SPEED;
//        }
//        if (moveRight.isPressed()) {
//            velocityX += Player.SPEED;
//        }
//        player.setVelocityX(velocityX);
//
//        if (jump.isPressed() &&
//                player.getState() != Player.STATE_JUMPING) {
//            player.jump();
//        }
    }

    public void draw(Graphics2D g) {
        // draw background
        g.drawImage(bgImage, 0, 0, screen.getWidth(), screen.getHeight(), null);

        // draw Frames Per Second
        float fps = 0f;
        try {
            fps = (float) loopCount / (currTime - startTime) * 1000;
        } catch (ArithmeticException e) {
            // ignore
        }
        StringBuilder fpsString = new StringBuilder();
        Formatter f = new Formatter(fpsString);
        f.format("%.1f FPS", fps);
        g.drawString(fpsString.toString(), 25, 25);

        board2.draw(g);
        board1.draw(g);
        board3.draw(g);
        board4.draw(g);
    }

    /**
    Creates GameActions and maps them to keys.
     */
    public void createGameActions() {
        exit = new GameAction("exit",
                GameAction.DETECT_INITAL_PRESS_ONLY);
        pause = new GameAction("pause",
                GameAction.DETECT_INITAL_PRESS_ONLY);

        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
        inputManager.mapToKey(pause, KeyEvent.VK_P);
    }

    /**
    Load images and creates the Player sprite.
     */
    private void createSprite() {
        // load images
        bgImage = loadImage("images/background.jpg");
        Image board = loadImage("images/purpleBoard.png");
        Image ball1 = loadImage("images/ball1.png");
        Image ball2 = loadImage("images/ball2.png");
        Animation anim = new Animation();
        Animation anim2 = new Animation();
        BufferedImage fullBoard = screen.createCompatibleImage(board.getWidth(null), board.getHeight(null), 
                Transparency.TRANSLUCENT);
        fullBoard.getGraphics().drawImage(board, 0, 0, null);
        fullBoard.getGraphics().drawImage(ball1, 50, 50, null);
        fullBoard.getGraphics().drawImage(ball2, 150, 150, null);

        anim.addFrame(fullBoard, 200);
        anim2.addFrame(board, 200);

//
//        for (float alpha = 1; alpha > .1; alpha -= .01) {
//            anim.addFrame(createImageAlphaCopy(board, alpha), 3);
//        }
//        for (float alpha = .1f; alpha < 1; alpha += .01) {
//            anim.addFrame(createImageAlphaCopy(board, alpha), 3);
//        }

//        Animation anim2 = anim.clone();
//        anim2.start(130);

        board1 = new RotatingSprite(anim2);
        board2 = new RotatingSprite(anim);
        board3 = new RotatingSprite(anim2);
        board4 = new RotatingSprite(anim);

        board1.setX(100);
        board1.setY(100);
//        board1.setVelocityX(0.00f);
//
//        board1.setAngularSpeed(0f);

        board2.setX(100 + board1.getWidth());
        board2.setY(100);
//        board2.setVelocityY(-0.000f);
        board2.setAngularSpeed(0.1f);
        board3.setX(100 + board1.getWidth());
        board3.setY(100 + board1.getHeight());

        board4.setX(100);
        board4.setY(100 + board1.getHeight());
    }

    private Image createImageAlphaCopy(Image source, float alpha) {
        // make translucent default image
        Image image = screen.createCompatibleImage(source.getWidth(null), 
                source.getHeight(null),
                Transparency.TRANSLUCENT);
        Graphics2D g = (Graphics2D) image.getGraphics();
        Composite alphaComp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g.setComposite(alphaComp);
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return image;
    }
}
