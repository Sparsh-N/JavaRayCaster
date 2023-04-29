import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Camera {
    private double angle;
    private double centerX;
    private double centerY;
    private ArrayList<Ray> rays;

    CameraMotionListener cameraMotionListener;

    private class cameraAngleListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                angle -= 2;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                angle += 2;
            }
        }
    }

    private cameraAngleListener cameraAngleListener;

    public Camera(final double centerX, final double centerY, double angle, ArrayList<Ray> rays) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.angle = angle;
        this.rays = rays;
        cameraAngleListener = new cameraAngleListener();
    }

    public cameraAngleListener getCameraAngleListener() {
        return this.cameraAngleListener;
    }

    public double getAngle() {
        return this.angle;
    }

    public double getCenterX() {
        return this.centerX;
    }

    public double getCenterY() {
        return this.centerY;
    }

    public double setAngle(final double angle) {
        return this.angle = angle;
    }

    public ArrayList<Ray> getRays() {
        return this.rays;
    }

    public ArrayList<Ray> setRays(ArrayList<Ray> rays) {
        return this.rays = rays;
    }

    public Point2D getCenter() {
        return new Point2D.Double(this.centerX, this.centerY);
    }

    private class CameraMotionListener extends MouseAdapter {
        private Camera camera;

        public CameraMotionListener(Camera c) {
            this.camera = c;
        }
        @Override
        public void mouseMoved(final MouseEvent e) {
            this.camera.centerX = e.getX();
            this.camera.centerY = e.getY();
        }
    }

    public CameraMotionListener getCameraMotionListener() {
        return new CameraMotionListener(this);
    }

    public void drawCamera(final Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval((int) this.centerX - 5, (int) this.centerY - 5, 10, 10);
   }

    public void addRay(final Ray ray) {

    }
}
