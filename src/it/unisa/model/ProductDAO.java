package it.unisa.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements ProductModel {

  private static final String TABLE_NAME = "articolo";
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

  @Override
  public synchronized void doSave(ProductBean product) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSQL =
        "INSERT INTO " + ProductDAO.TABLE_NAME + " (id,nome,prezzo, saldo, dataDiUscita,voto,descrizione,peso,quantita,mostra ) VALUES (?, ?, ?, ?,?,?,?,?,?,?)";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setLong(1, product.getId());
      preparedStatement.setString(2, product.getNome());
      preparedStatement.setFloat(3, product.getPrezzo());
      preparedStatement.setInt(4, product.getSaldo());
      preparedStatement.setDate(5, Date.valueOf(product.getDataDiUscita()));
      preparedStatement.setFloat(6, product.getVoto());
      preparedStatement.setString(7, product.getDescrizione());
      preparedStatement.setFloat(8, product.getPeso());
      preparedStatement.setInt(9, product.getQuantita());
      preparedStatement.setBoolean(10, product.isVisible());
      preparedStatement.executeUpdate();

      connection.commit();
    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        if (connection != null)
          connection.close();
      }
    }
  }

  @Override
  public synchronized ProductBean doRetrieveByKey(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ProductBean bean = new ProductBean();

    String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE id = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setId(rs.getInt("id"));
        bean.setNome(rs.getString("nome"));
        bean.setPrezzo(rs.getFloat("prezzo"));
        bean.setSaldo(rs.getInt("saldo"));
        bean.setDataDiUscita(rs.getDate("dataDiUscita").toLocalDate());
        bean.setVoto(rs.getFloat("voto"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setPeso(rs.getFloat("peso"));
        bean.setQuantita(rs.getInt("quantita"));
        bean.setVisible(rs.getBoolean("mostra"));

      }

    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        if (connection != null)
          connection.close();
      }
    }
    return bean;
  }

  @Override
  public synchronized boolean doDelete(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSQL = "DELETE FROM " + ProductDAO.TABLE_NAME + " WHERE id = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setInt(1, id);

      result = preparedStatement.executeUpdate();

    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        if (connection != null)
          connection.close();
      }
    }
    return (result != 0);
  }

  @Override
  public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Collection<ProductBean> products = new LinkedList<ProductBean>();

    String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME;

    if (order != null && !order.equals("")) {
      selectSQL += " ORDER BY " + order;
    }

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        ProductBean bean = new ProductBean();

        bean.setId(rs.getInt("id"));
        bean.setNome(rs.getString("nome"));
        bean.setPrezzo(rs.getFloat("prezzo"));
        bean.setSaldo(rs.getInt("saldo"));
        bean.setDataDiUscita(rs.getDate("dataDiUscita").toLocalDate());
        bean.setVoto(rs.getFloat("voto"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setPeso(rs.getFloat("peso"));
        bean.setQuantita(rs.getInt("quantita"));
        bean.setVisible(rs.getBoolean("mostra"));
        products.add(bean);
      }

    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        if (connection != null)
          connection.close();
      }
    }
    return products;
  }

  public synchronized void Alter(long id, ProductBean product) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String alterStatement =
        "UPDATE `comicshop`.`articolo` SET `id` = ?, `nome` = ?, " + "`prezzo` = ?, `saldo` = ?, `dataDiUscita` = ?, " + "`voto` = ?, `descrizione` = ?, `peso` = ?, `quantita` = ?  " + " WHERE (`id` = ?);";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(alterStatement);
      preparedStatement.setLong(1, product.getId());
      preparedStatement.setString(2, product.getNome());
      preparedStatement.setFloat(3, product.getPrezzo());
      preparedStatement.setInt(4, product.getSaldo());
      preparedStatement.setDate(5, Date.valueOf(product.getDataDiUscita()));
      preparedStatement.setFloat(6, product.getVoto());
      preparedStatement.setString(7, product.getDescrizione());
      preparedStatement.setFloat(8, product.getPeso());
      preparedStatement.setInt(9, product.getQuantita());
      preparedStatement.setLong(10, id);
      preparedStatement.executeUpdate();

    } catch (SQLException e) {

      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();

        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
