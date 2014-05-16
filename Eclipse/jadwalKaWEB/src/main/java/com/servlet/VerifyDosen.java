package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ac.id.itb.ppl.lavender.bean.DosenBean;
import ac.id.itb.ppl.lavender.bean.TopikBean;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Topik;


/**
 * Servlet implementation class VerifyDosen
 */
@WebServlet("/VerifyDosen")
public class VerifyDosen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    private DosenBean dosenBean;
    @EJB
    private TopikBean topikBean;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	 protected void processRequest(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        try {
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Servlet CountryList</title>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h1>Verify Dosen</h1>");
	            
	         // skenario 1
//	            List<Dosen> listDosen = dosenBean.findAll();
//	            Dosen dosen = null;
//	            for (Dosen d : listDosen) {
//					if (d.getInisialDosen().equals("AI")) {
//						dosen = d;
//					}
//				}
//	            List<KaryaAkhir> listKaryaAkhir = dosen.getKaryaAkhirs();
//	            out.println(dosen);
	            //out.println(dosenBean.getKaryaAkhir(dosen.getInisialDosen()));
//	            out.println(listKaryaAkhir);
	            
	            // skenario 2
	            Dosen d = dosenBean.find("AS");
	        	d.setNamaDosen("Ade Suk");
	            
//	        	List<Topik> listTopik = new ArrayList<Topik>();
//	        	Topik t1 = topikBean.find(new Integer(3));
//	        	Topik t2 = topikBean.find(new Integer(8));
//	        	
//	        	listTopik.add(t1);
//	        	listTopik.add(t2);
//	            
//	        	d.setBidangKeahlian(listTopik);	        	
	        	dosenBean.edit(d);
	        	out.println("Dosen Created");
	        					
//	        	Dosen dosen = dosenBean.find("AS");
//	        	out.println(dosen.getInisialDosen());
//	            for (Topik to : dosen.getBidangKeahlian()) {
//	            	out.println(to.getNamaTopik());
//				}
//	        	
//	            dosenBean.remove(dosen);
//	            
//	            Dosen dosen2 = dosenBean.find("AS");
//	        	if (dosen2 != null) {
//		            out.println(dosen2.getInisialDosen());
//		            for (Topik to : dosen2.getBidangKeahlian()) {
//		            	out.println(to.getNamaTopik());
//					}
//	        	}
	            out.println("</body>");
	            out.println("</html>");
	        } finally {
	            out.close();
	        }
	    }
}
