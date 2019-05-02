# SW Duel

Sovellus on peli, jossa kaksi pelaajaa taistelee toisiaan vastaan. Pelihahmoilla on aina jokin tietty ase, jolla yritetään saada osuma toiseen. Pisteen saaneen pelaajan ase vaihtuu aina huonompaan, kunnes kaikki aseet on käyty läpi. Se, joka voitti toisen kaikilla aseilla ensin, voittaa pelin.

## Dokumentaatio

[dokumentaatio](swduel/dokumentaatio)

[käyttöohje](swduel/dokumentaatio/kayttoohje.md)

[työaikakirjanpito](swduel/dokumentaatio/tuntikirjanpito.md)

[vaatimusmäärittely](swduel/dokumentaatio/vaatimusmaarittely.md)

[arkkitehtuurikuvaus](swduel/dokumentaatio/arkkitehtuurikuvaus.md)

[testausdokumentti](swduel/dokumentaatio/testaus.md)

## Releaset

[Viikon 7 release](https://github.com/alhopasi/ot-harjoitustyo/releases/tag/viikko7)

## Komentorivikomennot

Sovelluksen käynnistämiseen tarvitaan juurihakemistoon kansiot `arenas`,`images` sekä `sounds`, joista löytyy tarvittavat tiedostot (ks. github tai release).

Sovellus käynnistyy komennolla:
`java -jar swduel.jar`

tai mavenilla lähdekoodia käyttäen:
`mvn compile exec:java -Dexec.mainClass=swduel.ui.SwduelUi`

Checkstyle raportin komento:
`mvn jxr:jxr checkstyle:checkstyle`

Jacoco raportin komento:
`mvn test jacoco:report`


Helsingin yliopiston kurssin **Ohjelmistotekniikka** harjoitustyö.

By *Pasi Alho*
