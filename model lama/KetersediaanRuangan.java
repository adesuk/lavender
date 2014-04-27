/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.model;

import java.util.Date;

/**
 *
 * @author asri
 */
public class KetersediaanRuangan {
    private Date tanggalRuanganSedia;
    private Ruangan ruangan;
    private SlotWaktu slotWaktu;

    public KetersediaanRuangan() {
    }
    
    public KetersediaanRuangan(Date tanggalRuanganSedia, Ruangan ruangan, SlotWaktu slotWaktu) {
        this.tanggalRuanganSedia = tanggalRuanganSedia;
        this.ruangan = ruangan;
        this.slotWaktu = slotWaktu;
    }

    public Date getTanggalRuanganSedia() {
        return tanggalRuanganSedia;
    }

    public void setTanggalRuanganSedia(Date tanggalRuanganSedia) {
        this.tanggalRuanganSedia = tanggalRuanganSedia;
    }

    public Ruangan getRuangan() {
        return ruangan;
    }

    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }

    public SlotWaktu getSlotWaktu() {
        return slotWaktu;
    }

    public void setSlotWaktu(SlotWaktu slotWaktu) {
        this.slotWaktu = slotWaktu;
    }
}
