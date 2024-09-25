package pokedex.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PokedexApp {
    public static void main(String[] args) {
        // Create JFrame for GUI
        JFrame frame = new JFrame("Pokédex");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 1));

        // Create components for the GUI
        JLabel nameLabel = new JLabel("Name: ");
        JLabel heightLabel = new JLabel("Height: ");
        JLabel weightLabel = new JLabel("Weight: ");
        JLabel abilitiesLabel = new JLabel("Abilities: ");
        JLabel imageLabel = new JLabel();

        JTextField pokemonEntry = new JTextField();
        JButton fetchButton = new JButton("Fetch Pokémon");

        // Add components to the frame
        frame.add(pokemonEntry);
        frame.add(fetchButton);
        frame.add(nameLabel);
        frame.add(heightLabel);
        frame.add(weightLabel);
        frame.add(abilitiesLabel);
        frame.add(imageLabel);

        // Create an instance of PokemonDisplay for handling data display
        PokemonDisplay display = new PokemonDisplay(nameLabel, heightLabel, weightLabel, abilitiesLabel, imageLabel);

        // Add button click action
        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pokemonName = pokemonEntry.getText();
                display.displayPokemonData(pokemonName);
            }
        });

        // Display the window
        frame.setVisible(true);
    }
}
