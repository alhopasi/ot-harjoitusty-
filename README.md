# SW Duel

Sovellus on peli, jossa kaksi pelaajaa taistelee toisiaan vastaan. Pelihahmoilla on aina jokin tietty ase, jolla yritetään saada osuma toiseen. Pisteen saaneen pelaajan ase vaihtuu aina huonompaan, kunnes kaikki aseet on käyty läpi. Se, joka voitti toisen kaikilla aseilla ensin, voittaa pelin.

## Dokumentaatio

[dokumentaatio](swduel/dokumentaatio)

[työaikakirjanpito](swduel/dokumentaatio/tuntikirjanpito.md)

[vaatimusmäärittely](swduel/dokumentaatio/vaatimusmaarittely.md)

[arkkitehtuurikuvaus](swduel/dokumentaatio/arkkitehtuurikuvaus.md)

## Komentorivikomennot

Sovelluksen käynnistämiseen tarvitaan juurihakemistoon kansiot `arenas` sekä `images`, joista löytyy tarvittavat tiedostot (ks. github).

Sovellus käynnistyy komennolla:
`java -jar swduel-1.0-SNAPSHOT.jar`

tai lähdekoodia käyttäen:
`mvn compile exec:java -Dexec.mainClass=swduel.ui.SwduelUi`

Helsingin yliopiston kurssin **Ohjelmistotekniikka** harjoitustyö.

By *Pasi Alho*
