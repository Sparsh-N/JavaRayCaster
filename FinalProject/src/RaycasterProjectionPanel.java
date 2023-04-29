import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RaycasterProjectionPanel extends JPanel {

    /**
     * Root driver object to keep track of sizing.
     */
    private final RaycasterRunner RUNNER;

    /**
     * Overhead panel to access the generated rays.
     */
    private final RaycasterPanel RAYCASTER_PANEL;

    public RaycasterProjectionPanel(final RaycasterRunner raycasterRunner, final RaycasterPanel raycasterPanel) {
        this.RUNNER = raycasterRunner;
        this.setPreferredSize(new Dimension(this.RUNNER.getWidth() / 2, this.RUNNER.getHeight()));
        this.RAYCASTER_PANEL = raycasterPanel;
    }

    public void update() {
    }

    private void project(Graphics2D g) {
        for (int i = 0; i < RAYCASTER_PANEL.getCamera().getRays().size(); i++) {

            final double projectionHeight = this.RUNNER.getHeight();
            final double projectionWidth = (double) this.RUNNER.getWidth() / 2;
            double wallX = Math.abs(RaycasterUtils.normalize(i, 1, RAYCASTER_PANEL.getCamera().getRays().size(), 0, projectionWidth));
            double wallHeight = projectionHeight * (40 / RAYCASTER_PANEL.getCamera().getRays().get(i).getDistance());
            double wallY = (projectionHeight / 2) - (wallHeight / 2.0);

            g.setColor(Color.WHITE);
            g.drawRect((int) wallX, (int) wallY, 1, (int) wallHeight);
        }
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D g2d = (Graphics2D) g;
        project(g2d);
    }
}
