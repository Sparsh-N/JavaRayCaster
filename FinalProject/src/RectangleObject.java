import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static javax.sound.sampled.AudioSystem.getLine;

public class RectangleObject implements Drawable {
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;

    public RectangleObject(final Point2D.Double coordinates, final double width, final double height, final Color color) {
        this.x = coordinates.x;
        this.y = coordinates.y;
        this.width = width;
        this.height = height;
        this.color = color;

        Line2D.Double upperLine = new Line2D.Double(this.x, this.y, this.x + this.width, this.y);
        Line2D.Double lowerLine = new Line2D.Double(this.x, this.y + this.height, this.x + this.width, this.y + this.height);
        Line2D.Double leftLine = new Line2D.Double(this.x, this.y, this.x, this.y + this.height);
        Line2D.Double rightLine = new Line2D.Double(this.x + this.width, this.y, this.x + this.width, this.y + this.height);

        lines.add(upperLine);
        lines.add(lowerLine);
        lines.add(leftLine);
        lines.add(rightLine);
    }

    Rectangle2D.Double getShape() {
        return new Rectangle2D.Double(this.x, this.y, this.width, this.height);
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public void drawObject(Graphics2D g2d) {
        g2d.setColor(this.color);
        g2d.fill(this.getShape());
    }
    ArrayList<Line2D.Double> lines = new ArrayList<>();

    public Point2D.Double rectIntersection(final Ray ray) {
        ArrayList<Line2D.Double> lineSegments = (ArrayList<Line2D.Double>) this.lines;
        Point2D.Double minPt = null;
        double minDist = Integer.MAX_VALUE;
        for (int i = 0; i < lineSegments.size(); i++) {

            Line2D.Double line = lineSegments.get(i);
            if (ray.getLine().intersectsLine(line)) {
                Point2D.Double currMinPt = RaycasterUtils.intersection(line, ray.getLine());
                double currMinDist = line.ptSegDist(ray.getx1(), ray.gety1());
                if (currMinDist <= minDist) {
                    minPt = currMinPt;
                    minDist = currMinDist;
                }
            }
        }

        return minPt;
    }
}