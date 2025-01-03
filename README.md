# TP3 AC - Reduced Ordered Binary Decision Diagram 

## Description
Ce TP implémente la manipulation et la construction de ROBDD, ainsi qu’une application pour résoudre le problème des N reines.

**⚠️ Les classes et méthodes pertinentes ont été soigneusement documentées avec JavaDoc.** 

Cette documentation explique en détail leur rôle, leurs paramètres, et leur fonctionnement.

## Accéder à la documentation
La documentation JavaDoc générée est disponible dans le dossier `docs`. Pour y accéder :

1. Ouvrez le fichier `docs/index.html` dans un navigateur Web.
2. Naviguez dans les différentes sections pour explorer les packages et les classes.

## Contenu de la JavaDoc
- Les **méthodes principales** incluent une description de leur utilité, de leurs paramètres, et des retours attendus.
- Les classes essentielles (`Main`, `ROBDD`, `Expression`, `Atome`, `Noeud_ROBDD`, `Equiv`) sont accompagnées d’explications détaillées sur leur rôle, leur fonctionnement, et leurs interactions.

## Navigation recommandée
1. **Package `src`** :
   - Accédez à la classe `Main.java`, qui centralise l'exécution des exercices pour toutes les fonctionnalités du TP.
   - Chaque exercice est documenté pour indiquer son objectif et les méthodes utilisées.
   
2. **Package `robdd`** :
   - Découvrez la structure des ROBDD avec les classes `ROBDD` et `Noeud_ROBDD`, qui permettent la manipulation des arbres de décision réduits et ordonnés.

3. **Package `expression`** :
   - Explorez les classes `Expression`, `Atome`, et `Equiv` pour comprendre la manipulation des expressions booléennes, leur simplification, et leur évaluation.

**Note :** Les commentaires situés dans le code **ne sont pas directement lisibles dans les fichiers source** mais sont liés à la documentation JavaDoc.
**Nous recommandons d’utiliser la JavaDoc générée pour une compréhension optimale.**

## Générer la documentation JavaDoc
Pour générer ou régénérer la documentation JavaDoc, exécutez la commande suivante dans votre terminal :
``` bash
javadoc -private -d docs .\src\Main.java .\robdd\ROBDD.java .\expression\Expression.java .\robdd\Noeud_ROBDD.java .\expression\Atome.java .\expression\Equiv.java
```

## Auteur
Ce TP a été réalisé par **Eya Sammari** et **Mey Cherif**. Un effort particulier a été consacré à la rédaction de la documentation JavaDoc pour rendre chaque méthode compréhensible et utile dans le contexte des exercices.
