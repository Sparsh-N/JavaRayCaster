import javax.lang.model.type.ArrayType;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Displays and updates the logic for the top-down raycasting implementation.
 * This class is where the collision detection and movement occurs, whereas the
 * RaycasterPerspectivePanel just projects it to a pseudo-3d environment.
 */

public final class RaycasterPanel extends JPanel {

    /**
     * We need to keep a reference to the parent swing app for sizing and
     * other bookkeeping.
     */
    private final RaycasterRunner RUNNER;

    /**
     * Number of rays to fire from the camera.
     */
    private final int RESOLUTION;
    ArrayList<Ray> rays = new ArrayList<Ray>();
    private Camera cam = new Camera(150,150, 150, rays);

    public RaycasterPanel(final RaycasterRunner raycasterRunner) {
        this.RUNNER = raycasterRunner;
        this.setPreferredSize(new Dimension(this.RUNNER.getWidth() / 2, this.RUNNER.getHeight()));
        this.RESOLUTION = this.getPreferredSize().width;
        this.addMouseMotionListener(this.cam.getCameraMotionListener());
        this.addKeyListener(this.cam.getCameraAngleListener());
        this.requestFocusInWindow(true);

        objects.add(object1);
        objects.add(object2);
        objects.add(object3);
        objects.add(object4);
        objects.add(object5);
        objects.add(object6);
        objects.add(object7);
        objects.add(object8);
    }

    public void update() {
    }

    public Camera getCamera() {
        return cam;
    }

    Point2D.Double points1 = new Point2D.Double(RaycasterUtils.randomDouble(0,10) * 50,
                                                RaycasterUtils.randomDouble(0,10) * 50);
    Point2D.Double points2 = new Point2D.Double(RaycasterUtils.randomDouble(0,10) * 50,
                                                RaycasterUtils.randomDouble(0,10) * 50);
    Point2D.Double points3 = new Point2D.Double(RaycasterUtils.randomDouble(0,10) * 50,
                                                RaycasterUtils.randomDouble(0,10) * 50);
    Point2D.Double points4 = new Point2D.Double(RaycasterUtils.randomDouble(0,10) * 50,
                                                RaycasterUtils.randomDouble(0,10) * 50);
    Point2D.Double points5 = new Point2D.Double(RaycasterUtils.randomDouble(0,10) * 50,
                                                RaycasterUtils.randomDouble(0,10) * 50);
    Point2D.Double points6 = new Point2D.Double(RaycasterUtils.randomDouble(0,10) * 50,
                                                RaycasterUtils.randomDouble(0,10) * 50);
    Point2D.Double points7 = new Point2D.Double(RaycasterUtils.randomDouble(0,10) * 50,
                                                RaycasterUtils.randomDouble(0,10) * 50);
    Point2D.Double points8 = new Point2D.Double(RaycasterUtils.randomDouble(0,10) * 50,
                                                RaycasterUtils.randomDouble(0,10) * 50);
    ArrayList<RectangleObject> objects = new ArrayList<>();
    RectangleObject object1 = new RectangleObject(points1, 100, 50, Color.RED);
    RectangleObject object2 = new RectangleObject(points2, 50, 150, Color.BLUE);
    RectangleObject object3 = new RectangleObject(points3, 50, 30, Color.GREEN);
    RectangleObject object4 = new RectangleObject(points4, 20, 50, Color.GRAY);
    RectangleObject object5 = new RectangleObject(points5, 250, 20, Color.CYAN);
    RectangleObject object6 = new RectangleObject(points6, 150, 30, Color.MAGENTA);
    RectangleObject object7 = new RectangleObject(points7, 20, 30, Color.YELLOW);
    RectangleObject object8 = new RectangleObject(points8, 50, 50, Color.ORANGE);


    public void computeRays(ArrayList<Ray> rays) {
        for (int i = 0; i < RESOLUTION; i++) {
            double angle = RaycasterUtils.normalize(i, 0, RESOLUTION, cam.getAngle() - 70 / 2, cam.getAngle() + 70 / 2);
            double minDist = Integer.MAX_VALUE;
            angle = Math.toRadians(angle);
            Point2D.Double minPt = null;
            Ray rectRay = new Ray(new Line2D.Double(cam.getCenterX(), cam.getCenterY(), cam.getCenterX() + 1280 * Math.cos(angle), cam.getCenterY() + 1280 * Math.sin(angle)), Color.WHITE);
            for (RectangleObject rect : objects) {
                Point2D.Double currPt = rect.rectIntersection(rectRay);
                double currDist;
                if (currPt!=null)
                    currDist = cam.getCenter().distance(currPt);
                else
                    currDist = Integer.MAX_VALUE;
                if (currPt != null && minDist >= currDist) {
                    minPt = currPt;
                    minDist = currDist;
                }
            }

            if (minPt != null) {
                rectRay.setEndX(minPt.x);
                rectRay.setEndY(minPt.y);
            }
            rays.add(rectRay);
        }
    }

    private void drawRay(Graphics2D g2d) {
        for (Ray ray : cam.getRays()) {
            g2d.setColor(Color.WHITE);
            g2d.draw(ray.getLine());
        }
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        object1.drawObject(g2d);
        object2.drawObject(g2d);
        object3.drawObject(g2d);
        object4.drawObject(g2d);
        object5.drawObject(g2d);
        object6.drawObject(g2d);
        object7.drawObject(g2d);
        object8.drawObject(g2d);

        cam.drawCamera(g2d);
        rays.clear();
        computeRays(rays);
        drawRay(g2d);
    }
}