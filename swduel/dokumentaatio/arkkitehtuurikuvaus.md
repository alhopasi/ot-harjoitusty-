# Arkkitehtuurikuvaus

## Rakenne

Koodin rakenne on seuraava:
SwduelUi -> TitleMenu
TitleMenu -> Game
Game -> Logic / GameScreen
Logic -> Kaikki loput

## Käyttöliittymä

Käyttöliittymä sisältää kaksi erillistä näkymää

- valikkonäkymä
- pelinäkymä

molemmat ovat toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä, eli sijoitettu sovelluksen stageen. Käyttöliittymä on luokassa swduel.ui.SwduelUi.

## Sovelluslogiikka

Löytyy pakkauksesta swduel.domain

Valikkonäkymän sovelluslogiikasta huolehtii luokka TitleMenu.

Pelinäkymän sovelluslogiikasta huolehtii luokka Game, jolle annetaan parametrina pelattavan areenan nimi.

Luokka Game huolehtii peliin syötettävistä parametreistä.
- Kutsuu luokkaa Logic, joka huolehtii pelin logiikasta.
- Kutsuu luokkaa GameScreen, joka huolehtii kaiken piirtämisestä. Parametriksi logic.

Luokka Logic:
- Konstruktorissa kutsuu GameMapiä, joka rakentaa kartan annetun areenan nimen perusteella.
- Pelin päivittäminen
- Painetut näppäimet -> mitä tapahtuu
- Ammusten luominen ja liikuttaminen ja kesto
- Tarkasta osumat ammuksista
- Pelaajaan osuminen -> Luo pelaajat uudestaan -> valitsee aseet.

Luokka GameScreen:
- Piirtää pelialueen ja päivittää sitä (40 fps?)
- Tausta (vaalensininen?)
- Blokit
- Pelaajat
- Esineet (ammukset)