package model;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import javax.sound.sampled.Line;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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

        if (collision.getTime() >= moveTime) { //No Collision
            newBall = moveBallForTime(ball, moveTime);
        } else { //Collision
            newBall = moveBallForTime(ball, collision.getTime());
            newBall.setVelocity(collision.getVelocity());
            System.out.println("New Veloctiy:" + collision.getVelocity());
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
        ball.setCenter(new Vect(newX, newY));
        ball.setVelocity(new Vect(velX, velY));

        return new Ball("Ball", newX, newY, velX, velY);
    }

    private Collision getTimeTillCollision(Ball bll) {
        Circle ballC = bll.getCircle();
        Vect ballV = bll.getVelocity();
        Vect newV = new Vect(0, 0);
        IElement collidingElement;
        double shortestTime = Double.MAX_VALUE;
        double time = 0.0;
        for (IElement element : board.getElements()) {
            for (LineSegment line : element.getLines()) {
                time = Geometry.timeUntilWallCollision(line, ballC, ballV);
                if (time < shortestTime) {
                    shortestTime = time;
                    collidingElement = element;
                    element.setColor(Color.GREEN);

                    System.out.println("Colide Line: " + element + " time: " + time);
                    newV = Geometry.reflectWall(line, ballV);
                }

            }
            for (Circle circle : element.getCircles()) {
                time = Geometry.timeUntilCircleCollision(circle, ballC, ballV);
                if (time < shortestTime) {
                    shortestTime = time;
                    collidingElement = element;
                    System.out.println("Colide Circle: " + element + " time: " + time);
                    newV = Geometry.reflectCircle(circle.getCenter(), ballC.getCenter(), ballV);
                }
            }
        }
        return new Collision(newV, shortestTime);
    }
}