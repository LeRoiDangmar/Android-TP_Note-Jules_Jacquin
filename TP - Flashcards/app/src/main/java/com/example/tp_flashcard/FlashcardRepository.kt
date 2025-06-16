package com.example.tp_flashcard

import com.example.tp_flashcard.model.FlashCard
import com.example.tp_flashcard.model.FlashCardCategory

object FlashcardRepository {
    private val categories = listOf(
        FlashCardCategory("history",      "Histoire"),
        FlashCardCategory("science",      "Sciences"),
        FlashCardCategory("geography",    "Géographie"),
        FlashCardCategory("math",         "Mathématiques"),
        FlashCardCategory("languages",    "Langues")
    )

    private val flashcards = listOf(
        // Histoire
        FlashCard("h1", "history", "Qui était le premier empereur romain ?", "Auguste"),
        FlashCard("h2", "history", "En quelle année la Révolution française a-t-elle commencé ?", "1789"),
        FlashCard("h3", "history", "Quel mur est tombé en 1989 symbolisant la fin de la Guerre froide ?", "Le mur de Berlin"),
        FlashCard("h4", "history", "Qui a signé la Déclaration d'indépendance des États-Unis ?", "Thomas Jefferson"),
        FlashCard("h5", "history", "En quelle année l'homme a-t-il marché sur la Lune pour la première fois ?", "1969"),

        // Sciences
        FlashCard("s1", "science", "Quelle est la formule chimique de l'eau ?", "H₂O"),
        FlashCard("s2", "science", "Quelle planète est la troisième à partir du Soleil ?", "La Terre"),
        FlashCard("s3", "science", "Quelle est la vitesse de la lumière dans le vide (approx.) ?", "300 000 km/s"),
        FlashCard("s4", "science", "Comment appelle-t-on l'étude des os ?", "L'ostéologie"),
        FlashCard("s5", "science", "Quel gaz les plantes absorbent-elles pour la photosynthèse ?", "Le dioxyde de carbone"),

        // Géographie
        FlashCard("g1", "geography", "Quelle est la capitale de la France ?", "Paris"),
        FlashCard("g2", "geography", "Quel est le plus long fleuve du monde ?", "Le Nil"),
        FlashCard("g3", "geography", "Dans quel océan se trouve Madagascar ?", "Océan Indien"),
        FlashCard("g4", "geography", "Quel est le plus haut sommet du monde ?", "L'Everest"),
        FlashCard("g5", "geography", "Quel désert est le plus vaste ?", "Le Sahara"),

        // Mathématiques
        FlashCard("m1", "math", "Quelle est la valeur de π (arrondie à deux décimales) ?", "3,14"),
        FlashCard("m2", "math", "Combien de côtés a un hexagone ?", "6"),
        FlashCard("m3", "math", "Quel est le résultat de 12 × 8 ?", "96"),
        FlashCard("m4", "math", "Que vaut 2^10 ?", "1024"),
        FlashCard("m5", "math", "Comment appelle-t-on un triangle rectangle isocèle ?", "Un triangle isocèle droit"),

        // Langues
        FlashCard("l1", "languages", "Comment dit-on « bonjour » en espagnol ?", "Hola"),
        FlashCard("l2", "languages", "Quel est l’article défini féminin singulier en italien ?", "La"),
        FlashCard("l3", "languages", "Que signifie « Guten Tag » en allemand ?", "Bonjour"),
        FlashCard("l4", "languages", "Comment écrit-on « merci » en japonais (romaji) ?", "Arigato"),
        FlashCard("l5", "languages", "Quelle est la traduction anglaise de « chien » ?", "Dog")
    )

    suspend fun getCategories(): List<FlashCardCategory> = categories
    suspend fun getFlashcards(): List<FlashCard> = flashcards
}
