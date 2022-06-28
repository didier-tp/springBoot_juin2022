package tp.appliSpring.service;

import tp.appliSpring.entity.Client;

public interface ServiceClient {
     Client rechercherClientParId(long id);
     Client rechercherClientAvecComptesParId(long id);
     Client sauvegarderClient(Client cli);
     //...
}
