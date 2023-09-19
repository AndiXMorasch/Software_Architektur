package de.hsos.swa.ssa.suchen.al;

import java.util.List;

import de.hsos.swa.ssa.suchen.bl.Ware;

public interface SucheWare {
    public List<Ware> sucheWare(String warenname);

    public Ware sucheWare(long warennummer);
}
