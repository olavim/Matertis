Ohjelman Rakenne
================

Kirjallinen versio luokkakaaviosta, tosin puheenaiheet saattavat eritä hieman; tästä tekstistä saattaa ilmetä asioita,
jotka eivät ilmene luokkakaaviosta, sekä toisinpäin.

#### Perusasiat

`Main` luokalla ei ole mitään riippuvuuksia, ja sen ainoa funktio on käynnistää päävalikko: `MenuUI`.
Päävalikosta voi aloittaa pelin, tuoda asetusikkunan näkyviin tai sulkea ohjelman. Käytännössä näihin painikkeisiin
on kiinnitetty kullekin oma `Action`; *Start Game* nappiin on kiinnitetty `CloseUIAndStartGameAction`, *Settings* nappiin on
kiinnitetty `ShowSettingsAction` ja *Exit* nappiin on kiinnitetty `CloseApplicationAction`. Kukin nappi toimii oletetulla
tavalla.

Aina pelin alkaessa haetaan `GameHandler` luokka `SettingsManager`ilta, joka pitää kirjaa nykyisistä asetuksista ja
osaa antaa oikein asetetun `GameHandler` luokan. Uutta game handleria ei siis koskaan luoda, vaan `SettingsManager`illa
on tieto yhdestä `GameHandler`ista, jonka se alustaa ja säätää aina, kun kutsutaan `SettingsManager.getGameHandler()` metodia.
`GameHandler`ille annetaan kuitenkin aina uusi instanssi `Game` luokasta, jolla on päivitetty koko, sekä `ScoreHandler`luokasta.
Tämä helpottaa pelin alustamista ja uudelleenaloittamista.

`GameHandler` tuntee siis `Game` luokan olion, ja peliä pelataankin pitkälti näiden kahden luokan säestämänä. `GameHandler`
pitää huolen ajallisista kysymyksistä, eli esim. kuinka nopeasti palikat tippuvat, ja pyytää tietyin väliajoin `Game`
oliota pelaamaan seuraavan kierroksen. `GameHandler` tuntee myös `ScoreHandler` olion, joka määrää miten pelin pisteet
lasketaan. Jokaisen pelatun vuoron jälkeen `GameHandler` kertoo `ScoreHandler`ille, kuinka monta riviä täytettiin
(saattaa olla nolla riviä).

`Game` luokka tietää oman kokonsa (leveyden ja korkeuden), pelialustan (`GameGrid`), tällä hetkellä tippuvan tetrispalikan
(tetrispalikat ovat `Tetromino` olioita), sekä seuraavaksi tippuvan palikan. `Game` luokan tarkoituksena on antaa
peruslogiikka sille, mitä tapahtuu yhden siirron aikana. `GameGrid` luokka pitää kirjaa tippuneista palikoista; se ei siis
tiedä mitään tällä hetkellä tippuvasta palikasta, samoin kuin `Game` ei tiedä mitään jo tippuneista palikoista
(lukuunottamatta sitä, mitä `GameGrid` tietää).

#### Pelin piirtäminen

Kun peli käynnistetään, myös peli-ikkuna, `GameUI` käynnistyy. `GameUI` tuntee `GameHandler` olion, ja piirtääkin
sen perusteella pelialueen, kuvan seuraavaksi tippuvasta palikasta, sekä tämänhetkisen pistetilanteen.

`GameHandler` perii `Observable` luokan, ja `GameUI` toteuttaa `Observer` rajapinnan. `GameHandler` kertoo tietyistä
tapahtumista kaikille sen "observoijille", kuten pelin alkamisesta, lopettamisesta, pysäyttämisestä/jatkamisesta sekä
seuraavan kierroksen loppumisesta. Käytännössä `GameUI` on kiinostunut ainoastaan pysäyttämisestä,
jolloin pelialue vaihdetaan "paused" tekstiin, sekä jatkamisesta, jolloin pelialue tulee takaisin näkyviin.

#### Pelaajan komennot

`SettingsManager` tuntee myös `CommandHandler` olion, jota game handlerin tavoin luodaan ainoastaan yksi instanssi koko
ohjelman suorittamisen ajaksi. `GameUI` tuntee tämän `CommandHandler` olion, jolle kaikki käyttäjän tekemät näppäimistön
painallukset ohjataan. `CommandHandler` tuntee liudan `Command` rajapinnan toteuttavia komentoja, joihin kuuluu esim.
tetris palikan liikuttaminen, tiputtaminen ja kääntäminen, sekä vähän laajempia komentoja kuten pelin uudelleenaloittaminen
ja pysäyttäminen/jatkaminen. Jokaista komentoa vastaa jokin näppäinkomento, eli esim. oletuksena vasen nuolinäppäin
vastaa komentoa joka liikuttaa tetris palikkaa vasemmalle.

Vaikkei se ole pakollista, niin käytännössä kaikki tällä hetkellä olemassaolevat `Command` rajapinnan toteuttavat luokat
tuntevat `GameHandler` instanssin, jota komennot sitten tavalla tai toisella käyttävät.

#### Äänet

`AudioManager` luokka huolehtii ääniefekteistä ja musiikista. `Game` luokka kutsuu `AudioManager`ia aina, kun palikka
tippuu maahan, rivejä täytetään tai peli loppuu. `GameHandler` kutsuu `AudioManager`ia aina, kun peli käynnistyy.

#### Asetukset

Kuten varmaan jo tuli ilmi, `SettingsManager` luokka pitää kirjaa joistain asetuksista. Asetuksia muutetaan
`SettingsDialog` dialogissa, joka avautuu kun painetaan päävalikon *Settings* nappia. Dialogissa painamalla *Save*
nappia ohjelma tarkistaa että asetukset ovat kunnossa, ja jos ovat, tallentaa asetukset omiin paikkoihinsa.

Asetusikkunassa asetukset on jaettu omiin sektioihinsa: *Game*, *Controls* ja *Audio*. *Game* sektion asetukset
tallennetaan `SettingsManager` luokan muistiin, *Controls* sektion asetukset kirjataan `SettingsManager` luokan palauttaman
`CommandHandler` olion muistiin, ja *Audio* sektion asetukset kirjataan `AudioManager` luokan muistiin.
