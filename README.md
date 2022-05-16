# crochet-website

Ce projet est un petit site web utilisant Spring Boot, jQuery, et hsqldb.

## Cahier des charges

- Application autonome avec Spring Boot
- Services REST (consommés depuis le js) respectant au maximum les normes
- Offrir du CRUD sur les ressources de la base de données
- Framework javascript (ici jQuery)
- Base de données composée de plusieurs entités, avec au moins une relation

## Site web

L'idée de ce projet est de concevoir le site web de Max, un artiste qui réalise des peluches et autres oeuvres en crochet. 

Il souhaite disposer d'un site qui puisse jouer le rôle de **vitrine** (profil, galerie de photos, liens vers des réseaux sociaux), mais qui doit également permettre à de potentiels clients de lui envoyer une demande de **commission** - c'est-à-dire une commande.

Pour cela, notre base de données va disposer de deux tables : 
- **Client**, dans laquelle on va enregistrer les clients potentiels, leur nom, leurs contacts, etc.
- **Commission**, dans laquelle on va enregistrer les demandes de commissions individuelles : type de commande, prix envisagé, etc.

On pourra définir une limite de demandes de commissions, au-delà de laquelle il ne sera plus possible d'en demander afin de ne pas submerger Max.

Chaque commission est liée à un unique client, mais un client peut avoir plusieurs commissions (en cours ou terminées).
