Fabien CHEBEL Delphine LAUNAY


------------------------------------------------------------------
CORRECTION+CODE REVIEW:
------------------------------------------------------------------

-------------------------
Expérience utilisateur:
-------------------------
Quand je lance le projet, je tombe sur une page de login: bonne surprise, j'aime bien! Mais c'est quoi les login?

>Le README.MD est fait pour ca: c'est là qu'on trouve les infos qui vont m'aider à setter mon environnement.

Je trouve le script de la table dans 1.sql > Bien

J'ai du insérer un user (insérer une entrée dans 2.sql avec un user admin admin aurait été cool, ca m'aurait éviter de le faire tout seul, mais c'est un détail)

Ensuite: JDBC exception: access denied for root@localhost > Vous n'avez pas créé d'utilisateur admin admin dans la base de données, j'ai du modifier votre context.xml, attention la prochaine fois.

Allez, ca y est on arrive sur la page:
look stylé, vous avez bien bossé l'interface et c'est facile, on comprend vite. Dommage pour l'affichage de la date: j'aurais aimé avoir un formatage un peu plus parlant au format JJ-MM-AAAA ou autre... sans les secondes quoi!

Delete computer: les checkbox c'est super! ca marche, mais si j'ai cliqué par inadvertence sur le bouton je peux supprimer pas mal de choses... une confirmation de suppression aurait été cool

Orderby: ca marche aussi, mais si je veux un ordinateur "ZZ", recliquer sur "Computer Name" devrait pouvoir afficher l'ordre décroissant, ca aurait été sympa d'implémenter ça.

Recherche: ca marche, mais j'aurais aimé garder le texte de ma recherche dans le champ, au lieu de remettre le placeholder. Idem, quand je lance une recherche, je perds mon paramètre d'orderby. Ca aurait été cool de le garder.

Ajout d'ordinateur: belle implémentation des erreurs, bon controle global. L'ajout de company: bel effort!

Edit d'ordinateur:
Tiens tiens, les dates ne sont pas préremplies quand j'édite un ordinateur. Ca aurait été ok si ca n'overridais pas les changements quand je valide (ca remplace l'ancienne date par la date de maintenant) dommage là dessus, vous êtes allés un peu vite.


-------------------------
Le code:
-------------------------
-Commentaires: Bien!

-Protection des jsp: bien

-Utilisation des builder: oui

-Utilisation des enums pour les singletons: yes! mais attention aux oublis!


-Controllers: Attention! AddCompanyController/EditComputerController/RemoveComputerController: vous instanciez a chaque fois un service (ex: companyService = new CompanyServiceImpl();) violant ainsi le principe du singleton. Une bonne implementation serait ServiceManager.INSTANCE.getCompanyService(); par exemple. Vous avez fait cela correctement sur les autres classes... Potentiellement, cela peut vous poser des problemes de performances, et de fuites memoires.
EditComputerController: l122, un test de nullite non attendu devrait donner lieu a un log!
JSON: Bien!
ComputerController: On commence a avoir beaucoup d'attributs. Dans ce cas la, on creerait un objet wrapper (du style PageDescriptor) ca vous permettrait d'encapsuler tout ca dans un seul attribut.


-Services: RAS

-Dao: ComputerDao: Pourquoi utiliser un stringbuffer plutot qu'un stringbuilder? Le stringbuffer est threadsafe, ce qui n'est absolument pas un probleme ici vu qu'on n'a aucune problematique d'acces concurrent. Le stringbuilder sera donc un meilleur choix de perfs.

ComputerDao: Pas mal d'avoir tente le criteria!

Eresult: utiliser des entiers pour decrire une erreur, pourquoi pas... Mais c'est un peu demode! Cherches plutot du cote d'un array de flags, ou d'enums. PS: pour decrire tes nombres, tu aurais pu utiliser l'annotation binaire 0b00000001 par exemple (SDK 6 ou 7)

Le SearchWrapper avec des Generics, j'aime! C'est un bel exemple meme si il n'est pas extremement utile dans notre cas.

org.json: oulala! C'est le bordel. La prochaine fois, rajoutez juste le jar! Pourquoi recopier tous les sources?



-Domain: RAS

-JSP: Joli pour la taglib, mais n'oublie pas que c'est cense faciliter l'acces a une personne qui ne fait pas de dev. Les ${epf:generateGetParams(currentPage +1, searchQuery, sortCriterion, sortOrder)} ne sont pas vraiment parlants et auraient merite une taglib et une declaration par balises.

Les scripts inpage: bonne pratique: les mettre dans un fichier js separe.

-------------------------
Bilan: 
-------------------------
Excellent projet, de maniere globale, votre code est tres propre. Mais: vous avez fait quelques erreurs de debutants (instantiation de plusieurs services, import de org.json... 

