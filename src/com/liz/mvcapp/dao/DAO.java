package com.liz.mvcapp.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.liz.mvcapp.db.JdbcUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;



/**
 * 封装了基本的CRUD的方法，以供子类继续使用
 * 当前DAO直接在方法中获取数据库链接 .
 * 整个DAO采取DBUtils解决方案。
 * @param <T>：当前DAO处理的实体类的类型是什么
 */
public class DAO<T> {
	
	private QueryRunner queryRunner = new QueryRunner();//线程安全的
	
	private Class<T> clazz;
	
	public DAO(){
		Type superClass = getClass().getGenericSuperclass();
		
		if(superClass instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			
			Type [] typeArgs = parameterizedType.getActualTypeArguments();
			if(typeArgs !=null && typeArgs.length > 0){
				if(typeArgs[0] instanceof Class){
					clazz = (Class<T>) typeArgs[0];
				}
			}
		}
	}
	
	/**
	 * 返回某一个字段的值，例如返回某一条记录的customerName,或返回数据表中有多少条记录等。
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql,Object ...args ){
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			return (E) queryRunner.query(connection,sql,new ScalarHandler(), args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
	
		return null;	
	}
	
	/**
	 * 返回T 对应的 List
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getForList(String sql,Object ...args ){
		
	    Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection,sql,new BeanListHandler<>(clazz), args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
		
		return null;
	}
	
	/**
	 * 返回对应的 T 的一个实例类的对象
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql,Object ...args ){
		
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection,sql,new BeanHandler<>(clazz), args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
		
		return null;
	}
	
	/**
	 * 该方法封装了 INSERT、DELETE、UPDATE 操作。
	 * @param sql: SQL 语句
	 * @param args： 填充 SQL 语句的占位符。
	 */
	public Boolean update(String sql, Object ...args){
		Connection connection = null;
		boolean flag = false;
		try {
			connection = JdbcUtils.getConnection();
			queryRunner.update(connection, sql,  args);
			flag = true;
			
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
		
		return flag;
	}
}
