package com.jbit.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jbit.entity.User;

public class Test {
	public static void main(String[] args) {
		SqlSession session=null;
		try {
			//1,读取配置文件
			InputStream in=Resources.getResourceAsStream("mybatis.xml");
			//2,创建一个会话工厂建造对象
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			//3,创建会话工厂对象
			SqlSessionFactory factory = builder.build(in);
			//4,开启会话
			session=factory.openSession();
			//5,进行数据库操作
//			List<User> list=session.selectList("com.jbit.entity.User.selectAll");
//			for (User user : list) {
//				System.out.println(user);
//			}
			
			//根据id查
//			User user=session.selectOne("com.jbit.entity.User.selectId",4);
//			System.out.println(user);
			
			//增加
//			User user=new User("老六", "555555");
//			int insert = session.insert("com.jbit.entity.User.insertUser",user);
//			System.out.println("影响行数："+insert);
			
			//删除
//			int delete = session.delete("com.jbit.entity.User.deleteUser",5);
//			System.out.println(delete);
			
			//修改
//			User user=new User(4,"乔四","444");
//			int update = session.update("com.jbit.entity.User.updateUser",user);
//			System.out.println(update);
			
			//重点：selectList()和selectMap()以及selectCursor()游标
			//可以将返回值映射成pojo，也可以是零散键值对数据
//			List<Map<String, String>> list=session.selectList("com.jbit.entity.User.selectList",3);
//			for (Map<String, String> map : list) {
//				System.out.println(map);
//			}
			
			//查询多行多列数据
			//将每行数据的列名作为key，每行数据的列的值作为value得到一个map集合
			//key-value
			//value又是一个map集合
			//{key={k1=v1,...}}
//			Map<Long, Map<String, String>>map=session.selectMap("com.jbit.entity.User.selectList",3,"userId");
//			for (Entry<Long,Map<String, String>> entry : map.entrySet()) {
//				System.out.println(entry.getKey()+"\t"+entry.getValue());
//			}
			
			//将数据查询到游标中，根据需要再进行逐个提取
			Cursor<User> userCursor=session.selectCursor("com.jbit.entity.User.selectCursor");
			Iterator<User> iterator=userCursor.iterator();
			//创建一个新的集合用于存放从游标中提取出来的User对象
			List<User>users=new ArrayList<User>();
			while(iterator.hasNext()) {
				for (int i = 0; i < 3 && iterator.hasNext(); i++) {
					users.add(iterator.next());
				}
				//显示users集合数据	
				for (User user : users) {
					System.out.println(user);
				}
				System.out.println("-------------------------------------------");
				//显示完成每次从游标中提取的4个对象就清空集合
				users.clear();
			}
			
			//提交事务
			session.commit();
		} catch (IOException e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			//释放资源关闭
			if (session!=null) {
				session.close();
			}
		}
	}
}
