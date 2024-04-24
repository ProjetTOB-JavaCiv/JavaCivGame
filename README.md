# JavaCiv

Ce projet est un jeu inspiré du jeu "Civilisation". Il est implémenté en Java à l'aide de la librairie **LibGDX**. Il utilise la version 21 de Java.

## Build

Pour les différentes tâches de build, ce projet utilise **Gradle**.

### Build

Pour build le projet on peut utiliser la commande :

```bash
./gradlew build
```

### Run

Pour exécuter le projet on peut utiliser la commande :

```bash
./gradlew run
```

### Test

Pour lancer les tests unitaires sur le projet on peut utiliser la commande :

```bash
./gradlew test
```

### JavaDoc

Pour générer la documentation du projet dans le dossier `./doc` on peut utiliser la commande :

```bash
./gradlew doc
```

## Architecture

Ce projet est basé sur une architecture Client-Serveur et utilise les design patterns Proxy et ModelViewControler.