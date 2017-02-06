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
public abstract class AbstractQueryFactory 
{
    public abstract Query getQuery();
    public abstract Query getQuery(String[] tags, String[] tokens); 
    public abstract Query getQuery(String tags, String tokens); 
    

}
