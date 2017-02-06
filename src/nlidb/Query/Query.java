/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlidb.Query;

/**
 *
 * @author rskeggs
 */
//The top abstract class for the query to hang off
public interface Query
{
     public String getQuery();
     public String getQuery(String tags, String tokens);
     public String getQuery(String [] tags, String [] tokens);
}