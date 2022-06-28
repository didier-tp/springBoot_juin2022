package tp.appliSpring.delegate;

import java.util.List;

import tp.appliSpring.entity.Devise;

public interface RefreshDevise {
    public List<Devise> retreiveRecentDeviseValues();
    public List<Devise> refreshDeviseValuesInDataBase();
}
