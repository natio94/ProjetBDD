USE Art_Connect;

-- ============================================================
-- ARTISTES (10 artistes de styles et villes variés)
-- ============================================================
INSERT INTO Artiste VALUES
(1, 'Peintre expressionniste autodidacte, passionné par les couleurs vives et les émotions brutes.', '1985-03-12', 'lucas.moreau@mail.com', '0612345678', 'Paris', 'www.lucasmoreau.fr', 'instagram.com/lucas_moreau', TRUE, 'Lucas Moreau'),
(2, 'Sculptrice contemporaine travaillant principalement le marbre et le bronze.', '1979-07-24', 'amina.diallo@mail.com', '0623456789', 'Lyon', 'www.aminadiallo.com', 'instagram.com/amina_sculpt', TRUE, 'Amina Diallo'),
(3, 'Photographe documentaire spécialisé dans les portraits urbains en noir et blanc.', '1990-11-05', 'pierre.laurent@mail.com', '0634567890', 'Marseille', 'www.pierrelaurent.photo', 'instagram.com/pierre_lens', TRUE, 'Pierre Laurent'),
(4, 'Artiste numérique et illustratrice, mêlant folklore et futurisme.', '1995-01-18', 'sara.kim@mail.com', '0645678901', 'Bordeaux', 'www.sarakim.art', 'instagram.com/sara_kim_art', TRUE, 'Sara Kim'),
(5, 'Céramiste et artiste textile, influencée par les arts africains traditionnels.', '1982-09-30', 'fatou.ndiaye@mail.com', '0656789012', 'Nantes', 'www.fatoundiaye.fr', 'instagram.com/fatou_ceramic', TRUE, 'Fatou Ndiaye'),
(6, 'Peintre hyperréaliste reconnu pour ses natures mortes d\'une précision troublante.', '1975-04-22', 'olivier.blanc@mail.com', '0667890123', 'Paris', 'www.olivierblanc.com', 'instagram.com/olivier_hyper', TRUE, 'Olivier Blanc'),
(7, 'Graffeur et muraliste, reconverti en artiste de galerie tout en gardant ses racines urbaines.', '1988-06-14', 'yann.lebris@mail.com', '0678901234', 'Lille', 'www.yannlebris.fr', 'instagram.com/yann_walls', TRUE, 'Yann Lebris'),
(8, 'Artiste pluridisciplinaire travaillant sur les thèmes de mémoire et d\'identité.', '1970-12-03', 'claire.dupont@mail.com', '0689012345', 'Strasbourg', 'www.clairedupont.eu', 'instagram.com/claire_art', FALSE, 'Claire Dupont'),
(9, 'Jeune graveur et illustrateur, amoureux des techniques anciennes revisitées.', '1998-08-27', 'tom.garcia@mail.com', '0690123456', 'Toulouse', 'www.tomgarcia.art', 'instagram.com/tom_gravure', TRUE, 'Tom Garcia'),
(10, 'Aquarelliste et peintre de paysages, inspirée par la nature bretonne.', '1983-02-15', 'marie.leclerc@mail.com', '0601234567', 'Rennes', 'www.marieleclerc.fr', 'instagram.com/marie_aqua', TRUE, 'Marie Leclerc');

-- ============================================================
-- DISCIPLINES (8 disciplines)
-- ============================================================
INSERT INTO Discipline VALUES
(1, 'Peinture'),
(2, 'Sculpture'),
(3, 'Photographie'),
(4, 'Art numérique'),
(5, 'Céramique'),
(6, 'Textile'),
(7, 'Street Art'),
(8, 'Gravure');

-- ============================================================
-- ARTISTE_DISCIPLINE (participations croisées : certains artistes ont plusieurs disciplines)
-- ============================================================
INSERT INTO Artiste_Discipline VALUES
(1, 1),  -- Lucas : Peinture
(1, 7),  -- Lucas : Street Art (polyvalent)
(2, 2),  -- Amina : Sculpture
(2, 5),  -- Amina : Céramique (polyvalente)
(3, 3),  -- Pierre : Photographie
(4, 4),  -- Sara : Art numérique
(4, 1),  -- Sara : Peinture (polyvalente)
(5, 5),  -- Fatou : Céramique
(5, 6),  -- Fatou : Textile
(6, 1),  -- Olivier : Peinture
(7, 7),  -- Yann : Street Art
(7, 1),  -- Yann : Peinture (polyvalent)
(8, 1),  -- Claire : Peinture
(8, 6),  -- Claire : Textile
(9, 8),  -- Tom : Gravure
(9, 4),  -- Tom : Art numérique
(10, 1); -- Marie : Peinture

-- ============================================================
-- ARTWORKS (20 œuvres réparties entre les artistes)
-- ============================================================
INSERT INTO Artwork VALUES
(1,  'Les Âmes en feu',       2021, 'Peinture',      'Huile sur toile',    '120x150cm', 'Toile expressionniste aux teintes rouges et orangées évoquant la passion.',           3200.00, 'Available',  1),
(2,  'Cité Fantôme',          2022, 'Peinture',      'Acrylique sur bois', '80x100cm',  'Représentation onirique d\'une ville abandonnée.',                                    1800.00, 'Sold',       1),
(3,  'Equilibre I',           2020, 'Sculpture',     'Marbre blanc',       '40x20x20cm','Sculpture abstraite jouant avec les contrepoids et les vides.',                       8500.00, 'Available',  2),
(4,  'Mémoire de Pierre',     2023, 'Sculpture',     'Bronze',             '60x30x30cm','Visage fragmenté évoquant la persistance du souvenir.',                               12000.00,'Exhibition', 2),
(5,  'Regard Urbain #1',      2021, 'Photographie',  'Tirage argentique',  '50x70cm',   'Portrait en noir et blanc d\'un homme âgé dans le métro parisien.',                   650.00,  'Available',  3),
(6,  'Regard Urbain #2',      2021, 'Photographie',  'Tirage argentique',  '50x70cm',   'Jeune femme lisant sous la pluie, Paris 11e.',                                        650.00,  'Sold',       3),
(7,  'Neon Folklore',         2022, 'Art numérique', 'Impression UV',      '60x90cm',   'Personnages de contes traditionnels coréens revisités en cyberpunk.',                  2100.00, 'Available',  4),
(8,  'Data Bloom',            2023, 'Art numérique', 'Impression Fine Art','90x90cm',   'Visualisation artistique de données climatiques en forme de fleurs.',                  1500.00, 'Exhibition', 4),
(9,  'Bol des Origines',      2019, 'Céramique',     'Grès émaillé',       '15x15x10cm','Bol inspiré des poteries sénégalaises, aux motifs géométriques dorés.',               420.00,  'Sold',       5),
(10, 'Tissu de Vie',          2021, 'Textile',       'Coton et raphia',    '200x150cm', 'Tapisserie racontant un récit de migration à travers des symboles tissés.',           3800.00, 'Available',  5),
(11, 'La Pomme et l\'Ombre',  2022, 'Peinture',      'Huile sur toile',    '40x40cm',   'Nature morte hyperréaliste d\'une pomme avec un éclairage dramatique.',               4200.00, 'Available',  6),
(12, 'Verres et Lumières',    2023, 'Peinture',      'Huile sur toile',    '60x80cm',   'Composition de verres reflétant une fenêtre, rendu photographique.',                  5500.00, 'Sold',       6),
(13, 'Tag Mémoire',           2020, 'Street Art',    'Bombe aérosol',      '200x300cm', 'Fresque murale intérieure mêlant graffiti et peinture classique.',                    2800.00, 'Exhibition', 7),
(14, 'Révolte Douce',         2022, 'Peinture',      'Acrylique sur toile','100x100cm', 'Fusion entre esthétique urbaine et figuration classique.',                            3100.00, 'Available',  7),
(15, 'Fil Rouge',             2018, 'Textile',       'Soie et lin',        '180x120cm', 'Installation textile explorant le fil comme métaphore du destin.',                    6200.00, 'Sold',       8),
(16, 'Palimpseste',           2019, 'Peinture',      'Technique mixte',    '150x200cm', 'Couches superposées de peinture et de textes effacés évoquant la mémoire.',           7500.00, 'Exhibition', 8),
(17, 'Matrice #3',            2023, 'Gravure',       'Eau-forte sur zinc',  '30x40cm',  'Gravure abstraite aux lignes incisives inspirées des circuits imprimés.',              380.00,  'Available',  9),
(18, 'Rive Gauche au Matin',  2022, 'Peinture',      'Aquarelle',          '30x50cm',   'Vue impressionniste des bords de Seine au lever du soleil.',                          920.00,  'Available',  10),
(19, 'Forêt de Brocéliande',  2023, 'Peinture',      'Aquarelle',          '50x70cm',   'Sous-bois mystique baigné de lumière filtrée, ambiance celtique.',                    1100.00, 'Available',  10),
(20, 'Série Numérique Alpha', 2023, 'Art numérique', 'NFT / Impression',   '70x70cm',   'Première œuvre NFT de Tom Garcia, mêlant gravure scannée et traitement numérique.',   890.00,  'Available',  9);

-- ============================================================
-- ARTISTE_ARTWORK (co-créations et collaborations croisées)
-- ============================================================
INSERT INTO Artiste_Artwork VALUES
(1, 1), (1, 2),          -- Lucas seul
(2, 3), (2, 4),          -- Amina seule
(3, 5), (3, 6),          -- Pierre seul
(4, 7), (4, 8),          -- Sara seule
(5, 9), (5, 10),         -- Fatou seule
(6, 11), (6, 12),        -- Olivier seul
(7, 13), (7, 14),        -- Yann seul
(8, 15), (8, 16),        -- Claire seule
(9, 17), (9, 20),        -- Tom seul
(10, 18), (10, 19),      -- Marie seule
-- Collaborations croisées
(1, 13),                 -- Lucas collabore sur la fresque de Yann
(4, 20),                 -- Sara collabore sur l'œuvre numérique de Tom
(7, 2),                  -- Yann collabore sur une toile de Lucas
(2, 16);                 -- Amina collabore sur l'installation de Claire

-- ============================================================
-- ARTWORK TAGS (12 tags)
-- ============================================================
INSERT INTO ArtworkTag VALUES
(1,  'Abstrait'),
(2,  'Figuratif'),
(3,  'Urbain'),
(4,  'Nature'),
(5,  'Portrait'),
(6,  'Noir & Blanc'),
(7,  'Coloré'),
(8,  'Minimaliste'),
(9,  'Engagé'),
(10, 'Traditionnel'),
(11, 'Numérique'),
(12, 'Hyperréaliste');

-- ============================================================
-- ARTWORK_ARTWORKTAG (chaque œuvre a 1 à 3 tags)
-- ============================================================
INSERT INTO Artwork_ArtworkTag VALUES
(1, 7), (1, 9),           -- Les Âmes en feu : Coloré, Engagé
(2, 3), (2, 1),           -- Cité Fantôme : Urbain, Abstrait
(3, 8), (3, 1),           -- Equilibre I : Minimaliste, Abstrait
(4, 2), (4, 5),           -- Mémoire de Pierre : Figuratif, Portrait
(5, 5), (5, 6), (5, 3),  -- Regard Urbain #1 : Portrait, Noir & Blanc, Urbain
(6, 5), (6, 6),           -- Regard Urbain #2 : Portrait, Noir & Blanc
(7, 11),(7, 10),(7, 7),  -- Neon Folklore : Numérique, Traditionnel, Coloré
(8, 11),(8, 4), (8, 1),  -- Data Bloom : Numérique, Nature, Abstrait
(9, 10),(9, 8),           -- Bol des Origines : Traditionnel, Minimaliste
(10, 10),(10, 9),         -- Tissu de Vie : Traditionnel, Engagé
(11, 12),(11, 4),         -- La Pomme et l'Ombre : Hyperréaliste, Nature
(12, 12),(12, 8),         -- Verres et Lumières : Hyperréaliste, Minimaliste
(13, 3), (13, 7), (13, 9),-- Tag Mémoire : Urbain, Coloré, Engagé
(14, 3), (14, 2),         -- Révolte Douce : Urbain, Figuratif
(15, 9), (15, 1),         -- Fil Rouge : Engagé, Abstrait
(16, 1), (16, 9),         -- Palimpseste : Abstrait, Engagé
(17, 1), (17, 11),        -- Matrice #3 : Abstrait, Numérique
(18, 4), (18, 2),         -- Rive Gauche au Matin : Nature, Figuratif
(19, 4), (19, 2),         -- Forêt de Brocéliande : Nature, Figuratif
(20, 11),(20, 1);         -- Série Numérique Alpha : Numérique, Abstrait

-- -- ============================================================
-- -- GALERIES (5 galeries)
-- -- ============================================================
INSERT INTO Gallery VALUES
(1, 'Galerie du Marais',       '12 Rue de Bretagne, 75003 Paris',        'Sophie Arnaud',   'Mar-Sam 11h-19h',      '0142123456', 4.70, 'www.galeriemarais.fr'),
(2, 'Espace Lumière',          '8 Quai de la Pêcherie, 69001 Lyon',      'Henri Morel',     'Mer-Dim 10h-18h',      '0472234567', 4.30, 'www.espacelumiere.fr'),
(3, 'Galerie Urbaine',         '45 Rue de la République, 59000 Lille',   'Karim Benali',    'Jeu-Dim 14h-20h',      '0320345678', 4.50, 'www.galerie-urbaine.fr'),
(4, 'Atelier des Arts',        '3 Place Graslin, 44000 Nantes',          'Isabelle Renard', 'Mar-Sam 10h-19h',      '0240456789', 4.10, 'www.atelierarts-nantes.fr'),
(5, 'Maison de la Photographie','22 Rue Paradis, 13006 Marseille',       'Luc Bernard',     'Mar-Dim 11h-18h30',    '0491567890', 4.80, 'www.maisonphoto-marseille.fr');

-- ============================================================
-- EXHIBITIONS (6 expositions réparties dans les galeries)
-- ============================================================
INSERT INTO Exhibition VALUES
(1, 'Émotions Brutes',       'Sophie Arnaud',   'Expressionnisme',  '2023-03-01 10:00:00', '2023-04-30 20:00:00', 1),
(2, 'Ville et Mémoire',      'Henri Morel',     'Art Urbain',       '2023-05-15 10:00:00', '2023-07-15 20:00:00', 2),
(3, 'Regards Croisés',       'Karim Benali',    'Photographie',     '2023-09-01 10:00:00', '2023-10-31 20:00:00', 3),
(4, 'Futur Ancestral',       'Isabelle Renard', 'Art Numérique',    '2024-01-10 10:00:00', '2024-03-10 20:00:00', 4),
(5, 'Matières et Mémoires',  'Luc Bernard',     'Art Mixte',        '2024-04-05 10:00:00', '2024-06-05 20:00:00', 5),
(6, 'Hyperréel & Abstrait',  'Sophie Arnaud',   'Peinture',         '2024-09-01 10:00:00', '2024-11-30 20:00:00', 1);

-- ============================================================
-- EXHIBITION_ARTWORK (œuvres exposées dans plusieurs expos — cas croisés)
-- ============================================================
INSERT INTO Exhibition_Artwork VALUES
-- Expo 1 : Émotions Brutes (Paris) — Lucas, Yann, Claire
(1,  1), (2,  1), (13, 1), (16, 1),
-- Expo 2 : Ville et Mémoire (Lyon) — Lucas, Yann, Pierre
(2,  2), (13, 2), (14, 2), (5,  2),
-- Expo 3 : Regards Croisés (Lille) — Pierre, Sara, Tom
(5,  3), (6,  3), (8,  3), (17, 3),
-- Expo 4 : Futur Ancestral (Nantes) — Sara, Tom
(7,  4), (8,  4), (20, 4), (17, 4),
-- Expo 5 : Matières et Mémoires (Marseille) — Amina, Fatou, Claire
(3,  5), (4,  5), (10, 5), (15, 5), (16, 5),
-- Expo 6 : Hyperréel & Abstrait (Paris) — Olivier, Lucas, Yann
(11, 6), (12, 6), (1,  6), (14, 6),
-- Œuvres exposées dans plusieurs expos (cas croisés intéressants)
(8,  1),  -- Data Bloom aussi dans Émotions Brutes
(5,  5);  -- Regard Urbain #1 aussi dans Matières et Mémoires

-- ============================================================
-- COMMUNITY MEMBERS (12 membres)
-- ============================================================
INSERT INTO CommunityMember VALUES
(1,  'Julie Martin',    'julie.martin@mail.com',    '0611111111', 'Paris',      'Premium',  '1990-05-20'),
(2,  'Marc Lefevre',    'marc.lefevre@mail.com',    '0622222222', 'Lyon',       'Standard', '1985-08-14'),
(3,  'Nadia Cheikh',    'nadia.cheikh@mail.com',    '0633333333', 'Marseille',  'Premium',  '1993-02-28'),
(4,  'Thomas Petit',    'thomas.petit@mail.com',    '0644444444', 'Paris',      'Standard', '1978-11-03'),
(5,  'Emma Rousseau',   'emma.rousseau@mail.com',   '0655555555', 'Nantes',     'Premium',  '1995-07-17'),
(6,  'Hugo Bernard',    'hugo.bernard@mail.com',    '0666666666', 'Bordeaux',   'Standard', '1988-04-09'),
(7,  'Camille Girard',  'camille.girard@mail.com',  '0677777777', 'Lille',      'Premium',  '1992-12-25'),
(8,  'Antoine Roux',    'antoine.roux@mail.com',    '0688888888', 'Strasbourg', 'Standard', '1980-06-30'),
(9,  'Léa Simon',       'lea.simon@mail.com',       '0699999999', 'Toulouse',   'Premium',  '1997-03-11'),
(10, 'Paul Moreau',     'paul.moreau@mail.com',     '0610101010', 'Rennes',     'Standard', '1975-09-22'),
(11, 'Sophie Lambert',  'sophie.lambert@mail.com',  '0621212121', 'Paris',      'Premium',  '1991-01-05'),
(12, 'Romain Dupuis',   'romain.dupuis@mail.com',   '0632323232', 'Lyon',       'Standard', '1983-10-18');

-- ============================================================
-- WORKSHOPS (8 ateliers animés par différents artistes)
-- ============================================================
INSERT INTO Workshop VALUES
(1,  'Initiation à la peinture expressionniste', '2024-02-10 10:00:00', 180, 10, 60.00,  'Paris 11e',           'Découvrez les bases de l\'expressionnisme avec Lucas Moreau.',          'Débutant',       1),
(2,  'Sculpture sur argile',                     '2024-03-15 14:00:00', 240, 8,  90.00,  'Lyon, Atelier Diallo','Modelage et initiation à la sculpture avec Amina Diallo.',               'Débutant',       2),
(3,  'Photographie de rue',                      '2024-04-20 09:00:00', 300, 12, 75.00,  'Marseille Centre',    'Sortie photo en ville avec Pierre Laurent pour capturer l\'instant.',    'Intermédiaire',  3),
(4,  'Illustration numérique',                   '2024-05-11 10:00:00', 180, 15, 55.00,  'Bordeaux Numérique',  'Créer un personnage illustré avec tablette graphique, avec Sara Kim.',   'Débutant',       4),
(5,  'Initiation à la céramique',                '2024-06-08 14:00:00', 210, 8,  80.00,  'Nantes, Atelier Ndiaye','Tournage et émaillage avec Fatou Ndiaye.',                            'Débutant',       5),
(6,  'Techniques de l\'hyperréalisme',           '2024-07-13 10:00:00', 360, 6,  150.00, 'Paris 6e',            'Stage intensif sur la peinture hyperréaliste avec Olivier Blanc.',       'Avancé',         6),
(7,  'Street Art en atelier',                    '2024-08-24 10:00:00', 240, 10, 70.00,  'Lille, Friche',       'Initiation aux bombes et gabarits avec Yann Lebris.',                    'Débutant',       7),
(8,  'Gravure traditionnelle',                   '2024-09-14 10:00:00', 300, 6,  110.00, 'Toulouse, Atelier',   'Eau-forte et pointe sèche avec Tom Garcia.',                             'Intermédiaire',  9);

-- ============================================================
-- BOOKINGS (participations croisées membres / ateliers)
-- ============================================================
INSERT INTO Booking VALUES
(1,  '2024-01-20 10:00:00', 'Paid',      1, 1),   -- Julie → Peinture expressionniste
(2,  '2024-01-21 11:00:00', 'Paid',      2, 1),   -- Julie → Sculpture sur argile
(3,  '2024-01-22 09:00:00', 'Paid',      4, 1),   -- Julie → Illustration numérique
(4,  '2024-02-01 14:00:00', 'Paid',      2, 2),   -- Marc → Sculpture sur argile
(5,  '2024-02-03 10:00:00', 'Pending',   5, 2),   -- Marc → Céramique
(6,  '2024-02-10 09:00:00', 'Paid',      3, 3),   -- Nadia → Photographie de rue
(7,  '2024-02-11 11:00:00', 'Paid',      6, 3),   -- Nadia → Hyperréalisme
(8,  '2024-02-15 10:00:00', 'Paid',      1, 4),   -- Thomas → Peinture expressionniste
(9,  '2024-02-16 14:00:00', 'Cancelled', 7, 4),   -- Thomas → Street Art (annulé)
(10, '2024-03-01 10:00:00', 'Paid',      5, 5),   -- Emma → Céramique
(11, '2024-03-02 11:00:00', 'Paid',      4, 5),   -- Emma → Illustration numérique
(12, '2024-03-05 09:00:00', 'Paid',      8, 5),   -- Emma → Gravure traditionnelle
(13, '2024-03-10 14:00:00', 'Paid',      7, 6),   -- Hugo → Street Art
(14, '2024-03-11 10:00:00', 'Pending',   3, 6),   -- Hugo → Photographie de rue
(15, '2024-03-15 10:00:00', 'Paid',      7, 7),   -- Camille → Street Art
(16, '2024-03-16 11:00:00', 'Paid',      1, 7),   -- Camille → Peinture expressionniste
(17, '2024-03-20 09:00:00', 'Paid',      6, 8),   -- Antoine → Hyperréalisme
(18, '2024-03-21 14:00:00', 'Paid',      8, 8),   -- Antoine → Gravure traditionnelle
(19, '2024-04-01 10:00:00', 'Paid',      4, 9),   -- Léa → Illustration numérique
(20, '2024-04-02 11:00:00', 'Cancelled', 8, 9),   -- Léa → Gravure (annulé)
(21, '2024-04-05 09:00:00', 'Paid',      3, 10),  -- Paul → Photographie de rue
(22, '2024-04-06 14:00:00', 'Paid',      5, 10),  -- Paul → Céramique
(23, '2024-04-10 10:00:00', 'Paid',      6, 11),  -- Sophie → Hyperréalisme
(24, '2024-04-11 11:00:00', 'Paid',      2, 11),  -- Sophie → Sculpture sur argile
(25, '2024-04-15 09:00:00', 'Paid',      8, 12),  -- Romain → Gravure traditionnelle
(26, '2024-04-16 14:00:00', 'Paid',      1, 12);  -- Romain → Peinture expressionniste

-- ============================================================
-- REVIEWS (avis croisés membres / œuvres, notes variées)
-- ============================================================
INSERT INTO Review VALUES
(1,  'Une œuvre qui transperce l\'âme, les couleurs sont d\'une intensité rare.',         5, '2023-04-10 15:00:00', 1,  1),
(2,  'Travail remarquable, on sent l\'influence des grands expressionnistes.',             4, '2023-04-15 11:00:00', 2,  1),
(3,  'La sculpture dégage une présence incroyable, le marbre semble vivant.',             5, '2023-06-20 14:00:00', 3,  3),
(4,  'Œuvre puissante mais un peu froide à mon goût.',                                    3, '2023-07-01 10:00:00', 4,  4),
(5,  'Portrait saisissant, le sujet semble nous regarder droit dans les yeux.',           5, '2023-10-05 16:00:00', 5,  5),
(6,  'Beau travail de lumière, bien que le cadrage soit un peu serré.',                   4, '2023-10-12 13:00:00', 6,  5),
(7,  'Concept original et exécution parfaite, j\'adore le mélange des cultures.',         5, '2024-02-14 18:00:00', 7,  7),
(8,  'Très beau visuellement mais le propos reste un peu obscur.',                        3, '2024-02-20 10:00:00', 8,  8),
(9,  'Un bol d\'une délicatesse absolue, le savoir-faire est impressionnant.',            5, '2024-01-08 12:00:00', 9,  9),
(10, 'La tapisserie raconte une histoire poignante, le travail est d\'une grande qualité.',5,'2024-01-15 14:00:00', 10, 10),
(11, 'On croirait voir une vraie pomme ! Le talent de cet artiste est bluffant.',         5, '2024-03-05 11:00:00', 11, 11),
(12, 'Impressionnant de réalisme, mais je préfère les œuvres plus évocatrices.',          4, '2024-03-10 15:00:00', 12, 11),
(13, 'La fresque est monumentale et pleine d\'énergie, une vraie claque visuelle.',       5, '2023-04-25 17:00:00', 7,  13),
(14, 'Bonne technique mais le message politique est un peu trop appuyé pour moi.',        3, '2023-05-02 10:00:00', 8,  13),
(15, 'Œuvre émouvante qui interroge sur nos racines et notre identité.',                  4, '2024-05-10 13:00:00', 1,  15),
(16, 'Les couches de peinture créent une profondeur hypnotique.',                         5, '2024-05-15 16:00:00', 3,  16),
(17, 'Une gravure d\'une précision technique admirable.',                                 4, '2023-11-20 14:00:00', 9,  17),
(18, 'L\'aquarelle saisit parfaitement la douceur d\'un matin parisien.',                 5, '2024-02-28 10:00:00', 10, 18),
(19, 'Travail délicat et poétique, on ressent la sérénité de la forêt.',                 4, '2024-03-15 12:00:00', 11, 19),
(20, 'Fascinant de voir la gravure traditionnelle sublimée par le numérique.',            5, '2024-04-20 15:00:00', 9,  20);