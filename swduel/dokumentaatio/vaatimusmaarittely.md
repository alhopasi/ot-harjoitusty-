# Vaatimuusmäärittely

## Sovelluksen yleiskuvaus

Sovellus on peli, jossa kaksi pelaajaa taistelevat toisiaan vastaan. Molemmat pelaajat kontrolloivat omaa hahmoaan ja yrittävät saada osumia toiseen pelaajaan.

## Käyttäjät

Pelin pelaamiseen tarvitaan 2 henkilöä, jotka pelaavat samalta koneelta. Pelissä ei luoda omia käyttäjiä.

## Käyttöliittymä

Käyttöliittymä on mahdollisimman yksinkertainen. Otsikkonäkymästä voi siirtyä pelaamaan tai lukemaan ohjeet.

<img src="kuvat/otsikkonakyma.jpg" alt=otsikkonakyma width="400" /><img src="kuvat/taistelunakyma.jpg" alt=taistelunakyma width="400" />

## Pelin toiminnallisuus

Sovellus käynnistyy otsikko-ikkunaan, josta voi lukea ohjeen tai alkaa pelaamaan.
Ohje kertoo lyhyesti pelin tavoitteen ja miten pelihahmoja liikutetaan.

### Pelaa

#### Areena

Pelaajat taistelevat aina samalla areenalla. Areenalla on korkeuseroja ja tasoja, joilla pelihahmot voivat liikkua.

#### Taistelu

##### Liikkuminen

Pelihahmot pystyvät liikkumaan sivusuunnassa sekä hyppäämään.

##### Hyökkäys

Pelihahmot voivat hyökätä aseellaan 8 suuntaan ympärilleen.

##### Aseet

Pelihahmot käyttävät erilaisia aseita, lähitaisteluaseista ampuma-aseisiin. Aseilla on erilaisia ominaisuuksia, kuten hyökkäysnopeus, osuma-alue ja hyökkäyksen kesto.

#### Kuolema

Jos pelihahmo saa osuman, joko toiselta pelaajalta tai vaarasta, pelihahmo kuolee.
Tällöin toinen pelaaja saa pisteen. Pisteen saaneen pelaajan ase vaihdetaan huonompaan. Osuman saanut pelaaja syntyy uudelleen samalla aseella.

#### Voittaminen
Se pelaaja, joka ensimmäisenä on saanut pisteen jokaisella aseella parhaimmasta huonoimpaan, voittaa.

## Jatkokehitystä

Toimivan perusversion jälkeen peliä täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla.

- useampia areenoita
- areenoille valintasivu
- areenoille vaaroja: passiiviset (piikit, laava jne.) sekä aktiiviset (ammukset jne.)
- vaarojen laukeaminen kun pelaaja astuu tiettyyn kohtaan
- taustan luominen areenalle
- aseen vaihtumisen sijasta vaihtuu pelihahmo (jolla on kyseinen ase).
- Pelihahmoista erilaisempia. Esim. koko voi olla eri tai hyppäämisen sijasta hahmo lentää rakettirepulla.