package model;

import physics.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class BoardManager {
    private Board board;

    public BoardManager(){
        board = new Board(0,0,20,20);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void tick() {
        List<Ball> newBalls = new ArrayList<>();
        for (Ball ball : board.getBalls()) {
            newBalls.add(moveBall(ball));
        }
        board.setBalls(newBalls);
    }

    private Ball moveBall(Ball ball) {
        Ball newBall;
        double moveTime = 0.05; //20 FPS
        Collision collision = getTimeTillCollision(ball);

        if (collision.getTime() > moveTime) { //No Collision
            newBall = moveBallForTime(ball, moveTime);
        } else { //Collision
            newBall = moveBallForTime(ball, collision.getTime());
            newBall.setVelocity(collision.getVelocity());
        }

        return newBall;
    }

    private Ball moveBallForTime(Ball ball, double time) {
        double newX, newY;
        double velX, velY;

        velX = ball.getVelocity().x();
        velY = ball.getVelocity().y();

        newX = ball.getCenter().x() + (velX * time);
        newY = ball.getCenter().y() + (velY * time);

        return new Ball("Ball", newX, newY, velX, velY);
    }

    private Collision getTimeTillCollision(Ball ball) {
        //Circle ballC = new Circle();
        return null;
    }
}