package it.unisa.control;

import it.unisa.model.AddressBean;
import it.unisa.model.AddressDAO;
import it.unisa.model.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddressControl
 */
@WebServlet("/AddressControl")
public class AddressControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  static AddressDAO AddressDAO = new AddressDAO();

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddressControl() {
    super();

  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String via = request.getParameter("via");
    int numeroCivico = Integer.parseInt(request.getParameter("numeroCivico"));
    int cap = Integer.parseInt(request.getParameter("cap"));
    String citta = request.getParameter("citta");
    String provincia = request.getParameter("provincia");
    AddressBean address = new AddressBean();
    UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");
    address.setVia(via);
    address.setNumeroCivico(numeroCivico);
    address.setCap(cap);
    address.setCitta(citta);
    address.setProvincia(provincia);
    AddressDAO.doSave(user, address);
    response.sendRedirect("CheckoutPage.jsp");

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    doGet(request, response);
  }

}
