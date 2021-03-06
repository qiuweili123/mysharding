package org.sharding.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.sharding.jdbc.DataSourceFactory;
import org.sharding.jdbc.ShardDataSource;
import junit.framework.TestCase;

public class SelectTest  extends TestCase{

	ShardDataSource datasource;
	
	@Override
	protected void setUp() throws Exception {
		String resource = "org/sharding/configuration/configuration.xml";
		datasource = new DataSourceFactory(resource).openDataSource();
	}
	
//	public void testGoble() throws Exception{
//		String sql = "SELECT * FROM t_User";
//		
//		try( Connection connection = datasource.getConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//	        try (ResultSet rs = preparedStatement.executeQuery()) {
//	            while (rs.next()) {
//	                System.out.println(rs.getInt(1));
//	                System.out.println(rs.getInt(2));
//	                System.out.println(rs.getInt(3));
//	            }
//	        }
//		} catch ( Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//	}
	
//	public void test() throws Exception{
//		String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id=? AND o.order_id=?";
//		
//		try( Connection connection = datasource.getConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//			preparedStatement.setInt(1, 1);
//            preparedStatement.setInt(2, 1);
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                while (rs.next()) {
//                    System.out.println(rs.getInt(1));
//                    System.out.println(rs.getInt(2));
//                    System.out.println(rs.getInt(3));
//                }
//            }
//		} catch ( Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//	}
	
	
	public void testOrderBy() throws Exception{
		String sql = "SELECT * FROM t_order o WHERE o.user_id=? AND o.order_id between 1 and 100 order by order_id";
		
		try{
		 	Connection connection = datasource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 1);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	StringBuffer buffer = new StringBuffer();
            	buffer.append("order_id=").append(rs.getInt(1));
            	buffer.append(";user_id=").append(rs.getInt(2));
            	buffer.append(";status=").append(rs.getString(3));
                System.out.println(buffer.toString());
            }
            rs.close();
            preparedStatement.close();
            connection.close();
		} catch ( Exception e) {
			e.printStackTrace(); 
			throw e;
		}
	}
}
