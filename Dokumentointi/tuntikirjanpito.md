<h1>Tuntikirjanpito</h1>

Tähän dokumenttiin kokoan pikkuhiljaa tietoa projektin etenemisestä.

<h2>Sisällysluettelo</h2>

<ul>
  <li><a href="#muotoilu">Muotoilu</a></li>
  <li><a href="#kirjanpito">Kirjanpito</a></li>
  <ul>
    <li><a href="#19.12.2014">19.12.2014</a></li>
    <li><a href="#20.12.2014">20.12.2014</a></li>
    <li><a href="#21.12.2014">21.12.2014</a></li>
    <li><a href="#22.12.2014">22.12.2014</a></li>
    <li><a href="#23.12.2014">23.12.2014</a></li>
    <li><a href="#24.12.2014">24.12.2014 hyvää joulua</a></li>
    <li><a href="#26.12.2014">26.12.2014</a></li>
    <li><a href="#27.12.2014">27.12.2014</a></li>
    <li><a href="#28.12.2014">28.12.2014</a></li>
  </ul>
</ul>

<h2>Muotoilu</h2>

Tämä asiakirja on HTML-muodossa, joten sen tarkastelu tekstieditorissa voi olla raastavaa.<br>
Suosittelenkin katselemaan tätä GitHubista käsin.
<p>
Kirjanpito noudattaa seuraavaa muotoilua:
<p>
<table>
  <thead>
    <tr><th colspan="2">Päivämäärä</th></tr>
  </thead>
  <tbody>
    <tr>
      <td>Käytetty aika</td>
      <td>Ajankäytön kohde</td>
    </tr>
    <tr>
      <td>Yhteensä käytetty aika</td>
    </tr>
  </tbody>
</table>

Samalta päivämäärältä saattaa tulla useampi taulu, jos koen että jotkin tekemäni asiat<br>
liittyvät vahvasti toisiinsa. Eli jos esim. samana päivänä luon GitHub repon ja teen siihen<br>
liittyviä alustuksia, ja myöhemmin samana päivänä koodaan itse ohjelmaa, laitan nämä<br>
kaksi tapahtumaa omiin tauluihinsa.

<h2>Kirjanpito</h2>

<table id="19.12.2014">
  <thead>
    <tr>
      <th colspan="2">19.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>30 min</td>
      <td width="600px">
        GitHub repositorion luominen ja sen järjestelyä alustavaan kuntoon.<br>
        mm. READMEN ja tämän asiakirjan kirjoittaminen.
      </td>
    </tr>
    <tr>
      <td>30 min</td>
      <td>Maven projektin luominen. Tähän sisältyy Pitin ja Coberturan konffaus.</td>
    </tr>
    <tr>
      <td>15 min</td>
      <td>Gitin konfiguroimista ja paikallisten alustavien muutosten lähettäminen GitHubiin</td>
    </tr>
    <tr>
      <td>75 min</td>
    </tr>
  </tbody>
</table>

<table>
  <thead>
    <tr><th colspan="2">19.12.2014</th></tr>
  </thead>
  <tbody>
    <tr>
      <td>15 min</td>
      <td width="600px">Tein alustavan luokkakaavion.</td>
    </tr>
    <tr>
      <td>15 min</td>
    </tr>
  </tbody>
</table>

<table>
  <thead>
    <tr><th colspan="2">19.12.2014</th></tr>
  </thead>
  <tbody>
    <tr>
      <td>10 min</td>
      <td width="600px">
        Lisäsin <a href="http://www.miglayout.com/" target="_blank">MigLayoutin</a> projektiin
        pienimuotoisen opiskelun päätteeksi.
      </td>
    </tr>
    <tr>
      <td>30 min</td>
      <td width="600px">
        Päävalikon rakentamista. Se on nyt päällisin puolin valmis:
        <b>exit</b>-nappi sulkee ohjelman, mutta <b>start game</b> -nappi ei vielä tee mitään tähdellistä.
      </td>
    </tr>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Itse pelin ja siihen liittyvien luokkien koodaamista.
      </td>
    </tr>
    <tr>
      <td>100 min</td>
    </tr>
  </tbody>
</table>

<table id="20.12.2014">
  <thead>
    <tr>
      <th colspan="2">20.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>120 min</td>
      <td width="600px">
        Yksinkertaista toiminnallisuutta saatu aikaan. Pelin voi nyt aloittaa <b>Start Game</b> -napilla.
        Ruudulle ilmestyy kaikkien rakastama tetris peli kaikessa yksinkertaisuudessaan. Pisteet puuttuvat,
        palikoita ei voi tiputtaa alas asti kerralla, ja peli ei vielä lopu kun palikka osuu alueen ylälaitaan.
      </td>
    </tr>
    <tr>
      <td>120 min</td>
      <td width="600px">
        Armotonta refaktorointia. Jokainen tetromino (tetrispalikan muoto) on nyt staattisena sisäisenä luokkana
        <code>Tetromino</code> luokan sisällä. Myös muuta pienimuotoisempaa; metodien uudelleennimeämistä yms.
      </td>
    </tr>
    <tr>
      <td>30 min</td>
      <td width="600px">
        Pienoisia javadocseja saattaa jo löytää sieltä sun täältä; omasta mielestä kriittisimmistä paikoista.
      </td>
    </tr>
    <tr>
      <td>270 min</td>
    </tr>
  </tbody>
</table>

<table id="21.12.2014">
  <thead>
    <tr>
      <th colspan="2">21.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>120 min</td>
      <td width="600px">
        Refaktorointia. Muutin <code>MatertisGame</code> luokan nimen <code>GameLogic</code>:ksi, jotta se vähän paremmin
        vihjaisi tarkoitustaan. Tästä luonnollisesti kehittelin <code>GameHandler</code> luokan, joka vastaa ikkunan
        päivityspyynnöistä ja vuorojen alkamisista säännöllisin väliajoin, sekä vastaanottaa ikkunalta saatuja
        näppäinkomentoja ja ohjaa ne eteenpäin komentoina logiikkaluokalle (<code>GameLogic</code>).
      </td>
    </tr>
    <tr>
      <td>15 min</td>
      <td width="600px">
        Tetrominot piirretään nyt vähän eri tavalla; jokaisen tetrominon palasessa on nyt sellainen sisennyksen näköinen
        visuaalinen piirre, joka saa tetrominot näyttämään vähän perinteisemmiltä. Lisäksi eriväriset tetrominot!!
      </td>
    </tr>
    <tr>
      <td>30 min</td>
      <td width="600px">
        Peli loppuu nyt kun pelialue täyttyy. Lisäksi palikat voi tiputtaa alas asti painamalla välilyöntiä.
      </td>
    </tr>
    <tr>
      <td>165 min</td>
    </tr>
  </tbody>
</table>

<table>
  <thead>
    <tr>
      <th colspan="2">21.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Refaktorointia. Tetrominot piirretään taas vähän eri tavalla. Visuaaliseen ilmeeseen ei koskettu, mutta tapa,
        jolla piirrettävät asiat haetaan, muuttui. Ennen haettiin lista tetrominoista, jotka ovat pelialueella, ja
        piirrettiin jokainen tetromino yksi kerrallaan. Tämä lista myös sisälsi par'aikaa tippuvan palikan. Nyt koko
        alue vain väritetään <code>Grid</code> luokan sisältämän layoutin mukaan. Tämä on tehokkuuden kannalta teoriassa
        vähän raskaampaa, mutta tällä tavoin palikoiden tiputtaminen, kun jokin rivi täyttyy, on sata kertaa helpompaa ja
        hauskempaa.
      </td>
    </tr>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Nyt täydet rivit katoavat, joka tekeekin tästä pelistä "pelattavan".
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <b>CHECKPOINT:</b><br>
        Peliä voi pelata sanan yksinkertaisimmassa merkityksessä.
      </td>
    </tr>
    <tr>
      <td>120 min</td>
    </tr>
  </tbody>
</table>

<table>
  <thead>
    <tr>
      <th colspan="2">21.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Testit kirjoitettu <code>Tetromino</code> luokalle.
      </td>
    </tr>
    <tr>
      <td>120 min</td>
      <td width="600px">
        Refaktorointia ja testien kirjoittamista <code>GridLogic</code> luokalle.
      </td>
    </tr>
    <tr>
      <td>180 min</td>
    </tr>
  </tbody>
</table>

<table>
  <thead>
    <tr>
      <th colspan="2">21.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>30 min</td>
      <td width="600px">
        Päivitin luokkakaaviota.
      </td>
    </tr>
    <tr>
      <td>30 min</td>
    </tr>
  </tbody>
</table>

<table id="22.12.2014">
  <thead>
    <tr>
      <th colspan="2">22.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>120 min</td>
      <td width="600px">
        Refaktorointia ja testien kirjoittamista. <code>GridLogic</code> luokan testit valmiit.
      </td>
    </tr>
    <tr>
      <td>120 min</td>
      <td width="600px">
        Refaktorointia ja dokumentaation kirjoittamista.
      </td>
    </tr>
    <tr>
      <td>240 min</td>
    </tr>
  </tbody>
</table>

<table id="23.12.2014">
  <thead>
    <tr>
      <th colspan="2">23.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Refaktorointia. <code>GridLogic</code> ei ole enää utility class; sen nimi on nyt pelkkä <code>Grid</code>, ja
        esittää pelialuetta.
      </td>
    </tr>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Lisäsin ruudun peli-ikkunan ylälaitaan, josta näkee palikan, joka rupeaa seuraavaksi putoamaan.
      </td>
    </tr>
    <tr>
      <td>120 min</td>
    </tr>
  </tbody>
</table>

<table id="24.12.2014">
  <thead>
    <tr>
      <th colspan="2">24.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>120 min</td>
      <td width="600px">
        Petteri Punakuonon säestyksen alaisena pisteytysjärjestelmän kasaamista.
      </td>
    </tr>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Pisteet ja vaikeustaso näkyvät nyt peli-ikkunan oikeassa laidassa.
      </td>
    </tr>
    <tr>
      <td>30 min</td>
      <td width="600px">
        Peli nopeutuu 20% aina kymmenen täytetyn rivin jälkeen.
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center" width="600px">
        <b>CHECKPOINT:</b><br>
        kaikki pelin perusominaisuudet on tehty. Vielä puuttuu ainakin pysäyttäminen (pause), uusi peli edellisen päätyttyä          ja jonkinlainen scoreboard. Aikaa kun on vielä sadan rasvatun kääpiön verran niin muita ominaisuuksia tulenee olemaan         määriteltävä kentän koko, taustamusiikki, uudelleenmääriteltävät näppäimet ja ehkä kaksinpeli??
      </td>
    </tr>
    <tr>
      <td>210 min</td>
    </tr>
  </tbody>
</table>

<table id="26.12.2014">
  <thead>
    <tr>
      <th colspan="2">26.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>120 min</td>
      <td width="600px">
        Refaktorointia ja pysäytysvalikon luomista. Pelin voi nyt siis pysäyttää painamalla P- tai ESC-näppäintä.
        Näppäimiä painamalla peli pysähtyy ja esille tulee valikko, josta voi jatkaa peliä tai lopettaa sen ja
        palata päävalikkoon.
      </td>
    </tr>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Vähän testejä kirjoitellut lisää.
      </td>
    </tr>
    <tr>
      <td>180 min</td>
    </tr>
  </tbody>
</table>

<table id="27.12.2014">
  <thead>
    <tr>
      <th colspan="2">27.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>30 min</td>
      <td width="600px">
        Testit kirjoitettu loppuun <code>Game</code> luokalle.
      </td>
    </tr>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Pientä refaktorointia ja tulevan suunnittelua: uusi <code>CommandHandler</code> luokka toimii pelaajan komentojen
        joukon edustajana. <code>SettingsManager</code> tulee huolehtimaan kaikista asetuksista, joilla peli pelataan, kuten
        pelikentän koosta, valituista komentojen näppäimistä (<i>key bindings</i>) yms. <code>CommandHandler</code> luokka
        tulisikin aina hakea <code>SettingsManager</code>ilta, jotta saadaan halutut asetukset käyttöön.
      </td>
    </tr>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Testien kirjoittelua <code>GameHandler</code> luokalle.
      </td>
    </tr>
    <tr>
      <td>150 min</td>
    </tr>
  </tbody>
</table>

<table id="28.12.2014">
  <thead>
    <tr>
      <th colspan="2">28.12.2014</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Testien kirjoittelua <code>GameHandler</code> luokalle.
      </td>
    </tr>
    <tr>
      <td>60 min</td>
      <td width="600px">
        Dokumentaation kirjoittelua.
      </td>
    </tr>
    <tr>
      <td>120 min</td>
    </tr>
  </tbody>
</table>
