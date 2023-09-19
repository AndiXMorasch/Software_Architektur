package de.hsos.swa.ssa.suchen.dal;

import java.util.List;

import de.hsos.swa.ssa.suchen.bl.Ware;

public interface Warensuche {

    public List<Ware> sucheWare(String suchbegriff);
}
