Ohjeet
======

Tässä asiakirjassa kerrotaan miten ohjelmaa käytetään, ja miten itse peliä pelataan.

### Päävalikko

- **Start Game:** aloittaa pelin.
- **Settings:** tuo näkyviin asetusikkunan.
- **Exit:** sulkee ohjelman.

### Asetukset

Asetuksiin pääsee käsiksi päävalikon **Settings** nappia painamalla. Esille ilmestyneestä asetusikkunasta
voit muuttaa pelialueen kokoa (leveyttä ja korkeutta), pelin vaikeustasoa sekä näppäimiä.

- **Pelialueen koko *(width & height)*:** sen alueen koko, minne tetrispalikoita pyritään kasaamaan. Alueen pitää olla vähintään
4 palikkaa, sekä enintään 100 palikkaa leveä ja korkea. Jos alue on kovin iso, tosin, se ei välttämättä mahdu kokonaan
näytölle.
- **Vaikeustaso *(difficulty)*:** määrää kuinka nopeasti tetrispalikat tippuvat aluksi, kuinka usein taso vaihtuu ja
kuinka paljon nopeammin tetrispalikat tippuvat jokaisen tason jälkeen.
- **Näppäimet *(controls)*:** määräävät mikä komento tapahtuu mistäkin näppäimistön näppäimestä.
- **Äänet *(sounds)*:** onko musiikkia tai ääniefektejä. Oletuksena kaikki päällä.

Kaikki muutokset asetuksiin säilyvät niin kauan, kunnes ohjelma suljetaan.

### Pelaaminen

Tetris on hyvin yksinkertainen peli. Ideana on kasata erimuotoisia tippuvia palikoita toistensa päälle siten,
että yritetään täyttää vaakarivejä. Aina kun vaakarivi tulee täyteen, se rivi katoaa ja kaikki palikat sen yllä tippuvat
yhden pykälän alaspäin.

Tetristä ei siis voi voittaa, vaan ideana on selvitä mahdollisimman pitkään ja kerätä mahdollisimman paljon pisteitä.

### Pisteytys

Tässä pelissä voi saada pisteitä ainoastaan täyttämällä rivejä. Saatujen pisteiden määrä määräytyy kerralla
täytettyjen rivien määrästä, sekä tämänhetkisestä tasosta (level).

Tarkemmin sanottuna:

| täytetyt rivit | pisteet |
|:--------------:|:-------:|
|       1        | 1 * taso |
|       2        | 3 * taso |
|       3        | 5 * taso |
|       4        | 8 * taso |

### Näppäimet

Oletusnäppäimet ovat seuraavanlaiset:

| näppäin | komento |
|:-------:|:------- |
| Vasen nuoli | Liikuta palikkaa vasemmalle |
| Oikea nuoli | Liikuta palikkaa oikealle |
| Ylös nuoli | Käännä palikkaa myötäpäivään |
| Alas nuoli | Tipauta palikkaa yhden rivin verran |
| Välilyönti | Tiputa palikka alas asti |
| P | Pysäytä peli |
| R | Aloita peli alusta |
