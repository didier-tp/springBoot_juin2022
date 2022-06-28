profiles:
   "embeddedDb" pour base de données embarquée (ex: H2) pratique pour les test
   "remoteDb" pour base distante gérée via un vrai serveur (Mysql/mariaDB ou postgres ou ...)
   "init" pour initialiser un jeu de données au démarrage (en dev)
   "withSecurity" (JWT local web security) or "!withSecurity" 