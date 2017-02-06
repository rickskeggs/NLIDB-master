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
public class SimpleQuery implements Query
{
    static String WHITESPACE = "#";
    String tquery = new String();
    private String[] strSql;
    
    SimpleQuery(String tags, String tokens){getQuery(tags,tokens);}
    SimpleQuery(String [] tags, String [] tokens){getQuery(tags,tokens);}
    
    public String getQuery()
    {
        return null;
    }
            
    @Override        
    public String getQuery(String[] tags, String []tokens)
    {
        
        String newToken = null;
        String sql = " Select * from ";
        String sqlTable = null, sqlColumn = null, sqlValue = null;
        
         
        for (int i=0; i<tokens.length; i++)
        { //System.out.println (tags[i] + " = " + tokens[i]);
            
            if (!tags[i].matches("IRR") || !tags[i].equalsIgnoreCase("irr"))
            {
                if (tokens[i].contains(WHITESPACE))
                {
                    newToken = tokens[i].replaceAll(WHITESPACE, " ");
                }
                else
                {
                    newToken = tokens[i];
                }
               
                System.out.println(tags[i] + " = " + newToken);
                
                switch(tags[i])
                {
                    case "AP": sqlTable = "table "+newToken;
                    case "N": sqlColumn=newToken;
                    case "NP": sqlValue=newToken;
                    
                }
                
            }
            
        }
                
        String tquery = sql + " " + sqlTable + " where " +sqlColumn + " = " + sqlValue; 
        System.out.println(tquery);
        return tquery;
    }
    
    @Override
    public String getQuery(String tags, String tokens)
    {
        String newToken = null;
        String sql = " Select * from ";
        String sqlTable = null, sqlColumn = null, sqlValue = null;
        
        if (!tags.matches("IRR") || !tags.equalsIgnoreCase("irr"))
        {
            if (tokens.contains(WHITESPACE))
            {
                    newToken = tokens.replaceAll(WHITESPACE, " ");
            }
            switch(tags)
            {
                case "AP": sqlTable = "table "+newToken;
                case "N": sqlColumn=newToken;
                case "NP": sqlValue=newToken;
                    
            }
        }
        String tquery = sql + " " + sqlTable + " where " +sqlColumn + " = " + sqlValue; 
        System.out.println(tquery);
        return tquery;
    }
}