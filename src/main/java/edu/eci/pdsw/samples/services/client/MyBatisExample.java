/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.services.client;



import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException, ParseException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();
        ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        System.out.println(cm.consultarClientes());
        System.out.println("");
        System.out.println("");
        System.out.println("---------CONSULTANDO UN CLIENTE--------");
        System.out.println(cm.consultarCliente(1026585664));
        //agregamos una pelicula rentada al cliente 2104835
        
        //FUNCIONA!! SE COMENTA PORQ SOLO SE PUEDE INSERTAR UNA VEZ
        String string = "2017-03-29";
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date date1 = format.parse(string);
        string = "2017-03-31";
        Date date2 = format.parse(string);
        //cm.agregarItemRentadoACliente(2104835,100000000,date1,date2);
        
        //agregamos un nuevo item FUNCIONA! se comenta para no insertar doble
        SqlSession sqlsss = sessionfact.openSession();
        ItemMapper im=sqlsss.getMapper(ItemMapper.class);
        //im.insertarItem(new Item(new TipoItem(1998,"accion, sangre, comedia"),17998,"kvn y srg","muy larga de accion",date1,30000,"blurayX5","accion"));
        
        System.out.println("");
        System.out.println("");
        System.out.println("---------CONSULTANDO UN ITEM--------");
        //consultamos un item
        System.out.println(im.consultarItem(17998));
        System.out.println("");
        System.out.println("");
        System.out.println("---------CONSULTANDO UN ITEM--------");
        //consultamos un item
        System.out.println(im.getItems());
                
        sqlsss.commit();
        sqlsss.close();
        sqlss.commit();
        sqlss.close();
    }
}
