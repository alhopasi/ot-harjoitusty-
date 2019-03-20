package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }
    
    @Test
    public void kortinSaldoKutsuOikein() {
        assertEquals(1000, kortti.saldo());
    }

    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 10.00", kortti.toString());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(505);
        assertEquals("saldo: 15.05", kortti.toString());
    }

    @Test
    public void rahanOttaminenVahentaaSaldoa() {
        kortti.otaRahaa(250);
        assertEquals("saldo: 7.50", kortti.toString());
    }

    @Test
    public void eiVoiOttaaRahaaJosMeneeMiinukselle() {
        kortti.otaRahaa(1100);
        assertEquals("saldo: 10.00", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenPalauttaaTrueJosRahatRiittavat() {
        assertTrue(kortti.otaRahaa(95));
    }
    
    @Test
    public void rahanOttaminenPalauttaaFalseJosRahatEiRiita() {
        assertFalse(kortti.otaRahaa(1100));
    }
}
