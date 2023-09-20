USE jeoparody;

-- Create Languages Table
CREATE TABLE Languages (
language_id INT AUTO_INCREMENT PRIMARY KEY,
language_name VARCHAR(50) NOT NULL
);

-- Create Categories Table
CREATE TABLE Categories (
category_id INT AUTO_INCREMENT PRIMARY KEY,
category_name VARCHAR(50) NOT NULL,
language_id INT,
FOREIGN KEY (language_id) REFERENCES Languages(language_id)
);

-- Create Questions Table
CREATE TABLE Questions (
question_id INT AUTO_INCREMENT PRIMARY KEY,
category_id INT,
language_id INT,
question_text VARCHAR(300) NOT NULL,
image_location_data MEDIUMBLOB NOT NULL,
FOREIGN KEY (category_id) REFERENCES Categories (category_id) ON DELETE CASCADE,
FOREIGN KEY (language_id) REFERENCES Languages(language_id)
);

-- Create Options Table
CREATE TABLE Options (
    option_id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
    option_text VARCHAR(300) NOT NULL,
    is_correct BOOLEAN NOT NULL,
    FOREIGN KEY (question_id) REFERENCES Questions(question_id)
);

-- --------------------------------------
--    Insertion of Values Start here   --
-- --------------------------------------

-- LANGUAGES TABLE ---
INSERT INTO Languages (language_name) VALUES
('English'), -- ID of 1
('Spanish'), -- ID of 2
('French'); -- ID of 3


-- CATEGORIES TABLE ---
-- English Categories
INSERT INTO Categories (language_id, category_name) VALUES
(1, 'Famous People'),
(1, 'Historic Events'),
(1, 'Cars'),
(1, 'Music'),
(1, 'Fashion');

-- Spanish Categories 
INSERT INTO Categories (language_id, category_name) VALUES
(2, 'Personas Famosas'),
(2, 'Eventos Históricos'),
(2, 'Coches'),
(2, 'Música'),
(2, 'Moda');

-- French Categories 
INSERT INTO Categories (language_id, category_name) VALUES
(3, 'Personnes Célèbres'),
(3, 'Événements Historiques'),
(3, 'Voitures'),
(3, 'Musique'),
(3, 'Mode');



-- QUESTIONS TABLE ---

-- Famous People Category --
-- English Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(1, 1, 'This legendary boxer was originally named Cassius Clay.', '/images/MuhammadAli.jpg'),
(1, 1, 'This actress starred in the movie "Breakfast at Tiffany\'s."', '/images/AudreyHepburn.jpg'),
(1, 1, 'This actress and singer played Sandy in the movie "Grease."', '/images/OliviaNewton.jpg'),
(1, 1, 'This director is known for the movie "E.T. the Extra-Terrestrial" in the 1980s.', '/images/StevenSpielberg.jpg'),
(1, 1, 'He was known as the "King of Pop."', '/images/MichaelJackson.jpg'),
(1, 1, 'This actress played Princess Leia in the "Star Wars" series.', '/images/CarrieFisher.jpg'),
(1, 1, 'This legendary guitarist played the national anthem at Woodstock in 1969.', '/images/JimiHendrix.jpg'),
(1, 1, 'He became famous for playing the role of "The Terminator" in the 1984 movie.', '/images/ArnoldSchwarzenegger.jpg'),
(1, 1, 'This pop icon became the "Queen of Pop" during the 1980s.', '/images/Madonna.jpg'),
(1, 1, 'This South African leader was released from prison in 1990 after 27 years.', '/images/NelsonMandela.jpg');

-- Spanish Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(6, 2, 'Este legendario boxeador originalmente se llamaba Cassius Clay.', '/images/MuhammadAli.jpg'),
(6, 2, 'Esta actriz protagonizó la película "Desayuno con diamantes".', '/images/AudreyHepburn.jpg'),
(6, 2, 'Esta actriz y cantante interpretó a Sandy en la película "Grease".', '/images/OliviaNewton.jpg'),
(6, 2, 'Este director es conocido por la película "E.T. el Extraterrestre" en los años 80.', '/images/StevenSpielberg.jpg'),
(6, 2, 'Él fue conocido como el "Rey del Pop".', '/images/MichaelJackson.jpg'),
(6, 2, 'Esta actriz interpretó a la Princesa Leia en la serie "Star Wars".', '/images/CarrieFisher.jpg'),
(6, 2, 'Este legendario guitarrista tocó el himno nacional en Woodstock en 1969.', '/images/JimiHendrix.jpg'),
(6, 2, 'Se hizo famoso por interpretar el papel de "El Terminator" en la película de 1984.', '/images/ArnoldSchwarzenegger.jpg'),
(6, 2, 'Esta ícono del pop se convirtió en la "Reina del Pop" durante los años 80.', '/images/Madonna.jpg'),
(6, 2, 'Este líder sudafricano fue liberado de prisión en 1990 después de 27 años.', '/images/NelsonMandela.jpg');


-- French Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(11, 3, 'Ce boxeur légendaire s’appelait à l’origine Cassius Clay.', '/images/MuhammadAli.jpg'),
(11, 3, 'Cette actrice a joué dans le film "Diamants sur canapé".', '/images/AudreyHepburn.jpg'),
(11, 3, 'Cette actrice et chanteuse a interprété Sandy dans le film "Grease".', '/images/OliviaNewton.jpg'),
(11, 3, 'Ce réalisateur est connu pour le film "E.T. l’extra-terrestre" des années 80.', '/images/StevenSpielberg.jpg'),
(11, 3, 'Il était connu comme le "Roi de la Pop".', '/images/MichaelJackson.jpg'),
(11, 3, 'Cette actrice a joué la Princesse Leia dans la série "Star Wars".', '/images/CarrieFisher.jpg'),
(11, 3, 'Ce guitariste légendaire a joué l’hymne national à Woodstock en 1969.', '/images/JimiHendrix.jpg'),
(11, 3, 'Il est devenu célèbre pour avoir joué le rôle de "Terminator" dans le film de 1984.', '/images/ArnoldSchwarzenegger.jpg'),
(11, 3, 'Cette icône de la pop est devenue la "Reine de la Pop" dans les années 80.', '/images/Madonna.jpg'),
(11, 3, 'Ce leader sud-africain a été libéré de prison en 1990 après 27 ans.', '/images/NelsonMandela.jpg');



-- Historic Events Category --
-- English Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(2, 1, 'In 1969, humans first set foot on the Moon during this mission.', '/images/Apollo11.jpg'),
(2, 1, 'In 1991, the dissolution of this federation marked the end of the Cold War era.', '/images/SovietUnion.jpg'),
(2, 1, 'The Civil Rights Act, which aimed to end segregation in public places and banned employment discrimination, was signed into law in this year.', '/images/1964.jpg'),
(2, 1, 'In 1962, the U.S. and the Soviet Union were on the brink of nuclear war due to missiles in this country.', '/images/Cuba.jpg'),
(2, 1, 'This 1991 operation was a coalition-led war against Iraq following its invasion of Kuwait.', '/images/OperationDesertStorm.jpg'),
(2, 1, 'This concert held in 1985 was aimed at raising funds for famine relief in Ethiopia.', '/images/LiveAid.jpg'),
(2, 1, 'This American president was impeached in 1998 but was not removed from office.', '/images/BillClinton.jpg'),
(2, 1, 'This scandal in the early 1970s involved the U.S. government\'s secret bombing campaign during the Vietnam War.', '/images/PentagonPapers.jpg'),
(2, 1, 'This U.S. President signed the Civil Rights Act of 1964 into law.', '/images/Lyndon.jpg'),
(2, 1, 'In 1997, the United Kingdom handed over the sovereignty of this territory to China.', '/images/HongKong.jpg');

-- Spanish Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(7, 2, 'En 1969, los humanos pisaron la Luna por primera vez durante esta misión.', '/images/Apollo11.jpg'),
(7, 2, 'En 1991, la disolución de esta federación marcó el fin de la era de la Guerra Fría.', '/images/SovietUnion.jpg'),
(7, 2, 'La Ley de Derechos Civiles, que buscaba poner fin a la segregación en lugares públicos y prohibía la discriminación laboral, fue promulgada en este año.', '/images/1964.jpg'),
(7, 2, 'En 1962, EE.UU. y la Unión Soviética estuvieron al borde de una guerra nuclear debido a misiles en este país.', '/images/Cuba.jpg'),
(7, 2, 'Esta operación de 1991 fue una guerra liderada por una coalición contra Iraq tras su invasión a Kuwait.', '/images/OperationDesertStorm.jpg'),
(7, 2, 'Este concierto celebrado en 1985 tuvo como objetivo recaudar fondos para aliviar la hambruna en Etiopía.', '/images/LiveAid.jpg'),
(7, 2, 'Este presidente estadounidense fue sometido a juicio político en 1998 pero no fue destituido.', '/images/BillClinton.jpg'),
(7, 2, 'Este escándalo a principios de la década de 1970 involucró la campaña de bombardeo secreta del gobierno de EE.UU. durante la Guerra de Vietnam.', '/images/PentagonPapers.jpg'),
(7, 2, 'Este Presidente de EE.UU. promulgó la Ley de Derechos Civiles de 1964.', '/images/Lyndon.jpg'),
(7, 2, 'En 1997, el Reino Unido cedió la soberanía de este territorio a China.', '/images/HongKong.jpg');

-- French Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(12, 3, 'En 1969, les humains ont mis le pied sur la Lune pour la première fois lors de cette mission.', '/images/Apollo11.jpg'),
(12, 3, 'En 1991, la dissolution de cette fédération a marqué la fin de l\'ère de la Guerre Froide.', '/images/SovietUnion.jpg'),
(12, 3, 'La loi sur les droits civiques, visant à mettre fin à la ségrégation dans les lieux publics et interdisant la discrimination à l\'emploi, a été promulguée cette année.', '/images/1964.jpg'),
(12, 3, 'En 1962, les États-Unis et l\'Union Soviétique étaient au bord de la guerre nucléaire en raison de missiles dans ce pays.', '/images/Cuba.jpg'),
(12, 3, 'Cette opération de 1991 était une guerre menée par une coalition contre l\'Irak suite à son invasion du Koweït.', '/images/OperationDesertStorm.jpg'),
(12, 3, 'Ce concert organisé en 1985 visait à lever des fonds pour l\'aide contre la famine en Éthiopie.', '/images/LiveAid.jpg'),
(12, 3, 'Ce président américain a été destitué en 1998 mais n\'a pas été écarté du pouvoir.', '/images/BillClinton.jpg'),
(12, 3, 'Ce scandale au début des années 1970 concernait la campagne de bombardements secrets du gouvernement américain pendant la guerre du Vietnam.', '/images/PentagonPapers.jpg'),
(12, 3, 'Ce président des États-Unis a signé la loi sur les droits civiques de 1964.', '/images/Lyndon.jpg'),
(12, 3, 'En 1997, le Royaume-Uni a rendu la souveraineté de ce territoire à la Chine.', '/images/HongKong.jpg');




-- Cars Category --
-- English Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(3, 1, 'This American muscle car was introduced by Ford in 1964.', '/images/FordMustang.jpg'),
(3, 1, 'The 1991 Acura NSX was notable for its use of this lightweight material in its chassis.', '/images/Aluminum.jpg'),
(3, 1, 'In 1983, this American car company introduced the minivan, revolutionizing family transport.', '/images/Chrysler.jpg'),
(3, 1, 'Which British car manufacturer produced the luxury Silver Shadow model in the 1960s?', '/images/Rolls.jpg'),
(3, 1, 'The Subaru Impreza WRX, introduced in the 1990s, was especially popular in this kind of racing.', '/images/RallyRacing.jpg'),
(3, 1, 'Which Italian car manufacturer introduced the Countach in 1974?', '/images/Lamborghini.jpg'),
(3, 1, 'Which luxury Japanese car brand was launched by Toyota in 1989?', '/images/Lexus.jpg'),
(3, 1, 'Which car manufacturer produced the 911 model, first introduced in the early 1960s?', '/images/Porsche.jpg'),
(3, 1, 'This Swedish car manufacturer introduced the safety-focused 240 series in the 1970s.', '/images/Volvo.jpg'),
(3, 1, 'Honda introduced this model in 1972, which has since become one of the best-selling cars worldwide.', '/images/Civic.jpg');

-- Spanish Translations
(8, 2, 'Este muscle car americano fue introducido por Ford en 1964.', '/images/FordMustang.jpg'),
(8, 2, 'El Acura NSX de 1991 fue notable por el uso de este material ligero en su chasis.', '/images/Aluminum.jpg'),
(8, 2, 'En 1983, esta compañía americana de automóviles introdujo la minivan, revolucionando el transporte familiar.', '/images/Chrysler.jpg'),
(8, 2, '¿Qué fabricante británico de coches produjo el lujoso modelo Silver Shadow en la década de 1960?', '/images/Rolls.jpg'),
(8, 2, 'El Subaru Impreza WRX, introducido en la década de 1990, fue especialmente popular en este tipo de carreras.', '/images/RallyRacing.jpg'),
(8, 2, '¿Qué fabricante italiano de coches introdujo el Countach en 1974?', '/images/Lamborghini.jpg'),
(8, 2, '¿Qué marca japonesa de coches de lujo fue lanzada por Toyota en 1989?', '/images/Lexus.jpg'),
(8, 2, '¿Qué fabricante de coches produjo el modelo 911, introducido por primera vez a principios de la década de 1960?', '/images/Porsche.jpg'),
(8, 2, 'Este fabricante sueco de automóviles introdujo la serie 240 centrada en la seguridad en la década de 1970.', '/images/Volvo.jpg'),
(8, 2, 'Honda introdujo este modelo en 1972, que desde entonces se ha convertido en uno de los coches más vendidos en el mundo.', '/images/Civic.jpg');

-- French Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(13, 3, 'Cette voiture muscle américaine a été introduite par Ford en 1964.', '/images/FordMustang.jpg'),
(13, 3, 'La Acura NSX de 1991 était notable pour l\'utilisation de ce matériau léger dans son châssis.', '/images/Aluminum.jpg'),
(13, 3, 'En 1983, cette compagnie automobile américaine a introduit le monospace, révolutionnant le transport familial.', '/images/Chrysler.jpg'),
(13, 3, 'Quel constructeur automobile britannique a produit le modèle de luxe Silver Shadow dans les années 1960?', '/images/Rolls.jpg'),
(13, 3, 'La Subaru Impreza WRX, introduite dans les années 1990, était particulièrement populaire dans ce type de course.', '/images/RallyRacing.jpg'),
(13, 3, 'Quel constructeur automobile italien a introduit la Countach en 1974?', '/images/Lamborghini.jpg'),
(13, 3, 'Quelle marque japonaise de voitures de luxe a été lancée par Toyota en 1989?', '/images/Lexus.jpg'),
(13, 3, 'Quel constructeur automobile a produit le modèle 911, introduit pour la première fois au début des années 1960?', '/images/Porsche.jpg'),
(13, 3, 'Ce constructeur automobile suédois a introduit la série 240 axée sur la sécurité dans les années 1970.', '/images/Volvo.jpg'),
(13, 3, 'Honda a introduit ce modèle en 1972, qui est depuis devenu l\'une des voitures les plus vendues au monde.', '/images/Civic.jpg');




-- Music Category --
-- English Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(4, 1, 'This band, known for hits like "Hey Jude" and "Let It Be", is often considered the most influential rock band of all time.', '/images/TheBeatles.jpg'),
(4, 1, 'In 1991, which band released the album "Nevermind" featuring the hit "Smells Like Teen Spirit"?', '/images/Nirvana.jpg'),
(4, 1, 'Which artist released the iconic album "Thriller" in 1982?', '/images/Michael.jpg'),
(4, 1, 'The song "Stayin'' Alive" was a hit for this group and featured prominently in the movie "Saturday Night Fever".', '/images/BeeGees.jpg'),
(4, 1, 'The 1984 song "Purple Rain" is a track from the album of the same name by this artist.', '/images/Prince.jpg'),
(4, 1, 'Which band, fronted by Freddie Mercury, released hits like "Bohemian Rhapsody" and "We Are The Champions"?', '/images/Queen.jpg'),
(4, 1, 'This rock band, known for its distinctive guitar sound, released "Hotel California" in 1976.', '/images/TheEagles.jpg'),
(4, 1, 'This artist''s 1969 performance at Woodstock, where he played a rendition of "The Star-Spangled Banner", is legendary.', '/images/Jimi.jpg'),
(4, 1, 'This Jamaican singer is known as the "King of Reggae" and had hits like "No Woman, No Cry" and "Redemption Song".', '/images/BobMarley.jpg'),
(4, 1, 'Which British band released the critically acclaimed album "The Dark Side of the Moon" in 1973?', '/images/PinkFloyd.jpg');

-- Spanish Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(9, 2, 'Esta banda, conocida por éxitos como "Hey Jude" y "Let It Be", es a menudo considerada la banda de rock más influyente de todos los tiempos.', '/images/TheBeatles.jpg'),
(9, 2, 'En 1991, ¿qué banda lanzó el álbum "Nevermind" que incluye el éxito "Smells Like Teen Spirit"?', '/images/Nirvana.jpg'),
(9, 2, '¿Qué artista lanzó el icónico álbum "Thriller" en 1982?', '/images/Michael.jpg'),
(9, 2, 'La canción "Stayin'' Alive" fue un éxito para este grupo y se destacó en la película "Fiebre del sábado noche".', '/images/BeeGees.jpg'),
(9, 2, 'La canción "Purple Rain" de 1984 es una pista del álbum del mismo nombre de este artista.', '/images/Prince.jpg'),
(9, 2, '¿Qué banda, liderada por Freddie Mercury, lanzó éxitos como "Bohemian Rhapsody" y "We Are The Champions"?', '/images/Queen.jpg'),
(9, 2, 'Esta banda de rock, conocida por su distintivo sonido de guitarra, lanzó "Hotel California" en 1976.', '/images/TheEagles.jpg'),
(9, 2, 'La actuación de este artista en 1969 en Woodstock, donde interpretó una versión de "The Star-Spangled Banner", es legendaria.', '/images/Jimi.jpg'),
(9, 2, 'Este cantante jamaicano es conocido como el "Rey del Reggae" y tuvo éxitos como "No Woman, No Cry" y "Redemption Song".', '/images/BobMarley.jpg'),
(9, 2, '¿Qué banda británica lanzó el aclamado álbum "The Dark Side of the Moon" en 1973?', '/images/PinkFloyd.jpg');

-- French Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(14, 3, 'Ce groupe, connu pour des hits comme "Hey Jude" et "Let It Be", est souvent considéré comme le groupe de rock le plus influent de tous les temps.', '/images/TheBeatles.jpg'),
(14, 3, 'En 1991, quel groupe a sorti l''album "Nevermind" avec le hit "Smells Like Teen Spirit"?', '/images/Nirvana.jpg'),
(14, 3, 'Quel artiste a sorti l''album iconique "Thriller" en 1982?', '/images/Michael.jpg'),
(14, 3, 'La chanson "Stayin'' Alive" a été un succès pour ce groupe et a été mise en avant dans le film "Saturday Night Fever".', '/images/BeeGees.jpg'),
(14, 3, 'La chanson de 1984 "Purple Rain" est un morceau de l''album du même nom de cet artiste.', '/images/Prince.jpg'),
(14, 3, 'Quel groupe, dirigé par Freddie Mercury, a sorti des hits comme "Bohemian Rhapsody" et "We Are The Champions"?', '/images/Queen.jpg'),
(14, 3, 'Ce groupe de rock, connu pour son son de guitare distinctif, a sorti "Hotel California" en 1976.', '/images/TheEagles.jpg'),
(14, 3, 'La performance de cet artiste en 1969 à Woodstock, où il a joué une version de "The Star-Spangled Banner", est légendaire.', '/images/Jimi.jpg'),
(14, 3, 'Ce chanteur jamaïcain est connu comme le "Roi du Reggae" et a eu des hits comme "No Woman, No Cry" et "Redemption Song".', '/images/BobMarley.jpg'),
(14, 3, 'Quel groupe britannique a sorti l''album acclamé par la critique "The Dark Side of the Moon" en 1973?', '/images/PinkFloyd.jpg');




-- Fashion Category
-- English Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(5, 1, 'In the late 1960s, this fashion trend was inspired by space age design and often featured metallic materials and geometric shapes.', '/images/Futurism.jpg'),
(5, 1, 'This fashion trend of the late 1980s and early 1990s involved brightly colored beads and plastic shapes worn as necklaces.', '/images/CandyJewelry.jpg'),
(5, 1, 'In the 1960s, this British fashion designer popularized the miniskirt.', '/images/MaryQuant.jpg'),
(5, 1, 'What type of jewelry, often worn in the 1980s, involved multiple rubber bracelets worn at once, popularized by Madonna?', '/images/JellyBracelets.jpg'),
(5, 1, 'In the 1980s, women often wore oversized tops with tight-fitting pants or leggings and this type of footwear.', '/images/SlouchBoots.jpg'),
(5, 1, 'What handbag brand became famous in the 1980s for its quilted purses with a chain strap?', '/images/Chanel.jpg'),
(5, 1, 'In the 1970s, what accessory became popular, characterized by its round shape and colored, often mirrored, lenses?', '/images/JohnLennonGlasses.jpg'),
(5, 1, 'In the 1980s, "Members Only" jackets became a popular fashion item. What distinctive feature did they have?', '/images/Epaulettes.jpg'),
(5, 1, 'These necklaces, often made of plastic beads, became a popular accessory in the 1990s.', '/images/Chokers.jpg'),
(5, 1, 'In the 1970s, these flared pants became a fashion staple.', '/images/BellBottoms.jpg');

-- Spanish Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(10, 2, 'A finales de la década de 1960, esta tendencia de moda se inspiró en el diseño de la era espacial y a menudo presentaba materiales metálicos y formas geométricas.', '/images/Futurism.jpg'),
(10, 2, 'Esta tendencia de moda de finales de la década de 1980 y principios de la de 1990 involucraba cuentas de colores brillantes y formas de plástico usadas como collares.', '/images/CandyJewelry.jpg'),
(10, 2, 'En la década de 1960, esta diseñadora de moda británica popularizó la minifalda.', '/images/MaryQuant.jpg'),
(10, 2, '¿Qué tipo de joyería, a menudo usada en la década de 1980, involucraba múltiples pulseras de goma usadas al mismo tiempo, popularizadas por Madonna?', '/images/JellyBracelets.jpg'),
(10, 2, 'En la década de 1980, las mujeres a menudo usaban tops grandes con pantalones o leggings ajustados y este tipo de calzado.', '/images/SlouchBoots.jpg'),
(10, 2, '¿Qué marca de bolsos se hizo famosa en la década de 1980 por sus bolsos acolchados con una correa de cadena?', '/images/Chanel.jpg'),
(10, 2, 'En la década de 1970, ¿qué accesorio se popularizó, caracterizado por su forma redonda y lentes coloreadas, a menudo espejadas?', '/images/JohnLennonGlasses.jpg'),
(10, 2, 'En la década de 1980, las chaquetas "Members Only" se convirtieron en un artículo de moda popular. ¿Qué característica distintiva tenían?', '/images/Epaulettes.jpg'),
(10, 2, 'Estos collares, a menudo hechos de cuentas de plástico, se convirtieron en un accesorio popular en la década de 1990.', '/images/Chokers.jpg'),
(10, 2, 'En la década de 1970, estos pantalones acampanados se convirtieron en un elemento básico de la moda.', '/images/BellBottoms.jpg');

-- French Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(15, 3, 'À la fin des années 1960, cette tendance mode a été inspirée par le design de l''âge spatial et comportait souvent des matériaux métalliques et des formes géométriques.', '/images/Futurism.jpg'),
(15, 3, 'Cette tendance mode de la fin des années 1980 et du début des années 1990 comportait des perles et des formes en plastique de couleurs vives portées en colliers.', '/images/CandyJewelry.jpg'),
(15, 3, 'Dans les années 1960, cette créatrice de mode britannique a popularisé la minijupe.', '/images/MaryQuant.jpg'),
(15, 3, 'Quel type de bijoux, souvent portés dans les années 1980, impliquait plusieurs bracelets en caoutchouc portés à la fois, popularisés par Madonna?', '/images/JellyBracelets.jpg'),
(15, 3, 'Dans les années 1980, les femmes portaient souvent des hauts surdimensionnés avec des pantalons ou des leggings moulants et ce type de chaussures.', '/images/SlouchBoots.jpg'),
(15, 3, 'Quelle marque de sac à main est devenue célèbre dans les années 1980 pour ses sacs matelassés avec une bandoulière en chaîne?', '/images/Chanel.jpg'),
(15, 3, 'Dans les années 1970, quel accessoire est devenu populaire, caractérisé par sa forme ronde et ses verres colorés, souvent miroir?', '/images/JohnLennonGlasses.jpg'),
(15, 3, 'Dans les années 1980, les vestes "Members Only" sont devenues un article de mode populaire. Quelle caractéristique distinctive avaient-elles?', '/images/Epaulettes.jpg'),
(15, 3, 'Ces colliers, souvent faits de perles en plastique, sont devenus un accessoire populaire dans les années 1990.', '/images/Chokers.jpg'),
(15, 3, 'Dans les années 1970, ces pantalons évasés sont devenus un incontournable de la mode.', '/images/BellBottoms.jpg');


-- OPTIONS TABLE ---

-- Famous People Category
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(1, 'Joe Frazier', FALSE),
(1, 'Sugar Ray Leonard', FALSE),
(1, 'Muhammad Ali', TRUE),
(1, 'George Foreman', FALSE),

(2, 'Marilyn Monroe', FALSE),
(2, 'Audrey Hepburn', TRUE),
(2, 'Elizabeth Taylor', FALSE),
(2, 'Grace Kelly', FALSE),

(3, 'Debbie Reynolds', FALSE),
(3, 'Liza Minnelli', FALSE),
(3, 'Olivia Newton-John', TRUE),
(3, 'Barbra Streisand', FALSE),

(4, 'George Lucas', FALSE),
(4, 'Steven Spielberg', TRUE),
(4, 'James Cameron', FALSE),
(4, 'Ridley Scott', FALSE),

(5, 'Elvis Presley', FALSE),
(5, 'Michael Jackson', TRUE),
(5, 'Prince', FALSE),
(5, 'Freddie Mercury', FALSE),

(6, 'Natalie Portman', FALSE),
(6, 'Carrie Fisher', TRUE),
(6, 'Sigourney Weaver', FALSE),
(6, 'Linda Hamilton', FALSE),

(7, 'Eric Clapton', FALSE),
(7, 'Jimmy Page', FALSE),
(7, 'Jimi Hendrix', TRUE),
(7, 'Keith Richards', FALSE),

(8, 'Jean-Claude Van Damme', FALSE),
(8, 'Bruce Willis', FALSE),
(8, 'Sylvester Stallone', FALSE),
(8, 'Arnold Schwarzenegger', TRUE),

(9, 'Whitney Houston', FALSE),
(9, 'Janet Jackson', FALSE),
(9, 'Tina Turner', FALSE),
(9, 'Madonna', TRUE),

(10, 'F. W. de Klerk', FALSE),
(10, 'Desmond Tutu', FALSE),
(10, 'Nelson Mandela', TRUE),
(10, 'Steve Biko', FALSE);

-- Famous People Category Spanish
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(11, 'Joe Frazier', FALSE),
(11, 'Sugar Ray Leonard', FALSE),
(11, 'Muhammad Ali', TRUE),
(11, 'George Foreman', FALSE),

(12, 'Marilyn Monroe', FALSE),
(12, 'Audrey Hepburn', TRUE),
(12, 'Elizabeth Taylor', FALSE),
(12, 'Grace Kelly', FALSE),

(13, 'Debbie Reynolds', FALSE),
(13, 'Liza Minnelli', FALSE),
(13, 'Olivia Newton-John', TRUE),
(13, 'Barbra Streisand', FALSE),

(14, 'George Lucas', FALSE),
(14, 'Steven Spielberg', TRUE),
(14, 'James Cameron', FALSE),
(14, 'Ridley Scott', FALSE),

(15, 'Elvis Presley', FALSE),
(15, 'Michael Jackson', TRUE),
(15, 'Prince', FALSE),
(15, 'Freddie Mercury', FALSE),

(16, 'Natalie Portman', FALSE),
(16, 'Carrie Fisher', TRUE),
(16, 'Sigourney Weaver', FALSE),
(16, 'Linda Hamilton', FALSE),

(17, 'Eric Clapton', FALSE),
(17, 'Jimmy Page', FALSE),
(17, 'Jimi Hendrix', TRUE),
(17, 'Keith Richards', FALSE),

(18, 'Jean-Claude Van Damme', FALSE),
(18, 'Bruce Willis', FALSE),
(18, 'Sylvester Stallone', FALSE),
(18, 'Arnold Schwarzenegger', TRUE),

(19, 'Whitney Houston', FALSE),
(19, 'Janet Jackson', FALSE),
(19, 'Tina Turner', FALSE),
(19, 'Madonna', TRUE),

(20, 'F. W. de Klerk', FALSE),
(20, 'Desmond Tutu', FALSE),
(20, 'Nelson Mandela', TRUE),
(20, 'Steve Biko', FALSE);

-- Famous People Category French
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(21, 'Joe Frazier', FALSE),
(21, 'Sugar Ray Leonard', FALSE),
(21, 'Muhammad Ali', TRUE),
(21, 'George Foreman', FALSE),

(22, 'Marilyn Monroe', FALSE),
(22, 'Audrey Hepburn', TRUE),
(22, 'Elizabeth Taylor', FALSE),
(22, 'Grace Kelly', FALSE),

(23, 'Debbie Reynolds', FALSE),
(23, 'Liza Minnelli', FALSE),
(23, 'Olivia Newton-John', TRUE),
(23, 'Barbra Streisand', FALSE),

(24, 'George Lucas', FALSE),
(24, 'Steven Spielberg', TRUE),
(24, 'James Cameron', FALSE),
(24, 'Ridley Scott', FALSE),

(25, 'Elvis Presley', FALSE),
(25, 'Michael Jackson', TRUE),
(25, 'Prince', FALSE),
(25, 'Freddie Mercury', FALSE),

(26, 'Natalie Portman', FALSE),
(26, 'Carrie Fisher', TRUE),
(26, 'Sigourney Weaver', FALSE),
(26, 'Linda Hamilton', FALSE),

(27, 'Eric Clapton', FALSE),
(27, 'Jimmy Page', FALSE),
(27, 'Jimi Hendrix', TRUE),
(27, 'Keith Richards', FALSE),

(28, 'Jean-Claude Van Damme', FALSE),
(28, 'Bruce Willis', FALSE),
(28, 'Sylvester Stallone', FALSE),
(28, 'Arnold Schwarzenegger', TRUE),

(29, 'Whitney Houston', FALSE),
(29, 'Janet Jackson', FALSE),
(29, 'Tina Turner', FALSE),
(29, 'Madonna', TRUE),

(30, 'F. W. de Klerk', FALSE),
(30, 'Desmond Tutu', FALSE),
(30, 'Nelson Mandela', TRUE),
(30, 'Steve Biko', FALSE);


-- Historic Events Category
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(31, 'Apollo 10', FALSE),
(31, 'Apollo 13', FALSE),
(31, 'Apollo 11', TRUE),
(31, 'Apollo 15', FALSE),

(32, 'Federation of Rhodesia and Nyasaland', FALSE),
(32, 'Soviet Union', TRUE),
(32, 'Czechoslovakia', FALSE),
(32, 'Yugoslav Federation', FALSE),

(33, '1954', FALSE),
(33, '1960', FALSE),
(33, '1964', TRUE),
(33, '1968', FALSE),

(34, 'Vietnam', FALSE),
(34, 'Cuba', TRUE),
(34, 'Afghanistan', FALSE),
(34, 'Nicaragua', FALSE),

(35, 'Operation Iraqi Freedom', FALSE),
(35, 'Operation Desert Storm', TRUE),
(35, 'Operation Enduring Freedom', FALSE),
(35, 'Operation Desert Shield', FALSE),

(36, 'Farm Aid', FALSE),
(36, 'Us Festival', FALSE),
(36, 'Live Aid', TRUE),
(36, 'Band Aid', FALSE),

(37, 'Ronald Reagan', FALSE),
(37, 'George H.W. Bush', FALSE),
(37, 'Bill Clinton', TRUE),
(37, 'Jimmy Carter', FALSE),

(38, 'Pentagon Papers', TRUE),
(38, 'Watergate', FALSE),
(38, 'Iran-Contra Affair', FALSE),
(38, 'Whitewater', FALSE),

(39, 'Richard Nixon', FALSE),
(39, 'John F. Kennedy', FALSE),
(39, 'Lyndon B. Johnson', TRUE),
(39, 'Dwight D. Eisenhower', FALSE),

(40, 'Tibet', FALSE),
(40, 'Macau', FALSE),
(40, 'Hong Kong', TRUE),
(40, 'Singapore', FALSE);

-- Historic Events Category spanish
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(41, 'Apollo 10', FALSE),
(41, 'Apollo 13', FALSE),
(41, 'Apollo 11', TRUE),
(41, 'Apollo 15', FALSE),

(42, 'Federation of Rhodesia and Nyasaland', FALSE),
(42, 'Soviet Union', TRUE),
(42, 'Czechoslovakia', FALSE),
(42, 'Yugoslav Federation', FALSE),

(43, '1954', FALSE),
(43, '1960', FALSE),
(43, '1964', TRUE),
(43, '1968', FALSE),

(44, 'Vietnam', FALSE),
(44, 'Cuba', TRUE),
(44, 'Afghanistan', FALSE),
(44, 'Nicaragua', FALSE),

(45, 'Operation Iraqi Freedom', FALSE),
(45, 'Operation Desert Storm', TRUE),
(45, 'Operation Enduring Freedom', FALSE),
(45, 'Operation Desert Shield', FALSE),

(46, 'Farm Aid', FALSE),
(46, 'Us Festival', FALSE),
(46, 'Live Aid', TRUE),
(46, 'Band Aid', FALSE),

(47, 'Ronald Reagan', FALSE),
(47, 'George H.W. Bush', FALSE),
(47, 'Bill Clinton', TRUE),
(47, 'Jimmy Carter', FALSE),

(48, 'Pentagon Papers', TRUE),
(48, 'Watergate', FALSE),
(48, 'Iran-Contra Affair', FALSE),
(48, 'Whitewater', FALSE),

(49, 'Richard Nixon', FALSE),
(49, 'John F. Kennedy', FALSE),
(49, 'Lyndon B. Johnson', TRUE),
(49, 'Dwight D. Eisenhower', FALSE),

(50, 'Tibet', FALSE),
(50, 'Macau', FALSE),
(50, 'Hong Kong', TRUE),
(50, 'Singapore', FALSE);

-- Historic Events Category french
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(51, 'Apollo 10', FALSE),
(51, 'Apollo 13', FALSE),
(51, 'Apollo 11', TRUE),
(51, 'Apollo 15', FALSE),

(52, 'Federation of Rhodesia and Nyasaland', FALSE),
(52, 'Soviet Union', TRUE),
(52, 'Czechoslovakia', FALSE),
(52, 'Yugoslav Federation', FALSE),

(53, '1954', FALSE),
(53, '1960', FALSE),
(53, '1964', TRUE),
(53, '1968', FALSE),

(54, 'Vietnam', FALSE),
(54, 'Cuba', TRUE),
(54, 'Afghanistan', FALSE),
(54, 'Nicaragua', FALSE),

(55, 'Operation Iraqi Freedom', FALSE),
(55, 'Operation Desert Storm', TRUE),
(55, 'Operation Enduring Freedom', FALSE),
(55, 'Operation Desert Shield', FALSE),

(56, 'Farm Aid', FALSE),
(56, 'Us Festival', FALSE),
(56, 'Live Aid', TRUE),
(56, 'Band Aid', FALSE),

(57, 'Ronald Reagan', FALSE),
(57, 'George H.W. Bush', FALSE),
(57, 'Bill Clinton', TRUE),
(57, 'Jimmy Carter', FALSE),

(58, 'Pentagon Papers', TRUE),
(58, 'Watergate', FALSE),
(58, 'Iran-Contra Affair', FALSE),
(58, 'Whitewater', FALSE),

(59, 'Richard Nixon', FALSE),
(59, 'John F. Kennedy', FALSE),
(59, 'Lyndon B. Johnson', TRUE),
(59, 'Dwight D. Eisenhower', FALSE),

(60, 'Tibet', FALSE),
(60, 'Macau', FALSE),
(60, 'Hong Kong', TRUE),
(60, 'Singapore', FALSE);


-- Cars Category
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(61, 'Dodge Challenger', FALSE),
(61, 'Chevrolet Camaro', FALSE),
(61, 'Plymouth Barracuda', FALSE),
(61, 'Ford Mustang', TRUE),

(62, 'Carbon fiber', FALSE),
(62, 'Aluminum', TRUE),
(62, 'Titanium', FALSE),
(62, 'Steel', FALSE),

(63, 'Ford', FALSE),
(63, 'Chevrolet', FALSE),
(63, 'Chrysler', TRUE),
(63, 'GMC', FALSE),

(64, 'Bentley', FALSE),
(64, 'Rolls-Royce', TRUE),
(64, 'Aston Martin', FALSE),
(64, 'Land Rover', FALSE),

(65, 'Drag Racing', FALSE),
(65, 'Circuit Racing', FALSE),
(65, 'Rally Racing', TRUE),
(65, 'Drift Racing', FALSE),

(66, 'Ferrari', FALSE),
(66, 'Lamborghini', TRUE),
(66, 'Maserati', FALSE),
(66, 'Fiat', FALSE),

(67, 'Acura', FALSE),
(67, 'Lexus', TRUE),
(67, 'Infiniti', FALSE),
(67, 'Mazda', FALSE),

(68, 'Mercedes-Benz', FALSE),
(68, 'Audi', FALSE),
(68, 'Porsche', TRUE),
(68, 'BMW', FALSE),

(69, 'Saab', FALSE),
(69, 'Volvo', TRUE),
(69, 'Scania', FALSE),
(69, 'Koenigsegg', FALSE),

(70, 'Accord', FALSE),
(70, 'Prelude', FALSE),
(70, 'Insight', FALSE),
(70, 'Civic', TRUE);

-- Cars Category spanish
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(71, 'Dodge Challenger', FALSE),
(71, 'Chevrolet Camaro', FALSE),
(71, 'Plymouth Barracuda', FALSE),
(71, 'Ford Mustang', TRUE),

(72, 'Carbon fiber', FALSE),
(72, 'Aluminum', TRUE),
(72, 'Titanium', FALSE),
(72, 'Steel', FALSE),

(73, 'Ford', FALSE),
(73, 'Chevrolet', FALSE),
(73, 'Chrysler', TRUE),
(73, 'GMC', FALSE),

(74, 'Bentley', FALSE),
(74, 'Rolls-Royce', TRUE),
(74, 'Aston Martin', FALSE),
(74, 'Land Rover', FALSE),

(75, 'Drag Racing', FALSE),
(75, 'Circuit Racing', FALSE),
(75, 'Rally Racing', TRUE),
(75, 'Drift Racing', FALSE),

(76, 'Ferrari', FALSE),
(76, 'Lamborghini', TRUE),
(76, 'Maserati', FALSE),
(76, 'Fiat', FALSE),

(77, 'Acura', FALSE),
(77, 'Lexus', TRUE),
(77, 'Infiniti', FALSE),
(77, 'Mazda', FALSE),

(78, 'Mercedes-Benz', FALSE),
(78, 'Audi', FALSE),
(78, 'Porsche', TRUE),
(78, 'BMW', FALSE),

(79, 'Saab', FALSE),
(79, 'Volvo', TRUE),
(79, 'Scania', FALSE),
(79, 'Koenigsegg', FALSE),

(80, 'Accord', FALSE),
(80, 'Prelude', FALSE),
(80, 'Insight', FALSE),
(80, 'Civic', TRUE);

-- Cars Category french
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(81, 'Dodge Challenger', FALSE),
(81, 'Chevrolet Camaro', FALSE),
(81, 'Plymouth Barracuda', FALSE),
(81, 'Ford Mustang', TRUE),

(82, 'Carbon fiber', FALSE),
(82, 'Aluminum', TRUE),
(82, 'Titanium', FALSE),
(82, 'Steel', FALSE),

(83, 'Ford', FALSE),
(83, 'Chevrolet', FALSE),
(83, 'Chrysler', TRUE),
(83, 'GMC', FALSE),

(84, 'Bentley', FALSE),
(84, 'Rolls-Royce', TRUE),
(84, 'Aston Martin', FALSE),
(84, 'Land Rover', FALSE),

(85, 'Drag Racing', FALSE),
(85, 'Circuit Racing', FALSE),
(85, 'Rally Racing', TRUE),
(85, 'Drift Racing', FALSE),

(86, 'Ferrari', FALSE),
(86, 'Lamborghini', TRUE),
(86, 'Maserati', FALSE),
(86, 'Fiat', FALSE),

(87, 'Acura', FALSE),
(87, 'Lexus', TRUE),
(87, 'Infiniti', FALSE),
(87, 'Mazda', FALSE),

(88, 'Mercedes-Benz', FALSE),
(88, 'Audi', FALSE),
(88, 'Porsche', TRUE),
(88, 'BMW', FALSE),

(89, 'Saab', FALSE),
(89, 'Volvo', TRUE),
(89, 'Scania', FALSE),
(89, 'Koenigsegg', FALSE),

(90, 'Accord', FALSE),
(90, 'Prelude', FALSE),
(90, 'Insight', FALSE),
(90, 'Civic', TRUE);


-- Music Category
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(91, 'The Rolling Stones', FALSE),
(91, 'The Who', FALSE),
(91, 'Led Zeppelin', FALSE),
(91, 'The Beatles', TRUE),

(92, 'Pearl Jam', FALSE),
(92, 'Red Hot Chili Peppers', FALSE),
(92, 'Nirvana', TRUE),
(92, 'Radiohead', FALSE),

(93, 'Prince', FALSE),
(93, 'Michael Jackson', TRUE),
(93, 'Lionel Richie', FALSE),
(93, 'George Michael', FALSE),

(94, 'Bee Gees', TRUE),
(94, 'ABBA', FALSE),
(94, 'The Jackson 5', FALSE),
(94, 'Kool & The Gang', FALSE),

(95, 'David Bowie', FALSE),
(95, 'Stevie Wonder', FALSE),
(95, 'Prince', TRUE),
(95, 'Bruce Springsteen', FALSE),

(96, 'The Rolling Stones', FALSE),
(96, 'Queen', TRUE),
(96, 'Pink Floyd', FALSE),
(96, 'Aerosmith', FALSE),

(97, 'The Eagles', TRUE),
(97, 'Fleetwood Mac', FALSE),
(97, 'The Doors', FALSE),
(97, 'Lynyrd Skynyrd', FALSE),

(98, 'Jim Morrison', FALSE),
(98, 'Eric Clapton', FALSE),
(98, 'Jimi Hendrix', TRUE),
(98, 'Bob Dylan', FALSE),

(99, 'Peter Tosh', FALSE),
(99, 'Jimmy Cliff', FALSE),
(99, 'Bob Marley', TRUE),
(99, 'Toots Hibbert', FALSE),

(100, 'Led Zeppelin', FALSE),
(100, 'The Who', FALSE),
(100, 'Pink Floyd', TRUE),
(100, 'Deep Purple', FALSE);

-- Music Category spanish
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(101, 'The Rolling Stones', FALSE),
(101, 'The Who', FALSE),
(101, 'Led Zeppelin', FALSE),
(101, 'The Beatles', TRUE),

(102, 'Pearl Jam', FALSE),
(102, 'Red Hot Chili Peppers', FALSE),
(102, 'Nirvana', TRUE),
(102, 'Radiohead', FALSE),

(103, 'Prince', FALSE),
(103, 'Michael Jackson', TRUE),
(103, 'Lionel Richie', FALSE),
(103, 'George Michael', FALSE),

(104, 'Bee Gees', TRUE),
(104, 'ABBA', FALSE),
(104, 'The Jackson 5', FALSE),
(104, 'Kool & The Gang', FALSE),

(105, 'David Bowie', FALSE),
(105, 'Stevie Wonder', FALSE),
(105, 'Prince', TRUE),
(105, 'Bruce Springsteen', FALSE),

(106, 'The Rolling Stones', FALSE),
(106, 'Queen', TRUE),
(106, 'Pink Floyd', FALSE),
(106, 'Aerosmith', FALSE),

(107, 'The Eagles', TRUE),
(107, 'Fleetwood Mac', FALSE),
(107, 'The Doors', FALSE),
(107, 'Lynyrd Skynyrd', FALSE),

(108, 'Jim Morrison', FALSE),
(108, 'Eric Clapton', FALSE),
(108, 'Jimi Hendrix', TRUE),
(108, 'Bob Dylan', FALSE),

(109, 'Peter Tosh', FALSE),
(109, 'Jimmy Cliff', FALSE),
(109, 'Bob Marley', TRUE),
(109, 'Toots Hibbert', FALSE),

(110, 'Led Zeppelin', FALSE),
(110, 'The Who', FALSE),
(110, 'Pink Floyd', TRUE),
(110, 'Deep Purple', FALSE);

-- Music Category french
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(111, 'The Rolling Stones', FALSE),
(111, 'The Who', FALSE),
(111, 'Led Zeppelin', FALSE),
(111, 'The Beatles', TRUE),

(112, 'Pearl Jam', FALSE),
(112, 'Red Hot Chili Peppers', FALSE),
(112, 'Nirvana', TRUE),
(112, 'Radiohead', FALSE),

(113, 'Prince', FALSE),
(113, 'Michael Jackson', TRUE),
(113, 'Lionel Richie', FALSE),
(113, 'George Michael', FALSE),

(114, 'Bee Gees', TRUE),
(114, 'ABBA', FALSE),
(114, 'The Jackson 5', FALSE),
(114, 'Kool & The Gang', FALSE),

(115, 'David Bowie', FALSE),
(115, 'Stevie Wonder', FALSE),
(115, 'Prince', TRUE),
(115, 'Bruce Springsteen', FALSE),

(116, 'The Rolling Stones', FALSE),
(116, 'Queen', TRUE),
(116, 'Pink Floyd', FALSE),
(116, 'Aerosmith', FALSE),

(117, 'The Eagles', TRUE),
(117, 'Fleetwood Mac', FALSE),
(117, 'The Doors', FALSE),
(117, 'Lynyrd Skynyrd', FALSE),

(118, 'Jim Morrison', FALSE),
(118, 'Eric Clapton', FALSE),
(118, 'Jimi Hendrix', TRUE),
(118, 'Bob Dylan', FALSE),

(119, 'Peter Tosh', FALSE),
(119, 'Jimmy Cliff', FALSE),
(119, 'Bob Marley', TRUE),
(119, 'Toots Hibbert', FALSE),

(120, 'Led Zeppelin', FALSE),
(120, 'The Who', FALSE),
(120, 'Pink Floyd', TRUE),
(120, 'Deep Purple', FALSE);


-- Fashion Category
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(121, 'Punk', FALSE),
(121, 'Futurism', TRUE),
(121, 'New Romantic', FALSE),
(121, 'Boho chic', FALSE),

(122, 'Cameo necklaces', FALSE),
(122, 'Love beads', FALSE),
(122, 'Puka shell necklaces', FALSE),
(122, 'Candy jewelry', TRUE),

(123, 'Vivienne Westwood', FALSE),
(123, 'Mary Quant', TRUE),
(123, 'Stella McCartney', FALSE),
(123, 'Victoria Beckham', FALSE),

(124, 'Bangles', FALSE),
(124, 'Jelly bracelets', TRUE),
(124, 'Chokers', FALSE),
(124, 'Loom bands', FALSE),

(125, 'Stilettos', FALSE),
(125, 'Ankle boots', FALSE),
(125, 'Ballet flats', FALSE),
(125, 'Slouch boots', TRUE),

(126, 'Gucci', FALSE),
(126, 'Chanel', TRUE),
(126, 'Prada', FALSE),
(126, 'Fendi', FALSE),

(127, 'Aviator glasses', FALSE),
(127, 'Cat-eye glasses', FALSE),
(127, 'John Lennon glasses', TRUE),
(127, 'Wayfarer glasses', FALSE),

(128, 'Feather filling', FALSE),
(128, 'Epaulettes', TRUE),
(128, 'Fringe on the back', FALSE),
(128, 'Fur-lined hoods', FALSE),

(129, 'Chokers', TRUE),
(129, 'Lariats', FALSE),
(129, 'Bolo ties', FALSE),
(129, 'Medallions', FALSE),

(130, 'Bell-bottoms', TRUE),
(130, 'Skinny jeans', FALSE),
(130, 'Capris', FALSE),
(130, 'Jeggings', FALSE);

-- Fashion Category spanish
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(131, 'Punk', FALSE),
(131, 'Futurism', TRUE),
(131, 'New Romantic', FALSE),
(131, 'Boho chic', FALSE),

(132, 'Cameo necklaces', FALSE),
(132, 'Love beads', FALSE),
(132, 'Puka shell necklaces', FALSE),
(132, 'Candy jewelry', TRUE),

(133, 'Vivienne Westwood', FALSE),
(133, 'Mary Quant', TRUE),
(133, 'Stella McCartney', FALSE),
(133, 'Victoria Beckham', FALSE),

(134, 'Bangles', FALSE),
(134, 'Jelly bracelets', TRUE),
(134, 'Chokers', FALSE),
(134, 'Loom bands', FALSE),

(135, 'Stilettos', FALSE),
(135, 'Ankle boots', FALSE),
(135, 'Ballet flats', FALSE),
(135, 'Slouch boots', TRUE),

(136, 'Gucci', FALSE),
(136, 'Chanel', TRUE),
(136, 'Prada', FALSE),
(136, 'Fendi', FALSE),

(137, 'Aviator glasses', FALSE),
(137, 'Cat-eye glasses', FALSE),
(137, 'John Lennon glasses', TRUE),
(137, 'Wayfarer glasses', FALSE),

(138, 'Feather filling', FALSE),
(138, 'Epaulettes', TRUE),
(138, 'Fringe on the back', FALSE),
(138, 'Fur-lined hoods', FALSE),

(139, 'Chokers', TRUE),
(139, 'Lariats', FALSE),
(139, 'Bolo ties', FALSE),
(139, 'Medallions', FALSE),

(140, 'Bell-bottoms', TRUE),
(140, 'Skinny jeans', FALSE),
(140, 'Capris', FALSE),
(140, 'Jeggings', FALSE);

-- Fashion Category french
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(141, 'Punk', FALSE),
(141, 'Futurism', TRUE),
(141, 'New Romantic', FALSE),
(141, 'Boho chic', FALSE),

(142, 'Cameo necklaces', FALSE),
(142, 'Love beads', FALSE),
(142, 'Puka shell necklaces', FALSE),
(142, 'Candy jewelry', TRUE),

(143, 'Vivienne Westwood', FALSE),
(143, 'Mary Quant', TRUE),
(143, 'Stella McCartney', FALSE),
(143, 'Victoria Beckham', FALSE),

(144, 'Bangles', FALSE),
(144, 'Jelly bracelets', TRUE),
(144, 'Chokers', FALSE),
(144, 'Loom bands', FALSE),

(145, 'Stilettos', FALSE),
(145, 'Ankle boots', FALSE),
(145, 'Ballet flats', FALSE),
(145, 'Slouch boots', TRUE),

(146, 'Gucci', FALSE),
(146, 'Chanel', TRUE),
(146, 'Prada', FALSE),
(146, 'Fendi', FALSE),

(147, 'Aviator glasses', FALSE),
(147, 'Cat-eye glasses', FALSE),
(147, 'John Lennon glasses', TRUE),
(147, 'Wayfarer glasses', FALSE),

(148, 'Feather filling', FALSE),
(148, 'Epaulettes', TRUE),
(148, 'Fringe on the back', FALSE),
(148, 'Fur-lined hoods', FALSE),

(149, 'Chokers', TRUE),
(149, 'Lariats', FALSE),
(149, 'Bolo ties', FALSE),
(149, 'Medallions', FALSE),

(150, 'Bell-bottoms', TRUE),
(150, 'Skinny jeans', FALSE),
(150, 'Capris', FALSE),
(150, 'Jeggings', FALSE);
