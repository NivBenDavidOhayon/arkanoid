// 213394364 Niv Ben David Ohayone
package gameObjects;
import core.Collidable;
import core.Sprite;
import core.Velocity;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The Paddle class implements the Sprite and Collidable interfaces,
 * representing a rectangular game object that can be moved horizontally
 * using the keyboard. The paddle can collide with other game objects and
 * change the direction of their movement.
 */
public class Paddle implements Sprite, Collidable {

    private static final double EPSILON = 0.00001;
    private static final int DIVISION = 5;
    private static final int PART_1_COLLISION = 300;
    private static final int PART_2_COLLISION = 330;
    private static final int PART_3_COLLISION = 360;
    private static final int PART_4_COLLISION = 30;
    private static final int PART_5_COLLISION = 60;
    private static final int LEFT_COLLISION = 285;
    private static final int RIGHT_COLLISION = 75;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleShape;
    private Color paddleColor;
    private int paddleWidth;
    private int PaddleSpeed;


    /**
     * set the paddle with the keyboard and color values parameters.
     *
     * @param keyboard the keyboard parameter
     * @param color    the color of the paddle
     */
    public Paddle(KeyboardSensor keyboard, Color color, int speed, int width) {
        //set the paddle to default values
        this.keyboard = keyboard;
        this.paddleColor=color;
        this.PaddleSpeed=speed;
        this.paddleWidth=width;
        this.paddleShape = new Rectangle(new Point((760.0-this.paddleWidth)/2, 565),
                this.paddleWidth, 10, color);
    }

    /**
     * method that moves the paddle to the left if the paddle's left edge
     * is not past the left boundary of the game screen. It first calculates
     * the distance (dx) the paddle can move to the left without going past
     * the boundary. It then creates a new rectangle with the updated upper
     * left point and the same dimensions and color as the original paddle.
     */
    public void moveLeft() {
        if (this.paddleShape.getUpperLeft().getX() > 30) {
            double dx = Math.max(-7, 30 - this.paddleShape.getUpperLeft().getX());
            this.paddleShape = new Rectangle(new Point(
                    this.paddleShape.getUpperLeft().getX() + dx,
                    this.paddleShape.getUpperLeft().getY()),
                    this.paddleShape.getWidth(),
                    this.paddleShape.getHeight(), this.paddleColor);
        }
    }
     /**
     * method that moves the paddle to the right if the paddle's right edge
     * is not past the right boundary of the game screen. It first calculates
     * the distance (dx) the paddle can move to the right without going past
     * the boundary. It then creates a new rectangle with the updated upper
     * left point and the same dimensions and color as the original paddle.
     */
     public void moveRight() {
         if (this.paddleShape.getUpperLeft().getX()
                 + this.paddleShape.getWidth() < 770) {
             double dx = Math.min(7,
                     770 - this.paddleShape.getWidth() -
                             this.paddleShape.getUpperLeft().getX());
             this.paddleShape = new Rectangle(new Point
                     (this.paddleShape.getUpperLeft().getX() + dx,
                     this.paddleShape.getUpperLeft().getY()),
                     this.paddleShape.getWidth(),
                     this.paddleShape.getHeight(), this.paddleColor);
         }
     }
    /**
     * time passed method "plat this step" of the game.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * Draw on method draw the paddle to the screen.
     *
     * @param d the DrawSurface element to draw on it.
     */
    @Override
    public void drawOn(DrawSurface d) {
         /*Draws and fills a Paddle on the received DrawSurface according
           to the color and center fields*/
        this.paddleShape.fillRectangle(d);
    }
    /**
     * the method return the paddle shape from the field.
     *
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleShape;
    }

    /**
     * The "hit" method calculates and returns a new velocity for a ball after
     * colliding with a paddle. It takes into account the position of the
     * collision point and updates the velocity accordingly.
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter,Point collisionPoint, Velocity currentVelocity) {
        // divide the wight to 5 parts
        double newWight = this.paddleShape.getWidth() / DIVISION;
        //calculate the new velocity according to pythagorean theorem
        double newV = Math.sqrt(currentVelocity.getDx()
                * currentVelocity.getDx() + currentVelocity.getDy()
                * currentVelocity.getDy());

        //check if the collision point is in upper side of the paddle
        if (collisionPoint.getY() == paddleShape.getUpperLeft().getY()) {
            //check if the collision point is in the first part
            if (collisionPoint.getX()
                    >= this.paddleShape.getUpperLeft().getX() - EPSILON
                    && collisionPoint.getX()
                    < this.paddleShape.getUpperLeft().getX() + newWight
                    + EPSILON) {
                //Change the angle accordingly
                currentVelocity =
                        Velocity.fromAngleAndSpeed(PART_1_COLLISION, newV);
                return currentVelocity;
            }
            //check if the collision point is in the second part
            if (collisionPoint.getX()
                    >= this.paddleShape.getUpperLeft().getX() + newWight
                    - EPSILON && collisionPoint.getX()
                    < this.paddleShape.getUpperLeft().getX()
                    + 2 * newWight + EPSILON) {
                //Change the angle accordingly
                currentVelocity =
                        Velocity.fromAngleAndSpeed(PART_2_COLLISION, newV);
                return currentVelocity;
            }
            //check if the collision point is in the third part
            if (collisionPoint.getX()
                    >= this.paddleShape.getUpperLeft().getX()
                    + 2 * newWight - EPSILON
                    && collisionPoint.getX()
                    < this.paddleShape.getUpperLeft().getX()
                    + 3 * newWight + EPSILON) {
                //Change the angle accordingly
                currentVelocity =
                        Velocity.fromAngleAndSpeed(PART_3_COLLISION, newV);
                return currentVelocity;
            }
            //check if the collision point is in the four part
            if (collisionPoint.getX()
                    >= this.paddleShape.getUpperLeft().getX()
                    + 3 * newWight - EPSILON
                    && collisionPoint.getX()
                    < this.paddleShape.getUpperLeft().getX()
                    + 4 * newWight + EPSILON) {
                //Change the angle accordingly
                currentVelocity =
                        Velocity.fromAngleAndSpeed(PART_4_COLLISION, newV);
                return currentVelocity;
            }
            //check if the collision point is in the fifth part
            if (collisionPoint.getX()
                    >= this.paddleShape.getUpperLeft().getX()
                    + 4 * newWight - EPSILON
                    && collisionPoint.getX()
                    < this.paddleShape.getUpperLeft().getX()
                    + 5 * newWight + EPSILON) {
                //Change the angle accordingly
                currentVelocity =
                        Velocity.fromAngleAndSpeed(PART_5_COLLISION, newV);
                return currentVelocity;
            }
          // if the collision point is in he left side
        } else if (collisionPoint.getX()
                == paddleShape.getUpperLeft().getX()) {
            if ((collisionPoint.getX()
                    == this.paddleShape.getUpperLeft().getX()
                    && collisionPoint.getY()
                    > this.paddleShape.getUpperLeft().getY()
                    && collisionPoint.getY()
                    < this.paddleShape.getUpperLeft().getY()
                    + this.paddleShape.getHeight())) {
                //Change the angle accordingly
                currentVelocity =
                        Velocity.fromAngleAndSpeed(LEFT_COLLISION, newV);
                return currentVelocity;
            }
          // if the collision point is in he right side
        } else if (collisionPoint.getX()
                == paddleShape.getUpperLeft().getX()
                + paddleShape.getWidth()) {
            if ((collisionPoint.getX()
                    == this.paddleShape.getUpperLeft().getX()
                    + this.paddleShape.getWidth()
                    && collisionPoint.getY()
                    > this.paddleShape.getUpperLeft().getY()
                    && collisionPoint.getY()
                    < this.paddleShape.getUpperLeft().getY()
                    + this.paddleShape.getHeight())) {
                //Change the angle accordingly
                currentVelocity = Velocity.fromAngleAndSpeed(
                        RIGHT_COLLISION, newV);
                return currentVelocity;
            }
        }
        return currentVelocity;
    }

    /**
     * addToGame method add the paddle to the game. first by add the sprite,
     * and then by add the collidable.
     *
     * @param g the game parameter
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }


}