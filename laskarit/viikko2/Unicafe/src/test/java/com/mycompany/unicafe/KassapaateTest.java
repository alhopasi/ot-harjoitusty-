package com.mycompany.unicafe;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void kassassaRahaaOikeinUudellaKassalla() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisiaLounaitaMyytyOikeinUudellaKassalla() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaitaLounaitaMyytyOikeinUudellaKassalla() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisellaLounaallaKassaSaaRahaa() {
        paate.syoEdullisesti(500);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void maukkaallaLounaallaKassaSaaRahaa() {
        paate.syoMaukkaasti(500);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void vaihtoRahaSuuruusOikeaEdullisellaLounaalla() {
        assertEquals(260, paate.syoEdullisesti(500));
    }
    
    @Test
    public void vaihtoRahaSuuruusOikeaMaukkaallaLounaalla() {
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    
    @Test
    public void edullisellaLounaallaLounasMaaraKasvaa() {
        paate.syoEdullisesti(500);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaallaLounaallaLounasMaaraKasvaa() {
        paate.syoMaukkaasti(500);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josEiRahaaEdullisellaLounaallaKassaEiSaaRahaa() {
        paate.syoEdullisesti(100);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void josEiRahaaMaukkaallaLounaallaKassaEiSaaRahaa() {
        paate.syoMaukkaasti(300);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void josEiRahaaVaihtoRahaSuuruusOikeaEdullisellaLounaalla() {
        assertEquals(200, paate.syoEdullisesti(200));
    }
    
    @Test
    public void josEiRahaaVaihtoRahaSuuruusOikeaMaukkaallaLounaalla() {
        assertEquals(300, paate.syoMaukkaasti(300));
    }
    
    @Test
    public void josEiRahaaEdullisellaLounaallaLounasMaaraEiKasva() {
        paate.syoEdullisesti(100);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josEiRahaaMaukkaallaLounaallaLounasMaaraEiKasva() {
        paate.syoMaukkaasti(300);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoPalauttaaTrueKunRahaa() {
        assertTrue(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void korttiostoPalauttaaFalseKunEiRahaa() {
        kortti.otaRahaa(900);
        assertFalse(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void korttiostoLisaaMyytyjaEdullisiaLounaitaKunRahaa() {
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoLisaaMyytyjaMaukkaitaLounaitaKunRahaa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiLisaaMyytyjaEdullisiaLounaitaKunEiRahaa() {
        kortti.otaRahaa(900);
        paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiLisaaMyytyjaMaukkaitaLounaitaKunEiRahaa() {
        kortti.otaRahaa(700);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEdullisellaLounaallaVahentaaKortinSaldoa() {
        paate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void korttiostoMaukkaallaLounaallaVahentaaKortinSaldoa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void korttiostoEdullisellaLounaallaEiVahennaSaldoaJosEiRahaa() {
        kortti.otaRahaa(900);
        paate.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void korttiostoMaukkaallaLounaallaEiVahennaSaldoaJosEiRahaa() {
        kortti.otaRahaa(700);
        paate.syoMaukkaasti(kortti);
        assertEquals(300, kortti.saldo());
    }
    
    @Test
    public void kortilleRahaaLadattaessaKassaSaaRahaa() {
        paate.lataaRahaaKortille(kortti, 50);
        assertEquals(100050, paate.kassassaRahaa());
    }
    
    @Test
    public void kortilleRahaaLadattaessaKortinSaldoKasvaa() {
        paate.lataaRahaaKortille(kortti, 50);
        assertEquals(1050, kortti.saldo());
    }
    
    @Test
    public void kortilleNegatiivistaSummaaLadattaessaKassaEiMuutu() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortilleNegatiivistaSummaaLadattaessaKortinSaldoEiMuutu() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
    }

}
