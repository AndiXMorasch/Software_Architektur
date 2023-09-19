# Anmerkungen zu den Konzezpten

## UI-Layer

Im UI-Layer musste die Struktur komplett überarbeitet werden. Dafür wurde das Model-View-Controller in einer Variante implementiert. So sind die unterschiedlichen Views komplett von der Logik getrennt und sind nur für die Anzeige und Eingabe verantwortlich. Koordiniert werden diese von den Controllern, die auch mit der Applikations-Logik kommunizieren, die das Model repräsentiert.

Um zirkuläre Abhängigkeiten zwischen den Views und den Controllern, sowie unter den Controllern aufzubrechen wird das Observer-Pattern verwendet.

## AL-Layer

Die Applikations-Schicht bietet jeder View in der UI eine passende Schnittstelle. Jede View hat deshalb nur auf die Funktionen Zugriff, die sie benötigt. Zustäzlich wird das Delegationsprinzip verwendet und Services behandeln immer nur genau eine Funktion, wärend die EinkäuferIn zwischen den Services vermittelt. Die Services übernehmen die Applicationsspezifischen Entscheidungen für ihr Spezialgebiet und kommunizieren mit der Business-Logik oder dem Anti-Corruption-Layer.

## ACL

Der Anti-Corruption-Layer wurde stark verkleinert, weil die Suchkomponente keine Verwaltung des Warenkorbs durchführen soll. Die für die Suche benötigte Schnittstelle ist ledlich das Hinzufügen einer Ware zu dem aktuellen Warenkorb. Dafür wird ein DTO benötigt und das Interface, welches diese Funktion bereitstellt. Für die getrennte Ausführung wird ein MockAdapter erstellt, der die benötigte Funktionalität simuliert.

## BL-Layer

Die Business-Logik definiert die grundlegenden Funktionalitäten, die der Applikations-Logik zur Verfügung gestellt werden. Diese Schicht ist sehr abstrakt und implementiert nur die zentralsten Funktionalitäten in Form der Entität Ware.

## DAL

Der Data-Access-Layer implementiert die in der Business-Logik definierten Schnittstellen, um die gewünschten Persistierungsfunktionen bereit zu stellen. Für die Suche werden jedoch nur Lese-Funktionalitäten benötigt, weshalb die übrigen CRUD-Funktionalitäten nicht implementiert werden.
