USE JEOPARODY_DATABASE;

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
(1, 1, 'This legendary boxer was originally named Cassius Clay.', '/path/to/image1.jpg'),
(1, 1, 'This actress starred in the movie "Breakfast at Tiffany\'s."', '/path/to/image2.jpg'),
(1, 1, 'This actress and singer played Sandy in the movie "Grease."', '/path/to/image3.jpg'),
(1, 1, 'This director is known for the movie "E.T. the Extra-Terrestrial" in the 1980s.', '/path/to/image4.jpg'),
(1, 1, 'He was known as the "King of Pop."', '/path/to/image5.jpg'),
(1, 1, 'This actress played Princess Leia in the "Star Wars" series.', '/path/to/image6.jpg'),
(1, 1, 'This legendary guitarist played the national anthem at Woodstock in 1969.', '/path/to/image7.jpg'),
(1, 1, 'He became famous for playing the role of "The Terminator" in the 1984 movie.', '/path/to/image8.jpg'),
(1, 1, 'This pop icon became the "Queen of Pop" during the 1980s.', '/path/to/image9.jpg'),
(1, 1, 'This South African leader was released from prison in 1990 after 27 years.', '/path/to/image10.jpg');

-- Historic Events Category --
-- English Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(2, 1, 'In 1969, humans first set foot on the Moon during this mission.', '/path/to/image11.jpg'),
(2, 1, 'In 1991, the dissolution of this federation marked the end of the Cold War era.', '/path/to/image12.jpg'),
(2, 1, 'The Civil Rights Act, which aimed to end segregation in public places and banned employment discrimination, was signed into law in this year.', '/path/to/image13.jpg'),
(2, 1, 'In 1962, the U.S. and the Soviet Union were on the brink of nuclear war due to missiles in this country.', '/path/to/image14.jpg'),
(2, 1, 'This 1991 operation was a coalition-led war against Iraq following its invasion of Kuwait.', '/path/to/image15.jpg'),
(2, 1, 'This concert held in 1985 was aimed at raising funds for famine relief in Ethiopia.', '/path/to/image16.jpg'),
(2, 1, 'This American president was impeached in 1998 but was not removed from office.', '/path/to/image17.jpg'),
(2, 1, 'This scandal in the early 1970s involved the U.S. government\'s secret bombing campaign during the Vietnam War.', '/path/to/image18.jpg'),
(2, 1, 'This U.S. President signed the Civil Rights Act of 1964 into law.', '/path/to/image19.jpg'),
(2, 1, 'In 1997, the United Kingdom handed over the sovereignty of this territory to China.', '/path/to/image20.jpg');

-- Cars Category --
-- English Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(3, 1, 'This American muscle car was introduced by Ford in 1964.', '/path/to/image21.jpg'),
(3, 1, 'The 1991 Acura NSX was notable for its use of this lightweight material in its chassis.', '/path/to/image22.jpg'),
(3, 1, 'In 1983, this American car company introduced the minivan, revolutionizing family transport.', '/path/to/image23.jpg'),
(3, 1, 'Which British car manufacturer produced the luxury Silver Shadow model in the 1960s?', '/path/to/image24.jpg'),
(3, 1, 'The Subaru Impreza WRX, introduced in the 1990s, was especially popular in this kind of racing.', '/path/to/image25.jpg'),
(3, 1, 'Which Italian car manufacturer introduced the Countach in 1974?', '/path/to/image26.jpg'),
(3, 1, 'Which luxury Japanese car brand was launched by Toyota in 1989?', '/path/to/image27.jpg'),
(3, 1, 'Which car manufacturer produced the 911 model, first introduced in the early 1960s?', '/path/to/image28.jpg'),
(3, 1, 'This Swedish car manufacturer introduced the safety-focused 240 series in the 1970s.', '/path/to/image29.jpg'),
(3, 1, 'Honda introduced this model in 1972, which has since become one of the best-selling cars worldwide.', '/path/to/image30.jpg');


-- Music Category --
-- English Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(4, 1, 'This band, known for hits like "Hey Jude" and "Let It Be", is often considered the most influential rock band of all time.', '/path/to/image31.jpg'),
(4, 1, 'In 1991, which band released the album "Nevermind" featuring the hit "Smells Like Teen Spirit"?', '/path/to/image32.jpg'),
(4, 1, 'Which artist released the iconic album "Thriller" in 1982?', '/path/to/image33.jpg'),
(4, 1, 'The song "Stayin'' Alive" was a hit for this group and featured prominently in the movie "Saturday Night Fever".', '/path/to/image34.jpg'),
(4, 1, 'The 1984 song "Purple Rain" is a track from the album of the same name by this artist.', '/path/to/image35.jpg'),
(4, 1, 'Which band, fronted by Freddie Mercury, released hits like "Bohemian Rhapsody" and "We Are The Champions"?', '/path/to/image36.jpg'),
(4, 1, 'This rock band, known for its distinctive guitar sound, released "Hotel California" in 1976.', '/path/to/image37.jpg'),
(4, 1, 'This artist''s 1969 performance at Woodstock, where he played a rendition of "The Star-Spangled Banner", is legendary.', '/path/to/image38.jpg'),
(4, 1, 'This Jamaican singer is known as the "King of Reggae" and had hits like "No Woman, No Cry" and "Redemption Song".', '/path/to/image39.jpg'),
(4, 1, 'Which British band released the critically acclaimed album "The Dark Side of the Moon" in 1973?', '/path/to/image40.jpg');

-- Fashion Category
-- English Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(5, 1, 'In the late 1960s, this fashion trend was inspired by space age design and often featured metallic materials and geometric shapes.', '/path/to/image41.jpg'),
(5, 1, 'This fashion trend of the late 1980s and early 1990s involved brightly colored beads and plastic shapes worn as necklaces.', '/path/to/image42.jpg'),
(5, 1, 'In the 1960s, this British fashion designer popularized the miniskirt.', '/path/to/image43.jpg'),
(5, 1, 'What type of jewelry, often worn in the 1980s, involved multiple rubber bracelets worn at once, popularized by Madonna?', '/path/to/image44.jpg'),
(5, 1, 'In the 1980s, women often wore oversized tops with tight-fitting pants or leggings and this type of footwear.', '/path/to/image45.jpg'),
(5, 1, 'What handbag brand became famous in the 1980s for its quilted purses with a chain strap?', '/path/to/image46.jpg'),
(5, 1, 'In the 1970s, what accessory became popular, characterized by its round shape and colored, often mirrored, lenses?', '/path/to/image47.jpg'),
(5, 1, 'In the 1980s, "Members Only" jackets became a popular fashion item. What distinctive feature did they have?', '/path/to/image48.jpg'),
(5, 1, 'These necklaces, often made of plastic beads, became a popular accessory in the 1990s.', '/path/to/image49.jpg'),
(5, 1, 'In the 1970s, these flared pants became a fashion staple.', '/path/to/image50.jpg');








-- Famous People
-- Spanish Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(1, 2, 'Este legendario boxeador originalmente se llamaba Cassius Clay.', '/path/to/image1.jpg'),
(1, 2, 'Esta actriz protagonizó la película "Desayuno con diamantes".', '/path/to/image2.jpg'),
(1, 2, 'Esta actriz y cantante interpretó a Sandy en la película "Grease".', '/path/to/image3.jpg'),
(1, 2, 'Este director es conocido por la película "E.T. el Extraterrestre" en los años 80.', '/path/to/image4.jpg'),
(1, 2, 'Él fue conocido como el "Rey del Pop".', '/path/to/image5.jpg'),
(1, 2, 'Esta actriz interpretó a la Princesa Leia en la serie "Star Wars".', '/path/to/image6.jpg'),
(1, 2, 'Este legendario guitarrista tocó el himno nacional en Woodstock en 1969.', '/path/to/image7.jpg'),
(1, 2, 'Se hizo famoso por interpretar el papel de "El Terminator" en la película de 1984.', '/path/to/image8.jpg'),
(1, 2, 'Esta ícono del pop se convirtió en la "Reina del Pop" durante los años 80.', '/path/to/image9.jpg'),
(1, 2, 'Este líder sudafricano fue liberado de prisión en 1990 después de 27 años.', '/path/to/image10.jpg');


-- Historic Events
-- Spanish Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(2, 2, 'En 1969, los humanos pisaron la Luna por primera vez durante esta misión.', '/path/to/image11.jpg'),
(2, 2, 'En 1991, la disolución de esta federación marcó el fin de la era de la Guerra Fría.', '/path/to/image12.jpg'),
(2, 2, 'La Ley de Derechos Civiles, que buscaba poner fin a la segregación en lugares públicos y prohibía la discriminación laboral, fue promulgada en este año.', '/path/to/image13.jpg'),
(2, 2, 'En 1962, EE.UU. y la Unión Soviética estuvieron al borde de una guerra nuclear debido a misiles en este país.', '/path/to/image14.jpg'),
(2, 2, 'Esta operación de 1991 fue una guerra liderada por una coalición contra Iraq tras su invasión a Kuwait.', '/path/to/image15.jpg'),
(2, 2, 'Este concierto celebrado en 1985 tuvo como objetivo recaudar fondos para aliviar la hambruna en Etiopía.', '/path/to/image16.jpg'),
(2, 2, 'Este presidente estadounidense fue sometido a juicio político en 1998 pero no fue destituido.', '/path/to/image17.jpg'),
(2, 2, 'Este escándalo a principios de la década de 1970 involucró la campaña de bombardeo secreta del gobierno de EE.UU. durante la Guerra de Vietnam.', '/path/to/image18.jpg'),
(2, 2, 'Este Presidente de EE.UU. promulgó la Ley de Derechos Civiles de 1964.', '/path/to/image19.jpg'),
(2, 2, 'En 1997, el Reino Unido cedió la soberanía de este territorio a China.', '/path/to/image20.jpg');


-- Cars
-- Spanish Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(3, 2, 'Este muscle car americano fue introducido por Ford en 1964.', '/path/to/image21.jpg'),
(3, 2, 'El Acura NSX de 1991 fue notable por el uso de este material ligero en su chasis.', '/path/to/image22.jpg'),
(3, 2, 'En 1983, esta compañía americana de automóviles introdujo la minivan, revolucionando el transporte familiar.', '/path/to/image23.jpg'),
(3, 2, '¿Qué fabricante británico de coches produjo el lujoso modelo Silver Shadow en la década de 1960?', '/path/to/image24.jpg'),
(3, 2, 'El Subaru Impreza WRX, introducido en la década de 1990, fue especialmente popular en este tipo de carreras.', '/path/to/image25.jpg'),
(3, 2, '¿Qué fabricante italiano de coches introdujo el Countach en 1974?', '/path/to/image26.jpg'),
(3, 2, '¿Qué marca japonesa de coches de lujo fue lanzada por Toyota en 1989?', '/path/to/image27.jpg'),
(3, 2, '¿Qué fabricante de coches produjo el modelo 911, introducido por primera vez a principios de la década de 1960?', '/path/to/image28.jpg'),
(3, 2, 'Este fabricante sueco de automóviles introdujo la serie 240 centrada en la seguridad en la década de 1970.', '/path/to/image29.jpg'),
(3, 2, 'Honda introdujo este modelo en 1972, que desde entonces se ha convertido en uno de los coches más vendidos en el mundo.', '/path/to/image30.jpg');


-- Music
-- Spanish Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(4, 2, 'Esta banda, conocida por éxitos como "Hey Jude" y "Let It Be", es a menudo considerada la banda de rock más influyente de todos los tiempos.', '/path/to/image31.jpg'),
(4, 2, 'En 1991, ¿qué banda lanzó el álbum "Nevermind" que incluye el éxito "Smells Like Teen Spirit"?', '/path/to/image32.jpg'),
(4, 2, '¿Qué artista lanzó el icónico álbum "Thriller" en 1982?', '/path/to/image33.jpg'),
(4, 2, 'La canción "Stayin'' Alive" fue un éxito para este grupo y se destacó en la película "Fiebre del sábado noche".', '/path/to/image34.jpg'),
(4, 2, 'La canción "Purple Rain" de 1984 es una pista del álbum del mismo nombre de este artista.', '/path/to/image35.jpg'),
(4, 2, '¿Qué banda, liderada por Freddie Mercury, lanzó éxitos como "Bohemian Rhapsody" y "We Are The Champions"?', '/path/to/image36.jpg'),
(4, 2, 'Esta banda de rock, conocida por su distintivo sonido de guitarra, lanzó "Hotel California" en 1976.', '/path/to/image37.jpg'),
(4, 2, 'La actuación de este artista en 1969 en Woodstock, donde interpretó una versión de "The Star-Spangled Banner", es legendaria.', '/path/to/image38.jpg'),
(4, 2, 'Este cantante jamaicano es conocido como el "Rey del Reggae" y tuvo éxitos como "No Woman, No Cry" y "Redemption Song".', '/path/to/image39.jpg'),
(4, 2, '¿Qué banda británica lanzó el aclamado álbum "The Dark Side of the Moon" en 1973?', '/path/to/image40.jpg');



-- Fashion
-- Spanish Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(5, 2, 'A finales de la década de 1960, esta tendencia de moda se inspiró en el diseño de la era espacial y a menudo presentaba materiales metálicos y formas geométricas.', '/path/to/image41.jpg'),
(5, 2, 'Esta tendencia de moda de finales de la década de 1980 y principios de la de 1990 involucraba cuentas de colores brillantes y formas de plástico usadas como collares.', '/path/to/image42.jpg'),
(5, 2, 'En la década de 1960, esta diseñadora de moda británica popularizó la minifalda.', '/path/to/image43.jpg'),
(5, 2, '¿Qué tipo de joyería, a menudo usada en la década de 1980, involucraba múltiples pulseras de goma usadas al mismo tiempo, popularizadas por Madonna?', '/path/to/image44.jpg'),
(5, 2, 'En la década de 1980, las mujeres a menudo usaban tops grandes con pantalones o leggings ajustados y este tipo de calzado.', '/path/to/image45.jpg'),
(5, 2, '¿Qué marca de bolsos se hizo famosa en la década de 1980 por sus bolsos acolchados con una correa de cadena?', '/path/to/image46.jpg'),
(5, 2, 'En la década de 1970, ¿qué accesorio se popularizó, caracterizado por su forma redonda y lentes coloreadas, a menudo espejadas?', '/path/to/image47.jpg'),
(5, 2, 'En la década de 1980, las chaquetas "Members Only" se convirtieron en un artículo de moda popular. ¿Qué característica distintiva tenían?', '/path/to/image48.jpg'),
(5, 2, 'Estos collares, a menudo hechos de cuentas de plástico, se convirtieron en un accesorio popular en la década de 1990.', '/path/to/image49.jpg'),
(5, 2, 'En la década de 1970, estos pantalones acampanados se convirtieron en un elemento básico de la moda.', '/path/to/image50.jpg');




-- Famous People
-- French Questions
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(1, 3, 'Ce boxeur légendaire s’appelait à l’origine Cassius Clay.', '/path/to/image1.jpg'),
(1, 3, 'Cette actrice a joué dans le film "Diamants sur canapé".', '/path/to/image2.jpg'),
(1, 3, 'Cette actrice et chanteuse a interprété Sandy dans le film "Grease".', '/path/to/image3.jpg'),
(1, 3, 'Ce réalisateur est connu pour le film "E.T. l’extra-terrestre" des années 80.', '/path/to/image4.jpg'),
(1, 3, 'Il était connu comme le "Roi de la Pop".', '/path/to/image5.jpg'),
(1, 3, 'Cette actrice a joué la Princesse Leia dans la série "Star Wars".', '/path/to/image6.jpg'),
(1, 3, 'Ce guitariste légendaire a joué l’hymne national à Woodstock en 1969.', '/path/to/image7.jpg'),
(1, 3, 'Il est devenu célèbre pour avoir joué le rôle de "Terminator" dans le film de 1984.', '/path/to/image8.jpg'),
(1, 3, 'Cette icône de la pop est devenue la "Reine de la Pop" dans les années 80.', '/path/to/image9.jpg'),
(1, 3, 'Ce leader sud-africain a été libéré de prison en 1990 après 27 ans.', '/path/to/image10.jpg');


-- Historic Events
-- French Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(2, 3, 'En 1969, les humains ont mis le pied sur la Lune pour la première fois lors de cette mission.', '/path/to/image11.jpg'),
(2, 3, 'En 1991, la dissolution de cette fédération a marqué la fin de l\'ère de la Guerre Froide.', '/path/to/image12.jpg'),
(2, 3, 'La loi sur les droits civiques, visant à mettre fin à la ségrégation dans les lieux publics et interdisant la discrimination à l\'emploi, a été promulguée cette année.', '/path/to/image13.jpg'),
(2, 3, 'En 1962, les États-Unis et l\'Union Soviétique étaient au bord de la guerre nucléaire en raison de missiles dans ce pays.', '/path/to/image14.jpg'),
(2, 3, 'Cette opération de 1991 était une guerre menée par une coalition contre l\'Irak suite à son invasion du Koweït.', '/path/to/image15.jpg'),
(2, 3, 'Ce concert organisé en 1985 visait à lever des fonds pour l\'aide contre la famine en Éthiopie.', '/path/to/image16.jpg'),
(2, 3, 'Ce président américain a été destitué en 1998 mais n\'a pas été écarté du pouvoir.', '/path/to/image17.jpg'),
(2, 3, 'Ce scandale au début des années 1970 concernait la campagne de bombardements secrets du gouvernement américain pendant la guerre du Vietnam.', '/path/to/image18.jpg'),
(2, 3, 'Ce président des États-Unis a signé la loi sur les droits civiques de 1964.', '/path/to/image19.jpg'),
(2, 3, 'En 1997, le Royaume-Uni a rendu la souveraineté de ce territoire à la Chine.', '/path/to/image20.jpg');



-- Cars
-- French Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(3, 3, 'Cette voiture muscle américaine a été introduite par Ford en 1964.', '/path/to/image21.jpg'),
(3, 3, 'La Acura NSX de 1991 était notable pour l\'utilisation de ce matériau léger dans son châssis.', '/path/to/image22.jpg'),
(3, 3, 'En 1983, cette compagnie automobile américaine a introduit le monospace, révolutionnant le transport familial.', '/path/to/image23.jpg'),
(3, 3, 'Quel constructeur automobile britannique a produit le modèle de luxe Silver Shadow dans les années 1960?', '/path/to/image24.jpg'),
(3, 3, 'La Subaru Impreza WRX, introduite dans les années 1990, était particulièrement populaire dans ce type de course.', '/path/to/image25.jpg'),
(3, 3, 'Quel constructeur automobile italien a introduit la Countach en 1974?', '/path/to/image26.jpg'),
(3, 3, 'Quelle marque japonaise de voitures de luxe a été lancée par Toyota en 1989?', '/path/to/image27.jpg'),
(3, 3, 'Quel constructeur automobile a produit le modèle 911, introduit pour la première fois au début des années 1960?', '/path/to/image28.jpg'),
(3, 3, 'Ce constructeur automobile suédois a introduit la série 240 axée sur la sécurité dans les années 1970.', '/path/to/image29.jpg'),
(3, 3, 'Honda a introduit ce modèle en 1972, qui est depuis devenu l\'une des voitures les plus vendues au monde.', '/path/to/image30.jpg');



-- Music
-- French Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(4, 3, 'Ce groupe, connu pour des hits comme "Hey Jude" et "Let It Be", est souvent considéré comme le groupe de rock le plus influent de tous les temps.', '/path/to/image31.jpg'),
(4, 3, 'En 1991, quel groupe a sorti l''album "Nevermind" avec le hit "Smells Like Teen Spirit"?', '/path/to/image32.jpg'),
(4, 3, 'Quel artiste a sorti l''album iconique "Thriller" en 1982?', '/path/to/image33.jpg'),
(4, 3, 'La chanson "Stayin'' Alive" a été un succès pour ce groupe et a été mise en avant dans le film "Saturday Night Fever".', '/path/to/image34.jpg'),
(4, 3, 'La chanson de 1984 "Purple Rain" est un morceau de l''album du même nom de cet artiste.', '/path/to/image35.jpg'),
(4, 3, 'Quel groupe, dirigé par Freddie Mercury, a sorti des hits comme "Bohemian Rhapsody" et "We Are The Champions"?', '/path/to/image36.jpg'),
(4, 3, 'Ce groupe de rock, connu pour son son de guitare distinctif, a sorti "Hotel California" en 1976.', '/path/to/image37.jpg'),
(4, 3, 'La performance de cet artiste en 1969 à Woodstock, où il a joué une version de "The Star-Spangled Banner", est légendaire.', '/path/to/image38.jpg'),
(4, 3, 'Ce chanteur jamaïcain est connu comme le "Roi du Reggae" et a eu des hits comme "No Woman, No Cry" et "Redemption Song".', '/path/to/image39.jpg'),
(4, 3, 'Quel groupe britannique a sorti l''album acclamé par la critique "The Dark Side of the Moon" en 1973?', '/path/to/image40.jpg');



-- Fashion
-- French Translations
INSERT INTO Questions (category_id, language_id, question_text, image_location_data) VALUES
(5, 3, 'À la fin des années 1960, cette tendance mode a été inspirée par le design de l''âge spatial et comportait souvent des matériaux métalliques et des formes géométriques.', '/path/to/image41.jpg'),
(5, 3, 'Cette tendance mode de la fin des années 1980 et du début des années 1990 comportait des perles et des formes en plastique de couleurs vives portées en colliers.', '/path/to/image42.jpg'),
(5, 3, 'Dans les années 1960, cette créatrice de mode britannique a popularisé la minijupe.', '/path/to/image43.jpg'),
(5, 3, 'Quel type de bijoux, souvent portés dans les années 1980, impliquait plusieurs bracelets en caoutchouc portés à la fois, popularisés par Madonna?', '/path/to/image44.jpg'),
(5, 3, 'Dans les années 1980, les femmes portaient souvent des hauts surdimensionnés avec des pantalons ou des leggings moulants et ce type de chaussures.', '/path/to/image45.jpg'),
(5, 3, 'Quelle marque de sac à main est devenue célèbre dans les années 1980 pour ses sacs matelassés avec une bandoulière en chaîne?', '/path/to/image46.jpg'),
(5, 3, 'Dans les années 1970, quel accessoire est devenu populaire, caractérisé par sa forme ronde et ses verres colorés, souvent miroir?', '/path/to/image47.jpg'),
(5, 3, 'Dans les années 1980, les vestes "Members Only" sont devenues un article de mode populaire. Quelle caractéristique distinctive avaient-elles?', '/path/to/image48.jpg'),
(5, 3, 'Ces colliers, souvent faits de perles en plastique, sont devenus un accessoire populaire dans les années 1990.', '/path/to/image49.jpg'),
(5, 3, 'Dans les années 1970, ces pantalons évasés sont devenus un incontournable de la mode.', '/path/to/image50.jpg');




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


-- Historic Events Category
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(11, 'Apollo 10', FALSE),
(11, 'Apollo 13', FALSE),
(11, 'Apollo 11', TRUE),
(11, 'Apollo 15', FALSE),

(12, 'Federation of Rhodesia and Nyasaland', FALSE),
(12, 'Soviet Union', TRUE),
(12, 'Czechoslovakia', FALSE),
(12, 'Yugoslav Federation', FALSE),

(13, '1954', FALSE),
(13, '1960', FALSE),
(13, '1964', TRUE),
(13, '1968', FALSE),

(14, 'Vietnam', FALSE),
(14, 'Cuba', TRUE),
(14, 'Afghanistan', FALSE),
(14, 'Nicaragua', FALSE),

(15, 'Operation Iraqi Freedom', FALSE),
(15, 'Operation Desert Storm', TRUE),
(15, 'Operation Enduring Freedom', FALSE),
(15, 'Operation Desert Shield', FALSE),

(16, 'Farm Aid', FALSE),
(16, 'Us Festival', FALSE),
(16, 'Live Aid', TRUE),
(16, 'Band Aid', FALSE),

(17, 'Ronald Reagan', FALSE),
(17, 'George H.W. Bush', FALSE),
(17, 'Bill Clinton', TRUE),
(17, 'Jimmy Carter', FALSE),

(18, 'Pentagon Papers', TRUE),
(18, 'Watergate', FALSE),
(18, 'Iran-Contra Affair', FALSE),
(18, 'Whitewater', FALSE),

(19, 'Richard Nixon', FALSE),
(19, 'John F. Kennedy', FALSE),
(19, 'Lyndon B. Johnson', TRUE),
(19, 'Dwight D. Eisenhower', FALSE),

(20, 'Tibet', FALSE),
(20, 'Macau', FALSE),
(20, 'Hong Kong', TRUE),
(20, 'Singapore', FALSE);


-- Cars Category
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(21, 'Dodge Challenger', FALSE),
(21, 'Chevrolet Camaro', FALSE),
(21, 'Plymouth Barracuda', FALSE),
(21, 'Ford Mustang', TRUE),

(22, 'Carbon fiber', FALSE),
(22, 'Aluminum', TRUE),
(22, 'Titanium', FALSE),
(22, 'Steel', FALSE),

(23, 'Ford', FALSE),
(23, 'Chevrolet', FALSE),
(23, 'Chrysler', TRUE),
(23, 'GMC', FALSE),

(24, 'Bentley', FALSE),
(24, 'Rolls-Royce', TRUE),
(24, 'Aston Martin', FALSE),
(24, 'Land Rover', FALSE),

(25, 'Drag Racing', FALSE),
(25, 'Circuit Racing', FALSE),
(25, 'Rally Racing', TRUE),
(25, 'Drift Racing', FALSE),

(26, 'Ferrari', FALSE),
(26, 'Lamborghini', TRUE),
(26, 'Maserati', FALSE),
(26, 'Fiat', FALSE),

(27, 'Acura', FALSE),
(27, 'Lexus', TRUE),
(27, 'Infiniti', FALSE),
(27, 'Mazda', FALSE),

(28, 'Mercedes-Benz', FALSE),
(28, 'Audi', FALSE),
(28, 'Porsche', TRUE),
(28, 'BMW', FALSE),

(29, 'Saab', FALSE),
(29, 'Volvo', TRUE),
(29, 'Scania', FALSE),
(29, 'Koenigsegg', FALSE),

(30, 'Accord', FALSE),
(30, 'Prelude', FALSE),
(30, 'Insight', FALSE),
(30, 'Civic', TRUE);


-- Music Category
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(31, 'The Rolling Stones', FALSE),
(31, 'The Who', FALSE),
(31, 'Led Zeppelin', FALSE),
(31, 'The Beatles', TRUE),

(32, 'Pearl Jam', FALSE),
(32, 'Red Hot Chili Peppers', FALSE),
(32, 'Nirvana', TRUE),
(32, 'Radiohead', FALSE),

(33, 'Prince', FALSE),
(33, 'Michael Jackson', TRUE),
(33, 'Lionel Richie', FALSE),
(33, 'George Michael', FALSE),

(34, 'Bee Gees', TRUE),
(34, 'ABBA', FALSE),
(34, 'The Jackson 5', FALSE),
(34, 'Kool & The Gang', FALSE),

(35, 'David Bowie', FALSE),
(35, 'Stevie Wonder', FALSE),
(35, 'Prince', TRUE),
(35, 'Bruce Springsteen', FALSE),

(36, 'The Rolling Stones', FALSE),
(36, 'Queen', TRUE),
(36, 'Pink Floyd', FALSE),
(36, 'Aerosmith', FALSE),

(37, 'The Eagles', TRUE),
(37, 'Fleetwood Mac', FALSE),
(37, 'The Doors', FALSE),
(37, 'Lynyrd Skynyrd', FALSE),

(38, 'Jim Morrison', FALSE),
(38, 'Eric Clapton', FALSE),
(38, 'Jimi Hendrix', TRUE),
(38, 'Bob Dylan', FALSE),

(39, 'Peter Tosh', FALSE),
(39, 'Jimmy Cliff', FALSE),
(39, 'Bob Marley', TRUE),
(39, 'Toots Hibbert', FALSE),

(40, 'Led Zeppelin', FALSE),
(40, 'The Who', FALSE),
(40, 'Pink Floyd', TRUE),
(40, 'Deep Purple', FALSE);


-- Fashion Category
INSERT INTO Options (question_id, option_text, is_correct) VALUES
(41, 'Punk', FALSE),
(41, 'Futurism', TRUE),
(41, 'New Romantic', FALSE),
(41, 'Boho chic', FALSE),

(42, 'Cameo necklaces', FALSE),
(42, 'Love beads', FALSE),
(42, 'Puka shell necklaces', FALSE),
(42, 'Candy jewelry', TRUE),

(43, 'Vivienne Westwood', FALSE),
(43, 'Mary Quant', TRUE),
(43, 'Stella McCartney', FALSE),
(43, 'Victoria Beckham', FALSE),

(44, 'Bangles', FALSE),
(44, 'Jelly bracelets', TRUE),
(44, 'Chokers', FALSE),
(44, 'Loom bands', FALSE),

(45, 'Stilettos', FALSE),
(45, 'Ankle boots', FALSE),
(45, 'Ballet flats', FALSE),
(45, 'Slouch boots', TRUE),

(46, 'Gucci', FALSE),
(46, 'Chanel', TRUE),
(46, 'Prada', FALSE),
(46, 'Fendi', FALSE),

(47, 'Aviator glasses', FALSE),
(47, 'Cat-eye glasses', FALSE),
(47, 'John Lennon glasses', TRUE),
(47, 'Wayfarer glasses', FALSE),

(48, 'Feather filling', FALSE),
(48, 'Epaulettes', TRUE),
(48, 'Fringe on the back', FALSE),
(48, 'Fur-lined hoods', FALSE),

(49, 'Chokers', TRUE),
(49, 'Lariats', FALSE),
(49, 'Bolo ties', FALSE),
(49, 'Medallions', FALSE),

(50, 'Bell-bottoms', TRUE),
(50, 'Skinny jeans', FALSE),
(50, 'Capris', FALSE),
(50, 'Jeggings', FALSE);












