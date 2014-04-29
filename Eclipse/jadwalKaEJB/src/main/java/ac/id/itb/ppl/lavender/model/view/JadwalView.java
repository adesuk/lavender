package ac.id.itb.ppl.lavender.model.view;

public class JadwalView {
	private long idJadwal;
	private String hariTanggal;
	private String pukul;
	private String nim;
	private String nama;
	private String judul;
	private String pembimbing1;
	private String pembimbing2;
	private String penguji1;
	private String penguji2;
	private String ruangan;
	
	public JadwalView(long idJadwal, String hariTanggal, String pukul,
			String nim, String nama, String judul, String pembimbing1,
			String pembimbing2, String penguji1, String penguji2, String ruangan) {
		super();
		this.idJadwal = idJadwal;
		this.hariTanggal = hariTanggal;
		this.pukul = pukul;
		this.nim = nim;
		this.nama = nama;
		this.judul = judul;
		this.pembimbing1 = pembimbing1;
		this.pembimbing2 = pembimbing2;
		this.penguji1 = penguji1;
		this.penguji2 = penguji2;
		this.ruangan = ruangan;
	}
	
	public long getIdJadwal() {
		return idJadwal;
	}
	public void setIdJadwal(long idJadwal) {
		this.idJadwal = idJadwal;
	}
	public String getHariTanggal() {
		return hariTanggal;
	}
	public void setHariTanggal(String hariTanggal) {
		this.hariTanggal = hariTanggal;
	}
	public String getPukul() {
		return pukul;
	}
	public void setPukul(String pukul) {
		this.pukul = pukul;
	}
	public String getNim() {
		return nim;
	}
	public void setNim(String nim) {
		this.nim = nim;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getJudul() {
		return judul;
	}
	public void setJudul(String judul) {
		this.judul = judul;
	}
	public String getPembimbing1() {
		return pembimbing1;
	}
	public void setPembimbing1(String pembimbing1) {
		this.pembimbing1 = pembimbing1;
	}
	public String getPembimbing2() {
		return pembimbing2;
	}
	public void setPembimbing2(String pembimbing2) {
		this.pembimbing2 = pembimbing2;
	}
	public String getPenguji1() {
		return penguji1;
	}
	public void setPenguji1(String penguji1) {
		this.penguji1 = penguji1;
	}
	public String getPenguji2() {
		return penguji2;
	}
	public void setPenguji2(String penguji2) {
		this.penguji2 = penguji2;
	}
	public String getRuangan() {
		return ruangan;
	}
	public void setRuangan(String ruangan) {
		this.ruangan = ruangan;
	}
	
	
}
