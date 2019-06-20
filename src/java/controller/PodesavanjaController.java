package controller;

import dao.KategorijaDao;
import dao.KorisnikDao;
import dao.UlogaDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Kategorije;
import model.Korisnici;

public class PodesavanjaController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String LIST = "/podesavanja.jsp";
    private static String INSERT_EDIT_KOR = "/korisnikUnos.jsp";
    private static String INSERT_EDIT_KAT = "/kategorijaUnos.jsp";
    private KorisnikDao daoKor;
    private UlogaDao daoUlo;
    private KategorijaDao daoKat;

    public PodesavanjaController() {
        super();
        daoKor = new KorisnikDao();
        daoUlo = new UlogaDao();
        daoKat = new KategorijaDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        // action pregled podesavanja
        if (action.equalsIgnoreCase("pregled")){
            forward = LIST;
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
            request.setAttribute("uloge", daoUlo.getAllUloge());
            request.setAttribute("kategorije", daoKat.getAllKategorije());
        }
        // action insert korisnik
        else if (action.equalsIgnoreCase("insert")){
            forward = INSERT_EDIT_KOR;
            request.setAttribute("uloge", daoUlo.getAllUloge());
            request.setAttribute("kategorije", daoKat.getAllKategorije());
        }
        // action edit korisnik
        else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_EDIT_KOR;
            int korisnik_id = Integer.parseInt(request.getParameter("korisnik_id"));
            Korisnici korisnik = daoKor.getKorisnikById(korisnik_id);
            request.setAttribute("korisnik", korisnik);
            request.setAttribute("uloge", daoUlo.getAllUloge());
            request.setAttribute("kategorije", daoKat.getAllKategorije());
        }
        // action delete korisnik
        else if (action.equalsIgnoreCase("delete")){
            int korisnik_id = Integer.parseInt(request.getParameter("korisnik_id"));
            daoKor.deleteKorisnik(korisnik_id);
            forward = LIST;
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
            request.setAttribute("uloge", daoUlo.getAllUloge());
            request.setAttribute("kategorije", daoKat.getAllKategorije());
        }
        // action insert kategorija
        else if (action.equalsIgnoreCase("kinsert")){
            forward = INSERT_EDIT_KAT;
        }
        // action edit kategorija
        else if (action.equalsIgnoreCase("kedit")){
            forward = INSERT_EDIT_KAT;
            int kategorija_id = Integer.parseInt(request.getParameter("kategorija_id"));
            Kategorije kategorija = daoKat.getKategorijaById(kategorija_id);
            request.setAttribute("kategorija", kategorija);
        }
        // action delete kategorija
        else if (action.equalsIgnoreCase("kdelete")){
            int kategorija_id = Integer.parseInt(request.getParameter("kategorija_id"));
            daoKat.deleteKategorija(kategorija_id);
            forward = LIST;
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
            request.setAttribute("uloge", daoUlo.getAllUloge());
            request.setAttribute("kategorije", daoKat.getAllKategorije());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // insert korisnik
        String unos = request.getParameter("korisnikIme");
        if(unos != null && !unos.isEmpty()) {
        Korisnici korisnik = new Korisnici();
        korisnik.setIme(request.getParameter("korisnikIme"));
        korisnik.setPrezime(request.getParameter("korisnikPrezime"));
        korisnik.setEmail(request.getParameter("korisnikEmail"));
        korisnik.setUsername(request.getParameter("user"));
        korisnik.setPassword(request.getParameter("pass"));
        korisnik.setKor_kategorija_id(Integer.parseInt(request.getParameter("KorisnikKateg")));
        korisnik.setKor_uloga_id(Integer.parseInt(request.getParameter("KorisnikUloga")));
        String korisnik_id = request.getParameter("korisnikId");
        if(korisnik_id == null || korisnik_id.isEmpty())        
        {
            daoKor.addKorisnik(korisnik);
        }
        else
        {
            korisnik.setKorisnik_id(Integer.parseInt(korisnik_id));
            daoKor.updateKorisnik(korisnik);
        }
        }
        // insert kategorija
        String kunos = request.getParameter("kategorijaNaziv");
        if(kunos != null && !kunos.isEmpty()) {
        Kategorije kategorija = new Kategorije();
        kategorija.setNaziv(request.getParameter("kategorijaNaziv"));
        kategorija.setEmail(request.getParameter("kategorijaEmail"));
        String kategorija_id = request.getParameter("kategorijaId");
        if(kategorija_id == null || kategorija_id.isEmpty())        
        {
            daoKat.addKategorija(kategorija);
        }
        else
        {
            kategorija.setKategorija_id(Integer.parseInt(kategorija_id));
            daoKat.updateKategorija(kategorija);
        }
        } 
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        request.setAttribute("korisnici", daoKor.getAllKorisnici());
        request.setAttribute("uloge", daoUlo.getAllUloge());
        request.setAttribute("kategorije", daoKat.getAllKategorije());
        view.forward(request, response);
    }
}
