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
public class QueryProducer 
{
    public static AbstractQueryFactory getFactory()
    {
        return new QueryFactory();
    }
    
}
