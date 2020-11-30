# Les Etapes de deploiement de Projet 7 client 

## Prémière étape:
* Dans cette étape, nous allons d'abord récupérer le projet sur mon depo distant github(**remote**) avec l'url suivant:
[soro2305](https://github.com/soro1987/Client-p7) <br>
* Ensuite on l'enregistre en local avec la commande suivante:<br>
<pre>
    <code>
        git clone https://github.com/soro1987/Client-p7
    </code>
</pre>

## Deuxième étape:
* Dans celle-ci, nous allons préparer notre war, pour cela nous devons configurer eclipse pour éviter des erreurs pandant
la création de notre war.
* on va dans l'onglet **Window/Preferences**
![Preferences](screen1.png)
* Si vous trouvez que  Eclipse utilise  JRE, vous devez supprimer  JRE et ajouter  JDK.
![Installed JREs](screen2.png)

* Lancer le ternminer et rendez vous à la racine du projet.

* Puis tapez la commande suivante **mvn clean install** pour créer le fichier .war

## Les technologies utilisées:
* Java  1.8
* Spring boot 2.2.4
* Hibernate
* MYSQL 5
* Thymeleaf
* Bootstrap 4
* Eclipse IDE
