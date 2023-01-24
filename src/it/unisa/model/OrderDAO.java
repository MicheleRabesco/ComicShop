package it.unisa.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDAO {
  private static final String TABLE_NAME = "ordine";
  private static final String TABLE_NAME2 = "contenuto";
  private static final int IVA = 22;
  static ResultSet rs = null;
  static int numordine;
  private static DataSource ds;

  static {
    try {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");

      ds = (DataSource) envCtx.lookup("jdbc/comicshop");

    } catch (NamingException e) {
      System.out.println("Error:" + e.getMessage());
    }
  }

  public OrderDAO() {
    super();

  }

  public synchronized void doSave(UserBean bean, Object indirizzo, Object pagamento, Cart cart) {

    PreparedStatement preparedStatement = null;

    String indirizzospedizione = String.valueOf(indirizzo);
    String metododipagamento = String.valueOf(pagamento);
    String insertQuery =
        "INSERT INTO " + TABLE_NAME + " ( costoTotale, indirizzospedizione, dataSpedizione, " + "metododipagamento, username, dataOrdine )" + " VALUES(?,?,?,?,?,?)";

    // connect to DB
    Connection connection = null;
    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertQuery);
      preparedStatement.setFloat(1, cart.getTotalCost());
      preparedStatement.setString(2, indirizzospedizione);
      preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
      preparedStatement.setString(4, metododipagamento);
      preparedStatement.setString(5, bean.getUsername());
      preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));
      preparedStatement.executeUpdate();

    } catch (Exception ex) {
      System.out.println("Inserimento ordine fallito " + ex);
    }

    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (Exception e) {
      }
      preparedStatement = null;
    }

    if (connection != null) {
      try {
        connection.close();
      } catch (Exception e) {
      }

      connection = null;
    }

    try {
      connection = ds.getConnection();
      int autoIncKeyFromFunc = -1;
      preparedStatement = connection.prepareStatement("SELECT LAST_INSERT_ID()");
      rs = preparedStatement.executeQuery();
      if (rs.next()) {
        autoIncKeyFromFunc = rs.getInt(1);
      } else {
        // throw an exception from here
      }
      for (ItemOrder product : cart.getProducts()) {
        preparedStatement =
            connection.prepareStatement("INSERT INTO " + TABLE_NAME2 + " VALUES (?,?,?,?,?,?)");
        preparedStatement.setLong(1, autoIncKeyFromFunc);
        preparedStatement.setInt(2, product.getId());
        preparedStatement.setInt(3, IVA);
        preparedStatement.setFloat(4, product.getPrezzo());
        preparedStatement.setString(5, product.getNome());
        preparedStatement.setInt(6, product.getNumItems());
        preparedStatement.executeUpdate();

      }
    } catch (SQLException e) {
      System.out.print("inserimento ordine fallito");
      e.printStackTrace();
    }

  }

  public synchronized OrderBean getOrderById(String OrderID) {
    PreparedStatement preparedStatement = null;
    Connection connection = null;

    OrderBean bean = new OrderBean();

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE numeroOrdine = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setInt(1, Integer.valueOf(OrderID));

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setId(rs.getLong("numeroOrdine"));
        bean.setCostoTotale(rs.getFloat("costoTotale"));
        bean.setNumOrdine(rs.getLong("numeroOrdine"));
        bean.setDataSpedizione(rs.getDate("dataSpedizione").toLocalDate());
        bean.setMetodoDiPagamento(rs.getString("metododipagamento"));
        bean.setIndirizzoSpedizione(rs.getString("indirizzospedizione"));
        bean.setUsername(rs.getString("username"));
        bean.setDataOrdine(rs.getDate("dataOrdine").toLocalDate());
      }
    } catch (Exception e) {
      System.out.println("Error:" + e.getMessage());
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        System.out.println("Error:" + e.getMessage());
      }

    }

    return bean;

  }

  public synchronized List<OrderBean> getAllOrdersByUser(UserBean user) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<OrderBean> orders = new LinkedList<OrderBean>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? ";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, user.getUsername());
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        OrderBean bean = new OrderBean();
        bean.setId(rs.getLong("numeroOrdine"));
        bean.setCostoTotale(rs.getFloat("costoTotale"));
        bean.setNumOrdine(rs.getLong("numeroOrdine"));
        bean.setDataSpedizione(rs.getDate("dataSpedizione").toLocalDate());
        bean.setMetodoDiPagamento(rs.getString("metododipagamento"));
        bean.setIndirizzoSpedizione(rs.getString("indirizzospedizione"));
        bean.setUsername(rs.getString("username"));
        bean.setDataOrdine(rs.getDate("dataOrdine").toLocalDate());
        orders.add(bean);
      }
    } catch (Exception ex) {
      System.out.println("Errore,impossibile recuperare ordini " + ex);
    } finally {

      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        System.out.println("Errore,impossibile recuperare ordini " + e);
      }

    }

    return orders;
  }

  public synchronized List<OrderBean> getAllOrders() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<OrderBean> orders = new LinkedList<OrderBean>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME;

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        OrderBean bean = new OrderBean();
        bean.setId(rs.getLong("numeroOrdine"));
        bean.setCostoTotale(rs.getFloat("costoTotale"));
        bean.setNumOrdine(rs.getLong("numeroOrdine"));
        bean.setDataSpedizione(rs.getDate("dataSpedizione").toLocalDate());
        bean.setMetodoDiPagamento(rs.getString("metododipagamento"));
        bean.setIndirizzoSpedizione(rs.getString("indirizzospedizione"));
        bean.setUsername(rs.getString("username"));
        bean.setDataOrdine(rs.getDate("dataOrdine").toLocalDate());
        orders.add(bean);
      }
    } catch (Exception ex) {
      System.out.println("Inserimento ordine fallito " + ex);
    } finally {

      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        System.out.println("Errore,impossibile recuperare ordini " + e);
      }

    }

    return orders;

  }

  public synchronized List<OrderBean> getAllOrders(LocalDate startdate, LocalDate enddate) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<OrderBean> orders = new LinkedList<OrderBean>();

    String selectSQL =
        "SELECT * FROM " + TABLE_NAME + " WHERE dataOrdine BETWEEN  CAST(? AS DATE) AND CAST(? AS DATE)";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setDate(1, Date.valueOf(startdate));
      preparedStatement.setDate(2, Date.valueOf(enddate));
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        OrderBean bean = new OrderBean();
        bean.setId(rs.getLong("numeroOrdine"));
        bean.setCostoTotale(rs.getFloat("costoTotale"));
        bean.setNumOrdine(rs.getLong("numeroOrdine"));
        bean.setDataSpedizione(rs.getDate("dataSpedizione").toLocalDate());
        bean.setMetodoDiPagamento(rs.getString("metododipagamento"));
        bean.setIndirizzoSpedizione(rs.getString("indirizzospedizione"));
        bean.setUsername(rs.getString("username"));
        bean.setDataOrdine(rs.getDate("dataOrdine").toLocalDate());
        orders.add(bean);
      }
    } catch (Exception ex) {
      System.out.println("Inserimento ordine fallito " + ex);
    } finally {

      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        System.out.println("Errore,impossibile recuperare ordini " + e);
      }

    }

    return orders;
  }

  public synchronized LinkedList<ContentBean> getContentByOrderId(String OrderID) {
    PreparedStatement preparedStatement = null;
    Connection connection = null;

    LinkedList<ContentBean> products = new LinkedList<ContentBean>();

    String selectSQL =
        "SELECT contenuto.iva, contenuto.prezzoAcquisto, contenuto.nomeArt,contenuto.numArtAcq                                          from contenuto  where contenuto.ordine = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setInt(1, Integer.valueOf(OrderID));

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        ContentBean bean = new ContentBean();
        bean.setIva(rs.getInt("iva"));
        bean.setNomeArt(rs.getString("nomeArt"));
        bean.setNumArtAcq(rs.getInt("numArtAcq"));
        bean.setPrezzoAcquisto(rs.getFloat("prezzoAcquisto"));
        products.add(bean);

      }
    } catch (Exception e) {
      System.out.println("Error:" + e.getMessage());
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        System.out.println("Error:" + e.getMessage());
      }

    }

    return products;
  }

  public List<OrderBean> getAllOrders(String username) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<OrderBean> orders = new LinkedList<OrderBean>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, username);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        OrderBean bean = new OrderBean();
        bean.setId(rs.getLong("numeroOrdine"));
        bean.setCostoTotale(rs.getFloat("costoTotale"));
        bean.setNumOrdine(rs.getLong("numeroOrdine"));
        bean.setDataSpedizione(rs.getDate("dataSpedizione").toLocalDate());
        bean.setMetodoDiPagamento(rs.getString("metododipagamento"));
        bean.setIndirizzoSpedizione(rs.getString("indirizzospedizione"));
        bean.setUsername(rs.getString("username"));
        bean.setDataOrdine(rs.getDate("dataOrdine").toLocalDate());
        orders.add(bean);
      }
    } catch (Exception ex) {
      System.out.println("Inserimento ordine fallito " + ex);
    } finally {

      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        System.out.println("Errore,impossibile recuperare ordini " + e);
      }

    }

    return orders;
  }

}
