package interfaces;

import java.util.Collections;

public class Save {
    
    public Save() {
        super();
    }

    public boolean addGame(Puzzle partida) {
        Boolean sucessfull = false;
        sucessfull = super.add(partida);
        Collections.sort(this);
        if (super.indexOf(super.getLast()) >= 10)
            return sucessfull && !super.contains(super.removeLast());
        return sucessfull;
    }

}
