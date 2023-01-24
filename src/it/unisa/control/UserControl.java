package it.unisa.control;

import it.unisa.model.UserBean;
import it.unisa.model.UserDAO;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserControl
 */
@WebServlet("/UserControl")
public class UserControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  static UserDAO UserDAO = new UserDAO();

  /**
   * @see HttpServlet#HttpServlet()
   */
  public UserControl() {
    super();

  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String username = request.getParameter("usr");
    String nome = request.getParameter("nome");
    String cognome = request.getParameter("cogn");
    String email = request.getParameter("email");
    String password = request.getParameter("pwd");
    String numTelefono = request.getParameter("numTelefono");
    String paeseResidenza = request.getParameter("paeseResidenza");
    LocalDate dataNascita = LocalDate.parse(request.getParameter("dataNascita"));
    UserBean user = new UserBean();
    user.setUsername(username);
    user.setNome(nome);
    user.setCognome(cognome);
    user.setEmail(email);
    user.setPassword(password);
    user.setNumTel(numTelefono);
    user.setPaeseResidenza(paeseResidenza);
    user.setDataNascita(dataNascita);
    UserDAO.doSave(user);

    response.sendRedirect("loginPage.jsp");
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    doGet(request, response);
  }

}
