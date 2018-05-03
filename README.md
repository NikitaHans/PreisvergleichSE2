# Starter Code für Presirechner

Kurze Erläuterung:

Die Files im root-Verzeichnis sind Konfigurationen für die Datenbank, mehr dazu, wenn wir damit richtig arbeiten.

Die Dateien im Ordner src/main/java/edu/hm sind für dynamisches Linken (Guice) von den Interfaces zu den richtigen Klassen oder Testklassen (ShareITApplication, ShareITServletContextListener), bzw. JettyStarter, diese Klasse startet einen lokalen Server mit unserer Webanwendung. Bitte diese Dateien nicht verändern, außer ihr wisst was ihr tut ;)


im Ornder shareit finden sich folgende Unterordner:

models: Repräsentation des Datenmodels, sinnvoll für das mappen von Json auf Java-Objekte bzw. für das Mappen von Java-Objekte auf die Datenbank

service: Geschäftslogik, die wir implementiren müssen

Ressource: Schnittstellen für eingehende Requests (Gateway)

persistence: Schnittstelle zur und Konfiguration der Datenbank


Wichtig: Damit ihr das Projekt nutzen könnt, benötigt ihr Maven, das baut euch dann daraus ein Eclipse bzw. ein IntelliJ Projekt.
Pusht bitte auch nicht eure lokalen Projekt-Settings ins Repo und wenn ihr was pusht, dann bitte immer in einen eigenen Branch, damit wir gemeinsam das mergen können und keine Fehler in den Master-Branch kommen.

Das Frontend bitte im Ordner src/main/webapp implementieren und bitte nicht die Konfigurationen im Webinf-Ordner verändern.

Bei Fragen könnt ihr mich gerne kontaktieren. Ich bitte Nikita dazu, als Verantwortlichen für die Dev-Ops den Push-Vorgang zu kontrollieren und darauf zu achten, dass nur per Merge-Request auf den Master-Branch gepusht werden darf.
