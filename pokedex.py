import requests
import tkinter as tk
from tkinter import Label
from PIL import Image, ImageTk
from io import BytesIO

# Function to fetch Pokémon data by name
def get_pokemon_data(pokemon_name):
    url = f'https://pokeapi.co/api/v2/pokemon/{pokemon_name.lower()}/'
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        return {
            'name': data['name'],
            'height': data['height'],
            'weight': data['weight'],
            'abilities': [ability['ability']['name'] for ability in data['abilities']],
            'sprite_url': data['sprites']['front_default'],
        }
    else:
        return None

# Function to display Pokémon data in the GUI
def display_pokemon_data(pokemon_name):
    pokemon_data = get_pokemon_data(pokemon_name)
    if pokemon_data:
        # Display Pokémon data
        name_label.config(text=f"Name: {pokemon_data['name'].capitalize()}")
        height_label.config(text=f"Height: {pokemon_data['height']}")
        weight_label.config(text=f"Weight: {pokemon_data['weight']}")
        abilities_label.config(text=f"Abilities: {', '.join(pokemon_data['abilities'])}")

        # Display Pokémon sprite
        response = requests.get(pokemon_data['sprite_url'])
        img_data = response.content
        img = Image.open(BytesIO(img_data))
        img = img.resize((150, 150))  # Resize image to fit the window
        img_tk = ImageTk.PhotoImage(img)
        image_label.config(image=img_tk)
        image_label.image = img_tk  # Keep reference to avoid garbage collection
    else:
        name_label.config(text="Pokémon not found.")
        image_label.config(image='')

# Set up Tkinter window
root = tk.Tk()
root.title("Pokédex")

# Labels for Pokémon data
name_label = Label(root, text="Name:")
name_label.pack()

height_label = Label(root, text="Height:")
height_label.pack()

weight_label = Label(root, text="Weight:")
weight_label.pack()

abilities_label = Label(root, text="Abilities:")
abilities_label.pack()

# Label to display Pokémon sprite
image_label = Label(root)
image_label.pack()

# Entry to input Pokémon name
pokemon_entry = tk.Entry(root)
pokemon_entry.pack()

# Button to trigger the data fetch
fetch_button = tk.Button(root, text="Fetch Pokémon", command=lambda: display_pokemon_data(pokemon_entry.get()))
fetch_button.pack()

# Run the Tkinter event loop
root.mainloop()
