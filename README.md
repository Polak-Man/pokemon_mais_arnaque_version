```
CREATE TABLE pokemon_cards (
    card_id INT AUTO_INCREMENT PRIMARY KEY,
    card_name VARCHAR(255) NOT NULL,
    edition VARCHAR(50) NOT NULL,
    set_name VARCHAR(100) NOT NULL,
    card_type VARCHAR(50),
    hp INT,
    attack VARCHAR(255),
    rarity VARCHAR(50),
    description TEXT
);

INSERT INTO pokemon_cards (card_id, card_name, edition, set_name, card_type, hp, attack, rarity, description) VALUES

(1, 'Bulbasaur', '1st Edition', 'Base Set', 'Grass', 40, 49, 'Common', 'A seed Pokémon that can grow into a large plant.'),

(2, 'Ivysaur', '1st Edition', 'Base Set', 'Grass', 60, 62, 'Uncommon', 'The evolved form of Bulbasaur, with a large flower.'),

(3, 'Venusaur', '1st Edition', 'Base Set', 'Grass', 80, 100, 'Rare', 'A large Pokémon with a massive flower on its back.'),

(4, 'Charmander', '1st Edition', 'Base Set', 'Fire', 39, 52, 'Common', 'A small fire Pokémon that evolves into Charmeleon.'),

(5, 'Charmeleon', '1st Edition', 'Base Set', 'Fire', 58, 64, 'Uncommon', 'The evolved form of Charmander, known for its fiery tail.'),

(6, 'Charizard', '1st Edition', 'Base Set', 'Fire', 78, 100, 'Rare', 'A powerful Pokémon known for its fiery breath.'),

(7, 'Squirtle', '1st Edition', 'Base Set', 'Water', 44, 48, 'Common', 'A small water Pokémon that evolves into Wartortle.'),

(8, 'Wartortle', '1st Edition', 'Base Set', 'Water', 59, 63, 'Uncommon', 'The evolved form of Squirtle, known for its fluffy tail.'),

(9, 'Blastoise', '1st Edition', 'Base Set', 'Water', 79, 83, 'Rare', 'A Pokémon that can shoot water at high pressure.'),

(10, 'Caterpie', '1st Edition', 'Base Set', 'Bug', 50, 30, 'Common', 'A small bug Pokémon that evolves into Metapod.'),

(11, 'Metapod', '1st Edition', 'Base Set', 'Bug', 50, 20, 'Uncommon', 'The evolved form of Caterpie, known for its hard shell.'),

(12, 'Butterfree', '1st Edition', 'Base Set', 'Bug', 60, 45, 'Rare', 'A butterfly Pokémon that can use powerful moves.'),

(13, 'Weedle', '1st Edition', 'Base Set', 'Bug', 40, 35, 'Common', 'A small bug Pokémon that evolves into Kakuna.'),

(14, 'Kakuna', '1st Edition', 'Base Set', 'Bug', 45, 10, 'Uncommon', 'The evolved form of Weedle, known for its hard shell.'),

(15, 'Beedrill', '1st Edition', 'Base Set', 'Bug', 65, 90, 'Rare', 'A bee Pokémon that can use powerful stings.'),

(16, 'Pidgey', '1st Edition', 'Base Set', 'Normal', 40, 45, 'Common', 'A small bird Pokémon that evolves into Pidgeotto.'),

(17, 'Pidgeotto', '1st Edition', 'Base Set', 'Normal', 63, 60, 'Uncommon', 'The evolved form of Pidgey, known for its speed.'),

(18, 'Pidgeot', '1st Edition', 'Base Set', 'Normal', 83, 80, 'Rare', 'A large bird Pokémon that can fly at high speeds.'),

(19, 'Rattata', '1st Edition', 'Base Set', 'Normal', 30, 56, 'Common', 'A small rat Pokémon that is very common.'),

(20, 'Raticate', '1st Edition', 'Base Set', 'Normal', 55, 81, 'Uncommon', 'The evolved form of Rattata, known for its sharp teeth.'),

(21, 'Spearow', '1st Edition', 'Base Set', 'Normal', 40, 60, 'Common', 'A small bird Pokémon that is known for its aggressive nature.'),

(22, 'Fearow', '1st Edition', 'Base Set', 'Normal', 65, 90, 'Uncommon', 'The evolved form of Spearow, known for its speed.'),

(23, 'Ekans', '1st Edition', 'Base Set', 'Poison', 35, 60, 'Common', 'A snake Pokémon that can be very sneaky.'),

(24, 'Arbok', '1st Edition', 'Base Set', 'Poison', 60, 95, 'Uncommon', 'The evolved form of Ekans, known for its intimidating presence.'),

(25, 'Pikachu', '1st Edition', 'Base Set', 'Electric', 35, 55, 'Common', 'A small, adorable Pokémon that generates electricity.'),

(26, 'Raichu', '1st Edition', 'Base Set', 'Electric', 60, 90, 'Uncommon', 'The evolved form of Pikachu, known for its speed.'),

(27, 'Sandshrew', '1st Edition', 'Base Set', 'Ground', 50, 75, 'Common', 'A small ground Pokémon that can burrow underground.'),

(28, 'Sandslash', '1st Edition', 'Base Set', 'Ground', 75, 100, 'Uncommon', 'The evolved form of Sandshrew, known for its sharp claws.'),

(29, 'Nidoran♀', '1st Edition', 'Base Set', 'Poison', 55, 40, 'Common', 'A small Pokémon with a horn on its head.'),

(30, 'Nidorina', '1st Edition', 'Base Set', 'Poison', 70, 55, 'Uncommon', 'The evolved form of Nidoran♀, known for its agility.'),

(31, 'Nidoqueen', '1st Edition', 'Base Set', 'Poison', 90, 75, 'Rare', 'A powerful Pokémon that can use various moves.'),

(32, 'Nidoran♂', '1st Edition', 'Base Set', 'Poison', 55, 40, 'Common', 'A small Pokémon with a horn on its head.'),

(33, 'Nidorino', '1st Edition', 'Base Set', 'Poison', 70, 65, 'Uncommon', 'The evolved form of Nidoran♂, known for its strength.'),

(34, 'Nidoking', '1st Edition', 'Base Set', 'Poison', 90, 102, 'Rare', 'A powerful Pokémon that can use various moves.'),

(35, 'Clefairy', '1st Edition', 'Base Set', 'Fairy', 70, 45, 'Common', 'A small Pokémon known for its cute appearance.'),

(36, 'Clefable', '1st Edition', 'Base Set', 'Fairy', 95, 70, 'Uncommon', 'The evolved form of Clefairy, known for its magical abilities.'),

(37, 'Vulpix', '1st Edition', 'Base Set', 'Fire', 38, 41, 'Common', 'A small fox Pokémon with six orange tails.'),

(38, 'Ninetales', '1st Edition', 'Base Set', 'Fire', 73, 76, 'Rare', 'A mystical Pokémon with nine tails.'),

(39, 'Jigglypuff', '1st Edition', 'Base Set', 'Normal', 115, 45, 'Common', 'A balloon-like Pokémon that can put others to sleep.'),

(40, 'Wigglytuff', '1st Edition', 'Base Set', 'Normal', 140, 70, 'Uncommon', 'The evolved form of Jigglypuff, known for its singing.'),

(41 , 'Zubat', '1st Edition', 'Base Set', 'Poison', 40, 45, 'Common', 'A bat Pokémon that is often found in caves.'),

(42, 'Golbat', '1st Edition', 'Base Set', 'Poison', 75, 80, 'Uncommon', 'The evolved form of Zubat, known for its speed.'),

(43, 'Oddish', '1st Edition', 'Base Set', 'Grass', 45, 40, 'Common', 'A small plant Pokémon that can be found in gardens.'),

(44, 'Gloom', '1st Edition', 'Base Set', 'Grass', 60, 55, 'Uncommon', 'The evolved form of Oddish, known for its stinky smell.'),

(45, 'Vileplume', '1st Edition', 'Base Set', 'Grass', 75, 65, 'Rare', 'A large Pokémon with a massive flower on its head.'),

(46, 'Paras', '1st Edition', 'Base Set', 'Bug', 35, 70, 'Common', 'A small bug Pokémon that has mushrooms growing on its back.'),

(47, 'Parasect', '1st Edition', 'Base Set', 'Bug', 60, 95, 'Uncommon', 'The evolved form of Paras, known for its mushrooms.'),

(48, 'Venonat', '1st Edition', 'Base Set', 'Bug', 60, 55, 'Common', 'A bug Pokémon with large eyes.'),

(49, 'Venomoth', '1st Edition', 'Base Set', 'Bug', 70, 65, 'Uncommon', 'The evolved form of Venonat, known for its wings.'),

(50, 'Diglett', '1st Edition', 'Base Set', 'Ground', 10, 50, 'Common', 'A small ground Pokémon that can burrow underground.'),

(51, 'Dugtrio', '1st Edition', 'Base Set', 'Ground', 35, 80, 'Uncommon', 'The evolved form of Diglett, known for its speed.'),

(52, 'Meowth', '1st Edition', 'Base Set', 'Normal', 40, 45, 'Common', 'A small cat Pokémon that is known for its charm.'),

(53, 'Persian', '1st Edition', 'Base Set', 'Normal', 65, 70, 'Uncommon', 'The evolved form of Meowth, known for its elegance.'),

(54, 'Psyduck', '1st Edition', 'Base Set', 'Water', 50, 52, 'Common', 'A duck Pokémon that often suffers from headaches.'),

(55, 'Golduck', '1st Edition', 'Base Set', 'Water', 80, 75, 'Uncommon', 'The evolved form of Psyduck, known for its swimming ability.'),

(56, 'Poliwag', '1st Edition', 'Base Set', 'Water', 40, 50, 'Common', 'A small tadpole Pokémon.'),

(57, 'Poliwhirl', '1st Edition', 'Base Set', 'Water', 60, 65, 'Uncommon', 'The evolved form of Poliwag, known for its swirling pattern.'),

(58, 'Poliwrath', '1st Edition', 'Base Set', 'Water', 90, 95, 'Rare', 'A powerful Pokémon that can use various moves.'),

(59, 'Abra', '1st Edition', 'Base Set', 'Psychic', 25, 20, 'Common', 'A psychic Pokémon that can teleport.'),

(60, 'Kadabra', '1st Edition', 'Base Set', 'Psychic', 40, 50, 'Uncommon', 'The evolved form of Abra, known for its psychic abilities.'),

(61, 'Alakazam', '1st Edition', 'Base Set', 'Psychic', 55, 90, 'Rare', 'A powerful psychic Pokémon with high intelligence.'),

(62, 'Machop', '1st Edition', 'Base Set', 'Fighting', 70, 80, 'Common', 'A small fighting Pokémon that trains hard.'),

(63, 'Machoke', '1st Edition', 'Base Set', 'Fighting', 80, 100, 'Uncommon', 'The evolved form of Machop, known for its strength.'),

(64, 'Machamp', '1st Edition', 'Base Set', 'Fighting', 90, 130, 'Rare', 'A powerful Pokémon with four arms.'),

 (65, 'Bellsprout', '1st Edition', 'Base Set', 'Grass', 50, 40, 'Common', 'A small plant Pokémon that can be found in gardens.'),

(66, 'Weepinbell', '1st Edition', 'Base Set', 'Grass', 65, 50, 'Uncommon', 'The evolved form of Bellsprout, known for its bell shape.'),

(67, 'Victreebel', '1st Edition', 'Base Set', 'Grass', 80, 70, 'Rare', 'A large Pokémon that can trap its prey.'),

(68, 'Tentacool', '1st Edition', 'Base Set', 'Water', 40, 40, 'Common', 'A jellyfish Pokémon that can be found in the ocean.'),

(69, 'Tentacruel', '1st Edition', 'Base Set', 'Water', 80, 70, 'Uncommon', 'The evolved form of Tentacool, known for its long tentacles.'),

(70, 'Geodude', '1st Edition', 'Base Set', 'Rock', 40, 80, 'Common', 'A rock Pokémon that can be found in caves.'),

(71, 'Graveler', '1st Edition', 'Base Set', 'Rock', 55, 95, 'Uncommon', 'The evolved form of Geodude, known for its strength.'),

(72, 'Golem', '1st Edition', 'Base Set', 'Rock', 80, 110, 'Rare', 'A powerful Pokémon that can roll into a ball.'),

(73, 'Ponyta', '1st Edition', 'Base Set', 'Fire', 50, 65, 'Common', 'A small horse Pokémon known for its speed.'),

(74, 'Rapidash', '1st Edition', 'Base Set', 'Fire', 65, 100, 'Uncommon', 'The evolved form of Ponyta, known for its fiery mane.'),

(75, 'Slowpoke', '1st Edition', 'Base Set', 'Water', 90, 65, 'Common', 'A slow Pokémon that often daydreams.'),

(76, 'Slowbro', '1st Edition', 'Base Set', 'Water', 95, 75, 'Uncommon', 'The evolved form of Slowpoke, known for its calm demeanor.'),

(77, 'Magnemite', '1st Edition', 'Base Set', 'Electric', 25, 35, 'Common', 'A small Pokémon that can generate electricity.'),

(78, 'Magneton', '1st Edition', 'Base Set', 'Electric', 50, 60, 'Uncommon', 'The evolved form of Magnemite, known for its magnetic powers.'),

(79, 'Farfetch\'d', '1st Edition', 'Base Set', 'Normal', 52, 65, 'Uncommon', 'A duck Pokémon that carries a leek.'),

(80, 'Doduo', '1st Edition', 'Base Set', 'Normal', 60, 60, 'Common', 'A two-headed bird Pokémon.'),

(81, 'Dodrio', '1st Edition', 'Base Set', 'Normal', 60, 110, 'Uncommon', 'The evolved form of Doduo, known for its speed.'),

(82, 'Seel', '1st Edition', 'Base Set', 'Water', 65, 45, 'Common', 'A seal Pokémon that can swim well.'),

(83, 'Dewgong', '1st Edition', 'Base Set', 'Water', 90, 70, 'Uncommon', 'The evolved form of Seel, known for its elegance.'),

(84, 'Grimer', '1st Edition', 'Base Set', 'Poison', 80, 40, 'Common', 'A sludge Pokémon that can be found in polluted areas.'),

(85, 'Muk', '1st Edition', 'Base Set', 'Poison', 105, 65, 'Uncommon', 'The evolved form of Grimer, known for its toxic nature.'),

(86, 'Shellder', '1st Edition', 'Base Set', 'Water', 30, 40, 'Common', 'A small clam Pokémon that can close its shell.'),

(87, 'Cloyster', '1st Edition', 'Base Set', 'Water', 50, 95, 'Uncommon', 'The evolved form of Shellder, known for its hard shell.'),

(88, 'Gastly', '1st Edition', 'Base Set', 'Ghost', 30, 40, 'Common', 'A ghost Pokémon that is made of gas.'),

(89, 'Haunter', '1st Edition', 'Base Set', 'Ghost', 45, 50, 'Uncommon', 'The evolved form of Gastly, known for its mischievous nature.'),

(90, 'Gengar', '1st Edition', 'Base Set', 'Ghost', 60, 65, 'Rare', 'A powerful ghost Pokémon that can scare its opponents.'),

(91, 'Onix', '1st Edition', 'Base Set', 'Rock', 35, 45, 'Rare', 'A large rock snake Pokémon that is very strong.'),

(92, 'Drowzee', '1st Edition', 'Base Set', 'Psychic', 60, 48, 'Common', 'A Pokémon that can put others to sleep with its abilities.'),

(93, 'Hypno', '1st Edition', 'Base Set', 'Psychic', 80, 73, 'Uncommon', 'The evolved form of Drowzee, known for its hypnotic powers.'),

(94, 'Krabby', '1st Edition', 'Base Set', 'Water', 30, 50, 'Common', 'A small crab Pokémon that can be found on beaches.'),

(95, 'Kingler', '1st Edition', 'Base Set', 'Water', 55, 90, 'Uncommon', 'The evolved form of Krabby, known for its large claw.'),

(96, 'Voltorb', '1st Edition', 'Base Set', 'Electric', 40, 30, 'Common', 'A ball Pokémon that can generate electricity.'),

(97, 'Electrode', '1st Edition', 'Base Set', 'Electric', 60, 50, 'Uncommon', 'The evolved form of Voltorb, known for its speed.'),

(98, 'Exeggcute', '1st Edition', 'Base Set', 'Grass', 60, 40, 'Common', 'A group of small egg Pokémon that can be found in forests.'),

(99, 'Exeggutor', '1st Edition', 'Base Set', 'Grass', 95, 130, 'Rare', 'The evolved form of Exeggcute, known for its large head.'),

(100, 'Cubone', '1st Edition', 'Base Set', 'Ground', 50, 50, 'Common', 'A small Pokémon that wears a skull on its head.'),

(101, 'Marowak', '1st Edition', 'Base Set', 'Ground', 60, 80, 'Uncommon', 'The evolved form of Cubone, known for its bone club.'),

(102, 'Hitmonlee', '1st Edition', 'Base Set', 'Fighting', 50, 110, 'Rare', 'A powerful fighting Pokémon known for its kicking ability.'),

(103, 'Hitmonchan', '1st Edition', 'Base Set', 'Fighting', 50, 110, 'Rare', 'A powerful fighting Pokémon known for its punching ability.'),

(104, 'Lickitung', '1st Edition', 'Base Set', 'Normal', 90, 55, 'Common', 'A Pokémon with a long tongue that can lick its opponents.'),

(105, 'Koffing', '1st Edition', 'Base Set', 'Poison', 40, 65, 'Common', 'A gas Pokémon that can be found in polluted areas.'),

(106, 'Weezing', '1st Edition', 'Base Set', 'Poison', 65, 90, 'Uncommon', 'The evolved form of Koffing, known for its toxic gas.'),

(107, 'Rhyhorn', '1st Edition', 'Base Set', 'Ground', 80, 85, 'Common', 'A large Pokémon that can charge at its opponents.'),

(108, 'Rhydon', '1st Edition', 'Base Set', 'Ground', 105, 120, 'Uncommon', 'The evolved form of Rhyhorn, known for its strength.'),

(109, 'Chansey', '1st Edition', 'Base Set', 'Normal', 250, 30, 'Rare', 'A Pokémon known for its healing abilities.'),

(110, 'Tangela', '1st Edition', 'Base Set', 'Grass', 65, 55, 'Common', 'A Pokémon covered in blue vines.'),

(111, 'Kangaskhan', '1st Edition', 'Base Set', 'Normal', 105, 95, 'Rare', 'A Pokémon that carries its baby in its pouch.'),

(112, 'Horsea', '1st Edition', 'Base Set', 'Water', 30, 40, 'Common', 'A small sea horse Pokémon.'),

(113, 'Seadra', '1st Edition', 'Base Set', 'Water', 55, 65, 'Uncommon', 'The evolved form of Horsea, known for its speed.'),

(114, 'Goldeen', '1st Edition', 'Base Set', 'Water', 40, 30, 'Common', 'A small fish Pokémon that can be found in ponds.'),

(115, 'Seaking', '1st Edition', 'Base Set', 'Water', 65, 80, 'Uncommon', 'The evolved form of Goldeen, known for its large horn.'),

(116, 'Staryu', '1st Edition', 'Base Set', 'Water', 30, 45, 'Common', 'A starfish Pokémon that can be found in the ocean.'),

(117, 'Starmie', '1st Edition', 'Base Set', 'Water', 60, 75, 'Uncommon', 'The evolved form of Staryu, known for its speed.'),

(118, 'Mr. Mime', '1st Edition', 'Base Set', 'Psychic', 40, 45, 'Rare', 'A Pokémon that can create invisible barriers.'),

(119, 'Scyther', '1st Edition', 'Base Set', 'Bug', 70, 110, 'Rare', 'A bug Pokémon known for its sharp scythes.'),

(120, 'Jynx', '1st Edition', 'Base Set', 'Ice', 65, 50, 'Rare', 'A Pokémon known for its singing abilities.'),

(121, 'Electabuzz', '1st Edition', 'Base Set', 'Electric', 65, 55, 'Common', 'A small electric Pokémon known for its speed.'),

(122, 'Magmar', '1st Edition', 'Base Set', 'Fire', 65, 95, 'Common', 'A small fire Pokémon known for its fiery nature.'),

(123, 'Pinsir', '1st Edition', 'Base Set', 'Bug', 65, 125, 'Rare', 'A powerful bug Pokémon with large pincers.'),

(124, 'Tauros', '1st Edition', 'Base Set', 'Normal', 75, 100, 'Common', 'A bull Pokémon known for its strength.'),

(125, 'Magikarp', '1st Edition', 'Base Set', 'Water', 20, 10, 'Common', 'A weak fish Pokémon that can evolve into Gyarados.'),

(126, 'Gyarados', '1st Edition', 'Base Set', 'Water', 95, 125, 'Rare', 'A powerful Pokémon that can unleash a devastating attack.'),

(127, 'Lapras', '1st Edition', 'Base Set', 'Water', 130, 60, 'Rare', 'A gentle Pokémon that can ferry people across water.'),

(128, 'Ditto', '1st Edition', 'Base Set', 'Normal', 48, 48, 'Rare', 'A Pokémon that can transform into any other Pokémon.'),

(129, 'Eevee', '1st Edition', 'Base Set', 'Normal', 55, 40, 'Common', 'A small Pokémon with multiple evolution options.'),

(130, 'Vaporeon', '1st Edition', 'Base Set', 'Water', 130, 65, 'Rare', 'The evolved form of Eevee, known for its aquatic abilities.'),

(131, 'Jolteon', '1st Edition', 'Base Set', 'Electric', 65, 110, 'Rare', 'The evolved form of Eevee, known for its speed.'),

(132, 'Flareon', '1st Edition', 'Base Set', 'Fire', 65, 110, 'Rare', 'The evolved form of Eevee, known for its fiery nature.'),

(133, 'Porygon', '1st Edition', 'Base Set', 'Normal', 65, 60, 'Uncommon', 'A virtual Pokémon that can be upgraded.'),

(134, 'Omanyte', '1st Edition', 'Base Set', 'Rock', 35, 40, 'Uncommon', 'A fossil Pokémon that can be revived.'),

(135, 'Omastar', '1st Edition', 'Base Set', 'Rock', 70, 60, 'Rare', 'The evolved form of Omanyte, known for its powerful attacks.'),

(136, 'Kabuto', '1st Edition', 'Base Set', 'Rock', 30, 40, 'Uncommon', 'A fossil Pokémon that can be revived.'),

(137, 'Kabutops', '1st Edition', 'Base Set', 'Rock', 60, 90, 'Rare', 'The evolved form of Kabuto, known for its sharp claws.'),

(138, 'Aerodactyl', '1st Edition', 'Base Set', 'Rock', 80, 105, 'Rare', 'A powerful flying Pokémon that can attack from above.'),

(139, 'Snorlax', '1st Edition', 'Base Set', 'Normal', 160, 110, 'Rare', 'A large Pokémon that is known for its sleeping habits.'),

(140, 'Articuno', '1st Edition', 'Base Set', 'Ice', 90, 100, 'Legendary', 'A legendary bird Pokémon known for its ice powers.'),

(141, 'Zapdos', '1st Edition', 'Base Set', 'Electric', 90, 90, 'Legendary', 'A legendary bird Pokémon known for its electric powers.'),

(142, 'Moltres', '1st Edition', 'Base Set', 'Fire', 90, 100, 'Legendary', 'A legendary bird Pokémon known for its fiery powers.'),

(143, 'Dratini', '1st Edition', 'Base Set', 'Dragon', 41, 40, 'Uncommon', 'A small dragon Pokémon that can evolve into Dragonair.'),

(144, 'Dragonair', '1st Edition', 'Base Set', 'Dragon', 61, 70, 'Rare', 'The evolved form of Dratini, known for its elegance.'),

(145, 'Dragonite', '1st Edition', 'Base Set', 'Dragon', 91, 134, 'Rare', 'A powerful dragon Pokémon that can fly at high speeds.'),

(146, 'Mewtwo', '1st Edition', 'Base Set', 'Psychic', 106, 110, 'Rare', 'A genetically engineered Pokémon with immense psychic powers.'),

(147, 'Mew', '1st Edition', 'Base Set', 'Psychic', 100, 100, 'Legendary', 'A mythical Pokémon known for its ability to learn any move.');
```
