package org.example;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.json.*;

public class PokedexApp {
    // Method to fetch Pokémon data from the PokéAPI
    public static JSONObject getPokemonData(String pokemonName) {
        try {
            String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonName.toLowerCase();
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check if the response is successful
            if (connection.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Return the JSON data
                return new JSONObject(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to display Pokémon data in the GUI
    public static void displayPokemonData(String pokemonName, JLabel nameLabel, JLabel heightLabel, JLabel weightLabel, JLabel abilitiesLabel, JLabel imageLabel) {
        JSONObject pokemonData = getPokemonData(pokemonName);

        if (pokemonData != null) {
            // Set the basic Pokémon data
            nameLabel.setText("Name: " + pokemonData.getString("name").toUpperCase());
            heightLabel.setText("Height: " + pokemonData.getInt("height"));
            weightLabel.setText("Weight: " + pokemonData.getInt("weight"));

            // Get abilities
            JSONArray abilities = pokemonData.getJSONArray("abilities");
            StringBuilder abilitiesText = new StringBuilder("Abilities: ");
            for (int i = 0; i < abilities.length(); i++) {
                JSONObject abilityObj = abilities.getJSONObject(i).getJSONObject("ability");
                abilitiesText.append(abilityObj.getString("name"));
                if (i < abilities.length() - 1) abilitiesText.append(", ");
            }
            abilitiesLabel.setText(abilitiesText.toString());

            // Fetch and display Pokémon sprite
            String spriteUrl = pokemonData.getJSONObject("sprites").getString("front_default");
            try {
                URL imageUrl = new URL(spriteUrl);
                Image pokemonImage = ImageIO.read(imageUrl);
                ImageIcon icon = new ImageIcon(pokemonImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                imageLabel.setIcon(icon);
            } catch (IOException e) {
                e.printStackTrace();
                imageLabel.setText("Image not found.");
            }
        } else {
            nameLabel.setText("Pokémon not found.");
            heightLabel.setText("");
            weightLabel.setText("");
            abilitiesLabel.setText("");
            imageLabel.setIcon(null);
        }
    }

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

        // Add button click action
        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pokemonName = pokemonEntry.getText();
                displayPokemonData(pokemonName, nameLabel, heightLabel, weightLabel, abilitiesLabel, imageLabel);
            }
        });

        // Display the window
        frame.setVisible(true);
    }
}
