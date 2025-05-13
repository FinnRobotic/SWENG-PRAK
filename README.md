# MM - Mad Machines

README – Crazy Machines – Gruppe 5

Projektbeschreibung:
Dieses Projekt ist Teil des Software Engineering Praktikums (CS2301) im Sommersemester 2025 an der Universität zu Lübeck.
Ziel ist es, ein JavaFX-basiertes Spiel zu entwickeln, das auf dem Prinzip von 'Crazy Machines' basiert.

Technologien:
- Java 11
- JavaFX
- jBox2D (für Physiksimulation)
- Maven (Build-Tool)
- GitLab (Versionsverwaltung)
- UML-Diagramme zur Architekturplanung

Projektstruktur:
src/
├── main/       -> Java Code und Ressourcen
├── test/       -> JUnit Tests
├── pom.xml     -> Maven-Projektdatei

Projekt ausführen:
Projekt starten:
mvn clean javafx:run

Headless-Modus (ohne GUI):
mvn javafx:run -Dargs="--no-gui"

Sanity Check:
- Projekt erfolgreich gestartet mit Maven und JavaFX
- Abhängigkeiten installiert
- JavaFX läuft lokal mit Java 11

Team & Aufgabenverteilung:
- Nour Ktaech: Anforderungsanalyse, GitLab Setup
- Zelda Becker: Dokumentation, Zeitplanung
- Finn: Objektstruktur, JavaFX Views
- Maxi: GUI-Design mit CSS

Wichtige Dokumente:
- Anforderungsanalyse.pdf
- Netzplan.pdf
- ViewMan-MVC-UML-Diagramm.pdf
- Doku/StoryCards/*.pdf

Git-Tags:
git tag r1
git push origin r1

Projektstatus:
Release I: bereit zur Abgabe (Deadline: 21.05.2025 – 12:00 Uhr)


# Maven

Kurzübersicht nützlicher Maven-Befehle. Weitere Informationen finden sich im Tutorial:

* `mvn clean` löscht alle generierten Dateien
* `mvn compile` übersetzt den Code
* `mvn javafx:jlink` packt den gebauten Code als modulare Laufzeit-Image. Das Projekt kann danach gestartet werden mit `target/dhl/bin/dhl`
* `mvn test` führt die Tests aus
* `mvn compile site` baut den Code, die Dokumentation und die Tests und führt alle Tests, sowie JaCoCo und PMD inklusive CPD aus. Die Datei `target/site/index.html` bietet eine Übersicht über alle Reports.
* `mvn javafx:run` führt das Projekt aus
* `mvn javafx:run -Dargs="--no-gui"` führt das Projekt mit Command-Line-Parameter `--no-gui` aus.






## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.
