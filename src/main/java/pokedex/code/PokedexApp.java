package pokedex.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PokedexApp {
    public static void main(String[] args) {
        // Create JFrame for GUI
        JFrame frame = new JFrame("Pokédex");
        frame.setSize(600, 600); // Increase the size for a larger display
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Use absolute layout for more control

        // Set background color (Pokédex red)
        frame.getContentPane().setBackground(Color.RED);

        // Create components for the GUI with a futuristic look
        JTextField pokemonEntry = new JTextField();
        JButton fetchButton = new JButton("Fetch Pokémon");

        JLabel nameLabel = new JLabel("Name: ");
        JLabel heightLabel = new JLabel("Height: ");
        JLabel weightLabel = new JLabel("Weight: ");
        JLabel abilitiesLabel = new JLabel("Abilities: ");
        JLabel imageLabel = new JLabel();

        // Set custom fonts and colors for labels
        Font font = new Font("Arial", Font.BOLD, 14);
        nameLabel.setFont(font);
        heightLabel.setFont(font);
        weightLabel.setFont(font);
        abilitiesLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        heightLabel.setForeground(Color.WHITE);
        weightLabel.setForeground(Color.WHITE);
        abilitiesLabel.setForeground(Color.WHITE);

        // Customize button
        fetchButton.setBackground(Color.DARK_GRAY);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setFont(new Font("Arial", Font.BOLD, 12));

        // Set bounds for each component (manual positioning)
        pokemonEntry.setBounds(200, 20, 200, 30);
        fetchButton.setBounds(420, 20, 150, 30);
        nameLabel.setBounds(50, 100, 300, 30);
        heightLabel.setBounds(50, 150, 300, 30);
        weightLabel.setBounds(50, 200, 300, 30);
        abilitiesLabel.setBounds(50, 250, 500, 30);
        imageLabel.setBounds(300, 100, 150, 150);

        // Add components to the frame
        frame.add(pokemonEntry);
        frame.add(fetchButton);
        frame.add(nameLabel);
        frame.add(heightLabel);
        frame.add(weightLabel);
        frame.add(abilitiesLabel);
        frame.add(imageLabel);

        // Add button click action
        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pokemonName = pokemonEntry.getText();
                PokemonDisplay display = new PokemonDisplay(nameLabel, heightLabel, weightLabel, abilitiesLabel, imageLabel);
                display.displayPokemonData(pokemonName);
            }
        });

        // Display the window
        frame.setVisible(true);
    }
}
