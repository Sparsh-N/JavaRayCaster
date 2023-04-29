import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Ray {
    private Line2D.Double line;
    private Color color;

    public Ray(final Line2D.Double line, Color color) {
        this.line = line;
        this.color = color;
    }

    public void drawRay(final Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.drawLine((int) this.line.x1, (int) this.line.y1, (int) this.line.x2, (int) this.line.y2);
    }

    public Color getColor() {
        return this.color;
    }

    public int getx1() {
        return (int) this.line.x1;
    }

    public int gety1() {
        return (int) this.line.y1;
    }

    public Point2D.Double getOrigin() {
        return new Point2D.Double(this.line.x1, this.line.y1);
    }

    public Line2D.Double getLine() {
        return this.line;
    }

    public void setEndX(double x) {
        this.line.x2 = x;
    }

    public void setEndY(double y) {
        this.line.y2 = y;
    }
    public void setDistance(double distance) {
        this.line.x2 = this.line.x1 + distance * Math.cos(this.line.x2);
        this.line.y2 = this.line.y1 + distance * Math.sin(this.line.y2);
    }

    public double getDistance() {
        double distance = Math.sqrt(Math.pow(this.line.x2 - this.line.x1, 2) + Math.pow(this.line.y2 - this.line.y1, 2));
        return distance;
    }
}
