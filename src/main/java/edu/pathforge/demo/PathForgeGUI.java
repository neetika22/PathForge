
package edu.pathforge.demo;

import edu.pathforge.graph.SimpleGraph;
import edu.pathforge.search.*;

import javax.swing.*;
import java.awt.*;

public class PathForgeGUI extends JFrame {

    private final JTextField edgeFrom = new JTextField(5);
    private final JTextField edgeTo = new JTextField(5);
    private final JTextField edgeCost = new JTextField("1", 5);
    private final JTextField startField = new JTextField("A", 5);
    private final JTextField goalField = new JTextField("F", 5);

    private final JComboBox<String> algorithm =
            new JComboBox<>(new String[]{"Dijkstra", "A*", "BFS"});

    private final JTextArea output = new JTextArea(10, 35);

    private final SimpleGraph<String> graph = new SimpleGraph<>();

    public PathForgeGUI() {
        setTitle("PathForge - Graph Search Visualizer");
        setSize(650, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new BorderLayout(12, 12));
        main.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel heading = new JLabel("PATHFORGE", SwingConstants.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 26));
        main.add(heading, BorderLayout.NORTH);

        JPanel controls = new JPanel(new GridLayout(0, 2, 8, 8));
        controls.add(new JLabel("Edge from:"));
        controls.add(edgeFrom);
        controls.add(new JLabel("Edge to:"));
        controls.add(edgeTo);
        controls.add(new JLabel("Edge cost:"));
        controls.add(edgeCost);

        JButton addEdge = new JButton("Add Undirected Edge");
        controls.add(addEdge);
        controls.add(new JLabel(""));

        controls.add(new JLabel("Start node:"));
        controls.add(startField);
        controls.add(new JLabel("Goal node:"));
        controls.add(goalField);
        controls.add(new JLabel("Algorithm:"));
        controls.add(algorithm);

        JButton findPath = new JButton("Find Path");
        controls.add(findPath);

        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JPanel center = new JPanel(new BorderLayout(8, 8));
        center.add(controls, BorderLayout.NORTH);
        center.add(new JScrollPane(output), BorderLayout.CENTER);

        main.add(center, BorderLayout.CENTER);
        add(main);

        addEdge.addActionListener(e -> {
            try {
                String from = edgeFrom.getText().trim();
                String to = edgeTo.getText().trim();
                double cost = Double.parseDouble(edgeCost.getText().trim());

                if (from.isEmpty() || to.isEmpty()) {
                    throw new IllegalArgumentException("Enter both nodes.");
                }

                graph.addUndirectedEdge(from, to, cost);
                output.append("Added edge: " + from + " <-> " + to
                        + " (cost " + cost + ")\n");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(), "Invalid edge",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        findPath.addActionListener(e -> search());
    }

    private void search() {
        try {
            String start = startField.getText().trim();
            String goal = goalField.getText().trim();

            PathResult<String> result;
            String selected = (String) algorithm.getSelectedItem();

            if ("BFS".equals(selected)) {
                result = new BreadthFirstSearch<String>()
                        .findPath(graph, start, goal);
            } else if ("A*".equals(selected)) {
                Heuristic<String> heuristic = new Heuristic<String>() {
                    @Override
                    public double estimate(String node, String target) {
                        return 0.0;
                    }
                };

                result = new AStarSearch<String>(heuristic)
                        .findPath(graph, start, goal);
            } else {
                result = new DijkstraSearch<String>()
                        .findPath(graph, start, goal);
            }

            output.append("\n--- " + selected + " Result ---\n");
            output.append(result.toString() + "\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Search error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PathForgeGUI().setVisible(true);
        });
    }
}

