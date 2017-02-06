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
public class QueryFactory extends AbstractQueryFactory
{
    @Override
    public Query getQuery()
    {
        return null;
    }
    
    public Query getQuery(String tags, String tokens)
    {
        return new SimpleQuery(tags, tokens);        
    }
    
    public Query getQuery(String tags[], String tokens[])
    {
        return new SimpleQuery(tags, tokens);
    }
}
