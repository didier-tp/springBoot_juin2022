package tp.appliSpring.dao;

import org.springframework.data.repository.CrudRepository;
import tp.appliSpring.entity.Client;

public interface DaoClient extends CrudRepository<Client,Integer> {
}
