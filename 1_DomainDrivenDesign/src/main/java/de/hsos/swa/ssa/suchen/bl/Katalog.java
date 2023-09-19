package de.hsos.swa.ssa.suchen.bl;

import java.util.List;

public interface Katalog {
    public void legeSuchalgorithmusFest(SuchAlgorithmus algo);

    public List<Ware> suchen(String warenname);

    public Ware suchen(long warennummer);

    public Produktinformation gebeProduktinformation(Ware ware);
}
