# Prog-Fu-ball
Gruppen Projekt programmieren 

Inhalt
1	Einleitung	
2	Problembeschreibung	
3	Teamorganisation	

	 
1	Einleitung
Im Rahmen des Moduls Programmieren, Design und Implementierung von Algorithmen II wurde im Team ein Programmierprojekt entwickelt, das die Prinzipien der objektorientier-ten Programmierung umsetzt, welche wir in den Vorlesungen erlernt haben. Dabei soll das Projekt modular, erweiterbar und gut wartbar sein. Ziel des Projektes ist es, eine praxisnahe Softwarelösung zu entwerfen und umzusetzen, die eine konkrete Problemstellung behandelt und gleichzeitig den Einsatz moderner Entwicklungswerkzeuge und Methoden darstellt. 
Die schriftliche Ausarbeitung dokumentiert den gesamten Projektverlauf. Und stellt die Prob-lembeschreibungen über die Analyse möglicher Lösungsansätze dar, bis hin zur finalen Im-plementierung und exemplarischen Nutzung des Programms. Neben der technischen Umset-zung wird auch die Organisation des Projektes beschrieben, einschließlich Rollenverteilung, Kommunikationsstruktur sowie eingesetzter Tools. 
Darüber hinaus werden die strukturellen und technischen Entscheidungen begründet, welche im Laufe der Entwicklung getroffen wurden. Hier wird sowohl auf übergeordnete Architek-turfragen als auch auf konkrete Detailentscheidungen eingegangen. Die Beschreibung der Programmlogik erfolgt anhand einer Übersicht von ausgewählten Klassen, (ergänzt durch ein UML-Klassendiagramm). 

2	Problembeschreibung

2.1	Projektname und Logo

2.2	Beschreibung des Szenarios bzw. der Problemstellung in eigenen Worten.

In der Roboter-Fußballsimulation treten zwei Mannschaften mit jeweils fünf Robotern gegen-einander an. Jeder Roboter ist eindeutig einer Mannschaft zugeordnet und übernimmt eine spezifische Rolle auf dem Spielfeld. Die Rollen sind wie folgt festgelegt:  einem Torwart, ei-nem Verteidiger, zwei Mittelfeldspielern sowie einem Stürmer.
Die Roboter verfügen über individuelle Eigenschaften. Dazu zählen eine eindeutige Kennung (Name oder ID), Geschwindigkeit, Präzision beim Passen und Schießen sowie ein Energie Level, der sich im Spielverlauf durch Aktionen verbraucht und durch Aufladen teilweise re-generieren lässt.
Das Spiel selbst verläuft rundenbasiert. In jeder Runde wird für jeden Roboter festgelegt, welche Aktion er ausführt. Zu den möglichen Aktionen zählen das Laufen über das Spielfeld, das Passen zu einem Mitspieler, ein Torschuss, das Blocken gegnerischer Spieler oder das Energieaufladen. 
Der Ball kann sich entweder im Besitz eines Roboters befinden oder frei auf dem Spielfeld liegen. Eine Mannschaft gilt als ballführend, solange einer ihrer Roboter den Ball kontrolliert. Nach jedem Spielzug wird überprüft, ob ein Tor erzielt wurde, ob der Ballbesitz wechselt, beispielsweise durch einen misslungenen Pass oder eine erfolgreiche Abwehraktion und ob Roboter aufgrund von Energiemangel zeitweise nicht einsatzfähig sind. 
Die Software zur Simulation bietet verschiedene Funktionen. Zunächst werden Mannschaften angelegt und mit den passenden Rollen und Eigenschaften ausgestattet. Zu Beginn des Spiels werden die Startpositionen der Roboter sowie der anfängliche Ballbesitz festgelegt. In jeder Spielrunde trifft der Benutzer Entscheidungen über die Aktionen der Roboter, woraufhin das System die Folgen dieser berechnet, wie etwa Ballbewegungen oder den Energieverbrauch. Während des Spiels werden Informationen wie Spielstand, aktueller Ballbesitz und der Ener-giezustand der Roboter angezeigt. Das Spiel endet entweder nach einer festgelegten Anzahl an Runden oder durch einen manuellen Abbruch. Der Gewinner ergibt sich aus der Anzahl der erzielten Tore.

2.3	Teamübersicht: Wer war für welche Teile der Programmierung, Dokumentation und Präsentation verantwortlich? 

Aufbau des Programmes: Sascha, Karsten, Abdullah, Karim, Lisa
Spielfeld []
Roboter: ihre Eigenschaften und Rollen deklarieren und initialisieren + Roboter in ihre initia-le Position bringen [Abdullah]
Spielzüge deklarieren [Abdullah] 
Spielstart angeben, dass die Roboter in ihre Startposition gehen können; Spielstart durch die Eingabe für alle Roboter (Roboter anzeigen mit Rollen, Positionen und Eigenschaften, sodass der Eingeber entscheiden kann und sinnvoll eingeben kann) => Beachte das die Eingabe nach bestimmten Kriterien erfolgt Bsp.: Es wird gezielt gefragt, was welcher Roboter machen soll [Lisa]
Timer 2x 2:30 Minuten/ Runden [Sascha, Karim]
Nach jedem Spielzug auf Tor, Ballbesitz und Energielevel überprüfen (zeigen durch eine Aus-gabe) [Karsten]
Spielstand anzeigen [Karsten]
Zufallsfaktor einbauen für Fehlpässe und Torschüsse [Abdullah] 
Ende des Spiels angeben (hier kann im Anschluss die Startposition von jedem Roboter wieder eingenommen werden) [Lisa]

3	Teamorganisation

Zur Organisation des Codes wird GitHub verwendet. Innerhalb von GitHub wird der Reiter Projects zur Planung und Organisation von aufgaben verwendet. Hier ist ersichtlich, was die momentanen „Todo“ Aufgaben sind, welche Aufgaben in „Progress“ sind und welche Aufga-ben „Done“ sind. 
Zur Ausarbeitung der Schriftlichen Ausarbeitung wird das Textverarbeitungsprogramm Word benutzt
Mithilfe der Webseite sharepoint.com stellt Lisa in den Team Meetings kleine Präsentationen mit der Agenda für den Tag dar. Es wird kurz über die momentanen Aufgaben Verteilung geredet und weitere Fragen geklärt.
Kommunikation und Planung für die Meetings erfolgt über den Messenger WhatsApp. Dort wird über eine Umfrage ein möglicher neuer Termin für das nächste Meetings abgestimmt.
