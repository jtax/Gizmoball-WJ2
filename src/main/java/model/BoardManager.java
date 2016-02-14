package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class BoardManager {
    Board board;

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

        velX = ball.getVelocity().getX();
        velY = ball.getVelocity().getY();

        newX = ball.getCenter().getX() + (velX * time);
        newY = ball.getCenter().getY() + (velY * time);

        return new Ball("Ball", newX, newY, velX, velY);
    }

    private Collision getTimeTillCollision(Ball ball) {
        return new Collision(new Coordinate(1, 1), 50.0);
    }
}