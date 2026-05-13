import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * DESCRIPTION:
 * EcoMap is a GIS-lite application that allows users to digitize geographic points.
 * 
 * Technical Requirements Satisfaction:
 * 1) Swing: Uses JComboBox (Theme), JCheckBox (Grid toggle), and JLabel (Coordinates).
 * 2) 2D Graphics: Custom JPanel draws a coordinate grid and user-placed "Data Points".
 * 3) Listeners: An Action Listener updates the theme; a Mouse Listener places points; 
 *    a Mouse Motion Listener updates the real-time coordinate display.
 */
public class Project2Runner {
    public static void main(String[] args) {
        // Create the driver instance as suggested in Screenshot 2026-01-20 103215.jpg
        new EcoMapFrame();
    }
}

/**
 * The main window frame for the GIS application.
 */
class EcoMapFrame extends JFrame {
    private MapPanel mapPanel;
    private JLabel coordLabel;
    private JComboBox<String> themeBox;
    private JCheckBox gridToggle;

    public EcoMapFrame() {
        setTitle("EcoMap GIS Digitizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1. Setup Map Panel (The Canvas)
        mapPanel = new MapPanel();
        add(mapPanel, BorderLayout.CENTER);

        // 2. Setup Control Panel (Swing Components)
        JPanel controls = new JPanel();
        coordLabel = new JLabel("Lat: 0.00, Lon: 0.00");
        
        String[] themes = {"Blueprint", "Terrain", "Dark Mode"};
        themeBox = new JComboBox<>(themes);
        
        gridToggle = new JCheckBox("Show Grid", true);

        // Action Listener for JComboBox
        themeBox.addActionListener(e -> {
            mapPanel.setTheme((String) themeBox.getSelectedItem());
        });

        // Action Listener for JCheckBox
        gridToggle.addActionListener(e -> {
            mapPanel.setGridVisible(gridToggle.isSelected());
        });

        controls.add(new JLabel("Map Theme:"));
        controls.add(themeBox);
        controls.add(gridToggle);
        controls.add(coordLabel);
        add(controls, BorderLayout.SOUTH);

        // Mouse Motion Listener for Coordinates
        mapPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Simulating GIS decimal degrees conversion
                double lat = 90.0 - (e.getY() / 6.0);
                double lon = (e.getX() / 4.4) - 180.0;
                coordLabel.setText(String.format("Lat: %.2f, Lon: %.2f", lat, lon));
            }
        });

        setVisible(true);
    }
}

/**
 * Custom JPanel where GIS data is rendered using 2D Graphics.
 */
class MapPanel extends JPanel {
    private ArrayList<Point> dataPoints = new ArrayList<>();
    private String currentTheme = "Blueprint";
    private boolean showGrid = true;

    public MapPanel() {
        setBackground(new Color(30, 50, 100)); // Default Blueprint
        
        // Mouse Listener to "digitize" points
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dataPoints.add(e.getPoint());
                repaint(); // Redraw with new point
            }
        });
    }

    public void setTheme(String theme) {
        this.currentTheme = theme;
        if (theme.equals("Blueprint")) setBackground(new Color(30, 50, 100));
        else if (theme.equals("Terrain")) setBackground(new Color(34, 139, 34));
        else setBackground(Color.BLACK);
        repaint();
    }

    public void setGridVisible(boolean visible) {
        this.showGrid = visible;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw Grid (2D Shapes)
        if (showGrid) {
            g2.setColor(new Color(255, 255, 255, 50));
            for (int i = 0; i < getWidth(); i += 50) g2.drawLine(i, 0, i, getHeight());
            for (int i = 0; i < getHeight(); i += 50) g2.drawLine(0, i, getWidth(), i);
        }

        // Draw Data Points
        g2.setColor(Color.YELLOW);
        for (Point p : dataPoints) {
            // Drawing circles to represent digitized features
            g2.fillOval(p.x - 5, p.y - 5, 10, 10);
            g2.drawOval(p.x - 8, p.y - 8, 16, 16);
        }
    }
}