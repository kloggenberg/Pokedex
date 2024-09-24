import requests

# Function to fetch Pokémon data by name
def get_pokemon_data(pokemon_name):
    url = f'https://pokeapi.co/api/v2/pokemon/{pokemon_name.lower()}/'
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        # Extract relevant data
        pokemon_data = {
            'name': data['name'],
            'height': data['height'],
            'weight': data['weight'],
            'abilities': [ability['ability']['name'] for ability in data['abilities']],
            'sprites': {
                'front_default': data['sprites']['front_default'],
                'front_shiny': data['sprites']['front_shiny'],
                'back_default': data['sprites']['back_default'],
                'back_shiny': data['sprites']['back_shiny'],
            }
        }
        return pokemon_data
    else:
        return f"Pokémon {pokemon_name} not found."

# Example usage
pokemon_name = 'Pikachu'
pokemon_data = get_pokemon_data(pokemon_name)

if isinstance(pokemon_data, dict):
    print(f"Name: {pokemon_data['name']}")
    print(f"Height: {pokemon_data['height']}")
    print(f"Weight: {pokemon_data['weight']}")
    print(f"Abilities: {', '.join(pokemon_data['abilities'])}")
    print("Sprites:")
    for sprite_name, sprite_url in pokemon_data['sprites'].items():
        print(f"{sprite_name}: {sprite_url}")
else:
    print(pokemon_data)
