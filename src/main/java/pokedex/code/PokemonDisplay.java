package pokedex.code;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class PokemonDisplay {
    private JLabel nameLabel;
    private JLabel heightLabel;
    private JLabel weightLabel;
    private JLabel abilitiesLabel;
    private JLabel imageLabel;

    public PokemonDisplay(JLabel nameLabel, JLabel heightLabel, JLabel weightLabel, JLabel abilitiesLabel, JLabel imageLabel) {
        this.nameLabel = nameLabel;
        this.heightLabel = heightLabel;
        this.weightLabel = weightLabel;
        this.abilitiesLabel = abilitiesLabel;
        this.imageLabel = imageLabel;
    }

    // Method to display Pokémon data in the GUI
    public void displayPokemonData(String pokemonName) {
        JSONObject pokemonData = PokemonFetcher.getPokemonData(pokemonName);

        if (pokemonData != null) {
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
}
