package ua.goit.gojava32.kickstarter.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Deque;
import java.util.LinkedList;

import ua.goit.gojava32.kickstarter.factory.FactoryDB;

public abstract class ConnectionPool {
  private static final int STANDARD_POOL_SIZE = 100;
  private static int size = STANDARD_POOL_SIZE;
  private static int connectionsCreated;
  private static int connectionsUsed;
  private static Deque<Connection> connections = new LinkedList<>();

  public static void setConnectionPoolSize(int size) {
     ConnectionPool.size = size;
  }

  public static synchronized Connection getConnection() {
    if (connectionsCreated > connectionsUsed) {
      Connection con = connections.poll();
      connections.offer(con);
      connectionsUsed++;
      return con;
    } else if (connectionsCreated < size) {
      try {
        Connection con = DriverManager.getConnection(FactoryDB.CONNECTION_STRING);
        connectionsCreated++;
        connectionsUsed++;
        connections.offer(con);
        return con;
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    } else {
      throw new RuntimeException("Maximum connection number exceeded");
    }
  }

  public static synchronized void releaseConnection(Connection con) {
    connections.remove(con);
    connectionsUsed--;
    connections.offerFirst(con);
  }
}
