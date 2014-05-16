package ac.id.itb.ppl.lavender.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import ac.id.itb.ppl.lavender.bean.remote.TopikRemote;
import ac.id.itb.ppl.lavender.model.Topik;

/**
 * Session Bean implementation class TopikBean
 */
@Stateless
@LocalBean
public class TopikBean extends AbstractBean<Topik> implements TopikRemote {

	SessionContext ctx;
	
	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
	
	// JDBC
//	@Resource(mappedName="jdbc/jadwalDB")
//	private DataSource dataSource;
	
	public TopikBean() {
		super(Topik.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

//	public Connection getConnection() throws SQLException {       
//		Connection con = dataSource.getConnection();       
//		return con;
//	}
	
	public List<String> findAllBidangTopik() {
		List<String> bidangs = em.createQuery("select t.bidang from Topik t group by t.bidang").getResultList();
		List<String> bs = new ArrayList<String>();
		for (String str : bidangs) {
			if (str!= null) {
				bs.add(str);
			}
		}
		return bs;
	}
	
	public List<Topik> findTopikByBidang(String bidang) {		
		
//		List<Topik> topiks = em.createNamedQuery("Topik.findAll").getResultList();
		
//		Query q = 
//		List<Topik> topiks = q.getResultList();
		
		List<Topik> topiks = em.createQuery("SELECT t FROM Topik t WHERE t.bidang = ?1").setParameter(1, bidang).getResultList();
		
		//JDBC
		
//		List<Topik> topiks = new ArrayList<Topik>();
//		
//		try {
//			java.sql.Connection con = getConnection();
//			PreparedStatement stat = con.prepareStatement("SELECT * FROM Topik t WHERE t.bidang = ?");
//			stat.setString(1, bidang);
//			ResultSet rs = stat.executeQuery();
//			while(rs.next()) {
//				Topik t = new Topik();
//				t.setIdTopik(rs.getInt("ID_TOPIK"));
//				t.setNamaTopik(rs.getString("NAMA_TOPIK"));
//				topiks.add(t);
//			}			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		System.out.println("findTopikByBidang : "+ topiks.size());
		
		return topiks;
	}
	
	
	public List<Topik> findTopikByBidangAndDosen(String inisialDosen, String bidang) {		
		List<Topik> topiks = em.createQuery("select t from Topik t join t.dosenList d where d.inisialDosen=?1 "
				+ "and t.bidang=?2")
				.setParameter(1, inisialDosen)
				.setParameter(2, bidang).getResultList();
		
		return topiks;
	}

}
